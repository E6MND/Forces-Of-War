package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import java.util.HashSet;
import java.util.Set;

public class kr implements Parcelable.Creator<kq> {
    static void a(kq kqVar, Parcel parcel, int i) {
        int C = b.C(parcel);
        Set<Integer> kf = kqVar.kf();
        if (kf.contains(1)) {
            b.c(parcel, 1, kqVar.getVersionCode());
        }
        if (kf.contains(2)) {
            b.a(parcel, 2, kqVar.getId(), true);
        }
        if (kf.contains(4)) {
            b.a(parcel, 4, (Parcelable) kqVar.kw(), i, true);
        }
        if (kf.contains(5)) {
            b.a(parcel, 5, kqVar.getStartDate(), true);
        }
        if (kf.contains(6)) {
            b.a(parcel, 6, (Parcelable) kqVar.kx(), i, true);
        }
        if (kf.contains(7)) {
            b.a(parcel, 7, kqVar.getType(), true);
        }
        b.G(parcel, C);
    }

    /* renamed from: bF */
    public kq createFromParcel(Parcel parcel) {
        String str = null;
        int B = a.B(parcel);
        HashSet hashSet = new HashSet();
        int i = 0;
        ko koVar = null;
        String str2 = null;
        ko koVar2 = null;
        String str3 = null;
        while (parcel.dataPosition() < B) {
            int A = a.A(parcel);
            switch (a.ar(A)) {
                case 1:
                    i = a.g(parcel, A);
                    hashSet.add(1);
                    break;
                case 2:
                    str3 = a.o(parcel, A);
                    hashSet.add(2);
                    break;
                case 4:
                    hashSet.add(4);
                    koVar2 = (ko) a.a(parcel, A, ko.CREATOR);
                    break;
                case 5:
                    str2 = a.o(parcel, A);
                    hashSet.add(5);
                    break;
                case 6:
                    hashSet.add(6);
                    koVar = (ko) a.a(parcel, A, ko.CREATOR);
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
            return new kq(hashSet, i, str3, koVar2, str2, koVar, str);
        }
        throw new a.C0014a("Overread allowed size end=" + B, parcel);
    }

    /* renamed from: dc */
    public kq[] newArray(int i) {
        return new kq[i];
    }
}
