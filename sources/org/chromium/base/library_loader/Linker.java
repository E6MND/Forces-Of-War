package org.chromium.base.library_loader;

import android.os.Bundle;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import android.util.Log;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.annotation.Nullable;
import org.chromium.base.AccessedByNative;
import org.chromium.base.CalledByNative;
import org.chromium.base.SysUtils;
import org.chromium.base.ThreadUtils;

public class Linker {
    static final /* synthetic */ boolean $assertionsDisabled;
    public static final int BROWSER_SHARED_RELRO_CONFIG = 1;
    public static final int BROWSER_SHARED_RELRO_CONFIG_ALWAYS = 2;
    public static final int BROWSER_SHARED_RELRO_CONFIG_LOW_RAM_ONLY = 1;
    public static final int BROWSER_SHARED_RELRO_CONFIG_NEVER = 0;
    private static final boolean DEBUG = false;
    public static final String EXTRA_LINKER_SHARED_RELROS = "org.chromium.base.android.linker.shared_relros";
    public static final int MEMORY_DEVICE_CONFIG_INIT = 0;
    public static final int MEMORY_DEVICE_CONFIG_LOW = 1;
    public static final int MEMORY_DEVICE_CONFIG_NORMAL = 2;
    private static final String TAG = "chromium_android_linker";
    private static long sBaseLoadAddress = 0;
    private static boolean sBrowserUsesSharedRelro = false;
    private static long sCurrentLoadAddress = 0;
    private static boolean sInBrowserProcess = true;
    private static boolean sInitialized = false;
    private static HashMap<String, LibInfo> sLoadedLibraries = null;
    private static int sMemoryDeviceConfig = 0;
    private static boolean sPrepareLibraryLoadCalled = false;
    private static boolean sRelroSharingSupported = false;
    private static Bundle sSharedRelros = null;
    static String sTestRunnerClassName = null;
    private static boolean sWaitForSharedRelros = false;

    public interface TestRunner {
        boolean runChecks(int i, boolean z);
    }

    private static native boolean nativeCanUseSharedRelro();

    private static native boolean nativeCheckLibraryIsMappableInApk(String str, String str2);

    private static native boolean nativeCheckMapExecSupport(String str);

    private static native boolean nativeCreateSharedRelro(String str, long j, LibInfo libInfo);

    private static native void nativeEnableNoMapExecSupportFallback();

    private static native String nativeGetLibraryFilePathInZipFile(String str);

    private static native long nativeGetRandomBaseLoadAddress(long j);

    private static native boolean nativeLoadLibrary(String str, long j, LibInfo libInfo);

    private static native boolean nativeLoadLibraryInZipFile(String str, String str2, long j, LibInfo libInfo);

    /* access modifiers changed from: private */
    public static native void nativeRunCallbackOnUiThread(long j);

    private static native boolean nativeUseSharedRelro(String str, LibInfo libInfo);

    static {
        boolean z;
        if (!Linker.class.desiredAssertionStatus()) {
            z = true;
        } else {
            z = false;
        }
        $assertionsDisabled = z;
    }

