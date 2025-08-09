package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.internal.kt;
import java.util.HashSet;
import java.util.Set;

public class kz implements Parcelable.Creator<kt.c> {
    static void a(kt.c cVar, Parcel parcel, int i) {
        int C = b.C(parcel);
        Set<Integer> kf = cVar.kf();
        if (kf.contains(1)) {
            b.c(parcel, 1, cVar.getVersionCode());
        }
        if (kf.contains(2)) {
            b.a(parcel, 2, cVar.getUrl(), true);
        }
        b.G(parcel, C);
    }

    /* renamed from: bL */
    public kt.c createFromParcel(Parcel parcel) {
        int B = a.B(parcel);
        HashSet hashSet = new HashSet();
        int i = 0;
        String str = null;
        while (parcel.dataPosition() < B) {
            int A = a.A(parcel);
            switch (a.ar(A)) {
                case 1:
                    i = a.g(parcel, A);
                    hashSet.add(1);
                    break;
                case 2:
                    str = a.o(parcel, A);
                    hashSet.add(2);
                    break;
                default:
                    a.b(parcel, A);
                    break;
            }
        }
        if (parcel.dataPosition() == B) {
            return new kt.c(hashSet, i, str);
        }
        throw new a.C0014a("Overread allowed size end=" + B, parcel);
    }

    /* renamed from: di */
    public kt.c[] newArray(int i) {
        return new kt.c[i];
    }
}
