package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.Contents;

public class OnContentsResponse implements SafeParcelable {
    public static final Parcelable.Creator<OnContentsResponse> CREATOR = new ag();
    final Contents HD;
    final int xJ;

    OnContentsResponse(int versionCode, Contents contents) {
        this.xJ = versionCode;
        this.HD = contents;
    }

    public int describeContents() {
        return 0;
    }

    public Contents go() {
        return this.HD;
    }

    public void writeToParcel(Parcel dest, int flags) {
        ag.a(this, dest, flags);
    }
}
