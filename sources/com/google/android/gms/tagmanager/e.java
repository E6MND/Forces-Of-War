package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.a;
import com.google.android.gms.internal.b;
import com.google.android.gms.internal.d;
import java.util.Map;

class e extends aj {
    private static final String ID = a.ADWORDS_CLICK_REFERRER.toString();
    private static final String aeh = b.COMPONENT.toString();
    private static final String aei = b.CONVERSION_ID.toString();
    private final Context kM;

    public e(Context context) {
        super(ID, aei);
        this.kM = context;
    }

    public boolean lc() {
        return true;
    }

    public d.a w(Map<String, d.a> map) {
        d.a aVar = map.get(aei);
        if (aVar == null) {
            return dh.mY();
        }
        String j = dh.j(aVar);
        d.a aVar2 = map.get(aeh);
        String d = ay.d(this.kM, j, aVar2 != null ? dh.j(aVar2) : null);
        return d != null ? dh.r(d) : dh.mY();
    }
}
