package com.sponsorpay.sdk.android.publisher;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.webkit.WebView;
import com.sponsorpay.sdk.android.HostInfo;
import com.sponsorpay.sdk.android.UrlBuilder;
import com.sponsorpay.sdk.android.publisher.SponsorPayPublisher;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class OfferWallActivity extends Activity {
    public static final String EXTRA_KEYS_VALUES_MAP = "EXTRA_KEY_VALUES_MAP";
    public static final String EXTRA_OFFERWALL_TYPE = "EXTRA_OFFERWALL_TEMPLATE_KEY";
    public static final String EXTRA_OVERRIDEN_APP_ID = "EXTRA_OVERRIDEN_APP_ID";
    public static final String EXTRA_SHOULD_STAY_OPEN_KEY = "EXTRA_SHOULD_REMAIN_OPEN_KEY";
    public static final String EXTRA_USERID_KEY = "EXTRA_USERID_KEY";
    public static final String OFFERWALL_TYPE_MOBILE = "OFFERWALL_TYPE_MOBILE";
    public static final String OFFERWALL_TYPE_UNLOCK = "OFFERWALL_TYPE_UNLOCK";
    public static final int RESULT_CODE_NO_STATUS_CODE = -10;
    protected Map<String, String> mCustomKeysValues;
    /* access modifiers changed from: private */
    public AlertDialog mErrorDialog;
    protected HostInfo mHostInfo;
    /* access modifiers changed from: private */
    public ProgressDialog mProgressDialog;
    private boolean mShouldStayOpen;
    private OfferWallTemplate mTemplate;
    protected UserId mUserId;
    protected WebView mWebView;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(1);
        this.mProgressDialog = new ProgressDialog(this);
        this.mProgressDialog.setOwnerActivity(this);
        this.mProgressDialog.setIndeterminate(true);
        this.mProgressDialog.setMessage(SponsorPayPublisher.getUIString(SponsorPayPublisher.UIStringIdentifier.LOADING_OFFERWALL));
        this.mProgressDialog.show();
        this.mHostInfo = new HostInfo(getApplicationContext());
        instantiateTemplate();
        fetchPassedExtras();
        this.mWebView = new WebView(this);
        this.mWebView.setScrollBarStyle(0);
        setContentView(this.mWebView);
        this.mWebView.getSettings().setJavaScriptEnabled(true);
        this.mWebView.getSettings().setPluginsEnabled(true);
        this.mWebView.setWebViewClient(new ActivityOfferWebClient(this, this.mShouldStayOpen) {
            public void onPageFinished(WebView view, String url) {
                if (OfferWallActivity.this.mProgressDialog != null) {
                    OfferWallActivity.this.mProgressDialog.dismiss();
                    OfferWallActivity.this.mProgressDialog = null;
                }
                super.onPageFinished(view, url);
            }

            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                SponsorPayPublisher.UIStringIdentifier error;
                switch (errorCode) {
                    case -7:
                    case -2:
                        error = SponsorPayPublisher.UIStringIdentifier.ERROR_LOADING_OFFERWALL_NO_INTERNET_CONNECTION;
                        break;
                    default:
                        error = SponsorPayPublisher.UIStringIdentifier.ERROR_LOADING_OFFERWALL;
                        break;
                }
                OfferWallActivity.this.showErrorDialog(error);
            }
        });
    }

    private void instantiateTemplate() {
        if (OFFERWALL_TYPE_UNLOCK.equals(getIntent().getStringExtra(EXTRA_OFFERWALL_TYPE))) {
            this.mTemplate = new UnlockOfferWallTemplate();
        } else {
            this.mTemplate = new MobileOfferWallTemplate();
        }
    }

    /* access modifiers changed from: protected */
    public void fetchPassedExtras() {
        this.mUserId = UserId.make(getApplicationContext(), getIntent().getStringExtra(EXTRA_USERID_KEY));
        this.mShouldStayOpen = getIntent().getBooleanExtra("EXTRA_SHOULD_REMAIN_OPEN_KEY", this.mTemplate.shouldStayOpenByDefault());
        Serializable inflatedKvMap = getIntent().getSerializableExtra(EXTRA_KEYS_VALUES_MAP);
        if (inflatedKvMap instanceof HashMap) {
            this.mCustomKeysValues = (HashMap) inflatedKvMap;
        }
        String overridenAppId = getIntent().getStringExtra(EXTRA_OVERRIDEN_APP_ID);
        if (overridenAppId != null && !overridenAppId.equals("")) {
            this.mHostInfo.setOverriddenAppId(overridenAppId);
        }
        this.mTemplate.fetchAdditionalExtras();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        if (this.mErrorDialog != null) {
            this.mErrorDialog.dismiss();
            this.mErrorDialog = null;
        }
        if (this.mProgressDialog != null) {
            this.mProgressDialog.dismiss();
            this.mProgressDialog = null;
        }
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        try {
            String offerwallUrl = generateUrl();
            Log.d(getClass().getSimpleName(), "Offerwall request url: " + offerwallUrl);
            this.mWebView.loadUrl(offerwallUrl);
        } catch (RuntimeException ex) {
            showErrorDialog(ex.getMessage());
        }
    }

    private String generateUrl() {
        this.mCustomKeysValues = this.mTemplate.addAdditionalParameters(this.mCustomKeysValues);
        return UrlBuilder.buildUrl(this.mTemplate.getBaseUrl(), this.mUserId.toString(), this.mHostInfo, this.mCustomKeysValues, (String) null);
    }

    /* access modifiers changed from: protected */
    public void showErrorDialog(SponsorPayPublisher.UIStringIdentifier error) {
        showErrorDialog(SponsorPayPublisher.getUIString(error));
    }

    /* access modifiers changed from: protected */
    public void showErrorDialog(String error) {
        String errorMessage = error;
        String errorDialogTitle = SponsorPayPublisher.getUIString(SponsorPayPublisher.UIStringIdentifier.ERROR_DIALOG_TITLE);
        String dismissButtonCaption = SponsorPayPublisher.getUIString(SponsorPayPublisher.UIStringIdentifier.DISMISS_ERROR_DIALOG);
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle(errorDialogTitle);
        dialogBuilder.setMessage(errorMessage);
        dialogBuilder.setNegativeButton(dismissButtonCaption, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                OfferWallActivity.this.mErrorDialog = null;
                OfferWallActivity.this.finish();
            }
        });
        this.mErrorDialog = dialogBuilder.create();
        this.mErrorDialog.setOwnerActivity(this);
        try {
            this.mErrorDialog.show();
        } catch (WindowManager.BadTokenException e) {
            Log.e(getClass().getSimpleName(), "Couldn't show error dialog. Not displayed error message is: " + errorMessage, e);
        }
    }

    public abstract class OfferWallTemplate {
        public abstract Map<String, String> addAdditionalParameters(Map<String, String> map);

        public abstract void fetchAdditionalExtras();

        public abstract String getBaseUrl();

        public abstract boolean shouldStayOpenByDefault();

        public OfferWallTemplate() {
        }
    }

    public class MobileOfferWallTemplate extends OfferWallTemplate {
        private static final String OFFERWALL_PRODUCTION_BASE_URL = "http://iframe.sponsorpay.com/mobile?";
        private static final String OFFERWALL_STAGING_BASE_URL = "http://staging.iframe.sponsorpay.com/mobile?";

        public MobileOfferWallTemplate() {
            super();
        }

        public void fetchAdditionalExtras() {
        }

        public String getBaseUrl() {
            if (SponsorPayPublisher.shouldUseStagingUrls()) {
                return OFFERWALL_STAGING_BASE_URL;
            }
            return OFFERWALL_PRODUCTION_BASE_URL;
        }

        public Map<String, String> addAdditionalParameters(Map<String, String> params) {
            return params;
        }

        public boolean shouldStayOpenByDefault() {
            return true;
        }
    }

    public class UnlockOfferWallTemplate extends OfferWallTemplate {
        public static final String EXTRA_UNLOCK_ITEM_ID_KEY = "EXTRA_UNLOCK_ITEM_ID_KEY";
        public static final String PARAM_UNLOCK_ITEM_ID_KEY = "itemid";
        private static final String UNLOCK_OFFERWALL_PRODUCTION_BASE_URL = "http://iframe.sponsorpay.com/unlock?";
        private static final String UNLOCK_OFFERWALL_STAGING_BASE_URL = "http://staging.iframe.sponsorpay.com/unlock?";
        private String mUnlockItemId;

        public UnlockOfferWallTemplate() {
            super();
        }

        public void fetchAdditionalExtras() {
            this.mUnlockItemId = OfferWallActivity.this.getIntent().getStringExtra(EXTRA_UNLOCK_ITEM_ID_KEY);
        }

        public String getBaseUrl() {
            if (SponsorPayPublisher.shouldUseStagingUrls()) {
                return UNLOCK_OFFERWALL_STAGING_BASE_URL;
            }
            return UNLOCK_OFFERWALL_PRODUCTION_BASE_URL;
        }

        public Map<String, String> addAdditionalParameters(Map<String, String> params) {
            if (params == null) {
                params = new HashMap<>();
            }
            params.put(PARAM_UNLOCK_ITEM_ID_KEY, this.mUnlockItemId);
            return params;
        }

        public boolean shouldStayOpenByDefault() {
            return false;
        }
    }
}
