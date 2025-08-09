package com.crashlytics.android.internal;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.provider.Settings;
import com.crashlytics.android.Crashlytics;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Pattern;

/* renamed from: com.crashlytics.android.internal.ao  reason: case insensitive filesystem */
public final class C0156ao {
    private static final Pattern a = Pattern.compile("[^\\p{Alnum}]");
    private static final String b = Pattern.quote("/");
    private final ReentrantLock c = new ReentrantLock();
    private final boolean d;
    private final boolean e;
    private final Context f;

    public C0156ao(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("appContext must not be null");
        }
        this.f = context;
        this.d = C0143ab.a(context, "com.crashlytics.CollectDeviceIdentifiers", true);
        if (!this.d) {
            C0188v.a().b().a(Crashlytics.TAG, "Device ID collection disabled for " + context.getPackageName());
        }
        this.e = C0143ab.a(context, "com.crashlytics.CollectUserIdentifiers", true);
        if (!this.e) {
            C0188v.a().b().a(Crashlytics.TAG, "User information collection disabled for " + context.getPackageName());
        }
    }

    public final boolean a() {
        return this.e;
    }

    private boolean a(String str) {
        return this.f.checkCallingPermission(str) == 0;
    }

    private static String b(String str) {
        if (str == null) {
            return null;
        }
        return a.matcher(str).replaceAll("").toLowerCase(Locale.US);
    }

    public final String b() {
        String i = C0188v.a().i();
        if (i != null) {
            return i;
        }
        SharedPreferences a2 = C0143ab.a();
        String string = a2.getString("crashlytics.installation.id", (String) null);
        if (string == null) {
            return a(a2);
        }
        return string;
    }

    public final String c() {
        return String.format(Locale.US, "%s/%s", new Object[]{c(Build.VERSION.RELEASE), c(Build.VERSION.INCREMENTAL)});
    }

    public final String d() {
        return String.format(Locale.US, "%s/%s", new Object[]{c(Build.MANUFACTURER), c(Build.MODEL)});
    }

    private static String c(String str) {
        return str.replaceAll(b, "");
    }

    public final String e() {
        if (!this.d) {
            return "";
        }
        String g = g();
        if (g != null) {
            return g;
        }
        SharedPreferences a2 = C0143ab.a();
        String string = a2.getString("crashlytics.installation.id", (String) null);
        if (string == null) {
            return a(a2);
        }
        return string;
    }

    private String a(SharedPreferences sharedPreferences) {
        this.c.lock();
        try {
            String string = sharedPreferences.getString("crashlytics.installation.id", (String) null);
            if (string == null) {
                string = b(UUID.randomUUID().toString());
                sharedPreferences.edit().putString("crashlytics.installation.id", string).commit();
            }
            return string;
        } finally {
            this.c.unlock();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x001d, code lost:
        r0 = (android.telephony.TelephonyManager) r5.f.getSystemService("phone");
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.Map<com.crashlytics.android.internal.C0157ap, java.lang.String> f() {
        /*
            r5 = this;
            r1 = 0
            java.util.HashMap r2 = new java.util.HashMap
            r2.<init>()
            com.crashlytics.android.internal.ap r0 = com.crashlytics.android.internal.C0157ap.ANDROID_ID
            java.lang.String r3 = r5.g()
            a(r2, r0, r3)
            com.crashlytics.android.internal.ap r3 = com.crashlytics.android.internal.C0157ap.ANDROID_DEVICE_ID
            boolean r0 = r5.d
            if (r0 == 0) goto L_0x0076
            java.lang.String r0 = "android.permission.READ_PHONE_STATE"
            boolean r0 = r5.a((java.lang.String) r0)
            if (r0 == 0) goto L_0x0076
            android.content.Context r0 = r5.f
            java.lang.String r4 = "phone"
            java.lang.Object r0 = r0.getSystemService(r4)
            android.telephony.TelephonyManager r0 = (android.telephony.TelephonyManager) r0
            if (r0 == 0) goto L_0x0076
            java.lang.String r0 = r0.getDeviceId()
            java.lang.String r0 = b(r0)
        L_0x0031:
            a(r2, r3, r0)
            com.crashlytics.android.internal.ap r0 = com.crashlytics.android.internal.C0157ap.ANDROID_SERIAL
            java.lang.String r3 = r5.i()
            a(r2, r0, r3)
            com.crashlytics.android.internal.ap r3 = com.crashlytics.android.internal.C0157ap.WIFI_MAC_ADDRESS
            boolean r0 = r5.d
            if (r0 == 0) goto L_0x0065
            java.lang.String r0 = "android.permission.ACCESS_WIFI_STATE"
            boolean r0 = r5.a((java.lang.String) r0)
            if (r0 == 0) goto L_0x0065
            android.content.Context r0 = r5.f
            java.lang.String r4 = "wifi"
            java.lang.Object r0 = r0.getSystemService(r4)
            android.net.wifi.WifiManager r0 = (android.net.wifi.WifiManager) r0
            if (r0 == 0) goto L_0x0065
            android.net.wifi.WifiInfo r0 = r0.getConnectionInfo()
            if (r0 == 0) goto L_0x0065
            java.lang.String r0 = r0.getMacAddress()
            java.lang.String r1 = b(r0)
        L_0x0065:
            a(r2, r3, r1)
            com.crashlytics.android.internal.ap r0 = com.crashlytics.android.internal.C0157ap.BLUETOOTH_MAC_ADDRESS
            java.lang.String r1 = r5.h()
            a(r2, r0, r1)
            java.util.Map r0 = java.util.Collections.unmodifiableMap(r2)
            return r0
        L_0x0076:
            r0 = r1
            goto L_0x0031
        */
        throw new UnsupportedOperationException("Method not decompiled: com.crashlytics.android.internal.C0156ao.f():java.util.Map");
    }

    private static void a(Map<C0157ap, String> map, C0157ap apVar, String str) {
        if (str != null) {
            map.put(apVar, str);
        }
    }

    public final String g() {
        if (!this.d) {
            return null;
        }
        String string = Settings.Secure.getString(this.f.getContentResolver(), "android_id");
        if (!"9774d56d682e549c".equals(string)) {
            return b(string);
        }
        return null;
    }

    public final String h() {
        if (!this.d || !a("android.permission.BLUETOOTH")) {
            return null;
        }
        try {
            BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
            if (defaultAdapter == null) {
                return null;
            }
            b(defaultAdapter.getAddress());
            return null;
        } catch (Exception e2) {
            C0188v.a().b().a(Crashlytics.TAG, "Utils#getBluetoothMacAddress failed, returning null. Requires prior call to BluetoothAdatpter.getDefaultAdapter() on thread that has called Looper.prepare()", (Throwable) e2);
            return null;
        }
    }

    private String i() {
        if (this.d && Build.VERSION.SDK_INT >= 9) {
            try {
                return b((String) Build.class.getField("SERIAL").get((Object) null));
            } catch (Exception e2) {
                C0188v.a().b().a(Crashlytics.TAG, "Could not retrieve android.os.Build.SERIAL value", (Throwable) e2);
            }
        }
        return null;
    }
}
