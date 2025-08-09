package com.crashlytics.android.internal;

import java.util.Map;

final class V {
    public final String a;
    public final String b;
    public final String c;
    public final String d;
    public final String e;
    public final String f;
    public final String g;
    public final String h;
    public final long i;
    public final W j;
    public final Map<String, String> k;
    private String l;

    public static final V a(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, W w, Map<String, String> map) {
        return new V(str, str2, str3, str4, str5, str6, str7, str8, System.currentTimeMillis(), w, map);
    }

    private V(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, long j2, W w, Map<String, String> map) {
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = str4;
        this.e = str5;
        this.f = str6;
        this.g = str7;
        this.h = str8;
        this.i = j2;
        this.j = w;
        this.k = map;
    }

    public final String toString() {
        if (this.l == null) {
            this.l = "[" + getClass().getSimpleName() + ": appBundleId=" + this.a + ", executionId=" + this.b + ", installationId=" + this.c + ", androidId=" + this.d + ", osVersion=" + this.e + ", deviceModel=" + this.f + ", appVersionCode=" + this.g + ", appVersionName=" + this.h + ", timestamp=" + this.i + ", type=" + this.j + ", details=" + this.k.toString() + "]";
        }
        return this.l;
    }
}
