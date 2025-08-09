package com.google.android.gms.games;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.games.internal.GamesDowngradeableSafeParcel;
import com.google.android.gms.internal.hl;
import com.google.android.gms.internal.il;

public final class GameEntity extends GamesDowngradeableSafeParcel implements Game {
    public static final Parcelable.Creator<GameEntity> CREATOR = new GameEntityCreatorCompat();
    private final String Lk;
    private final String MA;
    private final String MB;
    private final boolean MC;
    private final boolean MD;
    private final boolean ME;
    private final String Mk;
    private final String Ml;
    private final String Mm;
    private final String Mn;
    private final Uri Mo;
    private final Uri Mp;
    private final Uri Mq;
    private final boolean Mr;
    private final boolean Ms;
    private final String Mt;
    private final int Mu;
    private final int Mv;
    private final int Mw;
    private final boolean Mx;
    private final boolean My;
    private final String Mz;
    private final int xJ;
    private final String zM;

    static final class GameEntityCreatorCompat extends GameEntityCreator {
        GameEntityCreatorCompat() {
        }

        /* renamed from: bd */
        public GameEntity createFromParcel(Parcel parcel) {
            if (GameEntity.c(GameEntity.fl()) || GameEntity.aA(GameEntity.class.getCanonicalName())) {
                return super.createFromParcel(parcel);
            }
            String readString = parcel.readString();
            String readString2 = parcel.readString();
            String readString3 = parcel.readString();
            String readString4 = parcel.readString();
            String readString5 = parcel.readString();
            String readString6 = parcel.readString();
            String readString7 = parcel.readString();
            Uri parse = readString7 == null ? null : Uri.parse(readString7);
            String readString8 = parcel.readString();
            Uri parse2 = readString8 == null ? null : Uri.parse(readString8);
            String readString9 = parcel.readString();
            return new GameEntity(4, readString, readString2, readString3, readString4, readString5, readString6, parse, parse2, readString9 == null ? null : Uri.parse(readString9), parcel.readInt() > 0, parcel.readInt() > 0, parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt(), false, false, (String) null, (String) null, (String) null, false, false, false);
        }
    }

    GameEntity(int versionCode, String applicationId, String displayName, String primaryCategory, String secondaryCategory, String description, String developerName, Uri iconImageUri, Uri hiResImageUri, Uri featuredImageUri, boolean playEnabledGame, boolean instanceInstalled, String instancePackageName, int gameplayAclStatus, int achievementTotalCount, int leaderboardCount, boolean realTimeEnabled, boolean turnBasedEnabled, String iconImageUrl, String hiResImageUrl, String featuredImageUrl, boolean muted, boolean identitySharingConfirmed, boolean snapshotsEnabled) {
        this.xJ = versionCode;
        this.zM = applicationId;
        this.Lk = displayName;
        this.Mk = primaryCategory;
        this.Ml = secondaryCategory;
        this.Mm = description;
        this.Mn = developerName;
        this.Mo = iconImageUri;
        this.Mz = iconImageUrl;
        this.Mp = hiResImageUri;
        this.MA = hiResImageUrl;
        this.Mq = featuredImageUri;
        this.MB = featuredImageUrl;
        this.Mr = playEnabledGame;
        this.Ms = instanceInstalled;
        this.Mt = instancePackageName;
        this.Mu = gameplayAclStatus;
        this.Mv = achievementTotalCount;
        this.Mw = leaderboardCount;
        this.Mx = realTimeEnabled;
        this.My = turnBasedEnabled;
        this.MC = muted;
        this.MD = identitySharingConfirmed;
        this.ME = snapshotsEnabled;
    }

