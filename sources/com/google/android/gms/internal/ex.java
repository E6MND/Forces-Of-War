package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class ex implements Parcelable.Creator<ew> {
    static void a(ew ewVar, Parcel parcel, int i) {
        int C = b.C(parcel);
        b.c(parcel, 1, ewVar.versionCode);
        b.a(parcel, 2, ewVar.st, false);
        b.c(parcel, 3, ewVar.su);
        b.c(parcel, 4, ewVar.sv);
        b.a(parcel, 5, ewVar.sw);
        b.G(parcel, C);
    }

    /* renamed from: j */
    public ew createFromParcel(Parcel parcel) {
        boolean z = false;
        int B = a.B(parcel);
        String str = null;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (parcel.dataPosition() < B) {
            int A = a.A(parcel);
            switch (a.ar(A)) {
                case 1:
                    i3 = a.g(parcel, A);
                    break;
                case 2:
                    str = a.o(parcel, A);
                    break;
                case 3:
                    i2 = a.g(parcel, A);
                    break;
                case 4:
                    i = a.g(parcel, A);
                    break;
                case 5:
                    z = a.c(parcel, A);
                    break;
                default:
                    a.b(parcel, A);
                    break;
            }
        }
        if (parcel.dataPosition() == B) {
            return new ew(i3, str, i2, i, z);
        }
        throw new a.C0014a("Overread allowed size end=" + B, parcel);
    }

    /* renamed from: q */
    public ew[] newArray(int i) {
        return new ew[i];
    }
}
