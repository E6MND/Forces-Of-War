package com.google.android.gms.drive;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class StorageStats implements SafeParcelable {
    public static final Parcelable.Creator<StorageStats> CREATOR = new e();
    final long HY;
    final long HZ;
    final long Ia;
    final long Ib;
    final int Ic;
    final int xJ;

    StorageStats(int versionCode, long metadataSizeBytes, long cachedContentsSizeBytes, long pinnedItemsSizeBytes, long totalSizeBytes, int numPinnedItems) {
        this.xJ = versionCode;
        this.HY = metadataSizeBytes;
        this.HZ = cachedContentsSizeBytes;
        this.Ia = pinnedItemsSizeBytes;
        this.Ib = totalSizeBytes;
        this.Ic = numPinnedItems;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        e.a(this, out, flags);
    }
}
