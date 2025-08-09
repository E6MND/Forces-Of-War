package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.drive.StorageStats;

public class ap implements Parcelable.Creator<OnStorageStatsResponse> {
    static void a(OnStorageStatsResponse onStorageStatsResponse, Parcel parcel, int i) {
        int C = b.C(parcel);
        b.c(parcel, 1, onStorageStatsResponse.xJ);
        b.a(parcel, 2, (Parcelable) onStorageStatsResponse.JA, i, false);
        b.G(parcel, C);
    }

    /* renamed from: aq */
    public OnStorageStatsResponse createFromParcel(Parcel parcel) {
        int B = a.B(parcel);
        int i = 0;
        StorageStats storageStats = null;
        while (parcel.dataPosition() < B) {
            int A = a.A(parcel);
            switch (a.ar(A)) {
                case 1:
                    i = a.g(parcel, A);
                    break;
                case 2:
                    storageStats = (StorageStats) a.a(parcel, A, StorageStats.CREATOR);
                    break;
                default:
                    a.b(parcel, A);
                    break;
            }
        }
        if (parcel.dataPosition() == B) {
            return new OnStorageStatsResponse(i, storageStats);
        }
        throw new a.C0014a("Overread allowed size end=" + B, parcel);
    }

    /* renamed from: bm */
    public OnStorageStatsResponse[] newArray(int i) {
        return new OnStorageStatsResponse[i];
    }
}
