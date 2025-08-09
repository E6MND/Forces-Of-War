package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.d;
import java.util.Map;

abstract class bx extends cc {
    public bx(String str) {
        super(str);
    }

    /* access modifiers changed from: protected */
    public boolean a(d.a aVar, d.a aVar2, Map<String, d.a> map) {
        dg k = dh.k(aVar);
        dg k2 = dh.k(aVar2);
        if (k == dh.mW() || k2 == dh.mW()) {
            return false;
        }
        return a(k, k2, map);
    }

    /* access modifiers changed from: protected */
    public abstract boolean a(dg dgVar, dg dgVar2, Map<String, d.a> map);
}
