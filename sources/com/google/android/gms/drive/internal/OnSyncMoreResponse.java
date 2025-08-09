package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class OnSyncMoreResponse implements SafeParcelable {
    public static final Parcelable.Creator<OnSyncMoreResponse> CREATOR = new aq();
    final boolean IM;
    final int xJ;

    OnSyncMoreResponse(int versionCode, boolean moreEntriesMayExist) {
        this.xJ = versionCode;
        this.IM = moreEntriesMayExist;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        aq.a(this, dest, flags);
    }
}
