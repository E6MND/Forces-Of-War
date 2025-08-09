package com.google.android.gms.drive.metadata.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.drive.metadata.CustomPropertyKey;

public class c implements Parcelable.Creator<CustomProperty> {
    static void a(CustomProperty customProperty, Parcel parcel, int i) {
        int C = b.C(parcel);
        b.c(parcel, 1, customProperty.xJ);
        b.a(parcel, 2, (Parcelable) customProperty.JN, i, false);
        b.a(parcel, 3, customProperty.mValue, false);
        b.G(parcel, C);
    }

    /* renamed from: aB */
    public CustomProperty createFromParcel(Parcel parcel) {
        String o;
        CustomPropertyKey customPropertyKey;
        int i;
        String str = null;
        int B = a.B(parcel);
        int i2 = 0;
        CustomPropertyKey customPropertyKey2 = null;
        while (parcel.dataPosition() < B) {
            int A = a.A(parcel);
            switch (a.ar(A)) {
                case 1:
                    String str2 = str;
                    customPropertyKey = customPropertyKey2;
                    i = a.g(parcel, A);
                    o = str2;
                    break;
                case 2:
                    i = i2;
                    CustomPropertyKey customPropertyKey3 = (CustomPropertyKey) a.a(parcel, A, CustomPropertyKey.CREATOR);
                    o = str;
                    customPropertyKey = customPropertyKey3;
                    break;
                case 3:
                    o = a.o(parcel, A);
                    customPropertyKey = customPropertyKey2;
                    i = i2;
                    break;
                default:
                    a.b(parcel, A);
                    o = str;
                    customPropertyKey = customPropertyKey2;
                    i = i2;
                    break;
            }
            i2 = i;
            customPropertyKey2 = customPropertyKey;
            str = o;
        }
        if (parcel.dataPosition() == B) {
            return new CustomProperty(i2, customPropertyKey2, str);
        }
        throw new a.C0014a("Overread allowed size end=" + B, parcel);
    }

    /* renamed from: bx */
    public CustomProperty[] newArray(int i) {
        return new CustomProperty[i];
    }
}
