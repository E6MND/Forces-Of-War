package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.internal.hd;
import com.google.android.gms.internal.hi;
import com.google.android.gms.internal.hj;
import java.util.ArrayList;

public abstract class hc<T extends IInterface> implements Api.a, hd.b {
    public static final String[] Ge = {"service_esmobile", "service_googleme"};
    private final Looper DC;
    /* access modifiers changed from: private */
    public final hd DP;
    /* access modifiers changed from: private */
    public T FY;
    /* access modifiers changed from: private */
    public final ArrayList<hc<T>.b<?>> FZ;
    /* access modifiers changed from: private */
    public hc<T>.f Ga;
    private volatile int Gb;
    private final String[] Gc;
    boolean Gd;
    /* access modifiers changed from: private */
    public final Context mContext;
    final Handler mHandler;

    final class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message msg) {
            if (msg.what == 1 && !hc.this.isConnecting()) {
                b bVar = (b) msg.obj;
                bVar.fp();
                bVar.unregister();
            } else if (msg.what == 3) {
                hc.this.DP.a(new ConnectionResult(((Integer) msg.obj).intValue(), (PendingIntent) null));
            } else if (msg.what == 4) {
                hc.this.am(1);
                IInterface unused = hc.this.FY = null;
                hc.this.DP.ao(((Integer) msg.obj).intValue());
            } else if (msg.what == 2 && !hc.this.isConnected()) {
                b bVar2 = (b) msg.obj;
                bVar2.fp();
                bVar2.unregister();
            } else if (msg.what == 2 || msg.what == 1) {
                ((b) msg.obj).fq();
            } else {
                Log.wtf("GmsClient", "Don't know how to handle this message.");
            }
        }
    }

    protected abstract class b<TListener> {
        private boolean Gg = false;
        private TListener mListener;

        public b(TListener tlistener) {
            this.mListener = tlistener;
        }

        /* access modifiers changed from: protected */
        public abstract void d(TListener tlistener);

        /* access modifiers changed from: protected */
        public abstract void fp();

        public void fq() {
            TListener tlistener;
            synchronized (this) {
                tlistener = this.mListener;
                if (this.Gg) {
                    Log.w("GmsClient", "Callback proxy " + this + " being reused. This is not safe.");
                }
            }
            if (tlistener != null) {
                try {
                    d(tlistener);
                } catch (RuntimeException e) {
                    fp();
                    throw e;
                }
            } else {
                fp();
            }
            synchronized (this) {
                this.Gg = true;
            }
            unregister();
        }

        public void fr() {
            synchronized (this) {
                this.mListener = null;
            }
        }

        public void unregister() {
            fr();
            synchronized (hc.this.FZ) {
                hc.this.FZ.remove(this);
            }
        }
    }

    public static final class c implements GoogleApiClient.ConnectionCallbacks {
        private final GooglePlayServicesClient.ConnectionCallbacks Gh;

        public c(GooglePlayServicesClient.ConnectionCallbacks connectionCallbacks) {
            this.Gh = connectionCallbacks;
        }

        public boolean equals(Object other) {
            return other instanceof c ? this.Gh.equals(((c) other).Gh) : this.Gh.equals(other);
        }

        public void onConnected(Bundle connectionHint) {
            this.Gh.onConnected(connectionHint);
        }

        public void onConnectionSuspended(int cause) {
            this.Gh.onDisconnected();
        }
    }

    public abstract class d<TListener> extends hc<T>.b<TListener> {
        private final DataHolder DD;

        public d(TListener tlistener, DataHolder dataHolder) {
            super(tlistener);
            this.DD = dataHolder;
        }

        /* access modifiers changed from: protected */
        public abstract void a(TListener tlistener, DataHolder dataHolder);

        /* access modifiers changed from: protected */
        public final void d(TListener tlistener) {
            a(tlistener, this.DD);
        }

        /* access modifiers changed from: protected */
        public void fp() {
            if (this.DD != null) {
                this.DD.close();
            }
        }

        public /* bridge */ /* synthetic */ void fq() {
            super.fq();
        }

        public /* bridge */ /* synthetic */ void fr() {
            super.fr();
        }

        public /* bridge */ /* synthetic */ void unregister() {
            super.unregister();
        }
    }

    public static final class e extends hi.a {
        private hc Gi;

        public e(hc hcVar) {
            this.Gi = hcVar;
        }

        public void b(int i, IBinder iBinder, Bundle bundle) {
            hn.b("onPostInitComplete can be called only once per call to getServiceFromBroker", (Object) this.Gi);
            this.Gi.a(i, iBinder, bundle);
            this.Gi = null;
        }
    }

    final class f implements ServiceConnection {
        f() {
        }

        public void onServiceConnected(ComponentName component, IBinder binder) {
            hc.this.I(binder);
        }

        public void onServiceDisconnected(ComponentName component) {
            hc.this.mHandler.sendMessage(hc.this.mHandler.obtainMessage(4, 1));
        }
    }

    public static final class g implements GoogleApiClient.OnConnectionFailedListener {
        private final GooglePlayServicesClient.OnConnectionFailedListener Gj;

        public g(GooglePlayServicesClient.OnConnectionFailedListener onConnectionFailedListener) {
            this.Gj = onConnectionFailedListener;
        }

        public boolean equals(Object other) {
            return other instanceof g ? this.Gj.equals(((g) other).Gj) : this.Gj.equals(other);
        }

        public void onConnectionFailed(ConnectionResult result) {
            this.Gj.onConnectionFailed(result);
        }
    }

    protected final class h extends hc<T>.b<Boolean> {
        public final Bundle Gk;
        public final IBinder Gl;
        public final int statusCode;

        public h(int i, IBinder iBinder, Bundle bundle) {
            super(true);
            this.statusCode = i;
            this.Gl = iBinder;
            this.Gk = bundle;
        }

        /* access modifiers changed from: protected */
        /* renamed from: b */
        public void d(Boolean bool) {
            if (bool == null) {
                hc.this.am(1);
                return;
            }
            switch (this.statusCode) {
                case 0:
                    try {
                        if (hc.this.bq().equals(this.Gl.getInterfaceDescriptor())) {
                            IInterface unused = hc.this.FY = hc.this.x(this.Gl);
                            if (hc.this.FY != null) {
                                hc.this.am(3);
                                hc.this.DP.ck();
                                return;
                            }
                        }
                    } catch (RemoteException e) {
                    }
                    he.B(hc.this.mContext).b(hc.this.bp(), hc.this.Ga);
                    f unused2 = hc.this.Ga = null;
                    hc.this.am(1);
                    IInterface unused3 = hc.this.FY = null;
                    hc.this.DP.a(new ConnectionResult(8, (PendingIntent) null));
                    return;
                case 10:
                    hc.this.am(1);
                    throw new IllegalStateException("A fatal developer error has occurred. Check the logs for further information.");
                default:
                    PendingIntent pendingIntent = this.Gk != null ? (PendingIntent) this.Gk.getParcelable("pendingIntent") : null;
                    if (hc.this.Ga != null) {
                        he.B(hc.this.mContext).b(hc.this.bp(), hc.this.Ga);
                        f unused4 = hc.this.Ga = null;
                    }
                    hc.this.am(1);
                    IInterface unused5 = hc.this.FY = null;
                    hc.this.DP.a(new ConnectionResult(this.statusCode, pendingIntent));
                    return;
            }
        }

        /* access modifiers changed from: protected */
        public void fp() {
        }
    }

    protected hc(Context context, Looper looper, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener, String... strArr) {
        this.FZ = new ArrayList<>();
        this.Gb = 1;
        this.Gd = false;
        this.mContext = (Context) hn.f(context);
        this.DC = (Looper) hn.b(looper, (Object) "Looper must not be null");
        this.DP = new hd(context, looper, this);
        this.mHandler = new a(looper);
        b(strArr);
        this.Gc = strArr;
        registerConnectionCallbacks((GoogleApiClient.ConnectionCallbacks) hn.f(connectionCallbacks));
        registerConnectionFailedListener((GoogleApiClient.OnConnectionFailedListener) hn.f(onConnectionFailedListener));
    }

    @Deprecated
    protected hc(Context context, GooglePlayServicesClient.ConnectionCallbacks connectionCallbacks, GooglePlayServicesClient.OnConnectionFailedListener onConnectionFailedListener, String... strArr) {
        this(context, context.getMainLooper(), new c(connectionCallbacks), new g(onConnectionFailedListener), strArr);
    }

    /* access modifiers changed from: private */
    public void am(int i) {
        int i2 = this.Gb;
        this.Gb = i;
        if (i2 == i) {
            return;
        }
        if (i == 3) {
            onConnected();
        } else if (i2 == 3 && i == 1) {
            onDisconnected();
        }
    }

    /* access modifiers changed from: protected */
    public final void I(IBinder iBinder) {
        try {
            a(hj.a.L(iBinder), new e(this));
        } catch (RemoteException e2) {
            Log.w("GmsClient", "service died");
        }
    }

    /* access modifiers changed from: protected */
    public void a(int i, IBinder iBinder, Bundle bundle) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(1, new h(i, iBinder, bundle)));
    }

    @Deprecated
    public final void a(hc<T>.b<?> bVar) {
        synchronized (this.FZ) {
            this.FZ.add(bVar);
        }
        this.mHandler.sendMessage(this.mHandler.obtainMessage(2, bVar));
    }

    /* access modifiers changed from: protected */
    public abstract void a(hj hjVar, e eVar) throws RemoteException;

    public void an(int i) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(4, Integer.valueOf(i)));
    }

    /* access modifiers changed from: protected */
    public void b(String... strArr) {
    }

    /* access modifiers changed from: protected */
    public abstract String bp();

    /* access modifiers changed from: protected */
    public abstract String bq();

    /* access modifiers changed from: protected */
    public final void ci() {
        if (!isConnected()) {
            throw new IllegalStateException("Not connected. Call connect() and wait for onConnected() to be called.");
        }
    }

    public void connect() {
        this.Gd = true;
        am(2);
        int isGooglePlayServicesAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this.mContext);
        if (isGooglePlayServicesAvailable != 0) {
            am(1);
            this.mHandler.sendMessage(this.mHandler.obtainMessage(3, Integer.valueOf(isGooglePlayServicesAvailable)));
            return;
        }
        if (this.Ga != null) {
            Log.e("GmsClient", "Calling connect() while still connected, missing disconnect().");
            this.FY = null;
            he.B(this.mContext).b(bp(), this.Ga);
        }
        this.Ga = new f();
        if (!he.B(this.mContext).a(bp(), this.Ga)) {
            Log.e("GmsClient", "unable to connect to service: " + bp());
            this.mHandler.sendMessage(this.mHandler.obtainMessage(3, 9));
        }
    }

    public void disconnect() {
        this.Gd = false;
        synchronized (this.FZ) {
            int size = this.FZ.size();
            for (int i = 0; i < size; i++) {
                this.FZ.get(i).fr();
            }
            this.FZ.clear();
        }
        am(1);
        this.FY = null;
        if (this.Ga != null) {
            he.B(this.mContext).b(bp(), this.Ga);
            this.Ga = null;
        }
    }

    public boolean eJ() {
        return this.Gd;
    }

    public Bundle ea() {
        return null;
    }

    public final String[] fn() {
        return this.Gc;
    }

    public final T fo() {
        ci();
        return this.FY;
    }

    public final Context getContext() {
        return this.mContext;
    }

    public final Looper getLooper() {
        return this.DC;
    }

    public boolean isConnected() {
        return this.Gb == 3;
    }

    public boolean isConnecting() {
        return this.Gb == 2;
    }

    @Deprecated
    public boolean isConnectionCallbacksRegistered(GooglePlayServicesClient.ConnectionCallbacks listener) {
        return this.DP.isConnectionCallbacksRegistered(new c(listener));
    }

    @Deprecated
    public boolean isConnectionFailedListenerRegistered(GooglePlayServicesClient.OnConnectionFailedListener listener) {
        return this.DP.isConnectionFailedListenerRegistered(listener);
    }

    /* access modifiers changed from: protected */
    public void onConnected() {
    }

    /* access modifiers changed from: protected */
    public void onDisconnected() {
    }

    @Deprecated
    public void registerConnectionCallbacks(GooglePlayServicesClient.ConnectionCallbacks listener) {
        this.DP.registerConnectionCallbacks(new c(listener));
    }

    public void registerConnectionCallbacks(GoogleApiClient.ConnectionCallbacks listener) {
        this.DP.registerConnectionCallbacks(listener);
    }

    @Deprecated
    public void registerConnectionFailedListener(GooglePlayServicesClient.OnConnectionFailedListener listener) {
        this.DP.registerConnectionFailedListener(listener);
    }

    public void registerConnectionFailedListener(GoogleApiClient.OnConnectionFailedListener listener) {
        this.DP.registerConnectionFailedListener(listener);
    }

    @Deprecated
    public void unregisterConnectionCallbacks(GooglePlayServicesClient.ConnectionCallbacks listener) {
        this.DP.unregisterConnectionCallbacks(new c(listener));
    }

    @Deprecated
    public void unregisterConnectionFailedListener(GooglePlayServicesClient.OnConnectionFailedListener listener) {
        this.DP.unregisterConnectionFailedListener(listener);
    }

    /* access modifiers changed from: protected */
    public abstract T x(IBinder iBinder);
}
