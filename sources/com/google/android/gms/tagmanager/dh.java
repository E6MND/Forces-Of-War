package com.google.android.gms.tagmanager;

import com.facebook.internal.ServerProtocol;
import com.google.android.gms.internal.d;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

class dh {
    private static final Object aik = null;
    private static Long ail = new Long(0);
    private static Double aim = new Double(0.0d);
    private static dg ain = dg.z(0);
    private static String aio = new String("");
    private static Boolean aip = new Boolean(false);
    private static List<Object> aiq = new ArrayList(0);
    private static Map<Object, Object> air = new HashMap();
    private static d.a ais = r(aio);

    public static d.a cp(String str) {
        d.a aVar = new d.a();
        aVar.type = 5;
        aVar.fS = str;
        return aVar;
    }

    private static dg cq(String str) {
        try {
            return dg.co(str);
        } catch (NumberFormatException e) {
            bh.A("Failed to convert '" + str + "' to a number.");
            return ain;
        }
    }

    private static Long cr(String str) {
        dg cq = cq(str);
        return cq == ain ? ail : Long.valueOf(cq.longValue());
    }

    private static Double cs(String str) {
        dg cq = cq(str);
        return cq == ain ? aim : Double.valueOf(cq.doubleValue());
    }

    private static Boolean ct(String str) {
        return ServerProtocol.DIALOG_RETURN_SCOPES_TRUE.equalsIgnoreCase(str) ? Boolean.TRUE : "false".equalsIgnoreCase(str) ? Boolean.FALSE : aip;
    }

    private static double getDouble(Object o) {
        if (o instanceof Number) {
            return ((Number) o).doubleValue();
        }
        bh.A("getDouble received non-Number");
        return 0.0d;
    }

    public static String j(d.a aVar) {
        return m(o(aVar));
    }

    public static dg k(d.a aVar) {
        return n(o(aVar));
    }

    public static Long l(d.a aVar) {
        return o(o(aVar));
    }

    public static Double m(d.a aVar) {
        return p(o(aVar));
    }

    public static String m(Object obj) {
        return obj == null ? aio : obj.toString();
    }

    public static Object mS() {
        return aik;
    }

    public static Long mT() {
        return ail;
    }

    public static Double mU() {
        return aim;
    }

    public static Boolean mV() {
        return aip;
    }

    public static dg mW() {
        return ain;
    }

    public static String mX() {
        return aio;
    }

    public static d.a mY() {
        return ais;
    }

    public static dg n(Object obj) {
        return obj instanceof dg ? (dg) obj : t(obj) ? dg.z(u(obj)) : s(obj) ? dg.a(Double.valueOf(getDouble(obj))) : cq(m(obj));
    }

    public static Boolean n(d.a aVar) {
        return q(o(aVar));
    }

    public static Long o(Object obj) {
        return t(obj) ? Long.valueOf(u(obj)) : cr(m(obj));
    }

    public static Object o(d.a aVar) {
        int i = 0;
        if (aVar == null) {
            return aik;
        }
        switch (aVar.type) {
            case 1:
                return aVar.fN;
            case 2:
                ArrayList arrayList = new ArrayList(aVar.fO.length);
                d.a[] aVarArr = aVar.fO;
                int length = aVarArr.length;
                while (i < length) {
                    Object o = o(aVarArr[i]);
                    if (o == aik) {
                        return aik;
                    }
                    arrayList.add(o);
                    i++;
                }
                return arrayList;
            case 3:
                if (aVar.fP.length != aVar.fQ.length) {
                    bh.A("Converting an invalid value to object: " + aVar.toString());
                    return aik;
                }
                HashMap hashMap = new HashMap(aVar.fQ.length);
                while (i < aVar.fP.length) {
                    Object o2 = o(aVar.fP[i]);
                    Object o3 = o(aVar.fQ[i]);
                    if (o2 == aik || o3 == aik) {
                        return aik;
                    }
                    hashMap.put(o2, o3);
                    i++;
                }
                return hashMap;
            case 4:
                bh.A("Trying to convert a macro reference to object");
                return aik;
            case 5:
                bh.A("Trying to convert a function id to object");
                return aik;
            case 6:
                return Long.valueOf(aVar.fT);
            case 7:
                StringBuffer stringBuffer = new StringBuffer();
                d.a[] aVarArr2 = aVar.fV;
                int length2 = aVarArr2.length;
                while (i < length2) {
                    String j = j(aVarArr2[i]);
                    if (j == aio) {
                        return aik;
                    }
                    stringBuffer.append(j);
                    i++;
                }
                return stringBuffer.toString();
            case 8:
                return Boolean.valueOf(aVar.fU);
            default:
                bh.A("Failed to convert a value of type: " + aVar.type);
                return aik;
        }
    }

