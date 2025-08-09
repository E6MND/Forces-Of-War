package com.google.android.gms.common.images;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.hl;
import org.json.JSONException;
import org.json.JSONObject;

public final class WebImage implements SafeParcelable {
    public static final Parcelable.Creator<WebImage> CREATOR = new b();
    private final Uri Fr;
    private final int ks;
    private final int kt;
    private final int xJ;

    WebImage(int versionCode, Uri url, int width, int height) {
        this.xJ = versionCode;
        this.Fr = url;
        this.ks = width;
        this.kt = height;
    }

    public WebImage(Uri url) throws IllegalArgumentException {
        this(url, 0, 0);
    }

    public WebImage(Uri url, int width, int height) throws IllegalArgumentException {
        this(1, url, width, height);
        if (url == null) {
            throw new IllegalArgumentException("url cannot be null");
        } else if (width < 0 || height < 0) {
            throw new IllegalArgumentException("width and height must not be negative");
        }
    }

    public WebImage(JSONObject json) throws IllegalArgumentException {
        this(c(json), json.optInt("width", 0), json.optInt("height", 0));
    }

    private static Uri c(JSONObject jSONObject) {
        if (!jSONObject.has("url")) {
            return null;
        }
        try {
            return Uri.parse(jSONObject.getString("url"));
        } catch (JSONException e) {
            return null;
        }
    }

    public JSONObject dU() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("url", this.Fr.toString());
            jSONObject.put("width", this.ks);
            jSONObject.put("height", this.kt);
        } catch (JSONException e) {
        }
        return jSONObject;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || !(other instanceof WebImage)) {
            return false;
        }
        WebImage webImage = (WebImage) other;
        return hl.equal(this.Fr, webImage.Fr) && this.ks == webImage.ks && this.kt == webImage.kt;
    }

    public int getHeight() {
        return this.kt;
    }

    public Uri getUrl() {
        return this.Fr;
    }

    /* access modifiers changed from: package-private */
    public int getVersionCode() {
        return this.xJ;
    }

    public int getWidth() {
        return this.ks;
    }

    public int hashCode() {
        return hl.hashCode(this.Fr, Integer.valueOf(this.ks), Integer.valueOf(this.kt));
    }

    public String toString() {
        return String.format("Image %dx%d %s", new Object[]{Integer.valueOf(this.ks), Integer.valueOf(this.kt), this.Fr.toString()});
    }

    public void writeToParcel(Parcel out, int flags) {
        b.a(this, out, flags);
    }
}
