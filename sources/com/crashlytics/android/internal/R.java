package com.crashlytics.android.internal;

final class R implements Runnable {
    private /* synthetic */ aK a;
    private /* synthetic */ String b;
    private /* synthetic */ O c;

    R(O o, aK aKVar, String str) {
        this.c = o;
        this.a = aKVar;
        this.b = str;
    }

    public final void run() {
        try {
            this.c.a.a(this.a, this.b);
        } catch (Exception e) {
            C0143ab.d("Crashlytics failed to set analytics settings data.");
        }
    }
}
