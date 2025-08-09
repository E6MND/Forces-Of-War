package com.google.android.gms.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.google.android.gms.internal.dg;

public final class cx extends dg.a implements ServiceConnection {
    private Context mContext;
    private cs oW;
    private String pd;
    private cw ph;
    private boolean pm = false;
    private int pn;
    private Intent po;

    public cx(Context context, String str, boolean z, int i, Intent intent, cw cwVar) {
        this.pd = str;
        this.pn = i;
        this.po = intent;
        this.pm = z;
        this.mContext = context;
        this.ph = cwVar;
    }

    public void finishPurchase() {
        int c = cz.c(this.po);
        if (this.pn == -1 && c == 0) {
            this.oW = new cs(this.mContext);
            Context context = this.mContext;
            Intent intent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
            Context context2 = this.mContext;
            context.bindService(intent, this, 1);
        }
    }

    public String getProductId() {
        return this.pd;
    }

    public Intent getPurchaseData() {
        return this.po;
    }

    public int getResultCode() {
        return this.pn;
    }

    public boolean isVerified() {
        return this.pm;
    }

    public void onServiceConnected(ComponentName name, IBinder service) {
        ev.B("In-app billing service connected.");
        this.oW.o(service);
        String q = cz.q(cz.d(this.po));
        if (q != null) {
            if (this.oW.a(this.mContext.getPackageName(), q) == 0) {
                cy.h(this.mContext).a(this.ph);
            }
            this.mContext.unbindService(this);
            this.oW.destroy();
        }
    }

    public void onServiceDisconnected(ComponentName name) {
        ev.B("In-app billing service disconnected.");
        this.oW.destroy();
    }
}
