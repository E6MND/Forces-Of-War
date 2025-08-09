package com.google.android.gms.games.multiplayer.turnbased;

import android.os.Bundle;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.internal.constants.TurnBasedMatchTurnStatus;
import com.google.android.gms.games.multiplayer.InvitationBuffer;

public final class LoadMatchesResponse {
    private final InvitationBuffer Ts;
    private final TurnBasedMatchBuffer Tt;
    private final TurnBasedMatchBuffer Tu;
    private final TurnBasedMatchBuffer Tv;

    public LoadMatchesResponse(Bundle matchData) {
        DataHolder a = a(matchData, 0);
        if (a != null) {
            this.Ts = new InvitationBuffer(a);
        } else {
            this.Ts = null;
        }
        DataHolder a2 = a(matchData, 1);
        if (a2 != null) {
            this.Tt = new TurnBasedMatchBuffer(a2);
        } else {
            this.Tt = null;
        }
        DataHolder a3 = a(matchData, 2);
        if (a3 != null) {
            this.Tu = new TurnBasedMatchBuffer(a3);
        } else {
            this.Tu = null;
        }
        DataHolder a4 = a(matchData, 3);
        if (a4 != null) {
            this.Tv = new TurnBasedMatchBuffer(a4);
        } else {
            this.Tv = null;
        }
    }

    private static DataHolder a(Bundle bundle, int i) {
        String cm = TurnBasedMatchTurnStatus.cm(i);
        if (!bundle.containsKey(cm)) {
            return null;
        }
        return (DataHolder) bundle.getParcelable(cm);
    }

    public void close() {
        if (this.Ts != null) {
            this.Ts.close();
        }
        if (this.Tt != null) {
            this.Tt.close();
        }
        if (this.Tu != null) {
            this.Tu.close();
        }
        if (this.Tv != null) {
            this.Tv.close();
        }
    }

    public TurnBasedMatchBuffer getCompletedMatches() {
        return this.Tv;
    }

    public InvitationBuffer getInvitations() {
        return this.Ts;
    }

    public TurnBasedMatchBuffer getMyTurnMatches() {
        return this.Tt;
    }

    public TurnBasedMatchBuffer getTheirTurnMatches() {
        return this.Tu;
    }

    public boolean hasData() {
        if (this.Ts != null && this.Ts.getCount() > 0) {
            return true;
        }
        if (this.Tt != null && this.Tt.getCount() > 0) {
            return true;
        }
        if (this.Tu == null || this.Tu.getCount() <= 0) {
            return this.Tv != null && this.Tv.getCount() > 0;
        }
        return true;
    }
}
