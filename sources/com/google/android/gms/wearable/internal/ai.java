package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.wearable.Node;

public class ai implements SafeParcelable, Node {
    public static final Parcelable.Creator<ai> CREATOR = new aj();
    private final String Lk;
    private final String xD;
    final int xJ;

    ai(int i, String str, String str2) {
        this.xJ = i;
        this.xD = str;
        this.Lk = str2;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object o) {
        if (!(o instanceof ai)) {
            return false;
        }
        ai aiVar = (ai) o;
        return aiVar.xD.equals(this.xD) && aiVar.Lk.equals(this.Lk);
    }

    public String getDisplayName() {
        return this.Lk;
    }

    public String getId() {
        return this.xD;
    }

    public int hashCode() {
        return ((this.xD.hashCode() + 629) * 37) + this.Lk.hashCode();
    }

    public String toString() {
        return "NodeParcelable{" + this.xD + "," + this.Lk + "}";
    }

    public void writeToParcel(Parcel dest, int flags) {
        aj.a(this, dest, flags);
    }
}
