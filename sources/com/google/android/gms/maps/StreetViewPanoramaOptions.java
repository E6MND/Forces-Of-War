package com.google.android.gms.maps;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.maps.internal.a;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;

public final class StreetViewPanoramaOptions implements SafeParcelable {
    public static final StreetViewPanoramaOptionsCreator CREATOR = new StreetViewPanoramaOptionsCreator();
    private StreetViewPanoramaCamera ZS;
    private String ZT;
    private LatLng ZU;
    private Integer ZV;
    private Boolean ZW;
    private Boolean ZX;
    private Boolean ZY;
    private Boolean Zq;
    private Boolean Zw;
    private final int xJ;

    public StreetViewPanoramaOptions() {
        this.ZW = true;
        this.Zw = true;
        this.ZX = true;
        this.ZY = true;
        this.xJ = 1;
    }

    StreetViewPanoramaOptions(int versionCode, StreetViewPanoramaCamera camera, String panoId, LatLng position, Integer radius, byte userNavigationEnabled, byte zoomGesturesEnabled, byte panningGesturesEnabled, byte streetNamesEnabled, byte useViewLifecycleInFragment) {
        this.ZW = true;
        this.Zw = true;
        this.ZX = true;
        this.ZY = true;
        this.xJ = versionCode;
        this.ZS = camera;
        this.ZU = position;
        this.ZV = radius;
        this.ZT = panoId;
        this.ZW = a.a(userNavigationEnabled);
        this.Zw = a.a(zoomGesturesEnabled);
        this.ZX = a.a(panningGesturesEnabled);
        this.ZY = a.a(streetNamesEnabled);
        this.Zq = a.a(useViewLifecycleInFragment);
    }

    public int describeContents() {
        return 0;
    }

    public Boolean getPanningGesturesEnabled() {
        return this.ZX;
    }

    public String getPanoramaId() {
        return this.ZT;
    }

    public LatLng getPosition() {
        return this.ZU;
    }

    public Integer getRadius() {
        return this.ZV;
    }

    public Boolean getStreetNamesEnabled() {
        return this.ZY;
    }

    public StreetViewPanoramaCamera getStreetViewPanoramaCamera() {
        return this.ZS;
    }

    public Boolean getUseViewLifecycleInFragment() {
        return this.Zq;
    }

    public Boolean getUserNavigationEnabled() {
        return this.ZW;
    }

    /* access modifiers changed from: package-private */
    public int getVersionCode() {
        return this.xJ;
    }

    public Boolean getZoomGesturesEnabled() {
        return this.Zw;
    }

    /* access modifiers changed from: package-private */
    public byte jA() {
        return a.c(this.ZY);
    }

    /* access modifiers changed from: package-private */
    public byte jm() {
        return a.c(this.Zq);
    }

    /* access modifiers changed from: package-private */
    public byte jq() {
        return a.c(this.Zw);
    }

    /* access modifiers changed from: package-private */
    public byte jy() {
        return a.c(this.ZW);
    }

    /* access modifiers changed from: package-private */
    public byte jz() {
        return a.c(this.ZX);
    }

    public StreetViewPanoramaOptions panningGesturesEnabled(boolean enabled) {
        this.ZX = Boolean.valueOf(enabled);
        return this;
    }

    public StreetViewPanoramaOptions panoramaCamera(StreetViewPanoramaCamera camera) {
        this.ZS = camera;
        return this;
    }

    public StreetViewPanoramaOptions panoramaId(String panoId) {
        this.ZT = panoId;
        return this;
    }

    public StreetViewPanoramaOptions position(LatLng position) {
        this.ZU = position;
        return this;
    }

    public StreetViewPanoramaOptions position(LatLng position, Integer radius) {
        this.ZU = position;
        this.ZV = radius;
        return this;
    }

    public StreetViewPanoramaOptions streetNamesEnabled(boolean enabled) {
        this.ZY = Boolean.valueOf(enabled);
        return this;
    }

    public StreetViewPanoramaOptions useViewLifecycleInFragment(boolean useViewLifecycleInFragment) {
        this.Zq = Boolean.valueOf(useViewLifecycleInFragment);
        return this;
    }

    public StreetViewPanoramaOptions userNavigationEnabled(boolean enabled) {
        this.ZW = Boolean.valueOf(enabled);
        return this;
    }

    public void writeToParcel(Parcel out, int flags) {
        StreetViewPanoramaOptionsCreator.a(this, out, flags);
    }

    public StreetViewPanoramaOptions zoomGesturesEnabled(boolean enabled) {
        this.Zw = Boolean.valueOf(enabled);
        return this;
    }
}
