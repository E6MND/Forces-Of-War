package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.internal.dx;
import com.google.android.gms.internal.hc;

public class ds extends hc<dx> {
    final int pT;

    public ds(Context context, GooglePlayServicesClient.ConnectionCallbacks connectionCallbacks, GooglePlayServicesClient.OnConnectionFailedListener onConnectionFailedListener, int i) {
        super(context, connectionCallbacks, onConnectionFailedListener, new String[0]);
        this.pT = i;
    }

    /* access modifiers changed from: protected */
    public void a(hj hjVar, hc.e eVar) throws RemoteException {
        hjVar.g(eVar, this.pT, getContext().getPackageName(), new Bundle());
    }

    /* access modifiers changed from: protected */
    public String bp() {
        return "com.google.android.gms.ads.service.START";
    }

    /* access modifiers changed from: protected */
    public String bq() {
        return "com.google.android.gms.ads.internal.request.IAdRequestService";
    }

    public dx br() {
        return (dx) super.fo();
    }

    /* access modifiers changed from: protected */
    /* renamed from: w */
    public dx x(IBinder iBinder) {
        return dx.a.y(iBinder);
    }
}
