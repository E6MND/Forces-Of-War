package com.google.android.gms.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.plus.Account;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.internal.e;

public final class kj implements Account {

    private static abstract class a extends Plus.a<Status> {
        private a() {
        }

        /* renamed from: d */
        public Status c(Status status) {
            return status;
        }
    }

    /* JADX WARNING: type inference failed for: r5v0, types: [com.google.android.gms.common.api.Api$c, com.google.android.gms.common.api.Api$c<com.google.android.gms.plus.internal.e>] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.google.android.gms.plus.internal.e a(com.google.android.gms.common.api.GoogleApiClient r4, com.google.android.gms.common.api.Api.c<com.google.android.gms.plus.internal.e> r5) {
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
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.kj.a(com.google.android.gms.common.api.GoogleApiClient, com.google.android.gms.common.api.Api$c):com.google.android.gms.plus.internal.e");
    }

    public void clearDefaultAccount(GoogleApiClient googleApiClient) {
        a(googleApiClient, Plus.yE).clearDefaultAccount();
    }

    public String getAccountName(GoogleApiClient googleApiClient) {
        return a(googleApiClient, Plus.yE).getAccountName();
    }

    public PendingResult<Status> revokeAccessAndDisconnect(GoogleApiClient googleApiClient) {
        return googleApiClient.b(new a() {
            /* access modifiers changed from: protected */
            public void a(e eVar) {
                eVar.m(this);
            }
        });
    }
}
