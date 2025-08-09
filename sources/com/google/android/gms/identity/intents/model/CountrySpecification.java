package com.google.android.gms.identity.intents.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class CountrySpecification implements SafeParcelable {
    public static final Parcelable.Creator<CountrySpecification> CREATOR = new a();
    String rc;
    private final int xJ;

    CountrySpecification(int versionCode, String countryCode) {
        this.xJ = versionCode;
        this.rc = countryCode;
    }

    public CountrySpecification(String countryCode) {
        this.xJ = 1;
        this.rc = countryCode;
    }

    public int describeContents() {
        return 0;
    }

    public String getCountryCode() {
        return this.rc;
    }

    public int getVersionCode() {
        return this.xJ;
    }

    public void writeToParcel(Parcel dest, int flags) {
        a.a(this, dest, flags);
    }
}
