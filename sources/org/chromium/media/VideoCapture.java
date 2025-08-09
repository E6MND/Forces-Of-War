package org.chromium.media;

import android.content.Context;
import org.chromium.base.CalledByNative;
import org.chromium.base.JNINamespace;

@JNINamespace("media")
public abstract class VideoCapture {
    protected CaptureFormat mCaptureFormat = null;
    protected final Context mContext;
    protected final int mId;
    protected final long mNativeVideoCaptureDeviceAndroid;

    /* access modifiers changed from: package-private */
    @CalledByNative
    public abstract boolean allocate(int i, int i2, int i3);

    /* access modifiers changed from: package-private */
    public abstract void allocateBuffers();

    /* access modifiers changed from: package-private */
    @CalledByNative
    public abstract void deallocate();

    public native void nativeOnFrameAvailable(long j, byte[] bArr, int i, int i2);

    /* access modifiers changed from: package-private */
    @CalledByNative
    public abstract int startCapture();

    /* access modifiers changed from: package-private */
    @CalledByNative
    public abstract int stopCapture();

    protected static class CaptureFormat {
        final int mFramerate;
        int mHeight;
        final int mPixelFormat;
        int mWidth;

        public CaptureFormat(int width, int height, int framerate, int pixelformat) {
            this.mWidth = width;
            this.mHeight = height;
            this.mFramerate = framerate;
            this.mPixelFormat = pixelformat;
        }

        public int getWidth() {
            return this.mWidth;
        }

        public int getHeight() {
            return this.mHeight;
        }

        public int getFramerate() {
            return this.mFramerate;
        }

        public int getPixelFormat() {
            return this.mPixelFormat;
        }
    }

    VideoCapture(Context context, int id, long nativeVideoCaptureDeviceAndroid) {
        this.mContext = context;
        this.mId = id;
        this.mNativeVideoCaptureDeviceAndroid = nativeVideoCaptureDeviceAndroid;
    }

    @CalledByNative
    public final int queryWidth() {
        return this.mCaptureFormat.mWidth;
    }

    @CalledByNative
    public final int queryHeight() {
        return this.mCaptureFormat.mHeight;
    }

    @CalledByNative
    public final int queryFrameRate() {
        return this.mCaptureFormat.mFramerate;
    }

    @CalledByNative
    public final int getColorspace() {
        switch (this.mCaptureFormat.mPixelFormat) {
            case 17:
                return 17;
            case AndroidImageFormat.YV12:
                return AndroidImageFormat.YV12;
            default:
                return 0;
        }
    }
}
