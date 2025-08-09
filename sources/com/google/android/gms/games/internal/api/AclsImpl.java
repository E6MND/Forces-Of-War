package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.games.internal.game.Acls;

public final class AclsImpl implements Acls {

    /* renamed from: com.google.android.gms.games.internal.api.AclsImpl$2  reason: invalid class name */
    class AnonymousClass2 extends LoadNotifyAclImpl {
        /* access modifiers changed from: protected */
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.h(this);
        }
    }

    /* renamed from: com.google.android.gms.games.internal.api.AclsImpl$3  reason: invalid class name */
    class AnonymousClass3 extends UpdateNotifyAclImpl {
        final /* synthetic */ String Pj;

        /* access modifiers changed from: protected */
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.p(this, this.Pj);
        }
    }

    private static abstract class LoadNotifyAclImpl extends Games.BaseGamesApiMethodImpl<Acls.LoadAclResult> {
        private LoadNotifyAclImpl() {
        }

        /* renamed from: z */
        public Acls.LoadAclResult c(Status status) {
            return AclsImpl.x(status);
        }
    }

    private static abstract class UpdateNotifyAclImpl extends Games.BaseGamesApiMethodImpl<Status> {
        private UpdateNotifyAclImpl() {
        }

        /* renamed from: d */
        public Status c(Status status) {
            return status;
        }
    }

    /* access modifiers changed from: private */
    public static Acls.LoadAclResult x(final Status status) {
        return new Acls.LoadAclResult() {
            public Status getStatus() {
                return status;
            }

            public void release() {
            }
        };
    }
}
