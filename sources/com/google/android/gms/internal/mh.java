package com.google.android.gms.internal;

import java.util.Arrays;

public final class mh {
    final byte[] amZ;
    final int tag;

    mh(int i, byte[] bArr) {
        this.tag = i;
        this.amZ = bArr;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof mh)) {
            return false;
        }
        mh mhVar = (mh) o;
        return this.tag == mhVar.tag && Arrays.equals(this.amZ, mhVar.amZ);
    }

    public int hashCode() {
        return ((this.tag + 527) * 31) + Arrays.hashCode(this.amZ);
    }
}
