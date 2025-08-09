package com.crashlytics.android.internal;

import java.io.IOException;
import java.util.concurrent.Callable;

public abstract class aE<V> implements Callable<V> {
    /* access modifiers changed from: protected */
    public abstract V a() throws aD, IOException;

    /* access modifiers changed from: protected */
    public abstract void b() throws IOException;

    protected aE() {
    }

    public V call() throws aD {
        boolean z = true;
        try {
            V a = a();
            try {
                b();
                return a;
            } catch (IOException e) {
                throw new aD(e);
            }
        } catch (aD e2) {
            throw e2;
        } catch (IOException e3) {
            throw new aD(e3);
        } catch (Throwable th) {
            th = th;
        }
        try {
            b();
        } catch (IOException e4) {
            if (!z) {
                throw new aD(e4);
            }
        }
        throw th;
    }
}
