package com.google.android.gms.drive.realtime.internal.event;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class ParcelableEventList implements SafeParcelable {
    public static final Parcelable.Creator<ParcelableEventList> CREATOR = new b();
    final boolean LA;
    final ParcelableObjectChangedEvent[] LB;
    final ParcelableEvent[] Ly;
    final DataHolder Lz;
    final int xJ;

    ParcelableEventList(int versionCode, ParcelableEvent[] events, DataHolder eventData, boolean undoRedoStateChanged, ParcelableObjectChangedEvent[] objectChangedEvents) {
        this.xJ = versionCode;
        this.Ly = events;
        this.Lz = eventData;
        this.LA = undoRedoStateChanged;
        this.LB = objectChangedEvents;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        b.a(this, dest, flags);
    }
}
