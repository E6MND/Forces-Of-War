package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class jj implements Parcelable.Creator<ji> {
    static void a(ji jiVar, Parcel parcel, int i) {
        int C = b.C(parcel);
        b.a(parcel, 1, jiVar.getRequestId(), false);
        b.c(parcel, 1000, jiVar.getVersionCode());
        b.a(parcel, 2, jiVar.getExpirationTime());
        b.a(parcel, 3, jiVar.iV());
        b.a(parcel, 4, jiVar.getLatitude());
        b.a(parcel, 5, jiVar.getLongitude());
        b.a(parcel, 6, jiVar.iW());
        b.c(parcel, 7, jiVar.iX());
        b.c(parcel, 8, jiVar.getNotificationResponsiveness());
        b.c(parcel, 9, jiVar.iY());
        b.G(parcel, C);
    }

    /* renamed from: bt */
    public ji createFromParcel(Parcel parcel) {
        int B = a.B(parcel);
        int i = 0;
        String str = null;
        int i2 = 0;
        short s = 0;
        double d = 0.0d;
        double d2 = 0.0d;
        float f = 0.0f;
        long j = 0;
        int i3 = 0;
        int i4 = -1;
        while (parcel.dataPosition() < B) {
            int A = a.A(parcel);
            switch (a.ar(A)) {
                case 1:
                    str = a.o(parcel, A);
                    break;
                case 2:
                    j = a.i(parcel, A);
                    break;
                case 3:
                    s = a.f(parcel, A);
                    break;
                case 4:
                    d = a.m(parcel, A);
                    break;
                case 5:
                    d2 = a.m(parcel, A);
                    break;
                case 6:
                    f = a.l(parcel, A);
                    break;
                case 7:
                    i2 = a.g(parcel, A);
                    break;
                case 8:
                    i3 = a.g(parcel, A);
                    break;
                case 9:
                    i4 = a.g(parcel, A);
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
            return new ji(i, str, i2, s, d, d2, f, j, i3, i4);
        }
        throw new a.C0014a("Overread allowed size end=" + B, parcel);
    }

    /* renamed from: cO */
    public ji[] newArray(int i) {
        return new ji[i];
    }
}
