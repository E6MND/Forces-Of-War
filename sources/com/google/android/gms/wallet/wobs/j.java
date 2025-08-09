package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class j implements SafeParcelable {
    public static final Parcelable.Creator<j> CREATOR = new k();
    String akW;
    String qb;
    private final int xJ;

    j() {
        this.xJ = 1;
    }

    j(int i, String str, String str2) {
        this.xJ = i;
        this.akW = str;
        this.qb = str2;
    }

    public int describeContents() {
        return 0;
    }

    public int getVersionCode() {
        return this.xJ;
    }

    public void writeToParcel(Parcel dest, int flags) {
        k.a(this, dest, flags);
    }
}
