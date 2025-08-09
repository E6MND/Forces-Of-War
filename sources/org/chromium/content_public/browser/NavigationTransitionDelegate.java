package org.chromium.content_public.browser;

public interface NavigationTransitionDelegate {
    void addEnteringStylesheetToTransition(String str);

    void didDeferAfterResponseStarted(String str, String str2, String str3);

    void didStartNavigationTransitionForFrame(long j);

    boolean willHandleDeferAfterResponseStarted();
}
