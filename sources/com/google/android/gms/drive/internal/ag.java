package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.drive.Contents;

public class ag implements Parcelable.Creator<OnContentsResponse> {
    static void a(OnContentsResponse onContentsResponse, Parcel parcel, int i) {
        int C = b.C(parcel);
        b.c(parcel, 1, onContentsResponse.xJ);
        b.a(parcel, 2, (Parcelable) onContentsResponse.HD, i, false);
        b.G(parcel, C);
    }

    /* renamed from: ah */
    public OnContentsResponse createFromParcel(Parcel parcel) {
        int B = a.B(parcel);
        int i = 0;
        Contents contents = null;
        while (parcel.dataPosition() < B) {
            int A = a.A(parcel);
            switch (a.ar(A)) {
                case 1:
                    i = a.g(parcel, A);
                    break;
                case 2:
                    contents = (Contents) a.a(parcel, A, Contents.CREATOR);
                    break;
                default:
                    a.b(parcel, A);
                    break;
            }
        }
        if (parcel.dataPosition() == B) {
            return new OnContentsResponse(i, contents);
        }
        throw new a.C0014a("Overread allowed size end=" + B, parcel);
    }

    /* renamed from: bd */
    public OnContentsResponse[] newArray(int i) {
        return new OnContentsResponse[i];
    }
}
