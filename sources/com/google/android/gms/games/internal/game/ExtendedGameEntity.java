package com.google.android.gms.games.internal.game;

import android.os.Parcel;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.internal.GamesDowngradeableSafeParcel;
import com.google.android.gms.games.snapshot.SnapshotMetadata;
import com.google.android.gms.games.snapshot.SnapshotMetadataEntity;
import com.google.android.gms.internal.hl;
import java.util.ArrayList;

public final class ExtendedGameEntity extends GamesDowngradeableSafeParcel implements ExtendedGame {
    public static final ExtendedGameEntityCreator CREATOR = new ExtendedGameEntityCreatorCompat();
    private final SnapshotMetadataEntity RA;
    private final GameEntity Rq;
    private final int Rr;
    private final boolean Rs;
    private final int Rt;
    private final long Ru;
    private final long Rv;
    private final String Rw;
    private final long Rx;
    private final String Ry;
    private final ArrayList<GameBadgeEntity> Rz;
    private final int xJ;

    static final class ExtendedGameEntityCreatorCompat extends ExtendedGameEntityCreator {
        ExtendedGameEntityCreatorCompat() {
        }

        /* renamed from: bg */
        public ExtendedGameEntity createFromParcel(Parcel parcel) {
            if (ExtendedGameEntity.c(ExtendedGameEntity.fl()) || ExtendedGameEntity.aA(ExtendedGameEntity.class.getCanonicalName())) {
                return super.createFromParcel(parcel);
            }
            GameEntity createFromParcel = GameEntity.CREATOR.createFromParcel(parcel);
            int readInt = parcel.readInt();
            boolean z = parcel.readInt() == 1;
            int readInt2 = parcel.readInt();
            long readLong = parcel.readLong();
            long readLong2 = parcel.readLong();
            String readString = parcel.readString();
            long readLong3 = parcel.readLong();
            String readString2 = parcel.readString();
            int readInt3 = parcel.readInt();
            ArrayList arrayList = new ArrayList(readInt3);
            for (int i = 0; i < readInt3; i++) {
                arrayList.add(GameBadgeEntity.CREATOR.createFromParcel(parcel));
            }
            return new ExtendedGameEntity(2, createFromParcel, readInt, z, readInt2, readLong, readLong2, readString, readLong3, readString2, arrayList, (SnapshotMetadataEntity) null);
        }
    }

    ExtendedGameEntity(int versionCode, GameEntity game, int availability, boolean owned, int achievementUnlockedCount, long lastPlayedServerTimestamp, long priceMicros, String formattedPrice, long fullPriceMicros, String formattedFullPrice, ArrayList<GameBadgeEntity> badges, SnapshotMetadataEntity snapshot) {
        this.xJ = versionCode;
        this.Rq = game;
        this.Rr = availability;
        this.Rs = owned;
        this.Rt = achievementUnlockedCount;
        this.Ru = lastPlayedServerTimestamp;
        this.Rv = priceMicros;
        this.Rw = formattedPrice;
        this.Rx = fullPriceMicros;
        this.Ry = formattedFullPrice;
        this.Rz = badges;
        this.RA = snapshot;
    }

