package com.uken.android.common;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.view.View;
import android.webkit.WebResourceResponse;
import android.widget.ImageView;
import com.chartboost.sdk.Chartboost;
import com.crashlytics.android.Crashlytics;
import com.facebook.AppEventsConstants;
import com.facebook.AppEventsLogger;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.model.GraphUser;
import com.google.android.gms.tagmanager.DataLayer;
import com.uken.android.cache.ImageCacheManager;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import org.xwalk.core.internal.XWalkResourceClientInternal;
import org.xwalk.core.internal.XWalkViewInternal;

public final class UkenWebViewClient extends XWalkResourceClientInternal {
    private static final String EMPTY_JSON = "{}";
    private static final String GAMEDATA_DIR = "gamedata/";
    private static final String JSON_MIME_TYPE = "application/json";
    private static final String[] PERMISSIONS = {"publish_stream", "read_stream", "offline_access", "email"};
    private static final String UTF8 = "utf-8";
    private Activity activity;
    private AssetManager assetManager = this.ukenActivity.getAssets();
    private ImageCacheManager imageCacheManager;
    private ImageView splash = this.ukenActivity.getSplash();
    private View splashRelativeView = this.ukenActivity.getSplashRelativeView();
    /* access modifiers changed from: private */
    public UkenActivity ukenActivity;
    private XWalkViewInternal xWalkView;

    public UkenWebViewClient(XWalkViewInternal xWalkView2) {
        super(xWalkView2);
        this.xWalkView = xWalkView2;
        Activity activity2 = xWalkView2.getActivity();
        this.activity = activity2;
        this.ukenActivity = (UkenActivity) activity2;
        if (ImageCacheManager.clientSupportsImageCache()) {
            this.imageCacheManager = ImageCacheManager.getInstance();
        }
    }

