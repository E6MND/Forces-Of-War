package com.google.android.gms.games.multiplayer.turnbased;

import android.os.Bundle;
import com.google.android.gms.games.multiplayer.Multiplayer;
import com.google.android.gms.internal.hn;
import java.util.ArrayList;

public final class TurnBasedMatchConfig {
    private final int SV;
    private final String[] Tk;
    private final Bundle Tl;
    private final int Tw;

    public static final class Builder {
        int SV;
        Bundle Tl;
        ArrayList<String> To;
        int Tw;

        private Builder() {
            this.SV = -1;
            this.To = new ArrayList<>();
            this.Tl = null;
            this.Tw = 2;
        }

        public Builder addInvitedPlayer(String playerId) {
            hn.f(playerId);
            this.To.add(playerId);
            return this;
        }

        public Builder addInvitedPlayers(ArrayList<String> playerIds) {
            hn.f(playerIds);
            this.To.addAll(playerIds);
            return this;
        }

        public TurnBasedMatchConfig build() {
            return new TurnBasedMatchConfig(this);
        }

        public Builder setAutoMatchCriteria(Bundle autoMatchCriteria) {
            this.Tl = autoMatchCriteria;
            return this;
        }

        public Builder setVariant(int variant) {
            hn.b(variant == -1 || variant > 0, (Object) "Variant must be a positive integer or TurnBasedMatch.MATCH_VARIANT_ANY");
            this.SV = variant;
            return this;
        }
    }

    private TurnBasedMatchConfig(Builder builder) {
        this.SV = builder.SV;
        this.Tw = builder.Tw;
        this.Tl = builder.Tl;
        this.Tk = (String[]) builder.To.toArray(new String[builder.To.size()]);
    }

    public static Builder builder() {
        return new Builder();
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

    public String[] getInvitedPlayerIds() {
        return this.Tk;
    }

    public int getVariant() {
        return this.SV;
    }

    public int iC() {
        return this.Tw;
    }
}
