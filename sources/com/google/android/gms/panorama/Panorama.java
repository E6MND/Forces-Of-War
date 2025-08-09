package com.google.android.gms.panorama;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.internal.gz;
import com.google.android.gms.internal.kg;
import com.google.android.gms.internal.kh;

public final class Panorama {
    public static final Api<Api.ApiOptions.NoOptions> API = new Api<>(yF, yE, new Scope[0]);
    public static final PanoramaApi PanoramaApi = new kg();
    public static final Api.c<kh> yE = new Api.c<>();
    static final Api.b<kh, Api.ApiOptions.NoOptions> yF = new Api.b<kh, Api.ApiOptions.NoOptions>() {
        /* renamed from: d */
        public kh a(Context context, Looper looper, gz gzVar, Api.ApiOptions.NoOptions noOptions, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
            return new kh(context, looper, connectionCallbacks, onConnectionFailedListener);
        }

        public int getPriority() {
            return Integer.MAX_VALUE;
        }
    };

    private Panorama() {
    }
}
