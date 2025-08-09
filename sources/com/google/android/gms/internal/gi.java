package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.cast.Cast;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.LaunchOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.a;
import com.google.android.gms.internal.gm;
import com.google.android.gms.internal.gn;
import com.google.android.gms.internal.hc;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

public final class gi extends hc<gm> {
    /* access modifiers changed from: private */
    public static final go BD = new go("CastClientImpl");
    /* access modifiers changed from: private */
    public static final Object BX = new Object();
    /* access modifiers changed from: private */
    public static final Object BY = new Object();
    private double AM = 0.0d;
    private boolean AN = false;
    /* access modifiers changed from: private */
    public final Cast.Listener Ab;
    /* access modifiers changed from: private */
    public ApplicationMetadata BE = null;
    /* access modifiers changed from: private */
    public final CastDevice BF;
    private final gn BG;
    /* access modifiers changed from: private */
    public final Map<String, Cast.MessageReceivedCallback> BH = new HashMap();
    private final long BI;
    private String BJ = null;
    private boolean BK;
    private boolean BL;
    /* access modifiers changed from: private */
    public boolean BM = false;
    /* access modifiers changed from: private */
    public AtomicBoolean BN = new AtomicBoolean(false);
    private int BO = -1;
    private final AtomicLong BP = new AtomicLong(0);
    /* access modifiers changed from: private */
    public String BQ;
    /* access modifiers changed from: private */
    public String BR;
    private Bundle BS;
    /* access modifiers changed from: private */
    public Map<Long, a.d<Status>> BT = new HashMap();
    private b BU = new b();
    /* access modifiers changed from: private */
    public a.d<Cast.ApplicationConnectionResult> BV;
    /* access modifiers changed from: private */
    public a.d<Status> BW;
    /* access modifiers changed from: private */
    public final Handler mHandler;

    private static final class a implements Cast.ApplicationConnectionResult {
        private final ApplicationMetadata Cf;
        private final String Cg;
        private final boolean Ch;
        private final String rO;
        private final Status yw;

        public a(Status status) {
            this(status, (ApplicationMetadata) null, (String) null, (String) null, false);
        }

        public a(Status status, ApplicationMetadata applicationMetadata, String str, String str2, boolean z) {
            this.yw = status;
            this.Cf = applicationMetadata;
            this.Cg = str;
            this.rO = str2;
            this.Ch = z;
        }

        public ApplicationMetadata getApplicationMetadata() {
            return this.Cf;
        }

        public String getApplicationStatus() {
            return this.Cg;
        }

        public String getSessionId() {
            return this.rO;
        }

        public Status getStatus() {
            return this.yw;
        }

        public boolean getWasLaunched() {
            return this.Ch;
        }
    }

    private class b implements GoogleApiClient.OnConnectionFailedListener {
        private b() {
        }

        public void onConnectionFailed(ConnectionResult result) {
            gi.this.ed();
        }
    }

