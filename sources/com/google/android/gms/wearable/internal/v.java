package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class v implements SafeParcelable {
    public static final Parcelable.Creator<v> CREATOR = new w();
    public final m alL;
    public final int statusCode;
    public final int versionCode;

    v(int i, int i2, m mVar) {
        this.versionCode = i;
        this.statusCode = i2;
        this.alL = mVar;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        w.a(this, dest, flags);
    }
}
