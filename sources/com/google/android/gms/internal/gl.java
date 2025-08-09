package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class gl implements Parcelable.Creator<gk> {
    static void a(gk gkVar, Parcel parcel, int i) {
        int C = b.C(parcel);
        b.c(parcel, 1, gkVar.getVersionCode());
        b.a(parcel, 2, gkVar.ec());
        b.a(parcel, 3, gkVar.ei());
        b.c(parcel, 4, gkVar.ej());
        b.G(parcel, C);
    }

    /* renamed from: Y */
    public gk[] newArray(int i) {
        return new gk[i];
    }

    /* renamed from: v */
    public gk createFromParcel(Parcel parcel) {
        int i = 0;
        int B = a.B(parcel);
        double d = 0.0d;
        boolean z = false;
        int i2 = 0;
        while (parcel.dataPosition() < B) {
            int A = a.A(parcel);
            switch (a.ar(A)) {
                case 1:
                    i2 = a.g(parcel, A);
                    break;
                case 2:
                    d = a.m(parcel, A);
                    break;
                case 3:
                    z = a.c(parcel, A);
                    break;
                case 4:
                    i = a.g(parcel, A);
                    break;
                default:
                    a.b(parcel, A);
                    break;
            }
        }
        if (parcel.dataPosition() == B) {
            return new gk(i2, d, z, i);
        }
        throw new a.C0014a("Overread allowed size end=" + B, parcel);
    }
}