    private static void ensureInitializedLocked() {
        boolean z;
        if (!$assertionsDisabled && !Thread.holdsLock(Linker.class)) {
            throw new AssertionError();
        } else if (!sInitialized) {
            sRelroSharingSupported = false;
            if (NativeLibraries.sUseLinker) {
                try {
                    System.loadLibrary(TAG);
                } catch (UnsatisfiedLinkError e) {
                    Log.w(TAG, "Couldn't load libchromium_android_linker.so, trying libchromium_android_linker.cr.so");
                    System.loadLibrary("chromium_android_linker.cr");
                }
                sRelroSharingSupported = nativeCanUseSharedRelro();
                if (!sRelroSharingSupported) {
                    Log.w(TAG, "This system cannot safely share RELRO sections");
                }
                if (sMemoryDeviceConfig == 0) {
                    sMemoryDeviceConfig = SysUtils.isLowEndDevice() ? 1 : 2;
                }
                switch (1) {
                    case 0:
                        sBrowserUsesSharedRelro = false;
                        break;
                    case 1:
                        if (sMemoryDeviceConfig == 1) {
                            z = true;
                        } else {
                            z = false;
                        }
                        sBrowserUsesSharedRelro = z;
                        if (sBrowserUsesSharedRelro) {
                            Log.w(TAG, "Low-memory device: shared RELROs used in all processes");
                            break;
                        }
                        break;
                    case 2:
                        Log.w(TAG, "Beware: shared RELROs used in all processes!");
                        sBrowserUsesSharedRelro = true;
                        break;
                    default:
                        if (!$assertionsDisabled) {
                            throw new AssertionError("Unreached");
                        }
                        break;
                }
            }
            if (!sRelroSharingSupported) {
                sBrowserUsesSharedRelro = false;
                sWaitForSharedRelros = false;
            }
            sInitialized = true;
        }
    }

    public static void setTestRunnerClassName(String testRunnerClassName) {
        if (NativeLibraries.sEnableLinkerTests) {
            synchronized (Linker.class) {
                if ($assertionsDisabled || sTestRunnerClassName == null) {
                    sTestRunnerClassName = testRunnerClassName;
                } else {
                    throw new AssertionError();
                }
            }
        }
    }

    public static String getTestRunnerClassName() {
        String str;
        synchronized (Linker.class) {
            str = sTestRunnerClassName;
        }
        return str;
    }

    public static void setMemoryDeviceConfig(int memoryDeviceConfig) {
        if ($assertionsDisabled || NativeLibraries.sEnableLinkerTests) {
            synchronized (Linker.class) {
                if (!$assertionsDisabled && sMemoryDeviceConfig != 0) {
                    throw new AssertionError();
                } else if ($assertionsDisabled || memoryDeviceConfig == 1 || memoryDeviceConfig == 2) {
                    sMemoryDeviceConfig = memoryDeviceConfig;
                } else {
                    throw new AssertionError();
                }
            }
            return;
        }
        throw new AssertionError();
    }

    public static boolean isUsed() {
        boolean z;
        if (!NativeLibraries.sUseLinker) {
            return false;
        }
        synchronized (Linker.class) {
            ensureInitializedLocked();
            z = sRelroSharingSupported;
        }
        return z;
    }

    public static boolean isUsingBrowserSharedRelros() {
        boolean z;
        synchronized (Linker.class) {
            ensureInitializedLocked();
            z = sBrowserUsesSharedRelro;
        }
        return z;
    }

    public static boolean isInZipFile() {
        return NativeLibraries.sUseLibraryInZipFile;
    }

    public static void prepareLibraryLoad() {
        synchronized (Linker.class) {
            sPrepareLibraryLoadCalled = true;
            if (sInBrowserProcess) {
                setupBaseLoadAddressLocked();
            }
        }
    }

    public static void finishLibraryLoad() {
        TestRunner testRunner;
        synchronized (Linker.class) {
            if (sLoadedLibraries != null) {
                if (sInBrowserProcess) {
                    sSharedRelros = createBundleFromLibInfoMap(sLoadedLibraries);
                    if (sBrowserUsesSharedRelro) {
                        useSharedRelrosLocked(sSharedRelros);
                    }
                }
                if (sWaitForSharedRelros) {
                    if ($assertionsDisabled || !sInBrowserProcess) {
                        while (sSharedRelros == null) {
                            try {
                                Linker.class.wait();
                            } catch (InterruptedException e) {
                            }
                        }
                        useSharedRelrosLocked(sSharedRelros);
                        sSharedRelros.clear();
                        sSharedRelros = null;
                    } else {
                        throw new AssertionError();
                    }
                }
            }
            if (NativeLibraries.sEnableLinkerTests && sTestRunnerClassName != null) {
                try {
                    testRunner = (TestRunner) Class.forName(sTestRunnerClassName).newInstance();
                } catch (Exception e2) {
                    Log.e(TAG, "Could not extract test runner class name", e2);
                    testRunner = null;
                }
                if (testRunner != null) {
                    if (!testRunner.runChecks(sMemoryDeviceConfig, sInBrowserProcess)) {
                        Log.wtf(TAG, "Linker runtime tests failed in this process!!");
                        if (!$assertionsDisabled) {
                            throw new AssertionError();
                        }
                    } else {
                        Log.i(TAG, "All linker tests passed!");
                    }
                }
            }
        }
    }

