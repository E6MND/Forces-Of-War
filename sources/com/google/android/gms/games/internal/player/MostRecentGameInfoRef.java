package com.google.android.gms.games.internal.player;

import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.d;

public final class MostRecentGameInfoRef extends d implements MostRecentGameInfo {
    private final PlayerColumnNames Nd;

    public MostRecentGameInfoRef(DataHolder holder, int dataRow, PlayerColumnNames columnNames) {
        super(holder, dataRow);
        this.Nd = columnNames;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        return MostRecentGameInfoEntity.a(this, obj);
    }

    public int hashCode() {
        return MostRecentGameInfoEntity.a(this);
    }

    public String ik() {
        return getString(this.Nd.Sd);
    }

    public String il() {
        return getString(this.Nd.Se);
    }

    public long im() {
        return getLong(this.Nd.Sf);
    }

    public Uri in() {
        return aw(this.Nd.Sg);
    }

    public Uri io() {
        return aw(this.Nd.Sh);
    }

    public Uri ip() {
        return aw(this.Nd.Si);
    }

    /* renamed from: iq */
    public MostRecentGameInfo freeze() {
        return new MostRecentGameInfoEntity(this);
    }

    public String toString() {
        return MostRecentGameInfoEntity.b(this);
    }

    public void writeToParcel(Parcel dest, int flags) {
        ((MostRecentGameInfoEntity) freeze()).writeToParcel(dest, flags);
    }
}
