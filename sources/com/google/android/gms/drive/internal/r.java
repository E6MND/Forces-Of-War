package com.google.android.gms.drive.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.events.DriveEvent;
import com.google.android.gms.drive.events.b;
import com.google.android.gms.drive.internal.aa;
import com.google.android.gms.drive.internal.p;
import com.google.android.gms.internal.gz;
import com.google.android.gms.internal.hc;
import com.google.android.gms.internal.hi;
import com.google.android.gms.internal.hj;
import com.google.android.gms.internal.hn;
import java.util.HashMap;
import java.util.Map;

public class r extends hc<aa> {
    private final String IN;
    private final Bundle IO;
    private DriveId IP;
    private DriveId IQ;
    final GoogleApiClient.ConnectionCallbacks IR;
    Map<DriveId, Map<DriveEvent.Listener<?>, x<?>>> IS = new HashMap();
    private final String yN;

    public r(Context context, Looper looper, gz gzVar, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener, String[] strArr, Bundle bundle) {
        super(context, looper, connectionCallbacks, onConnectionFailedListener, strArr);
        this.yN = (String) hn.b(gzVar.fe(), (Object) "Must call Api.ClientBuilder.setAccountName()");
        this.IN = gzVar.fi();
        this.IR = connectionCallbacks;
        this.IO = bundle;
    }

    /* access modifiers changed from: protected */
    /* renamed from: O */
    public aa x(IBinder iBinder) {
        return aa.a.P(iBinder);
    }

    /* access modifiers changed from: package-private */
    public <C extends DriveEvent> PendingResult<Status> a(GoogleApiClient googleApiClient, final DriveId driveId, final int i, DriveEvent.Listener<C> listener) {
        PendingResult<Status> b;
        hn.b(b.a(i, driveId), (Object) "id");
        hn.b(listener, (Object) "listener");
        hn.a(isConnected(), "Client must be connected");
        synchronized (this.IS) {
            Map map = this.IS.get(driveId);
            if (map == null) {
                map = new HashMap();
                this.IS.put(driveId, map);
            }
            if (map.containsKey(listener)) {
                b = new p.k(googleApiClient, Status.Ek);
            } else {
                final x xVar = new x(getLooper(), i, listener);
                map.put(listener, xVar);
                b = googleApiClient.b(new p.j() {
                    /* access modifiers changed from: protected */
                    public void a(r rVar) throws RemoteException {
                        rVar.gk().a(new AddEventListenerRequest(driveId, i, (PendingIntent) null), (ac) xVar, (String) null, (ab) new aw(this));
                    }
                });
            }
        }
        return b;
    }

    /* access modifiers changed from: protected */
    public void a(int i, IBinder iBinder, Bundle bundle) {
        if (bundle != null) {
            bundle.setClassLoader(getClass().getClassLoader());
            this.IP = (DriveId) bundle.getParcelable("com.google.android.gms.drive.root_id");
            this.IQ = (DriveId) bundle.getParcelable("com.google.android.gms.drive.appdata_id");
        }
        super.a(i, iBinder, bundle);
    }

    /* access modifiers changed from: protected */
    public void a(hj hjVar, hc.e eVar) throws RemoteException {
        String packageName = getContext().getPackageName();
        hn.f(eVar);
        hn.f(packageName);
        hn.f(fn());
        Bundle bundle = new Bundle();
        bundle.putString("proxy_package_name", this.IN);
        bundle.putAll(this.IO);
        hjVar.a((hi) eVar, (int) GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_VERSION_CODE, packageName, fn(), this.yN, bundle);
    }

    /* access modifiers changed from: package-private */
    public PendingResult<Status> b(GoogleApiClient googleApiClient, final DriveId driveId, final int i, DriveEvent.Listener<?> listener) {
        PendingResult<Status> b;
        hn.b(b.a(i, driveId), (Object) "id");
        hn.a(isConnected(), "Client must be connected");
        hn.b(listener, (Object) "listener");
        synchronized (this.IS) {
            Map map = this.IS.get(driveId);
            if (map == null) {
                b = new p.k(googleApiClient, Status.Ek);
            } else {
                final x xVar = (x) map.remove(listener);
                if (xVar == null) {
                    b = new p.k(googleApiClient, Status.Ek);
                } else {
                    if (map.isEmpty()) {
                        this.IS.remove(driveId);
                    }
                    b = googleApiClient.b(new p.j() {
                        /* access modifiers changed from: protected */
                        public void a(r rVar) throws RemoteException {
                            rVar.gk().a(new RemoveEventListenerRequest(driveId, i), (ac) xVar, (String) null, (ab) new aw(this));
                        }
                    });
                }
            }
        }
        return b;
    }

    /* access modifiers changed from: protected */
    public String bp() {
        return "com.google.android.gms.drive.ApiService.START";
    }

    /* access modifiers changed from: protected */
    public String bq() {
        return "com.google.android.gms.drive.internal.IDriveService";
    }

    public void disconnect() {
        aa aaVar = (aa) fo();
        if (aaVar != null) {
            try {
                aaVar.a(new DisconnectRequest());
            } catch (RemoteException e) {
            }
        }
        super.disconnect();
        this.IS.clear();
    }

    public aa gk() {
        return (aa) fo();
    }

    public DriveId gl() {
        return this.IP;
    }

    public DriveId gm() {
        return this.IQ;
    }
}
