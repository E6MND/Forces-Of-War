package com.google.android.gms.drive.metadata;

import android.os.Bundle;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import com.google.android.gms.internal.hn;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public abstract class a<T> implements MetadataField<T> {
    private final String JE;
    private final Set<String> JF;
    private final Set<String> JG;
    private final int JH;

    protected a(String str, int i) {
        this.JE = (String) hn.b(str, (Object) "fieldName");
        this.JF = Collections.singleton(str);
        this.JG = Collections.emptySet();
        this.JH = i;
    }

    protected a(String str, Collection<String> collection, Collection<String> collection2, int i) {
        this.JE = (String) hn.b(str, (Object) "fieldName");
        this.JF = Collections.unmodifiableSet(new HashSet(collection));
        this.JG = Collections.unmodifiableSet(new HashSet(collection2));
        this.JH = i;
    }

    public final T a(DataHolder dataHolder, int i, int i2) {
        for (String h : this.JF) {
            if (dataHolder.h(h, i, i2)) {
                return null;
            }
        }
        return b(dataHolder, i, i2);
    }

    /* access modifiers changed from: protected */
    public abstract void a(Bundle bundle, T t);

    public final void a(DataHolder dataHolder, MetadataBundle metadataBundle, int i, int i2) {
        hn.b(dataHolder, (Object) "dataHolder");
        hn.b(metadataBundle, (Object) "bundle");
        metadataBundle.b(this, a(dataHolder, i, i2));
    }

    public final void a(T t, Bundle bundle) {
        hn.b(bundle, (Object) "bundle");
        if (t == null) {
            bundle.putString(getName(), (String) null);
        } else {
            a(bundle, t);
        }
    }

    /* access modifiers changed from: protected */
    public abstract T b(DataHolder dataHolder, int i, int i2);

    public final T e(Bundle bundle) {
        hn.b(bundle, (Object) "bundle");
        if (bundle.get(getName()) != null) {
            return f(bundle);
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public abstract T f(Bundle bundle);

    public final String getName() {
        return this.JE;
    }

    public final Collection<String> gx() {
        return this.JF;
    }

    public String toString() {
        return this.JE;
    }
}
