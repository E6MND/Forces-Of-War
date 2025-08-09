package com.google.android.gms.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaStatus;
import com.google.android.gms.cast.TextTrackStyle;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xwalk.core.internal.extension.api.messaging.MessagingSmsConsts;

public class gp extends gh {
    private static final long Cm = TimeUnit.HOURS.toMillis(24);
    private static final long Cn = TimeUnit.HOURS.toMillis(24);
    private static final long Co = TimeUnit.HOURS.toMillis(24);
    private static final long Cp = TimeUnit.SECONDS.toMillis(1);
    private static final String NAMESPACE = gj.al("com.google.cast.media");
    private final gs CA;
    private final gs CB;
    /* access modifiers changed from: private */
    public final List<gs> CC;
    private final Runnable CD;
    /* access modifiers changed from: private */
    public boolean CE;
    private long Cq;
    private MediaStatus Cr;
    private final gs Cs;
    private final gs Ct;
    private final gs Cu;
    private final gs Cv;
    private final gs Cw;
    private final gs Cx;
    private final gs Cy;
    private final gs Cz;
    private final Handler mHandler;

    private class a implements Runnable {
        private a() {
        }

        public void run() {
            boolean z = false;
            boolean unused = gp.this.CE = false;
            long elapsedRealtime = SystemClock.elapsedRealtime();
            for (gs d : gp.this.CC) {
                d.d(elapsedRealtime, 3);
            }
            synchronized (gs.CK) {
                for (gs eq : gp.this.CC) {
                    z = eq.eq() ? true : z;
                }
            }
            gp.this.z(z);
        }
    }

    public gp() {
        this((String) null);
    }

    public gp(String str) {
        super(NAMESPACE, "MediaControlChannel", str);
        this.mHandler = new Handler(Looper.getMainLooper());
        this.CD = new a();
        this.CC = new ArrayList();
        this.Cs = new gs(Cn);
        this.CC.add(this.Cs);
        this.Ct = new gs(Cm);
        this.CC.add(this.Ct);
        this.Cu = new gs(Cm);
        this.CC.add(this.Cu);
        this.Cv = new gs(Cm);
        this.CC.add(this.Cv);
        this.Cw = new gs(Co);
        this.CC.add(this.Cw);
        this.Cx = new gs(Cm);
        this.CC.add(this.Cx);
        this.Cy = new gs(Cm);
        this.CC.add(this.Cy);
        this.Cz = new gs(Cm);
        this.CC.add(this.Cz);
        this.CA = new gs(Cm);
        this.CC.add(this.CA);
        this.CB = new gs(Cm);
        this.CC.add(this.CB);
        eo();
    }

    private void a(long j, JSONObject jSONObject) throws JSONException {
        int i;
        boolean z = true;
        boolean p = this.Cs.p(j);
        boolean z2 = this.Cw.eq() && !this.Cw.p(j);
        if ((!this.Cx.eq() || this.Cx.p(j)) && (!this.Cy.eq() || this.Cy.p(j))) {
            z = false;
        }
        int i2 = z2 ? 2 : 0;
        if (z) {
            i2 |= 1;
        }
        if (p || this.Cr == null) {
            this.Cr = new MediaStatus(jSONObject);
            this.Cq = SystemClock.elapsedRealtime();
            i = 7;
        } else {
            i = this.Cr.a(jSONObject, i2);
        }
        if ((i & 1) != 0) {
            this.Cq = SystemClock.elapsedRealtime();
            onStatusUpdated();
        }
        if ((i & 2) != 0) {
            this.Cq = SystemClock.elapsedRealtime();
            onStatusUpdated();
        }
        if ((i & 4) != 0) {
            onMetadataUpdated();
        }
        for (gs c : this.CC) {
            c.c(j, 0);
        }
    }

    private void eo() {
        z(false);
        this.Cq = 0;
        this.Cr = null;
        this.Cs.clear();
        this.Cw.clear();
        this.Cx.clear();
    }

    /* access modifiers changed from: private */
    public void z(boolean z) {
        if (this.CE != z) {
            this.CE = z;
            if (z) {
                this.mHandler.postDelayed(this.CD, Cp);
            } else {
                this.mHandler.removeCallbacks(this.CD);
            }
        }
    }

    public long a(gr grVar) throws IOException {
        JSONObject jSONObject = new JSONObject();
        long dY = dY();
        this.Cz.a(dY, grVar);
        z(true);
        try {
            jSONObject.put("requestId", dY);
            jSONObject.put(MessagingSmsConsts.TYPE, "GET_STATUS");
            if (this.Cr != null) {
                jSONObject.put("mediaSessionId", this.Cr.dV());
            }
        } catch (JSONException e) {
        }
        a(jSONObject.toString(), dY, (String) null);
        return dY;
    }

