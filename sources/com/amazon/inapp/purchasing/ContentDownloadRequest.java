package com.amazon.inapp.purchasing;

final class ContentDownloadRequest extends Request {
    /* access modifiers changed from: private */
    public final String _location;
    /* access modifiers changed from: private */
    public final String _sku;

    ContentDownloadRequest(String str, String str2) {
        Validator.validateNotNull(str, "sku");
        Validator.validateNotNull(str2, "location");
        this._sku = str;
        this._location = str2;
    }

    /* access modifiers changed from: package-private */
    public Runnable getRunnable() {
        return new Runnable() {
            public void run() {
                ImplementationFactory.getRequestHandler().sendContentDownloadRequest(ContentDownloadRequest.this._sku, ContentDownloadRequest.this._location, ContentDownloadRequest.this.getRequestId());
            }
        };
    }
}
