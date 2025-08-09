package com.crashlytics.android.internal;

import java.util.concurrent.ConcurrentLinkedQueue;

/* renamed from: com.crashlytics.android.internal.c  reason: case insensitive filesystem */
final class C0169c extends ThreadLocal<ConcurrentLinkedQueue<C0171e>> {
    C0169c(C0168b bVar) {
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Object initialValue() {
        return new ConcurrentLinkedQueue();
    }
}
