package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.DriveId;

public class RemoveEventListenerRequest implements SafeParcelable {
    public static final Parcelable.Creator<RemoveEventListenerRequest> CREATOR = new au();
    final DriveId Hw;
    final int In;
    final int xJ;

    RemoveEventListenerRequest(int versionCode, DriveId driveId, int eventType) {
        this.xJ = versionCode;
        this.Hw = driveId;
        this.In = eventType;
    }

    public RemoveEventListenerRequest(DriveId id, int eventType) {
        this(1, id, eventType);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        au.a(this, dest, flags);
    }
}
