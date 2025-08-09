package com.google.android.gms.games.internal.api;

import android.os.Bundle;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.a;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.Notifications;
import com.google.android.gms.games.internal.GamesClientImpl;

public final class NotificationsImpl implements Notifications {

    /* renamed from: com.google.android.gms.games.internal.api.NotificationsImpl$1  reason: invalid class name */
    class AnonymousClass1 extends Games.BaseGamesApiMethodImpl<Notifications.GameMuteStatusChangeResult> {
        final /* synthetic */ String PS;

        /* renamed from: K */
        public Notifications.GameMuteStatusChangeResult c(final Status status) {
            return new Notifications.GameMuteStatusChangeResult() {
                public Status getStatus() {
                    return status;
                }
            };
        }

        /* access modifiers changed from: protected */
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.d((a.d<Notifications.GameMuteStatusChangeResult>) this, this.PS, true);
        }
    }

    /* renamed from: com.google.android.gms.games.internal.api.NotificationsImpl$2  reason: invalid class name */
    class AnonymousClass2 extends Games.BaseGamesApiMethodImpl<Notifications.GameMuteStatusChangeResult> {
        final /* synthetic */ String PS;

        /* renamed from: K */
        public Notifications.GameMuteStatusChangeResult c(final Status status) {
            return new Notifications.GameMuteStatusChangeResult() {
                public Status getStatus() {
                    return status;
                }
            };
        }

        /* access modifiers changed from: protected */
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.d((a.d<Notifications.GameMuteStatusChangeResult>) this, this.PS, false);
        }
    }

    /* renamed from: com.google.android.gms.games.internal.api.NotificationsImpl$3  reason: invalid class name */
    class AnonymousClass3 extends Games.BaseGamesApiMethodImpl<Notifications.GameMuteStatusLoadResult> {
        final /* synthetic */ String PS;

        /* renamed from: L */
        public Notifications.GameMuteStatusLoadResult c(final Status status) {
            return new Notifications.GameMuteStatusLoadResult() {
                public Status getStatus() {
                    return status;
                }
            };
        }

        /* access modifiers changed from: protected */
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.q(this, this.PS);
        }
    }

    /* renamed from: com.google.android.gms.games.internal.api.NotificationsImpl$4  reason: invalid class name */
    class AnonymousClass4 extends ContactSettingLoadImpl {
        /* access modifiers changed from: protected */
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.i(this);
        }
    }

    /* renamed from: com.google.android.gms.games.internal.api.NotificationsImpl$5  reason: invalid class name */
    class AnonymousClass5 extends ContactSettingLoadImpl {
        final /* synthetic */ boolean Pb;

        /* access modifiers changed from: protected */
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.f((a.d<Notifications.ContactSettingLoadResult>) this, this.Pb);
        }
    }

    /* renamed from: com.google.android.gms.games.internal.api.NotificationsImpl$6  reason: invalid class name */
    class AnonymousClass6 extends ContactSettingUpdateImpl {
        final /* synthetic */ boolean PW;
        final /* synthetic */ Bundle PX;

        /* access modifiers changed from: protected */
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.a((a.d<Status>) this, this.PW, this.PX);
        }
    }

    /* renamed from: com.google.android.gms.games.internal.api.NotificationsImpl$7  reason: invalid class name */
    class AnonymousClass7 extends InboxCountImpl {
        /* access modifiers changed from: protected */
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.j(this);
        }
    }

    private static abstract class ContactSettingLoadImpl extends Games.BaseGamesApiMethodImpl<Notifications.ContactSettingLoadResult> {
        private ContactSettingLoadImpl() {
        }

        /* renamed from: M */
        public Notifications.ContactSettingLoadResult c(final Status status) {
            return new Notifications.ContactSettingLoadResult() {
                public Status getStatus() {
                    return status;
                }
            };
        }
    }

    private static abstract class ContactSettingUpdateImpl extends Games.BaseGamesApiMethodImpl<Status> {
        private ContactSettingUpdateImpl() {
        }

        /* renamed from: d */
        public Status c(Status status) {
            return status;
        }
    }

    private static abstract class InboxCountImpl extends Games.BaseGamesApiMethodImpl<Notifications.InboxCountResult> {
        private InboxCountImpl() {
        }

        /* renamed from: N */
        public Notifications.InboxCountResult c(final Status status) {
            return new Notifications.InboxCountResult() {
                public Status getStatus() {
                    return status;
                }
            };
        }
    }

    public void clear(GoogleApiClient apiClient, int notificationTypes) {
        Games.c(apiClient).ch(notificationTypes);
    }

    public void clearAll(GoogleApiClient apiClient) {
        clear(apiClient, 31);
    }
}
