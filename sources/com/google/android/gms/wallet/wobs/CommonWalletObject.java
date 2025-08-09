package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.ih;
import com.google.android.gms.maps.model.LatLng;
import java.util.ArrayList;

public class CommonWalletObject implements SafeParcelable {
    public static final Parcelable.Creator<CommonWalletObject> CREATOR = new a();
    String ajg;
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
    String eC;
    String name;
    int state;
    private final int xJ;

    public final class a {
        private a() {
        }

        public a cw(String str) {
            CommonWalletObject.this.eC = str;
            return this;
        }

        public CommonWalletObject ng() {
            return CommonWalletObject.this;
        }
    }

    CommonWalletObject() {
        this.xJ = 1;
        this.ajo = ih.fV();
        this.ajq = ih.fV();
        this.ajt = ih.fV();
        this.ajv = ih.fV();
        this.ajw = ih.fV();
        this.ajx = ih.fV();
    }

    CommonWalletObject(int versionCode, String id, String classId, String name2, String issuerName, String barcodeAlternateText, String barcodeType, String barcodeValue, String barcodeLabel, int state2, ArrayList<p> messages, l validTimeInterval, ArrayList<LatLng> locations, String infoModuleDataHexFontColor, String infoModuleDataHexBackgroundColor, ArrayList<d> infoModuleDataLabelValueRows, boolean infoModuleDataShowLastUpdateTime, ArrayList<n> imageModuleDataMainImageUris, ArrayList<j> textModulesData, ArrayList<n> linksModuleDataUris) {
        this.xJ = versionCode;
        this.eC = id;
        this.ajn = classId;
        this.name = name2;
        this.ajg = issuerName;
        this.ajj = barcodeAlternateText;
        this.ajk = barcodeType;
        this.ajl = barcodeValue;
        this.ajm = barcodeLabel;
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
    }

    public static a nf() {
        CommonWalletObject commonWalletObject = new CommonWalletObject();
        commonWalletObject.getClass();
        return new a();
    }

    public int describeContents() {
        return 0;
    }

    public String getId() {
        return this.eC;
    }

    public int getVersionCode() {
        return this.xJ;
    }

    public void writeToParcel(Parcel dest, int flags) {
        a.a(this, dest, flags);
    }
}
