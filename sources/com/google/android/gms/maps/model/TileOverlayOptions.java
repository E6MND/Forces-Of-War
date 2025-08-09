package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.maps.internal.v;
import com.google.android.gms.maps.model.internal.i;

public final class TileOverlayOptions implements SafeParcelable {
    public static final TileOverlayOptionsCreator CREATOR = new TileOverlayOptionsCreator();
    private float aau;
    private boolean aav;
    /* access modifiers changed from: private */
    public i aba;
    private TileProvider abb;
    private boolean abc;
    private final int xJ;

    public TileOverlayOptions() {
        this.aav = true;
        this.abc = true;
        this.xJ = 1;
    }

    TileOverlayOptions(int versionCode, IBinder delegate, boolean visible, float zIndex, boolean fadeIn) {
        this.aav = true;
        this.abc = true;
        this.xJ = versionCode;
        this.aba = i.a.bg(delegate);
        this.abb = this.aba == null ? null : new TileProvider() {
            private final i abd = TileOverlayOptions.this.aba;

            public Tile getTile(int x, int y, int zoom) {
                try {
                    return this.abd.getTile(x, y, zoom);
                } catch (RemoteException e) {
                    return null;
                }
            }
        };
        this.aav = visible;
        this.aau = zIndex;
        this.abc = fadeIn;
    }

    public int describeContents() {
        return 0;
    }

    public TileOverlayOptions fadeIn(boolean fadeIn) {
        this.abc = fadeIn;
        return this;
    }

    public boolean getFadeIn() {
        return this.abc;
    }

    public TileProvider getTileProvider() {
        return this.abb;
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

    /* access modifiers changed from: package-private */
    public IBinder jL() {
        return this.aba.asBinder();
    }

    public TileOverlayOptions tileProvider(final TileProvider tileProvider) {
        this.abb = tileProvider;
        this.aba = this.abb == null ? null : new i.a() {
            public Tile getTile(int x, int y, int zoom) {
                return tileProvider.getTile(x, y, zoom);
            }
        };
        return this;
    }

    public TileOverlayOptions visible(boolean visible) {
        this.aav = visible;
        return this;
    }

    public void writeToParcel(Parcel out, int flags) {
        if (v.jG()) {
            j.a(this, out, flags);
        } else {
            TileOverlayOptionsCreator.a(this, out, flags);
        }
    }

    public TileOverlayOptions zIndex(float zIndex) {
        this.aau = zIndex;
        return this;
    }
}
