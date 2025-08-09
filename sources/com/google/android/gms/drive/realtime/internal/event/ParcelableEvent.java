package com.google.android.gms.drive.realtime.internal.event;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class ParcelableEvent implements SafeParcelable {
    public static final Parcelable.Creator<ParcelableEvent> CREATOR = new a();
    final String Lj;
    final String Ln;
    final boolean Lp;
    final String Lq;
    final TextInsertedDetails Lr;
    final TextDeletedDetails Ls;
    final ValuesAddedDetails Lt;
    final ValuesRemovedDetails Lu;
    final ValuesSetDetails Lv;
    final ValueChangedDetails Lw;
    final ReferenceShiftedDetails Lx;
    final String rO;
    final int xJ;

    ParcelableEvent(int versionCode, String sessionId, String userId, boolean isLocal, String objectId, String objectType, TextInsertedDetails textInsertedDetails, TextDeletedDetails textDeletedDetails, ValuesAddedDetails valuesAddedDetails, ValuesRemovedDetails valuesRemovedDetails, ValuesSetDetails valuesSetDetails, ValueChangedDetails valueChangedDetails, ReferenceShiftedDetails referenceShiftedDetails) {
        this.xJ = versionCode;
        this.rO = sessionId;
        this.Lj = userId;
        this.Lp = isLocal;
        this.Ln = objectId;
        this.Lq = objectType;
        this.Lr = textInsertedDetails;
        this.Ls = textDeletedDetails;
        this.Lt = valuesAddedDetails;
        this.Lu = valuesRemovedDetails;
        this.Lv = valuesSetDetails;
        this.Lw = valueChangedDetails;
        this.Lx = referenceShiftedDetails;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        a.a(this, dest, flags);
    }
}
