package com.sponsorpay.sdk.android.publisher;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public abstract class OfferWebClient extends WebViewClient {
    private static final String EXIT_URL_RESULT_CODE_PARAM_KEY = "status";
    private static final String EXIT_URL_TARGET_URL_PARAM_KEY = "url";
    public static final String LOG_TAG = "OfferWebClient";
    public static final int RESULT_CODE_NO_STATUS_CODE = -10;
    private static final String SPONSORPAY_EXIT_SCHEMA = "sponsorpay://exit";

    /* access modifiers changed from: protected */
    public abstract void onSponsorPayExitScheme(int i, String str);

    protected static String parseSponsorPayExitUrlForTargetUrl(String url) {
        String targetUrl = Uri.parse(url).getQueryParameter("url");
        if (targetUrl != null) {
            return Uri.decode(targetUrl);
        }
        return null;
    }

    protected static int parseSponsorPayExitUrlForResultCode(String url) {
        String resultCodeAsString = Uri.parse(url).getQueryParameter("status");
        if (resultCodeAsString != null) {
            return Integer.parseInt(resultCodeAsString);
        }
        return -10;
    }

    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        Log.i(LOG_TAG, "shouldOverrideUrlLoading called with url: " + url);
        if (url.startsWith(SPONSORPAY_EXIT_SCHEMA)) {
            int resultCode = parseSponsorPayExitUrlForResultCode(url);
            String targetUrl = parseSponsorPayExitUrlForTargetUrl(url);
            Log.i(LOG_TAG, "Overriding. Target Url: " + targetUrl);
            onSponsorPayExitScheme(resultCode, targetUrl);
            return true;
        }
        Log.i(LOG_TAG, "Not overriding");
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean launchActivityWithUrl(Activity launcherActivity, String url) {
        if (url == null) {
            return false;
        }
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setData(Uri.parse(url));
        launcherActivity.startActivity(intent);
        return true;
    }
}
