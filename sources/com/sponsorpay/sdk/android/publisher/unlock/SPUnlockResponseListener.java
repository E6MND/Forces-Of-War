package com.sponsorpay.sdk.android.publisher.unlock;

import com.sponsorpay.sdk.android.publisher.AbstractResponse;

public interface SPUnlockResponseListener {
    void onSPUnlockItemsStatusResponseReceived(UnlockedItemsResponse unlockedItemsResponse);

    void onSPUnlockRequestError(AbstractResponse abstractResponse);
}
