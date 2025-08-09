package com.crashlytics.android;

import java.util.Date;
import java.util.concurrent.Callable;

final class N implements Callable<Void> {
    private /* synthetic */ Date a;
    private /* synthetic */ Thread b;
    private /* synthetic */ Throwable c;
    private /* synthetic */ C0205v d;

    N(C0205v vVar, Date date, Thread thread, Throwable th) {
        this.d = vVar;
        this.a = date;
        this.b = thread;
        this.c = th;
    }

    public final /* synthetic */ Object call() throws Exception {
        C0205v.a(this.d, this.a, this.b, this.c);
        return null;
    }
}
