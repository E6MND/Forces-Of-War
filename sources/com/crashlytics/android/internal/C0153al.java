package com.crashlytics.android.internal;

import java.util.Locale;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

/* renamed from: com.crashlytics.android.internal.al  reason: case insensitive filesystem */
final class C0153al implements ThreadFactory {
    private /* synthetic */ String a;
    private /* synthetic */ AtomicLong b;

    C0153al(String str, AtomicLong atomicLong) {
        this.a = str;
        this.b = atomicLong;
    }

    public final Thread newThread(Runnable runnable) {
        Thread newThread = Executors.defaultThreadFactory().newThread(new C0154am(this, runnable));
        newThread.setName(String.format(Locale.US, this.a, new Object[]{Long.valueOf(this.b.getAndIncrement())}));
        return newThread;
    }
}
