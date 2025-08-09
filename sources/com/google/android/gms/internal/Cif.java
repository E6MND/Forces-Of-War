package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.internal.hz;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/* renamed from: com.google.android.gms.internal.if  reason: invalid class name */
public class Cif extends hz implements SafeParcelable {
    public static final ig CREATOR = new ig();
    private final ic Hg;
    private final Parcel Hn;
    private final int Ho;
    private int Hp;
    private int Hq;
    private final String mClassName;
    private final int xJ;

    Cif(int i, Parcel parcel, ic icVar) {
        this.xJ = i;
        this.Hn = (Parcel) hn.f(parcel);
        this.Ho = 2;
        this.Hg = icVar;
        if (this.Hg == null) {
            this.mClassName = null;
        } else {
            this.mClassName = this.Hg.fR();
        }
        this.Hp = 2;
    }

    private Cif(SafeParcelable safeParcelable, ic icVar, String str) {
        this.xJ = 1;
        this.Hn = Parcel.obtain();
        safeParcelable.writeToParcel(this.Hn, 0);
        this.Ho = 1;
        this.Hg = (ic) hn.f(icVar);
        this.mClassName = (String) hn.f(str);
        this.Hp = 2;
    }

    public static <T extends hz & SafeParcelable> Cif a(T t) {
        String canonicalName = t.getClass().getCanonicalName();
        return new Cif((SafeParcelable) t, b((hz) t), canonicalName);
    }

    private static void a(ic icVar, hz hzVar) {
        Class<?> cls = hzVar.getClass();
        if (!icVar.b((Class<? extends hz>) cls)) {
            HashMap<String, hz.a<?, ?>> fB = hzVar.fB();
            icVar.a(cls, hzVar.fB());
            for (String str : fB.keySet()) {
                hz.a aVar = fB.get(str);
                Class<? extends hz> fJ = aVar.fJ();
                if (fJ != null) {
                    try {
                        a(icVar, (hz) fJ.newInstance());
                    } catch (InstantiationException e) {
                        throw new IllegalStateException("Could not instantiate an object of type " + aVar.fJ().getCanonicalName(), e);
                    } catch (IllegalAccessException e2) {
                        throw new IllegalStateException("Could not access object of type " + aVar.fJ().getCanonicalName(), e2);
                    }
                }
            }
        }
    }

