package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.images.WebImage;
import com.google.android.gms.common.internal.safeparcel.a;
import java.util.ArrayList;

public class b implements Parcelable.Creator<CastDevice> {
    static void a(CastDevice castDevice, Parcel parcel, int i) {
        int C = com.google.android.gms.common.internal.safeparcel.b.C(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, castDevice.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, castDevice.getDeviceId(), false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, castDevice.Af, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 4, castDevice.getFriendlyName(), false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 5, castDevice.getModelName(), false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 6, castDevice.getDeviceVersion(), false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 7, castDevice.getServicePort());
        com.google.android.gms.common.internal.safeparcel.b.b(parcel, 8, castDevice.getIcons(), false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 9, castDevice.getCapabilities());
        com.google.android.gms.common.internal.safeparcel.b.G(parcel, C);
    }

    /* renamed from: P */
    public CastDevice[] newArray(int i) {
        return new CastDevice[i];
    }

    /* renamed from: s */
    public CastDevice createFromParcel(Parcel parcel) {
        int i = 0;
        ArrayList<WebImage> arrayList = null;
        int B = a.B(parcel);
        int i2 = 0;
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        int i3 = 0;
        while (parcel.dataPosition() < B) {
            int A = a.A(parcel);
            switch (a.ar(A)) {
                case 1:
                    i3 = a.g(parcel, A);
                    break;
                case 2:
                    str5 = a.o(parcel, A);
                    break;
                case 3:
                    str4 = a.o(parcel, A);
                    break;
                case 4:
                    str3 = a.o(parcel, A);
                    break;
                case 5:
                    str2 = a.o(parcel, A);
                    break;
                case 6:
                    str = a.o(parcel, A);
                    break;
                case 7:
                    i2 = a.g(parcel, A);
                    break;
                case 8:
                    arrayList = a.c(parcel, A, WebImage.CREATOR);
                    break;
                case 9:
                    i = a.g(parcel, A);
                    break;
                default:
                    a.b(parcel, A);
                    break;
            }
        }
        if (parcel.dataPosition() == B) {
            return new CastDevice(i3, str5, str4, str3, str2, str, i2, arrayList, i);
        }
        throw new a.C0014a("Overread allowed size end=" + B, parcel);
    }
}
