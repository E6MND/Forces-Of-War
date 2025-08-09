package com.google.android.gms.drive.events;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.drive.DriveId;

public class a implements Parcelable.Creator<ChangeEvent> {
    static void a(ChangeEvent changeEvent, Parcel parcel, int i) {
        int C = b.C(parcel);
        b.c(parcel, 1, changeEvent.xJ);
        b.a(parcel, 2, (Parcelable) changeEvent.Hw, i, false);
        b.c(parcel, 3, changeEvent.Id);
        b.G(parcel, C);
    }

    /* renamed from: P */
    public ChangeEvent createFromParcel(Parcel parcel) {
        int g;
        DriveId driveId;
        int i;
        int i2 = 0;
        int B = com.google.android.gms.common.internal.safeparcel.a.B(parcel);
        DriveId driveId2 = null;
        int i3 = 0;
        while (parcel.dataPosition() < B) {
            int A = com.google.android.gms.common.internal.safeparcel.a.A(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.ar(A)) {
                case 1:
                    int i4 = i2;
                    driveId = driveId2;
                    i = com.google.android.gms.common.internal.safeparcel.a.g(parcel, A);
                    g = i4;
                    break;
                case 2:
                    i = i3;
                    DriveId driveId3 = (DriveId) com.google.android.gms.common.internal.safeparcel.a.a(parcel, A, DriveId.CREATOR);
                    g = i2;
                    driveId = driveId3;
                    break;
                case 3:
                    g = com.google.android.gms.common.internal.safeparcel.a.g(parcel, A);
                    driveId = driveId2;
                    i = i3;
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, A);
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
            return new ChangeEvent(i3, driveId2, i2);
        }
        throw new a.C0014a("Overread allowed size end=" + B, parcel);
    }

    /* renamed from: aJ */
    public ChangeEvent[] newArray(int i) {
        return new ChangeEvent[i];
    }
}
