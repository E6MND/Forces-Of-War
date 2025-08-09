package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class cf implements SafeParcelable {
    public static final ce CREATOR = new ce();
    public final String mimeType;
    public final String nY;
    public final String nZ;
    public final String oa;
    public final String ob;
    public final String oc;
    public final String packageName;
    public final int versionCode;

    public cf(int i, String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        this.versionCode = i;
        this.nY = str;
        this.nZ = str2;
        this.mimeType = str3;
        this.packageName = str4;
        this.oa = str5;
        this.ob = str6;
        this.oc = str7;
    }

    public cf(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        this(1, str, str2, str3, str4, str5, str6, str7);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        ce.a(this, out, flags);
    }
}
