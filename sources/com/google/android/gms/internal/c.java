package com.google.android.gms.internal;

import android.support.v4.media.TransportMediator;
import com.facebook.AppEventsConstants;
import com.google.android.gms.internal.d;
import com.googlecode.eyesfree.braille.display.BrailleInputEvent;
import java.io.IOException;

public interface c {

    public static final class a extends mb<a> {
        public int eE;
        public int eF;
        public int level;

        public a() {
            b();
        }

        /* renamed from: a */
        public a b(lz lzVar) throws IOException {
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
                                this.level = nz;
                                break;
                            default:
                                continue;
                        }
                    case 16:
                        this.eE = lzVar.nz();
                        continue;
                    case 24:
                        this.eF = lzVar.nz();
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

        public void a(ma maVar) throws IOException {
            if (this.level != 1) {
                maVar.p(1, this.level);
            }
            if (this.eE != 0) {
                maVar.p(2, this.eE);
            }
            if (this.eF != 0) {
                maVar.p(3, this.eF);
            }
            super.a(maVar);
        }

        public a b() {
            this.level = 1;
            this.eE = 0;
            this.eF = 0;
            this.amU = null;
            this.amY = -1;
            return this;
        }

        /* access modifiers changed from: protected */
        public int c() {
            int c = super.c();
            if (this.level != 1) {
                c += ma.r(1, this.level);
            }
            if (this.eE != 0) {
                c += ma.r(2, this.eE);
            }
            return this.eF != 0 ? c + ma.r(3, this.eF) : c;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof a)) {
                return false;
            }
            a aVar = (a) o;
            if (this.level != aVar.level || this.eE != aVar.eE || this.eF != aVar.eF) {
                return false;
            }
            if (this.amU == null || this.amU.isEmpty()) {
                return aVar.amU == null || aVar.amU.isEmpty();
            }
            return this.amU.equals(aVar.amU);
        }

        public int hashCode() {
            return ((this.amU == null || this.amU.isEmpty()) ? 0 : this.amU.hashCode()) + ((((((this.level + 527) * 31) + this.eE) * 31) + this.eF) * 31);
        }
    }

    public static final class b extends mb<b> {
        private static volatile b[] eG;
        public int[] eH;
        public int eI;
        public boolean eJ;
        public boolean eK;
        public int name;

        public b() {
            e();
        }

        public static b[] d() {
            if (eG == null) {
                synchronized (md.amX) {
                    if (eG == null) {
                        eG = new b[0];
                    }
                }
            }
            return eG;
        }

        public void a(ma maVar) throws IOException {
            if (this.eK) {
                maVar.a(1, this.eK);
            }
            maVar.p(2, this.eI);
            if (this.eH != null && this.eH.length > 0) {
                for (int p : this.eH) {
                    maVar.p(3, p);
                }
            }
            if (this.name != 0) {
                maVar.p(4, this.name);
            }
            if (this.eJ) {
                maVar.a(6, this.eJ);
            }
            super.a(maVar);
        }

        /* access modifiers changed from: protected */
        public int c() {
            int i;
            int i2 = 0;
            int c = super.c();
            if (this.eK) {
                c += ma.b(1, this.eK);
            }
            int r = ma.r(2, this.eI) + c;
            if (this.eH == null || this.eH.length <= 0) {
                i = r;
            } else {
                for (int eE : this.eH) {
                    i2 += ma.eE(eE);
                }
                i = r + i2 + (this.eH.length * 1);
            }
            if (this.name != 0) {
                i += ma.r(4, this.name);
            }
            return this.eJ ? i + ma.b(6, this.eJ) : i;
        }

        /* renamed from: c */
        public b b(lz lzVar) throws IOException {
            while (true) {
                int nw = lzVar.nw();
                switch (nw) {
                    case 0:
                        break;
                    case 8:
                        this.eK = lzVar.nA();
                        continue;
                    case 16:
                        this.eI = lzVar.nz();
                        continue;
                    case 24:
                        int b = mi.b(lzVar, 24);
                        int length = this.eH == null ? 0 : this.eH.length;
                        int[] iArr = new int[(b + length)];
                        if (length != 0) {
                            System.arraycopy(this.eH, 0, iArr, 0, length);
                        }
                        while (length < iArr.length - 1) {
                            iArr[length] = lzVar.nz();
                            lzVar.nw();
                            length++;
                        }
                        iArr[length] = lzVar.nz();
                        this.eH = iArr;
                        continue;
                    case 26:
                        int ex = lzVar.ex(lzVar.nD());
                        int position = lzVar.getPosition();
                        int i = 0;
                        while (lzVar.nI() > 0) {
                            lzVar.nz();
                            i++;
                        }
                        lzVar.ez(position);
                        int length2 = this.eH == null ? 0 : this.eH.length;
                        int[] iArr2 = new int[(i + length2)];
                        if (length2 != 0) {
                            System.arraycopy(this.eH, 0, iArr2, 0, length2);
                        }
                        while (length2 < iArr2.length) {
                            iArr2[length2] = lzVar.nz();
                            length2++;
                        }
                        this.eH = iArr2;
                        lzVar.ey(ex);
                        continue;
                    case 32:
                        this.name = lzVar.nz();
                        continue;
                    case 48:
                        this.eJ = lzVar.nA();
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

        public b e() {
            this.eH = mi.ana;
            this.eI = 0;
            this.name = 0;
            this.eJ = false;
            this.eK = false;
            this.amU = null;
            this.amY = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof b)) {
                return false;
            }
            b bVar = (b) o;
            if (!md.equals(this.eH, bVar.eH) || this.eI != bVar.eI || this.name != bVar.name || this.eJ != bVar.eJ || this.eK != bVar.eK) {
                return false;
            }
            if (this.amU == null || this.amU.isEmpty()) {
                return bVar.amU == null || bVar.amU.isEmpty();
            }
            return this.amU.equals(bVar.amU);
        }

        public int hashCode() {
            int i = 1231;
            int hashCode = ((this.eJ ? 1231 : 1237) + ((((((md.hashCode(this.eH) + 527) * 31) + this.eI) * 31) + this.name) * 31)) * 31;
            if (!this.eK) {
                i = 1237;
            }
            return ((this.amU == null || this.amU.isEmpty()) ? 0 : this.amU.hashCode()) + ((hashCode + i) * 31);
        }
    }

    /* renamed from: com.google.android.gms.internal.c$c  reason: collision with other inner class name */
    public static final class C0041c extends mb<C0041c> {
        private static volatile C0041c[] eL;
        public String eM;
        public long eN;
        public long eO;
        public boolean eP;
        public long eQ;

        public C0041c() {
            g();
        }

        public static C0041c[] f() {
            if (eL == null) {
                synchronized (md.amX) {
                    if (eL == null) {
                        eL = new C0041c[0];
                    }
                }
            }
            return eL;
        }

        public void a(ma maVar) throws IOException {
            if (!this.eM.equals("")) {
                maVar.b(1, this.eM);
            }
            if (this.eN != 0) {
                maVar.b(2, this.eN);
            }
            if (this.eO != 2147483647L) {
                maVar.b(3, this.eO);
            }
            if (this.eP) {
                maVar.a(4, this.eP);
            }
            if (this.eQ != 0) {
                maVar.b(5, this.eQ);
            }
            super.a(maVar);
        }

        /* access modifiers changed from: protected */
        public int c() {
            int c = super.c();
            if (!this.eM.equals("")) {
                c += ma.h(1, this.eM);
            }
            if (this.eN != 0) {
                c += ma.d(2, this.eN);
            }
            if (this.eO != 2147483647L) {
                c += ma.d(3, this.eO);
            }
            if (this.eP) {
                c += ma.b(4, this.eP);
            }
            return this.eQ != 0 ? c + ma.d(5, this.eQ) : c;
        }

        /* renamed from: d */
        public C0041c b(lz lzVar) throws IOException {
            while (true) {
                int nw = lzVar.nw();
                switch (nw) {
                    case 0:
                        break;
                    case 10:
                        this.eM = lzVar.readString();
                        continue;
                    case 16:
                        this.eN = lzVar.ny();
                        continue;
                    case 24:
                        this.eO = lzVar.ny();
                        continue;
                    case 32:
                        this.eP = lzVar.nA();
                        continue;
                    case BrailleInputEvent.CMD_SELECTION_START /*40*/:
                        this.eQ = lzVar.ny();
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

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof C0041c)) {
                return false;
            }
            C0041c cVar = (C0041c) o;
            if (this.eM == null) {
                if (cVar.eM != null) {
                    return false;
                }
            } else if (!this.eM.equals(cVar.eM)) {
                return false;
            }
            if (this.eN != cVar.eN || this.eO != cVar.eO || this.eP != cVar.eP || this.eQ != cVar.eQ) {
                return false;
            }
            if (this.amU == null || this.amU.isEmpty()) {
                return cVar.amU == null || cVar.amU.isEmpty();
            }
            return this.amU.equals(cVar.amU);
        }

        public C0041c g() {
            this.eM = "";
            this.eN = 0;
            this.eO = 2147483647L;
            this.eP = false;
            this.eQ = 0;
            this.amU = null;
            this.amY = -1;
            return this;
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((((this.eP ? 1231 : 1237) + (((((((this.eM == null ? 0 : this.eM.hashCode()) + 527) * 31) + ((int) (this.eN ^ (this.eN >>> 32)))) * 31) + ((int) (this.eO ^ (this.eO >>> 32)))) * 31)) * 31) + ((int) (this.eQ ^ (this.eQ >>> 32)))) * 31;
            if (this.amU != null && !this.amU.isEmpty()) {
                i = this.amU.hashCode();
            }
            return hashCode + i;
        }
    }

    public static final class d extends mb<d> {
        public d.a[] eR;
        public d.a[] eS;
        public C0041c[] eT;

        public d() {
            h();
        }

        public void a(ma maVar) throws IOException {
            if (this.eR != null && this.eR.length > 0) {
                for (d.a aVar : this.eR) {
                    if (aVar != null) {
                        maVar.a(1, (mf) aVar);
                    }
                }
            }
            if (this.eS != null && this.eS.length > 0) {
                for (d.a aVar2 : this.eS) {
                    if (aVar2 != null) {
                        maVar.a(2, (mf) aVar2);
                    }
                }
            }
            if (this.eT != null && this.eT.length > 0) {
                for (C0041c cVar : this.eT) {
                    if (cVar != null) {
                        maVar.a(3, (mf) cVar);
                    }
                }
            }
            super.a(maVar);
        }

        /* access modifiers changed from: protected */
        public int c() {
            int c = super.c();
            if (this.eR != null && this.eR.length > 0) {
                int i = c;
                for (d.a aVar : this.eR) {
                    if (aVar != null) {
                        i += ma.b(1, (mf) aVar);
                    }
                }
                c = i;
            }
            if (this.eS != null && this.eS.length > 0) {
                int i2 = c;
                for (d.a aVar2 : this.eS) {
                    if (aVar2 != null) {
                        i2 += ma.b(2, (mf) aVar2);
                    }
                }
                c = i2;
            }
            if (this.eT != null && this.eT.length > 0) {
                for (C0041c cVar : this.eT) {
                    if (cVar != null) {
                        c += ma.b(3, (mf) cVar);
                    }
                }
            }
            return c;
        }

        /* renamed from: e */
        public d b(lz lzVar) throws IOException {
            while (true) {
                int nw = lzVar.nw();
                switch (nw) {
                    case 0:
                        break;
                    case 10:
                        int b = mi.b(lzVar, 10);
                        int length = this.eR == null ? 0 : this.eR.length;
                        d.a[] aVarArr = new d.a[(b + length)];
                        if (length != 0) {
                            System.arraycopy(this.eR, 0, aVarArr, 0, length);
                        }
                        while (length < aVarArr.length - 1) {
                            aVarArr[length] = new d.a();
                            lzVar.a(aVarArr[length]);
                            lzVar.nw();
                            length++;
                        }
                        aVarArr[length] = new d.a();
                        lzVar.a(aVarArr[length]);
                        this.eR = aVarArr;
                        continue;
                    case 18:
                        int b2 = mi.b(lzVar, 18);
                        int length2 = this.eS == null ? 0 : this.eS.length;
                        d.a[] aVarArr2 = new d.a[(b2 + length2)];
                        if (length2 != 0) {
                            System.arraycopy(this.eS, 0, aVarArr2, 0, length2);
                        }
                        while (length2 < aVarArr2.length - 1) {
                            aVarArr2[length2] = new d.a();
                            lzVar.a(aVarArr2[length2]);
                            lzVar.nw();
                            length2++;
                        }
                        aVarArr2[length2] = new d.a();
                        lzVar.a(aVarArr2[length2]);
                        this.eS = aVarArr2;
                        continue;
                    case 26:
                        int b3 = mi.b(lzVar, 26);
                        int length3 = this.eT == null ? 0 : this.eT.length;
                        C0041c[] cVarArr = new C0041c[(b3 + length3)];
                        if (length3 != 0) {
                            System.arraycopy(this.eT, 0, cVarArr, 0, length3);
                        }
                        while (length3 < cVarArr.length - 1) {
                            cVarArr[length3] = new C0041c();
                            lzVar.a(cVarArr[length3]);
                            lzVar.nw();
                            length3++;
                        }
                        cVarArr[length3] = new C0041c();
                        lzVar.a(cVarArr[length3]);
                        this.eT = cVarArr;
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

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof d)) {
                return false;
            }
            d dVar = (d) o;
            if (!md.equals((Object[]) this.eR, (Object[]) dVar.eR) || !md.equals((Object[]) this.eS, (Object[]) dVar.eS) || !md.equals((Object[]) this.eT, (Object[]) dVar.eT)) {
                return false;
            }
            if (this.amU == null || this.amU.isEmpty()) {
                return dVar.amU == null || dVar.amU.isEmpty();
            }
            return this.amU.equals(dVar.amU);
        }

        public d h() {
            this.eR = d.a.r();
            this.eS = d.a.r();
            this.eT = C0041c.f();
            this.amU = null;
            this.amY = -1;
            return this;
        }

        public int hashCode() {
            return ((this.amU == null || this.amU.isEmpty()) ? 0 : this.amU.hashCode()) + ((((((md.hashCode((Object[]) this.eR) + 527) * 31) + md.hashCode((Object[]) this.eS)) * 31) + md.hashCode((Object[]) this.eT)) * 31);
        }
    }

    public static final class e extends mb<e> {
        private static volatile e[] eU;
        public int key;
        public int value;

        public e() {
            j();
        }

        public static e[] i() {
            if (eU == null) {
                synchronized (md.amX) {
                    if (eU == null) {
                        eU = new e[0];
                    }
                }
            }
            return eU;
        }

        public void a(ma maVar) throws IOException {
            maVar.p(1, this.key);
            maVar.p(2, this.value);
            super.a(maVar);
        }

        /* access modifiers changed from: protected */
        public int c() {
            return super.c() + ma.r(1, this.key) + ma.r(2, this.value);
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof e)) {
                return false;
            }
            e eVar = (e) o;
            if (this.key != eVar.key || this.value != eVar.value) {
                return false;
            }
            if (this.amU == null || this.amU.isEmpty()) {
                return eVar.amU == null || eVar.amU.isEmpty();
            }
            return this.amU.equals(eVar.amU);
        }

        /* renamed from: f */
        public e b(lz lzVar) throws IOException {
            while (true) {
                int nw = lzVar.nw();
                switch (nw) {
                    case 0:
                        break;
                    case 8:
                        this.key = lzVar.nz();
                        continue;
                    case 16:
                        this.value = lzVar.nz();
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

        public int hashCode() {
            return ((this.amU == null || this.amU.isEmpty()) ? 0 : this.amU.hashCode()) + ((((this.key + 527) * 31) + this.value) * 31);
        }

        public e j() {
            this.key = 0;
            this.value = 0;
            this.amU = null;
            this.amY = -1;
            return this;
        }
    }

    public static final class f extends mb<f> {
        public String[] eV;
        public String[] eW;
        public d.a[] eX;
        public e[] eY;
        public b[] eZ;
        public b[] fa;
        public b[] fb;
        public g[] fc;
        public String fd;
        public String fe;
        public String ff;
        public String fg;
        public a fh;
        public float fi;
        public boolean fj;
        public String[] fk;
        public int fl;

        public f() {
            k();
        }

        public static f a(byte[] bArr) throws me {
            return (f) mf.a(new f(), bArr);
        }

        public void a(ma maVar) throws IOException {
            if (this.eW != null && this.eW.length > 0) {
                for (String str : this.eW) {
                    if (str != null) {
                        maVar.b(1, str);
                    }
                }
            }
            if (this.eX != null && this.eX.length > 0) {
                for (d.a aVar : this.eX) {
                    if (aVar != null) {
                        maVar.a(2, (mf) aVar);
                    }
                }
            }
            if (this.eY != null && this.eY.length > 0) {
                for (e eVar : this.eY) {
                    if (eVar != null) {
                        maVar.a(3, (mf) eVar);
                    }
                }
            }
            if (this.eZ != null && this.eZ.length > 0) {
                for (b bVar : this.eZ) {
                    if (bVar != null) {
                        maVar.a(4, (mf) bVar);
                    }
                }
            }
            if (this.fa != null && this.fa.length > 0) {
                for (b bVar2 : this.fa) {
                    if (bVar2 != null) {
                        maVar.a(5, (mf) bVar2);
                    }
                }
            }
            if (this.fb != null && this.fb.length > 0) {
                for (b bVar3 : this.fb) {
                    if (bVar3 != null) {
                        maVar.a(6, (mf) bVar3);
                    }
                }
            }
            if (this.fc != null && this.fc.length > 0) {
                for (g gVar : this.fc) {
                    if (gVar != null) {
                        maVar.a(7, (mf) gVar);
                    }
                }
            }
            if (!this.fd.equals("")) {
                maVar.b(9, this.fd);
            }
            if (!this.fe.equals("")) {
                maVar.b(10, this.fe);
            }
            if (!this.ff.equals(AppEventsConstants.EVENT_PARAM_VALUE_NO)) {
                maVar.b(12, this.ff);
            }
            if (!this.fg.equals("")) {
                maVar.b(13, this.fg);
            }
            if (this.fh != null) {
                maVar.a(14, (mf) this.fh);
            }
            if (Float.floatToIntBits(this.fi) != Float.floatToIntBits(0.0f)) {
                maVar.b(15, this.fi);
            }
            if (this.fk != null && this.fk.length > 0) {
                for (String str2 : this.fk) {
                    if (str2 != null) {
                        maVar.b(16, str2);
                    }
                }
            }
            if (this.fl != 0) {
                maVar.p(17, this.fl);
            }
            if (this.fj) {
                maVar.a(18, this.fj);
            }
            if (this.eV != null && this.eV.length > 0) {
                for (String str3 : this.eV) {
                    if (str3 != null) {
                        maVar.b(19, str3);
                    }
                }
            }
            super.a(maVar);
        }

        /* access modifiers changed from: protected */
        public int c() {
            int i;
            int c = super.c();
            if (this.eW == null || this.eW.length <= 0) {
                i = c;
            } else {
                int i2 = 0;
                int i3 = 0;
                for (String str : this.eW) {
                    if (str != null) {
                        i3++;
                        i2 += ma.cz(str);
                    }
                }
                i = c + i2 + (i3 * 1);
            }
            if (this.eX != null && this.eX.length > 0) {
                int i4 = i;
                for (d.a aVar : this.eX) {
                    if (aVar != null) {
                        i4 += ma.b(2, (mf) aVar);
                    }
                }
                i = i4;
            }
            if (this.eY != null && this.eY.length > 0) {
                int i5 = i;
                for (e eVar : this.eY) {
                    if (eVar != null) {
                        i5 += ma.b(3, (mf) eVar);
                    }
                }
                i = i5;
            }
            if (this.eZ != null && this.eZ.length > 0) {
                int i6 = i;
                for (b bVar : this.eZ) {
                    if (bVar != null) {
                        i6 += ma.b(4, (mf) bVar);
                    }
                }
                i = i6;
            }
            if (this.fa != null && this.fa.length > 0) {
                int i7 = i;
                for (b bVar2 : this.fa) {
                    if (bVar2 != null) {
                        i7 += ma.b(5, (mf) bVar2);
                    }
                }
                i = i7;
            }
            if (this.fb != null && this.fb.length > 0) {
                int i8 = i;
                for (b bVar3 : this.fb) {
                    if (bVar3 != null) {
                        i8 += ma.b(6, (mf) bVar3);
                    }
                }
                i = i8;
            }
            if (this.fc != null && this.fc.length > 0) {
                int i9 = i;
                for (g gVar : this.fc) {
                    if (gVar != null) {
                        i9 += ma.b(7, (mf) gVar);
                    }
                }
                i = i9;
            }
            if (!this.fd.equals("")) {
                i += ma.h(9, this.fd);
            }
            if (!this.fe.equals("")) {
                i += ma.h(10, this.fe);
            }
            if (!this.ff.equals(AppEventsConstants.EVENT_PARAM_VALUE_NO)) {
                i += ma.h(12, this.ff);
            }
            if (!this.fg.equals("")) {
                i += ma.h(13, this.fg);
            }
            if (this.fh != null) {
                i += ma.b(14, (mf) this.fh);
            }
            if (Float.floatToIntBits(this.fi) != Float.floatToIntBits(0.0f)) {
                i += ma.c(15, this.fi);
            }
            if (this.fk != null && this.fk.length > 0) {
                int i10 = 0;
                int i11 = 0;
                for (String str2 : this.fk) {
                    if (str2 != null) {
                        i11++;
                        i10 += ma.cz(str2);
                    }
                }
                i = i + i10 + (i11 * 2);
            }
            if (this.fl != 0) {
                i += ma.r(17, this.fl);
            }
            if (this.fj) {
                i += ma.b(18, this.fj);
            }
            if (this.eV == null || this.eV.length <= 0) {
                return i;
            }
            int i12 = 0;
            int i13 = 0;
            for (String str3 : this.eV) {
                if (str3 != null) {
                    i13++;
                    i12 += ma.cz(str3);
                }
            }
            return i + i12 + (i13 * 2);
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof f)) {
                return false;
            }
            f fVar = (f) o;
            if (!md.equals((Object[]) this.eV, (Object[]) fVar.eV) || !md.equals((Object[]) this.eW, (Object[]) fVar.eW) || !md.equals((Object[]) this.eX, (Object[]) fVar.eX) || !md.equals((Object[]) this.eY, (Object[]) fVar.eY) || !md.equals((Object[]) this.eZ, (Object[]) fVar.eZ) || !md.equals((Object[]) this.fa, (Object[]) fVar.fa) || !md.equals((Object[]) this.fb, (Object[]) fVar.fb) || !md.equals((Object[]) this.fc, (Object[]) fVar.fc)) {
                return false;
            }
            if (this.fd == null) {
                if (fVar.fd != null) {
                    return false;
                }
            } else if (!this.fd.equals(fVar.fd)) {
                return false;
            }
            if (this.fe == null) {
                if (fVar.fe != null) {
                    return false;
                }
            } else if (!this.fe.equals(fVar.fe)) {
                return false;
            }
            if (this.ff == null) {
                if (fVar.ff != null) {
                    return false;
                }
            } else if (!this.ff.equals(fVar.ff)) {
                return false;
            }
            if (this.fg == null) {
                if (fVar.fg != null) {
                    return false;
                }
            } else if (!this.fg.equals(fVar.fg)) {
                return false;
            }
            if (this.fh == null) {
                if (fVar.fh != null) {
                    return false;
                }
            } else if (!this.fh.equals(fVar.fh)) {
                return false;
            }
            if (Float.floatToIntBits(this.fi) != Float.floatToIntBits(fVar.fi) || this.fj != fVar.fj || !md.equals((Object[]) this.fk, (Object[]) fVar.fk) || this.fl != fVar.fl) {
                return false;
            }
            if (this.amU == null || this.amU.isEmpty()) {
                return fVar.amU == null || fVar.amU.isEmpty();
            }
            return this.amU.equals(fVar.amU);
        }

        /* renamed from: g */
        public f b(lz lzVar) throws IOException {
            while (true) {
                int nw = lzVar.nw();
                switch (nw) {
                    case 0:
                        break;
                    case 10:
                        int b = mi.b(lzVar, 10);
                        int length = this.eW == null ? 0 : this.eW.length;
                        String[] strArr = new String[(b + length)];
                        if (length != 0) {
                            System.arraycopy(this.eW, 0, strArr, 0, length);
                        }
                        while (length < strArr.length - 1) {
                            strArr[length] = lzVar.readString();
                            lzVar.nw();
                            length++;
                        }
                        strArr[length] = lzVar.readString();
                        this.eW = strArr;
                        continue;
                    case 18:
                        int b2 = mi.b(lzVar, 18);
                        int length2 = this.eX == null ? 0 : this.eX.length;
                        d.a[] aVarArr = new d.a[(b2 + length2)];
                        if (length2 != 0) {
                            System.arraycopy(this.eX, 0, aVarArr, 0, length2);
                        }
                        while (length2 < aVarArr.length - 1) {
                            aVarArr[length2] = new d.a();
                            lzVar.a(aVarArr[length2]);
                            lzVar.nw();
                            length2++;
                        }
                        aVarArr[length2] = new d.a();
                        lzVar.a(aVarArr[length2]);
                        this.eX = aVarArr;
                        continue;
                    case 26:
                        int b3 = mi.b(lzVar, 26);
                        int length3 = this.eY == null ? 0 : this.eY.length;
                        e[] eVarArr = new e[(b3 + length3)];
                        if (length3 != 0) {
                            System.arraycopy(this.eY, 0, eVarArr, 0, length3);
                        }
                        while (length3 < eVarArr.length - 1) {
                            eVarArr[length3] = new e();
                            lzVar.a(eVarArr[length3]);
                            lzVar.nw();
                            length3++;
                        }
                        eVarArr[length3] = new e();
                        lzVar.a(eVarArr[length3]);
                        this.eY = eVarArr;
                        continue;
                    case 34:
                        int b4 = mi.b(lzVar, 34);
                        int length4 = this.eZ == null ? 0 : this.eZ.length;
                        b[] bVarArr = new b[(b4 + length4)];
                        if (length4 != 0) {
                            System.arraycopy(this.eZ, 0, bVarArr, 0, length4);
                        }
                        while (length4 < bVarArr.length - 1) {
                            bVarArr[length4] = new b();
                            lzVar.a(bVarArr[length4]);
                            lzVar.nw();
                            length4++;
                        }
                        bVarArr[length4] = new b();
                        lzVar.a(bVarArr[length4]);
                        this.eZ = bVarArr;
                        continue;
                    case BrailleInputEvent.CMD_SELECTION_SELECT_ALL /*42*/:
                        int b5 = mi.b(lzVar, 42);
                        int length5 = this.fa == null ? 0 : this.fa.length;
                        b[] bVarArr2 = new b[(b5 + length5)];
                        if (length5 != 0) {
                            System.arraycopy(this.fa, 0, bVarArr2, 0, length5);
                        }
                        while (length5 < bVarArr2.length - 1) {
                            bVarArr2[length5] = new b();
                            lzVar.a(bVarArr2[length5]);
                            lzVar.nw();
                            length5++;
                        }
                        bVarArr2[length5] = new b();
                        lzVar.a(bVarArr2[length5]);
                        this.fa = bVarArr2;
                        continue;
                    case 50:
                        int b6 = mi.b(lzVar, 50);
                        int length6 = this.fb == null ? 0 : this.fb.length;
                        b[] bVarArr3 = new b[(b6 + length6)];
                        if (length6 != 0) {
                            System.arraycopy(this.fb, 0, bVarArr3, 0, length6);
                        }
                        while (length6 < bVarArr3.length - 1) {
                            bVarArr3[length6] = new b();
                            lzVar.a(bVarArr3[length6]);
                            lzVar.nw();
                            length6++;
                        }
                        bVarArr3[length6] = new b();
                        lzVar.a(bVarArr3[length6]);
                        this.fb = bVarArr3;
                        continue;
                    case 58:
                        int b7 = mi.b(lzVar, 58);
                        int length7 = this.fc == null ? 0 : this.fc.length;
                        g[] gVarArr = new g[(b7 + length7)];
                        if (length7 != 0) {
                            System.arraycopy(this.fc, 0, gVarArr, 0, length7);
                        }
                        while (length7 < gVarArr.length - 1) {
                            gVarArr[length7] = new g();
                            lzVar.a(gVarArr[length7]);
                            lzVar.nw();
                            length7++;
                        }
                        gVarArr[length7] = new g();
                        lzVar.a(gVarArr[length7]);
                        this.fc = gVarArr;
                        continue;
                    case 74:
                        this.fd = lzVar.readString();
                        continue;
                    case 82:
                        this.fe = lzVar.readString();
                        continue;
                    case 98:
                        this.ff = lzVar.readString();
                        continue;
                    case 106:
                        this.fg = lzVar.readString();
                        continue;
                    case 114:
                        if (this.fh == null) {
                            this.fh = new a();
                        }
                        lzVar.a(this.fh);
                        continue;
                    case 125:
                        this.fi = lzVar.readFloat();
                        continue;
                    case TransportMediator.KEYCODE_MEDIA_RECORD:
                        int b8 = mi.b(lzVar, TransportMediator.KEYCODE_MEDIA_RECORD);
                        int length8 = this.fk == null ? 0 : this.fk.length;
                        String[] strArr2 = new String[(b8 + length8)];
                        if (length8 != 0) {
                            System.arraycopy(this.fk, 0, strArr2, 0, length8);
                        }
                        while (length8 < strArr2.length - 1) {
                            strArr2[length8] = lzVar.readString();
                            lzVar.nw();
                            length8++;
                        }
                        strArr2[length8] = lzVar.readString();
                        this.fk = strArr2;
                        continue;
                    case 136:
                        this.fl = lzVar.nz();
                        continue;
                    case 144:
                        this.fj = lzVar.nA();
                        continue;
                    case 154:
                        int b9 = mi.b(lzVar, 154);
                        int length9 = this.eV == null ? 0 : this.eV.length;
                        String[] strArr3 = new String[(b9 + length9)];
                        if (length9 != 0) {
                            System.arraycopy(this.eV, 0, strArr3, 0, length9);
                        }
                        while (length9 < strArr3.length - 1) {
                            strArr3[length9] = lzVar.readString();
                            lzVar.nw();
                            length9++;
                        }
                        strArr3[length9] = lzVar.readString();
                        this.eV = strArr3;
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

        public int hashCode() {
            int i = 0;
            int hashCode = ((((((this.fj ? 1231 : 1237) + (((((this.fh == null ? 0 : this.fh.hashCode()) + (((this.fg == null ? 0 : this.fg.hashCode()) + (((this.ff == null ? 0 : this.ff.hashCode()) + (((this.fe == null ? 0 : this.fe.hashCode()) + (((this.fd == null ? 0 : this.fd.hashCode()) + ((((((((((((((((md.hashCode((Object[]) this.eV) + 527) * 31) + md.hashCode((Object[]) this.eW)) * 31) + md.hashCode((Object[]) this.eX)) * 31) + md.hashCode((Object[]) this.eY)) * 31) + md.hashCode((Object[]) this.eZ)) * 31) + md.hashCode((Object[]) this.fa)) * 31) + md.hashCode((Object[]) this.fb)) * 31) + md.hashCode((Object[]) this.fc)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31) + Float.floatToIntBits(this.fi)) * 31)) * 31) + md.hashCode((Object[]) this.fk)) * 31) + this.fl) * 31;
            if (this.amU != null && !this.amU.isEmpty()) {
                i = this.amU.hashCode();
            }
            return hashCode + i;
        }

        public f k() {
            this.eV = mi.anf;
            this.eW = mi.anf;
            this.eX = d.a.r();
            this.eY = e.i();
            this.eZ = b.d();
            this.fa = b.d();
            this.fb = b.d();
            this.fc = g.l();
            this.fd = "";
            this.fe = "";
            this.ff = AppEventsConstants.EVENT_PARAM_VALUE_NO;
            this.fg = "";
            this.fh = null;
            this.fi = 0.0f;
            this.fj = false;
            this.fk = mi.anf;
            this.fl = 0;
            this.amU = null;
            this.amY = -1;
            return this;
        }
    }

    public static final class g extends mb<g> {
        private static volatile g[] fm;
        public int[] fn;
        public int[] fo;
        public int[] fp;
        public int[] fq;
        public int[] fr;
        public int[] fs;
        public int[] ft;
        public int[] fu;
        public int[] fv;
        public int[] fw;

        public g() {
            m();
        }

        public static g[] l() {
            if (fm == null) {
                synchronized (md.amX) {
                    if (fm == null) {
                        fm = new g[0];
                    }
                }
            }
            return fm;
        }

        public void a(ma maVar) throws IOException {
            if (this.fn != null && this.fn.length > 0) {
                for (int p : this.fn) {
                    maVar.p(1, p);
                }
            }
            if (this.fo != null && this.fo.length > 0) {
                for (int p2 : this.fo) {
                    maVar.p(2, p2);
                }
            }
            if (this.fp != null && this.fp.length > 0) {
                for (int p3 : this.fp) {
                    maVar.p(3, p3);
                }
            }
            if (this.fq != null && this.fq.length > 0) {
                for (int p4 : this.fq) {
                    maVar.p(4, p4);
                }
            }
            if (this.fr != null && this.fr.length > 0) {
                for (int p5 : this.fr) {
                    maVar.p(5, p5);
                }
            }
            if (this.fs != null && this.fs.length > 0) {
                for (int p6 : this.fs) {
                    maVar.p(6, p6);
                }
            }
            if (this.ft != null && this.ft.length > 0) {
                for (int p7 : this.ft) {
                    maVar.p(7, p7);
                }
            }
            if (this.fu != null && this.fu.length > 0) {
                for (int p8 : this.fu) {
                    maVar.p(8, p8);
                }
            }
            if (this.fv != null && this.fv.length > 0) {
                for (int p9 : this.fv) {
                    maVar.p(9, p9);
                }
            }
            if (this.fw != null && this.fw.length > 0) {
                for (int p10 : this.fw) {
                    maVar.p(10, p10);
                }
            }
            super.a(maVar);
        }

        /* access modifiers changed from: protected */
        public int c() {
            int i;
            int c = super.c();
            if (this.fn == null || this.fn.length <= 0) {
                i = c;
            } else {
                int i2 = 0;
                for (int eE : this.fn) {
                    i2 += ma.eE(eE);
                }
                i = c + i2 + (this.fn.length * 1);
            }
            if (this.fo != null && this.fo.length > 0) {
                int i3 = 0;
                for (int eE2 : this.fo) {
                    i3 += ma.eE(eE2);
                }
                i = i + i3 + (this.fo.length * 1);
            }
            if (this.fp != null && this.fp.length > 0) {
                int i4 = 0;
                for (int eE3 : this.fp) {
                    i4 += ma.eE(eE3);
                }
                i = i + i4 + (this.fp.length * 1);
            }
            if (this.fq != null && this.fq.length > 0) {
                int i5 = 0;
                for (int eE4 : this.fq) {
                    i5 += ma.eE(eE4);
                }
                i = i + i5 + (this.fq.length * 1);
            }
            if (this.fr != null && this.fr.length > 0) {
                int i6 = 0;
                for (int eE5 : this.fr) {
                    i6 += ma.eE(eE5);
                }
                i = i + i6 + (this.fr.length * 1);
            }
            if (this.fs != null && this.fs.length > 0) {
                int i7 = 0;
                for (int eE6 : this.fs) {
                    i7 += ma.eE(eE6);
                }
                i = i + i7 + (this.fs.length * 1);
            }
            if (this.ft != null && this.ft.length > 0) {
                int i8 = 0;
                for (int eE7 : this.ft) {
                    i8 += ma.eE(eE7);
                }
                i = i + i8 + (this.ft.length * 1);
            }
            if (this.fu != null && this.fu.length > 0) {
                int i9 = 0;
                for (int eE8 : this.fu) {
                    i9 += ma.eE(eE8);
                }
                i = i + i9 + (this.fu.length * 1);
            }
            if (this.fv != null && this.fv.length > 0) {
                int i10 = 0;
                for (int eE9 : this.fv) {
                    i10 += ma.eE(eE9);
                }
                i = i + i10 + (this.fv.length * 1);
            }
            if (this.fw == null || this.fw.length <= 0) {
                return i;
            }
            int i11 = 0;
            for (int eE10 : this.fw) {
                i11 += ma.eE(eE10);
            }
            return i + i11 + (this.fw.length * 1);
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof g)) {
                return false;
            }
            g gVar = (g) o;
            if (!md.equals(this.fn, gVar.fn) || !md.equals(this.fo, gVar.fo) || !md.equals(this.fp, gVar.fp) || !md.equals(this.fq, gVar.fq) || !md.equals(this.fr, gVar.fr) || !md.equals(this.fs, gVar.fs) || !md.equals(this.ft, gVar.ft) || !md.equals(this.fu, gVar.fu) || !md.equals(this.fv, gVar.fv) || !md.equals(this.fw, gVar.fw)) {
                return false;
            }
            if (this.amU == null || this.amU.isEmpty()) {
                return gVar.amU == null || gVar.amU.isEmpty();
            }
            return this.amU.equals(gVar.amU);
        }

        /* renamed from: h */
        public g b(lz lzVar) throws IOException {
            while (true) {
                int nw = lzVar.nw();
                switch (nw) {
                    case 0:
                        break;
                    case 8:
                        int b = mi.b(lzVar, 8);
                        int length = this.fn == null ? 0 : this.fn.length;
                        int[] iArr = new int[(b + length)];
                        if (length != 0) {
                            System.arraycopy(this.fn, 0, iArr, 0, length);
                        }
                        while (length < iArr.length - 1) {
                            iArr[length] = lzVar.nz();
                            lzVar.nw();
                            length++;
                        }
                        iArr[length] = lzVar.nz();
                        this.fn = iArr;
                        continue;
                    case 10:
                        int ex = lzVar.ex(lzVar.nD());
                        int position = lzVar.getPosition();
                        int i = 0;
                        while (lzVar.nI() > 0) {
                            lzVar.nz();
                            i++;
                        }
                        lzVar.ez(position);
                        int length2 = this.fn == null ? 0 : this.fn.length;
                        int[] iArr2 = new int[(i + length2)];
                        if (length2 != 0) {
                            System.arraycopy(this.fn, 0, iArr2, 0, length2);
                        }
                        while (length2 < iArr2.length) {
                            iArr2[length2] = lzVar.nz();
                            length2++;
                        }
                        this.fn = iArr2;
                        lzVar.ey(ex);
                        continue;
                    case 16:
                        int b2 = mi.b(lzVar, 16);
                        int length3 = this.fo == null ? 0 : this.fo.length;
                        int[] iArr3 = new int[(b2 + length3)];
                        if (length3 != 0) {
                            System.arraycopy(this.fo, 0, iArr3, 0, length3);
                        }
                        while (length3 < iArr3.length - 1) {
                            iArr3[length3] = lzVar.nz();
                            lzVar.nw();
                            length3++;
                        }
                        iArr3[length3] = lzVar.nz();
                        this.fo = iArr3;
                        continue;
                    case 18:
                        int ex2 = lzVar.ex(lzVar.nD());
                        int position2 = lzVar.getPosition();
                        int i2 = 0;
                        while (lzVar.nI() > 0) {
                            lzVar.nz();
                            i2++;
                        }
                        lzVar.ez(position2);
                        int length4 = this.fo == null ? 0 : this.fo.length;
                        int[] iArr4 = new int[(i2 + length4)];
                        if (length4 != 0) {
                            System.arraycopy(this.fo, 0, iArr4, 0, length4);
                        }
                        while (length4 < iArr4.length) {
                            iArr4[length4] = lzVar.nz();
                            length4++;
                        }
                        this.fo = iArr4;
                        lzVar.ey(ex2);
                        continue;
                    case 24:
                        int b3 = mi.b(lzVar, 24);
                        int length5 = this.fp == null ? 0 : this.fp.length;
                        int[] iArr5 = new int[(b3 + length5)];
                        if (length5 != 0) {
                            System.arraycopy(this.fp, 0, iArr5, 0, length5);
                        }
                        while (length5 < iArr5.length - 1) {
                            iArr5[length5] = lzVar.nz();
                            lzVar.nw();
                            length5++;
                        }
                        iArr5[length5] = lzVar.nz();
                        this.fp = iArr5;
                        continue;
                    case 26:
                        int ex3 = lzVar.ex(lzVar.nD());
                        int position3 = lzVar.getPosition();
                        int i3 = 0;
                        while (lzVar.nI() > 0) {
                            lzVar.nz();
                            i3++;
                        }
                        lzVar.ez(position3);
                        int length6 = this.fp == null ? 0 : this.fp.length;
                        int[] iArr6 = new int[(i3 + length6)];
                        if (length6 != 0) {
                            System.arraycopy(this.fp, 0, iArr6, 0, length6);
                        }
                        while (length6 < iArr6.length) {
                            iArr6[length6] = lzVar.nz();
                            length6++;
                        }
                        this.fp = iArr6;
                        lzVar.ey(ex3);
                        continue;
                    case 32:
                        int b4 = mi.b(lzVar, 32);
                        int length7 = this.fq == null ? 0 : this.fq.length;
                        int[] iArr7 = new int[(b4 + length7)];
                        if (length7 != 0) {
                            System.arraycopy(this.fq, 0, iArr7, 0, length7);
                        }
                        while (length7 < iArr7.length - 1) {
                            iArr7[length7] = lzVar.nz();
                            lzVar.nw();
                            length7++;
                        }
                        iArr7[length7] = lzVar.nz();
                        this.fq = iArr7;
                        continue;
                    case 34:
                        int ex4 = lzVar.ex(lzVar.nD());
                        int position4 = lzVar.getPosition();
                        int i4 = 0;
                        while (lzVar.nI() > 0) {
                            lzVar.nz();
                            i4++;
                        }
                        lzVar.ez(position4);
                        int length8 = this.fq == null ? 0 : this.fq.length;
                        int[] iArr8 = new int[(i4 + length8)];
                        if (length8 != 0) {
                            System.arraycopy(this.fq, 0, iArr8, 0, length8);
                        }
                        while (length8 < iArr8.length) {
                            iArr8[length8] = lzVar.nz();
                            length8++;
                        }
                        this.fq = iArr8;
                        lzVar.ey(ex4);
                        continue;
                    case BrailleInputEvent.CMD_SELECTION_START /*40*/:
                        int b5 = mi.b(lzVar, 40);
                        int length9 = this.fr == null ? 0 : this.fr.length;
                        int[] iArr9 = new int[(b5 + length9)];
                        if (length9 != 0) {
                            System.arraycopy(this.fr, 0, iArr9, 0, length9);
                        }
                        while (length9 < iArr9.length - 1) {
                            iArr9[length9] = lzVar.nz();
                            lzVar.nw();
                            length9++;
                        }
                        iArr9[length9] = lzVar.nz();
                        this.fr = iArr9;
                        continue;
                    case BrailleInputEvent.CMD_SELECTION_SELECT_ALL /*42*/:
                        int ex5 = lzVar.ex(lzVar.nD());
                        int position5 = lzVar.getPosition();
                        int i5 = 0;
                        while (lzVar.nI() > 0) {
                            lzVar.nz();
                            i5++;
                        }
                        lzVar.ez(position5);
                        int length10 = this.fr == null ? 0 : this.fr.length;
                        int[] iArr10 = new int[(i5 + length10)];
                        if (length10 != 0) {
                            System.arraycopy(this.fr, 0, iArr10, 0, length10);
                        }
                        while (length10 < iArr10.length) {
                            iArr10[length10] = lzVar.nz();
                            length10++;
                        }
                        this.fr = iArr10;
                        lzVar.ey(ex5);
                        continue;
                    case 48:
                        int b6 = mi.b(lzVar, 48);
                        int length11 = this.fs == null ? 0 : this.fs.length;
                        int[] iArr11 = new int[(b6 + length11)];
                        if (length11 != 0) {
                            System.arraycopy(this.fs, 0, iArr11, 0, length11);
                        }
                        while (length11 < iArr11.length - 1) {
                            iArr11[length11] = lzVar.nz();
                            lzVar.nw();
                            length11++;
                        }
                        iArr11[length11] = lzVar.nz();
                        this.fs = iArr11;
                        continue;
                    case 50:
                        int ex6 = lzVar.ex(lzVar.nD());
                        int position6 = lzVar.getPosition();
                        int i6 = 0;
                        while (lzVar.nI() > 0) {
                            lzVar.nz();
                            i6++;
                        }
                        lzVar.ez(position6);
                        int length12 = this.fs == null ? 0 : this.fs.length;
                        int[] iArr12 = new int[(i6 + length12)];
                        if (length12 != 0) {
                            System.arraycopy(this.fs, 0, iArr12, 0, length12);
                        }
                        while (length12 < iArr12.length) {
                            iArr12[length12] = lzVar.nz();
                            length12++;
                        }
                        this.fs = iArr12;
                        lzVar.ey(ex6);
                        continue;
                    case 56:
                        int b7 = mi.b(lzVar, 56);
                        int length13 = this.ft == null ? 0 : this.ft.length;
                        int[] iArr13 = new int[(b7 + length13)];
                        if (length13 != 0) {
                            System.arraycopy(this.ft, 0, iArr13, 0, length13);
                        }
                        while (length13 < iArr13.length - 1) {
                            iArr13[length13] = lzVar.nz();
                            lzVar.nw();
                            length13++;
                        }
                        iArr13[length13] = lzVar.nz();
                        this.ft = iArr13;
                        continue;
                    case 58:
                        int ex7 = lzVar.ex(lzVar.nD());
                        int position7 = lzVar.getPosition();
                        int i7 = 0;
                        while (lzVar.nI() > 0) {
                            lzVar.nz();
                            i7++;
                        }
                        lzVar.ez(position7);
                        int length14 = this.ft == null ? 0 : this.ft.length;
                        int[] iArr14 = new int[(i7 + length14)];
                        if (length14 != 0) {
                            System.arraycopy(this.ft, 0, iArr14, 0, length14);
                        }
                        while (length14 < iArr14.length) {
                            iArr14[length14] = lzVar.nz();
                            length14++;
                        }
                        this.ft = iArr14;
                        lzVar.ey(ex7);
                        continue;
                    case 64:
                        int b8 = mi.b(lzVar, 64);
                        int length15 = this.fu == null ? 0 : this.fu.length;
                        int[] iArr15 = new int[(b8 + length15)];
                        if (length15 != 0) {
                            System.arraycopy(this.fu, 0, iArr15, 0, length15);
                        }
                        while (length15 < iArr15.length - 1) {
                            iArr15[length15] = lzVar.nz();
                            lzVar.nw();
                            length15++;
                        }
                        iArr15[length15] = lzVar.nz();
                        this.fu = iArr15;
                        continue;
                    case 66:
                        int ex8 = lzVar.ex(lzVar.nD());
                        int position8 = lzVar.getPosition();
                        int i8 = 0;
                        while (lzVar.nI() > 0) {
                            lzVar.nz();
                            i8++;
                        }
                        lzVar.ez(position8);
                        int length16 = this.fu == null ? 0 : this.fu.length;
                        int[] iArr16 = new int[(i8 + length16)];
                        if (length16 != 0) {
                            System.arraycopy(this.fu, 0, iArr16, 0, length16);
                        }
                        while (length16 < iArr16.length) {
                            iArr16[length16] = lzVar.nz();
                            length16++;
                        }
                        this.fu = iArr16;
                        lzVar.ey(ex8);
                        continue;
                    case BrailleInputEvent.CMD_KEY_FORWARD_DEL /*72*/:
                        int b9 = mi.b(lzVar, 72);
                        int length17 = this.fv == null ? 0 : this.fv.length;
                        int[] iArr17 = new int[(b9 + length17)];
                        if (length17 != 0) {
                            System.arraycopy(this.fv, 0, iArr17, 0, length17);
                        }
                        while (length17 < iArr17.length - 1) {
                            iArr17[length17] = lzVar.nz();
                            lzVar.nw();
                            length17++;
                        }
                        iArr17[length17] = lzVar.nz();
                        this.fv = iArr17;
                        continue;
                    case 74:
                        int ex9 = lzVar.ex(lzVar.nD());
                        int position9 = lzVar.getPosition();
                        int i9 = 0;
                        while (lzVar.nI() > 0) {
                            lzVar.nz();
                            i9++;
                        }
                        lzVar.ez(position9);
                        int length18 = this.fv == null ? 0 : this.fv.length;
                        int[] iArr18 = new int[(i9 + length18)];
                        if (length18 != 0) {
                            System.arraycopy(this.fv, 0, iArr18, 0, length18);
                        }
                        while (length18 < iArr18.length) {
                            iArr18[length18] = lzVar.nz();
                            length18++;
                        }
                        this.fv = iArr18;
                        lzVar.ey(ex9);
                        continue;
                    case 80:
                        int b10 = mi.b(lzVar, 80);
                        int length19 = this.fw == null ? 0 : this.fw.length;
                        int[] iArr19 = new int[(b10 + length19)];
                        if (length19 != 0) {
                            System.arraycopy(this.fw, 0, iArr19, 0, length19);
                        }
                        while (length19 < iArr19.length - 1) {
                            iArr19[length19] = lzVar.nz();
                            lzVar.nw();
                            length19++;
                        }
                        iArr19[length19] = lzVar.nz();
                        this.fw = iArr19;
                        continue;
                    case 82:
                        int ex10 = lzVar.ex(lzVar.nD());
                        int position10 = lzVar.getPosition();
                        int i10 = 0;
                        while (lzVar.nI() > 0) {
                            lzVar.nz();
                            i10++;
                        }
                        lzVar.ez(position10);
                        int length20 = this.fw == null ? 0 : this.fw.length;
                        int[] iArr20 = new int[(i10 + length20)];
                        if (length20 != 0) {
                            System.arraycopy(this.fw, 0, iArr20, 0, length20);
                        }
                        while (length20 < iArr20.length) {
                            iArr20[length20] = lzVar.nz();
                            length20++;
                        }
                        this.fw = iArr20;
                        lzVar.ey(ex10);
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

        public int hashCode() {
            return ((this.amU == null || this.amU.isEmpty()) ? 0 : this.amU.hashCode()) + ((((((((((((((((((((md.hashCode(this.fn) + 527) * 31) + md.hashCode(this.fo)) * 31) + md.hashCode(this.fp)) * 31) + md.hashCode(this.fq)) * 31) + md.hashCode(this.fr)) * 31) + md.hashCode(this.fs)) * 31) + md.hashCode(this.ft)) * 31) + md.hashCode(this.fu)) * 31) + md.hashCode(this.fv)) * 31) + md.hashCode(this.fw)) * 31);
        }

        public g m() {
            this.fn = mi.ana;
            this.fo = mi.ana;
            this.fp = mi.ana;
            this.fq = mi.ana;
            this.fr = mi.ana;
            this.fs = mi.ana;
            this.ft = mi.ana;
            this.fu = mi.ana;
            this.fv = mi.ana;
            this.fw = mi.ana;
            this.amU = null;
            this.amY = -1;
            return this;
        }
    }

    public static final class h extends mb<h> {
        public static final mc<d.a, h> fx = mc.a(11, h.class, 810);
        private static final h[] fy = new h[0];
        public int[] fA;
        public int[] fB;
        public int fC;
        public int[] fD;
        public int fE;
        public int fF;
        public int[] fz;

        public h() {
            n();
        }

        public void a(ma maVar) throws IOException {
            if (this.fz != null && this.fz.length > 0) {
                for (int p : this.fz) {
                    maVar.p(1, p);
                }
            }
            if (this.fA != null && this.fA.length > 0) {
                for (int p2 : this.fA) {
                    maVar.p(2, p2);
                }
            }
            if (this.fB != null && this.fB.length > 0) {
                for (int p3 : this.fB) {
                    maVar.p(3, p3);
                }
            }
            if (this.fC != 0) {
                maVar.p(4, this.fC);
            }
            if (this.fD != null && this.fD.length > 0) {
                for (int p4 : this.fD) {
                    maVar.p(5, p4);
                }
            }
            if (this.fE != 0) {
                maVar.p(6, this.fE);
            }
            if (this.fF != 0) {
                maVar.p(7, this.fF);
            }
            super.a(maVar);
        }

        /* access modifiers changed from: protected */
        public int c() {
            int i;
            int c = super.c();
            if (this.fz == null || this.fz.length <= 0) {
                i = c;
            } else {
                int i2 = 0;
                for (int eE : this.fz) {
                    i2 += ma.eE(eE);
                }
                i = c + i2 + (this.fz.length * 1);
            }
            if (this.fA != null && this.fA.length > 0) {
                int i3 = 0;
                for (int eE2 : this.fA) {
                    i3 += ma.eE(eE2);
                }
                i = i + i3 + (this.fA.length * 1);
            }
            if (this.fB != null && this.fB.length > 0) {
                int i4 = 0;
                for (int eE3 : this.fB) {
                    i4 += ma.eE(eE3);
                }
                i = i + i4 + (this.fB.length * 1);
            }
            if (this.fC != 0) {
                i += ma.r(4, this.fC);
            }
            if (this.fD != null && this.fD.length > 0) {
                int i5 = 0;
                for (int eE4 : this.fD) {
                    i5 += ma.eE(eE4);
                }
                i = i + i5 + (this.fD.length * 1);
            }
            if (this.fE != 0) {
                i += ma.r(6, this.fE);
            }
            return this.fF != 0 ? i + ma.r(7, this.fF) : i;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof h)) {
                return false;
            }
            h hVar = (h) o;
            if (!md.equals(this.fz, hVar.fz) || !md.equals(this.fA, hVar.fA) || !md.equals(this.fB, hVar.fB) || this.fC != hVar.fC || !md.equals(this.fD, hVar.fD) || this.fE != hVar.fE || this.fF != hVar.fF) {
                return false;
            }
            if (this.amU == null || this.amU.isEmpty()) {
                return hVar.amU == null || hVar.amU.isEmpty();
            }
            return this.amU.equals(hVar.amU);
        }

        public int hashCode() {
            return ((this.amU == null || this.amU.isEmpty()) ? 0 : this.amU.hashCode()) + ((((((((((((((md.hashCode(this.fz) + 527) * 31) + md.hashCode(this.fA)) * 31) + md.hashCode(this.fB)) * 31) + this.fC) * 31) + md.hashCode(this.fD)) * 31) + this.fE) * 31) + this.fF) * 31);
        }

        /* renamed from: i */
        public h b(lz lzVar) throws IOException {
            while (true) {
                int nw = lzVar.nw();
                switch (nw) {
                    case 0:
                        break;
                    case 8:
                        int b = mi.b(lzVar, 8);
                        int length = this.fz == null ? 0 : this.fz.length;
                        int[] iArr = new int[(b + length)];
                        if (length != 0) {
                            System.arraycopy(this.fz, 0, iArr, 0, length);
                        }
                        while (length < iArr.length - 1) {
                            iArr[length] = lzVar.nz();
                            lzVar.nw();
                            length++;
                        }
                        iArr[length] = lzVar.nz();
                        this.fz = iArr;
                        continue;
                    case 10:
                        int ex = lzVar.ex(lzVar.nD());
                        int position = lzVar.getPosition();
                        int i = 0;
                        while (lzVar.nI() > 0) {
                            lzVar.nz();
                            i++;
                        }
                        lzVar.ez(position);
                        int length2 = this.fz == null ? 0 : this.fz.length;
                        int[] iArr2 = new int[(i + length2)];
                        if (length2 != 0) {
                            System.arraycopy(this.fz, 0, iArr2, 0, length2);
                        }
                        while (length2 < iArr2.length) {
                            iArr2[length2] = lzVar.nz();
                            length2++;
                        }
                        this.fz = iArr2;
                        lzVar.ey(ex);
                        continue;
                    case 16:
                        int b2 = mi.b(lzVar, 16);
                        int length3 = this.fA == null ? 0 : this.fA.length;
                        int[] iArr3 = new int[(b2 + length3)];
                        if (length3 != 0) {
                            System.arraycopy(this.fA, 0, iArr3, 0, length3);
                        }
                        while (length3 < iArr3.length - 1) {
                            iArr3[length3] = lzVar.nz();
                            lzVar.nw();
                            length3++;
                        }
                        iArr3[length3] = lzVar.nz();
                        this.fA = iArr3;
                        continue;
                    case 18:
                        int ex2 = lzVar.ex(lzVar.nD());
                        int position2 = lzVar.getPosition();
                        int i2 = 0;
                        while (lzVar.nI() > 0) {
                            lzVar.nz();
                            i2++;
                        }
                        lzVar.ez(position2);
                        int length4 = this.fA == null ? 0 : this.fA.length;
                        int[] iArr4 = new int[(i2 + length4)];
                        if (length4 != 0) {
                            System.arraycopy(this.fA, 0, iArr4, 0, length4);
                        }
                        while (length4 < iArr4.length) {
                            iArr4[length4] = lzVar.nz();
                            length4++;
                        }
                        this.fA = iArr4;
                        lzVar.ey(ex2);
                        continue;
                    case 24:
                        int b3 = mi.b(lzVar, 24);
                        int length5 = this.fB == null ? 0 : this.fB.length;
                        int[] iArr5 = new int[(b3 + length5)];
                        if (length5 != 0) {
                            System.arraycopy(this.fB, 0, iArr5, 0, length5);
                        }
                        while (length5 < iArr5.length - 1) {
                            iArr5[length5] = lzVar.nz();
                            lzVar.nw();
                            length5++;
                        }
                        iArr5[length5] = lzVar.nz();
                        this.fB = iArr5;
                        continue;
                    case 26:
                        int ex3 = lzVar.ex(lzVar.nD());
                        int position3 = lzVar.getPosition();
                        int i3 = 0;
                        while (lzVar.nI() > 0) {
                            lzVar.nz();
                            i3++;
                        }
                        lzVar.ez(position3);
                        int length6 = this.fB == null ? 0 : this.fB.length;
                        int[] iArr6 = new int[(i3 + length6)];
                        if (length6 != 0) {
                            System.arraycopy(this.fB, 0, iArr6, 0, length6);
                        }
                        while (length6 < iArr6.length) {
                            iArr6[length6] = lzVar.nz();
                            length6++;
                        }
                        this.fB = iArr6;
                        lzVar.ey(ex3);
                        continue;
                    case 32:
                        this.fC = lzVar.nz();
                        continue;
                    case BrailleInputEvent.CMD_SELECTION_START /*40*/:
                        int b4 = mi.b(lzVar, 40);
                        int length7 = this.fD == null ? 0 : this.fD.length;
                        int[] iArr7 = new int[(b4 + length7)];
                        if (length7 != 0) {
                            System.arraycopy(this.fD, 0, iArr7, 0, length7);
                        }
                        while (length7 < iArr7.length - 1) {
                            iArr7[length7] = lzVar.nz();
                            lzVar.nw();
                            length7++;
                        }
                        iArr7[length7] = lzVar.nz();
                        this.fD = iArr7;
                        continue;
                    case BrailleInputEvent.CMD_SELECTION_SELECT_ALL /*42*/:
                        int ex4 = lzVar.ex(lzVar.nD());
                        int position4 = lzVar.getPosition();
                        int i4 = 0;
                        while (lzVar.nI() > 0) {
                            lzVar.nz();
                            i4++;
                        }
                        lzVar.ez(position4);
                        int length8 = this.fD == null ? 0 : this.fD.length;
                        int[] iArr8 = new int[(i4 + length8)];
                        if (length8 != 0) {
                            System.arraycopy(this.fD, 0, iArr8, 0, length8);
                        }
                        while (length8 < iArr8.length) {
                            iArr8[length8] = lzVar.nz();
                            length8++;
                        }
                        this.fD = iArr8;
                        lzVar.ey(ex4);
                        continue;
                    case 48:
                        this.fE = lzVar.nz();
                        continue;
                    case 56:
                        this.fF = lzVar.nz();
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

        public h n() {
            this.fz = mi.ana;
            this.fA = mi.ana;
            this.fB = mi.ana;
            this.fC = 0;
            this.fD = mi.ana;
            this.fE = 0;
            this.fF = 0;
            this.amU = null;
            this.amY = -1;
            return this;
        }
    }

    public static final class i extends mb<i> {
        private static volatile i[] fG;
        public d.a fH;
        public d fI;
        public String name;

        public i() {
            p();
        }

        public static i[] o() {
            if (fG == null) {
                synchronized (md.amX) {
                    if (fG == null) {
                        fG = new i[0];
                    }
                }
            }
            return fG;
        }

        public void a(ma maVar) throws IOException {
            if (!this.name.equals("")) {
                maVar.b(1, this.name);
            }
            if (this.fH != null) {
                maVar.a(2, (mf) this.fH);
            }
            if (this.fI != null) {
                maVar.a(3, (mf) this.fI);
            }
            super.a(maVar);
        }

        /* access modifiers changed from: protected */
        public int c() {
            int c = super.c();
            if (!this.name.equals("")) {
                c += ma.h(1, this.name);
            }
            if (this.fH != null) {
                c += ma.b(2, (mf) this.fH);
            }
            return this.fI != null ? c + ma.b(3, (mf) this.fI) : c;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof i)) {
                return false;
            }
            i iVar = (i) o;
            if (this.name == null) {
                if (iVar.name != null) {
                    return false;
                }
            } else if (!this.name.equals(iVar.name)) {
                return false;
            }
            if (this.fH == null) {
                if (iVar.fH != null) {
                    return false;
                }
            } else if (!this.fH.equals(iVar.fH)) {
                return false;
            }
            if (this.fI == null) {
                if (iVar.fI != null) {
                    return false;
                }
            } else if (!this.fI.equals(iVar.fI)) {
                return false;
            }
            if (this.amU == null || this.amU.isEmpty()) {
                return iVar.amU == null || iVar.amU.isEmpty();
            }
            return this.amU.equals(iVar.amU);
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((this.fI == null ? 0 : this.fI.hashCode()) + (((this.fH == null ? 0 : this.fH.hashCode()) + (((this.name == null ? 0 : this.name.hashCode()) + 527) * 31)) * 31)) * 31;
            if (this.amU != null && !this.amU.isEmpty()) {
                i = this.amU.hashCode();
            }
            return hashCode + i;
        }

        /* renamed from: j */
        public i b(lz lzVar) throws IOException {
            while (true) {
                int nw = lzVar.nw();
                switch (nw) {
                    case 0:
                        break;
                    case 10:
                        this.name = lzVar.readString();
                        continue;
                    case 18:
                        if (this.fH == null) {
                            this.fH = new d.a();
                        }
                        lzVar.a(this.fH);
                        continue;
                    case 26:
                        if (this.fI == null) {
                            this.fI = new d();
                        }
                        lzVar.a(this.fI);
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

        public i p() {
            this.name = "";
            this.fH = null;
            this.fI = null;
            this.amU = null;
            this.amY = -1;
            return this;
        }
    }

    public static final class j extends mb<j> {
        public i[] fJ;
        public f fK;
        public String fL;

        public j() {
            q();
        }

        public static j b(byte[] bArr) throws me {
            return (j) mf.a(new j(), bArr);
        }

        public void a(ma maVar) throws IOException {
            if (this.fJ != null && this.fJ.length > 0) {
                for (i iVar : this.fJ) {
                    if (iVar != null) {
                        maVar.a(1, (mf) iVar);
                    }
                }
            }
            if (this.fK != null) {
                maVar.a(2, (mf) this.fK);
            }
            if (!this.fL.equals("")) {
                maVar.b(3, this.fL);
            }
            super.a(maVar);
        }

        /* access modifiers changed from: protected */
        public int c() {
            int c = super.c();
            if (this.fJ != null && this.fJ.length > 0) {
                for (i iVar : this.fJ) {
                    if (iVar != null) {
                        c += ma.b(1, (mf) iVar);
                    }
                }
            }
            if (this.fK != null) {
                c += ma.b(2, (mf) this.fK);
            }
            return !this.fL.equals("") ? c + ma.h(3, this.fL) : c;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof j)) {
                return false;
            }
            j jVar = (j) o;
            if (!md.equals((Object[]) this.fJ, (Object[]) jVar.fJ)) {
                return false;
            }
            if (this.fK == null) {
                if (jVar.fK != null) {
                    return false;
                }
            } else if (!this.fK.equals(jVar.fK)) {
                return false;
            }
            if (this.fL == null) {
                if (jVar.fL != null) {
                    return false;
                }
            } else if (!this.fL.equals(jVar.fL)) {
                return false;
            }
            if (this.amU == null || this.amU.isEmpty()) {
                return jVar.amU == null || jVar.amU.isEmpty();
            }
            return this.amU.equals(jVar.amU);
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((this.fL == null ? 0 : this.fL.hashCode()) + (((this.fK == null ? 0 : this.fK.hashCode()) + ((md.hashCode((Object[]) this.fJ) + 527) * 31)) * 31)) * 31;
            if (this.amU != null && !this.amU.isEmpty()) {
                i = this.amU.hashCode();
            }
            return hashCode + i;
        }

        /* renamed from: k */
        public j b(lz lzVar) throws IOException {
            while (true) {
                int nw = lzVar.nw();
                switch (nw) {
                    case 0:
                        break;
                    case 10:
                        int b = mi.b(lzVar, 10);
                        int length = this.fJ == null ? 0 : this.fJ.length;
                        i[] iVarArr = new i[(b + length)];
                        if (length != 0) {
                            System.arraycopy(this.fJ, 0, iVarArr, 0, length);
                        }
                        while (length < iVarArr.length - 1) {
                            iVarArr[length] = new i();
                            lzVar.a(iVarArr[length]);
                            lzVar.nw();
                            length++;
                        }
                        iVarArr[length] = new i();
                        lzVar.a(iVarArr[length]);
                        this.fJ = iVarArr;
                        continue;
                    case 18:
                        if (this.fK == null) {
                            this.fK = new f();
                        }
                        lzVar.a(this.fK);
                        continue;
                    case 26:
                        this.fL = lzVar.readString();
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

        public j q() {
            this.fJ = i.o();
            this.fK = null;
            this.fL = "";
            this.amU = null;
            this.amY = -1;
            return this;
        }
    }
}
