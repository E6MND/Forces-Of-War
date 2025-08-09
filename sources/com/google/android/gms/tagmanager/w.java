package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.a;
import com.google.android.gms.internal.b;
import com.google.android.gms.internal.d;
import java.util.List;
import java.util.Map;

class w extends df {
    private static final String ID = a.DATA_LAYER_WRITE.toString();
    private static final String VALUE = b.VALUE.toString();
    private static final String afq = b.CLEAR_PERSISTENT_DATA_LAYER_PREFIX.toString();
    private final DataLayer aer;

    public w(DataLayer dataLayer) {
        super(ID, VALUE);
        this.aer = dataLayer;
    }

    private void a(d.a aVar) {
        String j;
        if (aVar != null && aVar != dh.mS() && (j = dh.j(aVar)) != dh.mX()) {
            this.aer.bN(j);
        }
    }

    private void b(d.a aVar) {
        if (aVar != null && aVar != dh.mS()) {
            Object o = dh.o(aVar);
            if (o instanceof List) {
                for (Object next : (List) o) {
                    if (next instanceof Map) {
                        this.aer.push((Map) next);
                    }
                }
            }
        }
    }

    public void y(Map<String, d.a> map) {
        b(map.get(VALUE));
        a(map.get(afq));
    }
}
