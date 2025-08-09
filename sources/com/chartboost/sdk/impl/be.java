package com.chartboost.sdk.impl;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;

final class be<K, V> implements bg<K, V>, Map<K, V> {
    private final ConcurrentMap<K, V> a;
    private final bg<K, V> b;

    public static <K, V> Map<K, V> a(bg<K, V> bgVar) {
        return new be(bf.c(), bgVar);
    }

    be(ConcurrentMap<K, V> concurrentMap, bg<K, V> bgVar) {
        this.a = (ConcurrentMap) bb.a("map", concurrentMap);
        this.b = (bg) bb.a("function", bgVar);
    }

    public V get(Object key) {
        while (true) {
            V v = this.a.get(key);
            if (v != null) {
                return v;
            }
            V a2 = this.b.a(key);
            if (a2 == null) {
                return null;
            }
            this.a.putIfAbsent(key, a2);
        }
    }

    public V a(K k) {
        return get(k);
    }

    public int size() {
        return this.a.size();
    }

    public boolean isEmpty() {
        return this.a.isEmpty();
    }

    public boolean containsKey(Object key) {
        return this.a.containsKey(key);
    }

    public boolean containsValue(Object value) {
        return this.a.containsValue(value);
    }

    public V put(K key, V value) {
        return this.a.put(key, value);
    }

    public V remove(Object key) {
        return this.a.remove(key);
    }

    public void putAll(Map<? extends K, ? extends V> m) {
        this.a.putAll(m);
    }

    public void clear() {
        this.a.clear();
    }

    public Set<K> keySet() {
        return this.a.keySet();
    }

    public Collection<V> values() {
        return this.a.values();
    }

    public Set<Map.Entry<K, V>> entrySet() {
        return this.a.entrySet();
    }

    public boolean equals(Object o) {
        return this.a.equals(o);
    }

    public int hashCode() {
        return this.a.hashCode();
    }
}
