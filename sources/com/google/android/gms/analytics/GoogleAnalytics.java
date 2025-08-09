package com.google.android.gms.analytics;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import com.google.android.gms.analytics.u;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class GoogleAnalytics extends TrackerHandler {
    private static boolean wj;
    private static GoogleAnalytics wq;
    private Context mContext;
    private String tA;
    private f tS;
    private String tz;
    private boolean wk;
    private af wl;
    private volatile Boolean wm;
    private Logger wn;
    private Set<a> wo;
    private boolean wp;

    interface a {
        void h(Activity activity);

        void i(Activity activity);
    }

    class b implements Application.ActivityLifecycleCallbacks {
        b() {
        }

        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        }

        public void onActivityDestroyed(Activity activity) {
        }

        public void onActivityPaused(Activity activity) {
        }

        public void onActivityResumed(Activity activity) {
        }

        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        }

        public void onActivityStarted(Activity activity) {
            GoogleAnalytics.this.f(activity);
        }

        public void onActivityStopped(Activity activity) {
            GoogleAnalytics.this.g(activity);
        }
    }

    protected GoogleAnalytics(Context context) {
        this(context, t.u(context), r.cz());
    }

    private GoogleAnalytics(Context context, f thread, af serviceManager) {
        this.wm = false;
        this.wp = false;
        if (context == null) {
            throw new IllegalArgumentException("context cannot be null");
        }
        this.mContext = context.getApplicationContext();
        this.tS = thread;
        this.wl = serviceManager;
        g.r(this.mContext);
        ae.r(this.mContext);
        h.r(this.mContext);
        this.wn = new l();
        this.wo = new HashSet();
        de();
    }

    private int P(String str) {
        String lowerCase = str.toLowerCase();
        if ("verbose".equals(lowerCase)) {
            return 0;
        }
        if ("info".equals(lowerCase)) {
            return 1;
        }
        if ("warning".equals(lowerCase)) {
            return 2;
        }
        return "error".equals(lowerCase) ? 3 : -1;
    }

    private Tracker a(Tracker tracker) {
        if (this.tz != null) {
            tracker.set("&an", this.tz);
        }
        if (this.tA != null) {
            tracker.set("&av", this.tA);
        }
        return tracker;
    }

    static GoogleAnalytics dd() {
        GoogleAnalytics googleAnalytics;
        synchronized (GoogleAnalytics.class) {
            googleAnalytics = wq;
        }
        return googleAnalytics;
    }

    private void de() {
        ApplicationInfo applicationInfo;
        int i;
        w wVar;
        if (!wj) {
            try {
                applicationInfo = this.mContext.getPackageManager().getApplicationInfo(this.mContext.getPackageName(), 129);
            } catch (PackageManager.NameNotFoundException e) {
                aa.C("PackageManager doesn't know about package: " + e);
                applicationInfo = null;
            }
            if (applicationInfo == null) {
                aa.D("Couldn't get ApplicationInfo to load gloabl config.");
                return;
            }
            Bundle bundle = applicationInfo.metaData;
            if (bundle != null && (i = bundle.getInt("com.google.android.gms.analytics.globalConfigResource")) > 0 && (wVar = (w) new v(this.mContext).r(i)) != null) {
                a(wVar);
            }
        }
    }

    /* access modifiers changed from: private */
    public void f(Activity activity) {
        for (a h : this.wo) {
            h.h(activity);
        }
    }

    /* access modifiers changed from: private */
    public void g(Activity activity) {
        for (a i : this.wo) {
            i.i(activity);
        }
    }

    public static GoogleAnalytics getInstance(Context context) {
        GoogleAnalytics googleAnalytics;
        synchronized (GoogleAnalytics.class) {
            if (wq == null) {
                wq = new GoogleAnalytics(context);
            }
            googleAnalytics = wq;
        }
        return googleAnalytics;
    }

    /* access modifiers changed from: package-private */
    public void a(a aVar) {
        this.wo.add(aVar);
    }

    /* access modifiers changed from: package-private */
    public void a(w wVar) {
        int P;
        aa.C("Loading global config values.");
        if (wVar.cT()) {
            this.tz = wVar.cU();
            aa.C("app name loaded: " + this.tz);
        }
        if (wVar.cV()) {
            this.tA = wVar.cW();
            aa.C("app version loaded: " + this.tA);
        }
        if (wVar.cX() && (P = P(wVar.cY())) >= 0) {
            aa.C("log level loaded: " + P);
            getLogger().setLogLevel(P);
        }
        if (wVar.cZ()) {
            this.wl.setLocalDispatchPeriod(wVar.da());
        }
        if (wVar.db()) {
            setDryRun(wVar.dc());
        }
    }

    /* access modifiers changed from: package-private */
    public void b(a aVar) {
        this.wo.remove(aVar);
    }

    @Deprecated
    public void dispatchLocalHits() {
        this.wl.dispatchLocalHits();
    }

    public void enableAutoActivityReports(Application application) {
        if (Build.VERSION.SDK_INT >= 14 && !this.wp) {
            application.registerActivityLifecycleCallbacks(new b());
            this.wp = true;
        }
    }

    public boolean getAppOptOut() {
        u.cP().a(u.a.GET_APP_OPT_OUT);
        return this.wm.booleanValue();
    }

    public Logger getLogger() {
        return this.wn;
    }

    public boolean isDryRunEnabled() {
        u.cP().a(u.a.GET_DRY_RUN);
        return this.wk;
    }

    public Tracker newTracker(int configResId) {
        Tracker a2;
        aj ajVar;
        synchronized (this) {
            u.cP().a(u.a.GET_TRACKER);
            Tracker tracker = new Tracker((String) null, this, this.mContext);
            if (configResId > 0 && (ajVar = (aj) new ai(this.mContext).r(configResId)) != null) {
                tracker.a(ajVar);
            }
            a2 = a(tracker);
        }
        return a2;
    }

    public Tracker newTracker(String trackingId) {
        Tracker a2;
        synchronized (this) {
            u.cP().a(u.a.GET_TRACKER);
            a2 = a(new Tracker(trackingId, this, this.mContext));
        }
        return a2;
    }

    /* access modifiers changed from: package-private */
    public void p(Map<String, String> map) {
        synchronized (this) {
            if (map == null) {
                throw new IllegalArgumentException("hit cannot be null");
            }
            ak.a(map, "&ul", ak.a(Locale.getDefault()));
            ak.a(map, "&sr", ae.dq().getValue("&sr"));
            map.put("&_u", u.cP().cR());
            u.cP().cQ();
            this.tS.p(map);
        }
    }

    public void reportActivityStart(Activity activity) {
        if (!this.wp) {
            f(activity);
        }
    }

    public void reportActivityStop(Activity activity) {
        if (!this.wp) {
            g(activity);
        }
    }

    public void setAppOptOut(boolean optOut) {
        u.cP().a(u.a.SET_APP_OPT_OUT);
        this.wm = Boolean.valueOf(optOut);
        if (this.wm.booleanValue()) {
            this.tS.cg();
        }
    }

    public void setDryRun(boolean dryRun) {
        u.cP().a(u.a.SET_DRY_RUN);
        this.wk = dryRun;
    }

    @Deprecated
    public void setLocalDispatchPeriod(int dispatchPeriodInSeconds) {
        this.wl.setLocalDispatchPeriod(dispatchPeriodInSeconds);
    }

    public void setLogger(Logger logger) {
        u.cP().a(u.a.SET_LOGGER);
        this.wn = logger;
    }
}
