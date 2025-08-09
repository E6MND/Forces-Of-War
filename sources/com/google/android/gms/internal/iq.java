package com.google.android.gms.internal;

import android.os.Build;

public final class iq {
    private static boolean aF(int i) {
        return Build.VERSION.SDK_INT >= i;
    }

    public static boolean fX() {
        return aF(11);
    }

    public static boolean fY() {
        return aF(12);
    }

    public static boolean fZ() {
        return aF(13);
    }

    public static boolean ga() {
        return aF(14);
    }

    public static boolean gb() {
        return aF(16);
    }

    public static boolean gc() {
        return aF(17);
    }

    public static boolean gd() {
        return aF(19);
    }
}
