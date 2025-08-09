package com.google.android.gms.cast;

import android.text.TextUtils;
import com.google.android.gms.internal.gj;
import com.google.android.gms.internal.hl;
import com.google.android.gms.internal.io;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class MediaInfo {
    public static final int STREAM_TYPE_BUFFERED = 1;
    public static final int STREAM_TYPE_INVALID = -1;
    public static final int STREAM_TYPE_LIVE = 2;
    public static final int STREAM_TYPE_NONE = 0;
    private final String Aq;
    private int Ar;
    private String As;
    private MediaMetadata At;
    private long Au;
    private List<MediaTrack> Av;
    private TextTrackStyle Aw;
    private JSONObject Ax;

    public static class Builder {
        private final MediaInfo Ay;

        public Builder(String contentId) throws IllegalArgumentException {
            if (TextUtils.isEmpty(contentId)) {
                throw new IllegalArgumentException("Content ID cannot be empty");
            }
            this.Ay = new MediaInfo(contentId);
        }

        public MediaInfo build() throws IllegalArgumentException {
            this.Ay.dT();
            return this.Ay;
        }

        public Builder setContentType(String contentType) throws IllegalArgumentException {
            this.Ay.setContentType(contentType);
            return this;
        }

        public Builder setCustomData(JSONObject customData) {
            this.Ay.setCustomData(customData);
            return this;
        }

        public Builder setMediaTracks(List<MediaTrack> mediaTracks) {
            this.Ay.b(mediaTracks);
            return this;
        }

        public Builder setMetadata(MediaMetadata metadata) {
            this.Ay.a(metadata);
            return this;
        }

        public Builder setStreamDuration(long duration) throws IllegalArgumentException {
            this.Ay.m(duration);
            return this;
        }

        public Builder setStreamType(int streamType) throws IllegalArgumentException {
            this.Ay.setStreamType(streamType);
            return this;
        }

        public Builder setTextTrackStyle(TextTrackStyle textTrackStyle) {
            this.Ay.setTextTrackStyle(textTrackStyle);
            return this;
        }
    }

    MediaInfo(String contentId) throws IllegalArgumentException {
        if (TextUtils.isEmpty(contentId)) {
            throw new IllegalArgumentException("content ID cannot be null or empty");
        }
        this.Aq = contentId;
        this.Ar = -1;
    }

    MediaInfo(JSONObject json) throws JSONException {
        this.Aq = json.getString("contentId");
        String string = json.getString("streamType");
        if ("NONE".equals(string)) {
            this.Ar = 0;
        } else if ("BUFFERED".equals(string)) {
            this.Ar = 1;
        } else if ("LIVE".equals(string)) {
            this.Ar = 2;
        } else {
            this.Ar = -1;
        }
        this.As = json.getString("contentType");
        if (json.has("metadata")) {
            JSONObject jSONObject = json.getJSONObject("metadata");
            this.At = new MediaMetadata(jSONObject.getInt("metadataType"));
            this.At.b(jSONObject);
        }
        this.Au = gj.b(json.optDouble("duration", 0.0d));
        if (json.has("tracks")) {
            this.Av = new ArrayList();
            JSONArray jSONArray = json.getJSONArray("tracks");
            for (int i = 0; i < jSONArray.length(); i++) {
                this.Av.add(new MediaTrack(jSONArray.getJSONObject(i)));
            }
        } else {
            this.Av = null;
        }
        if (json.has("textTrackStyle")) {
            JSONObject jSONObject2 = json.getJSONObject("textTrackStyle");
            TextTrackStyle textTrackStyle = new TextTrackStyle();
            textTrackStyle.b(jSONObject2);
            this.Aw = textTrackStyle;
        } else {
            this.Aw = null;
        }
        this.Ax = json.optJSONObject("customData");
    }

    /* access modifiers changed from: package-private */
    public void a(MediaMetadata mediaMetadata) {
        this.At = mediaMetadata;
    }

    /* access modifiers changed from: package-private */
    public void b(List<MediaTrack> list) {
        this.Av = list;
    }

    /* access modifiers changed from: package-private */
    public void dT() throws IllegalArgumentException {
        if (TextUtils.isEmpty(this.Aq)) {
            throw new IllegalArgumentException("content ID cannot be null or empty");
        } else if (TextUtils.isEmpty(this.As)) {
            throw new IllegalArgumentException("content type cannot be null or empty");
        } else if (this.Ar == -1) {
            throw new IllegalArgumentException("a valid stream type must be specified");
        }
    }

    public JSONObject dU() {
        String str;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("contentId", this.Aq);
            switch (this.Ar) {
                case 1:
                    str = "BUFFERED";
                    break;
                case 2:
                    str = "LIVE";
                    break;
                default:
                    str = "NONE";
                    break;
            }
            jSONObject.put("streamType", str);
            if (this.As != null) {
                jSONObject.put("contentType", this.As);
            }
            if (this.At != null) {
                jSONObject.put("metadata", this.At.dU());
            }
            jSONObject.put("duration", gj.o(this.Au));
            if (this.Av != null) {
                JSONArray jSONArray = new JSONArray();
                for (MediaTrack dU : this.Av) {
                    jSONArray.put(dU.dU());
                }
                jSONObject.put("tracks", jSONArray);
            }
            if (this.Aw != null) {
                jSONObject.put("textTrackStyle", this.Aw.dU());
            }
            if (this.Ax != null) {
                jSONObject.put("customData", this.Ax);
            }
        } catch (JSONException e) {
        }
        return jSONObject;
    }

    public boolean equals(Object other) {
        boolean z = true;
        if (this == other) {
            return true;
        }
        if (!(other instanceof MediaInfo)) {
            return false;
        }
        MediaInfo mediaInfo = (MediaInfo) other;
        if ((this.Ax == null) != (mediaInfo.Ax == null)) {
            return false;
        }
        if (this.Ax != null && mediaInfo.Ax != null && !io.d(this.Ax, mediaInfo.Ax)) {
            return false;
        }
        if (!gj.a(this.Aq, mediaInfo.Aq) || this.Ar != mediaInfo.Ar || !gj.a(this.As, mediaInfo.As) || !gj.a(this.At, mediaInfo.At) || this.Au != mediaInfo.Au) {
            z = false;
        }
        return z;
    }

    public String getContentId() {
        return this.Aq;
    }

    public String getContentType() {
        return this.As;
    }

    public JSONObject getCustomData() {
        return this.Ax;
    }

    public List<MediaTrack> getMediaTracks() {
        return this.Av;
    }

    public MediaMetadata getMetadata() {
        return this.At;
    }

    public long getStreamDuration() {
        return this.Au;
    }

    public int getStreamType() {
        return this.Ar;
    }

    public TextTrackStyle getTextTrackStyle() {
        return this.Aw;
    }

    public int hashCode() {
        return hl.hashCode(this.Aq, Integer.valueOf(this.Ar), this.As, this.At, Long.valueOf(this.Au), String.valueOf(this.Ax));
    }

    /* access modifiers changed from: package-private */
    public void m(long j) throws IllegalArgumentException {
        if (j < 0) {
            throw new IllegalArgumentException("Stream duration cannot be negative");
        }
        this.Au = j;
    }

    /* access modifiers changed from: package-private */
    public void setContentType(String contentType) throws IllegalArgumentException {
        if (TextUtils.isEmpty(contentType)) {
            throw new IllegalArgumentException("content type cannot be null or empty");
        }
        this.As = contentType;
    }

    /* access modifiers changed from: package-private */
    public void setCustomData(JSONObject customData) {
        this.Ax = customData;
    }

    /* access modifiers changed from: package-private */
    public void setStreamType(int streamType) throws IllegalArgumentException {
        if (streamType < -1 || streamType > 2) {
            throw new IllegalArgumentException("invalid stream type");
        }
        this.Ar = streamType;
    }

    public void setTextTrackStyle(TextTrackStyle textTrackStyle) {
        this.Aw = textTrackStyle;
    }
}
