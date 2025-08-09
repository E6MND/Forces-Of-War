package com.crashlytics.android;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.crashlytics.android.internal.A;
import com.crashlytics.android.internal.C0143ab;
import com.crashlytics.android.internal.C0147af;
import com.crashlytics.android.internal.C0148ag;
import com.crashlytics.android.internal.C0150ai;
import com.crashlytics.android.internal.C0156ao;
import com.crashlytics.android.internal.C0163av;
import com.crashlytics.android.internal.C0165ax;
import com.crashlytics.android.internal.C0166ay;
import com.crashlytics.android.internal.C0183q;
import com.crashlytics.android.internal.C0184r;
import com.crashlytics.android.internal.C0187u;
import com.crashlytics.android.internal.C0188v;
import com.crashlytics.android.internal.D;
import com.crashlytics.android.internal.aG;
import com.crashlytics.android.internal.aM;
import com.crashlytics.android.internal.aQ;
import com.crashlytics.android.internal.aR;
import com.crashlytics.android.internal.aS;
import com.crashlytics.android.internal.aX;
import com.facebook.AppEventsConstants;
import java.net.URL;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HttpsURLConnection;

public final class Crashlytics extends C0187u {
    public static final String TAG = "Crashlytics";
    private static ContextWrapper j;
    private static String k;
    private static String l;
    private static String m;
    private static String n;
    private static String o;
    private static String p;
    private static String q;
    private static boolean r = false;
    private static PinningInfoProvider s = null;
    /* access modifiers changed from: private */
    public static C0163av t;
    private static float u;
    private static Crashlytics v;
    private final long a = System.currentTimeMillis();
    private final ConcurrentHashMap<String, String> b = new ConcurrentHashMap<>();
    private CrashlyticsListener c;
    /* access modifiers changed from: private */
    public C0205v d;
    private C0156ao e = null;
    private String f = null;
    private String g = null;
    private String h = null;
    private String i;

    static /* synthetic */ int a(Crashlytics crashlytics, float f2, int i2) {
        return (int) (((float) i2) * f2);
    }

    static /* synthetic */ boolean a(Crashlytics crashlytics, Activity activity, aQ aQVar) {
        X x = new X(activity, aQVar);
        C0204u uVar = new C0204u(crashlytics, (byte) 0);
        activity.runOnUiThread(new C0199p(crashlytics, activity, uVar, x, aQVar));
        C0188v.a().b().a(TAG, "Waiting for user opt-in.");
        uVar.b();
        return uVar.a();
    }

    public static void start(Context context) {
        start(context, 1.0f);
    }

    public static void start(Context context, float delay) {
        u = delay;
        if (!C0143ab.d(context)) {
            C0188v.a().a((C0183q) new A());
        }
        C0188v.a(context, getInstance(), new D());
    }

    public static synchronized Crashlytics getInstance() {
        Crashlytics crashlytics;
        synchronized (Crashlytics.class) {
            crashlytics = (Crashlytics) C0188v.a().a(Crashlytics.class);
            if (crashlytics == null) {
                if (v == null) {
                    v = new Crashlytics();
                }
                crashlytics = v;
            }
        }
        return crashlytics;
    }

    public static void logException(Throwable throwable) {
        Crashlytics instance = getInstance();
        if (instance == null || instance.d == null) {
            C0188v.a().b().a(TAG, "Crashlytics must be initialized by calling Crashlytics.start(Context) prior to logging exceptions.", (Throwable) null);
        } else if (throwable == null) {
            C0188v.a().b().a(5, TAG, "Crashlytics is ignoring a request to log a null exception.");
        } else {
            instance.d.a(Thread.currentThread(), throwable);
        }
    }

    static void a(String str) {
        D d2 = (D) C0188v.a().a(D.class);
        if (d2 != null) {
            d2.a(new C0148ag(str));
        }
    }

    static void b(String str) {
        D d2 = (D) C0188v.a().a(D.class);
        if (d2 != null) {
            d2.a(new C0147af(str));
        }
    }

    public static void log(String msg) {
        a(3, TAG, msg);
    }

    private static void a(int i2, String str, String str2) {
        Crashlytics instance = getInstance();
        if (instance == null || instance.d == null) {
            C0188v.a().b().a(str, "Crashlytics must be initialized by calling Crashlytics.start(Context) prior to logging messages.", (Throwable) null);
            return;
        }
        instance.d.a(System.currentTimeMillis() - instance.a, C0143ab.b(i2) + "/" + str + " " + str2);
    }

    public static void log(int priority, String tag, String msg) {
        a(priority, tag, msg);
        C0188v.a().b().a(priority, tag, msg, true);
    }

    public static void setUserIdentifier(String identifier) {
        getInstance().f = c(identifier);
    }

    public static void setUserName(String name) {
        getInstance().h = c(name);
    }

    public static void setUserEmail(String email) {
        getInstance().g = c(email);
    }

    public static void setApplicationInstallationIdentifier(String identifier) {
        C0188v.a().a(c(identifier));
    }

