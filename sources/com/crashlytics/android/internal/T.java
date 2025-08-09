package com.crashlytics.android.internal;

final class T implements Runnable {
    private /* synthetic */ O a;

    T(O o) {
        this.a = o;
    }

    public final void run() {
        try {
            U u = this.a.a;
            this.a.a = new I();
            u.b();
        } catch (Exception e) {
            C0143ab.d("Crashlytics failed to disable analytics.");
        }
    }
}
