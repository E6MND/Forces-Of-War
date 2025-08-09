package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.hc;
import com.google.android.gms.internal.je;
import com.google.android.gms.internal.jf;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationStatusCodes;
import java.util.List;

public class jh extends hc<jf> {
    private final jk<jf> VG;
    private final jg VM;
    private final ka VN;
    private final ja VO;
    private final String VP;

    private final class a extends hc<jf>.b<LocationClient.OnAddGeofencesResultListener> {
        private final int CQ;
        private final String[] VQ;

        public a(LocationClient.OnAddGeofencesResultListener onAddGeofencesResultListener, int i, String[] strArr) {
            super(onAddGeofencesResultListener);
            this.CQ = LocationStatusCodes.cJ(i);
            this.VQ = strArr;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void d(LocationClient.OnAddGeofencesResultListener onAddGeofencesResultListener) {
            if (onAddGeofencesResultListener != null) {
                onAddGeofencesResultListener.onAddGeofencesResult(this.CQ, this.VQ);
            }
        }

        /* access modifiers changed from: protected */
        public void fp() {
        }
    }

    private static final class b extends je.a {
        private LocationClient.OnAddGeofencesResultListener VS;
        private LocationClient.OnRemoveGeofencesResultListener VT;
        private jh VU;

        public b(LocationClient.OnAddGeofencesResultListener onAddGeofencesResultListener, jh jhVar) {
            this.VS = onAddGeofencesResultListener;
            this.VT = null;
            this.VU = jhVar;
        }

        public b(LocationClient.OnRemoveGeofencesResultListener onRemoveGeofencesResultListener, jh jhVar) {
            this.VT = onRemoveGeofencesResultListener;
            this.VS = null;
            this.VU = jhVar;
        }

        public void onAddGeofencesResult(int statusCode, String[] geofenceRequestIds) throws RemoteException {
            if (this.VU == null) {
                Log.wtf("LocationClientImpl", "onAddGeofenceResult called multiple times");
                return;
            }
            jh jhVar = this.VU;
            jh jhVar2 = this.VU;
            jhVar2.getClass();
            jhVar.a((hc<T>.b<?>) new a(this.VS, statusCode, geofenceRequestIds));
            this.VU = null;
            this.VS = null;
            this.VT = null;
        }

        public void onRemoveGeofencesByPendingIntentResult(int statusCode, PendingIntent pendingIntent) {
            if (this.VU == null) {
                Log.wtf("LocationClientImpl", "onRemoveGeofencesByPendingIntentResult called multiple times");
                return;
            }
            jh jhVar = this.VU;
            jh jhVar2 = this.VU;
            jhVar2.getClass();
            jhVar.a((hc<T>.b<?>) new d(jhVar2, 1, this.VT, statusCode, pendingIntent));
            this.VU = null;
            this.VS = null;
            this.VT = null;
        }

        public void onRemoveGeofencesByRequestIdsResult(int statusCode, String[] geofenceRequestIds) {
            if (this.VU == null) {
                Log.wtf("LocationClientImpl", "onRemoveGeofencesByRequestIdsResult called multiple times");
                return;
            }
            jh jhVar = this.VU;
            jh jhVar2 = this.VU;
            jhVar2.getClass();
            jhVar.a((hc<T>.b<?>) new d(2, this.VT, statusCode, geofenceRequestIds));
            this.VU = null;
            this.VS = null;
            this.VT = null;
        }
    }

    private final class c implements jk<jf> {
        private c() {
        }

        public void ci() {
            jh.this.ci();
        }

        /* renamed from: iU */
        public jf fo() {
            return (jf) jh.this.fo();
        }
    }

    private final class d extends hc<jf>.b<LocationClient.OnRemoveGeofencesResultListener> {
        private final int CQ;
        private final String[] VQ;
        private final int VV;
        private final PendingIntent mPendingIntent;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public d(jh jhVar, int i, LocationClient.OnRemoveGeofencesResultListener onRemoveGeofencesResultListener, int i2, PendingIntent pendingIntent) {
            super(onRemoveGeofencesResultListener);
            boolean z = true;
            jh.this = jhVar;
            gy.A(i != 1 ? false : z);
            this.VV = i;
            this.CQ = LocationStatusCodes.cJ(i2);
            this.mPendingIntent = pendingIntent;
            this.VQ = null;
        }

        public d(int i, LocationClient.OnRemoveGeofencesResultListener onRemoveGeofencesResultListener, int i2, String[] strArr) {
            super(onRemoveGeofencesResultListener);
            gy.A(i == 2);
            this.VV = i;
            this.CQ = LocationStatusCodes.cJ(i2);
            this.VQ = strArr;
            this.mPendingIntent = null;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void d(LocationClient.OnRemoveGeofencesResultListener onRemoveGeofencesResultListener) {
            if (onRemoveGeofencesResultListener != null) {
                switch (this.VV) {
                    case 1:
                        onRemoveGeofencesResultListener.onRemoveGeofencesByPendingIntentResult(this.CQ, this.mPendingIntent);
                        return;
                    case 2:
                        onRemoveGeofencesResultListener.onRemoveGeofencesByRequestIdsResult(this.CQ, this.VQ);
                        return;
                    default:
                        Log.wtf("LocationClientImpl", "Unsupported action: " + this.VV);
                        return;
                }
            }
        }

        /* access modifiers changed from: protected */
        public void fp() {
        }
    }

