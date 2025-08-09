package com.google.android.gms.ads;

import android.content.Context;
import com.google.android.gms.internal.am;
import com.google.android.gms.internal.eu;

public final class AdSize {
    public static final int AUTO_HEIGHT = -2;
    public static final AdSize BANNER = new AdSize(320, 50, "320x50_mb");
    public static final AdSize FULL_BANNER = new AdSize(468, 60, "468x60_as");
    public static final int FULL_WIDTH = -1;
    public static final AdSize LARGE_BANNER = new AdSize(320, 100, "320x100_as");
    public static final AdSize LEADERBOARD = new AdSize(728, 90, "728x90_as");
    public static final AdSize MEDIUM_RECTANGLE = new AdSize(300, 250, "300x250_as");
    public static final AdSize SMART_BANNER = new AdSize(-1, -2, "smart_banner");
    public static final AdSize WIDE_SKYSCRAPER = new AdSize(160, 600, "160x600_as");
    private final int ks;
    private final int kt;
    private final String ku;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public AdSize(int width, int height) {
        this(width, height, (width == -1 ? "FULL" : String.valueOf(width)) + "x" + (height == -2 ? "AUTO" : String.valueOf(height)) + "_as");
    }

    AdSize(int width, int height, String formatString) {
        if (width < 0 && width != -1) {
            throw new IllegalArgumentException("Invalid width for AdSize: " + width);
        } else if (height >= 0 || height == -2) {
            this.ks = width;
            this.kt = height;
            this.ku = formatString;
        } else {
            throw new IllegalArgumentException("Invalid height for AdSize: " + height);
        }
    }

    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof AdSize)) {
            return false;
        }
        AdSize adSize = (AdSize) other;
        return this.ks == adSize.ks && this.kt == adSize.kt && this.ku.equals(adSize.ku);
    }

    public int getHeight() {
        return this.kt;
    }

    public int getHeightInPixels(Context context) {
        return this.kt == -2 ? am.b(context.getResources().getDisplayMetrics()) : eu.a(context, this.kt);
    }

    public int getWidth() {
        return this.ks;
    }

    public int getWidthInPixels(Context context) {
        return this.ks == -1 ? am.a(context.getResources().getDisplayMetrics()) : eu.a(context, this.ks);
    }

    public int hashCode() {
        return this.ku.hashCode();
    }

    public boolean isAutoHeight() {
        return this.kt == -2;
    }

    public boolean isFullWidth() {
        return this.ks == -1;
    }

    public String toString() {
        return this.ku;
    }
}
