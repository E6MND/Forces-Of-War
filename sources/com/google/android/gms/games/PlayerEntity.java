package com.google.android.gms.games;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.games.internal.GamesDowngradeableSafeParcel;
import com.google.android.gms.games.internal.player.MostRecentGameInfo;
import com.google.android.gms.games.internal.player.MostRecentGameInfoEntity;
import com.google.android.gms.internal.gy;
import com.google.android.gms.internal.hl;
import com.google.android.gms.internal.il;

public final class PlayerEntity extends GamesDowngradeableSafeParcel implements Player {
    public static final Parcelable.Creator<PlayerEntity> CREATOR = new PlayerEntityCreatorCompat();
    private final String HV;
    private final String Lk;
    private final String MA;
    private final String MP;
    private final long MQ;
    private final int MR;
    private final long MS;
    private final MostRecentGameInfoEntity MT;
    private final PlayerLevelInfo MU;
    private final boolean MV;
    private final Uri Mo;
    private final Uri Mp;
    private final String Mz;
    private final int xJ;

    static final class PlayerEntityCreatorCompat extends PlayerEntityCreator {
        PlayerEntityCreatorCompat() {
        }

        /* renamed from: be */
        public PlayerEntity createFromParcel(Parcel parcel) {
            if (PlayerEntity.c(PlayerEntity.fl()) || PlayerEntity.aA(PlayerEntity.class.getCanonicalName())) {
                return super.createFromParcel(parcel);
            }
            String readString = parcel.readString();
            String readString2 = parcel.readString();
            String readString3 = parcel.readString();
            String readString4 = parcel.readString();
            return new PlayerEntity(10, readString, readString2, readString3 == null ? null : Uri.parse(readString3), readString4 == null ? null : Uri.parse(readString4), parcel.readLong(), -1, -1, (String) null, (String) null, (String) null, (MostRecentGameInfoEntity) null, (PlayerLevelInfo) null, true);
        }
    }

    PlayerEntity(int versionCode, String playerId, String displayName, Uri iconImageUri, Uri hiResImageUri, long retrievedTimestamp, int isInCircles, long lastPlayedWithTimestamp, String iconImageUrl, String hiResImageUrl, String title, MostRecentGameInfoEntity mostRecentGameInfo, PlayerLevelInfo playerLevelInfo, boolean hasAllPublic) {
        this.xJ = versionCode;
        this.MP = playerId;
        this.Lk = displayName;
        this.Mo = iconImageUri;
        this.Mz = iconImageUrl;
        this.Mp = hiResImageUri;
        this.MA = hiResImageUrl;
        this.MQ = retrievedTimestamp;
        this.MR = isInCircles;
        this.MS = lastPlayedWithTimestamp;
        this.HV = title;
        this.MV = hasAllPublic;
        this.MT = mostRecentGameInfo;
        this.MU = playerLevelInfo;
    }

    public PlayerEntity(Player player) {
        this.xJ = 10;
        this.MP = player.getPlayerId();
        this.Lk = player.getDisplayName();
        this.Mo = player.getIconImageUri();
        this.Mz = player.getIconImageUrl();
        this.Mp = player.getHiResImageUri();
        this.MA = player.getHiResImageUrl();
        this.MQ = player.getRetrievedTimestamp();
        this.MR = player.gN();
        this.MS = player.getLastPlayedWithTimestamp();
        this.HV = player.getTitle();
        this.MV = player.gO();
        MostRecentGameInfo gP = player.gP();
        this.MT = gP == null ? null : new MostRecentGameInfoEntity(gP);
        this.MU = player.getLevelInfo();
        gy.c(this.MP);
        gy.c(this.Lk);
        gy.A(this.MQ > 0);
    }

    static int a(Player player) {
        return hl.hashCode(player.getPlayerId(), player.getDisplayName(), player.getIconImageUri(), player.getHiResImageUri(), Long.valueOf(player.getRetrievedTimestamp()), player.getTitle(), player.getLevelInfo());
    }

    static boolean a(Player player, Object obj) {
        if (!(obj instanceof Player)) {
            return false;
        }
        if (player == obj) {
            return true;
        }
        Player player2 = (Player) obj;
        return hl.equal(player2.getPlayerId(), player.getPlayerId()) && hl.equal(player2.getDisplayName(), player.getDisplayName()) && hl.equal(player2.getIconImageUri(), player.getIconImageUri()) && hl.equal(player2.getHiResImageUri(), player.getHiResImageUri()) && hl.equal(Long.valueOf(player2.getRetrievedTimestamp()), Long.valueOf(player.getRetrievedTimestamp())) && hl.equal(player2.getTitle(), player.getTitle()) && hl.equal(player2.getLevelInfo(), player.getLevelInfo());
    }

    static String b(Player player) {
        return hl.e(player).a("PlayerId", player.getPlayerId()).a("DisplayName", player.getDisplayName()).a("IconImageUri", player.getIconImageUri()).a("IconImageUrl", player.getIconImageUrl()).a("HiResImageUri", player.getHiResImageUri()).a("HiResImageUrl", player.getHiResImageUrl()).a("RetrievedTimestamp", Long.valueOf(player.getRetrievedTimestamp())).a("Title", player.getTitle()).a("LevelInfo", player.getLevelInfo()).toString();
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        return a(this, obj);
    }

    public Player freeze() {
        return this;
    }

    public int gN() {
        return this.MR;
    }

    public boolean gO() {
        return this.MV;
    }

    public MostRecentGameInfo gP() {
        return this.MT;
    }

    public String getDisplayName() {
        return this.Lk;
    }

    public void getDisplayName(CharArrayBuffer dataOut) {
        il.b(this.Lk, dataOut);
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

    public long getLastPlayedWithTimestamp() {
        return this.MS;
    }

    public PlayerLevelInfo getLevelInfo() {
        return this.MU;
    }

    public String getPlayerId() {
        return this.MP;
    }

    public long getRetrievedTimestamp() {
        return this.MQ;
    }

    public String getTitle() {
        return this.HV;
    }

    public void getTitle(CharArrayBuffer dataOut) {
        il.b(this.HV, dataOut);
    }

    public int getVersionCode() {
        return this.xJ;
    }

    public boolean hasHiResImage() {
        return getHiResImageUri() != null;
    }

    public boolean hasIconImage() {
        return getIconImageUri() != null;
    }

    public int hashCode() {
        return a(this);
    }

    public boolean isDataValid() {
        return true;
    }

    public String toString() {
        return b((Player) this);
    }

    public void writeToParcel(Parcel dest, int flags) {
        String str = null;
        if (!fm()) {
            PlayerEntityCreator.a(this, dest, flags);
            return;
        }
        dest.writeString(this.MP);
        dest.writeString(this.Lk);
        dest.writeString(this.Mo == null ? null : this.Mo.toString());
        if (this.Mp != null) {
            str = this.Mp.toString();
        }
        dest.writeString(str);
        dest.writeLong(this.MQ);
    }
}
