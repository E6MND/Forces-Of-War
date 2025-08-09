package com.google.android.gms.internal;

import android.content.Context;
import android.media.MediaPlayer;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.MediaController;
import android.widget.VideoView;
import com.google.android.gms.tagmanager.DataLayer;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public final class ck extends FrameLayout implements MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, MediaPlayer.OnPreparedListener {
    private final ey lL;
    private final MediaController oG;
    private final a oH = new a(this);
    private final VideoView oI;
    private long oJ;
    private String oK;

    private static final class a {
        private final Runnable le;
        /* access modifiers changed from: private */
        public volatile boolean oL = false;

        public a(final ck ckVar) {
            this.le = new Runnable() {
                private final WeakReference<ck> oM = new WeakReference<>(ckVar);

                public void run() {
                    ck ckVar = (ck) this.oM.get();
                    if (!a.this.oL && ckVar != null) {
                        ckVar.aW();
                        a.this.aX();
                    }
                }
            };
        }

        public void aX() {
            eu.ss.postDelayed(this.le, 250);
        }

        public void cancel() {
            this.oL = true;
            eu.ss.removeCallbacks(this.le);
        }
    }

    public ck(Context context, ey eyVar) {
        super(context);
        this.lL = eyVar;
        this.oI = new VideoView(context);
        addView(this.oI, new FrameLayout.LayoutParams(-1, -1, 17));
        this.oG = new MediaController(context);
        this.oH.aX();
        this.oI.setOnCompletionListener(this);
        this.oI.setOnPreparedListener(this);
        this.oI.setOnErrorListener(this);
    }

    private static void a(ey eyVar, String str) {
        a(eyVar, str, (Map<String, String>) new HashMap(1));
    }

    public static void a(ey eyVar, String str, String str2) {
        boolean z = str2 == null;
        HashMap hashMap = new HashMap(z ? 2 : 3);
        hashMap.put("what", str);
        if (!z) {
            hashMap.put("extra", str2);
        }
        a(eyVar, "error", (Map<String, String>) hashMap);
    }

    private static void a(ey eyVar, String str, String str2, String str3) {
        HashMap hashMap = new HashMap(2);
        hashMap.put(str2, str3);
        a(eyVar, str, (Map<String, String>) hashMap);
    }

    private static void a(ey eyVar, String str, Map<String, String> map) {
        map.put(DataLayer.EVENT_KEY, str);
        eyVar.a("onVideoEvent", (Map<String, ?>) map);
    }

    public void aV() {
        if (!TextUtils.isEmpty(this.oK)) {
            this.oI.setVideoPath(this.oK);
        } else {
            a(this.lL, "no_src", (String) null);
        }
    }

    public void aW() {
        long currentPosition = (long) this.oI.getCurrentPosition();
        if (this.oJ != currentPosition) {
            a(this.lL, "timeupdate", "time", String.valueOf(((float) currentPosition) / 1000.0f));
            this.oJ = currentPosition;
        }
    }

    public void b(MotionEvent motionEvent) {
        this.oI.dispatchTouchEvent(motionEvent);
    }

    public void destroy() {
        this.oH.cancel();
        this.oI.stopPlayback();
    }

    public void l(boolean z) {
        if (z) {
            this.oI.setMediaController(this.oG);
            return;
        }
        this.oG.hide();
        this.oI.setMediaController((MediaController) null);
    }

    public void o(String str) {
        this.oK = str;
    }

    public void onCompletion(MediaPlayer mediaPlayer) {
        a(this.lL, "ended");
    }

    public boolean onError(MediaPlayer mediaPlayer, int what, int extra) {
        a(this.lL, String.valueOf(what), String.valueOf(extra));
        return true;
    }

    public void onPrepared(MediaPlayer mediaPlayer) {
        a(this.lL, "canplaythrough", "duration", String.valueOf(((float) this.oI.getDuration()) / 1000.0f));
    }

    public void pause() {
        this.oI.pause();
    }

    public void play() {
        this.oI.start();
    }

    public void seekTo(int timeInMilliseconds) {
        this.oI.seekTo(timeInMilliseconds);
    }
}
