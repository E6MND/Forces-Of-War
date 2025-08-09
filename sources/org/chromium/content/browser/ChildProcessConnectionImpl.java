package org.chromium.content.browser;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.IBinder;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.util.Log;
import java.io.IOException;
import org.chromium.base.CpuFeatures;
import org.chromium.base.ThreadUtils;
import org.chromium.base.TraceEvent;
import org.chromium.base.VisibleForTesting;
import org.chromium.base.library_loader.Linker;
import org.chromium.content.app.ChildProcessService;
import org.chromium.content.app.ChromiumLinkerParams;
import org.chromium.content.browser.ChildProcessConnection;
import org.chromium.content.common.IChildProcessCallback;
import org.chromium.content.common.IChildProcessService;

public class ChildProcessConnectionImpl implements ChildProcessConnection {
    static final /* synthetic */ boolean $assertionsDisabled = (!ChildProcessConnectionImpl.class.desiredAssertionStatus());
    private static final String TAG = "ChildProcessConnection";
    /* access modifiers changed from: private */
    public ChildProcessConnection.ConnectionCallback mConnectionCallback;
    /* access modifiers changed from: private */
    public ConnectionParams mConnectionParams;
    /* access modifiers changed from: private */
    public final Context mContext;
    /* access modifiers changed from: private */
    public final ChildProcessConnection.DeathCallback mDeathCallback;
    private final boolean mInSandbox;
    /* access modifiers changed from: private */
    public ChildServiceConnection mInitialBinding = null;
    /* access modifiers changed from: private */
    public ChromiumLinkerParams mLinkerParams = null;
    /* access modifiers changed from: private */
    public final Object mLock = new Object();
    /* access modifiers changed from: private */
    public int mPid = 0;
    /* access modifiers changed from: private */
    public IChildProcessService mService = null;
    /* access modifiers changed from: private */
    public final Class<? extends ChildProcessService> mServiceClass;
    /* access modifiers changed from: private */
    public boolean mServiceConnectComplete = false;
    /* access modifiers changed from: private */
    public boolean mServiceDisconnected = false;
    /* access modifiers changed from: private */
    public final int mServiceNumber;
    /* access modifiers changed from: private */
    public ChildServiceConnection mStrongBinding = null;
    private int mStrongBindingCount = 0;
    private ChildServiceConnection mWaivedBinding = null;
    /* access modifiers changed from: private */
    public boolean mWasOomProtected = false;

    private static class ConnectionParams {
        final IChildProcessCallback mCallback;
        final String[] mCommandLine;
        final FileDescriptorInfo[] mFilesToBeMapped;
        final Bundle mSharedRelros;

        ConnectionParams(String[] commandLine, FileDescriptorInfo[] filesToBeMapped, IChildProcessCallback callback, Bundle sharedRelros) {
            this.mCommandLine = commandLine;
            this.mFilesToBeMapped = filesToBeMapped;
            this.mCallback = callback;
            this.mSharedRelros = sharedRelros;
        }
    }

    private class ChildServiceConnection implements ServiceConnection {
        private final int mBindFlags;
        private boolean mBound = false;

        private Intent createServiceBindIntent() {
            Intent intent = new Intent();
            intent.setClassName(ChildProcessConnectionImpl.this.mContext, ChildProcessConnectionImpl.this.mServiceClass.getName() + ChildProcessConnectionImpl.this.mServiceNumber);
            intent.setPackage(ChildProcessConnectionImpl.this.mContext.getPackageName());
            return intent;
        }

        public ChildServiceConnection(int bindFlags) {
            this.mBindFlags = bindFlags;
        }

        /* access modifiers changed from: package-private */
        public boolean bind(String[] commandLine) {
            if (!this.mBound) {
                TraceEvent.begin();
                Intent intent = createServiceBindIntent();
                if (commandLine != null) {
                    intent.putExtra(ChildProcessConnection.EXTRA_COMMAND_LINE, commandLine);
                }
                if (ChildProcessConnectionImpl.this.mLinkerParams != null) {
                    ChildProcessConnectionImpl.this.mLinkerParams.addIntentExtras(intent);
                }
                this.mBound = ChildProcessConnectionImpl.this.mContext.bindService(intent, this, this.mBindFlags);
                TraceEvent.end();
            }
            return this.mBound;
        }

