package com.google.android.gms.internal;

import java.io.IOException;

public interface iw {

    public static final class a extends mb<a> {
        public C0064a[] Uv;

        /* renamed from: com.google.android.gms.internal.iw$a$a  reason: collision with other inner class name */
        public static final class C0064a extends mb<C0064a> {
            private static volatile C0064a[] Uw;
            public String Ux;
            public String Uy;
            public int viewId;

            public C0064a() {
                iN();
            }

            public static C0064a[] iM() {
                if (Uw == null) {
                    synchronized (md.amX) {
                        if (Uw == null) {
                            Uw = new C0064a[0];
                        }
                    }
                }
                return Uw;
            }

            public void a(ma maVar) throws IOException {
                if (!this.Ux.equals("")) {
                    maVar.b(1, this.Ux);
                }
                if (!this.Uy.equals("")) {
                    maVar.b(2, this.Uy);
                }
                if (this.viewId != 0) {
                    maVar.p(3, this.viewId);
                }
                super.a(maVar);
            }

            /* access modifiers changed from: protected */
            public int c() {
                int c = super.c();
                if (!this.Ux.equals("")) {
                    c += ma.h(1, this.Ux);
                }
                if (!this.Uy.equals("")) {
                    c += ma.h(2, this.Uy);
                }
                return this.viewId != 0 ? c + ma.r(3, this.viewId) : c;
            }

            public boolean equals(Object o) {
                if (o == this) {
                    return true;
                }
                if (!(o instanceof C0064a)) {
                    return false;
                }
                C0064a aVar = (C0064a) o;
                if (this.Ux == null) {
                    if (aVar.Ux != null) {
                        return false;
                    }
                } else if (!this.Ux.equals(aVar.Ux)) {
                    return false;
                }
                if (this.Uy == null) {
                    if (aVar.Uy != null) {
                        return false;
                    }
                } else if (!this.Uy.equals(aVar.Uy)) {
                    return false;
                }
                if (this.viewId != aVar.viewId) {
                    return false;
                }
                if (this.amU == null || this.amU.isEmpty()) {
                    return aVar.amU == null || aVar.amU.isEmpty();
                }
                return this.amU.equals(aVar.amU);
            }

            public int hashCode() {
                int i = 0;
                int hashCode = ((((this.Uy == null ? 0 : this.Uy.hashCode()) + (((this.Ux == null ? 0 : this.Ux.hashCode()) + 527) * 31)) * 31) + this.viewId) * 31;
                if (this.amU != null && !this.amU.isEmpty()) {
                    i = this.amU.hashCode();
                }
                return hashCode + i;
            }

            public C0064a iN() {
                this.Ux = "";
                this.Uy = "";
                this.viewId = 0;
                this.amU = null;
                this.amY = -1;
                return this;
            }

            /* renamed from: o */
            public C0064a b(lz lzVar) throws IOException {
                while (true) {
                    int nw = lzVar.nw();
                    switch (nw) {
                        case 0:
                            break;
                        case 10:
                            this.Ux = lzVar.readString();
                            continue;
                        case 18:
                            this.Uy = lzVar.readString();
                            continue;
                        case 24:
                            this.viewId = lzVar.nz();
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
            iL();
        }

        public void a(ma maVar) throws IOException {
            if (this.Uv != null && this.Uv.length > 0) {
                for (C0064a aVar : this.Uv) {
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
            if (this.Uv != null && this.Uv.length > 0) {
                for (C0064a aVar : this.Uv) {
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
            if (!(o instanceof a)) {
                return false;
            }
            a aVar = (a) o;
            if (!md.equals((Object[]) this.Uv, (Object[]) aVar.Uv)) {
                return false;
            }
            if (this.amU == null || this.amU.isEmpty()) {
                return aVar.amU == null || aVar.amU.isEmpty();
            }
            return this.amU.equals(aVar.amU);
        }

        public int hashCode() {
            return ((this.amU == null || this.amU.isEmpty()) ? 0 : this.amU.hashCode()) + ((md.hashCode((Object[]) this.Uv) + 527) * 31);
        }

        public a iL() {
            this.Uv = C0064a.iM();
            this.amU = null;
            this.amY = -1;
            return this;
        }

        /* renamed from: n */
        public a b(lz lzVar) throws IOException {
            while (true) {
                int nw = lzVar.nw();
                switch (nw) {
                    case 0:
                        break;
                    case 10:
                        int b = mi.b(lzVar, 10);
                        int length = this.Uv == null ? 0 : this.Uv.length;
                        C0064a[] aVarArr = new C0064a[(b + length)];
                        if (length != 0) {
                            System.arraycopy(this.Uv, 0, aVarArr, 0, length);
                        }
                        while (length < aVarArr.length - 1) {
                            aVarArr[length] = new C0064a();
                            lzVar.a(aVarArr[length]);
                            lzVar.nw();
                            length++;
                        }
                        aVarArr[length] = new C0064a();
                        lzVar.a(aVarArr[length]);
                        this.Uv = aVarArr;
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
