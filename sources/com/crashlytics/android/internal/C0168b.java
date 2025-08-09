package com.crashlytics.android.internal;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;

/* renamed from: com.crashlytics.android.internal.b  reason: case insensitive filesystem */
public class C0168b {
    public static final String a = "default";
    private final ConcurrentMap<Class<?>, Set<C0173g>> b;
    private final ConcurrentMap<Class<?>, C0174h> c;
    private final String d;
    private final C0179m e;
    private final C0175i f;
    private final ThreadLocal<ConcurrentLinkedQueue<C0171e>> g;
    private final ThreadLocal<Boolean> h;
    private final Map<Class<?>, Set<Class<?>>> i;

    public C0168b() {
        this(a);
    }

    public C0168b(String str) {
        this(C0179m.b, str);
    }

    public C0168b(C0179m mVar) {
        this(mVar, a);
    }

    public C0168b(C0179m mVar, String str) {
        this(mVar, str, C0175i.a);
    }

    private C0168b(C0179m mVar, String str, C0175i iVar) {
        this.b = new ConcurrentHashMap();
        this.c = new ConcurrentHashMap();
        this.g = new C0169c(this);
        this.h = new C0170d(this);
        this.i = new HashMap();
        this.e = mVar;
        this.d = str;
        this.f = iVar;
    }

