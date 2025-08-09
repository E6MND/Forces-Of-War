package org.chromium.content.browser;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.os.RemoteException;
import android.util.Log;
import android.util.Pair;
import android.view.Surface;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.chromium.base.CalledByNative;
import org.chromium.base.JNINamespace;
import org.chromium.base.ThreadUtils;
import org.chromium.base.TraceEvent;
import org.chromium.base.VisibleForTesting;
import org.chromium.base.library_loader.Linker;
import org.chromium.content.app.ChildProcessService;
import org.chromium.content.app.ChromiumLinkerParams;
import org.chromium.content.app.PrivilegedProcessService;
import org.chromium.content.app.SandboxedProcessService;
import org.chromium.content.browser.ChildProcessConnection;
import org.chromium.content.common.IChildProcessCallback;
import org.chromium.content.common.SurfaceWrapper;

@JNINamespace("content")
public class ChildProcessLauncher {
    static final /* synthetic */ boolean $assertionsDisabled;
    static final int CALLBACK_FOR_GPU_PROCESS = 1;
    static final int CALLBACK_FOR_RENDERER_PROCESS = 2;
    static final int CALLBACK_FOR_UNKNOWN_PROCESS = 0;
    static final int MAX_REGISTERED_PRIVILEGED_SERVICES = 3;
    static final int MAX_REGISTERED_SANDBOXED_SERVICES = 20;
    private static final int NULL_PROCESS_HANDLE = 0;
    private static final String SWITCH_GPU_PROCESS = "gpu-process";
    private static final String SWITCH_PPAPI_BROKER_PROCESS = "ppapi-broker";
    private static final String SWITCH_PROCESS_TYPE = "type";
    private static final String SWITCH_RENDERER_PROCESS = "renderer";
    private static final String TAG = "ChildProcessLauncher";
    /* access modifiers changed from: private */
    public static BindingManager sBindingManager = BindingManagerImpl.createBindingManager();
    private static boolean sConnectionAllocated = false;
    private static boolean sLinkerInitialized = false;
    private static long sLinkerLoadAddress = 0;
    private static final ChildConnectionAllocator sPrivilegedChildConnectionAllocator = new ChildConnectionAllocator(false);
    private static final ChildConnectionAllocator sSandboxedChildConnectionAllocator = new ChildConnectionAllocator(true);
    /* access modifiers changed from: private */
    public static Map<Integer, ChildProcessConnection> sServiceMap = new ConcurrentHashMap();
    private static ChildProcessConnection sSpareSandboxedConnection = null;
    private static Map<Pair<Integer, Integer>, Surface> sSurfaceTextureSurfaceMap = new ConcurrentHashMap();
    /* access modifiers changed from: private */
    public static Map<Integer, Surface> sViewSurfaceMap = new ConcurrentHashMap();

    /* access modifiers changed from: private */
    public static native void nativeEstablishSurfacePeer(int i, Surface surface, int i2, int i3);

    private static native boolean nativeIsSingleProcess();

    /* access modifiers changed from: private */
    public static native void nativeOnChildProcessStarted(long j, int i);

    static {
        boolean z;
        if (!ChildProcessLauncher.class.desiredAssertionStatus()) {
            z = true;
        } else {
            z = false;
        }
        $assertionsDisabled = z;
    }

    private static class ChildConnectionAllocator {
        static final /* synthetic */ boolean $assertionsDisabled = (!ChildProcessLauncher.class.desiredAssertionStatus());
        private Class<? extends ChildProcessService> mChildClass;
        private final ChildProcessConnection[] mChildProcessConnections;
        private final Object mConnectionLock = new Object();
        private final ArrayList<Integer> mFreeConnectionIndices;
        private final boolean mInSandbox;

