package com.crashlytics.android.internal;

import java.io.IOException;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import org.xwalk.core.internal.extension.api.messaging.MessagingSmsConsts;

final class X {
    X() {
    }

    public final byte[] a(V v) throws IOException {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("appBundleId", v.a);
            jSONObject.put("executionId", v.b);
            jSONObject.put("installationId", v.c);
            jSONObject.put("androidId", v.d);
            jSONObject.put("osVersion", v.e);
            jSONObject.put("deviceModel", v.f);
            jSONObject.put("appVersionCode", v.g);
            jSONObject.put("appVersionName", v.h);
            jSONObject.put("timestamp", v.i);
            jSONObject.put(MessagingSmsConsts.TYPE, v.j.toString());
            jSONObject.put("details", a(v.k));
            return jSONObject.toString().getBytes("UTF-8");
        } catch (JSONException e) {
            throw new IOException(e.getMessage());
        }
    }

    private static JSONObject a(Map<String, String> map) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        for (Map.Entry next : map.entrySet()) {
            jSONObject.put((String) next.getKey(), next.getValue());
        }
        return jSONObject;
    }
}
