package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.WeakHashMap;

public final class ac implements ae {
    private final Object lq = new Object();
    private WeakHashMap<eg, ad> lr = new WeakHashMap<>();
    private ArrayList<ad> ls = new ArrayList<>();

    public ad a(am amVar, eg egVar) {
        ad adVar;
        synchronized (this.lq) {
            if (c(egVar)) {
                adVar = this.lr.get(egVar);
            } else {
                adVar = new ad(amVar, egVar);
                adVar.a((ae) this);
                this.lr.put(egVar, adVar);
                this.ls.add(adVar);
            }
        }
        return adVar;
    }

    public void a(ad adVar) {
        synchronized (this.lq) {
            if (!adVar.au()) {
                this.ls.remove(adVar);
            }
        }
    }

    public boolean c(eg egVar) {
        boolean z;
        synchronized (this.lq) {
            ad adVar = this.lr.get(egVar);
            z = adVar != null && adVar.au();
        }
        return z;
    }

    public void d(eg egVar) {
        synchronized (this.lq) {
            ad adVar = this.lr.get(egVar);
            if (adVar != null) {
                adVar.as();
            }
        }
    }

    public void pause() {
        synchronized (this.lq) {
            Iterator<ad> it = this.ls.iterator();
            while (it.hasNext()) {
                it.next().pause();
            }
        }
    }

    public void resume() {
        synchronized (this.lq) {
            Iterator<ad> it = this.ls.iterator();
            while (it.hasNext()) {
                it.next().resume();
            }
        }
    }

    public void stop() {
        synchronized (this.lq) {
            Iterator<ad> it = this.ls.iterator();
            while (it.hasNext()) {
                it.next().stop();
            }
        }
    }
}
