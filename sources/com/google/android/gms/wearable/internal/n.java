package com.google.android.gms.wearable.internal;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class n implements Parcelable.Creator<m> {
    static void a(m mVar, Parcel parcel, int i) {
        int C = b.C(parcel);
        b.c(parcel, 1, mVar.xJ);
        b.a(parcel, 2, (Parcelable) mVar.getUri(), i, false);
        b.a(parcel, 4, mVar.nh(), false);
        b.a(parcel, 5, mVar.getData(), false);
        b.G(parcel, C);
    }

    /* renamed from: cx */
    public m createFromParcel(Parcel parcel) {
        byte[] r;
        Bundle bundle;
        Uri uri;
        int i;
        byte[] bArr = null;
        int B = a.B(parcel);
        int i2 = 0;
        Bundle bundle2 = null;
        Uri uri2 = null;
        while (parcel.dataPosition() < B) {
            int A = a.A(parcel);
            switch (a.ar(A)) {
                case 1:
                    byte[] bArr2 = bArr;
                    bundle = bundle2;
                    uri = uri2;
                    i = a.g(parcel, A);
                    r = bArr2;
                    break;
                case 2:
                    i = i2;
                    Bundle bundle3 = bundle2;
                    uri = (Uri) a.a(parcel, A, Uri.CREATOR);
                    r = bArr;
                    bundle = bundle3;
                    break;
                case 4:
                    uri = uri2;
                    i = i2;
                    byte[] bArr3 = bArr;
                    bundle = a.q(parcel, A);
                    r = bArr3;
                    break;
                case 5:
                    r = a.r(parcel, A);
                    bundle = bundle2;
                    uri = uri2;
                    i = i2;
                    break;
                default:
                    a.b(parcel, A);
                    r = bArr;
                    bundle = bundle2;
                    uri = uri2;
                    i = i2;
                    break;
            }
            i2 = i;
            uri2 = uri;
            bundle2 = bundle;
            bArr = r;
        }
        if (parcel.dataPosition() == B) {
            return new m(i2, uri2, bundle2, bArr);
        }
        throw new a.C0014a("Overread allowed size end=" + B, parcel);
    }

    /* renamed from: eg */
    public m[] newArray(int i) {
        return new m[i];
    }
}
