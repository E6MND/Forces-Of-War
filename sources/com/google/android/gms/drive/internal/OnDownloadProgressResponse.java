package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class OnDownloadProgressResponse implements SafeParcelable {
    public static final Parcelable.Creator<OnDownloadProgressResponse> CREATOR = new ah();
    final long Jt;
    final long Ju;
    final int xJ;

    OnDownloadProgressResponse(int versionCode, long bytesLoaded, long bytesExpected) {
        this.xJ = versionCode;
        this.Jt = bytesLoaded;
        this.Ju = bytesExpected;
    }

    public int describeContents() {
        return 0;
    }

    public long gp() {
        return this.Jt;
    }

    public long gq() {
        return this.Ju;
    }

    public void writeToParcel(Parcel dest, int flags) {
        ah.a(this, dest, flags);
    }
}
