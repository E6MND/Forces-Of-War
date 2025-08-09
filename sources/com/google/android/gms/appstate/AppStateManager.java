package com.google.android.gms.appstate;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.a;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.internal.gb;
import com.google.android.gms.internal.gz;
import com.google.android.gms.internal.hn;

public final class AppStateManager {
    public static final Api<Api.ApiOptions.NoOptions> API = new Api<>(yF, yE, SCOPE_APP_STATE);
    public static final Scope SCOPE_APP_STATE = new Scope(Scopes.APP_STATE);
    static final Api.c<gb> yE = new Api.c<>();
    private static final Api.b<gb, Api.ApiOptions.NoOptions> yF = new Api.b<gb, Api.ApiOptions.NoOptions>() {
        /* renamed from: b */
        public gb a(Context context, Looper looper, gz gzVar, Api.ApiOptions.NoOptions noOptions, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
            return new gb(context, looper, connectionCallbacks, onConnectionFailedListener, gzVar.fe(), (String[]) gzVar.fg().toArray(new String[0]));
        }

        public int getPriority() {
            return Integer.MAX_VALUE;
        }
    };

    public interface StateConflictResult extends Releasable, Result {
        byte[] getLocalData();

        String getResolvedVersion();

        byte[] getServerData();

        int getStateKey();
    }

    public interface StateDeletedResult extends Result {
        int getStateKey();
    }

    public interface StateListResult extends Result {
        AppStateBuffer getStateBuffer();
    }

    public interface StateLoadedResult extends Releasable, Result {
        byte[] getLocalData();

        int getStateKey();
    }

    public interface StateResult extends Releasable, Result {
        StateConflictResult getConflictResult();

        StateLoadedResult getLoadedResult();
    }

    public static abstract class a<R extends Result> extends a.b<R, gb> {
        public a() {
            super(AppStateManager.yE);
        }
    }

    private static abstract class b extends a<StateDeletedResult> {
        private b() {
        }
    }

    private static abstract class c extends a<StateListResult> {
        private c() {
        }

        /* renamed from: h */
        public StateListResult c(final Status status) {
            return new StateListResult() {
                public AppStateBuffer getStateBuffer() {
                    return new AppStateBuffer((DataHolder) null);
                }

                public Status getStatus() {
                    return status;
                }
            };
        }
    }

    private static abstract class d extends a<Status> {
        private d() {
        }

        /* renamed from: d */
        public Status c(Status status) {
            return status;
        }
    }

    private static abstract class e extends a<StateResult> {
        private e() {
        }

        /* renamed from: i */
        public StateResult c(Status status) {
            return AppStateManager.e(status);
        }
    }

    private AppStateManager() {
    }

    public static gb a(GoogleApiClient googleApiClient) {
        boolean z = true;
        hn.b(googleApiClient != null, (Object) "GoogleApiClient parameter is required.");
        hn.a(googleApiClient.isConnected(), "GoogleApiClient must be connected.");
        gb gbVar = (gb) googleApiClient.a(yE);
        if (gbVar == null) {
            z = false;
        }
        hn.a(z, "GoogleApiClient is not configured to use the AppState API. Pass AppStateManager.API into GoogleApiClient.Builder#addApi() to use this feature.");
        return gbVar;
    }

    public static PendingResult<StateDeletedResult> delete(GoogleApiClient googleApiClient, final int stateKey) {
        return googleApiClient.b(new b() {
            /* access modifiers changed from: protected */
            public void a(gb gbVar) {
                gbVar.a((a.d<StateDeletedResult>) this, stateKey);
            }

            /* renamed from: g */
            public StateDeletedResult c(final Status status) {
                return new StateDeletedResult() {
                    public int getStateKey() {
                        return stateKey;
                    }

                    public Status getStatus() {
                        return status;
                    }
                };
            }
        });
    }

    /* access modifiers changed from: private */
    public static StateResult e(final Status status) {
        return new StateResult() {
            public StateConflictResult getConflictResult() {
                return null;
            }

            public StateLoadedResult getLoadedResult() {
                return null;
            }

            public Status getStatus() {
                return status;
            }

            public void release() {
            }
        };
    }

    public static int getMaxNumKeys(GoogleApiClient googleApiClient) {
        return a(googleApiClient).dQ();
    }

    public static int getMaxStateSize(GoogleApiClient googleApiClient) {
        return a(googleApiClient).dP();
    }

    public static PendingResult<StateListResult> list(GoogleApiClient googleApiClient) {
        return googleApiClient.a(new c() {
            /* access modifiers changed from: protected */
            public void a(gb gbVar) {
                gbVar.a(this);
            }
        });
    }

    public static PendingResult<StateResult> load(GoogleApiClient googleApiClient, final int stateKey) {
        return googleApiClient.a(new e() {
            /* access modifiers changed from: protected */
            public void a(gb gbVar) {
                gbVar.b(this, stateKey);
            }
        });
    }

    public static PendingResult<StateResult> resolve(GoogleApiClient googleApiClient, final int stateKey, final String resolvedVersion, final byte[] resolvedData) {
        return googleApiClient.b(new e() {
            /* access modifiers changed from: protected */
            public void a(gb gbVar) {
                gbVar.a(this, stateKey, resolvedVersion, resolvedData);
            }
        });
    }

    public static PendingResult<Status> signOut(GoogleApiClient googleApiClient) {
        return googleApiClient.b(new d() {
            /* access modifiers changed from: protected */
            public void a(gb gbVar) {
                gbVar.b((a.d<Status>) this);
            }
        });
    }

    public static void update(GoogleApiClient googleApiClient, final int stateKey, final byte[] data) {
        googleApiClient.b(new e() {
            /* access modifiers changed from: protected */
            public void a(gb gbVar) {
                gbVar.a((a.d<StateResult>) null, stateKey, data);
            }
        });
    }

    public static PendingResult<StateResult> updateImmediate(GoogleApiClient googleApiClient, final int stateKey, final byte[] data) {
        return googleApiClient.b(new e() {
            /* access modifiers changed from: protected */
            public void a(gb gbVar) {
                gbVar.a(this, stateKey, data);
            }
        });
    }
}
