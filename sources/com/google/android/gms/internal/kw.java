package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.internal.kt;
import java.util.HashSet;
import java.util.Set;

public class kw implements Parcelable.Creator<kt.b> {
    static void a(kt.b bVar, Parcel parcel, int i) {
        int C = b.C(parcel);
        Set<Integer> kf = bVar.kf();
        if (kf.contains(1)) {
            b.c(parcel, 1, bVar.getVersionCode());
        }
        if (kf.contains(2)) {
            b.a(parcel, 2, (Parcelable) bVar.kJ(), i, true);
        }
        if (kf.contains(3)) {
            b.a(parcel, 3, (Parcelable) bVar.kK(), i, true);
        }
        if (kf.contains(4)) {
            b.c(parcel, 4, bVar.getLayout());
        }
        b.G(parcel, C);
    }

    /* renamed from: bI */
    public kt.b createFromParcel(Parcel parcel) {
        kt.b.C0072b bVar = null;
        int i = 0;
        int B = a.B(parcel);
        HashSet hashSet = new HashSet();
        kt.b.a aVar = null;
        int i2 = 0;
        while (parcel.dataPosition() < B) {
            int A = a.A(parcel);
            switch (a.ar(A)) {
                case 1:
                    i2 = a.g(parcel, A);
                    hashSet.add(1);
                    break;
                case 2:
                    hashSet.add(2);
                    aVar = (kt.b.a) a.a(parcel, A, kt.b.a.CREATOR);
                    break;
                case 3:
                    hashSet.add(3);
                    bVar = (kt.b.C0072b) a.a(parcel, A, kt.b.C0072b.CREATOR);
                    break;
                case 4:
                    i = a.g(parcel, A);
                    hashSet.add(4);
                    break;
                default:
                    a.b(parcel, A);
                    break;
            }
        }
        if (parcel.dataPosition() == B) {
            return new kt.b(hashSet, i2, aVar, bVar, i);
        }
        throw new a.C0014a("Overread allowed size end=" + B, parcel);
    }

    /* renamed from: df */
    public kt.b[] newArray(int i) {
        return new kt.b[i];
    }
}
