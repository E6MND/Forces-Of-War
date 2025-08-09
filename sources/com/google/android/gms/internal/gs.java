package com.google.android.gms.internal;

import android.os.SystemClock;
import org.json.JSONObject;

public final class gs {
    private static final go BD = new go("RequestTracker");
    public static final Object CK = new Object();
    private long CG;
    private long CH = -1;
    private long CI = 0;
    private gr CJ;

    public gs(long j) {
        this.CG = j;
    }

    private void ep() {
        this.CH = -1;
        this.CJ = null;
        this.CI = 0;
    }

    public void a(long j, gr grVar) {
        gr grVar2;
        long j2;
        synchronized (CK) {
            grVar2 = this.CJ;
            j2 = this.CH;
            this.CH = j;
            this.CJ = grVar;
            this.CI = SystemClock.elapsedRealtime();
        }
        if (grVar2 != null) {
            grVar2.n(j2);
        }
    }

    public boolean b(long j, int i, JSONObject jSONObject) {
        boolean z = true;
        gr grVar = null;
        synchronized (CK) {
            if (this.CH == -1 || this.CH != j) {
                z = false;
            } else {
                BD.b("request %d completed", Long.valueOf(this.CH));
                grVar = this.CJ;
                ep();
            }
        }
        if (grVar != null) {
            grVar.a(j, i, jSONObject);
        }
        return z;
    }

    public boolean c(long j, int i) {
        return b(j, i, (JSONObject) null);
    }

    public void clear() {
        synchronized (CK) {
            if (this.CH != -1) {
                ep();
            }
        }
    }

    public boolean d(long j, int i) {
        gr grVar;
        boolean z = true;
        long j2 = 0;
        synchronized (CK) {
            if (this.CH == -1 || j - this.CI < this.CG) {
                z = false;
                grVar = null;
            } else {
                BD.b("request %d timed out", Long.valueOf(this.CH));
                j2 = this.CH;
                grVar = this.CJ;
                ep();
            }
        }
        if (grVar != null) {
            grVar.a(j2, i, (JSONObject) null);
        }
        return z;
    }

    public boolean eq() {
        boolean z;
        synchronized (CK) {
            z = this.CH != -1;
        }
        return z;
    }

    public boolean p(long j) {
        boolean z;
        synchronized (CK) {
            z = this.CH != -1 && this.CH == j;
        }
        return z;
    }
}
