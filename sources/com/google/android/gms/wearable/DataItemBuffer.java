package com.google.android.gms.wearable;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.g;
import com.google.android.gms.wearable.internal.o;

public class DataItemBuffer extends g<DataItem> implements Result {
    private final Status yw;

    public DataItemBuffer(DataHolder dataHolder) {
        super(dataHolder);
        this.yw = new Status(dataHolder.getStatusCode());
    }

    /* access modifiers changed from: protected */
    public String eU() {
        return "path";
    }

    public Status getStatus() {
        return this.yw;
    }

    /* access modifiers changed from: protected */
    /* renamed from: n */
    public DataItem c(int i, int i2) {
        return new o(this.DD, i, i2);
    }
}
