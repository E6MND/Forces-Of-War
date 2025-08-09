package com.crashlytics.android;

import java.util.concurrent.Callable;

final class O implements Callable<Boolean> {
    private /* synthetic */ C0205v a;

    O(C0205v vVar) {
        this.a = vVar;
    }

    public final /* synthetic */ Object call() throws Exception {
        if (this.a.m.get()) {
            return false;
        }
        this.a.m();
        this.a.l();
        return true;
    }
}
