package org.chromium.media;

import android.content.Context;
import android.hardware.Camera;
import android.util.Log;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import org.chromium.media.VideoCapture;

public class VideoCaptureTango extends VideoCaptureCamera {
    private static final CamParams[] CAM_PARAMS = {new CamParams(0, "depth", 320, SF_LINES_FISHEYE), new CamParams(1, "fisheye", 640, 480), new CamParams(2, "4MP", SF_WIDTH, SF_LINES_BIGIMAGE)};
    private static final byte CHROMA_ZERO_LEVEL = Byte.MAX_VALUE;
    private static final int DEPTH_CAMERA_ID = 0;
    private static final int FISHEYE_CAMERA_ID = 1;
    private static final int FOURMP_CAMERA_ID = 2;
    private static final int SF_FULL_HEIGHT = 1752;
    private static final int SF_HEIGHT = 1168;
    private static final int SF_LINES_BIGIMAGE = 720;
    private static final int SF_LINES_DEPTH = 60;
    private static final int SF_LINES_DEPTH_PADDED = 112;
    private static final int SF_LINES_FISHEYE = 240;
    private static final int SF_LINES_HEADER = 16;
    private static final int SF_LINES_RESERVED = 80;
    private static final int SF_OFFSET_4MP_CHROMA = 112;
    private static final int SF_WIDTH = 1280;
    private static final String TAG = "VideoCaptureTango";
    private ByteBuffer mFrameBuffer = null;
    private final int mTangoCameraId;

    private static class CamParams {
        final int mHeight;
        final int mId;
        final String mName;
        final int mWidth;

        CamParams(int id, String name, int width, int height) {
            this.mId = id;
            this.mName = name;
            this.mWidth = width;
            this.mHeight = height;
        }
    }

    static int numberOfCameras() {
        return CAM_PARAMS.length;
    }

    static String getName(int index) {
        if (index >= CAM_PARAMS.length) {
            return "";
        }
        return CAM_PARAMS[index].mName;
    }

    static VideoCapture.CaptureFormat[] getDeviceSupportedFormats(int id) {
        ArrayList<VideoCapture.CaptureFormat> formatList = new ArrayList<>();
        if (id == 0) {
            formatList.add(new VideoCapture.CaptureFormat(320, 180, 5, AndroidImageFormat.YV12));
        } else if (id == 1) {
            formatList.add(new VideoCapture.CaptureFormat(640, 480, 30, AndroidImageFormat.YV12));
        } else if (id == 2) {
            formatList.add(new VideoCapture.CaptureFormat(SF_WIDTH, SF_LINES_BIGIMAGE, 20, AndroidImageFormat.YV12));
        }
        return (VideoCapture.CaptureFormat[]) formatList.toArray(new VideoCapture.CaptureFormat[formatList.size()]);
    }

    VideoCaptureTango(Context context, int id, long nativeVideoCaptureDeviceAndroid) {
        super(context, 0, nativeVideoCaptureDeviceAndroid);
        this.mTangoCameraId = id;
    }

    /* access modifiers changed from: protected */
    public void setCaptureParameters(int width, int height, int frameRate, Camera.Parameters cameraParameters) {
        this.mCaptureFormat = new VideoCapture.CaptureFormat(CAM_PARAMS[this.mTangoCameraId].mWidth, CAM_PARAMS[this.mTangoCameraId].mHeight, frameRate, AndroidImageFormat.YV12);
        cameraParameters.set("sf-mode", "all");
    }

    /* access modifiers changed from: protected */
    public void allocateBuffers() {
        this.mFrameBuffer = ByteBuffer.allocateDirect(((this.mCaptureFormat.mWidth * this.mCaptureFormat.mHeight) * 3) / 2);
        Arrays.fill(this.mFrameBuffer.array(), CHROMA_ZERO_LEVEL);
    }

    /* access modifiers changed from: protected */
    public void setPreviewCallback(Camera.PreviewCallback cb) {
        this.mCamera.setPreviewCallback(cb);
    }

    public void onPreviewFrame(byte[] data, Camera camera) {
        this.mPreviewBufferLock.lock();
        try {
            if (this.mIsRunning) {
                if (data.length == 2242560) {
                    int rotation = getDeviceOrientation();
                    if (rotation != this.mDeviceOrientation) {
                        this.mDeviceOrientation = rotation;
                    }
                    if (this.mCameraFacing == 0) {
                        rotation = 360 - rotation;
                    }
                    int rotation2 = (this.mCameraOrientation + rotation) % 360;
                    if (this.mTangoCameraId == 0) {
                        for (int j = 430080; j < 583680; j += 2) {
                            this.mFrameBuffer.put((byte) ((data[j + 1] << 4) | ((data[j] & 240) >> 4)));
                        }
                        for (int j2 = 0; j2 < (this.mCaptureFormat.mWidth * this.mCaptureFormat.mHeight) - 76800; j2++) {
                            this.mFrameBuffer.put((byte) 0);
                        }
                    } else if (this.mTangoCameraId == 1) {
                        ByteBuffer.wrap(data, 20480, 307200).get(this.mFrameBuffer.array(), 0, 307200);
                    } else if (this.mTangoCameraId == 2) {
                        ByteBuffer.wrap(data, 573440, 921600).get(this.mFrameBuffer.array(), 0, 921600);
                        ByteBuffer.wrap(data, 1638400, 230400).get(this.mFrameBuffer.array(), 921600, 230400);
                        ByteBuffer.wrap(data, 2012160, 230400).get(this.mFrameBuffer.array(), 1152000, 230400);
                    } else {
                        Log.e(TAG, "Unknown camera, #id: " + this.mTangoCameraId);
                        this.mPreviewBufferLock.unlock();
                        return;
                    }
                    this.mFrameBuffer.rewind();
                    nativeOnFrameAvailable(this.mNativeVideoCaptureDeviceAndroid, this.mFrameBuffer.array(), this.mFrameBuffer.capacity(), rotation2);
                }
                this.mPreviewBufferLock.unlock();
            }
        } finally {
            this.mPreviewBufferLock.unlock();
        }
    }
}
