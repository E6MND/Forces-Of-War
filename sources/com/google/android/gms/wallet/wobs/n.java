package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class n implements SafeParcelable {
    public static final Parcelable.Creator<n> CREATOR = new o();
    String akZ;
    String description;
    private final int xJ;

    n() {
        this.xJ = 1;
    }

    n(int i, String str, String str2) {
        this.xJ = i;
        this.akZ = str;
        this.description = str2;
    }

    public int describeContents() {
        return 0;
    }

    public int getVersionCode() {
        return this.xJ;
    }

    public void writeToParcel(Parcel dest, int flags) {
        o.a(this, dest, flags);
    }
}