    public long a(gr grVar, double d, JSONObject jSONObject) throws IOException, IllegalStateException, IllegalArgumentException {
        if (Double.isInfinite(d) || Double.isNaN(d)) {
            throw new IllegalArgumentException("Volume cannot be " + d);
        }
        JSONObject jSONObject2 = new JSONObject();
        long dY = dY();
        this.Cx.a(dY, grVar);
        z(true);
        try {
            jSONObject2.put("requestId", dY);
            jSONObject2.put(MessagingSmsConsts.TYPE, "SET_VOLUME");
            jSONObject2.put("mediaSessionId", dV());
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("level", d);
            jSONObject2.put("volume", jSONObject3);
            if (jSONObject != null) {
                jSONObject2.put("customData", jSONObject);
            }
        } catch (JSONException e) {
        }
        a(jSONObject2.toString(), dY, (String) null);
        return dY;
    }

    public long a(gr grVar, long j, int i, JSONObject jSONObject) throws IOException, IllegalStateException {
        JSONObject jSONObject2 = new JSONObject();
        long dY = dY();
        this.Cw.a(dY, grVar);
        z(true);
        try {
            jSONObject2.put("requestId", dY);
            jSONObject2.put(MessagingSmsConsts.TYPE, "SEEK");
            jSONObject2.put("mediaSessionId", dV());
            jSONObject2.put("currentTime", gj.o(j));
            if (i == 1) {
                jSONObject2.put("resumeState", "PLAYBACK_START");
            } else if (i == 2) {
                jSONObject2.put("resumeState", "PLAYBACK_PAUSE");
            }
            if (jSONObject != null) {
                jSONObject2.put("customData", jSONObject);
            }
        } catch (JSONException e) {
        }
        a(jSONObject2.toString(), dY, (String) null);
        return dY;
    }

    public long a(gr grVar, MediaInfo mediaInfo, boolean z, long j, JSONObject jSONObject) throws IOException {
        JSONObject jSONObject2 = new JSONObject();
        long dY = dY();
        this.Cs.a(dY, grVar);
        z(true);
        try {
            jSONObject2.put("requestId", dY);
            jSONObject2.put(MessagingSmsConsts.TYPE, "LOAD");
            jSONObject2.put("media", mediaInfo.dU());
            jSONObject2.put("autoplay", z);
            jSONObject2.put("currentTime", gj.o(j));
            if (jSONObject != null) {
                jSONObject2.put("customData", jSONObject);
            }
        } catch (JSONException e) {
        }
        a(jSONObject2.toString(), dY, (String) null);
        return dY;
    }

    public long a(gr grVar, TextTrackStyle textTrackStyle) throws IOException {
        JSONObject jSONObject = new JSONObject();
        long dY = dY();
        this.CB.a(dY, grVar);
        z(true);
        try {
            jSONObject.put("requestId", dY);
            jSONObject.put(MessagingSmsConsts.TYPE, "EDIT_TRACKS_INFO");
            if (textTrackStyle != null) {
                jSONObject.put("textTrackStyle", textTrackStyle.dU());
            }
            jSONObject.put("mediaSessionId", dV());
        } catch (JSONException e) {
        }
        a(jSONObject.toString(), dY, (String) null);
        return dY;
    }

    public long a(gr grVar, JSONObject jSONObject) throws IOException {
        JSONObject jSONObject2 = new JSONObject();
        long dY = dY();
        this.Ct.a(dY, grVar);
        z(true);
        try {
            jSONObject2.put("requestId", dY);
            jSONObject2.put(MessagingSmsConsts.TYPE, "PAUSE");
            jSONObject2.put("mediaSessionId", dV());
            if (jSONObject != null) {
                jSONObject2.put("customData", jSONObject);
            }
        } catch (JSONException e) {
        }
        a(jSONObject2.toString(), dY, (String) null);
        return dY;
    }

    public long a(gr grVar, boolean z, JSONObject jSONObject) throws IOException, IllegalStateException {
        JSONObject jSONObject2 = new JSONObject();
        long dY = dY();
        this.Cy.a(dY, grVar);
        z(true);
        try {
            jSONObject2.put("requestId", dY);
            jSONObject2.put(MessagingSmsConsts.TYPE, "SET_VOLUME");
            jSONObject2.put("mediaSessionId", dV());
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("muted", z);
            jSONObject2.put("volume", jSONObject3);
            if (jSONObject != null) {
                jSONObject2.put("customData", jSONObject);
            }
        } catch (JSONException e) {
        }
        a(jSONObject2.toString(), dY, (String) null);
        return dY;
    }

    public long a(gr grVar, long[] jArr) throws IOException {
        JSONObject jSONObject = new JSONObject();
        long dY = dY();
        this.CA.a(dY, grVar);
        z(true);
        try {
            jSONObject.put("requestId", dY);
            jSONObject.put(MessagingSmsConsts.TYPE, "EDIT_TRACKS_INFO");
            jSONObject.put("mediaSessionId", dV());
            JSONArray jSONArray = new JSONArray();
            for (int i = 0; i < jArr.length; i++) {
                jSONArray.put(i, jArr[i]);
            }
            jSONObject.put("activeTrackIds", jSONArray);
        } catch (JSONException e) {
        }
        a(jSONObject.toString(), dY, (String) null);
        return dY;
    }

