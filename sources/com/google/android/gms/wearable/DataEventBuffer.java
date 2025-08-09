package com.google.android.gms.wearable;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.g;
import com.google.android.gms.wearable.internal.h;

public class DataEventBuffer extends g<DataEvent> implements Result {
    private final Status yw;

    public DataEventBuffer(DataHolder dataHolder) {
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
    /* renamed from: m */
    public DataEvent c(int i, int i2) {
        return new h(this.DD, i, i2);
    }
}
