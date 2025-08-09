package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.internal.kt;
import java.util.HashSet;
import java.util.Set;

public class lc implements Parcelable.Creator<kt.g> {
    static void a(kt.g gVar, Parcel parcel, int i) {
        int C = b.C(parcel);
        Set<Integer> kf = gVar.kf();
        if (kf.contains(1)) {
            b.c(parcel, 1, gVar.getVersionCode());
        }
        if (kf.contains(2)) {
            b.a(parcel, 2, gVar.isPrimary());
        }
        if (kf.contains(3)) {
            b.a(parcel, 3, gVar.getValue(), true);
        }
        b.G(parcel, C);
    }

    /* renamed from: bO */
    public kt.g createFromParcel(Parcel parcel) {
        boolean z = false;
        int B = a.B(parcel);
        HashSet hashSet = new HashSet();
        String str = null;
        int i = 0;
        while (parcel.dataPosition() < B) {
            int A = a.A(parcel);
            switch (a.ar(A)) {
                case 1:
                    i = a.g(parcel, A);
                    hashSet.add(1);
                    break;
                case 2:
                    z = a.c(parcel, A);
                    hashSet.add(2);
                    break;
                case 3:
                    str = a.o(parcel, A);
                    hashSet.add(3);
                    break;
                default:
                    a.b(parcel, A);
                    break;
            }
        }
        if (parcel.dataPosition() == B) {
            return new kt.g(hashSet, i, z, str);
        }
        throw new a.C0014a("Overread allowed size end=" + B, parcel);
    }

    /* renamed from: dl */
    public kt.g[] newArray(int i) {
        return new kt.g[i];
    }
}
