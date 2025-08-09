package com.google.android.gms.internal;

public abstract class en {
    private final Runnable le = new Runnable() {
        public final void run() {
            Thread unused = en.this.sc = Thread.currentThread();
            en.this.bc();
        }
    };
    /* access modifiers changed from: private */
    public volatile Thread sc;

    public abstract void bc();

    public final void cancel() {
        onStop();
        if (this.sc != null) {
            this.sc.interrupt();
        }
    }

    public abstract void onStop();

    public final void start() {
        eo.execute(this.le);
    }
}
