package com.google.android.gms.identity.intents.model;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.identity.intents.AddressConstants;

public final class UserAddress implements SafeParcelable {
    public static final Parcelable.Creator<UserAddress> CREATOR = new b();
    String UE;
    String UF;
    String UG;
    String UH;
    String UI;
    String UJ;
    String UK;
    String UL;
    String UM;
    String UN;
    boolean UO;
    String UP;
    String UQ;
    String name;
    String rc;
    private final int xJ;

    UserAddress() {
        this.xJ = 1;
    }

    UserAddress(int versionCode, String name2, String address1, String address2, String address3, String address4, String address5, String administrativeArea, String locality, String countryCode, String postalCode, String sortingCode, String phoneNumber, boolean isPostBox, String companyName, String emailAddress) {
        this.xJ = versionCode;
        this.name = name2;
        this.UE = address1;
        this.UF = address2;
        this.UG = address3;
        this.UH = address4;
        this.UI = address5;
        this.UJ = administrativeArea;
        this.UK = locality;
        this.rc = countryCode;
        this.UL = postalCode;
        this.UM = sortingCode;
        this.UN = phoneNumber;
        this.UO = isPostBox;
        this.UP = companyName;
        this.UQ = emailAddress;
    }

    public static UserAddress fromIntent(Intent data) {
        if (data == null || !data.hasExtra(AddressConstants.Extras.EXTRA_ADDRESS)) {
            return null;
        }
        return (UserAddress) data.getParcelableExtra(AddressConstants.Extras.EXTRA_ADDRESS);
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

    public String getAddress4() {
        return this.UH;
    }

    public String getAddress5() {
        return this.UI;
    }

    public String getAdministrativeArea() {
        return this.UJ;
    }

    public String getCompanyName() {
        return this.UP;
    }

    public String getCountryCode() {
        return this.rc;
    }

    public String getEmailAddress() {
        return this.UQ;
    }

    public String getLocality() {
        return this.UK;
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

    public String getSortingCode() {
        return this.UM;
    }

    public int getVersionCode() {
        return this.xJ;
    }

    public boolean isPostBox() {
        return this.UO;
    }

    public void writeToParcel(Parcel out, int flags) {
        b.a(this, out, flags);
    }
}
