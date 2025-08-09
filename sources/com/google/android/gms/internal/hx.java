package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.internal.hw;
import java.util.ArrayList;

public class hx implements Parcelable.Creator<hw> {
    static void a(hw hwVar, Parcel parcel, int i) {
        int C = b.C(parcel);
        b.c(parcel, 1, hwVar.getVersionCode());
        b.b(parcel, 2, hwVar.fy(), false);
        b.G(parcel, C);
    }

    /* renamed from: F */
    public hw createFromParcel(Parcel parcel) {
        int B = a.B(parcel);
        int i = 0;
        ArrayList arrayList = null;
        while (parcel.dataPosition() < B) {
            int A = a.A(parcel);
            switch (a.ar(A)) {
                case 1:
                    i = a.g(parcel, A);
                    break;
                case 2:
                    arrayList = a.c(parcel, A, hw.a.CREATOR);
                    break;
                default:
                    a.b(parcel, A);
                    break;
            }
        }
        if (parcel.dataPosition() == B) {
            return new hw(i, arrayList);
        }
        throw new a.C0014a("Overread allowed size end=" + B, parcel);
    }

    /* renamed from: au */
    public hw[] newArray(int i) {
        return new hw[i];
    }
}
