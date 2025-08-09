package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class OnListParentsResponse implements SafeParcelable {
    public static final Parcelable.Creator<OnListParentsResponse> CREATOR = new al();
    final DataHolder Jy;
    final int xJ;

    OnListParentsResponse(int versionCode, DataHolder parents) {
        this.xJ = versionCode;
        this.Jy = parents;
    }

    public int describeContents() {
        return 0;
    }

    public DataHolder gv() {
        return this.Jy;
    }

    public void writeToParcel(Parcel dest, int flags) {
        al.a(this, dest, flags);
    }
}
