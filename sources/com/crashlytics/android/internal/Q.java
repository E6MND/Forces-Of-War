package com.crashlytics.android.internal;

final class Q implements Runnable {
    private /* synthetic */ V a;
    private /* synthetic */ boolean b;
    private /* synthetic */ O c;

    Q(O o, V v, boolean z) {
        this.c = o;
        this.a = v;
        this.b = z;
    }

    public final void run() {
        try {
            this.c.a.a(this.a);
            if (this.b) {
                this.c.a.d();
            }
        } catch (Exception e) {
            C0143ab.d("Crashlytics failed to record session event.");
        }
    }
}
