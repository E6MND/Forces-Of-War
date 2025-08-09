package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.drive.Contents;

public class f implements Parcelable.Creator<CloseContentsRequest> {
    static void a(CloseContentsRequest closeContentsRequest, Parcel parcel, int i) {
        int C = b.C(parcel);
        b.c(parcel, 1, closeContentsRequest.xJ);
        b.a(parcel, 2, (Parcelable) closeContentsRequest.It, i, false);
        b.a(parcel, 3, closeContentsRequest.Iw, false);
        b.G(parcel, C);
    }

    /* renamed from: V */
    public CloseContentsRequest createFromParcel(Parcel parcel) {
        Boolean d;
        Contents contents;
        int i;
        Boolean bool = null;
        int B = a.B(parcel);
        int i2 = 0;
        Contents contents2 = null;
        while (parcel.dataPosition() < B) {
            int A = a.A(parcel);
            switch (a.ar(A)) {
                case 1:
                    Boolean bool2 = bool;
                    contents = contents2;
                    i = a.g(parcel, A);
                    d = bool2;
                    break;
                case 2:
                    i = i2;
                    Contents contents3 = (Contents) a.a(parcel, A, Contents.CREATOR);
                    d = bool;
                    contents = contents3;
                    break;
                case 3:
                    d = a.d(parcel, A);
                    contents = contents2;
                    i = i2;
                    break;
                default:
                    a.b(parcel, A);
                    d = bool;
                    contents = contents2;
                    i = i2;
                    break;
            }
            i2 = i;
            contents2 = contents;
            bool = d;
        }
        if (parcel.dataPosition() == B) {
            return new CloseContentsRequest(i2, contents2, bool);
        }
        throw new a.C0014a("Overread allowed size end=" + B, parcel);
    }

    /* renamed from: aQ */
    public CloseContentsRequest[] newArray(int i) {
        return new CloseContentsRequest[i];
    }
}
