package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class e implements Parcelable.Creator<d> {
    static void a(d dVar, Parcel parcel, int i) {
        int C = b.C(parcel);
        b.c(parcel, 1, dVar.getVersionCode());
        b.a(parcel, 2, (Parcelable) dVar.aiL, i, false);
        b.a(parcel, 3, (Parcelable) dVar.aiM, i, false);
        b.G(parcel, C);
    }

    /* renamed from: bT */
    public d createFromParcel(Parcel parcel) {
        OfferWalletObject offerWalletObject;
        LoyaltyWalletObject loyaltyWalletObject;
        int i;
        OfferWalletObject offerWalletObject2 = null;
        int B = a.B(parcel);
        int i2 = 0;
        LoyaltyWalletObject loyaltyWalletObject2 = null;
        while (parcel.dataPosition() < B) {
            int A = a.A(parcel);
            switch (a.ar(A)) {
                case 1:
                    OfferWalletObject offerWalletObject3 = offerWalletObject2;
                    loyaltyWalletObject = loyaltyWalletObject2;
                    i = a.g(parcel, A);
                    offerWalletObject = offerWalletObject3;
                    break;
                case 2:
                    i = i2;
                    LoyaltyWalletObject loyaltyWalletObject3 = (LoyaltyWalletObject) a.a(parcel, A, LoyaltyWalletObject.CREATOR);
                    offerWalletObject = offerWalletObject2;
                    loyaltyWalletObject = loyaltyWalletObject3;
                    break;
                case 3:
                    offerWalletObject = (OfferWalletObject) a.a(parcel, A, OfferWalletObject.CREATOR);
                    loyaltyWalletObject = loyaltyWalletObject2;
                    i = i2;
                    break;
                default:
                    a.b(parcel, A);
                    offerWalletObject = offerWalletObject2;
                    loyaltyWalletObject = loyaltyWalletObject2;
                    i = i2;
                    break;
            }
            i2 = i;
            loyaltyWalletObject2 = loyaltyWalletObject;
            offerWalletObject2 = offerWalletObject;
        }
        if (parcel.dataPosition() == B) {
            return new d(i2, loyaltyWalletObject2, offerWalletObject2);
        }
        throw new a.C0014a("Overread allowed size end=" + B, parcel);
    }

    /* renamed from: dz */
    public d[] newArray(int i) {
        return new d[i];
    }
}
