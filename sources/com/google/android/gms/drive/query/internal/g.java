package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

public class g implements Parcelable.Creator<InFilter> {
    static void a(InFilter inFilter, Parcel parcel, int i) {
        int C = b.C(parcel);
        b.c(parcel, 1000, inFilter.xJ);
        b.a(parcel, 1, (Parcelable) inFilter.KJ, i, false);
        b.G(parcel, C);
    }

    /* renamed from: aK */
    public InFilter createFromParcel(Parcel parcel) {
        int B = a.B(parcel);
        int i = 0;
        MetadataBundle metadataBundle = null;
        while (parcel.dataPosition() < B) {
            int A = a.A(parcel);
            switch (a.ar(A)) {
                case 1:
                    metadataBundle = (MetadataBundle) a.a(parcel, A, MetadataBundle.CREATOR);
                    break;
                case 1000:
                    i = a.g(parcel, A);
                    break;
                default:
                    a.b(parcel, A);
                    break;
            }
        }
        if (parcel.dataPosition() == B) {
            return new InFilter(i, metadataBundle);
        }
        throw new a.C0014a("Overread allowed size end=" + B, parcel);
    }

    /* renamed from: bG */
    public InFilter[] newArray(int i) {
        return new InFilter[i];
    }
}
