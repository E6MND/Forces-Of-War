package com.google.android.gms.maps;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;

public class a {
    static void a(GoogleMapOptions googleMapOptions, Parcel parcel, int i) {
        int C = b.C(parcel);
        b.c(parcel, 1, googleMapOptions.getVersionCode());
        b.a(parcel, 2, googleMapOptions.jl());
        b.a(parcel, 3, googleMapOptions.jm());
        b.c(parcel, 4, googleMapOptions.getMapType());
        b.a(parcel, 5, (Parcelable) googleMapOptions.getCamera(), i, false);
        b.a(parcel, 6, googleMapOptions.jn());
        b.a(parcel, 7, googleMapOptions.jo());
        b.a(parcel, 8, googleMapOptions.jp());
        b.a(parcel, 9, googleMapOptions.jq());
        b.a(parcel, 10, googleMapOptions.jr());
        b.a(parcel, 11, googleMapOptions.js());
        b.G(parcel, C);
    }
}
