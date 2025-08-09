package com.google.android.gms.common.api;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.a;
import com.google.android.gms.internal.gz;
import com.google.android.gms.internal.hn;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public interface GoogleApiClient {

    public static final class Builder {
        private Looper DC;
        private final Set<String> DE;
        private int DF;
        private View DG;
        private String DH;
        private final Map<Api<?>, Api.ApiOptions> DI;
        private FragmentActivity DJ;
        private OnConnectionFailedListener DK;
        private final Set<ConnectionCallbacks> DL;
        private final Set<OnConnectionFailedListener> DM;
        private final Context mContext;
        private String yN;

        public Builder(Context context) {
            this.DE = new HashSet();
            this.DI = new HashMap();
            this.DL = new HashSet();
            this.DM = new HashSet();
            this.mContext = context;
            this.DC = context.getMainLooper();
            this.DH = context.getPackageName();
        }

        public Builder(Context context, ConnectionCallbacks connectedListener, OnConnectionFailedListener connectionFailedListener) {
            this(context);
            hn.b(connectedListener, (Object) "Must provide a connected listener");
            this.DL.add(connectedListener);
            hn.b(connectionFailedListener, (Object) "Must provide a connection failed listener");
            this.DM.add(connectionFailedListener);
        }

        private d eE() {
            FragmentManager supportFragmentManager = this.DJ.getSupportFragmentManager();
            if (supportFragmentManager.getFragments() != null) {
                for (Fragment next : supportFragmentManager.getFragments()) {
                    if ((next instanceof d) && next.isAdded() && !((d) next).isInitialized()) {
                        return (d) next;
                    }
                }
            }
            d dVar = new d();
            supportFragmentManager.beginTransaction().add((Fragment) dVar, (String) null).commit();
            return dVar;
        }

        public Builder addApi(Api<? extends Api.ApiOptions.NotRequiredOptions> api) {
            this.DI.put(api, (Object) null);
            List<Scope> ev = api.ev();
            int size = ev.size();
            for (int i = 0; i < size; i++) {
                this.DE.add(ev.get(i).eK());
            }
            return this;
        }

        public <O extends Api.ApiOptions.HasOptions> Builder addApi(Api<O> api, O options) {
            hn.b(options, (Object) "Null options are not permitted for this Api");
            this.DI.put(api, options);
            List<Scope> ev = api.ev();
            int size = ev.size();
            for (int i = 0; i < size; i++) {
                this.DE.add(ev.get(i).eK());
            }
            return this;
        }

        public Builder addConnectionCallbacks(ConnectionCallbacks listener) {
            this.DL.add(listener);
            return this;
        }

        public Builder addOnConnectionFailedListener(OnConnectionFailedListener listener) {
            this.DM.add(listener);
            return this;
        }

        public Builder addScope(Scope scope) {
            this.DE.add(scope.eK());
            return this;
        }

        public GoogleApiClient build() {
            hn.b(!this.DI.isEmpty(), (Object) "must call addApi() to add at least one API");
            d dVar = null;
            if (this.DJ != null) {
                dVar = eE();
            }
            c cVar = new c(this.mContext, this.DC, eD(), this.DI, dVar, this.DL, this.DM);
            if (dVar != null) {
                dVar.a(cVar, this.DK);
            }
            return cVar;
        }

        public gz eD() {
            return new gz(this.yN, this.DE, this.DF, this.DG, this.DH);
        }

        public Builder enableAutoManage(FragmentActivity fragmentActivity, OnConnectionFailedListener unresolvedConnectionFailedListener) {
            this.DJ = (FragmentActivity) hn.b(fragmentActivity, (Object) "Null activity is not permitted.");
            this.DK = unresolvedConnectionFailedListener;
            return this;
        }

        public Builder setAccountName(String accountName) {
            this.yN = accountName;
            return this;
        }

        public Builder setGravityForPopups(int gravityForPopups) {
            this.DF = gravityForPopups;
            return this;
        }

        public Builder setHandler(Handler handler) {
            hn.b(handler, (Object) "Handler must not be null");
            this.DC = handler.getLooper();
            return this;
        }

        public Builder setViewForPopups(View viewForPopups) {
            this.DG = viewForPopups;
            return this;
        }

        public Builder useDefaultAccount() {
            return setAccountName("<<default account>>");
        }
    }

    public interface ConnectionCallbacks {
        public static final int CAUSE_NETWORK_LOST = 2;
        public static final int CAUSE_SERVICE_DISCONNECTED = 1;

        void onConnected(Bundle bundle);

        void onConnectionSuspended(int i);
    }

    public interface OnConnectionFailedListener extends GooglePlayServicesClient.OnConnectionFailedListener {
        void onConnectionFailed(ConnectionResult connectionResult);
    }

    <C extends Api.a> C a(Api.c<C> cVar);

    <A extends Api.a, T extends a.b<? extends Result, A>> T a(T t);

    <A extends Api.a, T extends a.b<? extends Result, A>> T b(T t);

    ConnectionResult blockingConnect();

    ConnectionResult blockingConnect(long j, TimeUnit timeUnit);

    void connect();

    void disconnect();

    Looper getLooper();

    boolean isConnected();

    boolean isConnecting();

    boolean isConnectionCallbacksRegistered(ConnectionCallbacks connectionCallbacks);

    boolean isConnectionFailedListenerRegistered(OnConnectionFailedListener onConnectionFailedListener);

    void reconnect();

    void registerConnectionCallbacks(ConnectionCallbacks connectionCallbacks);

    void registerConnectionFailedListener(OnConnectionFailedListener onConnectionFailedListener);

    void stopAutoManage();

    void unregisterConnectionCallbacks(ConnectionCallbacks connectionCallbacks);

    void unregisterConnectionFailedListener(OnConnectionFailedListener onConnectionFailedListener);
}
