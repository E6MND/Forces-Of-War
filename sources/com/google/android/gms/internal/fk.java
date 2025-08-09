package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class fk implements Parcelable.Creator<fj> {
    static void a(fj fjVar, Parcel parcel, int i) {
        int C = b.C(parcel);
        b.a(parcel, 1, fjVar.xN, false);
        b.c(parcel, 1000, fjVar.xJ);
        b.a(parcel, 2, fjVar.xO, false);
        b.a(parcel, 3, fjVar.xP, false);
        b.G(parcel, C);
    }

    /* renamed from: E */
    public fj[] newArray(int i) {
        return new fj[i];
    }

    /* renamed from: m */
    public fj createFromParcel(Parcel parcel) {
        String str = null;
        int B = a.B(parcel);
        int i = 0;
        String str2 = null;
        String str3 = null;
        while (parcel.dataPosition() < B) {
            int A = a.A(parcel);
            switch (a.ar(A)) {
                case 1:
                    str3 = a.o(parcel, A);
                    break;
                case 2:
                    str2 = a.o(parcel, A);
                    break;
                case 3:
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
            return new fj(i, str3, str2, str);
        }
        throw new a.C0014a("Overread allowed size end=" + B, parcel);
    }
}
