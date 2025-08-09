package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.List;

public class t implements SafeParcelable {
    public static final Parcelable.Creator<t> CREATOR = new u();
    public final List<ai> alK;
    public final int statusCode;
    public final int versionCode;

    t(int i, int i2, List<ai> list) {
        this.versionCode = i;
        this.statusCode = i2;
        this.alK = list;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        u.a(this, dest, flags);
    }
}
