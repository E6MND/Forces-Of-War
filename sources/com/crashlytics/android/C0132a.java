package com.crashlytics.android;

import android.content.res.Resources;
import com.crashlytics.android.internal.C0143ab;
import com.crashlytics.android.internal.C0163av;
import com.crashlytics.android.internal.C0165ax;
import com.crashlytics.android.internal.C0166ay;
import com.crashlytics.android.internal.C0184r;
import com.crashlytics.android.internal.C0188v;
import com.crashlytics.android.internal.Z;
import java.io.Closeable;
import java.io.InputStream;

/* renamed from: com.crashlytics.android.a  reason: case insensitive filesystem */
abstract class C0132a extends Z {
    public C0132a(String str, String str2, C0163av avVar, C0165ax axVar) {
        super(str, str2, avVar, axVar);
    }

    public final boolean a(C0133b bVar) {
        C0166ay a = a(b().a("X-CRASHLYTICS-API-KEY", bVar.a).a("X-CRASHLYTICS-API-CLIENT-TYPE", "android").a("X-CRASHLYTICS-API-CLIENT-VERSION", C0188v.a().getVersion()), bVar);
        C0188v.a().b().a(Crashlytics.TAG, "Sending app info to " + a());
        if (bVar.j != null) {
            C0188v.a().b().a(Crashlytics.TAG, "App icon hash is " + bVar.j.a);
            C0188v.a().b().a(Crashlytics.TAG, "App icon size is " + bVar.j.c + "x" + bVar.j.d);
        }
        int b = a.b();
        C0188v.a().b().a(Crashlytics.TAG, ("POST".equals(a.d()) ? "Create" : "Update") + " app request ID: " + a.a("X-REQUEST-ID"));
        C0188v.a().b().a(Crashlytics.TAG, "Result was " + b);
        if (C0184r.a(b) == 0) {
            return true;
        }
        return false;
    }

    private static C0166ay a(C0166ay ayVar, C0133b bVar) {
        String str;
        C0166ay b = ayVar.b("app[identifier]", bVar.b).b("app[name]", bVar.f).b("app[display_version]", bVar.c).b("app[build_version]", bVar.d).a("app[source]", (Number) Integer.valueOf(bVar.g)).b("app[minimum_sdk_version]", bVar.h).b("app[built_sdk_version]", bVar.i);
        if (!C0143ab.e(bVar.e)) {
            b.b("app[instance_identifier]", bVar.e);
        }
        if (bVar.j != null) {
            InputStream inputStream = null;
            try {
                inputStream = C0188v.a().getContext().getResources().openRawResource(bVar.j.b);
                b.b("app[icon][hash]", bVar.j.a).a("app[icon][data]", "icon.png", "application/octet-stream", inputStream).a("app[icon][width]", (Number) Integer.valueOf(bVar.j.c)).a("app[icon][height]", (Number) Integer.valueOf(bVar.j.d));
            } catch (Resources.NotFoundException e) {
                C0188v.a().b().a(Crashlytics.TAG, "Failed to find app icon with resource ID: " + bVar.j.b, (Throwable) e);
            } finally {
                str = "Failed to close app icon InputStream.";
                C0143ab.a((Closeable) inputStream, str);
            }
        }
        return b;
    }
}
