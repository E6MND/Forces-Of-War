package com.facebook;

import android.os.Handler;
import com.facebook.Request;

class RequestProgress {
    private final Handler callbackHandler;
    private long lastReportedProgress;
    private long maxProgress;
    private long progress;
    private final Request request;
    private final long threshold = Settings.getOnProgressThreshold();

    RequestProgress(Handler callbackHandler2, Request request2) {
        this.request = request2;
        this.callbackHandler = callbackHandler2;
    }

    /* access modifiers changed from: package-private */
    public long getProgress() {
        return this.progress;
    }

    /* access modifiers changed from: package-private */
    public long getMaxProgress() {
        return this.maxProgress;
    }

    /* access modifiers changed from: package-private */
    public void addProgress(long size) {
        this.progress += size;
        if (this.progress >= this.lastReportedProgress + this.threshold || this.progress >= this.maxProgress) {
            reportProgress();
        }
    }

    /* access modifiers changed from: package-private */
    public void addToMax(long size) {
        this.maxProgress += size;
    }

    /* access modifiers changed from: package-private */
    public void reportProgress() {
        if (this.progress > this.lastReportedProgress) {
            Request.Callback callback = this.request.getCallback();
            if (this.maxProgress > 0 && (callback instanceof Request.OnProgressCallback)) {
                final long currentCopy = this.progress;
                final long maxProgressCopy = this.maxProgress;
                final Request.OnProgressCallback callbackCopy = (Request.OnProgressCallback) callback;
                if (this.callbackHandler == null) {
                    callbackCopy.onProgress(currentCopy, maxProgressCopy);
                } else {
                    this.callbackHandler.post(new Runnable() {
                        public void run() {
                            callbackCopy.onProgress(currentCopy, maxProgressCopy);
                        }
                    });
                }
                this.lastReportedProgress = this.progress;
            }
        }
    }
}
