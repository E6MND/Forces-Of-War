package com.crashlytics.android.internal;

import com.crashlytics.android.Crashlytics;

final class E extends C0142aa {
    private /* synthetic */ D a;

    E(D d) {
        this.a = d;
    }

    public final void a() {
        try {
            D.a(this.a);
        } catch (Exception e) {
            C0188v.a().b().a(Crashlytics.TAG, "Problem encountered during Crashlytics initialization.", (Throwable) e);
        }
    }
}
