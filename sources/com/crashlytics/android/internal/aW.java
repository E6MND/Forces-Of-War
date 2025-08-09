package com.crashlytics.android.internal;

import com.crashlytics.android.Crashlytics;
import org.json.JSONException;
import org.json.JSONObject;

public class aW {
    private aZ a;
    private final aY b;
    private final C0149ah c;
    private final aN d;
    private final ba e;

    public aW(aZ aZVar, C0149ah ahVar, aY aYVar, aN aNVar, ba baVar) {
        this.a = aZVar;
        this.c = ahVar;
        this.b = aYVar;
        this.d = aNVar;
        this.e = baVar;
    }

    public aX a() {
        return a(aV.USE_CACHE);
    }

    public aX a(aV aVVar) {
        Exception e2;
        aX aXVar;
        aX aXVar2 = null;
        try {
            if (!C0188v.a().f()) {
                aXVar2 = b(aVVar);
            }
            if (aXVar2 == null) {
                try {
                    JSONObject a2 = this.e.a(this.a);
                    if (a2 != null) {
                        aXVar2 = this.b.a(this.c, a2);
                        this.d.a(aXVar2.f, a2);
                        a(a2, "Loaded settings: ");
                    }
                } catch (Exception e3) {
                    Exception exc = e3;
                    aXVar = aXVar2;
                    e2 = exc;
                    C0188v.a().b().a(Crashlytics.TAG, "Unknown error while loading Crashlytics settings. Crashes will be cached until settings can be retrieved.", (Throwable) e2);
                    return aXVar;
                }
            }
            aXVar = aXVar2;
            if (aXVar != null) {
                return aXVar;
            }
            try {
                return b(aV.IGNORE_CACHE_EXPIRATION);
            } catch (Exception e4) {
                e2 = e4;
            }
        } catch (Exception e5) {
            Exception exc2 = e5;
            aXVar = null;
            e2 = exc2;
            C0188v.a().b().a(Crashlytics.TAG, "Unknown error while loading Crashlytics settings. Crashes will be cached until settings can be retrieved.", (Throwable) e2);
            return aXVar;
        }
    }

    private aX b(aV aVVar) {
        Exception e2;
        aX aXVar;
        try {
            if (!aV.SKIP_CACHE_LOOKUP.equals(aVVar)) {
                JSONObject a2 = this.d.a();
                if (a2 != null) {
                    aXVar = this.b.a(this.c, a2);
                    if (aXVar != null) {
                        a(a2, "Loaded cached settings: ");
                        long a3 = this.c.a();
                        if (!aV.IGNORE_CACHE_EXPIRATION.equals(aVVar)) {
                            if (aXVar.f < a3) {
                                C0188v.a().b().a(Crashlytics.TAG, "Cached settings have expired.");
                            }
                        }
                        try {
                            C0188v.a().b().a(Crashlytics.TAG, "Returning cached settings.");
                            return aXVar;
                        } catch (Exception e3) {
                            e2 = e3;
                        }
                    } else {
                        C0188v.a().b().a(Crashlytics.TAG, "Failed to transform cached settings data.", (Throwable) null);
                        return null;
                    }
                } else {
                    C0188v.a().b().a(Crashlytics.TAG, "No cached settings data found.");
                }
            }
            return null;
        } catch (Exception e4) {
            Exception exc = e4;
            aXVar = null;
            e2 = exc;
            C0188v.a().b().a(Crashlytics.TAG, "Failed to get cached settings", (Throwable) e2);
            return aXVar;
        }
    }

    private void a(JSONObject jSONObject, String str) throws JSONException {
        if (!C0143ab.e(C0188v.a().getContext())) {
            jSONObject = this.b.a(jSONObject);
        }
        C0188v.a().b().a(Crashlytics.TAG, str + jSONObject.toString());
    }
}
