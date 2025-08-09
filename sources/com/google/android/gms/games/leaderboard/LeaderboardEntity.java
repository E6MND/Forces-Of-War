package com.google.android.gms.games.leaderboard;

import android.database.CharArrayBuffer;
import android.net.Uri;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.internal.hl;
import com.google.android.gms.internal.il;
import java.util.ArrayList;

public final class LeaderboardEntity implements Leaderboard {
    private final String Lk;
    private final Uri Mo;
    private final String Mz;
    private final String Sm;
    private final int Sn;
    private final ArrayList<LeaderboardVariantEntity> So;
    private final Game Sp;

    public LeaderboardEntity(Leaderboard leaderboard) {
        this.Sm = leaderboard.getLeaderboardId();
        this.Lk = leaderboard.getDisplayName();
        this.Mo = leaderboard.getIconImageUri();
        this.Mz = leaderboard.getIconImageUrl();
        this.Sn = leaderboard.getScoreOrder();
        Game game = leaderboard.getGame();
        this.Sp = game == null ? null : new GameEntity(game);
        ArrayList<LeaderboardVariant> variants = leaderboard.getVariants();
        int size = variants.size();
        this.So = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            this.So.add((LeaderboardVariantEntity) variants.get(i).freeze());
        }
    }

    static int a(Leaderboard leaderboard) {
        return hl.hashCode(leaderboard.getLeaderboardId(), leaderboard.getDisplayName(), leaderboard.getIconImageUri(), Integer.valueOf(leaderboard.getScoreOrder()), leaderboard.getVariants());
    }

    static boolean a(Leaderboard leaderboard, Object obj) {
        if (!(obj instanceof Leaderboard)) {
            return false;
        }
        if (leaderboard == obj) {
            return true;
        }
        Leaderboard leaderboard2 = (Leaderboard) obj;
        return hl.equal(leaderboard2.getLeaderboardId(), leaderboard.getLeaderboardId()) && hl.equal(leaderboard2.getDisplayName(), leaderboard.getDisplayName()) && hl.equal(leaderboard2.getIconImageUri(), leaderboard.getIconImageUri()) && hl.equal(Integer.valueOf(leaderboard2.getScoreOrder()), Integer.valueOf(leaderboard.getScoreOrder())) && hl.equal(leaderboard2.getVariants(), leaderboard.getVariants());
    }

    static String b(Leaderboard leaderboard) {
        return hl.e(leaderboard).a("LeaderboardId", leaderboard.getLeaderboardId()).a("DisplayName", leaderboard.getDisplayName()).a("IconImageUri", leaderboard.getIconImageUri()).a("IconImageUrl", leaderboard.getIconImageUrl()).a("ScoreOrder", Integer.valueOf(leaderboard.getScoreOrder())).a("Variants", leaderboard.getVariants()).toString();
    }

    public boolean equals(Object obj) {
        return a(this, obj);
    }

    public String getDisplayName() {
        return this.Lk;
    }

    public void getDisplayName(CharArrayBuffer dataOut) {
        il.b(this.Lk, dataOut);
    }

    public Game getGame() {
        return this.Sp;
    }

    public Uri getIconImageUri() {
        return this.Mo;
    }

    public String getIconImageUrl() {
        return this.Mz;
    }

    public String getLeaderboardId() {
        return this.Sm;
    }

    public int getScoreOrder() {
        return this.Sn;
    }

    public ArrayList<LeaderboardVariant> getVariants() {
        return new ArrayList<>(this.So);
    }

    public int hashCode() {
        return a(this);
    }

    public boolean isDataValid() {
        return true;
    }

    /* renamed from: iu */
    public Leaderboard freeze() {
        return this;
    }

    public String toString() {
        return b(this);
    }
}
