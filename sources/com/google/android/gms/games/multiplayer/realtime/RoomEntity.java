package com.google.android.gms.games.multiplayer.realtime;

import android.database.CharArrayBuffer;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.internal.GamesDowngradeableSafeParcel;
import com.google.android.gms.games.multiplayer.Participant;
import com.google.android.gms.games.multiplayer.ParticipantEntity;
import com.google.android.gms.internal.hl;
import com.google.android.gms.internal.il;
import java.util.ArrayList;

public final class RoomEntity extends GamesDowngradeableSafeParcel implements Room {
    public static final Parcelable.Creator<RoomEntity> CREATOR = new RoomEntityCreatorCompat();
    private final String Mm;
    private final String NP;
    private final long SR;
    private final ArrayList<ParticipantEntity> SU;
    private final int SV;
    private final Bundle Tl;
    private final String Tp;
    private final int Tq;
    private final int Tr;
    private final int xJ;

    static final class RoomEntityCreatorCompat extends RoomEntityCreator {
        RoomEntityCreatorCompat() {
        }

        /* renamed from: bo */
        public RoomEntity createFromParcel(Parcel parcel) {
            if (RoomEntity.c(RoomEntity.fl()) || RoomEntity.aA(RoomEntity.class.getCanonicalName())) {
                return super.createFromParcel(parcel);
            }
            String readString = parcel.readString();
            String readString2 = parcel.readString();
            long readLong = parcel.readLong();
            int readInt = parcel.readInt();
            String readString3 = parcel.readString();
            int readInt2 = parcel.readInt();
            Bundle readBundle = parcel.readBundle();
            int readInt3 = parcel.readInt();
            ArrayList arrayList = new ArrayList(readInt3);
            for (int i = 0; i < readInt3; i++) {
                arrayList.add(ParticipantEntity.CREATOR.createFromParcel(parcel));
            }
            return new RoomEntity(2, readString, readString2, readLong, readInt, readString3, readInt2, readBundle, arrayList, -1);
        }
    }

    RoomEntity(int versionCode, String roomId, String creatorId, long creationTimestamp, int roomStatus, String description, int variant, Bundle autoMatchCriteria, ArrayList<ParticipantEntity> participants, int autoMatchWaitEstimateSeconds) {
        this.xJ = versionCode;
        this.NP = roomId;
        this.Tp = creatorId;
        this.SR = creationTimestamp;
        this.Tq = roomStatus;
        this.Mm = description;
        this.SV = variant;
        this.Tl = autoMatchCriteria;
        this.SU = participants;
        this.Tr = autoMatchWaitEstimateSeconds;
    }

