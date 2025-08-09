package org.chromium.content_public.browser;

import org.chromium.base.VisibleForTesting;

public interface NavigationController {
    boolean canGoBack();

    boolean canGoForward();

    boolean canGoToOffset(int i);

    void cancelPendingReload();

    @VisibleForTesting
    void clearHistory();

    void clearSslPreferences();

    void continuePendingReload();

    NavigationHistory getDirectedNavigationHistory(boolean z, int i);

    int getLastCommittedEntryIndex();

    NavigationHistory getNavigationHistory();

    String getOriginalUrlForVisibleNavigationEntry();

    NavigationEntry getPendingEntry();

    boolean getUseDesktopUserAgent();

    void goBack();

    void goForward();

    void goToNavigationIndex(int i);

    void goToOffset(int i);

    void loadIfNecessary();

    void loadUrl(LoadUrlParams loadUrlParams);

    void reload(boolean z);

    void reloadIgnoringCache(boolean z);

    boolean removeEntryAtIndex(int i);

    void requestRestoreLoad();

    void setUseDesktopUserAgent(boolean z, boolean z2);
}
