package com.crashlytics.android.internal;

import android.app.Activity;
import android.os.Looper;
import java.util.Collections;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.ScheduledExecutorService;

class O implements C0152ak {
    U a;
    /* access modifiers changed from: private */
    public final String b;
    /* access modifiers changed from: private */
    public final String c;
    /* access modifiers changed from: private */
    public final String d;
    /* access modifiers changed from: private */
    public final String e;
    /* access modifiers changed from: private */
    public final String f;
    /* access modifiers changed from: private */
    public final String g;
    /* access modifiers changed from: private */
    public final String h;
    /* access modifiers changed from: private */
    public final String i;
    private final ScheduledExecutorService j;

    public O(String str, String str2, String str3, String str4, String str5, String str6, String str7, K k, C0163av avVar) {
        this(str, str2, str3, str4, str5, str6, str7, k, C0149ah.b("Crashlytics SAM"), avVar);
    }

    O(String str, String str2, String str3, String str4, String str5, String str6, String str7, K k, ScheduledExecutorService scheduledExecutorService, C0163av avVar) {
        this.b = str;
        this.c = str2;
        this.d = str3;
        this.e = str4;
        this.f = str5;
        this.g = str6;
        this.h = str7;
        this.i = UUID.randomUUID().toString();
        this.j = scheduledExecutorService;
        this.a = new J(scheduledExecutorService, k, avVar);
        k.a((C0152ak) this);
    }

    public final void a(String str) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw new IllegalStateException("onCrash called from main thread!!!");
        }
        try {
            this.j.submit(new P(this, str)).get();
        } catch (Exception e2) {
            C0143ab.d("Crashlytics failed to run analytics task");
        }
    }

    public final void b() {
        a(V.a(this.b, this.i, this.c, this.d, this.e, this.f, this.g, this.h, W.INSTALL, new HashMap()), true);
    }

    public final void a(Activity activity) {
        a(W.CREATE, activity, false);
    }

    public final void b(Activity activity) {
        a(W.DESTROY, activity, false);
    }

    public final void b(String str) {
        a(V.a(this.b, this.i, this.c, this.d, this.e, this.f, this.g, this.h, W.ERROR, Collections.singletonMap("sessionId", str)), false);
    }

    public final void c(Activity activity) {
        a(W.PAUSE, activity, false);
    }

    public final void d(Activity activity) {
        a(W.RESUME, activity, false);
    }

    public final void e(Activity activity) {
        a(W.SAVE_INSTANCE_STATE, activity, false);
    }

    public final void f(Activity activity) {
        a(W.START, activity, false);
    }

    public final void g(Activity activity) {
        a(W.STOP, activity, false);
    }

    private void a(W w, Activity activity, boolean z) {
        a(V.a(this.b, this.i, this.c, this.d, this.e, this.f, this.g, this.h, w, Collections.singletonMap("activity", activity.getClass().getName())), false);
    }

    private void a(V v, boolean z) {
        a((Runnable) new Q(this, v, z));
    }

    /* access modifiers changed from: package-private */
    public final void a(aK aKVar, String str) {
        a((Runnable) new R(this, aKVar, str));
    }

    public final void c() {
        a((Runnable) new S(this));
    }

    /* access modifiers changed from: package-private */
    public void a() {
        a((Runnable) new T(this));
    }

    private void a(Runnable runnable) {
        try {
            this.j.submit(runnable);
        } catch (Exception e2) {
            C0143ab.d("Crashlytics failed to submit analytics task");
        }
    }
}
