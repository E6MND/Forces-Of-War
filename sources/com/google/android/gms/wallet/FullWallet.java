package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.identity.intents.model.UserAddress;

public final class FullWallet implements SafeParcelable {
    public static final Parcelable.Creator<FullWallet> CREATOR = new f();
    String aiN;
    String aiO;
    ProxyCard aiP;
    String aiQ;
    Address aiR;
    Address aiS;
    String[] aiT;
    UserAddress aiU;
    UserAddress aiV;
    InstrumentInfo[] aiW;
    private final int xJ;

    private FullWallet() {
        this.xJ = 1;
    }

    FullWallet(int versionCode, String googleTransactionId, String merchantTransactionId, ProxyCard proxyCard, String email, Address billingAddress, Address shippingAddress, String[] paymentDescriptions, UserAddress buyerBillingAddress, UserAddress buyerShippingAddress, InstrumentInfo[] instrumentInfos) {
        this.xJ = versionCode;
        this.aiN = googleTransactionId;
        this.aiO = merchantTransactionId;
        this.aiP = proxyCard;
        this.aiQ = email;
        this.aiR = billingAddress;
        this.aiS = shippingAddress;
        this.aiT = paymentDescriptions;
        this.aiU = buyerBillingAddress;
        this.aiV = buyerShippingAddress;
        this.aiW = instrumentInfos;
    }

    public int describeContents() {
        return 0;
    }

    @Deprecated
    public Address getBillingAddress() {
        return this.aiR;
    }

    public UserAddress getBuyerBillingAddress() {
        return this.aiU;
    }

    public UserAddress getBuyerShippingAddress() {
        return this.aiV;
    }

    public String getEmail() {
        return this.aiQ;
    }

    public String getGoogleTransactionId() {
        return this.aiN;
    }

    public InstrumentInfo[] getInstrumentInfos() {
        return this.aiW;
    }

    public String getMerchantTransactionId() {
        return this.aiO;
    }

    public String[] getPaymentDescriptions() {
        return this.aiT;
    }

    public ProxyCard getProxyCard() {
        return this.aiP;
    }

    @Deprecated
    public Address getShippingAddress() {
        return this.aiS;
    }

    public int getVersionCode() {
        return this.xJ;
    }

    public void writeToParcel(Parcel out, int flags) {
        f.a(this, out, flags);
    }
}
