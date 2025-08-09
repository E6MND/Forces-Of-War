package com.google.android.gms.tagmanager;

import com.google.android.gms.tagmanager.l;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

class cz<K, V> implements k<K, V> {
    private final Map<K, V> ahU = new HashMap();
    private final int ahV;
    private final l.a<K, V> ahW;
    private int ahX;

    cz(int i, l.a<K, V> aVar) {
        this.ahV = i;
        this.ahW = aVar;
    }

    public synchronized void e(K k, V v) {
        if (k == null || v == null) {
            throw new NullPointerException("key == null || value == null");
        }
        this.ahX += this.ahW.sizeOf(k, v);
        if (this.ahX > this.ahV) {
            Iterator<Map.Entry<K, V>> it = this.ahU.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry next = it.next();
                this.ahX -= this.ahW.sizeOf(next.getKey(), next.getValue());
                it.remove();
                if (this.ahX <= this.ahV) {
                    break;
                }
            }
        }
        this.ahU.put(k, v);
    }

    public synchronized V get(K key) {
        return this.ahU.get(key);
    }
}
