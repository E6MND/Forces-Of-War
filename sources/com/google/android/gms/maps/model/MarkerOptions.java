package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.dynamic.d;
import com.google.android.gms.maps.internal.v;

public final class MarkerOptions implements SafeParcelable {
    public static final MarkerOptionsCreator CREATOR = new MarkerOptionsCreator();
    private String HV;
    private LatLng ZU;
    private float aaD;
    private float aaE;
    private String aaM;
    private BitmapDescriptor aaN;
    private boolean aaO;
    private boolean aaP;
    private float aaQ;
    private float aaR;
    private float aaS;
    private boolean aav;
    private float mAlpha;
    private final int xJ;

    public MarkerOptions() {
        this.aaD = 0.5f;
        this.aaE = 1.0f;
        this.aav = true;
        this.aaP = false;
        this.aaQ = 0.0f;
        this.aaR = 0.5f;
        this.aaS = 0.0f;
        this.mAlpha = 1.0f;
        this.xJ = 1;
    }

    MarkerOptions(int versionCode, LatLng position, String title, String snippet, IBinder wrappedIcon, float anchorU, float anchorV, boolean draggable, boolean visible, boolean flat, float rotation, float infoWindowAnchorU, float infoWindowAnchorV, float alpha) {
        this.aaD = 0.5f;
        this.aaE = 1.0f;
        this.aav = true;
        this.aaP = false;
        this.aaQ = 0.0f;
        this.aaR = 0.5f;
        this.aaS = 0.0f;
        this.mAlpha = 1.0f;
        this.xJ = versionCode;
        this.ZU = position;
        this.HV = title;
        this.aaM = snippet;
        this.aaN = wrappedIcon == null ? null : new BitmapDescriptor(d.a.ag(wrappedIcon));
        this.aaD = anchorU;
        this.aaE = anchorV;
        this.aaO = draggable;
        this.aav = visible;
        this.aaP = flat;
        this.aaQ = rotation;
        this.aaR = infoWindowAnchorU;
        this.aaS = infoWindowAnchorV;
        this.mAlpha = alpha;
    }

    public MarkerOptions alpha(float alpha) {
        this.mAlpha = alpha;
        return this;
    }

    public MarkerOptions anchor(float u, float v) {
        this.aaD = u;
        this.aaE = v;
        return this;
    }

    public int describeContents() {
        return 0;
    }

    public MarkerOptions draggable(boolean draggable) {
        this.aaO = draggable;
        return this;
    }

    public MarkerOptions flat(boolean flat) {
        this.aaP = flat;
        return this;
    }

    public float getAlpha() {
        return this.mAlpha;
    }

    public float getAnchorU() {
        return this.aaD;
    }

    public float getAnchorV() {
        return this.aaE;
    }

    public BitmapDescriptor getIcon() {
        return this.aaN;
    }

    public float getInfoWindowAnchorU() {
        return this.aaR;
    }

    public float getInfoWindowAnchorV() {
        return this.aaS;
    }

    public LatLng getPosition() {
        return this.ZU;
    }

    public float getRotation() {
        return this.aaQ;
    }

    public String getSnippet() {
        return this.aaM;
    }

    public String getTitle() {
        return this.HV;
    }

    /* access modifiers changed from: package-private */
    public int getVersionCode() {
        return this.xJ;
    }

    public MarkerOptions icon(BitmapDescriptor icon) {
        this.aaN = icon;
        return this;
    }

    public MarkerOptions infoWindowAnchor(float u, float v) {
        this.aaR = u;
        this.aaS = v;
        return this;
    }

    public boolean isDraggable() {
        return this.aaO;
    }

    public boolean isFlat() {
        return this.aaP;
    }

    public boolean isVisible() {
        return this.aav;
    }

    /* access modifiers changed from: package-private */
    public IBinder jJ() {
        if (this.aaN == null) {
            return null;
        }
        return this.aaN.ji().asBinder();
    }

    public MarkerOptions position(LatLng position) {
        this.ZU = position;
        return this;
    }

    public MarkerOptions rotation(float rotation) {
        this.aaQ = rotation;
        return this;
    }

    public MarkerOptions snippet(String snippet) {
        this.aaM = snippet;
        return this;
    }

    public MarkerOptions title(String title) {
        this.HV = title;
        return this;
    }

    public MarkerOptions visible(boolean visible) {
        this.aav = visible;
        return this;
    }

    public void writeToParcel(Parcel out, int flags) {
        if (v.jG()) {
            f.a(this, out, flags);
        } else {
            MarkerOptionsCreator.a(this, out, flags);
        }
    }
}
