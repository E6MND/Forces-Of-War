package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class jq implements Parcelable.Creator<jp> {
    static void a(jp jpVar, Parcel parcel, int i) {
        int C = b.C(parcel);
        b.c(parcel, 1, jpVar.xJ);
        b.a(parcel, 2, jpVar.je(), false);
        b.a(parcel, 3, jpVar.getTag(), false);
        b.G(parcel, C);
    }

    /* renamed from: bw */
    public jp createFromParcel(Parcel parcel) {
        String str = null;
        int B = a.B(parcel);
        int i = 0;
        String str2 = null;
        while (parcel.dataPosition() < B) {
            int A = a.A(parcel);
            switch (a.ar(A)) {
                case 1:
                    i = a.g(parcel, A);
                    break;
                case 2:
                    str2 = a.o(parcel, A);
                    break;
                case 3:
                    str = a.o(parcel, A);
                    break;
                default:
                    a.b(parcel, A);
                    break;
            }
        }
        if (parcel.dataPosition() == B) {
            return new jp(i, str2, str);
        }
        throw new a.C0014a("Overread allowed size end=" + B, parcel);
    }

    /* renamed from: cR */
    public jp[] newArray(int i) {
        return new jp[i];
    }
}
