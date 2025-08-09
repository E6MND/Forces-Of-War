package com.google.android.gms.games.internal.player;

import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.hl;

public final class MostRecentGameInfoEntity implements SafeParcelable, MostRecentGameInfo {
    public static final MostRecentGameInfoEntityCreator CREATOR = new MostRecentGameInfoEntityCreator();
    private final String RF;
    private final String RG;
    private final long RH;
    private final Uri RI;
    private final Uri RJ;
    private final Uri RK;
    private final int xJ;

    MostRecentGameInfoEntity(int versionCode, String gameId, String gameName, long activityTimestampMillis, Uri gameIconImageUri, Uri gameHiResIconImageUri, Uri gameFeaturedImageUri) {
        this.xJ = versionCode;
        this.RF = gameId;
        this.RG = gameName;
        this.RH = activityTimestampMillis;
        this.RI = gameIconImageUri;
        this.RJ = gameHiResIconImageUri;
        this.RK = gameFeaturedImageUri;
    }

    public MostRecentGameInfoEntity(MostRecentGameInfo info) {
        this.xJ = 2;
        this.RF = info.ik();
        this.RG = info.il();
        this.RH = info.im();
        this.RI = info.in();
        this.RJ = info.io();
        this.RK = info.ip();
    }

    static int a(MostRecentGameInfo mostRecentGameInfo) {
        return hl.hashCode(mostRecentGameInfo.ik(), mostRecentGameInfo.il(), Long.valueOf(mostRecentGameInfo.im()), mostRecentGameInfo.in(), mostRecentGameInfo.io(), mostRecentGameInfo.ip());
    }

    static boolean a(MostRecentGameInfo mostRecentGameInfo, Object obj) {
        if (!(obj instanceof MostRecentGameInfo)) {
            return false;
        }
        if (mostRecentGameInfo == obj) {
            return true;
        }
        MostRecentGameInfo mostRecentGameInfo2 = (MostRecentGameInfo) obj;
        return hl.equal(mostRecentGameInfo2.ik(), mostRecentGameInfo.ik()) && hl.equal(mostRecentGameInfo2.il(), mostRecentGameInfo.il()) && hl.equal(Long.valueOf(mostRecentGameInfo2.im()), Long.valueOf(mostRecentGameInfo.im())) && hl.equal(mostRecentGameInfo2.in(), mostRecentGameInfo.in()) && hl.equal(mostRecentGameInfo2.io(), mostRecentGameInfo.io()) && hl.equal(mostRecentGameInfo2.ip(), mostRecentGameInfo.ip());
    }

    static String b(MostRecentGameInfo mostRecentGameInfo) {
        return hl.e(mostRecentGameInfo).a("GameId", mostRecentGameInfo.ik()).a("GameName", mostRecentGameInfo.il()).a("ActivityTimestampMillis", Long.valueOf(mostRecentGameInfo.im())).a("GameIconUri", mostRecentGameInfo.in()).a("GameHiResUri", mostRecentGameInfo.io()).a("GameFeaturedUri", mostRecentGameInfo.ip()).toString();
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

    public int hashCode() {
        return a(this);
    }

    public String ik() {
        return this.RF;
    }

    public String il() {
        return this.RG;
    }

    public long im() {
        return this.RH;
    }

    public Uri in() {
        return this.RI;
    }

    public Uri io() {
        return this.RJ;
    }

    public Uri ip() {
        return this.RK;
    }

    /* renamed from: iq */
    public MostRecentGameInfo freeze() {
        return this;
    }

    public boolean isDataValid() {
        return true;
    }

    public String toString() {
        return b(this);
    }

    public void writeToParcel(Parcel out, int flags) {
        MostRecentGameInfoEntityCreator.a(this, out, flags);
    }
}
