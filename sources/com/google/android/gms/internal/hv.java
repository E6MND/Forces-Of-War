package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class hv implements Parcelable.Creator<hu> {
    static void a(hu huVar, Parcel parcel, int i) {
        int C = b.C(parcel);
        b.c(parcel, 1, huVar.getVersionCode());
        b.a(parcel, 2, (Parcelable) huVar.fw(), i, false);
        b.G(parcel, C);
    }

    /* renamed from: E */
    public hu createFromParcel(Parcel parcel) {
        int B = a.B(parcel);
        int i = 0;
        hw hwVar = null;
        while (parcel.dataPosition() < B) {
            int A = a.A(parcel);
            switch (a.ar(A)) {
                case 1:
                    i = a.g(parcel, A);
                    break;
                case 2:
                    hwVar = (hw) a.a(parcel, A, hw.CREATOR);
                    break;
                default:
                    a.b(parcel, A);
                    break;
            }
        }
        if (parcel.dataPosition() == B) {
            return new hu(i, hwVar);
        }
        throw new a.C0014a("Overread allowed size end=" + B, parcel);
    }

    /* renamed from: at */
    public hu[] newArray(int i) {
        return new hu[i];
    }
}
