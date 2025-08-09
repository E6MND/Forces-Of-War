package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class jp implements SafeParcelable {
    public static final jq CREATOR = new jq();
    private final String Wi;
    private final String mTag;
    final int xJ;

    jp(int i, String str, String str2) {
        this.xJ = i;
        this.Wi = str;
        this.mTag = str2;
    }

    public int describeContents() {
        jq jqVar = CREATOR;
        return 0;
    }

    public boolean equals(Object that) {
        if (!(that instanceof jp)) {
            return false;
        }
        jp jpVar = (jp) that;
        return hl.equal(this.Wi, jpVar.Wi) && hl.equal(this.mTag, jpVar.mTag);
    }

    public String getTag() {
        return this.mTag;
    }

    public int hashCode() {
        return hl.hashCode(this.Wi, this.mTag);
    }

    public String je() {
        return this.Wi;
    }

    public String toString() {
        return hl.e(this).a("mPlaceId", this.Wi).a("mTag", this.mTag).toString();
    }

    public void writeToParcel(Parcel out, int flags) {
        jq jqVar = CREATOR;
        jq.a(this, out, flags);
    }
}
