package com.crashlytics.android;

import java.util.Date;

final class P implements Runnable {
    private /* synthetic */ Date a;
    private /* synthetic */ Thread b;
    private /* synthetic */ Throwable c;
    private /* synthetic */ C0205v d;

    P(C0205v vVar, Date date, Thread thread, Throwable th) {
        this.d = vVar;
        this.a = date;
        this.b = thread;
        this.c = th;
    }

    public final void run() {
        if (!this.d.m.get()) {
            C0205v.b(this.d, this.a, this.b, this.c);
        }
    }
}
