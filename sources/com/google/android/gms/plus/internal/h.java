package com.google.android.gms.plus.internal;

import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.hl;
import java.util.Arrays;

public class h implements SafeParcelable {
    public static final j CREATOR = new j();
    private final String[] abQ;
    private final String[] abR;
    private final String[] abS;
    private final String abT;
    private final String abU;
    private final String abV;
    private final String abW;
    private final PlusCommonExtras abX;
    private final int xJ;
    private final String yN;

    h(int i, String str, String[] strArr, String[] strArr2, String[] strArr3, String str2, String str3, String str4, String str5, PlusCommonExtras plusCommonExtras) {
        this.xJ = i;
        this.yN = str;
        this.abQ = strArr;
        this.abR = strArr2;
        this.abS = strArr3;
        this.abT = str2;
        this.abU = str3;
        this.abV = str4;
        this.abW = str5;
        this.abX = plusCommonExtras;
    }

    public h(String str, String[] strArr, String[] strArr2, String[] strArr3, String str2, String str3, String str4, PlusCommonExtras plusCommonExtras) {
        this.xJ = 1;
        this.yN = str;
        this.abQ = strArr;
        this.abR = strArr2;
        this.abS = strArr3;
        this.abT = str2;
        this.abU = str3;
        this.abV = str4;
        this.abW = null;
        this.abX = plusCommonExtras;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof h)) {
            return false;
        }
        h hVar = (h) obj;
        return this.xJ == hVar.xJ && hl.equal(this.yN, hVar.yN) && Arrays.equals(this.abQ, hVar.abQ) && Arrays.equals(this.abR, hVar.abR) && Arrays.equals(this.abS, hVar.abS) && hl.equal(this.abT, hVar.abT) && hl.equal(this.abU, hVar.abU) && hl.equal(this.abV, hVar.abV) && hl.equal(this.abW, hVar.abW) && hl.equal(this.abX, hVar.abX);
    }

    public String getAccountName() {
        return this.yN;
    }

    public int getVersionCode() {
        return this.xJ;
    }

    public int hashCode() {
        return hl.hashCode(Integer.valueOf(this.xJ), this.yN, this.abQ, this.abR, this.abS, this.abT, this.abU, this.abV, this.abW, this.abX);
    }

    public String[] jU() {
        return this.abQ;
    }

    public String[] jV() {
        return this.abR;
    }

    public String[] jW() {
        return this.abS;
    }

    public String jX() {
        return this.abT;
    }

    public String jY() {
        return this.abU;
    }

    public String jZ() {
        return this.abV;
    }

    public String ka() {
        return this.abW;
    }

    public PlusCommonExtras kb() {
        return this.abX;
    }

    public Bundle kc() {
        Bundle bundle = new Bundle();
        bundle.setClassLoader(PlusCommonExtras.class.getClassLoader());
        this.abX.n(bundle);
        return bundle;
    }

    public String toString() {
        return hl.e(this).a("versionCode", Integer.valueOf(this.xJ)).a("accountName", this.yN).a("requestedScopes", this.abQ).a("visibleActivities", this.abR).a("requiredFeatures", this.abS).a("packageNameForAuth", this.abT).a("callingPackageName", this.abU).a("applicationName", this.abV).a("extra", this.abX.toString()).toString();
    }

    public void writeToParcel(Parcel out, int flags) {
        j.a(this, out, flags);
    }
}
