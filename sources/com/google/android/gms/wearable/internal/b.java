package com.google.android.gms.wearable.internal;

import android.content.IntentFilter;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.wearable.internal.ac;

public class b implements SafeParcelable {
    public static final Parcelable.Creator<b> CREATOR = new c();
    public final ac alw;
    public final IntentFilter[] alx;
    final int xJ;

    b(int i, IBinder iBinder, IntentFilter[] intentFilterArr) {
        this.xJ = i;
        if (iBinder != null) {
            this.alw = ac.a.bx(iBinder);
        } else {
            this.alw = null;
        }
        this.alx = intentFilterArr;
    }

    public b(av avVar) {
        this.xJ = 1;
        this.alw = avVar;
        this.alx = avVar.np();
    }

    public int describeContents() {
        return 0;
    }

    /* access modifiers changed from: package-private */
    public IBinder nj() {
        if (this.alw == null) {
            return null;
        }
        return this.alw.asBinder();
    }

    public void writeToParcel(Parcel dest, int flags) {
        c.a(this, dest, flags);
    }
}
