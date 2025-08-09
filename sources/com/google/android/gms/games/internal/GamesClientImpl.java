package com.google.android.gms.games.internal;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.LocalSocket;
import android.net.LocalSocketAddress;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.view.View;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.a;
import com.google.android.gms.common.api.b;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameBuffer;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.GamesMetadata;
import com.google.android.gms.games.Notifications;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerBuffer;
import com.google.android.gms.games.PlayerEntity;
import com.google.android.gms.games.Players;
import com.google.android.gms.games.achievement.AchievementBuffer;
import com.google.android.gms.games.achievement.Achievements;
import com.google.android.gms.games.event.EventBuffer;
import com.google.android.gms.games.event.Events;
import com.google.android.gms.games.internal.IGamesService;
import com.google.android.gms.games.internal.constants.RequestType;
import com.google.android.gms.games.internal.events.EventIncrementCache;
import com.google.android.gms.games.internal.events.EventIncrementManager;
import com.google.android.gms.games.internal.experience.ExperienceEventBuffer;
import com.google.android.gms.games.internal.game.Acls;
import com.google.android.gms.games.internal.game.ExtendedGameBuffer;
import com.google.android.gms.games.internal.game.GameInstanceBuffer;
import com.google.android.gms.games.internal.request.RequestUpdateOutcomes;
import com.google.android.gms.games.leaderboard.Leaderboard;
import com.google.android.gms.games.leaderboard.LeaderboardBuffer;
import com.google.android.gms.games.leaderboard.LeaderboardEntity;
import com.google.android.gms.games.leaderboard.LeaderboardScore;
import com.google.android.gms.games.leaderboard.LeaderboardScoreBuffer;
import com.google.android.gms.games.leaderboard.LeaderboardScoreEntity;
import com.google.android.gms.games.leaderboard.Leaderboards;
import com.google.android.gms.games.leaderboard.ScoreSubmissionData;
import com.google.android.gms.games.multiplayer.Invitation;
import com.google.android.gms.games.multiplayer.InvitationBuffer;
import com.google.android.gms.games.multiplayer.Invitations;
import com.google.android.gms.games.multiplayer.OnInvitationReceivedListener;
import com.google.android.gms.games.multiplayer.ParticipantResult;
import com.google.android.gms.games.multiplayer.ParticipantUtils;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMessage;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMessageReceivedListener;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMultiplayer;
import com.google.android.gms.games.multiplayer.realtime.RealTimeSocket;
import com.google.android.gms.games.multiplayer.realtime.Room;
import com.google.android.gms.games.multiplayer.realtime.RoomBuffer;
import com.google.android.gms.games.multiplayer.realtime.RoomConfig;
import com.google.android.gms.games.multiplayer.realtime.RoomEntity;
import com.google.android.gms.games.multiplayer.realtime.RoomStatusUpdateListener;
import com.google.android.gms.games.multiplayer.realtime.RoomUpdateListener;
import com.google.android.gms.games.multiplayer.turnbased.LoadMatchesResponse;
import com.google.android.gms.games.multiplayer.turnbased.OnTurnBasedMatchUpdateReceivedListener;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatch;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatchBuffer;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatchConfig;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer;
import com.google.android.gms.games.quest.Milestone;
import com.google.android.gms.games.quest.Quest;
import com.google.android.gms.games.quest.QuestBuffer;
import com.google.android.gms.games.quest.QuestEntity;
import com.google.android.gms.games.quest.QuestUpdateListener;
import com.google.android.gms.games.quest.Quests;
import com.google.android.gms.games.request.GameRequest;
import com.google.android.gms.games.request.GameRequestBuffer;
import com.google.android.gms.games.request.OnRequestReceivedListener;
import com.google.android.gms.games.request.Requests;
import com.google.android.gms.games.snapshot.Snapshot;
import com.google.android.gms.games.snapshot.SnapshotEntity;
import com.google.android.gms.games.snapshot.SnapshotMetadata;
import com.google.android.gms.games.snapshot.SnapshotMetadataBuffer;
import com.google.android.gms.games.snapshot.SnapshotMetadataChange;
import com.google.android.gms.games.snapshot.SnapshotMetadataEntity;
import com.google.android.gms.games.snapshot.Snapshots;
import com.google.android.gms.internal.gy;
import com.google.android.gms.internal.hc;
import com.google.android.gms.internal.hj;
import com.google.android.gms.internal.hn;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class GamesClientImpl extends hc<IGamesService> implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    private final String NA;
    EventIncrementManager Nm = new EventIncrementManager() {
        public EventIncrementCache hs() {
            return new GameClientEventIncrementCache();
        }
    };
    private final String Nn;
    private final Map<String, RealTimeSocket> No;
    private PlayerEntity Np;
    private GameEntity Nq;
    private final PopupManager Nr;
    private boolean Ns = false;
    private boolean Nt = false;
    private int Nu;
    private final Binder Nv;
    private final long Nw;
    private final boolean Nx;
    private final int Ny;
    private final boolean Nz;
    private final String yN;

    private abstract class AbstractPeerStatusCallback extends AbstractRoomStatusCallback {
        private final ArrayList<String> NC = new ArrayList<>();

        AbstractPeerStatusCallback(RoomStatusUpdateListener listener, DataHolder dataHolder, String[] participantIds) {
            super(listener, dataHolder);
            for (String add : participantIds) {
                this.NC.add(add);
            }
        }

        /* access modifiers changed from: protected */
        public void a(RoomStatusUpdateListener roomStatusUpdateListener, Room room) {
            a(roomStatusUpdateListener, room, this.NC);
        }

        /* access modifiers changed from: protected */
        public abstract void a(RoomStatusUpdateListener roomStatusUpdateListener, Room room, ArrayList<String> arrayList);
    }

    private abstract class AbstractRoomCallback extends hc<IGamesService>.d<RoomUpdateListener> {
        AbstractRoomCallback(RoomUpdateListener listener, DataHolder dataHolder) {
            super(listener, dataHolder);
        }

        /* access modifiers changed from: protected */
        public void a(RoomUpdateListener roomUpdateListener, DataHolder dataHolder) {
            a(roomUpdateListener, GamesClientImpl.this.Q(dataHolder), dataHolder.getStatusCode());
        }

        /* access modifiers changed from: protected */
        public abstract void a(RoomUpdateListener roomUpdateListener, Room room, int i);
    }

    private abstract class AbstractRoomStatusCallback extends hc<IGamesService>.d<RoomStatusUpdateListener> {
        AbstractRoomStatusCallback(RoomStatusUpdateListener listener, DataHolder dataHolder) {
            super(listener, dataHolder);
        }

        /* access modifiers changed from: protected */
        public void a(RoomStatusUpdateListener roomStatusUpdateListener, DataHolder dataHolder) {
            a(roomStatusUpdateListener, GamesClientImpl.this.Q(dataHolder));
        }

        /* access modifiers changed from: protected */
        public abstract void a(RoomStatusUpdateListener roomStatusUpdateListener, Room room);
    }

    private static final class AcceptQuestResultImpl extends b implements Quests.AcceptQuestResult {
        private final Quest ND;

        AcceptQuestResultImpl(DataHolder dataHolder) {
            super(dataHolder);
            QuestBuffer questBuffer = new QuestBuffer(dataHolder);
            try {
                if (questBuffer.getCount() > 0) {
                    this.ND = new QuestEntity((Quest) questBuffer.get(0));
                } else {
                    this.ND = null;
                }
            } finally {
                questBuffer.close();
            }
        }

        public Quest getQuest() {
            return this.ND;
        }
    }

    private final class AchievementUpdatedBinderCallback extends AbstractGamesCallbacks {
        private final a.d<Achievements.UpdateAchievementResult> yO;

        AchievementUpdatedBinderCallback(a.d<Achievements.UpdateAchievementResult> resultHolder) {
            this.yO = (a.d) hn.b(resultHolder, (Object) "Holder must not be null");
        }

        public void e(int i, String str) {
            this.yO.a(new UpdateAchievementResultImpl(i, str));
        }
    }

    private final class AchievementsLoadedBinderCallback extends AbstractGamesCallbacks {
        private final a.d<Achievements.LoadAchievementsResult> yO;

        AchievementsLoadedBinderCallback(a.d<Achievements.LoadAchievementsResult> resultHolder) {
            this.yO = (a.d) hn.b(resultHolder, (Object) "Holder must not be null");
        }

        public void c(DataHolder dataHolder) {
            this.yO.a(new LoadAchievementsResultImpl(dataHolder));
        }
    }

    private static final class CancelMatchResultImpl implements TurnBasedMultiplayer.CancelMatchResult {
        private final String NE;
        private final Status yw;

        CancelMatchResultImpl(Status status, String externalMatchId) {
            this.yw = status;
            this.NE = externalMatchId;
        }

        public String getMatchId() {
            return this.NE;
        }

        public Status getStatus() {
            return this.yw;
        }
    }

    private static final class ClaimMilestoneResultImpl extends b implements Quests.ClaimMilestoneResult {
        private final Quest ND;
        private final Milestone NF;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        ClaimMilestoneResultImpl(DataHolder dataHolder, String milestoneId) {
            super(dataHolder);
            QuestBuffer questBuffer = new QuestBuffer(dataHolder);
            try {
                if (questBuffer.getCount() > 0) {
                    this.ND = new QuestEntity((Quest) questBuffer.get(0));
                    List<Milestone> iE = this.ND.iE();
                    int size = iE.size();
                    for (int i = 0; i < size; i++) {
                        if (iE.get(i).getMilestoneId().equals(milestoneId)) {
                            this.NF = iE.get(i);
                            return;
                        }
                    }
                    this.NF = null;
                } else {
                    this.NF = null;
                    this.ND = null;
                }
                questBuffer.close();
            } finally {
                questBuffer.close();
            }
        }

        public Milestone getMilestone() {
            return this.NF;
        }

        public Quest getQuest() {
            return this.ND;
        }
    }

    private static final class CommitSnapshotResultImpl extends b implements Snapshots.CommitSnapshotResult {
        private final SnapshotMetadata NG;

        CommitSnapshotResultImpl(DataHolder dataHolder) {
            super(dataHolder);
            SnapshotMetadataBuffer snapshotMetadataBuffer = new SnapshotMetadataBuffer(dataHolder);
            try {
                if (snapshotMetadataBuffer.getCount() > 0) {
                    this.NG = new SnapshotMetadataEntity(snapshotMetadataBuffer.get(0));
                } else {
                    this.NG = null;
                }
            } finally {
                snapshotMetadataBuffer.close();
            }
        }

        public SnapshotMetadata getSnapshotMetadata() {
            return this.NG;
        }
    }

    private final class ConnectedToRoomCallback extends AbstractRoomStatusCallback {
        ConnectedToRoomCallback(RoomStatusUpdateListener listener, DataHolder dataHolder) {
            super(listener, dataHolder);
        }

        public void a(RoomStatusUpdateListener roomStatusUpdateListener, Room room) {
            roomStatusUpdateListener.onConnectedToRoom(room);
        }
    }

    private static final class ContactSettingLoadResultImpl extends b implements Notifications.ContactSettingLoadResult {
        ContactSettingLoadResultImpl(DataHolder dataHolder) {
            super(dataHolder);
        }
    }

    private final class ContactSettingsLoadedBinderCallback extends AbstractGamesCallbacks {
        private final a.d<Notifications.ContactSettingLoadResult> yO;

        ContactSettingsLoadedBinderCallback(a.d<Notifications.ContactSettingLoadResult> holder) {
            this.yO = (a.d) hn.b(holder, (Object) "Holder must not be null");
        }

        public void D(DataHolder dataHolder) {
            this.yO.a(new ContactSettingLoadResultImpl(dataHolder));
        }
    }

    private final class ContactSettingsUpdatedBinderCallback extends AbstractGamesCallbacks {
        private final a.d<Status> yO;

        ContactSettingsUpdatedBinderCallback(a.d<Status> holder) {
            this.yO = (a.d) hn.b(holder, (Object) "Holder must not be null");
        }

        public void ce(int i) {
            this.yO.a(new Status(i));
        }
    }

    private static final class DeleteSnapshotResultImpl implements Snapshots.DeleteSnapshotResult {
        private final String NH;
        private final Status yw;

        DeleteSnapshotResultImpl(int statusCode, String snapshotId) {
            this.yw = new Status(statusCode);
            this.NH = snapshotId;
        }

        public String getSnapshotId() {
            return this.NH;
        }

        public Status getStatus() {
            return this.yw;
        }
    }

    private final class DisconnectedFromRoomCallback extends AbstractRoomStatusCallback {
        DisconnectedFromRoomCallback(RoomStatusUpdateListener listener, DataHolder dataHolder) {
            super(listener, dataHolder);
        }

        public void a(RoomStatusUpdateListener roomStatusUpdateListener, Room room) {
            roomStatusUpdateListener.onDisconnectedFromRoom(room);
        }
    }

    private final class EventsLoadedBinderCallback extends AbstractGamesCallbacks {
        private final a.d<Events.LoadEventsResult> yO;

        EventsLoadedBinderCallback(a.d<Events.LoadEventsResult> resultHolder) {
            this.yO = (a.d) hn.b(resultHolder, (Object) "Holder must not be null");
        }

        public void d(DataHolder dataHolder) {
            this.yO.a(new LoadEventResultImpl(dataHolder));
        }
    }

    private final class ExtendedGamesLoadedBinderCallback extends AbstractGamesCallbacks {
        private final a.d<GamesMetadata.LoadExtendedGamesResult> yO;

        ExtendedGamesLoadedBinderCallback(a.d<GamesMetadata.LoadExtendedGamesResult> holder) {
            this.yO = (a.d) hn.b(holder, (Object) "Holder must not be null");
        }

        public void j(DataHolder dataHolder) {
            this.yO.a(new LoadExtendedGamesResultImpl(dataHolder));
        }
    }

    private class GameClientEventIncrementCache extends EventIncrementCache {
        public GameClientEventIncrementCache() {
            super(GamesClientImpl.this.getContext().getMainLooper(), 1000);
        }

        /* access modifiers changed from: protected */
        public void o(String str, int i) {
            try {
                ((IGamesService) GamesClientImpl.this.fo()).l(str, i);
            } catch (RemoteException e) {
                GamesLog.j("GamesClientImpl", "service died");
            }
        }
    }

    private final class GameInstancesLoadedBinderCallback extends AbstractGamesCallbacks {
        private final a.d<GamesMetadata.LoadGameInstancesResult> yO;

        GameInstancesLoadedBinderCallback(a.d<GamesMetadata.LoadGameInstancesResult> holder) {
            this.yO = (a.d) hn.b(holder, (Object) "Holder must not be null");
        }

        public void k(DataHolder dataHolder) {
            this.yO.a(new LoadGameInstancesResultImpl(dataHolder));
        }
    }

    private static final class GameMuteStatusChangeResultImpl implements Notifications.GameMuteStatusChangeResult {
        private final String NI;
        private final boolean NJ;
        private final Status yw;

        public GameMuteStatusChangeResultImpl(int statusCode, String externalGameId, boolean isMuted) {
            this.yw = new Status(statusCode);
            this.NI = externalGameId;
            this.NJ = isMuted;
        }

        public Status getStatus() {
            return this.yw;
        }
    }

    private final class GameMuteStatusChangedBinderCallback extends AbstractGamesCallbacks {
        private final a.d<Notifications.GameMuteStatusChangeResult> yO;

        GameMuteStatusChangedBinderCallback(a.d<Notifications.GameMuteStatusChangeResult> holder) {
            this.yO = (a.d) hn.b(holder, (Object) "Holder must not be null");
        }

        public void a(int i, String str, boolean z) {
            this.yO.a(new GameMuteStatusChangeResultImpl(i, str, z));
        }
    }

    private static final class GameMuteStatusLoadResultImpl implements Notifications.GameMuteStatusLoadResult {
        private final String NI;
        private final boolean NJ;
        private final Status yw;

        public GameMuteStatusLoadResultImpl(DataHolder dataHolder) {
            try {
                this.yw = new Status(dataHolder.getStatusCode());
                if (dataHolder.getCount() > 0) {
                    this.NI = dataHolder.c("external_game_id", 0, 0);
                    this.NJ = dataHolder.d("muted", 0, 0);
                } else {
                    this.NI = null;
                    this.NJ = false;
                }
            } finally {
                dataHolder.close();
            }
        }

        public Status getStatus() {
            return this.yw;
        }
    }

    private final class GameMuteStatusLoadedBinderCallback extends AbstractGamesCallbacks {
        private final a.d<Notifications.GameMuteStatusLoadResult> yO;

        GameMuteStatusLoadedBinderCallback(a.d<Notifications.GameMuteStatusLoadResult> holder) {
            this.yO = (a.d) hn.b(holder, (Object) "Holder must not be null");
        }

        public void B(DataHolder dataHolder) {
            this.yO.a(new GameMuteStatusLoadResultImpl(dataHolder));
        }
    }

    private final class GameSearchSuggestionsLoadedBinderCallback extends AbstractGamesCallbacks {
        private final a.d<GamesMetadata.LoadGameSearchSuggestionsResult> yO;

        GameSearchSuggestionsLoadedBinderCallback(a.d<GamesMetadata.LoadGameSearchSuggestionsResult> holder) {
            this.yO = (a.d) hn.b(holder, (Object) "Holder must not be null");
        }

        public void l(DataHolder dataHolder) {
            this.yO.a(new LoadGameSearchSuggestionsResultImpl(dataHolder));
        }
    }

    private final class GamesLoadedBinderCallback extends AbstractGamesCallbacks {
        private final a.d<GamesMetadata.LoadGamesResult> yO;

        GamesLoadedBinderCallback(a.d<GamesMetadata.LoadGamesResult> holder) {
            this.yO = (a.d) hn.b(holder, (Object) "Holder must not be null");
        }

        public void i(DataHolder dataHolder) {
            this.yO.a(new LoadGamesResultImpl(dataHolder));
        }
    }

    private static final class InboxCountResultImpl implements Notifications.InboxCountResult {
        private final Bundle NK;
        private final Status yw;

        InboxCountResultImpl(Status status, Bundle inboxCounts) {
            this.yw = status;
            this.NK = inboxCounts;
        }

        public Status getStatus() {
            return this.yw;
        }
    }

    private final class InboxCountsLoadedBinderCallback extends AbstractGamesCallbacks {
        private final a.d<Notifications.InboxCountResult> yO;

        InboxCountsLoadedBinderCallback(a.d<Notifications.InboxCountResult> holder) {
            this.yO = (a.d) hn.b(holder, (Object) "Holder must not be null");
        }

        public void f(int i, Bundle bundle) {
            bundle.setClassLoader(getClass().getClassLoader());
            this.yO.a(new InboxCountResultImpl(new Status(i), bundle));
        }
    }

    private static final class InitiateMatchResultImpl extends TurnBasedMatchResult implements TurnBasedMultiplayer.InitiateMatchResult {
        InitiateMatchResultImpl(DataHolder dataHolder) {
            super(dataHolder);
        }
    }

    private final class InvitationReceivedBinderCallback extends AbstractGamesCallbacks {
        private final OnInvitationReceivedListener NL;

        InvitationReceivedBinderCallback(OnInvitationReceivedListener listener) {
            this.NL = listener;
        }

        public void n(DataHolder dataHolder) {
            InvitationBuffer invitationBuffer = new InvitationBuffer(dataHolder);
            Invitation invitation = null;
            try {
                if (invitationBuffer.getCount() > 0) {
                    invitation = (Invitation) ((Invitation) invitationBuffer.get(0)).freeze();
                }
                if (invitation != null) {
                    GamesClientImpl.this.a((hc<T>.b<?>) new InvitationReceivedCallback(this.NL, invitation));
                }
            } finally {
                invitationBuffer.close();
            }
        }

        public void onInvitationRemoved(String invitationId) {
            GamesClientImpl.this.a((hc<T>.b<?>) new InvitationRemovedCallback(this.NL, invitationId));
        }
    }

    private final class InvitationReceivedCallback extends hc<IGamesService>.b<OnInvitationReceivedListener> {
        private final Invitation NM;

        InvitationReceivedCallback(OnInvitationReceivedListener listener, Invitation invitation) {
            super(listener);
            this.NM = invitation;
        }

        /* access modifiers changed from: protected */
        /* renamed from: b */
        public void d(OnInvitationReceivedListener onInvitationReceivedListener) {
            onInvitationReceivedListener.onInvitationReceived(this.NM);
        }

        /* access modifiers changed from: protected */
        public void fp() {
        }
    }

    private final class InvitationRemovedCallback extends hc<IGamesService>.b<OnInvitationReceivedListener> {
        private final String NN;

        InvitationRemovedCallback(OnInvitationReceivedListener listener, String invitationId) {
            super(listener);
            this.NN = invitationId;
        }

        /* access modifiers changed from: protected */
        /* renamed from: b */
        public void d(OnInvitationReceivedListener onInvitationReceivedListener) {
            onInvitationReceivedListener.onInvitationRemoved(this.NN);
        }

        /* access modifiers changed from: protected */
        public void fp() {
        }
    }

    private final class InvitationsLoadedBinderCallback extends AbstractGamesCallbacks {
        private final a.d<Invitations.LoadInvitationsResult> yO;

        InvitationsLoadedBinderCallback(a.d<Invitations.LoadInvitationsResult> resultHolder) {
            this.yO = (a.d) hn.b(resultHolder, (Object) "Holder must not be null");
        }

        public void m(DataHolder dataHolder) {
            this.yO.a(new LoadInvitationsResultImpl(dataHolder));
        }
    }

    private final class JoinedRoomCallback extends AbstractRoomCallback {
        public JoinedRoomCallback(RoomUpdateListener listener, DataHolder dataHolder) {
            super(listener, dataHolder);
        }

        public void a(RoomUpdateListener roomUpdateListener, Room room, int i) {
            roomUpdateListener.onJoinedRoom(i, room);
        }
    }

    private static final class LeaderboardMetadataResultImpl extends b implements Leaderboards.LeaderboardMetadataResult {
        private final LeaderboardBuffer NO;

        LeaderboardMetadataResultImpl(DataHolder dataHolder) {
            super(dataHolder);
            this.NO = new LeaderboardBuffer(dataHolder);
        }

        public LeaderboardBuffer getLeaderboards() {
            return this.NO;
        }
    }

    private final class LeaderboardScoresLoadedBinderCallback extends AbstractGamesCallbacks {
        private final a.d<Leaderboards.LoadScoresResult> yO;

        LeaderboardScoresLoadedBinderCallback(a.d<Leaderboards.LoadScoresResult> resultHolder) {
            this.yO = (a.d) hn.b(resultHolder, (Object) "Holder must not be null");
        }

        public void a(DataHolder dataHolder, DataHolder dataHolder2) {
            this.yO.a(new LoadScoresResultImpl(dataHolder, dataHolder2));
        }
    }

    private final class LeaderboardsLoadedBinderCallback extends AbstractGamesCallbacks {
        private final a.d<Leaderboards.LeaderboardMetadataResult> yO;

        LeaderboardsLoadedBinderCallback(a.d<Leaderboards.LeaderboardMetadataResult> resultHolder) {
            this.yO = (a.d) hn.b(resultHolder, (Object) "Holder must not be null");
        }

        public void e(DataHolder dataHolder) {
            this.yO.a(new LeaderboardMetadataResultImpl(dataHolder));
        }
    }

    private static final class LeaveMatchResultImpl extends TurnBasedMatchResult implements TurnBasedMultiplayer.LeaveMatchResult {
        LeaveMatchResultImpl(DataHolder dataHolder) {
            super(dataHolder);
        }
    }

    private final class LeftRoomCallback extends hc<IGamesService>.b<RoomUpdateListener> {
        private final int CQ;
        private final String NP;

        LeftRoomCallback(RoomUpdateListener listener, int statusCode, String roomId) {
            super(listener);
            this.CQ = statusCode;
            this.NP = roomId;
        }

        /* renamed from: a */
        public void d(RoomUpdateListener roomUpdateListener) {
            roomUpdateListener.onLeftRoom(this.CQ, this.NP);
        }

        /* access modifiers changed from: protected */
        public void fp() {
        }
    }

    private static final class LoadAchievementsResultImpl extends b implements Achievements.LoadAchievementsResult {
        private final AchievementBuffer NQ;

        LoadAchievementsResultImpl(DataHolder dataHolder) {
            super(dataHolder);
            this.NQ = new AchievementBuffer(dataHolder);
        }

        public AchievementBuffer getAchievements() {
            return this.NQ;
        }
    }

    private static final class LoadAclResultImpl extends b implements Acls.LoadAclResult {
        LoadAclResultImpl(DataHolder dataHolder) {
            super(dataHolder);
        }
    }

    private static final class LoadEventResultImpl extends b implements Events.LoadEventsResult {
        private final EventBuffer NR;

        LoadEventResultImpl(DataHolder dataHolder) {
            super(dataHolder);
            this.NR = new EventBuffer(dataHolder);
        }

        public EventBuffer getEvents() {
            return this.NR;
        }
    }

    private static final class LoadExtendedGamesResultImpl extends b implements GamesMetadata.LoadExtendedGamesResult {
        private final ExtendedGameBuffer NS;

        LoadExtendedGamesResultImpl(DataHolder dataHolder) {
            super(dataHolder);
            this.NS = new ExtendedGameBuffer(dataHolder);
        }
    }

    private static final class LoadGameInstancesResultImpl extends b implements GamesMetadata.LoadGameInstancesResult {
        private final GameInstanceBuffer NT;

        LoadGameInstancesResultImpl(DataHolder dataHolder) {
            super(dataHolder);
            this.NT = new GameInstanceBuffer(dataHolder);
        }
    }

    private static final class LoadGameSearchSuggestionsResultImpl extends b implements GamesMetadata.LoadGameSearchSuggestionsResult {
        LoadGameSearchSuggestionsResultImpl(DataHolder data) {
            super(data);
        }
    }

    private static final class LoadGamesResultImpl extends b implements GamesMetadata.LoadGamesResult {
        private final GameBuffer NU;

        LoadGamesResultImpl(DataHolder dataHolder) {
            super(dataHolder);
            this.NU = new GameBuffer(dataHolder);
        }

        public GameBuffer getGames() {
            return this.NU;
        }
    }

    private static final class LoadInvitationsResultImpl extends b implements Invitations.LoadInvitationsResult {
        private final InvitationBuffer NV;

        LoadInvitationsResultImpl(DataHolder dataHolder) {
            super(dataHolder);
            this.NV = new InvitationBuffer(dataHolder);
        }

        public InvitationBuffer getInvitations() {
            return this.NV;
        }
    }

    private static final class LoadMatchResultImpl extends TurnBasedMatchResult implements TurnBasedMultiplayer.LoadMatchResult {
        LoadMatchResultImpl(DataHolder dataHolder) {
            super(dataHolder);
        }
    }

    private static final class LoadMatchesResultImpl implements TurnBasedMultiplayer.LoadMatchesResult {
        private final LoadMatchesResponse NW;
        private final Status yw;

        LoadMatchesResultImpl(Status status, Bundle matchData) {
            this.yw = status;
            this.NW = new LoadMatchesResponse(matchData);
        }

        public LoadMatchesResponse getMatches() {
            return this.NW;
        }

        public Status getStatus() {
            return this.yw;
        }

        public void release() {
            this.NW.close();
        }
    }

    private static final class LoadOwnerCoverPhotoUrisResultImpl implements Players.LoadOwnerCoverPhotoUrisResult {
        private final Bundle HJ;
        private final Status yw;

        LoadOwnerCoverPhotoUrisResultImpl(int statusCode, Bundle bundle) {
            this.yw = new Status(statusCode);
            this.HJ = bundle;
        }

        public Status getStatus() {
            return this.yw;
        }
    }

    private static final class LoadPlayerScoreResultImpl extends b implements Leaderboards.LoadPlayerScoreResult {
        private final LeaderboardScoreEntity NX;

        LoadPlayerScoreResultImpl(DataHolder scoreHolder) {
            super(scoreHolder);
            LeaderboardScoreBuffer leaderboardScoreBuffer = new LeaderboardScoreBuffer(scoreHolder);
            try {
                if (leaderboardScoreBuffer.getCount() > 0) {
                    this.NX = (LeaderboardScoreEntity) leaderboardScoreBuffer.get(0).freeze();
                } else {
                    this.NX = null;
                }
            } finally {
                leaderboardScoreBuffer.close();
            }
        }

        public LeaderboardScore getScore() {
            return this.NX;
        }
    }

    private static final class LoadPlayersResultImpl extends b implements Players.LoadPlayersResult {
        private final PlayerBuffer NY;

        LoadPlayersResultImpl(DataHolder dataHolder) {
            super(dataHolder);
            this.NY = new PlayerBuffer(dataHolder);
        }

        public PlayerBuffer getPlayers() {
            return this.NY;
        }
    }

    private static final class LoadQuestsResultImpl extends b implements Quests.LoadQuestsResult {
        private final DataHolder DD;

        LoadQuestsResultImpl(DataHolder dataHolder) {
            super(dataHolder);
            this.DD = dataHolder;
        }

        public QuestBuffer getQuests() {
            return new QuestBuffer(this.DD);
        }
    }

    private static final class LoadRequestSummariesResultImpl extends b implements Requests.LoadRequestSummariesResult {
        LoadRequestSummariesResultImpl(DataHolder dataHolder) {
            super(dataHolder);
        }
    }

    private static final class LoadRequestsResultImpl implements Requests.LoadRequestsResult {
        private final Bundle NZ;
        private final Status yw;

        LoadRequestsResultImpl(Status status, Bundle requestData) {
            this.yw = status;
            this.NZ = requestData;
        }

        public GameRequestBuffer getRequests(int requestType) {
            String cm = RequestType.cm(requestType);
            if (!this.NZ.containsKey(cm)) {
                return null;
            }
            return new GameRequestBuffer((DataHolder) this.NZ.get(cm));
        }

        public Status getStatus() {
            return this.yw;
        }

        public void release() {
            for (String parcelable : this.NZ.keySet()) {
                DataHolder dataHolder = (DataHolder) this.NZ.getParcelable(parcelable);
                if (dataHolder != null) {
                    dataHolder.close();
                }
            }
        }
    }

    private static final class LoadScoresResultImpl extends b implements Leaderboards.LoadScoresResult {
        private final LeaderboardEntity Oa;
        private final LeaderboardScoreBuffer Ob;

        /* JADX INFO: finally extract failed */
        LoadScoresResultImpl(DataHolder leaderboard, DataHolder scores) {
            super(scores);
            LeaderboardBuffer leaderboardBuffer = new LeaderboardBuffer(leaderboard);
            try {
                if (leaderboardBuffer.getCount() > 0) {
                    this.Oa = (LeaderboardEntity) ((Leaderboard) leaderboardBuffer.get(0)).freeze();
                } else {
                    this.Oa = null;
                }
                leaderboardBuffer.close();
                this.Ob = new LeaderboardScoreBuffer(scores);
            } catch (Throwable th) {
                leaderboardBuffer.close();
                throw th;
            }
        }

        public Leaderboard getLeaderboard() {
            return this.Oa;
        }

        public LeaderboardScoreBuffer getScores() {
            return this.Ob;
        }
    }

    private static final class LoadSnapshotsResultImpl extends b implements Snapshots.LoadSnapshotsResult {
        LoadSnapshotsResultImpl(DataHolder dataHolder) {
            super(dataHolder);
        }

        public SnapshotMetadataBuffer getSnapshots() {
            return new SnapshotMetadataBuffer(this.DD);
        }
    }

    private static final class LoadXpForGameCategoriesResultImpl implements Players.LoadXpForGameCategoriesResult {
        private final List<String> Oc;
        private final Bundle Od;
        private final Status yw;

        LoadXpForGameCategoriesResultImpl(Status status, Bundle xpData) {
            this.yw = status;
            this.Oc = xpData.getStringArrayList("game_category_list");
            this.Od = xpData;
        }

        public Status getStatus() {
            return this.yw;
        }
    }

    private static final class LoadXpStreamResultImpl extends b implements Players.LoadXpStreamResult {
        private final ExperienceEventBuffer Oe;

        LoadXpStreamResultImpl(DataHolder dataHolder) {
            super(dataHolder);
            this.Oe = new ExperienceEventBuffer(dataHolder);
        }
    }

    private final class MatchRemovedCallback extends hc<IGamesService>.b<OnTurnBasedMatchUpdateReceivedListener> {
        private final String Of;

        MatchRemovedCallback(OnTurnBasedMatchUpdateReceivedListener listener, String matchId) {
            super(listener);
            this.Of = matchId;
        }

        /* access modifiers changed from: protected */
        /* renamed from: b */
        public void d(OnTurnBasedMatchUpdateReceivedListener onTurnBasedMatchUpdateReceivedListener) {
            onTurnBasedMatchUpdateReceivedListener.onTurnBasedMatchRemoved(this.Of);
        }

        /* access modifiers changed from: protected */
        public void fp() {
        }
    }

    private final class MatchUpdateReceivedBinderCallback extends AbstractGamesCallbacks {
        private final OnTurnBasedMatchUpdateReceivedListener Og;

        MatchUpdateReceivedBinderCallback(OnTurnBasedMatchUpdateReceivedListener listener) {
            this.Og = listener;
        }

        public void onTurnBasedMatchRemoved(String matchId) {
            GamesClientImpl.this.a((hc<T>.b<?>) new MatchRemovedCallback(this.Og, matchId));
        }

        public void t(DataHolder dataHolder) {
            TurnBasedMatchBuffer turnBasedMatchBuffer = new TurnBasedMatchBuffer(dataHolder);
            TurnBasedMatch turnBasedMatch = null;
            try {
                if (turnBasedMatchBuffer.getCount() > 0) {
                    turnBasedMatch = (TurnBasedMatch) ((TurnBasedMatch) turnBasedMatchBuffer.get(0)).freeze();
                }
                if (turnBasedMatch != null) {
                    GamesClientImpl.this.a((hc<T>.b<?>) new MatchUpdateReceivedCallback(this.Og, turnBasedMatch));
                }
            } finally {
                turnBasedMatchBuffer.close();
            }
        }
    }

    private final class MatchUpdateReceivedCallback extends hc<IGamesService>.b<OnTurnBasedMatchUpdateReceivedListener> {
        private final TurnBasedMatch Oh;

        MatchUpdateReceivedCallback(OnTurnBasedMatchUpdateReceivedListener listener, TurnBasedMatch match) {
            super(listener);
            this.Oh = match;
        }

        /* access modifiers changed from: protected */
        /* renamed from: b */
        public void d(OnTurnBasedMatchUpdateReceivedListener onTurnBasedMatchUpdateReceivedListener) {
            onTurnBasedMatchUpdateReceivedListener.onTurnBasedMatchReceived(this.Oh);
        }

        /* access modifiers changed from: protected */
        public void fp() {
        }
    }

    private final class MessageReceivedCallback extends hc<IGamesService>.b<RealTimeMessageReceivedListener> {
        private final RealTimeMessage Oi;

        MessageReceivedCallback(RealTimeMessageReceivedListener listener, RealTimeMessage message) {
            super(listener);
            this.Oi = message;
        }

        /* renamed from: a */
        public void d(RealTimeMessageReceivedListener realTimeMessageReceivedListener) {
            if (realTimeMessageReceivedListener != null) {
                realTimeMessageReceivedListener.onRealTimeMessageReceived(this.Oi);
            }
        }

        /* access modifiers changed from: protected */
        public void fp() {
        }
    }

    private final class NotifyAclLoadedBinderCallback extends AbstractGamesCallbacks {
        private final a.d<Acls.LoadAclResult> yO;

        NotifyAclLoadedBinderCallback(a.d<Acls.LoadAclResult> resultHolder) {
            this.yO = (a.d) hn.b(resultHolder, (Object) "Holder must not be null");
        }

        public void C(DataHolder dataHolder) {
            this.yO.a(new LoadAclResultImpl(dataHolder));
        }
    }

    private final class NotifyAclUpdatedBinderCallback extends AbstractGamesCallbacks {
        private final a.d<Status> yO;

        NotifyAclUpdatedBinderCallback(a.d<Status> resultHolder) {
            this.yO = (a.d) hn.b(resultHolder, (Object) "Holder must not be null");
        }

        public void cd(int i) {
            this.yO.a(new Status(i));
        }
    }

    private static final class OpenSnapshotResultImpl extends b implements Snapshots.OpenSnapshotResult {
        private final Snapshot Oj;
        private final String Ok;
        private final Snapshot Ol;
        private final Contents Om;

        OpenSnapshotResultImpl(DataHolder dataHolder, Contents currentContents) {
            this(dataHolder, (String) null, currentContents, (Contents) null, (Contents) null);
        }

        /* JADX INFO: finally extract failed */
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        OpenSnapshotResultImpl(DataHolder metadataHolder, String conflictId, Contents currentContents, Contents conflictContents, Contents resolutionContents) {
            super(metadataHolder);
            boolean z = true;
            SnapshotMetadataBuffer snapshotMetadataBuffer = new SnapshotMetadataBuffer(metadataHolder);
            try {
                if (snapshotMetadataBuffer.getCount() == 0) {
                    this.Oj = null;
                    this.Ol = null;
                } else if (snapshotMetadataBuffer.getCount() == 1) {
                    gy.A(metadataHolder.getStatusCode() == 4004 ? false : z);
                    this.Oj = new SnapshotEntity(new SnapshotMetadataEntity(snapshotMetadataBuffer.get(0)), currentContents);
                    this.Ol = null;
                } else {
                    this.Oj = new SnapshotEntity(new SnapshotMetadataEntity(snapshotMetadataBuffer.get(0)), currentContents);
                    this.Ol = new SnapshotEntity(new SnapshotMetadataEntity(snapshotMetadataBuffer.get(1)), conflictContents);
                }
                snapshotMetadataBuffer.close();
                this.Ok = conflictId;
                this.Om = resolutionContents;
            } catch (Throwable th) {
                snapshotMetadataBuffer.close();
                throw th;
            }
        }

        public String getConflictId() {
            return this.Ok;
        }

        public Snapshot getConflictingSnapshot() {
            return this.Ol;
        }

        public Contents getResolutionContents() {
            return this.Om;
        }

        public Snapshot getSnapshot() {
            return this.Oj;
        }
    }

    private final class OwnerCoverPhotoUrisLoadedBinderCallback extends AbstractGamesCallbacks {
        private final a.d<Players.LoadOwnerCoverPhotoUrisResult> yO;

        OwnerCoverPhotoUrisLoadedBinderCallback(a.d<Players.LoadOwnerCoverPhotoUrisResult> holder) {
            this.yO = (a.d) hn.b(holder, (Object) "Holder must not be null");
        }

        public void d(int i, Bundle bundle) {
            bundle.setClassLoader(getClass().getClassLoader());
            this.yO.a(new LoadOwnerCoverPhotoUrisResultImpl(i, bundle));
        }
    }

    private final class P2PConnectedCallback extends hc<IGamesService>.b<RoomStatusUpdateListener> {
        private final String On;

        P2PConnectedCallback(RoomStatusUpdateListener listener, String participantId) {
            super(listener);
            this.On = participantId;
        }

        /* renamed from: a */
        public void d(RoomStatusUpdateListener roomStatusUpdateListener) {
            if (roomStatusUpdateListener != null) {
                roomStatusUpdateListener.onP2PConnected(this.On);
            }
        }

        /* access modifiers changed from: protected */
        public void fp() {
        }
    }

    private final class P2PDisconnectedCallback extends hc<IGamesService>.b<RoomStatusUpdateListener> {
        private final String On;

        P2PDisconnectedCallback(RoomStatusUpdateListener listener, String participantId) {
            super(listener);
            this.On = participantId;
        }

        /* renamed from: a */
        public void d(RoomStatusUpdateListener roomStatusUpdateListener) {
            if (roomStatusUpdateListener != null) {
                roomStatusUpdateListener.onP2PDisconnected(this.On);
            }
        }

        /* access modifiers changed from: protected */
        public void fp() {
        }
    }

    private final class PeerConnectedCallback extends AbstractPeerStatusCallback {
        PeerConnectedCallback(RoomStatusUpdateListener listener, DataHolder dataHolder, String[] participantIds) {
            super(listener, dataHolder, participantIds);
        }

        /* access modifiers changed from: protected */
        public void a(RoomStatusUpdateListener roomStatusUpdateListener, Room room, ArrayList<String> arrayList) {
            roomStatusUpdateListener.onPeersConnected(room, arrayList);
        }
    }

    private final class PeerDeclinedCallback extends AbstractPeerStatusCallback {
        PeerDeclinedCallback(RoomStatusUpdateListener listener, DataHolder dataHolder, String[] participantIds) {
            super(listener, dataHolder, participantIds);
        }

        /* access modifiers changed from: protected */
        public void a(RoomStatusUpdateListener roomStatusUpdateListener, Room room, ArrayList<String> arrayList) {
            roomStatusUpdateListener.onPeerDeclined(room, arrayList);
        }
    }

    private final class PeerDisconnectedCallback extends AbstractPeerStatusCallback {
        PeerDisconnectedCallback(RoomStatusUpdateListener listener, DataHolder dataHolder, String[] participantIds) {
            super(listener, dataHolder, participantIds);
        }

        /* access modifiers changed from: protected */
        public void a(RoomStatusUpdateListener roomStatusUpdateListener, Room room, ArrayList<String> arrayList) {
            roomStatusUpdateListener.onPeersDisconnected(room, arrayList);
        }
    }

    private final class PeerInvitedToRoomCallback extends AbstractPeerStatusCallback {
        PeerInvitedToRoomCallback(RoomStatusUpdateListener listener, DataHolder dataHolder, String[] participantIds) {
            super(listener, dataHolder, participantIds);
        }

        /* access modifiers changed from: protected */
        public void a(RoomStatusUpdateListener roomStatusUpdateListener, Room room, ArrayList<String> arrayList) {
            roomStatusUpdateListener.onPeerInvitedToRoom(room, arrayList);
        }
    }

    private final class PeerJoinedRoomCallback extends AbstractPeerStatusCallback {
        PeerJoinedRoomCallback(RoomStatusUpdateListener listener, DataHolder dataHolder, String[] participantIds) {
            super(listener, dataHolder, participantIds);
        }

        /* access modifiers changed from: protected */
        public void a(RoomStatusUpdateListener roomStatusUpdateListener, Room room, ArrayList<String> arrayList) {
            roomStatusUpdateListener.onPeerJoined(room, arrayList);
        }
    }

    private final class PeerLeftRoomCallback extends AbstractPeerStatusCallback {
        PeerLeftRoomCallback(RoomStatusUpdateListener listener, DataHolder dataHolder, String[] participantIds) {
            super(listener, dataHolder, participantIds);
        }

        /* access modifiers changed from: protected */
        public void a(RoomStatusUpdateListener roomStatusUpdateListener, Room room, ArrayList<String> arrayList) {
            roomStatusUpdateListener.onPeerLeft(room, arrayList);
        }
    }

    private final class PlayerLeaderboardScoreLoadedBinderCallback extends AbstractGamesCallbacks {
        private final a.d<Leaderboards.LoadPlayerScoreResult> yO;

        PlayerLeaderboardScoreLoadedBinderCallback(a.d<Leaderboards.LoadPlayerScoreResult> resultHolder) {
            this.yO = (a.d) hn.b(resultHolder, (Object) "Holder must not be null");
        }

        public void E(DataHolder dataHolder) {
            this.yO.a(new LoadPlayerScoreResultImpl(dataHolder));
        }
    }

    private final class PlayerXpForGameCategoriesLoadedBinderCallback extends AbstractGamesCallbacks {
        private final a.d<Players.LoadXpForGameCategoriesResult> yO;

        PlayerXpForGameCategoriesLoadedBinderCallback(a.d<Players.LoadXpForGameCategoriesResult> holder) {
            this.yO = (a.d) hn.b(holder, (Object) "Holder must not be null");
        }

        public void e(int i, Bundle bundle) {
            bundle.setClassLoader(getClass().getClassLoader());
            this.yO.a(new LoadXpForGameCategoriesResultImpl(new Status(i), bundle));
        }
    }

    final class PlayerXpStreamLoadedBinderCallback extends AbstractGamesCallbacks {
        private final a.d<Players.LoadXpStreamResult> yO;

        PlayerXpStreamLoadedBinderCallback(a.d<Players.LoadXpStreamResult> holder) {
            this.yO = (a.d) hn.b(holder, (Object) "Holder must not be null");
        }

        public void P(DataHolder dataHolder) {
            this.yO.a(new LoadXpStreamResultImpl(dataHolder));
        }
    }

    private final class PlayersLoadedBinderCallback extends AbstractGamesCallbacks {
        private final a.d<Players.LoadPlayersResult> yO;

        PlayersLoadedBinderCallback(a.d<Players.LoadPlayersResult> holder) {
            this.yO = (a.d) hn.b(holder, (Object) "Holder must not be null");
        }

        public void g(DataHolder dataHolder) {
            this.yO.a(new LoadPlayersResultImpl(dataHolder));
        }

        public void h(DataHolder dataHolder) {
            this.yO.a(new LoadPlayersResultImpl(dataHolder));
        }
    }

    private final class QuestAcceptedBinderCallbacks extends AbstractGamesCallbacks {
        private final a.d<Quests.AcceptQuestResult> Oo;

        public QuestAcceptedBinderCallbacks(a.d<Quests.AcceptQuestResult> resultHolder) {
            this.Oo = (a.d) hn.b(resultHolder, (Object) "Holder must not be null");
        }

        public void L(DataHolder dataHolder) {
            this.Oo.a(new AcceptQuestResultImpl(dataHolder));
        }
    }

    private final class QuestCompletedCallback extends hc<IGamesService>.b<QuestUpdateListener> {
        private final Quest ND;

        QuestCompletedCallback(QuestUpdateListener listener, Quest quest) {
            super(listener);
            this.ND = quest;
        }

        /* access modifiers changed from: protected */
        /* renamed from: b */
        public void d(QuestUpdateListener questUpdateListener) {
            questUpdateListener.onQuestCompleted(this.ND);
        }

        /* access modifiers changed from: protected */
        public void fp() {
        }
    }

    private final class QuestMilestoneClaimBinderCallbacks extends AbstractGamesCallbacks {
        private final a.d<Quests.ClaimMilestoneResult> Op;
        private final String Oq;

        public QuestMilestoneClaimBinderCallbacks(a.d<Quests.ClaimMilestoneResult> resultHolder, String milestoneId) {
            this.Op = (a.d) hn.b(resultHolder, (Object) "Holder must not be null");
            this.Oq = (String) hn.b(milestoneId, (Object) "MilestoneId must not be null");
        }

        public void K(DataHolder dataHolder) {
            this.Op.a(new ClaimMilestoneResultImpl(dataHolder, this.Oq));
        }
    }

    private final class QuestUpdateBinderCallback extends AbstractGamesCallbacks {
        private final QuestUpdateListener Or;

        QuestUpdateBinderCallback(QuestUpdateListener listener) {
            this.Or = listener;
        }

        private Quest R(DataHolder dataHolder) {
            QuestBuffer questBuffer = new QuestBuffer(dataHolder);
            Quest quest = null;
            try {
                if (questBuffer.getCount() > 0) {
                    quest = (Quest) ((Quest) questBuffer.get(0)).freeze();
                }
                return quest;
            } finally {
                questBuffer.close();
            }
        }

        public void M(DataHolder dataHolder) {
            Quest R = R(dataHolder);
            if (R != null) {
                GamesClientImpl.this.a((hc<T>.b<?>) new QuestCompletedCallback(this.Or, R));
            }
        }
    }

    private final class QuestsLoadedBinderCallbacks extends AbstractGamesCallbacks {
        private final a.d<Quests.LoadQuestsResult> Os;

        public QuestsLoadedBinderCallbacks(a.d<Quests.LoadQuestsResult> resultHolder) {
            this.Os = (a.d) hn.b(resultHolder, (Object) "Holder must not be null");
        }

        public void O(DataHolder dataHolder) {
            this.Os.a(new LoadQuestsResultImpl(dataHolder));
        }
    }

    private final class RealTimeMessageSentCallback extends hc<IGamesService>.b<RealTimeMultiplayer.ReliableMessageSentCallback> {
        private final int CQ;
        private final String Ot;
        private final int Ou;

        RealTimeMessageSentCallback(RealTimeMultiplayer.ReliableMessageSentCallback listener, int statusCode, int token, String recipientParticipantId) {
            super(listener);
            this.CQ = statusCode;
            this.Ou = token;
            this.Ot = recipientParticipantId;
        }

        /* renamed from: a */
        public void d(RealTimeMultiplayer.ReliableMessageSentCallback reliableMessageSentCallback) {
            if (reliableMessageSentCallback != null) {
                reliableMessageSentCallback.onRealTimeMessageSent(this.CQ, this.Ou, this.Ot);
            }
        }

        /* access modifiers changed from: protected */
        public void fp() {
        }
    }

    private final class RealTimeReliableMessageBinderCallbacks extends AbstractGamesCallbacks {
        final RealTimeMultiplayer.ReliableMessageSentCallback Ov;

        public RealTimeReliableMessageBinderCallbacks(RealTimeMultiplayer.ReliableMessageSentCallback messageSentCallbacks) {
            this.Ov = messageSentCallbacks;
        }

        public void b(int i, int i2, String str) {
            GamesClientImpl.this.a((hc<T>.b<?>) new RealTimeMessageSentCallback(this.Ov, i, i2, str));
        }
    }

    private final class RequestReceivedBinderCallback extends AbstractGamesCallbacks {
        private final OnRequestReceivedListener Ow;

        RequestReceivedBinderCallback(OnRequestReceivedListener listener) {
            this.Ow = listener;
        }

        public void o(DataHolder dataHolder) {
            GameRequestBuffer gameRequestBuffer = new GameRequestBuffer(dataHolder);
            GameRequest gameRequest = null;
            try {
                if (gameRequestBuffer.getCount() > 0) {
                    gameRequest = (GameRequest) ((GameRequest) gameRequestBuffer.get(0)).freeze();
                }
                if (gameRequest != null) {
                    GamesClientImpl.this.a((hc<T>.b<?>) new RequestReceivedCallback(this.Ow, gameRequest));
                }
            } finally {
                gameRequestBuffer.close();
            }
        }

        public void onRequestRemoved(String requestId) {
            GamesClientImpl.this.a((hc<T>.b<?>) new RequestRemovedCallback(this.Ow, requestId));
        }
    }

    private final class RequestReceivedCallback extends hc<IGamesService>.b<OnRequestReceivedListener> {
        private final GameRequest Ox;

        RequestReceivedCallback(OnRequestReceivedListener listener, GameRequest request) {
            super(listener);
            this.Ox = request;
        }

        /* access modifiers changed from: protected */
        /* renamed from: b */
        public void d(OnRequestReceivedListener onRequestReceivedListener) {
            onRequestReceivedListener.onRequestReceived(this.Ox);
        }

        /* access modifiers changed from: protected */
        public void fp() {
        }
    }

    private final class RequestRemovedCallback extends hc<IGamesService>.b<OnRequestReceivedListener> {
        private final String Oy;

        RequestRemovedCallback(OnRequestReceivedListener listener, String requestId) {
            super(listener);
            this.Oy = requestId;
        }

        /* access modifiers changed from: protected */
        /* renamed from: b */
        public void d(OnRequestReceivedListener onRequestReceivedListener) {
            onRequestReceivedListener.onRequestRemoved(this.Oy);
        }

        /* access modifiers changed from: protected */
        public void fp() {
        }
    }

    private final class RequestSentBinderCallbacks extends AbstractGamesCallbacks {
        private final a.d<Requests.SendRequestResult> Oz;

        public RequestSentBinderCallbacks(a.d<Requests.SendRequestResult> resultHolder) {
            this.Oz = (a.d) hn.b(resultHolder, (Object) "Holder must not be null");
        }

        public void G(DataHolder dataHolder) {
            this.Oz.a(new SendRequestResultImpl(dataHolder));
        }
    }

    private final class RequestSummariesLoadedBinderCallbacks extends AbstractGamesCallbacks {
        private final a.d<Requests.LoadRequestSummariesResult> OA;

        public RequestSummariesLoadedBinderCallbacks(a.d<Requests.LoadRequestSummariesResult> resultHolder) {
            this.OA = (a.d) hn.b(resultHolder, (Object) "Holder must not be null");
        }

        public void H(DataHolder dataHolder) {
            this.OA.a(new LoadRequestSummariesResultImpl(dataHolder));
        }
    }

    private final class RequestsLoadedBinderCallbacks extends AbstractGamesCallbacks {
        private final a.d<Requests.LoadRequestsResult> OB;

        public RequestsLoadedBinderCallbacks(a.d<Requests.LoadRequestsResult> resultHolder) {
            this.OB = (a.d) hn.b(resultHolder, (Object) "Holder must not be null");
        }

        public void c(int i, Bundle bundle) {
            bundle.setClassLoader(getClass().getClassLoader());
            this.OB.a(new LoadRequestsResultImpl(new Status(i), bundle));
        }
    }

    private final class RequestsUpdatedBinderCallbacks extends AbstractGamesCallbacks {
        private final a.d<Requests.UpdateRequestsResult> OC;

        public RequestsUpdatedBinderCallbacks(a.d<Requests.UpdateRequestsResult> resultHolder) {
            this.OC = (a.d) hn.b(resultHolder, (Object) "Holder must not be null");
        }

        public void F(DataHolder dataHolder) {
            this.OC.a(new UpdateRequestsResultImpl(dataHolder));
        }
    }

    private final class RoomAutoMatchingCallback extends AbstractRoomStatusCallback {
        RoomAutoMatchingCallback(RoomStatusUpdateListener listener, DataHolder dataHolder) {
            super(listener, dataHolder);
        }

        public void a(RoomStatusUpdateListener roomStatusUpdateListener, Room room) {
            roomStatusUpdateListener.onRoomAutoMatching(room);
        }
    }

    private final class RoomBinderCallbacks extends AbstractGamesCallbacks {
        private final RoomUpdateListener OD;
        private final RoomStatusUpdateListener OE;
        private final RealTimeMessageReceivedListener OF;

        public RoomBinderCallbacks(RoomUpdateListener roomCallbacks) {
            this.OD = (RoomUpdateListener) hn.b(roomCallbacks, (Object) "Callbacks must not be null");
            this.OE = null;
            this.OF = null;
        }

        public RoomBinderCallbacks(RoomUpdateListener roomCallbacks, RoomStatusUpdateListener roomStatusCallbacks, RealTimeMessageReceivedListener realTimeMessageReceivedCallbacks) {
            this.OD = (RoomUpdateListener) hn.b(roomCallbacks, (Object) "Callbacks must not be null");
            this.OE = roomStatusCallbacks;
            this.OF = realTimeMessageReceivedCallbacks;
        }

        public void A(DataHolder dataHolder) {
            GamesClientImpl.this.a((hc<T>.b<?>) new DisconnectedFromRoomCallback(this.OE, dataHolder));
        }

        public void a(DataHolder dataHolder, String[] strArr) {
            GamesClientImpl.this.a((hc<T>.b<?>) new PeerInvitedToRoomCallback(this.OE, dataHolder, strArr));
        }

        public void b(DataHolder dataHolder, String[] strArr) {
            GamesClientImpl.this.a((hc<T>.b<?>) new PeerJoinedRoomCallback(this.OE, dataHolder, strArr));
        }

        public void c(DataHolder dataHolder, String[] strArr) {
            GamesClientImpl.this.a((hc<T>.b<?>) new PeerLeftRoomCallback(this.OE, dataHolder, strArr));
        }

        public void d(DataHolder dataHolder, String[] strArr) {
            GamesClientImpl.this.a((hc<T>.b<?>) new PeerDeclinedCallback(this.OE, dataHolder, strArr));
        }

        public void e(DataHolder dataHolder, String[] strArr) {
            GamesClientImpl.this.a((hc<T>.b<?>) new PeerConnectedCallback(this.OE, dataHolder, strArr));
        }

        public void f(DataHolder dataHolder, String[] strArr) {
            GamesClientImpl.this.a((hc<T>.b<?>) new PeerDisconnectedCallback(this.OE, dataHolder, strArr));
        }

        public void onLeftRoom(int statusCode, String externalRoomId) {
            GamesClientImpl.this.a((hc<T>.b<?>) new LeftRoomCallback(this.OD, statusCode, externalRoomId));
        }

        public void onP2PConnected(String participantId) {
            GamesClientImpl.this.a((hc<T>.b<?>) new P2PConnectedCallback(this.OE, participantId));
        }

        public void onP2PDisconnected(String participantId) {
            GamesClientImpl.this.a((hc<T>.b<?>) new P2PDisconnectedCallback(this.OE, participantId));
        }

        public void onRealTimeMessageReceived(RealTimeMessage message) {
            GamesClientImpl.this.a((hc<T>.b<?>) new MessageReceivedCallback(this.OF, message));
        }

        public void u(DataHolder dataHolder) {
            GamesClientImpl.this.a((hc<T>.b<?>) new RoomCreatedCallback(this.OD, dataHolder));
        }

        public void v(DataHolder dataHolder) {
            GamesClientImpl.this.a((hc<T>.b<?>) new JoinedRoomCallback(this.OD, dataHolder));
        }

        public void w(DataHolder dataHolder) {
            GamesClientImpl.this.a((hc<T>.b<?>) new RoomConnectingCallback(this.OE, dataHolder));
        }

        public void x(DataHolder dataHolder) {
            GamesClientImpl.this.a((hc<T>.b<?>) new RoomAutoMatchingCallback(this.OE, dataHolder));
        }

        public void y(DataHolder dataHolder) {
            GamesClientImpl.this.a((hc<T>.b<?>) new RoomConnectedCallback(this.OD, dataHolder));
        }

        public void z(DataHolder dataHolder) {
            GamesClientImpl.this.a((hc<T>.b<?>) new ConnectedToRoomCallback(this.OE, dataHolder));
        }
    }

    private final class RoomConnectedCallback extends AbstractRoomCallback {
        RoomConnectedCallback(RoomUpdateListener listener, DataHolder dataHolder) {
            super(listener, dataHolder);
        }

        public void a(RoomUpdateListener roomUpdateListener, Room room, int i) {
            roomUpdateListener.onRoomConnected(i, room);
        }
    }

    private final class RoomConnectingCallback extends AbstractRoomStatusCallback {
        RoomConnectingCallback(RoomStatusUpdateListener listener, DataHolder dataHolder) {
            super(listener, dataHolder);
        }

        public void a(RoomStatusUpdateListener roomStatusUpdateListener, Room room) {
            roomStatusUpdateListener.onRoomConnecting(room);
        }
    }

    private final class RoomCreatedCallback extends AbstractRoomCallback {
        public RoomCreatedCallback(RoomUpdateListener listener, DataHolder dataHolder) {
            super(listener, dataHolder);
        }

        public void a(RoomUpdateListener roomUpdateListener, Room room, int i) {
            roomUpdateListener.onRoomCreated(i, room);
        }
    }

    private static final class SendRequestResultImpl extends b implements Requests.SendRequestResult {
        private final GameRequest Ox;

        SendRequestResultImpl(DataHolder dataHolder) {
            super(dataHolder);
            GameRequestBuffer gameRequestBuffer = new GameRequestBuffer(dataHolder);
            try {
                if (gameRequestBuffer.getCount() > 0) {
                    this.Ox = (GameRequest) ((GameRequest) gameRequestBuffer.get(0)).freeze();
                } else {
                    this.Ox = null;
                }
            } finally {
                gameRequestBuffer.close();
            }
        }
    }

    private final class SignOutCompleteBinderCallbacks extends AbstractGamesCallbacks {
        private final a.d<Status> yO;

        public SignOutCompleteBinderCallbacks(a.d<Status> resultHolder) {
            this.yO = (a.d) hn.b(resultHolder, (Object) "Holder must not be null");
        }

        public void dO() {
            this.yO.a(new Status(0));
        }
    }

    private final class SnapshotCommittedBinderCallbacks extends AbstractGamesCallbacks {
        private final a.d<Snapshots.CommitSnapshotResult> OG;

        public SnapshotCommittedBinderCallbacks(a.d<Snapshots.CommitSnapshotResult> resultHolder) {
            this.OG = (a.d) hn.b(resultHolder, (Object) "Holder must not be null");
        }

        public void J(DataHolder dataHolder) {
            this.OG.a(new CommitSnapshotResultImpl(dataHolder));
        }
    }

    final class SnapshotDeletedBinderCallbacks extends AbstractGamesCallbacks {
        private final a.d<Snapshots.DeleteSnapshotResult> yO;

        public SnapshotDeletedBinderCallbacks(a.d<Snapshots.DeleteSnapshotResult> resultHolder) {
            this.yO = (a.d) hn.b(resultHolder, (Object) "Holder must not be null");
        }

        public void g(int i, String str) {
            this.yO.a(new DeleteSnapshotResultImpl(i, str));
        }
    }

    private final class SnapshotOpenedBinderCallbacks extends AbstractGamesCallbacks {
        private final a.d<Snapshots.OpenSnapshotResult> OH;

        public SnapshotOpenedBinderCallbacks(a.d<Snapshots.OpenSnapshotResult> resultHolder) {
            this.OH = (a.d) hn.b(resultHolder, (Object) "Holder must not be null");
        }

        public void a(DataHolder dataHolder, Contents contents) {
            this.OH.a(new OpenSnapshotResultImpl(dataHolder, contents));
        }

        public void a(DataHolder dataHolder, String str, Contents contents, Contents contents2, Contents contents3) {
            this.OH.a(new OpenSnapshotResultImpl(dataHolder, str, contents, contents2, contents3));
        }
    }

    private final class SnapshotsLoadedBinderCallbacks extends AbstractGamesCallbacks {
        private final a.d<Snapshots.LoadSnapshotsResult> OI;

        public SnapshotsLoadedBinderCallbacks(a.d<Snapshots.LoadSnapshotsResult> resultHolder) {
            this.OI = (a.d) hn.b(resultHolder, (Object) "Holder must not be null");
        }

        public void I(DataHolder dataHolder) {
            this.OI.a(new LoadSnapshotsResultImpl(dataHolder));
        }
    }

    private final class SubmitScoreBinderCallbacks extends AbstractGamesCallbacks {
        private final a.d<Leaderboards.SubmitScoreResult> yO;

        public SubmitScoreBinderCallbacks(a.d<Leaderboards.SubmitScoreResult> resultHolder) {
            this.yO = (a.d) hn.b(resultHolder, (Object) "Holder must not be null");
        }

        public void f(DataHolder dataHolder) {
            this.yO.a(new SubmitScoreResultImpl(dataHolder));
        }
    }

    private static final class SubmitScoreResultImpl extends b implements Leaderboards.SubmitScoreResult {
        private final ScoreSubmissionData OJ;

        public SubmitScoreResultImpl(DataHolder dataHolder) {
            super(dataHolder);
            try {
                this.OJ = new ScoreSubmissionData(dataHolder);
            } finally {
                dataHolder.close();
            }
        }

        public ScoreSubmissionData getScoreData() {
            return this.OJ;
        }
    }

    private final class TurnBasedMatchCanceledBinderCallbacks extends AbstractGamesCallbacks {
        private final a.d<TurnBasedMultiplayer.CancelMatchResult> OK;

        public TurnBasedMatchCanceledBinderCallbacks(a.d<TurnBasedMultiplayer.CancelMatchResult> resultHolder) {
            this.OK = (a.d) hn.b(resultHolder, (Object) "Holder must not be null");
        }

        public void f(int i, String str) {
            this.OK.a(new CancelMatchResultImpl(new Status(i), str));
        }
    }

    private final class TurnBasedMatchInitiatedBinderCallbacks extends AbstractGamesCallbacks {
        private final a.d<TurnBasedMultiplayer.InitiateMatchResult> OL;

        public TurnBasedMatchInitiatedBinderCallbacks(a.d<TurnBasedMultiplayer.InitiateMatchResult> resultHolder) {
            this.OL = (a.d) hn.b(resultHolder, (Object) "Holder must not be null");
        }

        public void q(DataHolder dataHolder) {
            this.OL.a(new InitiateMatchResultImpl(dataHolder));
        }
    }

    private final class TurnBasedMatchLeftBinderCallbacks extends AbstractGamesCallbacks {
        private final a.d<TurnBasedMultiplayer.LeaveMatchResult> OM;

        public TurnBasedMatchLeftBinderCallbacks(a.d<TurnBasedMultiplayer.LeaveMatchResult> resultHolder) {
            this.OM = (a.d) hn.b(resultHolder, (Object) "Holder must not be null");
        }

        public void s(DataHolder dataHolder) {
            this.OM.a(new LeaveMatchResultImpl(dataHolder));
        }
    }

    private final class TurnBasedMatchLoadedBinderCallbacks extends AbstractGamesCallbacks {
        private final a.d<TurnBasedMultiplayer.LoadMatchResult> ON;

        public TurnBasedMatchLoadedBinderCallbacks(a.d<TurnBasedMultiplayer.LoadMatchResult> resultHolder) {
            this.ON = (a.d) hn.b(resultHolder, (Object) "Holder must not be null");
        }

        public void p(DataHolder dataHolder) {
            this.ON.a(new LoadMatchResultImpl(dataHolder));
        }
    }

    private static abstract class TurnBasedMatchResult extends b {
        final TurnBasedMatch Oh;

        TurnBasedMatchResult(DataHolder dataHolder) {
            super(dataHolder);
            TurnBasedMatchBuffer turnBasedMatchBuffer = new TurnBasedMatchBuffer(dataHolder);
            try {
                if (turnBasedMatchBuffer.getCount() > 0) {
                    this.Oh = (TurnBasedMatch) ((TurnBasedMatch) turnBasedMatchBuffer.get(0)).freeze();
                } else {
                    this.Oh = null;
                }
            } finally {
                turnBasedMatchBuffer.close();
            }
        }

        public TurnBasedMatch getMatch() {
            return this.Oh;
        }
    }

    private final class TurnBasedMatchUpdatedBinderCallbacks extends AbstractGamesCallbacks {
        private final a.d<TurnBasedMultiplayer.UpdateMatchResult> OO;

        public TurnBasedMatchUpdatedBinderCallbacks(a.d<TurnBasedMultiplayer.UpdateMatchResult> resultHolder) {
            this.OO = (a.d) hn.b(resultHolder, (Object) "Holder must not be null");
        }

        public void r(DataHolder dataHolder) {
            this.OO.a(new UpdateMatchResultImpl(dataHolder));
        }
    }

    private final class TurnBasedMatchesLoadedBinderCallbacks extends AbstractGamesCallbacks {
        private final a.d<TurnBasedMultiplayer.LoadMatchesResult> OP;

        public TurnBasedMatchesLoadedBinderCallbacks(a.d<TurnBasedMultiplayer.LoadMatchesResult> resultHolder) {
            this.OP = (a.d) hn.b(resultHolder, (Object) "Holder must not be null");
        }

        public void b(int i, Bundle bundle) {
            bundle.setClassLoader(getClass().getClassLoader());
            this.OP.a(new LoadMatchesResultImpl(new Status(i), bundle));
        }
    }

    private static final class UpdateAchievementResultImpl implements Achievements.UpdateAchievementResult {
        private final String OQ;
        private final Status yw;

        UpdateAchievementResultImpl(int statusCode, String achievementId) {
            this.yw = new Status(statusCode);
            this.OQ = achievementId;
        }

        public String getAchievementId() {
            return this.OQ;
        }

        public Status getStatus() {
            return this.yw;
        }
    }

    private static final class UpdateMatchResultImpl extends TurnBasedMatchResult implements TurnBasedMultiplayer.UpdateMatchResult {
        UpdateMatchResultImpl(DataHolder dataHolder) {
            super(dataHolder);
        }
    }

    private static final class UpdateRequestsResultImpl extends b implements Requests.UpdateRequestsResult {
        private final RequestUpdateOutcomes OR;

        UpdateRequestsResultImpl(DataHolder dataHolder) {
            super(dataHolder);
            this.OR = RequestUpdateOutcomes.U(dataHolder);
        }

        public Set<String> getRequestIds() {
            return this.OR.getRequestIds();
        }

        public int getRequestOutcome(String requestId) {
            return this.OR.getRequestOutcome(requestId);
        }
    }

    public GamesClientImpl(Context context, Looper looper, String gamePackageName, String accountName, GoogleApiClient.ConnectionCallbacks connectedListener, GoogleApiClient.OnConnectionFailedListener connectionFailedListener, String[] scopes, int gravity, View gamesContentView, boolean isHeadless, boolean showConnectingPopup, int connectingPopupGravity, boolean retryingSignIn, int sdkVariant, String forceResolveAccountKey) {
        super(context, looper, connectedListener, connectionFailedListener, scopes);
        this.Nn = gamePackageName;
        this.yN = (String) hn.f(accountName);
        this.Nv = new Binder();
        this.No = new HashMap();
        this.Nr = PopupManager.a(this, gravity);
        f(gamesContentView);
        this.Nt = showConnectingPopup;
        this.Nu = connectingPopupGravity;
        this.Nw = (long) hashCode();
        this.Nx = isHeadless;
        this.Nz = retryingSignIn;
        this.Ny = sdkVariant;
        this.NA = forceResolveAccountKey;
        registerConnectionCallbacks((GoogleApiClient.ConnectionCallbacks) this);
        registerConnectionFailedListener((GoogleApiClient.OnConnectionFailedListener) this);
    }

    /* access modifiers changed from: private */
    public Room Q(DataHolder dataHolder) {
        RoomBuffer roomBuffer = new RoomBuffer(dataHolder);
        Room room = null;
        try {
            if (roomBuffer.getCount() > 0) {
                room = (Room) ((Room) roomBuffer.get(0)).freeze();
            }
            return room;
        } finally {
            roomBuffer.close();
        }
    }

    private RealTimeSocket aT(String str) {
        try {
            ParcelFileDescriptor bb = ((IGamesService) fo()).bb(str);
            if (bb != null) {
                GamesLog.i("GamesClientImpl", "Created native libjingle socket.");
                LibjingleNativeSocket libjingleNativeSocket = new LibjingleNativeSocket(bb);
                this.No.put(str, libjingleNativeSocket);
                return libjingleNativeSocket;
            }
            GamesLog.i("GamesClientImpl", "Unable to create native libjingle socket, resorting to old socket.");
            String aW = ((IGamesService) fo()).aW(str);
            if (aW == null) {
                return null;
            }
            LocalSocket localSocket = new LocalSocket();
            try {
                localSocket.connect(new LocalSocketAddress(aW));
                RealTimeSocketImpl realTimeSocketImpl = new RealTimeSocketImpl(localSocket, str);
                this.No.put(str, realTimeSocketImpl);
                return realTimeSocketImpl;
            } catch (IOException e) {
                GamesLog.k("GamesClientImpl", "connect() call failed on socket: " + e.getMessage());
                return null;
            }
        } catch (RemoteException e2) {
            GamesLog.k("GamesClientImpl", "Unable to create socket. Service died.");
            return null;
        }
    }

    private void gT() {
        this.Np = null;
    }

    private void hq() {
        for (RealTimeSocket close : this.No.values()) {
            try {
                close.close();
            } catch (IOException e) {
                GamesLog.b("GamesClientImpl", "IOException:", e);
            }
        }
        this.No.clear();
    }

    public int a(RealTimeMultiplayer.ReliableMessageSentCallback reliableMessageSentCallback, byte[] bArr, String str, String str2) {
        try {
            return ((IGamesService) fo()).a((IGamesCallbacks) new RealTimeReliableMessageBinderCallbacks(reliableMessageSentCallback), bArr, str, str2);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
            return -1;
        }
    }

    public int a(byte[] bArr, String str, String[] strArr) {
        hn.b(strArr, (Object) "Participant IDs must not be null");
        try {
            return ((IGamesService) fo()).b(bArr, str, strArr);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
            return -1;
        }
    }

    public Intent a(int i, int i2, boolean z) {
        try {
            return ((IGamesService) fo()).a(i, i2, z);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
            return null;
        }
    }

    public Intent a(int i, byte[] bArr, int i2, Bitmap bitmap, String str) {
        try {
            Intent a = ((IGamesService) fo()).a(i, bArr, i2, str);
            hn.b(bitmap, (Object) "Must provide a non null icon");
            a.putExtra("com.google.android.gms.games.REQUEST_ITEM_ICON", bitmap);
            return a;
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
            return null;
        }
    }

    public Intent a(Room room, int i) {
        try {
            return ((IGamesService) fo()).a((RoomEntity) room.freeze(), i);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
            return null;
        }
    }

    public Intent a(String str, boolean z, boolean z2, int i) {
        try {
            return ((IGamesService) fo()).a(str, z, z2, i);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
            return null;
        }
    }

    public Intent a(int[] iArr) {
        try {
            return ((IGamesService) fo()).a(iArr);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public void a(int i, IBinder iBinder, Bundle bundle) {
        if (i == 0 && bundle != null) {
            this.Ns = bundle.getBoolean("show_welcome_popup");
        }
        super.a(i, iBinder, bundle);
    }

    public void a(IBinder iBinder, Bundle bundle) {
        if (isConnected()) {
            try {
                ((IGamesService) fo()).a(iBinder, bundle);
            } catch (RemoteException e) {
                GamesLog.j("GamesClientImpl", "service died");
            }
        }
    }

    public void a(a.d<Requests.LoadRequestsResult> dVar, int i, int i2, int i3) {
        try {
            ((IGamesService) fo()).a((IGamesCallbacks) new RequestsLoadedBinderCallbacks(dVar), i, i2, i3);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void a(a.d<GamesMetadata.LoadExtendedGamesResult> dVar, int i, int i2, boolean z, boolean z2) {
        try {
            ((IGamesService) fo()).a((IGamesCallbacks) new ExtendedGamesLoadedBinderCallback(dVar), i, i2, z, z2);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void a(a.d<Players.LoadPlayersResult> dVar, int i, boolean z, boolean z2) {
        try {
            ((IGamesService) fo()).a((IGamesCallbacks) new PlayersLoadedBinderCallback(dVar), i, z, z2);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void a(a.d<TurnBasedMultiplayer.LoadMatchesResult> dVar, int i, int[] iArr) {
        try {
            ((IGamesService) fo()).a((IGamesCallbacks) new TurnBasedMatchesLoadedBinderCallbacks(dVar), i, iArr);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void a(a.d<Leaderboards.LoadScoresResult> dVar, LeaderboardScoreBuffer leaderboardScoreBuffer, int i, int i2) {
        try {
            ((IGamesService) fo()).a((IGamesCallbacks) new LeaderboardScoresLoadedBinderCallback(dVar), leaderboardScoreBuffer.iv().iw(), i, i2);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void a(a.d<TurnBasedMultiplayer.InitiateMatchResult> dVar, TurnBasedMatchConfig turnBasedMatchConfig) {
        try {
            ((IGamesService) fo()).a((IGamesCallbacks) new TurnBasedMatchInitiatedBinderCallbacks(dVar), turnBasedMatchConfig.getVariant(), turnBasedMatchConfig.iC(), turnBasedMatchConfig.getInvitedPlayerIds(), turnBasedMatchConfig.getAutoMatchCriteria());
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void a(a.d<Snapshots.CommitSnapshotResult> dVar, Snapshot snapshot, SnapshotMetadataChange snapshotMetadataChange) {
        Contents contents = snapshot.getContents();
        hn.b(contents, (Object) "Must provide a previously opened Snapshot");
        com.google.android.gms.common.data.a iI = snapshotMetadataChange.iI();
        if (iI != null) {
            iI.a(getContext().getCacheDir());
        }
        snapshot.iH();
        try {
            ((IGamesService) fo()).a((IGamesCallbacks) new SnapshotCommittedBinderCallbacks(dVar), snapshot.getMetadata().getSnapshotId(), snapshotMetadataChange, contents);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void a(a.d<Players.LoadPlayersResult> dVar, String str) {
        try {
            ((IGamesService) fo()).a((IGamesCallbacks) new PlayersLoadedBinderCallback(dVar), str);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void a(a.d<Achievements.UpdateAchievementResult> dVar, String str, int i) {
        try {
            ((IGamesService) fo()).a((IGamesCallbacks) dVar == null ? null : new AchievementUpdatedBinderCallback(dVar), str, i, this.Nr.hI(), this.Nr.hH());
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void a(a.d<Leaderboards.LoadScoresResult> dVar, String str, int i, int i2, int i3, boolean z) {
        try {
            ((IGamesService) fo()).a((IGamesCallbacks) new LeaderboardScoresLoadedBinderCallback(dVar), str, i, i2, i3, z);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void a(a.d<Players.LoadPlayersResult> dVar, String str, int i, boolean z) {
        try {
            ((IGamesService) fo()).a((IGamesCallbacks) new PlayersLoadedBinderCallback(dVar), str, i, z);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void a(a.d<Players.LoadPlayersResult> dVar, String str, int i, boolean z, boolean z2) {
        if (!str.equals("played_with")) {
            throw new IllegalArgumentException("Invalid player collection: " + str);
        }
        try {
            ((IGamesService) fo()).d(new PlayersLoadedBinderCallback(dVar), str, i, z, z2);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void a(a.d<GamesMetadata.LoadExtendedGamesResult> dVar, String str, int i, boolean z, boolean z2, boolean z3, boolean z4) {
        try {
            ((IGamesService) fo()).a((IGamesCallbacks) new ExtendedGamesLoadedBinderCallback(dVar), str, i, z, z2, z3, z4);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void a(a.d<TurnBasedMultiplayer.LoadMatchesResult> dVar, String str, int i, int[] iArr) {
        try {
            ((IGamesService) fo()).a((IGamesCallbacks) new TurnBasedMatchesLoadedBinderCallbacks(dVar), str, i, iArr);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void a(a.d<Leaderboards.SubmitScoreResult> dVar, String str, long j, String str2) {
        try {
            ((IGamesService) fo()).a((IGamesCallbacks) dVar == null ? null : new SubmitScoreBinderCallbacks(dVar), str, j, str2);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void a(a.d<TurnBasedMultiplayer.LeaveMatchResult> dVar, String str, String str2) {
        try {
            ((IGamesService) fo()).c((IGamesCallbacks) new TurnBasedMatchLeftBinderCallbacks(dVar), str, str2);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void a(a.d<Leaderboards.LoadPlayerScoreResult> dVar, String str, String str2, int i, int i2) {
        try {
            ((IGamesService) fo()).a((IGamesCallbacks) new PlayerLeaderboardScoreLoadedBinderCallback(dVar), str, str2, i, i2);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void a(a.d<Requests.LoadRequestsResult> dVar, String str, String str2, int i, int i2, int i3) {
        try {
            ((IGamesService) fo()).a((IGamesCallbacks) new RequestsLoadedBinderCallbacks(dVar), str, str2, i, i2, i3);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void a(a.d<Leaderboards.LoadScoresResult> dVar, String str, String str2, int i, int i2, int i3, boolean z) {
        try {
            ((IGamesService) fo()).a((IGamesCallbacks) new LeaderboardScoresLoadedBinderCallback(dVar), str, str2, i, i2, i3, z);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void a(a.d<Players.LoadPlayersResult> dVar, String str, String str2, int i, boolean z, boolean z2) {
        if (str.equals("played_with") || str.equals("circled")) {
            try {
                ((IGamesService) fo()).a((IGamesCallbacks) new PlayersLoadedBinderCallback(dVar), str, str2, i, z, z2);
            } catch (RemoteException e) {
                GamesLog.j("GamesClientImpl", "service died");
            }
        } else {
            throw new IllegalArgumentException("Invalid player collection: " + str);
        }
    }

    public void a(a.d<Snapshots.OpenSnapshotResult> dVar, String str, String str2, SnapshotMetadataChange snapshotMetadataChange, Contents contents) {
        com.google.android.gms.common.data.a iI = snapshotMetadataChange.iI();
        if (iI != null) {
            iI.a(getContext().getCacheDir());
        }
        try {
            ((IGamesService) fo()).a((IGamesCallbacks) new SnapshotOpenedBinderCallbacks(dVar), str, str2, snapshotMetadataChange, contents);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void a(a.d<Leaderboards.LeaderboardMetadataResult> dVar, String str, String str2, boolean z) {
        try {
            ((IGamesService) fo()).b((IGamesCallbacks) new LeaderboardsLoadedBinderCallback(dVar), str, str2, z);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void a(a.d<Quests.LoadQuestsResult> dVar, String str, String str2, boolean z, String[] strArr) {
        try {
            ((IGamesService) fo()).a((IGamesCallbacks) new QuestsLoadedBinderCallbacks(dVar), str, str2, strArr, z);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void a(a.d<Quests.LoadQuestsResult> dVar, String str, String str2, int[] iArr, int i, boolean z) {
        try {
            ((IGamesService) fo()).a((IGamesCallbacks) new QuestsLoadedBinderCallbacks(dVar), str, str2, iArr, i, z);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void a(a.d<Requests.UpdateRequestsResult> dVar, String str, String str2, String[] strArr) {
        try {
            ((IGamesService) fo()).a((IGamesCallbacks) new RequestsUpdatedBinderCallbacks(dVar), str, str2, strArr);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void a(a.d<Leaderboards.LeaderboardMetadataResult> dVar, String str, boolean z) {
        try {
            ((IGamesService) fo()).c((IGamesCallbacks) new LeaderboardsLoadedBinderCallback(dVar), str, z);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void a(a.d<TurnBasedMultiplayer.UpdateMatchResult> dVar, String str, byte[] bArr, String str2, ParticipantResult[] participantResultArr) {
        try {
            ((IGamesService) fo()).a((IGamesCallbacks) new TurnBasedMatchUpdatedBinderCallbacks(dVar), str, bArr, str2, participantResultArr);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void a(a.d<TurnBasedMultiplayer.UpdateMatchResult> dVar, String str, byte[] bArr, ParticipantResult[] participantResultArr) {
        try {
            ((IGamesService) fo()).a((IGamesCallbacks) new TurnBasedMatchUpdatedBinderCallbacks(dVar), str, bArr, participantResultArr);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void a(a.d<Requests.SendRequestResult> dVar, String str, String[] strArr, int i, byte[] bArr, int i2) {
        try {
            ((IGamesService) fo()).a((IGamesCallbacks) new RequestSentBinderCallbacks(dVar), str, strArr, i, bArr, i2);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void a(a.d<Players.LoadPlayersResult> dVar, boolean z) {
        try {
            ((IGamesService) fo()).c((IGamesCallbacks) new PlayersLoadedBinderCallback(dVar), z);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void a(a.d<Status> dVar, boolean z, Bundle bundle) {
        try {
            ((IGamesService) fo()).a((IGamesCallbacks) new ContactSettingsUpdatedBinderCallback(dVar), z, bundle);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void a(a.d<Events.LoadEventsResult> dVar, boolean z, String... strArr) {
        try {
            this.Nm.flush();
            ((IGamesService) fo()).a((IGamesCallbacks) new EventsLoadedBinderCallback(dVar), z, strArr);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void a(a.d<Quests.LoadQuestsResult> dVar, int[] iArr, int i, boolean z) {
        try {
            this.Nm.flush();
            ((IGamesService) fo()).a((IGamesCallbacks) new QuestsLoadedBinderCallbacks(dVar), iArr, i, z);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void a(a.d<Players.LoadPlayersResult> dVar, String[] strArr) {
        try {
            ((IGamesService) fo()).c((IGamesCallbacks) new PlayersLoadedBinderCallback(dVar), strArr);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void a(OnInvitationReceivedListener onInvitationReceivedListener) {
        try {
            ((IGamesService) fo()).a((IGamesCallbacks) new InvitationReceivedBinderCallback(onInvitationReceivedListener), this.Nw);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void a(RoomConfig roomConfig) {
        try {
            ((IGamesService) fo()).a((IGamesCallbacks) new RoomBinderCallbacks(roomConfig.getRoomUpdateListener(), roomConfig.getRoomStatusUpdateListener(), roomConfig.getMessageReceivedListener()), (IBinder) this.Nv, roomConfig.getVariant(), roomConfig.getInvitedPlayerIds(), roomConfig.getAutoMatchCriteria(), roomConfig.isSocketEnabled(), this.Nw);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void a(RoomUpdateListener roomUpdateListener, String str) {
        try {
            ((IGamesService) fo()).c((IGamesCallbacks) new RoomBinderCallbacks(roomUpdateListener), str);
            hq();
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void a(OnTurnBasedMatchUpdateReceivedListener onTurnBasedMatchUpdateReceivedListener) {
        try {
            ((IGamesService) fo()).b((IGamesCallbacks) new MatchUpdateReceivedBinderCallback(onTurnBasedMatchUpdateReceivedListener), this.Nw);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void a(QuestUpdateListener questUpdateListener) {
        try {
            ((IGamesService) fo()).d((IGamesCallbacks) new QuestUpdateBinderCallback(questUpdateListener), this.Nw);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void a(OnRequestReceivedListener onRequestReceivedListener) {
        try {
            ((IGamesService) fo()).c((IGamesCallbacks) new RequestReceivedBinderCallback(onRequestReceivedListener), this.Nw);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void a(Snapshot snapshot) {
        Contents contents = snapshot.getContents();
        hn.b(contents, (Object) "Must provide a previously opened Snapshot");
        snapshot.iH();
        try {
            ((IGamesService) fo()).a(contents);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    /* access modifiers changed from: protected */
    public void a(hj hjVar, hc.e eVar) throws RemoteException {
        String locale = getContext().getResources().getConfiguration().locale.toString();
        Bundle bundle = new Bundle();
        bundle.putBoolean("com.google.android.gms.games.key.isHeadless", this.Nx);
        bundle.putBoolean("com.google.android.gms.games.key.showConnectingPopup", this.Nt);
        bundle.putInt("com.google.android.gms.games.key.connectingPopupGravity", this.Nu);
        bundle.putBoolean("com.google.android.gms.games.key.retryingSignIn", this.Nz);
        bundle.putInt("com.google.android.gms.games.key.sdkVariant", this.Ny);
        bundle.putString("com.google.android.gms.games.key.forceResolveAccountKey", this.NA);
        hjVar.a(eVar, GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_VERSION_CODE, getContext().getPackageName(), this.yN, fn(), this.Nn, this.Nr.hI(), locale, bundle);
    }

    public Intent aR(String str) {
        try {
            return ((IGamesService) fo()).aR(str);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
            return null;
        }
    }

    public void aS(String str) {
        try {
            ((IGamesService) fo()).ba(str);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public Intent aU(String str) {
        try {
            return ((IGamesService) fo()).aU(str);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
            return null;
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: ah */
    public IGamesService x(IBinder iBinder) {
        return IGamesService.Stub.aj(iBinder);
    }

    public Intent b(int i, int i2, boolean z) {
        try {
            return ((IGamesService) fo()).b(i, i2, z);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
            return null;
        }
    }

    public void b(a.d<Status> dVar) {
        try {
            this.Nm.flush();
            ((IGamesService) fo()).a((IGamesCallbacks) new SignOutCompleteBinderCallbacks(dVar));
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void b(a.d<Players.LoadPlayersResult> dVar, int i, boolean z, boolean z2) {
        try {
            ((IGamesService) fo()).b((IGamesCallbacks) new PlayersLoadedBinderCallback(dVar), i, z, z2);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void b(a.d<Achievements.UpdateAchievementResult> dVar, String str) {
        try {
            ((IGamesService) fo()).a((IGamesCallbacks) dVar == null ? null : new AchievementUpdatedBinderCallback(dVar), str, this.Nr.hI(), this.Nr.hH());
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void b(a.d<Achievements.UpdateAchievementResult> dVar, String str, int i) {
        try {
            ((IGamesService) fo()).b((IGamesCallbacks) dVar == null ? null : new AchievementUpdatedBinderCallback(dVar), str, i, this.Nr.hI(), this.Nr.hH());
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void b(a.d<Leaderboards.LoadScoresResult> dVar, String str, int i, int i2, int i3, boolean z) {
        try {
            ((IGamesService) fo()).b((IGamesCallbacks) new LeaderboardScoresLoadedBinderCallback(dVar), str, i, i2, i3, z);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void b(a.d<GamesMetadata.LoadExtendedGamesResult> dVar, String str, int i, boolean z, boolean z2) {
        try {
            ((IGamesService) fo()).a((IGamesCallbacks) new ExtendedGamesLoadedBinderCallback(dVar), str, i, z, z2);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void b(a.d<Quests.ClaimMilestoneResult> dVar, String str, String str2) {
        try {
            this.Nm.flush();
            ((IGamesService) fo()).f(new QuestMilestoneClaimBinderCallbacks(dVar, str2), str, str2);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void b(a.d<Leaderboards.LoadScoresResult> dVar, String str, String str2, int i, int i2, int i3, boolean z) {
        try {
            ((IGamesService) fo()).b(new LeaderboardScoresLoadedBinderCallback(dVar), str, str2, i, i2, i3, z);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void b(a.d<Achievements.LoadAchievementsResult> dVar, String str, String str2, boolean z) {
        try {
            ((IGamesService) fo()).a((IGamesCallbacks) new AchievementsLoadedBinderCallback(dVar), str, str2, z);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void b(a.d<Snapshots.OpenSnapshotResult> dVar, String str, boolean z) {
        try {
            ((IGamesService) fo()).e((IGamesCallbacks) new SnapshotOpenedBinderCallbacks(dVar), str, z);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void b(a.d<Leaderboards.LeaderboardMetadataResult> dVar, boolean z) {
        try {
            ((IGamesService) fo()).b((IGamesCallbacks) new LeaderboardsLoadedBinderCallback(dVar), z);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void b(a.d<Quests.LoadQuestsResult> dVar, boolean z, String[] strArr) {
        try {
            ((IGamesService) fo()).a((IGamesCallbacks) new QuestsLoadedBinderCallbacks(dVar), strArr, z);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void b(a.d<Requests.UpdateRequestsResult> dVar, String[] strArr) {
        try {
            ((IGamesService) fo()).a((IGamesCallbacks) new RequestsUpdatedBinderCallbacks(dVar), strArr);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void b(RoomConfig roomConfig) {
        try {
            ((IGamesService) fo()).a((IGamesCallbacks) new RoomBinderCallbacks(roomConfig.getRoomUpdateListener(), roomConfig.getRoomStatusUpdateListener(), roomConfig.getMessageReceivedListener()), (IBinder) this.Nv, roomConfig.getInvitationId(), roomConfig.isSocketEnabled(), this.Nw);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    /* access modifiers changed from: protected */
    public void b(String... strArr) {
        boolean z = false;
        boolean z2 = false;
        for (String str : strArr) {
            if (str.equals(Scopes.GAMES)) {
                z2 = true;
            } else if (str.equals("https://www.googleapis.com/auth/games.firstparty")) {
                z = true;
            }
        }
        if (z) {
            hn.a(!z2, "Cannot have both %s and %s!", Scopes.GAMES, "https://www.googleapis.com/auth/games.firstparty");
            return;
        }
        hn.a(z2, "Games APIs requires %s to function.", Scopes.GAMES);
    }

    /* access modifiers changed from: protected */
    public String bp() {
        return "com.google.android.gms.games.service.START";
    }

    /* access modifiers changed from: protected */
    public String bq() {
        return "com.google.android.gms.games.internal.IGamesService";
    }

    public void c(a.d<Invitations.LoadInvitationsResult> dVar, int i) {
        try {
            ((IGamesService) fo()).a((IGamesCallbacks) new InvitationsLoadedBinderCallback(dVar), i);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void c(a.d<Players.LoadPlayersResult> dVar, int i, boolean z, boolean z2) {
        try {
            ((IGamesService) fo()).c((IGamesCallbacks) new PlayersLoadedBinderCallback(dVar), i, z, z2);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void c(a.d<Achievements.UpdateAchievementResult> dVar, String str) {
        try {
            ((IGamesService) fo()).b((IGamesCallbacks) dVar == null ? null : new AchievementUpdatedBinderCallback(dVar), str, this.Nr.hI(), this.Nr.hH());
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void c(a.d<Players.LoadXpStreamResult> dVar, String str, int i) {
        try {
            ((IGamesService) fo()).b((IGamesCallbacks) new PlayerXpStreamLoadedBinderCallback(dVar), str, i);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void c(a.d<GamesMetadata.LoadExtendedGamesResult> dVar, String str, int i, boolean z, boolean z2) {
        try {
            ((IGamesService) fo()).e(new ExtendedGamesLoadedBinderCallback(dVar), str, i, z, z2);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void c(a.d<TurnBasedMultiplayer.InitiateMatchResult> dVar, String str, String str2) {
        try {
            ((IGamesService) fo()).d((IGamesCallbacks) new TurnBasedMatchInitiatedBinderCallbacks(dVar), str, str2);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void c(a.d<Snapshots.LoadSnapshotsResult> dVar, String str, String str2, boolean z) {
        try {
            ((IGamesService) fo()).c((IGamesCallbacks) new SnapshotsLoadedBinderCallbacks(dVar), str, str2, z);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void c(a.d<Leaderboards.LeaderboardMetadataResult> dVar, String str, boolean z) {
        try {
            ((IGamesService) fo()).d((IGamesCallbacks) new LeaderboardsLoadedBinderCallback(dVar), str, z);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void c(a.d<Achievements.LoadAchievementsResult> dVar, boolean z) {
        try {
            ((IGamesService) fo()).a((IGamesCallbacks) new AchievementsLoadedBinderCallback(dVar), z);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void c(a.d<Requests.UpdateRequestsResult> dVar, String[] strArr) {
        try {
            ((IGamesService) fo()).b((IGamesCallbacks) new RequestsUpdatedBinderCallbacks(dVar), strArr);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void cg(int i) {
        this.Nr.setGravity(i);
    }

    public void ch(int i) {
        try {
            ((IGamesService) fo()).ch(i);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void connect() {
        gT();
        super.connect();
    }

    public int d(byte[] bArr, String str) {
        try {
            return ((IGamesService) fo()).b(bArr, str, (String[]) null);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
            return -1;
        }
    }

    public void d(a.d<Players.LoadPlayersResult> dVar, int i, boolean z, boolean z2) {
        try {
            ((IGamesService) fo()).e(new PlayersLoadedBinderCallback(dVar), i, z, z2);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void d(a.d<TurnBasedMultiplayer.InitiateMatchResult> dVar, String str) {
        try {
            ((IGamesService) fo()).l((IGamesCallbacks) new TurnBasedMatchInitiatedBinderCallbacks(dVar), str);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void d(a.d<Players.LoadXpStreamResult> dVar, String str, int i) {
        try {
            ((IGamesService) fo()).c((IGamesCallbacks) new PlayerXpStreamLoadedBinderCallback(dVar), str, i);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void d(a.d<GamesMetadata.LoadExtendedGamesResult> dVar, String str, int i, boolean z, boolean z2) {
        try {
            ((IGamesService) fo()).f(new ExtendedGamesLoadedBinderCallback(dVar), str, i, z, z2);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void d(a.d<TurnBasedMultiplayer.InitiateMatchResult> dVar, String str, String str2) {
        try {
            ((IGamesService) fo()).e((IGamesCallbacks) new TurnBasedMatchInitiatedBinderCallbacks(dVar), str, str2);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void d(a.d<Notifications.GameMuteStatusChangeResult> dVar, String str, boolean z) {
        try {
            ((IGamesService) fo()).a((IGamesCallbacks) new GameMuteStatusChangedBinderCallback(dVar), str, z);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void d(a.d<Events.LoadEventsResult> dVar, boolean z) {
        try {
            this.Nm.flush();
            ((IGamesService) fo()).f((IGamesCallbacks) new EventsLoadedBinderCallback(dVar), z);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void disconnect() {
        this.Ns = false;
        if (isConnected()) {
            try {
                IGamesService iGamesService = (IGamesService) fo();
                iGamesService.hr();
                this.Nm.flush();
                iGamesService.q(this.Nw);
            } catch (RemoteException e) {
                GamesLog.j("GamesClientImpl", "Failed to notify client disconnect.");
            }
        }
        hq();
        super.disconnect();
    }

    public void e(a.d<Players.LoadPlayersResult> dVar, int i, boolean z, boolean z2) {
        try {
            ((IGamesService) fo()).d(new PlayersLoadedBinderCallback(dVar), i, z, z2);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void e(a.d<TurnBasedMultiplayer.InitiateMatchResult> dVar, String str) {
        try {
            ((IGamesService) fo()).m((IGamesCallbacks) new TurnBasedMatchInitiatedBinderCallbacks(dVar), str);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void e(a.d<Invitations.LoadInvitationsResult> dVar, String str, int i) {
        try {
            ((IGamesService) fo()).b((IGamesCallbacks) new InvitationsLoadedBinderCallback(dVar), str, i, false);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void e(a.d<GamesMetadata.LoadExtendedGamesResult> dVar, String str, int i, boolean z, boolean z2) {
        try {
            ((IGamesService) fo()).c(new ExtendedGamesLoadedBinderCallback(dVar), str, i, z, z2);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void e(a.d<Snapshots.LoadSnapshotsResult> dVar, boolean z) {
        try {
            ((IGamesService) fo()).d((IGamesCallbacks) new SnapshotsLoadedBinderCallbacks(dVar), z);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public Bundle ea() {
        try {
            Bundle ea = ((IGamesService) fo()).ea();
            if (ea == null) {
                return ea;
            }
            ea.setClassLoader(GamesClientImpl.class.getClassLoader());
            return ea;
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
            return null;
        }
    }

    public void f(View view) {
        this.Nr.g(view);
    }

    public void f(a.d<GamesMetadata.LoadGamesResult> dVar) {
        try {
            ((IGamesService) fo()).d(new GamesLoadedBinderCallback(dVar));
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void f(a.d<TurnBasedMultiplayer.LeaveMatchResult> dVar, String str) {
        try {
            ((IGamesService) fo()).o(new TurnBasedMatchLeftBinderCallbacks(dVar), str);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void f(a.d<Requests.LoadRequestSummariesResult> dVar, String str, int i) {
        try {
            ((IGamesService) fo()).a((IGamesCallbacks) new RequestSummariesLoadedBinderCallbacks(dVar), str, i);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void f(a.d<Players.LoadPlayersResult> dVar, String str, int i, boolean z, boolean z2) {
        try {
            ((IGamesService) fo()).b((IGamesCallbacks) new PlayersLoadedBinderCallback(dVar), str, i, z, z2);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void f(a.d<Notifications.ContactSettingLoadResult> dVar, boolean z) {
        try {
            ((IGamesService) fo()).e((IGamesCallbacks) new ContactSettingsLoadedBinderCallback(dVar), z);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void g(a.d<Players.LoadOwnerCoverPhotoUrisResult> dVar) {
        try {
            ((IGamesService) fo()).j(new OwnerCoverPhotoUrisLoadedBinderCallback(dVar));
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void g(a.d<TurnBasedMultiplayer.CancelMatchResult> dVar, String str) {
        try {
            ((IGamesService) fo()).n((IGamesCallbacks) new TurnBasedMatchCanceledBinderCallbacks(dVar), str);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void g(a.d<Players.LoadPlayersResult> dVar, String str, int i, boolean z, boolean z2) {
        try {
            ((IGamesService) fo()).b((IGamesCallbacks) new PlayersLoadedBinderCallback(dVar), str, (String) null, i, z, z2);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public String gU() {
        try {
            return ((IGamesService) fo()).gU();
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
            return null;
        }
    }

    public String gV() {
        try {
            return ((IGamesService) fo()).gV();
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
            return null;
        }
    }

    public Player gW() {
        PlayerBuffer playerBuffer;
        ci();
        synchronized (this) {
            if (this.Np == null) {
                try {
                    playerBuffer = new PlayerBuffer(((IGamesService) fo()).ht());
                    if (playerBuffer.getCount() > 0) {
                        this.Np = (PlayerEntity) playerBuffer.get(0).freeze();
                    }
                    playerBuffer.close();
                } catch (RemoteException e) {
                    GamesLog.j("GamesClientImpl", "service died");
                } catch (Throwable th) {
                    playerBuffer.close();
                    throw th;
                }
            }
        }
        return this.Np;
    }

    public Game gX() {
        GameBuffer gameBuffer;
        ci();
        synchronized (this) {
            if (this.Nq == null) {
                try {
                    gameBuffer = new GameBuffer(((IGamesService) fo()).hv());
                    if (gameBuffer.getCount() > 0) {
                        this.Nq = (GameEntity) gameBuffer.get(0).freeze();
                    }
                    gameBuffer.close();
                } catch (RemoteException e) {
                    GamesLog.j("GamesClientImpl", "service died");
                } catch (Throwable th) {
                    gameBuffer.close();
                    throw th;
                }
            }
        }
        return this.Nq;
    }

    public Intent gY() {
        try {
            return ((IGamesService) fo()).gY();
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
            return null;
        }
    }

    public Intent gZ() {
        try {
            return ((IGamesService) fo()).gZ();
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
            return null;
        }
    }

    public void h(a.d<Acls.LoadAclResult> dVar) {
        try {
            ((IGamesService) fo()).h(new NotifyAclLoadedBinderCallback(dVar));
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void h(a.d<TurnBasedMultiplayer.LoadMatchResult> dVar, String str) {
        try {
            ((IGamesService) fo()).p((IGamesCallbacks) new TurnBasedMatchLoadedBinderCallbacks(dVar), str);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public Intent ha() {
        try {
            return ((IGamesService) fo()).ha();
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
            return null;
        }
    }

    public Intent hb() {
        try {
            return ((IGamesService) fo()).hb();
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
            return null;
        }
    }

    public void hc() {
        try {
            ((IGamesService) fo()).r(this.Nw);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void hd() {
        try {
            ((IGamesService) fo()).s(this.Nw);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void he() {
        try {
            ((IGamesService) fo()).u(this.Nw);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void hf() {
        try {
            ((IGamesService) fo()).t(this.Nw);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public Intent hg() {
        try {
            return ((IGamesService) fo()).hg();
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
            return null;
        }
    }

    public Intent hh() {
        try {
            return ((IGamesService) fo()).hh();
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
            return null;
        }
    }

    public int hi() {
        try {
            return ((IGamesService) fo()).hi();
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
            return 4368;
        }
    }

    public String hj() {
        try {
            return ((IGamesService) fo()).hj();
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
            return null;
        }
    }

    public int hk() {
        try {
            return ((IGamesService) fo()).hk();
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
            return -1;
        }
    }

    public Intent hl() {
        try {
            return ((IGamesService) fo()).hl();
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
            return null;
        }
    }

    public int hm() {
        try {
            return ((IGamesService) fo()).hm();
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
            return -1;
        }
    }

    public int hn() {
        try {
            return ((IGamesService) fo()).hn();
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
            return -1;
        }
    }

    public int ho() {
        try {
            return ((IGamesService) fo()).ho();
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
            return -1;
        }
    }

    public int hp() {
        try {
            return ((IGamesService) fo()).hp();
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
            return -1;
        }
    }

    public void hr() {
        if (isConnected()) {
            try {
                ((IGamesService) fo()).hr();
            } catch (RemoteException e) {
                GamesLog.j("GamesClientImpl", "service died");
            }
        }
    }

    @Deprecated
    public void i(a.d<Notifications.ContactSettingLoadResult> dVar) {
        try {
            ((IGamesService) fo()).e((IGamesCallbacks) new ContactSettingsLoadedBinderCallback(dVar), false);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void i(a.d<Quests.AcceptQuestResult> dVar, String str) {
        try {
            this.Nm.flush();
            ((IGamesService) fo()).u(new QuestAcceptedBinderCallbacks(dVar), str);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void j(a.d<Notifications.InboxCountResult> dVar) {
        try {
            ((IGamesService) fo()).t(new InboxCountsLoadedBinderCallback(dVar), (String) null);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void j(a.d<Snapshots.DeleteSnapshotResult> dVar, String str) {
        try {
            ((IGamesService) fo()).r(new SnapshotDeletedBinderCallbacks(dVar), str);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void k(a.d<GamesMetadata.LoadExtendedGamesResult> dVar, String str) {
        try {
            ((IGamesService) fo()).e((IGamesCallbacks) new ExtendedGamesLoadedBinderCallback(dVar), str);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public RealTimeSocket l(String str, String str2) {
        if (str2 == null || !ParticipantUtils.bn(str2)) {
            throw new IllegalArgumentException("Bad participant ID");
        }
        RealTimeSocket realTimeSocket = this.No.get(str2);
        return (realTimeSocket == null || realTimeSocket.isClosed()) ? aT(str2) : realTimeSocket;
    }

    public void l(a.d<GamesMetadata.LoadGameInstancesResult> dVar, String str) {
        try {
            ((IGamesService) fo()).f((IGamesCallbacks) new GameInstancesLoadedBinderCallback(dVar), str);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void l(String str, int i) {
        this.Nm.l(str, i);
    }

    public void m(a.d<GamesMetadata.LoadGameSearchSuggestionsResult> dVar, String str) {
        try {
            ((IGamesService) fo()).q((IGamesCallbacks) new GameSearchSuggestionsLoadedBinderCallback(dVar), str);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void m(String str, int i) {
        try {
            ((IGamesService) fo()).m(str, i);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void n(a.d<Players.LoadXpForGameCategoriesResult> dVar, String str) {
        try {
            ((IGamesService) fo()).s(new PlayerXpForGameCategoriesLoadedBinderCallback(dVar), str);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void n(String str, int i) {
        try {
            ((IGamesService) fo()).n(str, i);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void o(a.d<Invitations.LoadInvitationsResult> dVar, String str) {
        try {
            ((IGamesService) fo()).k(new InvitationsLoadedBinderCallback(dVar), str);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void onConnected(Bundle connectionHint) {
        if (this.Ns) {
            this.Nr.hG();
            this.Ns = false;
        }
    }

    public void onConnectionFailed(ConnectionResult result) {
        this.Ns = false;
    }

    public void onConnectionSuspended(int cause) {
    }

    public void p(a.d<Status> dVar, String str) {
        try {
            ((IGamesService) fo()).j(new NotifyAclUpdatedBinderCallback(dVar), str);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }

    public void q(a.d<Notifications.GameMuteStatusLoadResult> dVar, String str) {
        try {
            ((IGamesService) fo()).i(new GameMuteStatusLoadedBinderCallback(dVar), str);
        } catch (RemoteException e) {
            GamesLog.j("GamesClientImpl", "service died");
        }
    }
}
