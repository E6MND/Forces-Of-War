package com.google.android.gms.wearable;

import android.net.Uri;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.hl;

public class Asset implements SafeParcelable {
    public static final Parcelable.Creator<Asset> CREATOR = new a();
    private byte[] TC;
    private String ald;
    public ParcelFileDescriptor ale;
    public Uri uri;
    final int xJ;

    Asset(int versionCode, byte[] data, String digest, ParcelFileDescriptor fd, Uri uri2) {
        this.xJ = versionCode;
        this.TC = data;
        this.ald = digest;
        this.ale = fd;
        this.uri = uri2;
    }

    public static Asset createFromBytes(byte[] assetData) {
        if (assetData != null) {
            return new Asset(1, assetData, (String) null, (ParcelFileDescriptor) null, (Uri) null);
        }
        throw new IllegalArgumentException("Asset data cannot be null");
    }

    public static Asset createFromFd(ParcelFileDescriptor fd) {
        if (fd != null) {
            return new Asset(1, (byte[]) null, (String) null, fd, (Uri) null);
        }
        throw new IllegalArgumentException("Asset fd cannot be null");
    }

    public static Asset createFromRef(String digest) {
        if (digest != null) {
            return new Asset(1, (byte[]) null, digest, (ParcelFileDescriptor) null, (Uri) null);
        }
        throw new IllegalArgumentException("Asset digest cannot be null");
    }

    public static Asset createFromUri(Uri uri2) {
        if (uri2 != null) {
            return new Asset(1, (byte[]) null, (String) null, (ParcelFileDescriptor) null, uri2);
        }
        throw new IllegalArgumentException("Asset uri cannot be null");
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Asset)) {
            return false;
        }
        Asset asset = (Asset) o;
        return hl.equal(this.TC, asset.TC) && hl.equal(this.ald, asset.ald) && hl.equal(this.ale, asset.ale) && hl.equal(this.uri, asset.uri);
    }

    public byte[] getData() {
        return this.TC;
    }

    public String getDigest() {
        return this.ald;
    }

    public ParcelFileDescriptor getFd() {
        return this.ale;
    }

    public Uri getUri() {
        return this.uri;
    }

    public int hashCode() {
        return hl.hashCode(this.TC, this.ald, this.ale, this.uri);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Asset[@");
        sb.append(Integer.toHexString(hashCode()));
        if (this.ald == null) {
            sb.append(", nodigest");
        } else {
            sb.append(", ");
            sb.append(this.ald);
        }
        if (this.TC != null) {
            sb.append(", size=");
            sb.append(this.TC.length);
        }
        if (this.ale != null) {
            sb.append(", fd=");
            sb.append(this.ale);
        }
        if (this.uri != null) {
            sb.append(", uri=");
            sb.append(this.uri);
        }
        sb.append("]");
        return sb.toString();
    }

    public void writeToParcel(Parcel dest, int flags) {
        a.a(this, dest, flags | 1);
    }
}
