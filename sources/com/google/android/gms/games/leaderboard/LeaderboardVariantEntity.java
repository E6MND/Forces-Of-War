package com.google.android.gms.games.leaderboard;

import com.google.android.gms.games.internal.constants.LeaderboardCollection;
import com.google.android.gms.games.internal.constants.TimeSpan;
import com.google.android.gms.internal.hl;

public final class LeaderboardVariantEntity implements LeaderboardVariant {
    private final int SE;
    private final int SF;
    private final boolean SG;
    private final long SH;
    private final String SI;
    private final long SJ;
    private final String SK;
    private final String SL;
    private final long SM;
    private final String SN;
    private final String SO;
    private final String SP;

    public LeaderboardVariantEntity(LeaderboardVariant variant) {
        this.SE = variant.getTimeSpan();
        this.SF = variant.getCollection();
        this.SG = variant.hasPlayerInfo();
        this.SH = variant.getRawPlayerScore();
        this.SI = variant.getDisplayPlayerScore();
        this.SJ = variant.getPlayerRank();
        this.SK = variant.getDisplayPlayerRank();
        this.SL = variant.getPlayerScoreTag();
        this.SM = variant.getNumScores();
        this.SN = variant.iy();
        this.SO = variant.iz();
        this.SP = variant.iA();
    }

    static int a(LeaderboardVariant leaderboardVariant) {
        return hl.hashCode(Integer.valueOf(leaderboardVariant.getTimeSpan()), Integer.valueOf(leaderboardVariant.getCollection()), Boolean.valueOf(leaderboardVariant.hasPlayerInfo()), Long.valueOf(leaderboardVariant.getRawPlayerScore()), leaderboardVariant.getDisplayPlayerScore(), Long.valueOf(leaderboardVariant.getPlayerRank()), leaderboardVariant.getDisplayPlayerRank(), Long.valueOf(leaderboardVariant.getNumScores()), leaderboardVariant.iy(), leaderboardVariant.iA(), leaderboardVariant.iz());
    }

    static boolean a(LeaderboardVariant leaderboardVariant, Object obj) {
        if (!(obj instanceof LeaderboardVariant)) {
            return false;
        }
        if (leaderboardVariant == obj) {
            return true;
        }
        LeaderboardVariant leaderboardVariant2 = (LeaderboardVariant) obj;
        return hl.equal(Integer.valueOf(leaderboardVariant2.getTimeSpan()), Integer.valueOf(leaderboardVariant.getTimeSpan())) && hl.equal(Integer.valueOf(leaderboardVariant2.getCollection()), Integer.valueOf(leaderboardVariant.getCollection())) && hl.equal(Boolean.valueOf(leaderboardVariant2.hasPlayerInfo()), Boolean.valueOf(leaderboardVariant.hasPlayerInfo())) && hl.equal(Long.valueOf(leaderboardVariant2.getRawPlayerScore()), Long.valueOf(leaderboardVariant.getRawPlayerScore())) && hl.equal(leaderboardVariant2.getDisplayPlayerScore(), leaderboardVariant.getDisplayPlayerScore()) && hl.equal(Long.valueOf(leaderboardVariant2.getPlayerRank()), Long.valueOf(leaderboardVariant.getPlayerRank())) && hl.equal(leaderboardVariant2.getDisplayPlayerRank(), leaderboardVariant.getDisplayPlayerRank()) && hl.equal(Long.valueOf(leaderboardVariant2.getNumScores()), Long.valueOf(leaderboardVariant.getNumScores())) && hl.equal(leaderboardVariant2.iy(), leaderboardVariant.iy()) && hl.equal(leaderboardVariant2.iA(), leaderboardVariant.iA()) && hl.equal(leaderboardVariant2.iz(), leaderboardVariant.iz());
    }

    static String b(LeaderboardVariant leaderboardVariant) {
        return hl.e(leaderboardVariant).a("TimeSpan", TimeSpan.cm(leaderboardVariant.getTimeSpan())).a("Collection", LeaderboardCollection.cm(leaderboardVariant.getCollection())).a("RawPlayerScore", leaderboardVariant.hasPlayerInfo() ? Long.valueOf(leaderboardVariant.getRawPlayerScore()) : "none").a("DisplayPlayerScore", leaderboardVariant.hasPlayerInfo() ? leaderboardVariant.getDisplayPlayerScore() : "none").a("PlayerRank", leaderboardVariant.hasPlayerInfo() ? Long.valueOf(leaderboardVariant.getPlayerRank()) : "none").a("DisplayPlayerRank", leaderboardVariant.hasPlayerInfo() ? leaderboardVariant.getDisplayPlayerRank() : "none").a("NumScores", Long.valueOf(leaderboardVariant.getNumScores())).a("TopPageNextToken", leaderboardVariant.iy()).a("WindowPageNextToken", leaderboardVariant.iA()).a("WindowPagePrevToken", leaderboardVariant.iz()).toString();
    }

    public boolean equals(Object obj) {
        return a(this, obj);
    }

    public int getCollection() {
        return this.SF;
    }

    public String getDisplayPlayerRank() {
        return this.SK;
    }

    public String getDisplayPlayerScore() {
        return this.SI;
    }

    public long getNumScores() {
        return this.SM;
    }

    public long getPlayerRank() {
        return this.SJ;
    }

    public String getPlayerScoreTag() {
        return this.SL;
    }

    public long getRawPlayerScore() {
        return this.SH;
    }

    public int getTimeSpan() {
        return this.SE;
    }

    public boolean hasPlayerInfo() {
        return this.SG;
    }

    public int hashCode() {
        return a(this);
    }

    public String iA() {
        return this.SP;
    }

    /* renamed from: iB */
    public LeaderboardVariant freeze() {
        return this;
    }

    public boolean isDataValid() {
        return true;
    }

    public String iy() {
        return this.SN;
    }

    public String iz() {
        return this.SO;
    }

    public String toString() {
        return b(this);
    }
}
