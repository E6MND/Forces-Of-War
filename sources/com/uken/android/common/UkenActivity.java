package com.uken.android.common;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.VideoView;
import com.aarki.AarkiContact;
import com.aarki.AarkiOfferActivity;
import com.amazon.inapp.purchasing.PurchasingManager;
import com.chartboost.sdk.Chartboost;
import com.chartboost.sdk.ChartboostDelegate;
import com.crashlytics.android.Crashlytics;
import com.facebook.AppEventsConstants;
import com.facebook.AppEventsLogger;
import com.facebook.Session;
import com.facebook.Settings;
import com.facebook.android.Facebook;
import com.facebook.internal.NativeProtocol;
import com.google.android.c2dm.C2DMessaging;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.sponsorpay.sdk.android.publisher.SponsorPayPublisher;
import com.uken.android.cache.ImageCacheManager;
import com.uken.android.common.LinearLayoutThatDetectsSoftKeyboard;
import com.uken.android.common.UkenApplication;
import com.uken.android.forcesofwar.R;
import com.uken.android.util.IabHelper;
import com.uken.android.util.IabResult;
import com.uken.android.util.Inventory;
import com.uken.android.util.OnFinalizePaymentFinishedListener;
import com.uken.android.util.Purchase;
import com.uken.android.util.TreasurerHelper;
import com.uken.android.util.UkenEventLog;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.xwalk.core.internal.XWalkViewInternal;

@SuppressLint({"SetJavaScriptEnabled"})
public class UkenActivity extends Activity implements LinearLayoutThatDetectsSoftKeyboard.Listener {
    public static String AARKI_PLACEMENT = null;
    private static final String AID_PREF_KEY = "AIDPrefsKey";
    public static String AMAZON_TREASURER_URL = null;
    public static final int ANDREW = 3069;
    public static final String APP_STATE = "APP_STATE";
    public static String BASEURL = null;
    public static String CHARTBOOST_APP_ID = null;
    public static String CHARTBOOST_APP_SIGNATURE = null;
    public static final int DIOGO = 3063;
    public static boolean DISPLAY_NAV_BAR = false;
    public static final int ELIAS = 3061;
    public static String FACEBOOK_APP_ID = null;
    public static String GAME_NAME = null;
    public static String GAME_NAMESPACE = null;
    public static String GAME_NAME_CONSTANT = null;
    public static final int HAMI = 3023;
    public static boolean IMAGES_PRELOADED = false;
    public static final int JUSTIN = 3019;
    public static final int KAITLIN = 3062;
    public static final String LAST_RUN_VERSION_CODE = "lastRunVersionCode";
    public static int MODAL_BACKGROUND_COLOR = 0;
    public static final int MODAL_REQUEST_CODE = 1;
    public static final String MODAL_URL_STRING = "modalUrlString";
    public static final String NOTIFICATION_JSON = "notification_json";
    public static String NOTIFICATION_SENDER_ID = null;
    public static final String NOTIFICATION_TOKEN = "notification_token";
    public static int PORT = ELIAS;
    static final int RC_REQUEST = 9164619;
    public static final int REBECCA = 3066;
    public static boolean RECORD_TOUCH_EVENTS = false;
    public static final int ROBIN = 3070;
    public static final String TAG = "UkenActivity";
    public static String TREASURER_URL = null;
    public static final int YORIE = 3050;
    private static String applicationState = "closed";
    public static UkenActivity curActivity;
    public static final Platform platform = null;
    private static LinkedList<String> queue = new LinkedList<>();
    public static boolean retriedVideo = false;
    public String BASE64_ENCODED_PUBLIC_KEY;
    public String aid;
    /* access modifiers changed from: private */
    public AlertDialog alertDialog = null;
    private AmazonPurchasingObserver amazonPurchasingObserver;
    private String amazonUser = null;
    public String androidId;
    private AndroidMarket androidMarket;
    private Button battleButton;
    public UkenBuild buildType = null;
    protected String[] buttonTags = {"home", "missions", "battle", "items", "empire"};
    private Chartboost cb;
    private Button empireButton;
    private Button homeButton;
    private Button itemsButton;
    /* access modifiers changed from: private */
    public IabHelper mHelper = null;
    public XWalkViewInternal mainWebView;
    private Button missionsButton;
    public String notificationJson;
    ProgressBar progressBar = null;
    private Button resetButton;
    private int screen;
    /* access modifiers changed from: private */
    public double screenZoomMultiplier;
    /* access modifiers changed from: private */
    public ImageView splash;
    /* access modifiers changed from: private */
    public View splashRelativeView;
    /* access modifiers changed from: private */
    public VideoView splashVideo;
    public boolean splashVideoFinished = false;
    public String udid;
    public String uuid;
    public boolean webViewLoadError = false;
    public boolean webViewLoaded = false;

