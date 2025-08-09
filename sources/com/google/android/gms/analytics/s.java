package com.google.android.gms.analytics;

import android.content.Context;
import android.content.Intent;
import com.google.android.gms.analytics.c;
import com.google.android.gms.internal.fe;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentLinkedQueue;

class s implements ag, c.b, c.C0009c {
    private final Context mContext;
    private d tR;
    private final f tS;
    private boolean tU;
    /* access modifiers changed from: private */
    public volatile long ue;
    /* access modifiers changed from: private */
    public volatile a uf;
    private volatile b ug;
    private d uh;
    private final GoogleAnalytics ui;
    /* access modifiers changed from: private */
    public final Queue<d> uj;
    private volatile int uk;
    private volatile Timer ul;
    private volatile Timer um;
    /* access modifiers changed from: private */
    public volatile Timer un;
    private boolean uo;
    private boolean up;
    private boolean uq;
    /* access modifiers changed from: private */
    public i ur;
    /* access modifiers changed from: private */
    public long us;

    private enum a {
        CONNECTING,
        CONNECTED_SERVICE,
        CONNECTED_LOCAL,
        BLOCKED,
        PENDING_CONNECTION,
        PENDING_DISCONNECT,
        DISCONNECTED
    }

    private class b extends TimerTask {
        private b() {
        }

        public void run() {
            if (s.this.uf != a.CONNECTED_SERVICE || !s.this.uj.isEmpty() || s.this.ue + s.this.us >= s.this.ur.currentTimeMillis()) {
                s.this.un.schedule(new b(), s.this.us);
                return;
            }
            aa.C("Disconnecting due to inactivity");
            s.this.bn();
        }
    }

    private class c extends TimerTask {
        private c() {
        }

        public void run() {
            if (s.this.uf == a.CONNECTING) {
                s.this.cJ();
            }
        }
    }

    private static class d {
        private final Map<String, String> uD;
        private final long uE;
        private final String uF;
        private final List<fe> uG;

        public d(Map<String, String> map, long j, String str, List<fe> list) {
            this.uD = map;
            this.uE = j;
            this.uF = str;
            this.uG = list;
        }

        public Map<String, String> cM() {
            return this.uD;
        }

        public long cN() {
            return this.uE;
        }

        public List<fe> cO() {
            return this.uG;
        }

        public String getPath() {
            return this.uF;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("PATH: ");
            sb.append(this.uF);
            if (this.uD != null) {
                sb.append("  PARAMS: ");
                for (Map.Entry next : this.uD.entrySet()) {
                    sb.append((String) next.getKey());
                    sb.append("=");
                    sb.append((String) next.getValue());
                    sb.append(",  ");
                }
            }
            return sb.toString();
        }
    }

    private class e extends TimerTask {
        private e() {
        }

        public void run() {
            s.this.cK();
        }
    }

    s(Context context, f fVar) {
        this(context, fVar, (d) null, GoogleAnalytics.getInstance(context));
    }

    s(Context context, f fVar, d dVar, GoogleAnalytics googleAnalytics) {
        this.uj = new ConcurrentLinkedQueue();
        this.us = 300000;
        this.uh = dVar;
        this.mContext = context;
        this.tS = fVar;
        this.ui = googleAnalytics;
        this.ur = new i() {
            public long currentTimeMillis() {
                return System.currentTimeMillis();
            }
        };
        this.uk = 0;
        this.uf = a.DISCONNECTED;
    }

    private Timer a(Timer timer) {
        if (timer == null) {
            return null;
        }
        timer.cancel();
        return null;
    }

    /* access modifiers changed from: private */
    public synchronized void bn() {
        if (this.ug != null && this.uf == a.CONNECTED_SERVICE) {
            this.uf = a.PENDING_DISCONNECT;
            this.ug.disconnect();
        }
    }

