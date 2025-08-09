package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class f implements SafeParcelable {
    public static final Parcelable.Creator<f> CREATOR = new i();
    l ajp;
    g akP;
    String label;
    String type;
    private final int xJ;

    f() {
        this.xJ = 1;
    }

    f(int i, String str, g gVar, String str2, l lVar) {
        this.xJ = i;
        this.label = str;
        this.akP = gVar;
        this.type = str2;
        this.ajp = lVar;
    }

    public int describeContents() {
        return 0;
    }

    public int getVersionCode() {
        return this.xJ;
    }

    public void writeToParcel(Parcel dest, int flags) {
        i.a(this, dest, flags);
    }
}
