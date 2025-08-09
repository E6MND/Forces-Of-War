package com.google.android.gms.cast;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Looper;
import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.gms.cast.LaunchOptions;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.a;
import com.google.android.gms.internal.gi;
import com.google.android.gms.internal.gz;
import com.google.android.gms.internal.hn;
import java.io.IOException;

public final class Cast {
    public static final Api<CastOptions> API = new Api<>(yF, yE, new Scope[0]);
    public static final CastApi CastApi = new CastApi.a();
    public static final String EXTRA_APP_NO_LONGER_RUNNING = "com.google.android.gms.cast.EXTRA_APP_NO_LONGER_RUNNING";
    public static final int MAX_MESSAGE_LENGTH = 65536;
    public static final int MAX_NAMESPACE_LENGTH = 128;
    static final Api.c<gi> yE = new Api.c<>();
    private static final Api.b<gi, CastOptions> yF = new Api.b<gi, CastOptions>() {
        public gi a(Context context, Looper looper, gz gzVar, CastOptions castOptions, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
            hn.b(castOptions, (Object) "Setting the API options is required.");
            return new gi(context, looper, castOptions.zX, (long) castOptions.zZ, castOptions.zY, connectionCallbacks, onConnectionFailedListener);
        }

        public int getPriority() {
            return Integer.MAX_VALUE;
        }
    };

    public interface ApplicationConnectionResult extends Result {
        ApplicationMetadata getApplicationMetadata();

        String getApplicationStatus();

        String getSessionId();

        boolean getWasLaunched();
    }

    public interface CastApi {

        public static final class a implements CastApi {
            public ApplicationMetadata getApplicationMetadata(GoogleApiClient client) throws IllegalStateException {
                return ((gi) client.a(Cast.yE)).getApplicationMetadata();
            }

            public String getApplicationStatus(GoogleApiClient client) throws IllegalStateException {
                return ((gi) client.a(Cast.yE)).getApplicationStatus();
            }

            public double getVolume(GoogleApiClient client) throws IllegalStateException {
                return ((gi) client.a(Cast.yE)).ec();
            }

            public boolean isMute(GoogleApiClient client) throws IllegalStateException {
                return ((gi) client.a(Cast.yE)).isMute();
            }

            public PendingResult<ApplicationConnectionResult> joinApplication(GoogleApiClient client) {
                return client.b(new c() {
                    /* access modifiers changed from: protected */
                    public void a(gi giVar) throws RemoteException {
                        try {
                            giVar.b((String) null, (String) null, this);
                        } catch (IllegalStateException e) {
                            N(2001);
                        }
                    }
                });
            }

            public PendingResult<ApplicationConnectionResult> joinApplication(GoogleApiClient client, final String applicationId) {
                return client.b(new c() {
                    /* access modifiers changed from: protected */
                    public void a(gi giVar) throws RemoteException {
                        try {
                            giVar.b(applicationId, (String) null, this);
                        } catch (IllegalStateException e) {
                            N(2001);
                        }
                    }
                });
            }

            public PendingResult<ApplicationConnectionResult> joinApplication(GoogleApiClient client, final String applicationId, final String sessionId) {
                return client.b(new c() {
                    /* access modifiers changed from: protected */
                    public void a(gi giVar) throws RemoteException {
                        try {
                            giVar.b(applicationId, sessionId, this);
                        } catch (IllegalStateException e) {
                            N(2001);
                        }
                    }
                });
            }

            public PendingResult<ApplicationConnectionResult> launchApplication(GoogleApiClient client, final String applicationId) {
                return client.b(new c() {
                    /* access modifiers changed from: protected */
                    public void a(gi giVar) throws RemoteException {
                        try {
                            giVar.a(applicationId, false, (a.d<ApplicationConnectionResult>) this);
                        } catch (IllegalStateException e) {
                            N(2001);
                        }
                    }
                });
            }

            public PendingResult<ApplicationConnectionResult> launchApplication(GoogleApiClient client, final String applicationId, final LaunchOptions options) {
                return client.b(new c() {
                    /* access modifiers changed from: protected */
                    public void a(gi giVar) throws RemoteException {
                        try {
                            giVar.a(applicationId, options, (a.d<ApplicationConnectionResult>) this);
                        } catch (IllegalStateException e) {
                            N(2001);
                        }
                    }
                });
            }

