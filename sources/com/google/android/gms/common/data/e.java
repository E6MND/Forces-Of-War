package com.google.android.gms.common.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class e<T extends SafeParcelable> extends DataBuffer<T> {
    private static final String[] EB = {"data"};
    private final Parcelable.Creator<T> EC;

    public e(DataHolder dataHolder, Parcelable.Creator<T> creator) {
        super(dataHolder);
        this.EC = creator;
    }

    /* renamed from: ad */
    public T get(int i) {
        byte[] f = this.DD.f("data", i, 0);
        Parcel obtain = Parcel.obtain();
        obtain.unmarshall(f, 0, f.length);
        obtain.setDataPosition(0);
        T t = (SafeParcelable) this.EC.createFromParcel(obtain);
        obtain.recycle();
        return t;
    }
}
