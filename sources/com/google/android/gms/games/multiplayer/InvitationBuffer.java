package com.google.android.gms.games.multiplayer;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.g;

public final class InvitationBuffer extends g<Invitation> {
    public InvitationBuffer(DataHolder dataHolder) {
        super(dataHolder);
    }

    /* access modifiers changed from: protected */
    public String eU() {
        return "external_invitation_id";
    }

    /* access modifiers changed from: protected */
    /* renamed from: g */
    public Invitation c(int i, int i2) {
        return new InvitationRef(this.DD, i, i2);
    }
}
