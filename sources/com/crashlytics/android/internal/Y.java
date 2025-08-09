package com.crashlytics.android.internal;

final class Y implements Runnable {
    private final K a;
    private final U b;

    public Y(K k, U u) {
        this.a = k;
        this.b = u;
    }

    public final void run() {
        try {
            C0143ab.c("Performing time based analytics file roll over.");
            if (!this.a.a()) {
                this.b.c();
            }
        } catch (Exception e) {
            C0143ab.d("Crashlytics failed to roll over session analytics file");
        }
    }
}
