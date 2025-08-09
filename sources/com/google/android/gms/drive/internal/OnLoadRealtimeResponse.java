package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class OnLoadRealtimeResponse implements SafeParcelable {
    public static final Parcelable.Creator<OnLoadRealtimeResponse> CREATOR = new an();
    final boolean Jz;
    final int xJ;

    OnLoadRealtimeResponse(int versionCode, boolean isInitialized) {
        this.xJ = versionCode;
        this.Jz = isInitialized;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        an.a(this, dest, flags);
    }
}
