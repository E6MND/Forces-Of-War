package com.google.android.gms.drive.realtime.internal.event;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class a implements Parcelable.Creator<ParcelableEvent> {
    static void a(ParcelableEvent parcelableEvent, Parcel parcel, int i) {
        int C = b.C(parcel);
        b.c(parcel, 1, parcelableEvent.xJ);
        b.a(parcel, 2, parcelableEvent.rO, false);
        b.a(parcel, 3, parcelableEvent.Lj, false);
        b.a(parcel, 4, parcelableEvent.Lp);
        b.a(parcel, 5, parcelableEvent.Ln, false);
        b.a(parcel, 6, parcelableEvent.Lq, false);
        b.a(parcel, 7, (Parcelable) parcelableEvent.Lr, i, false);
        b.a(parcel, 8, (Parcelable) parcelableEvent.Ls, i, false);
        b.a(parcel, 9, (Parcelable) parcelableEvent.Lt, i, false);
        b.a(parcel, 10, (Parcelable) parcelableEvent.Lu, i, false);
        b.a(parcel, 11, (Parcelable) parcelableEvent.Lv, i, false);
        b.a(parcel, 12, (Parcelable) parcelableEvent.Lw, i, false);
        b.a(parcel, 13, (Parcelable) parcelableEvent.Lx, i, false);
        b.G(parcel, C);
    }

    /* renamed from: aT */
    public ParcelableEvent createFromParcel(Parcel parcel) {
        int B = com.google.android.gms.common.internal.safeparcel.a.B(parcel);
        int i = 0;
        String str = null;
        String str2 = null;
        boolean z = false;
        String str3 = null;
        String str4 = null;
        TextInsertedDetails textInsertedDetails = null;
        TextDeletedDetails textDeletedDetails = null;
        ValuesAddedDetails valuesAddedDetails = null;
        ValuesRemovedDetails valuesRemovedDetails = null;
        ValuesSetDetails valuesSetDetails = null;
        ValueChangedDetails valueChangedDetails = null;
        ReferenceShiftedDetails referenceShiftedDetails = null;
        while (parcel.dataPosition() < B) {
            int A = com.google.android.gms.common.internal.safeparcel.a.A(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.ar(A)) {
                case 1:
                    i = com.google.android.gms.common.internal.safeparcel.a.g(parcel, A);
                    break;
                case 2:
                    str = com.google.android.gms.common.internal.safeparcel.a.o(parcel, A);
                    break;
                case 3:
                    str2 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, A);
                    break;
                case 4:
                    z = com.google.android.gms.common.internal.safeparcel.a.c(parcel, A);
                    break;
                case 5:
                    str3 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, A);
                    break;
                case 6:
                    str4 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, A);
                    break;
                case 7:
                    textInsertedDetails = (TextInsertedDetails) com.google.android.gms.common.internal.safeparcel.a.a(parcel, A, TextInsertedDetails.CREATOR);
                    break;
                case 8:
                    textDeletedDetails = (TextDeletedDetails) com.google.android.gms.common.internal.safeparcel.a.a(parcel, A, TextDeletedDetails.CREATOR);
                    break;
                case 9:
                    valuesAddedDetails = (ValuesAddedDetails) com.google.android.gms.common.internal.safeparcel.a.a(parcel, A, ValuesAddedDetails.CREATOR);
                    break;
                case 10:
                    valuesRemovedDetails = (ValuesRemovedDetails) com.google.android.gms.common.internal.safeparcel.a.a(parcel, A, ValuesRemovedDetails.CREATOR);
                    break;
                case 11:
                    valuesSetDetails = (ValuesSetDetails) com.google.android.gms.common.internal.safeparcel.a.a(parcel, A, ValuesSetDetails.CREATOR);
                    break;
                case 12:
                    valueChangedDetails = (ValueChangedDetails) com.google.android.gms.common.internal.safeparcel.a.a(parcel, A, ValueChangedDetails.CREATOR);
                    break;
                case 13:
                    referenceShiftedDetails = (ReferenceShiftedDetails) com.google.android.gms.common.internal.safeparcel.a.a(parcel, A, ReferenceShiftedDetails.CREATOR);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, A);
                    break;
            }
        }
        if (parcel.dataPosition() == B) {
            return new ParcelableEvent(i, str, str2, z, str3, str4, textInsertedDetails, textDeletedDetails, valuesAddedDetails, valuesRemovedDetails, valuesSetDetails, valueChangedDetails, referenceShiftedDetails);
        }
        throw new a.C0014a("Overread allowed size end=" + B, parcel);
    }

    /* renamed from: bQ */
    public ParcelableEvent[] newArray(int i) {
        return new ParcelableEvent[i];
    }
}
