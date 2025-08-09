package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.List;

public class OnResourceIdSetResponse implements SafeParcelable {
    public static final Parcelable.Creator<OnResourceIdSetResponse> CREATOR = new ao();
    private final List<String> Iq;
    private final int xJ;

    OnResourceIdSetResponse(int versionCode, List<String> resourceIds) {
        this.xJ = versionCode;
        this.Iq = resourceIds;
    }

    public int describeContents() {
        return 0;
    }

    public int getVersionCode() {
        return this.xJ;
    }

    public List<String> gj() {
        return this.Iq;
    }

    public void writeToParcel(Parcel dest, int flags) {
        ao.a(this, dest, flags);
    }
}
