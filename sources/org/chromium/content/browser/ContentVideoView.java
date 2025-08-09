package org.chromium.content.browser;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.graphics.Point;
import android.provider.Settings;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import org.chromium.base.CalledByNative;
import org.chromium.base.JNINamespace;
import org.chromium.base.ThreadUtils;
import org.chromium.content.R;

@JNINamespace("content")
public class ContentVideoView extends FrameLayout implements SurfaceHolder.Callback {
    private static final int MEDIA_BUFFERING_UPDATE = 3;
    private static final int MEDIA_ERROR = 100;
    public static final int MEDIA_ERROR_INVALID_CODE = 3;
    public static final int MEDIA_ERROR_NOT_VALID_FOR_PROGRESSIVE_PLAYBACK = 2;
    private static final int MEDIA_INFO = 200;
    private static final int MEDIA_NOP = 0;
    private static final int MEDIA_PLAYBACK_COMPLETE = 2;
    private static final int MEDIA_PREPARED = 1;
    private static final int MEDIA_SEEK_COMPLETE = 4;
    private static final int MEDIA_SET_VIDEO_SIZE = 5;
    private static final int STATE_ERROR = -1;
    private static final int STATE_IDLE = 0;
    private static final int STATE_PAUSED = 2;
    private static final int STATE_PLAYBACK_COMPLETED = 3;
    private static final int STATE_PLAYING = 1;
    private static final String TAG = "ContentVideoView";
    private final ContentVideoViewClient mClient;
    private int mCurrentState = 0;
    private int mDuration;
    private String mErrorButton;
    private String mErrorTitle;
    private final Runnable mExitFullscreenRunnable = new Runnable() {
        public void run() {
            ContentVideoView.this.exitFullscreen(true);
        }
    };
    /* access modifiers changed from: private */
    public boolean mInitialOrientation;
    private long mNativeContentVideoView;
    /* access modifiers changed from: private */
    public long mOrientationChangedTime;
    private String mPlaybackErrorText;
    /* access modifiers changed from: private */
    public long mPlaybackStartTime;
    /* access modifiers changed from: private */
    public boolean mPossibleAccidentalChange;
    private View mProgressView;
    private SurfaceHolder mSurfaceHolder;
    /* access modifiers changed from: private */
    public boolean mUmaRecorded;
    private String mUnknownErrorText;
    /* access modifiers changed from: private */
    public int mVideoHeight;
    private String mVideoLoadingText;
    private VideoSurfaceView mVideoSurfaceView;
    /* access modifiers changed from: private */
    public int mVideoWidth;

    private native void nativeExitFullscreen(long j, boolean z);

    private native int nativeGetCurrentPosition(long j);

    private native int nativeGetDurationInMilliSeconds(long j);

    private static native ContentVideoView nativeGetSingletonJavaContentVideoView();

    private native int nativeGetVideoHeight(long j);

    private native int nativeGetVideoWidth(long j);

    private native boolean nativeIsPlaying(long j);

    private native void nativePause(long j);

    private native void nativePlay(long j);

    private native void nativeRecordExitFullscreenPlayback(long j, boolean z, long j2, long j3);

    private native void nativeRecordFullscreenPlayback(long j, boolean z, boolean z2);

    private native void nativeRequestMediaMetadata(long j);

    private native void nativeSeekTo(long j, int i);

    private native void nativeSetSurface(long j, Surface surface);

    private class VideoSurfaceView extends SurfaceView {
        public VideoSurfaceView(Context context) {
            super(context);
        }

