package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.StorageStats;

public class OnStorageStatsResponse implements SafeParcelable {
    public static final Parcelable.Creator<OnStorageStatsResponse> CREATOR = new ap();
    StorageStats JA;
    final int xJ;

    OnStorageStatsResponse(int versionCode, StorageStats storageStats) {
        this.xJ = versionCode;
        this.JA = storageStats;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        ap.a(this, dest, flags);
    }
}
