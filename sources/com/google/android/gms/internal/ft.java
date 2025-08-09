package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class ft implements Parcelable.Creator<fs> {
    static void a(fs fsVar, Parcel parcel, int i) {
        int C = b.C(parcel);
        b.a(parcel, 1, (Parcelable) fsVar.yn, i, false);
        b.c(parcel, 1000, fsVar.xJ);
        b.a(parcel, 2, fsVar.yo);
        b.c(parcel, 3, fsVar.yp);
        b.a(parcel, 4, fsVar.mN, false);
        b.a(parcel, 5, (Parcelable) fsVar.yq, i, false);
        b.G(parcel, C);
    }

    /* renamed from: K */
    public fs[] newArray(int i) {
        return new fs[i];
    }

    /* renamed from: q */
    public fs createFromParcel(Parcel parcel) {
        int i = 0;
        fh fhVar = null;
        int B = a.B(parcel);
        long j = 0;
        String str = null;
        fj fjVar = null;
        int i2 = 0;
        while (parcel.dataPosition() < B) {
            int A = a.A(parcel);
            switch (a.ar(A)) {
                case 1:
                    fjVar = (fj) a.a(parcel, A, fj.CREATOR);
                    break;
                case 2:
                    j = a.i(parcel, A);
                    break;
                case 3:
                    i = a.g(parcel, A);
                    break;
                case 4:
                    str = a.o(parcel, A);
                    break;
                case 5:
                    fhVar = (fh) a.a(parcel, A, fh.CREATOR);
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
            return new fs(i2, fjVar, j, i, str, fhVar);
        }
        throw new a.C0014a("Overread allowed size end=" + B, parcel);
    }
}
