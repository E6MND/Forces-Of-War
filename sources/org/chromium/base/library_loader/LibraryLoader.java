package org.chromium.base.library_loader;

import android.content.Context;
import android.os.SystemClock;
import android.util.Log;
import javax.annotation.Nullable;
import org.chromium.base.CommandLine;
import org.chromium.base.JNINamespace;
import org.chromium.base.TraceEvent;

@JNINamespace("base::android")
public class LibraryLoader {
    static final /* synthetic */ boolean $assertionsDisabled = (!LibraryLoader.class.desiredAssertionStatus());
    private static final boolean DEBUG = false;
    private static final String TAG = "LibraryLoader";
    private static boolean sCommandLineSwitched = false;
    private static boolean sInitialized = false;
    private static boolean sIsUsingBrowserSharedRelros = false;
    private static boolean sLibraryIsMappableInApk = true;
    private static boolean sLibraryWasLoadedFromApk = false;
    private static boolean sLoadAtFixedAddressFailed = false;
    private static boolean sLoaded = false;
    private static final Object sLock = new Object();
    private static boolean sMapApkWithExecPermission = false;
    private static boolean sNativeLibraryHackWasUsed = false;

    private static native String nativeGetVersionNumber();

    private static native void nativeInitCommandLine(String[] strArr);

    private static native boolean nativeLibraryLoaded();

    private static native void nativeRecordChromiumAndroidLinkerBrowserHistogram(boolean z, boolean z2, int i);

    private static native void nativeRecordNativeLibraryHack(boolean z);

    private static native void nativeRegisterChromiumAndroidLinkerRendererHistogram(boolean z, boolean z2);

    public static void ensureInitialized() throws ProcessInitException {
        ensureInitialized((Context) null, false);
    }

    public static void ensureInitialized(Context context, boolean shouldDeleteOldWorkaroundLibraries) throws ProcessInitException {
        synchronized (sLock) {
            if (!sInitialized) {
                loadAlreadyLocked(context, shouldDeleteOldWorkaroundLibraries);
                initializeAlreadyLocked();
            }
        }
    }

    public static boolean isInitialized() {
        boolean z;
        synchronized (sLock) {
            z = sInitialized;
        }
        return z;
    }

    public static void loadNow() throws ProcessInitException {
        loadNow((Context) null, false);
    }

    public static void loadNow(Context context, boolean shouldDeleteOldWorkaroundLibraries) throws ProcessInitException {
        synchronized (sLock) {
            loadAlreadyLocked(context, shouldDeleteOldWorkaroundLibraries);
        }
    }

    public static void initialize() throws ProcessInitException {
        synchronized (sLock) {
            initializeAlreadyLocked();
        }
    }

    private static void loadAlreadyLocked(Context context, boolean shouldDeleteOldWorkaroundLibraries) throws ProcessInitException {
        String library;
        String str;
        try {
            if (!sLoaded) {
                if ($assertionsDisabled || !sInitialized) {
                    long startTime = SystemClock.uptimeMillis();
                    boolean fallbackWasUsed = false;
                    if (Linker.isUsed()) {
                        String apkFilePath = null;
                        boolean useMapExecSupportFallback = false;
                        if (context != null) {
                            apkFilePath = context.getApplicationInfo().sourceDir;
                            sMapApkWithExecPermission = Linker.checkMapExecSupport(apkFilePath);
                            if (!sMapApkWithExecPermission && Linker.isInZipFile()) {
                                Log.w(TAG, "the no map executable support fallback will be used because memory mapping the APK file with executable permissions is not supported");
                                Linker.enableNoMapExecSupportFallback();
                                useMapExecSupportFallback = true;
                            }
                        } else {
                            Log.w(TAG, "could not check load from APK support due to null context");
                        }
                        Linker.prepareLibraryLoad();
                        for (String library2 : NativeLibraries.LIBRARIES) {
                            if (!Linker.isChromiumLinkerLibrary(library2)) {
                                String zipFilePath = null;
                                String libFilePath = System.mapLibraryName(library2);
                                if (apkFilePath == null || !Linker.isInZipFile()) {
                                    Log.i(TAG, "Loading " + library2);
                                } else {
                                    if (!Linker.checkLibraryIsMappableInApk(apkFilePath, libFilePath)) {
                                        sLibraryIsMappableInApk = false;
                                    }
                                    if (sLibraryIsMappableInApk || useMapExecSupportFallback) {
                                        zipFilePath = apkFilePath;
                                        StringBuilder append = new StringBuilder().append("Loading ").append(library2).append(" ");
                                        if (useMapExecSupportFallback) {
                                            str = "using no map executable support fallback";
                                        } else {
                                            str = "directly";
                                        }
                                        Log.i(TAG, append.append(str).append(" from within ").append(apkFilePath).toString());
                                    } else {
                                        Log.i(TAG, "Loading " + library2 + " using unpack library fallback from within " + apkFilePath);
                                        libFilePath = LibraryLoaderHelper.buildFallbackLibrary(context, library2);
                                        fallbackWasUsed = true;
                                        Log.i(TAG, "Built fallback library " + libFilePath);
                                    }
                                }
                                boolean isLoaded = false;
                                if (Linker.isUsingBrowserSharedRelros()) {
                                    sIsUsingBrowserSharedRelros = true;
                                    try {
                                        loadLibrary(zipFilePath, libFilePath);
                                        isLoaded = true;
                                    } catch (UnsatisfiedLinkError e) {
                                        Log.w(TAG, "Failed to load native library with shared RELRO, retrying without");
                                        Linker.disableSharedRelros();
                                        sLoadAtFixedAddressFailed = true;
                                    }
                                }
                                if (!isLoaded) {
                                    loadLibrary(zipFilePath, libFilePath);
                                }
                            }
                        }
                        Linker.finishLibraryLoad();
                    } else {
                        String[] arr$ = NativeLibraries.LIBRARIES;
                        int len$ = arr$.length;
                        for (int i$ = 0; i$ < len$; i$++) {
                            library = arr$[i$];
                            System.loadLibrary(library);
                        }
                    }
                    if (context != null && shouldDeleteOldWorkaroundLibraries) {
                        if (!sNativeLibraryHackWasUsed) {
                            LibraryLoaderHelper.deleteLibrariesAsynchronously(context, LibraryLoaderHelper.PACKAGE_MANAGER_WORKAROUND_DIR);
                        }
                        if (!fallbackWasUsed) {
                            LibraryLoaderHelper.deleteLibrariesAsynchronously(context, LibraryLoaderHelper.LOAD_FROM_APK_FALLBACK_DIR);
                        }
                    }
                    long stopTime = SystemClock.uptimeMillis();
                    Log.i(TAG, String.format("Time to load native libraries: %d ms (timestamps %d-%d)", new Object[]{Long.valueOf(stopTime - startTime), Long.valueOf(startTime % 10000), Long.valueOf(stopTime % 10000)}));
                    sLoaded = true;
                } else {
                    throw new AssertionError();
                }
            }
            Log.i(TAG, String.format("Expected native library version number \"%s\",actual native library version number \"%s\"", new Object[]{NativeLibraries.sVersionNumber, nativeGetVersionNumber()}));
            if (!NativeLibraries.sVersionNumber.equals(nativeGetVersionNumber())) {
                throw new ProcessInitException(3);
            }
        } catch (UnsatisfiedLinkError e2) {
            if (context != null) {
                if (LibraryLoaderHelper.tryLoadLibraryUsingWorkaround(context, library)) {
                    sNativeLibraryHackWasUsed = true;
                }
            }
            throw e2;
        } catch (UnsatisfiedLinkError e3) {
            throw new ProcessInitException(2, e3);
        }
    }

