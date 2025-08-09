package com.google.android.gms.games.leaderboard;

import android.os.Bundle;

public final class LeaderboardScoreBufferHeader {
    private final Bundle HJ;

    public static final class Builder {
        private Builder() {
        }
    }

    public LeaderboardScoreBufferHeader(Bundle bundle) {
        this.HJ = bundle == null ? new Bundle() : bundle;
    }

    public Bundle iw() {
        return this.HJ;
    }
}
