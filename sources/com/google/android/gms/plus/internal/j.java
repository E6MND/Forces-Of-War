package com.google.android.gms.plus.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class j implements Parcelable.Creator<h> {
    static void a(h hVar, Parcel parcel, int i) {
        int C = b.C(parcel);
        b.a(parcel, 1, hVar.getAccountName(), false);
        b.c(parcel, 1000, hVar.getVersionCode());
        b.a(parcel, 2, hVar.jU(), false);
        b.a(parcel, 3, hVar.jV(), false);
        b.a(parcel, 4, hVar.jW(), false);
        b.a(parcel, 5, hVar.jX(), false);
        b.a(parcel, 6, hVar.jY(), false);
        b.a(parcel, 7, hVar.jZ(), false);
        b.a(parcel, 8, hVar.ka(), false);
        b.a(parcel, 9, (Parcelable) hVar.kb(), i, false);
        b.G(parcel, C);
    }

    /* renamed from: bD */
    public h createFromParcel(Parcel parcel) {
        PlusCommonExtras plusCommonExtras = null;
        int B = a.B(parcel);
        int i = 0;
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String[] strArr = null;
        String[] strArr2 = null;
        String[] strArr3 = null;
        String str5 = null;
        while (parcel.dataPosition() < B) {
            int A = a.A(parcel);
            switch (a.ar(A)) {
                case 1:
                    str5 = a.o(parcel, A);
                    break;
                case 2:
                    strArr3 = a.A(parcel, A);
                    break;
                case 3:
                    strArr2 = a.A(parcel, A);
                    break;
                case 4:
                    strArr = a.A(parcel, A);
                    break;
                case 5:
                    str4 = a.o(parcel, A);
                    break;
                case 6:
                    str3 = a.o(parcel, A);
                    break;
                case 7:
                    str2 = a.o(parcel, A);
                    break;
                case 8:
                    str = a.o(parcel, A);
                    break;
                case 9:
                    plusCommonExtras = (PlusCommonExtras) a.a(parcel, A, PlusCommonExtras.CREATOR);
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
            return new h(i, str5, strArr3, strArr2, strArr, str4, str3, str2, str, plusCommonExtras);
        }
        throw new a.C0014a("Overread allowed size end=" + B, parcel);
    }

    /* renamed from: da */
    public h[] newArray(int i) {
        return new h[i];
    }
}
