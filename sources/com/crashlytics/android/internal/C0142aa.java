package com.crashlytics.android.internal;

import android.os.Process;

/* renamed from: com.crashlytics.android.internal.aa  reason: case insensitive filesystem */
public abstract class C0142aa implements Runnable {
    /* access modifiers changed from: protected */
    public abstract void a();

    public final void run() {
        Process.setThreadPriority(10);
        a();
    }
}
