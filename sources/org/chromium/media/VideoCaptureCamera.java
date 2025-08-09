package org.chromium.media;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.opengl.GLES20;
import android.util.Log;
import android.view.WindowManager;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import org.chromium.base.JNINamespace;

@JNINamespace("media")
public abstract class VideoCaptureCamera extends VideoCapture implements Camera.PreviewCallback {
    protected static final int GL_TEXTURE_EXTERNAL_OES = 36197;
    private static final String TAG = "VideoCaptureCamera";
    protected Camera mCamera;
    protected int mCameraFacing;
    protected int mCameraOrientation;
    protected int mDeviceOrientation;
    protected int[] mGlTextures = null;
    protected boolean mIsRunning = false;
    protected ReentrantLock mPreviewBufferLock = new ReentrantLock();
    protected SurfaceTexture mSurfaceTexture = null;

    /* access modifiers changed from: package-private */
    public abstract void setCaptureParameters(int i, int i2, int i3, Camera.Parameters parameters);

    /* access modifiers changed from: package-private */
    public abstract void setPreviewCallback(Camera.PreviewCallback previewCallback);

    protected static Camera.CameraInfo getCameraInfo(int id) {
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        try {
            Camera.getCameraInfo(id, cameraInfo);
            return cameraInfo;
        } catch (RuntimeException ex) {
            Log.e(TAG, "getCameraInfo: Camera.getCameraInfo: " + ex);
            return null;
        }
    }

    protected static Camera.Parameters getCameraParameters(Camera camera) {
        try {
            return camera.getParameters();
        } catch (RuntimeException ex) {
            Log.e(TAG, "getCameraParameters: android.hardware.Camera.getParameters: " + ex);
            camera.release();
            return null;
        }
    }

    VideoCaptureCamera(Context context, int id, long nativeVideoCaptureDeviceAndroid) {
        super(context, id, nativeVideoCaptureDeviceAndroid);
    }

    public boolean allocate(int width, int height, int frameRate) {
        int frameRateNearest;
        Log.d(TAG, "allocate: requested (" + width + "x" + height + ")@" + frameRate + "fps");
        try {
            this.mCamera = Camera.open(this.mId);
            Camera.CameraInfo cameraInfo = getCameraInfo(this.mId);
            if (cameraInfo == null) {
                this.mCamera.release();
                this.mCamera = null;
                return false;
            }
            this.mCameraOrientation = cameraInfo.orientation;
            this.mCameraFacing = cameraInfo.facing;
            this.mDeviceOrientation = getDeviceOrientation();
            Log.d(TAG, "allocate: orientation dev=" + this.mDeviceOrientation + ", cam=" + this.mCameraOrientation + ", facing=" + this.mCameraFacing);
            Camera.Parameters parameters = getCameraParameters(this.mCamera);
            if (parameters == null) {
                this.mCamera = null;
                return false;
            }
            List<int[]> listFpsRange = parameters.getSupportedPreviewFpsRange();
            if (listFpsRange == null || listFpsRange.size() == 0) {
                Log.e(TAG, "allocate: no fps range found");
                return false;
            }
            int frameRateScaled = frameRate * 1000;
            int[] chosenFpsRange = listFpsRange.get(0);
            if (Math.abs(frameRateScaled - chosenFpsRange[0]) < Math.abs(frameRateScaled - chosenFpsRange[1])) {
                frameRateNearest = chosenFpsRange[0];
            } else {
                frameRateNearest = chosenFpsRange[1];
            }
            int chosenFrameRate = (frameRateNearest + 999) / 1000;
            int fpsRangeSize = Integer.MAX_VALUE;
            for (int[] fpsRange : listFpsRange) {
                if (fpsRange[0] <= frameRateScaled && frameRateScaled <= fpsRange[1] && fpsRange[1] - fpsRange[0] <= fpsRangeSize) {
                    chosenFpsRange = fpsRange;
                    chosenFrameRate = frameRate;
                    fpsRangeSize = fpsRange[1] - fpsRange[0];
                }
            }
            Log.d(TAG, "allocate: fps set to " + chosenFrameRate + ", [" + chosenFpsRange[0] + "-" + chosenFpsRange[1] + "]");
            int minDiff = Integer.MAX_VALUE;
            int matchedWidth = width;
            int matchedHeight = height;
            for (Camera.Size size : parameters.getSupportedPreviewSizes()) {
                int diff = Math.abs(size.width - width) + Math.abs(size.height - height);
                Log.d(TAG, "allocate: supported (" + size.width + ", " + size.height + "), diff=" + diff);
                if (diff < minDiff && size.width % 32 == 0) {
                    minDiff = diff;
                    matchedWidth = size.width;
                    matchedHeight = size.height;
                }
            }
            if (minDiff == Integer.MAX_VALUE) {
                Log.e(TAG, "allocate: can not find a multiple-of-32 resolution");
                return false;
            }
            Log.d(TAG, "allocate: matched (" + matchedWidth + "x" + matchedHeight + ")");
            if (parameters.isVideoStabilizationSupported()) {
                Log.d(TAG, "Image stabilization supported, currently: " + parameters.getVideoStabilization() + ", setting it.");
                parameters.setVideoStabilization(true);
            } else {
                Log.d(TAG, "Image stabilization not supported.");
            }
            setCaptureParameters(matchedWidth, matchedHeight, chosenFrameRate, parameters);
            parameters.setPictureSize(matchedWidth, matchedHeight);
            parameters.setPreviewSize(matchedWidth, matchedHeight);
            parameters.setPreviewFpsRange(chosenFpsRange[0], chosenFpsRange[1]);
            parameters.setPreviewFormat(this.mCaptureFormat.mPixelFormat);
            try {
                this.mCamera.setParameters(parameters);
                this.mGlTextures = new int[1];
                GLES20.glGenTextures(1, this.mGlTextures, 0);
                GLES20.glBindTexture(GL_TEXTURE_EXTERNAL_OES, this.mGlTextures[0]);
                GLES20.glTexParameterf(GL_TEXTURE_EXTERNAL_OES, 10241, 9729.0f);
                GLES20.glTexParameterf(GL_TEXTURE_EXTERNAL_OES, 10240, 9729.0f);
                GLES20.glTexParameteri(GL_TEXTURE_EXTERNAL_OES, 10242, 33071);
                GLES20.glTexParameteri(GL_TEXTURE_EXTERNAL_OES, 10243, 33071);
                this.mSurfaceTexture = new SurfaceTexture(this.mGlTextures[0]);
                this.mSurfaceTexture.setOnFrameAvailableListener((SurfaceTexture.OnFrameAvailableListener) null);
                try {
                    this.mCamera.setPreviewTexture(this.mSurfaceTexture);
                    allocateBuffers();
                    return true;
                } catch (IOException ex) {
                    Log.e(TAG, "allocate: " + ex);
                    return false;
                }
            } catch (RuntimeException ex2) {
                Log.e(TAG, "setParameters: " + ex2);
                return false;
            }
        } catch (RuntimeException ex3) {
            Log.e(TAG, "allocate: Camera.open: " + ex3);
            return false;
        }
    }

