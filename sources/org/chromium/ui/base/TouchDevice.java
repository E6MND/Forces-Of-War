package org.chromium.ui.base;

import android.content.Context;
import org.chromium.base.CalledByNative;
import org.chromium.base.JNINamespace;

@JNINamespace("ui")
public class TouchDevice {
    private TouchDevice() {
    }

    @CalledByNative
    private static int maxTouchPoints(Context context) {
        if (context.getPackageManager().hasSystemFeature("android.hardware.touchscreen.multitouch.jazzhand")) {
            return 5;
        }
        if (context.getPackageManager().hasSystemFeature("android.hardware.touchscreen.multitouch.distinct") || context.getPackageManager().hasSystemFeature("android.hardware.touchscreen.multitouch")) {
            return 2;
        }
        if (context.getPackageManager().hasSystemFeature("android.hardware.touchscreen")) {
            return 1;
        }
        return 0;
    }
}
