package com.google.android.gms.plus;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.a;
import com.google.android.gms.internal.gz;
import com.google.android.gms.internal.hn;
import com.google.android.gms.internal.kj;
import com.google.android.gms.internal.kk;
import com.google.android.gms.internal.kl;
import com.google.android.gms.internal.km;
import com.google.android.gms.internal.kn;
import com.google.android.gms.plus.internal.PlusCommonExtras;
import com.google.android.gms.plus.internal.e;
import com.google.android.gms.plus.internal.h;
import java.util.HashSet;
import java.util.Set;

public final class Plus {
    public static final Api<PlusOptions> API = new Api<>(yF, yE, new Scope[0]);
    public static final Account AccountApi = new kj();
    public static final Moments MomentsApi = new km();
    public static final People PeopleApi = new kn();
    public static final Scope SCOPE_PLUS_LOGIN = new Scope(Scopes.PLUS_LOGIN);
    public static final Scope SCOPE_PLUS_PROFILE = new Scope(Scopes.PLUS_ME);
    public static final b abm = new kl();
    public static final a abn = new kk();
    public static final Api.c<e> yE = new Api.c<>();
    static final Api.b<e, PlusOptions> yF = new Api.b<e, PlusOptions>() {
        public e a(Context context, Looper looper, gz gzVar, PlusOptions plusOptions, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
            if (plusOptions == null) {
                plusOptions = new PlusOptions();
            }
            return new e(context, looper, connectionCallbacks, onConnectionFailedListener, new h(gzVar.fe(), gzVar.fh(), (String[]) plusOptions.abp.toArray(new String[0]), new String[0], context.getPackageName(), context.getPackageName(), (String) null, new PlusCommonExtras()));
        }

        public int getPriority() {
            return 2;
        }
    };

    public static final class PlusOptions implements Api.ApiOptions.Optional {
        final String abo;
        final Set<String> abp;

        public static final class Builder {
            String abo;
            final Set<String> abp = new HashSet();

            public Builder addActivityTypes(String... activityTypes) {
                hn.b(activityTypes, (Object) "activityTypes may not be null.");
                for (String add : activityTypes) {
                    this.abp.add(add);
                }
                return this;
            }

            public PlusOptions build() {
                return new PlusOptions(this);
            }

            public Builder setServerClientId(String clientId) {
                this.abo = clientId;
                return this;
            }
        }

        private PlusOptions() {
            this.abo = null;
            this.abp = new HashSet();
        }

        private PlusOptions(Builder builder) {
            this.abo = builder.abo;
            this.abp = builder.abp;
        }

        public static Builder builder() {
            return new Builder();
        }
    }

    public static abstract class a<R extends Result> extends a.b<R, e> {
        public a() {
            super(Plus.yE);
        }
    }

    private Plus() {
    }

    /* JADX WARNING: type inference failed for: r5v0, types: [com.google.android.gms.common.api.Api$c, com.google.android.gms.common.api.Api$c<com.google.android.gms.plus.internal.e>] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.gms.plus.internal.e a(com.google.android.gms.common.api.GoogleApiClient r4, com.google.android.gms.common.api.Api.c<com.google.android.gms.plus.internal.e> r5) {
        /*
            r1 = 1
            r2 = 0
            if (r4 == 0) goto L_0x0021
            r0 = r1
        L_0x0005:
            java.lang.String r3 = "GoogleApiClient parameter is required."
            com.google.android.gms.internal.hn.b((boolean) r0, (java.lang.Object) r3)
            boolean r0 = r4.isConnected()
            java.lang.String r3 = "GoogleApiClient must be connected."
            com.google.android.gms.internal.hn.a(r0, r3)
            com.google.android.gms.common.api.Api$a r0 = r4.a(r5)
            com.google.android.gms.plus.internal.e r0 = (com.google.android.gms.plus.internal.e) r0
            if (r0 == 0) goto L_0x0023
        L_0x001b:
            java.lang.String r2 = "GoogleApiClient is not configured to use the Plus.API Api. Pass this into GoogleApiClient.Builder#addApi() to use this feature."
            com.google.android.gms.internal.hn.a(r1, r2)
            return r0
        L_0x0021:
            r0 = r2
            goto L_0x0005
        L_0x0023:
            r1 = r2
            goto L_0x001b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.plus.Plus.a(com.google.android.gms.common.api.GoogleApiClient, com.google.android.gms.common.api.Api$c):com.google.android.gms.plus.internal.e");
    }
}
