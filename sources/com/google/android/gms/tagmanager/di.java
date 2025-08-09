package com.google.android.gms.tagmanager;

import android.content.Context;
import com.facebook.AppEventsConstants;
import com.google.android.gms.analytics.Tracker;
import com.google.android.gms.internal.a;
import com.google.android.gms.internal.b;
import com.google.android.gms.internal.d;
import com.sponsorpay.sdk.android.UrlBuilder;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

class di extends df {
    private static final String ID = a.UNIVERSAL_ANALYTICS.toString();
    private static Map<String, String> aiA;
    private static final String ait = b.ACCOUNT.toString();
    private static final String aiu = b.ANALYTICS_PASS_THROUGH.toString();
    private static final String aiv = b.ANALYTICS_FIELDS.toString();
    private static final String aiw = b.TRACK_TRANSACTION.toString();
    private static final String aix = b.TRANSACTION_DATALAYER_MAP.toString();
    private static final String aiy = b.TRANSACTION_ITEM_DATALAYER_MAP.toString();
    private static Map<String, String> aiz;
    private final DataLayer aer;
    private final Set<String> aiB;
    private final de aiC;

    public di(Context context, DataLayer dataLayer) {
        this(context, dataLayer, new de(context));
    }

    di(Context context, DataLayer dataLayer, de deVar) {
        super(ID, new String[0]);
        this.aer = dataLayer;
        this.aiC = deVar;
        this.aiB = new HashSet();
        this.aiB.add("");
        this.aiB.add(AppEventsConstants.EVENT_PARAM_VALUE_NO);
        this.aiB.add("false");
    }

    private Map<String, String> G(Map<String, d.a> map) {
        d.a aVar = map.get(aix);
        if (aVar != null) {
            return c(aVar);
        }
        if (aiz == null) {
            HashMap hashMap = new HashMap();
            hashMap.put("transactionId", "&ti");
            hashMap.put("transactionAffiliation", "&ta");
            hashMap.put("transactionTax", "&tt");
            hashMap.put("transactionShipping", "&ts");
            hashMap.put("transactionTotal", "&tr");
            hashMap.put("transactionCurrency", "&cu");
            aiz = hashMap;
        }
        return aiz;
    }

    private Map<String, String> H(Map<String, d.a> map) {
        d.a aVar = map.get(aiy);
        if (aVar != null) {
            return c(aVar);
        }
        if (aiA == null) {
            HashMap hashMap = new HashMap();
            hashMap.put("name", "&in");
            hashMap.put("sku", "&ic");
            hashMap.put("category", "&iv");
            hashMap.put("price", "&ip");
            hashMap.put("quantity", "&iq");
            hashMap.put(UrlBuilder.URL_PARAM_CURRENCY_NAME_KEY, "&cu");
            aiA = hashMap;
        }
        return aiA;
    }

    private void a(Tracker tracker, Map<String, d.a> map) {
        String cu = cu("transactionId");
        if (cu == null) {
            bh.A("Cannot find transactionId in data layer.");
            return;
        }
        LinkedList<Map> linkedList = new LinkedList<>();
        try {
            Map<String, String> p = p(map.get(aiv));
            p.put("&t", "transaction");
            for (Map.Entry next : G(map).entrySet()) {
                b(p, (String) next.getValue(), cu((String) next.getKey()));
            }
            linkedList.add(p);
            List<Map<String, String>> mZ = mZ();
            if (mZ != null) {
                for (Map next2 : mZ) {
                    if (next2.get("name") == null) {
                        bh.A("Unable to send transaction item hit due to missing 'name' field.");
                        return;
                    }
                    Map<String, String> p2 = p(map.get(aiv));
                    p2.put("&t", "item");
                    p2.put("&ti", cu);
                    for (Map.Entry next3 : H(map).entrySet()) {
                        b(p2, (String) next3.getValue(), (String) next2.get(next3.getKey()));
                    }
                    linkedList.add(p2);
                }
            }
            for (Map send : linkedList) {
                tracker.send(send);
            }
        } catch (IllegalArgumentException e) {
            bh.b("Unable to send transaction", e);
        }
    }

    private void b(Map<String, String> map, String str, String str2) {
        if (str2 != null) {
            map.put(str, str2);
        }
    }

    private Map<String, String> c(d.a aVar) {
        Object o = dh.o(aVar);
        if (!(o instanceof Map)) {
            return null;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry entry : ((Map) o).entrySet()) {
            linkedHashMap.put(entry.getKey().toString(), entry.getValue().toString());
        }
        return linkedHashMap;
    }

    private String cu(String str) {
        Object obj = this.aer.get(str);
        if (obj == null) {
            return null;
        }
        return obj.toString();
    }

    private boolean e(Map<String, d.a> map, String str) {
        d.a aVar = map.get(str);
        if (aVar == null) {
            return false;
        }
        return dh.n(aVar).booleanValue();
    }

    private List<Map<String, String>> mZ() {
        Object obj = this.aer.get("transactionProducts");
        if (obj == null) {
            return null;
        }
        if (!(obj instanceof List)) {
            throw new IllegalArgumentException("transactionProducts should be of type List.");
        }
        for (Object obj2 : (List) obj) {
            if (!(obj2 instanceof Map)) {
                throw new IllegalArgumentException("Each element of transactionProducts should be of type Map.");
            }
        }
        return (List) obj;
    }

    private Map<String, String> p(d.a aVar) {
        if (aVar == null) {
            return new HashMap();
        }
        Map<String, String> c = c(aVar);
        if (c == null) {
            return new HashMap();
        }
        String str = c.get("&aip");
        if (str != null && this.aiB.contains(str.toLowerCase())) {
            c.remove("&aip");
        }
        return c;
    }

    public void y(Map<String, d.a> map) {
        Tracker cm = this.aiC.cm("_GTM_DEFAULT_TRACKER_");
        if (e(map, aiu)) {
            cm.send(p(map.get(aiv)));
        } else if (e(map, aiw)) {
            a(cm, map);
        } else {
            bh.D("Ignoring unknown tag.");
        }
    }
}
