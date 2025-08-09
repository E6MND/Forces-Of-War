package com.crashlytics.android;

import com.crashlytics.android.internal.C0163av;
import com.crashlytics.android.internal.C0165ax;
import com.crashlytics.android.internal.C0166ay;
import com.crashlytics.android.internal.C0184r;
import com.crashlytics.android.internal.C0188v;
import com.crashlytics.android.internal.Z;
import java.util.Iterator;
import java.util.Map;

final class W extends Z implements V {
    public W(String str, String str2, C0163av avVar) {
        super(str, str2, avVar, C0165ax.POST);
    }

    public final boolean a(U u) {
        C0166ay ayVar;
        C0166ay a = b().a("X-CRASHLYTICS-API-KEY", u.a).a("X-CRASHLYTICS-API-CLIENT-TYPE", "android").a("X-CRASHLYTICS-API-CLIENT-VERSION", Crashlytics.getInstance().getVersion());
        Iterator<Map.Entry<String, String>> it = u.b.e().entrySet().iterator();
        while (true) {
            ayVar = a;
            if (!it.hasNext()) {
                break;
            }
            a = ayVar.a(it.next());
        }
        Z z = u.b;
        C0166ay b = ayVar.a("report[file]", z.b(), "application/octet-stream", z.d()).b("report[identifier]", z.c());
        C0188v.a().b().a(Crashlytics.TAG, "Sending report to: " + a());
        int b2 = b.b();
        C0188v.a().b().a(Crashlytics.TAG, "Create report request ID: " + b.a("X-REQUEST-ID"));
        C0188v.a().b().a(Crashlytics.TAG, "Result was: " + b2);
        return C0184r.a(b2) == 0;
    }
}
