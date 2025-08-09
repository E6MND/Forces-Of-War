package com.google.android.gms.common.api;

import com.google.android.gms.common.data.DataHolder;

public abstract class b implements Releasable, Result {
    protected final DataHolder DD;
    protected final Status yw;

    protected b(DataHolder dataHolder) {
        this.yw = new Status(dataHolder.getStatusCode());
        this.DD = dataHolder;
    }

    public Status getStatus() {
        return this.yw;
    }

    public void release() {
        if (this.DD != null) {
            this.DD.close();
        }
    }
}
