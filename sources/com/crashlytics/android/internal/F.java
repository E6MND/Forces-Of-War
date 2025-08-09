package com.crashlytics.android.internal;

import android.annotation.TargetApi;
import android.app.Application;
import java.util.concurrent.ScheduledExecutorService;

@TargetApi(14)
final class F extends O {
    private final Application b;
    private final Application.ActivityLifecycleCallbacks c;

    public F(Application application, String str, String str2, String str3, String str4, String str5, String str6, String str7, K k, C0163av avVar) {
        this(application, str, str2, str3, str4, str5, str6, str7, k, C0149ah.b("Crashlytics Trace Manager"), avVar);
    }

    private F(Application application, String str, String str2, String str3, String str4, String str5, String str6, String str7, K k, ScheduledExecutorService scheduledExecutorService, C0163av avVar) {
        super(str, str2, str3, str4, str5, str6, str7, k, scheduledExecutorService, avVar);
        this.c = new G(this);
        this.b = application;
        C0143ab.c("Registering activity lifecycle callbacks for session analytics.");
        application.registerActivityLifecycleCallbacks(this.c);
    }

    /* access modifiers changed from: package-private */
    public final void a() {
        C0143ab.c("Unregistering activity lifecycle callbacks for session analytics");
        this.b.unregisterActivityLifecycleCallbacks(this.c);
        super.a();
    }
}
