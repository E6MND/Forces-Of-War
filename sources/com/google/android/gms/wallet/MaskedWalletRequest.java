package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.identity.intents.model.CountrySpecification;
import java.util.ArrayList;
import java.util.Collection;

public final class MaskedWalletRequest implements SafeParcelable {
    public static final Parcelable.Creator<MaskedWalletRequest> CREATOR = new l();
    String aiI;
    String aiO;
    Cart aiX;
    boolean ajC;
    boolean ajD;
    boolean ajE;
    String ajF;
    String ajG;
    boolean ajH;
    boolean ajI;
    CountrySpecification[] ajJ;
    boolean ajK;
    boolean ajL;
    ArrayList<CountrySpecification> ajM;
    private final int xJ;

    public final class Builder {
        private Builder() {
        }

        public Builder addAllowedCountrySpecificationForShipping(CountrySpecification countrySpecification) {
            if (MaskedWalletRequest.this.ajM == null) {
                MaskedWalletRequest.this.ajM = new ArrayList<>();
            }
            MaskedWalletRequest.this.ajM.add(countrySpecification);
            return this;
        }

        public Builder addAllowedCountrySpecificationsForShipping(Collection<CountrySpecification> countrySpecifications) {
            if (countrySpecifications != null) {
                if (MaskedWalletRequest.this.ajM == null) {
                    MaskedWalletRequest.this.ajM = new ArrayList<>();
                }
                MaskedWalletRequest.this.ajM.addAll(countrySpecifications);
            }
            return this;
        }

        public MaskedWalletRequest build() {
            return MaskedWalletRequest.this;
        }

        public Builder setAllowDebitCard(boolean allowDebitCard) {
            MaskedWalletRequest.this.ajL = allowDebitCard;
            return this;
        }

        public Builder setAllowPrepaidCard(boolean allowPrepaidCard) {
            MaskedWalletRequest.this.ajK = allowPrepaidCard;
            return this;
        }

        public Builder setCart(Cart cart) {
            MaskedWalletRequest.this.aiX = cart;
            return this;
        }

        public Builder setCurrencyCode(String currencyCode) {
            MaskedWalletRequest.this.aiI = currencyCode;
            return this;
        }

        public Builder setEstimatedTotalPrice(String estimatedTotalPrice) {
            MaskedWalletRequest.this.ajF = estimatedTotalPrice;
            return this;
        }

        public Builder setIsBillingAgreement(boolean isBillingAgreement) {
            MaskedWalletRequest.this.ajI = isBillingAgreement;
            return this;
        }

        public Builder setMerchantName(String merchantName) {
            MaskedWalletRequest.this.ajG = merchantName;
            return this;
        }

        public Builder setMerchantTransactionId(String merchantTransactionId) {
            MaskedWalletRequest.this.aiO = merchantTransactionId;
            return this;
        }

        public Builder setPhoneNumberRequired(boolean phoneNumberRequired) {
            MaskedWalletRequest.this.ajC = phoneNumberRequired;
            return this;
        }

        public Builder setShippingAddressRequired(boolean shippingAddressRequired) {
            MaskedWalletRequest.this.ajD = shippingAddressRequired;
            return this;
        }

        public Builder setShouldRetrieveWalletObjects(boolean shouldRetrieveWalletObjects) {
            MaskedWalletRequest.this.ajH = shouldRetrieveWalletObjects;
            return this;
        }

        public Builder setUseMinimalBillingAddress(boolean useMinimalBillingAddress) {
            MaskedWalletRequest.this.ajE = useMinimalBillingAddress;
            return this;
        }
    }

    MaskedWalletRequest() {
        this.xJ = 3;
        this.ajK = true;
        this.ajL = true;
    }

    MaskedWalletRequest(int versionCode, String merchantTransactionId, boolean phoneNumberRequired, boolean shippingAddressRequired, boolean useMinimalBillingAddress, String estimatedTotalPrice, String currencyCode, String merchantName, Cart cart, boolean shouldRetrieveWalletObjects, boolean isBillingAgreement, CountrySpecification[] allowedShippingCountrySpecifications, boolean allowPrepaidCard, boolean allowDebitCard, ArrayList<CountrySpecification> allowedCountrySpecificationsForShipping) {
        this.xJ = versionCode;
        this.aiO = merchantTransactionId;
        this.ajC = phoneNumberRequired;
        this.ajD = shippingAddressRequired;
        this.ajE = useMinimalBillingAddress;
        this.ajF = estimatedTotalPrice;
        this.aiI = currencyCode;
        this.ajG = merchantName;
        this.aiX = cart;
        this.ajH = shouldRetrieveWalletObjects;
        this.ajI = isBillingAgreement;
        this.ajJ = allowedShippingCountrySpecifications;
        this.ajK = allowPrepaidCard;
        this.ajL = allowDebitCard;
        this.ajM = allowedCountrySpecificationsForShipping;
    }

    public static Builder newBuilder() {
        MaskedWalletRequest maskedWalletRequest = new MaskedWalletRequest();
        maskedWalletRequest.getClass();
        return new Builder();
    }

    public boolean allowDebitCard() {
        return this.ajL;
    }

    public boolean allowPrepaidCard() {
        return this.ajK;
    }

    public int describeContents() {
        return 0;
    }

    public ArrayList<CountrySpecification> getAllowedCountrySpecificationsForShipping() {
        return this.ajM;
    }

    public CountrySpecification[] getAllowedShippingCountrySpecifications() {
        return this.ajJ;
    }

    public Cart getCart() {
        return this.aiX;
    }

    public String getCurrencyCode() {
        return this.aiI;
    }

    public String getEstimatedTotalPrice() {
        return this.ajF;
    }

    public String getMerchantName() {
        return this.ajG;
    }

    public String getMerchantTransactionId() {
        return this.aiO;
    }

    public int getVersionCode() {
        return this.xJ;
    }

    public boolean isBillingAgreement() {
        return this.ajI;
    }

    public boolean isPhoneNumberRequired() {
        return this.ajC;
    }

    public boolean isShippingAddressRequired() {
        return this.ajD;
    }

    public boolean shouldRetrieveWalletObjects() {
        return this.ajH;
    }

    public boolean useMinimalBillingAddress() {
        return this.ajE;
    }

    public void writeToParcel(Parcel dest, int flags) {
        l.a(this, dest, flags);
    }
}
