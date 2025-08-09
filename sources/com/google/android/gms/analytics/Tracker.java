package com.google.android.gms.analytics;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import com.facebook.AppEventsConstants;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.u;
import com.google.android.gms.internal.hn;
import java.lang.Thread;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

public class Tracker {
    private Context mContext;
    private final TrackerHandler xb;
    private final Map<String, String> xc;
    private ad xd;
    private final h xe;
    private final ae xf;
    private final g xg;
    private boolean xh;
    /* access modifiers changed from: private */
    public a xi;
    /* access modifiers changed from: private */
    public aj xj;
    private ExceptionReporter xk;

    private class a implements GoogleAnalytics.a {
        private i ur;
        private boolean xl = false;
        private int xm = 0;
        private long xn = -1;
        private boolean xo = false;
        private long xp;

        public a() {
            this.ur = new i(Tracker.this) {
                public long currentTimeMillis() {
                    return System.currentTimeMillis();
                }
            };
        }

        private void dw() {
            GoogleAnalytics dd = GoogleAnalytics.dd();
            if (dd == null) {
                aa.A("GoogleAnalytics isn't initialized for the Tracker!");
            } else if (this.xn >= 0 || this.xl) {
                dd.a((GoogleAnalytics.a) Tracker.this.xi);
            } else {
                dd.b(Tracker.this.xi);
            }
        }

        public long dt() {
            return this.xn;
        }

        public boolean du() {
            return this.xl;
        }

        public boolean dv() {
            boolean z = this.xo;
            this.xo = false;
            return z;
        }

        /* access modifiers changed from: package-private */
        public boolean dx() {
            return this.ur.currentTimeMillis() >= this.xp + Math.max(1000, this.xn);
        }

        public void enableAutoActivityTracking(boolean enabled) {
            this.xl = enabled;
            dw();
        }

        public void h(Activity activity) {
            u.cP().a(u.a.EASY_TRACKER_ACTIVITY_START);
            if (this.xm == 0 && dx()) {
                this.xo = true;
            }
            this.xm++;
            if (this.xl) {
                HashMap hashMap = new HashMap();
                hashMap.put("&t", "screenview");
                u.cP().u(true);
                Tracker.this.set("&cd", Tracker.this.xj != null ? Tracker.this.xj.j(activity) : activity.getClass().getCanonicalName());
                Tracker.this.send(hashMap);
                u.cP().u(false);
            }
        }

        public void i(Activity activity) {
            u.cP().a(u.a.EASY_TRACKER_ACTIVITY_STOP);
            this.xm--;
            this.xm = Math.max(0, this.xm);
            if (this.xm == 0) {
                this.xp = this.ur.currentTimeMillis();
            }
        }

        public void setSessionTimeout(long sessionTimeout) {
            this.xn = sessionTimeout;
            dw();
        }
    }

    Tracker(String trackingId, TrackerHandler handler, Context context) {
        this(trackingId, handler, h.cq(), ae.dq(), g.cp(), new z("tracking"), context);
    }

    Tracker(String trackingId, TrackerHandler handler, h clientIdDefaultProvider, ae screenResolutionDefaultProvider, g appFieldsDefaultProvider, ad rateLimiter, Context context) {
        this.xc = new HashMap();
        this.xb = handler;
        if (context != null) {
            this.mContext = context.getApplicationContext();
        }
        if (trackingId != null) {
            this.xc.put("&tid", trackingId);
        }
        this.xc.put("useSecure", AppEventsConstants.EVENT_PARAM_VALUE_YES);
        this.xe = clientIdDefaultProvider;
        this.xf = screenResolutionDefaultProvider;
        this.xg = appFieldsDefaultProvider;
        this.xc.put("&a", Integer.toString(new Random().nextInt(Integer.MAX_VALUE) + 1));
        this.xd = rateLimiter;
        this.xi = new a();
        enableAdvertisingIdCollection(false);
    }

    /* access modifiers changed from: package-private */
    public void a(aj ajVar) {
        aa.C("Loading Tracker config values.");
        this.xj = ajVar;
        if (this.xj.dz()) {
            String dA = this.xj.dA();
            set("&tid", dA);
            aa.C("[Tracker] trackingId loaded: " + dA);
        }
        if (this.xj.dB()) {
            String d = Double.toString(this.xj.dC());
            set("&sf", d);
            aa.C("[Tracker] sample frequency loaded: " + d);
        }
        if (this.xj.dD()) {
            setSessionTimeout((long) this.xj.getSessionTimeout());
            aa.C("[Tracker] session timeout loaded: " + dt());
        }
        if (this.xj.dE()) {
            enableAutoActivityTracking(this.xj.dF());
            aa.C("[Tracker] auto activity tracking loaded: " + du());
        }
        if (this.xj.dG()) {
            if (this.xj.dH()) {
                set("&aip", AppEventsConstants.EVENT_PARAM_VALUE_YES);
                aa.C("[Tracker] anonymize ip loaded: true");
            }
            aa.C("[Tracker] anonymize ip loaded: false");
        }
        enableExceptionReporting(this.xj.dI());
    }

    /* access modifiers changed from: package-private */
    public long dt() {
        return this.xi.dt();
    }

    /* access modifiers changed from: package-private */
    public boolean du() {
        return this.xi.du();
    }

