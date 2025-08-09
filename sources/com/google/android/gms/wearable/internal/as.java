package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.List;

public class as implements SafeParcelable {
    public static final Parcelable.Creator<as> CREATOR = new at();
    public final long alY;
    public final List<ak> ama;
    public final int statusCode;
    public final int versionCode;

    as(int i, int i2, long j, List<ak> list) {
        this.versionCode = i;
        this.statusCode = i2;
        this.alY = j;
        this.ama = list;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        at.a(this, out, flags);
    }
}
