package com.google.android.gms.drive.realtime.internal.event;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class ValuesSetDetails implements SafeParcelable {
    public static final Parcelable.Creator<ValuesSetDetails> CREATOR = new j();
    final int LC;
    final int LD;
    final int mIndex;
    final int xJ;

    ValuesSetDetails(int versionCode, int index, int valueIndex, int valueCount) {
        this.xJ = versionCode;
        this.mIndex = index;
        this.LC = valueIndex;
        this.LD = valueCount;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        j.a(this, dest, flags);
    }
}