    public ExtendedGameEntity(ExtendedGame extendedGame) {
        SnapshotMetadataEntity snapshotMetadataEntity = null;
        this.xJ = 2;
        Game game = extendedGame.getGame();
        this.Rq = game == null ? null : new GameEntity(game);
        this.Rr = extendedGame.hM();
        this.Rs = extendedGame.hN();
        this.Rt = extendedGame.hO();
        this.Ru = extendedGame.hP();
        this.Rv = extendedGame.hQ();
        this.Rw = extendedGame.hR();
        this.Rx = extendedGame.hS();
        this.Ry = extendedGame.hT();
        SnapshotMetadata hU = extendedGame.hU();
        this.RA = hU != null ? new SnapshotMetadataEntity(hU) : snapshotMetadataEntity;
        ArrayList<GameBadge> hL = extendedGame.hL();
        int size = hL.size();
        this.Rz = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            this.Rz.add((GameBadgeEntity) hL.get(i).freeze());
        }
    }

    static int a(ExtendedGame extendedGame) {
        return hl.hashCode(extendedGame.getGame(), Integer.valueOf(extendedGame.hM()), Boolean.valueOf(extendedGame.hN()), Integer.valueOf(extendedGame.hO()), Long.valueOf(extendedGame.hP()), Long.valueOf(extendedGame.hQ()), extendedGame.hR(), Long.valueOf(extendedGame.hS()), extendedGame.hT());
    }

    static boolean a(ExtendedGame extendedGame, Object obj) {
        if (!(obj instanceof ExtendedGame)) {
            return false;
        }
        if (extendedGame == obj) {
            return true;
        }
        ExtendedGame extendedGame2 = (ExtendedGame) obj;
        return hl.equal(extendedGame2.getGame(), extendedGame.getGame()) && hl.equal(Integer.valueOf(extendedGame2.hM()), Integer.valueOf(extendedGame.hM())) && hl.equal(Boolean.valueOf(extendedGame2.hN()), Boolean.valueOf(extendedGame.hN())) && hl.equal(Integer.valueOf(extendedGame2.hO()), Integer.valueOf(extendedGame.hO())) && hl.equal(Long.valueOf(extendedGame2.hP()), Long.valueOf(extendedGame.hP())) && hl.equal(Long.valueOf(extendedGame2.hQ()), Long.valueOf(extendedGame.hQ())) && hl.equal(extendedGame2.hR(), extendedGame.hR()) && hl.equal(Long.valueOf(extendedGame2.hS()), Long.valueOf(extendedGame.hS())) && hl.equal(extendedGame2.hT(), extendedGame.hT());
    }

    static String b(ExtendedGame extendedGame) {
        return hl.e(extendedGame).a("Game", extendedGame.getGame()).a("Availability", Integer.valueOf(extendedGame.hM())).a("Owned", Boolean.valueOf(extendedGame.hN())).a("AchievementUnlockedCount", Integer.valueOf(extendedGame.hO())).a("LastPlayedServerTimestamp", Long.valueOf(extendedGame.hP())).a("PriceMicros", Long.valueOf(extendedGame.hQ())).a("FormattedPrice", extendedGame.hR()).a("FullPriceMicros", Long.valueOf(extendedGame.hS())).a("FormattedFullPrice", extendedGame.hT()).a("Snapshot", extendedGame.hU()).toString();
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        return a(this, obj);
    }

    public int getVersionCode() {
        return this.xJ;
    }

    public ArrayList<GameBadge> hL() {
        return new ArrayList<>(this.Rz);
    }

    public int hM() {
        return this.Rr;
    }

    public boolean hN() {
        return this.Rs;
    }

    public int hO() {
        return this.Rt;
    }

    public long hP() {
        return this.Ru;
    }

    public long hQ() {
        return this.Rv;
    }

    public String hR() {
        return this.Rw;
    }

    public long hS() {
        return this.Rx;
    }

    public String hT() {
        return this.Ry;
    }

    public SnapshotMetadata hU() {
        return this.RA;
    }

    /* renamed from: hV */
    public GameEntity getGame() {
        return this.Rq;
    }

    /* renamed from: hW */
    public ExtendedGame freeze() {
        return this;
    }

    public int hashCode() {
        return a(this);
    }

    public boolean isDataValid() {
        return true;
    }

    public String toString() {
        return b((ExtendedGame) this);
    }

    public void writeToParcel(Parcel dest, int flags) {
        if (!fm()) {
            ExtendedGameEntityCreator.a(this, dest, flags);
            return;
        }
        this.Rq.writeToParcel(dest, flags);
        dest.writeInt(this.Rr);
        dest.writeInt(this.Rs ? 1 : 0);
        dest.writeInt(this.Rt);
        dest.writeLong(this.Ru);
        dest.writeLong(this.Rv);
        dest.writeString(this.Rw);
        dest.writeLong(this.Rx);
        dest.writeString(this.Ry);
        int size = this.Rz.size();
        dest.writeInt(size);
        for (int i = 0; i < size; i++) {
            this.Rz.get(i).writeToParcel(dest, flags);
        }
    }
}
