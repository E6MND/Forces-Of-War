package com.google.android.gms.internal;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.internal.aq;

public final class ah extends aq.a {
    private final AdListener lO;

    public ah(AdListener adListener) {
        this.lO = adListener;
    }

    public void onAdClosed() {
        this.lO.onAdClosed();
    }

    public void onAdFailedToLoad(int errorCode) {
        this.lO.onAdFailedToLoad(errorCode);
    }

    public void onAdLeftApplication() {
        this.lO.onAdLeftApplication();
    }

    public void onAdLoaded() {
        this.lO.onAdLoaded();
    }

    public void onAdOpened() {
        this.lO.onAdOpened();
    }
}
