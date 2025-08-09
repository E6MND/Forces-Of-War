package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.hn;
import com.google.android.gms.wearable.DataItemAsset;

public class DataItemAssetParcelable implements SafeParcelable, DataItemAsset {
    public static final Parcelable.Creator<DataItemAssetParcelable> CREATOR = new j();
    private final String JI;
    private final String xD;
    final int xJ;

    DataItemAssetParcelable(int versionCode, String id, String key) {
        this.xJ = versionCode;
        this.xD = id;
        this.JI = key;
    }

    public DataItemAssetParcelable(DataItemAsset value) {
        this.xJ = 1;
        this.xD = (String) hn.f(value.getId());
        this.JI = (String) hn.f(value.getDataItemKey());
    }

    public int describeContents() {
        return 0;
    }

    public String getDataItemKey() {
        return this.JI;
    }

    public String getId() {
        return this.xD;
    }

    public boolean isDataValid() {
        return true;
    }

    /* renamed from: nl */
    public DataItemAsset freeze() {
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("DataItemAssetParcelable[");
        sb.append("@");
        sb.append(Integer.toHexString(hashCode()));
        if (this.xD == null) {
            sb.append(",noid");
        } else {
            sb.append(",");
            sb.append(this.xD);
        }
        sb.append(", key=");
        sb.append(this.JI);
        sb.append("]");
        return sb.toString();
    }

    public void writeToParcel(Parcel dest, int flags) {
        j.a(this, dest, flags);
    }
}
