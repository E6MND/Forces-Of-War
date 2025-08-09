package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class lk implements Parcelable.Creator<lj> {
    static void a(lj ljVar, Parcel parcel, int i) {
        int C = b.C(parcel);
        b.c(parcel, 1, ljVar.getVersionCode());
        b.a(parcel, 2, ljVar.akb, false);
        b.a(parcel, 3, ljVar.akc, false);
        b.G(parcel, C);
    }

    /* renamed from: cf */
    public lj createFromParcel(Parcel parcel) {
        String[] strArr = null;
        int B = a.B(parcel);
        int i = 0;
        byte[][] bArr = null;
        while (parcel.dataPosition() < B) {
            int A = a.A(parcel);
            switch (a.ar(A)) {
                case 1:
                    i = a.g(parcel, A);
                    break;
                case 2:
                    strArr = a.A(parcel, A);
                    break;
                case 3:
                    bArr = a.s(parcel, A);
                    break;
                default:
                    a.b(parcel, A);
                    break;
            }
        }
        if (parcel.dataPosition() == B) {
            return new lj(i, strArr, bArr);
        }
        throw new a.C0014a("Overread allowed size end=" + B, parcel);
    }

    /* renamed from: dL */
    public lj[] newArray(int i) {
        return new lj[i];
    }
}
