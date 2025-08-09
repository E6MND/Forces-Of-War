package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class hs implements SafeParcelable {
    public static final ht CREATOR = new ht();
    public final String GQ;
    public final int GR;
    final int xJ;

    public hs(int i, String str, int i2) {
        this.xJ = i;
        this.GQ = str;
        this.GR = i2;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        ht.a(this, out, flags);
    }
}