    public void enableAdvertisingIdCollection(boolean enabled) {
        if (!enabled) {
            this.xc.put("&ate", (Object) null);
            this.xc.put("&adid", (Object) null);
            return;
        }
        if (this.xc.containsKey("&ate")) {
            this.xc.remove("&ate");
        }
        if (this.xc.containsKey("&adid")) {
            this.xc.remove("&adid");
        }
    }

    public void enableAutoActivityTracking(boolean enabled) {
        this.xi.enableAutoActivityTracking(enabled);
    }

    public void enableExceptionReporting(boolean enabled) {
        if (this.xh != enabled) {
            this.xh = enabled;
            if (enabled) {
                this.xk = new ExceptionReporter(this, Thread.getDefaultUncaughtExceptionHandler(), this.mContext);
                Thread.setDefaultUncaughtExceptionHandler(this.xk);
                aa.C("Uncaught exceptions will be reported to Google Analytics.");
                return;
            }
            if (this.xk != null) {
                Thread.setDefaultUncaughtExceptionHandler(this.xk.cy());
            } else {
                Thread.setDefaultUncaughtExceptionHandler((Thread.UncaughtExceptionHandler) null);
            }
            aa.C("Uncaught exceptions will not be reported to Google Analytics.");
        }
    }

    public String get(String key) {
        u.cP().a(u.a.GET);
        if (TextUtils.isEmpty(key)) {
            return null;
        }
        if (this.xc.containsKey(key)) {
            return this.xc.get(key);
        }
        if (key.equals("&ul")) {
            return ak.a(Locale.getDefault());
        }
        if (this.xe != null && this.xe.J(key)) {
            return this.xe.getValue(key);
        }
        if (this.xf != null && this.xf.J(key)) {
            return this.xf.getValue(key);
        }
        if (this.xg == null || !this.xg.J(key)) {
            return null;
        }
        return this.xg.getValue(key);
    }

    public void send(Map<String, String> params) {
        u.cP().a(u.a.SEND);
        HashMap hashMap = new HashMap();
        hashMap.putAll(this.xc);
        if (params != null) {
            hashMap.putAll(params);
        }
        if (TextUtils.isEmpty((CharSequence) hashMap.get("&tid"))) {
            aa.D(String.format("Missing tracking id (%s) parameter.", new Object[]{"&tid"}));
        }
        String str = (String) hashMap.get("&t");
        if (TextUtils.isEmpty(str)) {
            aa.D(String.format("Missing hit type (%s) parameter.", new Object[]{"&t"}));
            str = "";
        }
        if (this.xi.dv()) {
            hashMap.put("&sc", "start");
        }
        String lowerCase = str.toLowerCase();
        if ("screenview".equals(lowerCase) || "pageview".equals(lowerCase) || "appview".equals(lowerCase) || TextUtils.isEmpty(lowerCase)) {
            int parseInt = Integer.parseInt(this.xc.get("&a")) + 1;
            if (parseInt >= Integer.MAX_VALUE) {
                parseInt = 1;
            }
            this.xc.put("&a", Integer.toString(parseInt));
        }
        if (lowerCase.equals("transaction") || lowerCase.equals("item") || this.xd.dj()) {
            this.xb.p(hashMap);
        } else {
            aa.D("Too many hits sent too quickly, rate limiting invoked.");
        }
    }

    public void set(String key, String value) {
        hn.b(key, (Object) "Key should be non-null");
        u.cP().a(u.a.SET);
        this.xc.put(key, value);
    }

    public void setAnonymizeIp(boolean anonymize) {
        set("&aip", ak.v(anonymize));
    }

    public void setAppId(String appId) {
        set("&aid", appId);
    }

    public void setAppInstallerId(String appInstallerId) {
        set("&aiid", appInstallerId);
    }

    public void setAppName(String appName) {
        set("&an", appName);
    }

    public void setAppVersion(String appVersion) {
        set("&av", appVersion);
    }

    public void setClientId(String clientId) {
        set("&cid", clientId);
    }

    public void setEncoding(String encoding) {
        set("&de", encoding);
    }

    public void setHostname(String hostname) {
        set("&dh", hostname);
    }

    public void setLanguage(String language) {
        set("&ul", language);
    }

    public void setLocation(String location) {
        set("&dl", location);
    }

    public void setPage(String page) {
        set("&dp", page);
    }

    public void setReferrer(String referrer) {
        set("&dr", referrer);
    }

    public void setSampleRate(double sampleRate) {
        set("&sf", Double.toHexString(sampleRate));
    }

    public void setScreenColors(String screenColors) {
        set("&sd", screenColors);
    }

    public void setScreenName(String screenName) {
        set("&cd", screenName);
    }

    public void setScreenResolution(int width, int height) {
        if (width >= 0 || height >= 0) {
            set("&sr", width + "x" + height);
        } else {
            aa.D("Invalid width or height. The values should be non-negative.");
        }
    }

    public void setSessionTimeout(long sessionTimeout) {
        this.xi.setSessionTimeout(1000 * sessionTimeout);
    }

    public void setTitle(String title) {
        set("&dt", title);
    }

    public void setUseSecure(boolean useSecure) {
        set("useSecure", ak.v(useSecure));
    }

    public void setViewportSize(String viewportSize) {
        set("&vp", viewportSize);
    }
}
