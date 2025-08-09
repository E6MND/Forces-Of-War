package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class fm implements Parcelable.Creator<fl> {
    static void a(fl flVar, Parcel parcel, int i) {
        int C = b.C(parcel);
        b.a(parcel, 1, flVar.xR, false);
        b.c(parcel, 1000, flVar.xJ);
        b.a(parcel, 3, (Parcelable) flVar.xS, i, false);
        b.c(parcel, 4, flVar.xT);
        b.a(parcel, 5, flVar.xU, false);
        b.G(parcel, C);
    }

    /* renamed from: F */
    public fl[] newArray(int i) {
        return new fl[i];
    }

    /* renamed from: n */
    public fl createFromParcel(Parcel parcel) {
        byte[] bArr = null;
        int B = a.B(parcel);
        int i = 0;
        int i2 = -1;
        fq fqVar = null;
        String str = null;
        while (parcel.dataPosition() < B) {
            int A = a.A(parcel);
            switch (a.ar(A)) {
                case 1:
                    str = a.o(parcel, A);
                    break;
                case 3:
                    fqVar = (fq) a.a(parcel, A, fq.CREATOR);
                    break;
                case 4:
                    i2 = a.g(parcel, A);
                    break;
                case 5:
                    bArr = a.r(parcel, A);
                    break;
                case 1000:
                    i = a.g(parcel, A);
                    break;
                default:
                    a.b(parcel, A);
                    break;
            }
        }
        if (parcel.dataPosition() == B) {
            return new fl(i, str, fqVar, i2, bArr);
        }
        throw new a.C0014a("Overread allowed size end=" + B, parcel);
    }
}
