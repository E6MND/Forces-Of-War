package com.crashlytics.android.internal;

import android.content.Context;

/* renamed from: com.crashlytics.android.internal.p  reason: case insensitive filesystem */
public abstract class C0182p {
    private Context a;
    private boolean b;

    /* access modifiers changed from: protected */
    public abstract void c();

    public abstract String getVersion();

    /* access modifiers changed from: protected */
    public final synchronized void a(Context context) {
        if (!this.b) {
            if (context == null) {
                throw new IllegalArgumentException("context cannot be null.");
            }
            this.a = new C0192z(context.getApplicationContext(), getName());
            this.b = true;
            c();
        }
    }

    public final Context getContext() {
        return this.a;
    }

    public final synchronized boolean isInitialized() {
        return this.b;
    }

    public final String getName() {
        return getClass().getSimpleName().toLowerCase();
    }

    public String getComponentPath() {
        return ".TwitterSdk/" + getName();
    }
}
