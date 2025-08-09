package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.support.v4.view.ViewCompat;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.maps.internal.v;

public final class CircleOptions implements SafeParcelable {
    public static final CircleOptionsCreator CREATOR = new CircleOptionsCreator();
    private LatLng aap;
    private double aaq;
    private float aar;
    private int aas;
    private int aat;
    private float aau;
    private boolean aav;
    private final int xJ;

    public CircleOptions() {
        this.aap = null;
        this.aaq = 0.0d;
        this.aar = 10.0f;
        this.aas = ViewCompat.MEASURED_STATE_MASK;
        this.aat = 0;
        this.aau = 0.0f;
        this.aav = true;
        this.xJ = 1;
    }

    CircleOptions(int versionCode, LatLng center, double radius, float strokeWidth, int strokeColor, int fillColor, float zIndex, boolean visible) {
        this.aap = null;
        this.aaq = 0.0d;
        this.aar = 10.0f;
        this.aas = ViewCompat.MEASURED_STATE_MASK;
        this.aat = 0;
        this.aau = 0.0f;
        this.aav = true;
        this.xJ = versionCode;
        this.aap = center;
        this.aaq = radius;
        this.aar = strokeWidth;
        this.aas = strokeColor;
        this.aat = fillColor;
        this.aau = zIndex;
        this.aav = visible;
    }

    public CircleOptions center(LatLng center) {
        this.aap = center;
        return this;
    }

    public int describeContents() {
        return 0;
    }

    public CircleOptions fillColor(int color) {
        this.aat = color;
        return this;
    }

    public LatLng getCenter() {
        return this.aap;
    }

    public int getFillColor() {
        return this.aat;
    }

    public double getRadius() {
        return this.aaq;
    }

    public int getStrokeColor() {
        return this.aas;
    }

    public float getStrokeWidth() {
        return this.aar;
    }

    /* access modifiers changed from: package-private */
    public int getVersionCode() {
        return this.xJ;
    }

    public float getZIndex() {
        return this.aau;
    }

    public boolean isVisible() {
        return this.aav;
    }

    public CircleOptions radius(double radius) {
        this.aaq = radius;
        return this;
    }

    public CircleOptions strokeColor(int color) {
        this.aas = color;
        return this;
    }

    public CircleOptions strokeWidth(float width) {
        this.aar = width;
        return this;
    }

    public CircleOptions visible(boolean visible) {
        this.aav = visible;
        return this;
    }

    public void writeToParcel(Parcel out, int flags) {
        if (v.jG()) {
            b.a(this, out, flags);
        } else {
            CircleOptionsCreator.a(this, out, flags);
        }
    }

    public CircleOptions zIndex(float zIndex) {
        this.aau = zIndex;
        return this;
    }
}
