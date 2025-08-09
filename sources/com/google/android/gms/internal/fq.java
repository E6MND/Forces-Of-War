package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

public class fq implements SafeParcelable {
    public static final fr CREATOR = new fr();
    public final String name;
    public final int weight;
    final int xJ;
    public final String xY;
    public final boolean xZ;
    public final boolean ya;
    public final String yb;
    public final fn[] yc;
    final int[] yd;
    public final String ye;

    public static final class a {
        private final String mName;
        private String yf;
        private boolean yg;
        private int yh = 1;
        private boolean yi;
        private String yj;
        private final List<fn> yk = new ArrayList();
        private BitSet yl;
        private String ym;

        public a(String str) {
            this.mName = str;
        }

        public a I(int i) {
            if (this.yl == null) {
                this.yl = new BitSet();
            }
            this.yl.set(i);
            return this;
        }

        public a Z(String str) {
            this.yf = str;
            return this;
        }

        public a aa(String str) {
            this.ym = str;
            return this;
        }

        public fq dL() {
            int i = 0;
            int[] iArr = null;
            if (this.yl != null) {
                iArr = new int[this.yl.cardinality()];
                int nextSetBit = this.yl.nextSetBit(0);
                while (nextSetBit >= 0) {
                    iArr[i] = nextSetBit;
                    nextSetBit = this.yl.nextSetBit(nextSetBit + 1);
                    i++;
                }
            }
            return new fq(this.mName, this.yf, this.yg, this.yh, this.yi, this.yj, (fn[]) this.yk.toArray(new fn[this.yk.size()]), iArr, this.ym);
        }

        public a w(boolean z) {
            this.yg = z;
            return this;
        }

        public a x(boolean z) {
            this.yi = z;
            return this;
        }
    }

    fq(int i, String str, String str2, boolean z, int i2, boolean z2, String str3, fn[] fnVarArr, int[] iArr, String str4) {
        this.xJ = i;
        this.name = str;
        this.xY = str2;
        this.xZ = z;
        this.weight = i2;
        this.ya = z2;
        this.yb = str3;
        this.yc = fnVarArr;
        this.yd = iArr;
        this.ye = str4;
    }

    fq(String str, String str2, boolean z, int i, boolean z2, String str3, fn[] fnVarArr, int[] iArr, String str4) {
        this(2, str, str2, z, i, z2, str3, fnVarArr, iArr, str4);
    }

    public int describeContents() {
        fr frVar = CREATOR;
        return 0;
    }

    public boolean equals(Object object) {
        if (!(object instanceof fq)) {
            return false;
        }
        fq fqVar = (fq) object;
        return this.name.equals(fqVar.name) && this.xY.equals(fqVar.xY) && this.xZ == fqVar.xZ;
    }

    public void writeToParcel(Parcel out, int flags) {
        fr frVar = CREATOR;
        fr.a(this, out, flags);
    }
}
