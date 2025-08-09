package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.internal.dq;

public abstract class dr extends en {
    private final dt nc;
    private final dq.a pR;

    public static final class a extends dr {
        private final Context mContext;

        public a(Context context, dt dtVar, dq.a aVar) {
            super(dtVar, aVar);
            this.mContext = context;
        }

        public void bn() {
        }

        public dx bo() {
            return dy.a(this.mContext, new az(), (bj) new bk(), (ee) new ef());
        }
    }

    public static final class b extends dr implements GooglePlayServicesClient.ConnectionCallbacks, GooglePlayServicesClient.OnConnectionFailedListener {
        private final Object lq = new Object();
        private final dq.a pR;
        private final ds pS;

        public b(Context context, dt dtVar, dq.a aVar) {
            super(dtVar, aVar);
            this.pR = aVar;
            this.pS = new ds(context, this, this, dtVar.kO.sv);
            this.pS.connect();
        }

        public void bn() {
            synchronized (this.lq) {
                if (this.pS.isConnected() || this.pS.isConnecting()) {
                    this.pS.disconnect();
                }
            }
        }

        public dx bo() {
            dx dxVar;
            synchronized (this.lq) {
                try {
                    dxVar = this.pS.br();
                } catch (IllegalStateException e) {
                    dxVar = null;
                }
            }
            return dxVar;
        }

        public void onConnected(Bundle connectionHint) {
            start();
        }

        public void onConnectionFailed(ConnectionResult result) {
            this.pR.a(new dv(0));
        }

        public void onDisconnected() {
            ev.z("Disconnected from remote ad request service.");
        }
    }

    public dr(dt dtVar, dq.a aVar) {
        this.nc = dtVar;
        this.pR = aVar;
    }

    private static dv a(dx dxVar, dt dtVar) {
        try {
            return dxVar.b(dtVar);
        } catch (RemoteException e) {
            ev.c("Could not fetch ad response from ad request service.", e);
            return null;
        } catch (NullPointerException e2) {
            ev.c("Could not fetch ad response from ad request service due to an Exception.", e2);
            return null;
        } catch (SecurityException e3) {
            ev.c("Could not fetch ad response from ad request service due to an Exception.", e3);
            return null;
        }
    }

    /* JADX INFO: finally extract failed */
    public final void bc() {
        dv a2;
        try {
            dx bo = bo();
            if (bo == null) {
                a2 = new dv(0);
            } else {
                a2 = a(bo, this.nc);
                if (a2 == null) {
                    a2 = new dv(0);
                }
            }
            bn();
            this.pR.a(a2);
        } catch (Throwable th) {
            bn();
            throw th;
        }
    }

    public abstract void bn();

    public abstract dx bo();

    public final void onStop() {
        bn();
    }
}