    public boolean shouldOverrideUrlLoading(final XWalkViewInternal view, String url) {
        if (url.contains("android_connect_dialog")) {
            final String baseUrlToLoad = getUrl(view);
            Session.openActiveSession(this.activity, true, (Session.StatusCallback) new Session.StatusCallback() {
                public void call(final Session session, SessionState state, Exception exception) {
                    if (session.isOpened()) {
                        Request.newMeRequest(session, new Request.GraphUserCallback() {
                            public void onCompleted(GraphUser user, Response response) {
                                String ufid = user.getId();
                                String access_token = session.getAccessToken();
                                view.load(UkenWebViewClient.this.ukenActivity.addParametersToUrl(String.format("%s?ufid=%s&fb_access_token=%s", new Object[]{baseUrlToLoad, ufid, access_token})), (String) null);
                            }
                        }).executeAsync();
                    }
                }
            });
            view.load(this.ukenActivity.addParametersToUrl(baseUrlToLoad), (String) null);
            return true;
        }
        if (url.contains("facebook_logout")) {
            Session session = Session.getActiveSession();
            if (session != null && session.isOpened()) {
                session.closeAndClearTokenInformation();
            }
            AuthenticationManager.getInstance(this.ukenActivity).removeCredential();
        } else if (url.startsWith("market://")) {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(url));
            intent.addFlags(AccessibilityEventCompat.TYPE_GESTURE_DETECTION_END);
            this.activity.startActivity(intent);
            view.load(this.ukenActivity.addParametersToUrl(UkenActivity.BASEURL), (String) null);
        }
        return false;
    }

    public WebResourceResponse shouldInterceptLoadRequest(XWalkViewInternal view, String url) {
        if (url.contains("/command.gamedata.load/")) {
            return handleCommandGamedataLoad(view, url);
        }
        if (url.contains("/command.set_credential_token")) {
            return handleCommandSetCredentialToken(view, url);
        }
        if (url.contains("/command.modalwebview.show/")) {
            return handleCommandModalwebviewShow(view, url);
        }
        if (url.contains("/command.show_chartboost_interstitial/")) {
            return handleCommandShowChartboostInterstitial(view, url);
        }
        if (url.contains("/command.fb_track_events/")) {
            return handleCommandFbTrackEvents(view, url);
        }
        if (url.contains("/command.splashview.show/")) {
            return handleCommandSplashviewShow(view, url);
        }
        if (url.contains("/command.splashview.hide/")) {
            return handleCommandSplashviewHide(view, url);
        }
        if (url.contains("play_sound")) {
            return handlePlaySound(view, url);
        }
        if (url.contains("/mute_only_sound")) {
            return handleMuteOnlySound(view, url);
        }
        if (url.contains("/unmute_only_sound")) {
            return handleUnMuteOnlySound(view, url);
        }
        if (url.contains("/command.")) {
            return successResponse();
        }
        if (!ImageCacheManager.clientSupportsImageCache()) {
            return super.shouldInterceptLoadRequest(view, url);
        }
        WebResourceResponse response = this.imageCacheManager.getImageFromCache(url);
        if (response != null) {
            return response;
        }
        this.imageCacheManager.cacheImage(url);
        return null;
    }

    public void onLoadFinished(XWalkViewInternal view, String url) {
        if (!this.ukenActivity.webViewLoadError) {
            this.ukenActivity.webViewLoaded = true;
            if (this.ukenActivity.splashVideoFinished) {
                view.setVisibility(0);
                view.requestFocus();
                this.splash.setVisibility(8);
                this.splashRelativeView.setVisibility(8);
                if (this.ukenActivity.progressBar.isShown()) {
                    try {
                        this.ukenActivity.progressBar.setVisibility(8);
                    } catch (Exception e) {
                        Crashlytics.logException(e);
                    }
                }
            }
        }
    }

    public void onReceivedLoadError(XWalkViewInternal view, int errorCode, String description, String failingUrl) {
        this.ukenActivity.webViewLoadError = true;
        view.setVisibility(8);
        if (this.ukenActivity.splashVideoFinished) {
            this.splash.setVisibility(0);
            this.ukenActivity.showAlertDialog();
        }
    }

    public void onProgressChanged(XWalkViewInternal view, int progressInPercent) {
        super.onProgressChanged(view, progressInPercent);
        this.ukenActivity.progressBar.setProgress(progressInPercent);
    }

    private String getUrl(XWalkViewInternal view) {
        String ukenDevBaseUrl = view.getUrl().substring(0, view.getUrl().indexOf("com") + 4);
        if (UkenActivity.GAME_NAME != "Uken Dev") {
            return UkenActivity.BASEURL;
        }
        return ukenDevBaseUrl;
    }

    private WebResourceResponse getGameData(String model, String callback) {
        StringBuilder stringBuilder = new StringBuilder();
        boolean hasCallback = false;
        if (callback != null) {
            hasCallback = true;
            stringBuilder.append(callback);
            stringBuilder.append('(');
        }
        if (model == null || model.isEmpty()) {
            stringBuilder.append(EMPTY_JSON);
        } else {
            try {
                String contents = Utils.parseJson(this.assetManager.open(GAMEDATA_DIR + model + ".json"));
                if (!contents.trim().isEmpty()) {
                    stringBuilder.append(contents);
                } else {
                    stringBuilder.append(EMPTY_JSON);
                }
            } catch (IOException e) {
                stringBuilder.append(EMPTY_JSON);
            }
        }
        if (hasCallback) {
            stringBuilder.append(')');
        }
        return new WebResourceResponse(JSON_MIME_TYPE, UTF8, new ByteArrayInputStream(stringBuilder.toString().getBytes()));
    }

    private WebResourceResponse successResponse() {
        return new WebResourceResponse("text/plain", "UTF-8", new ByteArrayInputStream("".getBytes()));
    }

    private WebResourceResponse handleCommandGamedataLoad(XWalkViewInternal view, String url) {
        Uri uri = Uri.parse(url);
        return getGameData(uri.getQueryParameter("model"), uri.getQueryParameter(ModalActivity.CALLBACK));
    }

    private WebResourceResponse handleCommandSetCredentialToken(XWalkViewInternal view, String url) {
        String credentialToken = Uri.parse(url).getQueryParameter("credential_token");
        if (credentialToken != null) {
            AuthenticationManager.getInstance(this.activity).setCredentialToken(credentialToken);
            if (!Consts.DEBUG) {
                Crashlytics.setUserIdentifier(credentialToken);
            }
        }
        return successResponse();
    }

    private WebResourceResponse handleCommandModalwebviewShow(XWalkViewInternal view, String url) {
        String path = Uri.parse(url).getQueryParameter("path");
        if (path != null) {
            this.ukenActivity.showModal(path);
        }
        return successResponse();
    }

    private WebResourceResponse handlePlaySound(XWalkViewInternal view, String url) {
        String soundName = Uri.parse(url).getQueryParameter("soundId");
        if (soundName != null) {
            UkenAudioManager.getInstance(this.activity).playSound(soundName.substring(0, soundName.lastIndexOf(46)));
        }
        return successResponse();
    }

    private WebResourceResponse handleMuteOnlySound(XWalkViewInternal view, String url) {
        UkenAudioManager.getInstance(this.activity).mute();
        return successResponse();
    }

    private WebResourceResponse handleUnMuteOnlySound(XWalkViewInternal view, String url) {
        UkenAudioManager.getInstance(this.activity).unmute();
        return successResponse();
    }

    private WebResourceResponse handleCommandShowChartboostInterstitial(XWalkViewInternal view, String url) {
        String query = Uri.parse(url).getQuery();
        if (query == null || query.isEmpty()) {
            Chartboost.sharedChartboost().showInterstitial();
        } else {
            Chartboost.sharedChartboost().showInterstitial(query);
        }
        return successResponse();
    }

    private WebResourceResponse handleCommandFbTrackEvents(XWalkViewInternal view, String url) {
        Uri uri = Uri.parse(url);
        String event = uri.getQueryParameter(DataLayer.EVENT_KEY);
        if (event != null && event.equals("levelup")) {
            String level = uri.getQueryParameter("level");
            AppEventsLogger logger = AppEventsLogger.newLogger(this.ukenActivity);
            Bundle params = new Bundle();
            params.putString(AppEventsConstants.EVENT_PARAM_LEVEL, level);
            logger.logEvent(AppEventsConstants.EVENT_NAME_ACHIEVED_LEVEL, params);
        }
        return successResponse();
    }

    private WebResourceResponse handleCommandSplashviewShow(XWalkViewInternal view, String url) {
        this.splash.setVisibility(0);
        this.splashRelativeView.setVisibility(0);
        return successResponse();
    }

    private WebResourceResponse handleCommandSplashviewHide(XWalkViewInternal view, String url) {
        this.splash.setVisibility(8);
        this.splashRelativeView.setVisibility(8);
        return successResponse();
    }
}
