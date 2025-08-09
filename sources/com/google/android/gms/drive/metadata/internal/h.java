package com.google.android.gms.drive.metadata.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class h implements Parcelable.Creator<MetadataBundle> {
    static void a(MetadataBundle metadataBundle, Parcel parcel, int i) {
        int C = b.C(parcel);
        b.c(parcel, 1, metadataBundle.xJ);
        b.a(parcel, 2, metadataBundle.JP, false);
        b.G(parcel, C);
    }

    /* renamed from: aC */
    public MetadataBundle createFromParcel(Parcel parcel) {
        int B = a.B(parcel);
        int i = 0;
        Bundle bundle = null;
        while (parcel.dataPosition() < B) {
            int A = a.A(parcel);
            switch (a.ar(A)) {
                case 1:
                    i = a.g(parcel, A);
                    break;
                case 2:
                    bundle = a.q(parcel, A);
                    break;
                default:
                    a.b(parcel, A);
                    break;
            }
        }
        if (parcel.dataPosition() == B) {
            return new MetadataBundle(i, bundle);
        }
        throw new a.C0014a("Overread allowed size end=" + B, parcel);
    }

    /* renamed from: by */
    public MetadataBundle[] newArray(int i) {
        return new MetadataBundle[i];
    }
}
