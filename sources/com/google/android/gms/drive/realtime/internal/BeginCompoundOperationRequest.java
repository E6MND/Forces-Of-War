package com.google.android.gms.drive.realtime.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class BeginCompoundOperationRequest implements SafeParcelable {
    public static final Parcelable.Creator<BeginCompoundOperationRequest> CREATOR = new a();
    final boolean Lg;
    final String mName;
    final int xJ;

    BeginCompoundOperationRequest(int versionCode, boolean isCreation, String name) {
        this.xJ = versionCode;
        this.Lg = isCreation;
        this.mName = name;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        a.a(this, dest, flags);
    }
}
