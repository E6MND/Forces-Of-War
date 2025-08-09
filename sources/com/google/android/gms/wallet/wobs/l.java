package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class l implements SafeParcelable {
    public static final Parcelable.Creator<l> CREATOR = new m();
    long akX;
    long akY;
    private final int xJ;

    l() {
        this.xJ = 1;
    }

    l(int i, long j, long j2) {
        this.xJ = i;
        this.akX = j;
        this.akY = j2;
    }

    public int describeContents() {
        return 0;
    }

    public int getVersionCode() {
        return this.xJ;
    }

    public void writeToParcel(Parcel dest, int flags) {
        m.a(this, dest, flags);
    }
}
