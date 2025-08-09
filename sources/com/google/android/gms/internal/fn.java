package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class fn implements SafeParcelable {
    public static final fo CREATOR = new fo();
    public final int id;
    final int xJ;
    final Bundle xV;

    fn(int i, int i2, Bundle bundle) {
        this.xJ = i;
        this.id = i2;
        this.xV = bundle;
    }

    public int describeContents() {
        fo foVar = CREATOR;
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        fo foVar = CREATOR;
        fo.a(this, dest, flags);
    }
}
