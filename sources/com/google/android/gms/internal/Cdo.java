package com.google.android.gms.internal;

import android.content.Context;
import android.os.SystemClock;
import android.text.TextUtils;
import android.webkit.WebView;
import com.google.android.gms.internal.dn;
import com.google.android.gms.internal.dq;
import com.google.android.gms.internal.dt;
import com.google.android.gms.internal.ez;
import org.json.JSONException;

/* renamed from: com.google.android.gms.internal.do  reason: invalid class name */
public class Cdo extends en implements dq.a, ez.a {
    private final bu kz;
    /* access modifiers changed from: private */
    public final ey lL;
    /* access modifiers changed from: private */
    public final Object lq = new Object();
    private final Context mContext;
    private bn nd;
    /* access modifiers changed from: private */
    public dv pA;
    private boolean pB = false;
    private bl pC;
    private br pD;
    /* access modifiers changed from: private */
    public final dn.a pv;
    private final Object pw = new Object();
    private final dt.a px;
    private final l py;
    private en pz;

    /* renamed from: com.google.android.gms.internal.do$a */
    private static final class a extends Exception {
        private final int pH;

        public a(String str, int i) {
            super(str);
            this.pH = i;
        }

        public int getErrorCode() {
            return this.pH;
        }
    }

    public Cdo(Context context, dt.a aVar, l lVar, ey eyVar, bu buVar, dn.a aVar2) {
        this.kz = buVar;
        this.pv = aVar2;
        this.lL = eyVar;
        this.mContext = context;
        this.px = aVar;
        this.py = lVar;
    }

    private am a(dt dtVar) throws a {
        if (this.pA.qg == null) {
            throw new a("The ad response must specify one of the supported ad sizes.", 0);
        }
        String[] split = this.pA.qg.split("x");
        if (split.length != 2) {
            throw new a("Could not parse the ad size from the ad response: " + this.pA.qg, 0);
        }
        try {
            int parseInt = Integer.parseInt(split[0]);
            int parseInt2 = Integer.parseInt(split[1]);
            for (am amVar : dtVar.kR.me) {
                float f = this.mContext.getResources().getDisplayMetrics().density;
                int i = amVar.width == -1 ? (int) (((float) amVar.widthPixels) / f) : amVar.width;
                int i2 = amVar.height == -2 ? (int) (((float) amVar.heightPixels) / f) : amVar.height;
                if (parseInt == i && parseInt2 == i2) {
                    return new am(amVar, dtVar.kR.me);
                }
            }
            throw new a("The ad size from the ad response was not one of the requested sizes: " + this.pA.qg, 0);
        } catch (NumberFormatException e) {
            throw new a("Could not parse the ad size from the ad response: " + this.pA.qg, 0);
        }
    }

    private void a(dt dtVar, long j) throws a {
        synchronized (this.pw) {
            this.pC = new bl(this.mContext, dtVar, this.kz, this.nd);
        }
        this.pD = this.pC.a(j, 60000);
        switch (this.pD.nJ) {
            case 0:
                return;
            case 1:
                throw new a("No fill from any mediation ad networks.", 3);
            default:
                throw new a("Unexpected mediation result: " + this.pD.nJ, 0);
        }
    }

    private void bi() throws a {
        if (this.pA.errorCode != -3) {
            if (TextUtils.isEmpty(this.pA.qb)) {
                throw new a("No fill from ad server.", 3);
            } else if (this.pA.qd) {
                try {
                    this.nd = new bn(this.pA.qb);
                } catch (JSONException e) {
                    throw new a("Could not parse mediation config: " + this.pA.qb, 0);
                }
            }
        }
    }

    private boolean c(long j) throws a {
        long elapsedRealtime = 60000 - (SystemClock.elapsedRealtime() - j);
        if (elapsedRealtime <= 0) {
            return false;
        }
        try {
            this.lq.wait(elapsedRealtime);
            return true;
        } catch (InterruptedException e) {
            throw new a("Ad request cancelled.", -1);
        }
    }