    public static void useSharedRelros(Bundle bundle) {
        Bundle clonedBundle = null;
        if (bundle != null) {
            bundle.setClassLoader(LibInfo.class.getClassLoader());
            clonedBundle = new Bundle(LibInfo.class.getClassLoader());
            Parcel parcel = Parcel.obtain();
            bundle.writeToParcel(parcel, 0);
            parcel.setDataPosition(0);
            clonedBundle.readFromParcel(parcel);
            parcel.recycle();
        }
        synchronized (Linker.class) {
            sSharedRelros = clonedBundle;
            Linker.class.notifyAll();
        }
    }

    public static Bundle getSharedRelros() {
        Bundle bundle;
        synchronized (Linker.class) {
            if (!sInBrowserProcess) {
                bundle = null;
            } else {
                bundle = sSharedRelros;
            }
        }
        return bundle;
    }

    public static void disableSharedRelros() {
        synchronized (Linker.class) {
            sInBrowserProcess = false;
            sWaitForSharedRelros = false;
            sBrowserUsesSharedRelro = false;
        }
    }

    public static void initServiceProcess(long baseLoadAddress) {
        synchronized (Linker.class) {
            ensureInitializedLocked();
            sInBrowserProcess = false;
            sBrowserUsesSharedRelro = false;
            if (sRelroSharingSupported) {
                sWaitForSharedRelros = true;
                sBaseLoadAddress = baseLoadAddress;
                sCurrentLoadAddress = baseLoadAddress;
            }
        }
    }

    public static long getBaseLoadAddress() {
        long j;
        synchronized (Linker.class) {
            ensureInitializedLocked();
            if (!sInBrowserProcess) {
                Log.w(TAG, "Shared RELRO sections are disabled in this process!");
                j = 0;
            } else {
                setupBaseLoadAddressLocked();
                j = sBaseLoadAddress;
            }
        }
        return j;
    }

    private static void setupBaseLoadAddressLocked() {
        if (!$assertionsDisabled && !Thread.holdsLock(Linker.class)) {
            throw new AssertionError();
        } else if (sBaseLoadAddress == 0) {
            long address = computeRandomBaseLoadAddress();
            sBaseLoadAddress = address;
            sCurrentLoadAddress = address;
            if (address == 0) {
                Log.w(TAG, "Disabling shared RELROs due address space pressure");
                sBrowserUsesSharedRelro = false;
                sWaitForSharedRelros = false;
            }
        }
    }

    private static long computeRandomBaseLoadAddress() {
        return nativeGetRandomBaseLoadAddress(201326592);
    }

    private static void dumpBundle(Bundle bundle) {
    }

    private static void useSharedRelrosLocked(Bundle bundle) {
        if (!$assertionsDisabled && !Thread.holdsLock(Linker.class)) {
            throw new AssertionError();
        } else if (bundle != null && sRelroSharingSupported && sLoadedLibraries != null) {
            HashMap<String, LibInfo> relroMap = createLibInfoMapFromBundle(bundle);
            for (Map.Entry<String, LibInfo> entry : relroMap.entrySet()) {
                String libName = entry.getKey();
                if (!nativeUseSharedRelro(libName, entry.getValue())) {
                    Log.w(TAG, "Could not use shared RELRO section for " + libName);
                }
            }
            if (!sInBrowserProcess) {
                closeLibInfoMap(relroMap);
            }
        }
    }

