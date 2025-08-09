package com.crashlytics.android.internal;

import android.os.Looper;

/* renamed from: com.crashlytics.android.internal.o  reason: case insensitive filesystem */
final class C0181o implements C0179m {
    C0181o() {
    }

    public final void a(C0168b bVar) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw new IllegalStateException("Event bus " + bVar + " accessed from non-main thread " + Looper.myLooper());
        }
    }
}
