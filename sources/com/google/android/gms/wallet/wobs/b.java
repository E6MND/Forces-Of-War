package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class b implements SafeParcelable {
    public static final Parcelable.Creator<b> CREATOR = new c();
    String label;
    String value;
    private final int xJ;

    b() {
        this.xJ = 1;
    }

    b(int i, String str, String str2) {
        this.xJ = i;
        this.label = str;
        this.value = str2;
    }

    public int describeContents() {
        return 0;
    }

    public int getVersionCode() {
        return this.xJ;
    }

    public void writeToParcel(Parcel dest, int flags) {
        c.a(this, dest, flags);
    }
}
