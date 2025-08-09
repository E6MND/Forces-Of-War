package com.google.android.gms.internal;

import com.google.android.gms.location.LocationRequest;
import com.googlecode.eyesfree.braille.display.BrailleInputEvent;
import java.io.IOException;
import java.util.Arrays;

public final class lx extends mb<lx> {
    public a[] amo;

    public static final class a extends mb<a> {
        private static volatile a[] amp;
        public C0079a amq;
        public String name;

        /* renamed from: com.google.android.gms.internal.lx$a$a  reason: collision with other inner class name */
        public static final class C0079a extends mb<C0079a> {
            private static volatile C0079a[] amr;
            public C0080a ams;
            public int type;

            /* renamed from: com.google.android.gms.internal.lx$a$a$a  reason: collision with other inner class name */
            public static final class C0080a extends mb<C0080a> {
                public boolean amA;
                public a[] amB;
                public C0079a[] amC;
                public String[] amD;
                public long[] amE;
                public float[] amF;
                public long amG;
                public byte[] amt;
                public String amu;
                public double amv;
                public float amw;
                public long amx;
                public int amy;
                public int amz;

                public C0080a() {
                    nv();
                }

                public void a(ma maVar) throws IOException {
                    if (!Arrays.equals(this.amt, mi.anh)) {
                        maVar.a(1, this.amt);
                    }
                    if (!this.amu.equals("")) {
                        maVar.b(2, this.amu);
                    }
                    if (Double.doubleToLongBits(this.amv) != Double.doubleToLongBits(0.0d)) {
                        maVar.a(3, this.amv);
                    }
                    if (Float.floatToIntBits(this.amw) != Float.floatToIntBits(0.0f)) {
                        maVar.b(4, this.amw);
                    }
                    if (this.amx != 0) {
                        maVar.b(5, this.amx);
                    }
                    if (this.amy != 0) {
                        maVar.p(6, this.amy);
                    }
                    if (this.amz != 0) {
                        maVar.q(7, this.amz);
                    }
                    if (this.amA) {
                        maVar.a(8, this.amA);
                    }
                    if (this.amB != null && this.amB.length > 0) {
                        for (a aVar : this.amB) {
                            if (aVar != null) {
                                maVar.a(9, (mf) aVar);
                            }
                        }
                    }
                    if (this.amC != null && this.amC.length > 0) {
                        for (C0079a aVar2 : this.amC) {
                            if (aVar2 != null) {
                                maVar.a(10, (mf) aVar2);
                            }
                        }
                    }
                    if (this.amD != null && this.amD.length > 0) {
                        for (String str : this.amD) {
                            if (str != null) {
                                maVar.b(11, str);
                            }
                        }
                    }
                    if (this.amE != null && this.amE.length > 0) {
                        for (long b : this.amE) {
                            maVar.b(12, b);
                        }
                    }
                    if (this.amG != 0) {
                        maVar.b(13, this.amG);
                    }
                    if (this.amF != null && this.amF.length > 0) {
                        for (float b2 : this.amF) {
                            maVar.b(14, b2);
                        }
                    }
                    super.a(maVar);
                }

