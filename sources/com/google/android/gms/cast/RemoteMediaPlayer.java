package com.google.android.gms.cast;

import com.google.android.gms.cast.Cast;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.gi;
import com.google.android.gms.internal.gp;
import com.google.android.gms.internal.gq;
import com.google.android.gms.internal.gr;
import java.io.IOException;
import org.json.JSONObject;

public class RemoteMediaPlayer implements Cast.MessageReceivedCallback {
    public static final int RESUME_STATE_PAUSE = 2;
    public static final int RESUME_STATE_PLAY = 1;
    public static final int RESUME_STATE_UNCHANGED = 0;
    public static final int STATUS_CANCELED = 2;
    public static final int STATUS_FAILED = 1;
    public static final int STATUS_REPLACED = 4;
    public static final int STATUS_SUCCEEDED = 0;
    public static final int STATUS_TIMED_OUT = 3;
    /* access modifiers changed from: private */
    public final gp AT = new gp() {
        /* access modifiers changed from: protected */
        public void onMetadataUpdated() {
            RemoteMediaPlayer.this.onMetadataUpdated();
        }

        /* access modifiers changed from: protected */
        public void onStatusUpdated() {
            RemoteMediaPlayer.this.onStatusUpdated();
        }
    };
    /* access modifiers changed from: private */
    public final a AU = new a();
    private OnMetadataUpdatedListener AV;
    private OnStatusUpdatedListener AW;
    /* access modifiers changed from: private */
    public final Object lq = new Object();

    public interface MediaChannelResult extends Result {
        JSONObject getCustomData();
    }

    public interface OnMetadataUpdatedListener {
        void onMetadataUpdated();
    }

    public interface OnStatusUpdatedListener {
        void onStatusUpdated();
    }

    private class a implements gq {
        private GoogleApiClient Bj;
        private long Bk = 0;

        /* renamed from: com.google.android.gms.cast.RemoteMediaPlayer$a$a  reason: collision with other inner class name */
        private final class C0010a implements ResultCallback<Status> {
            private final long Bl;

            C0010a(long j) {
                this.Bl = j;
            }

            /* renamed from: k */
            public void onResult(Status status) {
                if (!status.isSuccess()) {
                    RemoteMediaPlayer.this.AT.a(this.Bl, status.getStatusCode());
                }
            }
        }

        public a() {
        }

        public void a(String str, String str2, long j, String str3) throws IOException {
            if (this.Bj == null) {
                throw new IOException("No GoogleApiClient available");
            }
            Cast.CastApi.sendMessage(this.Bj, str, str2).setResultCallback(new C0010a(j));
        }

        public void b(GoogleApiClient googleApiClient) {
            this.Bj = googleApiClient;
        }

        public long dW() {
            long j = this.Bk + 1;
            this.Bk = j;
            return j;
        }
    }

    private static abstract class b extends Cast.a<MediaChannelResult> {
        gr Bn = new gr() {
            public void a(long j, int i, JSONObject jSONObject) {
                b.this.a(new c(new Status(i), jSONObject));
            }

            public void n(long j) {
                b.this.a(b.this.c(new Status(4)));
            }
        };

        b() {
        }

        /* renamed from: l */
        public MediaChannelResult c(final Status status) {
            return new MediaChannelResult() {
                public JSONObject getCustomData() {
                    return null;
                }

                public Status getStatus() {
                    return status;
                }
            };
        }
    }

    private static final class c implements MediaChannelResult {
        private final JSONObject Ax;
        private final Status yw;

        c(Status status, JSONObject jSONObject) {
            this.yw = status;
            this.Ax = jSONObject;
        }

        public JSONObject getCustomData() {
            return this.Ax;
        }

        public Status getStatus() {
            return this.yw;
        }
    }

    public RemoteMediaPlayer() {
        this.AT.a(this.AU);
    }

    /* access modifiers changed from: private */
    public void onMetadataUpdated() {
        if (this.AV != null) {
            this.AV.onMetadataUpdated();
        }
    }

    /* access modifiers changed from: private */
    public void onStatusUpdated() {
        if (this.AW != null) {
            this.AW.onStatusUpdated();
        }
    }

    public long getApproximateStreamPosition() {
        long approximateStreamPosition;
        synchronized (this.lq) {
            approximateStreamPosition = this.AT.getApproximateStreamPosition();
        }
        return approximateStreamPosition;
    }

