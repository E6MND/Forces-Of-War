package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class ig implements Parcelable.Creator<Cif> {
    static void a(Cif ifVar, Parcel parcel, int i) {
        int C = b.C(parcel);
        b.c(parcel, 1, ifVar.getVersionCode());
        b.a(parcel, 2, ifVar.fT(), false);
        b.a(parcel, 3, (Parcelable) ifVar.fU(), i, false);
        b.G(parcel, C);
    }

    /* renamed from: L */
    public Cif createFromParcel(Parcel parcel) {
        ic icVar = null;
        int B = a.B(parcel);
        int i = 0;
        Parcel parcel2 = null;
        while (parcel.dataPosition() < B) {
            int A = a.A(parcel);
            switch (a.ar(A)) {
                case 1:
                    i = a.g(parcel, A);
                    break;
                case 2:
                    parcel2 = a.C(parcel, A);
                    break;
                case 3:
                    icVar = (ic) a.a(parcel, A, ic.CREATOR);
                    break;
                default:
                    a.b(parcel, A);
                    break;
            }
        }
        if (parcel.dataPosition() == B) {
            return new Cif(i, parcel2, icVar);
        }
        throw new a.C0014a("Overread allowed size end=" + B, parcel);
    }

    /* renamed from: aA */
    public Cif[] newArray(int i) {
        return new Cif[i];
    }
}
