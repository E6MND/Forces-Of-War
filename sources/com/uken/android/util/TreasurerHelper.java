package com.uken.android.util;

import android.content.pm.PackageManager;
import android.util.Log;
import com.uken.android.common.AuthenticationManager;
import com.uken.android.common.Consts;
import com.uken.android.common.DeviceInfo;
import com.uken.android.common.UkenActivity;
import com.uken.android.common.UkenUuid;
import com.uken.android.common.Utils;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

public class TreasurerHelper {
    public static String BASEURL;
    public static UkenActivity.UkenBuild BUILD_TYPE;
    public static String GAME_NAME_CONSTANT;
    private static String TAG = "TreasurerHelper";
    public static String TREASURER_URL;
    public UkenActivity mActivity;
    private int retryDelay = 0;
    private ScheduledFuture<?> retryPaymentTask = null;
    private final ScheduledExecutorService worker = Executors.newSingleThreadScheduledExecutor();

    public TreasurerHelper(UkenActivity activity) {
        this.mActivity = activity;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x00e6, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x00e7, code lost:
        remoteLog("Could not create payment", r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x00f7, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00f8, code lost:
        remoteLog("Could not create payment", r1);
        com.uken.android.common.Utils.logCrashlytics(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:?, code lost:
        return r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:?, code lost:
        return r7;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x00e6 A[ExcHandler: IOException (r1v2 'e' java.io.IOException A[CUSTOM_DECLARE]), PHI: r7 
      PHI: (r7v2 'orderId' java.lang.String) = (r7v0 'orderId' java.lang.String), (r7v0 'orderId' java.lang.String), (r7v3 'orderId' java.lang.String), (r7v3 'orderId' java.lang.String), (r7v4 'orderId' java.lang.String), (r7v4 'orderId' java.lang.String), (r7v5 'orderId' java.lang.String) binds: [B:4:0x004d, B:14:0x00aa, B:26:0x00f0, B:27:?, B:20:0x00e2, B:21:?, B:15:?] A[DONT_GENERATE, DONT_INLINE], Splitter:B:4:0x004d] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String createPayment(java.lang.String r15) {
        /*
            r14 = this;
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.lang.String r12 = "Creating treasurer payment with sku: "
            java.lang.StringBuilder r11 = r11.append(r12)
            java.lang.StringBuilder r11 = r11.append(r15)
            java.lang.String r11 = r11.toString()
            r14.remoteLog(r11)
            org.apache.http.impl.client.DefaultHttpClient r2 = new org.apache.http.impl.client.DefaultHttpClient
            r2.<init>()
            org.apache.http.params.HttpParams r11 = r2.getParams()
            java.lang.String r12 = "http.useragent"
            java.lang.String r13 = "http.agent"
            java.lang.String r13 = java.lang.System.getProperty(r13)
            r11.setParameter(r12, r13)
            org.apache.http.client.methods.HttpPost r3 = new org.apache.http.client.methods.HttpPost
            java.lang.String r11 = TREASURER_URL
            r3.<init>(r11)
            com.uken.android.common.UkenActivity r11 = r14.mActivity
            java.lang.String r9 = com.uken.android.common.UkenUuid.getUdid(r11)
            com.uken.android.common.UkenActivity r11 = r14.mActivity
            com.uken.android.common.AuthenticationManager r11 = com.uken.android.common.AuthenticationManager.getInstance(r11)
            java.lang.String r0 = r11.getCredentialToken()
            com.uken.android.common.UkenActivity r11 = r14.mActivity     // Catch:{ NameNotFoundException -> 0x00da }
            int r11 = com.uken.android.common.DeviceInfo.appVersionCode(r11)     // Catch:{ NameNotFoundException -> 0x00da }
            java.lang.String r10 = java.lang.Integer.toString(r11)     // Catch:{ NameNotFoundException -> 0x00da }
        L_0x004b:
            java.lang.String r7 = "0"
            java.util.ArrayList r6 = new java.util.ArrayList     // Catch:{ IOException -> 0x00e6, Exception -> 0x00f7 }
            r6.<init>()     // Catch:{ IOException -> 0x00e6, Exception -> 0x00f7 }
            org.apache.http.message.BasicNameValuePair r11 = new org.apache.http.message.BasicNameValuePair     // Catch:{ IOException -> 0x00e6, Exception -> 0x00f7 }
            java.lang.String r12 = "app"
            java.lang.String r13 = GAME_NAME_CONSTANT     // Catch:{ IOException -> 0x00e6, Exception -> 0x00f7 }
            r11.<init>(r12, r13)     // Catch:{ IOException -> 0x00e6, Exception -> 0x00f7 }
            r6.add(r11)     // Catch:{ IOException -> 0x00e6, Exception -> 0x00f7 }
            org.apache.http.message.BasicNameValuePair r11 = new org.apache.http.message.BasicNameValuePair     // Catch:{ IOException -> 0x00e6, Exception -> 0x00f7 }
            java.lang.String r12 = "pid"
            r11.<init>(r12, r15)     // Catch:{ IOException -> 0x00e6, Exception -> 0x00f7 }
            r6.add(r11)     // Catch:{ IOException -> 0x00e6, Exception -> 0x00f7 }
            org.apache.http.message.BasicNameValuePair r11 = new org.apache.http.message.BasicNameValuePair     // Catch:{ IOException -> 0x00e6, Exception -> 0x00f7 }
            java.lang.String r12 = "udid"
            r11.<init>(r12, r9)     // Catch:{ IOException -> 0x00e6, Exception -> 0x00f7 }
            r6.add(r11)     // Catch:{ IOException -> 0x00e6, Exception -> 0x00f7 }
            if (r0 == 0) goto L_0x0082
            java.lang.String r11 = ""
            if (r0 == r11) goto L_0x0082
            org.apache.http.message.BasicNameValuePair r11 = new org.apache.http.message.BasicNameValuePair     // Catch:{ IOException -> 0x00e6, Exception -> 0x00f7 }
            java.lang.String r12 = "credential_token"
            r11.<init>(r12, r0)     // Catch:{ IOException -> 0x00e6, Exception -> 0x00f7 }
            r6.add(r11)     // Catch:{ IOException -> 0x00e6, Exception -> 0x00f7 }
        L_0x0082:
            org.apache.http.message.BasicNameValuePair r11 = new org.apache.http.message.BasicNameValuePair     // Catch:{ IOException -> 0x00e6, Exception -> 0x00f7 }
            java.lang.String r12 = "android_app_version"
            r11.<init>(r12, r10)     // Catch:{ IOException -> 0x00e6, Exception -> 0x00f7 }
            r6.add(r11)     // Catch:{ IOException -> 0x00e6, Exception -> 0x00f7 }
            com.uken.android.common.UkenActivity$UkenBuild r11 = BUILD_TYPE     // Catch:{ IOException -> 0x00e6, Exception -> 0x00f7 }
            com.uken.android.common.UkenActivity$UkenBuild r12 = com.uken.android.common.UkenActivity.UkenBuild.Smoke     // Catch:{ IOException -> 0x00e6, Exception -> 0x00f7 }
            if (r11 != r12) goto L_0x009e
            org.apache.http.message.BasicNameValuePair r11 = new org.apache.http.message.BasicNameValuePair     // Catch:{ IOException -> 0x00e6, Exception -> 0x00f7 }
            java.lang.String r12 = "app_host"
            java.lang.String r13 = BASEURL     // Catch:{ IOException -> 0x00e6, Exception -> 0x00f7 }
            r11.<init>(r12, r13)     // Catch:{ IOException -> 0x00e6, Exception -> 0x00f7 }
            r6.add(r11)     // Catch:{ IOException -> 0x00e6, Exception -> 0x00f7 }
        L_0x009e:
            org.apache.http.client.entity.UrlEncodedFormEntity r11 = new org.apache.http.client.entity.UrlEncodedFormEntity     // Catch:{ IOException -> 0x00e6, Exception -> 0x00f7 }
            r11.<init>(r6)     // Catch:{ IOException -> 0x00e6, Exception -> 0x00f7 }
            r3.setEntity(r11)     // Catch:{ IOException -> 0x00e6, Exception -> 0x00f7 }
            org.apache.http.HttpResponse r8 = r2.execute(r3)     // Catch:{ IOException -> 0x00e6, Exception -> 0x00f7 }
            org.apache.http.HttpEntity r11 = r8.getEntity()     // Catch:{ JSONException -> 0x00df, Exception -> 0x00ed, IOException -> 0x00e6 }
            java.io.InputStream r11 = r11.getContent()     // Catch:{ JSONException -> 0x00df, Exception -> 0x00ed, IOException -> 0x00e6 }
            java.lang.String r5 = com.uken.android.common.Utils.parseJson(r11)     // Catch:{ JSONException -> 0x00df, Exception -> 0x00ed, IOException -> 0x00e6 }
            org.json.JSONObject r4 = new org.json.JSONObject     // Catch:{ JSONException -> 0x00df, Exception -> 0x00ed, IOException -> 0x00e6 }
            r4.<init>(r5)     // Catch:{ JSONException -> 0x00df, Exception -> 0x00ed, IOException -> 0x00e6 }
            java.lang.String r11 = "order_id"
            java.lang.String r12 = "0"
            java.lang.String r7 = r4.optString(r11, r12)     // Catch:{ JSONException -> 0x00df, Exception -> 0x00ed, IOException -> 0x00e6 }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x00df, Exception -> 0x00ed, IOException -> 0x00e6 }
            r11.<init>()     // Catch:{ JSONException -> 0x00df, Exception -> 0x00ed, IOException -> 0x00e6 }
            java.lang.String r12 = "order_id: "
            java.lang.StringBuilder r11 = r11.append(r12)     // Catch:{ JSONException -> 0x00df, Exception -> 0x00ed, IOException -> 0x00e6 }
            java.lang.StringBuilder r11 = r11.append(r7)     // Catch:{ JSONException -> 0x00df, Exception -> 0x00ed, IOException -> 0x00e6 }
            java.lang.String r11 = r11.toString()     // Catch:{ JSONException -> 0x00df, Exception -> 0x00ed, IOException -> 0x00e6 }
            r14.remoteLog(r11)     // Catch:{ JSONException -> 0x00df, Exception -> 0x00ed, IOException -> 0x00e6 }
        L_0x00d9:
            return r7
        L_0x00da:
            r1 = move-exception
            java.lang.String r10 = "(Default)"
            goto L_0x004b
        L_0x00df:
            r1 = move-exception
            java.lang.String r11 = "Could not parse json with order creation"
            r14.remoteLog(r11, r1)     // Catch:{ IOException -> 0x00e6, Exception -> 0x00f7 }
            goto L_0x00d9
        L_0x00e6:
            r1 = move-exception
            java.lang.String r11 = "Could not create payment"
            r14.remoteLog(r11, r1)
            goto L_0x00d9
        L_0x00ed:
            r1 = move-exception
            java.lang.String r11 = "Could not parse json with order creation"
            r14.remoteLog(r11, r1)     // Catch:{ IOException -> 0x00e6, Exception -> 0x00f7 }
            com.uken.android.common.Utils.logCrashlytics(r1)     // Catch:{ IOException -> 0x00e6, Exception -> 0x00f7 }
            goto L_0x00d9
        L_0x00f7:
            r1 = move-exception
            java.lang.String r11 = "Could not create payment"
            r14.remoteLog(r11, r1)
            com.uken.android.common.Utils.logCrashlytics(r1)
            goto L_0x00d9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.uken.android.util.TreasurerHelper.createPayment(java.lang.String):java.lang.String");
    }

    public void finalizePaymentAsync(final Purchase purchase, final OnFinalizePaymentFinishedListener listener) {
        remoteLog(String.format("Received payment info - purchase: %s", new Object[]{purchase}));
        new Thread(new Runnable() {
            public void run() {
                TreasurerHelper.this.finalizePayment(purchase, listener);
            }
        }).start();
    }

    /* access modifiers changed from: private */
    public void finalizePayment(Purchase purchase, OnFinalizePaymentFinishedListener listener) {
        String versionNumber;
        remoteLog(String.format("Finalizing treasurer payment - purchase: %s", new Object[]{purchase}));
        if (purchase != null) {
            String signedData = purchase.getOriginalJson();
            String signature = purchase.getSignature();
            String.format("finalizePayment - google_order_id: %s", new Object[]{purchase.getOrderId()});
            HttpClient httpclient = new DefaultHttpClient();
            httpclient.getParams().setParameter("http.useragent", System.getProperty("http.agent"));
            HttpPut httpput = new HttpPut(TREASURER_URL);
            String udid = UkenUuid.getUdid(this.mActivity);
            String credentialToken = AuthenticationManager.getInstance(this.mActivity).getCredentialToken();
            try {
                versionNumber = Integer.toString(DeviceInfo.appVersionCode(this.mActivity));
            } catch (PackageManager.NameNotFoundException e) {
                versionNumber = "(Default)";
            }
            try {
                remoteLog("finalizePayment - Setting up parameters");
                List<NameValuePair> nameValuePairs = new ArrayList<>();
                nameValuePairs.add(new BasicNameValuePair("app", GAME_NAME_CONSTANT));
                nameValuePairs.add(new BasicNameValuePair("signed_data", signedData));
                nameValuePairs.add(new BasicNameValuePair("signature", signature));
                nameValuePairs.add(new BasicNameValuePair("udid", udid));
                if (!(credentialToken == null || credentialToken == "")) {
                    nameValuePairs.add(new BasicNameValuePair("credential_token", credentialToken));
                }
                nameValuePairs.add(new BasicNameValuePair("android_app_version", versionNumber));
                nameValuePairs.add(new BasicNameValuePair("alt_device_ids", getDeviceIdentifiersJson()));
                if (BUILD_TYPE == UkenActivity.UkenBuild.Smoke) {
                    nameValuePairs.add(new BasicNameValuePair("app_host", BASEURL));
                }
                httpput.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                remoteLog("finalizePayment - Starting HTTP call with params: " + nameValuePairs.toString());
                HttpResponse response = httpclient.execute(httpput);
                remoteLog(String.format("finalizePayment - End HTTP call with status code = %d", new Object[]{Integer.valueOf(response.getStatusLine().getStatusCode())}));
                if (response.getStatusLine().getStatusCode() == 200) {
                    if (listener != null) {
                        final OnFinalizePaymentFinishedListener onFinalizePaymentFinishedListener = listener;
                        this.mActivity.runOnUiThread(new Runnable() {
                            public void run() {
                                onFinalizePaymentFinishedListener.onFinalizePaymentFinished();
                            }
                        });
                    }
                    this.retryDelay = 1;
                    return;
                }
                remoteLog("finalizePayment - Scheduling retry");
                retryPayments(purchase, listener);
            } catch (Exception e2) {
                remoteLog("finalizePayment - Exception, scheduling retry", e2);
                retryPayments(purchase, listener);
            }
        }
    }

    private void retryPayments(final Purchase purchase, final OnFinalizePaymentFinishedListener listener) {
        Runnable task = new Runnable() {
            public void run() {
                TreasurerHelper.this.finalizePayment(purchase, listener);
            }
        };
        if (this.retryDelay == 0) {
            this.retryDelay = 1;
        }
        this.retryDelay = Math.min(this.retryDelay * 2, 30);
        if (this.retryPaymentTask != null) {
            this.retryPaymentTask.cancel(true);
            this.retryPaymentTask = null;
            remoteLog("Retry payment cancelled");
        }
        this.retryPaymentTask = this.worker.schedule(task, (long) this.retryDelay, TimeUnit.SECONDS);
        remoteLog(String.format("retryPayments - scheduling done, retrying in %d seconds", new Object[]{Integer.valueOf(this.retryDelay)}));
    }

    private String getDeviceIdentifiersJson() {
        JSONObject alt_device_ids = new JSONObject();
        try {
            alt_device_ids.put("android_id", UkenUuid.getAndroidId(this.mActivity));
            alt_device_ids.put("uuid", UkenUuid.getUuid(this.mActivity));
            alt_device_ids.put("phone_id", DeviceInfo.devicePhoneIdentifier(this.mActivity));
        } catch (JSONException e) {
            remoteLog("Could not create device info json", e);
        }
        return alt_device_ids.toString();
    }

    private void remoteLog(String message) {
        remoteLog(message, (Exception) null);
    }

    private void remoteLog(String message, Exception e) {
        Utils.logUkenEvent(TAG, message, e);
        if (Consts.DEBUG) {
            Log.d(TAG, message);
        }
    }
}
