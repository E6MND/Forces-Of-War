package com.google.android.gms.maps;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.util.AttributeSet;
import com.google.android.gms.R;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.maps.internal.a;
import com.google.android.gms.maps.internal.v;
import com.google.android.gms.maps.model.CameraPosition;

public final class GoogleMapOptions implements SafeParcelable {
    public static final GoogleMapOptionsCreator CREATOR = new GoogleMapOptionsCreator();
    private Boolean Zp;
    private Boolean Zq;
    private int Zr;
    private CameraPosition Zs;
    private Boolean Zt;
    private Boolean Zu;
    private Boolean Zv;
    private Boolean Zw;
    private Boolean Zx;
    private Boolean Zy;
    private final int xJ;

    public GoogleMapOptions() {
        this.Zr = -1;
        this.xJ = 1;
    }

    GoogleMapOptions(int versionCode, byte zOrderOnTop, byte useViewLifecycleInFragment, int mapType, CameraPosition camera, byte zoomControlsEnabled, byte compassEnabled, byte scrollGesturesEnabled, byte zoomGesturesEnabled, byte tiltGesturesEnabled, byte rotateGesturesEnabled) {
        this.Zr = -1;
        this.xJ = versionCode;
        this.Zp = a.a(zOrderOnTop);
        this.Zq = a.a(useViewLifecycleInFragment);
        this.Zr = mapType;
        this.Zs = camera;
        this.Zt = a.a(zoomControlsEnabled);
        this.Zu = a.a(compassEnabled);
        this.Zv = a.a(scrollGesturesEnabled);
        this.Zw = a.a(zoomGesturesEnabled);
        this.Zx = a.a(tiltGesturesEnabled);
        this.Zy = a.a(rotateGesturesEnabled);
    }

    public static GoogleMapOptions createFromAttributes(Context context, AttributeSet attrs) {
        if (attrs == null) {
            return null;
        }
        TypedArray obtainAttributes = context.getResources().obtainAttributes(attrs, R.styleable.MapAttrs);
        GoogleMapOptions googleMapOptions = new GoogleMapOptions();
        if (obtainAttributes.hasValue(0)) {
            googleMapOptions.mapType(obtainAttributes.getInt(0, -1));
        }
        if (obtainAttributes.hasValue(13)) {
            googleMapOptions.zOrderOnTop(obtainAttributes.getBoolean(13, false));
        }
        if (obtainAttributes.hasValue(12)) {
            googleMapOptions.useViewLifecycleInFragment(obtainAttributes.getBoolean(12, false));
        }
        if (obtainAttributes.hasValue(6)) {
            googleMapOptions.compassEnabled(obtainAttributes.getBoolean(6, true));
        }
        if (obtainAttributes.hasValue(7)) {
            googleMapOptions.rotateGesturesEnabled(obtainAttributes.getBoolean(7, true));
        }
        if (obtainAttributes.hasValue(8)) {
            googleMapOptions.scrollGesturesEnabled(obtainAttributes.getBoolean(8, true));
        }
        if (obtainAttributes.hasValue(9)) {
            googleMapOptions.tiltGesturesEnabled(obtainAttributes.getBoolean(9, true));
        }
        if (obtainAttributes.hasValue(11)) {
            googleMapOptions.zoomGesturesEnabled(obtainAttributes.getBoolean(11, true));
        }
        if (obtainAttributes.hasValue(10)) {
            googleMapOptions.zoomControlsEnabled(obtainAttributes.getBoolean(10, true));
        }
        googleMapOptions.camera(CameraPosition.createFromAttributes(context, attrs));
        obtainAttributes.recycle();
        return googleMapOptions;
    }

    public GoogleMapOptions camera(CameraPosition camera) {
        this.Zs = camera;
        return this;
    }

    public GoogleMapOptions compassEnabled(boolean enabled) {
        this.Zu = Boolean.valueOf(enabled);
        return this;
    }

    public int describeContents() {
        return 0;
    }

    public CameraPosition getCamera() {
        return this.Zs;
    }

    public Boolean getCompassEnabled() {
        return this.Zu;
    }

    public int getMapType() {
        return this.Zr;
    }

    public Boolean getRotateGesturesEnabled() {
        return this.Zy;
    }

    public Boolean getScrollGesturesEnabled() {
        return this.Zv;
    }

    public Boolean getTiltGesturesEnabled() {
        return this.Zx;
    }

    public Boolean getUseViewLifecycleInFragment() {
        return this.Zq;
    }

    /* access modifiers changed from: package-private */
    public int getVersionCode() {
        return this.xJ;
    }

    public Boolean getZOrderOnTop() {
        return this.Zp;
    }

    public Boolean getZoomControlsEnabled() {
        return this.Zt;
    }

    public Boolean getZoomGesturesEnabled() {
        return this.Zw;
    }

    /* access modifiers changed from: package-private */
    public byte jl() {
        return a.c(this.Zp);
    }

    /* access modifiers changed from: package-private */
    public byte jm() {
        return a.c(this.Zq);
    }

    /* access modifiers changed from: package-private */
    public byte jn() {
        return a.c(this.Zt);
    }

    /* access modifiers changed from: package-private */
    public byte jo() {
        return a.c(this.Zu);
    }

    /* access modifiers changed from: package-private */
    public byte jp() {
        return a.c(this.Zv);
    }

    /* access modifiers changed from: package-private */
    public byte jq() {
        return a.c(this.Zw);
    }

    /* access modifiers changed from: package-private */
    public byte jr() {
        return a.c(this.Zx);
    }

    /* access modifiers changed from: package-private */
    public byte js() {
        return a.c(this.Zy);
    }

    public GoogleMapOptions mapType(int mapType) {
        this.Zr = mapType;
        return this;
    }

    public GoogleMapOptions rotateGesturesEnabled(boolean enabled) {
        this.Zy = Boolean.valueOf(enabled);
        return this;
    }

    public GoogleMapOptions scrollGesturesEnabled(boolean enabled) {
        this.Zv = Boolean.valueOf(enabled);
        return this;
    }

    public GoogleMapOptions tiltGesturesEnabled(boolean enabled) {
        this.Zx = Boolean.valueOf(enabled);
        return this;
    }

    public GoogleMapOptions useViewLifecycleInFragment(boolean useViewLifecycleInFragment) {
        this.Zq = Boolean.valueOf(useViewLifecycleInFragment);
        return this;
    }

    public void writeToParcel(Parcel out, int flags) {
        if (v.jG()) {
            a.a(this, out, flags);
        } else {
            GoogleMapOptionsCreator.a(this, out, flags);
        }
    }

    public GoogleMapOptions zOrderOnTop(boolean zOrderOnTop) {
        this.Zp = Boolean.valueOf(zOrderOnTop);
        return this;
    }

    public GoogleMapOptions zoomControlsEnabled(boolean enabled) {
        this.Zt = Boolean.valueOf(enabled);
        return this;
    }

    public GoogleMapOptions zoomGesturesEnabled(boolean enabled) {
        this.Zw = Boolean.valueOf(enabled);
        return this;
    }
}
