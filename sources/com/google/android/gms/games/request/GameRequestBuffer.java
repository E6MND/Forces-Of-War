package com.google.android.gms.games.request;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.g;

public final class GameRequestBuffer extends g<GameRequest> {
    public GameRequestBuffer(DataHolder dataHolder) {
        super(dataHolder);
    }

    /* access modifiers changed from: protected */
    public String eU() {
        return "external_request_id";
    }

    /* access modifiers changed from: protected */
    /* renamed from: k */
    public GameRequest c(int i, int i2) {
        return new GameRequestRef(this.DD, i, i2);
    }
}
