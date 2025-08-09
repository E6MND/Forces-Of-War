package org.chromium.content.browser;

import org.chromium.base.CalledByNative;
import org.chromium.ui.base.ViewAndroid;

class PowerSaveBlocker {
    PowerSaveBlocker() {
    }

    @CalledByNative
    private static void applyBlock(ViewAndroid view) {
        view.incrementKeepScreenOnCount();
    }

    @CalledByNative
    private static void removeBlock(ViewAndroid view) {
        view.decrementKeepScreenOnCount();
    }
}