        /* access modifiers changed from: package-private */
        public void unbind() {
            if (this.mBound) {
                ChildProcessConnectionImpl.this.mContext.unbindService(this);
                this.mBound = false;
            }
        }

        /* access modifiers changed from: package-private */
        public boolean isBound() {
            return this.mBound;
        }

        public void onServiceConnected(ComponentName className, IBinder service) {
            synchronized (ChildProcessConnectionImpl.this.mLock) {
                if (!ChildProcessConnectionImpl.this.mServiceConnectComplete) {
                    TraceEvent.begin();
                    boolean unused = ChildProcessConnectionImpl.this.mServiceConnectComplete = true;
                    IChildProcessService unused2 = ChildProcessConnectionImpl.this.mService = IChildProcessService.Stub.asInterface(service);
                    if (ChildProcessConnectionImpl.this.mConnectionParams != null) {
                        ChildProcessConnectionImpl.this.doConnectionSetupLocked();
                    }
                    TraceEvent.end();
                }
            }
        }

        public void onServiceDisconnected(ComponentName className) {
            boolean z = false;
            synchronized (ChildProcessConnectionImpl.this.mLock) {
                if (!ChildProcessConnectionImpl.this.mServiceDisconnected) {
                    boolean unused = ChildProcessConnectionImpl.this.mServiceDisconnected = true;
                    ChildProcessConnectionImpl childProcessConnectionImpl = ChildProcessConnectionImpl.this;
                    if (ChildProcessConnectionImpl.this.mInitialBinding.isBound() || ChildProcessConnectionImpl.this.mStrongBinding.isBound()) {
                        z = true;
                    }
                    boolean unused2 = childProcessConnectionImpl.mWasOomProtected = z;
                    Log.w(ChildProcessConnectionImpl.TAG, "onServiceDisconnected (crash or killed by oom): pid=" + ChildProcessConnectionImpl.this.mPid);
                    ChildProcessConnectionImpl.this.stop();
                    ChildProcessConnectionImpl.this.mDeathCallback.onChildProcessDied(ChildProcessConnectionImpl.this);
                    if (ChildProcessConnectionImpl.this.mConnectionCallback != null) {
                        ChildProcessConnectionImpl.this.mConnectionCallback.onConnected(0);
                    }
                    ChildProcessConnection.ConnectionCallback unused3 = ChildProcessConnectionImpl.this.mConnectionCallback = null;
                }
            }
        }
    }

    ChildProcessConnectionImpl(Context context, int number, boolean inSandbox, ChildProcessConnection.DeathCallback deathCallback, Class<? extends ChildProcessService> serviceClass, ChromiumLinkerParams chromiumLinkerParams) {
        this.mContext = context;
        this.mServiceNumber = number;
        this.mInSandbox = inSandbox;
        this.mDeathCallback = deathCallback;
        this.mServiceClass = serviceClass;
        this.mLinkerParams = chromiumLinkerParams;
        this.mInitialBinding = new ChildServiceConnection(1);
        this.mStrongBinding = new ChildServiceConnection(65);
        this.mWaivedBinding = new ChildServiceConnection(33);
    }

    public int getServiceNumber() {
        return this.mServiceNumber;
    }

    public boolean isInSandbox() {
        return this.mInSandbox;
    }

    public IChildProcessService getService() {
        IChildProcessService iChildProcessService;
        synchronized (this.mLock) {
            iChildProcessService = this.mService;
        }
        return iChildProcessService;
    }

    public int getPid() {
        int i;
        synchronized (this.mLock) {
            i = this.mPid;
        }
        return i;
    }

    public void start(String[] commandLine) {
        synchronized (this.mLock) {
            TraceEvent.begin();
            if (!$assertionsDisabled && ThreadUtils.runningOnUiThread()) {
                throw new AssertionError();
            } else if ($assertionsDisabled || this.mConnectionParams == null) {
                if (!this.mInitialBinding.bind(commandLine)) {
                    Log.e(TAG, "Failed to establish the service connection.");
                    this.mDeathCallback.onChildProcessDied(this);
                } else {
                    this.mWaivedBinding.bind((String[]) null);
                }
                TraceEvent.end();
            } else {
                throw new AssertionError("setupConnection() called before start() in ChildProcessConnectionImpl.");
            }
        }
    }

