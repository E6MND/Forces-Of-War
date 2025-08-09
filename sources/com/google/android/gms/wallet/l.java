package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.identity.intents.model.CountrySpecification;
import java.util.ArrayList;

public class l implements Parcelable.Creator<MaskedWalletRequest> {
    static void a(MaskedWalletRequest maskedWalletRequest, Parcel parcel, int i) {
        int C = b.C(parcel);
        b.c(parcel, 1, maskedWalletRequest.getVersionCode());
        b.a(parcel, 2, maskedWalletRequest.aiO, false);
        b.a(parcel, 3, maskedWalletRequest.ajC);
        b.a(parcel, 4, maskedWalletRequest.ajD);
        b.a(parcel, 5, maskedWalletRequest.ajE);
        b.a(parcel, 6, maskedWalletRequest.ajF, false);
        b.a(parcel, 7, maskedWalletRequest.aiI, false);
        b.a(parcel, 8, maskedWalletRequest.ajG, false);
        b.a(parcel, 9, (Parcelable) maskedWalletRequest.aiX, i, false);
        b.a(parcel, 10, maskedWalletRequest.ajH);
        b.a(parcel, 11, maskedWalletRequest.ajI);
        b.a(parcel, 12, (T[]) maskedWalletRequest.ajJ, i, false);
        b.a(parcel, 13, maskedWalletRequest.ajK);
        b.a(parcel, 14, maskedWalletRequest.ajL);
        b.b(parcel, 15, maskedWalletRequest.ajM, false);
        b.G(parcel, C);
    }

    /* renamed from: ca */
    public MaskedWalletRequest createFromParcel(Parcel parcel) {
        int B = a.B(parcel);
        int i = 0;
        String str = null;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        Cart cart = null;
        boolean z4 = false;
        boolean z5 = false;
        CountrySpecification[] countrySpecificationArr = null;
        boolean z6 = true;
        boolean z7 = true;
        ArrayList<CountrySpecification> arrayList = null;
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
                    z = a.c(parcel, A);
                    break;
                case 4:
                    z2 = a.c(parcel, A);
                    break;
                case 5:
                    z3 = a.c(parcel, A);
                    break;
                case 6:
                    str2 = a.o(parcel, A);
                    break;
                case 7:
                    str3 = a.o(parcel, A);
                    break;
                case 8:
                    str4 = a.o(parcel, A);
                    break;
                case 9:
                    cart = (Cart) a.a(parcel, A, Cart.CREATOR);
                    break;
                case 10:
                    z4 = a.c(parcel, A);
                    break;
                case 11:
                    z5 = a.c(parcel, A);
                    break;
                case 12:
                    countrySpecificationArr = (CountrySpecification[]) a.b(parcel, A, CountrySpecification.CREATOR);
                    break;
                case 13:
                    z6 = a.c(parcel, A);
                    break;
                case 14:
                    z7 = a.c(parcel, A);
                    break;
                case 15:
                    arrayList = a.c(parcel, A, CountrySpecification.CREATOR);
                    break;
                default:
                    a.b(parcel, A);
                    break;
            }
        }
        if (parcel.dataPosition() == B) {
            return new MaskedWalletRequest(i, str, z, z2, z3, str2, str3, str4, cart, z4, z5, countrySpecificationArr, z6, z7, arrayList);
        }
        throw new a.C0014a("Overread allowed size end=" + B, parcel);
    }

    /* renamed from: dG */
    public MaskedWalletRequest[] newArray(int i) {
        return new MaskedWalletRequest[i];
    }
}
