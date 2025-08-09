package org.chromium.base;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import java.io.File;
import java.io.FileNotFoundException;

public abstract class ContentUriUtils {
    private static final String TAG = "ContentUriUtils";
    private static FileProviderUtil sFileProviderUtil;

    public interface FileProviderUtil {
        Uri getContentUriFromFile(Context context, File file);
    }

    private ContentUriUtils() {
    }

    public static void setFileProviderUtil(FileProviderUtil util) {
        sFileProviderUtil = util;
    }

    public static Uri getContentUriFromFile(Context context, File file) {
        ThreadUtils.assertOnUiThread();
        if (sFileProviderUtil != null) {
            return sFileProviderUtil.getContentUriFromFile(context, file);
        }
        return null;
    }

    @CalledByNative
    public static int openContentUriForRead(Context context, String uriString) {
        ParcelFileDescriptor pfd = getParcelFileDescriptor(context, uriString);
        if (pfd != null) {
            return pfd.detachFd();
        }
        return -1;
    }

    @CalledByNative
    public static boolean contentUriExists(Context context, String uriString) {
        if (getParcelFileDescriptor(context, uriString) == null) {
            return false;
        }
        return true;
    }

    private static ParcelFileDescriptor getParcelFileDescriptor(Context context, String uriString) {
        try {
            return context.getContentResolver().openFileDescriptor(Uri.parse(uriString), "r");
        } catch (FileNotFoundException e) {
            Log.w(TAG, "Cannot find content uri: " + uriString, e);
            return null;
        }
    }

    public static String getDisplayName(Uri uri, ContentResolver contentResolver, String columnField) {
        if (contentResolver == null || uri == null) {
            return "";
        }
        Cursor cursor = null;
        try {
            Cursor cursor2 = contentResolver.query(uri, (String[]) null, (String) null, (String[]) null, (String) null);
            if (cursor2 != null && cursor2.getCount() >= 1) {
                cursor2.moveToFirst();
                int index = cursor2.getColumnIndex(columnField);
                if (index > -1) {
                    String string = cursor2.getString(index);
                    if (cursor2 == null) {
                        return string;
                    }
                    cursor2.close();
                    return string;
                }
            }
            if (cursor2 != null) {
                cursor2.close();
            }
            return "";
        } catch (NullPointerException e) {
            if (cursor == null) {
                return "";
            }
            cursor.close();
            return "";
        }
    }
}
