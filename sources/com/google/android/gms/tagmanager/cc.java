package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.b;
import com.google.android.gms.internal.d;
import java.util.Map;

abstract class cc extends aj {
    private static final String afv = b.ARG0.toString();
    private static final String ags = b.ARG1.toString();

    public cc(String str) {
        super(str, afv, ags);
    }

    /* access modifiers changed from: protected */
    public abstract boolean a(d.a aVar, d.a aVar2, Map<String, d.a> map);

    public boolean lc() {
        return true;
    }

    public d.a w(Map<String, d.a> map) {
        for (d.a aVar : map.values()) {
            if (aVar == dh.mY()) {
                return dh.r(false);
            }
        }
        d.a aVar2 = map.get(afv);
        d.a aVar3 = map.get(ags);
        return dh.r(Boolean.valueOf((aVar2 == null || aVar3 == null) ? false : a(aVar2, aVar3, map)));
    }
}
