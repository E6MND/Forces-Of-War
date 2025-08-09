package com.google.android.gms.wearable.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.wearable.internal.ac;

public class ao implements SafeParcelable {
    public static final Parcelable.Creator<ao> CREATOR = new ap();
    public final ac alw;
    final int xJ;

    ao(int i, IBinder iBinder) {
        this.xJ = i;
        if (iBinder != null) {
            this.alw = ac.a.bx(iBinder);
        } else {
            this.alw = null;
        }
    }

    public ao(ac acVar) {
        this.xJ = 1;
        this.alw = acVar;
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
        ap.a(this, dest, flags);
    }
}
