package com.google.android.gms.analytics;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

class g implements m {
    private static g tD;
    private static Object tn = new Object();
    protected String tA;
    protected String tB;
    protected String tC;
    protected String tz;

    protected g() {
    }

    private g(Context context) {
        PackageManager packageManager = context.getPackageManager();
        this.tB = context.getPackageName();
        this.tC = packageManager.getInstallerPackageName(this.tB);
        String str = this.tB;
        String str2 = null;
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            if (packageInfo != null) {
                str = packageManager.getApplicationLabel(packageInfo.applicationInfo).toString();
                str2 = packageInfo.versionName;
            }
        } catch (PackageManager.NameNotFoundException e) {
            aa.A("Error retrieving package info: appName set to " + str);
        }
        this.tz = str;
        this.tA = str2;
    }

    public static g cp() {
        return tD;
    }

    public static void r(Context context) {
        synchronized (tn) {
            if (tD == null) {
                tD = new g(context);
            }
        }
    }

    public boolean J(String str) {
        return "&an".equals(str) || "&av".equals(str) || "&aid".equals(str) || "&aiid".equals(str);
    }

    public String getValue(String field) {
        if (field == null) {
            return null;
        }
        if (field.equals("&an")) {
            return this.tz;
        }
        if (field.equals("&av")) {
            return this.tA;
        }
        if (field.equals("&aid")) {
            return this.tB;
        }
        if (field.equals("&aiid")) {
            return this.tC;
        }
        return null;
    }
}
