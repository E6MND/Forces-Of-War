package com.google.android.gms.games.multiplayer.realtime;

import android.os.Bundle;
import com.google.android.gms.games.multiplayer.Multiplayer;
import com.google.android.gms.internal.hn;
import java.util.ArrayList;
import java.util.Arrays;

public final class RoomConfig {
    private final String NN;
    private final int SV;
    private final RoomUpdateListener Th;
    private final RoomStatusUpdateListener Ti;
    private final RealTimeMessageReceivedListener Tj;
    private final String[] Tk;
    private final Bundle Tl;
    private final boolean Tm;

    public static final class Builder {
        int SV;
        final RoomUpdateListener Th;
        RoomStatusUpdateListener Ti;
        RealTimeMessageReceivedListener Tj;
        Bundle Tl;
        boolean Tm;
        String Tn;
        ArrayList<String> To;

        private Builder(RoomUpdateListener updateListener) {
            this.Tn = null;
            this.SV = -1;
            this.To = new ArrayList<>();
            this.Tm = false;
            this.Th = (RoomUpdateListener) hn.b(updateListener, (Object) "Must provide a RoomUpdateListener");
        }

        public Builder addPlayersToInvite(ArrayList<String> playerIds) {
            hn.f(playerIds);
            this.To.addAll(playerIds);
            return this;
        }

        public Builder addPlayersToInvite(String... playerIds) {
            hn.f(playerIds);
            this.To.addAll(Arrays.asList(playerIds));
            return this;
        }

        public RoomConfig build() {
            return new RoomConfig(this);
        }

        public Builder setAutoMatchCriteria(Bundle autoMatchCriteria) {
            this.Tl = autoMatchCriteria;
            return this;
        }

        public Builder setInvitationIdToAccept(String invitationId) {
            hn.f(invitationId);
            this.Tn = invitationId;
            return this;
        }

        public Builder setMessageReceivedListener(RealTimeMessageReceivedListener listener) {
            this.Tj = listener;
            return this;
        }

        public Builder setRoomStatusUpdateListener(RoomStatusUpdateListener listener) {
            this.Ti = listener;
            return this;
        }

        public Builder setSocketCommunicationEnabled(boolean enableSockets) {
            this.Tm = enableSockets;
            return this;
        }

        public Builder setVariant(int variant) {
            hn.b(variant == -1 || variant > 0, (Object) "Variant must be a positive integer or Room.ROOM_VARIANT_ANY");
            this.SV = variant;
            return this;
        }
    }

    private RoomConfig(Builder builder) {
        this.Th = builder.Th;
        this.Ti = builder.Ti;
        this.Tj = builder.Tj;
        this.NN = builder.Tn;
        this.SV = builder.SV;
        this.Tl = builder.Tl;
        this.Tm = builder.Tm;
        this.Tk = (String[]) builder.To.toArray(new String[builder.To.size()]);
        if (this.Tj == null) {
            hn.a(this.Tm, "Must either enable sockets OR specify a message listener");
        }
    }

    public static Builder builder(RoomUpdateListener listener) {
        return new Builder(listener);
    }

    public static Bundle createAutoMatchCriteria(int minAutoMatchPlayers, int maxAutoMatchPlayers, long exclusiveBitMask) {
        Bundle bundle = new Bundle();
        bundle.putInt(Multiplayer.EXTRA_MIN_AUTOMATCH_PLAYERS, minAutoMatchPlayers);
        bundle.putInt(Multiplayer.EXTRA_MAX_AUTOMATCH_PLAYERS, maxAutoMatchPlayers);
        bundle.putLong(Multiplayer.EXTRA_EXCLUSIVE_BIT_MASK, exclusiveBitMask);
        return bundle;
    }

    public Bundle getAutoMatchCriteria() {
        return this.Tl;
    }

    public String getInvitationId() {
        return this.NN;
    }

    public String[] getInvitedPlayerIds() {
        return this.Tk;
    }

    public RealTimeMessageReceivedListener getMessageReceivedListener() {
        return this.Tj;
    }

    public RoomStatusUpdateListener getRoomStatusUpdateListener() {
        return this.Ti;
    }

    public RoomUpdateListener getRoomUpdateListener() {
        return this.Th;
    }

    public int getVariant() {
        return this.SV;
    }

    public boolean isSocketEnabled() {
        return this.Tm;
    }
}
