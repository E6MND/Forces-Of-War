package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Collections;
import java.util.List;

public final class dv implements SafeParcelable {
    public static final dw CREATOR = new dw();
    public final int errorCode;
    public final List<String> nr;
    public final List<String> ns;
    public final long nv;
    public final int orientation;
    public final String oy;
    public final String qb;
    public final long qc;
    public final boolean qd;
    public final long qe;
    public final List<String> qf;
    public final String qg;
    public final long qh;
    public final String qi;
    public final boolean qj;
    public final String qk;
    public final String ql;
    public final int versionCode;

    public dv(int i) {
        this(7, (String) null, (String) null, (List<String>) null, i, (List<String>) null, -1, false, -1, (List<String>) null, -1, -1, (String) null, -1, (String) null, false, (String) null, (String) null);
    }

    public dv(int i, long j) {
        this(7, (String) null, (String) null, (List<String>) null, i, (List<String>) null, -1, false, -1, (List<String>) null, j, -1, (String) null, -1, (String) null, false, (String) null, (String) null);
    }

    dv(int i, String str, String str2, List<String> list, int i2, List<String> list2, long j, boolean z, long j2, List<String> list3, long j3, int i3, String str3, long j4, String str4, boolean z2, String str5, String str6) {
        this.versionCode = i;
        this.oy = str;
        this.qb = str2;
        this.nr = list != null ? Collections.unmodifiableList(list) : null;
        this.errorCode = i2;
        this.ns = list2 != null ? Collections.unmodifiableList(list2) : null;
        this.qc = j;
        this.qd = z;
        this.qe = j2;
        this.qf = list3 != null ? Collections.unmodifiableList(list3) : null;
        this.nv = j3;
        this.orientation = i3;
        this.qg = str3;
        this.qh = j4;
        this.qi = str4;
        this.qj = z2;
        this.qk = str5;
        this.ql = str6;
    }

    public dv(String str, String str2, List<String> list, List<String> list2, long j, boolean z, long j2, List<String> list3, long j3, int i, String str3, long j4, String str4, String str5) {
        this(7, str, str2, list, -2, list2, j, z, j2, list3, j3, i, str3, j4, str4, false, (String) null, str5);
    }

    public dv(String str, String str2, List<String> list, List<String> list2, long j, boolean z, long j2, List<String> list3, long j3, int i, String str3, long j4, String str4, boolean z2, String str5, String str6) {
        this(7, str, str2, list, -2, list2, j, z, j2, list3, j3, i, str3, j4, str4, z2, str5, str6);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        dw.a(this, out, flags);
    }
}
