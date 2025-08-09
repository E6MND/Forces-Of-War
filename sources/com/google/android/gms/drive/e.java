package com.google.android.gms.drive;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class e implements Parcelable.Creator<StorageStats> {
    static void a(StorageStats storageStats, Parcel parcel, int i) {
        int C = b.C(parcel);
        b.c(parcel, 1, storageStats.xJ);
        b.a(parcel, 2, storageStats.HY);
        b.a(parcel, 3, storageStats.HZ);
        b.a(parcel, 4, storageStats.Ia);
        b.a(parcel, 5, storageStats.Ib);
        b.c(parcel, 6, storageStats.Ic);
        b.G(parcel, C);
    }

    /* renamed from: O */
    public StorageStats createFromParcel(Parcel parcel) {
        int i = 0;
        long j = 0;
        int B = a.B(parcel);
        long j2 = 0;
        long j3 = 0;
        long j4 = 0;
        int i2 = 0;
        while (parcel.dataPosition() < B) {
            int A = a.A(parcel);
            switch (a.ar(A)) {
                case 1:
                    i2 = a.g(parcel, A);
                    break;
                case 2:
                    j4 = a.i(parcel, A);
                    break;
                case 3:
                    j3 = a.i(parcel, A);
                    break;
                case 4:
                    j2 = a.i(parcel, A);
                    break;
                case 5:
                    j = a.i(parcel, A);
                    break;
                case 6:
                    i = a.g(parcel, A);
                    break;
                default:
                    a.b(parcel, A);
                    break;
            }
        }
        if (parcel.dataPosition() == B) {
            return new StorageStats(i2, j4, j3, j2, j, i);
        }
        throw new a.C0014a("Overread allowed size end=" + B, parcel);
    }

    /* renamed from: aI */
    public StorageStats[] newArray(int i) {
        return new StorageStats[i];
    }
}
