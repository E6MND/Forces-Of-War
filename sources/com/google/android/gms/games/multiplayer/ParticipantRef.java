package com.google.android.gms.games.multiplayer;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.d;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerRef;

public final class ParticipantRef extends d implements Participant {
    private final PlayerRef Tb;

    public ParticipantRef(DataHolder holder, int dataRow) {
        super(holder, dataRow);
        this.Tb = new PlayerRef(holder, dataRow);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        return ParticipantEntity.a(this, obj);
    }

    public Participant freeze() {
        return new ParticipantEntity(this);
    }

    public String gR() {
        return getString("client_address");
    }

    public int getCapabilities() {
        return getInteger("capabilities");
    }

    public String getDisplayName() {
        return ax("external_player_id") ? getString("default_display_name") : this.Tb.getDisplayName();
    }

    public void getDisplayName(CharArrayBuffer dataOut) {
        if (ax("external_player_id")) {
            a("default_display_name", dataOut);
        } else {
            this.Tb.getDisplayName(dataOut);
        }
    }

    public Uri getHiResImageUri() {
        return ax("external_player_id") ? aw("default_display_hi_res_image_uri") : this.Tb.getHiResImageUri();
    }

    public String getHiResImageUrl() {
        return ax("external_player_id") ? getString("default_display_hi_res_image_url") : this.Tb.getHiResImageUrl();
    }

    public Uri getIconImageUri() {
        return ax("external_player_id") ? aw("default_display_image_uri") : this.Tb.getIconImageUri();
    }

    public String getIconImageUrl() {
        return ax("external_player_id") ? getString("default_display_image_url") : this.Tb.getIconImageUrl();
    }

    public String getParticipantId() {
        return getString("external_participant_id");
    }

    public Player getPlayer() {
        if (ax("external_player_id")) {
            return null;
        }
        return this.Tb;
    }

    public ParticipantResult getResult() {
        if (ax("result_type")) {
            return null;
        }
        return new ParticipantResult(getParticipantId(), getInteger("result_type"), getInteger("placing"));
    }

    public int getStatus() {
        return getInteger("player_status");
    }

    public int hashCode() {
        return ParticipantEntity.a(this);
    }

    public boolean isConnectedToRoom() {
        return getInteger("connected") > 0;
    }

    public String toString() {
        return ParticipantEntity.b((Participant) this);
    }

    public void writeToParcel(Parcel dest, int flags) {
        ((ParticipantEntity) freeze()).writeToParcel(dest, flags);
    }
}
