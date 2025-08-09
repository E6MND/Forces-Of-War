package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.util.TimeUtils;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import java.util.ArrayList;

public class dw implements Parcelable.Creator<dv> {
    static void a(dv dvVar, Parcel parcel, int i) {
        int C = b.C(parcel);
        b.c(parcel, 1, dvVar.versionCode);
        b.a(parcel, 2, dvVar.oy, false);
        b.a(parcel, 3, dvVar.qb, false);
        b.a(parcel, 4, dvVar.nr, false);
        b.c(parcel, 5, dvVar.errorCode);
        b.a(parcel, 6, dvVar.ns, false);
        b.a(parcel, 7, dvVar.qc);
        b.a(parcel, 8, dvVar.qd);
        b.a(parcel, 9, dvVar.qe);
        b.a(parcel, 10, dvVar.qf, false);
        b.a(parcel, 11, dvVar.nv);
        b.c(parcel, 12, dvVar.orientation);
        b.a(parcel, 13, dvVar.qg, false);
        b.a(parcel, 14, dvVar.qh);
        b.a(parcel, 15, dvVar.qi, false);
        b.a(parcel, 19, dvVar.qk, false);
        b.a(parcel, 18, dvVar.qj);
        b.a(parcel, 21, dvVar.ql, false);
        b.G(parcel, C);
    }

    /* renamed from: i */
    public dv createFromParcel(Parcel parcel) {
        int B = a.B(parcel);
        int i = 0;
        String str = null;
        String str2 = null;
        ArrayList<String> arrayList = null;
        int i2 = 0;
        ArrayList<String> arrayList2 = null;
        long j = 0;
        boolean z = false;
        long j2 = 0;
        ArrayList<String> arrayList3 = null;
        long j3 = 0;
        int i3 = 0;
        String str3 = null;
        long j4 = 0;
        String str4 = null;
        boolean z2 = false;
        String str5 = null;
        String str6 = null;
        while (parcel.dataPosition() < B) {
            int A = a.A(parcel);
            switch (a.ar(A)) {
                case 1:
                    i = a.g(parcel, A);
                    break;
                case 2:
                    str = a.o(parcel, A);
                    break;
                case 3:
                    str2 = a.o(parcel, A);
                    break;
                case 4:
                    arrayList = a.B(parcel, A);
                    break;
                case 5:
                    i2 = a.g(parcel, A);
                    break;
                case 6:
                    arrayList2 = a.B(parcel, A);
                    break;
                case 7:
                    j = a.i(parcel, A);
                    break;
                case 8:
                    z = a.c(parcel, A);
                    break;
                case 9:
                    j2 = a.i(parcel, A);
                    break;
                case 10:
                    arrayList3 = a.B(parcel, A);
                    break;
                case 11:
                    j3 = a.i(parcel, A);
                    break;
                case 12:
                    i3 = a.g(parcel, A);
                    break;
                case 13:
                    str3 = a.o(parcel, A);
                    break;
                case 14:
                    j4 = a.i(parcel, A);
                    break;
                case 15:
                    str4 = a.o(parcel, A);
                    break;
                case 18:
                    z2 = a.c(parcel, A);
                    break;
                case TimeUtils.HUNDRED_DAY_FIELD_LEN:
                    str5 = a.o(parcel, A);
                    break;
                case 21:
                    str6 = a.o(parcel, A);
                    break;
                default:
                    a.b(parcel, A);
                    break;
            }
        }
        if (parcel.dataPosition() == B) {
            return new dv(i, str, str2, arrayList, i2, arrayList2, j, z, j2, arrayList3, j3, i3, str3, j4, str4, z2, str5, str6);
        }
        throw new a.C0014a("Overread allowed size end=" + B, parcel);
    }

    /* renamed from: n */
    public dv[] newArray(int i) {
        return new dv[i];
    }
}
