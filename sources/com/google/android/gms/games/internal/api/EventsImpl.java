package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.a;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.event.EventBuffer;
import com.google.android.gms.games.event.Events;
import com.google.android.gms.games.internal.GamesClientImpl;

public final class EventsImpl implements Events {

    private static abstract class LoadImpl extends Games.BaseGamesApiMethodImpl<Events.LoadEventsResult> {
        private LoadImpl() {
        }

        /* renamed from: A */
        public Events.LoadEventsResult c(final Status status) {
            return new Events.LoadEventsResult() {
                public EventBuffer getEvents() {
                    return new EventBuffer(DataHolder.af(14));
                }

                public Status getStatus() {
                    return status;
                }

                public void release() {
                }
            };
        }
    }

    private static abstract class UpdateImpl extends Games.BaseGamesApiMethodImpl<Result> {
        private UpdateImpl() {
        }

        public Result c(final Status status) {
            return new Result() {
                public Status getStatus() {
                    return status;
                }
            };
        }
    }

    public void increment(GoogleApiClient apiClient, final String eventId, final int incrementAmount) {
        GamesClientImpl d = Games.d(apiClient);
        if (d.isConnected()) {
            d.l(eventId, incrementAmount);
        } else {
            apiClient.b(new UpdateImpl() {
                public void a(GamesClientImpl gamesClientImpl) {
                    gamesClientImpl.l(eventId, incrementAmount);
                }
            });
        }
    }

    public PendingResult<Events.LoadEventsResult> load(GoogleApiClient apiClient, final boolean forceReload) {
        return apiClient.a(new LoadImpl() {
            public void a(GamesClientImpl gamesClientImpl) {
                gamesClientImpl.d((a.d<Events.LoadEventsResult>) this, forceReload);
            }
        });
    }

    public PendingResult<Events.LoadEventsResult> loadByIds(GoogleApiClient apiClient, final boolean forceReload, final String... eventIds) {
        return apiClient.a(new LoadImpl() {
            public void a(GamesClientImpl gamesClientImpl) {
                gamesClientImpl.a((a.d<Events.LoadEventsResult>) this, forceReload, eventIds);
            }
        });
    }
}
