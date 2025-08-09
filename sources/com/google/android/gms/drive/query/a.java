package com.google.android.gms.drive.query;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.drive.query.internal.LogicalFilter;

public class a implements Parcelable.Creator<Query> {
    static void a(Query query, Parcel parcel, int i) {
        int C = b.C(parcel);
        b.c(parcel, 1000, query.xJ);
        b.a(parcel, 1, (Parcelable) query.KB, i, false);
        b.a(parcel, 3, query.KC, false);
        b.a(parcel, 4, (Parcelable) query.KD, i, false);
        b.G(parcel, C);
    }

    /* renamed from: aD */
    public Query createFromParcel(Parcel parcel) {
        SortOrder sortOrder;
        String str;
        LogicalFilter logicalFilter;
        int i;
        SortOrder sortOrder2 = null;
        int B = com.google.android.gms.common.internal.safeparcel.a.B(parcel);
        int i2 = 0;
        String str2 = null;
        LogicalFilter logicalFilter2 = null;
        while (parcel.dataPosition() < B) {
            int A = com.google.android.gms.common.internal.safeparcel.a.A(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.ar(A)) {
                case 1:
                    i = i2;
                    String str3 = str2;
                    logicalFilter = (LogicalFilter) com.google.android.gms.common.internal.safeparcel.a.a(parcel, A, LogicalFilter.CREATOR);
                    sortOrder = sortOrder2;
                    str = str3;
                    break;
                case 3:
                    logicalFilter = logicalFilter2;
                    i = i2;
                    SortOrder sortOrder3 = sortOrder2;
                    str = com.google.android.gms.common.internal.safeparcel.a.o(parcel, A);
                    sortOrder = sortOrder3;
                    break;
                case 4:
                    sortOrder = (SortOrder) com.google.android.gms.common.internal.safeparcel.a.a(parcel, A, SortOrder.CREATOR);
                    str = str2;
                    logicalFilter = logicalFilter2;
                    i = i2;
                    break;
                case 1000:
                    SortOrder sortOrder4 = sortOrder2;
                    str = str2;
                    logicalFilter = logicalFilter2;
                    i = com.google.android.gms.common.internal.safeparcel.a.g(parcel, A);
                    sortOrder = sortOrder4;
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, A);
                    sortOrder = sortOrder2;
                    str = str2;
                    logicalFilter = logicalFilter2;
                    i = i2;
                    break;
            }
            i2 = i;
            logicalFilter2 = logicalFilter;
            str2 = str;
            sortOrder2 = sortOrder;
        }
        if (parcel.dataPosition() == B) {
            return new Query(i2, logicalFilter2, str2, sortOrder2);
        }
        throw new a.C0014a("Overread allowed size end=" + B, parcel);
    }

    /* renamed from: bz */
    public Query[] newArray(int i) {
        return new Query[i];
    }
}
