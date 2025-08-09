package com.google.android.gms.games.internal.api;

import android.content.Intent;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.a;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerBuffer;
import com.google.android.gms.games.Players;
import com.google.android.gms.games.internal.GamesClientImpl;

public final class PlayersImpl implements Players {

    /* renamed from: com.google.android.gms.games.internal.api.PlayersImpl$10  reason: invalid class name */
    class AnonymousClass10 extends LoadPlayersImpl {
        final /* synthetic */ boolean Pb;
        final /* synthetic */ int Ps;

        /* access modifiers changed from: protected */
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.b((a.d<Players.LoadPlayersResult>) this, this.Ps, false, this.Pb);
        }
    }

    /* renamed from: com.google.android.gms.games.internal.api.PlayersImpl$11  reason: invalid class name */
    class AnonymousClass11 extends LoadPlayersImpl {
        final /* synthetic */ int Ps;

        /* access modifiers changed from: protected */
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.b((a.d<Players.LoadPlayersResult>) this, this.Ps, true, false);
        }
    }

    /* renamed from: com.google.android.gms.games.internal.api.PlayersImpl$12  reason: invalid class name */
    class AnonymousClass12 extends LoadPlayersImpl {
        final /* synthetic */ boolean Pb;
        final /* synthetic */ int Ps;

        /* access modifiers changed from: protected */
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.c((a.d<Players.LoadPlayersResult>) this, this.Ps, false, this.Pb);
        }
    }

    /* renamed from: com.google.android.gms.games.internal.api.PlayersImpl$13  reason: invalid class name */
    class AnonymousClass13 extends LoadPlayersImpl {
        final /* synthetic */ int Ps;

        /* access modifiers changed from: protected */
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.c((a.d<Players.LoadPlayersResult>) this, this.Ps, true, false);
        }
    }

    /* renamed from: com.google.android.gms.games.internal.api.PlayersImpl$14  reason: invalid class name */
    class AnonymousClass14 extends LoadPlayersImpl {
        final /* synthetic */ boolean Pb;
        final /* synthetic */ int Ps;

        /* access modifiers changed from: protected */
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.d(this, this.Ps, false, this.Pb);
        }
    }

    /* renamed from: com.google.android.gms.games.internal.api.PlayersImpl$15  reason: invalid class name */
    class AnonymousClass15 extends LoadPlayersImpl {
        final /* synthetic */ int Ps;

        /* access modifiers changed from: protected */
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.d(this, this.Ps, true, false);
        }
    }

    /* renamed from: com.google.android.gms.games.internal.api.PlayersImpl$16  reason: invalid class name */
    class AnonymousClass16 extends LoadPlayersImpl {
        final /* synthetic */ boolean Pb;
        final /* synthetic */ int Ps;
        final /* synthetic */ String Pu;

        /* access modifiers changed from: protected */
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.f(this, this.Pu, this.Ps, false, this.Pb);
        }
    }

    /* renamed from: com.google.android.gms.games.internal.api.PlayersImpl$17  reason: invalid class name */
    class AnonymousClass17 extends LoadPlayersImpl {
        final /* synthetic */ int Ps;
        final /* synthetic */ String Pu;

        /* access modifiers changed from: protected */
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.f(this, this.Pu, this.Ps, true, false);
        }
    }

    /* renamed from: com.google.android.gms.games.internal.api.PlayersImpl$18  reason: invalid class name */
    class AnonymousClass18 extends LoadPlayersImpl {
        final /* synthetic */ boolean Pb;
        final /* synthetic */ String Pe;
        final /* synthetic */ int Qb;

        /* access modifiers changed from: protected */
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.a((a.d<Players.LoadPlayersResult>) this, this.Pe, this.Qb, this.Pb);
        }
    }

    /* renamed from: com.google.android.gms.games.internal.api.PlayersImpl$19  reason: invalid class name */
    class AnonymousClass19 extends LoadPlayersImpl {
        final /* synthetic */ boolean Pb;
        final /* synthetic */ int Ps;

        /* access modifiers changed from: protected */
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.e(this, this.Ps, false, this.Pb);
        }
    }

    /* renamed from: com.google.android.gms.games.internal.api.PlayersImpl$2  reason: invalid class name */
    class AnonymousClass2 extends LoadPlayersImpl {
        final /* synthetic */ String[] Qc;

        /* access modifiers changed from: protected */
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.a((a.d<Players.LoadPlayersResult>) this, this.Qc);
        }
    }

    /* renamed from: com.google.android.gms.games.internal.api.PlayersImpl$20  reason: invalid class name */
    class AnonymousClass20 extends LoadPlayersImpl {
        final /* synthetic */ int Ps;

        /* access modifiers changed from: protected */
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.e(this, this.Ps, true, false);
        }
    }

    /* renamed from: com.google.android.gms.games.internal.api.PlayersImpl$21  reason: invalid class name */
    class AnonymousClass21 extends LoadPlayersImpl {
        final /* synthetic */ boolean Pb;
        final /* synthetic */ int Ps;
        final /* synthetic */ String Qd;

        /* access modifiers changed from: protected */
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.g(this, this.Qd, this.Ps, false, this.Pb);
        }
    }

    /* renamed from: com.google.android.gms.games.internal.api.PlayersImpl$22  reason: invalid class name */
    class AnonymousClass22 extends LoadPlayersImpl {
        final /* synthetic */ int Ps;
        final /* synthetic */ String Qd;

        /* access modifiers changed from: protected */
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.g(this, this.Qd, this.Ps, true, false);
        }
    }

    /* renamed from: com.google.android.gms.games.internal.api.PlayersImpl$23  reason: invalid class name */
    class AnonymousClass23 extends LoadOwnerCoverPhotoUrisImpl {
        /* access modifiers changed from: protected */
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.g(this);
        }
    }

    /* renamed from: com.google.android.gms.games.internal.api.PlayersImpl$24  reason: invalid class name */
    class AnonymousClass24 extends LoadXpForGameCategoriesResultImpl {
        final /* synthetic */ String Qe;

        /* access modifiers changed from: protected */
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.n((a.d<Players.LoadXpForGameCategoriesResult>) this, this.Qe);
        }
    }

    /* renamed from: com.google.android.gms.games.internal.api.PlayersImpl$25  reason: invalid class name */
    class AnonymousClass25 extends LoadXpStreamResultImpl {
        final /* synthetic */ String Qe;
        final /* synthetic */ int Qf;

        /* access modifiers changed from: protected */
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.c((a.d<Players.LoadXpStreamResult>) this, this.Qe, this.Qf);
        }
    }

    /* renamed from: com.google.android.gms.games.internal.api.PlayersImpl$26  reason: invalid class name */
    class AnonymousClass26 extends LoadXpStreamResultImpl {
        final /* synthetic */ String Qe;
        final /* synthetic */ int Qf;

        /* access modifiers changed from: protected */
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.d((a.d<Players.LoadXpStreamResult>) this, this.Qe, this.Qf);
        }
    }

    /* renamed from: com.google.android.gms.games.internal.api.PlayersImpl$7  reason: invalid class name */
    class AnonymousClass7 extends LoadPlayersImpl {
        final /* synthetic */ boolean Pb;
        final /* synthetic */ String Pe;
        final /* synthetic */ int Ps;

        /* access modifiers changed from: protected */
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.a((a.d<Players.LoadPlayersResult>) this, "played_with", this.Pe, this.Ps, false, this.Pb);
        }
    }

    /* renamed from: com.google.android.gms.games.internal.api.PlayersImpl$8  reason: invalid class name */
    class AnonymousClass8 extends LoadPlayersImpl {
        final /* synthetic */ String Pe;
        final /* synthetic */ int Ps;

        /* access modifiers changed from: protected */
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.a((a.d<Players.LoadPlayersResult>) this, "played_with", this.Pe, this.Ps, true, false);
        }
    }

    private static abstract class LoadOwnerCoverPhotoUrisImpl extends Games.BaseGamesApiMethodImpl<Players.LoadOwnerCoverPhotoUrisResult> {
        private LoadOwnerCoverPhotoUrisImpl() {
        }

        /* renamed from: O */
        public Players.LoadOwnerCoverPhotoUrisResult c(final Status status) {
            return new Players.LoadOwnerCoverPhotoUrisResult() {
                public Status getStatus() {
                    return status;
                }
            };
        }
    }

    private static abstract class LoadPlayersImpl extends Games.BaseGamesApiMethodImpl<Players.LoadPlayersResult> {
        private LoadPlayersImpl() {
        }

        /* renamed from: P */
        public Players.LoadPlayersResult c(final Status status) {
            return new Players.LoadPlayersResult() {
                public PlayerBuffer getPlayers() {
                    return new PlayerBuffer(DataHolder.af(14));
                }

                public Status getStatus() {
                    return status;
                }

                public void release() {
                }
            };
        }
    }

    private static abstract class LoadXpForGameCategoriesResultImpl extends Games.BaseGamesApiMethodImpl<Players.LoadXpForGameCategoriesResult> {
        private LoadXpForGameCategoriesResultImpl() {
        }

        /* renamed from: Q */
        public Players.LoadXpForGameCategoriesResult c(final Status status) {
            return new Players.LoadXpForGameCategoriesResult() {
                public Status getStatus() {
                    return status;
                }
            };
        }
    }

    private static abstract class LoadXpForGameResultImpl extends Games.BaseGamesApiMethodImpl<Players.LoadXpForGamesResult> {

        /* renamed from: com.google.android.gms.games.internal.api.PlayersImpl$LoadXpForGameResultImpl$1  reason: invalid class name */
        class AnonymousClass1 implements Players.LoadXpForGamesResult {
            final /* synthetic */ Status yG;

            public Status getStatus() {
                return this.yG;
            }
        }

        private LoadXpForGameResultImpl() {
        }
    }

    private static abstract class LoadXpStreamResultImpl extends Games.BaseGamesApiMethodImpl<Players.LoadXpStreamResult> {
        private LoadXpStreamResultImpl() {
        }

        /* renamed from: R */
        public Players.LoadXpStreamResult c(final Status status) {
            return new Players.LoadXpStreamResult() {
                public Status getStatus() {
                    return status;
                }
            };
        }
    }

    public Player getCurrentPlayer(GoogleApiClient apiClient) {
        return Games.c(apiClient).gW();
    }

    public String getCurrentPlayerId(GoogleApiClient apiClient) {
        return Games.c(apiClient).gV();
    }

    public Intent getPlayerSearchIntent(GoogleApiClient apiClient) {
        return Games.c(apiClient).hg();
    }

    public PendingResult<Players.LoadPlayersResult> loadConnectedPlayers(GoogleApiClient apiClient, final boolean forceReload) {
        return apiClient.a(new LoadPlayersImpl() {
            /* access modifiers changed from: protected */
            public void a(GamesClientImpl gamesClientImpl) {
                gamesClientImpl.a((a.d<Players.LoadPlayersResult>) this, forceReload);
            }
        });
    }

    public PendingResult<Players.LoadPlayersResult> loadInvitablePlayers(GoogleApiClient apiClient, final int pageSize, final boolean forceReload) {
        return apiClient.a(new LoadPlayersImpl() {
            /* access modifiers changed from: protected */
            public void a(GamesClientImpl gamesClientImpl) {
                gamesClientImpl.a((a.d<Players.LoadPlayersResult>) this, pageSize, false, forceReload);
            }
        });
    }

    public PendingResult<Players.LoadPlayersResult> loadMoreInvitablePlayers(GoogleApiClient apiClient, final int pageSize) {
        return apiClient.a(new LoadPlayersImpl() {
            /* access modifiers changed from: protected */
            public void a(GamesClientImpl gamesClientImpl) {
                gamesClientImpl.a((a.d<Players.LoadPlayersResult>) this, pageSize, true, false);
            }
        });
    }

    public PendingResult<Players.LoadPlayersResult> loadMoreRecentlyPlayedWithPlayers(GoogleApiClient apiClient, final int pageSize) {
        return apiClient.a(new LoadPlayersImpl() {
            /* access modifiers changed from: protected */
            public void a(GamesClientImpl gamesClientImpl) {
                gamesClientImpl.a((a.d<Players.LoadPlayersResult>) this, "played_with", pageSize, true, false);
            }
        });
    }

    public PendingResult<Players.LoadPlayersResult> loadPlayer(GoogleApiClient apiClient, final String playerId) {
        return apiClient.a(new LoadPlayersImpl() {
            /* access modifiers changed from: protected */
            public void a(GamesClientImpl gamesClientImpl) {
                gamesClientImpl.a((a.d<Players.LoadPlayersResult>) this, playerId);
            }
        });
    }

    public PendingResult<Players.LoadPlayersResult> loadRecentlyPlayedWithPlayers(GoogleApiClient apiClient, final int pageSize, final boolean forceReload) {
        return apiClient.a(new LoadPlayersImpl() {
            /* access modifiers changed from: protected */
            public void a(GamesClientImpl gamesClientImpl) {
                gamesClientImpl.a((a.d<Players.LoadPlayersResult>) this, "played_with", pageSize, false, forceReload);
            }
        });
    }
}
