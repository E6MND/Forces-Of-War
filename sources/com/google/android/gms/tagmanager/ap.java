package com.google.android.gms.tagmanager;

import android.text.TextUtils;

class ap {
    private final long afC;
    private String afD;
    private final long wt;
    private final long wu;

    ap(long j, long j2, long j3) {
        this.wt = j;
        this.wu = j2;
        this.afC = j3;
    }

    /* access modifiers changed from: package-private */
    public void R(String str) {
        if (str != null && !TextUtils.isEmpty(str.trim())) {
            this.afD = str;
        }
    }

    /* access modifiers changed from: package-private */
    public long dg() {
        return this.wt;
    }

    /* access modifiers changed from: package-private */
    public long lI() {
        return this.afC;
    }

    /* access modifiers changed from: package-private */
    public String lJ() {
        return this.afD;
    }
}
