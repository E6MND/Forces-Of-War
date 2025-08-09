package com.google.android.gms.games.multiplayer.realtime;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.hn;

public final class RealTimeMessage implements Parcelable {
    public static final Parcelable.Creator<RealTimeMessage> CREATOR = new Parcelable.Creator<RealTimeMessage>() {
        /* renamed from: bn */
        public RealTimeMessage createFromParcel(Parcel parcel) {
            return new RealTimeMessage(parcel);
        }

        /* renamed from: cz */
        public RealTimeMessage[] newArray(int i) {
            return new RealTimeMessage[i];
        }
    };
    public static final int RELIABLE = 1;
    public static final int UNRELIABLE = 0;
    private final String Te;
    private final byte[] Tf;
    private final int Tg;

    private RealTimeMessage(Parcel parcel) {
        this(parcel.readString(), parcel.createByteArray(), parcel.readInt());
    }

    public RealTimeMessage(String senderParticipantId, byte[] messageData, int isReliable) {
        this.Te = (String) hn.f(senderParticipantId);
        this.Tf = (byte[]) ((byte[]) hn.f(messageData)).clone();
        this.Tg = isReliable;
    }

    public int describeContents() {
        return 0;
    }

    public byte[] getMessageData() {
        return this.Tf;
    }

    public String getSenderParticipantId() {
        return this.Te;
    }

    public boolean isReliable() {
        return this.Tg == 1;
    }

    public void writeToParcel(Parcel parcel, int flag) {
        parcel.writeString(this.Te);
        parcel.writeByteArray(this.Tf);
        parcel.writeInt(this.Tg);
    }
}
