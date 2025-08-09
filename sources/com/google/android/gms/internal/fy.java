package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.fv;
import com.google.android.gms.internal.hc;

public class fy extends hc<fv> {
    public fy(Context context, Looper looper, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, connectionCallbacks, onConnectionFailedListener, new String[0]);
    }

    /* access modifiers changed from: protected */
    /* renamed from: C */
    public fv x(IBinder iBinder) {
        return fv.a.A(iBinder);
    }

    /* access modifiers changed from: protected */
    public void a(hj hjVar, hc.e eVar) throws RemoteException {
        hjVar.b(eVar, GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_VERSION_CODE, getContext().getPackageName());
    }

    /* access modifiers changed from: protected */
    public String bp() {
        return "com.google.android.gms.icing.LIGHTWEIGHT_INDEX_SERVICE";
    }

    /* access modifiers changed from: protected */
    public String bq() {
        return "com.google.android.gms.appdatasearch.internal.ILightweightAppDataSearch";
    }

    public fv dM() {
        return (fv) fo();
    }
}
