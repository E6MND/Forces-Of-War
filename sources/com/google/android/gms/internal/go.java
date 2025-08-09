package com.google.android.gms.internal;

import android.text.TextUtils;
import android.util.Log;

public class go {
    private static boolean Ci = false;
    private boolean Cj;
    private boolean Ck;
    private String Cl;
    private final String mTag;

    public go(String str) {
        this(str, en());
    }

    public go(String str, boolean z) {
        this.mTag = str;
        this.Cj = z;
        this.Ck = false;
    }

    private String e(String str, Object... objArr) {
        String format = String.format(str, objArr);
        return !TextUtils.isEmpty(this.Cl) ? this.Cl + format : format;
    }

    public static boolean en() {
        return Ci;
    }

    public void a(String str, Object... objArr) {
        if (em()) {
            Log.v(this.mTag, e(str, objArr));
        }
    }

    public void a(Throwable th, String str, Object... objArr) {
        if (el() || Ci) {
            Log.d(this.mTag, e(str, objArr), th);
        }
    }

    public void ap(String str) {
        String format;
        if (TextUtils.isEmpty(str)) {
            format = null;
        } else {
            format = String.format("[%s] ", new Object[]{str});
        }
        this.Cl = format;
    }

    public void b(String str, Object... objArr) {
        if (el() || Ci) {
            Log.d(this.mTag, e(str, objArr));
        }
    }

    public void c(String str, Object... objArr) {
        Log.i(this.mTag, e(str, objArr));
    }

    public void d(String str, Object... objArr) {
        Log.w(this.mTag, e(str, objArr));
    }

    public boolean el() {
        return this.Cj;
    }

    public boolean em() {
        return this.Ck;
    }
}
