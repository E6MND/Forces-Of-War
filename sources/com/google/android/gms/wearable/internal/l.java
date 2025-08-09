package com.google.android.gms.wearable.internal;

import android.net.Uri;
import android.util.Log;
import com.google.android.gms.wearable.DataItem;
import com.google.android.gms.wearable.DataItemAsset;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class l implements DataItem {
    private byte[] TC;
    private Map<String, DataItemAsset> alH;
    private Uri mUri;

    public l(DataItem dataItem) {
        this.mUri = dataItem.getUri();
        this.TC = dataItem.getData();
        HashMap hashMap = new HashMap();
        for (Map.Entry next : dataItem.getAssets().entrySet()) {
            if (next.getKey() != null) {
                hashMap.put(next.getKey(), ((DataItemAsset) next.getValue()).freeze());
            }
        }
        this.alH = Collections.unmodifiableMap(hashMap);
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

    /* renamed from: nm */
    public DataItem freeze() {
        return this;
    }

    public DataItem setData(byte[] data) {
        throw new UnsupportedOperationException();
    }

    public String toString() {
        return toString(Log.isLoggable("DataItem", 3));
    }

    public String toString(boolean verbose) {
        StringBuilder sb = new StringBuilder("DataItemEntity[");
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
}
