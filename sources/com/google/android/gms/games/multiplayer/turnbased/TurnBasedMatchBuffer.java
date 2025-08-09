package com.google.android.gms.games.multiplayer.turnbased;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.g;

public final class TurnBasedMatchBuffer extends g<TurnBasedMatch> {
    public TurnBasedMatchBuffer(DataHolder dataHolder) {
        super(dataHolder);
    }

    /* access modifiers changed from: protected */
    public String eU() {
        return "external_match_id";
    }

    /* access modifiers changed from: protected */
    /* renamed from: i */
    public TurnBasedMatch c(int i, int i2) {
        return new TurnBasedMatchRef(this.DD, i, i2);
    }
}
