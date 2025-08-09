package com.google.android.gms.games.leaderboard;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.d;

public final class LeaderboardVariantRef extends d implements LeaderboardVariant {
    LeaderboardVariantRef(DataHolder holder, int dataRow) {
        super(holder, dataRow);
    }

    public boolean equals(Object obj) {
        return LeaderboardVariantEntity.a(this, obj);
    }

    public int getCollection() {
        return getInteger("collection");
    }

    public String getDisplayPlayerRank() {
        return getString("player_display_rank");
    }

    public String getDisplayPlayerScore() {
        return getString("player_display_score");
    }

    public long getNumScores() {
        if (ax("total_scores")) {
            return -1;
        }
        return getLong("total_scores");
    }

    public long getPlayerRank() {
        if (ax("player_rank")) {
            return -1;
        }
        return getLong("player_rank");
    }

    public String getPlayerScoreTag() {
        return getString("player_score_tag");
    }

    public long getRawPlayerScore() {
        if (ax("player_raw_score")) {
            return -1;
        }
        return getLong("player_raw_score");
    }

    public int getTimeSpan() {
        return getInteger("timespan");
    }

    public boolean hasPlayerInfo() {
        return !ax("player_raw_score");
    }

    public int hashCode() {
        return LeaderboardVariantEntity.a(this);
    }

    public String iA() {
        return getString("window_page_token_next");
    }

    /* renamed from: iB */
    public LeaderboardVariant freeze() {
        return new LeaderboardVariantEntity(this);
    }

    public String iy() {
        return getString("top_page_token_next");
    }

    public String iz() {
        return getString("window_page_token_prev");
    }

    public String toString() {
        return LeaderboardVariantEntity.b(this);
    }
}
