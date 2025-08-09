package com.crashlytics.android;

import java.util.concurrent.Callable;

final class C implements Callable<Boolean> {
    private /* synthetic */ C0205v a;

    C(C0205v vVar) {
        this.a = vVar;
    }

    public final /* synthetic */ Object call() throws Exception {
        return Boolean.valueOf(this.a.l.exists());
    }
}
