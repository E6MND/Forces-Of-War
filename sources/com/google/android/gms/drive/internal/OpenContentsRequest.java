package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.DriveId;

public class OpenContentsRequest implements SafeParcelable {
    public static final Parcelable.Creator<OpenContentsRequest> CREATOR = new ar();
    final int Hv;
    final DriveId Ir;
    final int xJ;

    OpenContentsRequest(int versionCode, DriveId id, int mode) {
        this.xJ = versionCode;
        this.Ir = id;
        this.Hv = mode;
    }

    public OpenContentsRequest(DriveId id, int mode) {
        this(1, id, mode);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        ar.a(this, dest, flags);
    }
}
