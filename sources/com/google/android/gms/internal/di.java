package com.google.android.gms.internal;

import com.google.android.gms.ads.purchase.InAppPurchaseListener;
import com.google.android.gms.internal.dd;

public final class di extends dd.a {
    private final InAppPurchaseListener mz;

    public di(InAppPurchaseListener inAppPurchaseListener) {
        this.mz = inAppPurchaseListener;
    }

    public void a(dc dcVar) {
        this.mz.onInAppPurchaseRequested(new dl(dcVar));
    }
}