    private void e(long j) throws a {
        eu.ss.post(new Runnable() {
            /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
                return;
             */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void run() {
                /*
                    r7 = this;
                    com.google.android.gms.internal.do r0 = com.google.android.gms.internal.Cdo.this
                    java.lang.Object r6 = r0.lq
                    monitor-enter(r6)
                    com.google.android.gms.internal.do r0 = com.google.android.gms.internal.Cdo.this     // Catch:{ all -> 0x005f }
                    com.google.android.gms.internal.dv r0 = r0.pA     // Catch:{ all -> 0x005f }
                    int r0 = r0.errorCode     // Catch:{ all -> 0x005f }
                    r1 = -2
                    if (r0 == r1) goto L_0x0014
                    monitor-exit(r6)     // Catch:{ all -> 0x005f }
                L_0x0013:
                    return
                L_0x0014:
                    com.google.android.gms.internal.do r0 = com.google.android.gms.internal.Cdo.this     // Catch:{ all -> 0x005f }
                    com.google.android.gms.internal.ey r0 = r0.lL     // Catch:{ all -> 0x005f }
                    com.google.android.gms.internal.ez r0 = r0.bW()     // Catch:{ all -> 0x005f }
                    com.google.android.gms.internal.do r1 = com.google.android.gms.internal.Cdo.this     // Catch:{ all -> 0x005f }
                    r0.a((com.google.android.gms.internal.ez.a) r1)     // Catch:{ all -> 0x005f }
                    com.google.android.gms.internal.do r0 = com.google.android.gms.internal.Cdo.this     // Catch:{ all -> 0x005f }
                    com.google.android.gms.internal.dv r0 = r0.pA     // Catch:{ all -> 0x005f }
                    int r0 = r0.errorCode     // Catch:{ all -> 0x005f }
                    r1 = -3
                    if (r0 != r1) goto L_0x0062
                    java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x005f }
                    r0.<init>()     // Catch:{ all -> 0x005f }
                    java.lang.String r1 = "Loading URL in WebView: "
                    java.lang.StringBuilder r0 = r0.append(r1)     // Catch:{ all -> 0x005f }
                    com.google.android.gms.internal.do r1 = com.google.android.gms.internal.Cdo.this     // Catch:{ all -> 0x005f }
                    com.google.android.gms.internal.dv r1 = r1.pA     // Catch:{ all -> 0x005f }
                    java.lang.String r1 = r1.oy     // Catch:{ all -> 0x005f }
                    java.lang.StringBuilder r0 = r0.append(r1)     // Catch:{ all -> 0x005f }
                    java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x005f }
                    com.google.android.gms.internal.ev.C(r0)     // Catch:{ all -> 0x005f }
                    com.google.android.gms.internal.do r0 = com.google.android.gms.internal.Cdo.this     // Catch:{ all -> 0x005f }
                    com.google.android.gms.internal.ey r0 = r0.lL     // Catch:{ all -> 0x005f }
                    com.google.android.gms.internal.do r1 = com.google.android.gms.internal.Cdo.this     // Catch:{ all -> 0x005f }
                    com.google.android.gms.internal.dv r1 = r1.pA     // Catch:{ all -> 0x005f }
                    java.lang.String r1 = r1.oy     // Catch:{ all -> 0x005f }
                    r0.loadUrl(r1)     // Catch:{ all -> 0x005f }
                L_0x005d:
                    monitor-exit(r6)     // Catch:{ all -> 0x005f }
                    goto L_0x0013
                L_0x005f:
                    r0 = move-exception
                    monitor-exit(r6)     // Catch:{ all -> 0x005f }
                    throw r0
                L_0x0062:
                    java.lang.String r0 = "Loading HTML in WebView."
                    com.google.android.gms.internal.ev.C(r0)     // Catch:{ all -> 0x005f }
                    com.google.android.gms.internal.do r0 = com.google.android.gms.internal.Cdo.this     // Catch:{ all -> 0x005f }
                    com.google.android.gms.internal.ey r0 = r0.lL     // Catch:{ all -> 0x005f }
                    com.google.android.gms.internal.do r1 = com.google.android.gms.internal.Cdo.this     // Catch:{ all -> 0x005f }
                    com.google.android.gms.internal.dv r1 = r1.pA     // Catch:{ all -> 0x005f }
                    java.lang.String r1 = r1.oy     // Catch:{ all -> 0x005f }
                    java.lang.String r1 = com.google.android.gms.internal.ep.v(r1)     // Catch:{ all -> 0x005f }
                    com.google.android.gms.internal.do r2 = com.google.android.gms.internal.Cdo.this     // Catch:{ all -> 0x005f }
                    com.google.android.gms.internal.dv r2 = r2.pA     // Catch:{ all -> 0x005f }
                    java.lang.String r2 = r2.qb     // Catch:{ all -> 0x005f }
                    java.lang.String r3 = "text/html"
                    java.lang.String r4 = "UTF-8"
                    r5 = 0
                    r0.loadDataWithBaseURL(r1, r2, r3, r4, r5)     // Catch:{ all -> 0x005f }
                    goto L_0x005d
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.Cdo.AnonymousClass3.run():void");
            }
        });
        h(j);
    }

    private void g(long j) throws a {
        while (c(j)) {
            if (this.pA != null) {
                synchronized (this.pw) {
                    this.pz = null;
                }
                if (this.pA.errorCode != -2 && this.pA.errorCode != -3) {
                    throw new a("There was a problem getting an ad response. ErrorCode: " + this.pA.errorCode, this.pA.errorCode);
                }
                return;
            }
        }
        throw new a("Timed out waiting for ad response.", 2);
    }

    private void h(long j) throws a {
        while (c(j)) {
            if (this.pB) {
                return;
            }
        }
        throw new a("Timed out waiting for WebView to finish loading.", 2);
    }

    public void a(dv dvVar) {
        synchronized (this.lq) {
            ev.z("Received ad response.");
            this.pA = dvVar;
            this.lq.notify();
        }
    }

    public void a(ey eyVar) {
        synchronized (this.lq) {
            ev.z("WebView finished loading.");
            this.pB = true;
            this.lq.notify();
        }
    }

    /*  JADX ERROR: IndexOutOfBoundsException in pass: RegionMakerVisitor
        java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
        	at java.util.ArrayList.rangeCheck(ArrayList.java:659)
        	at java.util.ArrayList.get(ArrayList.java:435)
        	at jadx.core.dex.nodes.InsnNode.getArg(InsnNode.java:101)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:611)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:561)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:598)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:49)
        */
    public void bc() {
        /*
            r31 = this;
            r0 = r31
            java.lang.Object r0 = r0.lq
            r30 = r0
            monitor-enter(r30)
            java.lang.String r2 = "AdLoaderBackgroundTask started."
            com.google.android.gms.internal.ev.z(r2)     // Catch:{ all -> 0x018c }
            r0 = r31
            com.google.android.gms.internal.l r2 = r0.py     // Catch:{ all -> 0x018c }
            com.google.android.gms.internal.h r2 = r2.y()     // Catch:{ all -> 0x018c }
            r0 = r31
            android.content.Context r3 = r0.mContext     // Catch:{ all -> 0x018c }
            java.lang.String r2 = r2.a((android.content.Context) r3)     // Catch:{ all -> 0x018c }
            com.google.android.gms.internal.dt r12 = new com.google.android.gms.internal.dt     // Catch:{ all -> 0x018c }
            r0 = r31
            com.google.android.gms.internal.dt$a r3 = r0.px     // Catch:{ all -> 0x018c }
            r12.<init>(r3, r2)     // Catch:{ all -> 0x018c }
            r3 = 0
            r6 = -2
            r4 = -1
            long r8 = android.os.SystemClock.elapsedRealtime()     // Catch:{ a -> 0x0052 }
            r0 = r31
            android.content.Context r2 = r0.mContext     // Catch:{ a -> 0x0052 }
            r0 = r31
            com.google.android.gms.internal.en r2 = com.google.android.gms.internal.dq.a(r2, r12, r0)     // Catch:{ a -> 0x0052 }
            r0 = r31
            java.lang.Object r7 = r0.pw     // Catch:{ a -> 0x0052 }
            monitor-enter(r7)     // Catch:{ a -> 0x0052 }
            r0 = r31
            r0.pz = r2     // Catch:{ all -> 0x004f }
            r0 = r31
            com.google.android.gms.internal.en r2 = r0.pz     // Catch:{ all -> 0x004f }
            if (r2 != 0) goto L_0x0152
            com.google.android.gms.internal.do$a r2 = new com.google.android.gms.internal.do$a     // Catch:{ all -> 0x004f }
            java.lang.String r6 = "Could not start the ad request service."
            r8 = 0
            r2.<init>(r6, r8)     // Catch:{ all -> 0x004f }
            throw r2     // Catch:{ all -> 0x004f }
        L_0x004f:
            r2 = move-exception
            monitor-exit(r7)     // Catch:{ all -> 0x004f }
            throw r2     // Catch:{ a -> 0x0052 }
        L_0x0052:
            r2 = move-exception
            int r6 = r2.getErrorCode()     // Catch:{ all -> 0x018c }
            r7 = 3
            if (r6 == r7) goto L_0x005d
            r7 = -1
            if (r6 != r7) goto L_0x0195
        L_0x005d:
            java.lang.String r2 = r2.getMessage()     // Catch:{ all -> 0x018c }
            com.google.android.gms.internal.ev.B(r2)     // Catch:{ all -> 0x018c }
        L_0x0064:
            r0 = r31
            com.google.android.gms.internal.dv r2 = r0.pA     // Catch:{ all -> 0x018c }
            if (r2 != 0) goto L_0x019e
            com.google.android.gms.internal.dv r2 = new com.google.android.gms.internal.dv     // Catch:{ all -> 0x018c }
            r2.<init>(r6)     // Catch:{ all -> 0x018c }
            r0 = r31
            r0.pA = r2     // Catch:{ all -> 0x018c }
        L_0x0073:
            android.os.Handler r2 = com.google.android.gms.internal.eu.ss     // Catch:{ all -> 0x018c }
            com.google.android.gms.internal.do$1 r7 = new com.google.android.gms.internal.do$1     // Catch:{ all -> 0x018c }
            r0 = r31
            r7.<init>()     // Catch:{ all -> 0x018c }
            r2.post(r7)     // Catch:{ all -> 0x018c }
            r24 = r4
            r21 = r3
        L_0x0083:
            r3 = 0
            r0 = r31
            com.google.android.gms.internal.dv r2 = r0.pA     // Catch:{ all -> 0x018c }
            java.lang.String r2 = r2.ql     // Catch:{ all -> 0x018c }
            boolean r2 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x018c }
            if (r2 != 0) goto L_0x01b5
            org.json.JSONObject r29 = new org.json.JSONObject     // Catch:{ Exception -> 0x01af }
            r0 = r31
            com.google.android.gms.internal.dv r2 = r0.pA     // Catch:{ Exception -> 0x01af }
            java.lang.String r2 = r2.ql     // Catch:{ Exception -> 0x01af }
            r0 = r29
            r0.<init>(r2)     // Catch:{ Exception -> 0x01af }
        L_0x009d:
            com.google.android.gms.internal.eg r2 = new com.google.android.gms.internal.eg     // Catch:{ all -> 0x018c }
            com.google.android.gms.internal.aj r3 = r12.pV     // Catch:{ all -> 0x018c }
            r0 = r31
            com.google.android.gms.internal.ey r4 = r0.lL     // Catch:{ all -> 0x018c }
            r0 = r31
            com.google.android.gms.internal.dv r5 = r0.pA     // Catch:{ all -> 0x018c }
            java.util.List<java.lang.String> r5 = r5.nr     // Catch:{ all -> 0x018c }
            r0 = r31
            com.google.android.gms.internal.dv r7 = r0.pA     // Catch:{ all -> 0x018c }
            java.util.List<java.lang.String> r7 = r7.ns     // Catch:{ all -> 0x018c }
            r0 = r31
            com.google.android.gms.internal.dv r8 = r0.pA     // Catch:{ all -> 0x018c }
            java.util.List<java.lang.String> r8 = r8.qf     // Catch:{ all -> 0x018c }
            r0 = r31
            com.google.android.gms.internal.dv r9 = r0.pA     // Catch:{ all -> 0x018c }
            int r9 = r9.orientation     // Catch:{ all -> 0x018c }
            r0 = r31
            com.google.android.gms.internal.dv r10 = r0.pA     // Catch:{ all -> 0x018c }
            long r10 = r10.nv     // Catch:{ all -> 0x018c }
            java.lang.String r12 = r12.pY     // Catch:{ all -> 0x018c }
            r0 = r31
            com.google.android.gms.internal.dv r13 = r0.pA     // Catch:{ all -> 0x018c }
            boolean r13 = r13.qd     // Catch:{ all -> 0x018c }
            r0 = r31
            com.google.android.gms.internal.br r14 = r0.pD     // Catch:{ all -> 0x018c }
            if (r14 == 0) goto L_0x01b9
            r0 = r31
            com.google.android.gms.internal.br r14 = r0.pD     // Catch:{ all -> 0x018c }
            com.google.android.gms.internal.bm r14 = r14.nK     // Catch:{ all -> 0x018c }
        L_0x00d7:
            r0 = r31
            com.google.android.gms.internal.br r15 = r0.pD     // Catch:{ all -> 0x018c }
            if (r15 == 0) goto L_0x01bc
            r0 = r31
            com.google.android.gms.internal.br r15 = r0.pD     // Catch:{ all -> 0x018c }
            com.google.android.gms.internal.bv r15 = r15.nL     // Catch:{ all -> 0x018c }
        L_0x00e3:
            r0 = r31
            com.google.android.gms.internal.br r0 = r0.pD     // Catch:{ all -> 0x018c }
            r16 = r0
            if (r16 == 0) goto L_0x01bf
            r0 = r31
            com.google.android.gms.internal.br r0 = r0.pD     // Catch:{ all -> 0x018c }
            r16 = r0
            r0 = r16
            java.lang.String r0 = r0.nM     // Catch:{ all -> 0x018c }
            r16 = r0
        L_0x00f7:
            r0 = r31
            com.google.android.gms.internal.bn r0 = r0.nd     // Catch:{ all -> 0x018c }
            r17 = r0
            r0 = r31
            com.google.android.gms.internal.br r0 = r0.pD     // Catch:{ all -> 0x018c }
            r18 = r0
            if (r18 == 0) goto L_0x01c3
            r0 = r31
            com.google.android.gms.internal.br r0 = r0.pD     // Catch:{ all -> 0x018c }
            r18 = r0
            r0 = r18
            com.google.android.gms.internal.bp r0 = r0.nN     // Catch:{ all -> 0x018c }
            r18 = r0
        L_0x0111:
            r0 = r31
            com.google.android.gms.internal.dv r0 = r0.pA     // Catch:{ all -> 0x018c }
            r19 = r0
            r0 = r19
            long r0 = r0.qe     // Catch:{ all -> 0x018c }
            r19 = r0
            r0 = r31
            com.google.android.gms.internal.dv r0 = r0.pA     // Catch:{ all -> 0x018c }
            r22 = r0
            r0 = r22
            long r0 = r0.qc     // Catch:{ all -> 0x018c }
            r22 = r0
            r0 = r31
            com.google.android.gms.internal.dv r0 = r0.pA     // Catch:{ all -> 0x018c }
            r26 = r0
            r0 = r26
            long r0 = r0.qh     // Catch:{ all -> 0x018c }
            r26 = r0
            r0 = r31
            com.google.android.gms.internal.dv r0 = r0.pA     // Catch:{ all -> 0x018c }
            r28 = r0
            r0 = r28
            java.lang.String r0 = r0.qi     // Catch:{ all -> 0x018c }
            r28 = r0
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r12, r13, r14, r15, r16, r17, r18, r19, r21, r22, r24, r26, r28, r29)     // Catch:{ all -> 0x018c }
            android.os.Handler r3 = com.google.android.gms.internal.eu.ss     // Catch:{ all -> 0x018c }
            com.google.android.gms.internal.do$2 r4 = new com.google.android.gms.internal.do$2     // Catch:{ all -> 0x018c }
            r0 = r31
            r4.<init>(r2)     // Catch:{ all -> 0x018c }
            r3.post(r4)     // Catch:{ all -> 0x018c }
            monitor-exit(r30)     // Catch:{ all -> 0x018c }
            return
        L_0x0152:
            monitor-exit(r7)     // Catch:{ all -> 0x004f }
            r0 = r31
            r0.g(r8)     // Catch:{ a -> 0x0052 }
            long r4 = android.os.SystemClock.elapsedRealtime()     // Catch:{ a -> 0x0052 }
            r31.bi()     // Catch:{ a -> 0x0052 }
            com.google.android.gms.internal.am r2 = r12.kR     // Catch:{ a -> 0x0052 }
            com.google.android.gms.internal.am[] r2 = r2.me     // Catch:{ a -> 0x0052 }
            if (r2 == 0) goto L_0x016b
            r0 = r31
            com.google.android.gms.internal.am r3 = r0.a((com.google.android.gms.internal.dt) r12)     // Catch:{ a -> 0x0052 }
        L_0x016b:
            r0 = r31
            com.google.android.gms.internal.dv r2 = r0.pA     // Catch:{ a -> 0x0052 }
            boolean r2 = r2.qd     // Catch:{ a -> 0x0052 }
            if (r2 == 0) goto L_0x017e
            r0 = r31
            r0.a(r12, r8)     // Catch:{ a -> 0x0052 }
        L_0x0178:
            r24 = r4
            r21 = r3
            goto L_0x0083
        L_0x017e:
            r0 = r31
            com.google.android.gms.internal.dv r2 = r0.pA     // Catch:{ a -> 0x0052 }
            boolean r2 = r2.qj     // Catch:{ a -> 0x0052 }
            if (r2 == 0) goto L_0x018f
            r0 = r31
            r0.f(r8)     // Catch:{ a -> 0x0052 }
            goto L_0x0178
        L_0x018c:
            r2 = move-exception
            monitor-exit(r30)     // Catch:{ all -> 0x018c }
            throw r2
        L_0x018f:
            r0 = r31
            r0.e(r8)     // Catch:{ a -> 0x0052 }
            goto L_0x0178
        L_0x0195:
            java.lang.String r2 = r2.getMessage()     // Catch:{ all -> 0x018c }
            com.google.android.gms.internal.ev.D(r2)     // Catch:{ all -> 0x018c }
            goto L_0x0064
        L_0x019e:
            com.google.android.gms.internal.dv r2 = new com.google.android.gms.internal.dv     // Catch:{ all -> 0x018c }
            r0 = r31
            com.google.android.gms.internal.dv r7 = r0.pA     // Catch:{ all -> 0x018c }
            long r8 = r7.nv     // Catch:{ all -> 0x018c }
            r2.<init>(r6, r8)     // Catch:{ all -> 0x018c }
            r0 = r31
            r0.pA = r2     // Catch:{ all -> 0x018c }
            goto L_0x0073
        L_0x01af:
            r2 = move-exception
            java.lang.String r4 = "Error parsing the JSON for Active View."
            com.google.android.gms.internal.ev.b(r4, r2)     // Catch:{ all -> 0x018c }
        L_0x01b5:
            r29 = r3
            goto L_0x009d
        L_0x01b9:
            r14 = 0
            goto L_0x00d7
        L_0x01bc:
            r15 = 0
            goto L_0x00e3
        L_0x01bf:
            r16 = 0
            goto L_0x00f7
        L_0x01c3:
            r18 = 0
            goto L_0x0111
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.Cdo.bc():void");
    }

    /* access modifiers changed from: protected */
    public void f(long j) throws a {
        int i;
        int i2;
        am Q = this.lL.Q();
        if (Q.md) {
            i = this.mContext.getResources().getDisplayMetrics().widthPixels;
            i2 = this.mContext.getResources().getDisplayMetrics().heightPixels;
        } else {
            i = Q.widthPixels;
            i2 = Q.heightPixels;
        }
        final dp dpVar = new dp(this, this.lL, i, i2);
        eu.ss.post(new Runnable() {
            public void run() {
                synchronized (Cdo.this.lq) {
                    if (Cdo.this.pA.errorCode == -2) {
                        Cdo.this.lL.bW().a((ez.a) Cdo.this);
                        dpVar.b(Cdo.this.pA);
                    }
                }
            }
        });
        h(j);
        if (dpVar.bl()) {
            ev.z("Ad-Network indicated no fill with passback URL.");
            throw new a("AdNetwork sent passback url", 3);
        } else if (!dpVar.bm()) {
            throw new a("AdNetwork timed out", 2);
        }
    }

    public void onStop() {
        synchronized (this.pw) {
            if (this.pz != null) {
                this.pz.cancel();
            }
            this.lL.stopLoading();
            ep.a((WebView) this.lL);
            if (this.pC != null) {
                this.pC.cancel();
            }
        }
    }
}