    public void setupConnection(String[] commandLine, FileDescriptorInfo[] filesToBeMapped, IChildProcessCallback processCallback, ChildProcessConnection.ConnectionCallback connectionCallback, Bundle sharedRelros) {
        synchronized (this.mLock) {
            if (!$assertionsDisabled && this.mConnectionParams != null) {
                throw new AssertionError();
            } else if (this.mServiceDisconnected) {
                Log.w(TAG, "Tried to setup a connection that already disconnected.");
                connectionCallback.onConnected(0);
            } else {
                TraceEvent.begin();
                this.mConnectionCallback = connectionCallback;
                this.mConnectionParams = new ConnectionParams(commandLine, filesToBeMapped, processCallback, sharedRelros);
                if (this.mServiceConnectComplete) {
                    doConnectionSetupLocked();
                }
                TraceEvent.end();
            }
        }
    }

    public void stop() {
        synchronized (this.mLock) {
            this.mInitialBinding.unbind();
            this.mStrongBinding.unbind();
            this.mWaivedBinding.unbind();
            this.mStrongBindingCount = 0;
            if (this.mService != null) {
                this.mService = null;
            }
            this.mConnectionParams = null;
        }
    }

    /* access modifiers changed from: private */
    public void doConnectionSetupLocked() {
        TraceEvent.begin();
        if (!$assertionsDisabled && (!this.mServiceConnectComplete || this.mService == null)) {
            throw new AssertionError();
        } else if ($assertionsDisabled || this.mConnectionParams != null) {
            Bundle bundle = new Bundle();
            bundle.putStringArray(ChildProcessConnection.EXTRA_COMMAND_LINE, this.mConnectionParams.mCommandLine);
            FileDescriptorInfo[] fileInfos = this.mConnectionParams.mFilesToBeMapped;
            ParcelFileDescriptor[] parcelFiles = new ParcelFileDescriptor[fileInfos.length];
            for (int i = 0; i < fileInfos.length; i++) {
                if (fileInfos[i].mFd == -1) {
                    Log.e(TAG, "Invalid FD (id=" + fileInfos[i].mId + ") for process connection, " + "aborting connection.");
                    return;
                }
                String idName = ChildProcessConnection.EXTRA_FILES_PREFIX + i + "_id";
                String fdName = ChildProcessConnection.EXTRA_FILES_PREFIX + i + ChildProcessConnection.EXTRA_FILES_FD_SUFFIX;
                if (fileInfos[i].mAutoClose) {
                    parcelFiles[i] = ParcelFileDescriptor.adoptFd(fileInfos[i].mFd);
                } else {
                    try {
                        parcelFiles[i] = ParcelFileDescriptor.fromFd(fileInfos[i].mFd);
                    } catch (IOException e) {
                        Log.e(TAG, "Invalid FD provided for process connection, aborting connection.", e);
                        return;
                    }
                }
                bundle.putParcelable(fdName, parcelFiles[i]);
                bundle.putInt(idName, fileInfos[i].mId);
            }
            bundle.putInt(ChildProcessConnection.EXTRA_CPU_COUNT, CpuFeatures.getCount());
            bundle.putLong(ChildProcessConnection.EXTRA_CPU_FEATURES, CpuFeatures.getMask());
            bundle.putBundle(Linker.EXTRA_LINKER_SHARED_RELROS, this.mConnectionParams.mSharedRelros);
            try {
                this.mPid = this.mService.setupConnection(bundle, this.mConnectionParams.mCallback);
                if (!$assertionsDisabled && this.mPid == 0) {
                    throw new AssertionError("Child service claims to be run by a process of pid=0.");
                }
            } catch (RemoteException re) {
                Log.e(TAG, "Failed to setup connection.", re);
            }
            try {
                for (ParcelFileDescriptor parcelFile : parcelFiles) {
                    if (parcelFile != null) {
                        parcelFile.close();
                    }
                }
            } catch (IOException ioe) {
                Log.w(TAG, "Failed to close FD.", ioe);
            }
            this.mConnectionParams = null;
            if (this.mConnectionCallback != null) {
                this.mConnectionCallback.onConnected(this.mPid);
            }
            this.mConnectionCallback = null;
            TraceEvent.end();
        } else {
            throw new AssertionError();
        }
    }

