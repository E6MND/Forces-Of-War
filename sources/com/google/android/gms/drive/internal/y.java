package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class y implements Parcelable.Creator<GetDriveIdFromUniqueIdentifierRequest> {
    static void a(GetDriveIdFromUniqueIdentifierRequest getDriveIdFromUniqueIdentifierRequest, Parcel parcel, int i) {
        int C = b.C(parcel);
        b.c(parcel, 1, getDriveIdFromUniqueIdentifierRequest.xJ);
        b.a(parcel, 2, getDriveIdFromUniqueIdentifierRequest.Jm, false);
        b.a(parcel, 3, getDriveIdFromUniqueIdentifierRequest.Jn);
        b.G(parcel, C);
    }

    /* renamed from: aZ */
    public GetDriveIdFromUniqueIdentifierRequest[] newArray(int i) {
        return new GetDriveIdFromUniqueIdentifierRequest[i];
    }

    /* renamed from: ad */
    public GetDriveIdFromUniqueIdentifierRequest createFromParcel(Parcel parcel) {
        boolean z = false;
        int B = a.B(parcel);
        String str = null;
        int i = 0;
        while (parcel.dataPosition() < B) {
            int A = a.A(parcel);
            switch (a.ar(A)) {
                case 1:
                    i = a.g(parcel, A);
                    break;
                case 2:
                    str = a.o(parcel, A);
                    break;
                case 3:
                    z = a.c(parcel, A);
                    break;
                default:
                    a.b(parcel, A);
                    break;
            }
        }
        if (parcel.dataPosition() == B) {
            return new GetDriveIdFromUniqueIdentifierRequest(i, str, z);
        }
        throw new a.C0014a("Overread allowed size end=" + B, parcel);
    }
}
