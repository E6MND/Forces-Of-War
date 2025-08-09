package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.ads.search.SearchAdRequest;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class ax implements SafeParcelable {
    public static final ay CREATOR = new ay();
    public final int backgroundColor;
    public final int mB;
    public final int mC;
    public final int mD;
    public final int mE;
    public final int mF;
    public final int mG;
    public final int mH;
    public final String mI;
    public final int mJ;
    public final String mK;
    public final int mL;
    public final int mM;
    public final String mN;
    public final int versionCode;

    ax(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, String str, int i10, String str2, int i11, int i12, String str3) {
        this.versionCode = i;
        this.mB = i2;
        this.backgroundColor = i3;
        this.mC = i4;
        this.mD = i5;
        this.mE = i6;
        this.mF = i7;
        this.mG = i8;
        this.mH = i9;
        this.mI = str;
        this.mJ = i10;
        this.mK = str2;
        this.mL = i11;
        this.mM = i12;
        this.mN = str3;
    }

    public ax(SearchAdRequest searchAdRequest) {
        this.versionCode = 1;
        this.mB = searchAdRequest.getAnchorTextColor();
        this.backgroundColor = searchAdRequest.getBackgroundColor();
        this.mC = searchAdRequest.getBackgroundGradientBottom();
        this.mD = searchAdRequest.getBackgroundGradientTop();
        this.mE = searchAdRequest.getBorderColor();
        this.mF = searchAdRequest.getBorderThickness();
        this.mG = searchAdRequest.getBorderType();
        this.mH = searchAdRequest.getCallButtonColor();
        this.mI = searchAdRequest.getCustomChannels();
        this.mJ = searchAdRequest.getDescriptionTextColor();
        this.mK = searchAdRequest.getFontFace();
        this.mL = searchAdRequest.getHeaderTextColor();
        this.mM = searchAdRequest.getHeaderTextSize();
        this.mN = searchAdRequest.getQuery();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        ay.a(this, out, flags);
    }
}