    public GameEntity(Game game) {
        this.xJ = 4;
        this.zM = game.getApplicationId();
        this.Mk = game.getPrimaryCategory();
        this.Ml = game.getSecondaryCategory();
        this.Mm = game.getDescription();
        this.Mn = game.getDeveloperName();
        this.Lk = game.getDisplayName();
        this.Mo = game.getIconImageUri();
        this.Mz = game.getIconImageUrl();
        this.Mp = game.getHiResImageUri();
        this.MA = game.getHiResImageUrl();
        this.Mq = game.getFeaturedImageUri();
        this.MB = game.getFeaturedImageUrl();
        this.Mr = game.gH();
        this.Ms = game.gJ();
        this.Mt = game.gK();
        this.Mu = game.gL();
        this.Mv = game.getAchievementTotalCount();
        this.Mw = game.getLeaderboardCount();
        this.Mx = game.isRealTimeMultiplayerEnabled();
        this.My = game.isTurnBasedMultiplayerEnabled();
        this.MC = game.isMuted();
        this.MD = game.gI();
        this.ME = game.areSnapshotsEnabled();
    }

    static int a(Game game) {
        return hl.hashCode(game.getApplicationId(), game.getDisplayName(), game.getPrimaryCategory(), game.getSecondaryCategory(), game.getDescription(), game.getDeveloperName(), game.getIconImageUri(), game.getHiResImageUri(), game.getFeaturedImageUri(), Boolean.valueOf(game.gH()), Boolean.valueOf(game.gJ()), game.gK(), Integer.valueOf(game.gL()), Integer.valueOf(game.getAchievementTotalCount()), Integer.valueOf(game.getLeaderboardCount()), Boolean.valueOf(game.isRealTimeMultiplayerEnabled()), Boolean.valueOf(game.isTurnBasedMultiplayerEnabled()), Boolean.valueOf(game.isMuted()), Boolean.valueOf(game.gI()), Boolean.valueOf(game.areSnapshotsEnabled()));
    }

    static boolean a(Game game, Object obj) {
        if (!(obj instanceof Game)) {
            return false;
        }
        if (game == obj) {
            return true;
        }
        Game game2 = (Game) obj;
        if (hl.equal(game2.getApplicationId(), game.getApplicationId()) && hl.equal(game2.getDisplayName(), game.getDisplayName()) && hl.equal(game2.getPrimaryCategory(), game.getPrimaryCategory()) && hl.equal(game2.getSecondaryCategory(), game.getSecondaryCategory()) && hl.equal(game2.getDescription(), game.getDescription()) && hl.equal(game2.getDeveloperName(), game.getDeveloperName()) && hl.equal(game2.getIconImageUri(), game.getIconImageUri()) && hl.equal(game2.getHiResImageUri(), game.getHiResImageUri()) && hl.equal(game2.getFeaturedImageUri(), game.getFeaturedImageUri()) && hl.equal(Boolean.valueOf(game2.gH()), Boolean.valueOf(game.gH())) && hl.equal(Boolean.valueOf(game2.gJ()), Boolean.valueOf(game.gJ())) && hl.equal(game2.gK(), game.gK()) && hl.equal(Integer.valueOf(game2.gL()), Integer.valueOf(game.gL())) && hl.equal(Integer.valueOf(game2.getAchievementTotalCount()), Integer.valueOf(game.getAchievementTotalCount())) && hl.equal(Integer.valueOf(game2.getLeaderboardCount()), Integer.valueOf(game.getLeaderboardCount())) && hl.equal(Boolean.valueOf(game2.isRealTimeMultiplayerEnabled()), Boolean.valueOf(game.isRealTimeMultiplayerEnabled()))) {
            if (hl.equal(Boolean.valueOf(game2.isTurnBasedMultiplayerEnabled()), Boolean.valueOf(game.isTurnBasedMultiplayerEnabled() && hl.equal(Boolean.valueOf(game2.isMuted()), Boolean.valueOf(game.isMuted())) && hl.equal(Boolean.valueOf(game2.gI()), Boolean.valueOf(game.gI())))) && hl.equal(Boolean.valueOf(game2.areSnapshotsEnabled()), Boolean.valueOf(game.areSnapshotsEnabled()))) {
                return true;
            }
        }
        return false;
    }

