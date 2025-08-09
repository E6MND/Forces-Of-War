package org.chromium.content_public.browser;

public interface WebContents {
    void addStyleSheetByURL(String str);

    void beginExitTransition(String str);

    void clearNavigationTransitionData();

    void evaluateJavaScript(String str, JavaScriptCallback javaScriptCallback);

    void exitFullscreen();

    int getBackgroundColor();

    NavigationController getNavigationController();

    String getTitle();

    String getUrl();

    String getVisibleUrl();

    void insertCSS(String str);

    boolean isIncognito();

    boolean isLoading();

    boolean isLoadingToDifferentDocument();

    boolean isReady();

    boolean isShowingInterstitialPage();

    void onHide();

    void onShow();

    void postMessageToFrame(String str, String str2, String str3, String str4);

    void releaseMediaPlayers();

    void resumeResponseDeferredAtStart();

    void scrollFocusedEditableNodeIntoView();

    void selectWordAroundCaret();

    void setHasPendingNavigationTransitionForTesting();

    void setNavigationTransitionDelegate(NavigationTransitionDelegate navigationTransitionDelegate);

    void setupTransitionView(String str);

    void showImeIfNeeded();

    void showInterstitialPage(String str, long j);

    void stop();

    void updateTopControlsState(boolean z, boolean z2, boolean z3);
}
