package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.DriveId;

public class OnDriveIdResponse implements SafeParcelable {
    public static final Parcelable.Creator<OnDriveIdResponse> CREATOR = new ai();
    DriveId Ir;
    final int xJ;

    OnDriveIdResponse(int versionCode, DriveId driveId) {
        this.xJ = versionCode;
        this.Ir = driveId;
    }

    public int describeContents() {
        return 0;
    }

    public DriveId getDriveId() {
        return this.Ir;
    }

    public void writeToParcel(Parcel dest, int flags) {
        ai.a(this, dest, flags);
    }
}
