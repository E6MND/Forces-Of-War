package com.google.android.gms.wallet.fragment;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import com.google.android.gms.R;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class WalletFragmentOptions implements SafeParcelable {
    public static final Parcelable.Creator<WalletFragmentOptions> CREATOR = new b();
    /* access modifiers changed from: private */
    public int Hv;
    /* access modifiers changed from: private */
    public int ajY;
    /* access modifiers changed from: private */
    public WalletFragmentStyle akz;
    /* access modifiers changed from: private */
    public int mTheme;
    final int xJ;

    public final class Builder {
        private Builder() {
        }

        public WalletFragmentOptions build() {
            return WalletFragmentOptions.this;
        }

        public Builder setEnvironment(int environment) {
            int unused = WalletFragmentOptions.this.ajY = environment;
            return this;
        }

        public Builder setFragmentStyle(int styleResourceId) {
            WalletFragmentStyle unused = WalletFragmentOptions.this.akz = new WalletFragmentStyle().setStyleResourceId(styleResourceId);
            return this;
        }

        public Builder setFragmentStyle(WalletFragmentStyle fragmentStyle) {
            WalletFragmentStyle unused = WalletFragmentOptions.this.akz = fragmentStyle;
            return this;
        }

        public Builder setMode(int mode) {
            int unused = WalletFragmentOptions.this.Hv = mode;
            return this;
        }

        public Builder setTheme(int theme) {
            int unused = WalletFragmentOptions.this.mTheme = theme;
            return this;
        }
    }

    private WalletFragmentOptions() {
        this.xJ = 1;
    }

    WalletFragmentOptions(int versionCode, int environment, int theme, WalletFragmentStyle fragmentStyle, int mode) {
        this.xJ = versionCode;
        this.ajY = environment;
        this.mTheme = theme;
        this.akz = fragmentStyle;
        this.Hv = mode;
    }

    public static WalletFragmentOptions a(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.WalletFragmentOptions);
        int i = obtainStyledAttributes.getInt(0, 0);
        int i2 = obtainStyledAttributes.getInt(1, 1);
        int resourceId = obtainStyledAttributes.getResourceId(2, 0);
        int i3 = obtainStyledAttributes.getInt(3, 1);
        obtainStyledAttributes.recycle();
        WalletFragmentOptions walletFragmentOptions = new WalletFragmentOptions();
        walletFragmentOptions.mTheme = i;
        walletFragmentOptions.ajY = i2;
        walletFragmentOptions.akz = new WalletFragmentStyle().setStyleResourceId(resourceId);
        walletFragmentOptions.akz.N(context);
        walletFragmentOptions.Hv = i3;
        return walletFragmentOptions;
    }

    public static Builder newBuilder() {
        WalletFragmentOptions walletFragmentOptions = new WalletFragmentOptions();
        walletFragmentOptions.getClass();
        return new Builder();
    }

    public void N(Context context) {
        if (this.akz != null) {
            this.akz.N(context);
        }
    }

    public int describeContents() {
        return 0;
    }

    public int getEnvironment() {
        return this.ajY;
    }

    public WalletFragmentStyle getFragmentStyle() {
        return this.akz;
    }

    public int getMode() {
        return this.Hv;
    }

    public int getTheme() {
        return this.mTheme;
    }

    public void writeToParcel(Parcel dest, int flags) {
        b.a(this, dest, flags);
    }
}
