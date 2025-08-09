package com.google.android.gms.drive.metadata.internal;

import android.os.Bundle;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.drive.metadata.a;

public class b extends a<Boolean> {
    public b(String str, int i) {
        super(str, i);
    }

    /* access modifiers changed from: protected */
    public void a(Bundle bundle, Boolean bool) {
        bundle.putBoolean(getName(), bool.booleanValue());
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public Boolean b(DataHolder dataHolder, int i, int i2) {
        return Boolean.valueOf(dataHolder.d(getName(), i, i2));
    }

    /* access modifiers changed from: protected */
    /* renamed from: g */
    public Boolean f(Bundle bundle) {
        return Boolean.valueOf(bundle.getBoolean(getName()));
    }
}
