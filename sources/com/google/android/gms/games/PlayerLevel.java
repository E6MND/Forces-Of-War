package com.google.android.gms.games;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.hl;
import com.google.android.gms.internal.hn;

public final class PlayerLevel implements SafeParcelable {
    public static final PlayerLevelCreator CREATOR = new PlayerLevelCreator();
    private final int MW;
    private final long MX;
    private final long MY;
    private final int xJ;

    PlayerLevel(int versionCode, int levelNumber, long minXp, long maxXp) {
        boolean z = true;
        hn.a(minXp >= 0, "Min XP must be positive!");
        hn.a(maxXp <= minXp ? false : z, "Max XP must be more than min XP!");
        this.xJ = versionCode;
        this.MW = levelNumber;
        this.MX = minXp;
        this.MY = maxXp;
    }

    public PlayerLevel(int value, long minXp, long maxXp) {
        this(1, value, minXp, maxXp);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof PlayerLevel)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        PlayerLevel playerLevel = (PlayerLevel) obj;
        return hl.equal(Integer.valueOf(playerLevel.getLevelNumber()), Integer.valueOf(getLevelNumber())) && hl.equal(Long.valueOf(playerLevel.getMinXp()), Long.valueOf(getMinXp())) && hl.equal(Long.valueOf(playerLevel.getMaxXp()), Long.valueOf(getMaxXp()));
    }

    public int getLevelNumber() {
        return this.MW;
    }

    public long getMaxXp() {
        return this.MY;
    }

    public long getMinXp() {
        return this.MX;
    }

    public int getVersionCode() {
        return this.xJ;
    }

    public int hashCode() {
        return hl.hashCode(Integer.valueOf(this.MW), Long.valueOf(this.MX), Long.valueOf(this.MY));
    }

    public String toString() {
        return hl.e(this).a("LevelNumber", Integer.valueOf(getLevelNumber())).a("MinXp", Long.valueOf(getMinXp())).a("MaxXp", Long.valueOf(getMaxXp())).toString();
    }

    public void writeToParcel(Parcel out, int flags) {
        PlayerLevelCreator.a(this, out, flags);
    }
}
