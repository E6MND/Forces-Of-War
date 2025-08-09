package com.google.android.gms.tagmanager;

import android.util.LruCache;
import com.google.android.gms.tagmanager.l;

class bb<K, V> implements k<K, V> {
    private LruCache<K, V> afZ;

    bb(int i, final l.a<K, V> aVar) {
        this.afZ = new LruCache<K, V>(i) {
            /* access modifiers changed from: protected */
            public int sizeOf(K key, V value) {
                return aVar.sizeOf(key, value);
            }
        };
    }

    public void e(K k, V v) {
        this.afZ.put(k, v);
    }

    public V get(K key) {
        return this.afZ.get(key);
    }
}
