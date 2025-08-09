package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class p implements SafeParcelable {
    public static final Parcelable.Creator<p> CREATOR = new q();
    public final int alI;
    public final int statusCode;
    public final int versionCode;

    p(int i, int i2, int i3) {
        this.versionCode = i;
        this.statusCode = i2;
        this.alI = i3;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        q.a(this, dest, flags);
    }
}
