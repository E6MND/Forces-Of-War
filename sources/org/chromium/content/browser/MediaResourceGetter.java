package org.chromium.content.browser;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.MediaMetadataRetriever;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.chromium.base.CalledByNative;
import org.chromium.base.JNINamespace;
import org.chromium.base.PathUtils;
import org.chromium.base.VisibleForTesting;

@JNINamespace("content")
class MediaResourceGetter {
    private static final MediaMetadata EMPTY_METADATA = new MediaMetadata(0, 0, 0, false);
    private static final String TAG = "MediaResourceGetter";
    private final MediaMetadataRetriever mRetriever = new MediaMetadataRetriever();

    MediaResourceGetter() {
    }

    @VisibleForTesting
    static class MediaMetadata {
        private final int mDurationInMilliseconds;
        private final int mHeight;
        private final boolean mSuccess;
        private final int mWidth;

        MediaMetadata(int durationInMilliseconds, int width, int height, boolean success) {
            this.mDurationInMilliseconds = durationInMilliseconds;
            this.mWidth = width;
            this.mHeight = height;
            this.mSuccess = success;
        }

        /* access modifiers changed from: package-private */
        @CalledByNative("MediaMetadata")
        public int getDurationInMilliseconds() {
            return this.mDurationInMilliseconds;
        }

        /* access modifiers changed from: package-private */
        @CalledByNative("MediaMetadata")
        public int getWidth() {
            return this.mWidth;
        }

        /* access modifiers changed from: package-private */
        @CalledByNative("MediaMetadata")
        public int getHeight() {
            return this.mHeight;
        }

        /* access modifiers changed from: package-private */
        @CalledByNative("MediaMetadata")
        public boolean isSuccess() {
            return this.mSuccess;
        }

        public String toString() {
            return "MediaMetadata[durationInMilliseconds=" + this.mDurationInMilliseconds + ", width=" + this.mWidth + ", height=" + this.mHeight + ", success=" + this.mSuccess + "]";
        }

        public int hashCode() {
            return ((((((this.mDurationInMilliseconds + 31) * 31) + this.mHeight) * 31) + (this.mSuccess ? 1231 : 1237)) * 31) + this.mWidth;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            MediaMetadata other = (MediaMetadata) obj;
            if (this.mDurationInMilliseconds != other.mDurationInMilliseconds) {
                return false;
            }
            if (this.mHeight != other.mHeight) {
                return false;
            }
            if (this.mSuccess != other.mSuccess) {
                return false;
            }
            if (this.mWidth != other.mWidth) {
                return false;
            }
            return true;
        }
    }

    @CalledByNative
    private static MediaMetadata extractMediaMetadata(Context context, String url, String cookies, String userAgent) {
        return new MediaResourceGetter().extract(context, url, cookies, userAgent);
    }

