package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.internal.ic;
import java.util.ArrayList;

public class id implements Parcelable.Creator<ic> {
    static void a(ic icVar, Parcel parcel, int i) {
        int C = b.C(parcel);
        b.c(parcel, 1, icVar.getVersionCode());
        b.b(parcel, 2, icVar.fQ(), false);
        b.a(parcel, 3, icVar.fR(), false);
        b.G(parcel, C);
    }

    /* renamed from: J */
    public ic createFromParcel(Parcel parcel) {
        String str = null;
        int B = a.B(parcel);
        int i = 0;
        ArrayList arrayList = null;
        while (parcel.dataPosition() < B) {
            int A = a.A(parcel);
            switch (a.ar(A)) {
                case 1:
                    i = a.g(parcel, A);
                    break;
                case 2:
                    arrayList = a.c(parcel, A, ic.a.CREATOR);
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
            return new ic(i, arrayList, str);
        }
        throw new a.C0014a("Overread allowed size end=" + B, parcel);
    }

    /* renamed from: ay */
    public ic[] newArray(int i) {
        return new ic[i];
    }
}
