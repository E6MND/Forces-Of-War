package com.google.android.gms.analytics;

import android.app.Activity;
import java.util.HashMap;
import java.util.Map;

class aj implements j {
    Map<String, String> xA = new HashMap();
    String xu;
    double xv = -1.0d;
    int xw = -1;
    int xx = -1;
    int xy = -1;
    int xz = -1;

    aj() {
    }

    public String T(String str) {
        String str2 = this.xA.get(str);
        return str2 != null ? str2 : str;
    }

    public String dA() {
        return this.xu;
    }

    public boolean dB() {
        return this.xv >= 0.0d;
    }

    public double dC() {
        return this.xv;
    }

    public boolean dD() {
        return this.xw >= 0;
    }

    public boolean dE() {
        return this.xx != -1;
    }

    public boolean dF() {
        return this.xx == 1;
    }

    public boolean dG() {
        return this.xy != -1;
    }

    public boolean dH() {
        return this.xy == 1;
    }

    public boolean dI() {
        return this.xz == 1;
    }

    public boolean dz() {
        return this.xu != null;
    }

    public int getSessionTimeout() {
        return this.xw;
    }

    public String j(Activity activity) {
        return T(activity.getClass().getCanonicalName());
    }
}
