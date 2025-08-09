package com.google.android.gms.analytics;

import android.text.TextUtils;

class x {
    private String ws;
    private final long wt;
    private final long wu;
    private String wv = "https:";

    x(String str, long j, long j2) {
        this.ws = str;
        this.wt = j;
        this.wu = j2;
    }

    /* access modifiers changed from: package-private */
    public void Q(String str) {
        this.ws = str;
    }

    /* access modifiers changed from: package-private */
    public void R(String str) {
        if (str != null && !TextUtils.isEmpty(str.trim()) && str.toLowerCase().startsWith("http:")) {
            this.wv = "http:";
        }
    }

    /* access modifiers changed from: package-private */
    public String df() {
        return this.ws;
    }

    /* access modifiers changed from: package-private */
    public long dg() {
        return this.wt;
    }

    /* access modifiers changed from: package-private */
    public long dh() {
        return this.wu;
    }

    /* access modifiers changed from: package-private */
    public String di() {
        return this.wv;
    }
}