    private void cF() {
        this.ul = a(this.ul);
        this.um = a(this.um);
        this.un = a(this.un);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x003c, code lost:
        if (r8.uj.isEmpty() != false) goto L_0x0077;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x003e, code lost:
        r7 = r8.uj.poll();
        com.google.android.gms.analytics.aa.C("Sending hit to store  " + r7);
        r8.tR.a(r7.cM(), r7.cN(), r7.getPath(), r7.cO());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0079, code lost:
        if (r8.tU == false) goto L_0x001f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x007b, code lost:
        cI();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00d0, code lost:
        r8.ue = r8.ur.currentTimeMillis();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void cH() {
        /*
            r8 = this;
            monitor-enter(r8)
            java.lang.Thread r2 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x0074 }
            com.google.android.gms.analytics.f r3 = r8.tS     // Catch:{ all -> 0x0074 }
            java.lang.Thread r3 = r3.getThread()     // Catch:{ all -> 0x0074 }
            boolean r2 = r2.equals(r3)     // Catch:{ all -> 0x0074 }
            if (r2 != 0) goto L_0x0021
            com.google.android.gms.analytics.f r2 = r8.tS     // Catch:{ all -> 0x0074 }
            java.util.concurrent.LinkedBlockingQueue r2 = r2.co()     // Catch:{ all -> 0x0074 }
            com.google.android.gms.analytics.s$2 r3 = new com.google.android.gms.analytics.s$2     // Catch:{ all -> 0x0074 }
            r3.<init>()     // Catch:{ all -> 0x0074 }
            r2.add(r3)     // Catch:{ all -> 0x0074 }
        L_0x001f:
            monitor-exit(r8)
            return
        L_0x0021:
            boolean r2 = r8.uo     // Catch:{ all -> 0x0074 }
            if (r2 == 0) goto L_0x0028
            r8.cg()     // Catch:{ all -> 0x0074 }
        L_0x0028:
            int[] r2 = com.google.android.gms.analytics.s.AnonymousClass3.uu     // Catch:{ all -> 0x0074 }
            com.google.android.gms.analytics.s$a r3 = r8.uf     // Catch:{ all -> 0x0074 }
            int r3 = r3.ordinal()     // Catch:{ all -> 0x0074 }
            r2 = r2[r3]     // Catch:{ all -> 0x0074 }
            switch(r2) {
                case 1: goto L_0x0036;
                case 2: goto L_0x007f;
                case 3: goto L_0x0035;
                case 4: goto L_0x0035;
                case 5: goto L_0x0035;
                case 6: goto L_0x00da;
                default: goto L_0x0035;
            }     // Catch:{ all -> 0x0074 }
        L_0x0035:
            goto L_0x001f
        L_0x0036:
            java.util.Queue<com.google.android.gms.analytics.s$d> r2 = r8.uj     // Catch:{ all -> 0x0074 }
            boolean r2 = r2.isEmpty()     // Catch:{ all -> 0x0074 }
            if (r2 != 0) goto L_0x0077
            java.util.Queue<com.google.android.gms.analytics.s$d> r2 = r8.uj     // Catch:{ all -> 0x0074 }
            java.lang.Object r2 = r2.poll()     // Catch:{ all -> 0x0074 }
            r0 = r2
            com.google.android.gms.analytics.s$d r0 = (com.google.android.gms.analytics.s.d) r0     // Catch:{ all -> 0x0074 }
            r7 = r0
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0074 }
            r2.<init>()     // Catch:{ all -> 0x0074 }
            java.lang.String r3 = "Sending hit to store  "
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ all -> 0x0074 }
            java.lang.StringBuilder r2 = r2.append(r7)     // Catch:{ all -> 0x0074 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x0074 }
            com.google.android.gms.analytics.aa.C(r2)     // Catch:{ all -> 0x0074 }
            com.google.android.gms.analytics.d r2 = r8.tR     // Catch:{ all -> 0x0074 }
            java.util.Map r3 = r7.cM()     // Catch:{ all -> 0x0074 }
            long r4 = r7.cN()     // Catch:{ all -> 0x0074 }
            java.lang.String r6 = r7.getPath()     // Catch:{ all -> 0x0074 }
            java.util.List r7 = r7.cO()     // Catch:{ all -> 0x0074 }
            r2.a(r3, r4, r6, r7)     // Catch:{ all -> 0x0074 }
            goto L_0x0036
        L_0x0074:
            r2 = move-exception
            monitor-exit(r8)
            throw r2
        L_0x0077:
            boolean r2 = r8.tU     // Catch:{ all -> 0x0074 }
            if (r2 == 0) goto L_0x001f
            r8.cI()     // Catch:{ all -> 0x0074 }
            goto L_0x001f
        L_0x007f:
            java.util.Queue<com.google.android.gms.analytics.s$d> r2 = r8.uj     // Catch:{ all -> 0x0074 }
            boolean r2 = r2.isEmpty()     // Catch:{ all -> 0x0074 }
            if (r2 != 0) goto L_0x00d0
            java.util.Queue<com.google.android.gms.analytics.s$d> r2 = r8.uj     // Catch:{ all -> 0x0074 }
            java.lang.Object r2 = r2.peek()     // Catch:{ all -> 0x0074 }
            r0 = r2
            com.google.android.gms.analytics.s$d r0 = (com.google.android.gms.analytics.s.d) r0     // Catch:{ all -> 0x0074 }
            r7 = r0
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0074 }
            r2.<init>()     // Catch:{ all -> 0x0074 }
            java.lang.String r3 = "Sending hit to service   "
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ all -> 0x0074 }
            java.lang.StringBuilder r2 = r2.append(r7)     // Catch:{ all -> 0x0074 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x0074 }
            com.google.android.gms.analytics.aa.C(r2)     // Catch:{ all -> 0x0074 }
            com.google.android.gms.analytics.GoogleAnalytics r2 = r8.ui     // Catch:{ all -> 0x0074 }
            boolean r2 = r2.isDryRunEnabled()     // Catch:{ all -> 0x0074 }
            if (r2 != 0) goto L_0x00ca
            com.google.android.gms.analytics.b r2 = r8.ug     // Catch:{ all -> 0x0074 }
            java.util.Map r3 = r7.cM()     // Catch:{ all -> 0x0074 }
            long r4 = r7.cN()     // Catch:{ all -> 0x0074 }
            java.lang.String r6 = r7.getPath()     // Catch:{ all -> 0x0074 }
            java.util.List r7 = r7.cO()     // Catch:{ all -> 0x0074 }
            r2.a(r3, r4, r6, r7)     // Catch:{ all -> 0x0074 }
        L_0x00c4:
            java.util.Queue<com.google.android.gms.analytics.s$d> r2 = r8.uj     // Catch:{ all -> 0x0074 }
            r2.poll()     // Catch:{ all -> 0x0074 }
            goto L_0x007f
        L_0x00ca:
            java.lang.String r2 = "Dry run enabled. Hit not actually sent to service."
            com.google.android.gms.analytics.aa.C(r2)     // Catch:{ all -> 0x0074 }
            goto L_0x00c4
        L_0x00d0:
            com.google.android.gms.analytics.i r2 = r8.ur     // Catch:{ all -> 0x0074 }
            long r2 = r2.currentTimeMillis()     // Catch:{ all -> 0x0074 }
            r8.ue = r2     // Catch:{ all -> 0x0074 }
            goto L_0x001f
        L_0x00da:
            java.lang.String r2 = "Need to reconnect"
            com.google.android.gms.analytics.aa.C(r2)     // Catch:{ all -> 0x0074 }
            java.util.Queue<com.google.android.gms.analytics.s$d> r2 = r8.uj     // Catch:{ all -> 0x0074 }
            boolean r2 = r2.isEmpty()     // Catch:{ all -> 0x0074 }
            if (r2 != 0) goto L_0x001f
            r8.cK()     // Catch:{ all -> 0x0074 }
            goto L_0x001f
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.analytics.s.cH():void");
    }

    private void cI() {
        this.tR.cl();
        this.tU = false;
    }

    /* access modifiers changed from: private */
    public synchronized void cJ() {
        if (this.uf != a.CONNECTED_LOCAL) {
            cF();
            aa.C("falling back to local store");
            if (this.uh != null) {
                this.tR = this.uh;
            } else {
                r cz = r.cz();
                cz.a(this.mContext, this.tS);
                this.tR = cz.cC();
            }
            this.uf = a.CONNECTED_LOCAL;
            cH();
        }
    }

    /* access modifiers changed from: private */
    public synchronized void cK() {
        if (this.uq || this.ug == null || this.uf == a.CONNECTED_LOCAL) {
            aa.D("client not initialized.");
            cJ();
        } else {
            try {
                this.uk++;
                a(this.um);
                this.uf = a.CONNECTING;
                this.um = new Timer("Failed Connect");
                this.um.schedule(new c(), 3000);
                aa.C("connecting to Analytics service");
                this.ug.connect();
            } catch (SecurityException e2) {
                aa.D("security exception on connectToService");
                cJ();
            }
        }
        return;
    }

    private void cL() {
        this.ul = a(this.ul);
        this.ul = new Timer("Service Reconnect");
        this.ul.schedule(new e(), 5000);
    }

    public synchronized void a(int i, Intent intent) {
        this.uf = a.PENDING_CONNECTION;
        if (this.uk < 2) {
            aa.D("Service unavailable (code=" + i + "), will retry.");
            cL();
        } else {
            aa.D("Service unavailable (code=" + i + "), using local store.");
            cJ();
        }
    }

    public void b(Map<String, String> map, long j, String str, List<fe> list) {
        aa.C("putHit called");
        this.uj.add(new d(map, j, str, list));
        cH();
    }

    public void cG() {
        if (this.ug == null) {
            this.ug = new c(this.mContext, this, this);
            cK();
        }
    }

    public void cg() {
        aa.C("clearHits called");
        this.uj.clear();
        switch (this.uf) {
            case CONNECTED_LOCAL:
                this.tR.l(0);
                this.uo = false;
                return;
            case CONNECTED_SERVICE:
                this.ug.cg();
                this.uo = false;
                return;
            default:
                this.uo = true;
                return;
        }
    }

    public void cl() {
        switch (this.uf) {
            case CONNECTED_LOCAL:
                cI();
                return;
            case CONNECTED_SERVICE:
                return;
            default:
                this.tU = true;
                return;
        }
    }

    public synchronized void cn() {
        if (!this.uq) {
            aa.C("setForceLocalDispatch called.");
            this.uq = true;
            switch (this.uf) {
                case CONNECTED_LOCAL:
                case PENDING_CONNECTION:
                case PENDING_DISCONNECT:
                case DISCONNECTED:
                    break;
                case CONNECTED_SERVICE:
                    bn();
                    break;
                case CONNECTING:
                    this.up = true;
                    break;
            }
        }
    }

    public synchronized void onConnected() {
        this.um = a(this.um);
        this.uk = 0;
        aa.C("Connected to service");
        this.uf = a.CONNECTED_SERVICE;
        if (this.up) {
            bn();
            this.up = false;
        } else {
            cH();
            this.un = a(this.un);
            this.un = new Timer("disconnect check");
            this.un.schedule(new b(), this.us);
        }
    }

    public synchronized void onDisconnected() {
        if (this.uf == a.PENDING_DISCONNECT) {
            aa.C("Disconnected from service");
            cF();
            this.uf = a.DISCONNECTED;
        } else {
            aa.C("Unexpected disconnect.");
            this.uf = a.PENDING_CONNECTION;
            if (this.uk < 2) {
                cL();
            } else {
                cJ();
            }
        }
    }
}
