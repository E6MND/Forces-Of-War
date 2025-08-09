package com.google.android.gms.internal;

import com.google.android.gms.internal.lx;
import com.google.android.gms.wearable.Asset;
import com.google.android.gms.wearable.DataMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public final class lw {

    public static class a {
        public final lx amm;
        public final List<Asset> amn;

        public a(lx lxVar, List<Asset> list) {
            this.amm = lxVar;
            this.amn = list;
        }
    }

    private static int a(String str, lx.a.C0079a[] aVarArr) {
        int i = 14;
        for (lx.a.C0079a aVar : aVarArr) {
            if (i == 14) {
                if (aVar.type == 9 || aVar.type == 2 || aVar.type == 6) {
                    i = aVar.type;
                } else if (aVar.type != 14) {
                    throw new IllegalArgumentException("Unexpected TypedValue type: " + aVar.type + " for key " + str);
                }
            } else if (aVar.type != i) {
                throw new IllegalArgumentException("The ArrayList elements should all be the same type, but ArrayList with key " + str + " contains items of type " + i + " and " + aVar.type);
            }
        }
        return i;
    }

    static int a(List<Asset> list, Asset asset) {
        list.add(asset);
        return list.size() - 1;
    }

    public static a a(DataMap dataMap) {
        lx lxVar = new lx();
        ArrayList arrayList = new ArrayList();
        lxVar.amo = a(dataMap, (List<Asset>) arrayList);
        return new a(lxVar, arrayList);
    }

    private static lx.a.C0079a a(List<Asset> list, Object obj) {
        int i;
        int i2 = 0;
        lx.a.C0079a aVar = new lx.a.C0079a();
        if (obj == null) {
            aVar.type = 14;
            return aVar;
        }
        aVar.ams = new lx.a.C0079a.C0080a();
        if (obj instanceof String) {
            aVar.type = 2;
            aVar.ams.amu = (String) obj;
        } else if (obj instanceof Integer) {
            aVar.type = 6;
            aVar.ams.amy = ((Integer) obj).intValue();
        } else if (obj instanceof Long) {
            aVar.type = 5;
            aVar.ams.amx = ((Long) obj).longValue();
        } else if (obj instanceof Double) {
            aVar.type = 3;
            aVar.ams.amv = ((Double) obj).doubleValue();
        } else if (obj instanceof Float) {
            aVar.type = 4;
            aVar.ams.amw = ((Float) obj).floatValue();
        } else if (obj instanceof Boolean) {
            aVar.type = 8;
            aVar.ams.amA = ((Boolean) obj).booleanValue();
        } else if (obj instanceof Byte) {
            aVar.type = 7;
            aVar.ams.amz = ((Byte) obj).byteValue();
        } else if (obj instanceof byte[]) {
            aVar.type = 1;
            aVar.ams.amt = (byte[]) obj;
        } else if (obj instanceof String[]) {
            aVar.type = 11;
            aVar.ams.amD = (String[]) obj;
        } else if (obj instanceof long[]) {
            aVar.type = 12;
            aVar.ams.amE = (long[]) obj;
        } else if (obj instanceof float[]) {
            aVar.type = 15;
            aVar.ams.amF = (float[]) obj;
        } else if (obj instanceof Asset) {
            aVar.type = 13;
            aVar.ams.amG = (long) a(list, (Asset) obj);
        } else if (obj instanceof DataMap) {
            aVar.type = 9;
            DataMap dataMap = (DataMap) obj;
            Set<String> keySet = dataMap.keySet();
            lx.a[] aVarArr = new lx.a[keySet.size()];
            Iterator<String> it = keySet.iterator();
            while (true) {
                int i3 = i2;
                if (!it.hasNext()) {
                    break;
                }
                String next = it.next();
                aVarArr[i3] = new lx.a();
                aVarArr[i3].name = next;
                aVarArr[i3].amq = a(list, dataMap.get(next));
                i2 = i3 + 1;
            }
            aVar.ams.amB = aVarArr;
        } else if (obj instanceof ArrayList) {
            aVar.type = 10;
            ArrayList arrayList = (ArrayList) obj;
            lx.a.C0079a[] aVarArr2 = new lx.a.C0079a[arrayList.size()];
            Object obj2 = null;
            int size = arrayList.size();
            int i4 = 0;
            int i5 = 14;
            while (i4 < size) {
                Object obj3 = arrayList.get(i4);
                lx.a.C0079a a2 = a(list, obj3);
                if (a2.type == 14 || a2.type == 2 || a2.type == 6 || a2.type == 9) {
                    if (i5 == 14 && a2.type != 14) {
                        i = a2.type;
                    } else if (a2.type != i5) {
                        throw new IllegalArgumentException("ArrayList elements must all be of the sameclass, but this one contains a " + obj2.getClass() + " and a " + obj3.getClass());
                    } else {
                        obj3 = obj2;
                        i = i5;
                    }
                    aVarArr2[i4] = a2;
                    i4++;
                    i5 = i;
                    obj2 = obj3;
                } else {
                    throw new IllegalArgumentException("The only ArrayList element types supported by DataBundleUtil are String, Integer, Bundle, and null, but this ArrayList contains a " + obj3.getClass());
                }
            }
            aVar.ams.amC = aVarArr2;
        } else {
            throw new RuntimeException("newFieldValueFromValue: unexpected value " + obj.getClass().getSimpleName());
        }
        return aVar;
    }

    public static DataMap a(a aVar) {
        DataMap dataMap = new DataMap();
        for (lx.a aVar2 : aVar.amm.amo) {
            a(aVar.amn, dataMap, aVar2.name, aVar2.amq);
        }
        return dataMap;
    }

    private static ArrayList a(List<Asset> list, lx.a.C0079a.C0080a aVar, int i) {
        ArrayList arrayList = new ArrayList(aVar.amC.length);
        for (lx.a.C0079a aVar2 : aVar.amC) {
            if (aVar2.type == 14) {
                arrayList.add((Object) null);
            } else if (i == 9) {
                DataMap dataMap = new DataMap();
                for (lx.a aVar3 : aVar2.ams.amB) {
                    a(list, dataMap, aVar3.name, aVar3.amq);
                }
                arrayList.add(dataMap);
            } else if (i == 2) {
                arrayList.add(aVar2.ams.amu);
            } else if (i == 6) {
                arrayList.add(Integer.valueOf(aVar2.ams.amy));
            } else {
                throw new IllegalArgumentException("Unexpected typeOfArrayList: " + i);
            }
        }
        return arrayList;
    }

    private static void a(List<Asset> list, DataMap dataMap, String str, lx.a.C0079a aVar) {
        int i = aVar.type;
        if (i == 14) {
            dataMap.putString(str, (String) null);
            return;
        }
        lx.a.C0079a.C0080a aVar2 = aVar.ams;
        if (i == 1) {
            dataMap.putByteArray(str, aVar2.amt);
        } else if (i == 11) {
            dataMap.putStringArray(str, aVar2.amD);
        } else if (i == 12) {
            dataMap.putLongArray(str, aVar2.amE);
        } else if (i == 15) {
            dataMap.putFloatArray(str, aVar2.amF);
        } else if (i == 2) {
            dataMap.putString(str, aVar2.amu);
        } else if (i == 3) {
            dataMap.putDouble(str, aVar2.amv);
        } else if (i == 4) {
            dataMap.putFloat(str, aVar2.amw);
        } else if (i == 5) {
            dataMap.putLong(str, aVar2.amx);
        } else if (i == 6) {
            dataMap.putInt(str, aVar2.amy);
        } else if (i == 7) {
            dataMap.putByte(str, (byte) aVar2.amz);
        } else if (i == 8) {
            dataMap.putBoolean(str, aVar2.amA);
        } else if (i == 13) {
            if (list == null) {
                throw new RuntimeException("populateBundle: unexpected type for: " + str);
            }
            dataMap.putAsset(str, list.get((int) aVar2.amG));
        } else if (i == 9) {
            DataMap dataMap2 = new DataMap();
            for (lx.a aVar3 : aVar2.amB) {
                a(list, dataMap2, aVar3.name, aVar3.amq);
            }
            dataMap.putDataMap(str, dataMap2);
        } else if (i == 10) {
            int a2 = a(str, aVar2.amC);
            ArrayList a3 = a(list, aVar2, a2);
            if (a2 == 14) {
                dataMap.putStringArrayList(str, a3);
            } else if (a2 == 9) {
                dataMap.putDataMapArrayList(str, a3);
            } else if (a2 == 2) {
                dataMap.putStringArrayList(str, a3);
            } else if (a2 == 6) {
                dataMap.putIntegerArrayList(str, a3);
            } else {
                throw new IllegalStateException("Unexpected typeOfArrayList: " + a2);
            }
        } else {
            throw new RuntimeException("populateBundle: unexpected type " + i);
        }
    }

    private static lx.a[] a(DataMap dataMap, List<Asset> list) {
        Set<String> keySet = dataMap.keySet();
        lx.a[] aVarArr = new lx.a[keySet.size()];
        int i = 0;
        Iterator<String> it = keySet.iterator();
        while (true) {
            int i2 = i;
            if (!it.hasNext()) {
                return aVarArr;
            }
            String next = it.next();
            Object obj = dataMap.get(next);
            aVarArr[i2] = new lx.a();
            aVarArr[i2].name = next;
            aVarArr[i2].amq = a(list, obj);
            i = i2 + 1;
        }
    }
}
