package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class fl implements SafeParcelable {
    public static final fm CREATOR = new fm();
    public static final int xQ = Integer.parseInt("-1");
    final int xJ;
    public final String xR;
    final fq xS;
    public final int xT;
    public final byte[] xU;

    fl(int i, String str, fq fqVar, int i2, byte[] bArr) {
        hn.b(i2 == xQ || fp.H(i2) != null, (Object) "Invalid section type " + i2);
        this.xJ = i;
        this.xR = str;
        this.xS = fqVar;
        this.xT = i2;
        this.xU = bArr;
        String dJ = dJ();
        if (dJ != null) {
            throw new IllegalArgumentException(dJ);
        }
    }

    public fl(String str, fq fqVar) {
        this(1, str, fqVar, xQ, (byte[]) null);
    }

    public fl(String str, fq fqVar, String str2) {
        this(1, str, fqVar, fp.Y(str2), (byte[]) null);
    }

    public fl(byte[] bArr, fq fqVar) {
        this(1, (String) null, fqVar, xQ, bArr);
    }

    public String dJ() {
        if (this.xT != xQ && fp.H(this.xT) == null) {
            return "Invalid section type " + this.xT;
        }
        if (this.xR == null || this.xU == null) {
            return null;
        }
        return "Both content and blobContent set";
    }

    public int describeContents() {
        fm fmVar = CREATOR;
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        fm fmVar = CREATOR;
        fm.a(this, dest, flags);
    }
}
