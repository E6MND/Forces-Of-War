package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.hz;

public class hu implements SafeParcelable {
    public static final hv CREATOR = new hv();
    private final hw GS;
    private final int xJ;

    hu(int i, hw hwVar) {
        this.xJ = i;
        this.GS = hwVar;
    }

    private hu(hw hwVar) {
        this.xJ = 1;
        this.GS = hwVar;
    }

    public static hu a(hz.b<?, ?> bVar) {
        if (bVar instanceof hw) {
            return new hu((hw) bVar);
        }
        throw new IllegalArgumentException("Unsupported safe parcelable field converter class.");
    }

    public int describeContents() {
        hv hvVar = CREATOR;
        return 0;
    }

    /* access modifiers changed from: package-private */
    public hw fw() {
        return this.GS;
    }

    public hz.b<?, ?> fx() {
        if (this.GS != null) {
            return this.GS;
        }
        throw new IllegalStateException("There was no converter wrapped in this ConverterWrapper.");
    }

    /* access modifiers changed from: package-private */
    public int getVersionCode() {
        return this.xJ;
    }

    public void writeToParcel(Parcel out, int flags) {
        hv hvVar = CREATOR;
        hv.a(this, out, flags);
    }
}
