package org.chromium.content.browser.accessibility;

import android.os.Bundle;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import java.util.List;
import org.chromium.base.JNINamespace;
import org.chromium.content.browser.ContentViewCore;

@JNINamespace("content")
public class JellyBeanBrowserAccessibilityManager extends BrowserAccessibilityManager {
    private AccessibilityNodeProvider mAccessibilityNodeProvider = new AccessibilityNodeProvider() {
        public AccessibilityNodeInfo createAccessibilityNodeInfo(int virtualViewId) {
            return this.createAccessibilityNodeInfo(virtualViewId);
        }

        public List<AccessibilityNodeInfo> findAccessibilityNodeInfosByText(String text, int virtualViewId) {
            return this.findAccessibilityNodeInfosByText(text, virtualViewId);
        }

        public boolean performAction(int virtualViewId, int action, Bundle arguments) {
            return this.performAction(virtualViewId, action, arguments);
        }
    };

    JellyBeanBrowserAccessibilityManager(long nativeBrowserAccessibilityManagerAndroid, ContentViewCore contentViewCore) {
        super(nativeBrowserAccessibilityManagerAndroid, contentViewCore);
    }

    public AccessibilityNodeProvider getAccessibilityNodeProvider() {
        return this.mAccessibilityNodeProvider;
    }
}
