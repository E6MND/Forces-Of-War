package com.google.android.gms.ads.purchase;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.internal.de;
import com.google.android.gms.internal.dj;
import com.google.android.gms.internal.ev;

public final class InAppPurchaseActivity extends Activity {
    public static final String CLASS_NAME = "com.google.android.gms.ads.purchase.InAppPurchaseActivity";
    public static final String SIMPLE_CLASS_NAME = "InAppPurchaseActivity";
    private de sY;

    /* access modifiers changed from: protected */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            if (this.sY != null) {
                this.sY.onActivityResult(requestCode, resultCode, data);
            }
        } catch (RemoteException e) {
            ev.c("Could not forward onActivityResult to in-app purchase manager:", e);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.sY = dj.d((Activity) this);
        if (this.sY == null) {
            ev.D("Could not create in-app purchase manager.");
            finish();
            return;
        }
        try {
            this.sY.onCreate();
        } catch (RemoteException e) {
            ev.c("Could not forward onCreate to in-app purchase manager:", e);
            finish();
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        try {
            if (this.sY != null) {
                this.sY.onDestroy();
            }
        } catch (RemoteException e) {
            ev.c("Could not forward onDestroy to in-app purchase manager:", e);
        }
        super.onDestroy();
    }
}
