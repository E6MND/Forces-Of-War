package com.uken.android.common;

import android.util.Log;
import android.webkit.JavascriptInterface;
import com.facebook.AppEventsLogger;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.uken.android.common.UkenActivity;
import com.uken.android.common.UkenApplication;
import com.uken.android.util.IabHelper;
import com.uken.android.util.Purchase;
import com.uken.android.util.SkuDetails;
import com.uken.android.util.TreasurerHelper;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Currency;
import java.util.Map;

public class AndroidMarket {
    private static String TAG = "AndroidMarket";
    private UkenActivity mActivity;
    private IabHelper mHelper;
    private TreasurerHelper mTreasurerHelper;

    public AndroidMarket(UkenActivity activity, IabHelper iabHelper) {
        this.mTreasurerHelper = new TreasurerHelper(activity);
        this.mActivity = activity;
        this.mHelper = iabHelper;
    }

    @JavascriptInterface
    public void purchase(String pid) {
        if (this.mActivity.buildType != UkenActivity.UkenBuild.Production) {
        }
        if (this.mHelper.checkSetupDone()) {
            this.mHelper.launchPurchaseFlow(this.mActivity, pid, 9164619, new GooglePlayPurchasingObserver(this.mHelper, this.mTreasurerHelper, this.mActivity), (String) null);
            return;
        }
        this.mActivity.showUnsupportedBillingApiDialog();
    }

    public void fbLogPurchase(Purchase purchase) {
        try {
            debug("Log purchase with FB. Purchase: " + purchase);
            String sku = purchase.getSku();
            SkuDetails details = this.mHelper.queryInventory(true, Arrays.asList(new String[]{sku})).getSkuDetails(sku);
            debug("Log purchase with FB. Details: " + details);
            AppEventsLogger.newLogger(this.mActivity).logPurchase(BigDecimal.valueOf(details.getPriceAmount()), Currency.getInstance(details.getPriceCurrencyCode()));
        } catch (Exception e) {
            Utils.logCrashlytics(e);
        }
    }

    public void gaLogPurchase(Purchase purchase) {
        try {
            debug("Log purchase with GA. Purchase: " + purchase);
            String sku = purchase.getSku();
            SkuDetails details = this.mHelper.queryInventory(true, Arrays.asList(new String[]{sku})).getSkuDetails(sku);
            debug("Log purchase with GA. Details: " + details);
            sendDataToTwoTrackers(new HitBuilders.TransactionBuilder().setTransactionId(purchase.getOrderId()).setAffiliation(details.getType()).setRevenue(details.getTotalPrice()).setTax(details.getTaxAmount()).setShipping(0.0d).setCurrencyCode("USD").build());
            sendDataToTwoTrackers(new HitBuilders.ItemBuilder().setTransactionId(purchase.getOrderId()).setName(details.getTitle()).setSku(sku).setCategory(details.getType()).setPrice(details.getPriceAmount()).setQuantity(1).setCurrencyCode(details.getPriceCurrencyCode()).build());
        } catch (Exception e) {
            Utils.logCrashlytics(e);
        }
    }

    private void sendDataToTwoTrackers(Map<String, String> params) {
        UkenApplication ukenApplication = (UkenApplication) this.mActivity.getApplication();
        Tracker appTracker = ukenApplication.getTracker(UkenApplication.TrackerName.APP_TRACKER);
        Tracker ecommerceTracker = ukenApplication.getTracker(UkenApplication.TrackerName.ECOMMERCE_TRACKER);
        appTracker.send(params);
        ecommerceTracker.send(params);
    }

    private void debug(String message) {
        if (Consts.DEBUG) {
            Log.d(TAG, message);
        }
    }
}
