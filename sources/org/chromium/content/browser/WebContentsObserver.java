package org.chromium.content.browser;

import org.chromium.base.CalledByNative;
import org.chromium.base.JNINamespace;
import org.chromium.base.ThreadUtils;
import org.chromium.content_public.browser.WebContents;

@JNINamespace("content")
public abstract class WebContentsObserver {
    private long mNativeWebContentsObserverAndroid;

    private native void nativeDestroy(long j);

    private native long nativeInit(WebContents webContents);

    public WebContentsObserver(WebContents webContents) {
        ThreadUtils.assertOnUiThread();
        this.mNativeWebContentsObserverAndroid = nativeInit(webContents);
    }

    @CalledByNative
    public void renderProcessGone(boolean wasOomProtected) {
    }

    @CalledByNative
    public void didStartLoading(String url) {
    }

    @CalledByNative
    public void didStopLoading(String url) {
    }

    @CalledByNative
    public void didFailLoad(boolean isProvisionalLoad, boolean isMainFrame, int errorCode, String description, String failingUrl) {
    }

    public void didNavigateMainFrame(String url, String baseUrl, boolean isNavigationToDifferentPage, boolean isFragmentNavigation) {
    }

    @CalledByNative
    public void didNavigateMainFrame(String url, String baseUrl, boolean isNavigationToDifferentPage, boolean isFragmentNavigation, int statusCode) {
        didNavigateMainFrame(url, baseUrl, isNavigationToDifferentPage, isFragmentNavigation);
    }

    @CalledByNative
    public void didFirstVisuallyNonEmptyPaint() {
    }

    @CalledByNative
    public void didNavigateAnyFrame(String url, String baseUrl, boolean isReload) {
    }

    @CalledByNative
    public void didStartProvisionalLoadForFrame(long frameId, long parentFrameId, boolean isMainFrame, String validatedUrl, boolean isErrorPage, boolean isIframeSrcdoc) {
    }

    @CalledByNative
    public void didCommitProvisionalLoadForFrame(long frameId, boolean isMainFrame, String url, int transitionType) {
    }

    @CalledByNative
    public void didFinishLoad(long frameId, String validatedUrl, boolean isMainFrame) {
    }

    @CalledByNative
    public void documentLoadedInFrame(long frameId) {
    }

    @CalledByNative
    public void navigationEntryCommitted() {
    }

    @CalledByNative
    public void didAttachInterstitialPage() {
    }

    @CalledByNative
    public void didDetachInterstitialPage() {
    }

    @CalledByNative
    public void didChangeThemeColor(int color) {
    }

    @CalledByNative
    public void detachFromWebContents() {
        if (this.mNativeWebContentsObserverAndroid != 0) {
            nativeDestroy(this.mNativeWebContentsObserverAndroid);
            this.mNativeWebContentsObserverAndroid = 0;
        }
    }
}
