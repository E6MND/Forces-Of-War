package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class FieldWithSortOrder implements SafeParcelable {
    public static final c CREATOR = new c();
    final String JE;
    final boolean KL;
    final int xJ;

    FieldWithSortOrder(int versionCode, String fieldName, boolean isSortAscending) {
        this.xJ = versionCode;
        this.JE = fieldName;
        this.KL = isSortAscending;
    }

    public FieldWithSortOrder(String fieldName, boolean isSortAscending) {
        this(1, fieldName, isSortAscending);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        c.a(this, out, flags);
    }
}