    public MediaInfo getMediaInfo() {
        MediaInfo mediaInfo;
        synchronized (this.lq) {
            mediaInfo = this.AT.getMediaInfo();
        }
        return mediaInfo;
    }

    public MediaStatus getMediaStatus() {
        MediaStatus mediaStatus;
        synchronized (this.lq) {
            mediaStatus = this.AT.getMediaStatus();
        }
        return mediaStatus;
    }

    public String getNamespace() {
        return this.AT.getNamespace();
    }

    public long getStreamDuration() {
        long streamDuration;
        synchronized (this.lq) {
            streamDuration = this.AT.getStreamDuration();
        }
        return streamDuration;
    }

    public PendingResult<MediaChannelResult> load(GoogleApiClient apiClient, MediaInfo mediaInfo) {
        return load(apiClient, mediaInfo, true, 0, (JSONObject) null);
    }

    public PendingResult<MediaChannelResult> load(GoogleApiClient apiClient, MediaInfo mediaInfo, boolean autoplay) {
        return load(apiClient, mediaInfo, autoplay, 0, (JSONObject) null);
    }

    public PendingResult<MediaChannelResult> load(GoogleApiClient apiClient, MediaInfo mediaInfo, boolean autoplay, long playPosition) {
        return load(apiClient, mediaInfo, autoplay, playPosition, (JSONObject) null);
    }

    public PendingResult<MediaChannelResult> load(GoogleApiClient apiClient, MediaInfo mediaInfo, boolean autoplay, long playPosition, JSONObject customData) {
        final GoogleApiClient googleApiClient = apiClient;
        final MediaInfo mediaInfo2 = mediaInfo;
        final boolean z = autoplay;
        final long j = playPosition;
        final JSONObject jSONObject = customData;
        return apiClient.b(new b() {
            /* access modifiers changed from: protected */
            public void a(gi giVar) {
                synchronized (RemoteMediaPlayer.this.lq) {
                    RemoteMediaPlayer.this.AU.b(googleApiClient);
                    try {
                        RemoteMediaPlayer.this.AT.a(this.Bn, mediaInfo2, z, j, jSONObject);
                        RemoteMediaPlayer.this.AU.b((GoogleApiClient) null);
                    } catch (IOException e) {
                        a(c(new Status(1)));
                        RemoteMediaPlayer.this.AU.b((GoogleApiClient) null);
                    } catch (Throwable th) {
                        RemoteMediaPlayer.this.AU.b((GoogleApiClient) null);
                        throw th;
                    }
                }
            }
        });
    }

    public void onMessageReceived(CastDevice castDevice, String namespace, String message) {
        this.AT.ai(message);
    }

    public PendingResult<MediaChannelResult> pause(GoogleApiClient apiClient) {
        return pause(apiClient, (JSONObject) null);
    }

    public PendingResult<MediaChannelResult> pause(final GoogleApiClient apiClient, final JSONObject customData) {
        return apiClient.b(new b() {
            /* access modifiers changed from: protected */
            public void a(gi giVar) {
                synchronized (RemoteMediaPlayer.this.lq) {
                    RemoteMediaPlayer.this.AU.b(apiClient);
                    try {
                        RemoteMediaPlayer.this.AT.a(this.Bn, customData);
                        RemoteMediaPlayer.this.AU.b((GoogleApiClient) null);
                    } catch (IOException e) {
                        a(c(new Status(1)));
                        RemoteMediaPlayer.this.AU.b((GoogleApiClient) null);
                    } catch (Throwable th) {
                        RemoteMediaPlayer.this.AU.b((GoogleApiClient) null);
                        throw th;
                    }
                }
            }
        });
    }

    public PendingResult<MediaChannelResult> play(GoogleApiClient apiClient) {
        return play(apiClient, (JSONObject) null);
    }

    public PendingResult<MediaChannelResult> play(final GoogleApiClient apiClient, final JSONObject customData) {
        return apiClient.b(new b() {
            /* access modifiers changed from: protected */
            public void a(gi giVar) {
                synchronized (RemoteMediaPlayer.this.lq) {
                    RemoteMediaPlayer.this.AU.b(apiClient);
                    try {
                        RemoteMediaPlayer.this.AT.c(this.Bn, customData);
                        RemoteMediaPlayer.this.AU.b((GoogleApiClient) null);
                    } catch (IOException e) {
                        a(c(new Status(1)));
                        RemoteMediaPlayer.this.AU.b((GoogleApiClient) null);
                    } catch (Throwable th) {
                        RemoteMediaPlayer.this.AU.b((GoogleApiClient) null);
                        throw th;
                    }
                }
            }
        });
    }

