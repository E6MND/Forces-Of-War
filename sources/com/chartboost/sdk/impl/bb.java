package com.chartboost.sdk.impl;

public class bb {
    public static <T> T a(String str, T t) throws IllegalArgumentException {
        if (t != null) {
            return t;
        }
        throw new a(str);
    }

    static class a extends IllegalArgumentException {
        a(String str) {
            super(str + " should not be null!");
        }
    }
}
