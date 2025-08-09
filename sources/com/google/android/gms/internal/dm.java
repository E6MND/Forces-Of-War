package com.google.android.gms.internal;

import com.google.android.gms.ads.purchase.PlayStorePurchaseListener;
import com.google.android.gms.internal.dh;

public final class dm extends dh.a {
    private final PlayStorePurchaseListener mA;

    public dm(PlayStorePurchaseListener playStorePurchaseListener) {
        this.mA = playStorePurchaseListener;
    }

    public void a(dg dgVar) {
        this.mA.onInAppPurchaseFinished(new dk(dgVar));
    }

    public boolean isValidPurchase(String productId) {
        return this.mA.isValidPurchase(productId);
    }
}
