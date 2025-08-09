package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class gg implements Parcelable.Creator<gf> {
    static void a(gf gfVar, Parcel parcel, int i) {
        int C = b.C(parcel);
        b.c(parcel, 1, gfVar.getVersionCode());
        b.a(parcel, 2, gfVar.dX(), false);
        b.G(parcel, C);
    }

    /* renamed from: S */
    public gf[] newArray(int i) {
        return new gf[i];
    }

    /* renamed from: u */
    public gf createFromParcel(Parcel parcel) {
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
                default:
                    a.b(parcel, A);
                    break;
            }
        }
        if (parcel.dataPosition() == B) {
            return new gf(i, str);
        }
        throw new a.C0014a("Overread allowed size end=" + B, parcel);
    }
}
