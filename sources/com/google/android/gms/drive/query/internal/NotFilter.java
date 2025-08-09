package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.query.Filter;

public class NotFilter implements SafeParcelable, Filter {
    public static final Parcelable.Creator<NotFilter> CREATOR = new j();
    final FilterHolder KW;
    final int xJ;

    NotFilter(int versionCode, FilterHolder toNegate) {
        this.xJ = versionCode;
        this.KW = toNegate;
    }

    public NotFilter(Filter toNegate) {
        this(1, new FilterHolder(toNegate));
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        j.a(this, out, flags);
    }
}
