package com.google.android.gms.games.internal.api;

import android.content.Intent;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.a;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.games.quest.Milestone;
import com.google.android.gms.games.quest.Quest;
import com.google.android.gms.games.quest.QuestBuffer;
import com.google.android.gms.games.quest.QuestUpdateListener;
import com.google.android.gms.games.quest.Quests;

public final class QuestsImpl implements Quests {

    /* renamed from: com.google.android.gms.games.internal.api.QuestsImpl$5  reason: invalid class name */
    class AnonymousClass5 extends LoadsImpl {
        final /* synthetic */ int PB;
        final /* synthetic */ boolean Pb;
        final /* synthetic */ String Pe;
        final /* synthetic */ int[] Qn;
        final /* synthetic */ String Qp;

        /* access modifiers changed from: protected */
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.a((a.d<Quests.LoadQuestsResult>) this, this.Pe, this.Qp, this.Qn, this.PB, this.Pb);
        }
    }

    /* renamed from: com.google.android.gms.games.internal.api.QuestsImpl$6  reason: invalid class name */
    class AnonymousClass6 extends LoadsImpl {
        final /* synthetic */ boolean Pb;
        final /* synthetic */ String Pe;
        final /* synthetic */ String[] Qo;
        final /* synthetic */ String Qp;

        /* access modifiers changed from: protected */
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.a((a.d<Quests.LoadQuestsResult>) this, this.Pe, this.Qp, this.Pb, this.Qo);
        }
    }

    private static abstract class AcceptImpl extends Games.BaseGamesApiMethodImpl<Quests.AcceptQuestResult> {
        private AcceptImpl() {
        }

        /* renamed from: S */
        public Quests.AcceptQuestResult c(final Status status) {
            return new Quests.AcceptQuestResult() {
                public Quest getQuest() {
                    return null;
                }

                public Status getStatus() {
                    return status;
                }
            };
        }
    }

    private static abstract class ClaimImpl extends Games.BaseGamesApiMethodImpl<Quests.ClaimMilestoneResult> {
        private ClaimImpl() {
        }

        /* renamed from: T */
        public Quests.ClaimMilestoneResult c(final Status status) {
            return new Quests.ClaimMilestoneResult() {
                public Milestone getMilestone() {
                    return null;
                }

                public Quest getQuest() {
                    return null;
                }

                public Status getStatus() {
                    return status;
                }
            };
        }
    }

    private static abstract class LoadsImpl extends Games.BaseGamesApiMethodImpl<Quests.LoadQuestsResult> {
        private LoadsImpl() {
        }

        /* renamed from: U */
        public Quests.LoadQuestsResult c(final Status status) {
            return new Quests.LoadQuestsResult() {
                public QuestBuffer getQuests() {
                    return null;
                }

                public Status getStatus() {
                    return status;
                }

                public void release() {
                }
            };
        }
    }

    public PendingResult<Quests.AcceptQuestResult> accept(GoogleApiClient apiClient, final String questId) {
        return apiClient.b(new AcceptImpl() {
            /* access modifiers changed from: protected */
            public void a(GamesClientImpl gamesClientImpl) {
                gamesClientImpl.i(this, questId);
            }
        });
    }

    public PendingResult<Quests.ClaimMilestoneResult> claim(GoogleApiClient apiClient, final String questId, final String milestoneId) {
        return apiClient.b(new ClaimImpl() {
            /* access modifiers changed from: protected */
            public void a(GamesClientImpl gamesClientImpl) {
                gamesClientImpl.b((a.d<Quests.ClaimMilestoneResult>) this, questId, milestoneId);
            }
        });
    }

    public Intent getQuestIntent(GoogleApiClient apiClient, String questId) {
        return Games.c(apiClient).aU(questId);
    }

    public Intent getQuestsIntent(GoogleApiClient apiClient, int[] questSelectors) {
        return Games.c(apiClient).a(questSelectors);
    }

    public PendingResult<Quests.LoadQuestsResult> load(GoogleApiClient apiClient, final int[] questSelectors, final int sortOrder, final boolean forceReload) {
        return apiClient.a(new LoadsImpl() {
            /* access modifiers changed from: protected */
            public void a(GamesClientImpl gamesClientImpl) {
                gamesClientImpl.a((a.d<Quests.LoadQuestsResult>) this, questSelectors, sortOrder, forceReload);
            }
        });
    }

    public PendingResult<Quests.LoadQuestsResult> loadByIds(GoogleApiClient apiClient, final boolean forceReload, final String... questIds) {
        return apiClient.a(new LoadsImpl() {
            /* access modifiers changed from: protected */
            public void a(GamesClientImpl gamesClientImpl) {
                gamesClientImpl.b((a.d<Quests.LoadQuestsResult>) this, forceReload, questIds);
            }
        });
    }

    public void registerQuestUpdateListener(GoogleApiClient apiClient, QuestUpdateListener listener) {
        Games.c(apiClient).a(listener);
    }

    public void unregisterQuestUpdateListener(GoogleApiClient apiClient) {
        Games.c(apiClient).he();
    }
}
