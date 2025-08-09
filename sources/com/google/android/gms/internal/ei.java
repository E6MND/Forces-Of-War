package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class ei {
    private static final ei rM = new ei();
    public static final String rN = rM.rO;
    private final Object lq = new Object();
    public final String rO = ep.bO();
    private final ej rP = new ej(this.rO);
    private BigInteger rQ = BigInteger.ONE;
    private final HashSet<eh> rR = new HashSet<>();
    private final HashMap<String, el> rS = new HashMap<>();
    private boolean rT = false;

    private ei() {
    }

    public static Bundle a(Context context, ek ekVar, String str) {
        return rM.b(context, ekVar, str);
    }

    public static void b(HashSet<eh> hashSet) {
        rM.c(hashSet);
    }

    public static ei bC() {
        return rM;
    }

    public static String bD() {
        return rM.bE();
    }

    public static ej bF() {
        return rM.bG();
    }

    public static boolean bH() {
        return rM.bI();
    }

    public void a(eh ehVar) {
        synchronized (this.lq) {
            this.rR.add(ehVar);
        }
    }

    public void a(String str, el elVar) {
        synchronized (this.lq) {
            this.rS.put(str, elVar);
        }
    }

    public Bundle b(Context context, ek ekVar, String str) {
        Bundle bundle;
        synchronized (this.lq) {
            bundle = new Bundle();
            bundle.putBundle("app", this.rP.b(context, str));
            Bundle bundle2 = new Bundle();
            for (String next : this.rS.keySet()) {
                bundle2.putBundle(next, this.rS.get(next).toBundle());
            }
            bundle.putBundle("slots", bundle2);
            ArrayList arrayList = new ArrayList();
            Iterator<eh> it = this.rR.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().toBundle());
            }
            bundle.putParcelableArrayList("ads", arrayList);
            ekVar.a(this.rR);
            this.rR.clear();
        }
        return bundle;
    }

    public String bE() {
        String bigInteger;
        synchronized (this.lq) {
            bigInteger = this.rQ.toString();
            this.rQ = this.rQ.add(BigInteger.ONE);
        }
        return bigInteger;
    }

    public ej bG() {
        ej ejVar;
        synchronized (this.lq) {
            ejVar = this.rP;
        }
        return ejVar;
    }

    public boolean bI() {
        boolean z;
        synchronized (this.lq) {
            z = this.rT;
            this.rT = true;
        }
        return z;
    }

    public void c(HashSet<eh> hashSet) {
        synchronized (this.lq) {
            this.rR.addAll(hashSet);
        }
    }
}
