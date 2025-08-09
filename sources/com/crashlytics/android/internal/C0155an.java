package com.crashlytics.android.internal;

import com.crashlytics.android.Crashlytics;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/* renamed from: com.crashlytics.android.internal.an  reason: case insensitive filesystem */
final class C0155an extends C0142aa {
    private /* synthetic */ String a;
    private /* synthetic */ ExecutorService b;
    private /* synthetic */ long c;
    private /* synthetic */ TimeUnit d;

    C0155an(String str, ExecutorService executorService, long j, TimeUnit timeUnit) {
        this.a = str;
        this.b = executorService;
        this.c = j;
        this.d = timeUnit;
    }

    public final void a() {
        try {
            C0188v.a().b().a(Crashlytics.TAG, "Executing shutdown hook for " + this.a);
            this.b.shutdown();
            if (!this.b.awaitTermination(this.c, this.d)) {
                C0188v.a().b().a(Crashlytics.TAG, this.a + " did not shut down in the allocated time. Requesting immediate shutdown.");
                this.b.shutdownNow();
            }
        } catch (InterruptedException e) {
            C0188v.a().b().a(Crashlytics.TAG, String.format(Locale.US, "Interrupted while waiting for %s to shut down. Requesting immediate shutdown.", new Object[]{this.a}));
            this.b.shutdownNow();
        }
    }
}
