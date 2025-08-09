package com.amazon.inapp.purchasing;

final class GetUserIdRequest extends Request {
    GetUserIdRequest() {
    }

    /* access modifiers changed from: package-private */
    public Runnable getRunnable() {
        return new Runnable() {
            public void run() {
                ImplementationFactory.getRequestHandler().sendGetUserIdRequest(GetUserIdRequest.this.getRequestId());
            }
        };
    }
}
