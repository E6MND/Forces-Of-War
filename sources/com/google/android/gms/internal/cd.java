package com.google.android.gms.internal;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

public final class cd {
    public static boolean a(Context context, cf cfVar, cm cmVar) {
        if (cfVar == null) {
            ev.D("No intent data for launcher overlay.");
            return false;
        }
        Intent intent = new Intent();
        if (TextUtils.isEmpty(cfVar.nZ)) {
            ev.D("Open GMSG did not contain a URL.");
            return false;
        }
        if (!TextUtils.isEmpty(cfVar.mimeType)) {
            intent.setDataAndType(Uri.parse(cfVar.nZ), cfVar.mimeType);
        } else {
            intent.setData(Uri.parse(cfVar.nZ));
        }
        intent.setAction("android.intent.action.VIEW");
        if (!TextUtils.isEmpty(cfVar.packageName)) {
            intent.setPackage(cfVar.packageName);
        }
        if (!TextUtils.isEmpty(cfVar.oa)) {
            String[] split = cfVar.oa.split("/", 2);
            if (split.length < 2) {
                ev.D("Could not parse component name from open GMSG: " + cfVar.oa);
                return false;
            }
            intent.setClassName(split[0], split[1]);
        }
        try {
            ev.C("Launching an intent: " + intent);
            context.startActivity(intent);
            cmVar.T();
            return true;
        } catch (ActivityNotFoundException e) {
            ev.D(e.getMessage());
            return false;
        }
    }
}