    public boolean isInitialBindingBound() {
        boolean isBound;
        synchronized (this.mLock) {
            isBound = this.mInitialBinding.isBound();
        }
        return isBound;
    }

    public boolean isStrongBindingBound() {
        boolean isBound;
        synchronized (this.mLock) {
            isBound = this.mStrongBinding.isBound();
        }
        return isBound;
    }

    public void removeInitialBinding() {
        synchronized (this.mLock) {
            this.mInitialBinding.unbind();
        }
    }

    public boolean isOomProtectedOrWasWhenDied() {
        boolean z;
        synchronized (this.mLock) {
            if (this.mServiceDisconnected) {
                z = this.mWasOomProtected;
            } else {
                z = this.mInitialBinding.isBound() || this.mStrongBinding.isBound();
            }
        }
        return z;
    }

    public void dropOomBindings() {
        synchronized (this.mLock) {
            this.mInitialBinding.unbind();
            this.mStrongBindingCount = 0;
            this.mStrongBinding.unbind();
        }
    }

    public void addStrongBinding() {
        synchronized (this.mLock) {
            if (this.mService == null) {
                Log.w(TAG, "The connection is not bound for " + this.mPid);
                return;
            }
            if (this.mStrongBindingCount == 0) {
                this.mStrongBinding.bind((String[]) null);
            }
            this.mStrongBindingCount++;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void removeStrongBinding() {
        /*
            r4 = this;
            java.lang.Object r1 = r4.mLock
            monitor-enter(r1)
            org.chromium.content.common.IChildProcessService r0 = r4.mService     // Catch:{ all -> 0x0031 }
            if (r0 != 0) goto L_0x0023
            java.lang.String r0 = "ChildProcessConnection"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0031 }
            r2.<init>()     // Catch:{ all -> 0x0031 }
            java.lang.String r3 = "The connection is not bound for "
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ all -> 0x0031 }
            int r3 = r4.mPid     // Catch:{ all -> 0x0031 }
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ all -> 0x0031 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x0031 }
            android.util.Log.w(r0, r2)     // Catch:{ all -> 0x0031 }
            monitor-exit(r1)     // Catch:{ all -> 0x0031 }
        L_0x0022:
            return
        L_0x0023:
            boolean r0 = $assertionsDisabled     // Catch:{ all -> 0x0031 }
            if (r0 != 0) goto L_0x0034
            int r0 = r4.mStrongBindingCount     // Catch:{ all -> 0x0031 }
            if (r0 > 0) goto L_0x0034
            java.lang.AssertionError r0 = new java.lang.AssertionError     // Catch:{ all -> 0x0031 }
            r0.<init>()     // Catch:{ all -> 0x0031 }
            throw r0     // Catch:{ all -> 0x0031 }
        L_0x0031:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0031 }
            throw r0
        L_0x0034:
            int r0 = r4.mStrongBindingCount     // Catch:{ all -> 0x0031 }
            int r0 = r0 + -1
            r4.mStrongBindingCount = r0     // Catch:{ all -> 0x0031 }
            int r0 = r4.mStrongBindingCount     // Catch:{ all -> 0x0031 }
            if (r0 != 0) goto L_0x0043
            org.chromium.content.browser.ChildProcessConnectionImpl$ChildServiceConnection r0 = r4.mStrongBinding     // Catch:{ all -> 0x0031 }
            r0.unbind()     // Catch:{ all -> 0x0031 }
        L_0x0043:
            monitor-exit(r1)     // Catch:{ all -> 0x0031 }
            goto L_0x0022
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.content.browser.ChildProcessConnectionImpl.removeStrongBinding():void");
    }

    @VisibleForTesting
    public boolean crashServiceForTesting() throws RemoteException {
        try {
            this.mService.crashIntentionallyForTesting();
            return false;
        } catch (DeadObjectException e) {
            return true;
        }
    }

    @VisibleForTesting
    public boolean isConnected() {
        return this.mService != null;
    }
}
