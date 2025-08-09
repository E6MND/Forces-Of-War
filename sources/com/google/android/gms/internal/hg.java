package com.google.android.gms.internal;

import android.util.Log;

public final class hg {
    private final String GF;

    public hg(String str) {
        this.GF = (String) hn.f(str);
    }

    public void a(String str, String str2, Throwable th) {
        if (ap(4)) {
            Log.i(str, str2, th);
        }
    }

    public boolean ap(int i) {
        return Log.isLoggable(this.GF, i);
    }

    public void b(String str, String str2, Throwable th) {
        if (ap(6)) {
            Log.e(str, str2, th);
        }
    }

    public void i(String str, String str2) {
        if (ap(2)) {
            Log.v(str, str2);
        }
    }

    public void j(String str, String str2) {
        if (ap(5)) {
            Log.w(str, str2);
        }
    }

    public void k(String str, String str2) {
        if (ap(6)) {
            Log.e(str, str2);
        }
    }
}
