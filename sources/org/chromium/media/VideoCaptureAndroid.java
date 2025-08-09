package org.chromium.media;

import android.content.Context;
import android.graphics.ImageFormat;
import android.hardware.Camera;
import android.os.Build;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import org.chromium.media.VideoCapture;

public class VideoCaptureAndroid extends VideoCaptureCamera {
    private static final int NUM_CAPTURE_BUFFERS = 3;
    private static final String TAG = "VideoCaptureAndroid";
    private int mExpectedFrameSize;

    private static class BuggyDeviceHack {
        private static final String[] COLORSPACE_BUGGY_DEVICE_LIST = {"SAMSUNG-SGH-I747", "ODROID-U2"};

        private BuggyDeviceHack() {
        }

        static int getImageFormat() {
            if (Build.VERSION.SDK_INT < 16) {
                return 17;
            }
            for (String buggyDevice : COLORSPACE_BUGGY_DEVICE_LIST) {
                if (buggyDevice.contentEquals(Build.MODEL)) {
                    return 17;
                }
            }
            return AndroidImageFormat.YV12;
        }
    }

    static int getNumberOfCameras() {
        return Camera.getNumberOfCameras();
    }

    static String getName(int id) {
        Camera.CameraInfo cameraInfo = VideoCaptureCamera.getCameraInfo(id);
        if (cameraInfo == null) {
            return null;
        }
        return "camera " + id + ", facing " + (cameraInfo.facing == 1 ? "front" : "back");
    }

    static VideoCapture.CaptureFormat[] getDeviceSupportedFormats(int id) {
        try {
            Camera camera = Camera.open(id);
            Camera.Parameters parameters = getCameraParameters(camera);
            if (parameters == null) {
                return null;
            }
            ArrayList<VideoCapture.CaptureFormat> formatList = new ArrayList<>();
            List<Integer> pixelFormats = parameters.getSupportedPreviewFormats();
            if (pixelFormats == null) {
                pixelFormats = new ArrayList<>();
            }
            if (pixelFormats.size() == 0) {
                pixelFormats.add((Integer) null);
            }
            for (Integer previewFormat : pixelFormats) {
                int pixelFormat = 0;
                if (previewFormat.intValue() == 842094169) {
                    pixelFormat = AndroidImageFormat.YV12;
                } else if (previewFormat.intValue() == 17) {
                }
                List<int[]> listFpsRange = parameters.getSupportedPreviewFpsRange();
                if (listFpsRange == null) {
                    listFpsRange = new ArrayList<>();
                }
                if (listFpsRange.size() == 0) {
                    listFpsRange.add(new int[]{0, 0});
                }
                for (int[] fpsRange : listFpsRange) {
                    List<Camera.Size> supportedSizes = parameters.getSupportedPreviewSizes();
                    if (supportedSizes == null) {
                        supportedSizes = new ArrayList<>();
                    }
                    if (supportedSizes.size() == 0) {
                        camera.getClass();
                        supportedSizes.add(new Camera.Size(camera, 0, 0));
                    }
                    for (Camera.Size size : supportedSizes) {
                        formatList.add(new VideoCapture.CaptureFormat(size.width, size.height, (fpsRange[1] + 999) / 1000, pixelFormat));
                    }
                }
            }
            camera.release();
            return (VideoCapture.CaptureFormat[]) formatList.toArray(new VideoCapture.CaptureFormat[formatList.size()]);
        } catch (RuntimeException ex) {
            Log.e(TAG, "Camera.open: " + ex);
            return null;
        }
    }

    VideoCaptureAndroid(Context context, int id, long nativeVideoCaptureDeviceAndroid) {
        super(context, id, nativeVideoCaptureDeviceAndroid);
    }

    /* access modifiers changed from: protected */
    public void setCaptureParameters(int width, int height, int frameRate, Camera.Parameters cameraParameters) {
        this.mCaptureFormat = new VideoCapture.CaptureFormat(width, height, frameRate, BuggyDeviceHack.getImageFormat());
    }

    /* access modifiers changed from: protected */
    public void allocateBuffers() {
        this.mExpectedFrameSize = ((this.mCaptureFormat.mWidth * this.mCaptureFormat.mHeight) * ImageFormat.getBitsPerPixel(this.mCaptureFormat.mPixelFormat)) / 8;
        for (int i = 0; i < 3; i++) {
            this.mCamera.addCallbackBuffer(new byte[this.mExpectedFrameSize]);
        }
    }

    /* access modifiers changed from: protected */
    public void setPreviewCallback(Camera.PreviewCallback cb) {
        this.mCamera.setPreviewCallbackWithBuffer(cb);
    }

    public void onPreviewFrame(byte[] data, Camera camera) {
        this.mPreviewBufferLock.lock();
        try {
            if (this.mIsRunning) {
                if (data.length == this.mExpectedFrameSize) {
                    int rotation = getDeviceOrientation();
                    if (rotation != this.mDeviceOrientation) {
                        this.mDeviceOrientation = rotation;
                    }
                    if (this.mCameraFacing == 0) {
                        rotation = 360 - rotation;
                    }
                    byte[] bArr = data;
                    nativeOnFrameAvailable(this.mNativeVideoCaptureDeviceAndroid, bArr, this.mExpectedFrameSize, (this.mCameraOrientation + rotation) % 360);
                }
                this.mPreviewBufferLock.unlock();
                if (camera != null) {
                    camera.addCallbackBuffer(data);
                }
            }
        } finally {
            this.mPreviewBufferLock.unlock();
            if (camera != null) {
                camera.addCallbackBuffer(data);
            }
        }
    }
}
