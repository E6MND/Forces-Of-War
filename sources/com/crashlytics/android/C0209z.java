package com.crashlytics.android;

import java.util.concurrent.Callable;

/* renamed from: com.crashlytics.android.z  reason: case insensitive filesystem */
final class C0209z implements Callable<Void> {
    private /* synthetic */ C0205v a;

    C0209z(C0205v vVar) {
        this.a = vVar;
    }

    public final /* synthetic */ Object call() throws Exception {
        if (this.a.g()) {
            return null;
        }
        this.a.l();
        return null;
    }
}
