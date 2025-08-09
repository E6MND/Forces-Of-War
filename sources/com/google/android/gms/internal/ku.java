package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.util.TimeUtils;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.internal.kt;
import com.googlecode.eyesfree.braille.display.BrailleInputEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ku implements Parcelable.Creator<kt> {
    static void a(kt ktVar, Parcel parcel, int i) {
        int C = b.C(parcel);
        Set<Integer> kf = ktVar.kf();
        if (kf.contains(1)) {
            b.c(parcel, 1, ktVar.getVersionCode());
        }
        if (kf.contains(2)) {
            b.a(parcel, 2, ktVar.getAboutMe(), true);
        }
        if (kf.contains(3)) {
            b.a(parcel, 3, (Parcelable) ktVar.kA(), i, true);
        }
        if (kf.contains(4)) {
            b.a(parcel, 4, ktVar.getBirthday(), true);
        }
        if (kf.contains(5)) {
            b.a(parcel, 5, ktVar.getBraggingRights(), true);
        }
        if (kf.contains(6)) {
            b.c(parcel, 6, ktVar.getCircledByCount());
        }
        if (kf.contains(7)) {
            b.a(parcel, 7, (Parcelable) ktVar.kB(), i, true);
        }
        if (kf.contains(8)) {
            b.a(parcel, 8, ktVar.getCurrentLocation(), true);
        }
        if (kf.contains(9)) {
            b.a(parcel, 9, ktVar.getDisplayName(), true);
        }
        if (kf.contains(12)) {
            b.c(parcel, 12, ktVar.getGender());
        }
        if (kf.contains(14)) {
            b.a(parcel, 14, ktVar.getId(), true);
        }
        if (kf.contains(15)) {
            b.a(parcel, 15, (Parcelable) ktVar.kC(), i, true);
        }
        if (kf.contains(16)) {
            b.a(parcel, 16, ktVar.isPlusUser());
        }
        if (kf.contains(19)) {
            b.a(parcel, 19, (Parcelable) ktVar.kD(), i, true);
        }
        if (kf.contains(18)) {
            b.a(parcel, 18, ktVar.getLanguage(), true);
        }
        if (kf.contains(21)) {
            b.c(parcel, 21, ktVar.getObjectType());
        }
        if (kf.contains(20)) {
            b.a(parcel, 20, ktVar.getNickname(), true);
        }
        if (kf.contains(23)) {
            b.b(parcel, 23, ktVar.kF(), true);
        }
        if (kf.contains(22)) {
            b.b(parcel, 22, ktVar.kE(), true);
        }
        if (kf.contains(25)) {
            b.c(parcel, 25, ktVar.getRelationshipStatus());
        }
        if (kf.contains(24)) {
            b.c(parcel, 24, ktVar.getPlusOneCount());
        }
        if (kf.contains(27)) {
            b.a(parcel, 27, ktVar.getUrl(), true);
        }
        if (kf.contains(26)) {
            b.a(parcel, 26, ktVar.getTagline(), true);
        }
        if (kf.contains(29)) {
            b.a(parcel, 29, ktVar.isVerified());
        }
        if (kf.contains(28)) {
            b.b(parcel, 28, ktVar.kG(), true);
        }
        b.G(parcel, C);
    }

    /* renamed from: bG */
    public kt createFromParcel(Parcel parcel) {
        int B = a.B(parcel);
        HashSet hashSet = new HashSet();
        int i = 0;
        String str = null;
        kt.a aVar = null;
        String str2 = null;
        String str3 = null;
        int i2 = 0;
        kt.b bVar = null;
        String str4 = null;
        String str5 = null;
        int i3 = 0;
        String str6 = null;
        kt.c cVar = null;
        boolean z = false;
        String str7 = null;
        kt.d dVar = null;
        String str8 = null;
        int i4 = 0;
        ArrayList arrayList = null;
        ArrayList arrayList2 = null;
        int i5 = 0;
        int i6 = 0;
        String str9 = null;
        String str10 = null;
        ArrayList arrayList3 = null;
        boolean z2 = false;
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
                case 3:
                    hashSet.add(3);
                    aVar = (kt.a) a.a(parcel, A, kt.a.CREATOR);
                    break;
                case 4:
                    str2 = a.o(parcel, A);
                    hashSet.add(4);
                    break;
                case 5:
                    str3 = a.o(parcel, A);
                    hashSet.add(5);
                    break;
                case 6:
                    i2 = a.g(parcel, A);
                    hashSet.add(6);
                    break;
                case 7:
                    hashSet.add(7);
                    bVar = (kt.b) a.a(parcel, A, kt.b.CREATOR);
                    break;
                case 8:
                    str4 = a.o(parcel, A);
                    hashSet.add(8);
                    break;
                case 9:
                    str5 = a.o(parcel, A);
                    hashSet.add(9);
                    break;
                case 12:
                    i3 = a.g(parcel, A);
                    hashSet.add(12);
                    break;
                case 14:
                    str6 = a.o(parcel, A);
                    hashSet.add(14);
                    break;
                case 15:
                    hashSet.add(15);
                    cVar = (kt.c) a.a(parcel, A, kt.c.CREATOR);
                    break;
                case 16:
                    z = a.c(parcel, A);
                    hashSet.add(16);
                    break;
                case 18:
                    str7 = a.o(parcel, A);
                    hashSet.add(18);
                    break;
                case TimeUtils.HUNDRED_DAY_FIELD_LEN:
                    hashSet.add(19);
                    dVar = (kt.d) a.a(parcel, A, kt.d.CREATOR);
                    break;
                case BrailleInputEvent.CMD_ACTIVATE_CURRENT /*20*/:
                    str8 = a.o(parcel, A);
                    hashSet.add(20);
                    break;
                case 21:
                    i4 = a.g(parcel, A);
                    hashSet.add(21);
                    break;
                case 22:
                    arrayList = a.c(parcel, A, kt.f.CREATOR);
                    hashSet.add(22);
                    break;
                case 23:
                    arrayList2 = a.c(parcel, A, kt.g.CREATOR);
                    hashSet.add(23);
                    break;
                case 24:
                    i5 = a.g(parcel, A);
                    hashSet.add(24);
                    break;
                case 25:
                    i6 = a.g(parcel, A);
                    hashSet.add(25);
                    break;
                case 26:
                    str9 = a.o(parcel, A);
                    hashSet.add(26);
                    break;
                case 27:
                    str10 = a.o(parcel, A);
                    hashSet.add(27);
                    break;
                case 28:
                    arrayList3 = a.c(parcel, A, kt.h.CREATOR);
                    hashSet.add(28);
                    break;
                case 29:
                    z2 = a.c(parcel, A);
                    hashSet.add(29);
                    break;
                default:
                    a.b(parcel, A);
                    break;
            }
        }
        if (parcel.dataPosition() == B) {
            return new kt(hashSet, i, str, aVar, str2, str3, i2, bVar, str4, str5, i3, str6, cVar, z, str7, dVar, str8, i4, arrayList, arrayList2, i5, i6, str9, str10, arrayList3, z2);
        }
        throw new a.C0014a("Overread allowed size end=" + B, parcel);
    }

    /* renamed from: dd */
    public kt[] newArray(int i) {
        return new kt[i];
    }
}
