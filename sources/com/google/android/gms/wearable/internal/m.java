package com.google.android.gms.wearable.internal;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.wearable.DataItem;
import com.google.android.gms.wearable.DataItemAsset;
import java.util.HashMap;
import java.util.Map;

public class m implements SafeParcelable, DataItem {
    public static final Parcelable.Creator<m> CREATOR = new n();
    private byte[] TC;
    private final Map<String, DataItemAsset> alH;
    private final Uri mUri;
    final int xJ;

    m(int i, Uri uri, Bundle bundle, byte[] bArr) {
        this.xJ = i;
        this.mUri = uri;
        HashMap hashMap = new HashMap();
        bundle.setClassLoader(DataItemAssetParcelable.class.getClassLoader());
        for (String str : bundle.keySet()) {
            hashMap.put(str, (DataItemAssetParcelable) bundle.getParcelable(str));
        }
        this.alH = hashMap;
        this.TC = bArr;
    }

    public int describeContents() {
        return 0;
    }

    public Map<String, DataItemAsset> getAssets() {
        return this.alH;
    }

    public byte[] getData() {
        return this.TC;
    }

    public Uri getUri() {
        return this.mUri;
    }

    public boolean isDataValid() {
        return true;
    }

    /* renamed from: m */
    public m setData(byte[] bArr) {
        this.TC = bArr;
        return this;
    }

    public Bundle nh() {
        Bundle bundle = new Bundle();
        bundle.setClassLoader(DataItemAssetParcelable.class.getClassLoader());
        for (Map.Entry next : this.alH.entrySet()) {
            bundle.putParcelable((String) next.getKey(), new DataItemAssetParcelable((DataItemAsset) next.getValue()));
        }
        return bundle;
    }

    /* renamed from: nn */
    public m freeze() {
        return this;
    }

    public String toString() {
        return toString(Log.isLoggable("DataItem", 3));
    }

    public String toString(boolean verbose) {
        StringBuilder sb = new StringBuilder("DataItemParcelable[");
        sb.append("@");
        sb.append(Integer.toHexString(hashCode()));
        sb.append(",dataSz=" + (this.TC == null ? "null" : Integer.valueOf(this.TC.length)));
        sb.append(", numAssets=" + this.alH.size());
        sb.append(", uri=" + this.mUri);
        if (!verbose) {
            sb.append("]");
            return sb.toString();
        }
        sb.append("]\n  assets: ");
        for (String next : this.alH.keySet()) {
            sb.append("\n    " + next + ": " + this.alH.get(next));
        }
        sb.append("\n  ]");
        return sb.toString();
    }

    public void writeToParcel(Parcel dest, int flags) {
        n.a(this, dest, flags);
    }
}
