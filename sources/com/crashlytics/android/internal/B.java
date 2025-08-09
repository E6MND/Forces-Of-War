package com.crashlytics.android.internal;

import android.os.Handler;
import android.os.Looper;

public final class B extends C0168b {
    private final Handler b = new Handler(Looper.getMainLooper());

    public B(C0179m mVar) {
        super(mVar);
    }

    public final void c(Object obj) {
        if (obj instanceof C0186t) {
            C0186t tVar = (C0186t) obj;
            if (Looper.myLooper() == Looper.getMainLooper()) {
                super.c(tVar);
            } else {
                this.b.post(new C(this, tVar));
            }
        } else if ((obj instanceof C0185s) || (obj instanceof C0172f)) {
            super.c(obj);
        } else {
            throw new IllegalArgumentException("Posted argument must implement Event interface");
        }
    }
}
