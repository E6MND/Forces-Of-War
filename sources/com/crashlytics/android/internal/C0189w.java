package com.crashlytics.android.internal;

import android.app.Application;

/* renamed from: com.crashlytics.android.internal.w  reason: case insensitive filesystem */
final class C0189w {
    final /* synthetic */ C0188v a;

    private C0189w(C0188v vVar) {
        this.a = vVar;
    }

    /* synthetic */ C0189w(C0188v vVar, byte b) {
        this(vVar);
    }

    static /* synthetic */ void a(C0189w wVar, Application application) {
        if (application != null) {
            application.registerActivityLifecycleCallbacks(new C0190x(wVar));
        }
    }
}
