package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.internal.kt;
import java.util.HashSet;
import java.util.Set;

public class lb implements Parcelable.Creator<kt.f> {
    static void a(kt.f fVar, Parcel parcel, int i) {
        int C = b.C(parcel);
        Set<Integer> kf = fVar.kf();
        if (kf.contains(1)) {
            b.c(parcel, 1, fVar.getVersionCode());
        }
        if (kf.contains(2)) {
            b.a(parcel, 2, fVar.getDepartment(), true);
        }
        if (kf.contains(3)) {
            b.a(parcel, 3, fVar.getDescription(), true);
        }
        if (kf.contains(4)) {
            b.a(parcel, 4, fVar.getEndDate(), true);
        }
        if (kf.contains(5)) {
            b.a(parcel, 5, fVar.getLocation(), true);
        }
        if (kf.contains(6)) {
            b.a(parcel, 6, fVar.getName(), true);
        }
        if (kf.contains(7)) {
            b.a(parcel, 7, fVar.isPrimary());
        }
        if (kf.contains(8)) {
            b.a(parcel, 8, fVar.getStartDate(), true);
        }
        if (kf.contains(9)) {
            b.a(parcel, 9, fVar.getTitle(), true);
        }
        if (kf.contains(10)) {
            b.c(parcel, 10, fVar.getType());
        }
        b.G(parcel, C);
    }

    /* renamed from: bN */
    public kt.f createFromParcel(Parcel parcel) {
        int i = 0;
        String str = null;
        int B = a.B(parcel);
        HashSet hashSet = new HashSet();
        String str2 = null;
        boolean z = false;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        String str6 = null;
        String str7 = null;
        int i2 = 0;
        while (parcel.dataPosition() < B) {
            int A = a.A(parcel);
            switch (a.ar(A)) {
                case 1:
                    i2 = a.g(parcel, A);
                    hashSet.add(1);
                    break;
                case 2:
                    str7 = a.o(parcel, A);
                    hashSet.add(2);
                    break;
                case 3:
                    str6 = a.o(parcel, A);
                    hashSet.add(3);
                    break;
                case 4:
                    str5 = a.o(parcel, A);
                    hashSet.add(4);
                    break;
                case 5:
                    str4 = a.o(parcel, A);
                    hashSet.add(5);
                    break;
                case 6:
                    str3 = a.o(parcel, A);
                    hashSet.add(6);
                    break;
                case 7:
                    z = a.c(parcel, A);
                    hashSet.add(7);
                    break;
                case 8:
                    str2 = a.o(parcel, A);
                    hashSet.add(8);
                    break;
                case 9:
                    str = a.o(parcel, A);
                    hashSet.add(9);
                    break;
                case 10:
                    i = a.g(parcel, A);
                    hashSet.add(10);
                    break;
                default:
                    a.b(parcel, A);
                    break;
            }
        }
        if (parcel.dataPosition() == B) {
            return new kt.f(hashSet, i2, str7, str6, str5, str4, str3, z, str2, str, i);
        }
        throw new a.C0014a("Overread allowed size end=" + B, parcel);
    }

    /* renamed from: dk */
    public kt.f[] newArray(int i) {
        return new kt.f[i];
    }
}
