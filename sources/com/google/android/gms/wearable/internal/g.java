package com.google.android.gms.wearable.internal;

import com.google.android.gms.wearable.DataEvent;
import com.google.android.gms.wearable.DataItem;

public class g implements DataEvent {
    private int AQ;
    private DataItem alE;

    public g(DataEvent dataEvent) {
        this.AQ = dataEvent.getType();
        this.alE = (DataItem) dataEvent.getDataItem().freeze();
    }

    public DataItem getDataItem() {
        return this.alE;
    }

    public int getType() {
        return this.AQ;
    }

    public boolean isDataValid() {
        return true;
    }

    /* renamed from: nk */
    public DataEvent freeze() {
        return this;
    }
}
