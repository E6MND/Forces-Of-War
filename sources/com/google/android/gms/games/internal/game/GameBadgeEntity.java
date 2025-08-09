package com.google.android.gms.games.internal.game;

import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.games.internal.GamesDowngradeableSafeParcel;
import com.google.android.gms.internal.hl;

public final class GameBadgeEntity extends GamesDowngradeableSafeParcel implements GameBadge {
    public static final GameBadgeEntityCreator CREATOR = new GameBadgeEntityCreatorCompat();
    private int AQ;
    private String HV;
    private String Mm;
    private Uri Mo;
    private final int xJ;

    static final class GameBadgeEntityCreatorCompat extends GameBadgeEntityCreator {
        GameBadgeEntityCreatorCompat() {
        }

        /* renamed from: bh */
        public GameBadgeEntity createFromParcel(Parcel parcel) {
            if (GameBadgeEntity.c(GameBadgeEntity.fl()) || GameBadgeEntity.aA(GameBadgeEntity.class.getCanonicalName())) {
                return super.createFromParcel(parcel);
            }
            int readInt = parcel.readInt();
            String readString = parcel.readString();
            String readString2 = parcel.readString();
            String readString3 = parcel.readString();
            return new GameBadgeEntity(1, readInt, readString, readString2, readString3 == null ? null : Uri.parse(readString3));
        }
    }

    GameBadgeEntity(int versionCode, int type, String title, String description, Uri iconImageUri) {
        this.xJ = versionCode;
        this.AQ = type;
        this.HV = title;
        this.Mm = description;
        this.Mo = iconImageUri;
    }

    public GameBadgeEntity(GameBadge gameBadge) {
        this.xJ = 1;
        this.AQ = gameBadge.getType();
        this.HV = gameBadge.getTitle();
        this.Mm = gameBadge.getDescription();
        this.Mo = gameBadge.getIconImageUri();
    }

    static int a(GameBadge gameBadge) {
        return hl.hashCode(Integer.valueOf(gameBadge.getType()), gameBadge.getTitle(), gameBadge.getDescription(), gameBadge.getIconImageUri());
    }

    static boolean a(GameBadge gameBadge, Object obj) {
        if (!(obj instanceof GameBadge)) {
            return false;
        }
        if (gameBadge == obj) {
            return true;
        }
        GameBadge gameBadge2 = (GameBadge) obj;
        return hl.equal(Integer.valueOf(gameBadge2.getType()), gameBadge.getTitle()) && hl.equal(gameBadge2.getDescription(), gameBadge.getIconImageUri());
    }

    static String b(GameBadge gameBadge) {
        return hl.e(gameBadge).a("Type", Integer.valueOf(gameBadge.getType())).a("Title", gameBadge.getTitle()).a("Description", gameBadge.getDescription()).a("IconImageUri", gameBadge.getIconImageUri()).toString();
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        return a(this, obj);
    }

    public String getDescription() {
        return this.Mm;
    }

    public Uri getIconImageUri() {
        return this.Mo;
    }

    public String getTitle() {
        return this.HV;
    }

    public int getType() {
        return this.AQ;
    }

    public int getVersionCode() {
        return this.xJ;
    }

    /* renamed from: hX */
    public GameBadge freeze() {
        return this;
    }

    public int hashCode() {
        return a(this);
    }

    public boolean isDataValid() {
        return true;
    }

    public String toString() {
        return b((GameBadge) this);
    }

    public void writeToParcel(Parcel dest, int flags) {
        if (!fm()) {
            GameBadgeEntityCreator.a(this, dest, flags);
            return;
        }
        dest.writeInt(this.AQ);
        dest.writeString(this.HV);
        dest.writeString(this.Mm);
        dest.writeString(this.Mo == null ? null : this.Mo.toString());
    }
}
