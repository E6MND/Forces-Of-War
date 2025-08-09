package com.amazon.inapp.purchasing;

import android.content.Context;

public class BasePurchasingObserver extends PurchasingObserver {
    public BasePurchasingObserver(Context context) {
        super(context);
    }

    public void onGetUserIdResponse(GetUserIdResponse getUserIdResponse) {
    }

    public void onItemDataResponse(ItemDataResponse itemDataResponse) {
    }

    public void onPurchaseResponse(PurchaseResponse purchaseResponse) {
    }

    public void onPurchaseUpdatesResponse(PurchaseUpdatesResponse purchaseUpdatesResponse) {
    }

    public void onSdkAvailable(boolean z) {
    }
}