            @Deprecated
            public PendingResult<ApplicationConnectionResult> launchApplication(GoogleApiClient client, String applicationId, boolean relaunchIfRunning) {
                return launchApplication(client, applicationId, new LaunchOptions.Builder().setRelaunchIfRunning(relaunchIfRunning).build());
            }

            public PendingResult<Status> leaveApplication(GoogleApiClient client) {
                return client.b(new b() {
                    /* access modifiers changed from: protected */
                    public void a(gi giVar) throws RemoteException {
                        try {
                            giVar.d((a.d<Status>) this);
                        } catch (IllegalStateException e) {
                            N(2001);
                        }
                    }
                });
            }

            public void removeMessageReceivedCallbacks(GoogleApiClient client, String namespace) throws IOException, IllegalArgumentException {
                try {
                    ((gi) client.a(Cast.yE)).aj(namespace);
                } catch (RemoteException e) {
                    throw new IOException("service error");
                }
            }

            public void requestStatus(GoogleApiClient client) throws IOException, IllegalStateException {
                try {
                    ((gi) client.a(Cast.yE)).eb();
                } catch (RemoteException e) {
                    throw new IOException("service error");
                }
            }

            public PendingResult<Status> sendMessage(GoogleApiClient client, final String namespace, final String message) {
                return client.b(new b() {
                    /* access modifiers changed from: protected */
                    public void a(gi giVar) throws RemoteException {
                        try {
                            giVar.a(namespace, message, (a.d<Status>) this);
                        } catch (IllegalArgumentException e) {
                            N(2001);
                        } catch (IllegalStateException e2) {
                            N(2001);
                        }
                    }
                });
            }

            public void setMessageReceivedCallbacks(GoogleApiClient client, String namespace, MessageReceivedCallback callbacks) throws IOException, IllegalStateException {
                try {
                    ((gi) client.a(Cast.yE)).a(namespace, callbacks);
                } catch (RemoteException e) {
                    throw new IOException("service error");
                }
            }

            public void setMute(GoogleApiClient client, boolean mute) throws IOException, IllegalStateException {
                try {
                    ((gi) client.a(Cast.yE)).y(mute);
                } catch (RemoteException e) {
                    throw new IOException("service error");
                }
            }

            public void setVolume(GoogleApiClient client, double volume) throws IOException, IllegalArgumentException, IllegalStateException {
                try {
                    ((gi) client.a(Cast.yE)).a(volume);
                } catch (RemoteException e) {
                    throw new IOException("service error");
                }
            }

            public PendingResult<Status> stopApplication(GoogleApiClient client) {
                return client.b(new b() {
                    /* access modifiers changed from: protected */
                    public void a(gi giVar) throws RemoteException {
                        try {
                            giVar.a("", (a.d<Status>) this);
                        } catch (IllegalStateException e) {
                            N(2001);
                        }
                    }
                });
            }

            public PendingResult<Status> stopApplication(GoogleApiClient client, final String sessionId) {
                return client.b(new b() {
                    /* access modifiers changed from: protected */
                    public void a(gi giVar) throws RemoteException {
                        if (TextUtils.isEmpty(sessionId)) {
                            c(2001, "IllegalArgument: sessionId cannot be null or empty");
                            return;
                        }
                        try {
                            giVar.a(sessionId, (a.d<Status>) this);
                        } catch (IllegalStateException e) {
                            N(2001);
                        }
                    }
                });
            }
        }

        ApplicationMetadata getApplicationMetadata(GoogleApiClient googleApiClient) throws IllegalStateException;

        String getApplicationStatus(GoogleApiClient googleApiClient) throws IllegalStateException;

        double getVolume(GoogleApiClient googleApiClient) throws IllegalStateException;

        boolean isMute(GoogleApiClient googleApiClient) throws IllegalStateException;

        PendingResult<ApplicationConnectionResult> joinApplication(GoogleApiClient googleApiClient);

