package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class k implements Parcelable.Creator<Operator> {
    static void a(Operator operator, Parcel parcel, int i) {
        int C = b.C(parcel);
        b.c(parcel, 1000, operator.xJ);
        b.a(parcel, 1, operator.mTag, false);
        b.G(parcel, C);
    }

    /* renamed from: aO */
    public Operator createFromParcel(Parcel parcel) {
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
            return new Operator(i, str);
        }
        throw new a.C0014a("Overread allowed size end=" + B, parcel);
    }

    /* renamed from: bK */
    public Operator[] newArray(int i) {
        return new Operator[i];
    }
}
