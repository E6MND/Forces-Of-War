package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class o implements Parcelable.Creator<DisconnectRequest> {
    static void a(DisconnectRequest disconnectRequest, Parcel parcel, int i) {
        int C = b.C(parcel);
        b.c(parcel, 1, disconnectRequest.xJ);
        b.G(parcel, C);
    }

    /* renamed from: aY */
    public DisconnectRequest[] newArray(int i) {
        return new DisconnectRequest[i];
    }

    /* renamed from: ac */
    public DisconnectRequest createFromParcel(Parcel parcel) {
        int B = a.B(parcel);
        int i = 0;
        while (parcel.dataPosition() < B) {
            int A = a.A(parcel);
            switch (a.ar(A)) {
                case 1:
                    i = a.g(parcel, A);
                    break;
                default:
                    a.b(parcel, A);
                    break;
            }
        }
        if (parcel.dataPosition() == B) {
            return new DisconnectRequest(i);
        }
        throw new a.C0014a("Overread allowed size end=" + B, parcel);
    }
}
