package com.google.android.gms.games.multiplayer;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerEntity;
import com.google.android.gms.games.internal.GamesDowngradeableSafeParcel;
import com.google.android.gms.internal.hl;
import com.google.android.gms.internal.il;

public final class ParticipantEntity extends GamesDowngradeableSafeParcel implements Participant {
    public static final Parcelable.Creator<ParticipantEntity> CREATOR = new ParticipantEntityCreatorCompat();
    private final int Am;
    private final String Lk;
    private final String MA;
    private final Uri Mo;
    private final Uri Mp;
    private final String Mz;
    private final PlayerEntity Ng;
    private final String Nk;
    private final String On;
    private final int SY;
    private final boolean SZ;
    private final ParticipantResult Ta;
    private final int xJ;

    static final class ParticipantEntityCreatorCompat extends ParticipantEntityCreator {
        ParticipantEntityCreatorCompat() {
        }

        /* renamed from: bm */
        public ParticipantEntity createFromParcel(Parcel parcel) {
            boolean z = true;
            if (ParticipantEntity.c(ParticipantEntity.fl()) || ParticipantEntity.aA(ParticipantEntity.class.getCanonicalName())) {
                return super.createFromParcel(parcel);
            }
            String readString = parcel.readString();
            String readString2 = parcel.readString();
            String readString3 = parcel.readString();
            Uri parse = readString3 == null ? null : Uri.parse(readString3);
            String readString4 = parcel.readString();
            Uri parse2 = readString4 == null ? null : Uri.parse(readString4);
            int readInt = parcel.readInt();
            String readString5 = parcel.readString();
            boolean z2 = parcel.readInt() > 0;
            if (parcel.readInt() <= 0) {
                z = false;
            }
            return new ParticipantEntity(3, readString, readString2, parse, parse2, readInt, readString5, z2, z ? PlayerEntity.CREATOR.createFromParcel(parcel) : null, 7, (ParticipantResult) null, (String) null, (String) null);
        }
    }

    ParticipantEntity(int versionCode, String participantId, String displayName, Uri iconImageUri, Uri hiResImageUri, int status, String clientAddress, boolean connectedToRoom, PlayerEntity player, int capabilities, ParticipantResult result, String iconImageUrl, String hiResImageUrl) {
        this.xJ = versionCode;
        this.On = participantId;
        this.Lk = displayName;
        this.Mo = iconImageUri;
        this.Mp = hiResImageUri;
        this.SY = status;
        this.Nk = clientAddress;
        this.SZ = connectedToRoom;
        this.Ng = player;
        this.Am = capabilities;
        this.Ta = result;
        this.Mz = iconImageUrl;
        this.MA = hiResImageUrl;
    }

    public ParticipantEntity(Participant participant) {
        this.xJ = 3;
        this.On = participant.getParticipantId();
        this.Lk = participant.getDisplayName();
        this.Mo = participant.getIconImageUri();
        this.Mp = participant.getHiResImageUri();
        this.SY = participant.getStatus();
        this.Nk = participant.gR();
        this.SZ = participant.isConnectedToRoom();
        Player player = participant.getPlayer();
        this.Ng = player == null ? null : new PlayerEntity(player);
        this.Am = participant.getCapabilities();
        this.Ta = participant.getResult();
        this.Mz = participant.getIconImageUrl();
        this.MA = participant.getHiResImageUrl();
    }

    static int a(Participant participant) {
        return hl.hashCode(participant.getPlayer(), Integer.valueOf(participant.getStatus()), participant.gR(), Boolean.valueOf(participant.isConnectedToRoom()), participant.getDisplayName(), participant.getIconImageUri(), participant.getHiResImageUri(), Integer.valueOf(participant.getCapabilities()), participant.getResult(), participant.getParticipantId());
    }

