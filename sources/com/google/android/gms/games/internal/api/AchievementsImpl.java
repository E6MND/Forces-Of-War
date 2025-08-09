package com.google.android.gms.games.internal.api;

import android.content.Intent;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.a;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.achievement.AchievementBuffer;
import com.google.android.gms.games.achievement.Achievements;
import com.google.android.gms.games.internal.GamesClientImpl;

public final class AchievementsImpl implements Achievements {

    /* renamed from: com.google.android.gms.games.internal.api.AchievementsImpl$10  reason: invalid class name */
    class AnonymousClass10 extends LoadImpl {
        final /* synthetic */ boolean Pb;
        final /* synthetic */ String Pd;
        final /* synthetic */ String Pe;

        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.b((a.d<Achievements.LoadAchievementsResult>) this, this.Pd, this.Pe, this.Pb);
        }
    }

    private static abstract class LoadImpl extends Games.BaseGamesApiMethodImpl<Achievements.LoadAchievementsResult> {
        private LoadImpl() {
        }

        /* renamed from: v */
        public Achievements.LoadAchievementsResult c(final Status status) {
            return new Achievements.LoadAchievementsResult() {
                public AchievementBuffer getAchievements() {
                    return new AchievementBuffer(DataHolder.af(14));
                }

                public Status getStatus() {
                    return status;
                }

                public void release() {
                }
            };
        }
    }

    private static abstract class UpdateImpl extends Games.BaseGamesApiMethodImpl<Achievements.UpdateAchievementResult> {
        /* access modifiers changed from: private */
        public final String xD;

        public UpdateImpl(String id) {
            this.xD = id;
        }

        /* renamed from: w */
        public Achievements.UpdateAchievementResult c(final Status status) {
            return new Achievements.UpdateAchievementResult() {
                public String getAchievementId() {
                    return UpdateImpl.this.xD;
                }

                public Status getStatus() {
                    return status;
                }
            };
        }
    }

    public Intent getAchievementsIntent(GoogleApiClient apiClient) {
        return Games.c(apiClient).gZ();
    }

    public void increment(GoogleApiClient apiClient, final String id, final int numSteps) {
        apiClient.b(new UpdateImpl(id) {
            public void a(GamesClientImpl gamesClientImpl) {
                gamesClientImpl.a((a.d<Achievements.UpdateAchievementResult>) null, id, numSteps);
            }
        });
    }

    public PendingResult<Achievements.UpdateAchievementResult> incrementImmediate(GoogleApiClient apiClient, final String id, final int numSteps) {
        return apiClient.b(new UpdateImpl(id) {
            public void a(GamesClientImpl gamesClientImpl) {
                gamesClientImpl.a((a.d<Achievements.UpdateAchievementResult>) this, id, numSteps);
            }
        });
    }

    public PendingResult<Achievements.LoadAchievementsResult> load(GoogleApiClient apiClient, final boolean forceReload) {
        return apiClient.a(new LoadImpl() {
            public void a(GamesClientImpl gamesClientImpl) {
                gamesClientImpl.c((a.d<Achievements.LoadAchievementsResult>) this, forceReload);
            }
        });
    }

    public void reveal(GoogleApiClient apiClient, final String id) {
        apiClient.b(new UpdateImpl(id) {
            public void a(GamesClientImpl gamesClientImpl) {
                gamesClientImpl.b((a.d<Achievements.UpdateAchievementResult>) null, id);
            }
        });
    }

    public PendingResult<Achievements.UpdateAchievementResult> revealImmediate(GoogleApiClient apiClient, final String id) {
        return apiClient.b(new UpdateImpl(id) {
            public void a(GamesClientImpl gamesClientImpl) {
                gamesClientImpl.b((a.d<Achievements.UpdateAchievementResult>) this, id);
            }
        });
    }

    public void setSteps(GoogleApiClient apiClient, final String id, final int numSteps) {
        apiClient.b(new UpdateImpl(id) {
            public void a(GamesClientImpl gamesClientImpl) {
                gamesClientImpl.b((a.d<Achievements.UpdateAchievementResult>) null, id, numSteps);
            }
        });
    }

    public PendingResult<Achievements.UpdateAchievementResult> setStepsImmediate(GoogleApiClient apiClient, final String id, final int numSteps) {
        return apiClient.b(new UpdateImpl(id) {
            public void a(GamesClientImpl gamesClientImpl) {
                gamesClientImpl.b((a.d<Achievements.UpdateAchievementResult>) this, id, numSteps);
            }
        });
    }

    public void unlock(GoogleApiClient apiClient, final String id) {
        apiClient.b(new UpdateImpl(id) {
            public void a(GamesClientImpl gamesClientImpl) {
                gamesClientImpl.c((a.d<Achievements.UpdateAchievementResult>) null, id);
            }
        });
    }

    public PendingResult<Achievements.UpdateAchievementResult> unlockImmediate(GoogleApiClient apiClient, final String id) {
        return apiClient.b(new UpdateImpl(id) {
            public void a(GamesClientImpl gamesClientImpl) {
                gamesClientImpl.c((a.d<Achievements.UpdateAchievementResult>) this, id);
            }
        });
    }
}