    public String toString() {
        return "[Bus \"" + this.d + "\"]";
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x00b2, code lost:
        r2 = new java.util.concurrent.CopyOnWriteArraySet();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(java.lang.Object r7) {
        /*
            r6 = this;
            com.crashlytics.android.internal.m r0 = r6.e
            r0.a(r6)
            com.crashlytics.android.internal.i r0 = r6.f
            java.util.Map r3 = r0.a(r7)
            java.util.Set r0 = r3.keySet()
            java.util.Iterator r4 = r0.iterator()
        L_0x0013:
            boolean r0 = r4.hasNext()
            if (r0 == 0) goto L_0x008e
            java.lang.Object r0 = r4.next()
            java.lang.Class r0 = (java.lang.Class) r0
            java.lang.Object r1 = r3.get(r0)
            com.crashlytics.android.internal.h r1 = (com.crashlytics.android.internal.C0174h) r1
            java.util.concurrent.ConcurrentMap<java.lang.Class<?>, com.crashlytics.android.internal.h> r2 = r6.c
            java.lang.Object r2 = r2.putIfAbsent(r0, r1)
            com.crashlytics.android.internal.h r2 = (com.crashlytics.android.internal.C0174h) r2
            if (r2 == 0) goto L_0x006a
            java.lang.IllegalArgumentException r3 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "Producer method for type "
            r4.<init>(r5)
            java.lang.StringBuilder r0 = r4.append(r0)
            java.lang.String r4 = " found on type "
            java.lang.StringBuilder r0 = r0.append(r4)
            java.lang.Object r1 = r1.a
            java.lang.Class r1 = r1.getClass()
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r1 = ", but already registered by type "
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.Object r1 = r2.a
            java.lang.Class r1 = r1.getClass()
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r1 = "."
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r0 = r0.toString()
            r3.<init>(r0)
            throw r3
        L_0x006a:
            java.util.concurrent.ConcurrentMap<java.lang.Class<?>, java.util.Set<com.crashlytics.android.internal.g>> r2 = r6.b
            java.lang.Object r0 = r2.get(r0)
            java.util.Set r0 = (java.util.Set) r0
            if (r0 == 0) goto L_0x0013
            boolean r2 = r0.isEmpty()
            if (r2 != 0) goto L_0x0013
            java.util.Iterator r2 = r0.iterator()
        L_0x007e:
            boolean r0 = r2.hasNext()
            if (r0 == 0) goto L_0x0013
            java.lang.Object r0 = r2.next()
            com.crashlytics.android.internal.g r0 = (com.crashlytics.android.internal.C0173g) r0
            r6.a((com.crashlytics.android.internal.C0173g) r0, (com.crashlytics.android.internal.C0174h) r1)
            goto L_0x007e
        L_0x008e:
            com.crashlytics.android.internal.i r0 = r6.f
            java.util.Map r3 = r0.b(r7)
            java.util.Set r0 = r3.keySet()
            java.util.Iterator r4 = r0.iterator()
        L_0x009c:
            boolean r0 = r4.hasNext()
            if (r0 == 0) goto L_0x00cc
            java.lang.Object r0 = r4.next()
            java.lang.Class r0 = (java.lang.Class) r0
            java.util.concurrent.ConcurrentMap<java.lang.Class<?>, java.util.Set<com.crashlytics.android.internal.g>> r1 = r6.b
            java.lang.Object r1 = r1.get(r0)
            java.util.Set r1 = (java.util.Set) r1
            if (r1 != 0) goto L_0x00c2
            java.util.concurrent.CopyOnWriteArraySet r2 = new java.util.concurrent.CopyOnWriteArraySet
            r2.<init>()
            java.util.concurrent.ConcurrentMap<java.lang.Class<?>, java.util.Set<com.crashlytics.android.internal.g>> r1 = r6.b
            java.lang.Object r1 = r1.putIfAbsent(r0, r2)
            java.util.Set r1 = (java.util.Set) r1
            if (r1 != 0) goto L_0x00c2
            r1 = r2
        L_0x00c2:
            java.lang.Object r0 = r3.get(r0)
            java.util.Set r0 = (java.util.Set) r0
            r1.addAll(r0)
            goto L_0x009c
        L_0x00cc:
            java.util.Set r0 = r3.entrySet()
            java.util.Iterator r2 = r0.iterator()
        L_0x00d4:
            boolean r0 = r2.hasNext()
            if (r0 == 0) goto L_0x011c
            java.lang.Object r0 = r2.next()
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0
            java.lang.Object r1 = r0.getKey()
            java.lang.Class r1 = (java.lang.Class) r1
            java.util.concurrent.ConcurrentMap<java.lang.Class<?>, com.crashlytics.android.internal.h> r3 = r6.c
            java.lang.Object r1 = r3.get(r1)
            com.crashlytics.android.internal.h r1 = (com.crashlytics.android.internal.C0174h) r1
            if (r1 == 0) goto L_0x00d4
            boolean r3 = r1.a()
            if (r3 == 0) goto L_0x00d4
            java.lang.Object r0 = r0.getValue()
            java.util.Set r0 = (java.util.Set) r0
            java.util.Iterator r3 = r0.iterator()
        L_0x0100:
            boolean r0 = r3.hasNext()
            if (r0 == 0) goto L_0x00d4
            java.lang.Object r0 = r3.next()
            com.crashlytics.android.internal.g r0 = (com.crashlytics.android.internal.C0173g) r0
            boolean r4 = r1.a()
            if (r4 == 0) goto L_0x00d4
            boolean r4 = r0.a()
            if (r4 == 0) goto L_0x0100
            r6.a((com.crashlytics.android.internal.C0173g) r0, (com.crashlytics.android.internal.C0174h) r1)
            goto L_0x0100
        L_0x011c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.crashlytics.android.internal.C0168b.a(java.lang.Object):void");
    }

    private void a(C0173g gVar, C0174h hVar) {
        Object obj = null;
        try {
            obj = hVar.c();
        } catch (InvocationTargetException e2) {
            a("Producer " + hVar + " threw an exception.", e2);
        }
        if (obj != null) {
            a(obj, gVar);
        }
    }

    public void b(Object obj) {
        this.e.a(this);
        for (Map.Entry next : this.f.a(obj).entrySet()) {
            Class cls = (Class) next.getKey();
            C0174h hVar = (C0174h) this.c.get(cls);
            C0174h hVar2 = (C0174h) next.getValue();
            if (hVar2 == null || !hVar2.equals(hVar)) {
                throw new IllegalArgumentException("Missing event producer for an annotated method. Is " + obj.getClass() + " registered?");
            }
            ((C0174h) this.c.remove(cls)).b();
        }
        for (Map.Entry next2 : this.f.b(obj).entrySet()) {
            Set<C0173g> a2 = a((Class<?>) (Class) next2.getKey());
            Collection collection = (Collection) next2.getValue();
            if (a2 == null || !a2.containsAll(collection)) {
                throw new IllegalArgumentException("Missing event handler for an annotated method. Is " + obj.getClass() + " registered?");
            }
            for (C0173g next3 : a2) {
                if (collection.contains(next3)) {
                    next3.b();
                }
            }
            a2.removeAll(collection);
        }
    }

    public void c(Object obj) {
        boolean z;
        this.e.a(this);
        Class<?> cls = obj.getClass();
        Set<Class> set = this.i.get(cls);
        if (set == null) {
            LinkedList linkedList = new LinkedList();
            HashSet hashSet = new HashSet();
            linkedList.add(cls);
            while (!linkedList.isEmpty()) {
                Class cls2 = (Class) linkedList.remove(0);
                hashSet.add(cls2);
                Class superclass = cls2.getSuperclass();
                if (superclass != null) {
                    linkedList.add(superclass);
                }
            }
            this.i.put(cls, hashSet);
            set = hashSet;
        }
        boolean z2 = false;
        for (Class a2 : set) {
            Set<C0173g> a3 = a((Class<?>) a2);
            if (a3 == null || a3.isEmpty()) {
                z = z2;
            } else {
                for (C0173g eVar : a3) {
                    this.g.get().offer(new C0171e(obj, eVar));
                }
                z = true;
            }
            z2 = z;
        }
        if (!z2 && !(obj instanceof C0172f)) {
            c(new C0172f(this, obj));
        }
        a();
    }

    private void a() {
        if (!this.h.get().booleanValue()) {
            this.h.set(true);
            while (true) {
                try {
                    C0171e eVar = (C0171e) this.g.get().poll();
                    if (eVar == null) {
                        return;
                    }
                    if (eVar.b.a()) {
                        a(eVar.a, eVar.b);
                    }
                } finally {
                    this.h.set(false);
                }
            }
        }
    }

    private static void a(Object obj, C0173g gVar) {
        try {
            gVar.a(obj);
        } catch (InvocationTargetException e2) {
            a("Could not dispatch event: " + obj.getClass() + " to handler " + gVar, e2);
        }
    }

    private Set<C0173g> a(Class<?> cls) {
        return (Set) this.b.get(cls);
    }

    private static void a(String str, InvocationTargetException invocationTargetException) {
        Throwable cause = invocationTargetException.getCause();
        if (cause != null) {
            throw new RuntimeException(str, cause);
        }
        throw new RuntimeException(str);
    }
}
