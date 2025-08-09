package com.amazon.inapp.purchasing;

import com.sponsorpay.sdk.android.UrlBuilder;

final class PurchaseUpdatesRequest extends Request {
    /* access modifiers changed from: private */
    public final Offset _offset;

    PurchaseUpdatesRequest(Offset offset) {
        Validator.validateNotNull(offset, UrlBuilder.URL_PARAM_OFFSET_KEY);
        this._offset = offset;
    }

    /* access modifiers changed from: package-private */
    public Runnable getRunnable() {
        return new Runnable() {
            public void run() {
                ImplementationFactory.getRequestHandler().sendPurchaseUpdatesRequest(PurchaseUpdatesRequest.this._offset, PurchaseUpdatesRequest.this.getRequestId());
            }
        };
    }
}
