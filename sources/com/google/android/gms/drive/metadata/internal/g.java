package com.google.android.gms.drive.metadata.internal;

import android.os.Bundle;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.drive.metadata.a;

public class g extends a<Long> {
    public g(String str, int i) {
        super(str, i);
    }

    /* access modifiers changed from: protected */
    public void a(Bundle bundle, Long l) {
        bundle.putLong(getName(), l.longValue());
    }

    /* access modifiers changed from: protected */
    /* renamed from: g */
    public Long b(DataHolder dataHolder, int i, int i2) {
        return Long.valueOf(dataHolder.a(getName(), i, i2));
    }

    /* access modifiers changed from: protected */
    /* renamed from: j */
    public Long f(Bundle bundle) {
        return Long.valueOf(bundle.getLong(getName()));
    }
}
