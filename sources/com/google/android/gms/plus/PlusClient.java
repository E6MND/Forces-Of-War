package com.google.android.gms.plus;

import android.content.Context;
import android.net.Uri;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.a;
import com.google.android.gms.plus.Moments;
import com.google.android.gms.plus.People;
import com.google.android.gms.plus.internal.e;
import com.google.android.gms.plus.internal.i;
import com.google.android.gms.plus.model.moments.Moment;
import com.google.android.gms.plus.model.moments.MomentBuffer;
import com.google.android.gms.plus.model.people.Person;
import com.google.android.gms.plus.model.people.PersonBuffer;
import java.util.Collection;

@Deprecated
public class PlusClient implements GooglePlayServicesClient {
    final e abq;

    @Deprecated
    public static class Builder {
        private final GooglePlayServicesClient.ConnectionCallbacks abv;
        private final GooglePlayServicesClient.OnConnectionFailedListener abw;
        private final i abx = new i(this.mContext);
        private final Context mContext;

        public Builder(Context context, GooglePlayServicesClient.ConnectionCallbacks connectionCallbacks, GooglePlayServicesClient.OnConnectionFailedListener connectionFailedListener) {
            this.mContext = context;
            this.abv = connectionCallbacks;
            this.abw = connectionFailedListener;
        }

        public PlusClient build() {
            return new PlusClient(new e(this.mContext, this.abv, this.abw, this.abx.ke()));
        }

        public Builder clearScopes() {
            this.abx.kd();
            return this;
        }

        public Builder setAccountName(String accountName) {
            this.abx.bz(accountName);
            return this;
        }

        public Builder setActions(String... actions) {
            this.abx.f(actions);
            return this;
        }

        public Builder setScopes(String... scopes) {
            this.abx.e(scopes);
            return this;
        }
    }

    @Deprecated
    public interface OnAccessRevokedListener {
        void onAccessRevoked(ConnectionResult connectionResult);
    }

    @Deprecated
    public interface OnMomentsLoadedListener {
        @Deprecated
        void onMomentsLoaded(ConnectionResult connectionResult, MomentBuffer momentBuffer, String str, String str2);
    }

    @Deprecated
    public interface OnPeopleLoadedListener {
        void onPeopleLoaded(ConnectionResult connectionResult, PersonBuffer personBuffer, String str);
    }

    @Deprecated
    public interface OrderBy {
        @Deprecated
        public static final int ALPHABETICAL = 0;
        @Deprecated
        public static final int BEST = 1;
    }

    PlusClient(e plusClientImpl) {
        this.abq = plusClientImpl;
    }

    @Deprecated
    public void clearDefaultAccount() {
        this.abq.clearDefaultAccount();
    }

    @Deprecated
    public void connect() {
        this.abq.connect();
    }

    @Deprecated
    public void disconnect() {
        this.abq.disconnect();
    }

    @Deprecated
    public String getAccountName() {
        return this.abq.getAccountName();
    }

    @Deprecated
    public Person getCurrentPerson() {
        return this.abq.getCurrentPerson();
    }

    @Deprecated
    public boolean isConnected() {
        return this.abq.isConnected();
    }

    @Deprecated
    public boolean isConnecting() {
        return this.abq.isConnecting();
    }

    @Deprecated
    public boolean isConnectionCallbacksRegistered(GooglePlayServicesClient.ConnectionCallbacks listener) {
        return this.abq.isConnectionCallbacksRegistered(listener);
    }

    @Deprecated
    public boolean isConnectionFailedListenerRegistered(GooglePlayServicesClient.OnConnectionFailedListener listener) {
        return this.abq.isConnectionFailedListenerRegistered(listener);
    }

    /* access modifiers changed from: package-private */
    public e jN() {
        return this.abq;
    }

    @Deprecated
    public void loadMoments(final OnMomentsLoadedListener listener) {
        this.abq.k(new a.d<Moments.LoadMomentsResult>() {
            public void a(Moments.LoadMomentsResult loadMomentsResult) {
                listener.onMomentsLoaded(loadMomentsResult.getStatus().eM(), loadMomentsResult.getMomentBuffer(), loadMomentsResult.getNextPageToken(), loadMomentsResult.getUpdated());
            }
        });
    }

