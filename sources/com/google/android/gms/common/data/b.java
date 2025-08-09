package com.google.android.gms.common.data;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;

public class b implements Parcelable.Creator<a> {
    static void a(a aVar, Parcel parcel, int i) {
        int C = com.google.android.gms.common.internal.safeparcel.b.C(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, aVar.xJ);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, (Parcelable) aVar.Et, i, false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 3, aVar.AQ);
        com.google.android.gms.common.internal.safeparcel.b.G(parcel, C);
    }

    /* renamed from: ab */
    public a[] newArray(int i) {
        return new a[i];
    }

    /* renamed from: w */
    public a createFromParcel(Parcel parcel) {
        int g;
        ParcelFileDescriptor parcelFileDescriptor;
        int i;
        int i2 = 0;
        int B = a.B(parcel);
        ParcelFileDescriptor parcelFileDescriptor2 = null;
        int i3 = 0;
        while (parcel.dataPosition() < B) {
            int A = a.A(parcel);
            switch (a.ar(A)) {
                case 1:
                    int i4 = i2;
                    parcelFileDescriptor = parcelFileDescriptor2;
                    i = a.g(parcel, A);
                    g = i4;
                    break;
                case 2:
                    i = i3;
                    ParcelFileDescriptor parcelFileDescriptor3 = (ParcelFileDescriptor) a.a(parcel, A, ParcelFileDescriptor.CREATOR);
                    g = i2;
                    parcelFileDescriptor = parcelFileDescriptor3;
                    break;
                case 3:
                    g = a.g(parcel, A);
                    parcelFileDescriptor = parcelFileDescriptor2;
                    i = i3;
                    break;
                default:
                    a.b(parcel, A);
                    g = i2;
                    parcelFileDescriptor = parcelFileDescriptor2;
                    i = i3;
                    break;
            }
            i3 = i;
            parcelFileDescriptor2 = parcelFileDescriptor;
            i2 = g;
        }
        if (parcel.dataPosition() == B) {
            return new a(i3, parcelFileDescriptor2, i2);
        }
        throw new a.C0014a("Overread allowed size end=" + B, parcel);
    }
}
