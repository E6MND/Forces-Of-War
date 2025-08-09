package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.BitSet;

public class fh implements SafeParcelable {
    public static final fi CREATOR = new fi();
    final int xJ;
    final fl[] xK;
    public final String xL;
    public final boolean xM;

    fh(int i, fl[] flVarArr, String str, boolean z) {
        this.xJ = i;
        this.xK = flVarArr;
        this.xL = str;
        this.xM = z;
    }

    public fh(String str, boolean z, fl... flVarArr) {
        this(1, flVarArr, str, z);
        BitSet bitSet = new BitSet(fp.dK());
        for (fl flVar : flVarArr) {
            int i = flVar.xT;
            if (i != -1) {
                if (bitSet.get(i)) {
                    throw new IllegalArgumentException("Duplicate global search section type " + fp.H(i));
                }
                bitSet.set(i);
            }
        }
    }

    public int describeContents() {
        fi fiVar = CREATOR;
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        fi fiVar = CREATOR;
        fi.a(this, dest, flags);
    }
}
