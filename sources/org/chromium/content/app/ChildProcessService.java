package org.chromium.content.app;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.SurfaceTexture;
import android.os.Bundle;
import android.os.IBinder;
import android.os.ParcelFileDescriptor;
import android.os.Process;
import android.os.RemoteException;
import android.util.Log;
import android.view.Surface;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;
import org.chromium.base.CalledByNative;
import org.chromium.base.JNINamespace;
import org.chromium.base.library_loader.Linker;
import org.chromium.content.browser.ChildProcessConnection;
import org.chromium.content.common.IChildProcessCallback;
import org.chromium.content.common.IChildProcessService;

@JNINamespace("content")
public class ChildProcessService extends Service {
    private static final String MAIN_THREAD_NAME = "ChildProcessMain";
    private static final String TAG = "ChildProcessService";
    /* access modifiers changed from: private */
    public static AtomicReference<Context> sContext = new AtomicReference<>((Object) null);
    private final IChildProcessService.Stub mBinder = new IChildProcessService.Stub() {
        static final /* synthetic */ boolean $assertionsDisabled = (!ChildProcessService.class.desiredAssertionStatus());

        public int setupConnection(Bundle args, IChildProcessCallback callback) {
            IChildProcessCallback unused = ChildProcessService.this.mCallback = callback;
            synchronized (ChildProcessService.this.mMainThread) {
                if (ChildProcessService.this.mCommandLineParams == null) {
                    String[] unused2 = ChildProcessService.this.mCommandLineParams = args.getStringArray(ChildProcessConnection.EXTRA_COMMAND_LINE);
                }
                if ($assertionsDisabled || ChildProcessService.this.mCommandLineParams != null) {
                    int unused3 = ChildProcessService.this.mCpuCount = args.getInt(ChildProcessConnection.EXTRA_CPU_COUNT);
                    long unused4 = ChildProcessService.this.mCpuFeatures = args.getLong(ChildProcessConnection.EXTRA_CPU_FEATURES);
                    if ($assertionsDisabled || ChildProcessService.this.mCpuCount > 0) {
                        ArrayList unused5 = ChildProcessService.this.mFileIds = new ArrayList();
                        ArrayList unused6 = ChildProcessService.this.mFileFds = new ArrayList();
                        int i = 0;
                        while (true) {
                            ParcelFileDescriptor parcel = (ParcelFileDescriptor) args.getParcelable(ChildProcessConnection.EXTRA_FILES_PREFIX + i + ChildProcessConnection.EXTRA_FILES_FD_SUFFIX);
                            if (parcel == null) {
                                Bundle sharedRelros = args.getBundle(Linker.EXTRA_LINKER_SHARED_RELROS);
                                if (sharedRelros != null) {
                                    Linker.useSharedRelros(sharedRelros);
                                }
                                ChildProcessService.this.mMainThread.notifyAll();
                            } else {
                                ChildProcessService.this.mFileFds.add(parcel);
                                ChildProcessService.this.mFileIds.add(Integer.valueOf(args.getInt(ChildProcessConnection.EXTRA_FILES_PREFIX + i + "_id")));
                                i++;
                            }
                        }
                    } else {
                        throw new AssertionError();
                    }
                } else {
                    throw new AssertionError();
                }
            }
            return Process.myPid();
        }

        public void crashIntentionallyForTesting() {
            Process.killProcess(Process.myPid());
        }
    };
    /* access modifiers changed from: private */
    public IChildProcessCallback mCallback;
    /* access modifiers changed from: private */
    public String[] mCommandLineParams;
    /* access modifiers changed from: private */
    public int mCpuCount;
    /* access modifiers changed from: private */
    public long mCpuFeatures;
    /* access modifiers changed from: private */
    public ArrayList<ParcelFileDescriptor> mFileFds;
    /* access modifiers changed from: private */
    public ArrayList<Integer> mFileIds;
    /* access modifiers changed from: private */
    public boolean mIsBound = false;
    /* access modifiers changed from: private */
    public boolean mLibraryInitialized = false;
    /* access modifiers changed from: private */
    public ChromiumLinkerParams mLinkerParams;
    /* access modifiers changed from: private */
    public Thread mMainThread;

