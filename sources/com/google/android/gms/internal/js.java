package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class js implements Parcelable.Creator<jr> {
    static void a(jr jrVar, Parcel parcel, int i) {
        int C = b.C(parcel);
        b.c(parcel, 1000, jrVar.xJ);
        b.a(parcel, 2, (Parcelable) jrVar.ja(), i, false);
        b.a(parcel, 3, jrVar.getInterval());
        b.c(parcel, 4, jrVar.getPriority());
        b.G(parcel, C);
    }

    /* renamed from: bx */
    public jr createFromParcel(Parcel parcel) {
        int B = a.B(parcel);
        int i = 0;
        jn jnVar = null;
        long j = jr.Wj;
        int i2 = 102;
        while (parcel.dataPosition() < B) {
            int A = a.A(parcel);
            switch (a.ar(A)) {
                case 2:
                    jnVar = (jn) a.a(parcel, A, jn.CREATOR);
                    break;
                case 3:
                    j = a.i(parcel, A);
                    break;
                case 4:
                    i2 = a.g(parcel, A);
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
            return new jr(i, jnVar, j, i2);
        }
        throw new a.C0014a("Overread allowed size end=" + B, parcel);
    }

    /* renamed from: cS */
    public jr[] newArray(int i) {
        return new jr[i];
    }
}
