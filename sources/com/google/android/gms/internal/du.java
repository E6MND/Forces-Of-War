package com.google.android.gms.internal;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class du implements Parcelable.Creator<dt> {
    static void a(dt dtVar, Parcel parcel, int i) {
        int C = b.C(parcel);
        b.c(parcel, 1, dtVar.versionCode);
        b.a(parcel, 2, dtVar.pU, false);
        b.a(parcel, 3, (Parcelable) dtVar.pV, i, false);
        b.a(parcel, 4, (Parcelable) dtVar.kR, i, false);
        b.a(parcel, 5, dtVar.kL, false);
        b.a(parcel, 6, (Parcelable) dtVar.applicationInfo, i, false);
        b.a(parcel, 7, (Parcelable) dtVar.pW, i, false);
        b.a(parcel, 8, dtVar.pX, false);
        b.a(parcel, 9, dtVar.pY, false);
        b.a(parcel, 10, dtVar.pZ, false);
        b.a(parcel, 11, (Parcelable) dtVar.kO, i, false);
        b.a(parcel, 12, dtVar.qa, false);
        b.G(parcel, C);
    }

    /* renamed from: h */
    public dt createFromParcel(Parcel parcel) {
        int B = a.B(parcel);
        int i = 0;
        Bundle bundle = null;
        aj ajVar = null;
        am amVar = null;
        String str = null;
        ApplicationInfo applicationInfo = null;
        PackageInfo packageInfo = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        ew ewVar = null;
        Bundle bundle2 = null;
        while (parcel.dataPosition() < B) {
            int A = a.A(parcel);
            switch (a.ar(A)) {
                case 1:
                    i = a.g(parcel, A);
                    break;
                case 2:
                    bundle = a.q(parcel, A);
                    break;
                case 3:
                    ajVar = (aj) a.a(parcel, A, aj.CREATOR);
                    break;
                case 4:
                    amVar = (am) a.a(parcel, A, am.CREATOR);
                    break;
                case 5:
                    str = a.o(parcel, A);
                    break;
                case 6:
                    applicationInfo = (ApplicationInfo) a.a(parcel, A, ApplicationInfo.CREATOR);
                    break;
                case 7:
                    packageInfo = (PackageInfo) a.a(parcel, A, PackageInfo.CREATOR);
                    break;
                case 8:
                    str2 = a.o(parcel, A);
                    break;
                case 9:
                    str3 = a.o(parcel, A);
                    break;
                case 10:
                    str4 = a.o(parcel, A);
                    break;
                case 11:
                    ewVar = (ew) a.a(parcel, A, ew.CREATOR);
                    break;
                case 12:
                    bundle2 = a.q(parcel, A);
                    break;
                default:
                    a.b(parcel, A);
                    break;
            }
        }
        if (parcel.dataPosition() == B) {
            return new dt(i, bundle, ajVar, amVar, str, applicationInfo, packageInfo, str2, str3, str4, ewVar, bundle2);
        }
        throw new a.C0014a("Overread allowed size end=" + B, parcel);
    }

    /* renamed from: m */
    public dt[] newArray(int i) {
        return new dt[i];
    }
}