    public static void loadLibrary(@Nullable String zipFilePath, String libFilePath) {
        synchronized (Linker.class) {
            ensureInitializedLocked();
            if ($assertionsDisabled || sPrepareLibraryLoadCalled) {
                if (sLoadedLibraries == null) {
                    sLoadedLibraries = new HashMap<>();
                }
                if (!sLoadedLibraries.containsKey(libFilePath)) {
                    LibInfo libInfo = new LibInfo();
                    long loadAddress = 0;
                    if ((sInBrowserProcess && sBrowserUsesSharedRelro) || sWaitForSharedRelros) {
                        loadAddress = sCurrentLoadAddress;
                    }
                    String sharedRelRoName = libFilePath;
                    if (zipFilePath != null) {
                        if (!nativeLoadLibraryInZipFile(zipFilePath, libFilePath, loadAddress, libInfo)) {
                            String errorMessage = "Unable to load library: " + libFilePath + ", in: " + zipFilePath;
                            Log.e(TAG, errorMessage);
                            throw new UnsatisfiedLinkError(errorMessage);
                        }
                        sharedRelRoName = zipFilePath;
                    } else if (!nativeLoadLibrary(libFilePath, loadAddress, libInfo)) {
                        String errorMessage2 = "Unable to load library: " + libFilePath;
                        Log.e(TAG, errorMessage2);
                        throw new UnsatisfiedLinkError(errorMessage2);
                    }
                    if (NativeLibraries.sEnableLinkerTests) {
                        Locale locale = Locale.US;
                        Object[] objArr = new Object[3];
                        objArr[0] = sInBrowserProcess ? "BROWSER" : "RENDERER";
                        objArr[1] = libFilePath;
                        objArr[2] = Long.valueOf(libInfo.mLoadAddress);
                        Log.i(TAG, String.format(locale, "%s_LIBRARY_ADDRESS: %s %x", objArr));
                    }
                    if (sInBrowserProcess && !nativeCreateSharedRelro(sharedRelRoName, sCurrentLoadAddress, libInfo)) {
                        Log.w(TAG, String.format(Locale.US, "Could not create shared RELRO for %s at %x", new Object[]{libFilePath, Long.valueOf(sCurrentLoadAddress)}));
                    }
                    if (sCurrentLoadAddress != 0) {
                        sCurrentLoadAddress = libInfo.mLoadAddress + libInfo.mLoadSize;
                    }
                    sLoadedLibraries.put(sharedRelRoName, libInfo);
                    return;
                }
                return;
            }
            throw new AssertionError();
        }
    }

    public static void enableNoMapExecSupportFallback() {
        synchronized (Linker.class) {
            ensureInitializedLocked();
            nativeEnableNoMapExecSupportFallback();
        }
    }

    public static boolean isChromiumLinkerLibrary(String library) {
        return library.equals(TAG) || library.equals("chromium_android_linker.cr");
    }

    public static String getLibraryFilePathInZipFile(String library) throws FileNotFoundException {
        String path;
        synchronized (Linker.class) {
            ensureInitializedLocked();
            path = nativeGetLibraryFilePathInZipFile(library);
            if (path.equals("")) {
                throw new FileNotFoundException("Failed to retrieve path in zip file for library " + library);
            }
        }
        return path;
    }

    public static boolean checkMapExecSupport(String apkFile) {
        boolean supported;
        if ($assertionsDisabled || apkFile != null) {
            synchronized (Linker.class) {
                ensureInitializedLocked();
                supported = nativeCheckMapExecSupport(apkFile);
            }
            return supported;
        }
        throw new AssertionError();
    }

