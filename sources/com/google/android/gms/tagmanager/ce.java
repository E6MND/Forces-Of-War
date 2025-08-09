package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.a;
import com.google.android.gms.internal.b;
import com.google.android.gms.internal.d;
import java.util.Map;

class ce extends aj {
    private static final String ID = a.RANDOM.toString();
    private static final String agC = b.MIN.toString();
    private static final String agD = b.MAX.toString();

    public ce() {
        super(ID, new String[0]);
    }

    public boolean lc() {
        return false;
    }

    public d.a w(Map<String, d.a> map) {
        double d;
        double d2;
        d.a aVar = map.get(agC);
        d.a aVar2 = map.get(agD);
        if (!(aVar == null || aVar == dh.mY() || aVar2 == null || aVar2 == dh.mY())) {
            dg k = dh.k(aVar);
            dg k2 = dh.k(aVar2);
            if (!(k == dh.mW() || k2 == dh.mW())) {
                double doubleValue = k.doubleValue();
                d = k2.doubleValue();
                if (doubleValue <= d) {
                    d2 = doubleValue;
                    return dh.r(Long.valueOf(Math.round(((d - d2) * Math.random()) + d2)));
                }
            }
        }
        d = 2.147483647E9d;
        d2 = 0.0d;
        return dh.r(Long.valueOf(Math.round(((d - d2) * Math.random()) + d2)));
    }
}
