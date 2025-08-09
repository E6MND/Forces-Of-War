package com.amazon.inapp.purchasing;

import android.content.Intent;
import android.os.Bundle;
import com.sponsorpay.sdk.android.UrlBuilder;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

final class SandboxRequestHandler implements RequestHandler {
    private static final String TAG = "SandboxRequestHandler";

    SandboxRequestHandler() {
    }

    public void sendContentDownloadRequest(String str, String str2, String str3) {
    }

    public void sendGetUserIdRequest(String str) {
        if (Logger.isTraceOn()) {
            Logger.trace(TAG, "sendGetUserIdRequest");
        }
        try {
            Bundle bundle = new Bundle();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("requestId", str);
            jSONObject.put("packageName", PurchasingManager.getObserverContext().getPackageName());
            bundle.putString("userInput", jSONObject.toString());
            Intent intent = new Intent("com.amazon.testclient.iap.appUserId");
            intent.addFlags(268435456);
            intent.putExtras(bundle);
            PurchasingManager.getObserverContext().startService(intent);
        } catch (JSONException e) {
            Logger.error(TAG, "Error in sendGetUserIdRequest.");
        }
    }

    public void sendItemDataRequest(Set<String> set, String str) {
        if (Logger.isTraceOn()) {
            Logger.trace(TAG, "sendItemDataRequest");
        }
        try {
            Bundle bundle = new Bundle();
            JSONObject jSONObject = new JSONObject();
            JSONArray jSONArray = new JSONArray(set);
            jSONObject.put("requestId", str);
            jSONObject.put("packageName", PurchasingManager.getObserverContext().getPackageName());
            jSONObject.put("skus", jSONArray);
            bundle.putString("itemDataInput", jSONObject.toString());
            Intent intent = new Intent("com.amazon.testclient.iap.itemData");
            intent.addFlags(268435456);
            intent.putExtras(bundle);
            PurchasingManager.getObserverContext().startService(intent);
        } catch (JSONException e) {
            Logger.error(TAG, "Error in sendItemDataRequest.");
        }
    }

    public void sendPurchaseRequest(String str, String str2) {
        if (Logger.isTraceOn()) {
            Logger.trace(TAG, "sendPurchaseRequest");
        }
        try {
            Bundle bundle = new Bundle();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("sku", str);
            jSONObject.put("requestId", str2);
            jSONObject.put("packageName", PurchasingManager.getObserverContext().getPackageName());
            bundle.putString("purchaseInput", jSONObject.toString());
            Intent intent = new Intent("com.amazon.testclient.iap.purchase");
            intent.addFlags(268435456);
            intent.putExtras(bundle);
            PurchasingManager.getObserverContext().startService(intent);
        } catch (JSONException e) {
            Logger.error(TAG, "Error in sendPurchaseRequest.");
        }
    }

    public void sendPurchaseResponseReceivedRequest(String str) {
    }

    public void sendPurchaseUpdatesRequest(Offset offset, String str) {
        if (Logger.isTraceOn()) {
            Logger.trace(TAG, "sendPurchaseUpdatesRequest");
        }
        try {
            Bundle bundle = new Bundle();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("requestId", str);
            jSONObject.put("packageName", PurchasingManager.getObserverContext().getPackageName());
            jSONObject.put(UrlBuilder.URL_PARAM_OFFSET_KEY, offset.toString());
            bundle.putString("purchaseUpdatesInput", jSONObject.toString());
            Intent intent = new Intent("com.amazon.testclient.iap.purchaseUpdates");
            intent.addFlags(268435456);
            intent.putExtras(bundle);
            PurchasingManager.getObserverContext().startService(intent);
        } catch (JSONException e) {
            Logger.error(TAG, "Error in sendPurchaseUpdatesRequest.");
        }
    }
}
