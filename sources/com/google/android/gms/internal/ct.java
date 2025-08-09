package com.google.android.gms.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.SystemClock;
import com.uken.android.util.IabHelper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ct extends en implements ServiceConnection {
    private final Object lq = new Object();
    /* access modifiers changed from: private */
    public Context mContext;
    private boolean oU = false;
    /* access modifiers changed from: private */
    public dh oV;
    private cs oW;
    private cy oX;
    private List<cw> oY = null;
    /* access modifiers changed from: private */
    public da oZ;

    public ct(Context context, dh dhVar, da daVar) {
        this.mContext = context;
        this.oV = dhVar;
        this.oZ = daVar;
        this.oW = new cs(context);
        this.oX = cy.h(this.mContext);
        this.oY = this.oX.d(10);
    }

    private void a(final cw cwVar, String str, String str2) {
        final Intent intent = new Intent();
        intent.putExtra(IabHelper.RESPONSE_CODE, 0);
        intent.putExtra(IabHelper.RESPONSE_INAPP_PURCHASE_DATA, str);
        intent.putExtra(IabHelper.RESPONSE_INAPP_SIGNATURE, str2);
        eu.ss.post(new Runnable() {
            public void run() {
                try {
                    if (ct.this.oZ.a(cwVar.pk, -1, intent)) {
                        ct.this.oV.a(new cx(ct.this.mContext, cwVar.pl, true, -1, intent, cwVar));
                    } else {
                        ct.this.oV.a(new cx(ct.this.mContext, cwVar.pl, false, -1, intent, cwVar));
                    }
                } catch (RemoteException e) {
                    ev.D("Fail to verify and dispatch pending transaction");
                }
            }
        });
    }

    private void b(long j) {
        do {
            if (!c(j)) {
                ev.D("Timeout waiting for pending transaction to be processed.");
            }
        } while (!this.oU);
    }

    private void bd() {
        if (!this.oY.isEmpty()) {
            HashMap hashMap = new HashMap();
            for (cw next : this.oY) {
                hashMap.put(next.pl, next);
            }
            String str = null;
            while (true) {
                Bundle b = this.oW.b(this.mContext.getPackageName(), str);
                if (b == null || cz.a(b) != 0) {
                    break;
                }
                ArrayList<String> stringArrayList = b.getStringArrayList(IabHelper.RESPONSE_INAPP_ITEM_LIST);
                ArrayList<String> stringArrayList2 = b.getStringArrayList(IabHelper.RESPONSE_INAPP_PURCHASE_DATA_LIST);
                ArrayList<String> stringArrayList3 = b.getStringArrayList(IabHelper.RESPONSE_INAPP_SIGNATURE_LIST);
                String string = b.getString(IabHelper.INAPP_CONTINUATION_TOKEN);
                int i = 0;
                while (true) {
                    int i2 = i;
                    if (i2 >= stringArrayList.size()) {
                        break;
                    }
                    if (hashMap.containsKey(stringArrayList.get(i2))) {
                        String str2 = stringArrayList.get(i2);
                        String str3 = stringArrayList2.get(i2);
                        String str4 = stringArrayList3.get(i2);
                        cw cwVar = (cw) hashMap.get(str2);
                        if (cwVar.pk.equals(cz.p(str3))) {
                            a(cwVar, str3, str4);
                            hashMap.remove(str2);
                        }
                    }
                    i = i2 + 1;
                }
                if (string == null || hashMap.isEmpty()) {
                    break;
                }
                str = string;
            }
            for (String str5 : hashMap.keySet()) {
                this.oX.a((cw) hashMap.get(str5));
            }
        }
    }

    private boolean c(long j) {
        long elapsedRealtime = 60000 - (SystemClock.elapsedRealtime() - j);
        if (elapsedRealtime <= 0) {
            return false;
        }
        try {
            this.lq.wait(elapsedRealtime);
        } catch (InterruptedException e) {
            ev.D("waitWithTimeout_lock interrupted");
        }
        return true;
    }

    public void bc() {
        synchronized (this.lq) {
            Context context = this.mContext;
            Intent intent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
            Context context2 = this.mContext;
            context.bindService(intent, this, 1);
            b(SystemClock.elapsedRealtime());
            this.mContext.unbindService(this);
            this.oW.destroy();
        }
    }

    public void onServiceConnected(ComponentName name, IBinder service) {
        synchronized (this.lq) {
            this.oW.o(service);
            bd();
            this.oU = true;
            this.lq.notify();
        }
    }

    public void onServiceDisconnected(ComponentName name) {
        ev.B("In-app billing service disconnected.");
        this.oW.destroy();
    }

    public void onStop() {
        synchronized (this.lq) {
            this.mContext.unbindService(this);
            this.oW.destroy();
        }
    }
}
