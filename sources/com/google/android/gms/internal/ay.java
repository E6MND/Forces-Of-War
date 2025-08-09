package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class ay implements Parcelable.Creator<ax> {
    static void a(ax axVar, Parcel parcel, int i) {
        int C = b.C(parcel);
        b.c(parcel, 1, axVar.versionCode);
        b.c(parcel, 2, axVar.mB);
        b.c(parcel, 3, axVar.backgroundColor);
        b.c(parcel, 4, axVar.mC);
        b.c(parcel, 5, axVar.mD);
        b.c(parcel, 6, axVar.mE);
        b.c(parcel, 7, axVar.mF);
        b.c(parcel, 8, axVar.mG);
        b.c(parcel, 9, axVar.mH);
        b.a(parcel, 10, axVar.mI, false);
        b.c(parcel, 11, axVar.mJ);
        b.a(parcel, 12, axVar.mK, false);
        b.c(parcel, 13, axVar.mL);
        b.c(parcel, 14, axVar.mM);
        b.a(parcel, 15, axVar.mN, false);
        b.G(parcel, C);
    }

    /* renamed from: d */
    public ax createFromParcel(Parcel parcel) {
        int B = a.B(parcel);
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        String str = null;
        int i10 = 0;
        String str2 = null;
        int i11 = 0;
        int i12 = 0;
        String str3 = null;
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
                    i3 = a.g(parcel, A);
                    break;
                case 4:
                    i4 = a.g(parcel, A);
                    break;
                case 5:
                    i5 = a.g(parcel, A);
                    break;
                case 6:
                    i6 = a.g(parcel, A);
                    break;
                case 7:
                    i7 = a.g(parcel, A);
                    break;
                case 8:
                    i8 = a.g(parcel, A);
                    break;
                case 9:
                    i9 = a.g(parcel, A);
                    break;
                case 10:
                    str = a.o(parcel, A);
                    break;
                case 11:
                    i10 = a.g(parcel, A);
                    break;
                case 12:
                    str2 = a.o(parcel, A);
                    break;
                case 13:
                    i11 = a.g(parcel, A);
                    break;
                case 14:
                    i12 = a.g(parcel, A);
                    break;
                case 15:
                    str3 = a.o(parcel, A);
                    break;
                default:
                    a.b(parcel, A);
                    break;
            }
        }
        if (parcel.dataPosition() == B) {
            return new ax(i, i2, i3, i4, i5, i6, i7, i8, i9, str, i10, str2, i11, i12, str3);
        }
        throw new a.C0014a("Overread allowed size end=" + B, parcel);
    }

    /* renamed from: f */
    public ax[] newArray(int i) {
        return new ax[i];
    }
}
