package org.chromium.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.view.Choreographer;
import android.view.WindowManager;
import org.chromium.base.TraceEvent;

@SuppressLint({"NewApi"})
public class VSyncMonitor {
    static final /* synthetic */ boolean $assertionsDisabled = (!VSyncMonitor.class.desiredAssertionStatus());
    private static final long NANOSECONDS_PER_MICROSECOND = 1000;
    private static final long NANOSECONDS_PER_MILLISECOND = 1000000;
    private static final long NANOSECONDS_PER_SECOND = 1000000000;
    private final Choreographer mChoreographer;
    /* access modifiers changed from: private */
    public boolean mConsecutiveVSync;
    /* access modifiers changed from: private */
    public long mGoodStartingPointNano;
    private final Handler mHandler;
    private boolean mHaveRequestInFlight;
    private boolean mInsideVSync;
    private long mLastPostedNano;
    private long mLastVSyncCpuTimeNano;
    private Listener mListener;
    /* access modifiers changed from: private */
    public long mRefreshPeriodNano;
    private final Runnable mSyntheticVSyncRunnable;
    private final Choreographer.FrameCallback mVSyncFrameCallback;
    private final Runnable mVSyncRunnableCallback;

    public interface Listener {
        void onVSync(VSyncMonitor vSyncMonitor, long j);
    }

    static /* synthetic */ long access$214(VSyncMonitor x0, long x1) {
        long j = x0.mRefreshPeriodNano + x1;
        x0.mRefreshPeriodNano = j;
        return j;
    }

    public VSyncMonitor(Context context, Listener listener) {
        this(context, listener, true);
    }

    public VSyncMonitor(Context context, Listener listener, boolean enableJBVSync) {
        final boolean useEstimatedRefreshPeriod = false;
        this.mInsideVSync = false;
        this.mConsecutiveVSync = false;
        this.mHandler = new Handler();
        this.mListener = listener;
        float refreshRate = ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getRefreshRate();
        useEstimatedRefreshPeriod = refreshRate < 30.0f ? true : useEstimatedRefreshPeriod;
        this.mRefreshPeriodNano = (long) (1.0E9f / (refreshRate <= 0.0f ? 60.0f : refreshRate));
        if (!enableJBVSync || Build.VERSION.SDK_INT < 16) {
            this.mChoreographer = null;
            this.mVSyncFrameCallback = null;
            this.mVSyncRunnableCallback = new Runnable() {
                public void run() {
                    TraceEvent.begin("VSyncTimer");
                    long currentTime = VSyncMonitor.this.getCurrentNanoTime();
                    VSyncMonitor.this.onVSyncCallback(currentTime, currentTime);
                    TraceEvent.end("VSyncTimer");
                }
            };
            this.mLastPostedNano = 0;
        } else {
            this.mChoreographer = Choreographer.getInstance();
            this.mVSyncFrameCallback = new Choreographer.FrameCallback() {
                public void doFrame(long frameTimeNanos) {
                    TraceEvent.begin("VSync");
                    if (useEstimatedRefreshPeriod && VSyncMonitor.this.mConsecutiveVSync) {
                        VSyncMonitor.access$214(VSyncMonitor.this, (long) (((float) ((frameTimeNanos - VSyncMonitor.this.mGoodStartingPointNano) - VSyncMonitor.this.mRefreshPeriodNano)) * 0.1f));
                    }
                    long unused = VSyncMonitor.this.mGoodStartingPointNano = frameTimeNanos;
                    VSyncMonitor.this.onVSyncCallback(frameTimeNanos, VSyncMonitor.this.getCurrentNanoTime());
                    TraceEvent.end("VSync");
                }
            };
            this.mVSyncRunnableCallback = null;
        }
        this.mSyntheticVSyncRunnable = new Runnable() {
            public void run() {
                TraceEvent.begin("VSyncSynthetic");
                long currentTime = VSyncMonitor.this.getCurrentNanoTime();
                VSyncMonitor.this.onVSyncCallback(VSyncMonitor.this.estimateLastVSyncTime(currentTime), currentTime);
                TraceEvent.end("VSyncSynthetic");
            }
        };
        this.mGoodStartingPointNano = getCurrentNanoTime();
    }

    public long getVSyncPeriodInMicroseconds() {
        return this.mRefreshPeriodNano / NANOSECONDS_PER_MICROSECOND;
    }

    private boolean isVSyncSignalAvailable() {
        return this.mChoreographer != null;
    }

    public void requestUpdate() {
        postCallback();
    }

    public void setVSyncPointForICS(long goodStartingPointNano) {
        this.mGoodStartingPointNano = goodStartingPointNano;
    }

    public boolean isInsideVSync() {
        return this.mInsideVSync;
    }

    /* access modifiers changed from: private */
    public long getCurrentNanoTime() {
        return System.nanoTime();
    }

    /* access modifiers changed from: private */
    public void onVSyncCallback(long frameTimeNanos, long currentTimeNanos) {
        if ($assertionsDisabled || this.mHaveRequestInFlight) {
            this.mInsideVSync = true;
            this.mHaveRequestInFlight = false;
            this.mLastVSyncCpuTimeNano = currentTimeNanos;
            try {
                if (this.mListener != null) {
                    this.mListener.onVSync(this, frameTimeNanos / NANOSECONDS_PER_MICROSECOND);
                }
            } finally {
                this.mInsideVSync = false;
            }
        } else {
            throw new AssertionError();
        }
    }

    private void postCallback() {
        if (!this.mHaveRequestInFlight) {
            this.mHaveRequestInFlight = true;
            if (postSyntheticVSync()) {
                return;
            }
            if (isVSyncSignalAvailable()) {
                this.mConsecutiveVSync = this.mInsideVSync;
                this.mChoreographer.postFrameCallback(this.mVSyncFrameCallback);
                return;
            }
            postRunnableCallback();
        }
    }

    private boolean postSyntheticVSync() {
        long currentTime = getCurrentNanoTime();
        if (currentTime - this.mLastVSyncCpuTimeNano < this.mRefreshPeriodNano * 2 || currentTime - estimateLastVSyncTime(currentTime) > this.mRefreshPeriodNano / 2) {
            return false;
        }
        this.mHandler.post(this.mSyntheticVSyncRunnable);
        return true;
    }

    /* access modifiers changed from: private */
    public long estimateLastVSyncTime(long currentTime) {
        return this.mGoodStartingPointNano + (((currentTime - this.mGoodStartingPointNano) / this.mRefreshPeriodNano) * this.mRefreshPeriodNano);
    }

    private void postRunnableCallback() {
        if ($assertionsDisabled || !isVSyncSignalAvailable()) {
            long currentTime = getCurrentNanoTime();
            long delay = (this.mRefreshPeriodNano + estimateLastVSyncTime(currentTime)) - currentTime;
            if ($assertionsDisabled || (delay > 0 && delay <= this.mRefreshPeriodNano)) {
                if (currentTime + delay <= this.mLastPostedNano + (this.mRefreshPeriodNano / 2)) {
                    delay += this.mRefreshPeriodNano;
                }
                this.mLastPostedNano = currentTime + delay;
                if (delay == 0) {
                    this.mHandler.post(this.mVSyncRunnableCallback);
                } else {
                    this.mHandler.postDelayed(this.mVSyncRunnableCallback, delay / NANOSECONDS_PER_MILLISECOND);
                }
            } else {
                throw new AssertionError();
            }
        } else {
            throw new AssertionError();
        }
    }
}
