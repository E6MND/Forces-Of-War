package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.d;
import java.util.Map;

abstract class dc extends cc {
    public dc(String str) {
        super(str);
    }

    /* access modifiers changed from: protected */
    public boolean a(d.a aVar, d.a aVar2, Map<String, d.a> map) {
        String j = dh.j(aVar);
        String j2 = dh.j(aVar2);
        if (j == dh.mX() || j2 == dh.mX()) {
            return false;
        }
        return a(j, j2, map);
    }

    /* access modifiers changed from: protected */
    public abstract boolean a(String str, String str2, Map<String, d.a> map);
}
