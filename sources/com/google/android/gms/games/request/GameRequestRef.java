package com.google.android.gms.games.request;

import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.d;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameRef;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerRef;
import java.util.ArrayList;
import java.util.List;
import org.xwalk.core.internal.extension.api.messaging.MessagingSmsConsts;

public final class GameRequestRef extends d implements GameRequest {
    private final int RD;

    public GameRequestRef(DataHolder holder, int dataRow, int numChildren) {
        super(holder, dataRow);
        this.RD = numChildren;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        return GameRequestEntity.a(this, obj);
    }

    public GameRequest freeze() {
        return new GameRequestEntity(this);
    }

    public long getCreationTimestamp() {
        return getLong("creation_timestamp");
    }

    public byte[] getData() {
        return getByteArray("data");
    }

    public long getExpirationTimestamp() {
        return getLong("expiration_timestamp");
    }

    public Game getGame() {
        return new GameRef(this.DD, this.Ez);
    }

    public int getRecipientStatus(String playerId) {
        for (int i = this.Ez; i < this.Ez + this.RD; i++) {
            int ae = this.DD.ae(i);
            if (this.DD.c("recipient_external_player_id", i, ae).equals(playerId)) {
                return this.DD.b("recipient_status", i, ae);
            }
        }
        return -1;
    }

    public List<Player> getRecipients() {
        ArrayList arrayList = new ArrayList(this.RD);
        for (int i = 0; i < this.RD; i++) {
            arrayList.add(new PlayerRef(this.DD, this.Ez + i, "recipient_"));
        }
        return arrayList;
    }

    public String getRequestId() {
        return getString("external_request_id");
    }

    public Player getSender() {
        return new PlayerRef(this.DD, eQ(), "sender_");
    }

    public int getStatus() {
        return getInteger(MessagingSmsConsts.STATUS);
    }

    public int getType() {
        return getInteger(MessagingSmsConsts.TYPE);
    }

    public int hashCode() {
        return GameRequestEntity.a(this);
    }

    public boolean isConsumed(String playerId) {
        return getRecipientStatus(playerId) == 1;
    }

    public String toString() {
        return GameRequestEntity.c(this);
    }

    public void writeToParcel(Parcel dest, int flags) {
        ((GameRequestEntity) freeze()).writeToParcel(dest, flags);
    }
}
