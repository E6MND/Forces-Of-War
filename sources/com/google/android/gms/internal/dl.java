package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.ads.purchase.InAppPurchase;

public class dl implements InAppPurchase {
    private final dc pg;

    public dl(dc dcVar) {
        this.pg = dcVar;
    }

    public String getProductId() {
        try {
            return this.pg.getProductId();
        } catch (RemoteException e) {
            ev.c("Could not forward getProductId to InAppPurchase", e);
            return null;
        }
    }

    public void recordPlayBillingResolution(int billingResponseCode) {
        try {
            this.pg.recordPlayBillingResolution(billingResponseCode);
        } catch (RemoteException e) {
            ev.c("Could not forward recordPlayBillingResolution to InAppPurchase", e);
        }
    }

    public void recordResolution(int resolution) {
        try {
            this.pg.recordResolution(resolution);
        } catch (RemoteException e) {
            ev.c("Could not forward recordResolution to InAppPurchase", e);
        }
    }
}
