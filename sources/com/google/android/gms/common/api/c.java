package com.google.android.gms.common.api;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.a;
import com.google.android.gms.internal.gz;
import com.google.android.gms.internal.hd;
import com.google.android.gms.internal.hn;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

final class c implements GoogleApiClient {
    private final Looper DC;
    /* access modifiers changed from: private */
    public final Lock DN = new ReentrantLock();
    private final Condition DO = this.DN.newCondition();
    private final hd DP;
    private final Fragment DQ;
    final Queue<C0012c<?>> DR = new LinkedList();
    /* access modifiers changed from: private */
    public ConnectionResult DS;
    /* access modifiers changed from: private */
    public int DT;
    /* access modifiers changed from: private */
    public int DU = 4;
    /* access modifiers changed from: private */
    public int DV = 0;
    private boolean DW = false;
    private int DX;
    /* access modifiers changed from: private */
    public long DY = 5000;
    final Handler DZ;
    private final a Dv = new a() {
        public void b(C0012c<?> cVar) {
            c.this.Ee.remove(cVar);
        }
    };
    /* access modifiers changed from: private */
    public final Bundle Ea = new Bundle();
    private final Map<Api.c<?>, Api.a> Eb = new HashMap();
    private final List<String> Ec;
    /* access modifiers changed from: private */
    public boolean Ed;
    final Set<C0012c<?>> Ee = Collections.newSetFromMap(new ConcurrentHashMap());
    final GoogleApiClient.ConnectionCallbacks Ef = new GoogleApiClient.ConnectionCallbacks() {
        public void onConnected(Bundle connectionHint) {
            c.this.DN.lock();
            try {
                if (c.this.DU == 1) {
                    if (connectionHint != null) {
                        c.this.Ea.putAll(connectionHint);
                    }
                    c.this.eF();
                }
            } finally {
                c.this.DN.unlock();
            }
        }

        public void onConnectionSuspended(int cause) {
            c.this.DN.lock();
            try {
                c.this.aa(cause);
                switch (cause) {
                    case 1:
                        if (!c.this.eH()) {
                            int unused = c.this.DV = 2;
                            c.this.DZ.sendMessageDelayed(c.this.DZ.obtainMessage(1), c.this.DY);
                            break;
                        } else {
                            c.this.DN.unlock();
                            return;
                        }
                    case 2:
                        c.this.connect();
                        break;
                }
            } finally {
                c.this.DN.unlock();
            }
        }
    };
    private final hd.b Eg = new hd.b() {
        public boolean eJ() {
            return c.this.Ed;
        }

        public Bundle ea() {
            return null;
        }

        public boolean isConnected() {
            return c.this.isConnected();
        }
    };

    interface a {
        void b(C0012c<?> cVar);
    }

