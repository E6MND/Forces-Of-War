package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class i implements Parcelable.Creator<LineItem> {
    static void a(LineItem lineItem, Parcel parcel, int i) {
        int C = b.C(parcel);
        b.c(parcel, 1, lineItem.getVersionCode());
        b.a(parcel, 2, lineItem.description, false);
        b.a(parcel, 3, lineItem.ajb, false);
        b.a(parcel, 4, lineItem.ajc, false);
        b.a(parcel, 5, lineItem.aiH, false);
        b.c(parcel, 6, lineItem.ajd);
        b.a(parcel, 7, lineItem.aiI, false);
        b.G(parcel, C);
    }

    /* renamed from: bX */
    public LineItem createFromParcel(Parcel parcel) {
        int i = 0;
        String str = null;
        int B = a.B(parcel);
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        int i2 = 0;
        while (parcel.dataPosition() < B) {
            int A = a.A(parcel);
            switch (a.ar(A)) {
                case 1:
                    i2 = a.g(parcel, A);
                    break;
                case 2:
                    str5 = a.o(parcel, A);
                    break;
                case 3:
                    str4 = a.o(parcel, A);
                    break;
                case 4:
                    str3 = a.o(parcel, A);
                    break;
                case 5:
                    str2 = a.o(parcel, A);
                    break;
                case 6:
                    i = a.g(parcel, A);
                    break;
                case 7:
                    str = a.o(parcel, A);
                    break;
                default:
                    a.b(parcel, A);
                    break;
            }
        }
        if (parcel.dataPosition() == B) {
            return new LineItem(i2, str5, str4, str3, str2, i, str);
        }
        throw new a.C0014a("Overread allowed size end=" + B, parcel);
    }

    /* renamed from: dD */
    public LineItem[] newArray(int i) {
        return new LineItem[i];
    }
}