    public static Double p(Object obj) {
        return s(obj) ? Double.valueOf(getDouble(obj)) : cs(m(obj));
    }

    public static Boolean q(Object obj) {
        return obj instanceof Boolean ? (Boolean) obj : ct(m(obj));
    }

    public static d.a r(Object obj) {
        boolean z = false;
        d.a aVar = new d.a();
        if (obj instanceof d.a) {
            return (d.a) obj;
        }
        if (obj instanceof String) {
            aVar.type = 1;
            aVar.fN = (String) obj;
        } else if (obj instanceof List) {
            aVar.type = 2;
            List<Object> list = (List) obj;
            ArrayList arrayList = new ArrayList(list.size());
            boolean z2 = false;
            for (Object r : list) {
                d.a r2 = r(r);
                if (r2 == ais) {
                    return ais;
                }
                boolean z3 = z2 || r2.fX;
                arrayList.add(r2);
                z2 = z3;
            }
            aVar.fO = (d.a[]) arrayList.toArray(new d.a[0]);
            z = z2;
        } else if (obj instanceof Map) {
            aVar.type = 3;
            Set<Map.Entry> entrySet = ((Map) obj).entrySet();
            ArrayList arrayList2 = new ArrayList(entrySet.size());
            ArrayList arrayList3 = new ArrayList(entrySet.size());
            boolean z4 = false;
            for (Map.Entry entry : entrySet) {
                d.a r3 = r(entry.getKey());
                d.a r4 = r(entry.getValue());
                if (r3 == ais || r4 == ais) {
                    return ais;
                }
                boolean z5 = z4 || r3.fX || r4.fX;
                arrayList2.add(r3);
                arrayList3.add(r4);
                z4 = z5;
            }
            aVar.fP = (d.a[]) arrayList2.toArray(new d.a[0]);
            aVar.fQ = (d.a[]) arrayList3.toArray(new d.a[0]);
            z = z4;
        } else if (s(obj)) {
            aVar.type = 1;
            aVar.fN = obj.toString();
        } else if (t(obj)) {
            aVar.type = 6;
            aVar.fT = u(obj);
        } else if (obj instanceof Boolean) {
            aVar.type = 8;
            aVar.fU = ((Boolean) obj).booleanValue();
        } else {
            bh.A("Converting to Value from unknown object type: " + (obj == null ? "null" : obj.getClass().toString()));
            return ais;
        }
        aVar.fX = z;
        return aVar;
    }

    private static boolean s(Object obj) {
        return (obj instanceof Double) || (obj instanceof Float) || ((obj instanceof dg) && ((dg) obj).mN());
    }

    private static boolean t(Object obj) {
        return (obj instanceof Byte) || (obj instanceof Short) || (obj instanceof Integer) || (obj instanceof Long) || ((obj instanceof dg) && ((dg) obj).mO());
    }

    private static long u(Object obj) {
        if (obj instanceof Number) {
            return ((Number) obj).longValue();
        }
        bh.A("getInt64 received non-Number");
        return 0;
    }
}
