package com.google.android.gms.internal;

import com.google.android.gms.internal.c;
import java.io.IOException;

public interface lf {

    public static final class a extends mb<a> {
        public long aiD;
        public c.j aiE;
        public c.f fK;

        public a() {
            na();
        }

        public static a l(byte[] bArr) throws me {
            return (a) mf.a(new a(), bArr);
        }

        public void a(ma maVar) throws IOException {
            maVar.b(1, this.aiD);
            if (this.fK != null) {
                maVar.a(2, (mf) this.fK);
            }
            if (this.aiE != null) {
                maVar.a(3, (mf) this.aiE);
            }
            super.a(maVar);
        }

        /* access modifiers changed from: protected */
        public int c() {
            int c = super.c() + ma.d(1, this.aiD);
            if (this.fK != null) {
                c += ma.b(2, (mf) this.fK);
            }
            return this.aiE != null ? c + ma.b(3, (mf) this.aiE) : c;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof a)) {
                return false;
            }
            a aVar = (a) o;
            if (this.aiD != aVar.aiD) {
                return false;
            }
            if (this.fK == null) {
                if (aVar.fK != null) {
                    return false;
                }
            } else if (!this.fK.equals(aVar.fK)) {
                return false;
            }
            if (this.aiE == null) {
                if (aVar.aiE != null) {
                    return false;
                }
            } else if (!this.aiE.equals(aVar.aiE)) {
                return false;
            }
            if (this.amU == null || this.amU.isEmpty()) {
                return aVar.amU == null || aVar.amU.isEmpty();
            }
            return this.amU.equals(aVar.amU);
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((this.aiE == null ? 0 : this.aiE.hashCode()) + (((this.fK == null ? 0 : this.fK.hashCode()) + ((((int) (this.aiD ^ (this.aiD >>> 32))) + 527) * 31)) * 31)) * 31;
            if (this.amU != null && !this.amU.isEmpty()) {
                i = this.amU.hashCode();
            }
            return hashCode + i;
        }

        public a na() {
            this.aiD = 0;
            this.fK = null;
            this.aiE = null;
            this.amU = null;
            this.amY = -1;
            return this;
        }

        /* renamed from: p */
        public a b(lz lzVar) throws IOException {
            while (true) {
                int nw = lzVar.nw();
                switch (nw) {
                    case 0:
                        break;
                    case 8:
                        this.aiD = lzVar.ny();
                        continue;
                    case 18:
                        if (this.fK == null) {
                            this.fK = new c.f();
                        }
                        lzVar.a(this.fK);
                        continue;
                    case 26:
                        if (this.aiE == null) {
                            this.aiE = new c.j();
                        }
                        lzVar.a(this.aiE);
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
}
