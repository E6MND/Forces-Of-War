package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.hl;

public class StreetViewPanoramaLocation implements SafeParcelable {
    public static final StreetViewPanoramaLocationCreator CREATOR = new StreetViewPanoramaLocationCreator();
    public final StreetViewPanoramaLink[] links;
    public final String panoId;
    public final LatLng position;
    private final int xJ;

    StreetViewPanoramaLocation(int versionCode, StreetViewPanoramaLink[] links2, LatLng position2, String panoId2) {
        this.xJ = versionCode;
        this.links = links2;
        this.position = position2;
        this.panoId = panoId2;
    }

    public StreetViewPanoramaLocation(StreetViewPanoramaLink[] links2, LatLng position2, String panoId2) {
        this(1, links2, position2, panoId2);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof StreetViewPanoramaLocation)) {
            return false;
        }
        StreetViewPanoramaLocation streetViewPanoramaLocation = (StreetViewPanoramaLocation) o;
        return this.panoId.equals(streetViewPanoramaLocation.panoId) && this.position.equals(streetViewPanoramaLocation.position);
    }

    /* access modifiers changed from: package-private */
    public int getVersionCode() {
        return this.xJ;
    }

    public int hashCode() {
        return hl.hashCode(this.position, this.panoId);
    }

    public String toString() {
        return hl.e(this).a("panoId", this.panoId).a("position", this.position.toString()).toString();
    }

    public void writeToParcel(Parcel out, int flags) {
        StreetViewPanoramaLocationCreator.a(this, out, flags);
    }
}
