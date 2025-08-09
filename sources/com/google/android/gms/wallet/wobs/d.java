package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.ih;
import java.util.ArrayList;

public final class d implements SafeParcelable {
    public static final Parcelable.Creator<d> CREATOR = new e();
    String akM;
    String akN;
    ArrayList<b> akO;
    private final int xJ;

    d() {
        this.xJ = 1;
        this.akO = ih.fV();
    }

    d(int i, String str, String str2, ArrayList<b> arrayList) {
        this.xJ = i;
        this.akM = str;
        this.akN = str2;
        this.akO = arrayList;
    }

    public int describeContents() {
        return 0;
    }

    public int getVersionCode() {
        return this.xJ;
    }

    public void writeToParcel(Parcel dest, int flags) {
        e.a(this, dest, flags);
    }
}
