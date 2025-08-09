package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.util.TimeUtils;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.googlecode.eyesfree.braille.display.BrailleInputEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class kp implements Parcelable.Creator<ko> {
    static void a(ko koVar, Parcel parcel, int i) {
        int C = b.C(parcel);
        Set<Integer> kf = koVar.kf();
        if (kf.contains(1)) {
            b.c(parcel, 1, koVar.getVersionCode());
        }
        if (kf.contains(2)) {
            b.a(parcel, 2, (Parcelable) koVar.kg(), i, true);
        }
        if (kf.contains(3)) {
            b.a(parcel, 3, koVar.getAdditionalName(), true);
        }
        if (kf.contains(4)) {
            b.a(parcel, 4, (Parcelable) koVar.kh(), i, true);
        }
        if (kf.contains(5)) {
            b.a(parcel, 5, koVar.getAddressCountry(), true);
        }
        if (kf.contains(6)) {
            b.a(parcel, 6, koVar.getAddressLocality(), true);
        }
        if (kf.contains(7)) {
            b.a(parcel, 7, koVar.getAddressRegion(), true);
        }
        if (kf.contains(8)) {
            b.b(parcel, 8, koVar.ki(), true);
        }
        if (kf.contains(9)) {
            b.c(parcel, 9, koVar.getAttendeeCount());
        }
        if (kf.contains(10)) {
            b.b(parcel, 10, koVar.kj(), true);
        }
        if (kf.contains(11)) {
            b.a(parcel, 11, (Parcelable) koVar.kk(), i, true);
        }
        if (kf.contains(12)) {
            b.b(parcel, 12, koVar.kl(), true);
        }
        if (kf.contains(13)) {
            b.a(parcel, 13, koVar.getBestRating(), true);
        }
        if (kf.contains(14)) {
            b.a(parcel, 14, koVar.getBirthDate(), true);
        }
        if (kf.contains(15)) {
            b.a(parcel, 15, (Parcelable) koVar.km(), i, true);
        }
        if (kf.contains(17)) {
            b.a(parcel, 17, koVar.getContentSize(), true);
        }
        if (kf.contains(16)) {
            b.a(parcel, 16, koVar.getCaption(), true);
        }
        if (kf.contains(19)) {
            b.b(parcel, 19, koVar.kn(), true);
        }
        if (kf.contains(18)) {
            b.a(parcel, 18, koVar.getContentUrl(), true);
        }
        if (kf.contains(21)) {
            b.a(parcel, 21, koVar.getDateModified(), true);
        }
        if (kf.contains(20)) {
            b.a(parcel, 20, koVar.getDateCreated(), true);
        }
        if (kf.contains(23)) {
            b.a(parcel, 23, koVar.getDescription(), true);
        }
        if (kf.contains(22)) {
            b.a(parcel, 22, koVar.getDatePublished(), true);
        }
        if (kf.contains(25)) {
            b.a(parcel, 25, koVar.getEmbedUrl(), true);
        }
        if (kf.contains(24)) {
            b.a(parcel, 24, koVar.getDuration(), true);
        }
        if (kf.contains(27)) {
            b.a(parcel, 27, koVar.getFamilyName(), true);
        }
        if (kf.contains(26)) {
            b.a(parcel, 26, koVar.getEndDate(), true);
        }
        if (kf.contains(29)) {
            b.a(parcel, 29, (Parcelable) koVar.ko(), i, true);
        }
        if (kf.contains(28)) {
            b.a(parcel, 28, koVar.getGender(), true);
        }
        if (kf.contains(31)) {
            b.a(parcel, 31, koVar.getHeight(), true);
        }
        if (kf.contains(30)) {
            b.a(parcel, 30, koVar.getGivenName(), true);
        }
        if (kf.contains(34)) {
            b.a(parcel, 34, (Parcelable) koVar.kp(), i, true);
        }
        if (kf.contains(32)) {
            b.a(parcel, 32, koVar.getId(), true);
        }
        if (kf.contains(33)) {
            b.a(parcel, 33, koVar.getImage(), true);
        }
        if (kf.contains(38)) {
            b.a(parcel, 38, koVar.getLongitude());
        }
        if (kf.contains(39)) {
            b.a(parcel, 39, koVar.getName(), true);
        }
        if (kf.contains(36)) {
            b.a(parcel, 36, koVar.getLatitude());
        }
        if (kf.contains(37)) {
            b.a(parcel, 37, (Parcelable) koVar.kq(), i, true);
        }
        if (kf.contains(42)) {
            b.a(parcel, 42, koVar.getPlayerType(), true);
        }
        if (kf.contains(43)) {
            b.a(parcel, 43, koVar.getPostOfficeBoxNumber(), true);
        }
        if (kf.contains(40)) {
            b.a(parcel, 40, (Parcelable) koVar.kr(), i, true);
        }
        if (kf.contains(41)) {
            b.b(parcel, 41, koVar.ks(), true);
        }
        if (kf.contains(46)) {
            b.a(parcel, 46, (Parcelable) koVar.kt(), i, true);
        }
        if (kf.contains(47)) {
            b.a(parcel, 47, koVar.getStartDate(), true);
        }
        if (kf.contains(44)) {
            b.a(parcel, 44, koVar.getPostalCode(), true);
        }
        if (kf.contains(45)) {
            b.a(parcel, 45, koVar.getRatingValue(), true);
        }
        if (kf.contains(51)) {
            b.a(parcel, 51, koVar.getThumbnailUrl(), true);
        }
        if (kf.contains(50)) {
            b.a(parcel, 50, (Parcelable) koVar.ku(), i, true);
        }
        if (kf.contains(49)) {
            b.a(parcel, 49, koVar.getText(), true);
        }
        if (kf.contains(48)) {
            b.a(parcel, 48, koVar.getStreetAddress(), true);
        }
        if (kf.contains(55)) {
            b.a(parcel, 55, koVar.getWidth(), true);
        }
        if (kf.contains(54)) {
            b.a(parcel, 54, koVar.getUrl(), true);
        }
        if (kf.contains(53)) {
            b.a(parcel, 53, koVar.getType(), true);
        }
        if (kf.contains(52)) {
            b.a(parcel, 52, koVar.getTickerSymbol(), true);
        }
        if (kf.contains(56)) {
            b.a(parcel, 56, koVar.getWorstRating(), true);
        }
        b.G(parcel, C);
    }

    /* renamed from: bE */
    public ko createFromParcel(Parcel parcel) {
        int B = a.B(parcel);
        HashSet hashSet = new HashSet();
        int i = 0;
        ko koVar = null;
        ArrayList<String> arrayList = null;
        ko koVar2 = null;
        String str = null;
        String str2 = null;
        String str3 = null;
        ArrayList arrayList2 = null;
        int i2 = 0;
        ArrayList arrayList3 = null;
        ko koVar3 = null;
        ArrayList arrayList4 = null;
        String str4 = null;
        String str5 = null;
        ko koVar4 = null;
        String str6 = null;
        String str7 = null;
        String str8 = null;
        ArrayList arrayList5 = null;
        String str9 = null;
        String str10 = null;
        String str11 = null;
        String str12 = null;
        String str13 = null;
        String str14 = null;
        String str15 = null;
        String str16 = null;
        String str17 = null;
        ko koVar5 = null;
        String str18 = null;
        String str19 = null;
        String str20 = null;
        String str21 = null;
        ko koVar6 = null;
        double d = 0.0d;
        ko koVar7 = null;
        double d2 = 0.0d;
        String str22 = null;
        ko koVar8 = null;
        ArrayList arrayList6 = null;
        String str23 = null;
        String str24 = null;
        String str25 = null;
        String str26 = null;
        ko koVar9 = null;
        String str27 = null;
        String str28 = null;
        String str29 = null;
        ko koVar10 = null;
        String str30 = null;
        String str31 = null;
        String str32 = null;
        String str33 = null;
        String str34 = null;
        String str35 = null;
        while (parcel.dataPosition() < B) {
            int A = a.A(parcel);
            switch (a.ar(A)) {
                case 1:
                    i = a.g(parcel, A);
                    hashSet.add(1);
                    break;
                case 2:
                    hashSet.add(2);
                    koVar = (ko) a.a(parcel, A, ko.CREATOR);
                    break;
                case 3:
                    arrayList = a.B(parcel, A);
                    hashSet.add(3);
                    break;
                case 4:
                    hashSet.add(4);
                    koVar2 = (ko) a.a(parcel, A, ko.CREATOR);
                    break;
                case 5:
                    str = a.o(parcel, A);
                    hashSet.add(5);
                    break;
                case 6:
                    str2 = a.o(parcel, A);
                    hashSet.add(6);
                    break;
                case 7:
                    str3 = a.o(parcel, A);
                    hashSet.add(7);
                    break;
                case 8:
                    arrayList2 = a.c(parcel, A, ko.CREATOR);
                    hashSet.add(8);
                    break;
                case 9:
                    i2 = a.g(parcel, A);
                    hashSet.add(9);
                    break;
                case 10:
                    arrayList3 = a.c(parcel, A, ko.CREATOR);
                    hashSet.add(10);
                    break;
                case 11:
                    hashSet.add(11);
                    koVar3 = (ko) a.a(parcel, A, ko.CREATOR);
                    break;
                case 12:
                    arrayList4 = a.c(parcel, A, ko.CREATOR);
                    hashSet.add(12);
                    break;
                case 13:
                    str4 = a.o(parcel, A);
                    hashSet.add(13);
                    break;
                case 14:
                    str5 = a.o(parcel, A);
                    hashSet.add(14);
                    break;
                case 15:
                    hashSet.add(15);
                    koVar4 = (ko) a.a(parcel, A, ko.CREATOR);
                    break;
                case 16:
                    str6 = a.o(parcel, A);
                    hashSet.add(16);
                    break;
                case 17:
                    str7 = a.o(parcel, A);
                    hashSet.add(17);
                    break;
                case 18:
                    str8 = a.o(parcel, A);
                    hashSet.add(18);
                    break;
                case TimeUtils.HUNDRED_DAY_FIELD_LEN:
                    arrayList5 = a.c(parcel, A, ko.CREATOR);
                    hashSet.add(19);
                    break;
                case BrailleInputEvent.CMD_ACTIVATE_CURRENT /*20*/:
                    str9 = a.o(parcel, A);
                    hashSet.add(20);
                    break;
                case 21:
                    str10 = a.o(parcel, A);
                    hashSet.add(21);
                    break;
                case 22:
                    str11 = a.o(parcel, A);
                    hashSet.add(22);
                    break;
                case 23:
                    str12 = a.o(parcel, A);
                    hashSet.add(23);
                    break;
                case 24:
                    str13 = a.o(parcel, A);
                    hashSet.add(24);
                    break;
                case 25:
                    str14 = a.o(parcel, A);
                    hashSet.add(25);
                    break;
                case 26:
                    str15 = a.o(parcel, A);
                    hashSet.add(26);
                    break;
                case 27:
                    str16 = a.o(parcel, A);
                    hashSet.add(27);
                    break;
                case 28:
                    str17 = a.o(parcel, A);
                    hashSet.add(28);
                    break;
                case 29:
                    hashSet.add(29);
                    koVar5 = (ko) a.a(parcel, A, ko.CREATOR);
                    break;
                case BrailleInputEvent.CMD_SCROLL_BACKWARD /*30*/:
                    str18 = a.o(parcel, A);
                    hashSet.add(30);
                    break;
                case 31:
                    str19 = a.o(parcel, A);
                    hashSet.add(31);
                    break;
                case 32:
                    str20 = a.o(parcel, A);
                    hashSet.add(32);
                    break;
                case 33:
                    str21 = a.o(parcel, A);
                    hashSet.add(33);
                    break;
                case 34:
                    hashSet.add(34);
                    koVar6 = (ko) a.a(parcel, A, ko.CREATOR);
                    break;
                case 36:
                    d = a.m(parcel, A);
                    hashSet.add(36);
                    break;
                case 37:
                    hashSet.add(37);
                    koVar7 = (ko) a.a(parcel, A, ko.CREATOR);
                    break;
                case 38:
                    d2 = a.m(parcel, A);
                    hashSet.add(38);
                    break;
                case 39:
                    str22 = a.o(parcel, A);
                    hashSet.add(39);
                    break;
                case BrailleInputEvent.CMD_SELECTION_START /*40*/:
                    hashSet.add(40);
                    koVar8 = (ko) a.a(parcel, A, ko.CREATOR);
                    break;
                case BrailleInputEvent.CMD_SELECTION_END /*41*/:
                    arrayList6 = a.c(parcel, A, ko.CREATOR);
                    hashSet.add(41);
                    break;
                case BrailleInputEvent.CMD_SELECTION_SELECT_ALL /*42*/:
                    str23 = a.o(parcel, A);
                    hashSet.add(42);
                    break;
                case BrailleInputEvent.CMD_SELECTION_CUT /*43*/:
                    str24 = a.o(parcel, A);
                    hashSet.add(43);
                    break;
                case BrailleInputEvent.CMD_SELECTION_COPY /*44*/:
                    str25 = a.o(parcel, A);
                    hashSet.add(44);
                    break;
                case BrailleInputEvent.CMD_SELECTION_PASTE /*45*/:
                    str26 = a.o(parcel, A);
                    hashSet.add(45);
                    break;
                case 46:
                    hashSet.add(46);
                    koVar9 = (ko) a.a(parcel, A, ko.CREATOR);
                    break;
                case 47:
                    str27 = a.o(parcel, A);
                    hashSet.add(47);
                    break;
                case 48:
                    str28 = a.o(parcel, A);
                    hashSet.add(48);
                    break;
                case 49:
                    str29 = a.o(parcel, A);
                    hashSet.add(49);
                    break;
                case 50:
                    hashSet.add(50);
                    koVar10 = (ko) a.a(parcel, A, ko.CREATOR);
                    break;
                case 51:
                    str30 = a.o(parcel, A);
                    hashSet.add(51);
                    break;
                case 52:
                    str31 = a.o(parcel, A);
                    hashSet.add(52);
                    break;
                case 53:
                    str32 = a.o(parcel, A);
                    hashSet.add(53);
                    break;
                case 54:
                    str33 = a.o(parcel, A);
                    hashSet.add(54);
                    break;
                case 55:
                    str34 = a.o(parcel, A);
                    hashSet.add(55);
                    break;
                case 56:
                    str35 = a.o(parcel, A);
                    hashSet.add(56);
                    break;
                default:
                    a.b(parcel, A);
                    break;
            }
        }
        if (parcel.dataPosition() == B) {
            return new ko(hashSet, i, koVar, arrayList, koVar2, str, str2, str3, arrayList2, i2, arrayList3, koVar3, arrayList4, str4, str5, koVar4, str6, str7, str8, arrayList5, str9, str10, str11, str12, str13, str14, str15, str16, str17, koVar5, str18, str19, str20, str21, koVar6, d, koVar7, d2, str22, koVar8, arrayList6, str23, str24, str25, str26, koVar9, str27, str28, str29, koVar10, str30, str31, str32, str33, str34, str35);
        }
        throw new a.C0014a("Overread allowed size end=" + B, parcel);
    }

    /* renamed from: db */
    public ko[] newArray(int i) {
        return new ko[i];
    }
}
