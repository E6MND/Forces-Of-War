package com.google.android.gms.drive.realtime.internal.event;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class ParcelableObjectChangedEvent implements SafeParcelable {
    public static final Parcelable.Creator<ParcelableObjectChangedEvent> CREATOR = new c();
    final int LC;
    final int LD;
    final String Lj;
    final String Ln;
    final boolean Lp;
    final String Lq;
    final String rO;
    final int xJ;

    ParcelableObjectChangedEvent(int versionCode, String sessionId, String userId, boolean isLocal, String objectId, String objectType, int valueIndex, int valueCount) {
        this.rO = sessionId;
        this.Lj = userId;
        this.Lp = isLocal;
        this.Ln = objectId;
        this.Lq = objectType;
        this.xJ = versionCode;
        this.LC = valueIndex;
        this.LD = valueCount;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        c.a(this, dest, flags);
    }
}
