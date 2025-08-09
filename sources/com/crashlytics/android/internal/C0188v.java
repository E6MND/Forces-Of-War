package com.crashlytics.android.internal;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: com.crashlytics.android.internal.v  reason: case insensitive filesystem */
public final class C0188v extends C0182p {
    private C0168b a = new B(C0179m.a);
    private AtomicReference<C0183q> b = new AtomicReference<>();
    private boolean c;
    private File d;
    private Application e;
    private WeakReference<Activity> f;
    private String g;
    private int h = 4;
    private ConcurrentHashMap<Class<? extends C0187u>, C0187u> i = new ConcurrentHashMap<>();

    public static C0188v a() {
        return C0191y.a;
    }

    C0188v() {
    }

    public static synchronized void a(Context context, C0187u... uVarArr) {
        synchronized (C0188v.class) {
            if (!C0191y.a.isInitialized()) {
                C0188v a2 = C0191y.a;
                a2.e = C0184r.b(context);
                C0188v a3 = a2.a(C0184r.a(context));
                for (C0187u uVar : uVarArr) {
                    if (!a3.i.containsKey(uVarArr)) {
                        a3.i.putIfAbsent(uVar.getClass(), uVar);
                    }
                }
                a3.a(context);
            }
        }
    }

    public final C0183q b() {
        C0183q qVar = this.b.get();
        if (qVar != null) {
            return qVar;
        }
        C0184r rVar = new C0184r();
        if (!this.b.compareAndSet((Object) null, rVar)) {
            return this.b.get();
        }
        return rVar;
    }

    public final void a(C0183q qVar) {
        this.b.set(qVar);
    }

    public final Application d() {
        return this.e;
    }

    /* access modifiers changed from: private */
    public C0188v a(Activity activity) {
        this.f = new WeakReference<>(activity);
        return this;
    }

    public final Activity e() {
        if (this.f != null) {
            return (Activity) this.f.get();
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public final void c() {
        Context context = getContext();
        this.d = new File(context.getFilesDir(), "com.crashlytics.sdk.android");
        if (!this.d.exists()) {
            this.d.mkdirs();
        }
        if (Build.VERSION.SDK_INT >= 14) {
            C0189w.a(new C0189w(this, (byte) 0), this.e);
        }
        if (!this.c || !Log.isLoggable("CrashlyticsInternal", 3)) {
            for (C0187u a2 : this.i.values()) {
                a2.a(context);
            }
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (C0182p next : this.i.values()) {
            long nanoTime = System.nanoTime();
            next.a(context);
            sb.append("sdkPerfStart.").append(next.getClass().getName()).append('=').append(System.nanoTime() - nanoTime).append(10);
        }
        Log.d("CrashlyticsInternal", sb.toString());
    }

    public final String getVersion() {
        return "1.1.13.29";
    }

    public final <T extends C0187u> T a(Class<T> cls) {
        return (C0187u) this.i.get(cls);
    }

    public final void a(boolean z) {
        this.c = z;
        this.h = z ? 3 : 4;
    }

    public final boolean f() {
        return this.c;
    }

    public final int g() {
        return this.h;
    }

    public final File h() {
        return this.d;
    }

    public final void a(String str) {
        this.g = str;
    }

    public final String i() {
        return this.g;
    }
}
