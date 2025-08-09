package com.chartboost.sdk.impl;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class am extends LinkedHashMap<String, Object> implements aj {
    public am() {
    }

    public am(String str, Object obj) {
        put(str, obj);
    }

    public boolean b(String str) {
        return super.containsKey(str);
    }

    public Object a(String str) {
        return super.get(str);
    }

    /* renamed from: a */
    public Object put(String str, Object obj) {
        return super.put(str, obj);
    }

    public void putAll(Map m) {
        for (Map.Entry entry : m.entrySet()) {
            put(entry.getKey().toString(), entry.getValue());
        }
    }

    public String toString() {
        return ac.a(this);
    }

    public boolean equals(Object o) {
        if (!(o instanceof aj)) {
            return false;
        }
        aj ajVar = (aj) o;
        if (!keySet().equals(ajVar.keySet())) {
            return false;
        }
        for (String next : keySet()) {
            Object a = a(next);
            Object a2 = ajVar.a(next);
            if (a == null && a2 != null) {
                return false;
            }
            if (a2 == null) {
                if (a != null) {
                    return false;
                }
            } else if (!(a instanceof Number) || !(a2 instanceof Number)) {
                if ((a instanceof Pattern) && (a2 instanceof Pattern)) {
                    Pattern pattern = (Pattern) a;
                    Pattern pattern2 = (Pattern) a2;
                    if (!pattern.pattern().equals(pattern2.pattern()) || pattern.flags() != pattern2.flags()) {
                        return false;
                    }
                } else if (!a.equals(a2)) {
                    return false;
                }
            } else if (((Number) a).doubleValue() != ((Number) a2).doubleValue()) {
                return false;
            }
        }
        return true;
    }
}