    public gi(Context context, Looper looper, CastDevice castDevice, long j, Cast.Listener listener, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, connectionCallbacks, onConnectionFailedListener, (String[]) null);
        this.BF = castDevice;
        this.Ab = listener;
        this.BI = j;
        this.mHandler = new Handler(looper);
        registerConnectionFailedListener((GoogleApiClient.OnConnectionFailedListener) this.BU);
        this.BG = new gn.a() {
            private boolean X(int i) {
                synchronized (gi.BY) {
                    if (gi.this.BW == null) {
                        return false;
                    }
                    gi.this.BW.a(new Status(i));
                    a.d unused = gi.this.BW = null;
                    return true;
                }
            }

            private void b(long j, int i) {
                a.d dVar;
                synchronized (gi.this.BT) {
                    dVar = (a.d) gi.this.BT.remove(Long.valueOf(j));
                }
                if (dVar != null) {
                    dVar.a(new Status(i));
                }
            }

            public void T(int i) {
                gi.BD.b("ICastDeviceControllerListener.onDisconnected: %d", Integer.valueOf(i));
                boolean unused = gi.this.BM = false;
                gi.this.BN.set(false);
                ApplicationMetadata unused2 = gi.this.BE = null;
                if (i != 0) {
                    gi.this.an(2);
                }
            }

            public void U(int i) {
                synchronized (gi.BX) {
                    if (gi.this.BV != null) {
                        gi.this.BV.a(new a(new Status(i)));
                        a.d unused = gi.this.BV = null;
                    }
                }
            }

            public void V(int i) {
                X(i);
            }

            public void W(int i) {
                X(i);
            }

            public void a(ApplicationMetadata applicationMetadata, String str, String str2, boolean z) {
                ApplicationMetadata unused = gi.this.BE = applicationMetadata;
                String unused2 = gi.this.BQ = applicationMetadata.getApplicationId();
                String unused3 = gi.this.BR = str2;
                synchronized (gi.BX) {
                    if (gi.this.BV != null) {
                        gi.this.BV.a(new a(new Status(0), applicationMetadata, str, str2, z));
                        a.d unused4 = gi.this.BV = null;
                    }
                }
            }

            public void a(String str, double d, boolean z) {
                gi.BD.b("Deprecated callback: \"onStatusreceived\"", new Object[0]);
            }

            public void a(String str, long j) {
                b(j, 0);
            }

            public void a(String str, long j, int i) {
                b(j, i);
            }

            public void b(final gf gfVar) {
                gi.BD.b("onApplicationStatusChanged", new Object[0]);
                gi.this.mHandler.post(new Runnable() {
                    public void run() {
                        gi.this.a(gfVar);
                    }
                });
            }

            public void b(final gk gkVar) {
                gi.BD.b("onDeviceStatusChanged", new Object[0]);
                gi.this.mHandler.post(new Runnable() {
                    public void run() {
                        gi.this.a(gkVar);
                    }
                });
            }

            public void b(String str, byte[] bArr) {
                gi.BD.b("IGNORING: Receive (type=binary, ns=%s) <%d bytes>", str, Integer.valueOf(bArr.length));
            }

            public void g(final String str, final String str2) {
                gi.BD.b("Receive (type=text, ns=%s) %s", str, str2);
                gi.this.mHandler.post(new Runnable() {
                    public void run() {
                        Cast.MessageReceivedCallback messageReceivedCallback;
                        synchronized (gi.this.BH) {
                            messageReceivedCallback = (Cast.MessageReceivedCallback) gi.this.BH.get(str);
                        }
                        if (messageReceivedCallback != null) {
                            messageReceivedCallback.onMessageReceived(gi.this.BF, str, str2);
                            return;
                        }
                        gi.BD.b("Discarded message for unknown namespace '%s'", str);
                    }
                });
            }

            public void onApplicationDisconnected(final int statusCode) {
                String unused = gi.this.BQ = null;
                String unused2 = gi.this.BR = null;
                X(statusCode);
                if (gi.this.Ab != null) {
                    gi.this.mHandler.post(new Runnable() {
                        public void run() {
                            if (gi.this.Ab != null) {
                                gi.this.Ab.onApplicationDisconnected(statusCode);
                            }
                        }
                    });
                }
            }
        };
    }

    /* access modifiers changed from: private */
    public void a(gf gfVar) {
        boolean z;
        String dX = gfVar.dX();
        if (!gj.a(dX, this.BJ)) {
            this.BJ = dX;
            z = true;
        } else {
            z = false;
        }
        BD.b("hasChanged=%b, mFirstApplicationStatusUpdate=%b", Boolean.valueOf(z), Boolean.valueOf(this.BK));
        if (this.Ab != null && (z || this.BK)) {
            this.Ab.onApplicationStatusChanged();
        }
        this.BK = false;
    }

    /* access modifiers changed from: private */
    public void a(gk gkVar) {
        boolean z;
        boolean z2;
        double ec = gkVar.ec();
        if (ec == Double.NaN || ec == this.AM) {
            z = false;
        } else {
            this.AM = ec;
            z = true;
        }
        boolean ei = gkVar.ei();
        if (ei != this.AN) {
            this.AN = ei;
            z = true;
        }
        BD.b("hasVolumeChanged=%b, mFirstDeviceStatusUpdate=%b", Boolean.valueOf(z), Boolean.valueOf(this.BL));
        if (this.Ab != null && (z || this.BL)) {
            this.Ab.onVolumeChanged();
        }
        int ej = gkVar.ej();
        if (ej != this.BO) {
            this.BO = ej;
            z2 = true;
        } else {
            z2 = false;
        }
        BD.b("hasActiveInputChanged=%b, mFirstDeviceStatusUpdate=%b", Boolean.valueOf(z2), Boolean.valueOf(this.BL));
        if (this.Ab != null && (z2 || this.BL)) {
            this.Ab.O(this.BO);
        }
        this.BL = false;
    }

    private void c(a.d<Cast.ApplicationConnectionResult> dVar) {
        synchronized (BX) {
            if (this.BV != null) {
                this.BV.a(new a(new Status(2002)));
            }
            this.BV = dVar;
        }
    }

    private void e(a.d<Status> dVar) {
        synchronized (BY) {
            if (this.BW != null) {
                dVar.a(new Status(2001));
            } else {
                this.BW = dVar;
            }
        }
    }

    /* access modifiers changed from: private */
    public void ed() {
        BD.b("removing all MessageReceivedCallbacks", new Object[0]);
        synchronized (this.BH) {
            this.BH.clear();
        }
    }

    private void ee() throws IllegalStateException {
        if (!this.BM || this.BN.get()) {
            throw new IllegalStateException("Not connected to a device");
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: G */
    public gm x(IBinder iBinder) {
        return gm.a.H(iBinder);
    }

    public void a(double d) throws IllegalArgumentException, IllegalStateException, RemoteException {
        if (Double.isInfinite(d) || Double.isNaN(d)) {
            throw new IllegalArgumentException("Volume cannot be " + d);
        }
        ((gm) fo()).a(d, this.AM, this.AN);
    }

    /* access modifiers changed from: protected */
    public void a(int i, IBinder iBinder, Bundle bundle) {
        BD.b("in onPostInitHandler; statusCode=%d", Integer.valueOf(i));
        if (i == 0 || i == 1001) {
            this.BM = true;
            this.BK = true;
            this.BL = true;
        } else {
            this.BM = false;
        }
        if (i == 1001) {
            this.BS = new Bundle();
            this.BS.putBoolean(Cast.EXTRA_APP_NO_LONGER_RUNNING, true);
            i = 0;
        }
        super.a(i, iBinder, bundle);
    }

    /* access modifiers changed from: protected */
    public void a(hj hjVar, hc.e eVar) throws RemoteException {
        Bundle bundle = new Bundle();
        BD.b("getServiceFromBroker(): mLastApplicationId=%s, mLastSessionId=%s", this.BQ, this.BR);
        this.BF.putInBundle(bundle);
        bundle.putLong("com.google.android.gms.cast.EXTRA_CAST_FLAGS", this.BI);
        if (this.BQ != null) {
            bundle.putString("last_application_id", this.BQ);
            if (this.BR != null) {
                bundle.putString("last_session_id", this.BR);
            }
        }
        hjVar.a((hi) eVar, (int) GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_VERSION_CODE, getContext().getPackageName(), this.BG.asBinder(), bundle);
    }

    public void a(String str, Cast.MessageReceivedCallback messageReceivedCallback) throws IllegalArgumentException, IllegalStateException, RemoteException {
        gj.ak(str);
        aj(str);
        if (messageReceivedCallback != null) {
            synchronized (this.BH) {
                this.BH.put(str, messageReceivedCallback);
            }
            ((gm) fo()).an(str);
        }
    }

    public void a(String str, LaunchOptions launchOptions, a.d<Cast.ApplicationConnectionResult> dVar) throws IllegalStateException, RemoteException {
        c(dVar);
        ((gm) fo()).a(str, launchOptions);
    }

    public void a(String str, a.d<Status> dVar) throws IllegalStateException, RemoteException {
        e(dVar);
        ((gm) fo()).am(str);
    }

    public void a(String str, String str2, a.d<Status> dVar) throws IllegalArgumentException, IllegalStateException, RemoteException {
        if (TextUtils.isEmpty(str2)) {
            throw new IllegalArgumentException("The message payload cannot be null or empty");
        } else if (str2.length() > 65536) {
            throw new IllegalArgumentException("Message exceeds maximum size");
        } else {
            gj.ak(str);
            ee();
            long incrementAndGet = this.BP.incrementAndGet();
            ((gm) fo()).a(str, str2, incrementAndGet);
            this.BT.put(Long.valueOf(incrementAndGet), dVar);
        }
    }

    public void a(String str, boolean z, a.d<Cast.ApplicationConnectionResult> dVar) throws IllegalStateException, RemoteException {
        c(dVar);
        ((gm) fo()).e(str, z);
    }

    public void aj(String str) throws IllegalArgumentException, RemoteException {
        Cast.MessageReceivedCallback remove;
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("Channel namespace cannot be null or empty");
        }
        synchronized (this.BH) {
            remove = this.BH.remove(str);
        }
        if (remove != null) {
            try {
                ((gm) fo()).ao(str);
            } catch (IllegalStateException e) {
                BD.a(e, "Error unregistering namespace (%s): %s", str, e.getMessage());
            }
        }
    }

    public void b(String str, String str2, a.d<Cast.ApplicationConnectionResult> dVar) throws IllegalStateException, RemoteException {
        c(dVar);
        ((gm) fo()).h(str, str2);
    }

    /* access modifiers changed from: protected */
    public String bp() {
        return "com.google.android.gms.cast.service.BIND_CAST_DEVICE_CONTROLLER_SERVICE";
    }

    /* access modifiers changed from: protected */
    public String bq() {
        return "com.google.android.gms.cast.internal.ICastDeviceController";
    }

    public void d(a.d<Status> dVar) throws IllegalStateException, RemoteException {
        e(dVar);
        ((gm) fo()).ek();
    }

    public void disconnect() {
        BD.b("disconnect(); mDisconnecting=%b, isConnected=%b", Boolean.valueOf(this.BN.get()), Boolean.valueOf(isConnected()));
        if (this.BN.getAndSet(true)) {
            BD.b("mDisconnecting is set, so short-circuiting", new Object[0]);
            return;
        }
        ed();
        try {
            if (isConnected() || isConnecting()) {
                ((gm) fo()).disconnect();
            }
        } catch (RemoteException e) {
            BD.a(e, "Error while disconnecting the controller interface: %s", e.getMessage());
        } finally {
            super.disconnect();
        }
    }

    public Bundle ea() {
        if (this.BS == null) {
            return super.ea();
        }
        Bundle bundle = this.BS;
        this.BS = null;
        return bundle;
    }

    public void eb() throws IllegalStateException, RemoteException {
        ((gm) fo()).eb();
    }

    public double ec() throws IllegalStateException {
        ee();
        return this.AM;
    }

    public ApplicationMetadata getApplicationMetadata() throws IllegalStateException {
        ee();
        return this.BE;
    }

    public String getApplicationStatus() throws IllegalStateException {
        ee();
        return this.BJ;
    }

    public boolean isMute() throws IllegalStateException {
        ee();
        return this.AN;
    }

    public void y(boolean z) throws IllegalStateException, RemoteException {
        ((gm) fo()).a(z, this.AM, this.AN);
    }
}
