package com.google.android.gms.ads.doubleclick;

import android.content.Context;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.internal.aw;

public final class PublisherInterstitialAd {
    private final aw kw;

    public PublisherInterstitialAd(Context context) {
        this.kw = new aw(context);
    }

    public AdListener getAdListener() {
        return this.kw.getAdListener();
    }

    public String getAdUnitId() {
        return this.kw.getAdUnitId();
    }

    public AppEventListener getAppEventListener() {
        return this.kw.getAppEventListener();
    }

    public boolean isLoaded() {
        return this.kw.isLoaded();
    }

    public void loadAd(PublisherAdRequest publisherAdRequest) {
        this.kw.a(publisherAdRequest.O());
    }

    public void setAdListener(AdListener adListener) {
        this.kw.setAdListener(adListener);
    }

    public void setAdUnitId(String adUnitId) {
        this.kw.setAdUnitId(adUnitId);
    }

    public void setAppEventListener(AppEventListener appEventListener) {
        this.kw.setAppEventListener(appEventListener);
    }

    public void show() {
        this.kw.show();
    }
}
