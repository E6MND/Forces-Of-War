package com.chartboost.sdk.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

abstract class ba<K, V, M extends Map<K, V>> implements Serializable, ConcurrentMap<K, V> {
    /* access modifiers changed from: private */
    public volatile M a;
    /* access modifiers changed from: private */
    public final transient Lock b = new ReentrantLock();
    private final h<K, V> c;

    /* access modifiers changed from: package-private */
    public abstract <N extends Map<? extends K, ? extends V>> M a(N n);

    protected <N extends Map<? extends K, ? extends V>> ba(N n, h.a aVar) {
        this.a = (Map) bb.a("delegate", a((Map) bb.a("map", n)));
        this.c = ((h.a) bb.a("viewType", aVar)).a(this);
    }

    public final void clear() {
        this.b.lock();
        try {
            b(a(Collections.emptyMap()));
        } finally {
            this.b.unlock();
        }
    }

    public final V remove(Object key) {
        Map a2;
        this.b.lock();
        try {
            if (!this.a.containsKey(key)) {
                this.b.unlock();
                return null;
            }
            a2 = a();
            V remove = a2.remove(key);
            b(a2);
            this.b.unlock();
            return remove;
        } catch (Throwable th) {
            this.b.unlock();
            throw th;
        }
    }

    public boolean remove(Object key, Object value) {
        this.b.lock();
        try {
            if (!this.a.containsKey(key) || !a(value, this.a.get(key))) {
                this.b.unlock();
                return false;
            }
            Map a2 = a();
            a2.remove(key);
            b(a2);
            return true;
        } finally {
            this.b.unlock();
        }
    }

    public boolean replace(K key, V oldValue, V newValue) {
        this.b.lock();
        try {
            if (!this.a.containsKey(key) || !a(oldValue, this.a.get(key))) {
                return false;
            }
            Map a2 = a();
            a2.put(key, newValue);
            b(a2);
            this.b.unlock();
            return true;
        } finally {
            this.b.unlock();
        }
    }

    public V replace(K key, V value) {
        Map a2;
        this.b.lock();
        try {
            if (!this.a.containsKey(key)) {
                this.b.unlock();
                return null;
            }
            a2 = a();
            V put = a2.put(key, value);
            b(a2);
            this.b.unlock();
            return put;
        } catch (Throwable th) {
            this.b.unlock();
            throw th;
        }
    }

    public final V put(K key, V value) {
        Map a2;
        this.b.lock();
        try {
            a2 = a();
            V put = a2.put(key, value);
            b(a2);
            this.b.unlock();
            return put;
        } catch (Throwable th) {
            this.b.unlock();
            throw th;
        }
    }

    public V putIfAbsent(K key, V value) {
        V v;
        Map a2;
        this.b.lock();
        try {
            if (!this.a.containsKey(key)) {
                a2 = a();
                v = a2.put(key, value);
                b(a2);
                this.b.unlock();
            } else {
                v = this.a.get(key);
                this.b.unlock();
            }
            return v;
        } catch (Throwable th) {
            this.b.unlock();
            throw th;
        }
    }

    public final void putAll(Map<? extends K, ? extends V> t) {
        this.b.lock();
        try {
            Map a2 = a();
            a2.putAll(t);
            b(a2);
        } finally {
            this.b.unlock();
        }
    }

    /* access modifiers changed from: protected */
    public M a() {
        this.b.lock();
        try {
            return a(this.a);
        } finally {
            this.b.unlock();
        }
    }

    /* access modifiers changed from: protected */
    public void b(M m) {
        this.a = m;
    }

    public final Set<Map.Entry<K, V>> entrySet() {
        return this.c.b();
    }

    public final Set<K> keySet() {
        return this.c.a();
    }

    public final Collection<V> values() {
        return this.c.c();
    }

    public final boolean containsKey(Object key) {
        return this.a.containsKey(key);
    }

    public final boolean containsValue(Object value) {
        return this.a.containsValue(value);
    }

    public final V get(Object key) {
        return this.a.get(key);
    }

