package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class jl implements SafeParcelable {
    public static final jm CREATOR = new jm();
    private final int UX;
    private final int VX;
    private final jn VY;
    private final int xJ;

    jl(int i, int i2, int i3, jn jnVar) {
        this.xJ = i;
        this.UX = i2;
        this.VX = i3;
        this.VY = jnVar;
    }

    public int describeContents() {
        jm jmVar = CREATOR;
        return 0;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof jl)) {
            return false;
        }
        jl jlVar = (jl) object;
        return this.UX == jlVar.UX && this.VX == jlVar.VX && this.VY.equals(jlVar.VY);
    }

    public int getVersionCode() {
        return this.xJ;
    }

    public int hashCode() {
        return hl.hashCode(Integer.valueOf(this.UX), Integer.valueOf(this.VX));
    }

    public int iX() {
        return this.UX;
    }

    public int iZ() {
        return this.VX;
    }

    public jn ja() {
        return this.VY;
    }

    public String toString() {
        return hl.e(this).a("transitionTypes", Integer.valueOf(this.UX)).a("loiteringTimeMillis", Integer.valueOf(this.VX)).a("placeFilter", this.VY).toString();
    }

    public void writeToParcel(Parcel parcel, int flags) {
        jm jmVar = CREATOR;
        jm.a(this, parcel, flags);
    }
}
