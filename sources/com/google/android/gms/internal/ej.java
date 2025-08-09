package com.google.android.gms.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import com.google.android.gms.ads.AdActivity;

public class ej {
    private final Object lq = new Object();
    private final String rO;
    private int rU = 0;
    private long rV = -1;
    private long rW = -1;
    private int rX = 0;
    private int rY = -1;

    public ej(String str) {
        this.rO = str;
    }

    public static boolean i(Context context) {
        int identifier = context.getResources().getIdentifier("Theme.Translucent", "style", "android");
        if (identifier == 0) {
            ev.B("Please set theme of AdActivity to @android:style/Theme.Translucent to enable transparent background interstitial ad.");
            return false;
        }
        try {
            if (identifier == context.getPackageManager().getActivityInfo(new ComponentName(context.getPackageName(), AdActivity.CLASS_NAME), 0).theme) {
                return true;
            }
            ev.B("Please set theme of AdActivity to @android:style/Theme.Translucent to enable transparent background interstitial ad.");
            return false;
        } catch (PackageManager.NameNotFoundException e) {
            ev.D("Fail to fetch AdActivity theme");
            ev.B("Please set theme of AdActivity to @android:style/Theme.Translucent to enable transparent background interstitial ad.");
            return false;
        }
    }

    public Bundle b(Context context, String str) {
        Bundle bundle;
        synchronized (this.lq) {
            bundle = new Bundle();
            bundle.putString("session_id", this.rO);
            bundle.putLong("basets", this.rW);
            bundle.putLong("currts", this.rV);
            bundle.putString("seq_num", str);
            bundle.putInt("preqs", this.rY);
            bundle.putInt("pclick", this.rU);
            bundle.putInt("pimp", this.rX);
            bundle.putBoolean("support_transparent_background", i(context));
        }
        return bundle;
    }

    public void b(aj ajVar, long j) {
        synchronized (this.lq) {
            if (this.rW == -1) {
                this.rW = j;
                this.rV = this.rW;
            } else {
                this.rV = j;
            }
            if (ajVar.extras == null || ajVar.extras.getInt("gw", 2) != 1) {
                this.rY++;
            }
        }
    }

    public long bJ() {
        return this.rW;
    }

    public void bw() {
        synchronized (this.lq) {
            this.rX++;
        }
    }

    public void bx() {
        synchronized (this.lq) {
            this.rU++;
        }
    }
}