    public final boolean isEmpty() {
        return this.a.isEmpty();
    }

    public final int size() {
        return this.a.size();
    }

    public final boolean equals(Object o) {
        return this.a.equals(o);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public String toString() {
        return this.a.toString();
    }

    private class d extends a<K> implements Set<K> {
        private d() {
        }

        /* access modifiers changed from: package-private */
        public Collection<K> a() {
            return ba.this.a.keySet();
        }

        public void clear() {
            ba.this.b.lock();
            try {
                Map a2 = ba.this.a();
                a2.keySet().clear();
                ba.this.b(a2);
            } finally {
                ba.this.b.unlock();
            }
        }

        public boolean remove(Object o) {
            return ba.this.remove(o) != null;
        }

        public boolean removeAll(Collection<?> c) {
            Map a2;
            ba.this.b.lock();
            try {
                a2 = ba.this.a();
                boolean removeAll = a2.keySet().removeAll(c);
                ba.this.b(a2);
                ba.this.b.unlock();
                return removeAll;
            } catch (Throwable th) {
                ba.this.b.unlock();
                throw th;
            }
        }

        public boolean retainAll(Collection<?> c) {
            Map a2;
            ba.this.b.lock();
            try {
                a2 = ba.this.a();
                boolean retainAll = a2.keySet().retainAll(c);
                ba.this.b(a2);
                ba.this.b.unlock();
                return retainAll;
            } catch (Throwable th) {
                ba.this.b.unlock();
                throw th;
            }
        }
    }

    private final class g extends a<V> {
        private g() {
        }

        /* access modifiers changed from: package-private */
        public Collection<V> a() {
            return ba.this.a.values();
        }

        public void clear() {
            ba.this.b.lock();
            try {
                Map a2 = ba.this.a();
                a2.values().clear();
                ba.this.b(a2);
            } finally {
                ba.this.b.unlock();
            }
        }

        public boolean remove(Object o) {
            Map a2;
            ba.this.b.lock();
            try {
                if (!contains(o)) {
                    ba.this.b.unlock();
                    return false;
                }
                a2 = ba.this.a();
                boolean remove = a2.values().remove(o);
                ba.this.b(a2);
                ba.this.b.unlock();
                return remove;
            } catch (Throwable th) {
                ba.this.b.unlock();
                throw th;
            }
        }

        public boolean removeAll(Collection<?> c) {
            Map a2;
            ba.this.b.lock();
            try {
                a2 = ba.this.a();
                boolean removeAll = a2.values().removeAll(c);
                ba.this.b(a2);
                ba.this.b.unlock();
                return removeAll;
            } catch (Throwable th) {
                ba.this.b.unlock();
                throw th;
            }
        }

        public boolean retainAll(Collection<?> c) {
            Map a2;
            ba.this.b.lock();
            try {
                a2 = ba.this.a();
                boolean retainAll = a2.values().retainAll(c);
                ba.this.b(a2);
                ba.this.b.unlock();
                return retainAll;
            } catch (Throwable th) {
                ba.this.b.unlock();
                throw th;
            }
        }
    }

    private class b extends a<Map.Entry<K, V>> implements Set<Map.Entry<K, V>> {
        private b() {
        }

        /* access modifiers changed from: package-private */
        public Collection<Map.Entry<K, V>> a() {
            return ba.this.a.entrySet();
        }

        public void clear() {
            ba.this.b.lock();
            try {
                Map a2 = ba.this.a();
                a2.entrySet().clear();
                ba.this.b(a2);
            } finally {
                ba.this.b.unlock();
            }
        }

        public boolean remove(Object o) {
            Map a2;
            ba.this.b.lock();
            try {
                if (!contains(o)) {
                    ba.this.b.unlock();
                    return false;
                }
                a2 = ba.this.a();
                boolean remove = a2.entrySet().remove(o);
                ba.this.b(a2);
                ba.this.b.unlock();
                return remove;
            } catch (Throwable th) {
                ba.this.b.unlock();
                throw th;
            }
        }

        public boolean removeAll(Collection<?> c) {
            Map a2;
            ba.this.b.lock();
            try {
                a2 = ba.this.a();
                boolean removeAll = a2.entrySet().removeAll(c);
                ba.this.b(a2);
                ba.this.b.unlock();
                return removeAll;
            } catch (Throwable th) {
                ba.this.b.unlock();
                throw th;
            }
        }

        public boolean retainAll(Collection<?> c) {
            Map a2;
            ba.this.b.lock();
            try {
                a2 = ba.this.a();
                boolean retainAll = a2.entrySet().retainAll(c);
                ba.this.b(a2);
                ba.this.b.unlock();
                return retainAll;
            } catch (Throwable th) {
                ba.this.b.unlock();
                throw th;
            }
        }
    }

    private static class f<T> implements Iterator<T> {
        private final Iterator<T> a;

        public f(Iterator<T> it) {
            this.a = it;
        }

        public boolean hasNext() {
            return this.a.hasNext();
        }

        public T next() {
            return this.a.next();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    protected static abstract class a<E> implements Collection<E> {
        /* access modifiers changed from: package-private */
        public abstract Collection<E> a();

        protected a() {
        }

        public final boolean contains(Object o) {
            return a().contains(o);
        }

        public final boolean containsAll(Collection<?> c) {
            return a().containsAll(c);
        }

        public final Iterator<E> iterator() {
            return new f(a().iterator());
        }

        public final boolean isEmpty() {
            return a().isEmpty();
        }

        public final int size() {
            return a().size();
        }

        public final Object[] toArray() {
            return a().toArray();
        }

        public final <T> T[] toArray(T[] a) {
            return a().toArray(a);
        }

        public int hashCode() {
            return a().hashCode();
        }

        public boolean equals(Object obj) {
            return a().equals(obj);
        }

        public String toString() {
            return a().toString();
        }

        public final boolean add(E e) {
            throw new UnsupportedOperationException();
        }

        public final boolean addAll(Collection<? extends E> collection) {
            throw new UnsupportedOperationException();
        }
    }

    private boolean a(Object obj, Object obj2) {
        if (obj == null) {
            return obj2 == null;
        }
        return obj.equals(obj2);
    }

    public static abstract class h<K, V> {

        public enum a {
            STABLE {
                /* access modifiers changed from: package-private */
                public <K, V, M extends Map<K, V>> h<K, V> a(ba<K, V, M> baVar) {
                    baVar.getClass();
                    return new c();
                }
            },
            LIVE {
                /* access modifiers changed from: package-private */
                public <K, V, M extends Map<K, V>> h<K, V> a(ba<K, V, M> baVar) {
                    baVar.getClass();
                    return new e();
                }
            };

            /* access modifiers changed from: package-private */
            public abstract <K, V, M extends Map<K, V>> h<K, V> a(ba<K, V, M> baVar);
        }

        /* access modifiers changed from: package-private */
        public abstract Set<K> a();

        /* access modifiers changed from: package-private */
        public abstract Set<Map.Entry<K, V>> b();

        /* access modifiers changed from: package-private */
        public abstract Collection<V> c();

        h() {
        }
    }

    final class c extends h<K, V> implements Serializable {
        c() {
        }

        public Set<K> a() {
            return Collections.unmodifiableSet(ba.this.a.keySet());
        }

        public Set<Map.Entry<K, V>> b() {
            return Collections.unmodifiableSet(ba.this.a.entrySet());
        }

        public Collection<V> c() {
            return Collections.unmodifiableCollection(ba.this.a.values());
        }
    }

    final class e extends h<K, V> implements Serializable {
        private final transient ba<K, V, M>.d b = new d();
        private final transient ba<K, V, M>.b c = new b();
        private final transient ba<K, V, M>.g d = new g();

        e() {
        }

        public Set<K> a() {
            return this.b;
        }

        public Set<Map.Entry<K, V>> b() {
            return this.c;
        }

        public Collection<V> c() {
            return this.d;
        }
    }
}
