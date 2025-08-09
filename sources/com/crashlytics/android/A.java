package com.crashlytics.android;

import com.crashlytics.android.internal.C0188v;
import java.util.concurrent.Callable;

final class A implements Callable<Void> {
    private /* synthetic */ C0205v a;

    A(C0205v vVar) {
        this.a = vVar;
    }

    public final /* synthetic */ Object call() throws Exception {
        this.a.l.createNewFile();
        C0188v.a().b().a(Crashlytics.TAG, "Initialization marker file created.");
        return null;
    }
}
