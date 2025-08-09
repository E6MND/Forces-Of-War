package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class hz {

    public static class a<I, O> implements SafeParcelable {
        public static final ia CREATOR = new ia();
        protected final int GY;
        protected final boolean GZ;
        protected final int Ha;
        protected final boolean Hb;
        protected final String Hc;
        protected final int Hd;
        protected final Class<? extends hz> He;
        protected final String Hf;
        private ic Hg;
        /* access modifiers changed from: private */
        public b<I, O> Hh;
        private final int xJ;

        a(int i, int i2, boolean z, int i3, boolean z2, String str, int i4, String str2, hu huVar) {
            this.xJ = i;
            this.GY = i2;
            this.GZ = z;
            this.Ha = i3;
            this.Hb = z2;
            this.Hc = str;
            this.Hd = i4;
            if (str2 == null) {
                this.He = null;
                this.Hf = null;
            } else {
                this.He = Cif.class;
                this.Hf = str2;
            }
            if (huVar == null) {
                this.Hh = null;
            } else {
                this.Hh = huVar.fx();
            }
        }

        protected a(int i, boolean z, int i2, boolean z2, String str, int i3, Class<? extends hz> cls, b<I, O> bVar) {
            this.xJ = 1;
            this.GY = i;
            this.GZ = z;
            this.Ha = i2;
            this.Hb = z2;
            this.Hc = str;
            this.Hd = i3;
            this.He = cls;
            if (cls == null) {
                this.Hf = null;
            } else {
                this.Hf = cls.getCanonicalName();
            }
            this.Hh = bVar;
        }

        public static a a(String str, int i, b<?, ?> bVar, boolean z) {
            return new a(bVar.fz(), z, bVar.fA(), false, str, i, (Class<? extends hz>) null, bVar);
        }

        public static <T extends hz> a<T, T> a(String str, int i, Class<T> cls) {
            return new a<>(11, false, 11, false, str, i, cls, (b) null);
        }

        public static <T extends hz> a<ArrayList<T>, ArrayList<T>> b(String str, int i, Class<T> cls) {
            return new a<>(11, true, 11, true, str, i, cls, (b) null);
        }

        public static a<Integer, Integer> g(String str, int i) {
            return new a<>(0, false, 0, false, str, i, (Class<? extends hz>) null, (b) null);
        }

        public static a<Double, Double> h(String str, int i) {
            return new a<>(4, false, 4, false, str, i, (Class<? extends hz>) null, (b) null);
        }

        public static a<Boolean, Boolean> i(String str, int i) {
            return new a<>(6, false, 6, false, str, i, (Class<? extends hz>) null, (b) null);
        }

        public static a<String, String> j(String str, int i) {
            return new a<>(7, false, 7, false, str, i, (Class<? extends hz>) null, (b) null);
        }

        public static a<ArrayList<String>, ArrayList<String>> k(String str, int i) {
            return new a<>(7, true, 7, true, str, i, (Class<? extends hz>) null, (b) null);
        }

        public void a(ic icVar) {
            this.Hg = icVar;
        }

        public int describeContents() {
            ia iaVar = CREATOR;
            return 0;
        }

        public int fA() {
            return this.Ha;
        }

        public a<I, O> fE() {
            return new a<>(this.xJ, this.GY, this.GZ, this.Ha, this.Hb, this.Hc, this.Hd, this.Hf, fM());
        }

        public boolean fF() {
            return this.GZ;
        }

        public boolean fG() {
            return this.Hb;
        }

        public String fH() {
            return this.Hc;
        }

        public int fI() {
            return this.Hd;
        }

        public Class<? extends hz> fJ() {
            return this.He;
        }

        /* access modifiers changed from: package-private */
        public String fK() {
            if (this.Hf == null) {
                return null;
            }
            return this.Hf;
        }

        public boolean fL() {
            return this.Hh != null;
        }

        /* access modifiers changed from: package-private */
        public hu fM() {
            if (this.Hh == null) {
                return null;
            }
            return hu.a(this.Hh);
        }

        public HashMap<String, a<?, ?>> fN() {
            hn.f(this.Hf);
            hn.f(this.Hg);
            return this.Hg.aJ(this.Hf);
        }

        public int fz() {
            return this.GY;
        }

        public I g(O o) {
            return this.Hh.g(o);
        }

        public int getVersionCode() {
            return this.xJ;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Field\n");
            sb.append("            versionCode=").append(this.xJ).append(10);
            sb.append("                 typeIn=").append(this.GY).append(10);
            sb.append("            typeInArray=").append(this.GZ).append(10);
            sb.append("                typeOut=").append(this.Ha).append(10);
            sb.append("           typeOutArray=").append(this.Hb).append(10);
            sb.append("        outputFieldName=").append(this.Hc).append(10);
            sb.append("      safeParcelFieldId=").append(this.Hd).append(10);
            sb.append("       concreteTypeName=").append(fK()).append(10);
            if (fJ() != null) {
                sb.append("     concreteType.class=").append(fJ().getCanonicalName()).append(10);
            }
            sb.append("          converterName=").append(this.Hh == null ? "null" : this.Hh.getClass().getCanonicalName()).append(10);
            return sb.toString();
        }

        public void writeToParcel(Parcel out, int flags) {
            ia iaVar = CREATOR;
            ia.a(this, out, flags);
        }
    }

    public interface b<I, O> {
        int fA();

        int fz();

        I g(O o);
    }

    private void a(StringBuilder sb, a aVar, Object obj) {
        if (aVar.fz() == 11) {
            sb.append(((hz) aVar.fJ().cast(obj)).toString());
        } else if (aVar.fz() == 7) {
            sb.append("\"");
            sb.append(io.aK((String) obj));
            sb.append("\"");
        } else {
            sb.append(obj);
        }
    }

    private void a(StringBuilder sb, a aVar, ArrayList<Object> arrayList) {
        sb.append("[");
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                sb.append(",");
            }
            Object obj = arrayList.get(i);
            if (obj != null) {
                a(sb, aVar, obj);
            }
        }
        sb.append("]");
    }

    /* access modifiers changed from: protected */
    public <O, I> I a(a<I, O> aVar, Object obj) {
        return aVar.Hh != null ? aVar.g(obj) : obj;
    }

    /* access modifiers changed from: protected */
    public boolean a(a aVar) {
        return aVar.fA() == 11 ? aVar.fG() ? aI(aVar.fH()) : aH(aVar.fH()) : aG(aVar.fH());
    }

    /* access modifiers changed from: protected */
    public abstract Object aF(String str);

    /* access modifiers changed from: protected */
    public abstract boolean aG(String str);

    /* access modifiers changed from: protected */
    public boolean aH(String str) {
        throw new UnsupportedOperationException("Concrete types not supported");
    }

    /* access modifiers changed from: protected */
    public boolean aI(String str) {
        throw new UnsupportedOperationException("Concrete type arrays not supported");
    }

    /* access modifiers changed from: protected */
    public Object b(a aVar) {
        String fH = aVar.fH();
        if (aVar.fJ() == null) {
            return aF(aVar.fH());
        }
        hn.a(aF(aVar.fH()) == null, "Concrete field shouldn't be value object: %s", aVar.fH());
        HashMap<String, Object> fD = aVar.fG() ? fD() : fC();
        if (fD != null) {
            return fD.get(fH);
        }
        try {
            return getClass().getMethod("get" + Character.toUpperCase(fH.charAt(0)) + fH.substring(1), new Class[0]).invoke(this, new Object[0]);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public abstract HashMap<String, a<?, ?>> fB();

    public HashMap<String, Object> fC() {
        return null;
    }

    public HashMap<String, Object> fD() {
        return null;
    }

    public String toString() {
        HashMap<String, a<?, ?>> fB = fB();
        StringBuilder sb = new StringBuilder(100);
        for (String next : fB.keySet()) {
            a aVar = fB.get(next);
            if (a(aVar)) {
                Object a2 = a(aVar, b(aVar));
                if (sb.length() == 0) {
                    sb.append("{");
                } else {
                    sb.append(",");
                }
                sb.append("\"").append(next).append("\":");
                if (a2 != null) {
                    switch (aVar.fA()) {
                        case 8:
                            sb.append("\"").append(ii.d((byte[]) a2)).append("\"");
                            break;
                        case 9:
                            sb.append("\"").append(ii.e((byte[]) a2)).append("\"");
                            break;
                        case 10:
                            ip.a(sb, (HashMap) a2);
                            break;
                        default:
                            if (!aVar.fF()) {
                                a(sb, aVar, a2);
                                break;
                            } else {
                                a(sb, aVar, (ArrayList<Object>) (ArrayList) a2);
                                break;
                            }
                    }
                } else {
                    sb.append("null");
                }
            }
        }
        if (sb.length() > 0) {
            sb.append("}");
        } else {
            sb.append("{}");
        }
        return sb.toString();
    }
}