        /* access modifiers changed from: protected */
        public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            int width = 1;
            int height = 1;
            if (ContentVideoView.this.mVideoWidth > 0 && ContentVideoView.this.mVideoHeight > 0) {
                width = getDefaultSize(ContentVideoView.this.mVideoWidth, widthMeasureSpec);
                height = getDefaultSize(ContentVideoView.this.mVideoHeight, heightMeasureSpec);
                if (ContentVideoView.this.mVideoWidth * height > ContentVideoView.this.mVideoHeight * width) {
                    height = (ContentVideoView.this.mVideoHeight * width) / ContentVideoView.this.mVideoWidth;
                } else if (ContentVideoView.this.mVideoWidth * height < ContentVideoView.this.mVideoHeight * width) {
                    width = (ContentVideoView.this.mVideoWidth * height) / ContentVideoView.this.mVideoHeight;
                }
            }
            if (ContentVideoView.this.mUmaRecorded) {
                if (ContentVideoView.this.mPlaybackStartTime == ContentVideoView.this.mOrientationChangedTime) {
                    if (ContentVideoView.this.isOrientationPortrait() != ContentVideoView.this.mInitialOrientation) {
                        long unused = ContentVideoView.this.mOrientationChangedTime = System.currentTimeMillis();
                    }
                } else if (!ContentVideoView.this.mPossibleAccidentalChange && ContentVideoView.this.isOrientationPortrait() == ContentVideoView.this.mInitialOrientation && System.currentTimeMillis() - ContentVideoView.this.mOrientationChangedTime < 5000) {
                    boolean unused2 = ContentVideoView.this.mPossibleAccidentalChange = true;
                }
            }
            setMeasuredDimension(width, height);
        }
    }

    private static class ProgressView extends LinearLayout {
        private final ProgressBar mProgressBar;
        private final TextView mTextView;

        public ProgressView(Context context, String videoLoadingText) {
            super(context);
            setOrientation(1);
            setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
            this.mProgressBar = new ProgressBar(context, (AttributeSet) null, 16842874);
            this.mTextView = new TextView(context);
            this.mTextView.setText(videoLoadingText);
            addView(this.mProgressBar);
            addView(this.mTextView);
        }
    }

    protected ContentVideoView(Context context, long nativeContentVideoView, ContentVideoViewClient client) {
        super(context);
        this.mNativeContentVideoView = nativeContentVideoView;
        this.mClient = client;
        this.mUmaRecorded = false;
        this.mPossibleAccidentalChange = false;
        initResources(context);
        this.mVideoSurfaceView = new VideoSurfaceView(context);
        showContentVideoView();
        setVisibility(0);
    }

    /* access modifiers changed from: protected */
    public ContentVideoViewClient getContentVideoViewClient() {
        return this.mClient;
    }

    private void initResources(Context context) {
        if (this.mPlaybackErrorText == null) {
            this.mPlaybackErrorText = context.getString(R.string.media_player_error_text_invalid_progressive_playback);
            this.mUnknownErrorText = context.getString(R.string.media_player_error_text_unknown);
            this.mErrorButton = context.getString(R.string.media_player_error_button);
            this.mErrorTitle = context.getString(R.string.media_player_error_title);
            this.mVideoLoadingText = context.getString(R.string.media_player_loading_video);
        }
    }

    /* access modifiers changed from: protected */
    public void showContentVideoView() {
        this.mVideoSurfaceView.getHolder().addCallback(this);
        addView(this.mVideoSurfaceView, new FrameLayout.LayoutParams(-2, -2, 17));
        this.mProgressView = this.mClient.getVideoLoadingProgressView();
        if (this.mProgressView == null) {
            this.mProgressView = new ProgressView(getContext(), this.mVideoLoadingText);
        }
        addView(this.mProgressView, new FrameLayout.LayoutParams(-2, -2, 17));
    }

    /* access modifiers changed from: protected */
    public SurfaceView getSurfaceView() {
        return this.mVideoSurfaceView;
    }

    @CalledByNative
    public void onMediaPlayerError(int errorType) {
        String message;
        Log.d(TAG, "OnMediaPlayerError: " + errorType);
        if (this.mCurrentState != -1 && this.mCurrentState != 3 && errorType != 3) {
            this.mCurrentState = -1;
            if (!isActivityContext(getContext())) {
                Log.w(TAG, "Unable to show alert dialog because it requires an activity context");
            } else if (getWindowToken() != null) {
                if (errorType == 2) {
                    message = this.mPlaybackErrorText;
                } else {
                    message = this.mUnknownErrorText;
                }
                try {
                    new AlertDialog.Builder(getContext()).setTitle(this.mErrorTitle).setMessage(message).setPositiveButton(this.mErrorButton, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            ContentVideoView.this.onCompletion();
                        }
                    }).setCancelable(false).show();
                } catch (RuntimeException e) {
                    Log.e(TAG, "Cannot show the alert dialog, error message: " + message, e);
                }
            }
        }
    }

    @CalledByNative
    private void onVideoSizeChanged(int width, int height) {
        this.mVideoWidth = width;
        this.mVideoHeight = height;
        this.mVideoSurfaceView.getHolder().setFixedSize(this.mVideoWidth, this.mVideoHeight);
    }

    /* access modifiers changed from: protected */
    @CalledByNative
    public void onBufferingUpdate(int percent) {
    }

    @CalledByNative
    private void onPlaybackComplete() {
        onCompletion();
    }

    /* access modifiers changed from: protected */
    @CalledByNative
    public void onUpdateMediaMetadata(int videoWidth, int videoHeight, int duration, boolean canPause, boolean canSeekBack, boolean canSeekForward) {
        boolean z = true;
        this.mDuration = duration;
        this.mProgressView.setVisibility(8);
        this.mCurrentState = isPlaying() ? 1 : 2;
        onVideoSizeChanged(videoWidth, videoHeight);
        if (!this.mUmaRecorded) {
            try {
                if (Settings.System.getInt(getContext().getContentResolver(), "accelerometer_rotation") != 0) {
                    this.mInitialOrientation = isOrientationPortrait();
                    this.mUmaRecorded = true;
                    this.mPlaybackStartTime = System.currentTimeMillis();
                    this.mOrientationChangedTime = this.mPlaybackStartTime;
                    long j = this.mNativeContentVideoView;
                    if (videoHeight <= videoWidth) {
                        z = false;
                    }
                    nativeRecordFullscreenPlayback(j, z, this.mInitialOrientation);
                }
            } catch (Settings.SettingNotFoundException e) {
            }
        }
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    public void surfaceCreated(SurfaceHolder holder) {
        this.mSurfaceHolder = holder;
        openVideo();
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        if (this.mNativeContentVideoView != 0) {
            nativeSetSurface(this.mNativeContentVideoView, (Surface) null);
        }
        this.mSurfaceHolder = null;
        post(this.mExitFullscreenRunnable);
    }

    /* access modifiers changed from: protected */
    @CalledByNative
    public void openVideo() {
        if (this.mSurfaceHolder != null) {
            this.mCurrentState = 0;
            if (this.mNativeContentVideoView != 0) {
                nativeRequestMediaMetadata(this.mNativeContentVideoView);
                nativeSetSurface(this.mNativeContentVideoView, this.mSurfaceHolder.getSurface());
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onCompletion() {
        this.mCurrentState = 3;
    }

    /* access modifiers changed from: protected */
    public boolean isInPlaybackState() {
        return (this.mCurrentState == -1 || this.mCurrentState == 0) ? false : true;
    }

    /* access modifiers changed from: protected */
    public void start() {
        if (isInPlaybackState()) {
            if (this.mNativeContentVideoView != 0) {
                nativePlay(this.mNativeContentVideoView);
            }
            this.mCurrentState = 1;
        }
    }

    /* access modifiers changed from: protected */
    public void pause() {
        if (isInPlaybackState() && isPlaying()) {
            if (this.mNativeContentVideoView != 0) {
                nativePause(this.mNativeContentVideoView);
            }
            this.mCurrentState = 2;
        }
    }

    /* access modifiers changed from: protected */
    public int getDuration() {
        if (!isInPlaybackState()) {
            this.mDuration = -1;
            return this.mDuration;
        } else if (this.mDuration > 0) {
            return this.mDuration;
        } else {
            if (this.mNativeContentVideoView != 0) {
                this.mDuration = nativeGetDurationInMilliSeconds(this.mNativeContentVideoView);
            } else {
                this.mDuration = 0;
            }
            return this.mDuration;
        }
    }

    /* access modifiers changed from: protected */
    public int getCurrentPosition() {
        if (!isInPlaybackState() || this.mNativeContentVideoView == 0) {
            return 0;
        }
        return nativeGetCurrentPosition(this.mNativeContentVideoView);
    }

    /* access modifiers changed from: protected */
    public void seekTo(int msec) {
        if (this.mNativeContentVideoView != 0) {
            nativeSeekTo(this.mNativeContentVideoView, msec);
        }
    }

    public boolean isPlaying() {
        return this.mNativeContentVideoView != 0 && nativeIsPlaying(this.mNativeContentVideoView);
    }

    @CalledByNative
    private static ContentVideoView createContentVideoView(ContentViewCore contentViewCore, long nativeContentVideoView) {
        ThreadUtils.assertOnUiThread();
        Context context = contentViewCore.getContext();
        ContentVideoViewClient client = contentViewCore.getContentVideoViewClient();
        ContentVideoView videoView = new ContentVideoView(context, nativeContentVideoView, client);
        client.enterFullscreenVideo(videoView);
        return videoView;
    }

    private static boolean isActivityContext(Context context) {
        if (!(context instanceof ContextWrapper) || (context instanceof Activity)) {
            return context instanceof Activity;
        }
        return isActivityContext(((ContextWrapper) context).getBaseContext());
    }

    public void removeSurfaceView() {
        removeView(this.mVideoSurfaceView);
        removeView(this.mProgressView);
        this.mVideoSurfaceView = null;
        this.mProgressView = null;
    }

    public void exitFullscreen(boolean relaseMediaPlayer) {
        destroyContentVideoView(false);
        if (this.mNativeContentVideoView != 0) {
            if (this.mUmaRecorded && !this.mPossibleAccidentalChange) {
                long currentTime = System.currentTimeMillis();
                long timeBeforeOrientationChange = this.mOrientationChangedTime - this.mPlaybackStartTime;
                long timeAfterOrientationChange = currentTime - this.mOrientationChangedTime;
                if (timeBeforeOrientationChange == 0) {
                    timeBeforeOrientationChange = timeAfterOrientationChange;
                    timeAfterOrientationChange = 0;
                }
                nativeRecordExitFullscreenPlayback(this.mNativeContentVideoView, this.mInitialOrientation, timeBeforeOrientationChange, timeAfterOrientationChange);
            }
            nativeExitFullscreen(this.mNativeContentVideoView, relaseMediaPlayer);
            this.mNativeContentVideoView = 0;
        }
    }

    @CalledByNative
    private void onExitFullscreen() {
        exitFullscreen(false);
    }

    /* access modifiers changed from: protected */
    @CalledByNative
    public void destroyContentVideoView(boolean nativeViewDestroyed) {
        if (this.mVideoSurfaceView != null) {
            removeSurfaceView();
            setVisibility(8);
            this.mClient.exitFullscreenVideo();
        }
        if (nativeViewDestroyed) {
            this.mNativeContentVideoView = 0;
        }
    }

    public static ContentVideoView getContentVideoView() {
        return nativeGetSingletonJavaContentVideoView();
    }

    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode != 4) {
            return super.onKeyUp(keyCode, event);
        }
        exitFullscreen(false);
        return true;
    }

    /* access modifiers changed from: private */
    public boolean isOrientationPortrait() {
        Display display = ((WindowManager) getContext().getSystemService("window")).getDefaultDisplay();
        Point outputSize = new Point(0, 0);
        display.getSize(outputSize);
        if (outputSize.x <= outputSize.y) {
            return true;
        }
        return false;
    }
}
