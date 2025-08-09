package com.google.android.gms.internal;

import com.google.android.gms.internal.br;
import com.google.android.gms.internal.bw;

public final class bp extends bw.a {
    private final Object lq = new Object();
    private br.a ny;
    private bo nz;

    public void a(bo boVar) {
        synchronized (this.lq) {
            this.nz = boVar;
        }
    }

    public void a(br.a aVar) {
        synchronized (this.lq) {
            this.ny = aVar;
        }
    }

    public void onAdClicked() {
        synchronized (this.lq) {
            if (this.nz != null) {
                this.nz.W();
            }
        }
    }

    public void onAdClosed() {
        synchronized (this.lq) {
            if (this.nz != null) {
                this.nz.X();
            }
        }
    }

    public void onAdFailedToLoad(int error) {
        synchronized (this.lq) {
            if (this.ny != null) {
                this.ny.g(error == 3 ? 1 : 2);
                this.ny = null;
            }
        }
    }

    public void onAdLeftApplication() {
        synchronized (this.lq) {
            if (this.nz != null) {
                this.nz.Y();
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onAdLoaded() {
        /*
            r3 = this;
            java.lang.Object r1 = r3.lq
            monitor-enter(r1)
            com.google.android.gms.internal.br$a r0 = r3.ny     // Catch:{ all -> 0x001d }
            if (r0 == 0) goto L_0x0012
            com.google.android.gms.internal.br$a r0 = r3.ny     // Catch:{ all -> 0x001d }
            r2 = 0
            r0.g(r2)     // Catch:{ all -> 0x001d }
            r0 = 0
            r3.ny = r0     // Catch:{ all -> 0x001d }
            monitor-exit(r1)     // Catch:{ all -> 0x001d }
        L_0x0011:
            return
        L_0x0012:
            com.google.android.gms.internal.bo r0 = r3.nz     // Catch:{ all -> 0x001d }
            if (r0 == 0) goto L_0x001b
            com.google.android.gms.internal.bo r0 = r3.nz     // Catch:{ all -> 0x001d }
            r0.aa()     // Catch:{ all -> 0x001d }
        L_0x001b:
            monitor-exit(r1)     // Catch:{ all -> 0x001d }
            goto L_0x0011
        L_0x001d:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x001d }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.bp.onAdLoaded():void");
    }

    public void onAdOpened() {
        synchronized (this.lq) {
            if (this.nz != null) {
                this.nz.Z();
            }
        }
    }
}
