package com.crashlytics.android.internal;

import java.util.Collections;

final class P implements Runnable {
    private /* synthetic */ String a;
    private /* synthetic */ O b;

    P(O o, String str) {
        this.b = o;
        this.a = str;
    }

    public final void run() {
        try {
            this.b.a.a(V.a(this.b.b, this.b.i, this.b.c, this.b.d, this.b.e, this.b.f, this.b.g, this.b.h, W.CRASH, Collections.singletonMap("sessionId", this.a)));
        } catch (Exception e) {
            C0143ab.d("Crashlytics failed to record crash event");
        }
    }
}
