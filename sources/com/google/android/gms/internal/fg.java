package com.google.android.gms.internal;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;

public final class fg {
    public static final Api.c<fy> xF = new Api.c<>();
    private static final Api.b<fy, Api.ApiOptions.NoOptions> xG = new Api.b<fy, Api.ApiOptions.NoOptions>() {
        public fy a(Context context, Looper looper, gz gzVar, Api.ApiOptions.NoOptions noOptions, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
            return new fy(context, looper, connectionCallbacks, onConnectionFailedListener);
        }

        public int getPriority() {
            return Integer.MAX_VALUE;
        }
    };
    public static final Api<Api.ApiOptions.NoOptions> xH = new Api<>(xG, xF, new Scope[0]);
    public static final fu xI = new fz();
}
