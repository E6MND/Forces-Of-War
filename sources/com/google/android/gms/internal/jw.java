package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.maps.model.LatLng;
import java.util.ArrayList;

public class jw implements Parcelable.Creator<jv> {
    static void a(jv jvVar, Parcel parcel, int i) {
        int C = b.C(parcel);
        b.a(parcel, 1, jvVar.getName(), false);
        b.c(parcel, 1000, jvVar.xJ);
        b.a(parcel, 2, (Parcelable) jvVar.jf(), i, false);
        b.a(parcel, 3, jvVar.getAddress(), false);
        b.b(parcel, 4, jvVar.jg(), false);
        b.a(parcel, 5, jvVar.getPhoneNumber(), false);
        b.a(parcel, 6, jvVar.jh(), false);
        b.G(parcel, C);
    }

    /* renamed from: bz */
    public jv createFromParcel(Parcel parcel) {
        String str = null;
        int B = a.B(parcel);
        int i = 0;
        String str2 = null;
        ArrayList arrayList = null;
        String str3 = null;
        LatLng latLng = null;
        String str4 = null;
        while (parcel.dataPosition() < B) {
            int A = a.A(parcel);
            switch (a.ar(A)) {
                case 1:
                    str4 = a.o(parcel, A);
                    break;
                case 2:
                    latLng = (LatLng) a.a(parcel, A, LatLng.CREATOR);
                    break;
                case 3:
                    str3 = a.o(parcel, A);
                    break;
                case 4:
                    arrayList = a.c(parcel, A, jt.CREATOR);
                    break;
                case 5:
                    str2 = a.o(parcel, A);
                    break;
                case 6:
                    str = a.o(parcel, A);
                    break;
                case 1000:
                    i = a.g(parcel, A);
                    break;
                default:
                    a.b(parcel, A);
                    break;
            }
        }
        if (parcel.dataPosition() == B) {
            return new jv(i, str4, latLng, str3, arrayList, str2, str);
        }
        throw new a.C0014a("Overread allowed size end=" + B, parcel);
    }

    /* renamed from: cU */
    public jv[] newArray(int i) {
        return new jv[i];
    }
}
