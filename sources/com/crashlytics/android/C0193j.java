package com.crashlytics.android;

import android.os.AsyncTask;

/* renamed from: com.crashlytics.android.j  reason: case insensitive filesystem */
final class C0193j extends AsyncTask<Void, Void, Void> {
    private /* synthetic */ long a;
    private /* synthetic */ CrashTest b;

    C0193j(CrashTest crashTest, long j) {
        this.b = crashTest;
        this.a = j;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Object doInBackground(Object[] objArr) {
        return a();
    }

    private Void a() {
        try {
            Thread.sleep(this.a);
        } catch (InterruptedException e) {
        }
        this.b.throwRuntimeException("Background thread crash");
        return null;
    }
}
