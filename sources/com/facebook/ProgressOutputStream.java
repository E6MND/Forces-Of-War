package com.facebook;

import android.os.Handler;
import com.facebook.RequestBatch;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

class ProgressOutputStream extends FilterOutputStream implements RequestOutputStream {
    /* access modifiers changed from: private */
    public long batchProgress;
    private RequestProgress currentRequestProgress;
    private long lastReportedProgress;
    /* access modifiers changed from: private */
    public long maxProgress;
    private final Map<Request, RequestProgress> progressMap;
    /* access modifiers changed from: private */
    public final RequestBatch requests;
    private final long threshold = Settings.getOnProgressThreshold();

    ProgressOutputStream(OutputStream out, RequestBatch requests2, Map<Request, RequestProgress> progressMap2, long maxProgress2) {
        super(out);
        this.requests = requests2;
        this.progressMap = progressMap2;
        this.maxProgress = maxProgress2;
    }

    private void addProgress(long size) {
        if (this.currentRequestProgress != null) {
            this.currentRequestProgress.addProgress(size);
        }
        this.batchProgress += size;
        if (this.batchProgress >= this.lastReportedProgress + this.threshold || this.batchProgress >= this.maxProgress) {
            reportBatchProgress();
        }
    }

    private void reportBatchProgress() {
        if (this.batchProgress > this.lastReportedProgress) {
            for (RequestBatch.Callback callback : this.requests.getCallbacks()) {
                if (callback instanceof RequestBatch.OnProgressCallback) {
                    Handler callbackHandler = this.requests.getCallbackHandler();
                    final RequestBatch.OnProgressCallback progressCallback = (RequestBatch.OnProgressCallback) callback;
                    if (callbackHandler == null) {
                        progressCallback.onBatchProgress(this.requests, this.batchProgress, this.maxProgress);
                    } else {
                        callbackHandler.post(new Runnable() {
                            public void run() {
                                progressCallback.onBatchProgress(ProgressOutputStream.this.requests, ProgressOutputStream.this.batchProgress, ProgressOutputStream.this.maxProgress);
                            }
                        });
                    }
                }
            }
            this.lastReportedProgress = this.batchProgress;
        }
    }

    public void setCurrentRequest(Request request) {
        this.currentRequestProgress = request != null ? this.progressMap.get(request) : null;
    }

    /* access modifiers changed from: package-private */
    public long getBatchProgress() {
        return this.batchProgress;
    }

    /* access modifiers changed from: package-private */
    public long getMaxProgress() {
        return this.maxProgress;
    }

    public void write(byte[] buffer) throws IOException {
        this.out.write(buffer);
        addProgress((long) buffer.length);
    }

    public void write(byte[] buffer, int offset, int length) throws IOException {
        this.out.write(buffer, offset, length);
        addProgress((long) length);
    }

    public void write(int oneByte) throws IOException {
        this.out.write(oneByte);
        addProgress(1);
    }

    public void close() throws IOException {
        super.close();
        for (RequestProgress p : this.progressMap.values()) {
            p.reportProgress();
        }
        reportBatchProgress();
    }
}
