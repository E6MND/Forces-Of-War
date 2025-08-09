package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.support.v4.view.ViewCompat;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.maps.internal.v;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class PolygonOptions implements SafeParcelable {
    public static final PolygonOptionsCreator CREATOR = new PolygonOptionsCreator();
    private final List<LatLng> aaU;
    private final List<List<LatLng>> aaV;
    private boolean aaW;
    private float aar;
    private int aas;
    private int aat;
    private float aau;
    private boolean aav;
    private final int xJ;

    public PolygonOptions() {
        this.aar = 10.0f;
        this.aas = ViewCompat.MEASURED_STATE_MASK;
        this.aat = 0;
        this.aau = 0.0f;
        this.aav = true;
        this.aaW = false;
        this.xJ = 1;
        this.aaU = new ArrayList();
        this.aaV = new ArrayList();
    }

    PolygonOptions(int versionCode, List<LatLng> points, List holes, float strokeWidth, int strokeColor, int fillColor, float zIndex, boolean visible, boolean geodesic) {
        this.aar = 10.0f;
        this.aas = ViewCompat.MEASURED_STATE_MASK;
        this.aat = 0;
        this.aau = 0.0f;
        this.aav = true;
        this.aaW = false;
        this.xJ = versionCode;
        this.aaU = points;
        this.aaV = holes;
        this.aar = strokeWidth;
        this.aas = strokeColor;
        this.aat = fillColor;
        this.aau = zIndex;
        this.aav = visible;
        this.aaW = geodesic;
    }

    public PolygonOptions add(LatLng point) {
        this.aaU.add(point);
        return this;
    }

    public PolygonOptions add(LatLng... points) {
        this.aaU.addAll(Arrays.asList(points));
        return this;
    }

    public PolygonOptions addAll(Iterable<LatLng> points) {
        for (LatLng add : points) {
            this.aaU.add(add);
        }
        return this;
    }

    public PolygonOptions addHole(Iterable<LatLng> points) {
        ArrayList arrayList = new ArrayList();
        for (LatLng add : points) {
            arrayList.add(add);
        }
        this.aaV.add(arrayList);
        return this;
    }

    public int describeContents() {
        return 0;
    }

    public PolygonOptions fillColor(int color) {
        this.aat = color;
        return this;
    }

    public PolygonOptions geodesic(boolean geodesic) {
        this.aaW = geodesic;
        return this;
    }

    public int getFillColor() {
        return this.aat;
    }

    public List<List<LatLng>> getHoles() {
        return this.aaV;
    }

    public List<LatLng> getPoints() {
        return this.aaU;
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

    public boolean isGeodesic() {
        return this.aaW;
    }

    public boolean isVisible() {
        return this.aav;
    }

    /* access modifiers changed from: package-private */
    public List jK() {
        return this.aaV;
    }

    public PolygonOptions strokeColor(int color) {
        this.aas = color;
        return this;
    }

    public PolygonOptions strokeWidth(float width) {
        this.aar = width;
        return this;
    }

    public PolygonOptions visible(boolean visible) {
        this.aav = visible;
        return this;
    }

    public void writeToParcel(Parcel out, int flags) {
        if (v.jG()) {
            g.a(this, out, flags);
        } else {
            PolygonOptionsCreator.a(this, out, flags);
        }
    }

    public PolygonOptions zIndex(float zIndex) {
        this.aau = zIndex;
        return this;
    }
}
