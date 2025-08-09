package org.chromium.media;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import org.chromium.base.CalledByNative;
import org.chromium.base.JNINamespace;

@JNINamespace("media")
class MediaPlayerListener implements MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnSeekCompleteListener, MediaPlayer.OnVideoSizeChangedListener, MediaPlayer.OnErrorListener, AudioManager.OnAudioFocusChangeListener {
    private static final int MEDIA_ERROR_DECODE = 1;
    private static final int MEDIA_ERROR_FORMAT = 0;
    private static final int MEDIA_ERROR_INVALID_CODE = 3;
    public static final int MEDIA_ERROR_MALFORMED = -1007;
    private static final int MEDIA_ERROR_NOT_VALID_FOR_PROGRESSIVE_PLAYBACK = 2;
    public static final int MEDIA_ERROR_TIMED_OUT = -110;
    private final Context mContext;
    private long mNativeMediaPlayerListener = 0;

    private native void nativeOnBufferingUpdate(long j, int i);

    private native void nativeOnMediaError(long j, int i);

    private native void nativeOnMediaInterrupted(long j);

    private native void nativeOnMediaPrepared(long j);

    private native void nativeOnPlaybackComplete(long j);

    private native void nativeOnSeekComplete(long j);

    private native void nativeOnVideoSizeChanged(long j, int i, int i2);

    private MediaPlayerListener(long nativeMediaPlayerListener, Context context) {
        this.mNativeMediaPlayerListener = nativeMediaPlayerListener;
        this.mContext = context;
    }

    public boolean onError(MediaPlayer mp, int what, int extra) {
        int errorType;
        switch (what) {
            case 1:
                switch (extra) {
                    case -1007:
                        errorType = 1;
                        break;
                    case -110:
                        errorType = 3;
                        break;
                    default:
                        errorType = 0;
                        break;
                }
            case 100:
                errorType = 1;
                break;
            case 200:
                errorType = 2;
                break;
            default:
                errorType = 3;
                break;
        }
        nativeOnMediaError(this.mNativeMediaPlayerListener, errorType);
        return true;
    }

    public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
        nativeOnVideoSizeChanged(this.mNativeMediaPlayerListener, width, height);
    }

    public void onSeekComplete(MediaPlayer mp) {
        nativeOnSeekComplete(this.mNativeMediaPlayerListener);
    }

    public void onBufferingUpdate(MediaPlayer mp, int percent) {
        nativeOnBufferingUpdate(this.mNativeMediaPlayerListener, percent);
    }

    public void onCompletion(MediaPlayer mp) {
        nativeOnPlaybackComplete(this.mNativeMediaPlayerListener);
    }

    public void onPrepared(MediaPlayer mp) {
        nativeOnMediaPrepared(this.mNativeMediaPlayerListener);
    }

    public void onAudioFocusChange(int focusChange) {
        if (focusChange == -1 || focusChange == -2) {
            nativeOnMediaInterrupted(this.mNativeMediaPlayerListener);
        }
    }

    @CalledByNative
    public void releaseResources() {
        AudioManager am;
        if (this.mContext != null && (am = (AudioManager) this.mContext.getSystemService("audio")) != null) {
            am.abandonAudioFocus(this);
        }
    }

    @CalledByNative
    private static MediaPlayerListener create(long nativeMediaPlayerListener, Context context, MediaPlayerBridge mediaPlayerBridge) {
        MediaPlayerListener listener = new MediaPlayerListener(nativeMediaPlayerListener, context);
        if (mediaPlayerBridge != null) {
            mediaPlayerBridge.setOnBufferingUpdateListener(listener);
            mediaPlayerBridge.setOnCompletionListener(listener);
            mediaPlayerBridge.setOnErrorListener(listener);
            mediaPlayerBridge.setOnPreparedListener(listener);
            mediaPlayerBridge.setOnSeekCompleteListener(listener);
            mediaPlayerBridge.setOnVideoSizeChangedListener(listener);
        }
        ((AudioManager) context.getSystemService("audio")).requestAudioFocus(listener, 3, 3);
        return listener;
    }
}
