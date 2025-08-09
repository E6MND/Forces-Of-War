package org.chromium.ui.gl;

import android.graphics.SurfaceTexture;
import android.os.Build;
import android.util.Log;
import org.chromium.base.CalledByNative;
import org.chromium.base.JNINamespace;

@JNINamespace("gfx")
class SurfaceTexturePlatformWrapper {
    static final /* synthetic */ boolean $assertionsDisabled = (!SurfaceTexturePlatformWrapper.class.desiredAssertionStatus());
    private static final String TAG = "SurfaceTexturePlatformWrapper";

    SurfaceTexturePlatformWrapper() {
    }

    @CalledByNative
    private static SurfaceTexture create(int textureId) {
        return new SurfaceTexture(textureId);
    }

    @CalledByNative
    private static SurfaceTexture createSingleBuffered(int textureId) {
        if ($assertionsDisabled || Build.VERSION.SDK_INT >= 19) {
            return new SurfaceTexture(textureId, true);
        }
        throw new AssertionError();
    }

    @CalledByNative
    private static void destroy(SurfaceTexture surfaceTexture) {
        surfaceTexture.setOnFrameAvailableListener((SurfaceTexture.OnFrameAvailableListener) null);
        surfaceTexture.release();
    }

    @CalledByNative
    private static void setFrameAvailableCallback(SurfaceTexture surfaceTexture, long nativeSurfaceTextureListener) {
        surfaceTexture.setOnFrameAvailableListener(new SurfaceTextureListener(nativeSurfaceTextureListener));
    }

    @CalledByNative
    private static void updateTexImage(SurfaceTexture surfaceTexture) {
        try {
            surfaceTexture.updateTexImage();
        } catch (RuntimeException e) {
            Log.e(TAG, "Error calling updateTexImage", e);
        }
    }

    @CalledByNative
    private static void releaseTexImage(SurfaceTexture surfaceTexture) {
        if ($assertionsDisabled || Build.VERSION.SDK_INT >= 19) {
            surfaceTexture.releaseTexImage();
            return;
        }
        throw new AssertionError();
    }

    @CalledByNative
    private static void setDefaultBufferSize(SurfaceTexture surfaceTexture, int width, int height) {
        surfaceTexture.setDefaultBufferSize(width, height);
    }

    @CalledByNative
    private static void getTransformMatrix(SurfaceTexture surfaceTexture, float[] matrix) {
        surfaceTexture.getTransformMatrix(matrix);
    }

    @CalledByNative
    private static void attachToGLContext(SurfaceTexture surfaceTexture, int texName) {
        if ($assertionsDisabled || Build.VERSION.SDK_INT >= 16) {
            surfaceTexture.attachToGLContext(texName);
            return;
        }
        throw new AssertionError();
    }

    @CalledByNative
    private static void detachFromGLContext(SurfaceTexture surfaceTexture) {
        if ($assertionsDisabled || Build.VERSION.SDK_INT >= 16) {
            surfaceTexture.detachFromGLContext();
            return;
        }
        throw new AssertionError();
    }
}