    public static boolean checkLibraryIsMappableInApk(String apkFile, String library) {
        boolean aligned;
        synchronized (Linker.class) {
            ensureInitializedLocked();
            aligned = nativeCheckLibraryIsMappableInApk(apkFile, library);
        }
        return aligned;
    }

    @CalledByNative
    public static void postCallbackOnMainThread(final long opaque) {
        ThreadUtils.postOnUiThread((Runnable) new Runnable() {
            public void run() {
                Linker.nativeRunCallbackOnUiThread(opaque);
            }
        });
    }

    public static class LibInfo implements Parcelable {
        public static final Parcelable.Creator<LibInfo> CREATOR = new Parcelable.Creator<LibInfo>() {
            public LibInfo createFromParcel(Parcel in) {
                return new LibInfo(in);
            }

            public LibInfo[] newArray(int size) {
                return new LibInfo[size];
            }
        };
        @AccessedByNative
        public long mLoadAddress;
        @AccessedByNative
        public long mLoadSize;
        @AccessedByNative
        public int mRelroFd;
        @AccessedByNative
        public long mRelroSize;
        @AccessedByNative
        public long mRelroStart;

        public LibInfo() {
            this.mLoadAddress = 0;
            this.mLoadSize = 0;
            this.mRelroStart = 0;
            this.mRelroSize = 0;
            this.mRelroFd = -1;
        }

        public void close() {
            if (this.mRelroFd >= 0) {
                try {
                    ParcelFileDescriptor.adoptFd(this.mRelroFd).close();
                } catch (IOException e) {
                }
                this.mRelroFd = -1;
            }
        }

        public LibInfo(Parcel in) {
            this.mLoadAddress = in.readLong();
            this.mLoadSize = in.readLong();
            this.mRelroStart = in.readLong();
            this.mRelroSize = in.readLong();
            ParcelFileDescriptor fd = in.readFileDescriptor();
            this.mRelroFd = fd == null ? -1 : fd.detachFd();
        }

        public void writeToParcel(Parcel out, int flags) {
            if (this.mRelroFd >= 0) {
                out.writeLong(this.mLoadAddress);
                out.writeLong(this.mLoadSize);
                out.writeLong(this.mRelroStart);
                out.writeLong(this.mRelroSize);
                try {
                    ParcelFileDescriptor fd = ParcelFileDescriptor.fromFd(this.mRelroFd);
                    fd.writeToParcel(out, 0);
                    fd.close();
                } catch (IOException e) {
                    Log.e(Linker.TAG, "Cant' write LibInfo file descriptor to parcel", e);
                }
            }
        }

        public int describeContents() {
            return 1;
        }

        public String toString() {
            return String.format(Locale.US, "[load=0x%x-0x%x relro=0x%x-0x%x fd=%d]", new Object[]{Long.valueOf(this.mLoadAddress), Long.valueOf(this.mLoadAddress + this.mLoadSize), Long.valueOf(this.mRelroStart), Long.valueOf(this.mRelroStart + this.mRelroSize), Integer.valueOf(this.mRelroFd)});
        }
    }

    private static Bundle createBundleFromLibInfoMap(HashMap<String, LibInfo> map) {
        Bundle bundle = new Bundle(map.size());
        for (Map.Entry<String, LibInfo> entry : map.entrySet()) {
            bundle.putParcelable(entry.getKey(), entry.getValue());
        }
        return bundle;
    }

    private static HashMap<String, LibInfo> createLibInfoMapFromBundle(Bundle bundle) {
        HashMap<String, LibInfo> map = new HashMap<>();
        for (String library : bundle.keySet()) {
            map.put(library, (LibInfo) bundle.getParcelable(library));
        }
        return map;
    }

    private static void closeLibInfoMap(HashMap<String, LibInfo> map) {
        for (Map.Entry<String, LibInfo> entry : map.entrySet()) {
            entry.getValue().close();
        }
    }
}
