package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class g implements SafeParcelable {
    public static final Parcelable.Creator<g> CREATOR = new h();
    int akQ;
    String akR;
    double akS;
    String akT;
    long akU;
    int akV;
    private final int xJ;

    g() {
        this.xJ = 1;
        this.akV = -1;
        this.akQ = -1;
        this.akS = -1.0d;
    }

    g(int i, int i2, String str, double d, String str2, long j, int i3) {
        this.xJ = i;
        this.akQ = i2;
        this.akR = str;
        this.akS = d;
        this.akT = str2;
        this.akU = j;
        this.akV = i3;
    }

    public int describeContents() {
        return 0;
    }

    public int getVersionCode() {
        return this.xJ;
    }

    public void writeToParcel(Parcel dest, int flags) {
        h.a(this, dest, flags);
    }
}
