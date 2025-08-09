package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.b;
import com.google.android.gms.internal.d;
import com.google.android.gms.tagmanager.cq;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class ba {
    public static cq.c bY(String str) throws JSONException {
        d.a k = k(new JSONObject(str));
        cq.d mm = cq.c.mm();
        for (int i = 0; i < k.fP.length; i++) {
            mm.a(cq.a.mi().b(b.INSTANCE_NAME.toString(), k.fP[i]).b(b.FUNCTION.toString(), dh.cp(m.lf())).b(m.lg(), k.fQ[i]).ml());
        }
        return mm.mp();
    }

    private static d.a k(Object obj) throws JSONException {
        return dh.r(l(obj));
    }

    static Object l(Object obj) throws JSONException {
        if (obj instanceof JSONArray) {
            throw new RuntimeException("JSONArrays are not supported");
        } else if (JSONObject.NULL.equals(obj)) {
            throw new RuntimeException("JSON nulls are not supported");
        } else if (!(obj instanceof JSONObject)) {
            return obj;
        } else {
            JSONObject jSONObject = (JSONObject) obj;
            HashMap hashMap = new HashMap();
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                hashMap.put(next, l(jSONObject.get(next)));
            }
            return hashMap;
        }
    }
}
