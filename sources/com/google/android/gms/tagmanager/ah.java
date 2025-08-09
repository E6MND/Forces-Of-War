package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.a;
import com.google.android.gms.internal.d;
import java.util.Map;

class ah extends aj {
    private static final String ID = a.EVENT.toString();
    private final cs aes;

    public ah(cs csVar) {
        super(ID, new String[0]);
        this.aes = csVar;
    }

    public boolean lc() {
        return false;
    }

    public d.a w(Map<String, d.a> map) {
        String mC = this.aes.mC();
        return mC == null ? dh.mY() : dh.r(mC);
    }
}
