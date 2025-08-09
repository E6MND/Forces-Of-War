package com.amazon.inapp.purchasing;

import java.util.Set;

final class ItemDataRequest extends Request {
    /* access modifiers changed from: private */
    public final Set<String> _skus;

    ItemDataRequest(Set<String> set) {
        Validator.validateNotNull(set, "skus");
        Validator.validateNotEmpty(set, "skus");
        for (String trim : set) {
            if (trim.trim().length() == 0) {
                throw new IllegalArgumentException("Empty SKU values are not allowed");
            }
        }
        if (set.size() > 100) {
            throw new IllegalArgumentException(set.size() + " SKUs were provided, but no more than " + 100 + " SKUs are allowed");
        }
        this._skus = set;
    }

    /* access modifiers changed from: package-private */
    public Runnable getRunnable() {
        return new Runnable() {
            public void run() {
                ImplementationFactory.getRequestHandler().sendItemDataRequest(ItemDataRequest.this._skus, ItemDataRequest.this.getRequestId());
            }
        };
    }
}
