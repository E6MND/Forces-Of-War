package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.a;
import com.google.android.gms.internal.b;
import com.google.android.gms.internal.d;
import java.util.Map;

class m extends aj {
    private static final String ID = a.CONSTANT.toString();
    private static final String VALUE = b.VALUE.toString();

    public m() {
        super(ID, VALUE);
    }

    public static String lf() {
        return ID;
    }

    public static String lg() {
        return VALUE;
    }

    public boolean lc() {
        return true;
    }

    public d.a w(Map<String, d.a> map) {
        return map.get(VALUE);
    }
}