    public jh(Context context, Looper looper, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener, String str) {
        this(context, looper, context.getPackageName(), connectionCallbacks, onConnectionFailedListener, str, (String) null);
    }

    public jh(Context context, Looper looper, String str, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener, String str2, String str3) {
        super(context, looper, connectionCallbacks, onConnectionFailedListener, new String[0]);
        this.VG = new c();
        this.VM = new jg(context, this.VG);
        this.VP = str2;
        this.VN = new ka(str, this.VG);
        this.VO = ja.a(context, str3, this.VG);
    }

    public jh(Context context, GooglePlayServicesClient.ConnectionCallbacks connectionCallbacks, GooglePlayServicesClient.OnConnectionFailedListener onConnectionFailedListener, String str) {
        super(context, connectionCallbacks, onConnectionFailedListener, new String[0]);
        this.VG = new c();
        this.VM = new jg(context, this.VG);
        this.VP = str;
        this.VN = new ka(context.getPackageName(), this.VG);
        this.VO = ja.a(context, (String) null, this.VG);
    }

    /* access modifiers changed from: protected */
    public void a(hj hjVar, hc.e eVar) throws RemoteException {
        Bundle bundle = new Bundle();
        bundle.putString("client_name", this.VP);
        hjVar.e(eVar, GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_VERSION_CODE, getContext().getPackageName(), bundle);
    }

    public void addGeofences(List<ji> geofences, PendingIntent pendingIntent, LocationClient.OnAddGeofencesResultListener listener) throws RemoteException {
        ci();
        hn.b(geofences != null && geofences.size() > 0, (Object) "At least one geofence must be specified.");
        hn.b(pendingIntent, (Object) "PendingIntent must be specified.");
        hn.b(listener, (Object) "OnAddGeofencesResultListener not provided.");
        ((jf) fo()).a(geofences, pendingIntent, (je) listener == null ? null : new b(listener, this), getContext().getPackageName());
    }

    /* access modifiers changed from: protected */
    /* renamed from: at */
    public jf x(IBinder iBinder) {
        return jf.a.as(iBinder);
    }

    /* access modifiers changed from: protected */
    public String bp() {
        return "com.google.android.location.internal.GoogleLocationManagerService.START";
    }

    /* access modifiers changed from: protected */
    public String bq() {
        return "com.google.android.gms.location.internal.IGoogleLocationManagerService";
    }

    public void disconnect() {
        synchronized (this.VM) {
            if (isConnected()) {
                this.VM.removeAllListeners();
                this.VM.iT();
            }
            super.disconnect();
        }
    }

    public Location getLastLocation() {
        return this.VM.getLastLocation();
    }

    public void removeActivityUpdates(PendingIntent callbackIntent) throws RemoteException {
        ci();
        hn.f(callbackIntent);
        ((jf) fo()).removeActivityUpdates(callbackIntent);
    }

    public void removeGeofences(PendingIntent pendingIntent, LocationClient.OnRemoveGeofencesResultListener listener) throws RemoteException {
        ci();
        hn.b(pendingIntent, (Object) "PendingIntent must be specified.");
        hn.b(listener, (Object) "OnRemoveGeofencesResultListener not provided.");
        ((jf) fo()).a(pendingIntent, (je) listener == null ? null : new b(listener, this), getContext().getPackageName());
    }

    public void removeGeofences(List<String> geofenceRequestIds, LocationClient.OnRemoveGeofencesResultListener listener) throws RemoteException {
        ci();
        hn.b(geofenceRequestIds != null && geofenceRequestIds.size() > 0, (Object) "geofenceRequestIds can't be null nor empty.");
        hn.b(listener, (Object) "OnRemoveGeofencesResultListener not provided.");
        ((jf) fo()).a((String[]) geofenceRequestIds.toArray(new String[0]), (je) listener == null ? null : new b(listener, this), getContext().getPackageName());
    }

    public void removeLocationUpdates(PendingIntent callbackIntent) throws RemoteException {
        this.VM.removeLocationUpdates(callbackIntent);
    }

    public void removeLocationUpdates(LocationListener listener) throws RemoteException {
        this.VM.removeLocationUpdates(listener);
    }

    public void requestActivityUpdates(long detectionIntervalMillis, PendingIntent callbackIntent) throws RemoteException {
        ci();
        hn.f(callbackIntent);
        hn.b(detectionIntervalMillis >= 0, (Object) "detectionIntervalMillis must be >= 0");
        ((jf) fo()).a(detectionIntervalMillis, true, callbackIntent);
    }

    public void requestLocationUpdates(LocationRequest request, PendingIntent callbackIntent) throws RemoteException {
        this.VM.requestLocationUpdates(request, callbackIntent);
    }

    public void requestLocationUpdates(LocationRequest request, LocationListener listener) throws RemoteException {
        requestLocationUpdates(request, listener, (Looper) null);
    }

    public void requestLocationUpdates(LocationRequest request, LocationListener listener, Looper looper) throws RemoteException {
        synchronized (this.VM) {
            this.VM.requestLocationUpdates(request, listener, looper);
        }
    }

    public void setMockLocation(Location mockLocation) throws RemoteException {
        this.VM.setMockLocation(mockLocation);
    }

    public void setMockMode(boolean isMockMode) throws RemoteException {
        this.VM.setMockMode(isMockMode);
    }
}
