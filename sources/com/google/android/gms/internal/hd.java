package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.common.api.GoogleApiClient;
import java.util.ArrayList;
import java.util.Iterator;

public final class hd {
    /* access modifiers changed from: private */
    public final b Gm;
    /* access modifiers changed from: private */
    public final ArrayList<GoogleApiClient.ConnectionCallbacks> Gn = new ArrayList<>();
    final ArrayList<GoogleApiClient.ConnectionCallbacks> Go = new ArrayList<>();
    private boolean Gp = false;
    private final ArrayList<GooglePlayServicesClient.OnConnectionFailedListener> Gq = new ArrayList<>();
    private final Handler mHandler;

    final class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                synchronized (hd.this.Gn) {
                    if (hd.this.Gm.eJ() && hd.this.Gm.isConnected() && hd.this.Gn.contains(msg.obj)) {
                        ((GoogleApiClient.ConnectionCallbacks) msg.obj).onConnected(hd.this.Gm.ea());
                    }
                }
                return;
            }
            Log.wtf("GmsClientEvents", "Don't know how to handle this message.");
        }
    }

    public interface b {
        boolean eJ();

        Bundle ea();

        boolean isConnected();
    }

    public hd(Context context, Looper looper, b bVar) {
        this.Gm = bVar;
        this.mHandler = new a(looper);
    }

    public void a(ConnectionResult connectionResult) {
        this.mHandler.removeMessages(1);
        synchronized (this.Gq) {
            Iterator it = new ArrayList(this.Gq).iterator();
            while (it.hasNext()) {
                GooglePlayServicesClient.OnConnectionFailedListener onConnectionFailedListener = (GooglePlayServicesClient.OnConnectionFailedListener) it.next();
                if (this.Gm.eJ()) {
                    if (this.Gq.contains(onConnectionFailedListener)) {
                        onConnectionFailedListener.onConnectionFailed(connectionResult);
                    }
                } else {
                    return;
                }
            }
        }
    }

    public void ao(int i) {
        this.mHandler.removeMessages(1);
        synchronized (this.Gn) {
            this.Gp = true;
            Iterator it = new ArrayList(this.Gn).iterator();
            while (it.hasNext()) {
                GoogleApiClient.ConnectionCallbacks connectionCallbacks = (GoogleApiClient.ConnectionCallbacks) it.next();
                if (!this.Gm.eJ()) {
                    break;
                } else if (this.Gn.contains(connectionCallbacks)) {
                    connectionCallbacks.onConnectionSuspended(i);
                }
            }
            this.Gp = false;
        }
    }

    public void c(Bundle bundle) {
        boolean z = true;
        synchronized (this.Gn) {
            hn.A(!this.Gp);
            this.mHandler.removeMessages(1);
            this.Gp = true;
            if (this.Go.size() != 0) {
                z = false;
            }
            hn.A(z);
            Iterator it = new ArrayList(this.Gn).iterator();
            while (it.hasNext()) {
                GoogleApiClient.ConnectionCallbacks connectionCallbacks = (GoogleApiClient.ConnectionCallbacks) it.next();
                if (!this.Gm.eJ() || !this.Gm.isConnected()) {
                    break;
                } else if (!this.Go.contains(connectionCallbacks)) {
                    connectionCallbacks.onConnected(bundle);
                }
            }
            this.Go.clear();
            this.Gp = false;
        }
    }

    /* access modifiers changed from: protected */
    public void ck() {
        synchronized (this.Gn) {
            c(this.Gm.ea());
        }
    }

    public boolean isConnectionCallbacksRegistered(GoogleApiClient.ConnectionCallbacks listener) {
        boolean contains;
        hn.f(listener);
        synchronized (this.Gn) {
            contains = this.Gn.contains(listener);
        }
        return contains;
    }

    public boolean isConnectionFailedListenerRegistered(GooglePlayServicesClient.OnConnectionFailedListener listener) {
        boolean contains;
        hn.f(listener);
        synchronized (this.Gq) {
            contains = this.Gq.contains(listener);
        }
        return contains;
    }

    public void registerConnectionCallbacks(GoogleApiClient.ConnectionCallbacks listener) {
        hn.f(listener);
        synchronized (this.Gn) {
            if (this.Gn.contains(listener)) {
                Log.w("GmsClientEvents", "registerConnectionCallbacks(): listener " + listener + " is already registered");
            } else {
                this.Gn.add(listener);
            }
        }
        if (this.Gm.isConnected()) {
            this.mHandler.sendMessage(this.mHandler.obtainMessage(1, listener));
        }
    }

    public void registerConnectionFailedListener(GooglePlayServicesClient.OnConnectionFailedListener listener) {
        hn.f(listener);
        synchronized (this.Gq) {
            if (this.Gq.contains(listener)) {
                Log.w("GmsClientEvents", "registerConnectionFailedListener(): listener " + listener + " is already registered");
            } else {
                this.Gq.add(listener);
            }
        }
    }

    public void unregisterConnectionCallbacks(GoogleApiClient.ConnectionCallbacks listener) {
        hn.f(listener);
        synchronized (this.Gn) {
            if (this.Gn != null) {
                if (!this.Gn.remove(listener)) {
                    Log.w("GmsClientEvents", "unregisterConnectionCallbacks(): listener " + listener + " not found");
                } else if (this.Gp) {
                    this.Go.add(listener);
                }
            }
        }
    }

    public void unregisterConnectionFailedListener(GooglePlayServicesClient.OnConnectionFailedListener listener) {
        hn.f(listener);
        synchronized (this.Gq) {
            if (this.Gq != null && !this.Gq.remove(listener)) {
                Log.w("GmsClientEvents", "unregisterConnectionFailedListener(): listener " + listener + " not found");
            }
        }
    }
}
