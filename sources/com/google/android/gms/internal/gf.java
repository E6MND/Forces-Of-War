package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class gf implements SafeParcelable {
    public static final Parcelable.Creator<gf> CREATOR = new gg();
    private String Bz;
    private final int xJ;

    public gf() {
        this(1, (String) null);
    }

    gf(int i, String str) {
        this.xJ = i;
        this.Bz = str;
    }

    public String dX() {
        return this.Bz;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof gf)) {
            return false;
        }
        return gj.a(this.Bz, ((gf) obj).Bz);
    }

    public int getVersionCode() {
        return this.xJ;
    }

    public int hashCode() {
        return hl.hashCode(this.Bz);
    }

    public void writeToParcel(Parcel out, int flags) {
        gg.a(this, out, flags);
    }
}
