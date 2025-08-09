package com.google.android.gms.tagmanager;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import java.util.HashMap;
import java.util.Map;

class ay {
    private static String afP;
    static Map<String, String> afQ = new HashMap();

    ay() {
    }

    static void bX(String str) {
        synchronized (ay.class) {
            afP = str;
        }
    }

    static String d(Context context, String str, String str2) {
        String str3 = afQ.get(str);
        if (str3 == null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences("gtm_click_referrers", 0);
            str3 = sharedPreferences != null ? sharedPreferences.getString(str, "") : "";
            afQ.put(str, str3);
        }
        return p(str3, str2);
    }

    static void d(Context context, String str) {
        cy.a(context, "gtm_install_referrer", "referrer", str);
        f(context, str);
    }

    static String e(Context context, String str) {
        if (afP == null) {
            synchronized (ay.class) {
                if (afP == null) {
                    SharedPreferences sharedPreferences = context.getSharedPreferences("gtm_install_referrer", 0);
                    if (sharedPreferences != null) {
                        afP = sharedPreferences.getString("referrer", "");
                    } else {
                        afP = "";
                    }
                }
            }
        }
        return p(afP, str);
    }

    static void f(Context context, String str) {
        String p = p(str, "conv");
        if (p != null && p.length() > 0) {
            afQ.put(p, str);
            cy.a(context, "gtm_click_referrers", p, str);
        }
    }

    static String p(String str, String str2) {
        if (str2 != null) {
            return Uri.parse("http://hostname/?" + str).getQueryParameter(str2);
        }
        if (str.length() > 0) {
            return str;
        }
        return null;
    }
}
