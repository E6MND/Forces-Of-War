package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.wearable.MessageEvent;

public class af implements SafeParcelable, MessageEvent {
    public static final Parcelable.Creator<af> CREATOR = new ag();
    private final byte[] TC;
    private final String alS;
    private final String alT;
    private final int qX;
    final int xJ;

    af(int i, int i2, String str, byte[] bArr, String str2) {
        this.xJ = i;
        this.qX = i2;
        this.alS = str;
        this.TC = bArr;
        this.alT = str2;
    }

    public int describeContents() {
        return 0;
    }

    public byte[] getData() {
        return this.TC;
    }

    public String getPath() {
        return this.alS;
    }

    public int getRequestId() {
        return this.qX;
    }

    public String getSourceNodeId() {
        return this.alT;
    }

    public String toString() {
        return "MessageEventParcelable[" + this.qX + "," + this.alS + ", size=" + (this.TC == null ? "null" : Integer.valueOf(this.TC.length)) + "]";
    }

    public void writeToParcel(Parcel dest, int flags) {
        ag.a(this, dest, flags);
    }
}
