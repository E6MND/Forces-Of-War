package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.CustomPropertyKey;

public class m implements Parcelable.Creator<DeleteCustomPropertyRequest> {
    static void a(DeleteCustomPropertyRequest deleteCustomPropertyRequest, Parcel parcel, int i) {
        int C = b.C(parcel);
        b.c(parcel, 1, deleteCustomPropertyRequest.xJ);
        b.a(parcel, 2, (Parcelable) deleteCustomPropertyRequest.Hw, i, false);
        b.a(parcel, 3, (Parcelable) deleteCustomPropertyRequest.IG, i, false);
        b.G(parcel, C);
    }

    /* renamed from: aW */
    public DeleteCustomPropertyRequest[] newArray(int i) {
        return new DeleteCustomPropertyRequest[i];
    }

    /* renamed from: aa */
    public DeleteCustomPropertyRequest createFromParcel(Parcel parcel) {
        CustomPropertyKey customPropertyKey;
        DriveId driveId;
        int i;
        CustomPropertyKey customPropertyKey2 = null;
        int B = a.B(parcel);
        int i2 = 0;
        DriveId driveId2 = null;
        while (parcel.dataPosition() < B) {
            int A = a.A(parcel);
            switch (a.ar(A)) {
                case 1:
                    CustomPropertyKey customPropertyKey3 = customPropertyKey2;
                    driveId = driveId2;
                    i = a.g(parcel, A);
                    customPropertyKey = customPropertyKey3;
                    break;
                case 2:
                    i = i2;
                    DriveId driveId3 = (DriveId) a.a(parcel, A, DriveId.CREATOR);
                    customPropertyKey = customPropertyKey2;
                    driveId = driveId3;
                    break;
                case 3:
                    customPropertyKey = (CustomPropertyKey) a.a(parcel, A, CustomPropertyKey.CREATOR);
                    driveId = driveId2;
                    i = i2;
                    break;
                default:
                    a.b(parcel, A);
                    customPropertyKey = customPropertyKey2;
                    driveId = driveId2;
                    i = i2;
                    break;
            }
            i2 = i;
            driveId2 = driveId;
            customPropertyKey2 = customPropertyKey;
        }
        if (parcel.dataPosition() == B) {
            return new DeleteCustomPropertyRequest(i2, driveId2, customPropertyKey2);
        }
        throw new a.C0014a("Overread allowed size end=" + B, parcel);
    }
}
