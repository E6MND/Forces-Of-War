package com.google.android.gms.games.multiplayer;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.internal.GamesDowngradeableSafeParcel;
import com.google.android.gms.internal.hl;
import com.google.android.gms.internal.hn;
import java.util.ArrayList;

public final class InvitationEntity extends GamesDowngradeableSafeParcel implements Invitation {
    public static final Parcelable.Creator<InvitationEntity> CREATOR = new InvitationEntityCreatorCompat();
    private final String NN;
    private final GameEntity Rq;
    private final long SR;
    private final int SS;
    private final ParticipantEntity ST;
    private final ArrayList<ParticipantEntity> SU;
    private final int SV;
    private final int SW;
    private final int xJ;

    static final class InvitationEntityCreatorCompat extends InvitationEntityCreator {
        InvitationEntityCreatorCompat() {
        }

        /* renamed from: bl */
        public InvitationEntity createFromParcel(Parcel parcel) {
            if (InvitationEntity.c(InvitationEntity.fl()) || InvitationEntity.aA(InvitationEntity.class.getCanonicalName())) {
                return super.createFromParcel(parcel);
            }
            GameEntity createFromParcel = GameEntity.CREATOR.createFromParcel(parcel);
            String readString = parcel.readString();
            long readLong = parcel.readLong();
            int readInt = parcel.readInt();
            ParticipantEntity createFromParcel2 = ParticipantEntity.CREATOR.createFromParcel(parcel);
            int readInt2 = parcel.readInt();
            ArrayList arrayList = new ArrayList(readInt2);
            for (int i = 0; i < readInt2; i++) {
                arrayList.add(ParticipantEntity.CREATOR.createFromParcel(parcel));
            }
            return new InvitationEntity(2, createFromParcel, readString, readLong, readInt, createFromParcel2, arrayList, -1, 0);
        }
    }

    InvitationEntity(int versionCode, GameEntity game, String invitationId, long creationTimestamp, int invitationType, ParticipantEntity inviter, ArrayList<ParticipantEntity> participants, int variant, int availableAutoMatchSlots) {
        this.xJ = versionCode;
        this.Rq = game;
        this.NN = invitationId;
        this.SR = creationTimestamp;
        this.SS = invitationType;
        this.ST = inviter;
        this.SU = participants;
        this.SV = variant;
        this.SW = availableAutoMatchSlots;
    }

    InvitationEntity(Invitation invitation) {
        this.xJ = 2;
        this.Rq = new GameEntity(invitation.getGame());
        this.NN = invitation.getInvitationId();
        this.SR = invitation.getCreationTimestamp();
        this.SS = invitation.getInvitationType();
        this.SV = invitation.getVariant();
        this.SW = invitation.getAvailableAutoMatchSlots();
        String participantId = invitation.getInviter().getParticipantId();
        Participant participant = null;
        ArrayList<Participant> participants = invitation.getParticipants();
        int size = participants.size();
        this.SU = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            Participant participant2 = participants.get(i);
            if (participant2.getParticipantId().equals(participantId)) {
                participant = participant2;
            }
            this.SU.add((ParticipantEntity) participant2.freeze());
        }
        hn.b(participant, (Object) "Must have a valid inviter!");
        this.ST = (ParticipantEntity) participant.freeze();
    }

    static int a(Invitation invitation) {
        return hl.hashCode(invitation.getGame(), invitation.getInvitationId(), Long.valueOf(invitation.getCreationTimestamp()), Integer.valueOf(invitation.getInvitationType()), invitation.getInviter(), invitation.getParticipants(), Integer.valueOf(invitation.getVariant()), Integer.valueOf(invitation.getAvailableAutoMatchSlots()));
    }

    static boolean a(Invitation invitation, Object obj) {
        if (!(obj instanceof Invitation)) {
            return false;
        }
        if (invitation == obj) {
            return true;
        }
        Invitation invitation2 = (Invitation) obj;
        return hl.equal(invitation2.getGame(), invitation.getGame()) && hl.equal(invitation2.getInvitationId(), invitation.getInvitationId()) && hl.equal(Long.valueOf(invitation2.getCreationTimestamp()), Long.valueOf(invitation.getCreationTimestamp())) && hl.equal(Integer.valueOf(invitation2.getInvitationType()), Integer.valueOf(invitation.getInvitationType())) && hl.equal(invitation2.getInviter(), invitation.getInviter()) && hl.equal(invitation2.getParticipants(), invitation.getParticipants()) && hl.equal(Integer.valueOf(invitation2.getVariant()), Integer.valueOf(invitation.getVariant())) && hl.equal(Integer.valueOf(invitation2.getAvailableAutoMatchSlots()), Integer.valueOf(invitation.getAvailableAutoMatchSlots()));
    }

    static String b(Invitation invitation) {
        return hl.e(invitation).a("Game", invitation.getGame()).a("InvitationId", invitation.getInvitationId()).a("CreationTimestamp", Long.valueOf(invitation.getCreationTimestamp())).a("InvitationType", Integer.valueOf(invitation.getInvitationType())).a("Inviter", invitation.getInviter()).a("Participants", invitation.getParticipants()).a("Variant", Integer.valueOf(invitation.getVariant())).a("AvailableAutoMatchSlots", Integer.valueOf(invitation.getAvailableAutoMatchSlots())).toString();
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        return a(this, obj);
    }

    public Invitation freeze() {
        return this;
    }

    public int getAvailableAutoMatchSlots() {
        return this.SW;
    }

    public long getCreationTimestamp() {
        return this.SR;
    }

    public Game getGame() {
        return this.Rq;
    }

    public String getInvitationId() {
        return this.NN;
    }

    public int getInvitationType() {
        return this.SS;
    }

    public Participant getInviter() {
        return this.ST;
    }

    public ArrayList<Participant> getParticipants() {
        return new ArrayList<>(this.SU);
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
        return b((Invitation) this);
    }

    public void writeToParcel(Parcel dest, int flags) {
        if (!fm()) {
            InvitationEntityCreator.a(this, dest, flags);
            return;
        }
        this.Rq.writeToParcel(dest, flags);
        dest.writeString(this.NN);
        dest.writeLong(this.SR);
        dest.writeInt(this.SS);
        this.ST.writeToParcel(dest, flags);
        int size = this.SU.size();
        dest.writeInt(size);
        for (int i = 0; i < size; i++) {
            this.SU.get(i).writeToParcel(dest, flags);
        }
    }
}
