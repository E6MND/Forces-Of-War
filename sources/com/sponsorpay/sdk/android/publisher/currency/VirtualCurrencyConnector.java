package com.sponsorpay.sdk.android.publisher.currency;

import android.content.Context;
import android.util.Log;
import com.sponsorpay.sdk.android.HostInfo;
import com.sponsorpay.sdk.android.UrlBuilder;
import com.sponsorpay.sdk.android.publisher.AbstractConnector;
import com.sponsorpay.sdk.android.publisher.AsyncRequest;
import com.sponsorpay.sdk.android.publisher.SponsorPayPublisher;
import java.util.Map;

public class VirtualCurrencyConnector extends AbstractConnector implements SPCurrencyServerListener {
    private static final String CURRENCY_DELTA_REQUEST_RESOURCE = "new_credit.json";
    private static final String STATE_LATEST_TRANSACTION_ID_KEY_PREFIX = "STATE_LATEST_CURRENCY_TRANSACTION_ID_";
    private static final String STATE_LATEST_TRANSACTION_ID_KEY_SEPARATOR = "_";
    private static final String URL_PARAM_KEY_LAST_TRANSACTION_ID = "ltid";
    private static final String URL_PARAM_VALUE_NO_TRANSACTION = "NO_TRANSACTION";
    private static final String VIRTUAL_CURRENCY_SERVER_PRODUCTION_BASE_URL = "http://api.sponsorpay.com/vcs/v1/";
    private static final String VIRTUAL_CURRENCY_SERVER_STAGING_BASE_URL = "http://staging.iframe.sponsorpay.com/vcs/v1/";
    private SPCurrencyServerListener mUserListener;

    public enum RequestType {
        DELTA_COINS
    }

    private class CurrencyServerRequestAsyncTask extends AsyncRequest {
        public RequestType requestType;

        public CurrencyServerRequestAsyncTask(RequestType requestType2, String requestUrl, AsyncRequest.AsyncRequestResultListener listener) {
            super(requestUrl, listener);
            this.requestType = requestType2;
        }
    }

    public VirtualCurrencyConnector(Context context, String userId, SPCurrencyServerListener userListener, HostInfo hostInfo, String securityToken) {
        super(context, userId, hostInfo, securityToken);
        this.mUserListener = userListener;
    }

    public void fetchDeltaOfCoins() {
        fetchDeltaOfCoinsForCurrentUserSinceTransactionId(fetchLatestTransactionIdForCurrentAppAndUser());
    }

    public void fetchDeltaOfCoinsForCurrentUserSinceTransactionId(String transactionId) {
        String baseUrl;
        Map<String, String> extraKeysValues = UrlBuilder.mapKeysToValues(new String[]{URL_PARAM_KEY_LAST_TRANSACTION_ID, "timestamp"}, new String[]{transactionId, getCurrentUnixTimestampAsString()});
        if (this.mCustomParameters != null) {
            extraKeysValues.putAll(this.mCustomParameters);
        }
        if (SponsorPayPublisher.shouldUseStagingUrls()) {
            baseUrl = VIRTUAL_CURRENCY_SERVER_STAGING_BASE_URL;
        } else {
            baseUrl = VIRTUAL_CURRENCY_SERVER_PRODUCTION_BASE_URL;
        }
        String requestUrl = UrlBuilder.buildUrl(String.valueOf(baseUrl) + CURRENCY_DELTA_REQUEST_RESOURCE, this.mUserId.toString(), this.mHostInfo, extraKeysValues, this.mSecurityToken);
        Log.d(getClass().getSimpleName(), "Delta of coins request will be sent to URL + params: " + requestUrl);
        new CurrencyServerRequestAsyncTask(RequestType.DELTA_COINS, requestUrl, this).execute(new Void[0]);
    }

    public void onAsyncRequestComplete(AsyncRequest request) {
        CurrencyServerAbstractResponse response;
        CurrencyServerRequestAsyncTask requestTask = (CurrencyServerRequestAsyncTask) request;
        Log.d(getClass().getSimpleName(), String.format("Currency Server Response, status code: %d, response body: %s, signature: %s", new Object[]{Integer.valueOf(requestTask.getHttpStatusCode()), requestTask.getResponseBody(), requestTask.getResponseSignature()}));
        if (requestTask.didRequestThrowError()) {
            response = new RequestErrorResponse();
        } else {
            response = CurrencyServerAbstractResponse.getParsingInstance(requestTask.requestType);
            response.setResponseData(requestTask.getHttpStatusCode(), requestTask.getResponseBody(), requestTask.getResponseSignature());
        }
        response.setResponseListener(this);
        response.parseAndCallListener(this.mSecurityToken);
    }

    private void saveLatestTransactionIdForCurrentUser(String transactionId) {
        this.mContext.getSharedPreferences(SponsorPayPublisher.PREFERENCES_FILENAME, 0).edit().putString(generatePreferencesLatestTransactionIdKey(this.mUserId.toString(), this.mHostInfo.getAppId()), transactionId).commit();
    }

    private static String generatePreferencesLatestTransactionIdKey(String appId, String userId) {
        return STATE_LATEST_TRANSACTION_ID_KEY_PREFIX + appId + STATE_LATEST_TRANSACTION_ID_KEY_SEPARATOR + userId;
    }

    private String fetchLatestTransactionIdForCurrentAppAndUser() {
        return fetchLatestTransactionId(this.mContext, this.mHostInfo.getAppId(), this.mUserId.toString());
    }

    public static String fetchLatestTransactionId(Context context, String appId, String userId) {
        return context.getSharedPreferences(SponsorPayPublisher.PREFERENCES_FILENAME, 0).getString(generatePreferencesLatestTransactionIdKey(userId, appId), URL_PARAM_VALUE_NO_TRANSACTION);
    }

    public void onSPCurrencyServerError(CurrencyServerAbstractResponse response) {
        this.mUserListener.onSPCurrencyServerError(response);
    }

    public void onSPCurrencyDeltaReceived(CurrencyServerDeltaOfCoinsResponse response) {
        saveLatestTransactionIdForCurrentUser(response.getLatestTransactionId());
        this.mUserListener.onSPCurrencyDeltaReceived(response);
    }
}