    public static void setString(String key, String value) {
        String value2;
        if (key != null) {
            String key2 = c(key);
            if (getInstance().b.size() < 64 || getInstance().b.containsKey(key2)) {
                if (value == null) {
                    value2 = "";
                } else {
                    value2 = c(value);
                }
                getInstance().b.put(key2, value2);
                return;
            }
            C0188v.a().b().a(TAG, "Exceeded maximum number of custom attributes (64)");
        } else if (j == null || !C0143ab.f(j)) {
            C0188v.a().b().a(TAG, "Attempting to set custom attribute with null key, ignoring.", (Throwable) null);
        } else {
            throw new IllegalArgumentException("Custom attribute key cannot be null.");
        }
    }

    public static void setBool(String key, boolean value) {
        setString(key, Boolean.toString(value));
    }

    public static void setDouble(String key, double value) {
        setString(key, Double.toString(value));
    }

    public static void setFloat(String key, float value) {
        setString(key, Float.toString(value));
    }

    public static void setInt(String key, int value) {
        setString(key, Integer.toString(value));
    }

    public static void setLong(String key, long value) {
        setString(key, Long.toString(value));
    }

    /* access modifiers changed from: package-private */
    public final Map<String, String> a() {
        return Collections.unmodifiableMap(this.b);
    }

    public final void setListener(CrashlyticsListener listener) {
        this.c = listener;
    }

    public final void setDebugMode(boolean debug) {
        C0188v.a().a(debug);
    }

    public final boolean getDebugMode() {
        return C0188v.a().f();
    }

    public static void setPinningInfoProvider(PinningInfoProvider pinningInfo) {
        if (s != pinningInfo) {
            s = pinningInfo;
            if (t == null) {
                return;
            }
            if (pinningInfo == null) {
                t.a((aG) null);
            } else {
                t.a(new C0194k(pinningInfo));
            }
        }
    }

    public static PinningInfoProvider getPinningInfoProvider() {
        return s;
    }

    public final boolean verifyPinning(URL url) {
        try {
            if (getPinningInfoProvider() == null) {
                return false;
            }
            C0166ay a2 = t.a(C0165ax.GET, url.toString());
            ((HttpsURLConnection) a2.a()).setInstanceFollowRedirects(false);
            a2.b();
            return true;
        } catch (Exception e2) {
            C0188v.a().b().a(TAG, "Could not verify SSL pinning", (Throwable) e2);
            return false;
        }
    }

    @Deprecated
    public static String getCrashlyticsVersion() {
        return getInstance().getVersion();
    }

    public final void crash() {
        new CrashTest().indexOutOfBounds();
    }

    /* access modifiers changed from: package-private */
    public final C0156ao b() {
        return this.e;
    }

    /* access modifiers changed from: protected */
    public final void c() {
        Context context = super.getContext();
        String a2 = C0184r.a(context, false);
        if (a2 != null) {
            try {
                a(a2, context, u);
            } catch (CrashlyticsMissingDependencyException e2) {
                throw e2;
            } catch (Exception e3) {
                C0188v.a().b().a(TAG, "Crashlytics was not started due to an exception during initialization", (Throwable) e3);
            }
        }
    }

    public final String getVersion() {
        return C0188v.a().getVersion();
    }

    static String d() {
        return k;
    }

    static String e() {
        return l;
    }

    static String f() {
        return o;
    }

    static String g() {
        return n;
    }

    static String h() {
        return m;
    }

    static String i() {
        return C0143ab.a((Context) j, "com.crashlytics.ApiEndpoint");
    }

    /* access modifiers changed from: package-private */
    public final boolean j() {
        return ((Boolean) aS.a().a(new C0195l(this), false)).booleanValue();
    }

    static boolean k() {
        return C0143ab.a().getBoolean("always_send_reports_opt_in", false);
    }

    static void a(boolean z) {
        C0143ab.a().edit().putBoolean("always_send_reports_opt_in", true).commit();
    }

    /* access modifiers changed from: package-private */
    public final C0205v l() {
        return this.d;
    }

