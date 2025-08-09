package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.concurrent.TimeUnit;

public final class jr implements SafeParcelable {
    public static final js CREATOR = new js();
    static final long Wj = TimeUnit.HOURS.toMillis(1);
    private final long Vi;
    private final jn Wk;
    private final int mPriority;
    final int xJ;

    public jr(int i, jn jnVar, long j, int i2) {
        this.xJ = i;
        this.Wk = jnVar;
        this.Vi = j;
        this.mPriority = i2;
    }

    public int describeContents() {
        js jsVar = CREATOR;
        return 0;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof jr)) {
            return false;
        }
        jr jrVar = (jr) object;
        return hl.equal(this.Wk, jrVar.Wk) && this.Vi == jrVar.Vi && this.mPriority == jrVar.mPriority;
    }

    public long getInterval() {
        return this.Vi;
    }

    public int getPriority() {
        return this.mPriority;
    }

    public int hashCode() {
        return hl.hashCode(this.Wk, Long.valueOf(this.Vi), Integer.valueOf(this.mPriority));
    }

    public jn ja() {
        return this.Wk;
    }

    public String toString() {
        return hl.e(this).a("filter", this.Wk).a("interval", Long.valueOf(this.Vi)).a("priority", Integer.valueOf(this.mPriority)).toString();
    }

    public void writeToParcel(Parcel parcel, int flags) {
        js jsVar = CREATOR;
        js.a(this, parcel, flags);
    }
}
