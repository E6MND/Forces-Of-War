package com.aarki;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.webkit.WebView;
import com.aarki.a;
import java.util.Locale;

final class b {
    private static b a;
    private static String b;
    private static String c;
    private static String d;
    private final String e;
    private final String f;
    private final String g;
    private final String h;
    private final String i;
    private final String j;
    private final String k;
    private final SharedPreferences l;

    static synchronized void a(Context context, String str) {
        synchronized (b.class) {
            if (a == null) {
                a = new b(context);
            }
            if (str != null) {
                b = str;
            }
        }
    }

    static synchronized b a() {
        b bVar;
        synchronized (b.class) {
            bVar = a;
        }
        return bVar;
    }

    private b(Context context) {
        String str = null;
        this.e = context == null ? null : ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
        this.f = context == null ? null : Settings.Secure.getString(context.getContentResolver(), "android_id");
        if (context != null && context.checkCallingOrSelfPermission("android.permission.ACCESS_WIFI_STATE") == 0) {
            str = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getMacAddress();
        }
        this.g = str;
        Locale locale = Locale.getDefault();
        this.h = locale.getCountry();
        this.i = a.AnonymousClass1.C0000a.a(locale.getLanguage()) + "_" + a.AnonymousClass1.C0000a.a(locale.getCountry());
        this.j = new WebView(context).getSettings().getUserAgentString();
        this.k = context.getPackageName();
        this.l = context.getSharedPreferences("aarki", 0);
        n();
    }

    /* access modifiers changed from: package-private */
    public final String b() {
        return this.e;
    }

    /* access modifiers changed from: package-private */
    public final String c() {
        return this.f;
    }

    /* access modifiers changed from: package-private */
    public final String d() {
        return this.g;
    }

    /* access modifiers changed from: package-private */
    public final String e() {
        return this.h;
    }

    /* access modifiers changed from: package-private */
    public final String f() {
        return this.i;
    }

    /* access modifiers changed from: package-private */
    public final String g() {
        return this.j;
    }

    /* access modifiers changed from: package-private */
    public final String h() {
        return this.k;
    }

    /* access modifiers changed from: package-private */
    public final String i() {
        String j2 = j();
        return j2 != null ? j2 : a.AnonymousClass1.C0000a.a(this.f) + ":" + a.AnonymousClass1.C0000a.a(this.e);
    }

    private void n() {
        SharedPreferences.Editor edit = this.l.edit();
        if (c != null) {
            edit.putString("user_id", c);
        }
        if (d != null) {
            edit.putString("client_type", d);
        }
        edit.commit();
    }

    static synchronized void a(String str) {
        synchronized (b.class) {
            c = str;
            if (a != null) {
                a.n();
            }
            "Storing user ID: " + str;
        }
    }

    static synchronized void b(String str) {
        synchronized (b.class) {
            d = str;
            if (a != null) {
                a.n();
            }
            "Storing client type: " + str;
        }
    }

    /* access modifiers changed from: package-private */
    public final String j() {
        return this.l.getString("user_id", (String) null);
    }

    /* access modifiers changed from: package-private */
    public final String k() {
        return this.l.getString("client_type", (String) null);
    }

    static String l() {
        return b;
    }

    static String m() {
        return "android/" + Build.VERSION.RELEASE;
    }
}
