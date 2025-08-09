package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class ju implements Parcelable.Creator<jt> {
    static void a(jt jtVar, Parcel parcel, int i) {
        int C = b.C(parcel);
        b.a(parcel, 1, jtVar.qU, false);
        b.c(parcel, 1000, jtVar.xJ);
        b.G(parcel, C);
    }

    /* renamed from: by */
    public jt createFromParcel(Parcel parcel) {
        int B = a.B(parcel);
        int i = 0;
        String str = null;
        while (parcel.dataPosition() < B) {
            int A = a.A(parcel);
            switch (a.ar(A)) {
                case 1:
                    str = a.o(parcel, A);
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
            return new jt(i, str);
        }
        throw new a.C0014a("Overread allowed size end=" + B, parcel);
    }

    /* renamed from: cT */
    public jt[] newArray(int i) {
        return new jt[i];
    }
}
