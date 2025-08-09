package com.facebook;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.CookieSyncManager;
import com.facebook.Request;
import com.facebook.RequestBatch;
import com.facebook.Session;
import com.facebook.android.R;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.internal.NativeProtocol;
import com.facebook.internal.PlatformServiceClient;
import com.facebook.internal.ServerProtocol;
import com.facebook.internal.Utility;
import com.facebook.model.GraphUser;
import com.facebook.widget.FacebookDialog;
import com.facebook.widget.WebDialog;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

class AuthorizationClient implements Serializable {
    static final String EVENT_EXTRAS_DEFAULT_AUDIENCE = "default_audience";
    static final String EVENT_EXTRAS_IS_LEGACY = "is_legacy";
    static final String EVENT_EXTRAS_LOGIN_BEHAVIOR = "login_behavior";
    static final String EVENT_EXTRAS_MISSING_INTERNET_PERMISSION = "no_internet_permission";
    static final String EVENT_EXTRAS_NEW_PERMISSIONS = "new_permissions";
    static final String EVENT_EXTRAS_NOT_TRIED = "not_tried";
    static final String EVENT_EXTRAS_PERMISSIONS = "permissions";
    static final String EVENT_EXTRAS_REQUEST_CODE = "request_code";
    static final String EVENT_EXTRAS_TRY_LEGACY = "try_legacy";
    static final String EVENT_EXTRAS_TRY_LOGIN_ACTIVITY = "try_login_activity";
    static final String EVENT_NAME_LOGIN_COMPLETE = "fb_mobile_login_complete";
    private static final String EVENT_NAME_LOGIN_METHOD_COMPLETE = "fb_mobile_login_method_complete";
    private static final String EVENT_NAME_LOGIN_METHOD_START = "fb_mobile_login_method_start";
    static final String EVENT_NAME_LOGIN_START = "fb_mobile_login_start";
    static final String EVENT_PARAM_AUTH_LOGGER_ID = "0_auth_logger_id";
    static final String EVENT_PARAM_ERROR_CODE = "4_error_code";
    static final String EVENT_PARAM_ERROR_MESSAGE = "5_error_message";
    static final String EVENT_PARAM_EXTRAS = "6_extras";
    static final String EVENT_PARAM_LOGIN_RESULT = "2_result";
    static final String EVENT_PARAM_METHOD = "3_method";
    private static final String EVENT_PARAM_METHOD_RESULT_SKIPPED = "skipped";
    static final String EVENT_PARAM_TIMESTAMP = "1_timestamp_ms";
    private static final String TAG = "Facebook-AuthorizationClient";
    private static final String WEB_VIEW_AUTH_HANDLER_STORE = "com.facebook.AuthorizationClient.WebViewAuthHandler.TOKEN_STORE_KEY";
    private static final String WEB_VIEW_AUTH_HANDLER_TOKEN_KEY = "TOKEN";
    private static final long serialVersionUID = 1;
    private transient AppEventsLogger appEventsLogger;
    transient BackgroundProcessingListener backgroundProcessingListener;
    transient boolean checkedInternetPermission;
    transient Context context;
    AuthHandler currentHandler;
    List<AuthHandler> handlersToTry;
    Map<String, String> loggingExtras;
    transient OnCompletedListener onCompletedListener;
    AuthorizationRequest pendingRequest;
    transient StartActivityDelegate startActivityDelegate;

    interface BackgroundProcessingListener {
        void onBackgroundProcessingStarted();

        void onBackgroundProcessingStopped();
    }

    interface OnCompletedListener {
        void onCompleted(Result result);
    }

    interface StartActivityDelegate {
        Activity getActivityContext();

        void startActivityForResult(Intent intent, int i);
    }

    AuthorizationClient() {
    }

    /* access modifiers changed from: package-private */
    public void setContext(Context context2) {
        this.context = context2;
        this.startActivityDelegate = null;
    }

    /* access modifiers changed from: package-private */
    public void setContext(final Activity activity) {
        this.context = activity;
        this.startActivityDelegate = new StartActivityDelegate() {
            public void startActivityForResult(Intent intent, int requestCode) {
                activity.startActivityForResult(intent, requestCode);
            }

            public Activity getActivityContext() {
                return activity;
            }
        };
    }

    /* access modifiers changed from: package-private */
    public void startOrContinueAuth(AuthorizationRequest request) {
        if (getInProgress()) {
            continueAuth();
        } else {
            authorize(request);
        }
    }

