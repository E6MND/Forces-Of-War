package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class ProxyCard implements SafeParcelable {
    public static final Parcelable.Creator<ProxyCard> CREATOR = new o();
    String ajS;
    String ajT;
    int ajU;
    int ajV;
    private final int xJ;

    ProxyCard(int versionCode, String pan, String cvn, int expirationMonth, int expirationYear) {
        this.xJ = versionCode;
        this.ajS = pan;
        this.ajT = cvn;
        this.ajU = expirationMonth;
        this.ajV = expirationYear;
    }

    public int describeContents() {
        return 0;
    }

    public String getCvn() {
        return this.ajT;
    }

    public int getExpirationMonth() {
        return this.ajU;
    }

    public int getExpirationYear() {
        return this.ajV;
    }

    public String getPan() {
        return this.ajS;
    }

    public int getVersionCode() {
        return this.xJ;
    }

    public void writeToParcel(Parcel out, int flags) {
        o.a(this, out, flags);
    }
}
