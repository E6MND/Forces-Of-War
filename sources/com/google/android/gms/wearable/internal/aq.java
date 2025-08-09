package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class aq implements SafeParcelable {
    public static final Parcelable.Creator<aq> CREATOR = new ar();
    public final int alZ;
    public final int statusCode;
    public final int versionCode;

    aq(int i, int i2, int i3) {
        this.versionCode = i;
        this.statusCode = i2;
        this.alZ = i3;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        ar.a(this, dest, flags);
    }
}