    public enum Platform {
        mobage,
        ngmoco,
        amazon,
        blackberry
    }

    public enum UkenBuild {
        Staging,
        Production,
        Smoke,
        Local,
        Beta
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        setContentView(R.layout.main);
        curActivity = this;
        this.mainWebView = (XWalkViewInternal) findViewById(R.id.webView);
        initUdid();
        initAndroidId();
        initChartboost();
        this.alertDialog = initAlertDialog();
        selectBuild(this.buildType);
        initSplashViews();
        initScreen();
        initTreasurerHelper();
        UkenEventLog.BASEURL = BASEURL;
        if (platform == Platform.amazon) {
            initAmazonIAPHelper();
        } else {
            initGooglePlayIAPHelper();
        }
        initWebView();
        initAndroidAdvertisingId();
        initNotifications();
        initButtons();
        initCrashlytics();
        AarkiContact.setUserId(GAME_NAME_CONSTANT + "|" + this.udid);
        if (this.buildType == UkenBuild.Production) {
            ((UkenApplication) getApplication()).getTracker(UkenApplication.TrackerName.APP_TRACKER);
        }
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        this.cb.onStart(this);
        if (platform == Platform.amazon) {
            PurchasingManager.registerObserver(new AmazonPurchasingObserver(this));
        }
        if (this.buildType == UkenBuild.Production) {
            GoogleAnalytics.getInstance(this).reportActivityStart(this);
        }
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        this.cb.onStop(this);
        if (this.buildType == UkenBuild.Production) {
            GoogleAnalytics.getInstance(this).reportActivityStop(this);
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        applicationState = "foreground";
        UkenAudioManager.getInstance(this).setForeground(true);
        initSplashVideo(this);
        trackReferrer();
        AppEventsLogger.activateApp(getApplicationContext(), FACEBOOK_APP_ID);
        this.mainWebView.evaluateJavascript("androidOnResume()", (ValueCallback<String>) null);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        applicationState = "background";
        UkenAudioManager.getInstance(this).setForeground(false);
        this.mainWebView.evaluateJavascript("androidOnPause()", (ValueCallback<String>) null);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        if (this.cb != null) {
            this.cb.onDestroy(this);
        }
        if (this.mHelper != null) {
            this.mHelper.dispose();
            this.mHelper = null;
        }
    }

    /* access modifiers changed from: package-private */
    public void selectBuild(UkenBuild build) {
        String buildString = null;
        switch (build) {
            case Staging:
                buildString = "staging";
                break;
            case Smoke:
                buildString = "smoke";
                break;
            case Beta:
                buildString = "beta";
                break;
            case Local:
                buildString = "tunnel";
                Consts.DEBUG = true;
                break;
        }
        if (build != UkenBuild.Production) {
            if (BASEURL.startsWith("https://m")) {
                BASEURL = "https://m-" + buildString + BASEURL.substring(9);
            }
            if (this.buildType == UkenBuild.Local) {
                BASEURL = String.format("http://tunnel.uken.com:%d/", new Object[]{Integer.valueOf(PORT)});
            }
        }
        TREASURER_URL = BASEURL + "uken/payments/google_play/create";
        AMAZON_TREASURER_URL = BASEURL + "uken/payments/amazon/create";
        DeviceRegistrar.BASEURL = BASEURL;
        debug("BASE_URL: " + BASEURL);
        debug("TREASURER_URL: " + TREASURER_URL);
        debug("AMAZON_TREASURER_URL: " + AMAZON_TREASURER_URL);
    }

    private void trackReferrer() {
        SharedPreferences prefs = Prefs.get(this);
        if (prefs.getString("referrer", (String) null) != null) {
            try {
                PackageInfo pInfo = getPackageManager().getPackageInfo(getPackageName(), 128);
                if (!prefs.getBoolean("installTracked", false)) {
                    trackInstall();
                } else if (prefs.getInt(LAST_RUN_VERSION_CODE, 0) < pInfo.versionCode) {
                    trackUpdate(prefs, pInfo);
                }
            } catch (Exception e) {
                Utils.logCrashlytics(e);
            }
        }
    }