                /* access modifiers changed from: protected */
                public int c() {
                    int c = super.c();
                    if (!Arrays.equals(this.amt, mi.anh)) {
                        c += ma.b(1, this.amt);
                    }
                    if (!this.amu.equals("")) {
                        c += ma.h(2, this.amu);
                    }
                    if (Double.doubleToLongBits(this.amv) != Double.doubleToLongBits(0.0d)) {
                        c += ma.b(3, this.amv);
                    }
                    if (Float.floatToIntBits(this.amw) != Float.floatToIntBits(0.0f)) {
                        c += ma.c(4, this.amw);
                    }
                    if (this.amx != 0) {
                        c += ma.d(5, this.amx);
                    }
                    if (this.amy != 0) {
                        c += ma.r(6, this.amy);
                    }
                    if (this.amz != 0) {
                        c += ma.s(7, this.amz);
                    }
                    if (this.amA) {
                        c += ma.b(8, this.amA);
                    }
                    if (this.amB != null && this.amB.length > 0) {
                        int i = c;
                        for (a aVar : this.amB) {
                            if (aVar != null) {
                                i += ma.b(9, (mf) aVar);
                            }
                        }
                        c = i;
                    }
                    if (this.amC != null && this.amC.length > 0) {
                        int i2 = c;
                        for (C0079a aVar2 : this.amC) {
                            if (aVar2 != null) {
                                i2 += ma.b(10, (mf) aVar2);
                            }
                        }
                        c = i2;
                    }
                    if (this.amD != null && this.amD.length > 0) {
                        int i3 = 0;
                        int i4 = 0;
                        for (String str : this.amD) {
                            if (str != null) {
                                i4++;
                                i3 += ma.cz(str);
                            }
                        }
                        c = c + i3 + (i4 * 1);
                    }
                    if (this.amE != null && this.amE.length > 0) {
                        int i5 = 0;
                        for (long D : this.amE) {
                            i5 += ma.D(D);
                        }
                        c = c + i5 + (this.amE.length * 1);
                    }
                    if (this.amG != 0) {
                        c += ma.d(13, this.amG);
                    }
                    return (this.amF == null || this.amF.length <= 0) ? c : c + (this.amF.length * 4) + (this.amF.length * 1);
                }

                public boolean equals(Object o) {
                    if (o == this) {
                        return true;
                    }
                    if (!(o instanceof C0080a)) {
                        return false;
                    }
                    C0080a aVar = (C0080a) o;
                    if (!Arrays.equals(this.amt, aVar.amt)) {
                        return false;
                    }
                    if (this.amu == null) {
                        if (aVar.amu != null) {
                            return false;
                        }
                    } else if (!this.amu.equals(aVar.amu)) {
                        return false;
                    }
                    if (Double.doubleToLongBits(this.amv) != Double.doubleToLongBits(aVar.amv) || Float.floatToIntBits(this.amw) != Float.floatToIntBits(aVar.amw) || this.amx != aVar.amx || this.amy != aVar.amy || this.amz != aVar.amz || this.amA != aVar.amA || !md.equals((Object[]) this.amB, (Object[]) aVar.amB) || !md.equals((Object[]) this.amC, (Object[]) aVar.amC) || !md.equals((Object[]) this.amD, (Object[]) aVar.amD) || !md.equals(this.amE, aVar.amE) || !md.equals(this.amF, aVar.amF) || this.amG != aVar.amG) {
                        return false;
                    }
                    if (this.amU == null || this.amU.isEmpty()) {
                        return aVar.amU == null || aVar.amU.isEmpty();
                    }
                    return this.amU.equals(aVar.amU);
                }

                public int hashCode() {
                    int i = 0;
                    int hashCode = (this.amu == null ? 0 : this.amu.hashCode()) + ((Arrays.hashCode(this.amt) + 527) * 31);
                    long doubleToLongBits = Double.doubleToLongBits(this.amv);
                    int floatToIntBits = ((((((((((((((this.amA ? 1231 : 1237) + (((((((((((hashCode * 31) + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)))) * 31) + Float.floatToIntBits(this.amw)) * 31) + ((int) (this.amx ^ (this.amx >>> 32)))) * 31) + this.amy) * 31) + this.amz) * 31)) * 31) + md.hashCode((Object[]) this.amB)) * 31) + md.hashCode((Object[]) this.amC)) * 31) + md.hashCode((Object[]) this.amD)) * 31) + md.hashCode(this.amE)) * 31) + md.hashCode(this.amF)) * 31) + ((int) (this.amG ^ (this.amG >>> 32)))) * 31;
                    if (this.amU != null && !this.amU.isEmpty()) {
                        i = this.amU.hashCode();
                    }
                    return floatToIntBits + i;
                }

                public C0080a nv() {
                    this.amt = mi.anh;
                    this.amu = "";
                    this.amv = 0.0d;
                    this.amw = 0.0f;
                    this.amx = 0;
                    this.amy = 0;
                    this.amz = 0;
                    this.amA = false;
                    this.amB = a.nr();
                    this.amC = C0079a.nt();
                    this.amD = mi.anf;
                    this.amE = mi.anb;
                    this.amF = mi.anc;
                    this.amG = 0;
                    this.amU = null;
                    this.amY = -1;
                    return this;
                }

