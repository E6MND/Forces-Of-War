package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class jm implements Parcelable.Creator<jl> {
    static void a(jl jlVar, Parcel parcel, int i) {
        int C = b.C(parcel);
        b.c(parcel, 1, jlVar.iX());
        b.c(parcel, 1000, jlVar.getVersionCode());
        b.c(parcel, 2, jlVar.iZ());
        b.a(parcel, 3, (Parcelable) jlVar.ja(), i, false);
        b.G(parcel, C);
    }

    /* renamed from: bu */
    public jl createFromParcel(Parcel parcel) {
        int i = 0;
        int B = a.B(parcel);
        int i2 = -1;
        jn jnVar = null;
        int i3 = 0;
        while (parcel.dataPosition() < B) {
            int A = a.A(parcel);
            switch (a.ar(A)) {
                case 1:
                    i = a.g(parcel, A);
                    break;
                case 2:
                    i2 = a.g(parcel, A);
                    break;
                case 3:
                    jnVar = (jn) a.a(parcel, A, jn.CREATOR);
                    break;
                case 1000:
                    i3 = a.g(parcel, A);
                    break;
                default:
                    a.b(parcel, A);
                    break;
            }
        }
        if (parcel.dataPosition() == B) {
            return new jl(i3, i, i2, jnVar);
        }
        throw new a.C0014a("Overread allowed size end=" + B, parcel);
    }

    /* renamed from: cP */
    public jl[] newArray(int i) {
        return new jl[i];
    }
}
