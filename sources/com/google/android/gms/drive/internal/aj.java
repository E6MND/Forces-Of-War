package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.drive.events.ChangeEvent;
import com.google.android.gms.drive.events.FileConflictEvent;

public class aj implements Parcelable.Creator<OnEventResponse> {
    static void a(OnEventResponse onEventResponse, Parcel parcel, int i) {
        int C = b.C(parcel);
        b.c(parcel, 1, onEventResponse.xJ);
        b.c(parcel, 2, onEventResponse.In);
        b.a(parcel, 3, (Parcelable) onEventResponse.Jv, i, false);
        b.a(parcel, 4, (Parcelable) onEventResponse.Jw, i, false);
        b.G(parcel, C);
    }

    /* renamed from: ak */
    public OnEventResponse createFromParcel(Parcel parcel) {
        FileConflictEvent fileConflictEvent;
        ChangeEvent changeEvent;
        int i;
        int i2;
        FileConflictEvent fileConflictEvent2 = null;
        int i3 = 0;
        int B = a.B(parcel);
        ChangeEvent changeEvent2 = null;
        int i4 = 0;
        while (parcel.dataPosition() < B) {
            int A = a.A(parcel);
            switch (a.ar(A)) {
                case 1:
                    FileConflictEvent fileConflictEvent3 = fileConflictEvent2;
                    changeEvent = changeEvent2;
                    i = i3;
                    i2 = a.g(parcel, A);
                    fileConflictEvent = fileConflictEvent3;
                    break;
                case 2:
                    i2 = i4;
                    ChangeEvent changeEvent3 = changeEvent2;
                    i = a.g(parcel, A);
                    fileConflictEvent = fileConflictEvent2;
                    changeEvent = changeEvent3;
                    break;
                case 3:
                    i = i3;
                    i2 = i4;
                    FileConflictEvent fileConflictEvent4 = fileConflictEvent2;
                    changeEvent = (ChangeEvent) a.a(parcel, A, ChangeEvent.CREATOR);
                    fileConflictEvent = fileConflictEvent4;
                    break;
                case 4:
                    fileConflictEvent = (FileConflictEvent) a.a(parcel, A, FileConflictEvent.CREATOR);
                    changeEvent = changeEvent2;
                    i = i3;
                    i2 = i4;
                    break;
                default:
                    a.b(parcel, A);
                    fileConflictEvent = fileConflictEvent2;
                    changeEvent = changeEvent2;
                    i = i3;
                    i2 = i4;
                    break;
            }
            i4 = i2;
            i3 = i;
            changeEvent2 = changeEvent;
            fileConflictEvent2 = fileConflictEvent;
        }
        if (parcel.dataPosition() == B) {
            return new OnEventResponse(i4, i3, changeEvent2, fileConflictEvent2);
        }
        throw new a.C0014a("Overread allowed size end=" + B, parcel);
    }

    /* renamed from: bg */
    public OnEventResponse[] newArray(int i) {
        return new OnEventResponse[i];
    }
}
