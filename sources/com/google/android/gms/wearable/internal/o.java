package com.google.android.gms.wearable.internal;

import android.net.Uri;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.d;
import com.google.android.gms.wearable.DataItem;
import com.google.android.gms.wearable.DataItemAsset;
import java.util.HashMap;
import java.util.Map;

public final class o extends d implements DataItem {
    private final int RD;

    public o(DataHolder dataHolder, int i, int i2) {
        super(dataHolder, i);
        this.RD = i2;
    }

    public Map<String, DataItemAsset> getAssets() {
        HashMap hashMap = new HashMap(this.RD);
        for (int i = 0; i < this.RD; i++) {
            k kVar = new k(this.DD, this.Ez + i);
            if (kVar.getDataItemKey() != null) {
                hashMap.put(kVar.getDataItemKey(), kVar);
            }
        }
        return hashMap;
    }

    public byte[] getData() {
        return getByteArray("data");
    }

    public Uri getUri() {
        return Uri.parse(getString("path"));
    }

    /* renamed from: nm */
    public DataItem freeze() {
        return new l(this);
    }

    public DataItem setData(byte[] data) {
        throw new UnsupportedOperationException();
    }
}
