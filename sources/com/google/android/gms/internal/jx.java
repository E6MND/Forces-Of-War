package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public final class jx implements SafeParcelable {
    public static final jy CREATOR = new jy();
    public static final jx YM = w("test_type", 1);
    public static final jx YN = w("saved_offers", 4);
    public static final Set<jx> YO = Collections.unmodifiableSet(new HashSet(Arrays.asList(new jx[]{YM, YN})));
    final int YP;
    final String qU;
    final int xJ;

    jx(int i, String str, int i2) {
        hn.aE(str);
        this.xJ = i;
        this.qU = str;
        this.YP = i2;
    }

    private static jx w(String str, int i) {
        return new jx(0, str, i);
    }

    public int describeContents() {
        jy jyVar = CREATOR;
        return 0;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof jx)) {
            return false;
        }
        jx jxVar = (jx) object;
        return this.qU.equals(jxVar.qU) && this.YP == jxVar.YP;
    }

    public int hashCode() {
        return this.qU.hashCode();
    }

    public String toString() {
        return this.qU;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        jy jyVar = CREATOR;
        jy.a(this, parcel, flags);
    }
}
