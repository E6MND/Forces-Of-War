package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.maps.model.LatLng;
import java.util.ArrayList;
import java.util.List;

public class jv implements SafeParcelable {
    public static final Parcelable.Creator<jv> CREATOR = new jw();
    private final LatLng YH;
    private final String YI;
    private final List<jt> YJ;
    private final String YK;
    private final String YL;
    private final String mName;
    final int xJ;

    jv(int i, String str, LatLng latLng, String str2, List<jt> list, String str3, String str4) {
        this.xJ = i;
        this.mName = str;
        this.YH = latLng;
        this.YI = str2;
        this.YJ = new ArrayList(list);
        this.YK = str3;
        this.YL = str4;
    }

    public int describeContents() {
        return 0;
    }

    public String getAddress() {
        return this.YI;
    }

    public String getName() {
        return this.mName;
    }

    public String getPhoneNumber() {
        return this.YK;
    }

    public LatLng jf() {
        return this.YH;
    }

    public List<jt> jg() {
        return this.YJ;
    }

    public String jh() {
        return this.YL;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        jw.a(this, parcel, flags);
    }
}
