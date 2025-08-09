package com.google.android.gms.location;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.hl;

public class b implements SafeParcelable {
    public static final c CREATOR = new c();
    int Vn;
    int Vo;
    long Vp;
    private final int xJ;

    b(int i, int i2, int i3, long j) {
        this.xJ = i;
        this.Vn = i2;
        this.Vo = i3;
        this.Vp = j;
    }

    private String cI(int i) {
        switch (i) {
            case 0:
                return "STATUS_SUCCESSFUL";
            case 2:
                return "STATUS_TIMED_OUT_ON_SCAN";
            case 3:
                return "STATUS_NO_INFO_IN_DATABASE";
            case 4:
                return "STATUS_INVALID_SCAN";
            case 5:
                return "STATUS_UNABLE_TO_QUERY_DATABASE";
            case 6:
                return "STATUS_SCANS_DISABLED_IN_SETTINGS";
            case 7:
                return "STATUS_LOCATION_DISABLED_IN_SETTINGS";
            case 8:
                return "STATUS_IN_PROGRESS";
            default:
                return "STATUS_UNKNOWN";
        }
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object other) {
        if (!(other instanceof b)) {
            return false;
        }
        b bVar = (b) other;
        return this.Vn == bVar.Vn && this.Vo == bVar.Vo && this.Vp == bVar.Vp;
    }

    /* access modifiers changed from: package-private */
    public int getVersionCode() {
        return this.xJ;
    }

    public int hashCode() {
        return hl.hashCode(Integer.valueOf(this.Vn), Integer.valueOf(this.Vo), Long.valueOf(this.Vp));
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("LocationStatus[cell status: ").append(cI(this.Vn));
        sb.append(", wifi status: ").append(cI(this.Vo));
        sb.append(", elapsed realtime ns: ").append(this.Vp);
        sb.append(']');
        return sb.toString();
    }

    public void writeToParcel(Parcel parcel, int flags) {
        c.a(this, parcel, flags);
    }
}
