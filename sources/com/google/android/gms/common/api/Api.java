package com.google.android.gms.common.api;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.gz;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class Api<O extends ApiOptions> {
    private final b<?, O> Dj;
    private final c<?> Dk;
    private final ArrayList<Scope> Dl;

    public interface ApiOptions {

        public interface HasOptions extends ApiOptions {
        }

        public static final class NoOptions implements NotRequiredOptions {
            private NoOptions() {
            }
        }

        public interface NotRequiredOptions extends ApiOptions {
        }

        public interface Optional extends HasOptions, NotRequiredOptions {
        }
    }

    public interface a {
        void connect();

        void disconnect();

        Looper getLooper();

        boolean isConnected();
    }

    public interface b<T extends a, O> {
        T a(Context context, Looper looper, gz gzVar, O o, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener);

        int getPriority();
    }

    public static final class c<C extends a> {
    }

    public <C extends a> Api(b<C, O> clientBuilder, c<C> clientKey, Scope... impliedScopes) {
        this.Dj = clientBuilder;
        this.Dk = clientKey;
        this.Dl = new ArrayList<>(Arrays.asList(impliedScopes));
    }

    public b<?, O> eu() {
        return this.Dj;
    }

    public List<Scope> ev() {
        return this.Dl;
    }

    public c<?> ew() {
        return this.Dk;
    }
}
