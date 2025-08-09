package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.internal.kt;
import java.util.HashSet;
import java.util.Set;

public class ky implements Parcelable.Creator<kt.b.C0072b> {
    static void a(kt.b.C0072b bVar, Parcel parcel, int i) {
        int C = b.C(parcel);
        Set<Integer> kf = bVar.kf();
        if (kf.contains(1)) {
            b.c(parcel, 1, bVar.getVersionCode());
        }
        if (kf.contains(2)) {
            b.c(parcel, 2, bVar.getHeight());
        }
        if (kf.contains(3)) {
            b.a(parcel, 3, bVar.getUrl(), true);
        }
        if (kf.contains(4)) {
            b.c(parcel, 4, bVar.getWidth());
        }
        b.G(parcel, C);
    }

    /* renamed from: bK */
    public kt.b.C0072b createFromParcel(Parcel parcel) {
        int i = 0;
        int B = a.B(parcel);
        HashSet hashSet = new HashSet();
        String str = null;
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
                    str = a.o(parcel, A);
                    hashSet.add(3);
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
            return new kt.b.C0072b(hashSet, i3, i2, str, i);
        }
        throw new a.C0014a("Overread allowed size end=" + B, parcel);
    }

    /* renamed from: dh */
    public kt.b.C0072b[] newArray(int i) {
        return new kt.b.C0072b[i];
    }
}
