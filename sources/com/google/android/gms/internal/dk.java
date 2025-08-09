package com.google.android.gms.internal;

import android.content.Intent;
import android.os.RemoteException;
import com.google.android.gms.ads.purchase.InAppPurchaseResult;

public class dk implements InAppPurchaseResult {
    private final dg pu;

    public dk(dg dgVar) {
        this.pu = dgVar;
    }

    public void finishPurchase() {
        try {
            this.pu.finishPurchase();
        } catch (RemoteException e) {
            ev.c("Could not forward finishPurchase to InAppPurchaseResult", e);
        }
    }

    public String getProductId() {
        try {
            return this.pu.getProductId();
        } catch (RemoteException e) {
            ev.c("Could not forward getProductId to InAppPurchaseResult", e);
            return null;
        }
    }

    public Intent getPurchaseData() {
        try {
            return this.pu.getPurchaseData();
        } catch (RemoteException e) {
            ev.c("Could not forward getPurchaseData to InAppPurchaseResult", e);
            return null;
        }
    }

    public int getResultCode() {
        try {
            return this.pu.getResultCode();
        } catch (RemoteException e) {
            ev.c("Could not forward getPurchaseData to InAppPurchaseResult", e);
            return 0;
        }
    }

    public boolean isVerified() {
        try {
            return this.pu.isVerified();
        } catch (RemoteException e) {
            ev.c("Could not forward isVerified to InAppPurchaseResult", e);
            return false;
        }
    }
}
