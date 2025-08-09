package com.google.android.gms.tagmanager;

import android.content.Context;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import com.facebook.internal.AnalyticsEvents;
import com.google.android.gms.internal.c;
import com.google.android.gms.internal.d;
import com.google.android.gms.tagmanager.cq;
import com.google.android.gms.tagmanager.l;
import com.google.android.gms.tagmanager.s;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class cs {
    private static final by<d.a> ahj = new by<>(dh.mY(), true);
    private final DataLayer aer;
    private final cq.c ahk;
    private final ag ahl;
    private final Map<String, aj> ahm;
    private final Map<String, aj> ahn;
    private final Map<String, aj> aho;
    private final k<cq.a, by<d.a>> ahp;
    private final k<String, b> ahq;
    private final Set<cq.e> ahr;
    private final Map<String, c> ahs;
    private volatile String aht;
    private int ahu;

    interface a {
        void a(cq.e eVar, Set<cq.a> set, Set<cq.a> set2, cm cmVar);
    }

    private static class b {
        private d.a agV;
        private by<d.a> ahA;

        public b(by<d.a> byVar, d.a aVar) {
            this.ahA = byVar;
            this.agV = aVar;
        }

        public int getSize() {
            return (this.agV == null ? 0 : this.agV.nU()) + this.ahA.getObject().nU();
        }

        public by<d.a> mE() {
            return this.ahA;
        }

        public d.a mk() {
            return this.agV;
        }
    }

    private static class c {
        private final Map<cq.e, List<cq.a>> ahB = new HashMap();
        private final Map<cq.e, List<cq.a>> ahC = new HashMap();
        private final Map<cq.e, List<String>> ahD = new HashMap();
        private final Map<cq.e, List<String>> ahE = new HashMap();
        private cq.a ahF;
        private final Set<cq.e> ahr = new HashSet();

        public void a(cq.e eVar, cq.a aVar) {
            List list = this.ahB.get(eVar);
            if (list == null) {
                list = new ArrayList();
                this.ahB.put(eVar, list);
            }
            list.add(aVar);
        }

        public void a(cq.e eVar, String str) {
            List list = this.ahD.get(eVar);
            if (list == null) {
                list = new ArrayList();
                this.ahD.put(eVar, list);
            }
            list.add(str);
        }

        public void b(cq.e eVar) {
            this.ahr.add(eVar);
        }

        public void b(cq.e eVar, cq.a aVar) {
            List list = this.ahC.get(eVar);
            if (list == null) {
                list = new ArrayList();
                this.ahC.put(eVar, list);
            }
            list.add(aVar);
        }

        public void b(cq.e eVar, String str) {
            List list = this.ahE.get(eVar);
            if (list == null) {
                list = new ArrayList();
                this.ahE.put(eVar, list);
            }
            list.add(str);
        }

        public void i(cq.a aVar) {
            this.ahF = aVar;
        }

        public Set<cq.e> mF() {
            return this.ahr;
        }

        public Map<cq.e, List<cq.a>> mG() {
            return this.ahB;
        }

        public Map<cq.e, List<String>> mH() {
            return this.ahD;
        }

        public Map<cq.e, List<String>> mI() {
            return this.ahE;
        }

        public Map<cq.e, List<cq.a>> mJ() {
            return this.ahC;
        }

        public cq.a mK() {
            return this.ahF;
        }
    }

    public cs(Context context, cq.c cVar, DataLayer dataLayer, s.a aVar, s.a aVar2, ag agVar) {
        if (cVar == null) {
            throw new NullPointerException("resource cannot be null");
        }
        this.ahk = cVar;
        this.ahr = new HashSet(cVar.mn());
        this.aer = dataLayer;
        this.ahl = agVar;
        this.ahp = new l().a(AccessibilityEventCompat.TYPE_TOUCH_INTERACTION_START, new l.a<cq.a, by<d.a>>() {
            /* renamed from: a */
            public int sizeOf(cq.a aVar, by<d.a> byVar) {
                return byVar.getObject().nU();
            }
        });
        this.ahq = new l().a(AccessibilityEventCompat.TYPE_TOUCH_INTERACTION_START, new l.a<String, b>() {
            /* renamed from: a */
            public int sizeOf(String str, b bVar) {
                return str.length() + bVar.getSize();
            }
        });
        this.ahm = new HashMap();
        b(new i(context));
        b(new s(aVar2));
        b(new w(dataLayer));
        b(new di(context, dataLayer));
        this.ahn = new HashMap();
        c(new q());
        c(new ad());
        c(new ae());
        c(new al());
        c(new am());
        c(new bd());
        c(new be());
        c(new ch());
        c(new db());
        this.aho = new HashMap();
        a(new b(context));
        a(new c(context));
        a(new e(context));
        a(new f(context));
        a(new g(context));
        a(new h(context));
        a(new m());
        a(new p(this.ahk.getVersion()));
        a(new s(aVar));
        a(new u(dataLayer));
        a(new z(context));
        a(new aa());
        a(new ac());
        a(new ah(this));
        a(new an());
        a(new ao());
        a(new ax(context));
        a(new az());
        a(new bc());
        a(new bk(context));
        a(new bz());
        a(new cb());
        a(new ce());
        a(new cg());
        a(new ci(context));
        a(new ct());
        a(new cu());
        a(new dd());
        this.ahs = new HashMap();
        for (cq.e next : this.ahr) {
            if (agVar.lF()) {
                a(next.mv(), next.mw(), "add macro");
                a(next.mA(), next.mx(), "remove macro");
                a(next.mt(), next.my(), "add tag");
                a(next.mu(), next.mz(), "remove tag");
            }
            for (int i = 0; i < next.mv().size(); i++) {
                cq.a aVar3 = next.mv().get(i);
                String str = AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
                if (agVar.lF() && i < next.mw().size()) {
                    str = next.mw().get(i);
                }
                c d = d(this.ahs, h(aVar3));
                d.b(next);
                d.a(next, aVar3);
                d.a(next, str);
            }
            for (int i2 = 0; i2 < next.mA().size(); i2++) {
                cq.a aVar4 = next.mA().get(i2);
                String str2 = AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
                if (agVar.lF() && i2 < next.mx().size()) {
                    str2 = next.mx().get(i2);
                }
                c d2 = d(this.ahs, h(aVar4));
                d2.b(next);
                d2.b(next, aVar4);
                d2.b(next, str2);
            }
        }
        for (Map.Entry next2 : this.ahk.mo().entrySet()) {
            for (cq.a aVar5 : (List) next2.getValue()) {
                if (!dh.n(aVar5.mj().get(com.google.android.gms.internal.b.NOT_DEFAULT_MACRO.toString())).booleanValue()) {
                    d(this.ahs, (String) next2.getKey()).i(aVar5);
                }
            }
        }
    }

    private by<d.a> a(d.a aVar, Set<String> set, dj djVar) {
        if (!aVar.fX) {
            return new by<>(aVar, true);
        }
        switch (aVar.type) {
            case 2:
                d.a g = cq.g(aVar);
                g.fO = new d.a[aVar.fO.length];
                for (int i = 0; i < aVar.fO.length; i++) {
                    by<d.a> a2 = a(aVar.fO[i], set, djVar.dq(i));
                    if (a2 == ahj) {
                        return ahj;
                    }
                    g.fO[i] = a2.getObject();
                }
                return new by<>(g, false);
            case 3:
                d.a g2 = cq.g(aVar);
                if (aVar.fP.length != aVar.fQ.length) {
                    bh.A("Invalid serving value: " + aVar.toString());
                    return ahj;
                }
                g2.fP = new d.a[aVar.fP.length];
                g2.fQ = new d.a[aVar.fP.length];
                for (int i2 = 0; i2 < aVar.fP.length; i2++) {
                    by<d.a> a3 = a(aVar.fP[i2], set, djVar.dr(i2));
                    by<d.a> a4 = a(aVar.fQ[i2], set, djVar.ds(i2));
                    if (a3 == ahj || a4 == ahj) {
                        return ahj;
                    }
                    g2.fP[i2] = a3.getObject();
                    g2.fQ[i2] = a4.getObject();
                }
                return new by<>(g2, false);
            case 4:
                if (set.contains(aVar.fR)) {
                    bh.A("Macro cycle detected.  Current macro reference: " + aVar.fR + "." + "  Previous macro references: " + set.toString() + ".");
                    return ahj;
                }
                set.add(aVar.fR);
                by<d.a> a5 = dk.a(a(aVar.fR, set, djVar.lU()), aVar.fW);
                set.remove(aVar.fR);
                return a5;
            case 7:
                d.a g3 = cq.g(aVar);
                g3.fV = new d.a[aVar.fV.length];
                for (int i3 = 0; i3 < aVar.fV.length; i3++) {
                    by<d.a> a6 = a(aVar.fV[i3], set, djVar.dt(i3));
                    if (a6 == ahj) {
                        return ahj;
                    }
                    g3.fV[i3] = a6.getObject();
                }
                return new by<>(g3, false);
            default:
                bh.A("Unknown type: " + aVar.type);
                return ahj;
        }
    }

    private by<d.a> a(String str, Set<String> set, bj bjVar) {
        cq.a aVar;
        this.ahu++;
        b bVar = this.ahq.get(str);
        if (bVar == null || this.ahl.lF()) {
            c cVar = this.ahs.get(str);
            if (cVar == null) {
                bh.A(mD() + "Invalid macro: " + str);
                this.ahu--;
                return ahj;
            }
            by<Set<cq.a>> a2 = a(str, cVar.mF(), cVar.mG(), cVar.mH(), cVar.mJ(), cVar.mI(), set, bjVar.lw());
            if (a2.getObject().isEmpty()) {
                aVar = cVar.mK();
            } else {
                if (a2.getObject().size() > 1) {
                    bh.D(mD() + "Multiple macros active for macroName " + str);
                }
                aVar = (cq.a) a2.getObject().iterator().next();
            }
            if (aVar == null) {
                this.ahu--;
                return ahj;
            }
            by<d.a> a3 = a(this.aho, aVar, set, bjVar.lL());
            by<d.a> byVar = a3 == ahj ? ahj : new by<>(a3.getObject(), a2.lV() && a3.lV());
            d.a mk = aVar.mk();
            if (byVar.lV()) {
                this.ahq.e(str, new b(byVar, mk));
            }
            a(mk, set);
            this.ahu--;
            return byVar;
        }
        a(bVar.mk(), set);
        this.ahu--;
        return bVar.mE();
    }

    private by<d.a> a(Map<String, aj> map, cq.a aVar, Set<String> set, cj cjVar) {
        boolean z;
        boolean z2 = true;
        d.a aVar2 = aVar.mj().get(com.google.android.gms.internal.b.FUNCTION.toString());
        if (aVar2 == null) {
            bh.A("No function id in properties");
            return ahj;
        }
        String str = aVar2.fS;
        aj ajVar = map.get(str);
        if (ajVar == null) {
            bh.A(str + " has no backing implementation.");
            return ahj;
        }
        by<d.a> byVar = this.ahp.get(aVar);
        if (byVar != null && !this.ahl.lF()) {
            return byVar;
        }
        HashMap hashMap = new HashMap();
        boolean z3 = true;
        for (Map.Entry next : aVar.mj().entrySet()) {
            by<d.a> a2 = a((d.a) next.getValue(), set, cjVar.bZ((String) next.getKey()).e((d.a) next.getValue()));
            if (a2 == ahj) {
                return ahj;
            }
            if (a2.lV()) {
                aVar.a((String) next.getKey(), a2.getObject());
                z = z3;
            } else {
                z = false;
            }
            hashMap.put(next.getKey(), a2.getObject());
            z3 = z;
        }
        if (!ajVar.a(hashMap.keySet())) {
            bh.A("Incorrect keys for function " + str + " required " + ajVar.lH() + " had " + hashMap.keySet());
            return ahj;
        }
        if (!z3 || !ajVar.lc()) {
            z2 = false;
        }
        by<d.a> byVar2 = new by<>(ajVar.w(hashMap), z2);
        if (z2) {
            this.ahp.e(aVar, byVar2);
        }
        cjVar.d(byVar2.getObject());
        return byVar2;
    }

    private by<Set<cq.a>> a(Set<cq.e> set, Set<String> set2, a aVar, cr crVar) {
        HashSet hashSet = new HashSet();
        HashSet hashSet2 = new HashSet();
        boolean z = true;
        for (cq.e next : set) {
            cm lT = crVar.lT();
            by<Boolean> a2 = a(next, set2, lT);
            if (a2.getObject().booleanValue()) {
                aVar.a(next, hashSet, hashSet2, lT);
            }
            z = z && a2.lV();
        }
        hashSet.removeAll(hashSet2);
        crVar.b(hashSet);
        return new by<>(hashSet, z);
    }

    private void a(d.a aVar, Set<String> set) {
        by<d.a> a2;
        if (aVar != null && (a2 = a(aVar, set, (dj) new bw())) != ahj) {
            Object o = dh.o(a2.getObject());
            if (o instanceof Map) {
                this.aer.push((Map) o);
            } else if (o instanceof List) {
                for (Object next : (List) o) {
                    if (next instanceof Map) {
                        this.aer.push((Map) next);
                    } else {
                        bh.D("pushAfterEvaluate: value not a Map");
                    }
                }
            } else {
                bh.D("pushAfterEvaluate: value not a Map or List");
            }
        }
    }

    private static void a(List<cq.a> list, List<String> list2, String str) {
        if (list.size() != list2.size()) {
            bh.B("Invalid resource: imbalance of rule names of functions for " + str + " operation. Using default rule name instead");
        }
    }

    private static void a(Map<String, aj> map, aj ajVar) {
        if (map.containsKey(ajVar.lG())) {
            throw new IllegalArgumentException("Duplicate function type name: " + ajVar.lG());
        }
        map.put(ajVar.lG(), ajVar);
    }

    private static c d(Map<String, c> map, String str) {
        c cVar = map.get(str);
        if (cVar != null) {
            return cVar;
        }
        c cVar2 = new c();
        map.put(str, cVar2);
        return cVar2;
    }

    private static String h(cq.a aVar) {
        return dh.j(aVar.mj().get(com.google.android.gms.internal.b.INSTANCE_NAME.toString()));
    }

    private String mD() {
        if (this.ahu <= 1) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(Integer.toString(this.ahu));
        for (int i = 2; i < this.ahu; i++) {
            sb.append(' ');
        }
        sb.append(": ");
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public by<Boolean> a(cq.a aVar, Set<String> set, cj cjVar) {
        by<d.a> a2 = a(this.ahn, aVar, set, cjVar);
        Boolean n = dh.n(a2.getObject());
        cjVar.d(dh.r(n));
        return new by<>(n, a2.lV());
    }

    /* access modifiers changed from: package-private */
    public by<Boolean> a(cq.e eVar, Set<String> set, cm cmVar) {
        boolean z;
        boolean z2 = true;
        for (cq.a a2 : eVar.ms()) {
            by<Boolean> a3 = a(a2, set, cmVar.lN());
            if (a3.getObject().booleanValue()) {
                cmVar.f(dh.r(false));
                return new by<>(false, a3.lV());
            }
            z2 = z && a3.lV();
        }
        for (cq.a a4 : eVar.mr()) {
            by<Boolean> a5 = a(a4, set, cmVar.lO());
            if (!a5.getObject().booleanValue()) {
                cmVar.f(dh.r(false));
                return new by<>(false, a5.lV());
            }
            z = z && a5.lV();
        }
        cmVar.f(dh.r(true));
        return new by<>(true, z);
    }

    /* access modifiers changed from: package-private */
    public by<Set<cq.a>> a(String str, Set<cq.e> set, Map<cq.e, List<cq.a>> map, Map<cq.e, List<String>> map2, Map<cq.e, List<cq.a>> map3, Map<cq.e, List<String>> map4, Set<String> set2, cr crVar) {
        final Map<cq.e, List<cq.a>> map5 = map;
        final Map<cq.e, List<String>> map6 = map2;
        final Map<cq.e, List<cq.a>> map7 = map3;
        final Map<cq.e, List<String>> map8 = map4;
        return a(set, set2, (a) new a() {
            public void a(cq.e eVar, Set<cq.a> set, Set<cq.a> set2, cm cmVar) {
                List list = (List) map5.get(eVar);
                List list2 = (List) map6.get(eVar);
                if (list != null) {
                    set.addAll(list);
                    cmVar.lP().b(list, list2);
                }
                List list3 = (List) map7.get(eVar);
                List list4 = (List) map8.get(eVar);
                if (list3 != null) {
                    set2.addAll(list3);
                    cmVar.lQ().b(list3, list4);
                }
            }
        }, crVar);
    }

    /* access modifiers changed from: package-private */
    public by<Set<cq.a>> a(Set<cq.e> set, cr crVar) {
        return a(set, (Set<String>) new HashSet(), (a) new a() {
            public void a(cq.e eVar, Set<cq.a> set, Set<cq.a> set2, cm cmVar) {
                set.addAll(eVar.mt());
                set2.addAll(eVar.mu());
                cmVar.lR().b(eVar.mt(), eVar.my());
                cmVar.lS().b(eVar.mu(), eVar.mz());
            }
        }, crVar);
    }

    /* access modifiers changed from: package-private */
    public void a(aj ajVar) {
        a(this.aho, ajVar);
    }

    /* access modifiers changed from: package-private */
    public void b(aj ajVar) {
        a(this.ahm, ajVar);
    }

    public synchronized void bH(String str) {
        ck(str);
        af bT = this.ahl.bT(str);
        t lD = bT.lD();
        for (cq.a a2 : a(this.ahr, lD.lw()).getObject()) {
            a(this.ahm, a2, (Set<String>) new HashSet(), lD.lv());
        }
        bT.lE();
        ck((String) null);
    }

    /* access modifiers changed from: package-private */
    public void c(aj ajVar) {
        a(this.ahn, ajVar);
    }

    public by<d.a> cj(String str) {
        this.ahu = 0;
        af bS = this.ahl.bS(str);
        by<d.a> a2 = a(str, (Set<String>) new HashSet(), bS.lC());
        bS.lE();
        return a2;
    }

    /* access modifiers changed from: package-private */
    public synchronized void ck(String str) {
        this.aht = str;
    }

    public synchronized void h(List<c.i> list) {
        for (c.i next : list) {
            if (next.name == null || !next.name.startsWith("gaExperiment:")) {
                bh.C("Ignored supplemental: " + next);
            } else {
                ai.a(this.aer, next);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized String mC() {
        return this.aht;
    }
}
