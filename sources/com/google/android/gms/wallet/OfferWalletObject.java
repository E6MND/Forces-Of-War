package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.wallet.wobs.CommonWalletObject;

public final class OfferWalletObject implements SafeParcelable {
    public static final Parcelable.Creator<OfferWalletObject> CREATOR = new n();
    String ajQ;
    CommonWalletObject ajR;
    String eC;
    private final int xJ;

    OfferWalletObject() {
        this.xJ = 3;
    }

    OfferWalletObject(int versionCode, String id, String redemptionCode, CommonWalletObject commonWalletObject) {
        this.xJ = versionCode;
        this.ajQ = redemptionCode;
        if (versionCode < 3) {
            this.ajR = CommonWalletObject.nf().cw(id).ng();
        } else {
            this.ajR = commonWalletObject;
        }
    }

    public int describeContents() {
        return 0;
    }

    public String getId() {
        return this.ajR.getId();
    }

    public String getRedemptionCode() {
        return this.ajQ;
    }

    public int getVersionCode() {
        return this.xJ;
    }

    public void writeToParcel(Parcel dest, int flags) {
        n.a(this, dest, flags);
    }
}
