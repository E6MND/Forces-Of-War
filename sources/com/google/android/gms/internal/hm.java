package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.internal.gz;
import java.util.ArrayList;

public class hm implements Parcelable.Creator<gz.a> {
    static void a(gz.a aVar, Parcel parcel, int i) {
        int C = b.C(parcel);
        b.a(parcel, 1, aVar.getAccountName(), false);
        b.c(parcel, 1000, aVar.getVersionCode());
        b.a(parcel, 2, aVar.fg(), false);
        b.c(parcel, 3, aVar.ff());
        b.a(parcel, 4, aVar.fi(), false);
        b.G(parcel, C);
    }

    /* renamed from: aq */
    public gz.a[] newArray(int i) {
        return new gz.a[i];
    }

    /* renamed from: z */
    public gz.a createFromParcel(Parcel parcel) {
        int i = 0;
        String str = null;
        int B = a.B(parcel);
        ArrayList<String> arrayList = null;
        String str2 = null;
        int i2 = 0;
        while (parcel.dataPosition() < B) {
            int A = a.A(parcel);
            switch (a.ar(A)) {
                case 1:
                    str2 = a.o(parcel, A);
                    break;
                case 2:
                    arrayList = a.B(parcel, A);
                    break;
                case 3:
                    i = a.g(parcel, A);
                    break;
                case 4:
                    str = a.o(parcel, A);
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
            return new gz.a(i2, str2, arrayList, i, str);
        }
        throw new a.C0014a("Overread allowed size end=" + B, parcel);
    }
}