    @CalledByNative
    private static MediaMetadata extractMediaMetadataFromFd(int fd, long offset, long length) {
        return new MediaResourceGetter().extract(fd, offset, length);
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public MediaMetadata extract(int fd, long offset, long length) {
        if (!androidDeviceOk(Build.MODEL, Build.VERSION.SDK_INT)) {
            return EMPTY_METADATA;
        }
        configure(fd, offset, length);
        return doExtractMetadata();
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public MediaMetadata extract(Context context, String url, String cookies, String userAgent) {
        if (!androidDeviceOk(Build.MODEL, Build.VERSION.SDK_INT)) {
            return EMPTY_METADATA;
        }
        if (configure(context, url, cookies, userAgent)) {
            return doExtractMetadata();
        }
        Log.e(TAG, "Unable to configure metadata extractor");
        return EMPTY_METADATA;
    }

    private MediaMetadata doExtractMetadata() {
        try {
            String durationString = extractMetadata(9);
            if (durationString == null) {
                Log.w(TAG, "missing duration metadata");
                return EMPTY_METADATA;
            }
            try {
                int durationMillis = Integer.parseInt(durationString);
                int width = 0;
                int height = 0;
                boolean hasVideo = "yes".equals(extractMetadata(17));
                Log.d(TAG, hasVideo ? "resource has video" : "resource doesn't have video");
                if (hasVideo) {
                    String widthString = extractMetadata(18);
                    if (widthString == null) {
                        Log.w(TAG, "missing video width metadata");
                        return EMPTY_METADATA;
                    }
                    try {
                        width = Integer.parseInt(widthString);
                        String heightString = extractMetadata(19);
                        if (heightString == null) {
                            Log.w(TAG, "missing video height metadata");
                            return EMPTY_METADATA;
                        }
                        try {
                            height = Integer.parseInt(heightString);
                        } catch (NumberFormatException e) {
                            Log.w(TAG, "non-numeric height: " + heightString);
                            return EMPTY_METADATA;
                        }
                    } catch (NumberFormatException e2) {
                        Log.w(TAG, "non-numeric width: " + widthString);
                        return EMPTY_METADATA;
                    }
                }
                MediaMetadata result = new MediaMetadata(durationMillis, width, height, true);
                Log.d(TAG, "extracted valid metadata: " + result.toString());
                return result;
            } catch (NumberFormatException e3) {
                Log.w(TAG, "non-numeric duration: " + durationString);
                return EMPTY_METADATA;
            }
        } catch (RuntimeException e4) {
            Log.e(TAG, "Unable to extract medata", e4);
            return EMPTY_METADATA;
        }
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public boolean configure(Context context, String url, String cookies, String userAgent) {
        try {
            URI uri = URI.create(url);
            String scheme = uri.getScheme();
            if (scheme == null || scheme.equals(AndroidProtocolHandler.FILE_SCHEME) || scheme.equals("app")) {
                File file = uriToFile(uri.getPath());
                if (!file.exists()) {
                    Log.e(TAG, "File does not exist.");
                    return false;
                } else if (!filePathAcceptable(file, context)) {
                    Log.e(TAG, "Refusing to read from unsafe file location.");
                    return false;
                } else {
                    try {
                        configure(file.getAbsolutePath());
                        return true;
                    } catch (RuntimeException e) {
                        Log.e(TAG, "Error configuring data source", e);
                        return false;
                    }
                }
            } else if (isLoopbackAddress(uri.getHost()) || isNetworkReliable(context)) {
                Map<String, String> headersMap = new HashMap<>();
                if (!TextUtils.isEmpty(cookies)) {
                    headersMap.put("Cookie", cookies);
                }
                if (!TextUtils.isEmpty(userAgent)) {
                    headersMap.put("User-Agent", userAgent);
                }
                try {
                    configure(url, headersMap);
                    return true;
                } catch (RuntimeException e2) {
                    Log.e(TAG, "Error configuring data source", e2);
                    return false;
                }
            } else {
                Log.w(TAG, "non-file URI can't be read due to unsuitable network conditions");
                return false;
            }
        } catch (IllegalArgumentException e3) {
            Log.e(TAG, "Cannot parse uri.", e3);
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public boolean isNetworkReliable(Context context) {
        if (context.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") != 0) {
            Log.w(TAG, "permission denied to access network state");
            return false;
        }
        Integer networkType = getNetworkType(context);
        if (networkType == null) {
            return false;
        }
        switch (networkType.intValue()) {
            case 1:
            case 9:
                Log.d(TAG, "ethernet/wifi connection detected");
                return true;
            default:
                Log.d(TAG, "no ethernet/wifi connection detected");
                return false;
        }
    }

    private boolean isLoopbackAddress(String host) {
        return host != null && (host.equalsIgnoreCase("localhost") || host.equals("127.0.0.1") || host.equals("[::1]"));
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public boolean filePathAcceptable(File file, Context context) {
        try {
            String path = file.getCanonicalPath();
            List<String> acceptablePaths = canonicalize(getRawAcceptableDirectories(context));
            acceptablePaths.add(getExternalStorageDirectory());
            Log.d(TAG, "canonicalized file path: " + path);
            for (String acceptablePath : acceptablePaths) {
                if (path.startsWith(acceptablePath)) {
                    return true;
                }
            }
            return false;
        } catch (IOException e) {
            Log.w(TAG, "canonicalization of file path failed");
            return false;
        }
    }

    @VisibleForTesting
    static boolean androidDeviceOk(String model, int sdkVersion) {
        return !"GT-I9100".contentEquals(model) || sdkVersion >= 16;
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public File uriToFile(String path) {
        return new File(path);
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public Integer getNetworkType(Context context) {
        ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (mConnectivityManager == null) {
            Log.w(TAG, "no connectivity manager available");
            return null;
        }
        NetworkInfo info = mConnectivityManager.getActiveNetworkInfo();
        if (info != null) {
            return Integer.valueOf(info.getType());
        }
        Log.d(TAG, "no active network");
        return null;
    }

    @SuppressLint({"SdCardPath"})
    private List<String> getRawAcceptableDirectories(Context context) {
        List<String> result = new ArrayList<>();
        result.add("/mnt/sdcard/");
        result.add("/sdcard/");
        result.add("/data/data/" + context.getPackageName() + "/cache/");
        return result;
    }

    private List<String> canonicalize(List<String> paths) {
        List<String> result = new ArrayList<>(paths.size());
        try {
            for (String path : paths) {
                result.add(new File(path).getCanonicalPath());
            }
        } catch (IOException e) {
            Log.w(TAG, "canonicalization of file path failed");
        }
        return result;
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public String getExternalStorageDirectory() {
        return PathUtils.getExternalStorageDirectory();
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public void configure(int fd, long offset, long length) {
        ParcelFileDescriptor parcelFd = ParcelFileDescriptor.adoptFd(fd);
        try {
            this.mRetriever.setDataSource(parcelFd.getFileDescriptor(), offset, length);
            try {
            } catch (IOException e) {
                Log.e(TAG, "Failed to close file descriptor: " + e);
            }
        } finally {
            try {
                parcelFd.close();
            } catch (IOException e2) {
                Log.e(TAG, "Failed to close file descriptor: " + e2);
            }
        }
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public void configure(String url, Map<String, String> headers) {
        this.mRetriever.setDataSource(url, headers);
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public void configure(String path) {
        this.mRetriever.setDataSource(path);
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public String extractMetadata(int key) {
        return this.mRetriever.extractMetadata(key);
    }
}
