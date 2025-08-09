package com.google.android.gms.internal;

import android.graphics.drawable.Drawable;

public final class gx extends hr<a, Drawable> {

    public static final class a {
        public final int FP;
        public final int FQ;

        public a(int i, int i2) {
            this.FP = i;
            this.FQ = i2;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof a)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            a aVar = (a) obj;
            return aVar.FP == this.FP && aVar.FQ == this.FQ;
        }

        public int hashCode() {
            return hl.hashCode(Integer.valueOf(this.FP), Integer.valueOf(this.FQ));
        }
    }

    public gx() {
        super(10);
    }
}
