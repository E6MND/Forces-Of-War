package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class fj implements SafeParcelable {
    public static final fk CREATOR = new fk();
    final int xJ;
    final String xN;
    final String xO;
    final String xP;

    fj(int i, String str, String str2, String str3) {
        this.xJ = i;
        this.xN = str;
        this.xO = str2;
        this.xP = str3;
    }

    public fj(String str, String str2, String str3) {
        this(1, str, str2, str3);
    }

    public int describeContents() {
        fk fkVar = CREATOR;
        return 0;
    }

    public String toString() {
        return String.format("DocumentId[packageName=%s, corpusName=%s, uri=%s]", new Object[]{this.xN, this.xO, this.xP});
    }

    public void writeToParcel(Parcel dest, int flags) {
        fk fkVar = CREATOR;
        fk.a(this, dest, flags);
    }
}