    /* access modifiers changed from: private */
    public static native void nativeExitChildProcess();

    /* access modifiers changed from: private */
    public static native void nativeInitChildProcess(Context context, ChildProcessService childProcessService, int[] iArr, int[] iArr2, int i, long j);

    private native void nativeShutdownMainThread();

    static Context getContext() {
        return sContext.get();
    }

    public void onCreate() {
        Log.i(TAG, "Creating new ChildProcessService pid=" + Process.myPid());
        if (sContext.get() != null) {
            Log.e(TAG, "ChildProcessService created again in process!");
        }
        sContext.set(this);
        super.onCreate();
        this.mMainThread = new Thread(new Runnable() {
            static final /* synthetic */ boolean $assertionsDisabled = (!ChildProcessService.class.desiredAssertionStatus());

            /* JADX WARNING: Code restructure failed: missing block: B:11:0x001c, code lost:
                r0 = move-exception;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:12:0x001d, code lost:
                android.util.Log.w(org.chromium.content.app.ChildProcessService.TAG, "ChildProcessMain startup failed: " + r0);
             */
            /* JADX WARNING: Code restructure failed: missing block: B:73:0x011a, code lost:
                r0 = move-exception;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:74:0x011b, code lost:
                if (r11 != false) goto L_0x011d;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:75:0x011d, code lost:
                android.util.Log.w(org.chromium.content.app.ChildProcessService.TAG, "Failed to load native library with shared RELRO, retrying without");
                r10 = true;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:76:0x0126, code lost:
                android.util.Log.e(org.chromium.content.app.ChildProcessService.TAG, "Failed to load native library", r0);
             */
            /* JADX WARNING: Code restructure failed: missing block: B:77:0x012e, code lost:
                r0 = move-exception;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:78:0x012f, code lost:
                android.util.Log.e(org.chromium.content.app.ChildProcessService.TAG, "Failed to load native library on retry", r0);
             */
            /* JADX WARNING: Code restructure failed: missing block: B:94:?, code lost:
                return;
             */
            /* JADX WARNING: Failed to process nested try/catch */
            /* JADX WARNING: Removed duplicated region for block: B:11:0x001c A[ExcHandler: InterruptedException (r0v1 'e' java.lang.InterruptedException A[CUSTOM_DECLARE]), Splitter:B:0:0x0000] */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void run() {
                /*
                    r13 = this;
                    org.chromium.content.app.ChildProcessService r1 = org.chromium.content.app.ChildProcessService.this     // Catch:{ InterruptedException -> 0x001c, ProcessInitException -> 0x0063 }
                    java.lang.Thread r2 = r1.mMainThread     // Catch:{ InterruptedException -> 0x001c, ProcessInitException -> 0x0063 }
                    monitor-enter(r2)     // Catch:{ InterruptedException -> 0x001c, ProcessInitException -> 0x0063 }
                L_0x0007:
                    org.chromium.content.app.ChildProcessService r1 = org.chromium.content.app.ChildProcessService.this     // Catch:{ all -> 0x0019 }
                    java.lang.String[] r1 = r1.mCommandLineParams     // Catch:{ all -> 0x0019 }
                    if (r1 != 0) goto L_0x0036
                    org.chromium.content.app.ChildProcessService r1 = org.chromium.content.app.ChildProcessService.this     // Catch:{ all -> 0x0019 }
                    java.lang.Thread r1 = r1.mMainThread     // Catch:{ all -> 0x0019 }
                    r1.wait()     // Catch:{ all -> 0x0019 }
                    goto L_0x0007
                L_0x0019:
                    r1 = move-exception
                    monitor-exit(r2)     // Catch:{ all -> 0x0019 }
                    throw r1     // Catch:{ InterruptedException -> 0x001c, ProcessInitException -> 0x0063 }
                L_0x001c:
                    r0 = move-exception
                    java.lang.String r1 = "ChildProcessService"
                    java.lang.StringBuilder r2 = new java.lang.StringBuilder
                    r2.<init>()
                    java.lang.String r5 = "ChildProcessMain startup failed: "
                    java.lang.StringBuilder r2 = r2.append(r5)
                    java.lang.StringBuilder r2 = r2.append(r0)
                    java.lang.String r2 = r2.toString()
                    android.util.Log.w(r1, r2)
                L_0x0035:
                    return
                L_0x0036:
                    monitor-exit(r2)     // Catch:{ all -> 0x0019 }
                    org.chromium.content.app.ChildProcessService r1 = org.chromium.content.app.ChildProcessService.this     // Catch:{ InterruptedException -> 0x001c, ProcessInitException -> 0x0063 }
                    java.lang.String[] r1 = r1.mCommandLineParams     // Catch:{ InterruptedException -> 0x001c, ProcessInitException -> 0x0063 }
                    org.chromium.base.CommandLine.init(r1)     // Catch:{ InterruptedException -> 0x001c, ProcessInitException -> 0x0063 }
                    boolean r12 = org.chromium.base.library_loader.Linker.isUsed()     // Catch:{ InterruptedException -> 0x001c, ProcessInitException -> 0x0063 }
                    r11 = 0
                    if (r12 == 0) goto L_0x00b1
                    org.chromium.content.app.ChildProcessService r1 = org.chromium.content.app.ChildProcessService.this     // Catch:{ InterruptedException -> 0x001c, ProcessInitException -> 0x0063 }
                    java.lang.Thread r2 = r1.mMainThread     // Catch:{ InterruptedException -> 0x001c, ProcessInitException -> 0x0063 }
                    monitor-enter(r2)     // Catch:{ InterruptedException -> 0x001c, ProcessInitException -> 0x0063 }
                L_0x004e:
                    org.chromium.content.app.ChildProcessService r1 = org.chromium.content.app.ChildProcessService.this     // Catch:{ all -> 0x0060 }
                    boolean r1 = r1.mIsBound     // Catch:{ all -> 0x0060 }
                    if (r1 != 0) goto L_0x007d
                    org.chromium.content.app.ChildProcessService r1 = org.chromium.content.app.ChildProcessService.this     // Catch:{ all -> 0x0060 }
                    java.lang.Thread r1 = r1.mMainThread     // Catch:{ all -> 0x0060 }
                    r1.wait()     // Catch:{ all -> 0x0060 }
                    goto L_0x004e
                L_0x0060:
                    r1 = move-exception
                    monitor-exit(r2)     // Catch:{ all -> 0x0060 }
                    throw r1     // Catch:{ InterruptedException -> 0x001c, ProcessInitException -> 0x0063 }
                L_0x0063:
                    r0 = move-exception
                    java.lang.String r1 = "ChildProcessService"
                    java.lang.StringBuilder r2 = new java.lang.StringBuilder
                    r2.<init>()
                    java.lang.String r5 = "ChildProcessMain startup failed: "
                    java.lang.StringBuilder r2 = r2.append(r5)
                    java.lang.StringBuilder r2 = r2.append(r0)
                    java.lang.String r2 = r2.toString()
                    android.util.Log.w(r1, r2)
                    goto L_0x0035
                L_0x007d:
                    monitor-exit(r2)     // Catch:{ all -> 0x0060 }
                    boolean r1 = $assertionsDisabled     // Catch:{ InterruptedException -> 0x001c, ProcessInitException -> 0x0063 }
                    if (r1 != 0) goto L_0x0090
                    org.chromium.content.app.ChildProcessService r1 = org.chromium.content.app.ChildProcessService.this     // Catch:{ InterruptedException -> 0x001c, ProcessInitException -> 0x0063 }
                    org.chromium.content.app.ChromiumLinkerParams r1 = r1.mLinkerParams     // Catch:{ InterruptedException -> 0x001c, ProcessInitException -> 0x0063 }
                    if (r1 != 0) goto L_0x0090
                    java.lang.AssertionError r1 = new java.lang.AssertionError     // Catch:{ InterruptedException -> 0x001c, ProcessInitException -> 0x0063 }
                    r1.<init>()     // Catch:{ InterruptedException -> 0x001c, ProcessInitException -> 0x0063 }
                    throw r1     // Catch:{ InterruptedException -> 0x001c, ProcessInitException -> 0x0063 }
                L_0x0090:
                    org.chromium.content.app.ChildProcessService r1 = org.chromium.content.app.ChildProcessService.this     // Catch:{ InterruptedException -> 0x001c, ProcessInitException -> 0x0063 }
                    org.chromium.content.app.ChromiumLinkerParams r1 = r1.mLinkerParams     // Catch:{ InterruptedException -> 0x001c, ProcessInitException -> 0x0063 }
                    boolean r1 = r1.mWaitForSharedRelro     // Catch:{ InterruptedException -> 0x001c, ProcessInitException -> 0x0063 }
                    if (r1 == 0) goto L_0x0116
                    r11 = 1
                    org.chromium.content.app.ChildProcessService r1 = org.chromium.content.app.ChildProcessService.this     // Catch:{ InterruptedException -> 0x001c, ProcessInitException -> 0x0063 }
                    org.chromium.content.app.ChromiumLinkerParams r1 = r1.mLinkerParams     // Catch:{ InterruptedException -> 0x001c, ProcessInitException -> 0x0063 }
                    long r6 = r1.mBaseLoadAddress     // Catch:{ InterruptedException -> 0x001c, ProcessInitException -> 0x0063 }
                    org.chromium.base.library_loader.Linker.initServiceProcess(r6)     // Catch:{ InterruptedException -> 0x001c, ProcessInitException -> 0x0063 }
                L_0x00a6:
                    org.chromium.content.app.ChildProcessService r1 = org.chromium.content.app.ChildProcessService.this     // Catch:{ InterruptedException -> 0x001c, ProcessInitException -> 0x0063 }
                    org.chromium.content.app.ChromiumLinkerParams r1 = r1.mLinkerParams     // Catch:{ InterruptedException -> 0x001c, ProcessInitException -> 0x0063 }
                    java.lang.String r1 = r1.mTestRunnerClassName     // Catch:{ InterruptedException -> 0x001c, ProcessInitException -> 0x0063 }
                    org.chromium.base.library_loader.Linker.setTestRunnerClassName(r1)     // Catch:{ InterruptedException -> 0x001c, ProcessInitException -> 0x0063 }
                L_0x00b1:
                    r9 = 0
                    org.chromium.base.CommandLine r1 = org.chromium.base.CommandLine.getInstance()     // Catch:{ InterruptedException -> 0x001c, ProcessInitException -> 0x0063 }
                    java.lang.String r2 = "renderer-wait-for-java-debugger"
                    boolean r1 = r1.hasSwitch(r2)     // Catch:{ InterruptedException -> 0x001c, ProcessInitException -> 0x0063 }
                    if (r1 == 0) goto L_0x00c1
                    android.os.Debug.waitForDebugger()     // Catch:{ InterruptedException -> 0x001c, ProcessInitException -> 0x0063 }
                L_0x00c1:
                    r10 = 0
                    org.chromium.content.app.ChildProcessService r1 = org.chromium.content.app.ChildProcessService.this     // Catch:{ ProcessInitException -> 0x011a, InterruptedException -> 0x001c }
                    android.content.Context r1 = r1.getApplicationContext()     // Catch:{ ProcessInitException -> 0x011a, InterruptedException -> 0x001c }
                    r2 = 0
                    org.chromium.base.library_loader.LibraryLoader.loadNow(r1, r2)     // Catch:{ ProcessInitException -> 0x011a, InterruptedException -> 0x001c }
                    r9 = 1
                L_0x00cd:
                    if (r9 != 0) goto L_0x00df
                    if (r11 == 0) goto L_0x00df
                    org.chromium.base.library_loader.Linker.disableSharedRelros()     // Catch:{ InterruptedException -> 0x001c, ProcessInitException -> 0x0063 }
                    org.chromium.content.app.ChildProcessService r1 = org.chromium.content.app.ChildProcessService.this     // Catch:{ ProcessInitException -> 0x012e, InterruptedException -> 0x001c }
                    android.content.Context r1 = r1.getApplicationContext()     // Catch:{ ProcessInitException -> 0x012e, InterruptedException -> 0x001c }
                    r2 = 0
                    org.chromium.base.library_loader.LibraryLoader.loadNow(r1, r2)     // Catch:{ ProcessInitException -> 0x012e, InterruptedException -> 0x001c }
                    r9 = 1
                L_0x00df:
                    if (r9 != 0) goto L_0x00e5
                    r1 = -1
                    java.lang.System.exit(r1)     // Catch:{ InterruptedException -> 0x001c, ProcessInitException -> 0x0063 }
                L_0x00e5:
                    org.chromium.base.library_loader.LibraryLoader.registerRendererProcessHistogram(r11, r10)     // Catch:{ InterruptedException -> 0x001c, ProcessInitException -> 0x0063 }
                    org.chromium.base.library_loader.LibraryLoader.initialize()     // Catch:{ InterruptedException -> 0x001c, ProcessInitException -> 0x0063 }
                    org.chromium.content.app.ChildProcessService r1 = org.chromium.content.app.ChildProcessService.this     // Catch:{ InterruptedException -> 0x001c, ProcessInitException -> 0x0063 }
                    java.lang.Thread r2 = r1.mMainThread     // Catch:{ InterruptedException -> 0x001c, ProcessInitException -> 0x0063 }
                    monitor-enter(r2)     // Catch:{ InterruptedException -> 0x001c, ProcessInitException -> 0x0063 }
                    org.chromium.content.app.ChildProcessService r1 = org.chromium.content.app.ChildProcessService.this     // Catch:{ all -> 0x0113 }
                    r5 = 1
                    boolean unused = r1.mLibraryInitialized = r5     // Catch:{ all -> 0x0113 }
                    org.chromium.content.app.ChildProcessService r1 = org.chromium.content.app.ChildProcessService.this     // Catch:{ all -> 0x0113 }
                    java.lang.Thread r1 = r1.mMainThread     // Catch:{ all -> 0x0113 }
                    r1.notifyAll()     // Catch:{ all -> 0x0113 }
                L_0x0101:
                    org.chromium.content.app.ChildProcessService r1 = org.chromium.content.app.ChildProcessService.this     // Catch:{ all -> 0x0113 }
                    java.util.ArrayList r1 = r1.mFileIds     // Catch:{ all -> 0x0113 }
                    if (r1 != 0) goto L_0x0137
                    org.chromium.content.app.ChildProcessService r1 = org.chromium.content.app.ChildProcessService.this     // Catch:{ all -> 0x0113 }
                    java.lang.Thread r1 = r1.mMainThread     // Catch:{ all -> 0x0113 }
                    r1.wait()     // Catch:{ all -> 0x0113 }
                    goto L_0x0101
                L_0x0113:
                    r1 = move-exception
                    monitor-exit(r2)     // Catch:{ all -> 0x0113 }
                    throw r1     // Catch:{ InterruptedException -> 0x001c, ProcessInitException -> 0x0063 }
                L_0x0116:
                    org.chromium.base.library_loader.Linker.disableSharedRelros()     // Catch:{ InterruptedException -> 0x001c, ProcessInitException -> 0x0063 }
                    goto L_0x00a6
                L_0x011a:
                    r0 = move-exception
                    if (r11 == 0) goto L_0x0126
                    java.lang.String r1 = "ChildProcessService"
                    java.lang.String r2 = "Failed to load native library with shared RELRO, retrying without"
                    android.util.Log.w(r1, r2)     // Catch:{ InterruptedException -> 0x001c, ProcessInitException -> 0x0063 }
                    r10 = 1
                    goto L_0x00cd
                L_0x0126:
                    java.lang.String r1 = "ChildProcessService"
                    java.lang.String r2 = "Failed to load native library"
                    android.util.Log.e(r1, r2, r0)     // Catch:{ InterruptedException -> 0x001c, ProcessInitException -> 0x0063 }
                    goto L_0x00cd
                L_0x012e:
                    r0 = move-exception
                    java.lang.String r1 = "ChildProcessService"
                    java.lang.String r2 = "Failed to load native library on retry"
                    android.util.Log.e(r1, r2, r0)     // Catch:{ InterruptedException -> 0x001c, ProcessInitException -> 0x0063 }
                    goto L_0x00df
                L_0x0137:
                    monitor-exit(r2)     // Catch:{ all -> 0x0113 }
                    boolean r1 = $assertionsDisabled     // Catch:{ InterruptedException -> 0x001c, ProcessInitException -> 0x0063 }
                    if (r1 != 0) goto L_0x0158
                    org.chromium.content.app.ChildProcessService r1 = org.chromium.content.app.ChildProcessService.this     // Catch:{ InterruptedException -> 0x001c, ProcessInitException -> 0x0063 }
                    java.util.ArrayList r1 = r1.mFileIds     // Catch:{ InterruptedException -> 0x001c, ProcessInitException -> 0x0063 }
                    int r1 = r1.size()     // Catch:{ InterruptedException -> 0x001c, ProcessInitException -> 0x0063 }
                    org.chromium.content.app.ChildProcessService r2 = org.chromium.content.app.ChildProcessService.this     // Catch:{ InterruptedException -> 0x001c, ProcessInitException -> 0x0063 }
                    java.util.ArrayList r2 = r2.mFileFds     // Catch:{ InterruptedException -> 0x001c, ProcessInitException -> 0x0063 }
                    int r2 = r2.size()     // Catch:{ InterruptedException -> 0x001c, ProcessInitException -> 0x0063 }
                    if (r1 == r2) goto L_0x0158
                    java.lang.AssertionError r1 = new java.lang.AssertionError     // Catch:{ InterruptedException -> 0x001c, ProcessInitException -> 0x0063 }
                    r1.<init>()     // Catch:{ InterruptedException -> 0x001c, ProcessInitException -> 0x0063 }
                    throw r1     // Catch:{ InterruptedException -> 0x001c, ProcessInitException -> 0x0063 }
                L_0x0158:
                    org.chromium.content.app.ChildProcessService r1 = org.chromium.content.app.ChildProcessService.this     // Catch:{ InterruptedException -> 0x001c, ProcessInitException -> 0x0063 }
                    java.util.ArrayList r1 = r1.mFileIds     // Catch:{ InterruptedException -> 0x001c, ProcessInitException -> 0x0063 }
                    int r1 = r1.size()     // Catch:{ InterruptedException -> 0x001c, ProcessInitException -> 0x0063 }
                    int[] r3 = new int[r1]     // Catch:{ InterruptedException -> 0x001c, ProcessInitException -> 0x0063 }
                    org.chromium.content.app.ChildProcessService r1 = org.chromium.content.app.ChildProcessService.this     // Catch:{ InterruptedException -> 0x001c, ProcessInitException -> 0x0063 }
                    java.util.ArrayList r1 = r1.mFileFds     // Catch:{ InterruptedException -> 0x001c, ProcessInitException -> 0x0063 }
                    int r1 = r1.size()     // Catch:{ InterruptedException -> 0x001c, ProcessInitException -> 0x0063 }
                    int[] r4 = new int[r1]     // Catch:{ InterruptedException -> 0x001c, ProcessInitException -> 0x0063 }
                    r8 = 0
                L_0x0171:
                    org.chromium.content.app.ChildProcessService r1 = org.chromium.content.app.ChildProcessService.this     // Catch:{ InterruptedException -> 0x001c, ProcessInitException -> 0x0063 }
                    java.util.ArrayList r1 = r1.mFileIds     // Catch:{ InterruptedException -> 0x001c, ProcessInitException -> 0x0063 }
                    int r1 = r1.size()     // Catch:{ InterruptedException -> 0x001c, ProcessInitException -> 0x0063 }
                    if (r8 >= r1) goto L_0x01a4
                    org.chromium.content.app.ChildProcessService r1 = org.chromium.content.app.ChildProcessService.this     // Catch:{ InterruptedException -> 0x001c, ProcessInitException -> 0x0063 }
                    java.util.ArrayList r1 = r1.mFileIds     // Catch:{ InterruptedException -> 0x001c, ProcessInitException -> 0x0063 }
                    java.lang.Object r1 = r1.get(r8)     // Catch:{ InterruptedException -> 0x001c, ProcessInitException -> 0x0063 }
                    java.lang.Integer r1 = (java.lang.Integer) r1     // Catch:{ InterruptedException -> 0x001c, ProcessInitException -> 0x0063 }
                    int r1 = r1.intValue()     // Catch:{ InterruptedException -> 0x001c, ProcessInitException -> 0x0063 }
                    r3[r8] = r1     // Catch:{ InterruptedException -> 0x001c, ProcessInitException -> 0x0063 }
                    org.chromium.content.app.ChildProcessService r1 = org.chromium.content.app.ChildProcessService.this     // Catch:{ InterruptedException -> 0x001c, ProcessInitException -> 0x0063 }
                    java.util.ArrayList r1 = r1.mFileFds     // Catch:{ InterruptedException -> 0x001c, ProcessInitException -> 0x0063 }
                    java.lang.Object r1 = r1.get(r8)     // Catch:{ InterruptedException -> 0x001c, ProcessInitException -> 0x0063 }
                    android.os.ParcelFileDescriptor r1 = (android.os.ParcelFileDescriptor) r1     // Catch:{ InterruptedException -> 0x001c, ProcessInitException -> 0x0063 }
                    int r1 = r1.detachFd()     // Catch:{ InterruptedException -> 0x001c, ProcessInitException -> 0x0063 }
                    r4[r8] = r1     // Catch:{ InterruptedException -> 0x001c, ProcessInitException -> 0x0063 }
                    int r8 = r8 + 1
                    goto L_0x0171
                L_0x01a4:
                    java.util.concurrent.atomic.AtomicReference r1 = org.chromium.content.app.ChildProcessService.sContext     // Catch:{ InterruptedException -> 0x001c, ProcessInitException -> 0x0063 }
                    java.lang.Object r1 = r1.get()     // Catch:{ InterruptedException -> 0x001c, ProcessInitException -> 0x0063 }
                    android.content.Context r1 = (android.content.Context) r1     // Catch:{ InterruptedException -> 0x001c, ProcessInitException -> 0x0063 }
                    android.content.Context r1 = r1.getApplicationContext()     // Catch:{ InterruptedException -> 0x001c, ProcessInitException -> 0x0063 }
                    org.chromium.content.app.ContentMain.initApplicationContext(r1)     // Catch:{ InterruptedException -> 0x001c, ProcessInitException -> 0x0063 }
                    java.util.concurrent.atomic.AtomicReference r1 = org.chromium.content.app.ChildProcessService.sContext     // Catch:{ InterruptedException -> 0x001c, ProcessInitException -> 0x0063 }
                    java.lang.Object r1 = r1.get()     // Catch:{ InterruptedException -> 0x001c, ProcessInitException -> 0x0063 }
                    android.content.Context r1 = (android.content.Context) r1     // Catch:{ InterruptedException -> 0x001c, ProcessInitException -> 0x0063 }
                    android.content.Context r1 = r1.getApplicationContext()     // Catch:{ InterruptedException -> 0x001c, ProcessInitException -> 0x0063 }
                    org.chromium.content.app.ChildProcessService r2 = org.chromium.content.app.ChildProcessService.this     // Catch:{ InterruptedException -> 0x001c, ProcessInitException -> 0x0063 }
                    org.chromium.content.app.ChildProcessService r5 = org.chromium.content.app.ChildProcessService.this     // Catch:{ InterruptedException -> 0x001c, ProcessInitException -> 0x0063 }
                    int r5 = r5.mCpuCount     // Catch:{ InterruptedException -> 0x001c, ProcessInitException -> 0x0063 }
                    org.chromium.content.app.ChildProcessService r6 = org.chromium.content.app.ChildProcessService.this     // Catch:{ InterruptedException -> 0x001c, ProcessInitException -> 0x0063 }
                    long r6 = r6.mCpuFeatures     // Catch:{ InterruptedException -> 0x001c, ProcessInitException -> 0x0063 }
                    org.chromium.content.app.ChildProcessService.nativeInitChildProcess(r1, r2, r3, r4, r5, r6)     // Catch:{ InterruptedException -> 0x001c, ProcessInitException -> 0x0063 }
                    org.chromium.content.app.ContentMain.start()     // Catch:{ InterruptedException -> 0x001c, ProcessInitException -> 0x0063 }
                    org.chromium.content.app.ChildProcessService.nativeExitChildProcess()     // Catch:{ InterruptedException -> 0x001c, ProcessInitException -> 0x0063 }
                    goto L_0x0035
                */
                throw new UnsupportedOperationException("Method not decompiled: org.chromium.content.app.ChildProcessService.AnonymousClass2.run():void");
            }
        }, MAIN_THREAD_NAME);
        this.mMainThread.start();
    }

