package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.DriveId;

public class LoadRealtimeRequest implements SafeParcelable {
    public static final Parcelable.Creator<LoadRealtimeRequest> CREATOR = new ae();
    final DriveId Hw;
    final boolean Jp;
    final int xJ;

    LoadRealtimeRequest(int versionCode, DriveId driveId, boolean useTestMode) {
        this.xJ = versionCode;
        this.Hw = driveId;
        this.Jp = useTestMode;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        ae.a(this, dest, flags);
    }
}
