package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class li implements Parcelable.Creator<lh> {
    static void a(lh lhVar, Parcel parcel, int i) {
        int C = b.C(parcel);
        b.c(parcel, 1, lhVar.getVersionCode());
        b.a(parcel, 2, lhVar.aka, false);
        b.G(parcel, C);
    }

    /* renamed from: ce */
    public lh createFromParcel(Parcel parcel) {
        int B = a.B(parcel);
        int i = 0;
        int[] iArr = null;
        while (parcel.dataPosition() < B) {
            int A = a.A(parcel);
            switch (a.ar(A)) {
                case 1:
                    i = a.g(parcel, A);
                    break;
                case 2:
                    iArr = a.u(parcel, A);
                    break;
                default:
                    a.b(parcel, A);
                    break;
            }
        }
        if (parcel.dataPosition() == B) {
            return new lh(i, iArr);
        }
        throw new a.C0014a("Overread allowed size end=" + B, parcel);
    }

    /* renamed from: dK */
    public lh[] newArray(int i) {
        return new lh[i];
    }
}
