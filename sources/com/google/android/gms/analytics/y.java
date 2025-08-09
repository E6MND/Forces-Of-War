package com.google.android.gms.analytics;

import android.text.TextUtils;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

class y {
    static String a(x xVar, long j) {
        StringBuilder sb = new StringBuilder();
        sb.append(xVar.df());
        if (xVar.dh() > 0) {
            long dh = j - xVar.dh();
            if (dh >= 0) {
                sb.append("&qt").append("=").append(dh);
            }
        }
        sb.append("&z").append("=").append(xVar.dg());
        return sb.toString();
    }

    static String encode(String input) {
        try {
            return URLEncoder.encode(input, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError("URL encoding failed for: " + input);
        }
    }

    static Map<String, String> u(Map<String, String> map) {
        HashMap hashMap = new HashMap();
        for (Map.Entry next : map.entrySet()) {
            if (((String) next.getKey()).startsWith("&") && next.getValue() != null) {
                String substring = ((String) next.getKey()).substring(1);
                if (!TextUtils.isEmpty(substring)) {
                    hashMap.put(substring, next.getValue());
                }
            }
        }
        return hashMap;
    }
}
