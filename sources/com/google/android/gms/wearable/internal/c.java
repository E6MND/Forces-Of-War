package com.google.android.gms.wearable.internal;

import android.content.IntentFilter;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class c implements Parcelable.Creator<b> {
    static void a(b bVar, Parcel parcel, int i) {
        int C = b.C(parcel);
        b.c(parcel, 1, bVar.xJ);
        b.a(parcel, 2, bVar.nj(), false);
        b.a(parcel, 3, (T[]) bVar.alx, i, false);
        b.G(parcel, C);
    }

    /* renamed from: cv */
    public b createFromParcel(Parcel parcel) {
        IntentFilter[] intentFilterArr = null;
        int B = a.B(parcel);
        int i = 0;
        IBinder iBinder = null;
        while (parcel.dataPosition() < B) {
            int A = a.A(parcel);
            switch (a.ar(A)) {
                case 1:
                    i = a.g(parcel, A);
                    break;
                case 2:
                    iBinder = a.p(parcel, A);
                    break;
                case 3:
                    intentFilterArr = (IntentFilter[]) a.b(parcel, A, IntentFilter.CREATOR);
                    break;
                default:
                    a.b(parcel, A);
                    break;
            }
        }
        if (parcel.dataPosition() == B) {
            return new b(i, iBinder, intentFilterArr);
        }
        throw new a.C0014a("Overread allowed size end=" + B, parcel);
    }

    /* renamed from: ee */
    public b[] newArray(int i) {
        return new b[i];
    }
}
