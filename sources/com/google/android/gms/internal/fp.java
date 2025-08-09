package com.google.android.gms.internal;

import java.util.HashMap;
import java.util.Map;

public class fp {
    private static final String[] xW = {"text1", "text2", "icon", "intent_action", "intent_data", "intent_data_id", "intent_extra_data", "suggest_large_icon", "intent_activity"};
    private static final Map<String, Integer> xX = new HashMap(xW.length);

    static {
        for (int i = 0; i < xW.length; i++) {
            xX.put(xW[i], Integer.valueOf(i));
        }
    }

    public static String H(int i) {
        if (i < 0 || i >= xW.length) {
            return null;
        }
        return xW[i];
    }

    public static int Y(String str) {
        Integer num = xX.get(str);
        if (num != null) {
            return num.intValue();
        }
        throw new IllegalArgumentException("[" + str + "] is not a valid global search section name");
    }

    public static int dK() {
        return xW.length;
    }
}