        PendingResult<ApplicationConnectionResult> joinApplication(GoogleApiClient googleApiClient, String str);

        PendingResult<ApplicationConnectionResult> joinApplication(GoogleApiClient googleApiClient, String str, String str2);

        PendingResult<ApplicationConnectionResult> launchApplication(GoogleApiClient googleApiClient, String str);

        PendingResult<ApplicationConnectionResult> launchApplication(GoogleApiClient googleApiClient, String str, LaunchOptions launchOptions);

        @Deprecated
        PendingResult<ApplicationConnectionResult> launchApplication(GoogleApiClient googleApiClient, String str, boolean z);

        PendingResult<Status> leaveApplication(GoogleApiClient googleApiClient);

        void removeMessageReceivedCallbacks(GoogleApiClient googleApiClient, String str) throws IOException, IllegalArgumentException;

        void requestStatus(GoogleApiClient googleApiClient) throws IOException, IllegalStateException;

        PendingResult<Status> sendMessage(GoogleApiClient googleApiClient, String str, String str2);

        void setMessageReceivedCallbacks(GoogleApiClient googleApiClient, String str, MessageReceivedCallback messageReceivedCallback) throws IOException, IllegalStateException;

        void setMute(GoogleApiClient googleApiClient, boolean z) throws IOException, IllegalStateException;

        void setVolume(GoogleApiClient googleApiClient, double d) throws IOException, IllegalArgumentException, IllegalStateException;

        PendingResult<Status> stopApplication(GoogleApiClient googleApiClient);

        PendingResult<Status> stopApplication(GoogleApiClient googleApiClient, String str);
    }

    public static final class CastOptions implements Api.ApiOptions.HasOptions {
        final CastDevice zX;
        final Listener zY;
        /* access modifiers changed from: private */
        public final int zZ;

        public static final class Builder {
            CastDevice Aa;
            Listener Ab;
            /* access modifiers changed from: private */
            public int Ac;

            private Builder(CastDevice castDevice, Listener castListener) {
                hn.b(castDevice, (Object) "CastDevice parameter cannot be null");
                hn.b(castListener, (Object) "CastListener parameter cannot be null");
                this.Aa = castDevice;
                this.Ab = castListener;
                this.Ac = 0;
            }

            public CastOptions build() {
                return new CastOptions(this);
            }

            public Builder setVerboseLoggingEnabled(boolean enabled) {
                if (enabled) {
                    this.Ac |= 1;
                } else {
                    this.Ac &= -2;
                }
                return this;
            }
        }

        private CastOptions(Builder builder) {
            this.zX = builder.Aa;
            this.zY = builder.Ab;
            this.zZ = builder.Ac;
        }

        public static Builder builder(CastDevice castDevice, Listener castListener) {
            return new Builder(castDevice, castListener);
        }
    }

    public static class Listener {
        public void O(int i) {
        }

        public void onApplicationDisconnected(int statusCode) {
        }

        public void onApplicationStatusChanged() {
        }

        public void onVolumeChanged() {
        }
    }

    public interface MessageReceivedCallback {
        void onMessageReceived(CastDevice castDevice, String str, String str2);
    }

    protected static abstract class a<R extends Result> extends a.b<R, gi> {
        public a() {
            super(Cast.yE);
        }

        public void N(int i) {
            a(c(new Status(i)));
        }

        public void c(int i, String str) {
            a(c(new Status(i, str, (PendingIntent) null)));
        }
    }

    private static abstract class b extends a<Status> {
        private b() {
        }

        /* renamed from: d */
        public Status c(Status status) {
            return status;
        }
    }

    private static abstract class c extends a<ApplicationConnectionResult> {
        private c() {
        }

        /* renamed from: j */
        public ApplicationConnectionResult c(final Status status) {
            return new ApplicationConnectionResult() {
                public ApplicationMetadata getApplicationMetadata() {
                    return null;
                }

                public String getApplicationStatus() {
                    return null;
                }

                public String getSessionId() {
                    return null;
                }

                public Status getStatus() {
                    return status;
                }

                public boolean getWasLaunched() {
                    return false;
                }
            };
        }
    }

    private Cast() {
    }
}