    @Deprecated
    public void loadMoments(final OnMomentsLoadedListener listener, int maxResults, String pageToken, Uri targetUrl, String type, String userId) {
        this.abq.a(new a.d<Moments.LoadMomentsResult>() {
            public void a(Moments.LoadMomentsResult loadMomentsResult) {
                listener.onMomentsLoaded(loadMomentsResult.getStatus().eM(), loadMomentsResult.getMomentBuffer(), loadMomentsResult.getNextPageToken(), loadMomentsResult.getUpdated());
            }
        }, maxResults, pageToken, targetUrl, type, userId);
    }

    @Deprecated
    public void loadPeople(final OnPeopleLoadedListener listener, Collection<String> personIds) {
        this.abq.a((a.d<People.LoadPeopleResult>) new a.d<People.LoadPeopleResult>() {
            public void a(People.LoadPeopleResult loadPeopleResult) {
                listener.onPeopleLoaded(loadPeopleResult.getStatus().eM(), loadPeopleResult.getPersonBuffer(), loadPeopleResult.getNextPageToken());
            }
        }, personIds);
    }

    @Deprecated
    public void loadPeople(final OnPeopleLoadedListener listener, String... personIds) {
        this.abq.d(new a.d<People.LoadPeopleResult>() {
            public void a(People.LoadPeopleResult loadPeopleResult) {
                listener.onPeopleLoaded(loadPeopleResult.getStatus().eM(), loadPeopleResult.getPersonBuffer(), loadPeopleResult.getNextPageToken());
            }
        }, personIds);
    }

    @Deprecated
    public void loadVisiblePeople(final OnPeopleLoadedListener listener, int orderBy, String pageToken) {
        this.abq.a((a.d<People.LoadPeopleResult>) new a.d<People.LoadPeopleResult>() {
            public void a(People.LoadPeopleResult loadPeopleResult) {
                listener.onPeopleLoaded(loadPeopleResult.getStatus().eM(), loadPeopleResult.getPersonBuffer(), loadPeopleResult.getNextPageToken());
            }
        }, orderBy, pageToken);
    }

    @Deprecated
    public void loadVisiblePeople(final OnPeopleLoadedListener listener, String pageToken) {
        this.abq.r(new a.d<People.LoadPeopleResult>() {
            public void a(People.LoadPeopleResult loadPeopleResult) {
                listener.onPeopleLoaded(loadPeopleResult.getStatus().eM(), loadPeopleResult.getPersonBuffer(), loadPeopleResult.getNextPageToken());
            }
        }, pageToken);
    }

    @Deprecated
    public void registerConnectionCallbacks(GooglePlayServicesClient.ConnectionCallbacks listener) {
        this.abq.registerConnectionCallbacks(listener);
    }

    @Deprecated
    public void registerConnectionFailedListener(GooglePlayServicesClient.OnConnectionFailedListener listener) {
        this.abq.registerConnectionFailedListener(listener);
    }

    @Deprecated
    public void removeMoment(String momentId) {
        this.abq.removeMoment(momentId);
    }

    @Deprecated
    public void revokeAccessAndDisconnect(final OnAccessRevokedListener listener) {
        this.abq.m(new a.d<Status>() {
            /* renamed from: al */
            public void a(Status status) {
                listener.onAccessRevoked(status.getStatus().eM());
            }
        });
    }

    @Deprecated
    public void unregisterConnectionCallbacks(GooglePlayServicesClient.ConnectionCallbacks listener) {
        this.abq.unregisterConnectionCallbacks(listener);
    }

    @Deprecated
    public void unregisterConnectionFailedListener(GooglePlayServicesClient.OnConnectionFailedListener listener) {
        this.abq.unregisterConnectionFailedListener(listener);
    }

    @Deprecated
    public void writeMoment(Moment moment) {
        this.abq.a((a.d<Status>) null, moment);
    }
}
