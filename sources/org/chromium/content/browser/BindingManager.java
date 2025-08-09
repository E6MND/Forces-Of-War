package org.chromium.content.browser;

public interface BindingManager {
    void addNewConnection(int i, ChildProcessConnection childProcessConnection);

    void clearConnection(int i);

    void determinedVisibility(int i);

    boolean isOomProtected(int i);

    void onBroughtToForeground();

    void onSentToBackground();

    void setInForeground(int i, boolean z);
}
