package com.google.android.gms.tagmanager;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.a;
import com.google.android.gms.internal.c;
import com.google.android.gms.internal.ik;
import com.google.android.gms.internal.im;
import com.google.android.gms.internal.lf;
import com.google.android.gms.tagmanager.bg;
import com.google.android.gms.tagmanager.cd;
import com.google.android.gms.tagmanager.cq;
import com.google.android.gms.tagmanager.n;

class o extends a.C0011a<ContainerHolder> {
    private final Looper DC;
    private final TagManager aeC;
    private final d aeF;
    /* access modifiers changed from: private */
    public final cf aeG;
    private final int aeH;
    private f aeI;
    /* access modifiers changed from: private */
    public volatile n aeJ;
    /* access modifiers changed from: private */
    public volatile boolean aeK;
    /* access modifiers changed from: private */
    public c.j aeL;
    private String aeM;
    private e aeN;
    private a aeO;
    /* access modifiers changed from: private */
    public final ik aec;
    private final String aeq;
    /* access modifiers changed from: private */
    public long aev;
    private final Context mContext;

    interface a {
        boolean b(Container container);
    }

    private class b implements bg<lf.a> {
        private b() {
        }

        /* renamed from: a */
        public void i(lf.a aVar) {
            c.j jVar;
            if (aVar.aiE != null) {
                jVar = aVar.aiE;
            } else {
                c.f fVar = aVar.fK;
                jVar = new c.j();
                jVar.fK = fVar;
                jVar.fJ = null;
                jVar.fL = fVar.fg;
            }
            o.this.a(jVar, aVar.aiD, true);
        }

        public void a(bg.a aVar) {
            if (!o.this.aeK) {
                o.this.w(0);
            }
        }

        public void lq() {
        }
    }

    private class c implements bg<c.j> {
        private c() {
        }

