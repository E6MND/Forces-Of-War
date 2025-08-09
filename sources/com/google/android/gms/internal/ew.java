package com.google.android.gms.internal;

import android.os.Parcel;
import com.facebook.AppEventsConstants;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class ew implements SafeParcelable {
    public static final ex CREATOR = new ex();
    public String st;
    public int su;
    public int sv;
    public boolean sw;
    public final int versionCode;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public ew(int i, int i2, boolean z) {
        this(1, "afma-sdk-a-v" + i + "." + i2 + "." + (z ? AppEventsConstants.EVENT_PARAM_VALUE_NO : AppEventsConstants.EVENT_PARAM_VALUE_YES), i, i2, z);
    }

    ew(int i, String str, int i2, int i3, boolean z) {
        this.versionCode = i;
        this.st = str;
        this.su = i2;
        this.sv = i3;
        this.sw = z;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        ex.a(this, out, flags);
    }
}
