package com.crashlytics.android.internal;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

/* renamed from: com.crashlytics.android.internal.x  reason: case insensitive filesystem */
final class C0190x implements Application.ActivityLifecycleCallbacks {
    private /* synthetic */ C0189w a;

    C0190x(C0189w wVar) {
        this.a = wVar;
    }

    public final void onActivityCreated(Activity activity, Bundle bundle) {
        C0188v unused = this.a.a.a(activity);
    }

    public final void onActivityStarted(Activity activity) {
        C0188v unused = this.a.a.a(activity);
    }

    public final void onActivityResumed(Activity activity) {
        C0188v unused = this.a.a.a(activity);
    }

    public final void onActivityPaused(Activity activity) {
    }

    public final void onActivityStopped(Activity activity) {
    }

    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public final void onActivityDestroyed(Activity activity) {
    }
}