        public void a(bg.a aVar) {
            if (o.this.aeJ != null) {
                o.this.a(o.this.aeJ);
            } else {
                o.this.a(o.this.c(Status.En));
            }
            o.this.w(3600000);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
            return;
         */
        /* renamed from: b */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void i(com.google.android.gms.internal.c.j r6) {
            /*
                r5 = this;
                com.google.android.gms.tagmanager.o r1 = com.google.android.gms.tagmanager.o.this
                monitor-enter(r1)
                com.google.android.gms.internal.c$f r0 = r6.fK     // Catch:{ all -> 0x0065 }
                if (r0 != 0) goto L_0x002a
                com.google.android.gms.tagmanager.o r0 = com.google.android.gms.tagmanager.o.this     // Catch:{ all -> 0x0065 }
                com.google.android.gms.internal.c$j r0 = r0.aeL     // Catch:{ all -> 0x0065 }
                com.google.android.gms.internal.c$f r0 = r0.fK     // Catch:{ all -> 0x0065 }
                if (r0 != 0) goto L_0x0020
                java.lang.String r0 = "Current resource is null; network resource is also null"
                com.google.android.gms.tagmanager.bh.A(r0)     // Catch:{ all -> 0x0065 }
                com.google.android.gms.tagmanager.o r0 = com.google.android.gms.tagmanager.o.this     // Catch:{ all -> 0x0065 }
                r2 = 3600000(0x36ee80, double:1.7786363E-317)
                r0.w(r2)     // Catch:{ all -> 0x0065 }
                monitor-exit(r1)     // Catch:{ all -> 0x0065 }
            L_0x001f:
                return
            L_0x0020:
                com.google.android.gms.tagmanager.o r0 = com.google.android.gms.tagmanager.o.this     // Catch:{ all -> 0x0065 }
                com.google.android.gms.internal.c$j r0 = r0.aeL     // Catch:{ all -> 0x0065 }
                com.google.android.gms.internal.c$f r0 = r0.fK     // Catch:{ all -> 0x0065 }
                r6.fK = r0     // Catch:{ all -> 0x0065 }
            L_0x002a:
                com.google.android.gms.tagmanager.o r0 = com.google.android.gms.tagmanager.o.this     // Catch:{ all -> 0x0065 }
                com.google.android.gms.tagmanager.o r2 = com.google.android.gms.tagmanager.o.this     // Catch:{ all -> 0x0065 }
                com.google.android.gms.internal.ik r2 = r2.aec     // Catch:{ all -> 0x0065 }
                long r2 = r2.currentTimeMillis()     // Catch:{ all -> 0x0065 }
                r4 = 0
                r0.a(r6, r2, r4)     // Catch:{ all -> 0x0065 }
                java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0065 }
                r0.<init>()     // Catch:{ all -> 0x0065 }
                java.lang.String r2 = "setting refresh time to current time: "
                java.lang.StringBuilder r0 = r0.append(r2)     // Catch:{ all -> 0x0065 }
                com.google.android.gms.tagmanager.o r2 = com.google.android.gms.tagmanager.o.this     // Catch:{ all -> 0x0065 }
                long r2 = r2.aev     // Catch:{ all -> 0x0065 }
                java.lang.StringBuilder r0 = r0.append(r2)     // Catch:{ all -> 0x0065 }
                java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0065 }
                com.google.android.gms.tagmanager.bh.C(r0)     // Catch:{ all -> 0x0065 }
                com.google.android.gms.tagmanager.o r0 = com.google.android.gms.tagmanager.o.this     // Catch:{ all -> 0x0065 }
                boolean r0 = r0.lp()     // Catch:{ all -> 0x0065 }
                if (r0 != 0) goto L_0x0063
                com.google.android.gms.tagmanager.o r0 = com.google.android.gms.tagmanager.o.this     // Catch:{ all -> 0x0065 }
                r0.a((com.google.android.gms.internal.c.j) r6)     // Catch:{ all -> 0x0065 }
            L_0x0063:
                monitor-exit(r1)     // Catch:{ all -> 0x0065 }
                goto L_0x001f
            L_0x0065:
                r0 = move-exception
                monitor-exit(r1)     // Catch:{ all -> 0x0065 }
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.o.c.i(com.google.android.gms.internal.c$j):void");
        }

        public void lq() {
        }
    }

    private class d implements n.a {
        private d() {
        }

        public void bJ(String str) {
            o.this.bJ(str);
        }

        public String lj() {
            return o.this.lj();
        }

        public void ll() {
            if (o.this.aeG.dj()) {
                o.this.w(0);
            }
        }
    }

    interface e extends Releasable {
        void a(bg<c.j> bgVar);

        void bM(String str);

        void e(long j, String str);
    }

    interface f extends Releasable {
        void a(bg<lf.a> bgVar);

        void b(lf.a aVar);

        cq.c dn(int i);

        void lr();
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    o(Context context, TagManager tagManager, Looper looper, String str, int i, f fVar, e eVar, ik ikVar, cf cfVar) {
        super(looper == null ? Looper.getMainLooper() : looper);
        this.mContext = context;
        this.aeC = tagManager;
        this.DC = looper == null ? Looper.getMainLooper() : looper;
        this.aeq = str;
        this.aeH = i;
        this.aeI = fVar;
        this.aeN = eVar;
        this.aeF = new d();
        this.aeL = new c.j();
        this.aec = ikVar;
        this.aeG = cfVar;
        if (lp()) {
            bJ(cd.lY().ma());
        }
    }

    public o(Context context, TagManager tagManager, Looper looper, String str, int i, r rVar) {
        this(context, tagManager, looper, str, i, new cp(context, str), new co(context, str, rVar), im.fW(), new bf(30, 900000, 5000, "refreshing", im.fW()));
    }

    private void H(final boolean z) {
        this.aeI.a(new b());
        this.aeN.a(new c());
        cq.c dn = this.aeI.dn(this.aeH);
        if (dn != null) {
            this.aeJ = new n(this.aeC, this.DC, new Container(this.mContext, this.aeC.getDataLayer(), this.aeq, 0, dn), this.aeF);
        }
        this.aeO = new a() {
            public boolean b(Container container) {
                return z ? container.getLastRefreshTime() + 43200000 >= o.this.aec.currentTimeMillis() : !container.isDefault();
            }
        };
        if (lp()) {
            this.aeN.e(0, "");
        } else {
            this.aeI.lr();
        }
    }

    /* access modifiers changed from: private */
    public synchronized void a(c.j jVar) {
        if (this.aeI != null) {
            lf.a aVar = new lf.a();
            aVar.aiD = this.aev;
            aVar.fK = new c.f();
            aVar.aiE = jVar;
            this.aeI.b(aVar);
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0008, code lost:
        if (r8.aeK != false) goto L_0x000a;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void a(com.google.android.gms.internal.c.j r9, long r10, boolean r12) {
        /*
            r8 = this;
            r6 = 43200000(0x2932e00, double:2.1343636E-316)
            monitor-enter(r8)
            if (r12 == 0) goto L_0x000c
            boolean r0 = r8.aeK     // Catch:{ all -> 0x006a }
            if (r0 == 0) goto L_0x000c
        L_0x000a:
            monitor-exit(r8)
            return
        L_0x000c:
            boolean r0 = r8.isReady()     // Catch:{ all -> 0x006a }
            if (r0 == 0) goto L_0x0016
            com.google.android.gms.tagmanager.n r0 = r8.aeJ     // Catch:{ all -> 0x006a }
            if (r0 != 0) goto L_0x0016
        L_0x0016:
            r8.aeL = r9     // Catch:{ all -> 0x006a }
            r8.aev = r10     // Catch:{ all -> 0x006a }
            r0 = 0
            r2 = 43200000(0x2932e00, double:2.1343636E-316)
            long r4 = r8.aev     // Catch:{ all -> 0x006a }
            long r4 = r4 + r6
            com.google.android.gms.internal.ik r6 = r8.aec     // Catch:{ all -> 0x006a }
            long r6 = r6.currentTimeMillis()     // Catch:{ all -> 0x006a }
            long r4 = r4 - r6
            long r2 = java.lang.Math.min(r2, r4)     // Catch:{ all -> 0x006a }
            long r0 = java.lang.Math.max(r0, r2)     // Catch:{ all -> 0x006a }
            r8.w(r0)     // Catch:{ all -> 0x006a }
            com.google.android.gms.tagmanager.Container r0 = new com.google.android.gms.tagmanager.Container     // Catch:{ all -> 0x006a }
            android.content.Context r1 = r8.mContext     // Catch:{ all -> 0x006a }
            com.google.android.gms.tagmanager.TagManager r2 = r8.aeC     // Catch:{ all -> 0x006a }
            com.google.android.gms.tagmanager.DataLayer r2 = r2.getDataLayer()     // Catch:{ all -> 0x006a }
            java.lang.String r3 = r8.aeq     // Catch:{ all -> 0x006a }
            r4 = r10
            r6 = r9
            r0.<init>((android.content.Context) r1, (com.google.android.gms.tagmanager.DataLayer) r2, (java.lang.String) r3, (long) r4, (com.google.android.gms.internal.c.j) r6)     // Catch:{ all -> 0x006a }
            com.google.android.gms.tagmanager.n r1 = r8.aeJ     // Catch:{ all -> 0x006a }
            if (r1 != 0) goto L_0x006d
            com.google.android.gms.tagmanager.n r1 = new com.google.android.gms.tagmanager.n     // Catch:{ all -> 0x006a }
            com.google.android.gms.tagmanager.TagManager r2 = r8.aeC     // Catch:{ all -> 0x006a }
            android.os.Looper r3 = r8.DC     // Catch:{ all -> 0x006a }
            com.google.android.gms.tagmanager.o$d r4 = r8.aeF     // Catch:{ all -> 0x006a }
            r1.<init>(r2, r3, r0, r4)     // Catch:{ all -> 0x006a }
            r8.aeJ = r1     // Catch:{ all -> 0x006a }
        L_0x0056:
            boolean r1 = r8.isReady()     // Catch:{ all -> 0x006a }
            if (r1 != 0) goto L_0x000a
            com.google.android.gms.tagmanager.o$a r1 = r8.aeO     // Catch:{ all -> 0x006a }
            boolean r0 = r1.b(r0)     // Catch:{ all -> 0x006a }
            if (r0 == 0) goto L_0x000a
            com.google.android.gms.tagmanager.n r0 = r8.aeJ     // Catch:{ all -> 0x006a }
            r8.a(r0)     // Catch:{ all -> 0x006a }
            goto L_0x000a
        L_0x006a:
            r0 = move-exception
            monitor-exit(r8)
            throw r0
        L_0x006d:
            com.google.android.gms.tagmanager.n r1 = r8.aeJ     // Catch:{ all -> 0x006a }
            r1.a(r0)     // Catch:{ all -> 0x006a }
            goto L_0x0056
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.o.a(com.google.android.gms.internal.c$j, long, boolean):void");
    }

    /* access modifiers changed from: private */
    public boolean lp() {
        cd lY = cd.lY();
        return (lY.lZ() == cd.a.CONTAINER || lY.lZ() == cd.a.CONTAINER_DEBUG) && this.aeq.equals(lY.getContainerId());
    }

    /* access modifiers changed from: private */
    public synchronized void w(long j) {
        if (this.aeN == null) {
            bh.D("Refresh requested, but no network load scheduler.");
        } else {
            this.aeN.e(j, this.aeL.fL);
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: ap */
    public ContainerHolder c(Status status) {
        if (this.aeJ != null) {
            return this.aeJ;
        }
        if (status == Status.En) {
            bh.A("timer expired: setting result to failure");
        }
        return new n(status);
    }

    /* access modifiers changed from: package-private */
    public synchronized void bJ(String str) {
        this.aeM = str;
        if (this.aeN != null) {
            this.aeN.bM(str);
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized String lj() {
        return this.aeM;
    }

    public void lm() {
        cq.c dn = this.aeI.dn(this.aeH);
        if (dn != null) {
            a(new n(this.aeC, this.DC, new Container(this.mContext, this.aeC.getDataLayer(), this.aeq, 0, dn), new n.a() {
                public void bJ(String str) {
                    o.this.bJ(str);
                }

                public String lj() {
                    return o.this.lj();
                }

                public void ll() {
                    bh.D("Refresh ignored: container loaded as default only.");
                }
            }));
        } else {
            bh.A("Default was requested, but no default container was found");
            a(c(new Status(10, "Default was requested, but no default container was found", (PendingIntent) null)));
        }
        this.aeN = null;
        this.aeI = null;
    }

    public void ln() {
        H(false);
    }

    public void lo() {
        H(true);
    }
}
