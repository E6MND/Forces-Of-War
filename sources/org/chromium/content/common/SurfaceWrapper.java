package org.chromium.content.common;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.Surface;

public class SurfaceWrapper implements Parcelable {
    public static final Parcelable.Creator<SurfaceWrapper> CREATOR = new Parcelable.Creator<SurfaceWrapper>() {
        public SurfaceWrapper createFromParcel(Parcel in) {
            return new SurfaceWrapper((Surface) Surface.CREATOR.createFromParcel(in));
        }

        public SurfaceWrapper[] newArray(int size) {
            return new SurfaceWrapper[size];
        }
    };
    private final Surface mSurface;

    public SurfaceWrapper(Surface surface) {
        this.mSurface = surface;
    }

    public Surface getSurface() {
        return this.mSurface;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        this.mSurface.writeToParcel(out, 0);
    }
}
