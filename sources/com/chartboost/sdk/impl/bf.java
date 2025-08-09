package com.chartboost.sdk.impl;

import com.chartboost.sdk.impl.ba;
import java.util.HashMap;
import java.util.Map;

abstract class bf<K, V> extends ba<K, V, Map<K, V>> {
    public static <K, V> a<K, V> b() {
        return new a<>();
    }

    public static class a<K, V> {
        private ba.h.a a = ba.h.a.STABLE;
        private final Map<K, V> b = new HashMap();

        a() {
        }

        public bf<K, V> a() {
            return new b(this.b, this.a);
        }
    }

    public static <K, V> bf<K, V> c() {
        return b().a();
    }

    protected bf(Map<? extends K, ? extends V> map, ba.h.a aVar) {
        super(map, aVar);
    }

    static class b<K, V> extends bf<K, V> {
        b(Map<? extends K, ? extends V> map, ba.h.a aVar) {
            super(map, aVar);
        }

        public <N extends Map<? extends K, ? extends V>> Map<K, V> a(N n) {
            return new HashMap(n);
        }
    }
}
