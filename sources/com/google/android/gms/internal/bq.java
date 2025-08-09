package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import android.os.SystemClock;
import android.text.TextUtils;
import com.google.android.gms.ads.mediation.customevent.CustomEvent;
import com.google.android.gms.dynamic.e;
import com.google.android.gms.internal.br;

public final class bq implements br.a {
    private final bu kz;
    private final aj lf;
    /* access modifiers changed from: private */
    public final Object lq = new Object();
    private final Context mContext;
    private final String nA;
    private final long nB;
    private final bm nC;
    private final am nD;
    private final ew nE;
    /* access modifiers changed from: private */
    public bv nF;
    /* access modifiers changed from: private */
    public int nG = -2;

    public bq(Context context, String str, bu buVar, bn bnVar, bm bmVar, aj ajVar, am amVar, ew ewVar) {
        this.mContext = context;
        if ("com.google.ads.mediation.customevent.CustomEventAdapter".equals(str)) {
            this.nA = b(bmVar);
        } else {
            this.nA = str;
        }
        this.kz = buVar;
        this.nB = bnVar.nq != -1 ? bnVar.nq : 10000;
        this.nC = bmVar;
        this.lf = ajVar;
        this.nD = amVar;
        this.nE = ewVar;
    }

    private void a(long j, long j2, long j3, long j4) {
        while (this.nG == -2) {
            b(j, j2, j3, j4);
        }
    }

    /* access modifiers changed from: private */
    public void a(bp bpVar) {
        try {
            if (this.nE.sv < 4100000) {
                if (this.nD.md) {
                    this.nF.a(e.h(this.mContext), this.lf, this.nC.no, bpVar);
                } else {
                    this.nF.a(e.h(this.mContext), this.nD, this.lf, this.nC.no, (bw) bpVar);
                }
            } else if (this.nD.md) {
                this.nF.a(e.h(this.mContext), this.lf, this.nC.no, this.nC.ni, (bw) bpVar);
            } else {
                this.nF.a(e.h(this.mContext), this.nD, this.lf, this.nC.no, this.nC.ni, bpVar);
            }
        } catch (RemoteException e) {
            ev.c("Could not request ad from mediation adapter.", e);
            g(5);
        }
    }

    /* access modifiers changed from: private */
    public bv aK() {
        ev.B("Instantiating mediation adapter: " + this.nA);
        try {
            return this.kz.m(this.nA);
        } catch (RemoteException e) {
            ev.a("Could not instantiate mediation adapter: " + this.nA, e);
            return null;
        }
    }

    private String b(bm bmVar) {
        try {
            if (!TextUtils.isEmpty(bmVar.nm)) {
                if (CustomEvent.class.isAssignableFrom(Class.forName(bmVar.nm, false, bq.class.getClassLoader()))) {
                    return "com.google.android.gms.ads.mediation.customevent.CustomEventAdapter";
                }
            }
        } catch (ClassNotFoundException e) {
            ev.D("Could not create custom event adapter.");
        }
        return "com.google.ads.mediation.customevent.CustomEventAdapter";
    }

    private void b(long j, long j2, long j3, long j4) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        long j5 = j2 - (elapsedRealtime - j);
        long j6 = j4 - (elapsedRealtime - j3);
        if (j5 <= 0 || j6 <= 0) {
            ev.B("Timed out waiting for adapter.");
            this.nG = 3;
            return;
        }
        try {
            this.lq.wait(Math.min(j5, j6));
        } catch (InterruptedException e) {
            this.nG = -1;
        }
    }

    public br b(long j, long j2) {
        br brVar;
        synchronized (this.lq) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            final bp bpVar = new bp();
            eu.ss.post(new Runnable() {
                public void run() {
                    synchronized (bq.this.lq) {
                        if (bq.this.nG == -2) {
                            bv unused = bq.this.nF = bq.this.aK();
                            if (bq.this.nF == null) {
                                bq.this.g(4);
                                return;
                            }
                            bpVar.a((br.a) bq.this);
                            bq.this.a(bpVar);
                        }
                    }
                }
            });
            a(elapsedRealtime, this.nB, j, j2);
            brVar = new br(this.nC, this.nF, this.nA, bpVar, this.nG);
        }
        return brVar;
    }

    public void cancel() {
        synchronized (this.lq) {
            try {
                if (this.nF != null) {
                    this.nF.destroy();
                }
            } catch (RemoteException e) {
                ev.c("Could not destroy mediation adapter.", e);
            }
            this.nG = -1;
            this.lq.notify();
        }
    }

    public void g(int i) {
        synchronized (this.lq) {
            this.nG = i;
            this.lq.notify();
        }
    }
}
