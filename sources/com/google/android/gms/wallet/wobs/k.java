package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class k implements Parcelable.Creator<j> {
    static void a(j jVar, Parcel parcel, int i) {
        int C = b.C(parcel);
        b.c(parcel, 1, jVar.getVersionCode());
        b.a(parcel, 2, jVar.akW, false);
        b.a(parcel, 3, jVar.qb, false);
        b.G(parcel, C);
    }

    /* renamed from: co */
    public j createFromParcel(Parcel parcel) {
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
            return new j(i, str2, str);
        }
        throw new a.C0014a("Overread allowed size end=" + B, parcel);
    }

    /* renamed from: dW */
    public j[] newArray(int i) {
        return new j[i];
    }
}
