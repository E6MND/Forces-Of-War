package org.chromium.content.browser.webcontents;

import org.chromium.base.CalledByNative;
import org.chromium.base.JNINamespace;
import org.chromium.content_public.browser.JavaScriptCallback;
import org.chromium.content_public.browser.NavigationController;
import org.chromium.content_public.browser.NavigationTransitionDelegate;
import org.chromium.content_public.browser.WebContents;

@JNINamespace("content")
class WebContentsImpl implements WebContents {
    private long mNativeWebContentsAndroid;
    private NavigationController mNavigationController;
    private NavigationTransitionDelegate mNavigationTransitionDelegate = null;

    private native void nativeAddStyleSheetByURL(long j, String str);

    private native void nativeBeginExitTransition(long j, String str);

    private native void nativeClearNavigationTransitionData(long j);

    private native void nativeEvaluateJavaScript(long j, String str, JavaScriptCallback javaScriptCallback);

    private native void nativeExitFullscreen(long j);

    private native int nativeGetBackgroundColor(long j);

    private native String nativeGetTitle(long j);

    private native String nativeGetURL(long j);

    private native String nativeGetVisibleURL(long j);

    private native void nativeInsertCSS(long j, String str);

    private native boolean nativeIsIncognito(long j);

    private native boolean nativeIsLoading(long j);

    private native boolean nativeIsLoadingToDifferentDocument(long j);

    private native boolean nativeIsRenderWidgetHostViewReady(long j);

    private native boolean nativeIsShowingInterstitialPage(long j);

    private native void nativeOnHide(long j);

    private native void nativeOnShow(long j);

    private native void nativePostMessageToFrame(long j, String str, String str2, String str3, String str4);

    private native void nativeReleaseMediaPlayers(long j);

    private native void nativeResumeResponseDeferredAtStart(long j);

    private native void nativeScrollFocusedEditableNodeIntoView(long j);

    private native void nativeSelectWordAroundCaret(long j);

    private native void nativeSetHasPendingNavigationTransitionForTesting(long j);

    private native void nativeSetupTransitionView(long j, String str);

    private native void nativeShowImeIfNeeded(long j);

    private native void nativeShowInterstitialPage(long j, String str, long j2);

    private native void nativeStop(long j);

    private native void nativeUpdateTopControlsState(long j, boolean z, boolean z2, boolean z3);

    private WebContentsImpl(long nativeWebContentsAndroid, NavigationController navigationController) {
        this.mNativeWebContentsAndroid = nativeWebContentsAndroid;
        this.mNavigationController = navigationController;
    }

    @CalledByNative
    private static WebContentsImpl create(long nativeWebContentsAndroid, NavigationController navigationController) {
        return new WebContentsImpl(nativeWebContentsAndroid, navigationController);
    }

    @CalledByNative
    private void destroy() {
        this.mNativeWebContentsAndroid = 0;
        this.mNavigationController = null;
    }

    @CalledByNative
    private long getNativePointer() {
        return this.mNativeWebContentsAndroid;
    }

    public NavigationController getNavigationController() {
        return this.mNavigationController;
    }

    public String getTitle() {
        return nativeGetTitle(this.mNativeWebContentsAndroid);
    }

    public String getVisibleUrl() {
        return nativeGetVisibleURL(this.mNativeWebContentsAndroid);
    }

    public boolean isLoading() {
        return nativeIsLoading(this.mNativeWebContentsAndroid);
    }

    public boolean isLoadingToDifferentDocument() {
        return nativeIsLoadingToDifferentDocument(this.mNativeWebContentsAndroid);
    }

    public void stop() {
        nativeStop(this.mNativeWebContentsAndroid);
    }

    public void insertCSS(String css) {
        if (this.mNativeWebContentsAndroid != 0) {
            nativeInsertCSS(this.mNativeWebContentsAndroid, css);
        }
    }

    public void onHide() {
        nativeOnHide(this.mNativeWebContentsAndroid);
    }

    public void onShow() {
        nativeOnShow(this.mNativeWebContentsAndroid);
    }

    public void releaseMediaPlayers() {
        nativeReleaseMediaPlayers(this.mNativeWebContentsAndroid);
    }

