package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.internal.hw;

public class hy implements Parcelable.Creator<hw.a> {
    static void a(hw.a aVar, Parcel parcel, int i) {
        int C = b.C(parcel);
        b.c(parcel, 1, aVar.versionCode);
        b.a(parcel, 2, aVar.GW, false);
        b.c(parcel, 3, aVar.GX);
        b.G(parcel, C);
    }

    /* renamed from: G */
    public hw.a createFromParcel(Parcel parcel) {
        int i = 0;
        int B = a.B(parcel);
        String str = null;
        int i2 = 0;
        while (parcel.dataPosition() < B) {
            int A = a.A(parcel);
            switch (a.ar(A)) {
                case 1:
                    i2 = a.g(parcel, A);
                    break;
                case 2:
                    str = a.o(parcel, A);
                    break;
                case 3:
                    i = a.g(parcel, A);
                    break;
                default:
                    a.b(parcel, A);
                    break;
            }
        }
        if (parcel.dataPosition() == B) {
            return new hw.a(i2, str, i);
        }
        throw new a.C0014a("Overread allowed size end=" + B, parcel);
    }

    /* renamed from: av */
    public hw.a[] newArray(int i) {
        return new hw.a[i];
    }
}
