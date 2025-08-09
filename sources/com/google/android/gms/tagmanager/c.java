package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.a;
import com.google.android.gms.internal.d;
import java.util.Map;

class c extends aj {
    private static final String ID = a.ADVERTISING_TRACKING_ENABLED.toString();
    private final a aeg;

    public c(Context context) {
        this(a.J(context));
    }

    c(a aVar) {
        super(ID, new String[0]);
        this.aeg = aVar;
    }

    public boolean lc() {
        return false;
    }

    public d.a w(Map<String, d.a> map) {
        return dh.r(Boolean.valueOf(!this.aeg.isLimitAdTrackingEnabled()));
    }
}
