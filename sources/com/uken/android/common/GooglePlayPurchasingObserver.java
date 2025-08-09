package com.uken.android.common;

import android.util.Log;
import com.uken.android.util.IabHelper;
import com.uken.android.util.IabResult;
import com.uken.android.util.OnFinalizePaymentFinishedListener;
import com.uken.android.util.Purchase;
import com.uken.android.util.TreasurerHelper;

public class GooglePlayPurchasingObserver implements IabHelper.OnIabPurchaseFinishedListener, IabHelper.OnConsumeFinishedListener {
    private static final String TAG = "GooglePlayPurchaseFinishedListener";
    /* access modifiers changed from: private */
    public final UkenActivity mActivity;
    /* access modifiers changed from: private */
    public final IabHelper mHelper;
    private final TreasurerHelper mTreasurerHelper;

    public GooglePlayPurchasingObserver(IabHelper helper, TreasurerHelper treasurerHelper, UkenActivity activity) {
        this.mHelper = helper;
        this.mTreasurerHelper = treasurerHelper;
        this.mActivity = activity;
    }

    public void onConsumeFinished(Purchase purchase, IabResult result) {
        remoteLog("Consumption finished. Purchase: " + purchase + ", result: " + result);
        if (this.mHelper == null) {
            remoteLog("onConsumeFinished mHelper was disposed, return");
        } else if (result.isSuccess()) {
            remoteLog("Consumption successful. Provisioning.");
        }
    }

    public void onIabPurchaseFinished(IabResult result, final Purchase purchase) {
        remoteLog("Purchase finished: " + result + ", purchase: " + purchase);
        if (this.mHelper == null) {
            remoteLog("onIabPurchaseFinished mHelper was disposed, return");
            return;
        }
        this.mTreasurerHelper.finalizePaymentAsync(purchase, new OnFinalizePaymentFinishedListener() {
            public void onFinalizePaymentFinished() {
                GooglePlayPurchasingObserver.this.remoteLog("Purchase successful.");
                GooglePlayPurchasingObserver.this.mHelper.consumeAsync(purchase, (IabHelper.OnConsumeFinishedListener) GooglePlayPurchasingObserver.this);
                GooglePlayPurchasingObserver.this.mActivity.logPurchases(purchase);
            }
        });
        if (result.isFailure()) {
            remoteLog("Error purchasing: " + result);
        }
    }

    /* access modifiers changed from: private */
    public void remoteLog(String message) {
        remoteLog(message, (Exception) null);
    }

    private void remoteLog(String message, Exception e) {
        Utils.logUkenEvent(TAG, message, e);
        if (Consts.DEBUG) {
            Log.d(TAG, message);
        }
    }
}
