package com.google.android.gms.internal;

import com.google.android.gms.internal.mb;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class mc<M extends mb<M>, T> {
    protected final Class<T> amV;
    protected final boolean amW;
    protected final int tag;
    protected final int type;

    private mc(int i, Class<T> cls, int i2, boolean z) {
        this.type = i;
        this.amV = cls;
        this.tag = i2;
        this.amW = z;
    }

    public static <M extends mb<M>, T extends mf> mc<M, T> a(int i, Class<T> cls, int i2) {
        return new mc<>(i, cls, i2, false);
    }

    /* access modifiers changed from: protected */
    public void a(mh mhVar, List<Object> list) {
        list.add(u(lz.p(mhVar.amZ)));
    }

    /* access modifiers changed from: protected */
    public boolean eM(int i) {
        return i == this.tag;
    }

    /* access modifiers changed from: package-private */
    public final T i(List<mh> list) {
        if (list == null) {
            return null;
        }
        if (this.amW) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < list.size(); i++) {
                mh mhVar = list.get(i);
                if (eM(mhVar.tag) && mhVar.amZ.length != 0) {
                    a(mhVar, arrayList);
                }
            }
            int size = arrayList.size();
            if (size == 0) {
                return null;
            }
            T cast = this.amV.cast(Array.newInstance(this.amV.getComponentType(), size));
            for (int i2 = 0; i2 < size; i2++) {
                Array.set(cast, i2, arrayList.get(i2));
            }
            return cast;
        }
        int size2 = list.size() - 1;
        mh mhVar2 = null;
        while (mhVar2 == null && size2 >= 0) {
            mh mhVar3 = list.get(size2);
            if (!eM(mhVar3.tag) || mhVar3.amZ.length == 0) {
                mhVar3 = mhVar2;
            }
            size2--;
            mhVar2 = mhVar3;
        }
        if (mhVar2 == null) {
            return null;
        }
        return this.amV.cast(u(lz.p(mhVar2.amZ)));
    }

    /* access modifiers changed from: protected */
    public Object u(lz lzVar) {
        Class<?> componentType = this.amW ? this.amV.getComponentType() : this.amV;
        try {
            switch (this.type) {
                case 10:
                    mf mfVar = (mf) componentType.newInstance();
                    lzVar.a(mfVar, mi.eO(this.tag));
                    return mfVar;
                case 11:
                    mf mfVar2 = (mf) componentType.newInstance();
                    lzVar.a(mfVar2);
                    return mfVar2;
                default:
                    throw new IllegalArgumentException("Unknown type " + this.type);
            }
        } catch (InstantiationException e) {
            throw new IllegalArgumentException("Error creating instance of class " + componentType, e);
        } catch (IllegalAccessException e2) {
            throw new IllegalArgumentException("Error creating instance of class " + componentType, e2);
        } catch (IOException e3) {
            throw new IllegalArgumentException("Error reading extension field", e3);
        }
    }
}
