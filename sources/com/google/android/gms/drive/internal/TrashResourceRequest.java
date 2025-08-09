package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.DriveId;

public class TrashResourceRequest implements SafeParcelable {
    public static final Parcelable.Creator<TrashResourceRequest> CREATOR = new ax();
    final DriveId Ir;
    final int xJ;

    TrashResourceRequest(int versionCode, DriveId id) {
        this.xJ = versionCode;
        this.Ir = id;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        ax.a(this, dest, flags);
    }
}
