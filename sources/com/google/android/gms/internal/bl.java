package com.google.android.gms.internal;

import android.content.Context;

public final class bl {
    private final bu kz;
    private final Object lq = new Object();
    private final Context mContext;
    private final dt nc;
    private final bn nd;
    private boolean ne = false;
    private bq nf;

    public bl(Context context, dt dtVar, bu buVar, bn bnVar) {
        this.mContext = context;
        this.nc = dtVar;
        this.kz = buVar;
        this.nd = bnVar;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0080, code lost:
        r4 = r17.nf.b(r18, r20);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x008e, code lost:
        if (r4.nJ != 0) goto L_0x0099;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0090, code lost:
        com.google.android.gms.internal.ev.z("Adapter succeeded.");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x009b, code lost:
        if (r4.nL == null) goto L_0x0039;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x009d, code lost:
        com.google.android.gms.internal.eu.ss.post(new com.google.android.gms.internal.bl.AnonymousClass1(r17));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:?, code lost:
        return r4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.android.gms.internal.br a(long r18, long r20) {
        /*
            r17 = this;
            java.lang.String r4 = "Starting mediation."
            com.google.android.gms.internal.ev.z(r4)
            r0 = r17
            com.google.android.gms.internal.bn r4 = r0.nd
            java.util.List<com.google.android.gms.internal.bm> r4 = r4.np
            java.util.Iterator r13 = r4.iterator()
        L_0x000f:
            boolean r4 = r13.hasNext()
            if (r4 == 0) goto L_0x00aa
            java.lang.Object r9 = r13.next()
            com.google.android.gms.internal.bm r9 = (com.google.android.gms.internal.bm) r9
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "Trying mediation network: "
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r5 = r9.nj
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r4 = r4.toString()
            com.google.android.gms.internal.ev.B(r4)
            java.util.List<java.lang.String> r4 = r9.nk
            java.util.Iterator r14 = r4.iterator()
        L_0x0039:
            boolean r4 = r14.hasNext()
            if (r4 == 0) goto L_0x000f
            java.lang.Object r6 = r14.next()
            java.lang.String r6 = (java.lang.String) r6
            r0 = r17
            java.lang.Object r15 = r0.lq
            monitor-enter(r15)
            r0 = r17
            boolean r4 = r0.ne     // Catch:{ all -> 0x0096 }
            if (r4 == 0) goto L_0x0058
            com.google.android.gms.internal.br r4 = new com.google.android.gms.internal.br     // Catch:{ all -> 0x0096 }
            r5 = -1
            r4.<init>(r5)     // Catch:{ all -> 0x0096 }
            monitor-exit(r15)     // Catch:{ all -> 0x0096 }
        L_0x0057:
            return r4
        L_0x0058:
            com.google.android.gms.internal.bq r4 = new com.google.android.gms.internal.bq     // Catch:{ all -> 0x0096 }
            r0 = r17
            android.content.Context r5 = r0.mContext     // Catch:{ all -> 0x0096 }
            r0 = r17
            com.google.android.gms.internal.bu r7 = r0.kz     // Catch:{ all -> 0x0096 }
            r0 = r17
            com.google.android.gms.internal.bn r8 = r0.nd     // Catch:{ all -> 0x0096 }
            r0 = r17
            com.google.android.gms.internal.dt r10 = r0.nc     // Catch:{ all -> 0x0096 }
            com.google.android.gms.internal.aj r10 = r10.pV     // Catch:{ all -> 0x0096 }
            r0 = r17
            com.google.android.gms.internal.dt r11 = r0.nc     // Catch:{ all -> 0x0096 }
            com.google.android.gms.internal.am r11 = r11.kR     // Catch:{ all -> 0x0096 }
            r0 = r17
            com.google.android.gms.internal.dt r12 = r0.nc     // Catch:{ all -> 0x0096 }
            com.google.android.gms.internal.ew r12 = r12.kO     // Catch:{ all -> 0x0096 }
            r4.<init>(r5, r6, r7, r8, r9, r10, r11, r12)     // Catch:{ all -> 0x0096 }
            r0 = r17
            r0.nf = r4     // Catch:{ all -> 0x0096 }
            monitor-exit(r15)     // Catch:{ all -> 0x0096 }
            r0 = r17
            com.google.android.gms.internal.bq r4 = r0.nf
            r0 = r18
            r2 = r20
            com.google.android.gms.internal.br r4 = r4.b(r0, r2)
            int r5 = r4.nJ
            if (r5 != 0) goto L_0x0099
            java.lang.String r5 = "Adapter succeeded."
            com.google.android.gms.internal.ev.z(r5)
            goto L_0x0057
        L_0x0096:
            r4 = move-exception
            monitor-exit(r15)     // Catch:{ all -> 0x0096 }
            throw r4
        L_0x0099:
            com.google.android.gms.internal.bv r5 = r4.nL
            if (r5 == 0) goto L_0x0039
            android.os.Handler r5 = com.google.android.gms.internal.eu.ss
            com.google.android.gms.internal.bl$1 r6 = new com.google.android.gms.internal.bl$1
            r0 = r17
            r6.<init>(r4)
            r5.post(r6)
            goto L_0x0039
        L_0x00aa:
            com.google.android.gms.internal.br r4 = new com.google.android.gms.internal.br
            r5 = 1
            r4.<init>(r5)
            goto L_0x0057
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.bl.a(long, long):com.google.android.gms.internal.br");
    }

    public void cancel() {
        synchronized (this.lq) {
            this.ne = true;
            if (this.nf != null) {
                this.nf.cancel();
            }
        }
    }
}
