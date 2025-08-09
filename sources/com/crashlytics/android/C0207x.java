package com.crashlytics.android;

import java.util.concurrent.Callable;

/* renamed from: com.crashlytics.android.x  reason: case insensitive filesystem */
final class C0207x implements Callable<Void> {
    private /* synthetic */ long a;
    private /* synthetic */ String b;
    private /* synthetic */ C0205v c;

    C0207x(C0205v vVar, long j, String str) {
        this.c = vVar;
        this.a = j;
        this.b = str;
    }

    public final /* synthetic */ Object call() throws Exception {
        if (this.c.m.get()) {
            return null;
        }
        if (this.c.u == null) {
            boolean unused = this.c.k();
        }
        C0205v vVar = this.c;
        C0205v.a(this.c.u, 65536, this.a, this.b);
        return null;
    }
}
