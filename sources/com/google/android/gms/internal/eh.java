package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.SystemClock;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class eh {
    private final Object lq;
    private boolean qL;
    private final ei rA;
    private final LinkedList<a> rB;
    private final String rC;
    private final String rD;
    private long rE;
    private long rF;
    private long rG;
    private long rH;
    private long rI;
    private long rJ;

    private static final class a {
        private long rK = -1;
        private long rL = -1;

        public void bA() {
            this.rL = SystemClock.elapsedRealtime();
        }

        public void bB() {
            this.rK = SystemClock.elapsedRealtime();
        }

        public long bz() {
            return this.rL;
        }

        public Bundle toBundle() {
            Bundle bundle = new Bundle();
            bundle.putLong("topen", this.rK);
            bundle.putLong("tclose", this.rL);
            return bundle;
        }
    }

    public eh(ei eiVar, String str, String str2) {
        this.lq = new Object();
        this.rE = -1;
        this.rF = -1;
        this.qL = false;
        this.rG = -1;
        this.rH = 0;
        this.rI = -1;
        this.rJ = -1;
        this.rA = eiVar;
        this.rC = str;
        this.rD = str2;
        this.rB = new LinkedList<>();
    }

    public eh(String str, String str2) {
        this(ei.bC(), str, str2);
    }

    public void bw() {
        synchronized (this.lq) {
            if (this.rJ != -1 && this.rF == -1) {
                this.rF = SystemClock.elapsedRealtime();
                this.rA.a(this);
            }
            ei eiVar = this.rA;
            ei.bF().bw();
        }
    }

    public void bx() {
        synchronized (this.lq) {
            if (this.rJ != -1) {
                a aVar = new a();
                aVar.bB();
                this.rB.add(aVar);
                this.rH++;
                ei eiVar = this.rA;
                ei.bF().bx();
                this.rA.a(this);
            }
        }
    }

    public void by() {
        synchronized (this.lq) {
            if (this.rJ != -1 && !this.rB.isEmpty()) {
                a last = this.rB.getLast();
                if (last.bz() == -1) {
                    last.bA();
                    this.rA.a(this);
                }
            }
        }
    }

    public void f(aj ajVar) {
        synchronized (this.lq) {
            this.rI = SystemClock.elapsedRealtime();
            ei eiVar = this.rA;
            ei.bF().b(ajVar, this.rI);
        }
    }

    public void j(long j) {
        synchronized (this.lq) {
            this.rJ = j;
            if (this.rJ != -1) {
                this.rA.a(this);
            }
        }
    }

    public void k(long j) {
        synchronized (this.lq) {
            if (this.rJ != -1) {
                this.rE = j;
                this.rA.a(this);
            }
        }
    }

    public void n(boolean z) {
        synchronized (this.lq) {
            if (this.rJ != -1) {
                this.rG = SystemClock.elapsedRealtime();
                if (!z) {
                    this.rF = this.rG;
                    this.rA.a(this);
                }
            }
        }
    }

    public void o(boolean z) {
        synchronized (this.lq) {
            if (this.rJ != -1) {
                this.qL = z;
                this.rA.a(this);
            }
        }
    }

    public Bundle toBundle() {
        Bundle bundle;
        synchronized (this.lq) {
            bundle = new Bundle();
            bundle.putString("seqnum", this.rC);
            bundle.putString("slotid", this.rD);
            bundle.putBoolean("ismediation", this.qL);
            bundle.putLong("treq", this.rI);
            bundle.putLong("tresponse", this.rJ);
            bundle.putLong("timp", this.rF);
            bundle.putLong("tload", this.rG);
            bundle.putLong("pcc", this.rH);
            bundle.putLong("tfetch", this.rE);
            ArrayList arrayList = new ArrayList();
            Iterator it = this.rB.iterator();
            while (it.hasNext()) {
                arrayList.add(((a) it.next()).toBundle());
            }
            bundle.putParcelableArrayList("tclick", arrayList);
        }
        return bundle;
    }
}
