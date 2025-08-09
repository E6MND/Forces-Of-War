package org.chromium.components.navigation_interception;

import org.chromium.base.CalledByNative;

public class NavigationParams {
    public final boolean hasUserGesture;
    public final boolean isPost;
    public final boolean isRedirect;
    public final int pageTransitionType;
    public final String url;

    public NavigationParams(String url2, boolean isPost2, boolean hasUserGesture2, int pageTransitionType2, boolean isRedirect2) {
        this.url = url2;
        this.isPost = isPost2;
        this.hasUserGesture = hasUserGesture2;
        this.pageTransitionType = pageTransitionType2;
        this.isRedirect = isRedirect2;
    }

    @CalledByNative
    public static NavigationParams create(String url2, boolean isPost2, boolean hasUserGesture2, int pageTransitionType2, boolean isRedirect2) {
        return new NavigationParams(url2, isPost2, hasUserGesture2, pageTransitionType2, isRedirect2);
    }
}
