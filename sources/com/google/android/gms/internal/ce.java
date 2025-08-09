package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class ce implements Parcelable.Creator<cf> {
    static void a(cf cfVar, Parcel parcel, int i) {
        int C = b.C(parcel);
        b.c(parcel, 1, cfVar.versionCode);
        b.a(parcel, 2, cfVar.nY, false);
        b.a(parcel, 3, cfVar.nZ, false);
        b.a(parcel, 4, cfVar.mimeType, false);
        b.a(parcel, 5, cfVar.packageName, false);
        b.a(parcel, 6, cfVar.oa, false);
        b.a(parcel, 7, cfVar.ob, false);
        b.a(parcel, 8, cfVar.oc, false);
        b.G(parcel, C);
    }

    /* renamed from: e */
    public cf createFromParcel(Parcel parcel) {
        String str = null;
        int B = a.B(parcel);
        int i = 0;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        String str6 = null;
        String str7 = null;
        while (parcel.dataPosition() < B) {
            int A = a.A(parcel);
            switch (a.ar(A)) {
                case 1:
                    i = a.g(parcel, A);
                    break;
                case 2:
                    str7 = a.o(parcel, A);
                    break;
                case 3:
                    str6 = a.o(parcel, A);
                    break;
                case 4:
                    str5 = a.o(parcel, A);
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
                default:
                    a.b(parcel, A);
                    break;
            }
        }
        if (parcel.dataPosition() == B) {
            return new cf(i, str7, str6, str5, str4, str3, str2, str);
        }
        throw new a.C0014a("Overread allowed size end=" + B, parcel);
    }

    /* renamed from: i */
    public cf[] newArray(int i) {
        return new cf[i];
    }
}
