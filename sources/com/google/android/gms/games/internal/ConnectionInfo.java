package com.google.android.gms.games.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class ConnectionInfo implements SafeParcelable {
    public static final ConnectionInfoCreator CREATOR = new ConnectionInfoCreator();
    private final String Nk;
    private final int Nl;
    private final int xJ;

    public ConnectionInfo(int versionCode, String clientAddress, int registrationLatency) {
        this.xJ = versionCode;
        this.Nk = clientAddress;
        this.Nl = registrationLatency;
    }

    public int describeContents() {
        return 0;
    }

    public String gR() {
        return this.Nk;
    }

    public int gS() {
        return this.Nl;
    }

    public int getVersionCode() {
        return this.xJ;
    }

    public void writeToParcel(Parcel out, int flags) {
        ConnectionInfoCreator.a(this, out, flags);
    }
}
