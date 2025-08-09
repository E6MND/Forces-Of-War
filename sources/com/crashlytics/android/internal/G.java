package com.crashlytics.android.internal;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

final class G implements Application.ActivityLifecycleCallbacks {
    private /* synthetic */ F a;

    G(F f) {
        this.a = f;
    }

    public final void onActivityCreated(Activity activity, Bundle bundle) {
        this.a.a(activity);
    }

    public final void onActivityDestroyed(Activity activity) {
        this.a.b(activity);
    }

    public final void onActivityPaused(Activity activity) {
        this.a.c(activity);
    }

    public final void onActivityResumed(Activity activity) {
        this.a.d(activity);
    }

    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        this.a.e(activity);
    }

    public final void onActivityStarted(Activity activity) {
        this.a.f(activity);
    }

    public final void onActivityStopped(Activity activity) {
        this.a.g(activity);
    }
}
