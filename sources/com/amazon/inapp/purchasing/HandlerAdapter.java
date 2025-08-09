package com.amazon.inapp.purchasing;

import android.os.Handler;

class HandlerAdapter {
    private Handler _handler;

    HandlerAdapter(Handler handler) {
        this._handler = handler;
    }

    /* access modifiers changed from: package-private */
    public boolean post(Runnable runnable) {
        return this._handler.post(runnable);
    }
}
