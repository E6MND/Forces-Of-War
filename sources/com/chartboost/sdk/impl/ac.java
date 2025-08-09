package com.chartboost.sdk.impl;

public class ac {
    public static String a(Object obj) {
        StringBuilder sb = new StringBuilder();
        a(obj, sb);
        return sb.toString();
    }

    public static void a(Object obj, StringBuilder sb) {
        ad.a().a(obj, sb);
    }

    static void a(StringBuilder sb, String str) {
        sb.append("\"");
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt == '\\') {
                sb.append("\\\\");
            } else if (charAt == '\"') {
                sb.append("\\\"");
            } else if (charAt == 10) {
                sb.append("\\n");
            } else if (charAt == 13) {
                sb.append("\\r");
            } else if (charAt == 9) {
                sb.append("\\t");
            } else if (charAt == 8) {
                sb.append("\\b");
            } else if (charAt >= ' ') {
                sb.append(charAt);
            }
        }
        sb.append("\"");
    }
}
