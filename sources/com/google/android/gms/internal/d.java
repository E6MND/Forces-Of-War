package com.google.android.gms.internal;

import com.googlecode.eyesfree.braille.display.BrailleInputEvent;
import java.io.IOException;

public interface d {

    public static final class a extends mb<a> {
        private static volatile a[] fM;
        public String fN;
        public a[] fO;
        public a[] fP;
        public a[] fQ;
        public String fR;
        public String fS;
        public long fT;
        public boolean fU;
        public a[] fV;
        public int[] fW;
        public boolean fX;
        public int type;

        public a() {
            s();
        }

        public static a[] r() {
            if (fM == null) {
                synchronized (md.amX) {
                    if (fM == null) {
                        fM = new a[0];
                    }
                }
            }
            return fM;
        }

        public void a(ma maVar) throws IOException {
            maVar.p(1, this.type);
            if (!this.fN.equals("")) {
                maVar.b(2, this.fN);
            }
            if (this.fO != null && this.fO.length > 0) {
                for (a aVar : this.fO) {
                    if (aVar != null) {
                        maVar.a(3, (mf) aVar);
                    }
                }
            }
            if (this.fP != null && this.fP.length > 0) {
                for (a aVar2 : this.fP) {
                    if (aVar2 != null) {
                        maVar.a(4, (mf) aVar2);
                    }
                }
            }
            if (this.fQ != null && this.fQ.length > 0) {
                for (a aVar3 : this.fQ) {
                    if (aVar3 != null) {
                        maVar.a(5, (mf) aVar3);
                    }
                }
            }
            if (!this.fR.equals("")) {
                maVar.b(6, this.fR);
            }
            if (!this.fS.equals("")) {
                maVar.b(7, this.fS);
            }
            if (this.fT != 0) {
                maVar.b(8, this.fT);
            }
            if (this.fX) {
                maVar.a(9, this.fX);
            }
            if (this.fW != null && this.fW.length > 0) {
                for (int p : this.fW) {
                    maVar.p(10, p);
                }
            }
            if (this.fV != null && this.fV.length > 0) {
                for (a aVar4 : this.fV) {
                    if (aVar4 != null) {
                        maVar.a(11, (mf) aVar4);
                    }
                }
            }
            if (this.fU) {
                maVar.a(12, this.fU);
            }
            super.a(maVar);
        }

