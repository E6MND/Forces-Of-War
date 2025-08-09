package com.google.android.gms.drive.metadata.internal;

import android.os.Bundle;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.drive.metadata.a;

public class l extends a<String> {
    public l(String str, int i) {
        super(str, i);
    }

    /* access modifiers changed from: protected */
    public void a(Bundle bundle, String str) {
        bundle.putString(getName(), str);
    }

    /* access modifiers changed from: protected */
    /* renamed from: h */
    public String b(DataHolder dataHolder, int i, int i2) {
        return dataHolder.c(getName(), i, i2);
    }

    /* access modifiers changed from: protected */
    /* renamed from: m */
    public String f(Bundle bundle) {
        return bundle.getString(getName());
    }
}
