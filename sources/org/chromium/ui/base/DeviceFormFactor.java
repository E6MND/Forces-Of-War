package org.chromium.ui.base;

import android.content.Context;
import org.chromium.base.CalledByNative;

public class DeviceFormFactor {
    private static final int MINIMUM_TABLET_WIDTH_DP = 600;
    private static Boolean sIsTablet = null;

    @CalledByNative
    public static boolean isTablet(Context context) {
        if (sIsTablet == null) {
            sIsTablet = Boolean.valueOf(context.getResources().getConfiguration().smallestScreenWidthDp >= MINIMUM_TABLET_WIDTH_DP);
        }
        return sIsTablet.booleanValue();
    }
}
