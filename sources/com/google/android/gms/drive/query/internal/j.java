package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class j implements Parcelable.Creator<NotFilter> {
    static void a(NotFilter notFilter, Parcel parcel, int i) {
        int C = b.C(parcel);
        b.c(parcel, 1000, notFilter.xJ);
        b.a(parcel, 1, (Parcelable) notFilter.KW, i, false);
        b.G(parcel, C);
    }

    /* renamed from: aN */
    public NotFilter createFromParcel(Parcel parcel) {
        int B = a.B(parcel);
        int i = 0;
        FilterHolder filterHolder = null;
        while (parcel.dataPosition() < B) {
            int A = a.A(parcel);
            switch (a.ar(A)) {
                case 1:
                    filterHolder = (FilterHolder) a.a(parcel, A, FilterHolder.CREATOR);
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
            return new NotFilter(i, filterHolder);
        }
        throw new a.C0014a("Overread allowed size end=" + B, parcel);
    }

    /* renamed from: bJ */
    public NotFilter[] newArray(int i) {
        return new NotFilter[i];
    }
}
