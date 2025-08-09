package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.d;
import java.util.Map;

abstract class df extends aj {
    public df(String str, String... strArr) {
        super(str, strArr);
    }

    public boolean lc() {
        return false;
    }

    public d.a w(Map<String, d.a> map) {
        y(map);
        return dh.mY();
    }

    public abstract void y(Map<String, d.a> map);
}