    /* access modifiers changed from: package-private */
    public void authorize(AuthorizationRequest request) {
        if (request != null) {
            if (this.pendingRequest != null) {
                throw new FacebookException("Attempted to authorize while a request is pending.");
            } else if (!request.needsNewTokenValidation() || checkInternetPermission()) {
                this.pendingRequest = request;
                this.handlersToTry = getHandlerTypes(request);
                tryNextHandler();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void continueAuth() {
        if (this.pendingRequest == null || this.currentHandler == null) {
            throw new FacebookException("Attempted to continue authorization without a pending request.");
        } else if (this.currentHandler.needsRestart()) {
            this.currentHandler.cancel();
            tryCurrentHandler();
        }
    }

    /* access modifiers changed from: package-private */
    public boolean getInProgress() {
        return (this.pendingRequest == null || this.currentHandler == null) ? false : true;
    }

    /* access modifiers changed from: package-private */
    public void cancelCurrentHandler() {
        if (this.currentHandler != null) {
            this.currentHandler.cancel();
        }
    }

    /* access modifiers changed from: package-private */
    public boolean onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == this.pendingRequest.getRequestCode()) {
            return this.currentHandler.onActivityResult(requestCode, resultCode, data);
        }
        return false;
    }

    private List<AuthHandler> getHandlerTypes(AuthorizationRequest request) {
        ArrayList<AuthHandler> handlers = new ArrayList<>();
        SessionLoginBehavior behavior = request.getLoginBehavior();
        if (behavior.allowsKatanaAuth()) {
            if (!request.isLegacy()) {
                handlers.add(new GetTokenAuthHandler());
            }
            handlers.add(new KatanaProxyAuthHandler());
        }
        if (behavior.allowsWebViewAuth()) {
            handlers.add(new WebViewAuthHandler());
        }
        return handlers;
    }

    /* access modifiers changed from: package-private */
    public boolean checkInternetPermission() {
        if (this.checkedInternetPermission) {
            return true;
        }
        if (checkPermission("android.permission.INTERNET") != 0) {
            complete(Result.createErrorResult(this.pendingRequest, this.context.getString(R.string.com_facebook_internet_permission_error_title), this.context.getString(R.string.com_facebook_internet_permission_error_message)));
            return false;
        }
        this.checkedInternetPermission = true;
        return true;
    }

    /* access modifiers changed from: package-private */
    public void tryNextHandler() {
        if (this.currentHandler != null) {
            logAuthorizationMethodComplete(this.currentHandler.getNameForLogging(), EVENT_PARAM_METHOD_RESULT_SKIPPED, (String) null, (String) null, this.currentHandler.methodLoggingExtras);
        }
        while (this.handlersToTry != null && !this.handlersToTry.isEmpty()) {
            this.currentHandler = this.handlersToTry.remove(0);
            if (tryCurrentHandler()) {
                return;
            }
        }
        if (this.pendingRequest != null) {
            completeWithFailure();
        }
    }

    private void completeWithFailure() {
        complete(Result.createErrorResult(this.pendingRequest, "Login attempt failed.", (String) null));
    }

    private void addLoggingExtra(String key, String value, boolean accumulate) {
        if (this.loggingExtras == null) {
            this.loggingExtras = new HashMap();
        }
        if (this.loggingExtras.containsKey(key) && accumulate) {
            value = this.loggingExtras.get(key) + "," + value;
        }
        this.loggingExtras.put(key, value);
    }

    /* access modifiers changed from: package-private */
    public boolean tryCurrentHandler() {
        boolean tried = false;
        if (!this.currentHandler.needsInternetPermission() || checkInternetPermission()) {
            tried = this.currentHandler.tryAuthorize(this.pendingRequest);
            if (tried) {
                logAuthorizationMethodStart(this.currentHandler.getNameForLogging());
            } else {
                addLoggingExtra(EVENT_EXTRAS_NOT_TRIED, this.currentHandler.getNameForLogging(), true);
            }
        } else {
            addLoggingExtra(EVENT_EXTRAS_MISSING_INTERNET_PERMISSION, AppEventsConstants.EVENT_PARAM_VALUE_YES, false);
        }
        return tried;
    }

    /* access modifiers changed from: package-private */
    public void completeAndValidate(Result outcome) {
        if (outcome.token == null || !this.pendingRequest.needsNewTokenValidation()) {
            complete(outcome);
        } else {
            validateSameFbidAndFinish(outcome);
        }
    }

    /* access modifiers changed from: package-private */
    public void complete(Result outcome) {
        if (this.currentHandler != null) {
            logAuthorizationMethodComplete(this.currentHandler.getNameForLogging(), outcome, this.currentHandler.methodLoggingExtras);
        }
        if (this.loggingExtras != null) {
            outcome.loggingExtras = this.loggingExtras;
        }
        this.handlersToTry = null;
        this.currentHandler = null;
        this.pendingRequest = null;
        this.loggingExtras = null;
        notifyOnCompleteListener(outcome);
    }

    /* access modifiers changed from: package-private */
    public OnCompletedListener getOnCompletedListener() {
        return this.onCompletedListener;
    }

    /* access modifiers changed from: package-private */
    public void setOnCompletedListener(OnCompletedListener onCompletedListener2) {
        this.onCompletedListener = onCompletedListener2;
    }

    /* access modifiers changed from: package-private */
    public BackgroundProcessingListener getBackgroundProcessingListener() {
        return this.backgroundProcessingListener;
    }

    /* access modifiers changed from: package-private */
    public void setBackgroundProcessingListener(BackgroundProcessingListener backgroundProcessingListener2) {
        this.backgroundProcessingListener = backgroundProcessingListener2;
    }

    /* access modifiers changed from: package-private */
    public StartActivityDelegate getStartActivityDelegate() {
        if (this.startActivityDelegate != null) {
            return this.startActivityDelegate;
        }
        if (this.pendingRequest != null) {
            return new StartActivityDelegate() {
                public void startActivityForResult(Intent intent, int requestCode) {
                    AuthorizationClient.this.pendingRequest.getStartActivityDelegate().startActivityForResult(intent, requestCode);
                }

                public Activity getActivityContext() {
                    return AuthorizationClient.this.pendingRequest.getStartActivityDelegate().getActivityContext();
                }
            };
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public int checkPermission(String permission) {
        return this.context.checkCallingOrSelfPermission(permission);
    }

    /* access modifiers changed from: package-private */
    public void validateSameFbidAndFinish(Result pendingResult) {
        if (pendingResult.token == null) {
            throw new FacebookException("Can't validate without a token");
        }
        RequestBatch batch = createReauthValidationBatch(pendingResult);
        notifyBackgroundProcessingStart();
        batch.executeAsync();
    }

    /* access modifiers changed from: package-private */
    public RequestBatch createReauthValidationBatch(Result pendingResult) {
        final ArrayList<String> fbids = new ArrayList<>();
        final ArrayList<String> grantedPermissions = new ArrayList<>();
        final ArrayList<String> declinedPermissions = new ArrayList<>();
        String newToken = pendingResult.token.getToken();
        Request.Callback meCallback = new Request.Callback() {
            public void onCompleted(Response response) {
                try {
                    GraphUser user = (GraphUser) response.getGraphObjectAs(GraphUser.class);
                    if (user != null) {
                        fbids.add(user.getId());
                    }
                } catch (Exception e) {
                }
            }
        };
        String validateSameFbidAsToken = this.pendingRequest.getPreviousAccessToken();
        Request requestCurrentTokenMe = createGetProfileIdRequest(validateSameFbidAsToken);
        requestCurrentTokenMe.setCallback(meCallback);
        Request requestNewTokenMe = createGetProfileIdRequest(newToken);
        requestNewTokenMe.setCallback(meCallback);
        Request requestCurrentTokenPermissions = createGetPermissionsRequest(validateSameFbidAsToken);
        requestCurrentTokenPermissions.setCallback(new Request.Callback() {
            public void onCompleted(Response response) {
                try {
                    Session.PermissionsPair permissionsPair = Session.handlePermissionResponse(response);
                    if (permissionsPair != null) {
                        grantedPermissions.addAll(permissionsPair.getGrantedPermissions());
                        declinedPermissions.addAll(permissionsPair.getDeclinedPermissions());
                    }
                } catch (Exception e) {
                }
            }
        });
        RequestBatch batch = new RequestBatch(requestCurrentTokenMe, requestNewTokenMe, requestCurrentTokenPermissions);
        batch.setBatchApplicationId(this.pendingRequest.getApplicationId());
        final Result result = pendingResult;
        batch.addCallback(new RequestBatch.Callback() {
            public void onBatchCompleted(RequestBatch batch) {
                Result result;
                try {
                    if (fbids.size() != 2 || fbids.get(0) == null || fbids.get(1) == null || !((String) fbids.get(0)).equals(fbids.get(1))) {
                        result = Result.createErrorResult(AuthorizationClient.this.pendingRequest, "User logged in as different Facebook user.", (String) null);
                    } else {
                        result = Result.createTokenResult(AuthorizationClient.this.pendingRequest, AccessToken.createFromTokenWithRefreshedPermissions(result.token, grantedPermissions, declinedPermissions));
                    }
                    AuthorizationClient.this.complete(result);
                } catch (Exception ex) {
                    AuthorizationClient.this.complete(Result.createErrorResult(AuthorizationClient.this.pendingRequest, "Caught exception", ex.getMessage()));
                } finally {
                    AuthorizationClient.this.notifyBackgroundProcessingStop();
                }
            }
        });
        return batch;
    }

    /* access modifiers changed from: package-private */
    public Request createGetPermissionsRequest(String accessToken) {
        Bundle parameters = new Bundle();
        parameters.putString("access_token", accessToken);
        return new Request((Session) null, "me/permissions", parameters, HttpMethod.GET, (Request.Callback) null);
    }

    /* access modifiers changed from: package-private */
    public Request createGetProfileIdRequest(String accessToken) {
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id");
        parameters.putString("access_token", accessToken);
        return new Request((Session) null, "me", parameters, HttpMethod.GET, (Request.Callback) null);
    }

    private AppEventsLogger getAppEventsLogger() {
        if (this.appEventsLogger == null || !this.appEventsLogger.getApplicationId().equals(this.pendingRequest.getApplicationId())) {
            this.appEventsLogger = AppEventsLogger.newLogger(this.context, this.pendingRequest.getApplicationId());
        }
        return this.appEventsLogger;
    }

    private void notifyOnCompleteListener(Result outcome) {
        if (this.onCompletedListener != null) {
            this.onCompletedListener.onCompleted(outcome);
        }
    }

    /* access modifiers changed from: private */
    public void notifyBackgroundProcessingStart() {
        if (this.backgroundProcessingListener != null) {
            this.backgroundProcessingListener.onBackgroundProcessingStarted();
        }
    }

    /* access modifiers changed from: private */
    public void notifyBackgroundProcessingStop() {
        if (this.backgroundProcessingListener != null) {
            this.backgroundProcessingListener.onBackgroundProcessingStopped();
        }
    }

    private void logAuthorizationMethodStart(String method) {
        Bundle bundle = newAuthorizationLoggingBundle(this.pendingRequest.getAuthId());
        bundle.putLong(EVENT_PARAM_TIMESTAMP, System.currentTimeMillis());
        bundle.putString(EVENT_PARAM_METHOD, method);
        getAppEventsLogger().logSdkEvent(EVENT_NAME_LOGIN_METHOD_START, (Double) null, bundle);
    }

    private void logAuthorizationMethodComplete(String method, Result result, Map<String, String> loggingExtras2) {
        logAuthorizationMethodComplete(method, result.code.getLoggingValue(), result.errorMessage, result.errorCode, loggingExtras2);
    }

    private void logAuthorizationMethodComplete(String method, String result, String errorMessage, String errorCode, Map<String, String> loggingExtras2) {
        Bundle bundle;
        if (this.pendingRequest == null) {
            bundle = newAuthorizationLoggingBundle("");
            bundle.putString(EVENT_PARAM_LOGIN_RESULT, Result.Code.ERROR.getLoggingValue());
            bundle.putString(EVENT_PARAM_ERROR_MESSAGE, "Unexpected call to logAuthorizationMethodComplete with null pendingRequest.");
        } else {
            bundle = newAuthorizationLoggingBundle(this.pendingRequest.getAuthId());
            if (result != null) {
                bundle.putString(EVENT_PARAM_LOGIN_RESULT, result);
            }
            if (errorMessage != null) {
                bundle.putString(EVENT_PARAM_ERROR_MESSAGE, errorMessage);
            }
            if (errorCode != null) {
                bundle.putString(EVENT_PARAM_ERROR_CODE, errorCode);
            }
            if (loggingExtras2 != null && !loggingExtras2.isEmpty()) {
                bundle.putString(EVENT_PARAM_EXTRAS, new JSONObject(loggingExtras2).toString());
            }
        }
        bundle.putString(EVENT_PARAM_METHOD, method);
        bundle.putLong(EVENT_PARAM_TIMESTAMP, System.currentTimeMillis());
        getAppEventsLogger().logSdkEvent(EVENT_NAME_LOGIN_METHOD_COMPLETE, (Double) null, bundle);
    }

    static Bundle newAuthorizationLoggingBundle(String authLoggerId) {
        Bundle bundle = new Bundle();
        bundle.putLong(EVENT_PARAM_TIMESTAMP, System.currentTimeMillis());
        bundle.putString(EVENT_PARAM_AUTH_LOGGER_ID, authLoggerId);
        bundle.putString(EVENT_PARAM_METHOD, "");
        bundle.putString(EVENT_PARAM_LOGIN_RESULT, "");
        bundle.putString(EVENT_PARAM_ERROR_MESSAGE, "");
        bundle.putString(EVENT_PARAM_ERROR_CODE, "");
        bundle.putString(EVENT_PARAM_EXTRAS, "");
        return bundle;
    }

    abstract class AuthHandler implements Serializable {
        private static final long serialVersionUID = 1;
        Map<String, String> methodLoggingExtras;

        /* access modifiers changed from: package-private */
        public abstract String getNameForLogging();

        /* access modifiers changed from: package-private */
        public abstract boolean tryAuthorize(AuthorizationRequest authorizationRequest);

        AuthHandler() {
        }

        /* access modifiers changed from: package-private */
        public boolean onActivityResult(int requestCode, int resultCode, Intent data) {
            return false;
        }

        /* access modifiers changed from: package-private */
        public boolean needsRestart() {
            return false;
        }

        /* access modifiers changed from: package-private */
        public boolean needsInternetPermission() {
            return false;
        }

        /* access modifiers changed from: package-private */
        public void cancel() {
        }

        /* access modifiers changed from: protected */
        public void addLoggingExtra(String key, Object value) {
            if (this.methodLoggingExtras == null) {
                this.methodLoggingExtras = new HashMap();
            }
            this.methodLoggingExtras.put(key, value == null ? null : value.toString());
        }
    }

    class WebViewAuthHandler extends AuthHandler {
        private static final long serialVersionUID = 1;
        private String applicationId;
        private String e2e;
        private transient WebDialog loginDialog;

        WebViewAuthHandler() {
            super();
        }

        /* access modifiers changed from: package-private */
        public String getNameForLogging() {
            return "web_view";
        }

        /* access modifiers changed from: package-private */
        public boolean needsRestart() {
            return true;
        }

        /* access modifiers changed from: package-private */
        public boolean needsInternetPermission() {
            return true;
        }

        /* access modifiers changed from: package-private */
        public void cancel() {
            if (this.loginDialog != null) {
                this.loginDialog.dismiss();
                this.loginDialog = null;
            }
        }

        /* access modifiers changed from: package-private */
        public boolean tryAuthorize(final AuthorizationRequest request) {
            this.applicationId = request.getApplicationId();
            Bundle parameters = new Bundle();
            if (!Utility.isNullOrEmpty(request.getPermissions())) {
                String scope = TextUtils.join(",", request.getPermissions());
                parameters.putString("scope", scope);
                addLoggingExtra("scope", scope);
            }
            parameters.putString("default_audience", request.getDefaultAudience().getNativeProtocolAudience());
            String previousToken = request.getPreviousAccessToken();
            if (Utility.isNullOrEmpty(previousToken) || !previousToken.equals(loadCookieToken())) {
                Utility.clearFacebookCookies(AuthorizationClient.this.context);
                addLoggingExtra("access_token", AppEventsConstants.EVENT_PARAM_VALUE_NO);
            } else {
                parameters.putString("access_token", previousToken);
                addLoggingExtra("access_token", AppEventsConstants.EVENT_PARAM_VALUE_YES);
            }
            WebDialog.OnCompleteListener listener = new WebDialog.OnCompleteListener() {
                public void onComplete(Bundle values, FacebookException error) {
                    WebViewAuthHandler.this.onWebDialogComplete(request, values, error);
                }
            };
            this.e2e = AuthorizationClient.getE2E();
            addLoggingExtra("e2e", this.e2e);
            this.loginDialog = ((WebDialog.Builder) new AuthDialogBuilder(AuthorizationClient.this.getStartActivityDelegate().getActivityContext(), this.applicationId, parameters).setE2E(this.e2e).setIsRerequest(request.isRerequest()).setOnCompleteListener(listener)).build();
            this.loginDialog.show();
            return true;
        }

        /* access modifiers changed from: package-private */
        public void onWebDialogComplete(AuthorizationRequest request, Bundle values, FacebookException error) {
            Result outcome;
            if (values != null) {
                if (values.containsKey("e2e")) {
                    this.e2e = values.getString("e2e");
                }
                AccessToken token = AccessToken.createFromWebBundle(request.getPermissions(), values, AccessTokenSource.WEB_VIEW);
                outcome = Result.createTokenResult(AuthorizationClient.this.pendingRequest, token);
                CookieSyncManager.createInstance(AuthorizationClient.this.context).sync();
                saveCookieToken(token.getToken());
            } else if (error instanceof FacebookOperationCanceledException) {
                outcome = Result.createCancelResult(AuthorizationClient.this.pendingRequest, "User canceled log in.");
            } else {
                this.e2e = null;
                String errorCode = null;
                String errorMessage = error.getMessage();
                if (error instanceof FacebookServiceException) {
                    FacebookRequestError requestError = ((FacebookServiceException) error).getRequestError();
                    errorCode = String.format("%d", new Object[]{Integer.valueOf(requestError.getErrorCode())});
                    errorMessage = requestError.toString();
                }
                outcome = Result.createErrorResult(AuthorizationClient.this.pendingRequest, (String) null, errorMessage, errorCode);
            }
            if (!Utility.isNullOrEmpty(this.e2e)) {
                AuthorizationClient.this.logWebLoginCompleted(this.applicationId, this.e2e);
            }
            AuthorizationClient.this.completeAndValidate(outcome);
        }

        private void saveCookieToken(String token) {
            SharedPreferences.Editor editor = AuthorizationClient.this.getStartActivityDelegate().getActivityContext().getSharedPreferences(AuthorizationClient.WEB_VIEW_AUTH_HANDLER_STORE, 0).edit();
            editor.putString(AuthorizationClient.WEB_VIEW_AUTH_HANDLER_TOKEN_KEY, token);
            if (!editor.commit()) {
                Utility.logd(AuthorizationClient.TAG, "Could not update saved web view auth handler token.");
            }
        }

        private String loadCookieToken() {
            return AuthorizationClient.this.getStartActivityDelegate().getActivityContext().getSharedPreferences(AuthorizationClient.WEB_VIEW_AUTH_HANDLER_STORE, 0).getString(AuthorizationClient.WEB_VIEW_AUTH_HANDLER_TOKEN_KEY, "");
        }
    }

    class GetTokenAuthHandler extends AuthHandler {
        private static final long serialVersionUID = 1;
        private transient GetTokenClient getTokenClient;

        GetTokenAuthHandler() {
            super();
        }

        /* access modifiers changed from: package-private */
        public String getNameForLogging() {
            return "get_token";
        }

        /* access modifiers changed from: package-private */
        public void cancel() {
            if (this.getTokenClient != null) {
                this.getTokenClient.cancel();
                this.getTokenClient = null;
            }
        }

        /* access modifiers changed from: package-private */
        public boolean needsRestart() {
            return this.getTokenClient == null;
        }

        /* access modifiers changed from: package-private */
        public boolean tryAuthorize(final AuthorizationRequest request) {
            this.getTokenClient = new GetTokenClient(AuthorizationClient.this.context, request.getApplicationId());
            if (!this.getTokenClient.start()) {
                return false;
            }
            AuthorizationClient.this.notifyBackgroundProcessingStart();
            this.getTokenClient.setCompletedListener(new PlatformServiceClient.CompletedListener() {
                public void completed(Bundle result) {
                    GetTokenAuthHandler.this.getTokenCompleted(request, result);
                }
            });
            return true;
        }

        /* access modifiers changed from: package-private */
        public void getTokenCompleted(AuthorizationRequest request, Bundle result) {
            this.getTokenClient = null;
            AuthorizationClient.this.notifyBackgroundProcessingStop();
            if (result != null) {
                ArrayList<String> currentPermissions = result.getStringArrayList(NativeProtocol.EXTRA_PERMISSIONS);
                List<String> permissions = request.getPermissions();
                if (currentPermissions == null || (permissions != null && !currentPermissions.containsAll(permissions))) {
                    List<String> newPermissions = new ArrayList<>();
                    for (String permission : permissions) {
                        if (!currentPermissions.contains(permission)) {
                            newPermissions.add(permission);
                        }
                    }
                    if (!newPermissions.isEmpty()) {
                        addLoggingExtra(AuthorizationClient.EVENT_EXTRAS_NEW_PERMISSIONS, TextUtils.join(",", newPermissions));
                    }
                    request.setPermissions(newPermissions);
                } else {
                    AuthorizationClient.this.completeAndValidate(Result.createTokenResult(AuthorizationClient.this.pendingRequest, AccessToken.createFromNativeLogin(result, AccessTokenSource.FACEBOOK_APPLICATION_SERVICE)));
                    return;
                }
            }
            AuthorizationClient.this.tryNextHandler();
        }
    }

    abstract class KatanaAuthHandler extends AuthHandler {
        private static final long serialVersionUID = 1;

        KatanaAuthHandler() {
            super();
        }

        /* access modifiers changed from: protected */
        public boolean tryIntent(Intent intent, int requestCode) {
            if (intent == null) {
                return false;
            }
            try {
                AuthorizationClient.this.getStartActivityDelegate().startActivityForResult(intent, requestCode);
                return true;
            } catch (ActivityNotFoundException e) {
                return false;
            }
        }
    }

    class KatanaProxyAuthHandler extends KatanaAuthHandler {
        private static final long serialVersionUID = 1;
        private String applicationId;

        KatanaProxyAuthHandler() {
            super();
        }

        /* access modifiers changed from: package-private */
        public String getNameForLogging() {
            return "katana_proxy_auth";
        }

        /* access modifiers changed from: package-private */
        public boolean tryAuthorize(AuthorizationRequest request) {
            this.applicationId = request.getApplicationId();
            String e2e = AuthorizationClient.getE2E();
            Intent intent = NativeProtocol.createProxyAuthIntent(AuthorizationClient.this.context, request.getApplicationId(), request.getPermissions(), e2e, request.isRerequest(), request.getDefaultAudience());
            addLoggingExtra("e2e", e2e);
            return tryIntent(intent, request.getRequestCode());
        }

        /* access modifiers changed from: package-private */
        public boolean onActivityResult(int requestCode, int resultCode, Intent data) {
            Result outcome;
            if (data == null) {
                outcome = Result.createCancelResult(AuthorizationClient.this.pendingRequest, "Operation canceled");
            } else if (resultCode == 0) {
                outcome = Result.createCancelResult(AuthorizationClient.this.pendingRequest, data.getStringExtra("error"));
            } else if (resultCode != -1) {
                outcome = Result.createErrorResult(AuthorizationClient.this.pendingRequest, "Unexpected resultCode from authorization.", (String) null);
            } else {
                outcome = handleResultOk(data);
            }
            if (outcome != null) {
                AuthorizationClient.this.completeAndValidate(outcome);
                return true;
            }
            AuthorizationClient.this.tryNextHandler();
            return true;
        }

        private Result handleResultOk(Intent data) {
            Bundle extras = data.getExtras();
            String error = extras.getString("error");
            if (error == null) {
                error = extras.getString("error_type");
            }
            String errorCode = extras.getString("error_code");
            String errorMessage = extras.getString("error_message");
            if (errorMessage == null) {
                errorMessage = extras.getString("error_description");
            }
            String e2e = extras.getString("e2e");
            if (!Utility.isNullOrEmpty(e2e)) {
                AuthorizationClient.this.logWebLoginCompleted(this.applicationId, e2e);
            }
            if (error == null && errorCode == null && errorMessage == null) {
                return Result.createTokenResult(AuthorizationClient.this.pendingRequest, AccessToken.createFromWebBundle(AuthorizationClient.this.pendingRequest.getPermissions(), extras, AccessTokenSource.FACEBOOK_APPLICATION_WEB));
            } else if (ServerProtocol.errorsProxyAuthDisabled.contains(error)) {
                return null;
            } else {
                if (ServerProtocol.errorsUserCanceled.contains(error)) {
                    return Result.createCancelResult(AuthorizationClient.this.pendingRequest, (String) null);
                }
                return Result.createErrorResult(AuthorizationClient.this.pendingRequest, error, errorMessage, errorCode);
            }
        }
    }

    /* access modifiers changed from: private */
    public static String getE2E() {
        JSONObject e2e = new JSONObject();
        try {
            e2e.put("init", System.currentTimeMillis());
        } catch (JSONException e) {
        }
        return e2e.toString();
    }

    /* access modifiers changed from: private */
    public void logWebLoginCompleted(String applicationId, String e2e) {
        AppEventsLogger appEventsLogger2 = AppEventsLogger.newLogger(this.context, applicationId);
        Bundle parameters = new Bundle();
        parameters.putString(AnalyticsEvents.PARAMETER_WEB_LOGIN_E2E, e2e);
        parameters.putLong(AnalyticsEvents.PARAMETER_WEB_LOGIN_SWITCHBACK_TIME, System.currentTimeMillis());
        parameters.putString("app_id", applicationId);
        appEventsLogger2.logSdkEvent(AnalyticsEvents.EVENT_WEB_LOGIN_COMPLETE, (Double) null, parameters);
    }

    static class AuthDialogBuilder extends WebDialog.Builder {
        private static final String OAUTH_DIALOG = "oauth";
        static final String REDIRECT_URI = "fbconnect://success";
        private String e2e;
        private boolean isRerequest;

        public AuthDialogBuilder(Context context, String applicationId, Bundle parameters) {
            super(context, applicationId, OAUTH_DIALOG, parameters);
        }

        public AuthDialogBuilder setE2E(String e2e2) {
            this.e2e = e2e2;
            return this;
        }

        public AuthDialogBuilder setIsRerequest(boolean isRerequest2) {
            this.isRerequest = isRerequest2;
            return this;
        }

        public WebDialog build() {
            Bundle parameters = getParameters();
            parameters.putString(ServerProtocol.DIALOG_PARAM_REDIRECT_URI, "fbconnect://success");
            parameters.putString("client_id", getApplicationId());
            parameters.putString("e2e", this.e2e);
            parameters.putString(ServerProtocol.DIALOG_PARAM_RESPONSE_TYPE, ServerProtocol.DIALOG_RESPONSE_TYPE_TOKEN);
            parameters.putString(ServerProtocol.DIALOG_PARAM_RETURN_SCOPES, ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
            if (this.isRerequest && !Settings.getPlatformCompatibilityEnabled()) {
                parameters.putString(ServerProtocol.DIALOG_PARAM_AUTH_TYPE, ServerProtocol.DIALOG_REREQUEST_AUTH_TYPE);
            }
            return new WebDialog(getContext(), OAUTH_DIALOG, parameters, getTheme(), getListener());
        }
    }

    static class AuthorizationRequest implements Serializable {
        private static final long serialVersionUID = 1;
        private final String applicationId;
        private final String authId;
        private final SessionDefaultAudience defaultAudience;
        private boolean isLegacy = false;
        private boolean isRerequest = false;
        private final SessionLoginBehavior loginBehavior;
        private List<String> permissions;
        private final String previousAccessToken;
        private final int requestCode;
        private final transient StartActivityDelegate startActivityDelegate;

        AuthorizationRequest(SessionLoginBehavior loginBehavior2, int requestCode2, boolean isLegacy2, List<String> permissions2, SessionDefaultAudience defaultAudience2, String applicationId2, String validateSameFbidAsToken, StartActivityDelegate startActivityDelegate2, String authId2) {
            this.loginBehavior = loginBehavior2;
            this.requestCode = requestCode2;
            this.isLegacy = isLegacy2;
            this.permissions = permissions2;
            this.defaultAudience = defaultAudience2;
            this.applicationId = applicationId2;
            this.previousAccessToken = validateSameFbidAsToken;
            this.startActivityDelegate = startActivityDelegate2;
            this.authId = authId2;
        }

        /* access modifiers changed from: package-private */
        public StartActivityDelegate getStartActivityDelegate() {
            return this.startActivityDelegate;
        }

        /* access modifiers changed from: package-private */
        public List<String> getPermissions() {
            return this.permissions;
        }

        /* access modifiers changed from: package-private */
        public void setPermissions(List<String> permissions2) {
            this.permissions = permissions2;
        }

        /* access modifiers changed from: package-private */
        public SessionLoginBehavior getLoginBehavior() {
            return this.loginBehavior;
        }

        /* access modifiers changed from: package-private */
        public int getRequestCode() {
            return this.requestCode;
        }

        /* access modifiers changed from: package-private */
        public SessionDefaultAudience getDefaultAudience() {
            return this.defaultAudience;
        }

        /* access modifiers changed from: package-private */
        public String getApplicationId() {
            return this.applicationId;
        }

        /* access modifiers changed from: package-private */
        public boolean isLegacy() {
            return this.isLegacy;
        }

        /* access modifiers changed from: package-private */
        public void setIsLegacy(boolean isLegacy2) {
            this.isLegacy = isLegacy2;
        }

        /* access modifiers changed from: package-private */
        public String getPreviousAccessToken() {
            return this.previousAccessToken;
        }

        /* access modifiers changed from: package-private */
        public boolean needsNewTokenValidation() {
            return this.previousAccessToken != null && !this.isLegacy;
        }

        /* access modifiers changed from: package-private */
        public String getAuthId() {
            return this.authId;
        }

        /* access modifiers changed from: package-private */
        public boolean isRerequest() {
            return this.isRerequest;
        }

        /* access modifiers changed from: package-private */
        public void setRerequest(boolean isRerequest2) {
            this.isRerequest = isRerequest2;
        }
    }

    static class Result implements Serializable {
        private static final long serialVersionUID = 1;
        final Code code;
        final String errorCode;
        final String errorMessage;
        Map<String, String> loggingExtras;
        final AuthorizationRequest request;
        final AccessToken token;

        enum Code {
            SUCCESS(Response.SUCCESS_KEY),
            CANCEL(FacebookDialog.COMPLETION_GESTURE_CANCEL),
            ERROR("error");
            
            private final String loggingValue;

            private Code(String loggingValue2) {
                this.loggingValue = loggingValue2;
            }

            /* access modifiers changed from: package-private */
            public String getLoggingValue() {
                return this.loggingValue;
            }
        }

        private Result(AuthorizationRequest request2, Code code2, AccessToken token2, String errorMessage2, String errorCode2) {
            this.request = request2;
            this.token = token2;
            this.errorMessage = errorMessage2;
            this.code = code2;
            this.errorCode = errorCode2;
        }

        static Result createTokenResult(AuthorizationRequest request2, AccessToken token2) {
            return new Result(request2, Code.SUCCESS, token2, (String) null, (String) null);
        }

        static Result createCancelResult(AuthorizationRequest request2, String message) {
            return new Result(request2, Code.CANCEL, (AccessToken) null, message, (String) null);
        }

        static Result createErrorResult(AuthorizationRequest request2, String errorType, String errorDescription) {
            return createErrorResult(request2, errorType, errorDescription, (String) null);
        }

        static Result createErrorResult(AuthorizationRequest request2, String errorType, String errorDescription, String errorCode2) {
            return new Result(request2, Code.ERROR, (AccessToken) null, TextUtils.join(": ", Utility.asListNoNulls(errorType, errorDescription)), errorCode2);
        }
    }
}
