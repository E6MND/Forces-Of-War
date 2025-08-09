package com.google.android.gms.drive.realtime.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class ParcelableCollaborator implements SafeParcelable {
    public static final Parcelable.Creator<ParcelableCollaborator> CREATOR = new p();
    final boolean Lh;
    final boolean Li;
    final String Lj;
    final String Lk;
    final String Ll;
    final String Lm;
    final String rO;
    final int xJ;

    ParcelableCollaborator(int versionCode, boolean isMe, boolean isAnonymous, String sessionId, String userId, String displayName, String color, String photoUrl) {
        this.xJ = versionCode;
        this.Lh = isMe;
        this.Li = isAnonymous;
        this.rO = sessionId;
        this.Lj = userId;
        this.Lk = displayName;
        this.Ll = color;
        this.Lm = photoUrl;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ParcelableCollaborator)) {
            return false;
        }
        return this.rO.equals(((ParcelableCollaborator) obj).rO);
    }

    public int hashCode() {
        return this.rO.hashCode();
    }

    public String toString() {
        return "Collaborator [isMe=" + this.Lh + ", isAnonymous=" + this.Li + ", sessionId=" + this.rO + ", userId=" + this.Lj + ", displayName=" + this.Lk + ", color=" + this.Ll + ", photoUrl=" + this.Lm + "]";
    }

    public void writeToParcel(Parcel dest, int flags) {
        p.a(this, dest, flags);
    }
}
