package org.chromium.base;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Environment;

public abstract class PathUtils {
    private static String sDataDirectorySuffix;

    private PathUtils() {
    }

    public static void setPrivateDataDirectorySuffix(String suffix) {
        sDataDirectorySuffix = suffix;
    }

    @CalledByNative
    public static String getDataDirectory(Context appContext) {
        if (sDataDirectorySuffix != null) {
            return appContext.getDir(sDataDirectorySuffix, 0).getPath();
        }
        throw new IllegalStateException("setDataDirectorySuffix must be called before getDataDirectory");
    }

    @CalledByNative
    public static String getDatabaseDirectory(Context appContext) {
        return appContext.getDatabasePath("foo").getParent();
    }

    @CalledByNative
    public static String getCacheDirectory(Context appContext) {
        return appContext.getCacheDir().getPath();
    }

    @CalledByNative
    private static String getDownloadsDirectory(Context appContext) {
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
    }

    @CalledByNative
    private static String getNativeLibraryDirectory(Context appContext) {
        ApplicationInfo ai = appContext.getApplicationInfo();
        if ((ai.flags & 128) != 0 || (ai.flags & 1) == 0) {
            return ai.nativeLibraryDir;
        }
        return "/system/lib/";
    }

    @CalledByNative
    public static String getExternalStorageDirectory() {
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }
}
