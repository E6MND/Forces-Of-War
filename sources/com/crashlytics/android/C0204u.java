package com.crashlytics.android;

import java.util.concurrent.CountDownLatch;

/* renamed from: com.crashlytics.android.u  reason: case insensitive filesystem */
final class C0204u {
    private boolean a;
    private final CountDownLatch b;

    private C0204u(Crashlytics crashlytics) {
        this.a = false;
        this.b = new CountDownLatch(1);
    }

    /* synthetic */ C0204u(Crashlytics crashlytics, byte b2) {
        this(crashlytics);
    }

    /* access modifiers changed from: package-private */
    public final void a(boolean z) {
        this.a = z;
        this.b.countDown();
    }

    /* access modifiers changed from: package-private */
    public final boolean a() {
        return this.a;
    }

    /* access modifiers changed from: package-private */
    public final void b() {
        try {
            this.b.await();
        } catch (InterruptedException e) {
        }
    }
}
