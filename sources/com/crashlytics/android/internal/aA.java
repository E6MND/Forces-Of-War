package com.crashlytics.android.internal;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;

public abstract class aA<V> extends aE<V> {
    private final Closeable a;
    private final boolean b;

    protected aA(Closeable closeable, boolean z) {
        this.a = closeable;
        this.b = z;
    }

    /* access modifiers changed from: protected */
    public final void b() throws IOException {
        if (this.a instanceof Flushable) {
            ((Flushable) this.a).flush();
        }
        if (this.b) {
            try {
                this.a.close();
            } catch (IOException e) {
            }
        } else {
            this.a.close();
        }
    }
}
