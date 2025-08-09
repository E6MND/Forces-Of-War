package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class w implements SafeParcelable {
    public static final x CREATOR = new x();
    public final boolean kZ;
    public final boolean lb;
    public final int versionCode;

    w(int i, boolean z, boolean z2) {
        this.versionCode = i;
        this.kZ = z;
        this.lb = z2;
    }

    public w(boolean z, boolean z2) {
        this.versionCode = 1;
        this.kZ = z;
        this.lb = z2;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        x.a(this, out, flags);
    }
}
