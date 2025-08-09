package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class lj implements SafeParcelable {
    public static final Parcelable.Creator<lj> CREATOR = new lk();
    String[] akb;
    byte[][] akc;
    private final int xJ;

    lj() {
        this(1, new String[0], new byte[0][]);
    }

    lj(int i, String[] strArr, byte[][] bArr) {
        this.xJ = i;
        this.akb = strArr;
        this.akc = bArr;
    }

    public int describeContents() {
        return 0;
    }

    public int getVersionCode() {
        return this.xJ;
    }

    public void writeToParcel(Parcel out, int flags) {
        lk.a(this, out, flags);
    }
}
