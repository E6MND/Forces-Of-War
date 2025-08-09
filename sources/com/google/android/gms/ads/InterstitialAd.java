package com.google.android.gms.ads;

import android.content.Context;
import com.google.android.gms.ads.purchase.InAppPurchaseListener;
import com.google.android.gms.ads.purchase.PlayStorePurchaseListener;
import com.google.android.gms.internal.aw;

public final class InterstitialAd {
    private final aw kw;

    public InterstitialAd(Context context) {
        this.kw = new aw(context);
    }

    public AdListener getAdListener() {
        return this.kw.getAdListener();
    }

    public String getAdUnitId() {
        return this.kw.getAdUnitId();
    }

    public InAppPurchaseListener getInAppPurchaseListener() {
        return this.kw.getInAppPurchaseListener();
    }

    public boolean isLoaded() {
        return this.kw.isLoaded();
    }

    public void loadAd(AdRequest adRequest) {
        this.kw.a(adRequest.O());
    }

    public void setAdListener(AdListener adListener) {
        this.kw.setAdListener(adListener);
    }

    public void setAdUnitId(String adUnitId) {
        this.kw.setAdUnitId(adUnitId);
    }

    public void setInAppPurchaseListener(InAppPurchaseListener inAppPurchaseListener) {
        this.kw.setInAppPurchaseListener(inAppPurchaseListener);
    }

    public void setPlayStorePurchaseParams(PlayStorePurchaseListener playStorePurchaseListener, String publicKey) {
        this.kw.setPlayStorePurchaseParams(playStorePurchaseListener, publicKey);
    }

    public void show() {
        this.kw.show();
    }
}
