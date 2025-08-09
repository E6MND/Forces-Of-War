package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.support.v4.view.ViewCompat;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.maps.internal.v;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class PolylineOptions implements SafeParcelable {
    public static final PolylineOptionsCreator CREATOR = new PolylineOptionsCreator();
    private int Dg;
    private final List<LatLng> aaU;
    private boolean aaW;
    private float aau;
    private boolean aav;
    private float aaz;
    private final int xJ;

    public PolylineOptions() {
        this.aaz = 10.0f;
        this.Dg = ViewCompat.MEASURED_STATE_MASK;
        this.aau = 0.0f;
        this.aav = true;
        this.aaW = false;
        this.xJ = 1;
        this.aaU = new ArrayList();
    }

    PolylineOptions(int versionCode, List points, float width, int color, float zIndex, boolean visible, boolean geodesic) {
        this.aaz = 10.0f;
        this.Dg = ViewCompat.MEASURED_STATE_MASK;
        this.aau = 0.0f;
        this.aav = true;
        this.aaW = false;
        this.xJ = versionCode;
        this.aaU = points;
        this.aaz = width;
        this.Dg = color;
        this.aau = zIndex;
        this.aav = visible;
        this.aaW = geodesic;
    }

    public PolylineOptions add(LatLng point) {
        this.aaU.add(point);
        return this;
    }

    public PolylineOptions add(LatLng... points) {
        this.aaU.addAll(Arrays.asList(points));
        return this;
    }

    public PolylineOptions addAll(Iterable<LatLng> points) {
        for (LatLng add : points) {
            this.aaU.add(add);
        }
        return this;
    }

    public PolylineOptions color(int color) {
        this.Dg = color;
        return this;
    }

    public int describeContents() {
        return 0;
    }

    public PolylineOptions geodesic(boolean geodesic) {
        this.aaW = geodesic;
        return this;
    }

    public int getColor() {
        return this.Dg;
    }

    public List<LatLng> getPoints() {
        return this.aaU;
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

    public boolean isGeodesic() {
        return this.aaW;
    }

    public boolean isVisible() {
        return this.aav;
    }

    public PolylineOptions visible(boolean visible) {
        this.aav = visible;
        return this;
    }

    public PolylineOptions width(float width) {
        this.aaz = width;
        return this;
    }

    public void writeToParcel(Parcel out, int flags) {
        if (v.jG()) {
            h.a(this, out, flags);
        } else {
            PolylineOptionsCreator.a(this, out, flags);
        }
    }

    public PolylineOptions zIndex(float zIndex) {
        this.aau = zIndex;
        return this;
    }
}
