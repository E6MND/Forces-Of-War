package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.drive.DriveId;
import java.util.ArrayList;

public class av implements Parcelable.Creator<SetResourceParentsRequest> {
    static void a(SetResourceParentsRequest setResourceParentsRequest, Parcel parcel, int i) {
        int C = b.C(parcel);
        b.c(parcel, 1, setResourceParentsRequest.xJ);
        b.a(parcel, 2, (Parcelable) setResourceParentsRequest.JC, i, false);
        b.b(parcel, 3, setResourceParentsRequest.JD, false);
        b.G(parcel, C);
    }

    /* renamed from: aw */
    public SetResourceParentsRequest createFromParcel(Parcel parcel) {
        ArrayList<DriveId> c;
        DriveId driveId;
        int i;
        ArrayList<DriveId> arrayList = null;
        int B = a.B(parcel);
        int i2 = 0;
        DriveId driveId2 = null;
        while (parcel.dataPosition() < B) {
            int A = a.A(parcel);
            switch (a.ar(A)) {
                case 1:
                    ArrayList<DriveId> arrayList2 = arrayList;
                    driveId = driveId2;
                    i = a.g(parcel, A);
                    c = arrayList2;
                    break;
                case 2:
                    i = i2;
                    DriveId driveId3 = (DriveId) a.a(parcel, A, DriveId.CREATOR);
                    c = arrayList;
                    driveId = driveId3;
                    break;
                case 3:
                    c = a.c(parcel, A, DriveId.CREATOR);
                    driveId = driveId2;
                    i = i2;
                    break;
                default:
                    a.b(parcel, A);
                    c = arrayList;
                    driveId = driveId2;
                    i = i2;
                    break;
            }
            i2 = i;
            driveId2 = driveId;
            arrayList = c;
        }
        if (parcel.dataPosition() == B) {
            return new SetResourceParentsRequest(i2, driveId2, arrayList);
        }
        throw new a.C0014a("Overread allowed size end=" + B, parcel);
    }

    /* renamed from: bs */
    public SetResourceParentsRequest[] newArray(int i) {
        return new SetResourceParentsRequest[i];
    }
}
