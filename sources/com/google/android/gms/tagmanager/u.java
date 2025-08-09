package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.a;
import com.google.android.gms.internal.b;
import com.google.android.gms.internal.d;
import java.util.Map;

class u extends aj {
    private static final String ID = a.CUSTOM_VAR.toString();
    private static final String NAME = b.NAME.toString();
    private static final String aff = b.DEFAULT_VALUE.toString();
    private final DataLayer aer;

    public u(DataLayer dataLayer) {
        super(ID, NAME);
        this.aer = dataLayer;
    }

    public boolean lc() {
        return false;
    }

    public d.a w(Map<String, d.a> map) {
        Object obj = this.aer.get(dh.j(map.get(NAME)));
        if (obj != null) {
            return dh.r(obj);
        }
        d.a aVar = map.get(aff);
        return aVar != null ? aVar : dh.mY();
    }
}
