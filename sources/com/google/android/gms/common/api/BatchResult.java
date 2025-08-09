package com.google.android.gms.common.api;

import com.google.android.gms.internal.hn;
import java.util.concurrent.TimeUnit;

public final class BatchResult implements Result {
    private final PendingResult<?>[] Dz;
    private final Status yw;

    BatchResult(Status status, PendingResult<?>[] pendingResults) {
        this.yw = status;
        this.Dz = pendingResults;
    }

    public Status getStatus() {
        return this.yw;
    }

    public <R extends Result> R take(BatchResultToken<R> resultToken) {
        hn.b(resultToken.mId < this.Dz.length, (Object) "The result token does not belong to this batch");
        return this.Dz[resultToken.mId].await(0, TimeUnit.MILLISECONDS);
    }
}
