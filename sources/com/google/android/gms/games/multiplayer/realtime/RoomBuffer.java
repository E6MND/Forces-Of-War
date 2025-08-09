package com.google.android.gms.games.multiplayer.realtime;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.g;

public final class RoomBuffer extends g<Room> {
    public RoomBuffer(DataHolder dataHolder) {
        super(dataHolder);
    }

    /* access modifiers changed from: protected */
    public String eU() {
        return "external_match_id";
    }

    /* access modifiers changed from: protected */
    /* renamed from: h */
    public Room c(int i, int i2) {
        return new RoomRef(this.DD, i, i2);
    }
}
