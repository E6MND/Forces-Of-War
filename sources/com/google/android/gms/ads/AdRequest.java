package com.google.android.gms.ads;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.ads.mediation.NetworkExtras;
import com.google.android.gms.ads.mediation.customevent.CustomEvent;
import com.google.android.gms.internal.au;
import com.google.android.gms.internal.hn;
import java.util.Date;
import java.util.Set;

public final class AdRequest {
    public static final String DEVICE_ID_EMULATOR = au.DEVICE_ID_EMULATOR;
    public static final int ERROR_CODE_INTERNAL_ERROR = 0;
    public static final int ERROR_CODE_INVALID_REQUEST = 1;
    public static final int ERROR_CODE_NETWORK_ERROR = 2;
    public static final int ERROR_CODE_NO_FILL = 3;
    public static final int GENDER_FEMALE = 2;
    public static final int GENDER_MALE = 1;
    public static final int GENDER_UNKNOWN = 0;
    public static final int MAX_CONTENT_URL_LENGTH = 512;
    private final au kq;

    public static final class Builder {
        /* access modifiers changed from: private */
        public final au.a kr = new au.a();

        public Builder addCustomEventExtrasBundle(Class<? extends CustomEvent> adapterClass, Bundle customEventExtras) {
            this.kr.b(adapterClass, customEventExtras);
            return this;
        }

        public Builder addKeyword(String keyword) {
            this.kr.g(keyword);
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

        public AdRequest build() {
            return new AdRequest(this);
        }

        public Builder setBirthday(Date birthday) {
            this.kr.a(birthday);
            return this;
        }

        public Builder setContentUrl(String contentUrl) {
            hn.b(contentUrl, (Object) "Content URL must be non-null.");
            hn.b(contentUrl, (Object) "Content URL must be non-empty.");
            hn.b(contentUrl.length() <= 512, "Content URL must not exceed %d in length.  Provided length was %d.", 512, Integer.valueOf(contentUrl.length()));
            this.kr.i(contentUrl);
            return this;
        }

        public Builder setGender(int gender) {
            this.kr.e(gender);
            return this;
        }

        public Builder setLocation(Location location) {
            this.kr.a(location);
            return this;
        }

        public Builder tagForChildDirectedTreatment(boolean tagForChildDirectedTreatment) {
            this.kr.h(tagForChildDirectedTreatment);
            return this;
        }
    }

    private AdRequest(Builder builder) {
        this.kq = new au(builder.kr);
    }

    /* access modifiers changed from: package-private */
    public au O() {
        return this.kq;
    }

    public Date getBirthday() {
        return this.kq.getBirthday();
    }

    public String getContentUrl() {
        return this.kq.getContentUrl();
    }

    public <T extends CustomEvent> Bundle getCustomEventExtrasBundle(Class<T> adapterClass) {
        return this.kq.getCustomEventExtrasBundle(adapterClass);
    }

    public int getGender() {
        return this.kq.getGender();
    }

    public Set<String> getKeywords() {
        return this.kq.getKeywords();
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

    public boolean isTestDevice(Context context) {
        return this.kq.isTestDevice(context);
    }
}
