package com.google.android.gms.tagmanager;

import android.os.Build;

class l<K, V> {
    final a<K, V> aeo = new a<K, V>() {
        public int sizeOf(K k, V v) {
            return 1;
        }
    };

    public interface a<K, V> {
        int sizeOf(K k, V v);
    }

    public k<K, V> a(int i, a<K, V> aVar) {
        if (i > 0) {
            return le() < 12 ? new cz(i, aVar) : new bb(i, aVar);
        }
        throw new IllegalArgumentException("maxSize <= 0");
    }

    /* access modifiers changed from: package-private */
    public int le() {
        return Build.VERSION.SDK_INT;
    }
}
