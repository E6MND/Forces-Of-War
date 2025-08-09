package org.chromium.content.browser;

import org.chromium.base.CalledByNative;
import org.chromium.base.JNINamespace;

@JNINamespace("content")
public class InterstitialPageDelegateAndroid {
    private long mNativePtr;

    private native void nativeDontProceed(long j);

    private native long nativeInit(String str);

    private native void nativeProceed(long j);

    public InterstitialPageDelegateAndroid(String htmlContent) {
        this.mNativePtr = nativeInit(htmlContent);
    }

    public long getNative() {
        return this.mNativePtr;
    }

    /* access modifiers changed from: protected */
    @CalledByNative
    public void onProceed() {
    }

    /* access modifiers changed from: protected */
    @CalledByNative
    public void onDontProceed() {
    }

    /* access modifiers changed from: protected */
    @CalledByNative
    public void commandReceived(String command) {
    }

    @CalledByNative
    private void onNativeDestroyed() {
        this.mNativePtr = 0;
    }

    /* access modifiers changed from: protected */
    public void proceed() {
        if (this.mNativePtr != 0) {
            nativeProceed(this.mNativePtr);
        }
    }

    /* access modifiers changed from: protected */
    public void dontProceed() {
        if (this.mNativePtr != 0) {
            nativeDontProceed(this.mNativePtr);
        }
    }
}
