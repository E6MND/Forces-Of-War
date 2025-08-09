package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.a;
import com.google.android.gms.internal.d;
import java.util.Map;

class b extends aj {
    private static final String ID = a.ADVERTISER_ID.toString();
    private final a aeg;

    public b(Context context) {
        this(a.J(context));
    }

    b(a aVar) {
        super(ID, new String[0]);
        this.aeg = aVar;
    }

    public boolean lc() {
        return false;
    }

    public d.a w(Map<String, d.a> map) {
        String kY = this.aeg.kY();
        return kY == null ? dh.mY() : dh.r(kY);
    }
}
