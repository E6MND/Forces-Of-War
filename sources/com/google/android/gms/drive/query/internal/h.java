package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import java.util.ArrayList;
import java.util.List;

public class h implements Parcelable.Creator<LogicalFilter> {
    static void a(LogicalFilter logicalFilter, Parcel parcel, int i) {
        int C = b.C(parcel);
        b.c(parcel, 1000, logicalFilter.xJ);
        b.a(parcel, 1, (Parcelable) logicalFilter.KI, i, false);
        b.b(parcel, 2, logicalFilter.KV, false);
        b.G(parcel, C);
    }

    /* renamed from: aL */
    public LogicalFilter createFromParcel(Parcel parcel) {
        ArrayList<FilterHolder> c;
        Operator operator;
        int i;
        ArrayList<FilterHolder> arrayList = null;
        int B = a.B(parcel);
        int i2 = 0;
        Operator operator2 = null;
        while (parcel.dataPosition() < B) {
            int A = a.A(parcel);
            switch (a.ar(A)) {
                case 1:
                    i = i2;
                    Operator operator3 = (Operator) a.a(parcel, A, Operator.CREATOR);
                    c = arrayList;
                    operator = operator3;
                    break;
                case 2:
                    c = a.c(parcel, A, FilterHolder.CREATOR);
                    operator = operator2;
                    i = i2;
                    break;
                case 1000:
                    ArrayList<FilterHolder> arrayList2 = arrayList;
                    operator = operator2;
                    i = a.g(parcel, A);
                    c = arrayList2;
                    break;
                default:
                    a.b(parcel, A);
                    c = arrayList;
                    operator = operator2;
                    i = i2;
                    break;
            }
            i2 = i;
            operator2 = operator;
            arrayList = c;
        }
        if (parcel.dataPosition() == B) {
            return new LogicalFilter(i2, operator2, (List<FilterHolder>) arrayList);
        }
        throw new a.C0014a("Overread allowed size end=" + B, parcel);
    }

    /* renamed from: bH */
    public LogicalFilter[] newArray(int i) {
        return new LogicalFilter[i];
    }
}
