package com.google.android.gms.games.request;

import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerEntity;
import com.google.android.gms.internal.hl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class GameRequestEntity implements SafeParcelable, GameRequest {
    public static final GameRequestEntityCreator CREATOR = new GameRequestEntityCreator();
    private final int AQ;
    private final String Oy;
    private final GameEntity Rq;
    private final long SR;
    private final int SY;
    private final byte[] TC;
    private final PlayerEntity TX;
    private final ArrayList<PlayerEntity> TY;
    private final long TZ;
    private final Bundle Ua;
    private final int xJ;

    GameRequestEntity(int versionCode, GameEntity game, PlayerEntity sender, byte[] data, String requestId, ArrayList<PlayerEntity> recipients, int type, long creationTimestamp, long expirationTimestamp, Bundle recipientStatuses, int status) {
        this.xJ = versionCode;
        this.Rq = game;
        this.TX = sender;
        this.TC = data;
        this.Oy = requestId;
        this.TY = recipients;
        this.AQ = type;
        this.SR = creationTimestamp;
        this.TZ = expirationTimestamp;
        this.Ua = recipientStatuses;
        this.SY = status;
    }

    public GameRequestEntity(GameRequest request) {
        this.xJ = 2;
        this.Rq = new GameEntity(request.getGame());
        this.TX = new PlayerEntity(request.getSender());
        this.Oy = request.getRequestId();
        this.AQ = request.getType();
        this.SR = request.getCreationTimestamp();
        this.TZ = request.getExpirationTimestamp();
        this.SY = request.getStatus();
        byte[] data = request.getData();
        if (data == null) {
            this.TC = null;
        } else {
            this.TC = new byte[data.length];
            System.arraycopy(data, 0, this.TC, 0, data.length);
        }
        List<Player> recipients = request.getRecipients();
        int size = recipients.size();
        this.TY = new ArrayList<>(size);
        this.Ua = new Bundle();
        for (int i = 0; i < size; i++) {
            Player player = (Player) recipients.get(i).freeze();
            String playerId = player.getPlayerId();
            this.TY.add((PlayerEntity) player);
            this.Ua.putInt(playerId, request.getRecipientStatus(playerId));
        }
    }

    static int a(GameRequest gameRequest) {
        return hl.hashCode(gameRequest.getGame(), gameRequest.getRecipients(), gameRequest.getRequestId(), gameRequest.getSender(), b(gameRequest), Integer.valueOf(gameRequest.getType()), Long.valueOf(gameRequest.getCreationTimestamp()), Long.valueOf(gameRequest.getExpirationTimestamp()));
    }

    static boolean a(GameRequest gameRequest, Object obj) {
        if (!(obj instanceof GameRequest)) {
            return false;
        }
        if (gameRequest == obj) {
            return true;
        }
        GameRequest gameRequest2 = (GameRequest) obj;
        return hl.equal(gameRequest2.getGame(), gameRequest.getGame()) && hl.equal(gameRequest2.getRecipients(), gameRequest.getRecipients()) && hl.equal(gameRequest2.getRequestId(), gameRequest.getRequestId()) && hl.equal(gameRequest2.getSender(), gameRequest.getSender()) && Arrays.equals(b(gameRequest2), b(gameRequest)) && hl.equal(Integer.valueOf(gameRequest2.getType()), Integer.valueOf(gameRequest.getType())) && hl.equal(Long.valueOf(gameRequest2.getCreationTimestamp()), Long.valueOf(gameRequest.getCreationTimestamp())) && hl.equal(Long.valueOf(gameRequest2.getExpirationTimestamp()), Long.valueOf(gameRequest.getExpirationTimestamp()));
    }

    private static int[] b(GameRequest gameRequest) {
        List<Player> recipients = gameRequest.getRecipients();
        int size = recipients.size();
        int[] iArr = new int[size];
        for (int i = 0; i < size; i++) {
            iArr[i] = gameRequest.getRecipientStatus(recipients.get(i).getPlayerId());
        }
        return iArr;
    }

    static String c(GameRequest gameRequest) {
        return hl.e(gameRequest).a("Game", gameRequest.getGame()).a("Sender", gameRequest.getSender()).a("Recipients", gameRequest.getRecipients()).a("Data", gameRequest.getData()).a("RequestId", gameRequest.getRequestId()).a("Type", Integer.valueOf(gameRequest.getType())).a("CreationTimestamp", Long.valueOf(gameRequest.getCreationTimestamp())).a("ExpirationTimestamp", Long.valueOf(gameRequest.getExpirationTimestamp())).toString();
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        return a(this, obj);
    }

    public GameRequest freeze() {
        return this;
    }

    public long getCreationTimestamp() {
        return this.SR;
    }

    public byte[] getData() {
        return this.TC;
    }

    public long getExpirationTimestamp() {
        return this.TZ;
    }

    public Game getGame() {
        return this.Rq;
    }

    public int getRecipientStatus(String playerId) {
        return this.Ua.getInt(playerId, 0);
    }

    public List<Player> getRecipients() {
        return new ArrayList(this.TY);
    }

    public String getRequestId() {
        return this.Oy;
    }

    public Player getSender() {
        return this.TX;
    }

    public int getStatus() {
        return this.SY;
    }

    public int getType() {
        return this.AQ;
    }

    public int getVersionCode() {
        return this.xJ;
    }

    public int hashCode() {
        return a(this);
    }

    public Bundle iG() {
        return this.Ua;
    }

    public boolean isConsumed(String playerId) {
        return getRecipientStatus(playerId) == 1;
    }

    public boolean isDataValid() {
        return true;
    }

    public String toString() {
        return c(this);
    }

    public void writeToParcel(Parcel dest, int flags) {
        GameRequestEntityCreator.a(this, dest, flags);
    }
}
