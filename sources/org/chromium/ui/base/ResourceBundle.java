package org.chromium.ui.base;

import android.content.Context;
import java.io.IOException;
import org.chromium.base.CalledByNative;
import org.chromium.base.JNINamespace;

@JNINamespace("ui")
class ResourceBundle {
    ResourceBundle() {
    }

    @CalledByNative
    static boolean assetContainedInApk(Context ctx, String filename) {
        try {
            ctx.getAssets().openFd(filename).close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
