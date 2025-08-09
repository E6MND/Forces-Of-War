package com.amazon.inapp.purchasing;

import java.util.UUID;

abstract class Request {
    private final String _requestId = UUID.randomUUID().toString();

    Request() {
    }

    /* access modifiers changed from: package-private */
    public String getRequestId() {
        return this._requestId;
    }

    /* access modifiers changed from: package-private */
    public abstract Runnable getRunnable();
}
