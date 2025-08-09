package com.crashlytics.android;

import com.crashlytics.android.internal.C0142aa;
import com.crashlytics.android.internal.C0188v;
import java.util.List;

final class ac extends C0142aa {
    private final float a;
    private /* synthetic */ aa b;

    ac(aa aaVar, float f) {
        this.b = aaVar;
        this.a = f;
    }

    public final void a() {
        try {
            C0188v.a().b().a(Crashlytics.TAG, "Starting report processing in " + this.a + " second(s)...");
            if (this.a > 0.0f) {
                try {
                    Thread.sleep((long) (this.a * 1000.0f));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            Crashlytics instance = Crashlytics.getInstance();
            C0205v l = instance.l();
            List<Z> a2 = this.b.a();
            if (!l.a()) {
                if (a2.isEmpty() || instance.p()) {
                    int i = 0;
                    while (!a2.isEmpty() && !Crashlytics.getInstance().l().a()) {
                        C0188v.a().b().a(Crashlytics.TAG, "Attempting to send " + a2.size() + " report(s)");
                        for (Z a3 : a2) {
                            this.b.a(a3);
                        }
                        a2 = this.b.a();
                        if (!a2.isEmpty()) {
                            int i2 = i + 1;
                            long j = (long) aa.c[Math.min(i, aa.c.length - 1)];
                            C0188v.a().b().a(Crashlytics.TAG, "Report submisson: scheduling delayed retry in " + j + " seconds");
                            try {
                                Thread.sleep(j * 1000);
                                i = i2;
                            } catch (InterruptedException e2) {
                                Thread.currentThread().interrupt();
                            }
                        }
                    }
                } else {
                    C0188v.a().b().a(Crashlytics.TAG, "User declined to send. Removing " + a2.size() + " Report(s).");
                    for (Z a4 : a2) {
                        a4.a();
                    }
                }
            }
        } catch (Exception e3) {
            C0188v.a().b().a(Crashlytics.TAG, "An unexpected error occurred while attempting to upload crash reports.", (Throwable) e3);
        }
        Thread unused = this.b.f = null;
    }
}
