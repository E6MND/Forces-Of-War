package com.google.android.gms.drive.events;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.DriveId;

public final class ChangeEvent implements SafeParcelable, ResourceEvent {
    public static final Parcelable.Creator<ChangeEvent> CREATOR = new a();
    final DriveId Hw;
    final int Id;
    final int xJ;

    ChangeEvent(int versionCode, DriveId driveId, int changeFlags) {
        this.xJ = versionCode;
        this.Hw = driveId;
        this.Id = changeFlags;
    }

    public int describeContents() {
        return 0;
    }

    public DriveId getDriveId() {
        return this.Hw;
    }

    public int getType() {
        return 1;
    }

    public boolean hasContentChanged() {
        return (this.Id & 2) != 0;
    }

    public boolean hasMetadataChanged() {
        return (this.Id & 1) != 0;
    }

    public String toString() {
        return String.format("ChangeEvent [id=%s,changeFlags=%x]", new Object[]{this.Hw, Integer.valueOf(this.Id)});
    }

    public void writeToParcel(Parcel dest, int flags) {
        a.a(this, dest, flags);
    }
}