    private void a(StringBuilder sb, int i, Object obj) {
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                sb.append(obj);
                return;
            case 7:
                sb.append("\"").append(io.aK(obj.toString())).append("\"");
                return;
            case 8:
                sb.append("\"").append(ii.d((byte[]) obj)).append("\"");
                return;
            case 9:
                sb.append("\"").append(ii.e((byte[]) obj));
                sb.append("\"");
                return;
            case 10:
                ip.a(sb, (HashMap) obj);
                return;
            case 11:
                throw new IllegalArgumentException("Method does not accept concrete type.");
            default:
                throw new IllegalArgumentException("Unknown type = " + i);
        }
    }

    private void a(StringBuilder sb, hz.a<?, ?> aVar, Parcel parcel, int i) {
        switch (aVar.fA()) {
            case 0:
                b(sb, aVar, (Object) a(aVar, Integer.valueOf(a.g(parcel, i))));
                return;
            case 1:
                b(sb, aVar, (Object) a(aVar, a.k(parcel, i)));
                return;
            case 2:
                b(sb, aVar, (Object) a(aVar, Long.valueOf(a.i(parcel, i))));
                return;
            case 3:
                b(sb, aVar, (Object) a(aVar, Float.valueOf(a.l(parcel, i))));
                return;
            case 4:
                b(sb, aVar, (Object) a(aVar, Double.valueOf(a.m(parcel, i))));
                return;
            case 5:
                b(sb, aVar, (Object) a(aVar, a.n(parcel, i)));
                return;
            case 6:
                b(sb, aVar, (Object) a(aVar, Boolean.valueOf(a.c(parcel, i))));
                return;
            case 7:
                b(sb, aVar, (Object) a(aVar, a.o(parcel, i)));
                return;
            case 8:
            case 9:
                b(sb, aVar, (Object) a(aVar, a.r(parcel, i)));
                return;
            case 10:
                b(sb, aVar, (Object) a(aVar, d(a.q(parcel, i))));
                return;
            case 11:
                throw new IllegalArgumentException("Method does not accept concrete type.");
            default:
                throw new IllegalArgumentException("Unknown field out type = " + aVar.fA());
        }
    }

    private void a(StringBuilder sb, String str, hz.a<?, ?> aVar, Parcel parcel, int i) {
        sb.append("\"").append(str).append("\":");
        if (aVar.fL()) {
            a(sb, aVar, parcel, i);
        } else {
            b(sb, aVar, parcel, i);
        }
    }

    private void a(StringBuilder sb, HashMap<String, hz.a<?, ?>> hashMap, Parcel parcel) {
        HashMap<Integer, Map.Entry<String, hz.a<?, ?>>> b = b(hashMap);
        sb.append('{');
        int B = a.B(parcel);
        boolean z = false;
        while (parcel.dataPosition() < B) {
            int A = a.A(parcel);
            Map.Entry entry = b.get(Integer.valueOf(a.ar(A)));
            if (entry != null) {
                if (z) {
                    sb.append(",");
                }
                a(sb, (String) entry.getKey(), (hz.a) entry.getValue(), parcel, A);
                z = true;
            }
        }
        if (parcel.dataPosition() != B) {
            throw new a.C0014a("Overread allowed size end=" + B, parcel);
        }
        sb.append('}');
    }

    private static ic b(hz hzVar) {
        ic icVar = new ic(hzVar.getClass());
        a(icVar, hzVar);
        icVar.fP();
        icVar.fO();
        return icVar;
    }

    private static HashMap<Integer, Map.Entry<String, hz.a<?, ?>>> b(HashMap<String, hz.a<?, ?>> hashMap) {
        HashMap<Integer, Map.Entry<String, hz.a<?, ?>>> hashMap2 = new HashMap<>();
        for (Map.Entry next : hashMap.entrySet()) {
            hashMap2.put(Integer.valueOf(((hz.a) next.getValue()).fI()), next);
        }
        return hashMap2;
    }

    private void b(StringBuilder sb, hz.a<?, ?> aVar, Parcel parcel, int i) {
        if (aVar.fG()) {
            sb.append("[");
            switch (aVar.fA()) {
                case 0:
                    ih.a(sb, a.u(parcel, i));
                    break;
                case 1:
                    ih.a(sb, (T[]) a.w(parcel, i));
                    break;
                case 2:
                    ih.a(sb, a.v(parcel, i));
                    break;
                case 3:
                    ih.a(sb, a.x(parcel, i));
                    break;
                case 4:
                    ih.a(sb, a.y(parcel, i));
                    break;
                case 5:
                    ih.a(sb, (T[]) a.z(parcel, i));
                    break;
                case 6:
                    ih.a(sb, a.t(parcel, i));
                    break;
                case 7:
                    ih.a(sb, a.A(parcel, i));
                    break;
                case 8:
                case 9:
                case 10:
                    throw new UnsupportedOperationException("List of type BASE64, BASE64_URL_SAFE, or STRING_MAP is not supported");
                case 11:
                    Parcel[] D = a.D(parcel, i);
                    int length = D.length;
                    for (int i2 = 0; i2 < length; i2++) {
                        if (i2 > 0) {
                            sb.append(",");
                        }
                        D[i2].setDataPosition(0);
                        a(sb, aVar.fN(), D[i2]);
                    }
                    break;
                default:
                    throw new IllegalStateException("Unknown field type out.");
            }
            sb.append("]");
            return;
        }
        switch (aVar.fA()) {
            case 0:
                sb.append(a.g(parcel, i));
                return;
            case 1:
                sb.append(a.k(parcel, i));
                return;
            case 2:
                sb.append(a.i(parcel, i));
                return;
            case 3:
                sb.append(a.l(parcel, i));
                return;
            case 4:
                sb.append(a.m(parcel, i));
                return;
            case 5:
                sb.append(a.n(parcel, i));
                return;
            case 6:
                sb.append(a.c(parcel, i));
                return;
            case 7:
                sb.append("\"").append(io.aK(a.o(parcel, i))).append("\"");
                return;
            case 8:
                sb.append("\"").append(ii.d(a.r(parcel, i))).append("\"");
                return;
            case 9:
                sb.append("\"").append(ii.e(a.r(parcel, i)));
                sb.append("\"");
                return;
            case 10:
                Bundle q = a.q(parcel, i);
                Set<String> keySet = q.keySet();
                keySet.size();
                sb.append("{");
                boolean z = true;
                for (String str : keySet) {
                    if (!z) {
                        sb.append(",");
                    }
                    sb.append("\"").append(str).append("\"");
                    sb.append(":");
                    sb.append("\"").append(io.aK(q.getString(str))).append("\"");
                    z = false;
                }
                sb.append("}");
                return;
            case 11:
                Parcel C = a.C(parcel, i);
                C.setDataPosition(0);
                a(sb, aVar.fN(), C);
                return;
            default:
                throw new IllegalStateException("Unknown field type out");
        }
    }

    private void b(StringBuilder sb, hz.a<?, ?> aVar, Object obj) {
        if (aVar.fF()) {
            b(sb, aVar, (ArrayList<?>) (ArrayList) obj);
        } else {
            a(sb, aVar.fz(), obj);
        }
    }

    private void b(StringBuilder sb, hz.a<?, ?> aVar, ArrayList<?> arrayList) {
        sb.append("[");
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                sb.append(",");
            }
            a(sb, aVar.fz(), (Object) arrayList.get(i));
        }
        sb.append("]");
    }

    public static HashMap<String, String> d(Bundle bundle) {
        HashMap<String, String> hashMap = new HashMap<>();
        for (String str : bundle.keySet()) {
            hashMap.put(str, bundle.getString(str));
        }
        return hashMap;
    }

    /* access modifiers changed from: protected */
    public Object aF(String str) {
        throw new UnsupportedOperationException("Converting to JSON does not require this method.");
    }

    /* access modifiers changed from: protected */
    public boolean aG(String str) {
        throw new UnsupportedOperationException("Converting to JSON does not require this method.");
    }

    public int describeContents() {
        ig igVar = CREATOR;
        return 0;
    }

    public HashMap<String, hz.a<?, ?>> fB() {
        if (this.Hg == null) {
            return null;
        }
        return this.Hg.aJ(this.mClassName);
    }

    public Parcel fT() {
        switch (this.Hp) {
            case 0:
                this.Hq = b.C(this.Hn);
                b.G(this.Hn, this.Hq);
                this.Hp = 2;
                break;
            case 1:
                b.G(this.Hn, this.Hq);
                this.Hp = 2;
                break;
        }
        return this.Hn;
    }

    /* access modifiers changed from: package-private */
    public ic fU() {
        switch (this.Ho) {
            case 0:
                return null;
            case 1:
                return this.Hg;
            case 2:
                return this.Hg;
            default:
                throw new IllegalStateException("Invalid creation type: " + this.Ho);
        }
    }

    public int getVersionCode() {
        return this.xJ;
    }

    public String toString() {
        hn.b(this.Hg, (Object) "Cannot convert to JSON on client side.");
        Parcel fT = fT();
        fT.setDataPosition(0);
        StringBuilder sb = new StringBuilder(100);
        a(sb, this.Hg.aJ(this.mClassName), fT);
        return sb.toString();
    }

    public void writeToParcel(Parcel out, int flags) {
        ig igVar = CREATOR;
        ig.a(this, out, flags);
    }
}