                /* renamed from: t */
                public C0080a b(lz lzVar) throws IOException {
                    while (true) {
                        int nw = lzVar.nw();
                        switch (nw) {
                            case 0:
                                break;
                            case 10:
                                this.amt = lzVar.readBytes();
                                continue;
                            case 18:
                                this.amu = lzVar.readString();
                                continue;
                            case 25:
                                this.amv = lzVar.readDouble();
                                continue;
                            case 37:
                                this.amw = lzVar.readFloat();
                                continue;
                            case BrailleInputEvent.CMD_SELECTION_START /*40*/:
                                this.amx = lzVar.ny();
                                continue;
                            case 48:
                                this.amy = lzVar.nz();
                                continue;
                            case 56:
                                this.amz = lzVar.nB();
                                continue;
                            case 64:
                                this.amA = lzVar.nA();
                                continue;
                            case 74:
                                int b = mi.b(lzVar, 74);
                                int length = this.amB == null ? 0 : this.amB.length;
                                a[] aVarArr = new a[(b + length)];
                                if (length != 0) {
                                    System.arraycopy(this.amB, 0, aVarArr, 0, length);
                                }
                                while (length < aVarArr.length - 1) {
                                    aVarArr[length] = new a();
                                    lzVar.a(aVarArr[length]);
                                    lzVar.nw();
                                    length++;
                                }
                                aVarArr[length] = new a();
                                lzVar.a(aVarArr[length]);
                                this.amB = aVarArr;
                                continue;
                            case 82:
                                int b2 = mi.b(lzVar, 82);
                                int length2 = this.amC == null ? 0 : this.amC.length;
                                C0079a[] aVarArr2 = new C0079a[(b2 + length2)];
                                if (length2 != 0) {
                                    System.arraycopy(this.amC, 0, aVarArr2, 0, length2);
                                }
                                while (length2 < aVarArr2.length - 1) {
                                    aVarArr2[length2] = new C0079a();
                                    lzVar.a(aVarArr2[length2]);
                                    lzVar.nw();
                                    length2++;
                                }
                                aVarArr2[length2] = new C0079a();
                                lzVar.a(aVarArr2[length2]);
                                this.amC = aVarArr2;
                                continue;
                            case 90:
                                int b3 = mi.b(lzVar, 90);
                                int length3 = this.amD == null ? 0 : this.amD.length;
                                String[] strArr = new String[(b3 + length3)];
                                if (length3 != 0) {
                                    System.arraycopy(this.amD, 0, strArr, 0, length3);
                                }
                                while (length3 < strArr.length - 1) {
                                    strArr[length3] = lzVar.readString();
                                    lzVar.nw();
                                    length3++;
                                }
                                strArr[length3] = lzVar.readString();
                                this.amD = strArr;
                                continue;
                            case 96:
                                int b4 = mi.b(lzVar, 96);
                                int length4 = this.amE == null ? 0 : this.amE.length;
                                long[] jArr = new long[(b4 + length4)];
                                if (length4 != 0) {
                                    System.arraycopy(this.amE, 0, jArr, 0, length4);
                                }
                                while (length4 < jArr.length - 1) {
                                    jArr[length4] = lzVar.ny();
                                    lzVar.nw();
                                    length4++;
                                }
                                jArr[length4] = lzVar.ny();
                                this.amE = jArr;
                                continue;
                            case 98:
                                int ex = lzVar.ex(lzVar.nD());
                                int position = lzVar.getPosition();
                                int i = 0;
                                while (lzVar.nI() > 0) {
                                    lzVar.ny();
                                    i++;
                                }
                                lzVar.ez(position);
                                int length5 = this.amE == null ? 0 : this.amE.length;
                                long[] jArr2 = new long[(i + length5)];
                                if (length5 != 0) {
                                    System.arraycopy(this.amE, 0, jArr2, 0, length5);
                                }
                                while (length5 < jArr2.length) {
                                    jArr2[length5] = lzVar.ny();
                                    length5++;
                                }
                                this.amE = jArr2;
                                lzVar.ey(ex);
                                continue;
                            case LocationRequest.PRIORITY_LOW_POWER:
                                this.amG = lzVar.ny();
                                continue;
                            case 114:
                                int nD = lzVar.nD();
                                int ex2 = lzVar.ex(nD);
                                int i2 = nD / 4;
                                int length6 = this.amF == null ? 0 : this.amF.length;
                                float[] fArr = new float[(i2 + length6)];
                                if (length6 != 0) {
                                    System.arraycopy(this.amF, 0, fArr, 0, length6);
                                }
                                while (length6 < fArr.length) {
                                    fArr[length6] = lzVar.readFloat();
                                    length6++;
                                }
                                this.amF = fArr;
                                lzVar.ey(ex2);
                                continue;
                            case 117:
                                int b5 = mi.b(lzVar, 117);
                                int length7 = this.amF == null ? 0 : this.amF.length;
                                float[] fArr2 = new float[(b5 + length7)];
                                if (length7 != 0) {
                                    System.arraycopy(this.amF, 0, fArr2, 0, length7);
                                }
                                while (length7 < fArr2.length - 1) {
                                    fArr2[length7] = lzVar.readFloat();
                                    lzVar.nw();
                                    length7++;
                                }
                                fArr2[length7] = lzVar.readFloat();
                                this.amF = fArr2;
                                continue;
                            default:
                                if (!a(lzVar, nw)) {
                                    break;
                                } else {
                                    continue;
                                }
                        }
                    }
                    return this;
                }
            }

            public C0079a() {
                nu();
            }

            public static C0079a[] nt() {
                if (amr == null) {
                    synchronized (md.amX) {
                        if (amr == null) {
                            amr = new C0079a[0];
                        }
                    }
                }
                return amr;
            }

            public void a(ma maVar) throws IOException {
                maVar.p(1, this.type);
                if (this.ams != null) {
                    maVar.a(2, (mf) this.ams);
                }
                super.a(maVar);
            }

            /* access modifiers changed from: protected */
            public int c() {
                int c = super.c() + ma.r(1, this.type);
                return this.ams != null ? c + ma.b(2, (mf) this.ams) : c;
            }

            public boolean equals(Object o) {
                if (o == this) {
                    return true;
                }
                if (!(o instanceof C0079a)) {
                    return false;
                }
                C0079a aVar = (C0079a) o;
                if (this.type != aVar.type) {
                    return false;
                }
                if (this.ams == null) {
                    if (aVar.ams != null) {
                        return false;
                    }
                } else if (!this.ams.equals(aVar.ams)) {
                    return false;
                }
                if (this.amU == null || this.amU.isEmpty()) {
                    return aVar.amU == null || aVar.amU.isEmpty();
                }
                return this.amU.equals(aVar.amU);
            }

            public int hashCode() {
                int i = 0;
                int hashCode = ((this.ams == null ? 0 : this.ams.hashCode()) + ((this.type + 527) * 31)) * 31;
                if (this.amU != null && !this.amU.isEmpty()) {
                    i = this.amU.hashCode();
                }
                return hashCode + i;
            }

            public C0079a nu() {
                this.type = 1;
                this.ams = null;
                this.amU = null;
                this.amY = -1;
                return this;
            }

            /* renamed from: s */
            public C0079a b(lz lzVar) throws IOException {
                while (true) {
                    int nw = lzVar.nw();
                    switch (nw) {
                        case 0:
                            break;
                        case 8:
                            int nz = lzVar.nz();
                            switch (nz) {
                                case 1:
                                case 2:
                                case 3:
                                case 4:
                                case 5:
                                case 6:
                                case 7:
                                case 8:
                                case 9:
                                case 10:
                                case 11:
                                case 12:
                                case 13:
                                case 14:
                                case 15:
                                    this.type = nz;
                                    break;
                                default:
                                    continue;
                            }
                        case 18:
                            if (this.ams == null) {
                                this.ams = new C0080a();
                            }
                            lzVar.a(this.ams);
                            continue;
                        default:
                            if (!a(lzVar, nw)) {
                                break;
                            } else {
                                continue;
                            }
                    }
                }
                return this;
            }
        }

        public a() {
            ns();
        }

        public static a[] nr() {
            if (amp == null) {
                synchronized (md.amX) {
                    if (amp == null) {
                        amp = new a[0];
                    }
                }
            }
            return amp;
        }

        public void a(ma maVar) throws IOException {
            maVar.b(1, this.name);
            if (this.amq != null) {
                maVar.a(2, (mf) this.amq);
            }
            super.a(maVar);
        }

        /* access modifiers changed from: protected */
        public int c() {
            int c = super.c() + ma.h(1, this.name);
            return this.amq != null ? c + ma.b(2, (mf) this.amq) : c;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof a)) {
                return false;
            }
            a aVar = (a) o;
            if (this.name == null) {
                if (aVar.name != null) {
                    return false;
                }
            } else if (!this.name.equals(aVar.name)) {
                return false;
            }
            if (this.amq == null) {
                if (aVar.amq != null) {
                    return false;
                }
            } else if (!this.amq.equals(aVar.amq)) {
                return false;
            }
            if (this.amU == null || this.amU.isEmpty()) {
                return aVar.amU == null || aVar.amU.isEmpty();
            }
            return this.amU.equals(aVar.amU);
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((this.amq == null ? 0 : this.amq.hashCode()) + (((this.name == null ? 0 : this.name.hashCode()) + 527) * 31)) * 31;
            if (this.amU != null && !this.amU.isEmpty()) {
                i = this.amU.hashCode();
            }
            return hashCode + i;
        }

        public a ns() {
            this.name = "";
            this.amq = null;
            this.amU = null;
            this.amY = -1;
            return this;
        }

        /* renamed from: r */
        public a b(lz lzVar) throws IOException {
            while (true) {
                int nw = lzVar.nw();
                switch (nw) {
                    case 0:
                        break;
                    case 10:
                        this.name = lzVar.readString();
                        continue;
                    case 18:
                        if (this.amq == null) {
                            this.amq = new C0079a();
                        }
                        lzVar.a(this.amq);
                        continue;
                    default:
                        if (!a(lzVar, nw)) {
                            break;
                        } else {
                            continue;
                        }
                }
            }
            return this;
        }
    }

    public lx() {
        nq();
    }

    public static lx n(byte[] bArr) throws me {
        return (lx) mf.a(new lx(), bArr);
    }

    public void a(ma maVar) throws IOException {
        if (this.amo != null && this.amo.length > 0) {
            for (a aVar : this.amo) {
                if (aVar != null) {
                    maVar.a(1, (mf) aVar);
                }
            }
        }
        super.a(maVar);
    }

    /* access modifiers changed from: protected */
    public int c() {
        int c = super.c();
        if (this.amo != null && this.amo.length > 0) {
            for (a aVar : this.amo) {
                if (aVar != null) {
                    c += ma.b(1, (mf) aVar);
                }
            }
        }
        return c;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof lx)) {
            return false;
        }
        lx lxVar = (lx) o;
        if (!md.equals((Object[]) this.amo, (Object[]) lxVar.amo)) {
            return false;
        }
        if (this.amU == null || this.amU.isEmpty()) {
            return lxVar.amU == null || lxVar.amU.isEmpty();
        }
        return this.amU.equals(lxVar.amU);
    }

    public int hashCode() {
        return ((this.amU == null || this.amU.isEmpty()) ? 0 : this.amU.hashCode()) + ((md.hashCode((Object[]) this.amo) + 527) * 31);
    }

    public lx nq() {
        this.amo = a.nr();
        this.amU = null;
        this.amY = -1;
        return this;
    }

    /* renamed from: q */
    public lx b(lz lzVar) throws IOException {
        while (true) {
            int nw = lzVar.nw();
            switch (nw) {
                case 0:
                    break;
                case 10:
                    int b = mi.b(lzVar, 10);
                    int length = this.amo == null ? 0 : this.amo.length;
                    a[] aVarArr = new a[(b + length)];
                    if (length != 0) {
                        System.arraycopy(this.amo, 0, aVarArr, 0, length);
                    }
                    while (length < aVarArr.length - 1) {
                        aVarArr[length] = new a();
                        lzVar.a(aVarArr[length]);
                        lzVar.nw();
                        length++;
                    }
                    aVarArr[length] = new a();
                    lzVar.a(aVarArr[length]);
                    this.amo = aVarArr;
                    continue;
                default:
                    if (!a(lzVar, nw)) {
                        break;
                    } else {
                        continue;
                    }
            }
        }
        return this;
    }
}
