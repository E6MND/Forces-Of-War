package com.google.android.gms.location;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.a;
import com.google.android.gms.internal.gz;
import com.google.android.gms.internal.hn;
import com.google.android.gms.internal.jc;
import com.google.android.gms.internal.jd;
import com.google.android.gms.internal.jh;

public class LocationServices {
    public static final Api<Api.ApiOptions.NoOptions> API = new Api<>(yF, yE, new Scope[0]);
    public static FusedLocationProviderApi FusedLocationApi = new jc();
    public static GeofencingApi GeofencingApi = new jd();
    /* access modifiers changed from: private */
    public static final Api.c<jh> yE = new Api.c<>();
    private static final Api.b<jh, Api.ApiOptions.NoOptions> yF = new Api.b<jh, Api.ApiOptions.NoOptions>() {
        /* renamed from: c */
        public jh a(Context context, Looper looper, gz gzVar, Api.ApiOptions.NoOptions noOptions, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
            return new jh(context, looper, connectionCallbacks, onConnectionFailedListener, "locationServices");
        }

        public int getPriority() {
            return Integer.MAX_VALUE;
        }
    };

    public static abstract class a<R extends Result> extends a.b<R, jh> {
        public a() {
            super(LocationServices.yE);
        }
    }

    private LocationServices() {
    }

    public static jh e(GoogleApiClient googleApiClient) {
        boolean z = true;
        hn.b(googleApiClient != null, (Object) "GoogleApiClient parameter is required.");
        jh jhVar = (jh) googleApiClient.a(yE);
        if (jhVar == null) {
            z = false;
        }
        hn.a(z, "GoogleApiClient is not configured to use the LocationServices.API Api. Pass thisinto GoogleApiClient.Builder#addApi() to use this feature.");
        return jhVar;
    }
}
