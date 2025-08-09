package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.internal.hz;
import com.google.android.gms.internal.ic;

public class ib implements Parcelable.Creator<ic.b> {
    static void a(ic.b bVar, Parcel parcel, int i) {
        int C = b.C(parcel);
        b.c(parcel, 1, bVar.versionCode);
        b.a(parcel, 2, bVar.eM, false);
        b.a(parcel, 3, (Parcelable) bVar.Hm, i, false);
        b.G(parcel, C);
    }

    /* renamed from: I */
    public ic.b createFromParcel(Parcel parcel) {
        hz.a aVar = null;
        int B = a.B(parcel);
        int i = 0;
        String str = null;
        while (parcel.dataPosition() < B) {
            int A = a.A(parcel);
            switch (a.ar(A)) {
                case 1:
                    i = a.g(parcel, A);
                    break;
                case 2:
                    str = a.o(parcel, A);
                    break;
                case 3:
                    aVar = (hz.a) a.a(parcel, A, hz.a.CREATOR);
                    break;
                default:
                    a.b(parcel, A);
                    break;
            }
        }
        if (parcel.dataPosition() == B) {
            return new ic.b(i, str, aVar);
        }
        throw new a.C0014a("Overread allowed size end=" + B, parcel);
    }

    /* renamed from: ax */
    public ic.b[] newArray(int i) {
        return new ic.b[i];
    }
}
