package com.crashlytics.android.internal;

import java.io.IOException;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

final class J implements U {
    private final ScheduledExecutorService a;
    private final K b;
    private final C0163av c;
    private ScheduledFuture<?> d;
    private int e = -1;
    private N f;

    public J(ScheduledExecutorService scheduledExecutorService, K k, C0163av avVar) {
        this.a = scheduledExecutorService;
        this.b = k;
        this.c = avVar;
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x0088  */
    /* JADX WARNING: Removed duplicated region for block: B:31:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a() {
        /*
            r8 = this;
            r1 = 0
            com.crashlytics.android.internal.N r0 = r8.f
            if (r0 != 0) goto L_0x000b
            java.lang.String r0 = "skipping analytics files send because we don't yet know the target endpoint"
            com.crashlytics.android.internal.C0143ab.c((java.lang.String) r0)
        L_0x000a:
            return
        L_0x000b:
            java.lang.String r0 = "Sending all analytics files"
            com.crashlytics.android.internal.C0143ab.c((java.lang.String) r0)
            com.crashlytics.android.internal.K r0 = r8.b
            java.util.List r0 = r0.b()
            r2 = r0
            r0 = r1
        L_0x0018:
            int r1 = r2.size()     // Catch:{ Exception -> 0x006b }
            if (r1 <= 0) goto L_0x0086
            com.crashlytics.android.internal.N r1 = r8.f     // Catch:{ Exception -> 0x006b }
            com.crashlytics.android.internal.D r3 = com.crashlytics.android.internal.D.a()     // Catch:{ Exception -> 0x006b }
            android.content.Context r3 = r3.getContext()     // Catch:{ Exception -> 0x006b }
            r4 = 0
            java.lang.String r3 = com.crashlytics.android.internal.C0184r.a((android.content.Context) r3, (boolean) r4)     // Catch:{ Exception -> 0x006b }
            boolean r3 = r1.a(r3, r2)     // Catch:{ Exception -> 0x006b }
            if (r3 == 0) goto L_0x003e
            int r1 = r2.size()     // Catch:{ Exception -> 0x006b }
            int r1 = r1 + r0
            com.crashlytics.android.internal.K r0 = r8.b     // Catch:{ Exception -> 0x008f }
            r0.a((java.util.List<java.io.File>) r2)     // Catch:{ Exception -> 0x008f }
            r0 = r1
        L_0x003e:
            java.util.Locale r4 = java.util.Locale.US     // Catch:{ Exception -> 0x006b }
            java.lang.String r5 = "attempt to send batch of %d analytics files %s"
            r1 = 2
            java.lang.Object[] r6 = new java.lang.Object[r1]     // Catch:{ Exception -> 0x006b }
            r1 = 0
            int r2 = r2.size()     // Catch:{ Exception -> 0x006b }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ Exception -> 0x006b }
            r6[r1] = r2     // Catch:{ Exception -> 0x006b }
            r2 = 1
            if (r3 == 0) goto L_0x0068
            java.lang.String r1 = "succeeded"
        L_0x0055:
            r6[r2] = r1     // Catch:{ Exception -> 0x006b }
            java.lang.String r1 = java.lang.String.format(r4, r5, r6)     // Catch:{ Exception -> 0x006b }
            com.crashlytics.android.internal.C0143ab.c((java.lang.String) r1)     // Catch:{ Exception -> 0x006b }
            if (r3 == 0) goto L_0x0086
            com.crashlytics.android.internal.K r1 = r8.b     // Catch:{ Exception -> 0x006b }
            java.util.List r1 = r1.b()     // Catch:{ Exception -> 0x006b }
            r2 = r1
            goto L_0x0018
        L_0x0068:
            java.lang.String r1 = "did not succeed"
            goto L_0x0055
        L_0x006b:
            r1 = move-exception
            r7 = r1
            r1 = r0
            r0 = r7
        L_0x006f:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "Crashlytics failed to send batch of analytics files to server: "
            r2.<init>(r3)
            java.lang.String r0 = r0.getMessage()
            java.lang.StringBuilder r0 = r2.append(r0)
            java.lang.String r0 = r0.toString()
            com.crashlytics.android.internal.C0143ab.d((java.lang.String) r0)
            r0 = r1
        L_0x0086:
            if (r0 != 0) goto L_0x000a
            com.crashlytics.android.internal.K r0 = r8.b
            r0.d()
            goto L_0x000a
        L_0x008f:
            r0 = move-exception
            goto L_0x006f
        */
        throw new UnsupportedOperationException("Method not decompiled: com.crashlytics.android.internal.J.a():void");
    }

    private void a(int i, int i2) {
        try {
            Y y = new Y(this.b, this);
            C0143ab.c("Scheduling time based file roll over every " + i2 + " seconds");
            this.d = this.a.scheduleAtFixedRate(y, (long) i, (long) i2, TimeUnit.SECONDS);
        } catch (RejectedExecutionException e2) {
            C0143ab.d("Crashlytics failed to schedule time based analytics file roll over");
        }
    }

    public final void c() {
        if (this.d != null) {
            C0143ab.c("Cancelling time-based rollover because no events are currently being generated.");
            this.d.cancel(false);
            this.d = null;
        }
    }

    public final void a(aK aKVar, String str) {
        this.f = new H(str, aKVar.a, this.c);
        this.b.a(aKVar);
        this.e = aKVar.b;
        a(0, this.e);
    }

    public final void b() {
        this.b.c();
    }

    public final void a(V v) {
        boolean z;
        boolean z2 = true;
        C0143ab.c(v.toString());
        try {
            this.b.a(v);
        } catch (IOException e2) {
            C0143ab.d("Crashlytics failed to write session event.");
        }
        if (this.e != -1) {
            z = true;
        } else {
            z = false;
        }
        if (this.d != null) {
            z2 = false;
        }
        if (z && z2) {
            a(this.e, this.e);
        }
    }

    public final void d() {
        try {
            this.b.a();
        } catch (IOException e2) {
            C0143ab.d("Crashlytics failed to roll analytics file over.");
        }
    }
}
