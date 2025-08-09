package com.chartboost.sdk.impl;

import java.util.List;
import java.util.Map;

public class bd<T> {
    /* access modifiers changed from: private */
    public final Map<Class<?>, T> a = bf.c();
    private final Map<Class<?>, T> b = be.a(new a());

    public static <T> List<Class<?>> a(Class<T> cls) {
        return bc.a(cls);
    }

    private final class a implements bg<Class<?>, T> {
        private a() {
        }

        public T a(Class<?> cls) {
            for (Class<?> cls2 : bd.a(cls)) {
                T t = bd.this.a.get(cls2);
                if (t != null) {
                    return t;
                }
            }
            return null;
        }
    }

    public T a(Object obj) {
        return this.b.get(obj);
    }

    public T a(Class<?> cls, T t) {
        try {
            return this.a.put(cls, t);
        } finally {
            this.b.clear();
        }
    }

    public int a() {
        return this.a.size();
    }
}
