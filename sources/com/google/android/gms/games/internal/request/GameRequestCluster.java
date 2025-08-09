package com.google.android.gms.games.internal.request;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.request.GameRequest;
import com.google.android.gms.games.request.GameRequestEntity;
import com.google.android.gms.internal.gy;
import com.google.android.gms.internal.hl;
import java.util.ArrayList;

public final class GameRequestCluster implements SafeParcelable, GameRequest {
    public static final GameRequestClusterCreator CREATOR = new GameRequestClusterCreator();
    private final ArrayList<GameRequestEntity> Sj;
    private final int xJ;

    GameRequestCluster(int versionCode, ArrayList<GameRequestEntity> requestList) {
        this.xJ = versionCode;
        this.Sj = requestList;
        id();
    }

    private void id() {
        gy.A(!this.Sj.isEmpty());
        GameRequest gameRequest = this.Sj.get(0);
        int size = this.Sj.size();
        for (int i = 1; i < size; i++) {
            GameRequest gameRequest2 = this.Sj.get(i);
            gy.a(gameRequest.getType() == gameRequest2.getType(), "All the requests must be of the same type");
            gy.a(gameRequest.getSender().equals(gameRequest2.getSender()), "All the requests must be from the same sender");
        }
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof GameRequestCluster)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        GameRequestCluster gameRequestCluster = (GameRequestCluster) obj;
        if (gameRequestCluster.Sj.size() != this.Sj.size()) {
            return false;
        }
        int size = this.Sj.size();
        for (int i = 0; i < size; i++) {
            if (!this.Sj.get(i).equals(gameRequestCluster.Sj.get(i))) {
                return false;
            }
        }
        return true;
    }

    public GameRequest freeze() {
        return this;
    }

    public long getCreationTimestamp() {
        throw new UnsupportedOperationException("Method not supported on a cluster");
    }

    public byte[] getData() {
        throw new UnsupportedOperationException("Method not supported on a cluster");
    }

    public long getExpirationTimestamp() {
        throw new UnsupportedOperationException("Method not supported on a cluster");
    }

    public Game getGame() {
        throw new UnsupportedOperationException("Method not supported on a cluster");
    }

    public int getRecipientStatus(String playerId) {
        throw new UnsupportedOperationException("Method not supported on a cluster");
    }

    public String getRequestId() {
        return this.Sj.get(0).getRequestId();
    }

    public Player getSender() {
        return this.Sj.get(0).getSender();
    }

    public int getStatus() {
        throw new UnsupportedOperationException("Method not supported on a cluster");
    }

    public int getType() {
        return this.Sj.get(0).getType();
    }

    public int getVersionCode() {
        return this.xJ;
    }

    public int hashCode() {
        return hl.hashCode(this.Sj.toArray());
    }

    public ArrayList<GameRequest> ir() {
        return new ArrayList<>(this.Sj);
    }

    /* renamed from: is */
    public ArrayList<Player> getRecipients() {
        throw new UnsupportedOperationException("Method not supported on a cluster");
    }

    public boolean isConsumed(String playerId) {
        throw new UnsupportedOperationException("Method not supported on a cluster");
    }

    public boolean isDataValid() {
        return true;
    }

    public void writeToParcel(Parcel dest, int flags) {
        GameRequestClusterCreator.a(this, dest, flags);
    }
}