    class b extends Handler {
        b(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                c.this.DN.lock();
                try {
                    if (!c.this.isConnected() && !c.this.isConnecting()) {
                        c.this.connect();
                    }
                } finally {
                    c.this.DN.unlock();
                }
            } else {
                Log.wtf("GoogleApiClientImpl", "Don't know how to handle this message.");
            }
        }
    }

    /* renamed from: com.google.android.gms.common.api.c$c  reason: collision with other inner class name */
    interface C0012c<A extends Api.a> {
        void a(a aVar);

        void b(A a) throws DeadObjectException;

        void cancel();

        int eB();

        Api.c<A> ew();

        void m(Status status);
    }

    public c(Context context, Looper looper, gz gzVar, Map<Api<?>, Api.ApiOptions> map, Fragment fragment, Set<GoogleApiClient.ConnectionCallbacks> set, Set<GoogleApiClient.OnConnectionFailedListener> set2) {
        this.DP = new hd(context, looper, this.Eg);
        this.DQ = fragment;
        this.DC = looper;
        this.DZ = new b(looper);
        for (GoogleApiClient.ConnectionCallbacks registerConnectionCallbacks : set) {
            this.DP.registerConnectionCallbacks(registerConnectionCallbacks);
        }
        for (GoogleApiClient.OnConnectionFailedListener registerConnectionFailedListener : set2) {
            this.DP.registerConnectionFailedListener(registerConnectionFailedListener);
        }
        for (Api next : map.keySet()) {
            final Api.b eu = next.eu();
            Api.ApiOptions apiOptions = map.get(next);
            this.Eb.put(next.ew(), a(eu, apiOptions, context, looper, gzVar, this.Ef, new GoogleApiClient.OnConnectionFailedListener() {
                public void onConnectionFailed(ConnectionResult result) {
                    c.this.DN.lock();
                    try {
                        if (c.this.DS == null || eu.getPriority() < c.this.DT) {
                            ConnectionResult unused = c.this.DS = result;
                            int unused2 = c.this.DT = eu.getPriority();
                        }
                        c.this.eF();
                    } finally {
                        c.this.DN.unlock();
                    }
                }
            }));
        }
        this.Ec = Collections.unmodifiableList(gzVar.fg());
    }

    private static <C extends Api.a, O> C a(Api.b<C, O> bVar, Object obj, Context context, Looper looper, gz gzVar, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        return bVar.a(context, looper, gzVar, obj, connectionCallbacks, onConnectionFailedListener);
    }

    private <A extends Api.a> void a(C0012c<A> cVar) throws DeadObjectException {
        boolean z = true;
        this.DN.lock();
        try {
            hn.a(isConnected() || eH(), "GoogleApiClient is not connected yet.");
            if (cVar.ew() == null) {
                z = false;
            }
            hn.b(z, (Object) "This task can not be executed or enqueued (it's probably a Batch or malformed)");
            this.Ee.add(cVar);
            cVar.a(this.Dv);
            if (eH()) {
                cVar.m(new Status(8));
                return;
            }
            cVar.b(a(cVar.ew()));
            this.DN.unlock();
        } finally {
            this.DN.unlock();
        }
    }

    /* access modifiers changed from: private */
    public void aa(int i) {
        this.DN.lock();
        try {
            if (this.DU != 3) {
                if (i == -1) {
                    if (isConnecting()) {
                        Iterator it = this.DR.iterator();
                        while (it.hasNext()) {
                            C0012c cVar = (C0012c) it.next();
                            if (cVar.eB() != 1) {
                                cVar.cancel();
                                it.remove();
                            }
                        }
                    } else {
                        this.DR.clear();
                    }
                    for (C0012c<?> cancel : this.Ee) {
                        cancel.cancel();
                    }
                    this.Ee.clear();
                    if (this.DS == null && !this.DR.isEmpty()) {
                        this.DW = true;
                        return;
                    }
                }
                boolean isConnecting = isConnecting();
                boolean isConnected = isConnected();
                this.DU = 3;
                if (isConnecting) {
                    if (i == -1) {
                        this.DS = null;
                    }
                    this.DO.signalAll();
                }
                this.Ed = false;
                for (Api.a next : this.Eb.values()) {
                    if (next.isConnected()) {
                        next.disconnect();
                    }
                }
                this.Ed = true;
                this.DU = 4;
                if (isConnected) {
                    if (i != -1) {
                        this.DP.ao(i);
                    }
                    this.Ed = false;
                }
            }
            this.DN.unlock();
        } finally {
            this.DN.unlock();
        }
    }

    /* access modifiers changed from: private */
    public void eF() {
        this.DN.lock();
        try {
            this.DX--;
            if (this.DX == 0) {
                if (this.DS != null) {
                    this.DW = false;
                    aa(3);
                    if (eH()) {
                        this.DV--;
                    }
                    if (eH()) {
                        this.DZ.sendMessageDelayed(this.DZ.obtainMessage(1), this.DY);
                    } else {
                        this.DP.a(this.DS);
                    }
                    this.Ed = false;
                } else {
                    this.DU = 2;
                    eI();
                    this.DO.signalAll();
                    eG();
                    if (this.DW) {
                        this.DW = false;
                        aa(-1);
                    } else {
                        this.DP.c(this.Ea.isEmpty() ? null : this.Ea);
                    }
                }
            }
        } finally {
            this.DN.unlock();
        }
    }

    private void eG() {
        hn.a(isConnected() || eH(), "GoogleApiClient is not connected yet.");
        this.DN.lock();
        while (!this.DR.isEmpty()) {
            try {
                a(this.DR.remove());
            } catch (DeadObjectException e) {
                Log.w("GoogleApiClientImpl", "Service died while flushing queue", e);
            } catch (Throwable th) {
                this.DN.unlock();
                throw th;
            }
        }
        this.DN.unlock();
    }

    /* access modifiers changed from: private */
    public boolean eH() {
        this.DN.lock();
        try {
            return this.DV != 0;
        } finally {
            this.DN.unlock();
        }
    }

    private void eI() {
        this.DN.lock();
        try {
            this.DV = 0;
            this.DZ.removeMessages(1);
        } finally {
            this.DN.unlock();
        }
    }

    public <C extends Api.a> C a(Api.c<C> cVar) {
        C c = (Api.a) this.Eb.get(cVar);
        hn.b(c, (Object) "Appropriate Api was not requested.");
        return c;
    }

    public <A extends Api.a, T extends a.b<? extends Result, A>> T a(T t) {
        this.DN.lock();
        try {
            if (isConnected()) {
                b(t);
            } else {
                this.DR.add(t);
            }
            return t;
        } finally {
            this.DN.unlock();
        }
    }

    public <A extends Api.a, T extends a.b<? extends Result, A>> T b(T t) {
        hn.a(isConnected() || eH(), "GoogleApiClient is not connected yet.");
        eG();
        try {
            a(t);
        } catch (DeadObjectException e) {
            aa(1);
        }
        return t;
    }

    public ConnectionResult blockingConnect() {
        ConnectionResult connectionResult;
        hn.a(Looper.myLooper() != Looper.getMainLooper(), "blockingConnect must not be called on the UI thread");
        this.DN.lock();
        try {
            connect();
            while (isConnecting()) {
                this.DO.await();
            }
            if (isConnected()) {
                connectionResult = ConnectionResult.CP;
            } else if (this.DS != null) {
                connectionResult = this.DS;
                this.DN.unlock();
            } else {
                connectionResult = new ConnectionResult(13, (PendingIntent) null);
                this.DN.unlock();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            connectionResult = new ConnectionResult(15, (PendingIntent) null);
        } finally {
            this.DN.unlock();
        }
        return connectionResult;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0058, code lost:
        if (isConnected() == false) goto L_0x0062;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x005a, code lost:
        r0 = com.google.android.gms.common.ConnectionResult.CP;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0064, code lost:
        if (r5.DS == null) goto L_0x006e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0066, code lost:
        r0 = r5.DS;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0068, code lost:
        r5.DN.unlock();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
        r0 = new com.google.android.gms.common.ConnectionResult(13, (android.app.PendingIntent) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0076, code lost:
        r5.DN.unlock();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.android.gms.common.ConnectionResult blockingConnect(long r6, java.util.concurrent.TimeUnit r8) {
        /*
            r5 = this;
            android.os.Looper r0 = android.os.Looper.myLooper()
            android.os.Looper r1 = android.os.Looper.getMainLooper()
            if (r0 == r1) goto L_0x003c
            r0 = 1
        L_0x000b:
            java.lang.String r1 = "blockingConnect must not be called on the UI thread"
            com.google.android.gms.internal.hn.a(r0, r1)
            java.util.concurrent.locks.Lock r0 = r5.DN
            r0.lock()
            r5.connect()     // Catch:{ all -> 0x007c }
            long r0 = r8.toNanos(r6)     // Catch:{ all -> 0x007c }
        L_0x001c:
            boolean r2 = r5.isConnecting()     // Catch:{ all -> 0x007c }
            if (r2 == 0) goto L_0x0054
            java.util.concurrent.locks.Condition r2 = r5.DO     // Catch:{ InterruptedException -> 0x003e }
            long r0 = r2.awaitNanos(r0)     // Catch:{ InterruptedException -> 0x003e }
            r2 = 0
            int r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r2 > 0) goto L_0x001c
            com.google.android.gms.common.ConnectionResult r0 = new com.google.android.gms.common.ConnectionResult     // Catch:{ InterruptedException -> 0x003e }
            r1 = 14
            r2 = 0
            r0.<init>(r1, r2)     // Catch:{ InterruptedException -> 0x003e }
            java.util.concurrent.locks.Lock r1 = r5.DN
            r1.unlock()
        L_0x003b:
            return r0
        L_0x003c:
            r0 = 0
            goto L_0x000b
        L_0x003e:
            r0 = move-exception
            java.lang.Thread r0 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x007c }
            r0.interrupt()     // Catch:{ all -> 0x007c }
            com.google.android.gms.common.ConnectionResult r0 = new com.google.android.gms.common.ConnectionResult     // Catch:{ all -> 0x007c }
            r1 = 15
            r2 = 0
            r0.<init>(r1, r2)     // Catch:{ all -> 0x007c }
            java.util.concurrent.locks.Lock r1 = r5.DN
            r1.unlock()
            goto L_0x003b
        L_0x0054:
            boolean r0 = r5.isConnected()     // Catch:{ all -> 0x007c }
            if (r0 == 0) goto L_0x0062
            com.google.android.gms.common.ConnectionResult r0 = com.google.android.gms.common.ConnectionResult.CP     // Catch:{ all -> 0x007c }
            java.util.concurrent.locks.Lock r1 = r5.DN
            r1.unlock()
            goto L_0x003b
        L_0x0062:
            com.google.android.gms.common.ConnectionResult r0 = r5.DS     // Catch:{ all -> 0x007c }
            if (r0 == 0) goto L_0x006e
            com.google.android.gms.common.ConnectionResult r0 = r5.DS     // Catch:{ all -> 0x007c }
            java.util.concurrent.locks.Lock r1 = r5.DN
            r1.unlock()
            goto L_0x003b
        L_0x006e:
            com.google.android.gms.common.ConnectionResult r0 = new com.google.android.gms.common.ConnectionResult     // Catch:{ all -> 0x007c }
            r1 = 13
            r2 = 0
            r0.<init>(r1, r2)     // Catch:{ all -> 0x007c }
            java.util.concurrent.locks.Lock r1 = r5.DN
            r1.unlock()
            goto L_0x003b
        L_0x007c:
            r0 = move-exception
            java.util.concurrent.locks.Lock r1 = r5.DN
            r1.unlock()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.c.blockingConnect(long, java.util.concurrent.TimeUnit):com.google.android.gms.common.ConnectionResult");
    }

    public void connect() {
        this.DN.lock();
        try {
            this.DW = false;
            if (!isConnected() && !isConnecting()) {
                this.Ed = true;
                this.DS = null;
                this.DU = 1;
                this.Ea.clear();
                this.DX = this.Eb.size();
                for (Api.a connect : this.Eb.values()) {
                    connect.connect();
                }
                this.DN.unlock();
            }
        } finally {
            this.DN.unlock();
        }
    }

    public void disconnect() {
        eI();
        aa(-1);
    }

    public Looper getLooper() {
        return this.DC;
    }

    public boolean isConnected() {
        this.DN.lock();
        try {
            return this.DU == 2;
        } finally {
            this.DN.unlock();
        }
    }

    public boolean isConnecting() {
        boolean z = true;
        this.DN.lock();
        try {
            if (this.DU != 1) {
                z = false;
            }
            return z;
        } finally {
            this.DN.unlock();
        }
    }

    public boolean isConnectionCallbacksRegistered(GoogleApiClient.ConnectionCallbacks listener) {
        return this.DP.isConnectionCallbacksRegistered(listener);
    }

    public boolean isConnectionFailedListenerRegistered(GoogleApiClient.OnConnectionFailedListener listener) {
        return this.DP.isConnectionFailedListenerRegistered(listener);
    }

    public void reconnect() {
        disconnect();
        connect();
    }

    public void registerConnectionCallbacks(GoogleApiClient.ConnectionCallbacks listener) {
        this.DP.registerConnectionCallbacks(listener);
    }

    public void registerConnectionFailedListener(GoogleApiClient.OnConnectionFailedListener listener) {
        this.DP.registerConnectionFailedListener(listener);
    }

    public void stopAutoManage() {
        hn.a(this.DQ != null, "Called stopAutoManage but automatic lifecycle management is not enabled.");
        if (this.DQ.getActivity() != null) {
            this.DQ.getActivity().getSupportFragmentManager().beginTransaction().remove(this.DQ).commit();
            disconnect();
        }
    }

    public void unregisterConnectionCallbacks(GoogleApiClient.ConnectionCallbacks listener) {
        this.DP.unregisterConnectionCallbacks(listener);
    }

    public void unregisterConnectionFailedListener(GoogleApiClient.OnConnectionFailedListener listener) {
        this.DP.unregisterConnectionFailedListener(listener);
    }
}
