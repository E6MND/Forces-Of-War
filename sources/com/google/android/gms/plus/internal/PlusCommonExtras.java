package com.google.android.gms.plus.internal;

import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.c;
import com.google.android.gms.internal.hl;

public class PlusCommonExtras implements SafeParcelable {
    public static final f CREATOR = new f();
    public static String TAG = "PlusCommonExtras";
    private String abN;
    private String abO;
    private final int xJ;

    public PlusCommonExtras() {
        this.xJ = 1;
        this.abN = "";
        this.abO = "";
    }

    PlusCommonExtras(int versionCode, String gpsrc, String clientCallingPackage) {
        this.xJ = versionCode;
        this.abN = gpsrc;
        this.abO = clientCallingPackage;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof PlusCommonExtras)) {
            return false;
        }
        PlusCommonExtras plusCommonExtras = (PlusCommonExtras) obj;
        return this.xJ == plusCommonExtras.xJ && hl.equal(this.abN, plusCommonExtras.abN) && hl.equal(this.abO, plusCommonExtras.abO);
    }

    public int getVersionCode() {
        return this.xJ;
    }

    public int hashCode() {
        return hl.hashCode(Integer.valueOf(this.xJ), this.abN, this.abO);
    }

    public String jS() {
        return this.abN;
    }

    public String jT() {
        return this.abO;
    }

    public void n(Bundle bundle) {
        bundle.putByteArray("android.gms.plus.internal.PlusCommonExtras.extraPlusCommon", c.a(this));
    }

    public String toString() {
        return hl.e(this).a("versionCode", Integer.valueOf(this.xJ)).a("Gpsrc", this.abN).a("ClientCallingPackage", this.abO).toString();
    }

    public void writeToParcel(Parcel out, int flags) {
        f.a(this, out, flags);
    }
}