        /* access modifiers changed from: protected */
        public int c() {
            int c = super.c() + ma.r(1, this.type);
            if (!this.fN.equals("")) {
                c += ma.h(2, this.fN);
            }
            if (this.fO != null && this.fO.length > 0) {
                int i = c;
                for (a aVar : this.fO) {
                    if (aVar != null) {
                        i += ma.b(3, (mf) aVar);
                    }
                }
                c = i;
            }
            if (this.fP != null && this.fP.length > 0) {
                int i2 = c;
                for (a aVar2 : this.fP) {
                    if (aVar2 != null) {
                        i2 += ma.b(4, (mf) aVar2);
                    }
                }
                c = i2;
            }
            if (this.fQ != null && this.fQ.length > 0) {
                int i3 = c;
                for (a aVar3 : this.fQ) {
                    if (aVar3 != null) {
                        i3 += ma.b(5, (mf) aVar3);
                    }
                }
                c = i3;
            }
            if (!this.fR.equals("")) {
                c += ma.h(6, this.fR);
            }
            if (!this.fS.equals("")) {
                c += ma.h(7, this.fS);
            }
            if (this.fT != 0) {
                c += ma.d(8, this.fT);
            }
            if (this.fX) {
                c += ma.b(9, this.fX);
            }
            if (this.fW != null && this.fW.length > 0) {
                int i4 = 0;
                for (int eE : this.fW) {
                    i4 += ma.eE(eE);
                }
                c = c + i4 + (this.fW.length * 1);
            }
            if (this.fV != null && this.fV.length > 0) {
                for (a aVar4 : this.fV) {
                    if (aVar4 != null) {
                        c += ma.b(11, (mf) aVar4);
                    }
                }
            }
            return this.fU ? c + ma.b(12, this.fU) : c;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof a)) {
                return false;
            }
            a aVar = (a) o;
            if (this.type != aVar.type) {
                return false;
            }
            if (this.fN == null) {
                if (aVar.fN != null) {
                    return false;
                }
            } else if (!this.fN.equals(aVar.fN)) {
                return false;
            }
            if (!md.equals((Object[]) this.fO, (Object[]) aVar.fO) || !md.equals((Object[]) this.fP, (Object[]) aVar.fP) || !md.equals((Object[]) this.fQ, (Object[]) aVar.fQ)) {
                return false;
            }
            if (this.fR == null) {
                if (aVar.fR != null) {
                    return false;
                }
            } else if (!this.fR.equals(aVar.fR)) {
                return false;
            }
            if (this.fS == null) {
                if (aVar.fS != null) {
                    return false;
                }
            } else if (!this.fS.equals(aVar.fS)) {
                return false;
            }
            if (this.fT != aVar.fT || this.fU != aVar.fU || !md.equals((Object[]) this.fV, (Object[]) aVar.fV) || !md.equals(this.fW, aVar.fW) || this.fX != aVar.fX) {
                return false;
            }
            if (this.amU == null || this.amU.isEmpty()) {
                return aVar.amU == null || aVar.amU.isEmpty();
            }
            return this.amU.equals(aVar.amU);
        }

        public int hashCode() {
            int i = 1231;
            int i2 = 0;
            int hashCode = ((((((this.fU ? 1231 : 1237) + (((((this.fS == null ? 0 : this.fS.hashCode()) + (((this.fR == null ? 0 : this.fR.hashCode()) + (((((((((this.fN == null ? 0 : this.fN.hashCode()) + ((this.type + 527) * 31)) * 31) + md.hashCode((Object[]) this.fO)) * 31) + md.hashCode((Object[]) this.fP)) * 31) + md.hashCode((Object[]) this.fQ)) * 31)) * 31)) * 31) + ((int) (this.fT ^ (this.fT >>> 32)))) * 31)) * 31) + md.hashCode((Object[]) this.fV)) * 31) + md.hashCode(this.fW)) * 31;
            if (!this.fX) {
                i = 1237;
            }
            int i3 = (hashCode + i) * 31;
            if (this.amU != null && !this.amU.isEmpty()) {
                i2 = this.amU.hashCode();
            }
            return i3 + i2;
        }

        /* renamed from: l */
        public a b(lz lzVar) throws IOException {
            int i;
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
                                this.type = nz;
                                break;
                            default:
                                continue;
                        }
                    case 18:
                        this.fN = lzVar.readString();
                        continue;
                    case 26:
                        int b = mi.b(lzVar, 26);
                        int length = this.fO == null ? 0 : this.fO.length;
                        a[] aVarArr = new a[(b + length)];
                        if (length != 0) {
                            System.arraycopy(this.fO, 0, aVarArr, 0, length);
                        }
                        while (length < aVarArr.length - 1) {
                            aVarArr[length] = new a();
                            lzVar.a(aVarArr[length]);
                            lzVar.nw();
                            length++;
                        }
                        aVarArr[length] = new a();
                        lzVar.a(aVarArr[length]);
                        this.fO = aVarArr;
                        continue;
                    case 34:
                        int b2 = mi.b(lzVar, 34);
                        int length2 = this.fP == null ? 0 : this.fP.length;
                        a[] aVarArr2 = new a[(b2 + length2)];
                        if (length2 != 0) {
                            System.arraycopy(this.fP, 0, aVarArr2, 0, length2);
                        }
                        while (length2 < aVarArr2.length - 1) {
                            aVarArr2[length2] = new a();
                            lzVar.a(aVarArr2[length2]);
                            lzVar.nw();
                            length2++;
                        }
                        aVarArr2[length2] = new a();
                        lzVar.a(aVarArr2[length2]);
                        this.fP = aVarArr2;
                        continue;
                    case BrailleInputEvent.CMD_SELECTION_SELECT_ALL /*42*/:
                        int b3 = mi.b(lzVar, 42);
                        int length3 = this.fQ == null ? 0 : this.fQ.length;
                        a[] aVarArr3 = new a[(b3 + length3)];
                        if (length3 != 0) {
                            System.arraycopy(this.fQ, 0, aVarArr3, 0, length3);
                        }
                        while (length3 < aVarArr3.length - 1) {
                            aVarArr3[length3] = new a();
                            lzVar.a(aVarArr3[length3]);
                            lzVar.nw();
                            length3++;
                        }
                        aVarArr3[length3] = new a();
                        lzVar.a(aVarArr3[length3]);
                        this.fQ = aVarArr3;
                        continue;
                    case 50:
                        this.fR = lzVar.readString();
                        continue;
                    case 58:
                        this.fS = lzVar.readString();
                        continue;
                    case 64:
                        this.fT = lzVar.ny();
                        continue;
                    case BrailleInputEvent.CMD_KEY_FORWARD_DEL /*72*/:
                        this.fX = lzVar.nA();
                        continue;
                    case 80:
                        int b4 = mi.b(lzVar, 80);
                        int[] iArr = new int[b4];
                        int i2 = 0;
                        int i3 = 0;
                        while (i2 < b4) {
                            if (i2 != 0) {
                                lzVar.nw();
                            }
                            int nz2 = lzVar.nz();
                            switch (nz2) {
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
                                case 16:
                                case 17:
                                    i = i3 + 1;
                                    iArr[i3] = nz2;
                                    break;
                                default:
                                    i = i3;
                                    break;
                            }
                            i2++;
                            i3 = i;
                        }
                        if (i3 != 0) {
                            int length4 = this.fW == null ? 0 : this.fW.length;
                            if (length4 != 0 || i3 != iArr.length) {
                                int[] iArr2 = new int[(length4 + i3)];
                                if (length4 != 0) {
                                    System.arraycopy(this.fW, 0, iArr2, 0, length4);
                                }
                                System.arraycopy(iArr, 0, iArr2, length4, i3);
                                this.fW = iArr2;
                                break;
                            } else {
                                this.fW = iArr;
                                break;
                            }
                        } else {
                            continue;
                        }
                    case 82:
                        int ex = lzVar.ex(lzVar.nD());
                        int position = lzVar.getPosition();
                        int i4 = 0;
                        while (lzVar.nI() > 0) {
                            switch (lzVar.nz()) {
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
                                case 16:
                                case 17:
                                    i4++;
                                    break;
                            }
                        }
                        if (i4 != 0) {
                            lzVar.ez(position);
                            int length5 = this.fW == null ? 0 : this.fW.length;
                            int[] iArr3 = new int[(i4 + length5)];
                            if (length5 != 0) {
                                System.arraycopy(this.fW, 0, iArr3, 0, length5);
                            }
                            while (lzVar.nI() > 0) {
                                int nz3 = lzVar.nz();
                                switch (nz3) {
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
                                    case 16:
                                    case 17:
                                        iArr3[length5] = nz3;
                                        length5++;
                                        break;
                                }
                            }
                            this.fW = iArr3;
                        }
                        lzVar.ey(ex);
                        continue;
                    case 90:
                        int b5 = mi.b(lzVar, 90);
                        int length6 = this.fV == null ? 0 : this.fV.length;
                        a[] aVarArr4 = new a[(b5 + length6)];
                        if (length6 != 0) {
                            System.arraycopy(this.fV, 0, aVarArr4, 0, length6);
                        }
                        while (length6 < aVarArr4.length - 1) {
                            aVarArr4[length6] = new a();
                            lzVar.a(aVarArr4[length6]);
                            lzVar.nw();
                            length6++;
                        }
                        aVarArr4[length6] = new a();
                        lzVar.a(aVarArr4[length6]);
                        this.fV = aVarArr4;
                        continue;
                    case 96:
                        this.fU = lzVar.nA();
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

        public a s() {
            this.type = 1;
            this.fN = "";
            this.fO = r();
            this.fP = r();
            this.fQ = r();
            this.fR = "";
            this.fS = "";
            this.fT = 0;
            this.fU = false;
            this.fV = r();
            this.fW = mi.ana;
            this.fX = false;
            this.amU = null;
            this.amY = -1;
            return this;
        }
    }
}
