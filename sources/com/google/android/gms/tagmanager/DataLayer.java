package com.google.android.gms.tagmanager;

import com.google.android.gms.location.LocationRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataLayer {
    public static final String EVENT_KEY = "event";
    public static final Object OBJECT_NOT_PRESENT = new Object();
    static final String[] aeV = "gtm.lifetime".toString().split("\\.");
    private static final Pattern aeW = Pattern.compile("(\\d+)\\s*([smhd]?)");
    private final ConcurrentHashMap<b, Integer> aeX;
    private final Map<String, Object> aeY;
    private final ReentrantLock aeZ;
    private final LinkedList<Map<String, Object>> afa;
    private final c afb;
    /* access modifiers changed from: private */
    public final CountDownLatch afc;

    static final class a {
        public final String JI;
        public final Object afe;

        a(String str, Object obj) {
            this.JI = str;
            this.afe = obj;
        }

        public boolean equals(Object o) {
            if (!(o instanceof a)) {
                return false;
            }
            a aVar = (a) o;
            return this.JI.equals(aVar.JI) && this.afe.equals(aVar.afe);
        }

        public int hashCode() {
            return Arrays.hashCode(new Integer[]{Integer.valueOf(this.JI.hashCode()), Integer.valueOf(this.afe.hashCode())});
        }

        public String toString() {
            return "Key: " + this.JI + " value: " + this.afe.toString();
        }
    }

    interface b {
        void x(Map<String, Object> map);
    }

    interface c {

        public interface a {
            void d(List<a> list);
        }

        void a(a aVar);

        void a(List<a> list, long j);

        void bP(String str);
    }

    DataLayer() {
        this(new c() {
            public void a(c.a aVar) {
                aVar.d(new ArrayList());
            }

            public void a(List<a> list, long j) {
            }

            public void bP(String str) {
            }
        });
    }

    DataLayer(c persistentStore) {
        this.afb = persistentStore;
        this.aeX = new ConcurrentHashMap<>();
        this.aeY = new HashMap();
        this.aeZ = new ReentrantLock();
        this.afa = new LinkedList<>();
        this.afc = new CountDownLatch(1);
        lt();
    }

    private void A(Map<String, Object> map) {
        Long B = B(map);
        if (B != null) {
            List<a> D = D(map);
            D.remove("gtm.lifetime");
            this.afb.a(D, B.longValue());
        }
    }

    private Long B(Map<String, Object> map) {
        Object C = C(map);
        if (C == null) {
            return null;
        }
        return bO(C.toString());
    }

    private Object C(Map<String, Object> map) {
        String[] strArr = aeV;
        int length = strArr.length;
        int i = 0;
        Object obj = map;
        while (i < length) {
            String str = strArr[i];
            if (!(obj instanceof Map)) {
                return null;
            }
            i++;
            obj = ((Map) obj).get(str);
        }
        return obj;
    }

    private List<a> D(Map<String, Object> map) {
        ArrayList arrayList = new ArrayList();
        a(map, "", arrayList);
        return arrayList;
    }

    private void E(Map<String, Object> map) {
        synchronized (this.aeY) {
            for (String next : map.keySet()) {
                a(c(next, map.get(next)), this.aeY);
            }
        }
        F(map);
    }

    private void F(Map<String, Object> map) {
        for (b x : this.aeX.keySet()) {
            x.x(map);
        }
    }

    private void a(Map<String, Object> map, String str, Collection<a> collection) {
        for (Map.Entry next : map.entrySet()) {
            String str2 = str + (str.length() == 0 ? "" : ".") + ((String) next.getKey());
            if (next.getValue() instanceof Map) {
                a((Map) next.getValue(), str2, collection);
            } else if (!str2.equals("gtm.lifetime")) {
                collection.add(new a(str2, next.getValue()));
            }
        }
    }

    static Long bO(String str) {
        long j;
        Matcher matcher = aeW.matcher(str);
        if (!matcher.matches()) {
            bh.B("unknown _lifetime: " + str);
            return null;
        }
        try {
            j = Long.parseLong(matcher.group(1));
        } catch (NumberFormatException e) {
            bh.D("illegal number in _lifetime value: " + str);
            j = 0;
        }
        if (j <= 0) {
            bh.B("non-positive _lifetime: " + str);
            return null;
        }
        String group = matcher.group(2);
        if (group.length() == 0) {
            return Long.valueOf(j);
        }
        switch (group.charAt(0)) {
            case 'd':
                return Long.valueOf(j * 1000 * 60 * 60 * 24);
            case LocationRequest.PRIORITY_LOW_POWER:
                return Long.valueOf(j * 1000 * 60 * 60);
            case 'm':
                return Long.valueOf(j * 1000 * 60);
            case 's':
                return Long.valueOf(j * 1000);
            default:
                bh.D("unknown units in _lifetime: " + str);
                return null;
        }
    }

    public static List<Object> listOf(Object... objects) {
        ArrayList arrayList = new ArrayList();
        for (Object add : objects) {
            arrayList.add(add);
        }
        return arrayList;
    }

    private void lt() {
        this.afb.a(new c.a() {
            public void d(List<a> list) {
                for (a next : list) {
                    DataLayer.this.z(DataLayer.this.c(next.JI, next.afe));
                }
                DataLayer.this.afc.countDown();
            }
        });
    }

    private void lu() {
        int i = 0;
        while (true) {
            int i2 = i;
            Map poll = this.afa.poll();
            if (poll != null) {
                E(poll);
                i = i2 + 1;
                if (i > 500) {
                    this.afa.clear();
                    throw new RuntimeException("Seems like an infinite loop of pushing to the data layer");
                }
            } else {
                return;
            }
        }
    }

    public static Map<String, Object> mapOf(Object... objects) {
        if (objects.length % 2 != 0) {
            throw new IllegalArgumentException("expected even number of key-value pairs");
        }
        HashMap hashMap = new HashMap();
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= objects.length) {
                return hashMap;
            }
            if (!(objects[i2] instanceof String)) {
                throw new IllegalArgumentException("key is not a string: " + objects[i2]);
            }
            hashMap.put(objects[i2], objects[i2 + 1]);
            i = i2 + 2;
        }
    }

    /* access modifiers changed from: private */
    public void z(Map<String, Object> map) {
        this.aeZ.lock();
        try {
            this.afa.offer(map);
            if (this.aeZ.getHoldCount() == 1) {
                lu();
            }
            A(map);
        } finally {
            this.aeZ.unlock();
        }
    }

    /* access modifiers changed from: package-private */
    public void a(b bVar) {
        this.aeX.put(bVar, 0);
    }

    /* access modifiers changed from: package-private */
    public void a(List<Object> list, List<Object> list2) {
        while (list2.size() < list.size()) {
            list2.add((Object) null);
        }
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 < list.size()) {
                Object obj = list.get(i2);
                if (obj instanceof List) {
                    if (!(list2.get(i2) instanceof List)) {
                        list2.set(i2, new ArrayList());
                    }
                    a((List<Object>) (List) obj, (List<Object>) (List) list2.get(i2));
                } else if (obj instanceof Map) {
                    if (!(list2.get(i2) instanceof Map)) {
                        list2.set(i2, new HashMap());
                    }
                    a((Map<String, Object>) (Map) obj, (Map<String, Object>) (Map) list2.get(i2));
                } else if (obj != OBJECT_NOT_PRESENT) {
                    list2.set(i2, obj);
                }
                i = i2 + 1;
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void a(Map<String, Object> map, Map<String, Object> map2) {
        for (String next : map.keySet()) {
            Object obj = map.get(next);
            if (obj instanceof List) {
                if (!(map2.get(next) instanceof List)) {
                    map2.put(next, new ArrayList());
                }
                a((List<Object>) (List) obj, (List<Object>) (List) map2.get(next));
            } else if (obj instanceof Map) {
                if (!(map2.get(next) instanceof Map)) {
                    map2.put(next, new HashMap());
                }
                a((Map<String, Object>) (Map) obj, (Map<String, Object>) (Map) map2.get(next));
            } else {
                map2.put(next, obj);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void bN(String str) {
        push(str, (Object) null);
        this.afb.bP(str);
    }

    /* access modifiers changed from: package-private */
    public Map<String, Object> c(String str, Object obj) {
        HashMap hashMap = new HashMap();
        String[] split = str.toString().split("\\.");
        int i = 0;
        HashMap hashMap2 = hashMap;
        while (i < split.length - 1) {
            HashMap hashMap3 = new HashMap();
            hashMap2.put(split[i], hashMap3);
            i++;
            hashMap2 = hashMap3;
        }
        hashMap2.put(split[split.length - 1], obj);
        return hashMap;
    }

    public Object get(String key) {
        synchronized (this.aeY) {
            Object obj = this.aeY;
            String[] split = key.split("\\.");
            int length = split.length;
            Object obj2 = obj;
            int i = 0;
            while (i < length) {
                String str = split[i];
                if (!(obj2 instanceof Map)) {
                    return null;
                }
                Object obj3 = ((Map) obj2).get(str);
                if (obj3 == null) {
                    return null;
                }
                i++;
                obj2 = obj3;
            }
            return obj2;
        }
    }

    public void push(String key, Object value) {
        push(c(key, value));
    }

    public void push(Map<String, Object> update) {
        try {
            this.afc.await();
        } catch (InterruptedException e) {
            bh.D("DataLayer.push: unexpected InterruptedException");
        }
        z(update);
    }

    public void pushEvent(String eventName, Map<String, Object> update) {
        HashMap hashMap = new HashMap(update);
        hashMap.put(EVENT_KEY, eventName);
        push(hashMap);
    }
}