    private void trackUpdate(SharedPreferences prefs, PackageInfo pInfo) {
        try {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt(LAST_RUN_VERSION_CODE, pInfo.versionCode);
            editor.commit();
        } catch (Exception e) {
            Utils.logCrashlytics(e);
        }
    }

    private void trackInstall() {
        new Thread(new Runnable() {
            public void run() {
                SharedPreferences prefs = Prefs.get(UkenActivity.this);
                String referrer = prefs.getString("referrer", (String) null);
                String deviceId = UkenUuid.getUdid(UkenActivity.this);
                String androidId = DeviceInfo.deviceAndroidIdentifier(UkenActivity.this);
                String credentialToken = AuthenticationManager.getInstance(UkenActivity.this).getCredentialToken();
                try {
                    String postBackUrl = String.format("%sping/track_install?referrer=%s&deviceid=%s&androidid=%s", new Object[]{UkenActivity.BASEURL, URLEncoder.encode(referrer, "UTF-8"), URLEncoder.encode(deviceId, "UTF-8"), URLEncoder.encode(androidId, "UTF-8")});
                    if (!credentialToken.isEmpty()) {
                        postBackUrl = postBackUrl + String.format("&credential_token=%s", new Object[]{URLEncoder.encode(credentialToken, "UTF-8")});
                    }
                    new DefaultHttpClient().execute(new HttpGet(postBackUrl));
                    PackageInfo pInfo = UkenActivity.this.getPackageManager().getPackageInfo(UkenActivity.this.getPackageName(), 128);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putBoolean("installTracked", true);
                    editor.putInt(UkenActivity.LAST_RUN_VERSION_CODE, pInfo.versionCode);
                    editor.commit();
                } catch (Exception e) {
                    Utils.logCrashlytics(e);
                }
            }
        }).start();
    }

    public String addParametersToUrl(String url) {
        String urlString;
        List<NameValuePair> parameters = new ArrayList<>(2);
        parameters.add(new BasicNameValuePair("android_id", this.androidId));
        parameters.add(new BasicNameValuePair("udid", this.udid));
        parameters.add(new BasicNameValuePair("uuid", this.uuid));
        parameters.add(new BasicNameValuePair("android_sdk_version", DeviceInfo.systemSDKVersion()));
        parameters.add(new BasicNameValuePair("screen", Integer.toString(this.screen)));
        parameters.add(new BasicNameValuePair("package_name", DeviceInfo.appIdentifier(this)));
        parameters.add(new BasicNameValuePair("device_manufacturer", DeviceInfo.deviceManufacturer()));
        parameters.add(new BasicNameValuePair("device_model", DeviceInfo.deviceModel()));
        if (platform != null) {
            parameters.add(new BasicNameValuePair(platform.toString(), AppEventsConstants.EVENT_PARAM_VALUE_YES));
        }
        parameters.add(new BasicNameValuePair("phone_id", DeviceInfo.devicePhoneIdentifier(this)));
        parameters.add(new BasicNameValuePair("system_version", DeviceInfo.systemVersion()));
        parameters.add(new BasicNameValuePair("system_name", DeviceInfo.systemName()));
        parameters.add(new BasicNameValuePair("country", DeviceInfo.localeCountryCode(this)));
        parameters.add(new BasicNameValuePair("language", DeviceInfo.localeLanguageCode(this)));
        parameters.add(new BasicNameValuePair("timezone", DeviceInfo.localeTimeZone()));
        parameters.add(new BasicNameValuePair("time_gmt_offset", DeviceInfo.localeTimeGMTOffsetString()));
        if (this.notificationJson != null && !this.notificationJson.equals("")) {
            parameters.add(new BasicNameValuePair(NOTIFICATION_JSON, this.notificationJson));
        }
        String credentialToken = AuthenticationManager.getInstance(this).getCredentialToken();
        if (credentialToken != null && !credentialToken.equals("")) {
            parameters.add(new BasicNameValuePair("credential_token", credentialToken));
        }
        String attributionId = Settings.getAttributionId(getContentResolver());
        if (attributionId != null) {
            parameters.add(new BasicNameValuePair("fb_attribution_id", attributionId));
        }
        try {
            parameters.add(new BasicNameValuePair("hmid", AeSimpleSHA1.SHA1(DeviceInfo.deviceMacAddress(this))));
        } catch (Exception e1) {
            if (Consts.DEBUG) {
                e1.printStackTrace();
            }
        }
        try {
            parameters.add(new BasicNameValuePair("android_app_version", Integer.toString(DeviceInfo.appVersionCode(this))));
        } catch (PackageManager.NameNotFoundException e12) {
            parameters.add(new BasicNameValuePair("android_app_version", "39"));
            Utils.logCrashlytics(e12);
        }
        try {
            parameters.add(new BasicNameValuePair(NativeProtocol.BRIDGE_ARG_APP_NAME_STRING, DeviceInfo.appName(this)));
        } catch (PackageManager.NameNotFoundException e13) {
            parameters.add(new BasicNameValuePair(NativeProtocol.BRIDGE_ARG_APP_NAME_STRING, GAME_NAME));
            Utils.logCrashlytics(e13);
        }
        if (this.aid != null) {
            parameters.add(new BasicNameValuePair(Facebook.ATTRIBUTION_ID_COLUMN_NAME, this.aid));
        }
        String paramString = URLEncodedUtils.format(parameters, "utf-8");
        if (!url.contains("?")) {
            urlString = url + "?" + paramString;
        } else {
            urlString = url + "&" + paramString;
        }
        debug("URL to laod: " + urlString);
        return urlString;
    }