    /* JADX INFO: finally extract failed */
    public int startCapture() {
        if (this.mCamera == null) {
            Log.e(TAG, "startCapture: camera is null");
            return -1;
        }
        this.mPreviewBufferLock.lock();
        try {
            if (this.mIsRunning) {
                this.mPreviewBufferLock.unlock();
                return 0;
            }
            this.mIsRunning = true;
            this.mPreviewBufferLock.unlock();
            setPreviewCallback(this);
            try {
                this.mCamera.startPreview();
                return 0;
            } catch (RuntimeException ex) {
                Log.e(TAG, "startCapture: Camera.startPreview: " + ex);
                return -1;
            }
        } catch (Throwable th) {
            this.mPreviewBufferLock.unlock();
            throw th;
        }
    }

    public int stopCapture() {
        if (this.mCamera == null) {
            Log.e(TAG, "stopCapture: camera is null");
        } else {
            this.mPreviewBufferLock.lock();
            try {
                if (this.mIsRunning) {
                    this.mIsRunning = false;
                    this.mPreviewBufferLock.unlock();
                    this.mCamera.stopPreview();
                    setPreviewCallback((Camera.PreviewCallback) null);
                }
            } finally {
                this.mPreviewBufferLock.unlock();
            }
        }
        return 0;
    }

    public void deallocate() {
        if (this.mCamera != null) {
            stopCapture();
            try {
                this.mCamera.setPreviewTexture((SurfaceTexture) null);
                if (this.mGlTextures != null) {
                    GLES20.glDeleteTextures(1, this.mGlTextures, 0);
                }
                this.mCaptureFormat = null;
                this.mCamera.release();
                this.mCamera = null;
            } catch (IOException ex) {
                Log.e(TAG, "deallocate: failed to deallocate camera, " + ex);
            }
        }
    }

    /* access modifiers changed from: protected */
    public int getDeviceOrientation() {
        if (this.mContext == null) {
            return 0;
        }
        switch (((WindowManager) this.mContext.getSystemService("window")).getDefaultDisplay().getRotation()) {
            case 1:
                return 90;
            case 2:
                return 180;
            case 3:
                return 270;
            default:
                return 0;
        }
    }
}