    public void a(long j, int i) {
        for (gs c : this.CC) {
            c.c(j, i);
        }
    }

    public final void ai(String str) {
        this.BA.b("message received: %s", str);
        try {
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString(MessagingSmsConsts.TYPE);
            long optLong = jSONObject.optLong("requestId", -1);
            if (string.equals("MEDIA_STATUS")) {
                JSONArray jSONArray = jSONObject.getJSONArray(MessagingSmsConsts.STATUS);
                if (jSONArray.length() > 0) {
                    a(optLong, jSONArray.getJSONObject(0));
                    return;
                }
                this.Cr = null;
                onStatusUpdated();
                onMetadataUpdated();
                this.Cz.c(optLong, 0);
            } else if (string.equals("INVALID_PLAYER_STATE")) {
                this.BA.d("received unexpected error: Invalid Player State.", new Object[0]);
                JSONObject optJSONObject = jSONObject.optJSONObject("customData");
                for (gs b : this.CC) {
                    b.b(optLong, 1, optJSONObject);
                }
            } else if (string.equals("LOAD_FAILED")) {
                this.Cs.b(optLong, 1, jSONObject.optJSONObject("customData"));
            } else if (string.equals("LOAD_CANCELLED")) {
                this.Cs.b(optLong, 2, jSONObject.optJSONObject("customData"));
            } else if (string.equals("INVALID_REQUEST")) {
                this.BA.d("received unexpected error: Invalid Request.", new Object[0]);
                JSONObject optJSONObject2 = jSONObject.optJSONObject("customData");
                for (gs b2 : this.CC) {
                    b2.b(optLong, 1, optJSONObject2);
                }
            }
        } catch (JSONException e) {
            this.BA.d("Message is malformed (%s); ignoring: %s", e.getMessage(), str);
        }
    }

    public long b(gr grVar, JSONObject jSONObject) throws IOException {
        JSONObject jSONObject2 = new JSONObject();
        long dY = dY();
        this.Cv.a(dY, grVar);
        z(true);
        try {
            jSONObject2.put("requestId", dY);
            jSONObject2.put(MessagingSmsConsts.TYPE, "STOP");
            jSONObject2.put("mediaSessionId", dV());
            if (jSONObject != null) {
                jSONObject2.put("customData", jSONObject);
            }
        } catch (JSONException e) {
        }
        a(jSONObject2.toString(), dY, (String) null);
        return dY;
    }

    public long c(gr grVar, JSONObject jSONObject) throws IOException, IllegalStateException {
        JSONObject jSONObject2 = new JSONObject();
        long dY = dY();
        this.Cu.a(dY, grVar);
        z(true);
        try {
            jSONObject2.put("requestId", dY);
            jSONObject2.put(MessagingSmsConsts.TYPE, "PLAY");
            jSONObject2.put("mediaSessionId", dV());
            if (jSONObject != null) {
                jSONObject2.put("customData", jSONObject);
            }
        } catch (JSONException e) {
        }
        a(jSONObject2.toString(), dY, (String) null);
        return dY;
    }

    public long dV() throws IllegalStateException {
        if (this.Cr != null) {
            return this.Cr.dV();
        }
        throw new IllegalStateException("No current media session");
    }

    public void dZ() {
        eo();
    }

    public long getApproximateStreamPosition() {
        MediaInfo mediaInfo = getMediaInfo();
        if (mediaInfo == null || this.Cq == 0) {
            return 0;
        }
        double playbackRate = this.Cr.getPlaybackRate();
        long streamPosition = this.Cr.getStreamPosition();
        int playerState = this.Cr.getPlayerState();
        if (playbackRate == 0.0d || playerState != 2) {
            return streamPosition;
        }
        long elapsedRealtime = SystemClock.elapsedRealtime() - this.Cq;
        long j = elapsedRealtime < 0 ? 0 : elapsedRealtime;
        if (j == 0) {
            return streamPosition;
        }
        long streamDuration = mediaInfo.getStreamDuration();
        long j2 = streamPosition + ((long) (((double) j) * playbackRate));
        if (j2 <= streamDuration) {
            streamDuration = j2 < 0 ? 0 : j2;
        }
        return streamDuration;
    }

    public MediaInfo getMediaInfo() {
        if (this.Cr == null) {
            return null;
        }
        return this.Cr.getMediaInfo();
    }

    public MediaStatus getMediaStatus() {
        return this.Cr;
    }

    public long getStreamDuration() {
        MediaInfo mediaInfo = getMediaInfo();
        if (mediaInfo != null) {
            return mediaInfo.getStreamDuration();
        }
        return 0;
    }

    /* access modifiers changed from: protected */
    public void onMetadataUpdated() {
    }

    /* access modifiers changed from: protected */
    public void onStatusUpdated() {
    }
}
