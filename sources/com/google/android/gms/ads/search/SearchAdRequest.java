package com.google.android.gms.ads.search;

import android.content.Context;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.ads.mediation.NetworkExtras;
import com.google.android.gms.ads.mediation.customevent.CustomEvent;
import com.google.android.gms.internal.au;

public final class SearchAdRequest {
    public static final int BORDER_TYPE_DASHED = 1;
    public static final int BORDER_TYPE_DOTTED = 2;
    public static final int BORDER_TYPE_NONE = 0;
    public static final int BORDER_TYPE_SOLID = 3;
    public static final int CALL_BUTTON_COLOR_DARK = 2;
    public static final int CALL_BUTTON_COLOR_LIGHT = 0;
    public static final int CALL_BUTTON_COLOR_MEDIUM = 1;
    public static final String DEVICE_ID_EMULATOR = au.DEVICE_ID_EMULATOR;
    public static final int ERROR_CODE_INTERNAL_ERROR = 0;
    public static final int ERROR_CODE_INVALID_REQUEST = 1;
    public static final int ERROR_CODE_NETWORK_ERROR = 2;
    public static final int ERROR_CODE_NO_FILL = 3;
    private final au kq;
    private final int sZ;
    private final int ta;
    private final int tb;
    private final int tc;
    private final int td;
    private final int te;
    private final int tf;
    private final int tg;
    private final String th;
    private final int ti;
    private final String tj;
    private final int tk;
    private final int tl;
    private final String tm;

    public static final class Builder {
        /* access modifiers changed from: private */
        public final au.a kr = new au.a();
        /* access modifiers changed from: private */
        public int sZ;
        /* access modifiers changed from: private */
        public int ta;
        /* access modifiers changed from: private */
        public int tb;
        /* access modifiers changed from: private */
        public int tc;
        /* access modifiers changed from: private */
        public int td;
        /* access modifiers changed from: private */
        public int te;
        /* access modifiers changed from: private */
        public int tf = 0;
        /* access modifiers changed from: private */
        public int tg;
        /* access modifiers changed from: private */
        public String th;
        /* access modifiers changed from: private */
        public int ti;
        /* access modifiers changed from: private */
        public String tj;
        /* access modifiers changed from: private */
        public int tk;
        /* access modifiers changed from: private */
        public int tl;
        /* access modifiers changed from: private */
        public String tm;

        public Builder addCustomEventExtrasBundle(Class<? extends CustomEvent> adapterClass, Bundle customEventExtras) {
            this.kr.b(adapterClass, customEventExtras);
            return this;
        }

        public Builder addNetworkExtras(NetworkExtras networkExtras) {
            this.kr.a(networkExtras);
            return this;
        }

        public Builder addNetworkExtrasBundle(Class<? extends MediationAdapter> adapterClass, Bundle networkExtras) {
            this.kr.a(adapterClass, networkExtras);
            return this;
        }

        public Builder addTestDevice(String deviceId) {
            this.kr.h(deviceId);
            return this;
        }

        public SearchAdRequest build() {
            return new SearchAdRequest(this);
        }

        public Builder setAnchorTextColor(int anchorTextColor) {
            this.sZ = anchorTextColor;
            return this;
        }

        public Builder setBackgroundColor(int backgroundColor) {
            this.ta = backgroundColor;
            this.tb = Color.argb(0, 0, 0, 0);
            this.tc = Color.argb(0, 0, 0, 0);
            return this;
        }

        public Builder setBackgroundGradient(int top, int bottom) {
            this.ta = Color.argb(0, 0, 0, 0);
            this.tb = bottom;
            this.tc = top;
            return this;
        }

        public Builder setBorderColor(int borderColor) {
            this.td = borderColor;
            return this;
        }

        public Builder setBorderThickness(int borderThickness) {
            this.te = borderThickness;
            return this;
        }

        public Builder setBorderType(int borderType) {
            this.tf = borderType;
            return this;
        }

        public Builder setCallButtonColor(int callButtonColor) {
            this.tg = callButtonColor;
            return this;
        }

        public Builder setCustomChannels(String channelIds) {
            this.th = channelIds;
            return this;
        }

        public Builder setDescriptionTextColor(int descriptionTextColor) {
            this.ti = descriptionTextColor;
            return this;
        }

        public Builder setFontFace(String fontFace) {
            this.tj = fontFace;
            return this;
        }

        public Builder setHeaderTextColor(int headerTextColor) {
            this.tk = headerTextColor;
            return this;
        }

        public Builder setHeaderTextSize(int headerTextSize) {
            this.tl = headerTextSize;
            return this;
        }

        public Builder setLocation(Location location) {
            this.kr.a(location);
            return this;
        }

        public Builder setQuery(String query) {
            this.tm = query;
            return this;
        }

        public Builder tagForChildDirectedTreatment(boolean tagForChildDirectedTreatment) {
            this.kr.h(tagForChildDirectedTreatment);
            return this;
        }
    }

    private SearchAdRequest(Builder builder) {
        this.sZ = builder.sZ;
        this.ta = builder.ta;
        this.tb = builder.tb;
        this.tc = builder.tc;
        this.td = builder.td;
        this.te = builder.te;
        this.tf = builder.tf;
        this.tg = builder.tg;
        this.th = builder.th;
        this.ti = builder.ti;
        this.tj = builder.tj;
        this.tk = builder.tk;
        this.tl = builder.tl;
        this.tm = builder.tm;
        this.kq = new au(builder.kr, this);
    }

    /* access modifiers changed from: package-private */
    public au O() {
        return this.kq;
    }

    public int getAnchorTextColor() {
        return this.sZ;
    }

    public int getBackgroundColor() {
        return this.ta;
    }

    public int getBackgroundGradientBottom() {
        return this.tb;
    }

    public int getBackgroundGradientTop() {
        return this.tc;
    }

    public int getBorderColor() {
        return this.td;
    }

    public int getBorderThickness() {
        return this.te;
    }

    public int getBorderType() {
        return this.tf;
    }

    public int getCallButtonColor() {
        return this.tg;
    }

    public String getCustomChannels() {
        return this.th;
    }

    public <T extends CustomEvent> Bundle getCustomEventExtrasBundle(Class<T> adapterClass) {
        return this.kq.getCustomEventExtrasBundle(adapterClass);
    }

    public int getDescriptionTextColor() {
        return this.ti;
    }

    public String getFontFace() {
        return this.tj;
    }

    public int getHeaderTextColor() {
        return this.tk;
    }

    public int getHeaderTextSize() {
        return this.tl;
    }

    public Location getLocation() {
        return this.kq.getLocation();
    }

    @Deprecated
    public <T extends NetworkExtras> T getNetworkExtras(Class<T> networkExtrasClass) {
        return this.kq.getNetworkExtras(networkExtrasClass);
    }

    public <T extends MediationAdapter> Bundle getNetworkExtrasBundle(Class<T> adapterClass) {
        return this.kq.getNetworkExtrasBundle(adapterClass);
    }

    public String getQuery() {
        return this.tm;
    }

    public boolean isTestDevice(Context context) {
        return this.kq.isTestDevice(context);
    }
}
