package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class fo implements Parcelable.Creator<fn> {
    static void a(fn fnVar, Parcel parcel, int i) {
        int C = b.C(parcel);
        b.c(parcel, 1, fnVar.id);
        b.c(parcel, 1000, fnVar.xJ);
        b.a(parcel, 2, fnVar.xV, false);
        b.G(parcel, C);
    }

    /* renamed from: G */
    public fn[] newArray(int i) {
        return new fn[i];
    }

    /* renamed from: o */
    public fn createFromParcel(Parcel parcel) {
        int i = 0;
        int B = a.B(parcel);
        Bundle bundle = null;
        int i2 = 0;
        while (parcel.dataPosition() < B) {
            int A = a.A(parcel);
            switch (a.ar(A)) {
                case 1:
                    i = a.g(parcel, A);
                    break;
                case 2:
                    bundle = a.q(parcel, A);
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
            return new fn(i2, i, bundle);
        }
        throw new a.C0014a("Overread allowed size end=" + B, parcel);
    }
}
