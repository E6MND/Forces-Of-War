package com.google.android.gms.wearable;

import android.net.Uri;
import android.util.Log;
import com.google.android.gms.internal.lw;
import com.google.android.gms.internal.mf;

public class PutDataMapRequest {
    private final DataMap ali = new DataMap();
    private final PutDataRequest alj;

    private PutDataMapRequest(PutDataRequest putDataRequest, DataMap dataMap) {
        this.alj = putDataRequest;
        if (dataMap != null) {
            this.ali.putAll(dataMap);
        }
    }

    public static PutDataMapRequest create(String path) {
        return new PutDataMapRequest(PutDataRequest.create(path), (DataMap) null);
    }

    public static PutDataMapRequest createFromDataMapItem(DataMapItem source) {
        return new PutDataMapRequest(PutDataRequest.j(source.getUri()), source.getDataMap());
    }

    public static PutDataMapRequest createWithAutoAppendedId(String pathPrefix) {
        return new PutDataMapRequest(PutDataRequest.createWithAutoAppendedId(pathPrefix), (DataMap) null);
    }

    public PutDataRequest asPutDataRequest() {
        lw.a a = lw.a(this.ali);
        this.alj.setData(mf.d(a.amm));
        int size = a.amn.size();
        int i = 0;
        while (i < size) {
            String num = Integer.toString(i);
            Asset asset = a.amn.get(i);
            if (num == null) {
                throw new IllegalStateException("asset key cannot be null: " + asset);
            } else if (asset == null) {
                throw new IllegalStateException("asset cannot be null: key=" + num);
            } else {
                if (Log.isLoggable(DataMap.TAG, 3)) {
                    Log.d(DataMap.TAG, "asPutDataRequest: adding asset: " + num + " " + asset);
                }
                this.alj.putAsset(num, asset);
                i++;
            }
        }
        return this.alj;
    }

    public DataMap getDataMap() {
        return this.ali;
    }

    public Uri getUri() {
        return this.alj.getUri();
    }
}
