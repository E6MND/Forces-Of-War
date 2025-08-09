package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class an implements Parcelable.Creator<am> {
    static void a(am amVar, Parcel parcel, int i) {
        int C = b.C(parcel);
        b.c(parcel, 1, amVar.versionCode);
        b.a(parcel, 2, amVar.mc, false);
        b.c(parcel, 3, amVar.height);
        b.c(parcel, 4, amVar.heightPixels);
        b.a(parcel, 5, amVar.md);
        b.c(parcel, 6, amVar.width);
        b.c(parcel, 7, amVar.widthPixels);
        b.a(parcel, 8, (T[]) amVar.me, i, false);
        b.G(parcel, C);
    }

    /* renamed from: c */
    public am createFromParcel(Parcel parcel) {
        am[] amVarArr = null;
        int i = 0;
        int B = a.B(parcel);
        int i2 = 0;
        boolean z = false;
        int i3 = 0;
        int i4 = 0;
        String str = null;
        int i5 = 0;
        while (parcel.dataPosition() < B) {
            int A = a.A(parcel);
            switch (a.ar(A)) {
                case 1:
                    i5 = a.g(parcel, A);
                    break;
                case 2:
                    str = a.o(parcel, A);
                    break;
                case 3:
                    i4 = a.g(parcel, A);
                    break;
                case 4:
                    i3 = a.g(parcel, A);
                    break;
                case 5:
                    z = a.c(parcel, A);
                    break;
                case 6:
                    i2 = a.g(parcel, A);
                    break;
                case 7:
                    i = a.g(parcel, A);
                    break;
                case 8:
                    amVarArr = (am[]) a.b(parcel, A, am.CREATOR);
                    break;
                default:
                    a.b(parcel, A);
                    break;
            }
        }
        if (parcel.dataPosition() == B) {
            return new am(i5, str, i4, i3, z, i2, i, amVarArr);
        }
        throw new a.C0014a("Overread allowed size end=" + B, parcel);
    }

    /* renamed from: d */
    public am[] newArray(int i) {
        return new am[i];
    }
}