    static String b(Game game) {
        return hl.e(game).a("ApplicationId", game.getApplicationId()).a("DisplayName", game.getDisplayName()).a("PrimaryCategory", game.getPrimaryCategory()).a("SecondaryCategory", game.getSecondaryCategory()).a("Description", game.getDescription()).a("DeveloperName", game.getDeveloperName()).a("IconImageUri", game.getIconImageUri()).a("IconImageUrl", game.getIconImageUrl()).a("HiResImageUri", game.getHiResImageUri()).a("HiResImageUrl", game.getHiResImageUrl()).a("FeaturedImageUri", game.getFeaturedImageUri()).a("FeaturedImageUrl", game.getFeaturedImageUrl()).a("PlayEnabledGame", Boolean.valueOf(game.gH())).a("InstanceInstalled", Boolean.valueOf(game.gJ())).a("InstancePackageName", game.gK()).a("AchievementTotalCount", Integer.valueOf(game.getAchievementTotalCount())).a("LeaderboardCount", Integer.valueOf(game.getLeaderboardCount())).a("RealTimeMultiplayerEnabled", Boolean.valueOf(game.isRealTimeMultiplayerEnabled())).a("TurnBasedMultiplayerEnabled", Boolean.valueOf(game.isTurnBasedMultiplayerEnabled())).a("AreSnapshotsEnabled", Boolean.valueOf(game.areSnapshotsEnabled())).toString();
    }

    public boolean areSnapshotsEnabled() {
        return this.ME;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        return a(this, obj);
    }

    public Game freeze() {
        return this;
    }

    public boolean gH() {
        return this.Mr;
    }

    public boolean gI() {
        return this.MD;
    }

    public boolean gJ() {
        return this.Ms;
    }

    public String gK() {
        return this.Mt;
    }

    public int gL() {
        return this.Mu;
    }

    public int getAchievementTotalCount() {
        return this.Mv;
    }

    public String getApplicationId() {
        return this.zM;
    }

    public String getDescription() {
        return this.Mm;
    }

    public void getDescription(CharArrayBuffer dataOut) {
        il.b(this.Mm, dataOut);
    }

    public String getDeveloperName() {
        return this.Mn;
    }

    public void getDeveloperName(CharArrayBuffer dataOut) {
        il.b(this.Mn, dataOut);
    }

    public String getDisplayName() {
        return this.Lk;
    }

    public void getDisplayName(CharArrayBuffer dataOut) {
        il.b(this.Lk, dataOut);
    }

    public Uri getFeaturedImageUri() {
        return this.Mq;
    }

    public String getFeaturedImageUrl() {
        return this.MB;
    }

    public Uri getHiResImageUri() {
        return this.Mp;
    }

    public String getHiResImageUrl() {
        return this.MA;
    }

    public Uri getIconImageUri() {
        return this.Mo;
    }

    public String getIconImageUrl() {
        return this.Mz;
    }

    public int getLeaderboardCount() {
        return this.Mw;
    }

    public String getPrimaryCategory() {
        return this.Mk;
    }

    public String getSecondaryCategory() {
        return this.Ml;
    }

    public int getVersionCode() {
        return this.xJ;
    }

    public int hashCode() {
        return a(this);
    }

    public boolean isDataValid() {
        return true;
    }

    public boolean isMuted() {
        return this.MC;
    }

    public boolean isRealTimeMultiplayerEnabled() {
        return this.Mx;
    }

    public boolean isTurnBasedMultiplayerEnabled() {
        return this.My;
    }

    public String toString() {
        return b((Game) this);
    }

    public void writeToParcel(Parcel dest, int flags) {
        int i = 1;
        String str = null;
        if (!fm()) {
            GameEntityCreator.a(this, dest, flags);
            return;
        }
        dest.writeString(this.zM);
        dest.writeString(this.Lk);
        dest.writeString(this.Mk);
        dest.writeString(this.Ml);
        dest.writeString(this.Mm);
        dest.writeString(this.Mn);
        dest.writeString(this.Mo == null ? null : this.Mo.toString());
        dest.writeString(this.Mp == null ? null : this.Mp.toString());
        if (this.Mq != null) {
            str = this.Mq.toString();
        }
        dest.writeString(str);
        dest.writeInt(this.Mr ? 1 : 0);
        if (!this.Ms) {
            i = 0;
        }
        dest.writeInt(i);
        dest.writeString(this.Mt);
        dest.writeInt(this.Mu);
        dest.writeInt(this.Mv);
        dest.writeInt(this.Mw);
    }
}
