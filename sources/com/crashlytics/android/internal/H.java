package com.crashlytics.android.internal;

import java.io.File;
import java.util.List;

final class H extends Z implements N {
    public H(String str, String str2, C0163av avVar) {
        super(str, str2, avVar, C0165ax.POST);
    }

    public final boolean a(String str, List<File> list) {
        C0166ay a = b().a("X-CRASHLYTICS-API-CLIENT-TYPE", "android").a("X-CRASHLYTICS-API-CLIENT-VERSION", C0188v.a().getVersion()).a("X-CRASHLYTICS-API-KEY", str);
        int i = 0;
        for (File next : list) {
            C0143ab.c("Adding analytics session file " + next.getName() + " to multipart POST");
            a.a("session_analytics_file_" + i, next.getName(), "application/vnd.crashlytics.android.events", next);
            i++;
        }
        C0143ab.c("Sending " + list.size() + " analytics files to " + a());
        int b = a.b();
        C0143ab.c("Response code for analytics file send is " + b);
        if (C0184r.a(b) == 0) {
            return true;
        }
        return false;
    }
}
