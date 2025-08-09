package com.sponsorpay.sdk.android.publisher;

import android.os.AsyncTask;
import android.util.Log;
import com.sponsorpay.sdk.android.HttpResponseParser;
import java.util.Locale;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;

public class AsyncRequest extends AsyncTask<Void, Void, Void> {
    private static String ACCEPT_LANGUAGE_HEADER_NAME = "Accept-Language";
    public static String LOG_TAG = "AsyncRequest";
    private static final String SIGNATURE_HEADER = "X-Sponsorpay-Response-Signature";
    private static String USER_AGENT_HEADER_NAME = "User-Agent";
    private static String USER_AGENT_HEADER_VALUE = "Android";
    public static boolean shouldLogVerbosely = false;
    private String[] mCookieStrings;
    private String mRequestUrl;
    private String mResponseBody;
    private String mResponseSignature;
    private AsyncRequestResultListener mResultListener;
    private int mStatusCode;
    private Throwable mThrownRequestError;

    public interface AsyncRequestResultListener {
        void onAsyncRequestComplete(AsyncRequest asyncRequest);
    }

    public AsyncRequest(String requestUrl, AsyncRequestResultListener listener) {
        this.mRequestUrl = requestUrl;
        this.mResultListener = listener;
    }

    /* access modifiers changed from: protected */
    public Void doInBackground(Void... params) {
        HttpUriRequest request = new HttpGet(this.mRequestUrl);
        request.addHeader(USER_AGENT_HEADER_NAME, USER_AGENT_HEADER_VALUE);
        String acceptLanguageHeaderValue = makeAcceptLanguageHeaderValue();
        if (shouldLogVerbosely) {
            Log.i(getClass().getSimpleName(), "acceptLanguageHeaderValue: " + acceptLanguageHeaderValue);
        }
        request.addHeader(ACCEPT_LANGUAGE_HEADER_NAME, acceptLanguageHeaderValue);
        HttpClient client = new DefaultHttpClient();
        this.mThrownRequestError = null;
        try {
            HttpResponse response = client.execute(request);
            this.mStatusCode = response.getStatusLine().getStatusCode();
            this.mResponseBody = HttpResponseParser.extractResponseString(response);
            Header[] responseSignatureHeaders = response.getHeaders(SIGNATURE_HEADER);
            this.mResponseSignature = responseSignatureHeaders.length > 0 ? responseSignatureHeaders[0].getValue() : "";
            Header[] cookieHeaders = response.getHeaders("Set-Cookie");
            if (cookieHeaders.length > 0) {
                if (shouldLogVerbosely) {
                    Log.v(LOG_TAG, String.format("Got following cookies from server (url: %s):", new Object[]{this.mRequestUrl}));
                }
                this.mCookieStrings = new String[cookieHeaders.length];
                for (int i = 0; i < cookieHeaders.length; i++) {
                    this.mCookieStrings[i] = cookieHeaders[i].getValue();
                    if (shouldLogVerbosely) {
                        Log.v(LOG_TAG, this.mCookieStrings[i]);
                    }
                }
            }
        } catch (Throwable t) {
            Log.e(LOG_TAG, "Exception triggered when executing request: " + t);
            this.mThrownRequestError = t;
        }
        return null;
    }

    private String makeAcceptLanguageHeaderValue() {
        String preferredLanguage = Locale.getDefault().getLanguage();
        String acceptLanguageLocaleValue = preferredLanguage;
        String englishLanguageCode = Locale.ENGLISH.getLanguage();
        if (preferredLanguage == null || preferredLanguage.equals("")) {
            return englishLanguageCode;
        }
        if (englishLanguageCode.equals(preferredLanguage)) {
            return acceptLanguageLocaleValue;
        }
        return String.valueOf(acceptLanguageLocaleValue) + String.format(", %s;q=0.8", new Object[]{englishLanguageCode});
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(Void result) {
        super.onPostExecute(result);
        this.mResultListener.onAsyncRequestComplete(this);
    }

    public String[] getCookieStrings() {
        return this.mCookieStrings;
    }

    public boolean hasCookies() {
        Boolean retval;
        if (this.mCookieStrings == null || this.mCookieStrings.length == 0) {
            retval = false;
        } else {
            String firstCookieString = this.mCookieStrings[0];
            if (firstCookieString == null || "".equals(firstCookieString)) {
                retval = false;
            } else {
                retval = true;
            }
        }
        return retval.booleanValue();
    }

    public String getResponseBody() {
        return this.mResponseBody;
    }

    public int getHttpStatusCode() {
        return this.mStatusCode;
    }

    public String getResponseSignature() {
        return this.mResponseSignature;
    }

    public boolean didRequestThrowError() {
        return this.mThrownRequestError != null;
    }

    public Throwable getRequestThrownError() {
        return this.mThrownRequestError;
    }

    public boolean hasSucessfulStatusCode() {
        return this.mStatusCode >= 200 && this.mStatusCode < 400;
    }
}
