package com.google.android.gms.internal;

import android.text.TextUtils;
import java.io.IOException;

public abstract class gh {
    protected final go BA;
    private final String BB;
    private gq BC;

    protected gh(String str, String str2, String str3) {
        gj.ak(str);
        this.BB = str;
        this.BA = new go(str2);
        if (!TextUtils.isEmpty(str3)) {
            this.BA.ap(str3);
        }
    }

    public void a(long j, int i) {
    }

    public final void a(gq gqVar) {
        this.BC = gqVar;
        if (this.BC == null) {
            dZ();
        }
    }

    /* access modifiers changed from: protected */
    public final void a(String str, long j, String str2) throws IOException {
        this.BA.a("Sending text message: %s to: %s", str, str2);
        this.BC.a(this.BB, str, j, str2);
    }

    public void ai(String str) {
    }

    /* access modifiers changed from: protected */
    public final long dY() {
        return this.BC.dW();
    }

    public void dZ() {
    }

    public final String getNamespace() {
        return this.BB;
    }
}
