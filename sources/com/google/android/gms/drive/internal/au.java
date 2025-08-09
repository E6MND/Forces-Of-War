package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.drive.DriveId;

public class au implements Parcelable.Creator<RemoveEventListenerRequest> {
    static void a(RemoveEventListenerRequest removeEventListenerRequest, Parcel parcel, int i) {
        int C = b.C(parcel);
        b.c(parcel, 1, removeEventListenerRequest.xJ);
        b.a(parcel, 2, (Parcelable) removeEventListenerRequest.Hw, i, false);
        b.c(parcel, 3, removeEventListenerRequest.In);
        b.G(parcel, C);
    }

    /* renamed from: av */
    public RemoveEventListenerRequest createFromParcel(Parcel parcel) {
        int g;
        DriveId driveId;
        int i;
        int i2 = 0;
        int B = a.B(parcel);
        DriveId driveId2 = null;
        int i3 = 0;
        while (parcel.dataPosition() < B) {
            int A = a.A(parcel);
            switch (a.ar(A)) {
                case 1:
                    int i4 = i2;
                    driveId = driveId2;
                    i = a.g(parcel, A);
                    g = i4;
                    break;
                case 2:
                    i = i3;
                    DriveId driveId3 = (DriveId) a.a(parcel, A, DriveId.CREATOR);
                    g = i2;
                    driveId = driveId3;
                    break;
                case 3:
                    g = a.g(parcel, A);
                    driveId = driveId2;
                    i = i3;
                    break;
                default:
                    a.b(parcel, A);
                    g = i2;
                    driveId = driveId2;
                    i = i3;
                    break;
            }
            i3 = i;
            driveId2 = driveId;
            i2 = g;
        }
        if (parcel.dataPosition() == B) {
            return new RemoveEventListenerRequest(i3, driveId2, i2);
        }
        throw new a.C0014a("Overread allowed size end=" + B, parcel);
    }

    /* renamed from: br */
    public RemoveEventListenerRequest[] newArray(int i) {
        return new RemoveEventListenerRequest[i];
    }
}
