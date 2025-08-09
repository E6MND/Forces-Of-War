package com.google.android.gms.analytics;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.internal.fe;
import com.google.android.gms.internal.ff;
import java.util.List;
import java.util.Map;

class c implements b {
    /* access modifiers changed from: private */
    public Context mContext;
    /* access modifiers changed from: private */
    public ServiceConnection tu;
    /* access modifiers changed from: private */
    public b tv;
    /* access modifiers changed from: private */
    public C0009c tw;
    /* access modifiers changed from: private */
    public ff tx;

    final class a implements ServiceConnection {
        a() {
        }

        public void onServiceConnected(ComponentName component, IBinder binder) {
            aa.C("service connected, binder: " + binder);
            try {
                if ("com.google.android.gms.analytics.internal.IAnalyticsService".equals(binder.getInterfaceDescriptor())) {
                    aa.C("bound to service");
                    ff unused = c.this.tx = ff.a.z(binder);
                    c.this.cj();
                    return;
                }
            } catch (RemoteException e) {
            }
            c.this.mContext.unbindService(this);
            ServiceConnection unused2 = c.this.tu = null;
            c.this.tw.a(2, (Intent) null);
        }

        public void onServiceDisconnected(ComponentName component) {
            aa.C("service disconnected: " + component);
            ServiceConnection unused = c.this.tu = null;
            c.this.tv.onDisconnected();
        }
    }

    public interface b {
        void onConnected();

        void onDisconnected();
    }

    /* renamed from: com.google.android.gms.analytics.c$c  reason: collision with other inner class name */
    public interface C0009c {
        void a(int i, Intent intent);
    }

    public c(Context context, b bVar, C0009c cVar) {
        this.mContext = context;
        if (bVar == null) {
            throw new IllegalArgumentException("onConnectedListener cannot be null");
        }
        this.tv = bVar;
        if (cVar == null) {
            throw new IllegalArgumentException("onConnectionFailedListener cannot be null");
        }
        this.tw = cVar;
    }

    private ff ch() {
        ci();
        return this.tx;
    }

    /* access modifiers changed from: private */
    public void cj() {
        ck();
    }

    private void ck() {
        this.tv.onConnected();
    }

    public void a(Map<String, String> map, long j, String str, List<fe> list) {
        try {
            ch().a(map, j, str, list);
        } catch (RemoteException e) {
            aa.A("sendHit failed: " + e);
        }
    }

    public void cg() {
        try {
            ch().cg();
        } catch (RemoteException e) {
            aa.A("clear hits failed: " + e);
        }
    }

    /* access modifiers changed from: protected */
    public void ci() {
        if (!isConnected()) {
            throw new IllegalStateException("Not connected. Call connect() and wait for onConnected() to be called.");
        }
    }

    public void connect() {
        Intent intent = new Intent("com.google.android.gms.analytics.service.START");
        intent.setComponent(new ComponentName(GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_PACKAGE, "com.google.android.gms.analytics.service.AnalyticsService"));
        intent.putExtra("app_package_name", this.mContext.getPackageName());
        if (this.tu != null) {
            aa.A("Calling connect() while still connected, missing disconnect().");
            return;
        }
        this.tu = new a();
        boolean bindService = this.mContext.bindService(intent, this.tu, 129);
        aa.C("connect: bindService returned " + bindService + " for " + intent);
        if (!bindService) {
            this.tu = null;
            this.tw.a(1, (Intent) null);
        }
    }

    public void disconnect() {
        this.tx = null;
        if (this.tu != null) {
            try {
                this.mContext.unbindService(this.tu);
            } catch (IllegalArgumentException | IllegalStateException e) {
            }
            this.tu = null;
            this.tv.onDisconnected();
        }
    }

    public boolean isConnected() {
        return this.tx != null;
    }
}
