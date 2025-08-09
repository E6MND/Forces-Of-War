package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.appstate.AppStateBuffer;
import com.google.android.gms.appstate.AppStateManager;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.a;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.internal.gd;
import com.google.android.gms.internal.hc;

public final class gb extends hc<gd> {
    private final String yN;

    private static final class a extends ga {
        private final a.d<AppStateManager.StateDeletedResult> yO;

        public a(a.d<AppStateManager.StateDeletedResult> dVar) {
            this.yO = (a.d) hn.b(dVar, (Object) "Result holder must not be null");
        }

        public void b(int i, int i2) {
            this.yO.a(new b(new Status(i), i2));
        }
    }

    private static final class b implements AppStateManager.StateDeletedResult {
        private final int yP;
        private final Status yw;

        public b(Status status, int i) {
            this.yw = status;
            this.yP = i;
        }

        public int getStateKey() {
            return this.yP;
        }

        public Status getStatus() {
            return this.yw;
        }
    }

    private static final class c extends ga {
        private final a.d<AppStateManager.StateListResult> yO;

        public c(a.d<AppStateManager.StateListResult> dVar) {
            this.yO = (a.d) hn.b(dVar, (Object) "Result holder must not be null");
        }

        public void a(DataHolder dataHolder) {
            this.yO.a(new d(dataHolder));
        }
    }

    private static final class d extends com.google.android.gms.common.api.b implements AppStateManager.StateListResult {
        private final AppStateBuffer yQ;

        public d(DataHolder dataHolder) {
            super(dataHolder);
            this.yQ = new AppStateBuffer(dataHolder);
        }

        public AppStateBuffer getStateBuffer() {
            return this.yQ;
        }
    }

    private static final class e extends ga {
        private final a.d<AppStateManager.StateResult> yO;

        public e(a.d<AppStateManager.StateResult> dVar) {
            this.yO = (a.d) hn.b(dVar, (Object) "Result holder must not be null");
        }

        public void a(int i, DataHolder dataHolder) {
            this.yO.a(new f(i, dataHolder));
        }
    }

    private static final class f extends com.google.android.gms.common.api.b implements AppStateManager.StateConflictResult, AppStateManager.StateLoadedResult, AppStateManager.StateResult {
        private final int yP;
        private final AppStateBuffer yQ;

        public f(int i, DataHolder dataHolder) {
            super(dataHolder);
            this.yP = i;
            this.yQ = new AppStateBuffer(dataHolder);
        }

        private boolean dR() {
            return this.yw.getStatusCode() == 2000;
        }

        public AppStateManager.StateConflictResult getConflictResult() {
            if (dR()) {
                return this;
            }
            return null;
        }

        public AppStateManager.StateLoadedResult getLoadedResult() {
            if (dR()) {
                return null;
            }
            return this;
        }

        public byte[] getLocalData() {
            if (this.yQ.getCount() == 0) {
                return null;
            }
            return this.yQ.get(0).getLocalData();
        }

        public String getResolvedVersion() {
            if (this.yQ.getCount() == 0) {
                return null;
            }
            return this.yQ.get(0).getConflictVersion();
        }

        public byte[] getServerData() {
            if (this.yQ.getCount() == 0) {
                return null;
            }
            return this.yQ.get(0).getConflictData();
        }

        public int getStateKey() {
            return this.yP;
        }

        public void release() {
            this.yQ.close();
        }
    }

    private static final class g extends ga {
        private final a.d<Status> yO;

        public g(a.d<Status> dVar) {
            this.yO = (a.d) hn.b(dVar, (Object) "Holder must not be null");
        }

        public void dO() {
            this.yO.a(new Status(0));
        }
    }

    public gb(Context context, Looper looper, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener, String str, String[] strArr) {
        super(context, looper, connectionCallbacks, onConnectionFailedListener, strArr);
        this.yN = (String) hn.f(str);
    }

    /* access modifiers changed from: protected */
    /* renamed from: D */
    public gd x(IBinder iBinder) {
        return gd.a.F(iBinder);
    }

    public void a(a.d<AppStateManager.StateListResult> dVar) {
        try {
            ((gd) fo()).a(new c(dVar));
        } catch (RemoteException e2) {
            Log.w("AppStateClient", "service died");
        }
    }

    public void a(a.d<AppStateManager.StateDeletedResult> dVar, int i) {
        try {
            ((gd) fo()).b(new a(dVar), i);
        } catch (RemoteException e2) {
            Log.w("AppStateClient", "service died");
        }
    }

    public void a(a.d<AppStateManager.StateResult> dVar, int i, String str, byte[] bArr) {
        try {
            ((gd) fo()).a(new e(dVar), i, str, bArr);
        } catch (RemoteException e2) {
            Log.w("AppStateClient", "service died");
        }
    }

    public void a(a.d<AppStateManager.StateResult> dVar, int i, byte[] bArr) {
        try {
            ((gd) fo()).a(dVar == null ? null : new e(dVar), i, bArr);
        } catch (RemoteException e2) {
            Log.w("AppStateClient", "service died");
        }
    }

    /* access modifiers changed from: protected */
    public void a(hj hjVar, hc.e eVar) throws RemoteException {
        hjVar.a((hi) eVar, (int) GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_VERSION_CODE, getContext().getPackageName(), this.yN, fn());
    }

    public void b(a.d<Status> dVar) {
        try {
            ((gd) fo()).b(new g(dVar));
        } catch (RemoteException e2) {
            Log.w("AppStateClient", "service died");
        }
    }

    public void b(a.d<AppStateManager.StateResult> dVar, int i) {
        try {
            ((gd) fo()).a(new e(dVar), i);
        } catch (RemoteException e2) {
            Log.w("AppStateClient", "service died");
        }
    }

    /* access modifiers changed from: protected */
    public void b(String... strArr) {
        boolean z = false;
        for (String equals : strArr) {
            if (equals.equals(Scopes.APP_STATE)) {
                z = true;
            }
        }
        hn.a(z, String.format("App State APIs requires %s to function.", new Object[]{Scopes.APP_STATE}));
    }

    /* access modifiers changed from: protected */
    public String bp() {
        return "com.google.android.gms.appstate.service.START";
    }

    /* access modifiers changed from: protected */
    public String bq() {
        return "com.google.android.gms.appstate.internal.IAppStateService";
    }

    public int dP() {
        try {
            return ((gd) fo()).dP();
        } catch (RemoteException e2) {
            Log.w("AppStateClient", "service died");
            return 2;
        }
    }

    public int dQ() {
        try {
            return ((gd) fo()).dQ();
        } catch (RemoteException e2) {
            Log.w("AppStateClient", "service died");
            return 2;
        }
    }
}
