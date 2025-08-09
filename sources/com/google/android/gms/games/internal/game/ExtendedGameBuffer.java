package com.google.android.gms.games.internal.game;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.g;

public final class ExtendedGameBuffer extends g<ExtendedGame> {
    public ExtendedGameBuffer(DataHolder dataHolder) {
        super(dataHolder);
    }

    /* access modifiers changed from: protected */
    /* renamed from: e */
    public ExtendedGame c(int i, int i2) {
        return new ExtendedGameRef(this.DD, i, i2);
    }

    /* access modifiers changed from: protected */
    public String eU() {
        return "external_game_id";
    }
}
