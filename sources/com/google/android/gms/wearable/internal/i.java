package com.google.android.gms.wearable.internal;

import com.google.android.gms.wearable.DataItemAsset;

public class i implements DataItemAsset {
    private final String JI;
    private final String xD;

    public i(DataItemAsset dataItemAsset) {
        this.xD = dataItemAsset.getId();
        this.JI = dataItemAsset.getDataItemKey();
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
        sb.append("DataItemAssetEntity[");
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
}
