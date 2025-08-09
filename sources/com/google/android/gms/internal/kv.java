package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.internal.kt;
import java.util.HashSet;
import java.util.Set;

public class kv implements Parcelable.Creator<kt.a> {
    static void a(kt.a aVar, Parcel parcel, int i) {
        int C = b.C(parcel);
        Set<Integer> kf = aVar.kf();
        if (kf.contains(1)) {
            b.c(parcel, 1, aVar.getVersionCode());
        }
        if (kf.contains(2)) {
            b.c(parcel, 2, aVar.getMax());
        }
        if (kf.contains(3)) {
            b.c(parcel, 3, aVar.getMin());
        }
        b.G(parcel, C);
    }

    /* renamed from: bH */
    public kt.a createFromParcel(Parcel parcel) {
        int i = 0;
        int B = a.B(parcel);
        HashSet hashSet = new HashSet();
        int i2 = 0;
        int i3 = 0;
        while (parcel.dataPosition() < B) {
            int A = a.A(parcel);
            switch (a.ar(A)) {
                case 1:
                    i3 = a.g(parcel, A);
                    hashSet.add(1);
                    break;
                case 2:
                    i2 = a.g(parcel, A);
                    hashSet.add(2);
                    break;
                case 3:
                    i = a.g(parcel, A);
                    hashSet.add(3);
                    break;
                default:
                    a.b(parcel, A);
                    break;
            }
        }
        if (parcel.dataPosition() == B) {
            return new kt.a(hashSet, i3, i2, i);
        }
        throw new a.C0014a("Overread allowed size end=" + B, parcel);
    }

    /* renamed from: de */
    public kt.a[] newArray(int i) {
        return new kt.a[i];
    }
}
