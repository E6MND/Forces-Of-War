package com.google.android.gms.games.internal.api;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.a;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.games.request.GameRequest;
import com.google.android.gms.games.request.GameRequestBuffer;
import com.google.android.gms.games.request.OnRequestReceivedListener;
import com.google.android.gms.games.request.Requests;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public final class RequestsImpl implements Requests {

    /* renamed from: com.google.android.gms.games.internal.api.RequestsImpl$4  reason: invalid class name */
    class AnonymousClass4 extends SendRequestImpl {
        final /* synthetic */ String Pe;
        final /* synthetic */ int QA;
        final /* synthetic */ String[] Qx;
        final /* synthetic */ int Qy;
        final /* synthetic */ byte[] Qz;

        /* access modifiers changed from: protected */
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.a((a.d<Requests.SendRequestResult>) this, this.Pe, this.Qx, this.Qy, this.Qz, this.QA);
        }
    }

    /* renamed from: com.google.android.gms.games.internal.api.RequestsImpl$5  reason: invalid class name */
    class AnonymousClass5 extends SendRequestImpl {
        final /* synthetic */ String Pe;
        final /* synthetic */ int QA;
        final /* synthetic */ String[] Qx;
        final /* synthetic */ int Qy;
        final /* synthetic */ byte[] Qz;

        /* access modifiers changed from: protected */
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.a((a.d<Requests.SendRequestResult>) this, this.Pe, this.Qx, this.Qy, this.Qz, this.QA);
        }
    }

    /* renamed from: com.google.android.gms.games.internal.api.RequestsImpl$6  reason: invalid class name */
    class AnonymousClass6 extends UpdateRequestsImpl {
        final /* synthetic */ String Pe;
        final /* synthetic */ String Qp;
        final /* synthetic */ String[] Qt;

        /* access modifiers changed from: protected */
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.a((a.d<Requests.UpdateRequestsResult>) this, this.Pe, this.Qp, this.Qt);
        }
    }

    /* renamed from: com.google.android.gms.games.internal.api.RequestsImpl$7  reason: invalid class name */
    class AnonymousClass7 extends LoadRequestsImpl {
        final /* synthetic */ int PB;
        final /* synthetic */ String Pe;
        final /* synthetic */ String Qp;
        final /* synthetic */ int Qv;
        final /* synthetic */ int Qw;

        /* access modifiers changed from: protected */
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.a((a.d<Requests.LoadRequestsResult>) this, this.Pe, this.Qp, this.Qv, this.Qw, this.PB);
        }
    }

    /* renamed from: com.google.android.gms.games.internal.api.RequestsImpl$8  reason: invalid class name */
    class AnonymousClass8 extends LoadRequestSummariesImpl {
        final /* synthetic */ String Qp;
        final /* synthetic */ int Qw;

        /* access modifiers changed from: protected */
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.f(this, this.Qp, this.Qw);
        }
    }

    private static abstract class LoadRequestSummariesImpl extends Games.BaseGamesApiMethodImpl<Requests.LoadRequestSummariesResult> {
        private LoadRequestSummariesImpl() {
        }

        /* renamed from: V */
        public Requests.LoadRequestSummariesResult c(final Status status) {
            return new Requests.LoadRequestSummariesResult() {
                public Status getStatus() {
                    return status;
                }

                public void release() {
                }
            };
        }
    }

    private static abstract class LoadRequestsImpl extends Games.BaseGamesApiMethodImpl<Requests.LoadRequestsResult> {
        private LoadRequestsImpl() {
        }

        /* renamed from: W */
        public Requests.LoadRequestsResult c(final Status status) {
            return new Requests.LoadRequestsResult() {
                public GameRequestBuffer getRequests(int type) {
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

    private static abstract class SendRequestImpl extends Games.BaseGamesApiMethodImpl<Requests.SendRequestResult> {
        private SendRequestImpl() {
        }

        /* renamed from: X */
        public Requests.SendRequestResult c(final Status status) {
            return new Requests.SendRequestResult() {
                public Status getStatus() {
                    return status;
                }
            };
        }
    }

    private static abstract class UpdateRequestsImpl extends Games.BaseGamesApiMethodImpl<Requests.UpdateRequestsResult> {
        private UpdateRequestsImpl() {
        }

        /* renamed from: Y */
        public Requests.UpdateRequestsResult c(final Status status) {
            return new Requests.UpdateRequestsResult() {
                public Set<String> getRequestIds() {
                    return null;
                }

                public int getRequestOutcome(String requestId) {
                    throw new IllegalArgumentException("Unknown request ID " + requestId);
                }

                public Status getStatus() {
                    return status;
                }

                public void release() {
                }
            };
        }
    }

    public PendingResult<Requests.UpdateRequestsResult> acceptRequest(GoogleApiClient apiClient, String requestId) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(requestId);
        return acceptRequests(apiClient, arrayList);
    }

    public PendingResult<Requests.UpdateRequestsResult> acceptRequests(GoogleApiClient apiClient, List<String> requestIds) {
        final String[] strArr = requestIds == null ? null : (String[]) requestIds.toArray(new String[requestIds.size()]);
        return apiClient.b(new UpdateRequestsImpl() {
            /* access modifiers changed from: protected */
            public void a(GamesClientImpl gamesClientImpl) {
                gamesClientImpl.b((a.d<Requests.UpdateRequestsResult>) this, strArr);
            }
        });
    }

    public PendingResult<Requests.UpdateRequestsResult> dismissRequest(GoogleApiClient apiClient, String requestId) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(requestId);
        return dismissRequests(apiClient, arrayList);
    }

    public PendingResult<Requests.UpdateRequestsResult> dismissRequests(GoogleApiClient apiClient, List<String> requestIds) {
        final String[] strArr = requestIds == null ? null : (String[]) requestIds.toArray(new String[requestIds.size()]);
        return apiClient.b(new UpdateRequestsImpl() {
            /* access modifiers changed from: protected */
            public void a(GamesClientImpl gamesClientImpl) {
                gamesClientImpl.c((a.d<Requests.UpdateRequestsResult>) this, strArr);
            }
        });
    }

    public ArrayList<GameRequest> getGameRequestsFromBundle(Bundle extras) {
        if (extras == null || !extras.containsKey(Requests.EXTRA_REQUESTS)) {
            return new ArrayList<>();
        }
        ArrayList arrayList = (ArrayList) extras.get(Requests.EXTRA_REQUESTS);
        ArrayList<GameRequest> arrayList2 = new ArrayList<>();
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            arrayList2.add((GameRequest) arrayList.get(i));
        }
        return arrayList2;
    }

    public ArrayList<GameRequest> getGameRequestsFromInboxResponse(Intent response) {
        return response == null ? new ArrayList<>() : getGameRequestsFromBundle(response.getExtras());
    }

    public Intent getInboxIntent(GoogleApiClient apiClient) {
        return Games.c(apiClient).hl();
    }

    public int getMaxLifetimeDays(GoogleApiClient apiClient) {
        return Games.c(apiClient).hn();
    }

    public int getMaxPayloadSize(GoogleApiClient apiClient) {
        return Games.c(apiClient).hm();
    }

    public Intent getSendIntent(GoogleApiClient apiClient, int type, byte[] payload, int requestLifetimeDays, Bitmap icon, String description) {
        return Games.c(apiClient).a(type, payload, requestLifetimeDays, icon, description);
    }

    public PendingResult<Requests.LoadRequestsResult> loadRequests(GoogleApiClient apiClient, final int requestDirection, final int types, final int sortOrder) {
        return apiClient.a(new LoadRequestsImpl() {
            /* access modifiers changed from: protected */
            public void a(GamesClientImpl gamesClientImpl) {
                gamesClientImpl.a((a.d<Requests.LoadRequestsResult>) this, requestDirection, types, sortOrder);
            }
        });
    }

    public void registerRequestListener(GoogleApiClient apiClient, OnRequestReceivedListener listener) {
        Games.c(apiClient).a(listener);
    }

    public void unregisterRequestListener(GoogleApiClient apiClient) {
        Games.c(apiClient).hf();
    }
}
