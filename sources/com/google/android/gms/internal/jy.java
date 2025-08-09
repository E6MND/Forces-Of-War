package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class jy implements Parcelable.Creator<jx> {
    static void a(jx jxVar, Parcel parcel, int i) {
        int C = b.C(parcel);
        b.a(parcel, 1, jxVar.qU, false);
        b.c(parcel, 1000, jxVar.xJ);
        b.c(parcel, 2, jxVar.YP);
        b.G(parcel, C);
    }

    /* renamed from: bA */
    public jx createFromParcel(Parcel parcel) {
        int i = 0;
        int B = a.B(parcel);
        String str = null;
        int i2 = 0;
        while (parcel.dataPosition() < B) {
            int A = a.A(parcel);
            switch (a.ar(A)) {
                case 1:
                    str = a.o(parcel, A);
                    break;
                case 2:
                    i = a.g(parcel, A);
                    break;
                case 1000:
                    i2 = a.g(parcel, A);
                    break;
                default:
                    a.b(parcel, A);
                    break;
            }
        }
        if (parcel.dataPosition() == B) {
            return new jx(i2, str, i);
        }
        throw new a.C0014a("Overread allowed size end=" + B, parcel);
    }

    /* renamed from: cV */
    public jx[] newArray(int i) {
        return new jx[i];
    }
}
