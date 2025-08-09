package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.internal.kt;
import java.util.HashSet;
import java.util.Set;

public class la implements Parcelable.Creator<kt.d> {
    static void a(kt.d dVar, Parcel parcel, int i) {
        int C = b.C(parcel);
        Set<Integer> kf = dVar.kf();
        if (kf.contains(1)) {
            b.c(parcel, 1, dVar.getVersionCode());
        }
        if (kf.contains(2)) {
            b.a(parcel, 2, dVar.getFamilyName(), true);
        }
        if (kf.contains(3)) {
            b.a(parcel, 3, dVar.getFormatted(), true);
        }
        if (kf.contains(4)) {
            b.a(parcel, 4, dVar.getGivenName(), true);
        }
        if (kf.contains(5)) {
            b.a(parcel, 5, dVar.getHonorificPrefix(), true);
        }
        if (kf.contains(6)) {
            b.a(parcel, 6, dVar.getHonorificSuffix(), true);
        }
        if (kf.contains(7)) {
            b.a(parcel, 7, dVar.getMiddleName(), true);
        }
        b.G(parcel, C);
    }

    /* renamed from: bM */
    public kt.d createFromParcel(Parcel parcel) {
        String str = null;
        int B = a.B(parcel);
        HashSet hashSet = new HashSet();
        int i = 0;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        String str6 = null;
        while (parcel.dataPosition() < B) {
            int A = a.A(parcel);
            switch (a.ar(A)) {
                case 1:
                    i = a.g(parcel, A);
                    hashSet.add(1);
                    break;
                case 2:
                    str6 = a.o(parcel, A);
                    hashSet.add(2);
                    break;
                case 3:
                    str5 = a.o(parcel, A);
                    hashSet.add(3);
                    break;
                case 4:
                    str4 = a.o(parcel, A);
                    hashSet.add(4);
                    break;
                case 5:
                    str3 = a.o(parcel, A);
                    hashSet.add(5);
                    break;
                case 6:
                    str2 = a.o(parcel, A);
                    hashSet.add(6);
                    break;
                case 7:
                    str = a.o(parcel, A);
                    hashSet.add(7);
                    break;
                default:
                    a.b(parcel, A);
                    break;
            }
        }
        if (parcel.dataPosition() == B) {
            return new kt.d(hashSet, i, str6, str5, str4, str3, str2, str);
        }
        throw new a.C0014a("Overread allowed size end=" + B, parcel);
    }

    /* renamed from: dj */
    public kt.d[] newArray(int i) {
        return new kt.d[i];
    }
}
