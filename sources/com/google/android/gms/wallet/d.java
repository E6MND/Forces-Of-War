package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class d implements SafeParcelable {
    public static final Parcelable.Creator<d> CREATOR = new e();
    LoyaltyWalletObject aiL;
    OfferWalletObject aiM;
    private final int xJ;

    d() {
        this.xJ = 2;
    }

    d(int i, LoyaltyWalletObject loyaltyWalletObject, OfferWalletObject offerWalletObject) {
        this.xJ = i;
        this.aiL = loyaltyWalletObject;
        this.aiM = offerWalletObject;
    }

    public int describeContents() {
        return 0;
    }

    public int getVersionCode() {
        return this.xJ;
    }

    public void writeToParcel(Parcel dest, int flags) {
        e.a(this, dest, flags);
    }
}
