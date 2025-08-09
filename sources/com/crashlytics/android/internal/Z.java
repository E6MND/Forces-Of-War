package com.crashlytics.android.internal;

import java.util.Collections;
import java.util.Map;
import java.util.regex.Pattern;

public abstract class Z {
    private static String a = ("Crashlytics Android SDK/" + C0188v.a().getVersion());
    private static final Pattern b = Pattern.compile("http(s?)://[^\\/]+", 2);
    private final String c;
    private final C0163av d;
    private final C0165ax e;
    private final String f;

    public Z(String str, String str2, C0163av avVar, C0165ax axVar) {
        if (str2 == null) {
            throw new IllegalArgumentException("url must not be null.");
        } else if (avVar == null) {
            throw new IllegalArgumentException("requestFactory must not be null.");
        } else {
            this.f = str;
            this.c = !C0143ab.e(this.f) ? b.matcher(str2).replaceFirst(this.f) : str2;
            this.d = avVar;
            this.e = axVar;
        }
    }

    /* access modifiers changed from: protected */
    public final String a() {
        return this.c;
    }

    /* access modifiers changed from: protected */
    public final C0166ay b() {
        return a(Collections.emptyMap());
    }

    /* access modifiers changed from: protected */
    public final C0166ay a(Map<String, String> map) {
        return this.d.a(this.e, this.c, map).a(false).a(10000).a("User-Agent", a).a("X-CRASHLYTICS-DEVELOPER-TOKEN", "bca6990fc3c15a8105800c0673517a4b579634a1");
    }
}
