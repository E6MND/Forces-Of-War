package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.a;
import com.google.android.gms.internal.b;
import com.google.android.gms.internal.d;
import java.util.Map;

class ax extends aj {
    private static final String ID = a.INSTALL_REFERRER.toString();
    private static final String aeh = b.COMPONENT.toString();
    private final Context kM;

    public ax(Context context) {
        super(ID, new String[0]);
        this.kM = context;
    }

    public boolean lc() {
        return true;
    }

    public d.a w(Map<String, d.a> map) {
        String e = ay.e(this.kM, map.get(aeh) != null ? dh.j(map.get(aeh)) : null);
        return e != null ? dh.r(e) : dh.mY();
    }
}
