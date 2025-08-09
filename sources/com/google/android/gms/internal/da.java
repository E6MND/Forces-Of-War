package com.google.android.gms.internal;

import android.content.Intent;

public class da {
    private final String mx;

    public da(String str) {
        this.mx = str;
    }

    public boolean a(String str, int i, Intent intent) {
        if (str == null || intent == null) {
            return false;
        }
        String d = cz.d(intent);
        String e = cz.e(intent);
        if (d == null || e == null) {
            return false;
        }
        if (!str.equals(cz.p(d))) {
            ev.D("Developer payload not match.");
            return false;
        } else if (this.mx == null || db.b(this.mx, d, e)) {
            return true;
        } else {
            ev.D("Fail to verify signature.");
            return false;
        }
    }

    public String bh() {
        return ep.bO();
    }
}
