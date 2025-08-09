package com.google.android.gms.analytics.ecommerce;

import com.google.android.gms.analytics.o;
import com.google.android.gms.internal.hn;
import java.util.HashMap;
import java.util.Map;

public class Product {
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

    public Product setBrand(String value) {
        put("br", value);
        return this;
    }

    public Product setCategory(String value) {
        put("ca", value);
        return this;
    }

    public Product setCouponCode(String value) {
        put("cc", value);
        return this;
    }

    public Product setCustomDimension(int index, String value) {
        put(o.y(index), value);
        return this;
    }

    public Product setCustomMetric(int index, int value) {
        put(o.z(index), Integer.toString(value));
        return this;
    }

    public Product setId(String value) {
        put("id", value);
        return this;
    }

    public Product setName(String value) {
        put("nm", value);
        return this;
    }

    public Product setPosition(int value) {
        put("ps", Integer.toString(value));
        return this;
    }

    public Product setPrice(double value) {
        put("pr", Double.toString(value));
        return this;
    }

    public Product setQuantity(int value) {
        put("qt", Integer.toString(value));
        return this;
    }

    public Product setVariant(String value) {
        put("va", value);
        return this;
    }
}
