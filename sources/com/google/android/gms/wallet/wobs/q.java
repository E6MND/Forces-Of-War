package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class q implements Parcelable.Creator<p> {
    static void a(p pVar, Parcel parcel, int i) {
        int C = b.C(parcel);
        b.c(parcel, 1, pVar.getVersionCode());
        b.a(parcel, 2, pVar.akW, false);
        b.a(parcel, 3, pVar.qb, false);
        b.a(parcel, 4, (Parcelable) pVar.ala, i, false);
        b.a(parcel, 5, (Parcelable) pVar.alb, i, false);
        b.a(parcel, 6, (Parcelable) pVar.alc, i, false);
        b.G(parcel, C);
    }

    /* renamed from: cr */
    public p createFromParcel(Parcel parcel) {
        n nVar = null;
        int B = a.B(parcel);
        int i = 0;
        n nVar2 = null;
        l lVar = null;
        String str = null;
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
                case 4:
                    lVar = (l) a.a(parcel, A, l.CREATOR);
                    break;
                case 5:
                    nVar2 = (n) a.a(parcel, A, n.CREATOR);
                    break;
                case 6:
                    nVar = (n) a.a(parcel, A, n.CREATOR);
                    break;
                default:
                    a.b(parcel, A);
                    break;
            }
        }
        if (parcel.dataPosition() == B) {
            return new p(i, str2, str, lVar, nVar2, nVar);
        }
        throw new a.C0014a("Overread allowed size end=" + B, parcel);
    }

    /* renamed from: dZ */
    public p[] newArray(int i) {
        return new p[i];
    }
}