    public void onBackPressed() {
        if (!this.cb.onBackPressed()) {
            this.mainWebView.evaluateJavascript("backButton()", (ValueCallback<String>) null);
        }
    }

    public void onSoftKeyboardShown(boolean isShowing) {
        if (getResources().getConfiguration().hardKeyboardHidden == 2) {
            handleKeyboardChange(isShowing);
        }
    }

    private void handleKeyboardChange(boolean isShowing) {
        LinearLayout tabBarLinearView = (LinearLayout) findViewById(R.id.tabBarLinearView);
        if (isShowing) {
            tabBarLinearView.setVisibility(8);
        } else if (shouldShowNavigationBar()) {
            tabBarLinearView.setVisibility(0);
        }
        if (!isShowing) {
            while (!queue.isEmpty()) {
                this.mainWebView.load(queue.remove(), (String) null);
            }
        }
    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.hardKeyboardHidden == 1) {
            handleKeyboardChange(true);
        } else if (newConfig.hardKeyboardHidden == 2) {
            handleKeyboardChange(false);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        String callback;
        debug("onActivityResult(" + requestCode + "," + resultCode + "," + data);
        if (this.mHelper != null && !this.mHelper.handleActivityResult(requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data);
            switch (requestCode) {
                case 1:
                    if (resultCode == -1 && (callback = data.getStringExtra(ModalActivity.CALLBACK)) != null) {
                        this.mainWebView.evaluateJavascript(callback, (ValueCallback<String>) null);
                        break;
                    }
            }
        } else {
            remoteLog("onActivityResult(" + requestCode + "," + resultCode + "," + data);
            remoteLog("onActivityResult handled by IABUtil.");
        }
        Session session = Session.getActiveSession();
        if (session != null) {
            session.onActivityResult(this, requestCode, resultCode, data);
        }
    }

