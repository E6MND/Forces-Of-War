package com.google.android.gms.drive.internal;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.drive.DriveId;

public class a implements Parcelable.Creator<AddEventListenerRequest> {
    static void a(AddEventListenerRequest addEventListenerRequest, Parcel parcel, int i) {
        int C = b.C(parcel);
        b.c(parcel, 1, addEventListenerRequest.xJ);
        b.a(parcel, 2, (Parcelable) addEventListenerRequest.Hw, i, false);
        b.c(parcel, 3, addEventListenerRequest.In);
        b.a(parcel, 4, (Parcelable) addEventListenerRequest.Io, i, false);
        b.G(parcel, C);
    }

    /* renamed from: R */
    public AddEventListenerRequest createFromParcel(Parcel parcel) {
        PendingIntent pendingIntent;
        int i;
        DriveId driveId;
        int i2;
        PendingIntent pendingIntent2 = null;
        int i3 = 0;
        int B = com.google.android.gms.common.internal.safeparcel.a.B(parcel);
        DriveId driveId2 = null;
        int i4 = 0;
        while (parcel.dataPosition() < B) {
            int A = com.google.android.gms.common.internal.safeparcel.a.A(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.ar(A)) {
                case 1:
                    PendingIntent pendingIntent3 = pendingIntent2;
                    i = i3;
                    driveId = driveId2;
                    i2 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, A);
                    pendingIntent = pendingIntent3;
                    break;
                case 2:
                    i2 = i4;
                    int i5 = i3;
                    driveId = (DriveId) com.google.android.gms.common.internal.safeparcel.a.a(parcel, A, DriveId.CREATOR);
                    pendingIntent = pendingIntent2;
                    i = i5;
                    break;
                case 3:
                    driveId = driveId2;
                    i2 = i4;
                    PendingIntent pendingIntent4 = pendingIntent2;
                    i = com.google.android.gms.common.internal.safeparcel.a.g(parcel, A);
                    pendingIntent = pendingIntent4;
                    break;
                case 4:
                    pendingIntent = (PendingIntent) com.google.android.gms.common.internal.safeparcel.a.a(parcel, A, PendingIntent.CREATOR);
                    i = i3;
                    driveId = driveId2;
                    i2 = i4;
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, A);
                    pendingIntent = pendingIntent2;
                    i = i3;
                    driveId = driveId2;
                    i2 = i4;
                    break;
            }
            i4 = i2;
            driveId2 = driveId;
            i3 = i;
            pendingIntent2 = pendingIntent;
        }
        if (parcel.dataPosition() == B) {
            return new AddEventListenerRequest(i4, driveId2, i3, pendingIntent2);
        }
        throw new a.C0014a("Overread allowed size end=" + B, parcel);
    }

    /* renamed from: aM */
    public AddEventListenerRequest[] newArray(int i) {
        return new AddEventListenerRequest[i];
    }
}
