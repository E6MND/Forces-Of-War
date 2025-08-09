package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.internal.kt;
import java.util.HashSet;
import java.util.Set;

public class ld implements Parcelable.Creator<kt.h> {
    static void a(kt.h hVar, Parcel parcel, int i) {
        int C = b.C(parcel);
        Set<Integer> kf = hVar.kf();
        if (kf.contains(1)) {
            b.c(parcel, 1, hVar.getVersionCode());
        }
        if (kf.contains(3)) {
            b.c(parcel, 3, hVar.kS());
        }
        if (kf.contains(4)) {
            b.a(parcel, 4, hVar.getValue(), true);
        }
        if (kf.contains(5)) {
            b.a(parcel, 5, hVar.getLabel(), true);
        }
        if (kf.contains(6)) {
            b.c(parcel, 6, hVar.getType());
        }
        b.G(parcel, C);
    }

    /* renamed from: bP */
    public kt.h createFromParcel(Parcel parcel) {
        String str = null;
        int i = 0;
        int B = a.B(parcel);
        HashSet hashSet = new HashSet();
        int i2 = 0;
        String str2 = null;
        int i3 = 0;
        while (parcel.dataPosition() < B) {
            int A = a.A(parcel);
            switch (a.ar(A)) {
                case 1:
                    i3 = a.g(parcel, A);
                    hashSet.add(1);
                    break;
                case 3:
                    i = a.g(parcel, A);
                    hashSet.add(3);
                    break;
                case 4:
                    str = a.o(parcel, A);
                    hashSet.add(4);
                    break;
                case 5:
                    str2 = a.o(parcel, A);
                    hashSet.add(5);
                    break;
                case 6:
                    i2 = a.g(parcel, A);
                    hashSet.add(6);
                    break;
                default:
                    a.b(parcel, A);
                    break;
            }
        }
        if (parcel.dataPosition() == B) {
            return new kt.h(hashSet, i3, str2, i2, str, i);
        }
        throw new a.C0014a("Overread allowed size end=" + B, parcel);
    }

    /* renamed from: dm */
    public kt.h[] newArray(int i) {
        return new kt.h[i];
    }
}