    public void onDestroy() {
        Log.i(TAG, "Destroying ChildProcessService pid=" + Process.myPid());
        super.onDestroy();
        if (this.mCommandLineParams != null) {
            synchronized (this.mMainThread) {
                while (!this.mLibraryInitialized) {
                    try {
                        this.mMainThread.wait();
                    } catch (InterruptedException e) {
                    }
                }
            }
            nativeShutdownMainThread();
        }
    }

    public IBinder onBind(Intent intent) {
        stopSelf();
        synchronized (this.mMainThread) {
            this.mCommandLineParams = intent.getStringArrayExtra(ChildProcessConnection.EXTRA_COMMAND_LINE);
            this.mLinkerParams = new ChromiumLinkerParams(intent);
            this.mIsBound = true;
            this.mMainThread.notifyAll();
        }
        return this.mBinder;
    }

    @CalledByNative
    private void establishSurfaceTexturePeer(int pid, Object surfaceObject, int primaryID, int secondaryID) {
        Surface surface;
        if (this.mCallback == null) {
            Log.e(TAG, "No callback interface has been provided.");
            return;
        }
        boolean needRelease = false;
        if (surfaceObject instanceof Surface) {
            surface = (Surface) surfaceObject;
        } else if (surfaceObject instanceof SurfaceTexture) {
            surface = new Surface((SurfaceTexture) surfaceObject);
            needRelease = true;
        } else {
            Log.e(TAG, "Not a valid surfaceObject: " + surfaceObject);
            return;
        }
        try {
            this.mCallback.establishSurfacePeer(pid, surface, primaryID, secondaryID);
            if (needRelease) {
                surface.release();
            }
        } catch (RemoteException e) {
            Log.e(TAG, "Unable to call establishSurfaceTexturePeer: " + e);
            if (needRelease) {
                surface.release();
            }
        } catch (Throwable th) {
            if (needRelease) {
                surface.release();
            }
            throw th;
        }
    }

