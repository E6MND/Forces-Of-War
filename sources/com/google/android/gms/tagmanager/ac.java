package com.google.android.gms.tagmanager;

import android.util.Base64;
import com.google.android.gms.internal.a;
import com.google.android.gms.internal.b;
import com.google.android.gms.internal.d;
import java.util.Map;

class ac extends aj {
    private static final String ID = a.ENCODE.toString();
    private static final String afv = b.ARG0.toString();
    private static final String afw = b.NO_PADDING.toString();
    private static final String afx = b.INPUT_FORMAT.toString();
    private static final String afy = b.OUTPUT_FORMAT.toString();

    public ac() {
        super(ID, afv);
    }

    public boolean lc() {
        return true;
    }

    public d.a w(Map<String, d.a> map) {
        byte[] decode;
        String encodeToString;
        d.a aVar = map.get(afv);
        if (aVar == null || aVar == dh.mY()) {
            return dh.mY();
        }
        String j = dh.j(aVar);
        d.a aVar2 = map.get(afx);
        String j2 = aVar2 == null ? "text" : dh.j(aVar2);
        d.a aVar3 = map.get(afy);
        String j3 = aVar3 == null ? "base16" : dh.j(aVar3);
        d.a aVar4 = map.get(afw);
        int i = (aVar4 == null || !dh.n(aVar4).booleanValue()) ? 2 : 3;
        try {
            if ("text".equals(j2)) {
                decode = j.getBytes();
            } else if ("base16".equals(j2)) {
                decode = j.bE(j);
            } else if ("base64".equals(j2)) {
                decode = Base64.decode(j, i);
            } else if ("base64url".equals(j2)) {
                decode = Base64.decode(j, i | 8);
            } else {
                bh.A("Encode: unknown input format: " + j2);
                return dh.mY();
            }
            if ("base16".equals(j3)) {
                encodeToString = j.d(decode);
            } else if ("base64".equals(j3)) {
                encodeToString = Base64.encodeToString(decode, i);
            } else if ("base64url".equals(j3)) {
                encodeToString = Base64.encodeToString(decode, i | 8);
            } else {
                bh.A("Encode: unknown output format: " + j3);
                return dh.mY();
            }
            return dh.r(encodeToString);
        } catch (IllegalArgumentException e) {
            bh.A("Encode: invalid input:");
            return dh.mY();
        }
    }
}
