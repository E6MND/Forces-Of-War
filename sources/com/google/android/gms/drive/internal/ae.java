package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.drive.DriveId;

public class ae implements Parcelable.Creator<LoadRealtimeRequest> {
    static void a(LoadRealtimeRequest loadRealtimeRequest, Parcel parcel, int i) {
        int C = b.C(parcel);
        b.c(parcel, 1, loadRealtimeRequest.xJ);
        b.a(parcel, 2, (Parcelable) loadRealtimeRequest.Hw, i, false);
        b.a(parcel, 3, loadRealtimeRequest.Jp);
        b.G(parcel, C);
    }

    /* renamed from: ag */
    public LoadRealtimeRequest createFromParcel(Parcel parcel) {
        boolean c;
        DriveId driveId;
        int i;
        boolean z = false;
        int B = a.B(parcel);
        DriveId driveId2 = null;
        int i2 = 0;
        while (parcel.dataPosition() < B) {
            int A = a.A(parcel);
            switch (a.ar(A)) {
                case 1:
                    boolean z2 = z;
                    driveId = driveId2;
                    i = a.g(parcel, A);
                    c = z2;
                    break;
                case 2:
                    i = i2;
                    DriveId driveId3 = (DriveId) a.a(parcel, A, DriveId.CREATOR);
                    c = z;
                    driveId = driveId3;
                    break;
                case 3:
                    c = a.c(parcel, A);
                    driveId = driveId2;
                    i = i2;
                    break;
                default:
                    a.b(parcel, A);
                    c = z;
                    driveId = driveId2;
                    i = i2;
                    break;
            }
            i2 = i;
            driveId2 = driveId;
            z = c;
        }
        if (parcel.dataPosition() == B) {
            return new LoadRealtimeRequest(i2, driveId2, z);
        }
        throw new a.C0014a("Overread allowed size end=" + B, parcel);
    }

    /* renamed from: bc */
    public LoadRealtimeRequest[] newArray(int i) {
        return new LoadRealtimeRequest[i];
    }
}