    public RoomEntity(Room room) {
        this.xJ = 2;
        this.NP = room.getRoomId();
        this.Tp = room.getCreatorId();
        this.SR = room.getCreationTimestamp();
        this.Tq = room.getStatus();
        this.Mm = room.getDescription();
        this.SV = room.getVariant();
        this.Tl = room.getAutoMatchCriteria();
        ArrayList<Participant> participants = room.getParticipants();
        int size = participants.size();
        this.SU = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            this.SU.add((ParticipantEntity) participants.get(i).freeze());
        }
        this.Tr = room.getAutoMatchWaitEstimateSeconds();
    }

    static int a(Room room) {
        return hl.hashCode(room.getRoomId(), room.getCreatorId(), Long.valueOf(room.getCreationTimestamp()), Integer.valueOf(room.getStatus()), room.getDescription(), Integer.valueOf(room.getVariant()), room.getAutoMatchCriteria(), room.getParticipants(), Integer.valueOf(room.getAutoMatchWaitEstimateSeconds()));
    }

    static int a(Room room, String str) {
        ArrayList<Participant> participants = room.getParticipants();
        int size = participants.size();
        for (int i = 0; i < size; i++) {
            Participant participant = participants.get(i);
            if (participant.getParticipantId().equals(str)) {
                return participant.getStatus();
            }
        }
        throw new IllegalStateException("Participant " + str + " is not in room " + room.getRoomId());
    }

    static boolean a(Room room, Object obj) {
        if (!(obj instanceof Room)) {
            return false;
        }
        if (room == obj) {
            return true;
        }
        Room room2 = (Room) obj;
        return hl.equal(room2.getRoomId(), room.getRoomId()) && hl.equal(room2.getCreatorId(), room.getCreatorId()) && hl.equal(Long.valueOf(room2.getCreationTimestamp()), Long.valueOf(room.getCreationTimestamp())) && hl.equal(Integer.valueOf(room2.getStatus()), Integer.valueOf(room.getStatus())) && hl.equal(room2.getDescription(), room.getDescription()) && hl.equal(Integer.valueOf(room2.getVariant()), Integer.valueOf(room.getVariant())) && hl.equal(room2.getAutoMatchCriteria(), room.getAutoMatchCriteria()) && hl.equal(room2.getParticipants(), room.getParticipants()) && hl.equal(Integer.valueOf(room2.getAutoMatchWaitEstimateSeconds()), Integer.valueOf(room.getAutoMatchWaitEstimateSeconds()));
    }

    static String b(Room room) {
        return hl.e(room).a("RoomId", room.getRoomId()).a("CreatorId", room.getCreatorId()).a("CreationTimestamp", Long.valueOf(room.getCreationTimestamp())).a("RoomStatus", Integer.valueOf(room.getStatus())).a("Description", room.getDescription()).a("Variant", Integer.valueOf(room.getVariant())).a("AutoMatchCriteria", room.getAutoMatchCriteria()).a("Participants", room.getParticipants()).a("AutoMatchWaitEstimateSeconds", Integer.valueOf(room.getAutoMatchWaitEstimateSeconds())).toString();
    }

    static String b(Room room, String str) {
        ArrayList<Participant> participants = room.getParticipants();
        int size = participants.size();
        for (int i = 0; i < size; i++) {
            Participant participant = participants.get(i);
            Player player = participant.getPlayer();
            if (player != null && player.getPlayerId().equals(str)) {
                return participant.getParticipantId();
            }
        }
        return null;
    }

    static Participant c(Room room, String str) {
        ArrayList<Participant> participants = room.getParticipants();
        int size = participants.size();
        for (int i = 0; i < size; i++) {
            Participant participant = participants.get(i);
            if (participant.getParticipantId().equals(str)) {
                return participant;
            }
        }
        throw new IllegalStateException("Participant " + str + " is not in match " + room.getRoomId());
    }

    static ArrayList<String> c(Room room) {
        ArrayList<Participant> participants = room.getParticipants();
        int size = participants.size();
        ArrayList<String> arrayList = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            arrayList.add(participants.get(i).getParticipantId());
        }
        return arrayList;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        return a((Room) this, obj);
    }

    public Room freeze() {
        return this;
    }

    public Bundle getAutoMatchCriteria() {
        return this.Tl;
    }

    public int getAutoMatchWaitEstimateSeconds() {
        return this.Tr;
    }

    public long getCreationTimestamp() {
        return this.SR;
    }

    public String getCreatorId() {
        return this.Tp;
    }

    public String getDescription() {
        return this.Mm;
    }

    public void getDescription(CharArrayBuffer dataOut) {
        il.b(this.Mm, dataOut);
    }

    public Participant getParticipant(String participantId) {
        return c(this, participantId);
    }

    public String getParticipantId(String playerId) {
        return b(this, playerId);
    }

    public ArrayList<String> getParticipantIds() {
        return c(this);
    }

    public int getParticipantStatus(String participantId) {
        return a((Room) this, participantId);
    }

    public ArrayList<Participant> getParticipants() {
        return new ArrayList<>(this.SU);
    }

    public String getRoomId() {
        return this.NP;
    }

    public int getStatus() {
        return this.Tq;
    }

    public int getVariant() {
        return this.SV;
    }

    public int getVersionCode() {
        return this.xJ;
    }

    public int hashCode() {
        return a(this);
    }

    public boolean isDataValid() {
        return true;
    }

    public String toString() {
        return b((Room) this);
    }

    public void writeToParcel(Parcel dest, int flags) {
        if (!fm()) {
            RoomEntityCreator.a(this, dest, flags);
            return;
        }
        dest.writeString(this.NP);
        dest.writeString(this.Tp);
        dest.writeLong(this.SR);
        dest.writeInt(this.Tq);
        dest.writeString(this.Mm);
        dest.writeInt(this.SV);
        dest.writeBundle(this.Tl);
        int size = this.SU.size();
        dest.writeInt(size);
        for (int i = 0; i < size; i++) {
            this.SU.get(i).writeToParcel(dest, flags);
        }
    }
}
