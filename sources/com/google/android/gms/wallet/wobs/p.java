package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class p implements SafeParcelable {
    public static final Parcelable.Creator<p> CREATOR = new q();
    String akW;
    l ala;
    n alb;
    n alc;
    String qb;
    private final int xJ;

    p() {
        this.xJ = 1;
    }

    p(int i, String str, String str2, l lVar, n nVar, n nVar2) {
        this.xJ = i;
        this.akW = str;
        this.qb = str2;
        this.ala = lVar;
        this.alb = nVar;
        this.alc = nVar2;
    }

    public int describeContents() {
        return 0;
    }

    public int getVersionCode() {
        return this.xJ;
    }

    public void writeToParcel(Parcel dest, int flags) {
        q.a(this, dest, flags);
    }
}
