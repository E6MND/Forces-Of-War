package com.google.android.gms.drive.realtime.internal.event;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class i implements Parcelable.Creator<ValuesRemovedDetails> {
    static void a(ValuesRemovedDetails valuesRemovedDetails, Parcel parcel, int i) {
        int C = b.C(parcel);
        b.c(parcel, 1, valuesRemovedDetails.xJ);
        b.c(parcel, 2, valuesRemovedDetails.mIndex);
        b.c(parcel, 3, valuesRemovedDetails.LC);
        b.c(parcel, 4, valuesRemovedDetails.LD);
        b.a(parcel, 5, valuesRemovedDetails.LM, false);
        b.c(parcel, 6, valuesRemovedDetails.LN);
        b.G(parcel, C);
    }

    /* renamed from: bY */
    public ValuesRemovedDetails[] newArray(int i) {
        return new ValuesRemovedDetails[i];
    }

    /* renamed from: bb */
    public ValuesRemovedDetails createFromParcel(Parcel parcel) {
        int i = 0;
        int B = a.B(parcel);
        String str = null;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        while (parcel.dataPosition() < B) {
            int A = a.A(parcel);
            switch (a.ar(A)) {
                case 1:
                    i5 = a.g(parcel, A);
                    break;
                case 2:
                    i4 = a.g(parcel, A);
                    break;
                case 3:
                    i3 = a.g(parcel, A);
                    break;
                case 4:
                    i2 = a.g(parcel, A);
                    break;
                case 5:
                    str = a.o(parcel, A);
                    break;
                case 6:
                    i = a.g(parcel, A);
                    break;
                default:
                    a.b(parcel, A);
                    break;
            }
        }
        if (parcel.dataPosition() == B) {
            return new ValuesRemovedDetails(i5, i4, i3, i2, str, i);
        }
        throw new a.C0014a("Overread allowed size end=" + B, parcel);
    }
}
