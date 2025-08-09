package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.dynamic.d;
import com.google.android.gms.internal.hn;
import com.google.android.gms.maps.internal.v;

public final class GroundOverlayOptions implements SafeParcelable {
    public static final GroundOverlayOptionsCreator CREATOR = new GroundOverlayOptionsCreator();
    public static final float NO_DIMENSION = -1.0f;
    private float aaA;
    private LatLngBounds aaB;
    private float aaC;
    private float aaD;
    private float aaE;
    private float aan;
    private float aau;
    private boolean aav;
    private BitmapDescriptor aax;
    private LatLng aay;
    private float aaz;
    private final int xJ;

    public GroundOverlayOptions() {
        this.aav = true;
        this.aaC = 0.0f;
        this.aaD = 0.5f;
        this.aaE = 0.5f;
        this.xJ = 1;
    }

    GroundOverlayOptions(int versionCode, IBinder wrappedImage, LatLng location, float width, float height, LatLngBounds bounds, float bearing, float zIndex, boolean visible, float transparency, float anchorU, float anchorV) {
        this.aav = true;
        this.aaC = 0.0f;
        this.aaD = 0.5f;
        this.aaE = 0.5f;
        this.xJ = versionCode;
        this.aax = new BitmapDescriptor(d.a.ag(wrappedImage));
        this.aay = location;
        this.aaz = width;
        this.aaA = height;
        this.aaB = bounds;
        this.aan = bearing;
        this.aau = zIndex;
        this.aav = visible;
        this.aaC = transparency;
        this.aaD = anchorU;
        this.aaE = anchorV;
    }

    private GroundOverlayOptions a(LatLng latLng, float f, float f2) {
        this.aay = latLng;
        this.aaz = f;
        this.aaA = f2;
        return this;
    }

    public GroundOverlayOptions anchor(float u, float v) {
        this.aaD = u;
        this.aaE = v;
        return this;
    }

    public GroundOverlayOptions bearing(float bearing) {
        this.aan = ((bearing % 360.0f) + 360.0f) % 360.0f;
        return this;
    }

    public int describeContents() {
        return 0;
    }

    public float getAnchorU() {
        return this.aaD;
    }

    public float getAnchorV() {
        return this.aaE;
    }

    public float getBearing() {
        return this.aan;
    }

    public LatLngBounds getBounds() {
        return this.aaB;
    }

    public float getHeight() {
        return this.aaA;
    }

    public BitmapDescriptor getImage() {
        return this.aax;
    }

    public LatLng getLocation() {
        return this.aay;
    }

    public float getTransparency() {
        return this.aaC;
    }

    /* access modifiers changed from: package-private */
    public int getVersionCode() {
        return this.xJ;
    }

    public float getWidth() {
        return this.aaz;
    }

    public float getZIndex() {
        return this.aau;
    }

    public GroundOverlayOptions image(BitmapDescriptor image) {
        this.aax = image;
        return this;
    }

    public boolean isVisible() {
        return this.aav;
    }

    /* access modifiers changed from: package-private */
    public IBinder jI() {
        return this.aax.ji().asBinder();
    }

    public GroundOverlayOptions position(LatLng location, float width) {
        boolean z = true;
        hn.a(this.aaB == null, "Position has already been set using positionFromBounds");
        hn.b(location != null, (Object) "Location must be specified");
        if (width < 0.0f) {
            z = false;
        }
        hn.b(z, (Object) "Width must be non-negative");
        return a(location, width, -1.0f);
    }

    public GroundOverlayOptions position(LatLng location, float width, float height) {
        boolean z = true;
        hn.a(this.aaB == null, "Position has already been set using positionFromBounds");
        hn.b(location != null, (Object) "Location must be specified");
        hn.b(width >= 0.0f, (Object) "Width must be non-negative");
        if (height < 0.0f) {
            z = false;
        }
        hn.b(z, (Object) "Height must be non-negative");
        return a(location, width, height);
    }

    public GroundOverlayOptions positionFromBounds(LatLngBounds bounds) {
        hn.a(this.aay == null, "Position has already been set using position: %s", this.aay);
        this.aaB = bounds;
        return this;
    }

    public GroundOverlayOptions transparency(float transparency) {
        hn.b(transparency >= 0.0f && transparency <= 1.0f, (Object) "Transparency must be in the range [0..1]");
        this.aaC = transparency;
        return this;
    }

    public GroundOverlayOptions visible(boolean visible) {
        this.aav = visible;
        return this;
    }

    public void writeToParcel(Parcel out, int flags) {
        if (v.jG()) {
            c.a(this, out, flags);
        } else {
            GroundOverlayOptionsCreator.a(this, out, flags);
        }
    }

    public GroundOverlayOptions zIndex(float zIndex) {
        this.aau = zIndex;
        return this;
    }
}
