package com.google.android.gms.internal;

import android.os.Handler;
import java.lang.ref.WeakReference;

public final class z {
    private final a ld;
    private final Runnable le;
    /* access modifiers changed from: private */
    public aj lf;
    /* access modifiers changed from: private */
    public boolean lg;
    private boolean lh;
    private long li;

    public static class a {
        private final Handler mHandler;

        public a(Handler handler) {
            this.mHandler = handler;
        }

        public boolean postDelayed(Runnable runnable, long timeFromNowInMillis) {
            return this.mHandler.postDelayed(runnable, timeFromNowInMillis);
        }

        public void removeCallbacks(Runnable runnable) {
            this.mHandler.removeCallbacks(runnable);
        }
    }

    public z(v vVar) {
        this(vVar, new a(eu.ss));
    }

    z(final v vVar, a aVar) {
        this.lg = false;
        this.lh = false;
        this.li = 0;
        this.ld = aVar;
        this.le = new Runnable() {
            private final WeakReference<v> lj = new WeakReference<>(vVar);

            public void run() {
                boolean unused = z.this.lg = false;
                v vVar = (v) this.lj.get();
                if (vVar != null) {
                    vVar.b(z.this.lf);
                }
            }
        };
    }

    public void a(aj ajVar, long j) {
        if (this.lg) {
            ev.D("An ad refresh is already scheduled.");
            return;
        }
        this.lf = ajVar;
        this.lg = true;
        this.li = j;
        if (!this.lh) {
            ev.B("Scheduling ad refresh " + j + " milliseconds from now.");
            this.ld.postDelayed(this.le, j);
        }
    }

    public void cancel() {
        this.lg = false;
        this.ld.removeCallbacks(this.le);
    }

    public void d(aj ajVar) {
        a(ajVar, 60000);
    }

    public void pause() {
        this.lh = true;
        if (this.lg) {
            this.ld.removeCallbacks(this.le);
        }
    }

    public void resume() {
        this.lh = false;
        if (this.lg) {
            this.lg = false;
            a(this.lf, this.li);
        }
    }
}
