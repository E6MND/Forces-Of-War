package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class fi implements Parcelable.Creator<fh> {
    static void a(fh fhVar, Parcel parcel, int i) {
        int C = b.C(parcel);
        b.a(parcel, 1, (T[]) fhVar.xK, i, false);
        b.c(parcel, 1000, fhVar.xJ);
        b.a(parcel, 2, fhVar.xL, false);
        b.a(parcel, 3, fhVar.xM);
        b.G(parcel, C);
    }

    /* renamed from: D */
    public fh[] newArray(int i) {
        return new fh[i];
    }

    /* renamed from: l */
    public fh createFromParcel(Parcel parcel) {
        boolean c;
        String str;
        fl[] flVarArr;
        int i;
        String str2 = null;
        boolean z = false;
        int B = a.B(parcel);
        fl[] flVarArr2 = null;
        int i2 = 0;
        while (parcel.dataPosition() < B) {
            int A = a.A(parcel);
            switch (a.ar(A)) {
                case 1:
                    i = i2;
                    String str3 = str2;
                    flVarArr = (fl[]) a.b(parcel, A, fl.CREATOR);
                    c = z;
                    str = str3;
                    break;
                case 2:
                    flVarArr = flVarArr2;
                    i = i2;
                    boolean z2 = z;
                    str = a.o(parcel, A);
                    c = z2;
                    break;
                case 3:
                    c = a.c(parcel, A);
                    str = str2;
                    flVarArr = flVarArr2;
                    i = i2;
                    break;
                case 1000:
                    boolean z3 = z;
                    str = str2;
                    flVarArr = flVarArr2;
                    i = a.g(parcel, A);
                    c = z3;
                    break;
                default:
                    a.b(parcel, A);
                    c = z;
                    str = str2;
                    flVarArr = flVarArr2;
                    i = i2;
                    break;
            }
            i2 = i;
            flVarArr2 = flVarArr;
            str2 = str;
            z = c;
        }
        if (parcel.dataPosition() == B) {
            return new fh(i2, flVarArr2, str2, z);
        }
        throw new a.C0014a("Overread allowed size end=" + B, parcel);
    }
}