    @CalledByNative
    private Surface getViewSurface(int surfaceId) {
        if (this.mCallback == null) {
            Log.e(TAG, "No callback interface has been provided.");
            return null;
        }
        try {
            return this.mCallback.getViewSurface(surfaceId).getSurface();
        } catch (RemoteException e) {
            Log.e(TAG, "Unable to call establishSurfaceTexturePeer: " + e);
            return null;
        }
    }

    @CalledByNative
    private void createSurfaceTextureSurface(int surfaceTextureId, int clientId, SurfaceTexture surfaceTexture) {
        if (this.mCallback == null) {
            Log.e(TAG, "No callback interface has been provided.");
            return;
        }
        Surface surface = new Surface(surfaceTexture);
        try {
            this.mCallback.registerSurfaceTextureSurface(surfaceTextureId, clientId, surface);
        } catch (RemoteException e) {
            Log.e(TAG, "Unable to call registerSurfaceTextureSurface: " + e);
        }
        surface.release();
    }

    @CalledByNative
    private void destroySurfaceTextureSurface(int surfaceTextureId, int clientId) {
        if (this.mCallback == null) {
            Log.e(TAG, "No callback interface has been provided.");
            return;
        }
        try {
            this.mCallback.unregisterSurfaceTextureSurface(surfaceTextureId, clientId);
        } catch (RemoteException e) {
            Log.e(TAG, "Unable to call unregisterSurfaceTextureSurface: " + e);
        }
    }

    @CalledByNative
    private Surface getSurfaceTextureSurface(int surfaceTextureId) {
        if (this.mCallback == null) {
            Log.e(TAG, "No callback interface has been provided.");
            return null;
        }
        try {
            return this.mCallback.getSurfaceTextureSurface(surfaceTextureId).getSurface();
        } catch (RemoteException e) {
            Log.e(TAG, "Unable to call getSurfaceTextureSurface: " + e);
            return null;
        }
    }
}
