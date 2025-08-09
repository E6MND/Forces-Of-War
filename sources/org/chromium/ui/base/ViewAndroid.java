package org.chromium.ui.base;

import android.view.View;
import org.chromium.base.JNINamespace;

@JNINamespace("ui")
public class ViewAndroid {
    static final /* synthetic */ boolean $assertionsDisabled = (!ViewAndroid.class.desiredAssertionStatus());
    private int mKeepScreenOnCount;
    private View mKeepScreenOnView;
    private long mNativeViewAndroid = 0;
    private final ViewAndroidDelegate mViewAndroidDelegate;
    private final WindowAndroid mWindowAndroid;

    private native void nativeDestroy(long j);

    private native long nativeInit(long j);

    public ViewAndroid(WindowAndroid nativeWindow, ViewAndroidDelegate viewAndroidDelegate) {
        this.mWindowAndroid = nativeWindow;
        this.mViewAndroidDelegate = viewAndroidDelegate;
        this.mNativeViewAndroid = nativeInit(this.mWindowAndroid.getNativePointer());
    }

    public ViewAndroidDelegate getViewAndroidDelegate() {
        return this.mViewAndroidDelegate;
    }

    public void destroy() {
        if (this.mNativeViewAndroid != 0) {
            nativeDestroy(this.mNativeViewAndroid);
            this.mNativeViewAndroid = 0;
        }
    }

    public long getNativePointer() {
        return this.mNativeViewAndroid;
    }

    public void incrementKeepScreenOnCount() {
        this.mKeepScreenOnCount++;
        if (this.mKeepScreenOnCount == 1) {
            this.mKeepScreenOnView = this.mViewAndroidDelegate.acquireAnchorView();
            this.mViewAndroidDelegate.setAnchorViewPosition(this.mKeepScreenOnView, 0.0f, 0.0f, 0.0f, 0.0f);
            this.mKeepScreenOnView.setKeepScreenOn(true);
        }
    }

    public void decrementKeepScreenOnCount() {
        if ($assertionsDisabled || this.mKeepScreenOnCount > 0) {
            this.mKeepScreenOnCount--;
            if (this.mKeepScreenOnCount == 0) {
                this.mViewAndroidDelegate.releaseAnchorView(this.mKeepScreenOnView);
                this.mKeepScreenOnView = null;
                return;
            }
            return;
        }
        throw new AssertionError();
    }
}
