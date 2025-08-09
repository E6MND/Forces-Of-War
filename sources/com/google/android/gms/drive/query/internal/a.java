package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

public class a implements Parcelable.Creator<ComparisonFilter> {
    static void a(ComparisonFilter comparisonFilter, Parcel parcel, int i) {
        int C = b.C(parcel);
        b.c(parcel, 1000, comparisonFilter.xJ);
        b.a(parcel, 1, (Parcelable) comparisonFilter.KI, i, false);
        b.a(parcel, 2, (Parcelable) comparisonFilter.KJ, i, false);
        b.G(parcel, C);
    }

    /* renamed from: aF */
    public ComparisonFilter createFromParcel(Parcel parcel) {
        MetadataBundle metadataBundle;
        Operator operator;
        int i;
        MetadataBundle metadataBundle2 = null;
        int B = com.google.android.gms.common.internal.safeparcel.a.B(parcel);
        int i2 = 0;
        Operator operator2 = null;
        while (parcel.dataPosition() < B) {
            int A = com.google.android.gms.common.internal.safeparcel.a.A(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.ar(A)) {
                case 1:
                    i = i2;
                    Operator operator3 = (Operator) com.google.android.gms.common.internal.safeparcel.a.a(parcel, A, Operator.CREATOR);
                    metadataBundle = metadataBundle2;
                    operator = operator3;
                    break;
                case 2:
                    metadataBundle = (MetadataBundle) com.google.android.gms.common.internal.safeparcel.a.a(parcel, A, MetadataBundle.CREATOR);
                    operator = operator2;
                    i = i2;
                    break;
                case 1000:
                    MetadataBundle metadataBundle3 = metadataBundle2;
                    operator = operator2;
                    i = com.google.android.gms.common.internal.safeparcel.a.g(parcel, A);
                    metadataBundle = metadataBundle3;
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, A);
                    metadataBundle = metadataBundle2;
                    operator = operator2;
                    i = i2;
                    break;
            }
            i2 = i;
            operator2 = operator;
            metadataBundle2 = metadataBundle;
        }
        if (parcel.dataPosition() == B) {
            return new ComparisonFilter(i2, operator2, metadataBundle2);
        }
        throw new a.C0014a("Overread allowed size end=" + B, parcel);
    }

    /* renamed from: bB */
    public ComparisonFilter[] newArray(int i) {
        return new ComparisonFilter[i];
    }
}
