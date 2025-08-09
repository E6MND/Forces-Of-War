package com.google.android.gms.internal;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.SystemClock;
import com.google.android.gms.internal.dc;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.xwalk.core.internal.extension.api.messaging.MessagingSmsConsts;

public final class cu extends dc.a {
    private String lp;
    private Context mContext;
    private String pd;
    private ArrayList<String> pe;

    public cu(String str, ArrayList<String> arrayList, Context context, String str2) {
        this.pd = str;
        this.pe = arrayList;
        this.lp = str2;
        this.mContext = context;
    }

    private void be() {
        try {
            this.mContext.getClassLoader().loadClass("com.google.ads.conversiontracking.IAPConversionReporter").getDeclaredMethod("reportWithProductId", new Class[]{Context.class, String.class, String.class, Boolean.TYPE}).invoke((Object) null, new Object[]{this.mContext, this.pd, "", true});
        } catch (ClassNotFoundException e) {
            ev.D("Google Conversion Tracking SDK 1.2.0 or above is required to report a conversion.");
        } catch (NoSuchMethodException e2) {
            ev.D("Google Conversion Tracking SDK 1.2.0 or above is required to report a conversion.");
        } catch (Exception e3) {
            ev.c("Fail to report a conversion.", e3);
        }
    }

    /* access modifiers changed from: protected */
    public String a(String str, HashMap<String, String> hashMap) {
        String str2;
        String packageName = this.mContext.getPackageName();
        try {
            str2 = this.mContext.getPackageManager().getPackageInfo(packageName, 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            ev.c("Error to retrieve app version", e);
            str2 = "";
        }
        long elapsedRealtime = SystemClock.elapsedRealtime() - ei.bF().bJ();
        for (String next : hashMap.keySet()) {
            str = str.replaceAll(String.format("(?<!@)((?:@@)*)@%s(?<!@)((?:@@)*)@", new Object[]{next}), String.format("$1%s$2", new Object[]{hashMap.get(next)}));
        }
        return str.replaceAll(String.format("(?<!@)((?:@@)*)@%s(?<!@)((?:@@)*)@", new Object[]{"sessionid"}), String.format("$1%s$2", new Object[]{ei.rN})).replaceAll(String.format("(?<!@)((?:@@)*)@%s(?<!@)((?:@@)*)@", new Object[]{"appid"}), String.format("$1%s$2", new Object[]{packageName})).replaceAll(String.format("(?<!@)((?:@@)*)@%s(?<!@)((?:@@)*)@", new Object[]{"osversion"}), String.format("$1%s$2", new Object[]{String.valueOf(Build.VERSION.SDK_INT)})).replaceAll(String.format("(?<!@)((?:@@)*)@%s(?<!@)((?:@@)*)@", new Object[]{"sdkversion"}), String.format("$1%s$2", new Object[]{this.lp})).replaceAll(String.format("(?<!@)((?:@@)*)@%s(?<!@)((?:@@)*)@", new Object[]{"appversion"}), String.format("$1%s$2", new Object[]{str2})).replaceAll(String.format("(?<!@)((?:@@)*)@%s(?<!@)((?:@@)*)@", new Object[]{"timestamp"}), String.format("$1%s$2", new Object[]{String.valueOf(elapsedRealtime)})).replaceAll(String.format("(?<!@)((?:@@)*)@%s(?<!@)((?:@@)*)@", new Object[]{"[^@]+"}), String.format("$1%s$2", new Object[]{""})).replaceAll("@@", "@");
    }

    public String getProductId() {
        return this.pd;
    }

    /* access modifiers changed from: protected */
    public int l(int i) {
        if (i == 0) {
            return 1;
        }
        if (i == 1) {
            return 2;
        }
        return i == 4 ? 3 : 0;
    }

    public void recordPlayBillingResolution(int billingResponseCode) {
        if (billingResponseCode == 0) {
            be();
        }
        HashMap hashMap = new HashMap();
        hashMap.put("google_play_status", String.valueOf(billingResponseCode));
        hashMap.put("sku", this.pd);
        hashMap.put(MessagingSmsConsts.STATUS, String.valueOf(l(billingResponseCode)));
        Iterator<String> it = this.pe.iterator();
        while (it.hasNext()) {
            new et(this.mContext, this.lp, a(it.next(), hashMap)).start();
        }
    }

    public void recordResolution(int resolution) {
        if (resolution == 1) {
            be();
        }
        HashMap hashMap = new HashMap();
        hashMap.put(MessagingSmsConsts.STATUS, String.valueOf(resolution));
        hashMap.put("sku", this.pd);
        Iterator<String> it = this.pe.iterator();
        while (it.hasNext()) {
            new et(this.mContext, this.lp, a(it.next(), hashMap)).start();
        }
    }
}
