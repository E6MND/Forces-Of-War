package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.a;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameBuffer;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.GamesMetadata;
import com.google.android.gms.games.internal.GamesClientImpl;

public final class GamesMetadataImpl implements GamesMetadata {

    /* renamed from: com.google.android.gms.games.internal.api.GamesMetadataImpl$10  reason: invalid class name */
    class AnonymousClass10 extends LoadExtendedGamesImpl {
        final /* synthetic */ String Pr;
        final /* synthetic */ int Ps;
        final /* synthetic */ boolean Pt;

        /* access modifiers changed from: protected */
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.a((a.d<GamesMetadata.LoadExtendedGamesResult>) this, this.Pr, this.Ps, false, true, false, this.Pt);
        }
    }

    /* renamed from: com.google.android.gms.games.internal.api.GamesMetadataImpl$11  reason: invalid class name */
    class AnonymousClass11 extends LoadExtendedGamesImpl {
        final /* synthetic */ boolean Pb;
        final /* synthetic */ String Pd;
        final /* synthetic */ int Ps;

        /* access modifiers changed from: protected */
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.c(this, this.Pd, this.Ps, false, this.Pb);
        }
    }

    /* renamed from: com.google.android.gms.games.internal.api.GamesMetadataImpl$12  reason: invalid class name */
    class AnonymousClass12 extends LoadExtendedGamesImpl {
        final /* synthetic */ String Pd;
        final /* synthetic */ int Ps;

        /* access modifiers changed from: protected */
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.c(this, this.Pd, this.Ps, true, false);
        }
    }

    /* renamed from: com.google.android.gms.games.internal.api.GamesMetadataImpl$13  reason: invalid class name */
    class AnonymousClass13 extends LoadExtendedGamesImpl {
        final /* synthetic */ boolean Pb;
        final /* synthetic */ String Pd;
        final /* synthetic */ int Ps;

        /* access modifiers changed from: protected */
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.d(this, this.Pd, this.Ps, false, this.Pb);
        }
    }

    /* renamed from: com.google.android.gms.games.internal.api.GamesMetadataImpl$14  reason: invalid class name */
    class AnonymousClass14 extends LoadExtendedGamesImpl {
        final /* synthetic */ String Pd;
        final /* synthetic */ int Ps;

        /* access modifiers changed from: protected */
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.d(this, this.Pd, this.Ps, true, false);
        }
    }

    /* renamed from: com.google.android.gms.games.internal.api.GamesMetadataImpl$15  reason: invalid class name */
    class AnonymousClass15 extends LoadExtendedGamesImpl {
        final /* synthetic */ boolean Pb;
        final /* synthetic */ String Pr;
        final /* synthetic */ int Ps;
        final /* synthetic */ boolean Pt;

        /* access modifiers changed from: protected */
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.a((a.d<GamesMetadata.LoadExtendedGamesResult>) this, this.Pr, this.Ps, true, false, this.Pb, this.Pt);
        }
    }

    /* renamed from: com.google.android.gms.games.internal.api.GamesMetadataImpl$16  reason: invalid class name */
    class AnonymousClass16 extends LoadExtendedGamesImpl {
        final /* synthetic */ String Pr;
        final /* synthetic */ int Ps;
        final /* synthetic */ boolean Pt;

        /* access modifiers changed from: protected */
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.a((a.d<GamesMetadata.LoadExtendedGamesResult>) this, this.Pr, this.Ps, true, true, false, this.Pt);
        }
    }

    /* renamed from: com.google.android.gms.games.internal.api.GamesMetadataImpl$17  reason: invalid class name */
    class AnonymousClass17 extends LoadExtendedGamesImpl {
        final /* synthetic */ boolean Pb;
        final /* synthetic */ int Ps;
        final /* synthetic */ String Pu;

        /* access modifiers changed from: protected */
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.e(this, this.Pu, this.Ps, false, this.Pb);
        }
    }

    /* renamed from: com.google.android.gms.games.internal.api.GamesMetadataImpl$18  reason: invalid class name */
    class AnonymousClass18 extends LoadExtendedGamesImpl {
        final /* synthetic */ int Ps;
        final /* synthetic */ String Pu;

        /* access modifiers changed from: protected */
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.e(this, this.Pu, this.Ps, true, false);
        }
    }

    /* renamed from: com.google.android.gms.games.internal.api.GamesMetadataImpl$19  reason: invalid class name */
    class AnonymousClass19 extends LoadGameSearchSuggestionsImpl {
        final /* synthetic */ String Pu;

        /* access modifiers changed from: protected */
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.m((a.d<GamesMetadata.LoadGameSearchSuggestionsResult>) this, this.Pu);
        }
    }

    /* renamed from: com.google.android.gms.games.internal.api.GamesMetadataImpl$2  reason: invalid class name */
    class AnonymousClass2 extends LoadExtendedGamesImpl {
        final /* synthetic */ String Pe;

        /* access modifiers changed from: protected */
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.k(this, this.Pe);
        }
    }

    /* renamed from: com.google.android.gms.games.internal.api.GamesMetadataImpl$3  reason: invalid class name */
    class AnonymousClass3 extends LoadGameInstancesImpl {
        final /* synthetic */ String Pe;

        /* access modifiers changed from: protected */
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.l((a.d<GamesMetadata.LoadGameInstancesResult>) this, this.Pe);
        }
    }

    /* renamed from: com.google.android.gms.games.internal.api.GamesMetadataImpl$4  reason: invalid class name */
    class AnonymousClass4 extends LoadExtendedGamesImpl {
        final /* synthetic */ boolean Pb;
        final /* synthetic */ int Ps;

        /* access modifiers changed from: protected */
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.b(this, (String) null, this.Ps, false, this.Pb);
        }
    }

    /* renamed from: com.google.android.gms.games.internal.api.GamesMetadataImpl$5  reason: invalid class name */
    class AnonymousClass5 extends LoadExtendedGamesImpl {
        final /* synthetic */ int Ps;

        /* access modifiers changed from: protected */
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.b(this, (String) null, this.Ps, true, false);
        }
    }

    /* renamed from: com.google.android.gms.games.internal.api.GamesMetadataImpl$6  reason: invalid class name */
    class AnonymousClass6 extends LoadExtendedGamesImpl {
        final /* synthetic */ boolean Pb;
        final /* synthetic */ String Pd;
        final /* synthetic */ int Ps;

        /* access modifiers changed from: protected */
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.b(this, this.Pd, this.Ps, false, this.Pb);
        }
    }

    /* renamed from: com.google.android.gms.games.internal.api.GamesMetadataImpl$7  reason: invalid class name */
    class AnonymousClass7 extends LoadExtendedGamesImpl {
        final /* synthetic */ String Pd;
        final /* synthetic */ int Ps;

        /* access modifiers changed from: protected */
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.b(this, this.Pd, this.Ps, true, false);
        }
    }

    /* renamed from: com.google.android.gms.games.internal.api.GamesMetadataImpl$8  reason: invalid class name */
    class AnonymousClass8 extends LoadExtendedGamesImpl {
        final /* synthetic */ boolean Pb;
        final /* synthetic */ int Ps;
        final /* synthetic */ int Pv;
        final /* synthetic */ boolean Pw;

        /* access modifiers changed from: protected */
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.a((a.d<GamesMetadata.LoadExtendedGamesResult>) this, this.Ps, this.Pv, this.Pw, this.Pb);
        }
    }

    /* renamed from: com.google.android.gms.games.internal.api.GamesMetadataImpl$9  reason: invalid class name */
    class AnonymousClass9 extends LoadExtendedGamesImpl {
        final /* synthetic */ boolean Pb;
        final /* synthetic */ String Pr;
        final /* synthetic */ int Ps;
        final /* synthetic */ boolean Pt;

        /* access modifiers changed from: protected */
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.a((a.d<GamesMetadata.LoadExtendedGamesResult>) this, this.Pr, this.Ps, false, false, this.Pb, this.Pt);
        }
    }

    private static abstract class LoadExtendedGamesImpl extends Games.BaseGamesApiMethodImpl<GamesMetadata.LoadExtendedGamesResult> {
        private LoadExtendedGamesImpl() {
        }

        /* renamed from: B */
        public GamesMetadata.LoadExtendedGamesResult c(final Status status) {
            return new GamesMetadata.LoadExtendedGamesResult() {
                public Status getStatus() {
                    return status;
                }

                public void release() {
                }
            };
        }
    }

    private static abstract class LoadGameInstancesImpl extends Games.BaseGamesApiMethodImpl<GamesMetadata.LoadGameInstancesResult> {
        private LoadGameInstancesImpl() {
        }

        /* renamed from: C */
        public GamesMetadata.LoadGameInstancesResult c(final Status status) {
            return new GamesMetadata.LoadGameInstancesResult() {
                public Status getStatus() {
                    return status;
                }

                public void release() {
                }
            };
        }
    }

    private static abstract class LoadGameSearchSuggestionsImpl extends Games.BaseGamesApiMethodImpl<GamesMetadata.LoadGameSearchSuggestionsResult> {
        private LoadGameSearchSuggestionsImpl() {
        }

        /* renamed from: D */
        public GamesMetadata.LoadGameSearchSuggestionsResult c(final Status status) {
            return new GamesMetadata.LoadGameSearchSuggestionsResult() {
                public Status getStatus() {
                    return status;
                }

                public void release() {
                }
            };
        }
    }

    private static abstract class LoadGamesImpl extends Games.BaseGamesApiMethodImpl<GamesMetadata.LoadGamesResult> {
        private LoadGamesImpl() {
        }

        /* renamed from: E */
        public GamesMetadata.LoadGamesResult c(final Status status) {
            return new GamesMetadata.LoadGamesResult() {
                public GameBuffer getGames() {
                    return new GameBuffer(DataHolder.af(14));
                }

                public Status getStatus() {
                    return status;
                }

                public void release() {
                }
            };
        }
    }

    public Game getCurrentGame(GoogleApiClient apiClient) {
        return Games.c(apiClient).gX();
    }

    public PendingResult<GamesMetadata.LoadGamesResult> loadGame(GoogleApiClient apiClient) {
        return apiClient.a(new LoadGamesImpl() {
            /* access modifiers changed from: protected */
            public void a(GamesClientImpl gamesClientImpl) {
                gamesClientImpl.f((a.d<GamesMetadata.LoadGamesResult>) this);
            }
        });
    }
}
