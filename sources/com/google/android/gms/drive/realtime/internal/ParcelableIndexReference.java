package com.google.android.gms.drive.realtime.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class ParcelableIndexReference implements SafeParcelable {
    public static final Parcelable.Creator<ParcelableIndexReference> CREATOR = new q();
    final String Ln;
    final boolean Lo;
    final int mIndex;
    final int xJ;

    ParcelableIndexReference(int versionCode, String objectId, int index, boolean canBeDeleted) {
        this.xJ = versionCode;
        this.Ln = objectId;
        this.mIndex = index;
        this.Lo = canBeDeleted;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        q.a(this, dest, flags);
    }
}
