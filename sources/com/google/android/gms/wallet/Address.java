package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@Deprecated
public final class Address implements SafeParcelable {
    public static final Parcelable.Creator<Address> CREATOR = new a();
    String UE;
    String UF;
    String UG;
    String UL;
    String UN;
    boolean UO;
    String UP;
    String aiF;
    String aiG;
    String name;
    String rc;
    private final int xJ;

    Address() {
        this.xJ = 1;
    }

    Address(int versionCode, String name2, String address1, String address2, String address3, String countryCode, String city, String state, String postalCode, String phoneNumber, boolean isPostBox, String companyName) {
        this.xJ = versionCode;
        this.name = name2;
        this.UE = address1;
        this.UF = address2;
        this.UG = address3;
        this.rc = countryCode;
        this.aiF = city;
        this.aiG = state;
        this.UL = postalCode;
        this.UN = phoneNumber;
        this.UO = isPostBox;
        this.UP = companyName;
    }

    public int describeContents() {
        return 0;
    }

    public String getAddress1() {
        return this.UE;
    }

    public String getAddress2() {
        return this.UF;
    }

    public String getAddress3() {
        return this.UG;
    }

    public String getCity() {
        return this.aiF;
    }

    public String getCompanyName() {
        return this.UP;
    }

    public String getCountryCode() {
        return this.rc;
    }

    public String getName() {
        return this.name;
    }

    public String getPhoneNumber() {
        return this.UN;
    }

    public String getPostalCode() {
        return this.UL;
    }

    public String getState() {
        return this.aiG;
    }

    public int getVersionCode() {
        return this.xJ;
    }

    public boolean isPostBox() {
        return this.UO;
    }

    public void writeToParcel(Parcel out, int flags) {
        a.a(this, out, flags);
    }
}
