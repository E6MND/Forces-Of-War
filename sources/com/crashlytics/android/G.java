package com.crashlytics.android;

import com.crashlytics.android.internal.C0188v;

final class G implements Runnable {
    private /* synthetic */ Runnable a;

    G(C0205v vVar, Runnable runnable) {
        this.a = runnable;
    }

    public final void run() {
        try {
            this.a.run();
        } catch (Exception e) {
            C0188v.a().b().a(Crashlytics.TAG, "Failed to execute task.", (Throwable) e);
        }
    }
}
