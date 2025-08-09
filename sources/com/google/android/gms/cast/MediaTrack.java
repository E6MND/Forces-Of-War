package com.google.android.gms.cast;

import android.text.TextUtils;
import com.google.android.gms.internal.gj;
import com.google.android.gms.internal.hl;
import com.google.android.gms.internal.io;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;
import org.xwalk.core.internal.extension.api.messaging.MessagingSmsConsts;

public final class MediaTrack {
    public static final int SUBTYPE_CAPTIONS = 2;
    public static final int SUBTYPE_CHAPTERS = 4;
    public static final int SUBTYPE_DESCRIPTIONS = 3;
    public static final int SUBTYPE_METADATA = 5;
    public static final int SUBTYPE_NONE = 0;
    public static final int SUBTYPE_SUBTITLES = 1;
    public static final int SUBTYPE_UNKNOWN = -1;
    public static final int TYPE_AUDIO = 2;
    public static final int TYPE_TEXT = 1;
    public static final int TYPE_UNKNOWN = 0;
    public static final int TYPE_VIDEO = 3;
    private long AP;
    private int AQ;
    private int AR;
    private String Ao;
    private String Aq;
    private String As;
    private JSONObject Ax;
    private String mName;

    public static class Builder {
        private final MediaTrack AS;

        public Builder(long trackId, int trackType) throws IllegalArgumentException {
            this.AS = new MediaTrack(trackId, trackType);
        }

        public MediaTrack build() {
            return this.AS;
        }

        public Builder setContentId(String contentId) {
            this.AS.setContentId(contentId);
            return this;
        }

        public Builder setContentType(String contentType) {
            this.AS.setContentType(contentType);
            return this;
        }

        public Builder setCustomData(JSONObject customData) {
            this.AS.setCustomData(customData);
            return this;
        }

        public Builder setLanguage(String language) {
            this.AS.setLanguage(language);
            return this;
        }

        public Builder setLanguage(Locale locale) {
            this.AS.setLanguage(gj.b(locale));
            return this;
        }

        public Builder setName(String trackName) {
            this.AS.setName(trackName);
            return this;
        }

        public Builder setSubtype(int subtype) throws IllegalArgumentException {
            this.AS.R(subtype);
            return this;
        }
    }

    MediaTrack(long id, int type) throws IllegalArgumentException {
        clear();
        this.AP = id;
        if (type <= 0 || type > 3) {
            throw new IllegalArgumentException("invalid type " + type);
        }
        this.AQ = type;
    }

    MediaTrack(JSONObject json) throws JSONException {
        b(json);
    }

    private void b(JSONObject jSONObject) throws JSONException {
        clear();
        this.AP = jSONObject.getLong("trackId");
        String string = jSONObject.getString(MessagingSmsConsts.TYPE);
        if ("TEXT".equals(string)) {
            this.AQ = 1;
        } else if ("AUDIO".equals(string)) {
            this.AQ = 2;
        } else if ("VIDEO".equals(string)) {
            this.AQ = 3;
        } else {
            throw new JSONException("invalid type: " + string);
        }
        this.Aq = jSONObject.optString("trackContentId", (String) null);
        this.As = jSONObject.optString("trackContentType", (String) null);
        this.mName = jSONObject.optString("name", (String) null);
        this.Ao = jSONObject.optString("language", (String) null);
        if (jSONObject.has("subtype")) {
            String string2 = jSONObject.getString("subtype");
            if ("SUBTITLES".equals(string2)) {
                this.AR = 1;
            } else if ("CAPTIONS".equals(string2)) {
                this.AR = 2;
            } else if ("DESCRIPTIONS".equals(string2)) {
                this.AR = 3;
            } else if ("CHAPTERS".equals(string2)) {
                this.AR = 4;
            } else if ("METADATA".equals(string2)) {
                this.AR = 5;
            } else {
                throw new JSONException("invalid subtype: " + string2);
            }
        } else {
            this.AR = 0;
        }
        this.Ax = jSONObject.optJSONObject("customData");
    }

    private void clear() {
        this.AP = 0;
        this.AQ = 0;
        this.Aq = null;
        this.mName = null;
        this.Ao = null;
        this.AR = -1;
        this.Ax = null;
    }

    /* access modifiers changed from: package-private */
    public void R(int i) throws IllegalArgumentException {
        if (i <= -1 || i > 5) {
            throw new IllegalArgumentException("invalid subtype " + i);
        } else if (i == 0 || this.AQ == 1) {
            this.AR = i;
        } else {
            throw new IllegalArgumentException("subtypes are only valid for text tracks");
        }
    }

    public JSONObject dU() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("trackId", this.AP);
            switch (this.AQ) {
                case 1:
                    jSONObject.put(MessagingSmsConsts.TYPE, "TEXT");
                    break;
                case 2:
                    jSONObject.put(MessagingSmsConsts.TYPE, "AUDIO");
                    break;
                case 3:
                    jSONObject.put(MessagingSmsConsts.TYPE, "VIDEO");
                    break;
            }
            if (this.Aq != null) {
                jSONObject.put("trackContentId", this.Aq);
            }
            if (this.As != null) {
                jSONObject.put("trackContentType", this.As);
            }
            if (this.mName != null) {
                jSONObject.put("name", this.mName);
            }
            if (!TextUtils.isEmpty(this.Ao)) {
                jSONObject.put("language", this.Ao);
            }
            switch (this.AR) {
                case 1:
                    jSONObject.put("subtype", "SUBTITLES");
                    break;
                case 2:
                    jSONObject.put("subtype", "CAPTIONS");
                    break;
                case 3:
                    jSONObject.put("subtype", "DESCRIPTIONS");
                    break;
                case 4:
                    jSONObject.put("subtype", "CHAPTERS");
                    break;
                case 5:
                    jSONObject.put("subtype", "METADATA");
                    break;
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
        if (!(other instanceof MediaTrack)) {
            return false;
        }
        MediaTrack mediaTrack = (MediaTrack) other;
        if ((this.Ax == null) != (mediaTrack.Ax == null)) {
            return false;
        }
        if (this.Ax != null && mediaTrack.Ax != null && !io.d(this.Ax, mediaTrack.Ax)) {
            return false;
        }
        if (this.AP != mediaTrack.AP || this.AQ != mediaTrack.AQ || !gj.a(this.Aq, mediaTrack.Aq) || !gj.a(this.As, mediaTrack.As) || !gj.a(this.mName, mediaTrack.mName) || !gj.a(this.Ao, mediaTrack.Ao) || this.AR != mediaTrack.AR) {
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

    public long getId() {
        return this.AP;
    }

    public String getLanguage() {
        return this.Ao;
    }

    public String getName() {
        return this.mName;
    }

    public int getSubtype() {
        return this.AR;
    }

    public int getType() {
        return this.AQ;
    }

    public int hashCode() {
        return hl.hashCode(Long.valueOf(this.AP), Integer.valueOf(this.AQ), this.Aq, this.As, this.mName, this.Ao, Integer.valueOf(this.AR), this.Ax);
    }

    public void setContentId(String contentId) {
        this.Aq = contentId;
    }

    public void setContentType(String contentType) {
        this.As = contentType;
    }

    /* access modifiers changed from: package-private */
    public void setCustomData(JSONObject customData) {
        this.Ax = customData;
    }

    /* access modifiers changed from: package-private */
    public void setLanguage(String language) {
        this.Ao = language;
    }

    /* access modifiers changed from: package-private */
    public void setName(String name) {
        this.mName = name;
    }
}
