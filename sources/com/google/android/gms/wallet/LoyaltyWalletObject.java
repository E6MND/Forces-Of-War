package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.ih;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.wallet.wobs.d;
import com.google.android.gms.wallet.wobs.f;
import com.google.android.gms.wallet.wobs.j;
import com.google.android.gms.wallet.wobs.l;
import com.google.android.gms.wallet.wobs.n;
import com.google.android.gms.wallet.wobs.p;
import java.util.ArrayList;

public final class LoyaltyWalletObject implements SafeParcelable {
    public static final Parcelable.Creator<LoyaltyWalletObject> CREATOR = new j();
    String ajf;
    String ajg;
    String ajh;
    String aji;
    String ajj;
    String ajk;
    String ajl;
    String ajm;
    String ajn;
    ArrayList<p> ajo;
    l ajp;
    ArrayList<LatLng> ajq;
    String ajr;
    String ajs;
    ArrayList<d> ajt;
    boolean aju;
    ArrayList<n> ajv;
    ArrayList<j> ajw;
    ArrayList<n> ajx;
    f ajy;
    String eC;
    int state;
    private final int xJ;

    LoyaltyWalletObject() {
        this.xJ = 4;
        this.ajo = ih.fV();
        this.ajq = ih.fV();
        this.ajt = ih.fV();
        this.ajv = ih.fV();
        this.ajw = ih.fV();
        this.ajx = ih.fV();
    }

    LoyaltyWalletObject(int versionCode, String id, String accountId, String issuerName, String programName, String accountName, String barcodeAlternateText, String barcodeType, String barcodeValue, String barcodeLabel, String classId, int state2, ArrayList<p> messages, l validTimeInterval, ArrayList<LatLng> locations, String infoModuleDataHexFontColor, String infoModuleDataHexBackgroundColor, ArrayList<d> infoModuleDataLabelValueRows, boolean infoModuleDataShowLastUpdateTime, ArrayList<n> imageModuleDataMainImageUris, ArrayList<j> textModulesData, ArrayList<n> linksModuleDataUris, f loyaltyPoints) {
        this.xJ = versionCode;
        this.eC = id;
        this.ajf = accountId;
        this.ajg = issuerName;
        this.ajh = programName;
        this.aji = accountName;
        this.ajj = barcodeAlternateText;
        this.ajk = barcodeType;
        this.ajl = barcodeValue;
        this.ajm = barcodeLabel;
        this.ajn = classId;
        this.state = state2;
        this.ajo = messages;
        this.ajp = validTimeInterval;
        this.ajq = locations;
        this.ajr = infoModuleDataHexFontColor;
        this.ajs = infoModuleDataHexBackgroundColor;
        this.ajt = infoModuleDataLabelValueRows;
        this.aju = infoModuleDataShowLastUpdateTime;
        this.ajv = imageModuleDataMainImageUris;
        this.ajw = textModulesData;
        this.ajx = linksModuleDataUris;
        this.ajy = loyaltyPoints;
    }

    public int describeContents() {
        return 0;
    }

    public String getAccountId() {
        return this.ajf;
    }

    public String getAccountName() {
        return this.aji;
    }

    public String getBarcodeAlternateText() {
        return this.ajj;
    }

    public String getBarcodeType() {
        return this.ajk;
    }

    public String getBarcodeValue() {
        return this.ajl;
    }

    public String getId() {
        return this.eC;
    }

    public String getIssuerName() {
        return this.ajg;
    }

    public String getProgramName() {
        return this.ajh;
    }

    public int getVersionCode() {
        return this.xJ;
    }

    public void writeToParcel(Parcel dest, int flags) {
        j.a(this, dest, flags);
    }
}
