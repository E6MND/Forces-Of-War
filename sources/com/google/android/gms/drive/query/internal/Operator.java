package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class Operator implements SafeParcelable {
    public static final Parcelable.Creator<Operator> CREATOR = new k();
    public static final Operator KX = new Operator("=");
    public static final Operator KY = new Operator("<");
    public static final Operator KZ = new Operator("<=");
    public static final Operator La = new Operator(">");
    public static final Operator Lb = new Operator(">=");
    public static final Operator Lc = new Operator("and");
    public static final Operator Ld = new Operator("or");
    public static final Operator Le = new Operator("not");
    public static final Operator Lf = new Operator("contains");
    final String mTag;
    final int xJ;

    Operator(int versionCode, String tag) {
        this.xJ = versionCode;
        this.mTag = tag;
    }

    private Operator(String tag) {
        this(1, tag);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Operator operator = (Operator) obj;
        return this.mTag == null ? operator.mTag == null : this.mTag.equals(operator.mTag);
    }

    public int hashCode() {
        return (this.mTag == null ? 0 : this.mTag.hashCode()) + 31;
    }

    public void writeToParcel(Parcel out, int flags) {
        k.a(this, out, flags);
    }
}
