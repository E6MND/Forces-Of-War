package com.google.android.gms.games.internal.api;

import android.content.Intent;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.a;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.games.leaderboard.Leaderboard;
import com.google.android.gms.games.leaderboard.LeaderboardBuffer;
import com.google.android.gms.games.leaderboard.LeaderboardScore;
import com.google.android.gms.games.leaderboard.LeaderboardScoreBuffer;
import com.google.android.gms.games.leaderboard.Leaderboards;
import com.google.android.gms.games.leaderboard.ScoreSubmissionData;

public final class LeaderboardsImpl implements Leaderboards {

    /* renamed from: com.google.android.gms.games.internal.api.LeaderboardsImpl$10  reason: invalid class name */
    class AnonymousClass10 extends LoadScoresImpl {
        final /* synthetic */ String PG;
        final /* synthetic */ int PH;
        final /* synthetic */ int PI;
        final /* synthetic */ int PJ;
        final /* synthetic */ boolean Pb;
        final /* synthetic */ String Pe;

        /* access modifiers changed from: protected */
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.a((a.d<Leaderboards.LoadScoresResult>) this, this.Pe, this.PG, this.PH, this.PI, this.PJ, this.Pb);
        }
    }

    /* renamed from: com.google.android.gms.games.internal.api.LeaderboardsImpl$11  reason: invalid class name */
    class AnonymousClass11 extends LoadScoresImpl {
        final /* synthetic */ String PG;
        final /* synthetic */ int PH;
        final /* synthetic */ int PI;
        final /* synthetic */ int PJ;
        final /* synthetic */ boolean Pb;
        final /* synthetic */ String Pe;

        /* access modifiers changed from: protected */
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.b(this, this.Pe, this.PG, this.PH, this.PI, this.PJ, this.Pb);
        }
    }

    /* renamed from: com.google.android.gms.games.internal.api.LeaderboardsImpl$8  reason: invalid class name */
    class AnonymousClass8 extends LoadMetadataImpl {
        final /* synthetic */ boolean Pb;
        final /* synthetic */ String Pe;

        /* access modifiers changed from: protected */
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.c((a.d<Leaderboards.LeaderboardMetadataResult>) this, this.Pe, this.Pb);
        }
    }

    /* renamed from: com.google.android.gms.games.internal.api.LeaderboardsImpl$9  reason: invalid class name */
    class AnonymousClass9 extends LoadMetadataImpl {
        final /* synthetic */ String PG;
        final /* synthetic */ boolean Pb;
        final /* synthetic */ String Pe;

        /* access modifiers changed from: protected */
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.a((a.d<Leaderboards.LeaderboardMetadataResult>) this, this.Pe, this.PG, this.Pb);
        }
    }

    private static abstract class LoadMetadataImpl extends Games.BaseGamesApiMethodImpl<Leaderboards.LeaderboardMetadataResult> {
        private LoadMetadataImpl() {
        }

        /* renamed from: G */
        public Leaderboards.LeaderboardMetadataResult c(final Status status) {
            return new Leaderboards.LeaderboardMetadataResult() {
                public LeaderboardBuffer getLeaderboards() {
                    return new LeaderboardBuffer(DataHolder.af(14));
                }

                public Status getStatus() {
                    return status;
                }

                public void release() {
                }
            };
        }
    }

    private static abstract class LoadPlayerScoreImpl extends Games.BaseGamesApiMethodImpl<Leaderboards.LoadPlayerScoreResult> {
        private LoadPlayerScoreImpl() {
        }

        /* renamed from: H */
        public Leaderboards.LoadPlayerScoreResult c(final Status status) {
            return new Leaderboards.LoadPlayerScoreResult() {
                public LeaderboardScore getScore() {
                    return null;
                }

                public Status getStatus() {
                    return status;
                }
            };
        }
    }

    private static abstract class LoadScoresImpl extends Games.BaseGamesApiMethodImpl<Leaderboards.LoadScoresResult> {
        private LoadScoresImpl() {
        }

        /* renamed from: I */
        public Leaderboards.LoadScoresResult c(final Status status) {
            return new Leaderboards.LoadScoresResult() {
                public Leaderboard getLeaderboard() {
                    return null;
                }

                public LeaderboardScoreBuffer getScores() {
                    return new LeaderboardScoreBuffer(DataHolder.af(14));
                }

                public Status getStatus() {
                    return status;
                }

                public void release() {
                }
            };
        }
    }

    protected static abstract class SubmitScoreImpl extends Games.BaseGamesApiMethodImpl<Leaderboards.SubmitScoreResult> {
        protected SubmitScoreImpl() {
        }

        /* renamed from: J */
        public Leaderboards.SubmitScoreResult c(final Status status) {
            return new Leaderboards.SubmitScoreResult() {
                public ScoreSubmissionData getScoreData() {
                    return new ScoreSubmissionData(DataHolder.af(14));
                }

                public Status getStatus() {
                    return status;
                }

                public void release() {
                }
            };
        }
    }

    public Intent getAllLeaderboardsIntent(GoogleApiClient apiClient) {
        return Games.c(apiClient).gY();
    }

    public Intent getLeaderboardIntent(GoogleApiClient apiClient, String leaderboardId) {
        return Games.c(apiClient).aR(leaderboardId);
    }

    public PendingResult<Leaderboards.LoadPlayerScoreResult> loadCurrentPlayerLeaderboardScore(GoogleApiClient apiClient, final String leaderboardId, final int span, final int leaderboardCollection) {
        return apiClient.a(new LoadPlayerScoreImpl() {
            /* access modifiers changed from: protected */
            public void a(GamesClientImpl gamesClientImpl) {
                gamesClientImpl.a((a.d<Leaderboards.LoadPlayerScoreResult>) this, (String) null, leaderboardId, span, leaderboardCollection);
            }
        });
    }

    public PendingResult<Leaderboards.LeaderboardMetadataResult> loadLeaderboardMetadata(GoogleApiClient apiClient, final String leaderboardId, final boolean forceReload) {
        return apiClient.a(new LoadMetadataImpl() {
            /* access modifiers changed from: protected */
            public void a(GamesClientImpl gamesClientImpl) {
                gamesClientImpl.a((a.d<Leaderboards.LeaderboardMetadataResult>) this, leaderboardId, forceReload);
            }
        });
    }

    public PendingResult<Leaderboards.LeaderboardMetadataResult> loadLeaderboardMetadata(GoogleApiClient apiClient, final boolean forceReload) {
        return apiClient.a(new LoadMetadataImpl() {
            /* access modifiers changed from: protected */
            public void a(GamesClientImpl gamesClientImpl) {
                gamesClientImpl.b((a.d<Leaderboards.LeaderboardMetadataResult>) this, forceReload);
            }
        });
    }

    public PendingResult<Leaderboards.LoadScoresResult> loadMoreScores(GoogleApiClient apiClient, final LeaderboardScoreBuffer buffer, final int maxResults, final int pageDirection) {
        return apiClient.a(new LoadScoresImpl() {
            /* access modifiers changed from: protected */
            public void a(GamesClientImpl gamesClientImpl) {
                gamesClientImpl.a((a.d<Leaderboards.LoadScoresResult>) this, buffer, maxResults, pageDirection);
            }
        });
    }

    public PendingResult<Leaderboards.LoadScoresResult> loadPlayerCenteredScores(GoogleApiClient apiClient, String leaderboardId, int span, int leaderboardCollection, int maxResults) {
        return loadPlayerCenteredScores(apiClient, leaderboardId, span, leaderboardCollection, maxResults, false);
    }

    public PendingResult<Leaderboards.LoadScoresResult> loadPlayerCenteredScores(GoogleApiClient apiClient, String leaderboardId, int span, int leaderboardCollection, int maxResults, boolean forceReload) {
        final String str = leaderboardId;
        final int i = span;
        final int i2 = leaderboardCollection;
        final int i3 = maxResults;
        final boolean z = forceReload;
        return apiClient.a(new LoadScoresImpl() {
            /* access modifiers changed from: protected */
            public void a(GamesClientImpl gamesClientImpl) {
                gamesClientImpl.b(this, str, i, i2, i3, z);
            }
        });
    }

    public PendingResult<Leaderboards.LoadScoresResult> loadTopScores(GoogleApiClient apiClient, String leaderboardId, int span, int leaderboardCollection, int maxResults) {
        return loadTopScores(apiClient, leaderboardId, span, leaderboardCollection, maxResults, false);
    }

    public PendingResult<Leaderboards.LoadScoresResult> loadTopScores(GoogleApiClient apiClient, String leaderboardId, int span, int leaderboardCollection, int maxResults, boolean forceReload) {
        final String str = leaderboardId;
        final int i = span;
        final int i2 = leaderboardCollection;
        final int i3 = maxResults;
        final boolean z = forceReload;
        return apiClient.a(new LoadScoresImpl() {
            /* access modifiers changed from: protected */
            public void a(GamesClientImpl gamesClientImpl) {
                gamesClientImpl.a((a.d<Leaderboards.LoadScoresResult>) this, str, i, i2, i3, z);
            }
        });
    }

    public void submitScore(GoogleApiClient apiClient, String leaderboardId, long score) {
        submitScore(apiClient, leaderboardId, score, (String) null);
    }

    public void submitScore(GoogleApiClient apiClient, String leaderboardId, long score, String scoreTag) {
        Games.c(apiClient).a((a.d<Leaderboards.SubmitScoreResult>) null, leaderboardId, score, scoreTag);
    }

    public PendingResult<Leaderboards.SubmitScoreResult> submitScoreImmediate(GoogleApiClient apiClient, String leaderboardId, long score) {
        return submitScoreImmediate(apiClient, leaderboardId, score, (String) null);
    }

    public PendingResult<Leaderboards.SubmitScoreResult> submitScoreImmediate(GoogleApiClient apiClient, String leaderboardId, long score, String scoreTag) {
        final String str = leaderboardId;
        final long j = score;
        final String str2 = scoreTag;
        return apiClient.b(new SubmitScoreImpl() {
            /* access modifiers changed from: protected */
            public void a(GamesClientImpl gamesClientImpl) {
                gamesClientImpl.a((a.d<Leaderboards.SubmitScoreResult>) this, str, j, str2);
            }
        });
    }
}
