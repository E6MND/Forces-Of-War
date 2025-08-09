package com.google.android.gms.games.leaderboard;

import com.google.android.gms.common.data.DataBuffer;
import com.google.android.gms.common.data.DataHolder;

public final class LeaderboardScoreBuffer extends DataBuffer<LeaderboardScore> {
    private final LeaderboardScoreBufferHeader Sq;

    public LeaderboardScoreBuffer(DataHolder dataHolder) {
        super(dataHolder);
        this.Sq = new LeaderboardScoreBufferHeader(dataHolder.eP());
    }

    public LeaderboardScore get(int position) {
        return new LeaderboardScoreRef(this.DD, position);
    }

    public LeaderboardScoreBufferHeader iv() {
        return this.Sq;
    }
}
