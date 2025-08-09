package com.google.android.gms.games;

import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import android.view.View;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.a;
import com.google.android.gms.games.achievement.Achievements;
import com.google.android.gms.games.event.Events;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.games.internal.api.AchievementsImpl;
import com.google.android.gms.games.internal.api.AclsImpl;
import com.google.android.gms.games.internal.api.EventsImpl;
import com.google.android.gms.games.internal.api.GamesMetadataImpl;
import com.google.android.gms.games.internal.api.InvitationsImpl;
import com.google.android.gms.games.internal.api.LeaderboardsImpl;
import com.google.android.gms.games.internal.api.MultiplayerImpl;
import com.google.android.gms.games.internal.api.NotificationsImpl;
import com.google.android.gms.games.internal.api.PlayersImpl;
import com.google.android.gms.games.internal.api.QuestsImpl;
import com.google.android.gms.games.internal.api.RealTimeMultiplayerImpl;
import com.google.android.gms.games.internal.api.RequestsImpl;
import com.google.android.gms.games.internal.api.SnapshotsImpl;
import com.google.android.gms.games.internal.api.TurnBasedMultiplayerImpl;
import com.google.android.gms.games.internal.game.Acls;
import com.google.android.gms.games.leaderboard.Leaderboards;
import com.google.android.gms.games.multiplayer.Invitations;
import com.google.android.gms.games.multiplayer.Multiplayer;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMultiplayer;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer;
import com.google.android.gms.games.quest.Quests;
import com.google.android.gms.games.request.Requests;
import com.google.android.gms.games.snapshot.Snapshots;
import com.google.android.gms.internal.gz;
import com.google.android.gms.internal.hn;

public final class Games {
    public static final Api<GamesOptions> API = new Api<>(yF, yE, SCOPE_GAMES);
    public static final Achievements Achievements = new AchievementsImpl();
    public static final String EXTRA_PLAYER_IDS = "players";
    public static final Events Events = new EventsImpl();
    public static final GamesMetadata GamesMetadata = new GamesMetadataImpl();
    public static final Invitations Invitations = new InvitationsImpl();
    public static final Leaderboards Leaderboards = new LeaderboardsImpl();
    public static final Scope MF = new Scope("https://www.googleapis.com/auth/games.firstparty");
    public static final Api<GamesOptions> MG = new Api<>(yF, yE, MF);
    public static final Multiplayer MH = new MultiplayerImpl();
    public static final Acls MI = new AclsImpl();
    public static final Notifications Notifications = new NotificationsImpl();
    public static final Players Players = new PlayersImpl();
    public static final Quests Quests = new QuestsImpl();
    public static final RealTimeMultiplayer RealTimeMultiplayer = new RealTimeMultiplayerImpl();
    public static final Requests Requests = new RequestsImpl();
    public static final Scope SCOPE_GAMES = new Scope(Scopes.GAMES);
    public static final Snapshots Snapshots = new SnapshotsImpl();
    public static final TurnBasedMultiplayer TurnBasedMultiplayer = new TurnBasedMultiplayerImpl();
    static final Api.c<GamesClientImpl> yE = new Api.c<>();
    private static final Api.b<GamesClientImpl, GamesOptions> yF = new Api.b<GamesClientImpl, GamesOptions>() {
        public GamesClientImpl a(Context context, Looper looper, gz gzVar, GamesOptions gamesOptions, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
            if (gamesOptions == null) {
                gamesOptions = new GamesOptions();
            }
            return new GamesClientImpl(context, looper, gzVar.fi(), gzVar.fe(), connectionCallbacks, onConnectionFailedListener, gzVar.fh(), gzVar.ff(), gzVar.fj(), gamesOptions.MJ, gamesOptions.MK, gamesOptions.ML, gamesOptions.MM, gamesOptions.MN, gamesOptions.MO);
        }

        public int getPriority() {
            return 1;
        }
    };

    public static abstract class BaseGamesApiMethodImpl<R extends Result> extends a.b<R, GamesClientImpl> {
        public BaseGamesApiMethodImpl() {
            super(Games.yE);
        }
    }

    public static final class GamesOptions implements Api.ApiOptions.Optional {
        final boolean MJ;
        final boolean MK;
        final int ML;
        final boolean MM;
        final int MN;
        final String MO;

        public static final class Builder {
            boolean MJ;
            boolean MK;
            int ML;
            boolean MM;
            int MN;
            String MO;

            private Builder() {
                this.MJ = false;
                this.MK = true;
                this.ML = 17;
                this.MM = false;
                this.MN = 4368;
                this.MO = null;
            }

            public GamesOptions build() {
                return new GamesOptions(this);
            }

            public Builder setSdkVariant(int variant) {
                this.MN = variant;
                return this;
            }

            public Builder setShowConnectingPopup(boolean showConnectingPopup) {
                this.MK = showConnectingPopup;
                this.ML = 17;
                return this;
            }

            public Builder setShowConnectingPopup(boolean showConnectingPopup, int gravity) {
                this.MK = showConnectingPopup;
                this.ML = gravity;
                return this;
            }
        }

        private GamesOptions() {
            this.MJ = false;
            this.MK = true;
            this.ML = 17;
            this.MM = false;
            this.MN = 4368;
            this.MO = null;
        }

        private GamesOptions(Builder builder) {
            this.MJ = builder.MJ;
            this.MK = builder.MK;
            this.ML = builder.ML;
            this.MM = builder.MM;
            this.MN = builder.MN;
            this.MO = builder.MO;
        }

        public static Builder builder() {
            return new Builder();
        }
    }

    private static abstract class SignOutImpl extends BaseGamesApiMethodImpl<Status> {
        private SignOutImpl() {
        }

        /* renamed from: d */
        public Status c(Status status) {
            return status;
        }
    }

    private Games() {
    }

    public static GamesClientImpl c(GoogleApiClient googleApiClient) {
        hn.b(googleApiClient != null, (Object) "GoogleApiClient parameter is required.");
        hn.a(googleApiClient.isConnected(), "GoogleApiClient must be connected.");
        return d(googleApiClient);
    }

    public static GamesClientImpl d(GoogleApiClient googleApiClient) {
        GamesClientImpl gamesClientImpl = (GamesClientImpl) googleApiClient.a(yE);
        hn.a(gamesClientImpl != null, "GoogleApiClient is not configured to use the Games Api. Pass Games.API into GoogleApiClient.Builder#addApi() to use this feature.");
        return gamesClientImpl;
    }

    public static String getAppId(GoogleApiClient apiClient) {
        return c(apiClient).hj();
    }

    public static String getCurrentAccountName(GoogleApiClient apiClient) {
        return c(apiClient).gU();
    }

    public static int getSdkVariant(GoogleApiClient apiClient) {
        return c(apiClient).hi();
    }

    public static Intent getSettingsIntent(GoogleApiClient apiClient) {
        return c(apiClient).hh();
    }

    public static void setGravityForPopups(GoogleApiClient apiClient, int gravity) {
        c(apiClient).cg(gravity);
    }

    public static void setViewForPopups(GoogleApiClient apiClient, View gamesContentView) {
        hn.f(gamesContentView);
        c(apiClient).f(gamesContentView);
    }

    public static PendingResult<Status> signOut(GoogleApiClient apiClient) {
        return apiClient.b(new SignOutImpl() {
            /* access modifiers changed from: protected */
            public void a(GamesClientImpl gamesClientImpl) {
                gamesClientImpl.b((a.d<Status>) this);
            }
        });
    }
}
