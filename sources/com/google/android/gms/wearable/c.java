package com.google.android.gms.wearable;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.hl;

public class c implements SafeParcelable {
    public static final Parcelable.Creator<c> CREATOR = new d();
    private final int AQ;
    private final String YI;
    private final int alf;
    private final boolean alg;
    private final String mName;
    final int xJ;

    c(int i, String str, String str2, int i2, int i3, boolean z) {
        this.xJ = i;
        this.mName = str;
        this.YI = str2;
        this.AQ = i2;
        this.alf = i3;
        this.alg = z;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object o) {
        if (!(o instanceof c)) {
            return false;
        }
        c cVar = (c) o;
        return hl.equal(Integer.valueOf(this.xJ), Integer.valueOf(cVar.xJ)) && hl.equal(this.mName, cVar.mName) && hl.equal(this.YI, cVar.YI) && hl.equal(Integer.valueOf(this.AQ), Integer.valueOf(cVar.AQ)) && hl.equal(Integer.valueOf(this.alf), Integer.valueOf(cVar.alf)) && hl.equal(Boolean.valueOf(this.alg), Boolean.valueOf(cVar.alg));
    }

    public String getAddress() {
        return this.YI;
    }

    public String getName() {
        return this.mName;
    }

    public int getRole() {
        return this.alf;
    }

    public int getType() {
        return this.AQ;
    }

    public int hashCode() {
        return hl.hashCode(Integer.valueOf(this.xJ), this.mName, this.YI, Integer.valueOf(this.AQ), Integer.valueOf(this.alf), Boolean.valueOf(this.alg));
    }

    public boolean isEnabled() {
        return this.alg;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("ConnectionConfiguration[ ");
        sb.append("mName=" + this.mName);
        sb.append(", mAddress=" + this.YI);
        sb.append(", mType=" + this.AQ);
        sb.append(", mRole=" + this.alf);
        sb.append(", mEnabled=" + this.alg);
        sb.append("]");
        return sb.toString();
    }

    public void writeToParcel(Parcel dest, int flags) {
        d.a(this, dest, flags);
    }
}
