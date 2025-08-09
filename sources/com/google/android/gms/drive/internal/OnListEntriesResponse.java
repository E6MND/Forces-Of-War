package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class OnListEntriesResponse implements SafeParcelable {
    public static final Parcelable.Creator<OnListEntriesResponse> CREATOR = new ak();
    final boolean IM;
    final DataHolder Jx;
    final int xJ;

    OnListEntriesResponse(int versionCode, DataHolder entries, boolean moreEntriesMayExist) {
        this.xJ = versionCode;
        this.Jx = entries;
        this.IM = moreEntriesMayExist;
    }

    public int describeContents() {
        return 0;
    }

    public DataHolder gt() {
        return this.Jx;
    }

    public boolean gu() {
        return this.IM;
    }

    public void writeToParcel(Parcel dest, int flags) {
        ak.a(this, dest, flags);
    }
}