    /* access modifiers changed from: package-private */
    public final String m() {
        if (this.e.a()) {
            return this.f;
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public final String n() {
        if (this.e.a()) {
            return this.g;
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public final String o() {
        if (this.e.a()) {
            return this.h;
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public final boolean p() {
        return ((Boolean) aS.a().a(new C0196m(this), true)).booleanValue();
    }

    /* access modifiers changed from: package-private */
    public final V q() {
        return (V) aS.a().a(new C0197n(this), null);
    }

    /* access modifiers changed from: package-private */
    public final aR r() {
        return (aR) aS.a().a(new C0198o(this), null);
    }

    private static String c(String str) {
        if (str == null) {
            return str;
        }
        String trim = str.trim();
        if (trim.length() > 1024) {
            return trim.substring(0, 1024);
        }
        return trim;
    }

    private synchronized void a(String str, Context context, float f2) {
        boolean z = false;
        synchronized (this) {
            if (j != null) {
                C0188v.a().b().a(TAG, "Crashlytics already started, ignoring re-initialization attempt.");
            } else {
                p = str;
                j = new ContextWrapper(context.getApplicationContext());
                t = new C0163av(C0188v.a().b());
                C0188v.a().b().b(TAG, "Initializing Crashlytics " + getCrashlyticsVersion());
                try {
                    k = j.getPackageName();
                    PackageManager packageManager = j.getPackageManager();
                    l = packageManager.getInstallerPackageName(k);
                    C0188v.a().b().a(TAG, "Installer package name is: " + l);
                    PackageInfo packageInfo = packageManager.getPackageInfo(k, 0);
                    n = Integer.toString(packageInfo.versionCode);
                    o = packageInfo.versionName == null ? "0.0" : packageInfo.versionName;
                    m = context.getPackageManager().getApplicationLabel(context.getApplicationInfo()).toString();
                    q = Integer.toString(context.getApplicationInfo().targetSdkVersion);
                    this.i = C0143ab.i(context);
                } catch (Exception e2) {
                    C0188v.a().b().a(TAG, "Error setting up app properties", (Throwable) e2);
                }
                this.e = new C0156ao(j);
                this.e.h();
                new C0134c(this.i, C0143ab.a((Context) j, "com.crashlytics.RequireBuildId", true)).a(str, k);
                try {
                    C0188v.a().b().a(TAG, "Installing exception handler...");
                    this.d = new C0205v(Thread.getDefaultUncaughtExceptionHandler(), this.c, this.i);
                    z = this.d.f();
                    this.d.d();
                    this.d.c();
                    this.d.h();
                    Thread.setDefaultUncaughtExceptionHandler(this.d);
                    C0188v.a().b().a(TAG, "Successfully installed exception handler.");
                } catch (Exception e3) {
                    C0188v.a().b().a(TAG, "There was a problem installing the exception handler.", (Throwable) e3);
                }
                CountDownLatch countDownLatch = new CountDownLatch(1);
                new Thread(new C0203t(this, context, f2, countDownLatch), "Crashlytics Initializer").start();
                if (z) {
                    C0188v.a().b().a(TAG, "Crashlytics detected incomplete initialization on previous app launch. Will initialize synchronously.");
                    try {
                        if (!countDownLatch.await(4000, TimeUnit.MILLISECONDS)) {
                            C0188v.a().b().c(TAG, "Crashlytics initialization was not completed in the allotted time.");
                        }
                    } catch (InterruptedException e4) {
                        C0188v.a().b().a(TAG, "Crashlytics was interrupted during initialization.", (Throwable) e4);
                    }
                }
            }
        }
        return;
    }

    /* access modifiers changed from: private */
    public boolean a(Context context, float f2) {
        aX aXVar;
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4 = true;
        boolean z5 = false;
        String g2 = C0143ab.g(getContext());
        try {
            aS.a().a(context, t, n, o, i()).c();
            aXVar = aS.a().b();
        } catch (Exception e2) {
            C0188v.a().b().a(TAG, "Error dealing with settings", (Throwable) e2);
            aXVar = null;
        }
        if (aXVar != null) {
            try {
                aM aMVar = aXVar.a;
                if ("new".equals(aMVar.a)) {
                    if (new T(i(), aMVar.b, t).a(a(Y.a(getContext(), g2)))) {
                        z3 = aS.a().d();
                    } else {
                        C0188v.a().b().a(TAG, "Failed to create app with Crashlytics service.", (Throwable) null);
                        z3 = false;
                    }
                } else if ("configured".equals(aMVar.a)) {
                    z3 = aS.a().d();
                } else {
                    if (aMVar.d) {
                        C0188v.a().b().a(TAG, "Server says an update is required - forcing a full App update.");
                        new ad(i(), aMVar.b, t).a(a(Y.a(getContext(), g2)));
                    }
                    z3 = true;
                }
                z2 = z3;
            } catch (Exception e3) {
                C0188v.a().b().a(TAG, "Error performing auto configuration.", (Throwable) e3);
                z2 = false;
            }
            try {
                z = aXVar.d.b;
            } catch (Exception e4) {
                C0188v.a().b().a(TAG, "Error getting collect reports setting.", (Throwable) e4);
                z = false;
            }
        } else {
            z = false;
            z2 = false;
        }
        if (!z2 || !z) {
            z5 = true;
        } else {
            try {
                z4 = this.d.b() & true;
                V q2 = q();
                if (q2 != null) {
                    new aa(q2).a(f2);
                }
            } catch (Exception e5) {
                C0188v.a().b().a(TAG, "Error sending crash report", (Throwable) e5);
            }
        }
        if (z5) {
            C0188v.a().b().a(TAG, "Crash reporting disabled.");
        }
        return z4;
    }

    private C0133b a(Y y) {
        return new C0133b(p, k, o, n, C0143ab.a(this.i), m, C0150ai.a(l).a(), q, AppEventsConstants.EVENT_PARAM_VALUE_NO, y);
    }
}
