package com.google.android.gms.internal;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.ads.purchase.InAppPurchaseActivity;
import com.google.android.gms.internal.de;
import com.uken.android.util.IabHelper;

public class cv extends de.a implements ServiceConnection {
    private dh oV;
    private cs oW;
    private final cy oX;
    private da oZ;
    private final Activity oe;
    private Context pf;
    private dc pg;
    private cw ph;
    private String pi = null;

    public cv(Activity activity) {
        this.oe = activity;
        this.oX = cy.h(this.oe.getApplicationContext());
    }

    public static void a(Context context, boolean z, cr crVar) {
        Intent intent = new Intent();
        intent.setClassName(context, InAppPurchaseActivity.CLASS_NAME);
        intent.putExtra("com.google.android.gms.ads.internal.purchase.useClientJar", z);
        cr.a(intent, crVar);
        context.startActivity(intent);
    }

    private void a(String str, boolean z, int i, Intent intent) {
        try {
            this.oV.a(new cx(this.pf, str, z, i, intent, this.ph));
        } catch (RemoteException e) {
            ev.D("Fail to invoke PlayStorePurchaseListener.");
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1001) {
            try {
                int c = cz.c(data);
                if (resultCode != -1 || c != 0) {
                    this.oX.a(this.ph);
                    a(this.pg.getProductId(), false, resultCode, data);
                } else if (this.oZ.a(this.pi, resultCode, data)) {
                    a(this.pg.getProductId(), true, resultCode, data);
                } else {
                    a(this.pg.getProductId(), false, resultCode, data);
                }
                this.pg.recordPlayBillingResolution(c);
            } catch (RemoteException e) {
                ev.D("Fail to process purchase result.");
            } finally {
                this.pi = null;
                this.oe.finish();
            }
        }
    }

    public void onCreate() {
        cr b = cr.b(this.oe.getIntent());
        this.oV = b.kV;
        this.oZ = b.kX;
        this.pg = b.oR;
        this.oW = new cs(this.oe.getApplicationContext());
        this.pf = b.oS;
        Activity activity = this.oe;
        Intent intent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
        this.oe.getApplicationContext();
        activity.bindService(intent, this, 1);
    }

    public void onDestroy() {
        this.oe.unbindService(this);
        this.oW.destroy();
    }

    public void onServiceConnected(ComponentName name, IBinder service) {
        this.oW.o(service);
        try {
            this.pi = this.oZ.bh();
            Bundle a = this.oW.a(this.oe.getPackageName(), this.pg.getProductId(), this.pi);
            PendingIntent pendingIntent = (PendingIntent) a.getParcelable(IabHelper.RESPONSE_BUY_INTENT);
            if (pendingIntent == null) {
                int a2 = cz.a(a);
                this.pg.recordPlayBillingResolution(a2);
                a(this.pg.getProductId(), false, a2, (Intent) null);
                this.oe.finish();
                return;
            }
            this.ph = new cw(this.pg.getProductId(), this.pi);
            this.oX.b(this.ph);
            Integer num = 0;
            Integer num2 = 0;
            Integer num3 = 0;
            this.oe.startIntentSenderForResult(pendingIntent.getIntentSender(), 1001, new Intent(), num.intValue(), num2.intValue(), num3.intValue());
        } catch (IntentSender.SendIntentException | RemoteException e) {
            ev.c("Error when connecting in-app billing service", e);
            this.oe.finish();
        }
    }

    public void onServiceDisconnected(ComponentName name) {
        ev.B("In-app billing service disconnected.");
        this.oW.destroy();
    }
}
