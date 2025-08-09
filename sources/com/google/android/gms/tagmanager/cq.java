package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.c;
import com.google.android.gms.internal.d;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class cq {

    public static class a {
        private final Map<String, d.a> agU;
        private final d.a agV;

        private a(Map<String, d.a> map, d.a aVar) {
            this.agU = map;
            this.agV = aVar;
        }

        public static b mi() {
            return new b();
        }

        public void a(String str, d.a aVar) {
            this.agU.put(str, aVar);
        }

        public Map<String, d.a> mj() {
            return Collections.unmodifiableMap(this.agU);
        }

        public d.a mk() {
            return this.agV;
        }

        public String toString() {
            return "Properties: " + mj() + " pushAfterEvaluate: " + this.agV;
        }
    }

    public static class b {
        private final Map<String, d.a> agU;
        private d.a agV;

        private b() {
            this.agU = new HashMap();
        }

        public b b(String str, d.a aVar) {
            this.agU.put(str, aVar);
            return this;
        }

        public b i(d.a aVar) {
            this.agV = aVar;
            return this;
        }

        public a ml() {
            return new a(this.agU, this.agV);
        }
    }

    public static class c {
        private final String aeR;
        private final List<e> agW;
        private final Map<String, List<a>> agX;
        private final int agY;

        private c(List<e> list, Map<String, List<a>> map, String str, int i) {
            this.agW = Collections.unmodifiableList(list);
            this.agX = Collections.unmodifiableMap(map);
            this.aeR = str;
            this.agY = i;
        }

        public static d mm() {
            return new d();
        }

        public String getVersion() {
            return this.aeR;
        }

        public List<e> mn() {
            return this.agW;
        }

        public Map<String, List<a>> mo() {
            return this.agX;
        }

        public String toString() {
            return "Rules: " + mn() + "  Macros: " + this.agX;
        }
    }

    public static class d {
        private String aeR;
        private final List<e> agW;
        private final Map<String, List<a>> agX;
        private int agY;

        private d() {
            this.agW = new ArrayList();
            this.agX = new HashMap();
            this.aeR = "";
            this.agY = 0;
        }

        public d a(a aVar) {
            String j = dh.j(aVar.mj().get(com.google.android.gms.internal.b.INSTANCE_NAME.toString()));
            List list = this.agX.get(j);
            if (list == null) {
                list = new ArrayList();
                this.agX.put(j, list);
            }
            list.add(aVar);
            return this;
        }

        public d a(e eVar) {
            this.agW.add(eVar);
            return this;
        }

        public d ce(String str) {
            this.aeR = str;
            return this;
        }

        public d du(int i) {
            this.agY = i;
            return this;
        }

        public c mp() {
            return new c(this.agW, this.agX, this.aeR, this.agY);
        }
    }

    public static class e {
        private final List<a> agZ;
        private final List<a> aha;
        private final List<a> ahb;
        private final List<a> ahc;
        private final List<a> ahd;
        private final List<a> ahe;
        private final List<String> ahf;
        private final List<String> ahg;
        private final List<String> ahh;
        private final List<String> ahi;

        private e(List<a> list, List<a> list2, List<a> list3, List<a> list4, List<a> list5, List<a> list6, List<String> list7, List<String> list8, List<String> list9, List<String> list10) {
            this.agZ = Collections.unmodifiableList(list);
            this.aha = Collections.unmodifiableList(list2);
            this.ahb = Collections.unmodifiableList(list3);
            this.ahc = Collections.unmodifiableList(list4);
            this.ahd = Collections.unmodifiableList(list5);
            this.ahe = Collections.unmodifiableList(list6);
            this.ahf = Collections.unmodifiableList(list7);
            this.ahg = Collections.unmodifiableList(list8);
            this.ahh = Collections.unmodifiableList(list9);
            this.ahi = Collections.unmodifiableList(list10);
        }

        public static f mq() {
            return new f();
        }

        public List<a> mA() {
            return this.ahe;
        }

        public List<a> mr() {
            return this.agZ;
        }

        public List<a> ms() {
            return this.aha;
        }

        public List<a> mt() {
            return this.ahb;
        }

        public List<a> mu() {
            return this.ahc;
        }

        public List<a> mv() {
            return this.ahd;
        }

        public List<String> mw() {
            return this.ahf;
        }

        public List<String> mx() {
            return this.ahg;
        }

        public List<String> my() {
            return this.ahh;
        }

        public List<String> mz() {
            return this.ahi;
        }

        public String toString() {
            return "Positive predicates: " + mr() + "  Negative predicates: " + ms() + "  Add tags: " + mt() + "  Remove tags: " + mu() + "  Add macros: " + mv() + "  Remove macros: " + mA();
        }
    }

    public static class f {
        private final List<a> agZ;
        private final List<a> aha;
        private final List<a> ahb;
        private final List<a> ahc;
        private final List<a> ahd;
        private final List<a> ahe;
        private final List<String> ahf;
        private final List<String> ahg;
        private final List<String> ahh;
        private final List<String> ahi;

        private f() {
            this.agZ = new ArrayList();
            this.aha = new ArrayList();
            this.ahb = new ArrayList();
            this.ahc = new ArrayList();
            this.ahd = new ArrayList();
            this.ahe = new ArrayList();
            this.ahf = new ArrayList();
            this.ahg = new ArrayList();
            this.ahh = new ArrayList();
            this.ahi = new ArrayList();
        }

        public f b(a aVar) {
            this.agZ.add(aVar);
            return this;
        }

        public f c(a aVar) {
            this.aha.add(aVar);
            return this;
        }

        public f cf(String str) {
            this.ahh.add(str);
            return this;
        }

        public f cg(String str) {
            this.ahi.add(str);
            return this;
        }

        public f ch(String str) {
            this.ahf.add(str);
            return this;
        }

        public f ci(String str) {
            this.ahg.add(str);
            return this;
        }

        public f d(a aVar) {
            this.ahb.add(aVar);
            return this;
        }

        public f e(a aVar) {
            this.ahc.add(aVar);
            return this;
        }

        public f f(a aVar) {
            this.ahd.add(aVar);
            return this;
        }

        public f g(a aVar) {
            this.ahe.add(aVar);
            return this;
        }

        public e mB() {
            return new e(this.agZ, this.aha, this.ahb, this.ahc, this.ahd, this.ahe, this.ahf, this.ahg, this.ahh, this.ahi);
        }
    }

    public static class g extends Exception {
        public g(String str) {
            super(str);
        }
    }

    private static d.a a(int i, c.f fVar, d.a[] aVarArr, Set<Integer> set) throws g {
        int i2 = 0;
        if (set.contains(Integer.valueOf(i))) {
            cd("Value cycle detected.  Current value reference: " + i + "." + "  Previous value references: " + set + ".");
        }
        d.a aVar = (d.a) a(fVar.eX, i, "values");
        if (aVarArr[i] != null) {
            return aVarArr[i];
        }
        d.a aVar2 = null;
        set.add(Integer.valueOf(i));
        switch (aVar.type) {
            case 1:
            case 5:
            case 6:
            case 8:
                aVar2 = aVar;
                break;
            case 2:
                c.h h = h(aVar);
                aVar2 = g(aVar);
                aVar2.fO = new d.a[h.fz.length];
                int[] iArr = h.fz;
                int length = iArr.length;
                int i3 = 0;
                while (i2 < length) {
                    aVar2.fO[i3] = a(iArr[i2], fVar, aVarArr, set);
                    i2++;
                    i3++;
                }
                break;
            case 3:
                aVar2 = g(aVar);
                c.h h2 = h(aVar);
                if (h2.fA.length != h2.fB.length) {
                    cd("Uneven map keys (" + h2.fA.length + ") and map values (" + h2.fB.length + ")");
                }
                aVar2.fP = new d.a[h2.fA.length];
                aVar2.fQ = new d.a[h2.fA.length];
                int[] iArr2 = h2.fA;
                int length2 = iArr2.length;
                int i4 = 0;
                int i5 = 0;
                while (i4 < length2) {
                    aVar2.fP[i5] = a(iArr2[i4], fVar, aVarArr, set);
                    i4++;
                    i5++;
                }
                int[] iArr3 = h2.fB;
                int length3 = iArr3.length;
                int i6 = 0;
                while (i2 < length3) {
                    aVar2.fQ[i6] = a(iArr3[i2], fVar, aVarArr, set);
                    i2++;
                    i6++;
                }
                break;
            case 4:
                aVar2 = g(aVar);
                aVar2.fR = dh.j(a(h(aVar).fE, fVar, aVarArr, set));
                break;
            case 7:
                aVar2 = g(aVar);
                c.h h3 = h(aVar);
                aVar2.fV = new d.a[h3.fD.length];
                int[] iArr4 = h3.fD;
                int length4 = iArr4.length;
                int i7 = 0;
                while (i2 < length4) {
                    aVar2.fV[i7] = a(iArr4[i2], fVar, aVarArr, set);
                    i2++;
                    i7++;
                }
                break;
        }
        if (aVar2 == null) {
            cd("Invalid value: " + aVar);
        }
        aVarArr[i] = aVar2;
        set.remove(Integer.valueOf(i));
        return aVar2;
    }

    private static a a(c.b bVar, c.f fVar, d.a[] aVarArr, int i) throws g {
        b mi = a.mi();
        for (int valueOf : bVar.eH) {
            c.e eVar = (c.e) a(fVar.eY, Integer.valueOf(valueOf).intValue(), "properties");
            String str = (String) a(fVar.eW, eVar.key, "keys");
            d.a aVar = (d.a) a(aVarArr, eVar.value, "values");
            if (com.google.android.gms.internal.b.PUSH_AFTER_EVALUATE.toString().equals(str)) {
                mi.i(aVar);
            } else {
                mi.b(str, aVar);
            }
        }
        return mi.ml();
    }

    private static e a(c.g gVar, List<a> list, List<a> list2, List<a> list3, c.f fVar) {
        f mq = e.mq();
        for (int valueOf : gVar.fn) {
            mq.b(list3.get(Integer.valueOf(valueOf).intValue()));
        }
        for (int valueOf2 : gVar.fo) {
            mq.c(list3.get(Integer.valueOf(valueOf2).intValue()));
        }
        for (int valueOf3 : gVar.fp) {
            mq.d(list.get(Integer.valueOf(valueOf3).intValue()));
        }
        for (int valueOf4 : gVar.fr) {
            mq.cf(fVar.eX[Integer.valueOf(valueOf4).intValue()].fN);
        }
        for (int valueOf5 : gVar.fq) {
            mq.e(list.get(Integer.valueOf(valueOf5).intValue()));
        }
        for (int valueOf6 : gVar.fs) {
            mq.cg(fVar.eX[Integer.valueOf(valueOf6).intValue()].fN);
        }
        for (int valueOf7 : gVar.ft) {
            mq.f(list2.get(Integer.valueOf(valueOf7).intValue()));
        }
        for (int valueOf8 : gVar.fv) {
            mq.ch(fVar.eX[Integer.valueOf(valueOf8).intValue()].fN);
        }
        for (int valueOf9 : gVar.fu) {
            mq.g(list2.get(Integer.valueOf(valueOf9).intValue()));
        }
        for (int valueOf10 : gVar.fw) {
            mq.ci(fVar.eX[Integer.valueOf(valueOf10).intValue()].fN);
        }
        return mq.mB();
    }

    private static <T> T a(T[] tArr, int i, String str) throws g {
        if (i < 0 || i >= tArr.length) {
            cd("Index out of bounds detected: " + i + " in " + str);
        }
        return tArr[i];
    }

    public static c b(c.f fVar) throws g {
        d.a[] aVarArr = new d.a[fVar.eX.length];
        for (int i = 0; i < fVar.eX.length; i++) {
            a(i, fVar, aVarArr, (Set<Integer>) new HashSet(0));
        }
        d mm = c.mm();
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < fVar.fa.length; i2++) {
            arrayList.add(a(fVar.fa[i2], fVar, aVarArr, i2));
        }
        ArrayList arrayList2 = new ArrayList();
        for (int i3 = 0; i3 < fVar.fb.length; i3++) {
            arrayList2.add(a(fVar.fb[i3], fVar, aVarArr, i3));
        }
        ArrayList arrayList3 = new ArrayList();
        for (int i4 = 0; i4 < fVar.eZ.length; i4++) {
            a a2 = a(fVar.eZ[i4], fVar, aVarArr, i4);
            mm.a(a2);
            arrayList3.add(a2);
        }
        for (c.g a3 : fVar.fc) {
            mm.a(a(a3, arrayList, arrayList3, arrayList2, fVar));
        }
        mm.ce(fVar.fg);
        mm.du(fVar.fl);
        return mm.mp();
    }

    public static void b(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                outputStream.write(bArr, 0, read);
            } else {
                return;
            }
        }
    }

    private static void cd(String str) throws g {
        bh.A(str);
        throw new g(str);
    }

    public static d.a g(d.a aVar) {
        d.a aVar2 = new d.a();
        aVar2.type = aVar.type;
        aVar2.fW = (int[]) aVar.fW.clone();
        if (aVar.fX) {
            aVar2.fX = aVar.fX;
        }
        return aVar2;
    }

    private static c.h h(d.a aVar) throws g {
        if (((c.h) aVar.a(c.h.fx)) == null) {
            cd("Expected a ServingValue and didn't get one. Value is: " + aVar);
        }
        return (c.h) aVar.a(c.h.fx);
    }
}