    private boolean shouldShowNavigationBar() {
        if (platform == Platform.amazon) {
            return false;
        }
        if (this.screen == 4 && platform != Platform.blackberry) {
            return false;
        }
        if (Build.VERSION.SDK_INT < 11 || DISPLAY_NAV_BAR) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public boolean isHDVideoPlaybackSupported() {
        return Build.VERSION.SDK_INT >= 14;
    }

    private void initSplashVideo(final Context mainContext) {
        if (!isHDVideoPlaybackSupported() || retriedVideo) {
            this.splashVideo.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.uken_logo);
        } else {
            this.splashVideo.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.uken_logo_hd);
        }
        this.splashVideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            public void onPrepared(MediaPlayer mp) {
                if (UkenAudioManager.getInstance(mainContext).isMuted()) {
                    mp.setVolume(0.0f, 0.0f);
                }
            }
        });
        this.splashVideo.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            public boolean onError(MediaPlayer mp, int what, int extra) {
                if (UkenActivity.retriedVideo || !UkenActivity.this.isHDVideoPlaybackSupported()) {
                    return false;
                }
                UkenActivity.retriedVideo = true;
                Intent intent = UkenActivity.this.getIntent();
                intent.addFlags(65536);
                UkenActivity.this.overridePendingTransition(0, 0);
                UkenActivity.this.finish();
                UkenActivity.this.overridePendingTransition(0, 0);
                UkenActivity.this.startActivity(intent);
                return true;
            }
        });
        this.splashVideo.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer vmp) {
                UkenActivity.this.splashVideoFinished = true;
                UkenActivity.this.splashVideo.setVisibility(8);
                UkenActivity.this.splash.setVisibility(0);
                if (UkenActivity.this.webViewLoaded) {
                    UkenActivity.this.mainWebView.setVisibility(0);
                    UkenActivity.this.mainWebView.requestFocus();
                    UkenActivity.this.splash.setVisibility(8);
                    UkenActivity.this.splashRelativeView.setVisibility(8);
                } else if (!UkenActivity.this.webViewLoadError) {
                    UkenActivity.this.progressBar.setVisibility(0);
                } else {
                    UkenActivity.this.showAlertDialog();
                }
            }
        });
        this.splashVideo.requestFocus();
        this.splashVideo.start();
    }

    private void initSplashViews() {
        this.splash = (ImageView) findViewById(R.id.splashScreen);
        this.splashVideo = (VideoView) findViewById(R.id.splashVideo);
        this.splashRelativeView = findViewById(R.id.splashRelativeView);
        curActivity.getWindow().getDecorView().setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        this.splash.setVisibility(8);
        this.splashVideo.setVisibility(0);
    }

    public View getSplashRelativeView() {
        return this.splashRelativeView;
    }

    public ImageView getSplash() {
        return this.splash;
    }

    private void initNotifications() {
        String regId = C2DMessaging.getRegistrationId(this);
        if (regId == null || "".equals(regId)) {
            C2DMessaging.register(this, NOTIFICATION_SENDER_ID);
        } else {
            DeviceRegistrar.registerWithServer(this, regId);
        }
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.notificationJson = extras.getString(NOTIFICATION_JSON);
        }
    }

    private void initWebView() {
        this.mainWebView.addJavascriptInterface(UkenAudioManager.getInstance(this), "AudioManager");
        this.progressBar = (ProgressBar) findViewById(R.id.progressBar);
        this.progressBar.bringToFront();
        initJavascriptInterfaces();
        this.mainWebView.getSettings().setJavaScriptEnabled(true);
        this.mainWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(false);
        this.mainWebView.getSettings().setSupportMultipleWindows(false);
        this.mainWebView.getSettings().setDomStorageEnabled(true);
        this.mainWebView.getSettings().setDatabaseEnabled(true);
        this.mainWebView.enableRemoteDebugging();
        this.mainWebView.setVerticalScrollBarEnabled(true);
        this.mainWebView.setHorizontalScrollBarEnabled(false);
        this.mainWebView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if (!UkenActivity.RECORD_TOUCH_EVENTS) {
                    return false;
                }
                if ((event.getAction() != 0 && event.getAction() != 1) || !UkenActivity.this.mainWebView.getUrl().contains(UkenActivity.GAME_NAMESPACE)) {
                    return false;
                }
                double x = ((double) event.getX()) * UkenActivity.this.screenZoomMultiplier;
                double y = ((double) event.getY()) * UkenActivity.this.screenZoomMultiplier;
                UkenActivity.this.debug(String.format("touched x:%f y:%f", new Object[]{Double.valueOf(x), Double.valueOf(y)}));
                if (event.getAction() == 0) {
                    UkenActivity.this.mainWebView.evaluateJavascript(String.format("PerfMonitor.touchStart(%f, %f)", new Object[]{Double.valueOf(x), Double.valueOf(y)}), (ValueCallback<String>) null);
                }
                if (event.getAction() != 1) {
                    return false;
                }
                UkenActivity.this.mainWebView.evaluateJavascript(String.format("PerfMonitor.touchEnd(%f, %f)", new Object[]{Double.valueOf(x), Double.valueOf(y)}), (ValueCallback<String>) null);
                return false;
            }
        });
        this.mainWebView.setXWalkClient(new UkenXWalkClient(this.mainWebView));
        this.mainWebView.setResourceClient(new UkenWebViewClient(this.mainWebView));
        if (ImageCacheManager.clientSupportsImageCache()) {
            this.mainWebView.getSettings().setCacheMode(2);
        }
        if (this.screen <= 3) {
            Point p = new Point();
            getWindowManager().getDefaultDisplay().getSize(p);
            this.screenZoomMultiplier = 320.0d / ((double) p.x);
            this.mainWebView.getSettings().setUseWideViewPort(true);
        }
        if (this.buildType != UkenBuild.Production) {
            RECORD_TOUCH_EVENTS = true;
        }
        if (!GAME_NAME.equalsIgnoreCase("Uken Dev")) {
            this.mainWebView.setBackgroundColor(Color.argb(1, 0, 0, 0));
        }
    }

    private void initChartboost() {
        this.cb = Chartboost.sharedChartboost();
        this.cb.onCreate(this, CHARTBOOST_APP_ID, CHARTBOOST_APP_SIGNATURE, (ChartboostDelegate) null);
        this.cb.startSession();
    }

    /* access modifiers changed from: protected */
    public void initCrashlytics() {
        try {
            if (!Consts.DEBUG) {
                Crashlytics.start(this);
                Crashlytics.setUserIdentifier(AuthenticationManager.getInstance(this).getCredentialToken());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initTreasurerHelper() {
        TreasurerHelper.BASEURL = BASEURL;
        TreasurerHelper.GAME_NAME_CONSTANT = GAME_NAME_CONSTANT;
        TreasurerHelper.TREASURER_URL = TREASURER_URL;
        TreasurerHelper.BUILD_TYPE = this.buildType;
    }

    private void initAmazonIAPHelper() {
        AmazonPurchasingObserver.GAME_NAME_CONSTANT = GAME_NAME_CONSTANT;
        AmazonPurchasingObserver.AMAZON_TREASURER_URL = AMAZON_TREASURER_URL;
        this.amazonPurchasingObserver = new AmazonPurchasingObserver(this);
        PurchasingManager.registerObserver(this.amazonPurchasingObserver);
    }

    private void initGooglePlayIAPHelper() {
        if (platform == null) {
            this.mHelper = new IabHelper(this, this.BASE64_ENCODED_PUBLIC_KEY);
            if (this.buildType != UkenBuild.Production) {
                this.mHelper.enableDebugLogging(true);
            }
            final IabHelper.OnConsumeFinishedListener consumeFinishedListener = new IabHelper.OnConsumeFinishedListener() {
                public void onConsumeFinished(Purchase purchase, IabResult result) {
                    UkenActivity.this.remoteLog("Consumption finished");
                    UkenActivity.this.remoteLog(String.format("Purchase: %s, result: %s, google_order_id: %s", new Object[]{purchase, result, purchase.getOrderId()}));
                    UkenActivity.this.remoteLog("End consumption flow");
                }
            };
            final IabHelper.QueryInventoryFinishedListener queryInventoryFinishedListener = new IabHelper.QueryInventoryFinishedListener() {
                public void onQueryInventoryFinished(IabResult result, Inventory inventory) {
                    UkenActivity.this.remoteLog("Query inventory finished.");
                    TreasurerHelper mTreasurerHelper = new TreasurerHelper(UkenActivity.this);
                    if (UkenActivity.this.mHelper != null) {
                        if (result.isFailure()) {
                            UkenActivity.this.remoteLog("Failed to query inventory: " + result);
                            return;
                        }
                        UkenActivity.this.remoteLog("Query inventory was successful.");
                        List<Purchase> purchases = inventory.getAllPurchases();
                        if (purchases != null) {
                            UkenActivity.this.remoteLog(String.format("Consuming %d IAPs", new Object[]{Integer.valueOf(purchases.size())}));
                            for (final Purchase purchase : purchases) {
                                mTreasurerHelper.finalizePaymentAsync(purchase, new OnFinalizePaymentFinishedListener() {
                                    public void onFinalizePaymentFinished() {
                                        UkenActivity.this.remoteLog("consuming IAP with google_order_id:" + purchase.getOrderId());
                                        UkenActivity.this.mHelper.consumeAsync(purchase, consumeFinishedListener);
                                    }
                                });
                            }
                        }
                    }
                }
            };
            this.mHelper.startSetup(new IabHelper.OnIabSetupFinishedListener() {
                public void onIabSetupFinished(IabResult result) {
                    UkenActivity.this.remoteLog("Setup finished. " + result.toString());
                    if (UkenActivity.this.mHelper != null && result.isSuccess()) {
                        UkenActivity.this.mHelper.queryInventoryAsync(queryInventoryFinishedListener);
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public AlertDialog initAlertDialog() {
        return initAlertDialog("Failed to Connect", "There was an error connecting to " + GAME_NAME, "Retry", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                UkenActivity.this.webViewLoadError = false;
                UkenActivity.this.splashRelativeView.setVisibility(0);
                UkenActivity.this.progressBar.setVisibility(0);
                UkenActivity.this.progressBar.setProgress(0);
                UkenActivity.this.mainWebView.reload(0);
            }
        });
    }

    private AlertDialog initAlertDialog(String title, String message, String buttonLabel, DialogInterface.OnClickListener buttonClickListener) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle(title);
        alertDialogBuilder.setMessage(message);
        alertDialogBuilder.setPositiveButton(buttonLabel, buttonClickListener);
        alertDialogBuilder.setIcon(R.drawable.icon);
        alertDialogBuilder.setCancelable(false);
        return alertDialogBuilder.create();
    }

    public void showAlertDialog() {
        if (this.alertDialog == null) {
            this.alertDialog = initAlertDialog();
        }
        if (!isFinishing()) {
            this.alertDialog.show();
        }
    }

    public void showUnsupportedBillingApiDialog() {
        this.alertDialog = initAlertDialog("Unable to complete the purchase", "Please update Google Play and Google Play Services or contact support@uken.com for assistance.", "OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                AlertDialog unused = UkenActivity.this.alertDialog = UkenActivity.this.initAlertDialog();
            }
        });
        showAlertDialog();
    }

    /* access modifiers changed from: protected */
    public void initScreen() {
        ((LinearLayoutThatDetectsSoftKeyboard) findViewById(R.id.main)).setListener(this);
        this.screen = getResources().getConfiguration().screenLayout & 15;
        if (platform == Platform.blackberry) {
            this.screen = 3;
        }
    }

    /* access modifiers changed from: protected */
    public void initButtons() {
        if (shouldShowNavigationBar()) {
            View.OnClickListener buttonClickListener = new View.OnClickListener() {
                public void onClick(View view) {
                    UkenActivity.this.mainWebView.evaluateJavascript("menuLoader('" + view.getTag().toString() + "')", (ValueCallback<String>) null);
                }
            };
            this.homeButton = (Button) findViewById(R.id.homeTabButton);
            this.homeButton.setTag(this.buttonTags[0]);
            this.homeButton.setOnClickListener(buttonClickListener);
            this.missionsButton = (Button) findViewById(R.id.missionsTabButton);
            this.missionsButton.setTag(this.buttonTags[1]);
            this.missionsButton.setOnClickListener(buttonClickListener);
            this.battleButton = (Button) findViewById(R.id.battleTabButton);
            this.battleButton.setTag(this.buttonTags[2]);
            this.battleButton.setOnClickListener(buttonClickListener);
            this.itemsButton = (Button) findViewById(R.id.itemsTabButton);
            this.itemsButton.setTag(this.buttonTags[3]);
            this.itemsButton.setOnClickListener(buttonClickListener);
            this.empireButton = (Button) findViewById(R.id.empireTabButton);
            this.empireButton.setTag(this.buttonTags[4]);
            if (this.empireButton.getTag().equals("scienceLab")) {
                this.empireButton.setText(R.string.scienceLabTabBarItem);
            }
            this.empireButton.setOnClickListener(buttonClickListener);
            if (GAME_NAME.equalsIgnoreCase("Uken Dev")) {
                this.resetButton = (Button) findViewById(R.id.resetTabButton);
                this.resetButton.setVisibility(0);
                this.resetButton.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        UkenActivity.this.mainWebView.load(UkenActivity.this.addParametersToUrl(UkenActivity.BASEURL), (String) null);
                    }
                });
                return;
            }
            return;
        }
        ((LinearLayout) findViewById(R.id.tabBarLinearView)).setVisibility(8);
    }

    private void initJavascriptInterfaces() {
        this.androidMarket = new AndroidMarket(this, this.mHelper);
        this.mainWebView.addJavascriptInterface(this.androidMarket, "AndroidMarket");
        this.mainWebView.addJavascriptInterface(new Object() {
            @JavascriptInterface
            public void purchase(String pid) {
                String initiatePurchaseRequest = PurchasingManager.initiatePurchaseRequest(pid);
            }
        }, "AmazonMarket");
        this.mainWebView.addJavascriptInterface(new Object(this) {
            private UkenActivity ukenActivity = null;

            {
                this.ukenActivity = ukenActivity;
            }

            @JavascriptInterface
            public void showOffers() {
                this.ukenActivity.showAarkiOffers();
            }
        }, "AarkiOfferWall");
        this.mainWebView.addJavascriptInterface(new Object(this) {
            private UkenActivity ukenActivity = null;

            {
                this.ukenActivity = ukenActivity;
            }

            @JavascriptInterface
            public void showOffers() {
                this.ukenActivity.startActivityForResult(SponsorPayPublisher.getIntentForOfferWallActivity(this.ukenActivity.getApplicationContext(), UkenActivity.GAME_NAME_CONSTANT + "|" + UkenActivity.this.udid), 255);
            }
        }, "SponsorPayOfferWall");
        this.mainWebView.addJavascriptInterface(new AppCloser(this), "AppCloser");
    }

    /* access modifiers changed from: protected */
    public void initUdid() {
        this.udid = UkenUuid.getUdid(this);
    }

    /* access modifiers changed from: protected */
    public void initAndroidId() {
        this.androidId = UkenUuid.getAndroidId(this);
    }

    /* access modifiers changed from: protected */
    public void initAndroidAdvertisingId() {
        final SharedPreferences prefs = Prefs.get(this);
        this.aid = prefs.getString(AID_PREF_KEY, (String) null);
        new Thread(new Runnable() {
            public void run() {
                try {
                    AdvertisingIdClient.Info adInfo = AdvertisingIdClient.getAdvertisingIdInfo(UkenActivity.this);
                    if (adInfo != null) {
                        UkenActivity.this.aid = adInfo.getId();
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putString(UkenActivity.AID_PREF_KEY, UkenActivity.this.aid);
                        editor.commit();
                    }
                } catch (IOException e) {
                    Utils.logCrashlytics(e);
                } catch (GooglePlayServicesRepairableException e2) {
                    UkenActivity.this.handleGooglePlayServicesExceptions();
                } catch (GooglePlayServicesNotAvailableException e3) {
                    UkenActivity.this.handleGooglePlayServicesExceptions();
                } catch (Exception e4) {
                    Utils.logCrashlytics(e4);
                }
                UkenActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        UkenActivity.this.mainWebView.load(UkenActivity.this.addParametersToUrl(UkenActivity.BASEURL), (String) null);
                    }
                });
            }
        }).start();
    }

    /* access modifiers changed from: private */
    public void handleGooglePlayServicesExceptions() {
        try {
            GooglePlayServicesUtil.showErrorNotification(GooglePlayServicesUtil.isGooglePlayServicesAvailable(this), this);
        } catch (Exception e) {
            Utils.logCrashlytics(e);
        }
    }

    public void showAarkiOffers() {
        if (AARKI_PLACEMENT != null) {
            runOnUiThread(new Runnable() {
                public void run() {
                    AarkiOfferActivity.launch(UkenActivity.this, UkenActivity.AARKI_PLACEMENT);
                }
            });
        }
    }

    public static String getApplicationState() {
        return applicationState;
    }

    public void showModal(String url) {
        Intent intent = new Intent(this, ModalActivity.class);
        Bundle b = new Bundle();
        b.putString(MODAL_URL_STRING, BASEURL + url);
        intent.putExtras(b);
        startActivityForResult(intent, 1);
    }

    /* access modifiers changed from: private */
    public void debug(String message) {
        if (Consts.DEBUG) {
            Log.d(TAG, message);
        }
    }

    public void logPurchases(Purchase purchase) {
        this.androidMarket.fbLogPurchase(purchase);
        this.androidMarket.gaLogPurchase(purchase);
    }

    /* access modifiers changed from: private */
    public void remoteLog(String message) {
        remoteLog(message, (Exception) null);
    }

    private void remoteLog(String message, Exception e) {
        Utils.logUkenEvent(TAG, message, e);
        debug(message);
    }
}
