package org.chromium.content.browser;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.util.SparseArray;
import org.chromium.base.CalledByNative;
import org.chromium.base.JNINamespace;
import org.chromium.base.ThreadUtils;
import org.chromium.ui.base.WindowAndroid;

@JNINamespace("content")
public abstract class ContentReadbackHandler {
    static final /* synthetic */ boolean $assertionsDisabled = (!ContentReadbackHandler.class.desiredAssertionStatus());
    private SparseArray<GetBitmapCallback> mGetBitmapRequests = new SparseArray<>();
    private long mNativeContentReadbackHandler;
    private int mNextReadbackId = 1;

    public interface GetBitmapCallback {
        void onFinishGetBitmap(Bitmap bitmap);
    }

    private native void nativeDestroy(long j);

    private native void nativeGetCompositorBitmap(long j, int i, long j2);

    private native void nativeGetContentBitmap(long j, int i, float f, Bitmap.Config config, float f2, float f3, float f4, float f5, Object obj);

    private native long nativeInit();

    /* access modifiers changed from: protected */
    public abstract boolean readyForReadback();

    public void initNativeContentReadbackHandler() {
        this.mNativeContentReadbackHandler = nativeInit();
    }

    public void destroy() {
        if (this.mNativeContentReadbackHandler != 0) {
            nativeDestroy(this.mNativeContentReadbackHandler);
        }
        this.mNativeContentReadbackHandler = 0;
    }

    @CalledByNative
    private void notifyGetBitmapFinished(int readbackId, Bitmap bitmap) {
        GetBitmapCallback callback = this.mGetBitmapRequests.get(readbackId);
        if (callback != null) {
            this.mGetBitmapRequests.delete(readbackId);
            callback.onFinishGetBitmap(bitmap);
        } else if (!$assertionsDisabled) {
            throw new AssertionError("Readback finished for unregistered Id: " + readbackId);
        }
    }

    public void getContentBitmapAsync(float scale, Rect srcRect, ContentViewCore view, GetBitmapCallback callback) {
        if (!readyForReadback()) {
            callback.onFinishGetBitmap((Bitmap) null);
            return;
        }
        ThreadUtils.assertOnUiThread();
        int readbackId = this.mNextReadbackId;
        this.mNextReadbackId = readbackId + 1;
        this.mGetBitmapRequests.put(readbackId, callback);
        nativeGetContentBitmap(this.mNativeContentReadbackHandler, readbackId, scale, Bitmap.Config.ARGB_8888, (float) srcRect.top, (float) srcRect.left, (float) srcRect.width(), (float) srcRect.height(), view);
    }

    public void getCompositorBitmapAsync(WindowAndroid windowAndroid, GetBitmapCallback callback) {
        if (!readyForReadback()) {
            callback.onFinishGetBitmap((Bitmap) null);
            return;
        }
        ThreadUtils.assertOnUiThread();
        int readbackId = this.mNextReadbackId;
        this.mNextReadbackId = readbackId + 1;
        this.mGetBitmapRequests.put(readbackId, callback);
        nativeGetCompositorBitmap(this.mNativeContentReadbackHandler, readbackId, windowAndroid.getNativePointer());
    }
}