    private static void loadLibrary(@Nullable String zipFilePath, String libFilePath) {
        Linker.loadLibrary(zipFilePath, libFilePath);
        if (zipFilePath != null) {
            sLibraryWasLoadedFromApk = true;
        }
    }

    public static void switchCommandLineForWebView() {
        synchronized (sLock) {
            ensureCommandLineSwitchedAlreadyLocked();
        }
    }

    private static void ensureCommandLineSwitchedAlreadyLocked() {
        if (!$assertionsDisabled && !sLoaded) {
            throw new AssertionError();
        } else if (!sCommandLineSwitched) {
            nativeInitCommandLine(CommandLine.getJavaSwitchesOrNull());
            CommandLine.enableNativeProxy();
            sCommandLineSwitched = true;
        }
    }

    private static void initializeAlreadyLocked() throws ProcessInitException {
        if (!sInitialized) {
            if (!sCommandLineSwitched) {
                nativeInitCommandLine(CommandLine.getJavaSwitchesOrNull());
            }
            if (!nativeLibraryLoaded()) {
                Log.e(TAG, "error calling nativeLibraryLoaded");
                throw new ProcessInitException(1);
            }
            sInitialized = true;
            if (!sCommandLineSwitched) {
                CommandLine.enableNativeProxy();
                sCommandLineSwitched = true;
            }
            TraceEvent.registerNativeEnabledObserver();
        }
    }

    public static void onNativeInitializationComplete(Context context) {
        recordBrowserProcessHistogram(context);
        nativeRecordNativeLibraryHack(sNativeLibraryHackWasUsed);
    }

    private static void recordBrowserProcessHistogram(Context context) {
        if (Linker.isUsed()) {
            nativeRecordChromiumAndroidLinkerBrowserHistogram(sIsUsingBrowserSharedRelros, sLoadAtFixedAddressFailed, getLibraryLoadFromApkStatus(context));
        }
    }

    private static int getLibraryLoadFromApkStatus(Context context) {
        if (!$assertionsDisabled && !Linker.isUsed()) {
            throw new AssertionError();
        } else if (sLibraryWasLoadedFromApk) {
            if (sMapApkWithExecPermission) {
                return 3;
            }
            return 5;
        } else if (!sLibraryIsMappableInApk) {
            return 4;
        } else {
            if (context != null) {
                return sMapApkWithExecPermission ? 2 : 1;
            }
            Log.w(TAG, "Unknown APK filename due to null context");
            return 0;
        }
    }

    public static void registerRendererProcessHistogram(boolean requestedSharedRelro, boolean loadAtFixedAddressFailed) {
        if (Linker.isUsed()) {
            nativeRegisterChromiumAndroidLinkerRendererHistogram(requestedSharedRelro, loadAtFixedAddressFailed);
        }
    }
}
