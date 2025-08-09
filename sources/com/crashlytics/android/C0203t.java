package com.crashlytics.android;

import android.content.Context;
import com.crashlytics.android.internal.C0142aa;
import com.crashlytics.android.internal.C0188v;
import java.util.concurrent.CountDownLatch;

/* renamed from: com.crashlytics.android.t  reason: case insensitive filesystem */
final class C0203t extends C0142aa {
    private /* synthetic */ Context a;
    private /* synthetic */ float b;
    private /* synthetic */ CountDownLatch c;
    private /* synthetic */ Crashlytics d;

    C0203t(Crashlytics crashlytics, Context context, float f, CountDownLatch countDownLatch) {
        this.d = crashlytics;
        this.a = context;
        this.b = f;
        this.c = countDownLatch;
    }

    public final void a() {
        try {
            if (this.d.a(this.a, this.b)) {
                this.d.d.e();
            }
        } catch (Exception e) {
            C0188v.a().b().a(Crashlytics.TAG, "Problem encountered during Crashlytics initialization.", (Throwable) e);
        } finally {
            this.c.countDown();
        }
    }
}
