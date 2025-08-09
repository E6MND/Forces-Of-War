package com.google.android.gms.games;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.hl;
import com.google.android.gms.internal.hn;

public final class PlayerLevelInfo implements SafeParcelable {
    public static final PlayerLevelInfoCreator CREATOR = new PlayerLevelInfoCreator();
    private final long MZ;
    private final long Na;
    private final PlayerLevel Nb;
    private final PlayerLevel Nc;
    private final int xJ;

    PlayerLevelInfo(int versionCode, long currentXpTotal, long lastLevelUpTimestamp, PlayerLevel currentLevel, PlayerLevel nextLevel) {
        hn.A(currentXpTotal != -1);
        hn.f(currentLevel);
        hn.f(nextLevel);
        this.xJ = versionCode;
        this.MZ = currentXpTotal;
        this.Na = lastLevelUpTimestamp;
        this.Nb = currentLevel;
        this.Nc = nextLevel;
    }

    public PlayerLevelInfo(long currentXpTotal, long lastLevelUpTimestamp, PlayerLevel currentLevel, PlayerLevel nextLevel) {
        this(1, currentXpTotal, lastLevelUpTimestamp, currentLevel, nextLevel);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof PlayerLevelInfo)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        PlayerLevelInfo playerLevelInfo = (PlayerLevelInfo) obj;
        return hl.equal(Long.valueOf(this.MZ), Long.valueOf(playerLevelInfo.MZ)) && hl.equal(Long.valueOf(this.Na), Long.valueOf(playerLevelInfo.Na)) && hl.equal(this.Nb, playerLevelInfo.Nb) && hl.equal(this.Nc, playerLevelInfo.Nc);
    }

    public PlayerLevel getCurrentLevel() {
        return this.Nb;
    }

    public long getCurrentXpTotal() {
        return this.MZ;
    }

    public long getLastLevelUpTimestamp() {
        return this.Na;
    }

    public PlayerLevel getNextLevel() {
        return this.Nc;
    }

    public int getVersionCode() {
        return this.xJ;
    }

    public int hashCode() {
        return hl.hashCode(Long.valueOf(this.MZ), Long.valueOf(this.Na), this.Nb, this.Nc);
    }

    public boolean isMaxLevel() {
        return this.Nb.equals(this.Nc);
    }

    public void writeToParcel(Parcel out, int flags) {
        PlayerLevelInfoCreator.a(this, out, flags);
    }
}
