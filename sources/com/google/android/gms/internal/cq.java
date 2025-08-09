package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class cq implements Parcelable.Creator<cr> {
    static void a(cr crVar, Parcel parcel, int i) {
        int C = b.C(parcel);
        b.c(parcel, 1, crVar.versionCode);
        b.a(parcel, 2, crVar.aY(), false);
        b.a(parcel, 3, crVar.aZ(), false);
        b.a(parcel, 4, crVar.ba(), false);
        b.a(parcel, 5, crVar.bb(), false);
        b.G(parcel, C);
    }

    /* renamed from: g */
    public cr createFromParcel(Parcel parcel) {
        IBinder iBinder = null;
        int B = a.B(parcel);
        int i = 0;
        IBinder iBinder2 = null;
        IBinder iBinder3 = null;
        IBinder iBinder4 = null;
        while (parcel.dataPosition() < B) {
            int A = a.A(parcel);
            switch (a.ar(A)) {
                case 1:
                    i = a.g(parcel, A);
                    break;
                case 2:
                    iBinder4 = a.p(parcel, A);
                    break;
                case 3:
                    iBinder3 = a.p(parcel, A);
                    break;
                case 4:
                    iBinder2 = a.p(parcel, A);
                    break;
                case 5:
                    iBinder = a.p(parcel, A);
                    break;
                default:
                    a.b(parcel, A);
                    break;
            }
        }
        if (parcel.dataPosition() == B) {
            return new cr(i, iBinder4, iBinder3, iBinder2, iBinder);
        }
        throw new a.C0014a("Overread allowed size end=" + B, parcel);
    }

    /* renamed from: k */
    public cr[] newArray(int i) {
        return new cr[i];
    }
}
