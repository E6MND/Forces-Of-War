package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class x implements Parcelable.Creator<w> {
    static void a(w wVar, Parcel parcel, int i) {
        int C = b.C(parcel);
        b.c(parcel, 1, wVar.versionCode);
        b.a(parcel, 2, wVar.kZ);
        b.a(parcel, 3, wVar.lb);
        b.G(parcel, C);
    }

    /* renamed from: a */
    public w createFromParcel(Parcel parcel) {
        boolean z = false;
        int B = a.B(parcel);
        boolean z2 = false;
        int i = 0;
        while (parcel.dataPosition() < B) {
            int A = a.A(parcel);
            switch (a.ar(A)) {
                case 1:
                    i = a.g(parcel, A);
                    break;
                case 2:
                    z2 = a.c(parcel, A);
                    break;
                case 3:
                    z = a.c(parcel, A);
                    break;
                default:
                    a.b(parcel, A);
                    break;
            }
        }
        if (parcel.dataPosition() == B) {
            return new w(i, z2, z);
        }
        throw new a.C0014a("Overread allowed size end=" + B, parcel);
    }

    /* renamed from: b */
    public w[] newArray(int i) {
        return new w[i];
    }
}
