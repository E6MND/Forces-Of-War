package com.crashlytics.android.internal;

import android.content.Context;
import com.crashlytics.android.Crashlytics;
import java.security.GeneralSecurityException;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import javax.crypto.Cipher;
import org.json.JSONObject;

public final class aS {
    private final AtomicReference<aX> a;
    private aW b;
    private boolean c;

    /* synthetic */ aS(byte b2) {
        this();
    }

    public static aS a() {
        return aT.a;
    }

    private aS() {
        this.a = new AtomicReference<>();
        this.c = false;
    }

    public final synchronized aS a(Context context, C0163av avVar, String str, String str2, String str3) {
        aS aSVar;
        if (this.c) {
            aSVar = this;
        } else {
            if (this.b == null) {
                String a2 = C0184r.a(context, false);
                String packageName = context.getPackageName();
                String installerPackageName = context.getPackageManager().getInstallerPackageName(packageName);
                C0149ah ahVar = new C0149ah();
                aY aYVar = new aY();
                aN aNVar = new aN();
                String g = C0143ab.g(context);
                String str4 = str3;
                String str5 = str2;
                String str6 = str;
                this.b = new aW(new aZ(a2, a(a2, packageName, context), C0143ab.a(C0143ab.i(context)), str5, str6, C0150ai.a(installerPackageName).a(), g), ahVar, aYVar, aNVar, new aO(str4, String.format(Locale.US, "https://settings.crashlytics.com/spi/v2/platforms/android/apps/%s/settings", new Object[]{packageName}), avVar));
            }
            this.c = true;
            aSVar = this;
        }
        return aSVar;
    }

    public final aX b() {
        return this.a.get();
    }

    public final <T> T a(aU<T> aUVar, T t) {
        aX aXVar = this.a.get();
        return aXVar == null ? t : aUVar.a(aXVar);
    }

    public final synchronized boolean c() {
        aX a2;
        a2 = this.b.a();
        this.a.set(a2);
        return a2 != null;
    }

    public final synchronized boolean d() {
        aX a2;
        a2 = this.b.a(aV.SKIP_CACHE_LOOKUP);
        this.a.set(a2);
        if (a2 == null) {
            C0188v.a().b().a(Crashlytics.TAG, "Failed to force reload of settings from Crashlytics.", (Throwable) null);
        }
        return a2 != null;
    }

    private static String a(String str, String str2, Context context) {
        try {
            Cipher b2 = C0143ab.b(1, C0143ab.a(str + str2.replaceAll("\\.", new StringBuffer("slc").reverse().toString())));
            JSONObject jSONObject = new JSONObject();
            C0156ao aoVar = new C0156ao(context);
            try {
                jSONObject.put("APPLICATION_INSTALLATION_UUID".toLowerCase(Locale.US), aoVar.b());
            } catch (Exception e) {
                C0188v.a().b().a(Crashlytics.TAG, "Could not write application id to JSON", (Throwable) e);
            }
            for (Map.Entry next : aoVar.f().entrySet()) {
                try {
                    jSONObject.put(((C0157ap) next.getKey()).name().toLowerCase(Locale.US), next.getValue());
                } catch (Exception e2) {
                    C0188v.a().b().a(Crashlytics.TAG, "Could not write value to JSON: " + ((C0157ap) next.getKey()).name(), (Throwable) e2);
                }
            }
            try {
                jSONObject.put("os_version", aoVar.c());
            } catch (Exception e3) {
                C0188v.a().b().a(Crashlytics.TAG, "Could not write OS version to JSON", (Throwable) e3);
            }
            try {
                jSONObject.put("model", aoVar.d());
            } catch (Exception e4) {
                C0188v.a().b().a(Crashlytics.TAG, "Could not write model to JSON", (Throwable) e4);
            }
            if (jSONObject.length() <= 0) {
                return "";
            }
            try {
                return C0143ab.a(b2.doFinal(jSONObject.toString().getBytes()));
            } catch (GeneralSecurityException e5) {
                C0188v.a().b().a(Crashlytics.TAG, "Could not encrypt IDs", (Throwable) e5);
                return "";
            }
        } catch (GeneralSecurityException e6) {
            C0188v.a().b().a(Crashlytics.TAG, "Could not create cipher to encrypt headers.", (Throwable) e6);
            return "";
        }
    }
}