    static boolean a(Participant participant, Object obj) {
        if (!(obj instanceof Participant)) {
            return false;
        }
        if (participant == obj) {
            return true;
        }
        Participant participant2 = (Participant) obj;
        return hl.equal(participant2.getPlayer(), participant.getPlayer()) && hl.equal(Integer.valueOf(participant2.getStatus()), Integer.valueOf(participant.getStatus())) && hl.equal(participant2.gR(), participant.gR()) && hl.equal(Boolean.valueOf(participant2.isConnectedToRoom()), Boolean.valueOf(participant.isConnectedToRoom())) && hl.equal(participant2.getDisplayName(), participant.getDisplayName()) && hl.equal(participant2.getIconImageUri(), participant.getIconImageUri()) && hl.equal(participant2.getHiResImageUri(), participant.getHiResImageUri()) && hl.equal(Integer.valueOf(participant2.getCapabilities()), Integer.valueOf(participant.getCapabilities())) && hl.equal(participant2.getResult(), participant.getResult()) && hl.equal(participant2.getParticipantId(), participant.getParticipantId());
    }

    static String b(Participant participant) {
        return hl.e(participant).a("ParticipantId", participant.getParticipantId()).a("Player", participant.getPlayer()).a("Status", Integer.valueOf(participant.getStatus())).a("ClientAddress", participant.gR()).a("ConnectedToRoom", Boolean.valueOf(participant.isConnectedToRoom())).a("DisplayName", participant.getDisplayName()).a("IconImage", participant.getIconImageUri()).a("IconImageUrl", participant.getIconImageUrl()).a("HiResImage", participant.getHiResImageUri()).a("HiResImageUrl", participant.getHiResImageUrl()).a("Capabilities", Integer.valueOf(participant.getCapabilities())).a("Result", participant.getResult()).toString();
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        return a(this, obj);
    }

    public Participant freeze() {
        return this;
    }

    public String gR() {
        return this.Nk;
    }

    public int getCapabilities() {
        return this.Am;
    }

    public String getDisplayName() {
        return this.Ng == null ? this.Lk : this.Ng.getDisplayName();
    }

    public void getDisplayName(CharArrayBuffer dataOut) {
        if (this.Ng == null) {
            il.b(this.Lk, dataOut);
        } else {
            this.Ng.getDisplayName(dataOut);
        }
    }

    public Uri getHiResImageUri() {
        return this.Ng == null ? this.Mp : this.Ng.getHiResImageUri();
    }

    public String getHiResImageUrl() {
        return this.Ng == null ? this.MA : this.Ng.getHiResImageUrl();
    }

    public Uri getIconImageUri() {
        return this.Ng == null ? this.Mo : this.Ng.getIconImageUri();
    }

    public String getIconImageUrl() {
        return this.Ng == null ? this.Mz : this.Ng.getIconImageUrl();
    }

    public String getParticipantId() {
        return this.On;
    }

    public Player getPlayer() {
        return this.Ng;
    }

    public ParticipantResult getResult() {
        return this.Ta;
    }

    public int getStatus() {
        return this.SY;
    }

    public int getVersionCode() {
        return this.xJ;
    }

    public int hashCode() {
        return a(this);
    }

    public boolean isConnectedToRoom() {
        return this.SZ;
    }

    public boolean isDataValid() {
        return true;
    }

    public String toString() {
        return b((Participant) this);
    }

    public void writeToParcel(Parcel dest, int flags) {
        String str = null;
        int i = 0;
        if (!fm()) {
            ParticipantEntityCreator.a(this, dest, flags);
            return;
        }
        dest.writeString(this.On);
        dest.writeString(this.Lk);
        dest.writeString(this.Mo == null ? null : this.Mo.toString());
        if (this.Mp != null) {
            str = this.Mp.toString();
        }
        dest.writeString(str);
        dest.writeInt(this.SY);
        dest.writeString(this.Nk);
        dest.writeInt(this.SZ ? 1 : 0);
        if (this.Ng != null) {
            i = 1;
        }
        dest.writeInt(i);
        if (this.Ng != null) {
            this.Ng.writeToParcel(dest, flags);
        }
    }
}
