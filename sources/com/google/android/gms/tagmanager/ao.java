package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.a;
import com.google.android.gms.internal.b;
import com.google.android.gms.internal.d;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

class ao extends aj {
    private static final String ID = a.HASH.toString();
    private static final String afB = b.ALGORITHM.toString();
    private static final String afv = b.ARG0.toString();
    private static final String afx = b.INPUT_FORMAT.toString();

    public ao() {
        super(ID, afv);
    }

    private byte[] c(String str, byte[] bArr) throws NoSuchAlgorithmException {
        MessageDigest instance = MessageDigest.getInstance(str);
        instance.update(bArr);
        return instance.digest();
    }

    public boolean lc() {
        return true;
    }

    public d.a w(Map<String, d.a> map) {
        byte[] bE;
        d.a aVar = map.get(afv);
        if (aVar == null || aVar == dh.mY()) {
            return dh.mY();
        }
        String j = dh.j(aVar);
        d.a aVar2 = map.get(afB);
        String j2 = aVar2 == null ? "MD5" : dh.j(aVar2);
        d.a aVar3 = map.get(afx);
        String j3 = aVar3 == null ? "text" : dh.j(aVar3);
        if ("text".equals(j3)) {
            bE = j.getBytes();
        } else if ("base16".equals(j3)) {
            bE = j.bE(j);
        } else {
            bh.A("Hash: unknown input format: " + j3);
            return dh.mY();
        }
        try {
            return dh.r(j.d(c(j2, bE)));
        } catch (NoSuchAlgorithmException e) {
            bh.A("Hash: unknown algorithm: " + j2);
            return dh.mY();
        }
    }
}
