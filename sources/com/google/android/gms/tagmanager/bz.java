package com.google.android.gms.tagmanager;

import android.os.Build;
import com.google.android.gms.internal.a;
import com.google.android.gms.internal.d;
import java.util.Map;

class bz extends aj {
    private static final String ID = a.OS_VERSION.toString();

    public bz() {
        super(ID, new String[0]);
    }

    public boolean lc() {
        return true;
    }

    public d.a w(Map<String, d.a> map) {
        return dh.r(Build.VERSION.RELEASE);
    }
}
