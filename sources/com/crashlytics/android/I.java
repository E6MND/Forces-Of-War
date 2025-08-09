package com.crashlytics.android;

import com.crashlytics.android.internal.C0188v;
import java.util.concurrent.Callable;

final class I implements Callable<T> {
    private /* synthetic */ Callable a;

    I(C0205v vVar, Callable callable) {
        this.a = callable;
    }

    public final T call() throws Exception {
        try {
            return this.a.call();
        } catch (Exception e) {
            C0188v.a().b().a(Crashlytics.TAG, "Failed to execute task.", (Throwable) e);
            return null;
        }
    }
}
