package com.google.android.gms.games.leaderboard;

import android.database.CharArrayBuffer;
import android.net.Uri;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.d;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerRef;

public final class LeaderboardScoreRef extends d implements LeaderboardScore {
    private final PlayerRef SD;

    LeaderboardScoreRef(DataHolder holder, int dataRow) {
        super(holder, dataRow);
        this.SD = new PlayerRef(holder, dataRow);
    }

    public boolean equals(Object obj) {
        return LeaderboardScoreEntity.a(this, obj);
    }

    public String getDisplayRank() {
        return getString("display_rank");
    }

    public void getDisplayRank(CharArrayBuffer dataOut) {
        a("display_rank", dataOut);
    }

    public String getDisplayScore() {
        return getString("display_score");
    }

    public void getDisplayScore(CharArrayBuffer dataOut) {
        a("display_score", dataOut);
    }

    public long getRank() {
        return getLong("rank");
    }

    public long getRawScore() {
        return getLong("raw_score");
    }

    public Player getScoreHolder() {
        if (ax("external_player_id")) {
            return null;
        }
        return this.SD;
    }

    public String getScoreHolderDisplayName() {
        return ax("external_player_id") ? getString("default_display_name") : this.SD.getDisplayName();
    }

    public void getScoreHolderDisplayName(CharArrayBuffer dataOut) {
        if (ax("external_player_id")) {
            a("default_display_name", dataOut);
        } else {
            this.SD.getDisplayName(dataOut);
        }
    }

    public Uri getScoreHolderHiResImageUri() {
        if (ax("external_player_id")) {
            return null;
        }
        return this.SD.getHiResImageUri();
    }

    public String getScoreHolderHiResImageUrl() {
        if (ax("external_player_id")) {
            return null;
        }
        return this.SD.getHiResImageUrl();
    }

    public Uri getScoreHolderIconImageUri() {
        return ax("external_player_id") ? aw("default_display_image_uri") : this.SD.getIconImageUri();
    }

    public String getScoreHolderIconImageUrl() {
        return ax("external_player_id") ? getString("default_display_image_url") : this.SD.getIconImageUrl();
    }

    public String getScoreTag() {
        return getString("score_tag");
    }

    public long getTimestampMillis() {
        return getLong("achieved_timestamp");
    }

    public int hashCode() {
        return LeaderboardScoreEntity.a(this);
    }

    /* renamed from: ix */
    public LeaderboardScore freeze() {
        return new LeaderboardScoreEntity(this);
    }

    public String toString() {
        return LeaderboardScoreEntity.b(this);
    }
}
