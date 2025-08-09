package com.google.android.gms.internal;

import android.content.Context;
import android.os.Parcel;
import android.util.DisplayMetrics;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.a;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class am implements SafeParcelable {
    public static final an CREATOR = new an();
    public final int height;
    public final int heightPixels;
    public final String mc;
    public final boolean md;
    public final am[] me;
    public final int versionCode;
    public final int width;
    public final int widthPixels;

    public am() {
        this(2, "interstitial_mb", 0, 0, true, 0, 0, (am[]) null);
    }

    am(int i, String str, int i2, int i3, boolean z, int i4, int i5, am[] amVarArr) {
        this.versionCode = i;
        this.mc = str;
        this.height = i2;
        this.heightPixels = i3;
        this.md = z;
        this.width = i4;
        this.widthPixels = i5;
        this.me = amVarArr;
    }

    public am(Context context, AdSize adSize) {
        this(context, new AdSize[]{adSize});
    }

    public am(Context context, AdSize[] adSizeArr) {
        int i;
        AdSize adSize = adSizeArr[0];
        this.versionCode = 2;
        this.md = false;
        this.width = adSize.getWidth();
        this.height = adSize.getHeight();
        boolean z = this.width == -1;
        boolean z2 = this.height == -2;
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        if (z) {
            this.widthPixels = a(displayMetrics);
            i = (int) (((float) this.widthPixels) / displayMetrics.density);
        } else {
            int i2 = this.width;
            this.widthPixels = eu.a(displayMetrics, this.width);
            i = i2;
        }
        int c = z2 ? c(displayMetrics) : this.height;
        this.heightPixels = eu.a(displayMetrics, c);
        if (z || z2) {
            this.mc = i + "x" + c + "_as";
        } else {
            this.mc = adSize.toString();
        }
        if (adSizeArr.length > 1) {
            this.me = new am[adSizeArr.length];
            for (int i3 = 0; i3 < adSizeArr.length; i3++) {
                this.me[i3] = new am(context, adSizeArr[i3]);
            }
            return;
        }
        this.me = null;
    }

    public am(am amVar, am[] amVarArr) {
        this(2, amVar.mc, amVar.height, amVar.heightPixels, amVar.md, amVar.width, amVar.widthPixels, amVarArr);
    }

    public static int a(DisplayMetrics displayMetrics) {
        return displayMetrics.widthPixels;
    }

    public static int b(DisplayMetrics displayMetrics) {
        return (int) (((float) c(displayMetrics)) * displayMetrics.density);
    }

    private static int c(DisplayMetrics displayMetrics) {
        int i = (int) (((float) displayMetrics.heightPixels) / displayMetrics.density);
        if (i <= 400) {
            return 32;
        }
        return i <= 720 ? 50 : 90;
    }

    public AdSize aB() {
        return a.a(this.width, this.height, this.mc);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        an.a(this, out, flags);
    }
}
