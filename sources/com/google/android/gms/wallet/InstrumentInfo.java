package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class InstrumentInfo implements SafeParcelable {
    public static final Parcelable.Creator<InstrumentInfo> CREATOR = new h();
    private String aiZ;
    private String aja;
    private final int xJ;

    InstrumentInfo(int versionCode, String instrumentType, String instrumentDetails) {
        this.xJ = versionCode;
        this.aiZ = instrumentType;
        this.aja = instrumentDetails;
    }

    public int describeContents() {
        return 0;
    }

    public String getInstrumentDetails() {
        return this.aja;
    }

    public String getInstrumentType() {
        return this.aiZ;
    }

    public int getVersionCode() {
        return this.xJ;
    }

    public void writeToParcel(Parcel out, int flags) {
        h.a(this, out, flags);
    }
}
