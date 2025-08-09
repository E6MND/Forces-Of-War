package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.b;
import com.google.android.gms.internal.d;
import java.util.HashMap;
import java.util.Map;

class s extends aj {
    private static final String ID = com.google.android.gms.internal.a.FUNCTION_CALL.toString();
    private static final String aeT = b.FUNCTION_CALL_NAME.toString();
    private static final String aej = b.ADDITIONAL_PARAMS.toString();
    private final a aeU;

    public interface a {
        Object b(String str, Map<String, Object> map);
    }

    public s(a aVar) {
        super(ID, aeT);
        this.aeU = aVar;
    }

    public boolean lc() {
        return false;
    }

    public d.a w(Map<String, d.a> map) {
        String j = dh.j(map.get(aeT));
        HashMap hashMap = new HashMap();
        d.a aVar = map.get(aej);
        if (aVar != null) {
            Object o = dh.o(aVar);
            if (!(o instanceof Map)) {
                bh.D("FunctionCallMacro: expected ADDITIONAL_PARAMS to be a map.");
                return dh.mY();
            }
            for (Map.Entry entry : ((Map) o).entrySet()) {
                hashMap.put(entry.getKey().toString(), entry.getValue());
            }
        }
        try {
            return dh.r(this.aeU.b(j, hashMap));
        } catch (Exception e) {
            bh.D("Custom macro/tag " + j + " threw exception " + e.getMessage());
            return dh.mY();
        }
    }
}
