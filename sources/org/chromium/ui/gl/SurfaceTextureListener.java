package org.chromium.ui.gl;

import android.graphics.SurfaceTexture;
import org.chromium.base.JNINamespace;

@JNINamespace("gfx")
class SurfaceTextureListener implements SurfaceTexture.OnFrameAvailableListener {
    static final /* synthetic */ boolean $assertionsDisabled = (!SurfaceTextureListener.class.desiredAssertionStatus());
    private final long mNativeSurfaceTextureListener;

    private native void nativeDestroy(long j);

    private native void nativeFrameAvailable(long j);

    SurfaceTextureListener(long nativeSurfaceTextureListener) {
        if ($assertionsDisabled || nativeSurfaceTextureListener != 0) {
            this.mNativeSurfaceTextureListener = nativeSurfaceTextureListener;
            return;
        }
        throw new AssertionError();
    }

    public void onFrameAvailable(SurfaceTexture surfaceTexture) {
        nativeFrameAvailable(this.mNativeSurfaceTextureListener);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        try {
            nativeDestroy(this.mNativeSurfaceTextureListener);
        } finally {
            super.finalize();
        }
    }
}
