package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class kc implements Parcelable.Creator<kb> {
    static void a(kb kbVar, Parcel parcel, int i) {
        int C = b.C(parcel);
        b.a(parcel, 1, kbVar.YS, false);
        b.c(parcel, 1000, kbVar.versionCode);
        b.a(parcel, 2, kbVar.YT, false);
        b.G(parcel, C);
    }

    /* renamed from: bB */
    public kb createFromParcel(Parcel parcel) {
        String str = null;
        int B = a.B(parcel);
        int i = 0;
        String str2 = null;
        while (parcel.dataPosition() < B) {
            int A = a.A(parcel);
            switch (a.ar(A)) {
                case 1:
                    str2 = a.o(parcel, A);
                    break;
                case 2:
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
            return new kb(i, str2, str);
        }
        throw new a.C0014a("Overread allowed size end=" + B, parcel);
    }

    /* renamed from: cW */
    public kb[] newArray(int i) {
        return new kb[i];
    }
}
