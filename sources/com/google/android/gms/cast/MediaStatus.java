package com.google.android.gms.cast;

import com.google.android.gms.internal.gj;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class MediaStatus {
    public static final long COMMAND_PAUSE = 1;
    public static final long COMMAND_SEEK = 2;
    public static final long COMMAND_SET_VOLUME = 4;
    public static final long COMMAND_SKIP_BACKWARD = 32;
    public static final long COMMAND_SKIP_FORWARD = 16;
    public static final long COMMAND_TOGGLE_MUTE = 8;
    public static final int IDLE_REASON_CANCELED = 2;
    public static final int IDLE_REASON_ERROR = 4;
    public static final int IDLE_REASON_FINISHED = 1;
    public static final int IDLE_REASON_INTERRUPTED = 3;
    public static final int IDLE_REASON_NONE = 0;
    public static final int PLAYER_STATE_BUFFERING = 4;
    public static final int PLAYER_STATE_IDLE = 1;
    public static final int PLAYER_STATE_PAUSED = 3;
    public static final int PLAYER_STATE_PLAYING = 2;
    public static final int PLAYER_STATE_UNKNOWN = 0;
    private long AG;
    private double AH;
    private int AI;
    private int AJ;
    private long AK;
    private long AL;
    private double AM;
    private boolean AN;
    private long[] AO;
    private JSONObject Ax;
    private MediaInfo Ay;

    public MediaStatus(JSONObject json) throws JSONException {
        a(json, 0);
    }

    public int a(JSONObject jSONObject, int i) throws JSONException {
        int i2;
        long[] jArr;
        int i3 = 2;
        boolean z = false;
        boolean z2 = true;
        long j = jSONObject.getLong("mediaSessionId");
        if (j != this.AG) {
            this.AG = j;
            i2 = 1;
        } else {
            i2 = 0;
        }
        if (jSONObject.has("playerState")) {
            String string = jSONObject.getString("playerState");
            int i4 = string.equals("IDLE") ? 1 : string.equals("PLAYING") ? 2 : string.equals("PAUSED") ? 3 : string.equals("BUFFERING") ? 4 : 0;
            if (i4 != this.AI) {
                this.AI = i4;
                i2 |= 2;
            }
            if (i4 == 1 && jSONObject.has("idleReason")) {
                String string2 = jSONObject.getString("idleReason");
                if (!string2.equals("CANCELLED")) {
                    i3 = string2.equals("INTERRUPTED") ? 3 : string2.equals("FINISHED") ? 1 : string2.equals("ERROR") ? 4 : 0;
                }
                if (i3 != this.AJ) {
                    this.AJ = i3;
                    i2 |= 2;
                }
            }
        }
        if (jSONObject.has("playbackRate")) {
            double d = jSONObject.getDouble("playbackRate");
            if (this.AH != d) {
                this.AH = d;
                i2 |= 2;
            }
        }
        if (jSONObject.has("currentTime") && (i & 2) == 0) {
            long b = gj.b(jSONObject.getDouble("currentTime"));
            if (b != this.AK) {
                this.AK = b;
                i2 |= 2;
            }
        }
        if (jSONObject.has("supportedMediaCommands")) {
            long j2 = jSONObject.getLong("supportedMediaCommands");
            if (j2 != this.AL) {
                this.AL = j2;
                i2 |= 2;
            }
        }
        if (jSONObject.has("volume") && (i & 1) == 0) {
            JSONObject jSONObject2 = jSONObject.getJSONObject("volume");
            double d2 = jSONObject2.getDouble("level");
            if (d2 != this.AM) {
                this.AM = d2;
                i2 |= 2;
            }
            boolean z3 = jSONObject2.getBoolean("muted");
            if (z3 != this.AN) {
                this.AN = z3;
                i2 |= 2;
            }
        }
        if (jSONObject.has("activeTrackIds")) {
            JSONArray jSONArray = jSONObject.getJSONArray("activeTrackIds");
            int length = jSONArray.length();
            long[] jArr2 = new long[length];
            for (int i5 = 0; i5 < length; i5++) {
                jArr2[i5] = jSONArray.getLong(i5);
            }
            if (this.AO != null && this.AO.length == length) {
                int i6 = 0;
                while (true) {
                    if (i6 < length) {
                        if (this.AO[i6] != jArr2[i6]) {
                            break;
                        }
                        i6++;
                    } else {
                        z2 = false;
                        break;
                    }
                }
            }
            if (z2) {
                this.AO = jArr2;
            }
            z = z2;
            jArr = jArr2;
        } else if (this.AO != null) {
            z = true;
            jArr = null;
        } else {
            jArr = null;
        }
        if (z) {
            this.AO = jArr;
            i2 |= 2;
        }
        if (jSONObject.has("customData")) {
            this.Ax = jSONObject.getJSONObject("customData");
            i2 |= 2;
        }
        if (!jSONObject.has("media")) {
            return i2;
        }
        JSONObject jSONObject3 = jSONObject.getJSONObject("media");
        this.Ay = new MediaInfo(jSONObject3);
        int i7 = i2 | 2;
        return jSONObject3.has("metadata") ? i7 | 4 : i7;
    }

    public long dV() {
        return this.AG;
    }

    public long[] getActiveTrackIds() {
        return this.AO;
    }

    public JSONObject getCustomData() {
        return this.Ax;
    }

    public int getIdleReason() {
        return this.AJ;
    }

    public MediaInfo getMediaInfo() {
        return this.Ay;
    }

    public double getPlaybackRate() {
        return this.AH;
    }

    public int getPlayerState() {
        return this.AI;
    }

    public long getStreamPosition() {
        return this.AK;
    }

    public double getStreamVolume() {
        return this.AM;
    }

    public boolean isMediaCommandSupported(long mediaCommand) {
        return (this.AL & mediaCommand) != 0;
    }

    public boolean isMute() {
        return this.AN;
    }
}
