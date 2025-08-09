package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.util.TimeUtils;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.internal.ih;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.wallet.wobs.d;
import com.google.android.gms.wallet.wobs.f;
import com.google.android.gms.wallet.wobs.l;
import com.google.android.gms.wallet.wobs.n;
import com.google.android.gms.wallet.wobs.p;
import com.googlecode.eyesfree.braille.display.BrailleInputEvent;
import java.util.ArrayList;

public class j implements Parcelable.Creator<LoyaltyWalletObject> {
    static void a(LoyaltyWalletObject loyaltyWalletObject, Parcel parcel, int i) {
        int C = b.C(parcel);
        b.c(parcel, 1, loyaltyWalletObject.getVersionCode());
        b.a(parcel, 2, loyaltyWalletObject.eC, false);
        b.a(parcel, 3, loyaltyWalletObject.ajf, false);
        b.a(parcel, 4, loyaltyWalletObject.ajg, false);
        b.a(parcel, 5, loyaltyWalletObject.ajh, false);
        b.a(parcel, 6, loyaltyWalletObject.aji, false);
        b.a(parcel, 7, loyaltyWalletObject.ajj, false);
        b.a(parcel, 8, loyaltyWalletObject.ajk, false);
        b.a(parcel, 9, loyaltyWalletObject.ajl, false);
        b.a(parcel, 10, loyaltyWalletObject.ajm, false);
        b.a(parcel, 11, loyaltyWalletObject.ajn, false);
        b.c(parcel, 12, loyaltyWalletObject.state);
        b.b(parcel, 13, loyaltyWalletObject.ajo, false);
        b.a(parcel, 14, (Parcelable) loyaltyWalletObject.ajp, i, false);
        b.b(parcel, 15, loyaltyWalletObject.ajq, false);
        b.a(parcel, 17, loyaltyWalletObject.ajs, false);
        b.a(parcel, 16, loyaltyWalletObject.ajr, false);
        b.a(parcel, 19, loyaltyWalletObject.aju);
        b.b(parcel, 18, loyaltyWalletObject.ajt, false);
        b.b(parcel, 21, loyaltyWalletObject.ajw, false);
        b.b(parcel, 20, loyaltyWalletObject.ajv, false);
        b.a(parcel, 23, (Parcelable) loyaltyWalletObject.ajy, i, false);
        b.b(parcel, 22, loyaltyWalletObject.ajx, false);
        b.G(parcel, C);
    }

    /* renamed from: bY */
    public LoyaltyWalletObject createFromParcel(Parcel parcel) {
        int B = a.B(parcel);
        int i = 0;
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        String str6 = null;
        String str7 = null;
        String str8 = null;
        String str9 = null;
        String str10 = null;
        int i2 = 0;
        ArrayList<p> fV = ih.fV();
        l lVar = null;
        ArrayList fV2 = ih.fV();
        String str11 = null;
        String str12 = null;
        ArrayList<d> fV3 = ih.fV();
        boolean z = false;
        ArrayList<n> fV4 = ih.fV();
        ArrayList<com.google.android.gms.wallet.wobs.j> fV5 = ih.fV();
        ArrayList<n> fV6 = ih.fV();
        f fVar = null;
        while (parcel.dataPosition() < B) {
            int A = a.A(parcel);
            switch (a.ar(A)) {
                case 1:
                    i = a.g(parcel, A);
                    break;
                case 2:
                    str = a.o(parcel, A);
                    break;
                case 3:
                    str2 = a.o(parcel, A);
                    break;
                case 4:
                    str3 = a.o(parcel, A);
                    break;
                case 5:
                    str4 = a.o(parcel, A);
                    break;
                case 6:
                    str5 = a.o(parcel, A);
                    break;
                case 7:
                    str6 = a.o(parcel, A);
                    break;
                case 8:
                    str7 = a.o(parcel, A);
                    break;
                case 9:
                    str8 = a.o(parcel, A);
                    break;
                case 10:
                    str9 = a.o(parcel, A);
                    break;
                case 11:
                    str10 = a.o(parcel, A);
                    break;
                case 12:
                    i2 = a.g(parcel, A);
                    break;
                case 13:
                    fV = a.c(parcel, A, p.CREATOR);
                    break;
                case 14:
                    lVar = (l) a.a(parcel, A, l.CREATOR);
                    break;
                case 15:
                    fV2 = a.c(parcel, A, LatLng.CREATOR);
                    break;
                case 16:
                    str11 = a.o(parcel, A);
                    break;
                case 17:
                    str12 = a.o(parcel, A);
                    break;
                case 18:
                    fV3 = a.c(parcel, A, d.CREATOR);
                    break;
                case TimeUtils.HUNDRED_DAY_FIELD_LEN:
                    z = a.c(parcel, A);
                    break;
                case BrailleInputEvent.CMD_ACTIVATE_CURRENT /*20*/:
                    fV4 = a.c(parcel, A, n.CREATOR);
                    break;
                case 21:
                    fV5 = a.c(parcel, A, com.google.android.gms.wallet.wobs.j.CREATOR);
                    break;
                case 22:
                    fV6 = a.c(parcel, A, n.CREATOR);
                    break;
                case 23:
                    fVar = (f) a.a(parcel, A, f.CREATOR);
                    break;
                default:
                    a.b(parcel, A);
                    break;
            }
        }
        if (parcel.dataPosition() == B) {
            return new LoyaltyWalletObject(i, str, str2, str3, str4, str5, str6, str7, str8, str9, str10, i2, fV, lVar, fV2, str11, str12, fV3, z, fV4, fV5, fV6, fVar);
        }
        throw new a.C0014a("Overread allowed size end=" + B, parcel);
    }

    /* renamed from: dE */
    public LoyaltyWalletObject[] newArray(int i) {
        return new LoyaltyWalletObject[i];
    }
}
