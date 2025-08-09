package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class al implements Parcelable.Creator<OnListParentsResponse> {
    static void a(OnListParentsResponse onListParentsResponse, Parcel parcel, int i) {
        int C = b.C(parcel);
        b.c(parcel, 1, onListParentsResponse.xJ);
        b.a(parcel, 2, (Parcelable) onListParentsResponse.Jy, i, false);
        b.G(parcel, C);
    }

    /* renamed from: am */
    public OnListParentsResponse createFromParcel(Parcel parcel) {
        int B = a.B(parcel);
        int i = 0;
        DataHolder dataHolder = null;
        while (parcel.dataPosition() < B) {
            int A = a.A(parcel);
            switch (a.ar(A)) {
                case 1:
                    i = a.g(parcel, A);
                    break;
                case 2:
                    dataHolder = (DataHolder) a.a(parcel, A, DataHolder.CREATOR);
                    break;
                default:
                    a.b(parcel, A);
                    break;
            }
        }
        if (parcel.dataPosition() == B) {
            return new OnListParentsResponse(i, dataHolder);
        }
        throw new a.C0014a("Overread allowed size end=" + B, parcel);
    }

    /* renamed from: bi */
    public OnListParentsResponse[] newArray(int i) {
        return new OnListParentsResponse[i];
    }
}
