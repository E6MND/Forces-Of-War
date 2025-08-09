package com.google.android.gms.games.leaderboard;

import android.database.CharArrayBuffer;
import android.net.Uri;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerEntity;
import com.google.android.gms.internal.hl;
import com.google.android.gms.internal.hn;
import com.google.android.gms.internal.il;

public final class LeaderboardScoreEntity implements LeaderboardScore {
    private final String SA;
    private final String SB;
    private final String SC;
    private final long Sr;
    private final String Ss;
    private final String St;
    private final long Su;
    private final long Sv;
    private final String Sw;
    private final Uri Sx;
    private final Uri Sy;
    private final PlayerEntity Sz;

    public LeaderboardScoreEntity(LeaderboardScore score) {
        this.Sr = score.getRank();
        this.Ss = (String) hn.f(score.getDisplayRank());
        this.St = (String) hn.f(score.getDisplayScore());
        this.Su = score.getRawScore();
        this.Sv = score.getTimestampMillis();
        this.Sw = score.getScoreHolderDisplayName();
        this.Sx = score.getScoreHolderIconImageUri();
        this.Sy = score.getScoreHolderHiResImageUri();
        Player scoreHolder = score.getScoreHolder();
        this.Sz = scoreHolder == null ? null : (PlayerEntity) scoreHolder.freeze();
        this.SA = score.getScoreTag();
        this.SB = score.getScoreHolderIconImageUrl();
        this.SC = score.getScoreHolderHiResImageUrl();
    }

    static int a(LeaderboardScore leaderboardScore) {
        return hl.hashCode(Long.valueOf(leaderboardScore.getRank()), leaderboardScore.getDisplayRank(), Long.valueOf(leaderboardScore.getRawScore()), leaderboardScore.getDisplayScore(), Long.valueOf(leaderboardScore.getTimestampMillis()), leaderboardScore.getScoreHolderDisplayName(), leaderboardScore.getScoreHolderIconImageUri(), leaderboardScore.getScoreHolderHiResImageUri(), leaderboardScore.getScoreHolder());
    }

    static boolean a(LeaderboardScore leaderboardScore, Object obj) {
        if (!(obj instanceof LeaderboardScore)) {
            return false;
        }
        if (leaderboardScore == obj) {
            return true;
        }
        LeaderboardScore leaderboardScore2 = (LeaderboardScore) obj;
        return hl.equal(Long.valueOf(leaderboardScore2.getRank()), Long.valueOf(leaderboardScore.getRank())) && hl.equal(leaderboardScore2.getDisplayRank(), leaderboardScore.getDisplayRank()) && hl.equal(Long.valueOf(leaderboardScore2.getRawScore()), Long.valueOf(leaderboardScore.getRawScore())) && hl.equal(leaderboardScore2.getDisplayScore(), leaderboardScore.getDisplayScore()) && hl.equal(Long.valueOf(leaderboardScore2.getTimestampMillis()), Long.valueOf(leaderboardScore.getTimestampMillis())) && hl.equal(leaderboardScore2.getScoreHolderDisplayName(), leaderboardScore.getScoreHolderDisplayName()) && hl.equal(leaderboardScore2.getScoreHolderIconImageUri(), leaderboardScore.getScoreHolderIconImageUri()) && hl.equal(leaderboardScore2.getScoreHolderHiResImageUri(), leaderboardScore.getScoreHolderHiResImageUri()) && hl.equal(leaderboardScore2.getScoreHolder(), leaderboardScore.getScoreHolder()) && hl.equal(leaderboardScore2.getScoreTag(), leaderboardScore.getScoreTag());
    }

    static String b(LeaderboardScore leaderboardScore) {
        return hl.e(leaderboardScore).a("Rank", Long.valueOf(leaderboardScore.getRank())).a("DisplayRank", leaderboardScore.getDisplayRank()).a("Score", Long.valueOf(leaderboardScore.getRawScore())).a("DisplayScore", leaderboardScore.getDisplayScore()).a("Timestamp", Long.valueOf(leaderboardScore.getTimestampMillis())).a("DisplayName", leaderboardScore.getScoreHolderDisplayName()).a("IconImageUri", leaderboardScore.getScoreHolderIconImageUri()).a("IconImageUrl", leaderboardScore.getScoreHolderIconImageUrl()).a("HiResImageUri", leaderboardScore.getScoreHolderHiResImageUri()).a("HiResImageUrl", leaderboardScore.getScoreHolderHiResImageUrl()).a("Player", leaderboardScore.getScoreHolder() == null ? null : leaderboardScore.getScoreHolder()).a("ScoreTag", leaderboardScore.getScoreTag()).toString();
    }

    public boolean equals(Object obj) {
        return a(this, obj);
    }

    public String getDisplayRank() {
        return this.Ss;
    }

    public void getDisplayRank(CharArrayBuffer dataOut) {
        il.b(this.Ss, dataOut);
    }

    public String getDisplayScore() {
        return this.St;
    }

    public void getDisplayScore(CharArrayBuffer dataOut) {
        il.b(this.St, dataOut);
    }

    public long getRank() {
        return this.Sr;
    }

    public long getRawScore() {
        return this.Su;
    }

    public Player getScoreHolder() {
        return this.Sz;
    }

    public String getScoreHolderDisplayName() {
        return this.Sz == null ? this.Sw : this.Sz.getDisplayName();
    }

    public void getScoreHolderDisplayName(CharArrayBuffer dataOut) {
        if (this.Sz == null) {
            il.b(this.Sw, dataOut);
        } else {
            this.Sz.getDisplayName(dataOut);
        }
    }

    public Uri getScoreHolderHiResImageUri() {
        return this.Sz == null ? this.Sy : this.Sz.getHiResImageUri();
    }

    public String getScoreHolderHiResImageUrl() {
        return this.Sz == null ? this.SC : this.Sz.getHiResImageUrl();
    }

    public Uri getScoreHolderIconImageUri() {
        return this.Sz == null ? this.Sx : this.Sz.getIconImageUri();
    }

    public String getScoreHolderIconImageUrl() {
        return this.Sz == null ? this.SB : this.Sz.getIconImageUrl();
    }

    public String getScoreTag() {
        return this.SA;
    }

    public long getTimestampMillis() {
        return this.Sv;
    }

    public int hashCode() {
        return a(this);
    }

    public boolean isDataValid() {
        return true;
    }

    /* renamed from: ix */
    public LeaderboardScore freeze() {
        return this;
    }

    public String toString() {
        return b(this);
    }
}