        public ChildConnectionAllocator(boolean inSandbox) {
            int numChildServices = inSandbox ? 20 : 3;
            this.mChildProcessConnections = new ChildProcessConnectionImpl[numChildServices];
            this.mFreeConnectionIndices = new ArrayList<>(numChildServices);
            for (int i = 0; i < numChildServices; i++) {
                this.mFreeConnectionIndices.add(Integer.valueOf(i));
            }
            setServiceClass(inSandbox ? SandboxedProcessService.class : PrivilegedProcessService.class);
            this.mInSandbox = inSandbox;
        }

        public void setServiceClass(Class<? extends ChildProcessService> childClass) {
            this.mChildClass = childClass;
        }

        public ChildProcessConnection allocate(Context context, ChildProcessConnection.DeathCallback deathCallback, ChromiumLinkerParams chromiumLinkerParams) {
            ChildProcessConnection childProcessConnection;
            synchronized (this.mConnectionLock) {
                if (this.mFreeConnectionIndices.isEmpty()) {
                    Log.e(ChildProcessLauncher.TAG, "Ran out of services to allocate.");
                    if (!$assertionsDisabled) {
                        throw new AssertionError();
                    }
                    childProcessConnection = null;
                } else {
                    int slot = this.mFreeConnectionIndices.remove(0).intValue();
                    if ($assertionsDisabled || this.mChildProcessConnections[slot] == null) {
                        this.mChildProcessConnections[slot] = new ChildProcessConnectionImpl(context, slot, this.mInSandbox, deathCallback, this.mChildClass, chromiumLinkerParams);
                        Log.d(ChildProcessLauncher.TAG, "Allocator allocated a connection, sandbox: " + this.mInSandbox + ", slot: " + slot);
                        childProcessConnection = this.mChildProcessConnections[slot];
                    } else {
                        throw new AssertionError();
                    }
                }
            }
            return childProcessConnection;
        }

        public void free(ChildProcessConnection connection) {
            synchronized (this.mConnectionLock) {
                int slot = connection.getServiceNumber();
                if (this.mChildProcessConnections[slot] != connection) {
                    Log.e(ChildProcessLauncher.TAG, "Unable to find connection to free in slot: " + slot + " already occupied by service: " + (this.mChildProcessConnections[slot] == null ? -1 : this.mChildProcessConnections[slot].getServiceNumber()));
                    if (!$assertionsDisabled) {
                        throw new AssertionError();
                    }
                } else {
                    this.mChildProcessConnections[slot] = null;
                    if ($assertionsDisabled || !this.mFreeConnectionIndices.contains(Integer.valueOf(slot))) {
                        this.mFreeConnectionIndices.add(Integer.valueOf(slot));
                        Log.d(ChildProcessLauncher.TAG, "Allocator freed a connection, sandbox: " + this.mInSandbox + ", slot: " + slot);
                    } else {
                        throw new AssertionError();
                    }
                }
            }
        }

        /* access modifiers changed from: package-private */
        @VisibleForTesting
        public int allocatedConnectionsCountForTesting() {
            return this.mChildProcessConnections.length - this.mFreeConnectionIndices.size();
        }
    }

    public static void setChildProcessClass(Class<? extends SandboxedProcessService> sandboxedServiceClass, Class<? extends PrivilegedProcessService> privilegedServiceClass) {
        if ($assertionsDisabled || !sConnectionAllocated) {
            sSandboxedChildConnectionAllocator.setServiceClass(sandboxedServiceClass);
            sPrivilegedChildConnectionAllocator.setServiceClass(privilegedServiceClass);
            return;
        }
        throw new AssertionError();
    }

    private static ChildConnectionAllocator getConnectionAllocator(boolean inSandbox) {
        return inSandbox ? sSandboxedChildConnectionAllocator : sPrivilegedChildConnectionAllocator;
    }

    private static ChildProcessConnection allocateConnection(Context context, boolean inSandbox, ChromiumLinkerParams chromiumLinkerParams) {
        ChildProcessConnection.DeathCallback deathCallback = new ChildProcessConnection.DeathCallback() {
            public void onChildProcessDied(ChildProcessConnection connection) {
                if (connection.getPid() != 0) {
                    ChildProcessLauncher.stop(connection.getPid());
                } else {
                    ChildProcessLauncher.freeConnection(connection);
                }
            }
        };
        sConnectionAllocated = true;
        return getConnectionAllocator(inSandbox).allocate(context, deathCallback, chromiumLinkerParams);
    }

