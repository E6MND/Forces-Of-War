package com.google.android.gms.internal;

import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public abstract class hb implements SafeParcelable {
    private static final Object FU = new Object();
    private static ClassLoader FV = null;
    private static Integer FW = null;
    private boolean FX = false;

    private static boolean a(Class<?> cls) {
        try {
            return SafeParcelable.NULL.equals(cls.getField("NULL").get((Object) null));
        } catch (IllegalAccessException | NoSuchFieldException e) {
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public static boolean aA(String str) {
        ClassLoader fk = fk();
        if (fk == null) {
            return true;
        }
        try {
            return a(fk.loadClass(str));
        } catch (Exception e) {
            return false;
        }
    }

    protected static ClassLoader fk() {
        ClassLoader classLoader;
        synchronized (FU) {
            classLoader = FV;
        }
        return classLoader;
    }

    /* access modifiers changed from: protected */
    public static Integer fl() {
        Integer num;
        synchronized (FU) {
            num = FW;
        }
        return num;
    }

    /* access modifiers changed from: protected */
    public boolean fm() {
        return this.FX;
    }
}
