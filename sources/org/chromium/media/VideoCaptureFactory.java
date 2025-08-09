package org.chromium.media;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import org.chromium.base.CalledByNative;
import org.chromium.base.JNINamespace;
import org.chromium.media.VideoCapture;

@JNINamespace("media")
class VideoCaptureFactory {
    VideoCaptureFactory() {
    }

    static class ChromiumCameraInfo {
        static final /* synthetic */ boolean $assertionsDisabled = (!VideoCaptureFactory.class.desiredAssertionStatus());
        private static final String[][] SPECIAL_DEVICE_LIST = {new String[]{"Peanut", "peanut"}};
        private static final String TAG = "ChromiumCameraInfo";
        private static int sNumberOfSystemCameras = -1;

        ChromiumCameraInfo() {
        }

        private static boolean isSpecialDevice() {
            for (String[] device : SPECIAL_DEVICE_LIST) {
                if (device[0].contentEquals(Build.MODEL) && device[1].contentEquals(Build.DEVICE)) {
                    return true;
                }
            }
            return false;
        }

        /* access modifiers changed from: private */
        public static boolean isSpecialCamera(int id) {
            return id >= sNumberOfSystemCameras;
        }

        /* access modifiers changed from: private */
        public static int toSpecialCameraId(int id) {
            if ($assertionsDisabled || isSpecialCamera(id)) {
                return id - sNumberOfSystemCameras;
            }
            throw new AssertionError();
        }

        /* access modifiers changed from: private */
        public static int getNumberOfCameras(Context appContext) {
            if (sNumberOfSystemCameras == -1) {
                if (appContext.getPackageManager().checkPermission("android.permission.CAMERA", appContext.getPackageName()) == 0) {
                    sNumberOfSystemCameras = VideoCaptureAndroid.getNumberOfCameras();
                } else {
                    sNumberOfSystemCameras = 0;
                    Log.w(TAG, "Missing android.permission.CAMERA permission, no system camera available.");
                }
            }
            if (!isSpecialDevice()) {
                return sNumberOfSystemCameras;
            }
            Log.d(TAG, "Special device: " + Build.MODEL);
            return sNumberOfSystemCameras + VideoCaptureTango.numberOfCameras();
        }
    }

    @CalledByNative
    static VideoCapture createVideoCapture(Context context, int id, long nativeVideoCaptureDeviceAndroid) {
        if (ChromiumCameraInfo.isSpecialCamera(id)) {
            return new VideoCaptureTango(context, ChromiumCameraInfo.toSpecialCameraId(id), nativeVideoCaptureDeviceAndroid);
        }
        return new VideoCaptureAndroid(context, id, nativeVideoCaptureDeviceAndroid);
    }

    @CalledByNative
    static int getNumberOfCameras(Context appContext) {
        return ChromiumCameraInfo.getNumberOfCameras(appContext);
    }

    @CalledByNative
    static String getDeviceName(int id) {
        return ChromiumCameraInfo.isSpecialCamera(id) ? VideoCaptureTango.getName(ChromiumCameraInfo.toSpecialCameraId(id)) : VideoCaptureAndroid.getName(id);
    }

    @CalledByNative
    static VideoCapture.CaptureFormat[] getDeviceSupportedFormats(int id) {
        return ChromiumCameraInfo.isSpecialCamera(id) ? VideoCaptureTango.getDeviceSupportedFormats(ChromiumCameraInfo.toSpecialCameraId(id)) : VideoCaptureAndroid.getDeviceSupportedFormats(id);
    }

    @CalledByNative
    static int getCaptureFormatWidth(VideoCapture.CaptureFormat format) {
        return format.getWidth();
    }

    @CalledByNative
    static int getCaptureFormatHeight(VideoCapture.CaptureFormat format) {
        return format.getHeight();
    }

    @CalledByNative
    static int getCaptureFormatFramerate(VideoCapture.CaptureFormat format) {
        return format.getFramerate();
    }

    @CalledByNative
    static int getCaptureFormatPixelFormat(VideoCapture.CaptureFormat format) {
        return format.getPixelFormat();
    }
}
