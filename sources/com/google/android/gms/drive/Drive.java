package com.google.android.gms.drive;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.drive.internal.p;
import com.google.android.gms.drive.internal.r;
import com.google.android.gms.drive.internal.t;
import com.google.android.gms.drive.internal.w;
import com.google.android.gms.internal.gz;
import java.util.List;

public final class Drive {
    public static final Api<Api.ApiOptions.NoOptions> API = new Api<>(new a<Api.ApiOptions.NoOptions>() {
        /* access modifiers changed from: protected */
        public Bundle a(Api.ApiOptions.NoOptions noOptions) {
            return new Bundle();
        }
    }, yE, new Scope[0]);
    public static final DriveApi DriveApi = new p();
    public static final Scope HE = new Scope("https://www.googleapis.com/auth/drive");
    public static final Scope HF = new Scope("https://www.googleapis.com/auth/drive.apps");
    public static final Api<b> HG = new Api<>(new a<b>() {
        /* access modifiers changed from: protected */
        public Bundle a(b bVar) {
            return bVar == null ? new Bundle() : bVar.ge();
        }
    }, yE, new Scope[0]);
    public static final b HH = new t();
    public static final d HI = new w();
    public static final Scope SCOPE_APPFOLDER = new Scope(Scopes.DRIVE_APPFOLDER);
    public static final Scope SCOPE_FILE = new Scope(Scopes.DRIVE_FILE);
    public static final Api.c<r> yE = new Api.c<>();

    public static abstract class a<O extends Api.ApiOptions> implements Api.b<r, O> {
        /* access modifiers changed from: protected */
        public abstract Bundle a(O o);

        public r a(Context context, Looper looper, gz gzVar, O o, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
            List<String> fg = gzVar.fg();
            return new r(context, looper, gzVar, connectionCallbacks, onConnectionFailedListener, (String[]) fg.toArray(new String[fg.size()]), a(o));
        }

        public int getPriority() {
            return Integer.MAX_VALUE;
        }
    }

    public static class b implements Api.ApiOptions.Optional {
        private final Bundle HJ;

        private b() {
            this(new Bundle());
        }

        private b(Bundle bundle) {
            this.HJ = bundle;
        }

        public Bundle ge() {
            return this.HJ;
        }
    }

    private Drive() {
    }
}