    private static ChromiumLinkerParams getLinkerParamsForNewConnection() {
        if (!sLinkerInitialized) {
            if (Linker.isUsed()) {
                sLinkerLoadAddress = Linker.getBaseLoadAddress();
                if (sLinkerLoadAddress == 0) {
                    Log.i(TAG, "Shared RELRO support disabled!");
                }
            }
            sLinkerInitialized = true;
        }
        if (sLinkerLoadAddress == 0) {
            return null;
        }
        return new ChromiumLinkerParams(sLinkerLoadAddress, true, Linker.getTestRunnerClassName());
    }

    private static ChildProcessConnection allocateBoundConnection(Context context, String[] commandLine, boolean inSandbox) {
        ChildProcessConnection connection = allocateConnection(context, inSandbox, getLinkerParamsForNewConnection());
        if (connection != null) {
            connection.start(commandLine);
        }
        return connection;
    }

    /* access modifiers changed from: private */
    public static void freeConnection(ChildProcessConnection connection) {
        getConnectionAllocator(connection.isInSandbox()).free(connection);
    }

    @VisibleForTesting
    public static void setBindingManagerForTesting(BindingManager manager) {
        sBindingManager = manager;
    }

    @CalledByNative
    private static boolean isOomProtected(int pid) {
        return sBindingManager.isOomProtected(pid);
    }

    @CalledByNative
    private static void registerViewSurface(int surfaceId, Surface surface) {
        sViewSurfaceMap.put(Integer.valueOf(surfaceId), surface);
    }

    @CalledByNative
    private static void unregisterViewSurface(int surfaceId) {
        sViewSurfaceMap.remove(Integer.valueOf(surfaceId));
    }

    /* access modifiers changed from: private */
    public static void registerSurfaceTextureSurface(int surfaceTextureId, int clientId, Surface surface) {
        sSurfaceTextureSurfaceMap.put(new Pair<>(Integer.valueOf(surfaceTextureId), Integer.valueOf(clientId)), surface);
    }

    /* access modifiers changed from: private */
    public static void unregisterSurfaceTextureSurface(int surfaceTextureId, int clientId) {
        Surface surface = sSurfaceTextureSurfaceMap.remove(new Pair<>(Integer.valueOf(surfaceTextureId), Integer.valueOf(clientId)));
        if (surface != null) {
            if ($assertionsDisabled || surface.isValid()) {
                surface.release();
                return;
            }
            throw new AssertionError();
        }
    }

    @CalledByNative
    private static void createSurfaceTextureSurface(int surfaceTextureId, int clientId, SurfaceTexture surfaceTexture) {
        registerSurfaceTextureSurface(surfaceTextureId, clientId, new Surface(surfaceTexture));
    }

    @CalledByNative
    private static void destroySurfaceTextureSurface(int surfaceTextureId, int clientId) {
        unregisterSurfaceTextureSurface(surfaceTextureId, clientId);
    }

    /* access modifiers changed from: private */
    @CalledByNative
    public static SurfaceWrapper getSurfaceTextureSurface(int surfaceTextureId, int clientId) {
        Surface surface = sSurfaceTextureSurfaceMap.get(new Pair<>(Integer.valueOf(surfaceTextureId), Integer.valueOf(clientId)));
        if (surface == null) {
            Log.e(TAG, "Invalid Id for surface texture.");
            return null;
        } else if ($assertionsDisabled || surface.isValid()) {
            return new SurfaceWrapper(surface);
        } else {
            throw new AssertionError();
        }
    }

    @CalledByNative
    public static void setInForeground(int pid, boolean inForeground) {
        sBindingManager.setInForeground(pid, inForeground);
    }

