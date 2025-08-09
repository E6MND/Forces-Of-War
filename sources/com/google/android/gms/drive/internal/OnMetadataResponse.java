package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

public class OnMetadataResponse implements SafeParcelable {
    public static final Parcelable.Creator<OnMetadataResponse> CREATOR = new am();
    final MetadataBundle IA;
    final int xJ;

    OnMetadataResponse(int versionCode, MetadataBundle metadata) {
        this.xJ = versionCode;
        this.IA = metadata;
    }

    public int describeContents() {
        return 0;
    }

    public MetadataBundle gw() {
        return this.IA;
    }

    public void writeToParcel(Parcel dest, int flags) {
        am.a(this, dest, flags);
    }
}
