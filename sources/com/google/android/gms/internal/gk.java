package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class gk implements SafeParcelable {
    public static final Parcelable.Creator<gk> CREATOR = new gl();
    private double AM;
    private boolean AN;
    private int BO;
    private final int xJ;

    public gk() {
        this(1, Double.NaN, false, -1);
    }

    gk(int i, double d, boolean z, int i2) {
        this.xJ = i;
        this.AM = d;
        this.AN = z;
        this.BO = i2;
    }

    public int describeContents() {
        return 0;
    }

    public double ec() {
        return this.AM;
    }

    public boolean ei() {
        return this.AN;
    }

    public int ej() {
        return this.BO;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof gk)) {
            return false;
        }
        gk gkVar = (gk) obj;
        return this.AM == gkVar.AM && this.AN == gkVar.AN && this.BO == gkVar.BO;
    }

    public int getVersionCode() {
        return this.xJ;
    }

    public int hashCode() {
        return hl.hashCode(Double.valueOf(this.AM), Boolean.valueOf(this.AN), Integer.valueOf(this.BO));
    }

    public void writeToParcel(Parcel out, int flags) {
        gl.a(this, out, flags);
    }
}