    public PendingResult<MediaChannelResult> requestStatus(final GoogleApiClient apiClient) {
        return apiClient.b(new b() {
            /* access modifiers changed from: protected */
            public void a(gi giVar) {
                synchronized (RemoteMediaPlayer.this.lq) {
                    RemoteMediaPlayer.this.AU.b(apiClient);
                    try {
                        RemoteMediaPlayer.this.AT.a(this.Bn);
                        RemoteMediaPlayer.this.AU.b((GoogleApiClient) null);
                    } catch (IOException e) {
                        a(c(new Status(1)));
                        RemoteMediaPlayer.this.AU.b((GoogleApiClient) null);
                    } catch (Throwable th) {
                        RemoteMediaPlayer.this.AU.b((GoogleApiClient) null);
                        throw th;
                    }
                }
            }
        });
    }

    public PendingResult<MediaChannelResult> seek(GoogleApiClient apiClient, long position) {
        return seek(apiClient, position, 0, (JSONObject) null);
    }

    public PendingResult<MediaChannelResult> seek(GoogleApiClient apiClient, long position, int resumeState) {
        return seek(apiClient, position, resumeState, (JSONObject) null);
    }

    public PendingResult<MediaChannelResult> seek(GoogleApiClient apiClient, long position, int resumeState, JSONObject customData) {
        final GoogleApiClient googleApiClient = apiClient;
        final long j = position;
        final int i = resumeState;
        final JSONObject jSONObject = customData;
        return apiClient.b(new b() {
            /* access modifiers changed from: protected */
            public void a(gi giVar) {
                synchronized (RemoteMediaPlayer.this.lq) {
                    RemoteMediaPlayer.this.AU.b(googleApiClient);
                    try {
                        RemoteMediaPlayer.this.AT.a(this.Bn, j, i, jSONObject);
                        RemoteMediaPlayer.this.AU.b((GoogleApiClient) null);
                    } catch (IOException e) {
                        a(c(new Status(1)));
                        RemoteMediaPlayer.this.AU.b((GoogleApiClient) null);
                    } catch (Throwable th) {
                        RemoteMediaPlayer.this.AU.b((GoogleApiClient) null);
                        throw th;
                    }
                }
            }
        });
    }

    public PendingResult<MediaChannelResult> setActiveMediaTracks(final GoogleApiClient apiClient, final long[] trackIds) {
        return apiClient.b(new b() {
            /* access modifiers changed from: protected */
            public void a(gi giVar) {
                synchronized (RemoteMediaPlayer.this.lq) {
                    RemoteMediaPlayer.this.AU.b(apiClient);
                    try {
                        RemoteMediaPlayer.this.AT.a(this.Bn, trackIds);
                        RemoteMediaPlayer.this.AU.b((GoogleApiClient) null);
                    } catch (IOException e) {
                        a(c(new Status(1)));
                        RemoteMediaPlayer.this.AU.b((GoogleApiClient) null);
                    } catch (Throwable th) {
                        RemoteMediaPlayer.this.AU.b((GoogleApiClient) null);
                        throw th;
                    }
                }
            }
        });
    }

    public void setOnMetadataUpdatedListener(OnMetadataUpdatedListener listener) {
        this.AV = listener;
    }

    public void setOnStatusUpdatedListener(OnStatusUpdatedListener listener) {
        this.AW = listener;
    }

    public PendingResult<MediaChannelResult> setStreamMute(GoogleApiClient apiClient, boolean muteState) {
        return setStreamMute(apiClient, muteState, (JSONObject) null);
    }

