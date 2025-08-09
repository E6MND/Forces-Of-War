package com.google.android.gms.games;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.d;
import com.google.android.gms.games.internal.player.MostRecentGameInfo;
import com.google.android.gms.games.internal.player.MostRecentGameInfoRef;
import com.google.android.gms.games.internal.player.PlayerColumnNames;

public final class PlayerRef extends d implements Player {
    private final PlayerLevelInfo MU;
    private final PlayerColumnNames Nd;
    private final MostRecentGameInfoRef Ne;

    public PlayerRef(DataHolder holder, int dataRow) {
        this(holder, dataRow, (String) null);
    }

    public PlayerRef(DataHolder holder, int dataRow, String prefix) {
        super(holder, dataRow);
        PlayerLevel playerLevel;
        this.Nd = new PlayerColumnNames(prefix);
        this.Ne = new MostRecentGameInfoRef(holder, dataRow, this.Nd);
        if (gQ()) {
            int integer = getInteger(this.Nd.RV);
            int integer2 = getInteger(this.Nd.RY);
            PlayerLevel playerLevel2 = new PlayerLevel(integer, getLong(this.Nd.RW), getLong(this.Nd.RX));
            if (integer != integer2) {
                playerLevel = new PlayerLevel(integer2, getLong(this.Nd.RX), getLong(this.Nd.RZ));
            } else {
                playerLevel = playerLevel2;
            }
            this.MU = new PlayerLevelInfo(getLong(this.Nd.RU), getLong(this.Nd.Sa), playerLevel2, playerLevel);
            return;
        }
        this.MU = null;
    }

    private boolean gQ() {
        return !ax(this.Nd.RU) && getLong(this.Nd.RU) != -1;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        return PlayerEntity.a(this, obj);
    }

    public Player freeze() {
        return new PlayerEntity(this);
    }

    public int gN() {
        return getInteger(this.Nd.RS);
    }

    public boolean gO() {
        return getBoolean(this.Nd.Sc);
    }

    public MostRecentGameInfo gP() {
        if (ax(this.Nd.Sd)) {
            return null;
        }
        return this.Ne;
    }

    public String getDisplayName() {
        return getString(this.Nd.RM);
    }

    public void getDisplayName(CharArrayBuffer dataOut) {
        a(this.Nd.RM, dataOut);
    }

    public Uri getHiResImageUri() {
        return aw(this.Nd.RP);
    }

    public String getHiResImageUrl() {
        return getString(this.Nd.RQ);
    }

    public Uri getIconImageUri() {
        return aw(this.Nd.RN);
    }

    public String getIconImageUrl() {
        return getString(this.Nd.RO);
    }

    public long getLastPlayedWithTimestamp() {
        if (!av(this.Nd.RT) || ax(this.Nd.RT)) {
            return -1;
        }
        return getLong(this.Nd.RT);
    }

    public PlayerLevelInfo getLevelInfo() {
        return this.MU;
    }

    public String getPlayerId() {
        return getString(this.Nd.RL);
    }

    public long getRetrievedTimestamp() {
        return getLong(this.Nd.RR);
    }

    public String getTitle() {
        return getString(this.Nd.Sb);
    }

    public void getTitle(CharArrayBuffer dataOut) {
        a(this.Nd.Sb, dataOut);
    }

    public boolean hasHiResImage() {
        return getHiResImageUri() != null;
    }

    public boolean hasIconImage() {
        return getIconImageUri() != null;
    }

    public int hashCode() {
        return PlayerEntity.a(this);
    }

    public String toString() {
        return PlayerEntity.b((Player) this);
    }

    public void writeToParcel(Parcel dest, int flags) {
        ((PlayerEntity) freeze()).writeToParcel(dest, flags);
    }
}
