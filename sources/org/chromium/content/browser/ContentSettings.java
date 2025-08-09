package org.chromium.content.browser;

import org.chromium.base.CalledByNative;
import org.chromium.base.JNINamespace;
import org.chromium.base.ThreadUtils;

@JNINamespace("content")
public class ContentSettings {
    static final /* synthetic */ boolean $assertionsDisabled = (!ContentSettings.class.desiredAssertionStatus());
    private static final String TAG = "ContentSettings";
    private ContentViewCore mContentViewCore;
    private long mNativeContentSettings = 0;

    private native boolean nativeGetJavaScriptEnabled(long j);

    private native long nativeInit(long j);

    ContentSettings(ContentViewCore contentViewCore, long nativeContentView) {
        ThreadUtils.assertOnUiThread();
        this.mContentViewCore = contentViewCore;
        this.mNativeContentSettings = nativeInit(nativeContentView);
        if (!$assertionsDisabled && this.mNativeContentSettings == 0) {
            throw new AssertionError();
        }
    }

    @CalledByNative
    private void onNativeContentSettingsDestroyed(long nativeContentSettings) {
        if ($assertionsDisabled || this.mNativeContentSettings == nativeContentSettings) {
            this.mNativeContentSettings = 0;
            return;
        }
        throw new AssertionError();
    }

    public boolean getJavaScriptEnabled() {
        ThreadUtils.assertOnUiThread();
        if (this.mNativeContentSettings != 0) {
            return nativeGetJavaScriptEnabled(this.mNativeContentSettings);
        }
        return false;
    }
}
