package org.chromium.content.browser;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import org.chromium.base.CalledByNative;
import org.chromium.base.JNINamespace;
import org.chromium.base.ResourceExtractor;
import org.chromium.base.ThreadUtils;
import org.chromium.base.VisibleForTesting;
import org.chromium.base.library_loader.LibraryLoader;
import org.chromium.base.library_loader.ProcessInitException;
import org.chromium.content.app.ContentMain;

@JNINamespace("content")
public class BrowserStartupController {
    static final /* synthetic */ boolean $assertionsDisabled;
    private static final boolean ALREADY_STARTED = true;
    private static final boolean NOT_ALREADY_STARTED = false;
    @VisibleForTesting
    static final int STARTUP_FAILURE = 1;
    @VisibleForTesting
    static final int STARTUP_SUCCESS = -1;
    private static final String TAG = "BrowserStartupController";
    private static boolean sBrowserMayStartAsynchronously = false;
    private static BrowserStartupController sInstance;
    private final List<StartupCallback> mAsyncStartupCallbacks = new ArrayList();
    private final Context mContext;
    private boolean mHasStartedInitializingBrowserProcess;
    private boolean mStartupDone;
    /* access modifiers changed from: private */
    public boolean mStartupSuccess;

    public interface StartupCallback {
        void onFailure();

        void onSuccess(boolean z);
    }

    private static native boolean nativeIsOfficialBuild();

    private static native boolean nativeIsPluginEnabled();

    private static native void nativeSetCommandLineFlags(boolean z, String str);

    static {
        boolean z;
        if (!BrowserStartupController.class.desiredAssertionStatus()) {
            z = true;
        } else {
            z = false;
        }
        $assertionsDisabled = z;
    }

    private static void setAsynchronousStartup(boolean enable) {
        sBrowserMayStartAsynchronously = enable;
    }

    @CalledByNative
    @VisibleForTesting
    static boolean browserMayStartAsynchonously() {
        return sBrowserMayStartAsynchronously;
    }

    @CalledByNative
    @VisibleForTesting
    static void browserStartupComplete(int result) {
        if (sInstance != null) {
            sInstance.executeEnqueuedCallbacks(result, false);
        }
    }

    BrowserStartupController(Context context) {
        this.mContext = context;
    }

    public static BrowserStartupController get(Context context) {
        if ($assertionsDisabled || ThreadUtils.runningOnUiThread()) {
            ThreadUtils.assertOnUiThread();
            if (sInstance == null) {
                sInstance = new BrowserStartupController(context.getApplicationContext());
            }
            return sInstance;
        }
        throw new AssertionError("Tried to start the browser on the wrong thread.");
    }

    @VisibleForTesting
    static BrowserStartupController overrideInstanceForTest(BrowserStartupController controller) {
        if (sInstance == null) {
            sInstance = controller;
        }
        return sInstance;
    }

    public void startBrowserProcessesAsync(StartupCallback callback) throws ProcessInitException {
        if (!$assertionsDisabled && !ThreadUtils.runningOnUiThread()) {
            throw new AssertionError("Tried to start the browser on the wrong thread.");
        } else if (this.mStartupDone) {
            postStartupCompleted(callback);
        } else {
            this.mAsyncStartupCallbacks.add(callback);
            if (!this.mHasStartedInitializingBrowserProcess) {
                this.mHasStartedInitializingBrowserProcess = true;
                prepareToStartBrowserProcess(false);
                setAsynchronousStartup(true);
                if (contentStart() > 0) {
                    enqueueCallbackExecution(1, false);
                }
            }
        }
    }

    public void startBrowserProcessesSync(boolean singleProcess) throws ProcessInitException {
        if (!this.mStartupDone) {
            if (!this.mHasStartedInitializingBrowserProcess) {
                prepareToStartBrowserProcess(singleProcess);
            }
            setAsynchronousStartup(false);
            if (contentStart() > 0) {
                enqueueCallbackExecution(1, false);
            }
        }
        if (!$assertionsDisabled && !this.mStartupDone) {
            throw new AssertionError();
        } else if (!this.mStartupSuccess) {
            throw new ProcessInitException(4);
        }
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public int contentStart() {
        return ContentMain.start();
    }

    public void addStartupCompletedObserver(StartupCallback callback) {
        ThreadUtils.assertOnUiThread();
        if (this.mStartupDone) {
            postStartupCompleted(callback);
        } else {
            this.mAsyncStartupCallbacks.add(callback);
        }
    }

    /* access modifiers changed from: private */
    public void executeEnqueuedCallbacks(int startupResult, boolean alreadyStarted) {
        boolean z = true;
        if ($assertionsDisabled || ThreadUtils.runningOnUiThread()) {
            this.mStartupDone = true;
            if (startupResult > 0) {
                z = false;
            }
            this.mStartupSuccess = z;
            for (StartupCallback asyncStartupCallback : this.mAsyncStartupCallbacks) {
                if (this.mStartupSuccess) {
                    asyncStartupCallback.onSuccess(alreadyStarted);
                } else {
                    asyncStartupCallback.onFailure();
                }
            }
            this.mAsyncStartupCallbacks.clear();
            return;
        }
        throw new AssertionError("Callback from browser startup from wrong thread.");
    }

    private void enqueueCallbackExecution(final int startupFailure, final boolean alreadyStarted) {
        new Handler().post(new Runnable() {
            public void run() {
                BrowserStartupController.this.executeEnqueuedCallbacks(startupFailure, alreadyStarted);
            }
        });
    }

    private void postStartupCompleted(final StartupCallback callback) {
        new Handler().post(new Runnable() {
            public void run() {
                if (BrowserStartupController.this.mStartupSuccess) {
                    callback.onSuccess(true);
                } else {
                    callback.onFailure();
                }
            }
        });
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public void prepareToStartBrowserProcess(boolean singleProcess) throws ProcessInitException {
        Log.i(TAG, "Initializing chromium process, singleProcess=" + singleProcess);
        ResourceExtractor resourceExtractor = ResourceExtractor.get(this.mContext);
        resourceExtractor.startExtractingResources();
        LibraryLoader.ensureInitialized(this.mContext, true);
        DeviceUtils.addDeviceSpecificUserAgentSwitch(this.mContext);
        Context appContext = this.mContext.getApplicationContext();
        resourceExtractor.waitForCompletion();
        nativeSetCommandLineFlags(singleProcess, nativeIsPluginEnabled() ? getPlugins() : null);
        ContentMain.initApplicationContext(appContext);
    }

    public void initChromiumBrowserProcessForTests() {
        ResourceExtractor resourceExtractor = ResourceExtractor.get(this.mContext);
        resourceExtractor.startExtractingResources();
        resourceExtractor.waitForCompletion();
        nativeSetCommandLineFlags(false, (String) null);
    }

    private String getPlugins() {
        return PepperPluginManager.getPlugins(this.mContext);
    }
}
