package com.google.android.gms.internal;

import android.location.Location;
import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.List;

public final class aj implements SafeParcelable {
    public static final ak CREATOR = new ak();
    public final Bundle extras;
    public final long lQ;
    public final int lR;
    public final List<String> lS;
    public final boolean lT;
    public final int lU;
    public final boolean lV;
    public final String lW;
    public final ax lX;
    public final Location lY;
    public final String lZ;
    public final Bundle ma;
    public final int versionCode;

    public aj(int i, long j, Bundle bundle, int i2, List<String> list, boolean z, int i3, boolean z2, String str, ax axVar, Location location, String str2, Bundle bundle2) {
        this.versionCode = i;
        this.lQ = j;
        this.extras = bundle;
        this.lR = i2;
        this.lS = list;
        this.lT = z;
        this.lU = i3;
        this.lV = z2;
        this.lW = str;
        this.lX = axVar;
        this.lY = location;
        this.lZ = str2;
        this.ma = bundle2;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        ak.a(this, out, flags);
    }
}
