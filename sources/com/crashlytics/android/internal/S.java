package com.crashlytics.android.internal;

final class S implements Runnable {
    private /* synthetic */ O a;

    S(O o) {
        this.a = o;
    }

    public final void run() {
        try {
            this.a.a.a();
        } catch (Exception e) {
            C0143ab.d("Crashlytics failed to send analytics files.");
        }
    }
}
