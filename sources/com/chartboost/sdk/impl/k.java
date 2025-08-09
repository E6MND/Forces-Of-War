package com.chartboost.sdk.impl;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import com.chartboost.sdk.Chartboost;
import com.chartboost.sdk.Libraries.b;
import com.chartboost.sdk.Libraries.d;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import org.xwalk.core.internal.extension.api.messaging.MessagingSmsConsts;

public class k {
    public String a;
    public String b;
    public String c;
    public Map<String, String> d;
    public JSONObject e;
    public Map<String, String> f;
    public List<String> g;
    public a h;
    public boolean i;
    public JSONObject j = null;

    public interface a {
        void a(JSONObject jSONObject);
    }

    public k(String str, String str2) {
        this.a = str;
        this.b = str2;
        this.c = "GET";
    }

    public void a(String str, String str2) {
        if (this.e == null) {
            this.e = new JSONObject();
            this.c = "POST";
        }
        try {
            this.e.put(str, str2);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public void b(String str, String str2) {
        if (this.d == null) {
            this.d = new HashMap();
        }
        this.d.put(str, str2);
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0064  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0069  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void b(android.content.Context r6) {
        /*
            r5 = this;
            r2 = 0
            boolean r1 = r6 instanceof android.app.Activity     // Catch:{ Exception -> 0x0060 }
            if (r1 == 0) goto L_0x0071
            r0 = r6
            android.app.Activity r0 = (android.app.Activity) r0     // Catch:{ Exception -> 0x0060 }
            r1 = r0
            android.graphics.Rect r4 = new android.graphics.Rect     // Catch:{ Exception -> 0x0060 }
            r4.<init>()     // Catch:{ Exception -> 0x0060 }
            android.view.Window r1 = r1.getWindow()     // Catch:{ Exception -> 0x0060 }
            android.view.View r1 = r1.getDecorView()     // Catch:{ Exception -> 0x0060 }
            r1.getWindowVisibleDisplayFrame(r4)     // Catch:{ Exception -> 0x0060 }
            int r1 = android.os.Build.VERSION.SDK_INT     // Catch:{ Exception -> 0x0060 }
            r3 = 9
            if (r1 >= r3) goto L_0x0022
            r1 = 0
            r4.top = r1     // Catch:{ Exception -> 0x0060 }
        L_0x0022:
            int r3 = r4.width()     // Catch:{ Exception -> 0x0060 }
            int r1 = r4.height()     // Catch:{ Exception -> 0x006e }
            r2 = r1
        L_0x002b:
            java.lang.String r1 = "window"
            java.lang.Object r1 = r6.getSystemService(r1)
            android.view.WindowManager r1 = (android.view.WindowManager) r1
            android.view.Display r1 = r1.getDefaultDisplay()
            if (r3 <= 0) goto L_0x0064
        L_0x0039:
            if (r2 <= 0) goto L_0x0069
        L_0x003b:
            java.lang.String r1 = "w"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.StringBuilder r3 = r4.append(r3)
            java.lang.String r3 = r3.toString()
            r5.a(r1, r3)
            java.lang.String r1 = "h"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.StringBuilder r2 = r3.append(r2)
            java.lang.String r2 = r2.toString()
            r5.a(r1, r2)
            return
        L_0x0060:
            r1 = move-exception
            r1 = r2
        L_0x0062:
            r3 = r1
            goto L_0x002b
        L_0x0064:
            int r3 = r1.getWidth()
            goto L_0x0039
        L_0x0069:
            int r2 = r1.getHeight()
            goto L_0x003b
        L_0x006e:
            r1 = move-exception
            r1 = r3
            goto L_0x0062
        L_0x0071:
            r3 = r2
            goto L_0x002b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.chartboost.sdk.impl.k.b(android.content.Context):void");
    }

    public void a(Context context) {
        a("app", Chartboost.sharedChartboost().getAppID());
        if (Build.PRODUCT.equals("sdk")) {
            a("model", "Android Simulator");
            a("identity", d.b());
        } else {
            a("model", Build.MODEL);
            a("identity", d.b());
        }
        a("device_type", String.valueOf(Build.MANUFACTURER) + " " + Build.MODEL);
        a("os", "Android " + Build.VERSION.RELEASE);
        a("country", Locale.getDefault().getCountry());
        a("language", Locale.getDefault().getLanguage());
        a("sdk", "3.1.5");
        a("timestamp", new StringBuilder(String.valueOf(new Date().getTime())).toString());
        b(context);
        a("scale", new StringBuilder().append(context.getResources().getDisplayMetrics().density).toString());
        try {
            a("bundle", context.getPackageManager().getPackageInfo(context.getPackageName(), 128).versionName);
        } catch (Exception e2) {
        }
    }

    public void c(String str, String str2) {
        String b2 = b.b(b.a((String.valueOf(this.c) + " " + a() + "\n" + str2 + "\n" + b()).getBytes()));
        b("X-Chartboost-App", str);
        b("X-Chartboost-Signature", b2);
    }

    public String a() {
        return "/" + this.a + "/" + this.b + d.a(this.f);
    }

    public String b() {
        return this.e.toString();
    }

    public Map<String, String> c() {
        return this.d;
    }

    public static k a(JSONObject jSONObject) {
        try {
            k kVar = new k(jSONObject.getString("controller"), jSONObject.getString("action"));
            kVar.g = d.a(jSONObject.optJSONArray("params"));
            kVar.f = d.a(jSONObject.optJSONObject("query"));
            kVar.e = jSONObject.optJSONObject(MessagingSmsConsts.BODY);
            kVar.i = jSONObject.getBoolean("ensureDelivery");
            kVar.d = d.a(jSONObject.optJSONObject("headers"));
            return kVar;
        } catch (Exception e2) {
            Log.w("Chartboost", "Unable to deserialize failed request");
            return null;
        }
    }

    public JSONObject d() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("controller", this.a);
            jSONObject.put("action", this.b);
            jSONObject.put("params", d.a(this.g));
            jSONObject.put("query", d.b(this.f));
            jSONObject.put(MessagingSmsConsts.BODY, this.e);
            jSONObject.put("ensureDelivery", this.i);
            jSONObject.put("headers", d.b(this.d));
            return jSONObject;
        } catch (Exception e2) {
            Log.w("Chartboost", "Unable to serialize failed request");
            return null;
        }
    }
}
