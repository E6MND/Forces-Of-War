package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.wearable.c;

public class r implements SafeParcelable {
    public static final Parcelable.Creator<r> CREATOR = new s();
    public final c alJ;
    public final int statusCode;
    public final int versionCode;

    r(int i, int i2, c cVar) {
        this.versionCode = i;
        this.statusCode = i2;
        this.alJ = cVar;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        s.a(this, dest, flags);
    }
}
