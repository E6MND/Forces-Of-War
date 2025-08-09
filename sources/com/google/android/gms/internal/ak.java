package com.google.android.gms.internal;

import android.location.Location;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import java.util.ArrayList;

public class ak implements Parcelable.Creator<aj> {
    static void a(aj ajVar, Parcel parcel, int i) {
        int C = b.C(parcel);
        b.c(parcel, 1, ajVar.versionCode);
        b.a(parcel, 2, ajVar.lQ);
        b.a(parcel, 3, ajVar.extras, false);
        b.c(parcel, 4, ajVar.lR);
        b.a(parcel, 5, ajVar.lS, false);
        b.a(parcel, 6, ajVar.lT);
        b.c(parcel, 7, ajVar.lU);
        b.a(parcel, 8, ajVar.lV);
        b.a(parcel, 9, ajVar.lW, false);
        b.a(parcel, 10, (Parcelable) ajVar.lX, i, false);
        b.a(parcel, 11, (Parcelable) ajVar.lY, i, false);
        b.a(parcel, 12, ajVar.lZ, false);
        b.a(parcel, 13, ajVar.ma, false);
        b.G(parcel, C);
    }

    /* renamed from: b */
    public aj createFromParcel(Parcel parcel) {
        int B = a.B(parcel);
        int i = 0;
        long j = 0;
        Bundle bundle = null;
        int i2 = 0;
        ArrayList<String> arrayList = null;
        boolean z = false;
        int i3 = 0;
        boolean z2 = false;
        String str = null;
        ax axVar = null;
        Location location = null;
        String str2 = null;
        Bundle bundle2 = null;
        while (parcel.dataPosition() < B) {
            int A = a.A(parcel);
            switch (a.ar(A)) {
                case 1:
                    i = a.g(parcel, A);
                    break;
                case 2:
                    j = a.i(parcel, A);
                    break;
                case 3:
                    bundle = a.q(parcel, A);
                    break;
                case 4:
                    i2 = a.g(parcel, A);
                    break;
                case 5:
                    arrayList = a.B(parcel, A);
                    break;
                case 6:
                    z = a.c(parcel, A);
                    break;
                case 7:
                    i3 = a.g(parcel, A);
                    break;
                case 8:
                    z2 = a.c(parcel, A);
                    break;
                case 9:
                    str = a.o(parcel, A);
                    break;
                case 10:
                    axVar = (ax) a.a(parcel, A, ax.CREATOR);
                    break;
                case 11:
                    location = (Location) a.a(parcel, A, Location.CREATOR);
                    break;
                case 12:
                    str2 = a.o(parcel, A);
                    break;
                case 13:
                    bundle2 = a.q(parcel, A);
                    break;
                default:
                    a.b(parcel, A);
                    break;
            }
        }
        if (parcel.dataPosition() == B) {
            return new aj(i, j, bundle, i2, arrayList, z, i3, z2, str, axVar, location, str2, bundle2);
        }
        throw new a.C0014a("Overread allowed size end=" + B, parcel);
    }

    /* renamed from: c */
    public aj[] newArray(int i) {
        return new aj[i];
    }
}