    public int getBackgroundColor() {
        return nativeGetBackgroundColor(this.mNativeWebContentsAndroid);
    }

    public void addStyleSheetByURL(String url) {
        nativeAddStyleSheetByURL(this.mNativeWebContentsAndroid, url);
    }

    public void showInterstitialPage(String url, long interstitialPageDelegateAndroid) {
        nativeShowInterstitialPage(this.mNativeWebContentsAndroid, url, interstitialPageDelegateAndroid);
    }

    public boolean isShowingInterstitialPage() {
        return nativeIsShowingInterstitialPage(this.mNativeWebContentsAndroid);
    }

    public boolean isReady() {
        return nativeIsRenderWidgetHostViewReady(this.mNativeWebContentsAndroid);
    }

    public void exitFullscreen() {
        nativeExitFullscreen(this.mNativeWebContentsAndroid);
    }

    public void updateTopControlsState(boolean enableHiding, boolean enableShowing, boolean animate) {
        nativeUpdateTopControlsState(this.mNativeWebContentsAndroid, enableHiding, enableShowing, animate);
    }

    public void showImeIfNeeded() {
        nativeShowImeIfNeeded(this.mNativeWebContentsAndroid);
    }

    public void scrollFocusedEditableNodeIntoView() {
        nativeScrollFocusedEditableNodeIntoView(this.mNativeWebContentsAndroid);
    }

    public void selectWordAroundCaret() {
        nativeSelectWordAroundCaret(this.mNativeWebContentsAndroid);
    }

    public String getUrl() {
        return nativeGetURL(this.mNativeWebContentsAndroid);
    }

    public boolean isIncognito() {
        return nativeIsIncognito(this.mNativeWebContentsAndroid);
    }

    public void resumeResponseDeferredAtStart() {
        nativeResumeResponseDeferredAtStart(this.mNativeWebContentsAndroid);
    }

    public void setHasPendingNavigationTransitionForTesting() {
        nativeSetHasPendingNavigationTransitionForTesting(this.mNativeWebContentsAndroid);
    }

    public void setNavigationTransitionDelegate(NavigationTransitionDelegate delegate) {
        this.mNavigationTransitionDelegate = delegate;
    }

    public void setupTransitionView(String markup) {
        nativeSetupTransitionView(this.mNativeWebContentsAndroid, markup);
    }

    public void beginExitTransition(String cssSelector) {
        nativeBeginExitTransition(this.mNativeWebContentsAndroid, cssSelector);
    }

    public void clearNavigationTransitionData() {
        nativeClearNavigationTransitionData(this.mNativeWebContentsAndroid);
    }

    @CalledByNative
    private void didDeferAfterResponseStarted(String markup, String cssSelector, String enteringColor) {
        if (this.mNavigationTransitionDelegate != null) {
            this.mNavigationTransitionDelegate.didDeferAfterResponseStarted(markup, cssSelector, enteringColor);
        }
    }

    @CalledByNative
    private boolean willHandleDeferAfterResponseStarted() {
        if (this.mNavigationTransitionDelegate == null) {
            return false;
        }
        return this.mNavigationTransitionDelegate.willHandleDeferAfterResponseStarted();
    }

    @CalledByNative
    private void addEnteringStylesheetToTransition(String stylesheet) {
        if (this.mNavigationTransitionDelegate != null) {
            this.mNavigationTransitionDelegate.addEnteringStylesheetToTransition(stylesheet);
        }
    }

    @CalledByNative
    private void didStartNavigationTransitionForFrame(long frameId) {
        if (this.mNavigationTransitionDelegate != null) {
            this.mNavigationTransitionDelegate.didStartNavigationTransitionForFrame(frameId);
        }
    }

    public void evaluateJavaScript(String script, JavaScriptCallback callback) {
        nativeEvaluateJavaScript(this.mNativeWebContentsAndroid, script, callback);
    }

    @CalledByNative
    private static void onEvaluateJavaScriptResult(String jsonResult, JavaScriptCallback callback) {
        callback.handleJavaScriptResult(jsonResult);
    }

    public void postMessageToFrame(String frameName, String message, String sourceOrigin, String targetOrigin) {
        nativePostMessageToFrame(this.mNativeWebContentsAndroid, frameName, message, sourceOrigin, targetOrigin);
    }
}