    public static void determinedVisibility(int pid) {
        sBindingManager.determinedVisibility(pid);
    }

    public static void onSentToBackground() {
        sBindingManager.onSentToBackground();
    }

    public static void onBroughtToForeground() {
        sBindingManager.onBroughtToForeground();
    }

    public static void warmUp(Context context) {
        synchronized (ChildProcessLauncher.class) {
            if (!$assertionsDisabled && ThreadUtils.runningOnUiThread()) {
                throw new AssertionError();
            } else if (sSpareSandboxedConnection == null) {
                sSpareSandboxedConnection = allocateBoundConnection(context, (String[]) null, true);
            }
        }
    }

    private static String getSwitchValue(String[] commandLine, String switchKey) {
        if (commandLine == null || switchKey == null) {
            return null;
        }
        String switchKeyPrefix = "--" + switchKey + "=";
        for (String command : commandLine) {
            if (command != null && command.startsWith(switchKeyPrefix)) {
                return command.substring(switchKeyPrefix.length());
            }
        }
        return null;
    }

    @CalledByNative
    static void start(Context context, String[] commandLine, int childProcessId, int[] fileIds, int[] fileFds, boolean[] fileAutoClose, long clientContext) {
        TraceEvent.begin();
        if ($assertionsDisabled || (fileIds.length == fileFds.length && fileFds.length == fileAutoClose.length)) {
            FileDescriptorInfo[] filesToBeMapped = new FileDescriptorInfo[fileFds.length];
            for (int i = 0; i < fileFds.length; i++) {
                filesToBeMapped[i] = new FileDescriptorInfo(fileIds[i], fileFds[i], fileAutoClose[i]);
            }
            if ($assertionsDisabled || clientContext != 0) {
                int callbackType = 0;
                boolean inSandbox = true;
                String processType = getSwitchValue(commandLine, "type");
                if (SWITCH_RENDERER_PROCESS.equals(processType)) {
                    callbackType = 2;
                } else if (SWITCH_GPU_PROCESS.equals(processType)) {
                    callbackType = 1;
                } else if (SWITCH_PPAPI_BROKER_PROCESS.equals(processType)) {
                    inSandbox = false;
                }
                ChildProcessConnection allocatedConnection = null;
                synchronized (ChildProcessLauncher.class) {
                    if (inSandbox) {
                        allocatedConnection = sSpareSandboxedConnection;
                        sSpareSandboxedConnection = null;
                    }
                }
                if (allocatedConnection == null && (allocatedConnection = allocateBoundConnection(context, commandLine, inSandbox)) == null) {
                    nativeOnChildProcessStarted(clientContext, 0);
                    Log.e(TAG, "Allocation of new service failed.");
                    TraceEvent.end();
                    return;
                }
                Log.d(TAG, "Setting up connection to process: slot=" + allocatedConnection.getServiceNumber());
                triggerConnectionSetup(allocatedConnection, commandLine, childProcessId, filesToBeMapped, callbackType, clientContext);
                TraceEvent.end();
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    @VisibleForTesting
    static void triggerConnectionSetup(final ChildProcessConnection connection, String[] commandLine, int childProcessId, FileDescriptorInfo[] filesToBeMapped, int callbackType, final long clientContext) {
        ChildProcessConnection childProcessConnection = connection;
        String[] strArr = commandLine;
        FileDescriptorInfo[] fileDescriptorInfoArr = filesToBeMapped;
        childProcessConnection.setupConnection(strArr, fileDescriptorInfoArr, createCallback(childProcessId, callbackType), new ChildProcessConnection.ConnectionCallback() {
            public void onConnected(int pid) {
                Log.d(ChildProcessLauncher.TAG, "on connect callback, pid=" + pid + " context=" + clientContext);
                if (pid != 0) {
                    ChildProcessLauncher.sBindingManager.addNewConnection(pid, connection);
                    ChildProcessLauncher.sServiceMap.put(Integer.valueOf(pid), connection);
                }
                if (clientContext != 0) {
                    ChildProcessLauncher.nativeOnChildProcessStarted(clientContext, pid);
                }
            }
        }, Linker.getSharedRelros());
    }

    @CalledByNative
    static void stop(int pid) {
        Log.d(TAG, "stopping child connection: pid=" + pid);
        ChildProcessConnection connection = sServiceMap.remove(Integer.valueOf(pid));
        if (connection == null) {
            logPidWarning(pid, "Tried to stop non-existent connection");
            return;
        }
        sBindingManager.clearConnection(pid);
        connection.stop();
        freeConnection(connection);
    }

    private static IChildProcessCallback createCallback(final int childProcessId, final int callbackType) {
        return new IChildProcessCallback.Stub() {
            static final /* synthetic */ boolean $assertionsDisabled = (!ChildProcessLauncher.class.desiredAssertionStatus());

            public void establishSurfacePeer(int pid, Surface surface, int primaryID, int secondaryID) {
                if (callbackType != 1) {
                    Log.e(ChildProcessLauncher.TAG, "Illegal callback for non-GPU process.");
                } else {
                    ChildProcessLauncher.nativeEstablishSurfacePeer(pid, surface, primaryID, secondaryID);
                }
            }

            public SurfaceWrapper getViewSurface(int surfaceId) {
                if (callbackType != 1) {
                    Log.e(ChildProcessLauncher.TAG, "Illegal callback for non-GPU process.");
                    return null;
                }
                Surface surface = (Surface) ChildProcessLauncher.sViewSurfaceMap.get(Integer.valueOf(surfaceId));
                if (surface == null) {
                    Log.e(ChildProcessLauncher.TAG, "Invalid surfaceId.");
                    return null;
                } else if ($assertionsDisabled || surface.isValid()) {
                    return new SurfaceWrapper(surface);
                } else {
                    throw new AssertionError();
                }
            }

            public void registerSurfaceTextureSurface(int surfaceTextureId, int clientId, Surface surface) {
                if (callbackType != 1) {
                    Log.e(ChildProcessLauncher.TAG, "Illegal callback for non-GPU process.");
                } else {
                    ChildProcessLauncher.registerSurfaceTextureSurface(surfaceTextureId, clientId, surface);
                }
            }

            public void unregisterSurfaceTextureSurface(int surfaceTextureId, int clientId) {
                if (callbackType != 1) {
                    Log.e(ChildProcessLauncher.TAG, "Illegal callback for non-GPU process.");
                } else {
                    ChildProcessLauncher.unregisterSurfaceTextureSurface(surfaceTextureId, clientId);
                }
            }

            public SurfaceWrapper getSurfaceTextureSurface(int surfaceTextureId) {
                if (callbackType == 2) {
                    return ChildProcessLauncher.getSurfaceTextureSurface(surfaceTextureId, childProcessId);
                }
                Log.e(ChildProcessLauncher.TAG, "Illegal callback for non-renderer process.");
                return null;
            }
        };
    }

    static void logPidWarning(int pid, String message) {
        if (pid > 0 && !nativeIsSingleProcess()) {
            Log.w(TAG, message + ", pid=" + pid);
        }
    }

    @VisibleForTesting
    static ChildProcessConnection allocateBoundConnectionForTesting(Context context) {
        return allocateBoundConnection(context, (String[]) null, true);
    }

    @VisibleForTesting
    static int allocatedConnectionsCountForTesting() {
        return sSandboxedChildConnectionAllocator.allocatedConnectionsCountForTesting();
    }

    @VisibleForTesting
    static int connectedServicesCountForTesting() {
        return sServiceMap.size();
    }

    @VisibleForTesting
    public static boolean crashProcessForTesting(int pid) {
        if (sServiceMap.get(Integer.valueOf(pid)) == null) {
            return false;
        }
        try {
            ((ChildProcessConnectionImpl) sServiceMap.get(Integer.valueOf(pid))).crashServiceForTesting();
            return true;
        } catch (RemoteException e) {
            return false;
        }
    }
}
