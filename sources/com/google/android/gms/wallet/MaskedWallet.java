package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.identity.intents.model.UserAddress;
import com.google.android.gms.internal.hn;

public final class MaskedWallet implements SafeParcelable {
    public static final Parcelable.Creator<MaskedWallet> CREATOR = new k();
    String aiN;
    String aiO;
    String aiQ;
    Address aiR;
    Address aiS;
    String[] aiT;
    UserAddress aiU;
    UserAddress aiV;
    InstrumentInfo[] aiW;
    OfferWalletObject[] ajA;
    LoyaltyWalletObject[] ajz;
    private final int xJ;

    public final class Builder {
        private Builder() {
        }

        public MaskedWallet build() {
            return MaskedWallet.this;
        }

        public Builder setBillingAddress(Address billingAddress) {
            MaskedWallet.this.aiR = billingAddress;
            return this;
        }

        public Builder setBuyerBillingAddress(UserAddress buyerBillingAddress) {
            MaskedWallet.this.aiU = buyerBillingAddress;
            return this;
        }

        public Builder setBuyerShippingAddress(UserAddress buyerShippingAddress) {
            MaskedWallet.this.aiV = buyerShippingAddress;
            return this;
        }

        public Builder setEmail(String email) {
            MaskedWallet.this.aiQ = email;
            return this;
        }

        public Builder setGoogleTransactionId(String googleTransactionId) {
            MaskedWallet.this.aiN = googleTransactionId;
            return this;
        }

        public Builder setInstrumentInfos(InstrumentInfo[] instrumentInfos) {
            MaskedWallet.this.aiW = instrumentInfos;
            return this;
        }

        public Builder setLoyaltyWalletObjects(LoyaltyWalletObject[] loyaltyWalletObjects) {
            MaskedWallet.this.ajz = loyaltyWalletObjects;
            return this;
        }

        public Builder setMerchantTransactionId(String merchantTransactionId) {
            MaskedWallet.this.aiO = merchantTransactionId;
            return this;
        }

        public Builder setOfferWalletObjects(OfferWalletObject[] offerWalletObjects) {
            MaskedWallet.this.ajA = offerWalletObjects;
            return this;
        }

        public Builder setPaymentDescriptions(String[] paymentDescriptions) {
            MaskedWallet.this.aiT = paymentDescriptions;
            return this;
        }

        public Builder setShippingAddress(Address shippingAddress) {
            MaskedWallet.this.aiS = shippingAddress;
            return this;
        }
    }

    private MaskedWallet() {
        this.xJ = 2;
    }

    MaskedWallet(int versionCode, String googleTransactionId, String merchantTransactionId, String[] paymentDescriptions, String email, Address billingAddress, Address shippingAddress, LoyaltyWalletObject[] loyaltyWalletObjects, OfferWalletObject[] offerWalletObjects, UserAddress buyerBillingAddress, UserAddress buyerShippingAddress, InstrumentInfo[] instrumentInfos) {
        this.xJ = versionCode;
        this.aiN = googleTransactionId;
        this.aiO = merchantTransactionId;
        this.aiT = paymentDescriptions;
        this.aiQ = email;
        this.aiR = billingAddress;
        this.aiS = shippingAddress;
        this.ajz = loyaltyWalletObjects;
        this.ajA = offerWalletObjects;
        this.aiU = buyerBillingAddress;
        this.aiV = buyerShippingAddress;
        this.aiW = instrumentInfos;
    }

    public static Builder nb() {
        MaskedWallet maskedWallet = new MaskedWallet();
        maskedWallet.getClass();
        return new Builder();
    }

    public static Builder newBuilderFrom(MaskedWallet maskedWallet) {
        hn.f(maskedWallet);
        return nb().setGoogleTransactionId(maskedWallet.getGoogleTransactionId()).setMerchantTransactionId(maskedWallet.getMerchantTransactionId()).setPaymentDescriptions(maskedWallet.getPaymentDescriptions()).setInstrumentInfos(maskedWallet.getInstrumentInfos()).setEmail(maskedWallet.getEmail()).setLoyaltyWalletObjects(maskedWallet.getLoyaltyWalletObjects()).setOfferWalletObjects(maskedWallet.getOfferWalletObjects()).setBuyerBillingAddress(maskedWallet.getBuyerBillingAddress()).setBuyerShippingAddress(maskedWallet.getBuyerShippingAddress());
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

    public LoyaltyWalletObject[] getLoyaltyWalletObjects() {
        return this.ajz;
    }

    public String getMerchantTransactionId() {
        return this.aiO;
    }

    public OfferWalletObject[] getOfferWalletObjects() {
        return this.ajA;
    }

    public String[] getPaymentDescriptions() {
        return this.aiT;
    }

    @Deprecated
    public Address getShippingAddress() {
        return this.aiS;
    }

    public int getVersionCode() {
        return this.xJ;
    }

    public void writeToParcel(Parcel dest, int flags) {
        k.a(this, dest, flags);
    }
}
