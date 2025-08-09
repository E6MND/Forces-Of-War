package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class ak implements Parcelable.Creator<OnListEntriesResponse> {
    static void a(OnListEntriesResponse onListEntriesResponse, Parcel parcel, int i) {
        int C = b.C(parcel);
        b.c(parcel, 1, onListEntriesResponse.xJ);
        b.a(parcel, 2, (Parcelable) onListEntriesResponse.Jx, i, false);
        b.a(parcel, 3, onListEntriesResponse.IM);
        b.G(parcel, C);
    }

    /* renamed from: al */
    public OnListEntriesResponse createFromParcel(Parcel parcel) {
        boolean c;
        DataHolder dataHolder;
        int i;
        boolean z = false;
        int B = a.B(parcel);
        DataHolder dataHolder2 = null;
        int i2 = 0;
        while (parcel.dataPosition() < B) {
            int A = a.A(parcel);
            switch (a.ar(A)) {
                case 1:
                    boolean z2 = z;
                    dataHolder = dataHolder2;
                    i = a.g(parcel, A);
                    c = z2;
                    break;
                case 2:
                    i = i2;
                    DataHolder dataHolder3 = (DataHolder) a.a(parcel, A, DataHolder.CREATOR);
                    c = z;
                    dataHolder = dataHolder3;
                    break;
                case 3:
                    c = a.c(parcel, A);
                    dataHolder = dataHolder2;
                    i = i2;
                    break;
                default:
                    a.b(parcel, A);
                    c = z;
                    dataHolder = dataHolder2;
                    i = i2;
                    break;
            }
            i2 = i;
            dataHolder2 = dataHolder;
            z = c;
        }
        if (parcel.dataPosition() == B) {
            return new OnListEntriesResponse(i2, dataHolder2, z);
        }
        throw new a.C0014a("Overread allowed size end=" + B, parcel);
    }

    /* renamed from: bh */
    public OnListEntriesResponse[] newArray(int i) {
        return new OnListEntriesResponse[i];
    }
}
