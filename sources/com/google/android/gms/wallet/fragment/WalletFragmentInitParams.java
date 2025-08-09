package com.google.android.gms.wallet.fragment;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.hn;
import com.google.android.gms.wallet.MaskedWallet;
import com.google.android.gms.wallet.MaskedWalletRequest;

public final class WalletFragmentInitParams implements SafeParcelable {
    public static final Parcelable.Creator<WalletFragmentInitParams> CREATOR = new a();
    /* access modifiers changed from: private */
    public MaskedWalletRequest akj;
    /* access modifiers changed from: private */
    public MaskedWallet akk;
    /* access modifiers changed from: private */
    public int akx;
    final int xJ;
    /* access modifiers changed from: private */
    public String yN;

    public final class Builder {
        private Builder() {
        }

        public WalletFragmentInitParams build() {
            boolean z = true;
            hn.a((WalletFragmentInitParams.this.akk != null && WalletFragmentInitParams.this.akj == null) || (WalletFragmentInitParams.this.akk == null && WalletFragmentInitParams.this.akj != null), "Exactly one of MaskedWallet or MaskedWalletRequest is required");
            if (WalletFragmentInitParams.this.akx < 0) {
                z = false;
            }
            hn.a(z, "masked wallet request code is required and must be non-negative");
            return WalletFragmentInitParams.this;
        }

        public Builder setAccountName(String accountName) {
            String unused = WalletFragmentInitParams.this.yN = accountName;
            return this;
        }

        public Builder setMaskedWallet(MaskedWallet maskedWallet) {
            MaskedWallet unused = WalletFragmentInitParams.this.akk = maskedWallet;
            return this;
        }

        public Builder setMaskedWalletRequest(MaskedWalletRequest request) {
            MaskedWalletRequest unused = WalletFragmentInitParams.this.akj = request;
            return this;
        }

        public Builder setMaskedWalletRequestCode(int requestCode) {
            int unused = WalletFragmentInitParams.this.akx = requestCode;
            return this;
        }
    }

    private WalletFragmentInitParams() {
        this.xJ = 1;
        this.akx = -1;
    }

    WalletFragmentInitParams(int versionCode, String accountName, MaskedWalletRequest maskedWalletRequest, int maskedWalletRequestCode, MaskedWallet maskedWallet) {
        this.xJ = versionCode;
        this.yN = accountName;
        this.akj = maskedWalletRequest;
        this.akx = maskedWalletRequestCode;
        this.akk = maskedWallet;
    }

    public static Builder newBuilder() {
        WalletFragmentInitParams walletFragmentInitParams = new WalletFragmentInitParams();
        walletFragmentInitParams.getClass();
        return new Builder();
    }

    public int describeContents() {
        return 0;
    }

    public String getAccountName() {
        return this.yN;
    }

    public MaskedWallet getMaskedWallet() {
        return this.akk;
    }

    public MaskedWalletRequest getMaskedWalletRequest() {
        return this.akj;
    }

    public int getMaskedWalletRequestCode() {
        return this.akx;
    }

    public void writeToParcel(Parcel dest, int flags) {
        a.a(this, dest, flags);
    }
}
