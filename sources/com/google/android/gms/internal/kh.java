package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.hc;
import com.google.android.gms.internal.kf;

public class kh extends hc<kf> {
    public kh(Context context, Looper looper, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, connectionCallbacks, onConnectionFailedListener, (String[]) null);
    }

    /* access modifiers changed from: protected */
    public void a(hj hjVar, hc.e eVar) throws RemoteException {
        hjVar.a((hi) eVar, (int) GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_VERSION_CODE, getContext().getPackageName(), new Bundle());
    }

    /* renamed from: bj */
    public kf x(IBinder iBinder) {
        return kf.a.bi(iBinder);
    }

    /* access modifiers changed from: protected */
    public String bp() {
        return "com.google.android.gms.panorama.service.START";
    }

    /* access modifiers changed from: protected */
    public String bq() {
        return "com.google.android.gms.panorama.internal.IPanoramaService";
    }
}
