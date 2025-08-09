package com.google.android.gms.games.leaderboard;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.g;

public final class LeaderboardBuffer extends g<Leaderboard> {
    public LeaderboardBuffer(DataHolder dataHolder) {
        super(dataHolder);
    }

    /* access modifiers changed from: protected */
    public String eU() {
        return "external_leaderboard_id";
    }

    /* access modifiers changed from: protected */
    /* renamed from: f */
    public Leaderboard c(int i, int i2) {
        return new LeaderboardRef(this.DD, i, i2);
    }
}
