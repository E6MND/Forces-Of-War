package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.internal.ic;
import java.util.ArrayList;

public class ie implements Parcelable.Creator<ic.a> {
    static void a(ic.a aVar, Parcel parcel, int i) {
        int C = b.C(parcel);
        b.c(parcel, 1, aVar.versionCode);
        b.a(parcel, 2, aVar.className, false);
        b.b(parcel, 3, aVar.Hl, false);
        b.G(parcel, C);
    }

    /* renamed from: K */
    public ic.a createFromParcel(Parcel parcel) {
        ArrayList arrayList = null;
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
                    arrayList = a.c(parcel, A, ic.b.CREATOR);
                    break;
                default:
                    a.b(parcel, A);
                    break;
            }
        }
        if (parcel.dataPosition() == B) {
            return new ic.a(i, str, arrayList);
        }
        throw new a.C0014a("Overread allowed size end=" + B, parcel);
    }

    /* renamed from: az */
    public ic.a[] newArray(int i) {
        return new ic.a[i];
    }
}
