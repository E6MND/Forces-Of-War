package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import java.util.ArrayList;

public class jo implements Parcelable.Creator<jn> {
    static void a(jn jnVar, Parcel parcel, int i) {
        int C = b.C(parcel);
        b.b(parcel, 1, jnVar.VZ, false);
        b.c(parcel, 1000, jnVar.xJ);
        b.a(parcel, 2, jnVar.jb(), false);
        b.a(parcel, 3, jnVar.jc());
        b.b(parcel, 4, jnVar.Wc, false);
        b.a(parcel, 5, jnVar.jd(), false);
        b.a(parcel, 6, jnVar.We, false);
        b.G(parcel, C);
    }

    /* renamed from: bv */
    public jn createFromParcel(Parcel parcel) {
        boolean z = false;
        ArrayList<String> arrayList = null;
        int B = a.B(parcel);
        String str = null;
        ArrayList arrayList2 = null;
        String str2 = null;
        ArrayList arrayList3 = null;
        int i = 0;
        while (parcel.dataPosition() < B) {
            int A = a.A(parcel);
            switch (a.ar(A)) {
                case 1:
                    arrayList3 = a.c(parcel, A, jt.CREATOR);
                    break;
                case 2:
                    str2 = a.o(parcel, A);
                    break;
                case 3:
                    z = a.c(parcel, A);
                    break;
                case 4:
                    arrayList2 = a.c(parcel, A, jx.CREATOR);
                    break;
                case 5:
                    str = a.o(parcel, A);
                    break;
                case 6:
                    arrayList = a.B(parcel, A);
                    break;
                case 1000:
                    i = a.g(parcel, A);
                    break;
                default:
                    a.b(parcel, A);
                    break;
            }
        }
        if (parcel.dataPosition() == B) {
            return new jn(i, arrayList3, str2, z, arrayList2, str, arrayList);
        }
        throw new a.C0014a("Overread allowed size end=" + B, parcel);
    }

    /* renamed from: cQ */
    public jn[] newArray(int i) {
        return new jn[i];
    }
}
