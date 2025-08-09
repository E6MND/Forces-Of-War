package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

public class fe implements Parcelable {
    @Deprecated
    public static final Parcelable.Creator<fe> CREATOR = new Parcelable.Creator<fe>() {
        @Deprecated
        /* renamed from: C */
        public fe[] newArray(int i) {
            return new fe[i];
        }

        @Deprecated
        /* renamed from: k */
        public fe createFromParcel(Parcel parcel) {
            return new fe(parcel);
        }
    };
    private String mValue;
    private String xD;
    private String xE;

    @Deprecated
    public fe() {
    }

    @Deprecated
    fe(Parcel parcel) {
        readFromParcel(parcel);
    }

    public fe(String str, String str2, String str3) {
        this.xD = str;
        this.xE = str2;
        this.mValue = str3;
    }

    @Deprecated
    private void readFromParcel(Parcel in) {
        this.xD = in.readString();
        this.xE = in.readString();
        this.mValue = in.readString();
    }

    @Deprecated
    public int describeContents() {
        return 0;
    }

    public String getId() {
        return this.xD;
    }

    public String getValue() {
        return this.mValue;
    }

    @Deprecated
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(this.xD);
        out.writeString(this.xE);
        out.writeString(this.mValue);
    }
}
