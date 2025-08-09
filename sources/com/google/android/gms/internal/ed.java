package com.google.android.gms.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import java.util.Locale;
import org.xwalk.core.internal.extension.api.messaging.MessagingSmsConsts;

public final class ed {
    public final int qY;
    public final boolean qZ;
    public final boolean ra;
    public final String rb;
    public final String rc;
    public final boolean rd;
    public final boolean re;
    public final boolean rf;
    public final String rg;
    public final String rh;
    public final int ri;
    public final int rj;
    public final int rk;
    public final int rl;
    public final int rm;
    public final int rn;
    public final float ro;
    public final int rp;
    public final int rq;
    public final double rr;
    public final boolean rs;
    public final boolean rt;
    public final int ru;

    public ed(Context context) {
        boolean z = true;
        AudioManager audioManager = (AudioManager) context.getSystemService("audio");
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        Locale locale = Locale.getDefault();
        PackageManager packageManager = context.getPackageManager();
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        Intent registerReceiver = context.registerReceiver((BroadcastReceiver) null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        this.qY = audioManager.getMode();
        this.qZ = a(packageManager, "geo:0,0?q=donuts") != null;
        this.ra = a(packageManager, "http://www.google.com") != null;
        this.rb = telephonyManager.getNetworkOperator();
        this.rc = locale.getCountry();
        this.rd = eu.bQ();
        this.re = audioManager.isMusicActive();
        this.rf = audioManager.isSpeakerphoneOn();
        this.rg = locale.getLanguage();
        this.rh = a(packageManager);
        this.ri = audioManager.getStreamVolume(3);
        this.rj = a(context, connectivityManager, packageManager);
        this.rk = telephonyManager.getNetworkType();
        this.rl = telephonyManager.getPhoneType();
        this.rm = audioManager.getRingerMode();
        this.rn = audioManager.getStreamVolume(2);
        this.ro = displayMetrics.density;
        this.rp = displayMetrics.widthPixels;
        this.rq = displayMetrics.heightPixels;
        if (registerReceiver != null) {
            int intExtra = registerReceiver.getIntExtra(MessagingSmsConsts.STATUS, -1);
            this.rr = (double) (((float) registerReceiver.getIntExtra("level", -1)) / ((float) registerReceiver.getIntExtra("scale", -1)));
            if (!(intExtra == 2 || intExtra == 5)) {
                z = false;
            }
            this.rs = z;
        } else {
            this.rr = -1.0d;
            this.rs = false;
        }
        if (Build.VERSION.SDK_INT >= 16) {
            this.rt = connectivityManager.isActiveNetworkMetered();
            if (connectivityManager.getActiveNetworkInfo() != null) {
                this.ru = connectivityManager.getActiveNetworkInfo().getDetailedState().ordinal();
            } else {
                this.ru = -1;
            }
        } else {
            this.rt = false;
            this.ru = -1;
        }
    }

    private static int a(Context context, ConnectivityManager connectivityManager, PackageManager packageManager) {
        if (!ep.a(packageManager, context.getPackageName(), "android.permission.ACCESS_NETWORK_STATE")) {
            return -2;
        }
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo != null) {
            return activeNetworkInfo.getType();
        }
        return -1;
    }

    private static ResolveInfo a(PackageManager packageManager, String str) {
        return packageManager.resolveActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)), 65536);
    }

    private static String a(PackageManager packageManager) {
        ActivityInfo activityInfo;
        ResolveInfo a = a(packageManager, "market://details?id=com.google.android.gms.ads");
        if (a == null || (activityInfo = a.activityInfo) == null) {
            return null;
        }
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(activityInfo.packageName, 0);
            if (packageInfo != null) {
                return packageInfo.versionCode + "." + activityInfo.packageName;
            }
            return null;
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
    }
}
