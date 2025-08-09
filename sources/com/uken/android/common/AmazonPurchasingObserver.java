package com.uken.android.common;

import android.os.AsyncTask;
import android.util.Log;
import com.amazon.inapp.purchasing.BasePurchasingObserver;
import com.amazon.inapp.purchasing.GetUserIdResponse;
import com.amazon.inapp.purchasing.ItemDataResponse;
import com.amazon.inapp.purchasing.PurchaseResponse;
import com.amazon.inapp.purchasing.PurchaseUpdatesResponse;
import com.amazon.inapp.purchasing.PurchasingManager;
import com.amazon.inapp.purchasing.Receipt;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

public class AmazonPurchasingObserver extends BasePurchasingObserver {
    public static String AMAZON_TREASURER_URL = null;
    public static String GAME_NAME_CONSTANT = null;
    private static final String TAG = "Amazon-IAP";
    /* access modifiers changed from: private */
    public final UkenActivity activity;
    /* access modifiers changed from: private */
    public String userId;

    public AmazonPurchasingObserver(UkenActivity ukenActivity) {
        super(ukenActivity);
        this.activity = ukenActivity;
    }

    public void onSdkAvailable(boolean isSandboxMode) {
        Log.v(TAG, "onSdkAvailable recieved: Response -" + isSandboxMode);
        PurchasingManager.initiateGetUserIdRequest();
    }

    public void onGetUserIdResponse(GetUserIdResponse getUserIdResponse) {
        Log.v(TAG, "onGetUserIdResponse recieved: Response -" + getUserIdResponse);
        Log.v(TAG, "RequestId:" + getUserIdResponse.getRequestId());
        Log.v(TAG, "IdRequestStatus:" + getUserIdResponse.getUserIdRequestStatus());
        new GetUserIdAsyncTask().execute(new GetUserIdResponse[]{getUserIdResponse});
    }

    public void onItemDataResponse(ItemDataResponse itemDataResponse) {
        Log.v(TAG, "onItemDataResponse recieved");
        Log.v(TAG, "ItemDataRequestStatus" + itemDataResponse.getItemDataRequestStatus());
        Log.v(TAG, "ItemDataRequestId" + itemDataResponse.getRequestId());
    }

    public void onPurchaseResponse(final PurchaseResponse purchaseResponse) {
        new Thread(new Runnable() {
            public void run() {
                Log.v(AmazonPurchasingObserver.TAG, "onPurchaseResponse recieved");
                Log.v(AmazonPurchasingObserver.TAG, "PurchaseRequestStatus:" + purchaseResponse.getPurchaseRequestStatus());
                if (purchaseResponse.getReceipt() != null) {
                    Log.v(AmazonPurchasingObserver.TAG, "PurchaseRequestSKU:" + purchaseResponse.getReceipt().getSku());
                    HttpClient httpclient = new DefaultHttpClient();
                    httpclient.getParams().setParameter("http.useragent", System.getProperty("http.agent"));
                    HttpPost httppost = new HttpPost(AmazonPurchasingObserver.AMAZON_TREASURER_URL);
                    String udid = UkenUuid.getUdid(AmazonPurchasingObserver.this.activity);
                    try {
                        List<NameValuePair> nameValuePairs = new ArrayList<>();
                        nameValuePairs.add(new BasicNameValuePair("app", AmazonPurchasingObserver.GAME_NAME_CONSTANT));
                        nameValuePairs.add(new BasicNameValuePair("pid", purchaseResponse.getReceipt().getSku()));
                        nameValuePairs.add(new BasicNameValuePair("requestStatus", purchaseResponse.getPurchaseRequestStatus().toString()));
                        nameValuePairs.add(new BasicNameValuePair("udid", udid));
                        nameValuePairs.add(new BasicNameValuePair("purchaseToken", purchaseResponse.getReceipt().getPurchaseToken()));
                        nameValuePairs.add(new BasicNameValuePair("alt_device_ids", AmazonPurchasingObserver.this.getDeviceIdentifiersJson()));
                        if (AmazonPurchasingObserver.this.userId != null) {
                            nameValuePairs.add(new BasicNameValuePair("amazonUserId", AmazonPurchasingObserver.this.userId));
                        }
                        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                        httpclient.execute(httppost);
                    } catch (ClientProtocolException e) {
                        Utils.logCrashlytics(e);
                    } catch (IOException e2) {
                        Utils.logCrashlytics(e2);
                    }
                }
            }
        }).start();
    }

    public void onPurchaseUpdatesResponse(PurchaseUpdatesResponse purchaseUpdatesResponse) {
        Log.v(TAG, "onPurchaseUpdatesRecived recieved: Response -" + purchaseUpdatesResponse);
        Log.v(TAG, "PurchaseUpdatesRequestStatus:" + purchaseUpdatesResponse.getPurchaseUpdatesRequestStatus());
        Log.v(TAG, "RequestID:" + purchaseUpdatesResponse.getRequestId());
    }

    private void printReceipt(Receipt receipt) {
        Log.v(TAG, String.format("Receipt: ItemType: %s Sku: %s SubscriptionPeriod: %s", new Object[]{receipt.getItemType(), receipt.getSku(), receipt.getSubscriptionPeriod()}));
    }

    private String getKey(String sku) {
        Log.v(TAG, "SKU: " + sku);
        return sku;
    }

    /* access modifiers changed from: private */
    public String getDeviceIdentifiersJson() {
        String androidId = UkenUuid.getAndroidId(this.activity);
        String uuid = UkenUuid.getUuid(this.activity);
        String phoneId = DeviceInfo.devicePhoneIdentifier(this.activity);
        JSONObject alt_device_ids = new JSONObject();
        try {
            alt_device_ids.put("android_id", androidId);
            alt_device_ids.put("uuid", uuid);
            alt_device_ids.put("phone_id", phoneId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return alt_device_ids.toString();
    }

    private class GetUserIdAsyncTask extends AsyncTask<GetUserIdResponse, Void, Boolean> {
        private GetUserIdAsyncTask() {
        }

        /* access modifiers changed from: protected */
        public Boolean doInBackground(GetUserIdResponse... params) {
            GetUserIdResponse getUserIdResponse = params[0];
            if (getUserIdResponse.getUserIdRequestStatus() == GetUserIdResponse.GetUserIdRequestStatus.SUCCESSFUL) {
                String unused = AmazonPurchasingObserver.this.userId = getUserIdResponse.getUserId();
                return true;
            }
            Log.v(AmazonPurchasingObserver.TAG, "onGetUserIdResponse: Unable to get user ID.");
            return false;
        }
    }
}
