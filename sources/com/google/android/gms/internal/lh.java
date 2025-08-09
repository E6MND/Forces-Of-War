package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class lh implements SafeParcelable {
    public static final Parcelable.Creator<lh> CREATOR = new li();
    int[] aka;
    private final int xJ;

    lh() {
        this(1, (int[]) null);
    }

    lh(int i, int[] iArr) {
        this.xJ = i;
        this.aka = iArr;
    }

    public int describeContents() {
        return 0;
    }

    public int getVersionCode() {
        return this.xJ;
    }

    public void writeToParcel(Parcel out, int flags) {
        li.a(this, out, flags);
    }
}
