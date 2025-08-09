package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.d;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

class dk {
    private static by<d.a> a(by<d.a> byVar) {
        try {
            return new by<>(dh.r(cv(dh.j(byVar.getObject()))), byVar.lV());
        } catch (UnsupportedEncodingException e) {
            bh.b("Escape URI: unsupported encoding", e);
            return byVar;
        }
    }

    private static by<d.a> a(by<d.a> byVar, int i) {
        if (!q(byVar.getObject())) {
            bh.A("Escaping can only be applied to strings.");
            return byVar;
        }
        switch (i) {
            case 12:
                return a(byVar);
            default:
                bh.A("Unsupported Value Escaping: " + i);
                return byVar;
        }
    }

    static by<d.a> a(by<d.a> byVar, int... iArr) {
        for (int a : iArr) {
            byVar = a(byVar, a);
        }
        return byVar;
    }

    static String cv(String str) throws UnsupportedEncodingException {
        return URLEncoder.encode(str, "UTF-8").replaceAll("\\+", "%20");
    }

    private static boolean q(d.a aVar) {
        return dh.o(aVar) instanceof String;
    }
}
