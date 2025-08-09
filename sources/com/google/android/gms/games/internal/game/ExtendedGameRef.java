package com.google.android.gms.games.internal.game;

import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.d;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameRef;
import com.google.android.gms.games.snapshot.SnapshotMetadata;
import com.google.android.gms.games.snapshot.SnapshotMetadataRef;
import java.util.ArrayList;

public class ExtendedGameRef extends d implements ExtendedGame {
    private final GameRef RB;
    private final SnapshotMetadataRef RC;
    private final int RD;

    ExtendedGameRef(DataHolder holder, int dataRow, int numChildren) {
        super(holder, dataRow);
        this.RB = new GameRef(holder, dataRow);
        this.RD = numChildren;
        if (!av("external_snapshot_id") || ax("external_snapshot_id")) {
            this.RC = null;
        } else {
            this.RC = new SnapshotMetadataRef(holder, dataRow);
        }
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        return ExtendedGameEntity.a(this, obj);
    }

    public Game getGame() {
        return this.RB;
    }

    public ArrayList<GameBadge> hL() {
        if (this.DD.c("badge_title", this.Ez, this.DD.ae(this.Ez)) == null) {
            return new ArrayList<>(0);
        }
        ArrayList<GameBadge> arrayList = new ArrayList<>(this.RD);
        for (int i = 0; i < this.RD; i++) {
            arrayList.add(new GameBadgeRef(this.DD, this.Ez + i));
        }
        return arrayList;
    }

    public int hM() {
        return getInteger("availability");
    }

    public boolean hN() {
        return getBoolean("owned");
    }

    public int hO() {
        return getInteger("achievement_unlocked_count");
    }

    public long hP() {
        return getLong("last_played_server_time");
    }

    public long hQ() {
        return getLong("price_micros");
    }

    public String hR() {
        return getString("formatted_price");
    }

    public long hS() {
        return getLong("full_price_micros");
    }

    public String hT() {
        return getString("formatted_full_price");
    }

    public SnapshotMetadata hU() {
        return this.RC;
    }

    /* renamed from: hW */
    public ExtendedGame freeze() {
        return new ExtendedGameEntity(this);
    }

    public int hashCode() {
        return ExtendedGameEntity.a(this);
    }

    public String toString() {
        return ExtendedGameEntity.b((ExtendedGame) this);
    }

    public void writeToParcel(Parcel dest, int flags) {
        ((ExtendedGameEntity) freeze()).writeToParcel(dest, flags);
    }
}
