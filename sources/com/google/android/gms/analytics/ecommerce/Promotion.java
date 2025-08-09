package com.google.android.gms.analytics.ecommerce;

import com.google.android.gms.internal.hn;
import java.util.HashMap;
import java.util.Map;

public class Promotion {
    public static final String ACTION_CLICK = "click";
    public static final String ACTION_VIEW = "view";
    Map<String, String> xC = new HashMap();

    public Map<String, String> X(String str) {
        HashMap hashMap = new HashMap();
        for (Map.Entry next : this.xC.entrySet()) {
            hashMap.put(str + ((String) next.getKey()), next.getValue());
        }
        return hashMap;
    }

    /* access modifiers changed from: package-private */
    public void put(String name, String value) {
        hn.b(name, (Object) "Name should be non-null");
        this.xC.put(name, value);
    }

    public Promotion setCreative(String value) {
        put("cr", value);
        return this;
    }

    public Promotion setId(String value) {
        put("id", value);
        return this;
    }

    public Promotion setName(String value) {
        put("nm", value);
        return this;
    }

    public Promotion setPosition(String value) {
        put("ps", value);
        return this;
    }
}
