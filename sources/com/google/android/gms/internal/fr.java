package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class fr implements Parcelable.Creator<fq> {
    static void a(fq fqVar, Parcel parcel, int i) {
        int C = b.C(parcel);
        b.a(parcel, 1, fqVar.name, false);
        b.c(parcel, 1000, fqVar.xJ);
        b.a(parcel, 2, fqVar.xY, false);
        b.a(parcel, 3, fqVar.xZ);
        b.c(parcel, 4, fqVar.weight);
        b.a(parcel, 5, fqVar.ya);
        b.a(parcel, 6, fqVar.yb, false);
        b.a(parcel, 7, (T[]) fqVar.yc, i, false);
        b.a(parcel, 8, fqVar.yd, false);
        b.a(parcel, 11, fqVar.ye, false);
        b.G(parcel, C);
    }

    /* renamed from: J */
    public fq[] newArray(int i) {
        return new fq[i];
    }

    /* renamed from: p */
    public fq createFromParcel(Parcel parcel) {
        boolean z = false;
        String str = null;
        int B = a.B(parcel);
        int i = 1;
        int[] iArr = null;
        fn[] fnVarArr = null;
        String str2 = null;
        boolean z2 = false;
        String str3 = null;
        String str4 = null;
        int i2 = 0;
        while (parcel.dataPosition() < B) {
            int A = a.A(parcel);
            switch (a.ar(A)) {
                case 1:
                    str4 = a.o(parcel, A);
                    break;
                case 2:
                    str3 = a.o(parcel, A);
                    break;
                case 3:
                    z2 = a.c(parcel, A);
                    break;
                case 4:
                    i = a.g(parcel, A);
                    break;
                case 5:
                    z = a.c(parcel, A);
                    break;
                case 6:
                    str2 = a.o(parcel, A);
                    break;
                case 7:
                    fnVarArr = (fn[]) a.b(parcel, A, fn.CREATOR);
                    break;
                case 8:
                    iArr = a.u(parcel, A);
                    break;
                case 11:
                    str = a.o(parcel, A);
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
            return new fq(i2, str4, str3, z2, i, z, str2, fnVarArr, iArr, str);
        }
        throw new a.C0014a("Overread allowed size end=" + B, parcel);
    }
}
