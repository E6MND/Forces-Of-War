package com.google.android.gms.location;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.a;
import com.google.android.gms.internal.gz;
import com.google.android.gms.internal.jb;
import com.google.android.gms.internal.jh;

public class ActivityRecognition {
    public static final Api<Api.ApiOptions.NoOptions> API = new Api<>(yF, yE, new Scope[0]);
    public static ActivityRecognitionApi ActivityRecognitionApi = new jb();
    public static final String CLIENT_NAME = "activity_recognition";
    /* access modifiers changed from: private */
    public static final Api.c<jh> yE = new Api.c<>();
    private static final Api.b<jh, Api.ApiOptions.NoOptions> yF = new Api.b<jh, Api.ApiOptions.NoOptions>() {
        /* renamed from: c */
        public jh a(Context context, Looper looper, gz gzVar, Api.ApiOptions.NoOptions noOptions, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
            return new jh(context, looper, connectionCallbacks, onConnectionFailedListener, ActivityRecognition.CLIENT_NAME);
        }

        public int getPriority() {
            return Integer.MAX_VALUE;
        }
    };

    public static abstract class a<R extends Result> extends a.b<R, jh> {
        public a() {
            super(ActivityRecognition.yE);
        }
    }

    private ActivityRecognition() {
    }
}
