package com.google.android.gms.wearable;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.hn;
import com.google.android.gms.wearable.internal.DataItemAssetParcelable;
import java.security.SecureRandom;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class PutDataRequest implements SafeParcelable {
    public static final Parcelable.Creator<PutDataRequest> CREATOR = new e();
    public static final String WEAR_URI_SCHEME = "wear";
    private static final Random alk = new SecureRandom();
    private byte[] TC;
    private final Bundle all;
    private final Uri mUri;
    final int xJ;

    private PutDataRequest(int versionCode, Uri uri) {
        this(versionCode, uri, new Bundle(), (byte[]) null);
    }

    PutDataRequest(int versionCode, Uri uri, Bundle assets, byte[] data) {
        this.xJ = versionCode;
        this.mUri = uri;
        this.all = assets;
        this.all.setClassLoader(DataItemAssetParcelable.class.getClassLoader());
        this.TC = data;
    }

    public static PutDataRequest create(String path) {
        return j(cx(path));
    }

    public static PutDataRequest createFromDataItem(DataItem source) {
        PutDataRequest j = j(source.getUri());
        for (Map.Entry next : source.getAssets().entrySet()) {
            if (((DataItemAsset) next.getValue()).getId() == null) {
                throw new IllegalStateException("Cannot create an asset for a put request without a digest: " + ((String) next.getKey()));
            }
            j.putAsset((String) next.getKey(), Asset.createFromRef(((DataItemAsset) next.getValue()).getId()));
        }
        j.setData(source.getData());
        return j;
    }

    public static PutDataRequest createWithAutoAppendedId(String pathPrefix) {
        StringBuilder sb = new StringBuilder(pathPrefix);
        if (!pathPrefix.endsWith("/")) {
            sb.append("/");
        }
        sb.append("PN").append(alk.nextLong());
        return new PutDataRequest(1, cx(sb.toString()));
    }

    private static Uri cx(String str) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("An empty path was supplied.");
        } else if (!str.startsWith("/")) {
            throw new IllegalArgumentException("A path must start with a single / .");
        } else if (!str.startsWith("//")) {
            return new Uri.Builder().scheme(WEAR_URI_SCHEME).path(str).build();
        } else {
            throw new IllegalArgumentException("A path must start with a single / .");
        }
    }

    public static PutDataRequest j(Uri uri) {
        return new PutDataRequest(1, uri);
    }

    public int describeContents() {
        return 0;
    }

    public Asset getAsset(String key) {
        return (Asset) this.all.getParcelable(key);
    }

    public Map<String, Asset> getAssets() {
        HashMap hashMap = new HashMap();
        for (String str : this.all.keySet()) {
            hashMap.put(str, (Asset) this.all.getParcelable(str));
        }
        return Collections.unmodifiableMap(hashMap);
    }

    public byte[] getData() {
        return this.TC;
    }

    public Uri getUri() {
        return this.mUri;
    }

    public boolean hasAsset(String key) {
        return this.all.containsKey(key);
    }

    public Bundle nh() {
        return this.all;
    }

    public PutDataRequest putAsset(String key, Asset value) {
        hn.f(key);
        hn.f(value);
        this.all.putParcelable(key, value);
        return this;
    }

    public PutDataRequest removeAsset(String key) {
        this.all.remove(key);
        return this;
    }

    public PutDataRequest setData(byte[] data) {
        this.TC = data;
        return this;
    }

    public String toString() {
        return toString(Log.isLoggable(DataMap.TAG, 3));
    }

    public String toString(boolean verbose) {
        StringBuilder sb = new StringBuilder("PutDataRequest[");
        sb.append("dataSz=" + (this.TC == null ? "null" : Integer.valueOf(this.TC.length)));
        sb.append(", numAssets=" + this.all.size());
        sb.append(", uri=" + this.mUri);
        if (!verbose) {
            sb.append("]");
            return sb.toString();
        }
        sb.append("]\n  assets: ");
        for (String str : this.all.keySet()) {
            sb.append("\n    " + str + ": " + this.all.getParcelable(str));
        }
        sb.append("\n  ]");
        return sb.toString();
    }

    public void writeToParcel(Parcel dest, int flags) {
        e.a(this, dest, flags);
    }
}
