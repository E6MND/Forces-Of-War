package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.internal.hz;

public class ia implements Parcelable.Creator<hz.a> {
    static void a(hz.a aVar, Parcel parcel, int i) {
        int C = b.C(parcel);
        b.c(parcel, 1, aVar.getVersionCode());
        b.c(parcel, 2, aVar.fz());
        b.a(parcel, 3, aVar.fF());
        b.c(parcel, 4, aVar.fA());
        b.a(parcel, 5, aVar.fG());
        b.a(parcel, 6, aVar.fH(), false);
        b.c(parcel, 7, aVar.fI());
        b.a(parcel, 8, aVar.fK(), false);
        b.a(parcel, 9, (Parcelable) aVar.fM(), i, false);
        b.G(parcel, C);
    }

    /* renamed from: H */
    public hz.a createFromParcel(Parcel parcel) {
        hu huVar = null;
        int i = 0;
        int B = a.B(parcel);
        String str = null;
        String str2 = null;
        boolean z = false;
        int i2 = 0;
        boolean z2 = false;
        int i3 = 0;
        int i4 = 0;
        while (parcel.dataPosition() < B) {
            int A = a.A(parcel);
            switch (a.ar(A)) {
                case 1:
                    i4 = a.g(parcel, A);
                    break;
                case 2:
                    i3 = a.g(parcel, A);
                    break;
                case 3:
                    z2 = a.c(parcel, A);
                    break;
                case 4:
                    i2 = a.g(parcel, A);
                    break;
                case 5:
                    z = a.c(parcel, A);
                    break;
                case 6:
                    str2 = a.o(parcel, A);
                    break;
                case 7:
                    i = a.g(parcel, A);
                    break;
                case 8:
                    str = a.o(parcel, A);
                    break;
                case 9:
                    huVar = (hu) a.a(parcel, A, hu.CREATOR);
                    break;
                default:
                    a.b(parcel, A);
                    break;
            }
        }
        if (parcel.dataPosition() == B) {
            return new hz.a(i4, i3, z2, i2, z, str2, i, str, huVar);
        }
        throw new a.C0014a("Overread allowed size end=" + B, parcel);
    }

    /* renamed from: aw */
    public hz.a[] newArray(int i) {
        return new hz.a[i];
    }
}
