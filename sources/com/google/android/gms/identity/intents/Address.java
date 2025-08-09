package com.google.android.gms.identity.intents;

import android.app.Activity;
import android.content.Context;
import android.os.Looper;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.a;
import com.google.android.gms.internal.gz;
import com.google.android.gms.internal.hn;
import com.google.android.gms.internal.ix;

public final class Address {
    public static final Api<AddressOptions> API = new Api<>(yF, yE, new Scope[0]);
    static final Api.c<ix> yE = new Api.c<>();
    private static final Api.b<ix, AddressOptions> yF = new Api.b<ix, AddressOptions>() {
        public ix a(Context context, Looper looper, gz gzVar, AddressOptions addressOptions, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
            hn.b(context instanceof Activity, (Object) "An Activity must be used for Address APIs");
            if (addressOptions == null) {
                addressOptions = new AddressOptions();
            }
            return new ix((Activity) context, looper, connectionCallbacks, onConnectionFailedListener, gzVar.getAccountName(), addressOptions.theme);
        }

        public int getPriority() {
            return Integer.MAX_VALUE;
        }
    };

    public static final class AddressOptions implements Api.ApiOptions.HasOptions {
        public final int theme;

        public AddressOptions() {
            this.theme = 0;
        }

        public AddressOptions(int theme2) {
            this.theme = theme2;
        }
    }

    private static abstract class a extends a.b<Status, ix> {
        public a() {
            super(Address.yE);
        }

        /* renamed from: d */
        public Status c(Status status) {
            return status;
        }
    }

    public static void requestUserAddress(GoogleApiClient googleApiClient, final UserAddressRequest request, final int requestCode) {
        googleApiClient.a(new a() {
            /* access modifiers changed from: protected */
            public void a(ix ixVar) throws RemoteException {
                ixVar.a(request, requestCode);
                a(Status.Ek);
            }
        });
    }
}
