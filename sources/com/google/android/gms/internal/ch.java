package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class ch implements Parcelable.Creator<ci> {
    static void a(ci ciVar, Parcel parcel, int i) {
        int C = b.C(parcel);
        b.c(parcel, 1, ciVar.versionCode);
        b.a(parcel, 2, (Parcelable) ciVar.ot, i, false);
        b.a(parcel, 3, ciVar.aP(), false);
        b.a(parcel, 4, ciVar.aQ(), false);
        b.a(parcel, 5, ciVar.aR(), false);
        b.a(parcel, 6, ciVar.aS(), false);
        b.a(parcel, 7, ciVar.oy, false);
        b.a(parcel, 8, ciVar.oz);
        b.a(parcel, 9, ciVar.oA, false);
        b.a(parcel, 10, ciVar.aU(), false);
        b.c(parcel, 11, ciVar.orientation);
        b.c(parcel, 12, ciVar.oC);
        b.a(parcel, 13, ciVar.nZ, false);
        b.a(parcel, 14, (Parcelable) ciVar.kO, i, false);
        b.a(parcel, 15, ciVar.aT(), false);
        b.a(parcel, 17, (Parcelable) ciVar.oF, i, false);
        b.a(parcel, 16, ciVar.oE, false);
        b.G(parcel, C);
    }

    /* renamed from: f */
    public ci createFromParcel(Parcel parcel) {
        int B = a.B(parcel);
        int i = 0;
        cf cfVar = null;
        IBinder iBinder = null;
        IBinder iBinder2 = null;
        IBinder iBinder3 = null;
        IBinder iBinder4 = null;
        String str = null;
        boolean z = false;
        String str2 = null;
        IBinder iBinder5 = null;
        int i2 = 0;
        int i3 = 0;
        String str3 = null;
        ew ewVar = null;
        IBinder iBinder6 = null;
        String str4 = null;
        w wVar = null;
        while (parcel.dataPosition() < B) {
            int A = a.A(parcel);
            switch (a.ar(A)) {
                case 1:
                    i = a.g(parcel, A);
                    break;
                case 2:
                    cfVar = (cf) a.a(parcel, A, cf.CREATOR);
                    break;
                case 3:
                    iBinder = a.p(parcel, A);
                    break;
                case 4:
                    iBinder2 = a.p(parcel, A);
                    break;
                case 5:
                    iBinder3 = a.p(parcel, A);
                    break;
                case 6:
                    iBinder4 = a.p(parcel, A);
                    break;
                case 7:
                    str = a.o(parcel, A);
                    break;
                case 8:
                    z = a.c(parcel, A);
                    break;
                case 9:
                    str2 = a.o(parcel, A);
                    break;
                case 10:
                    iBinder5 = a.p(parcel, A);
                    break;
                case 11:
                    i2 = a.g(parcel, A);
                    break;
                case 12:
                    i3 = a.g(parcel, A);
                    break;
                case 13:
                    str3 = a.o(parcel, A);
                    break;
                case 14:
                    ewVar = (ew) a.a(parcel, A, ew.CREATOR);
                    break;
                case 15:
                    iBinder6 = a.p(parcel, A);
                    break;
                case 16:
                    str4 = a.o(parcel, A);
                    break;
                case 17:
                    wVar = (w) a.a(parcel, A, w.CREATOR);
                    break;
                default:
                    a.b(parcel, A);
                    break;
            }
        }
        if (parcel.dataPosition() == B) {
            return new ci(i, cfVar, iBinder, iBinder2, iBinder3, iBinder4, str, z, str2, iBinder5, i2, i3, str3, ewVar, iBinder6, str4, wVar);
        }
        throw new a.C0014a("Overread allowed size end=" + B, parcel);
    }

    /* renamed from: j */
    public ci[] newArray(int i) {
        return new ci[i];
    }
}