    public PendingResult<MediaChannelResult> setStreamMute(final GoogleApiClient apiClient, final boolean muteState, final JSONObject customData) {
        return apiClient.b(new b() {
            /* access modifiers changed from: protected */
            public void a(gi giVar) {
                synchronized (RemoteMediaPlayer.this.lq) {
                    RemoteMediaPlayer.this.AU.b(apiClient);
                    try {
                        RemoteMediaPlayer.this.AT.a(this.Bn, muteState, customData);
                        RemoteMediaPlayer.this.AU.b((GoogleApiClient) null);
                    } catch (IllegalStateException e) {
                        a(c(new Status(1)));
                        RemoteMediaPlayer.this.AU.b((GoogleApiClient) null);
                    } catch (IOException e2) {
                        a(c(new Status(1)));
                        RemoteMediaPlayer.this.AU.b((GoogleApiClient) null);
                    } catch (Throwable th) {
                        RemoteMediaPlayer.this.AU.b((GoogleApiClient) null);
                        throw th;
                    }
                }
            }
        });
    }

    public PendingResult<MediaChannelResult> setStreamVolume(GoogleApiClient apiClient, double volume) throws IllegalArgumentException {
        return setStreamVolume(apiClient, volume, (JSONObject) null);
    }

    public PendingResult<MediaChannelResult> setStreamVolume(GoogleApiClient apiClient, double volume, JSONObject customData) throws IllegalArgumentException {
        if (Double.isInfinite(volume) || Double.isNaN(volume)) {
            throw new IllegalArgumentException("Volume cannot be " + volume);
        }
        final GoogleApiClient googleApiClient = apiClient;
        final double d = volume;
        final JSONObject jSONObject = customData;
        return apiClient.b(new b() {
            /* access modifiers changed from: protected */
            public void a(gi giVar) {
                synchronized (RemoteMediaPlayer.this.lq) {
                    RemoteMediaPlayer.this.AU.b(googleApiClient);
                    try {
                        RemoteMediaPlayer.this.AT.a(this.Bn, d, jSONObject);
                        RemoteMediaPlayer.this.AU.b((GoogleApiClient) null);
                    } catch (IllegalStateException e) {
                        a(c(new Status(1)));
                        RemoteMediaPlayer.this.AU.b((GoogleApiClient) null);
                    } catch (IllegalArgumentException e2) {
                        a(c(new Status(1)));
                        RemoteMediaPlayer.this.AU.b((GoogleApiClient) null);
                    } catch (IOException e3) {
                        a(c(new Status(1)));
                        RemoteMediaPlayer.this.AU.b((GoogleApiClient) null);
                    } catch (Throwable th) {
                        RemoteMediaPlayer.this.AU.b((GoogleApiClient) null);
                        throw th;
                    }
                }
                return;
            }
        });
    }

    public PendingResult<MediaChannelResult> setTextTrackStyle(final GoogleApiClient apiClient, final TextTrackStyle trackStyle) {
        return apiClient.b(new b() {
            /* access modifiers changed from: protected */
            public void a(gi giVar) {
                synchronized (RemoteMediaPlayer.this.lq) {
                    RemoteMediaPlayer.this.AU.b(apiClient);
                    try {
                        RemoteMediaPlayer.this.AT.a(this.Bn, trackStyle);
                        RemoteMediaPlayer.this.AU.b((GoogleApiClient) null);
                    } catch (IOException e) {
                        a(c(new Status(1)));
                        RemoteMediaPlayer.this.AU.b((GoogleApiClient) null);
                    } catch (Throwable th) {
                        RemoteMediaPlayer.this.AU.b((GoogleApiClient) null);
                        throw th;
                    }
                }
            }
        });
    }

    public PendingResult<MediaChannelResult> stop(GoogleApiClient apiClient) {
        return stop(apiClient, (JSONObject) null);
    }

    public PendingResult<MediaChannelResult> stop(final GoogleApiClient apiClient, final JSONObject customData) {
        return apiClient.b(new b() {
            /* access modifiers changed from: protected */
            public void a(gi giVar) {
                synchronized (RemoteMediaPlayer.this.lq) {
                    RemoteMediaPlayer.this.AU.b(apiClient);
                    try {
                        RemoteMediaPlayer.this.AT.b(this.Bn, customData);
                        RemoteMediaPlayer.this.AU.b((GoogleApiClient) null);
                    } catch (IOException e) {
                        a(c(new Status(1)));
                        RemoteMediaPlayer.this.AU.b((GoogleApiClient) null);
                    } catch (Throwable th) {
                        RemoteMediaPlayer.this.AU.b((GoogleApiClient) null);
                        throw th;
                    }
                }
            }
        });
    }
}
