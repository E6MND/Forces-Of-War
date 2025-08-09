package com.google.android.gms.wallet.fragment;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import com.google.android.gms.R;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class WalletFragmentStyle implements SafeParcelable {
    public static final Parcelable.Creator<WalletFragmentStyle> CREATOR = new c();
    Bundle akB;
    int akC;
    final int xJ;

    public WalletFragmentStyle() {
        this.xJ = 1;
        this.akB = new Bundle();
    }

    WalletFragmentStyle(int versionCode, Bundle attributes, int styleResourceId) {
        this.xJ = versionCode;
        this.akB = attributes;
        this.akC = styleResourceId;
    }

    private void a(TypedArray typedArray, int i, String str) {
        TypedValue peekValue;
        if (!this.akB.containsKey(str) && (peekValue = typedArray.peekValue(i)) != null) {
            this.akB.putLong(str, Dimension.a(peekValue));
        }
    }

    private void a(TypedArray typedArray, int i, String str, String str2) {
        TypedValue peekValue;
        if (!this.akB.containsKey(str) && !this.akB.containsKey(str2) && (peekValue = typedArray.peekValue(i)) != null) {
            if (peekValue.type < 28 || peekValue.type > 31) {
                this.akB.putInt(str2, peekValue.resourceId);
            } else {
                this.akB.putInt(str, peekValue.data);
            }
        }
    }

    private void b(TypedArray typedArray, int i, String str) {
        TypedValue peekValue;
        if (!this.akB.containsKey(str) && (peekValue = typedArray.peekValue(i)) != null) {
            this.akB.putInt(str, peekValue.data);
        }
    }

    public void N(Context context) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(this.akC <= 0 ? R.style.WalletFragmentDefaultStyle : this.akC, R.styleable.WalletFragmentStyle);
        a(obtainStyledAttributes, 1, "buyButtonWidth");
        a(obtainStyledAttributes, 0, "buyButtonHeight");
        b(obtainStyledAttributes, 2, "buyButtonText");
        b(obtainStyledAttributes, 3, "buyButtonAppearance");
        b(obtainStyledAttributes, 4, "maskedWalletDetailsTextAppearance");
        b(obtainStyledAttributes, 5, "maskedWalletDetailsHeaderTextAppearance");
        a(obtainStyledAttributes, 6, "maskedWalletDetailsBackgroundColor", "maskedWalletDetailsBackgroundResource");
        b(obtainStyledAttributes, 7, "maskedWalletDetailsButtonTextAppearance");
        a(obtainStyledAttributes, 8, "maskedWalletDetailsButtonBackgroundColor", "maskedWalletDetailsButtonBackgroundResource");
        b(obtainStyledAttributes, 9, "maskedWalletDetailsLogoTextColor");
        b(obtainStyledAttributes, 10, "maskedWalletDetailsLogoImageType");
        obtainStyledAttributes.recycle();
    }

    public int a(String str, DisplayMetrics displayMetrics, int i) {
        return this.akB.containsKey(str) ? Dimension.a(this.akB.getLong(str), displayMetrics) : i;
    }

    public int describeContents() {
        return 0;
    }

    public WalletFragmentStyle setBuyButtonAppearance(int buyButtonAppearance) {
        this.akB.putInt("buyButtonAppearance", buyButtonAppearance);
        return this;
    }

    public WalletFragmentStyle setBuyButtonHeight(int height) {
        this.akB.putLong("buyButtonHeight", Dimension.dM(height));
        return this;
    }

    public WalletFragmentStyle setBuyButtonHeight(int unit, float height) {
        this.akB.putLong("buyButtonHeight", Dimension.a(unit, height));
        return this;
    }

    public WalletFragmentStyle setBuyButtonText(int buyButtonText) {
        this.akB.putInt("buyButtonText", buyButtonText);
        return this;
    }

    public WalletFragmentStyle setBuyButtonWidth(int width) {
        this.akB.putLong("buyButtonWidth", Dimension.dM(width));
        return this;
    }

    public WalletFragmentStyle setBuyButtonWidth(int unit, float width) {
        this.akB.putLong("buyButtonWidth", Dimension.a(unit, width));
        return this;
    }

    public WalletFragmentStyle setMaskedWalletDetailsBackgroundColor(int color) {
        this.akB.remove("maskedWalletDetailsBackgroundResource");
        this.akB.putInt("maskedWalletDetailsBackgroundColor", color);
        return this;
    }

    public WalletFragmentStyle setMaskedWalletDetailsBackgroundResource(int resourceId) {
        this.akB.remove("maskedWalletDetailsBackgroundColor");
        this.akB.putInt("maskedWalletDetailsBackgroundResource", resourceId);
        return this;
    }

    public WalletFragmentStyle setMaskedWalletDetailsButtonBackgroundColor(int color) {
        this.akB.remove("maskedWalletDetailsButtonBackgroundResource");
        this.akB.putInt("maskedWalletDetailsButtonBackgroundColor", color);
        return this;
    }

    public WalletFragmentStyle setMaskedWalletDetailsButtonBackgroundResource(int resourceId) {
        this.akB.remove("maskedWalletDetailsButtonBackgroundColor");
        this.akB.putInt("maskedWalletDetailsButtonBackgroundResource", resourceId);
        return this;
    }

    public WalletFragmentStyle setMaskedWalletDetailsButtonTextAppearance(int resourceId) {
        this.akB.putInt("maskedWalletDetailsButtonTextAppearance", resourceId);
        return this;
    }

    public WalletFragmentStyle setMaskedWalletDetailsHeaderTextAppearance(int resourceId) {
        this.akB.putInt("maskedWalletDetailsHeaderTextAppearance", resourceId);
        return this;
    }

    public WalletFragmentStyle setMaskedWalletDetailsLogoImageType(int imageType) {
        this.akB.putInt("maskedWalletDetailsLogoImageType", imageType);
        return this;
    }

    public WalletFragmentStyle setMaskedWalletDetailsLogoTextColor(int color) {
        this.akB.putInt("maskedWalletDetailsLogoTextColor", color);
        return this;
    }

    public WalletFragmentStyle setMaskedWalletDetailsTextAppearance(int resourceId) {
        this.akB.putInt("maskedWalletDetailsTextAppearance", resourceId);
        return this;
    }

    public WalletFragmentStyle setStyleResourceId(int id) {
        this.akC = id;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        c.a(this, dest, flags);
    }
}
