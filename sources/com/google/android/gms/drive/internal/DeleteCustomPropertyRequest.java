package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.CustomPropertyKey;

public class DeleteCustomPropertyRequest implements SafeParcelable {
    public static final Parcelable.Creator<DeleteCustomPropertyRequest> CREATOR = new m();
    final DriveId Hw;
    final CustomPropertyKey IG;
    final int xJ;

    DeleteCustomPropertyRequest(int versionCode, DriveId driveId, CustomPropertyKey propertyKey) {
        this.xJ = versionCode;
        this.Hw = driveId;
        this.IG = propertyKey;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        m.a(this, dest, flags);
    }
}
