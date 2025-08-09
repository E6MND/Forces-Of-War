package com.amazon.inapp.purchasing;

class PurchaseRequest extends Request {
    /* access modifiers changed from: private */
    public final String _sku;

    PurchaseRequest(String str) {
        Validator.validateNotNull(str, "sku");
        this._sku = str;
    }

    /* access modifiers changed from: package-private */
    public Runnable getRunnable() {
        return new Runnable() {
            public void run() {
                ImplementationFactory.getRequestHandler().sendPurchaseRequest(PurchaseRequest.this._sku, PurchaseRequest.this.getRequestId());
            }
        };
    }
}
