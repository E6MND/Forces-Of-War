package com.facebook;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;
import com.facebook.AuthorizationClient;
import com.facebook.Request;
import com.facebook.internal.NativeProtocol;
import com.facebook.internal.ServerProtocol;
import com.facebook.internal.SessionAuthorizationType;
import com.facebook.internal.Utility;
import com.facebook.internal.Validate;
import com.facebook.model.GraphMultiResult;
import com.facebook.model.GraphObject;
import com.facebook.model.GraphObjectList;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;
import org.xwalk.core.internal.extension.api.messaging.MessagingSmsConsts;

public class Session implements Serializable {
    public static final String ACTION_ACTIVE_SESSION_CLOSED = "com.facebook.sdk.ACTIVE_SESSION_CLOSED";
    public static final String ACTION_ACTIVE_SESSION_OPENED = "com.facebook.sdk.ACTIVE_SESSION_OPENED";
    public static final String ACTION_ACTIVE_SESSION_SET = "com.facebook.sdk.ACTIVE_SESSION_SET";
    public static final String ACTION_ACTIVE_SESSION_UNSET = "com.facebook.sdk.ACTIVE_SESSION_UNSET";
    private static final String AUTH_BUNDLE_SAVE_KEY = "com.facebook.sdk.Session.authBundleKey";
    public static final int DEFAULT_AUTHORIZE_ACTIVITY_CODE = 64206;
    private static final String MANAGE_PERMISSION_PREFIX = "manage";
    private static final Set<String> OTHER_PUBLISH_PERMISSIONS = new HashSet<String>() {
        {
            add("ads_management");
            add("create_event");
            add("rsvp_event");
        }
    };
    private static final String PUBLISH_PERMISSION_PREFIX = "publish";
    private static final String SESSION_BUNDLE_SAVE_KEY = "com.facebook.sdk.Session.saveSessionKey";
    private static final Object STATIC_LOCK = new Object();
    public static final String TAG = Session.class.getCanonicalName();
    private static final int TOKEN_EXTEND_RETRY_SECONDS = 3600;
    private static final int TOKEN_EXTEND_THRESHOLD_SECONDS = 86400;
    public static final String WEB_VIEW_ERROR_CODE_KEY = "com.facebook.sdk.WebViewErrorCode";
    public static final String WEB_VIEW_FAILING_URL_KEY = "com.facebook.sdk.FailingUrl";
    private static Session activeSession = null;
    private static final long serialVersionUID = 1;
    /* access modifiers changed from: private */
    public static volatile Context staticContext;
    private AppEventsLogger appEventsLogger;
    private String applicationId;
    private volatile Bundle authorizationBundle;
    private AuthorizationClient authorizationClient;
    /* access modifiers changed from: private */
    public AutoPublishAsyncTask autoPublishAsyncTask;
    /* access modifiers changed from: private */
    public final List<StatusCallback> callbacks;
    /* access modifiers changed from: private */
    public volatile TokenRefreshRequest currentTokenRefreshRequest;
    /* access modifiers changed from: private */
    public Handler handler;
    private Date lastAttemptedTokenExtendDate;
    /* access modifiers changed from: private */
    public final Object lock;
    private AuthorizationRequest pendingAuthorizationRequest;
    /* access modifiers changed from: private */
    public SessionState state;
    private TokenCachingStrategy tokenCachingStrategy;
    /* access modifiers changed from: private */
    public AccessToken tokenInfo;

    interface StartActivityDelegate {
        Activity getActivityContext();

        void startActivityForResult(Intent intent, int i);
    }

    public interface StatusCallback {
        void call(Session session, SessionState sessionState, Exception exc);
    }

    private static class SerializationProxyV1 implements Serializable {
        private static final long serialVersionUID = 7663436173185080063L;
        private final String applicationId;
        private final Date lastAttemptedTokenExtendDate;
        private final AuthorizationRequest pendingAuthorizationRequest;
        private final boolean shouldAutoPublish;
        private final SessionState state;
        private final AccessToken tokenInfo;

        SerializationProxyV1(String applicationId2, SessionState state2, AccessToken tokenInfo2, Date lastAttemptedTokenExtendDate2, boolean shouldAutoPublish2, AuthorizationRequest pendingAuthorizationRequest2) {
            this.applicationId = applicationId2;
            this.state = state2;
            this.tokenInfo = tokenInfo2;
            this.lastAttemptedTokenExtendDate = lastAttemptedTokenExtendDate2;
            this.shouldAutoPublish = shouldAutoPublish2;
            this.pendingAuthorizationRequest = pendingAuthorizationRequest2;
        }

        private Object readResolve() {
            return new Session(this.applicationId, this.state, this.tokenInfo, this.lastAttemptedTokenExtendDate, this.shouldAutoPublish, this.pendingAuthorizationRequest);
        }
    }

    private static class SerializationProxyV2 implements Serializable {
        private static final long serialVersionUID = 7663436173185080064L;
        private final String applicationId;
        private final Date lastAttemptedTokenExtendDate;
        private final AuthorizationRequest pendingAuthorizationRequest;
        private final Set<String> requestedPermissions;
        private final boolean shouldAutoPublish;
        private final SessionState state;
        private final AccessToken tokenInfo;

        SerializationProxyV2(String applicationId2, SessionState state2, AccessToken tokenInfo2, Date lastAttemptedTokenExtendDate2, boolean shouldAutoPublish2, AuthorizationRequest pendingAuthorizationRequest2, Set<String> requestedPermissions2) {
            this.applicationId = applicationId2;
            this.state = state2;
            this.tokenInfo = tokenInfo2;
            this.lastAttemptedTokenExtendDate = lastAttemptedTokenExtendDate2;
            this.shouldAutoPublish = shouldAutoPublish2;
            this.pendingAuthorizationRequest = pendingAuthorizationRequest2;
            this.requestedPermissions = requestedPermissions2;
        }

        private Object readResolve() {
            return new Session(this.applicationId, this.state, this.tokenInfo, this.lastAttemptedTokenExtendDate, this.shouldAutoPublish, this.pendingAuthorizationRequest, this.requestedPermissions);
        }
    }

    private Session(String applicationId2, SessionState state2, AccessToken tokenInfo2, Date lastAttemptedTokenExtendDate2, boolean shouldAutoPublish, AuthorizationRequest pendingAuthorizationRequest2) {
        this.lastAttemptedTokenExtendDate = new Date(0);
        this.lock = new Object();
        this.applicationId = applicationId2;
        this.state = state2;
        this.tokenInfo = tokenInfo2;
        this.lastAttemptedTokenExtendDate = lastAttemptedTokenExtendDate2;
        this.pendingAuthorizationRequest = pendingAuthorizationRequest2;
        this.handler = new Handler(Looper.getMainLooper());
        this.currentTokenRefreshRequest = null;
        this.tokenCachingStrategy = null;
        this.callbacks = new ArrayList();
    }

    private Session(String applicationId2, SessionState state2, AccessToken tokenInfo2, Date lastAttemptedTokenExtendDate2, boolean shouldAutoPublish, AuthorizationRequest pendingAuthorizationRequest2, Set<String> set) {
        this.lastAttemptedTokenExtendDate = new Date(0);
        this.lock = new Object();
        this.applicationId = applicationId2;
        this.state = state2;
        this.tokenInfo = tokenInfo2;
        this.lastAttemptedTokenExtendDate = lastAttemptedTokenExtendDate2;
        this.pendingAuthorizationRequest = pendingAuthorizationRequest2;
        this.handler = new Handler(Looper.getMainLooper());
        this.currentTokenRefreshRequest = null;
        this.tokenCachingStrategy = null;
        this.callbacks = new ArrayList();
    }

    public Session(Context currentContext) {
        this(currentContext, (String) null, (TokenCachingStrategy) null, true);
    }

    Session(Context context, String applicationId2, TokenCachingStrategy tokenCachingStrategy2) {
        this(context, applicationId2, tokenCachingStrategy2, true);
    }

    Session(Context context, String applicationId2, TokenCachingStrategy tokenCachingStrategy2, boolean loadTokenFromCache) {
        Bundle tokenState = null;
        this.lastAttemptedTokenExtendDate = new Date(0);
        this.lock = new Object();
        if (context != null && applicationId2 == null) {
            applicationId2 = Utility.getMetadataApplicationId(context);
        }
        Validate.notNull(applicationId2, "applicationId");
        initializeStaticContext(context);
        tokenCachingStrategy2 = tokenCachingStrategy2 == null ? new SharedPreferencesTokenCachingStrategy(staticContext) : tokenCachingStrategy2;
        this.applicationId = applicationId2;
        this.tokenCachingStrategy = tokenCachingStrategy2;
        this.state = SessionState.CREATED;
        this.pendingAuthorizationRequest = null;
        this.callbacks = new ArrayList();
        this.handler = new Handler(Looper.getMainLooper());
        tokenState = loadTokenFromCache ? tokenCachingStrategy2.load() : tokenState;
        if (TokenCachingStrategy.hasTokenInformation(tokenState)) {
            Date cachedExpirationDate = TokenCachingStrategy.getDate(tokenState, TokenCachingStrategy.EXPIRATION_DATE_KEY);
            Date now = new Date();
            if (cachedExpirationDate == null || cachedExpirationDate.before(now)) {
                tokenCachingStrategy2.clear();
                this.tokenInfo = AccessToken.createEmptyToken();
                return;
            }
            this.tokenInfo = AccessToken.createFromCache(tokenState);
            this.state = SessionState.CREATED_TOKEN_LOADED;
            return;
        }
        this.tokenInfo = AccessToken.createEmptyToken();
    }

    public final Bundle getAuthorizationBundle() {
        Bundle bundle;
        synchronized (this.lock) {
            bundle = this.authorizationBundle;
        }
        return bundle;
    }

    public final boolean isOpened() {
        boolean isOpened;
        synchronized (this.lock) {
            isOpened = this.state.isOpened();
        }
        return isOpened;
    }

    public final boolean isClosed() {
        boolean isClosed;
        synchronized (this.lock) {
            isClosed = this.state.isClosed();
        }
        return isClosed;
    }

    public final SessionState getState() {
        SessionState sessionState;
        synchronized (this.lock) {
            sessionState = this.state;
        }
        return sessionState;
    }

    public final String getApplicationId() {
        return this.applicationId;
    }

    public final String getAccessToken() {
        String token;
        synchronized (this.lock) {
            token = this.tokenInfo == null ? null : this.tokenInfo.getToken();
        }
        return token;
    }

    public final Date getExpirationDate() {
        Date expires;
        synchronized (this.lock) {
            expires = this.tokenInfo == null ? null : this.tokenInfo.getExpires();
        }
        return expires;
    }

    public final List<String> getPermissions() {
        List<String> permissions;
        synchronized (this.lock) {
            permissions = this.tokenInfo == null ? null : this.tokenInfo.getPermissions();
        }
        return permissions;
    }

    public boolean isPermissionGranted(String permission) {
        List<String> grantedPermissions = getPermissions();
        if (grantedPermissions != null) {
            return grantedPermissions.contains(permission);
        }
        return false;
    }

    public final List<String> getDeclinedPermissions() {
        List<String> declinedPermissions;
        synchronized (this.lock) {
            declinedPermissions = this.tokenInfo == null ? null : this.tokenInfo.getDeclinedPermissions();
        }
        return declinedPermissions;
    }

    public final void openForRead(OpenRequest openRequest) {
        open(openRequest, SessionAuthorizationType.READ);
    }

    public final void openForPublish(OpenRequest openRequest) {
        open(openRequest, SessionAuthorizationType.PUBLISH);
    }

    public final void open(AccessToken accessToken, StatusCallback callback) {
        synchronized (this.lock) {
            if (this.pendingAuthorizationRequest != null) {
                throw new UnsupportedOperationException("Session: an attempt was made to open a session that has a pending request.");
            } else if (this.state.isClosed()) {
                throw new UnsupportedOperationException("Session: an attempt was made to open a previously-closed session.");
            } else if (this.state == SessionState.CREATED || this.state == SessionState.CREATED_TOKEN_LOADED) {
                if (callback != null) {
                    addCallback(callback);
                }
                this.tokenInfo = accessToken;
                if (this.tokenCachingStrategy != null) {
                    this.tokenCachingStrategy.save(accessToken.toCacheBundle());
                }
                SessionState oldState = this.state;
                this.state = SessionState.OPENED;
                postStateChange(oldState, this.state, (Exception) null);
            } else {
                throw new UnsupportedOperationException("Session: an attempt was made to open an already opened session.");
            }
        }
        autoPublishAsync();
    }

    public final void requestNewReadPermissions(NewPermissionsRequest newPermissionsRequest) {
        requestNewPermissions(newPermissionsRequest, SessionAuthorizationType.READ);
    }

    public final void requestNewPublishPermissions(NewPermissionsRequest newPermissionsRequest) {
        requestNewPermissions(newPermissionsRequest, SessionAuthorizationType.PUBLISH);
    }

    public final void refreshPermissions() {
        Request request = new Request(this, "me/permissions");
        request.setCallback(new Request.Callback() {
            public void onCompleted(Response response) {
                PermissionsPair permissionsPair = Session.handlePermissionResponse(response);
                if (permissionsPair != null) {
                    synchronized (Session.this.lock) {
                        AccessToken unused = Session.this.tokenInfo = AccessToken.createFromTokenWithRefreshedPermissions(Session.this.tokenInfo, permissionsPair.getGrantedPermissions(), permissionsPair.getDeclinedPermissions());
                        Session.this.postStateChange(Session.this.state, SessionState.OPENED_TOKEN_UPDATED, (Exception) null);
                    }
                }
            }
        });
        request.executeAsync();
    }

    static class PermissionsPair {
        List<String> declinedPermissions;
        List<String> grantedPermissions;

        public PermissionsPair(List<String> grantedPermissions2, List<String> declinedPermissions2) {
            this.grantedPermissions = grantedPermissions2;
            this.declinedPermissions = declinedPermissions2;
        }

        public List<String> getGrantedPermissions() {
            return this.grantedPermissions;
        }

        public List<String> getDeclinedPermissions() {
            return this.declinedPermissions;
        }
    }

    static PermissionsPair handlePermissionResponse(Response response) {
        GraphMultiResult result;
        GraphObjectList<GraphObject> data;
        if (response.getError() != null || (result = (GraphMultiResult) response.getGraphObjectAs(GraphMultiResult.class)) == null || (data = result.getData()) == null || data.size() == 0) {
            return null;
        }
        List<String> grantedPermissions = new ArrayList<>(data.size());
        List<String> declinedPermissions = new ArrayList<>(data.size());
        GraphObject firstObject = (GraphObject) data.get(0);
        if (firstObject.getProperty("permission") != null) {
            for (GraphObject graphObject : data) {
                String permission = (String) graphObject.getProperty("permission");
                if (!permission.equals("installed")) {
                    String status = (String) graphObject.getProperty(MessagingSmsConsts.STATUS);
                    if (status.equals("granted")) {
                        grantedPermissions.add(permission);
                    } else if (status.equals("declined")) {
                        declinedPermissions.add(permission);
                    }
                }
            }
        } else {
            for (Map.Entry<String, Object> entry : firstObject.asMap().entrySet()) {
                if (!entry.getKey().equals("installed") && ((Integer) entry.getValue()).intValue() == 1) {
                    grantedPermissions.add(entry.getKey());
                }
            }
        }
        return new PermissionsPair(grantedPermissions, declinedPermissions);
    }

    public final boolean onActivityResult(Activity currentActivity, int requestCode, int resultCode, Intent data) {
        boolean z = true;
        Validate.notNull(currentActivity, "currentActivity");
        initializeStaticContext(currentActivity);
        synchronized (this.lock) {
            if (this.pendingAuthorizationRequest == null || requestCode != this.pendingAuthorizationRequest.getRequestCode()) {
                z = false;
            } else {
                Exception exception = null;
                AuthorizationClient.Result.Code code = AuthorizationClient.Result.Code.ERROR;
                if (data != null) {
                    AuthorizationClient.Result result = (AuthorizationClient.Result) data.getSerializableExtra("com.facebook.LoginActivity:Result");
                    if (result != null) {
                        handleAuthorizationResult(resultCode, result);
                    } else if (this.authorizationClient != null) {
                        this.authorizationClient.onActivityResult(requestCode, resultCode, data);
                    }
                } else if (resultCode == 0) {
                    exception = new FacebookOperationCanceledException("User canceled operation.");
                    code = AuthorizationClient.Result.Code.CANCEL;
                }
                if (exception == null) {
                    exception = new FacebookException("Unexpected call to Session.onActivityResult");
                }
                logAuthorizationComplete(code, (Map<String, String>) null, exception);
                finishAuthOrReauth((AccessToken) null, exception);
            }
        }
        return z;
    }

    public final void close() {
        synchronized (this.lock) {
            SessionState oldState = this.state;
            switch (this.state) {
                case CREATED:
                case OPENING:
                    this.state = SessionState.CLOSED_LOGIN_FAILED;
                    postStateChange(oldState, this.state, new FacebookException("Log in attempt aborted."));
                    break;
                case CREATED_TOKEN_LOADED:
                case OPENED:
                case OPENED_TOKEN_UPDATED:
                    this.state = SessionState.CLOSED;
                    postStateChange(oldState, this.state, (Exception) null);
                    break;
            }
        }
    }

    public final void closeAndClearTokenInformation() {
        if (this.tokenCachingStrategy != null) {
            this.tokenCachingStrategy.clear();
        }
        Utility.clearFacebookCookies(staticContext);
        Utility.clearCaches(staticContext);
        close();
    }

    public final void addCallback(StatusCallback callback) {
        synchronized (this.callbacks) {
            if (callback != null) {
                if (!this.callbacks.contains(callback)) {
                    this.callbacks.add(callback);
                }
            }
        }
    }

    public final void removeCallback(StatusCallback callback) {
        synchronized (this.callbacks) {
            this.callbacks.remove(callback);
        }
    }

    public String toString() {
        return "{Session" + " state:" + this.state + ", token:" + (this.tokenInfo == null ? "null" : this.tokenInfo) + ", appId:" + (this.applicationId == null ? "null" : this.applicationId) + "}";
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void extendTokenCompleted(android.os.Bundle r6) {
        /*
            r5 = this;
            java.lang.Object r2 = r5.lock
            monitor-enter(r2)
            com.facebook.SessionState r0 = r5.state     // Catch:{ all -> 0x0051 }
            int[] r1 = com.facebook.Session.AnonymousClass5.$SwitchMap$com$facebook$SessionState     // Catch:{ all -> 0x0051 }
            com.facebook.SessionState r3 = r5.state     // Catch:{ all -> 0x0051 }
            int r3 = r3.ordinal()     // Catch:{ all -> 0x0051 }
            r1 = r1[r3]     // Catch:{ all -> 0x0051 }
            switch(r1) {
                case 4: goto L_0x002e;
                case 5: goto L_0x0038;
                default: goto L_0x0012;
            }     // Catch:{ all -> 0x0051 }
        L_0x0012:
            java.lang.String r1 = TAG     // Catch:{ all -> 0x0051 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0051 }
            r3.<init>()     // Catch:{ all -> 0x0051 }
            java.lang.String r4 = "refreshToken ignored in state "
            java.lang.StringBuilder r3 = r3.append(r4)     // Catch:{ all -> 0x0051 }
            com.facebook.SessionState r4 = r5.state     // Catch:{ all -> 0x0051 }
            java.lang.StringBuilder r3 = r3.append(r4)     // Catch:{ all -> 0x0051 }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x0051 }
            android.util.Log.d(r1, r3)     // Catch:{ all -> 0x0051 }
            monitor-exit(r2)     // Catch:{ all -> 0x0051 }
        L_0x002d:
            return
        L_0x002e:
            com.facebook.SessionState r1 = com.facebook.SessionState.OPENED_TOKEN_UPDATED     // Catch:{ all -> 0x0051 }
            r5.state = r1     // Catch:{ all -> 0x0051 }
            com.facebook.SessionState r1 = r5.state     // Catch:{ all -> 0x0051 }
            r3 = 0
            r5.postStateChange(r0, r1, r3)     // Catch:{ all -> 0x0051 }
        L_0x0038:
            com.facebook.AccessToken r1 = r5.tokenInfo     // Catch:{ all -> 0x0051 }
            com.facebook.AccessToken r1 = com.facebook.AccessToken.createFromRefresh(r1, r6)     // Catch:{ all -> 0x0051 }
            r5.tokenInfo = r1     // Catch:{ all -> 0x0051 }
            com.facebook.TokenCachingStrategy r1 = r5.tokenCachingStrategy     // Catch:{ all -> 0x0051 }
            if (r1 == 0) goto L_0x004f
            com.facebook.TokenCachingStrategy r1 = r5.tokenCachingStrategy     // Catch:{ all -> 0x0051 }
            com.facebook.AccessToken r3 = r5.tokenInfo     // Catch:{ all -> 0x0051 }
            android.os.Bundle r3 = r3.toCacheBundle()     // Catch:{ all -> 0x0051 }
            r1.save(r3)     // Catch:{ all -> 0x0051 }
        L_0x004f:
            monitor-exit(r2)     // Catch:{ all -> 0x0051 }
            goto L_0x002d
        L_0x0051:
            r1 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0051 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.Session.extendTokenCompleted(android.os.Bundle):void");
    }

    private Object writeReplace() {
        return new SerializationProxyV1(this.applicationId, this.state, this.tokenInfo, this.lastAttemptedTokenExtendDate, false, this.pendingAuthorizationRequest);
    }

    private void readObject(ObjectInputStream stream) throws InvalidObjectException {
        throw new InvalidObjectException("Cannot readObject, serialization proxy required");
    }

    public static final void saveSession(Session session, Bundle bundle) {
        if (bundle != null && session != null && !bundle.containsKey(SESSION_BUNDLE_SAVE_KEY)) {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            try {
                new ObjectOutputStream(outputStream).writeObject(session);
                bundle.putByteArray(SESSION_BUNDLE_SAVE_KEY, outputStream.toByteArray());
                bundle.putBundle(AUTH_BUNDLE_SAVE_KEY, session.authorizationBundle);
            } catch (IOException e) {
                throw new FacebookException("Unable to save session.", e);
            }
        }
    }

    public static final Session restoreSession(Context context, TokenCachingStrategy cachingStrategy, StatusCallback callback, Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        byte[] data = bundle.getByteArray(SESSION_BUNDLE_SAVE_KEY);
        if (data != null) {
            try {
                Session session = (Session) new ObjectInputStream(new ByteArrayInputStream(data)).readObject();
                initializeStaticContext(context);
                if (cachingStrategy != null) {
                    session.tokenCachingStrategy = cachingStrategy;
                } else {
                    session.tokenCachingStrategy = new SharedPreferencesTokenCachingStrategy(context);
                }
                if (callback != null) {
                    session.addCallback(callback);
                }
                session.authorizationBundle = bundle.getBundle(AUTH_BUNDLE_SAVE_KEY);
                return session;
            } catch (ClassNotFoundException e) {
                Log.w(TAG, "Unable to restore session", e);
            } catch (IOException e2) {
                Log.w(TAG, "Unable to restore session.", e2);
            }
        }
        return null;
    }

    public static final Session getActiveSession() {
        Session session;
        synchronized (STATIC_LOCK) {
            session = activeSession;
        }
        return session;
    }

    public static final void setActiveSession(Session session) {
        synchronized (STATIC_LOCK) {
            if (session != activeSession) {
                Session oldSession = activeSession;
                if (oldSession != null) {
                    oldSession.close();
                }
                activeSession = session;
                if (oldSession != null) {
                    postActiveSessionAction(ACTION_ACTIVE_SESSION_UNSET);
                }
                if (session != null) {
                    postActiveSessionAction(ACTION_ACTIVE_SESSION_SET);
                    if (session.isOpened()) {
                        postActiveSessionAction(ACTION_ACTIVE_SESSION_OPENED);
                    }
                }
            }
        }
    }

    public static Session openActiveSessionFromCache(Context context) {
        return openActiveSession(context, false, (OpenRequest) null);
    }

    public static Session openActiveSession(Activity activity, boolean allowLoginUI, StatusCallback callback) {
        return openActiveSession((Context) activity, allowLoginUI, new OpenRequest(activity).setCallback(callback));
    }

    public static Session openActiveSession(Activity activity, boolean allowLoginUI, List<String> permissions, StatusCallback callback) {
        return openActiveSession((Context) activity, allowLoginUI, new OpenRequest(activity).setCallback(callback).setPermissions((List) permissions));
    }

    public static Session openActiveSession(Context context, Fragment fragment, boolean allowLoginUI, StatusCallback callback) {
        return openActiveSession(context, allowLoginUI, new OpenRequest(fragment).setCallback(callback));
    }

    public static Session openActiveSession(Context context, Fragment fragment, boolean allowLoginUI, List<String> permissions, StatusCallback callback) {
        return openActiveSession(context, allowLoginUI, new OpenRequest(fragment).setCallback(callback).setPermissions((List) permissions));
    }

    public static Session openActiveSessionWithAccessToken(Context context, AccessToken accessToken, StatusCallback callback) {
        Session session = new Session(context, (String) null, (TokenCachingStrategy) null, false);
        setActiveSession(session);
        session.open(accessToken, callback);
        return session;
    }

    private static Session openActiveSession(Context context, boolean allowLoginUI, OpenRequest openRequest) {
        Session session = new Builder(context).build();
        if (!SessionState.CREATED_TOKEN_LOADED.equals(session.getState()) && !allowLoginUI) {
            return null;
        }
        setActiveSession(session);
        session.openForRead(openRequest);
        return session;
    }

    static Context getStaticContext() {
        return staticContext;
    }

    static void initializeStaticContext(Context currentContext) {
        if (currentContext != null && staticContext == null) {
            Context applicationContext = currentContext.getApplicationContext();
            if (applicationContext == null) {
                applicationContext = currentContext;
            }
            staticContext = applicationContext;
        }
    }

    /* access modifiers changed from: package-private */
    public void authorize(AuthorizationRequest request) {
        request.setApplicationId(this.applicationId);
        autoPublishAsync();
        logAuthorizationStart();
        boolean started = tryLoginActivity(request);
        this.pendingAuthorizationRequest.loggingExtras.put("try_login_activity", started ? AppEventsConstants.EVENT_PARAM_VALUE_YES : AppEventsConstants.EVENT_PARAM_VALUE_NO);
        if (!started && request.isLegacy) {
            this.pendingAuthorizationRequest.loggingExtras.put("try_legacy", AppEventsConstants.EVENT_PARAM_VALUE_YES);
            tryLegacyAuth(request);
            started = true;
        }
        if (!started) {
            synchronized (this.lock) {
                SessionState oldState = this.state;
                switch (this.state) {
                    case CLOSED:
                    case CLOSED_LOGIN_FAILED:
                        return;
                    default:
                        this.state = SessionState.CLOSED_LOGIN_FAILED;
                        Exception exception = new FacebookException("Log in attempt failed: LoginActivity could not be started, and not legacy request");
                        logAuthorizationComplete(AuthorizationClient.Result.Code.ERROR, (Map<String, String>) null, exception);
                        postStateChange(oldState, this.state, exception);
                        return;
                }
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0057, code lost:
        if (r0 != com.facebook.SessionState.OPENING) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0059, code lost:
        authorize(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void open(com.facebook.Session.OpenRequest r8, com.facebook.internal.SessionAuthorizationType r9) {
        /*
            r7 = this;
            r7.validatePermissions(r8, r9)
            r7.validateLoginBehavior(r8)
            java.lang.Object r3 = r7.lock
            monitor-enter(r3)
            com.facebook.Session$AuthorizationRequest r2 = r7.pendingAuthorizationRequest     // Catch:{ all -> 0x0034 }
            if (r2 == 0) goto L_0x001d
            com.facebook.SessionState r2 = r7.state     // Catch:{ all -> 0x0034 }
            com.facebook.SessionState r4 = r7.state     // Catch:{ all -> 0x0034 }
            java.lang.UnsupportedOperationException r5 = new java.lang.UnsupportedOperationException     // Catch:{ all -> 0x0034 }
            java.lang.String r6 = "Session: an attempt was made to open a session that has a pending request."
            r5.<init>(r6)     // Catch:{ all -> 0x0034 }
            r7.postStateChange(r2, r4, r5)     // Catch:{ all -> 0x0034 }
            monitor-exit(r3)     // Catch:{ all -> 0x0034 }
        L_0x001c:
            return
        L_0x001d:
            com.facebook.SessionState r1 = r7.state     // Catch:{ all -> 0x0034 }
            int[] r2 = com.facebook.Session.AnonymousClass5.$SwitchMap$com$facebook$SessionState     // Catch:{ all -> 0x0034 }
            com.facebook.SessionState r4 = r7.state     // Catch:{ all -> 0x0034 }
            int r4 = r4.ordinal()     // Catch:{ all -> 0x0034 }
            r2 = r2[r4]     // Catch:{ all -> 0x0034 }
            switch(r2) {
                case 1: goto L_0x0037;
                case 2: goto L_0x002c;
                case 3: goto L_0x005d;
                default: goto L_0x002c;
            }     // Catch:{ all -> 0x0034 }
        L_0x002c:
            java.lang.UnsupportedOperationException r2 = new java.lang.UnsupportedOperationException     // Catch:{ all -> 0x0034 }
            java.lang.String r4 = "Session: an attempt was made to open an already opened session."
            r2.<init>(r4)     // Catch:{ all -> 0x0034 }
            throw r2     // Catch:{ all -> 0x0034 }
        L_0x0034:
            r2 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x0034 }
            throw r2
        L_0x0037:
            com.facebook.SessionState r0 = com.facebook.SessionState.OPENING     // Catch:{ all -> 0x0034 }
            r7.state = r0     // Catch:{ all -> 0x0034 }
            if (r8 != 0) goto L_0x0045
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException     // Catch:{ all -> 0x0034 }
            java.lang.String r4 = "openRequest cannot be null when opening a new Session"
            r2.<init>(r4)     // Catch:{ all -> 0x0034 }
            throw r2     // Catch:{ all -> 0x0034 }
        L_0x0045:
            r7.pendingAuthorizationRequest = r8     // Catch:{ all -> 0x0034 }
        L_0x0047:
            if (r8 == 0) goto L_0x0050
            com.facebook.Session$StatusCallback r2 = r8.getCallback()     // Catch:{ all -> 0x0034 }
            r7.addCallback(r2)     // Catch:{ all -> 0x0034 }
        L_0x0050:
            r2 = 0
            r7.postStateChange(r1, r0, r2)     // Catch:{ all -> 0x0034 }
            monitor-exit(r3)     // Catch:{ all -> 0x0034 }
            com.facebook.SessionState r2 = com.facebook.SessionState.OPENING
            if (r0 != r2) goto L_0x001c
            r7.authorize(r8)
            goto L_0x001c
        L_0x005d:
            if (r8 == 0) goto L_0x0079
            java.util.List r2 = r8.getPermissions()     // Catch:{ all -> 0x0034 }
            boolean r2 = com.facebook.internal.Utility.isNullOrEmpty(r2)     // Catch:{ all -> 0x0034 }
            if (r2 != 0) goto L_0x0079
            java.util.List r2 = r8.getPermissions()     // Catch:{ all -> 0x0034 }
            java.util.List r4 = r7.getPermissions()     // Catch:{ all -> 0x0034 }
            boolean r2 = com.facebook.internal.Utility.isSubset(r2, r4)     // Catch:{ all -> 0x0034 }
            if (r2 != 0) goto L_0x0079
            r7.pendingAuthorizationRequest = r8     // Catch:{ all -> 0x0034 }
        L_0x0079:
            com.facebook.Session$AuthorizationRequest r2 = r7.pendingAuthorizationRequest     // Catch:{ all -> 0x0034 }
            if (r2 != 0) goto L_0x0082
            com.facebook.SessionState r0 = com.facebook.SessionState.OPENED     // Catch:{ all -> 0x0034 }
            r7.state = r0     // Catch:{ all -> 0x0034 }
            goto L_0x0047
        L_0x0082:
            com.facebook.SessionState r0 = com.facebook.SessionState.OPENING     // Catch:{ all -> 0x0034 }
            r7.state = r0     // Catch:{ all -> 0x0034 }
            goto L_0x0047
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.Session.open(com.facebook.Session$OpenRequest, com.facebook.internal.SessionAuthorizationType):void");
    }

    private void requestNewPermissions(NewPermissionsRequest newPermissionsRequest, SessionAuthorizationType authType) {
        validatePermissions(newPermissionsRequest, authType);
        validateLoginBehavior(newPermissionsRequest);
        if (newPermissionsRequest != null) {
            synchronized (this.lock) {
                if (this.pendingAuthorizationRequest != null) {
                    throw new UnsupportedOperationException("Session: an attempt was made to request new permissions for a session that has a pending request.");
                } else if (this.state.isOpened()) {
                    this.pendingAuthorizationRequest = newPermissionsRequest;
                } else if (this.state.isClosed()) {
                    throw new UnsupportedOperationException("Session: an attempt was made to request new permissions for a session that has been closed.");
                } else {
                    throw new UnsupportedOperationException("Session: an attempt was made to request new permissions for a session that is not currently open.");
                }
            }
            newPermissionsRequest.setValidateSameFbidAsToken(getAccessToken());
            addCallback(newPermissionsRequest.getCallback());
            authorize(newPermissionsRequest);
        }
    }

    private void validateLoginBehavior(AuthorizationRequest request) {
        if (request != null && !request.isLegacy) {
            Intent intent = new Intent();
            intent.setClass(getStaticContext(), LoginActivity.class);
            if (!resolveIntent(intent)) {
                throw new FacebookException(String.format("Cannot use SessionLoginBehavior %s when %s is not declared as an activity in AndroidManifest.xml", new Object[]{request.getLoginBehavior(), LoginActivity.class.getName()}));
            }
        }
    }

    private void validatePermissions(AuthorizationRequest request, SessionAuthorizationType authType) {
        if (request != null && !Utility.isNullOrEmpty(request.getPermissions())) {
            for (String permission : request.getPermissions()) {
                if (isPublishPermission(permission)) {
                    if (SessionAuthorizationType.READ.equals(authType)) {
                        throw new FacebookException(String.format("Cannot pass a publish or manage permission (%s) to a request for read authorization", new Object[]{permission}));
                    }
                } else if (SessionAuthorizationType.PUBLISH.equals(authType)) {
                    Log.w(TAG, String.format("Should not pass a read permission (%s) to a request for publish or manage authorization", new Object[]{permission}));
                }
            }
        } else if (SessionAuthorizationType.PUBLISH.equals(authType)) {
            throw new FacebookException("Cannot request publish or manage authorization with no permissions.");
        }
    }

    public static boolean isPublishPermission(String permission) {
        return permission != null && (permission.startsWith(PUBLISH_PERMISSION_PREFIX) || permission.startsWith(MANAGE_PERMISSION_PREFIX) || OTHER_PUBLISH_PERMISSIONS.contains(permission));
    }

    /* access modifiers changed from: private */
    public void handleAuthorizationResult(int resultCode, AuthorizationClient.Result result) {
        AccessToken newToken = null;
        Exception exception = null;
        if (resultCode == -1) {
            if (result.code == AuthorizationClient.Result.Code.SUCCESS) {
                newToken = result.token;
            } else {
                exception = new FacebookAuthorizationException(result.errorMessage);
            }
        } else if (resultCode == 0) {
            exception = new FacebookOperationCanceledException(result.errorMessage);
        }
        logAuthorizationComplete(result.code, result.loggingExtras, exception);
        this.authorizationClient = null;
        finishAuthOrReauth(newToken, exception);
    }

    private void logAuthorizationStart() {
        Bundle bundle = AuthorizationClient.newAuthorizationLoggingBundle(this.pendingAuthorizationRequest.getAuthId());
        bundle.putLong("1_timestamp_ms", System.currentTimeMillis());
        try {
            JSONObject extras = new JSONObject();
            extras.put("login_behavior", this.pendingAuthorizationRequest.loginBehavior.toString());
            extras.put("request_code", this.pendingAuthorizationRequest.requestCode);
            extras.put("is_legacy", this.pendingAuthorizationRequest.isLegacy);
            extras.put("permissions", TextUtils.join(",", this.pendingAuthorizationRequest.permissions));
            extras.put(ServerProtocol.DIALOG_PARAM_DEFAULT_AUDIENCE, this.pendingAuthorizationRequest.defaultAudience.toString());
            bundle.putString("6_extras", extras.toString());
        } catch (JSONException e) {
        }
        getAppEventsLogger().logSdkEvent("fb_mobile_login_start", (Double) null, bundle);
    }

    private void logAuthorizationComplete(AuthorizationClient.Result.Code result, Map<String, String> resultExtras, Exception exception) {
        Bundle bundle;
        if (this.pendingAuthorizationRequest == null) {
            bundle = AuthorizationClient.newAuthorizationLoggingBundle("");
            bundle.putString("2_result", AuthorizationClient.Result.Code.ERROR.getLoggingValue());
            bundle.putString("5_error_message", "Unexpected call to logAuthorizationComplete with null pendingAuthorizationRequest.");
        } else {
            bundle = AuthorizationClient.newAuthorizationLoggingBundle(this.pendingAuthorizationRequest.getAuthId());
            if (result != null) {
                bundle.putString("2_result", result.getLoggingValue());
            }
            if (!(exception == null || exception.getMessage() == null)) {
                bundle.putString("5_error_message", exception.getMessage());
            }
            JSONObject jsonObject = null;
            if (!this.pendingAuthorizationRequest.loggingExtras.isEmpty()) {
                jsonObject = new JSONObject(this.pendingAuthorizationRequest.loggingExtras);
            }
            if (resultExtras != null) {
                if (jsonObject == null) {
                    jsonObject = new JSONObject();
                }
                try {
                    for (Map.Entry<String, String> entry : resultExtras.entrySet()) {
                        jsonObject.put(entry.getKey(), entry.getValue());
                    }
                } catch (JSONException e) {
                }
            }
            if (jsonObject != null) {
                bundle.putString("6_extras", jsonObject.toString());
            }
        }
        bundle.putLong("1_timestamp_ms", System.currentTimeMillis());
        getAppEventsLogger().logSdkEvent("fb_mobile_login_complete", (Double) null, bundle);
    }

    private boolean tryLoginActivity(AuthorizationRequest request) {
        Intent intent = getLoginActivityIntent(request);
        if (!resolveIntent(intent)) {
            return false;
        }
        try {
            request.getStartActivityDelegate().startActivityForResult(intent, request.getRequestCode());
            return true;
        } catch (ActivityNotFoundException e) {
            return false;
        }
    }

    private boolean resolveIntent(Intent intent) {
        if (getStaticContext().getPackageManager().resolveActivity(intent, 0) == null) {
            return false;
        }
        return true;
    }

    private Intent getLoginActivityIntent(AuthorizationRequest request) {
        Intent intent = new Intent();
        intent.setClass(getStaticContext(), LoginActivity.class);
        intent.setAction(request.getLoginBehavior().toString());
        intent.putExtras(LoginActivity.populateIntentExtras(request.getAuthorizationClientRequest()));
        return intent;
    }

    private void tryLegacyAuth(AuthorizationRequest request) {
        this.authorizationClient = new AuthorizationClient();
        this.authorizationClient.setOnCompletedListener(new AuthorizationClient.OnCompletedListener() {
            public void onCompleted(AuthorizationClient.Result result) {
                int activityResult;
                if (result.code == AuthorizationClient.Result.Code.CANCEL) {
                    activityResult = 0;
                } else {
                    activityResult = -1;
                }
                Session.this.handleAuthorizationResult(activityResult, result);
            }
        });
        this.authorizationClient.setContext(getStaticContext());
        this.authorizationClient.startOrContinueAuth(request.getAuthorizationClientRequest());
    }

    /* access modifiers changed from: package-private */
    public void finishAuthOrReauth(AccessToken newToken, Exception exception) {
        if (newToken != null && newToken.isInvalid()) {
            newToken = null;
            exception = new FacebookException("Invalid access token.");
        }
        synchronized (this.lock) {
            switch (this.state) {
                case CREATED:
                case CREATED_TOKEN_LOADED:
                case CLOSED:
                case CLOSED_LOGIN_FAILED:
                    Log.d(TAG, "Unexpected call to finishAuthOrReauth in state " + this.state);
                    break;
                case OPENING:
                    finishAuthorization(newToken, exception);
                    break;
                case OPENED:
                case OPENED_TOKEN_UPDATED:
                    finishReauthorization(newToken, exception);
                    break;
            }
        }
    }

    private void finishAuthorization(AccessToken newToken, Exception exception) {
        SessionState oldState = this.state;
        if (newToken != null) {
            this.tokenInfo = newToken;
            saveTokenToCache(newToken);
            this.state = SessionState.OPENED;
        } else if (exception != null) {
            this.state = SessionState.CLOSED_LOGIN_FAILED;
        }
        this.pendingAuthorizationRequest = null;
        postStateChange(oldState, this.state, exception);
    }

    private void finishReauthorization(AccessToken newToken, Exception exception) {
        SessionState oldState = this.state;
        if (newToken != null) {
            this.tokenInfo = newToken;
            saveTokenToCache(newToken);
            this.state = SessionState.OPENED_TOKEN_UPDATED;
        }
        this.pendingAuthorizationRequest = null;
        postStateChange(oldState, this.state, exception);
    }

    private void saveTokenToCache(AccessToken newToken) {
        if (newToken != null && this.tokenCachingStrategy != null) {
            this.tokenCachingStrategy.save(newToken.toCacheBundle());
        }
    }

    /* access modifiers changed from: package-private */
    public void postStateChange(SessionState oldState, final SessionState newState, final Exception exception) {
        if (oldState != newState || oldState == SessionState.OPENED_TOKEN_UPDATED || exception != null) {
            if (newState.isClosed()) {
                this.tokenInfo = AccessToken.createEmptyToken();
            }
            runWithHandlerOrExecutor(this.handler, new Runnable() {
                public void run() {
                    synchronized (Session.this.callbacks) {
                        for (final StatusCallback callback : Session.this.callbacks) {
                            Session.runWithHandlerOrExecutor(Session.this.handler, new Runnable() {
                                public void run() {
                                    callback.call(Session.this, newState, exception);
                                }
                            });
                        }
                    }
                }
            });
            if (this == activeSession && oldState.isOpened() != newState.isOpened()) {
                if (newState.isOpened()) {
                    postActiveSessionAction(ACTION_ACTIVE_SESSION_OPENED);
                } else {
                    postActiveSessionAction(ACTION_ACTIVE_SESSION_CLOSED);
                }
            }
        }
    }

    static void postActiveSessionAction(String action) {
        LocalBroadcastManager.getInstance(getStaticContext()).sendBroadcast(new Intent(action));
    }

    /* access modifiers changed from: private */
    public static void runWithHandlerOrExecutor(Handler handler2, Runnable runnable) {
        if (handler2 != null) {
            handler2.post(runnable);
        } else {
            Settings.getExecutor().execute(runnable);
        }
    }

    /* access modifiers changed from: package-private */
    public void extendAccessTokenIfNeeded() {
        if (shouldExtendAccessToken()) {
            extendAccessToken();
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0011, code lost:
        if (r0 == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0013, code lost:
        r0.bind();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void extendAccessToken() {
        /*
            r4 = this;
            r0 = 0
            java.lang.Object r3 = r4.lock
            monitor-enter(r3)
            com.facebook.Session$TokenRefreshRequest r2 = r4.currentTokenRefreshRequest     // Catch:{ all -> 0x0017 }
            if (r2 != 0) goto L_0x0010
            com.facebook.Session$TokenRefreshRequest r1 = new com.facebook.Session$TokenRefreshRequest     // Catch:{ all -> 0x0017 }
            r1.<init>()     // Catch:{ all -> 0x0017 }
            r4.currentTokenRefreshRequest = r1     // Catch:{ all -> 0x001a }
            r0 = r1
        L_0x0010:
            monitor-exit(r3)     // Catch:{ all -> 0x0017 }
            if (r0 == 0) goto L_0x0016
            r0.bind()
        L_0x0016:
            return
        L_0x0017:
            r2 = move-exception
        L_0x0018:
            monitor-exit(r3)     // Catch:{ all -> 0x0017 }
            throw r2
        L_0x001a:
            r2 = move-exception
            r0 = r1
            goto L_0x0018
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.Session.extendAccessToken():void");
    }

    /* access modifiers changed from: package-private */
    public boolean shouldExtendAccessToken() {
        if (this.currentTokenRefreshRequest != null) {
            return false;
        }
        Date now = new Date();
        if (!this.state.isOpened() || !this.tokenInfo.getSource().canExtendToken() || now.getTime() - this.lastAttemptedTokenExtendDate.getTime() <= 3600000 || now.getTime() - this.tokenInfo.getLastRefresh().getTime() <= 86400000) {
            return false;
        }
        return true;
    }

    private AppEventsLogger getAppEventsLogger() {
        AppEventsLogger appEventsLogger2;
        synchronized (this.lock) {
            if (this.appEventsLogger == null) {
                this.appEventsLogger = AppEventsLogger.newLogger(staticContext, this.applicationId);
            }
            appEventsLogger2 = this.appEventsLogger;
        }
        return appEventsLogger2;
    }

    /* access modifiers changed from: package-private */
    public AccessToken getTokenInfo() {
        return this.tokenInfo;
    }

    /* access modifiers changed from: package-private */
    public void setTokenInfo(AccessToken tokenInfo2) {
        this.tokenInfo = tokenInfo2;
    }

    /* access modifiers changed from: package-private */
    public Date getLastAttemptedTokenExtendDate() {
        return this.lastAttemptedTokenExtendDate;
    }

    /* access modifiers changed from: package-private */
    public void setLastAttemptedTokenExtendDate(Date lastAttemptedTokenExtendDate2) {
        this.lastAttemptedTokenExtendDate = lastAttemptedTokenExtendDate2;
    }

    /* access modifiers changed from: package-private */
    public void setCurrentTokenRefreshRequest(TokenRefreshRequest request) {
        this.currentTokenRefreshRequest = request;
    }

    class TokenRefreshRequest implements ServiceConnection {
        final Messenger messageReceiver = new Messenger(new TokenRefreshRequestHandler(Session.this, this));
        Messenger messageSender = null;

        TokenRefreshRequest() {
        }

        public void bind() {
            Intent intent = NativeProtocol.createTokenRefreshIntent(Session.getStaticContext());
            if (intent == null || !Session.staticContext.bindService(intent, this, 1)) {
                cleanup();
            } else {
                Session.this.setLastAttemptedTokenExtendDate(new Date());
            }
        }

        public void onServiceConnected(ComponentName className, IBinder service) {
            this.messageSender = new Messenger(service);
            refreshToken();
        }

        public void onServiceDisconnected(ComponentName arg) {
            cleanup();
            Session.staticContext.unbindService(this);
        }

        /* access modifiers changed from: private */
        public void cleanup() {
            if (Session.this.currentTokenRefreshRequest == this) {
                TokenRefreshRequest unused = Session.this.currentTokenRefreshRequest = null;
            }
        }

        private void refreshToken() {
            Bundle requestData = new Bundle();
            requestData.putString("access_token", Session.this.getTokenInfo().getToken());
            Message request = Message.obtain();
            request.setData(requestData);
            request.replyTo = this.messageReceiver;
            try {
                this.messageSender.send(request);
            } catch (RemoteException e) {
                cleanup();
            }
        }
    }

    static class TokenRefreshRequestHandler extends Handler {
        private WeakReference<TokenRefreshRequest> refreshRequestWeakReference;
        private WeakReference<Session> sessionWeakReference;

        TokenRefreshRequestHandler(Session session, TokenRefreshRequest refreshRequest) {
            super(Looper.getMainLooper());
            this.sessionWeakReference = new WeakReference<>(session);
            this.refreshRequestWeakReference = new WeakReference<>(refreshRequest);
        }

        public void handleMessage(Message msg) {
            String token = msg.getData().getString("access_token");
            Session session = (Session) this.sessionWeakReference.get();
            if (!(session == null || token == null)) {
                session.extendTokenCompleted(msg.getData());
            }
            TokenRefreshRequest request = (TokenRefreshRequest) this.refreshRequestWeakReference.get();
            if (request != null) {
                Session.staticContext.unbindService(request);
                request.cleanup();
            }
        }
    }

    public int hashCode() {
        return 0;
    }

    public boolean equals(Object otherObj) {
        if (!(otherObj instanceof Session)) {
            return false;
        }
        Session other = (Session) otherObj;
        if (!areEqual(other.applicationId, this.applicationId) || !areEqual(other.authorizationBundle, this.authorizationBundle) || !areEqual(other.state, this.state) || !areEqual(other.getExpirationDate(), getExpirationDate())) {
            return false;
        }
        return true;
    }

    private static boolean areEqual(Object a, Object b) {
        if (a == null) {
            return b == null;
        }
        return a.equals(b);
    }

    public static final class Builder {
        private String applicationId;
        private final Context context;
        private TokenCachingStrategy tokenCachingStrategy;

        public Builder(Context context2) {
            this.context = context2;
        }

        public Builder setApplicationId(String applicationId2) {
            this.applicationId = applicationId2;
            return this;
        }

        public Builder setTokenCachingStrategy(TokenCachingStrategy tokenCachingStrategy2) {
            this.tokenCachingStrategy = tokenCachingStrategy2;
            return this;
        }

        public Session build() {
            return new Session(this.context, this.applicationId, this.tokenCachingStrategy);
        }
    }

    private void autoPublishAsync() {
        String applicationId2;
        AutoPublishAsyncTask asyncTask = null;
        synchronized (this) {
            if (this.autoPublishAsyncTask == null && Settings.getShouldAutoPublishInstall() && (applicationId2 = this.applicationId) != null) {
                AutoPublishAsyncTask asyncTask2 = new AutoPublishAsyncTask(applicationId2, staticContext);
                this.autoPublishAsyncTask = asyncTask2;
                asyncTask = asyncTask2;
            }
        }
        if (asyncTask != null) {
            asyncTask.execute(new Void[0]);
        }
    }

    private class AutoPublishAsyncTask extends AsyncTask<Void, Void, Void> {
        private final Context mApplicationContext;
        private final String mApplicationId;

        public AutoPublishAsyncTask(String applicationId, Context context) {
            this.mApplicationId = applicationId;
            this.mApplicationContext = context.getApplicationContext();
        }

        /* access modifiers changed from: protected */
        public Void doInBackground(Void... voids) {
            try {
                Settings.publishInstallAndWaitForResponse(this.mApplicationContext, this.mApplicationId, true);
                return null;
            } catch (Exception e) {
                Utility.logd("Facebook-publish", e);
                return null;
            }
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Void result) {
            synchronized (Session.this) {
                AutoPublishAsyncTask unused = Session.this.autoPublishAsyncTask = null;
            }
        }
    }

    public static class AuthorizationRequest implements Serializable {
        private static final long serialVersionUID = 1;
        private String applicationId;
        private final String authId;
        /* access modifiers changed from: private */
        public SessionDefaultAudience defaultAudience;
        /* access modifiers changed from: private */
        public boolean isLegacy;
        /* access modifiers changed from: private */
        public final Map<String, String> loggingExtras;
        /* access modifiers changed from: private */
        public SessionLoginBehavior loginBehavior;
        /* access modifiers changed from: private */
        public List<String> permissions;
        /* access modifiers changed from: private */
        public int requestCode;
        /* access modifiers changed from: private */
        public final StartActivityDelegate startActivityDelegate;
        private StatusCallback statusCallback;
        private String validateSameFbidAsToken;

        AuthorizationRequest(final Activity activity) {
            this.loginBehavior = SessionLoginBehavior.SSO_WITH_FALLBACK;
            this.requestCode = Session.DEFAULT_AUTHORIZE_ACTIVITY_CODE;
            this.isLegacy = false;
            this.permissions = Collections.emptyList();
            this.defaultAudience = SessionDefaultAudience.FRIENDS;
            this.authId = UUID.randomUUID().toString();
            this.loggingExtras = new HashMap();
            this.startActivityDelegate = new StartActivityDelegate() {
                public void startActivityForResult(Intent intent, int requestCode) {
                    activity.startActivityForResult(intent, requestCode);
                }

                public Activity getActivityContext() {
                    return activity;
                }
            };
        }

        AuthorizationRequest(final Fragment fragment) {
            this.loginBehavior = SessionLoginBehavior.SSO_WITH_FALLBACK;
            this.requestCode = Session.DEFAULT_AUTHORIZE_ACTIVITY_CODE;
            this.isLegacy = false;
            this.permissions = Collections.emptyList();
            this.defaultAudience = SessionDefaultAudience.FRIENDS;
            this.authId = UUID.randomUUID().toString();
            this.loggingExtras = new HashMap();
            this.startActivityDelegate = new StartActivityDelegate() {
                public void startActivityForResult(Intent intent, int requestCode) {
                    fragment.startActivityForResult(intent, requestCode);
                }

                public Activity getActivityContext() {
                    return fragment.getActivity();
                }
            };
        }

        private AuthorizationRequest(SessionLoginBehavior loginBehavior2, int requestCode2, List<String> permissions2, String defaultAudience2, boolean isLegacy2, String applicationId2, String validateSameFbidAsToken2) {
            this.loginBehavior = SessionLoginBehavior.SSO_WITH_FALLBACK;
            this.requestCode = Session.DEFAULT_AUTHORIZE_ACTIVITY_CODE;
            this.isLegacy = false;
            this.permissions = Collections.emptyList();
            this.defaultAudience = SessionDefaultAudience.FRIENDS;
            this.authId = UUID.randomUUID().toString();
            this.loggingExtras = new HashMap();
            this.startActivityDelegate = new StartActivityDelegate() {
                public void startActivityForResult(Intent intent, int requestCode) {
                    throw new UnsupportedOperationException("Cannot create an AuthorizationRequest without a valid Activity or Fragment");
                }

                public Activity getActivityContext() {
                    throw new UnsupportedOperationException("Cannot create an AuthorizationRequest without a valid Activity or Fragment");
                }
            };
            this.loginBehavior = loginBehavior2;
            this.requestCode = requestCode2;
            this.permissions = permissions2;
            this.defaultAudience = SessionDefaultAudience.valueOf(defaultAudience2);
            this.isLegacy = isLegacy2;
            this.applicationId = applicationId2;
            this.validateSameFbidAsToken = validateSameFbidAsToken2;
        }

        public void setIsLegacy(boolean isLegacy2) {
            this.isLegacy = isLegacy2;
        }

        /* access modifiers changed from: package-private */
        public boolean isLegacy() {
            return this.isLegacy;
        }

        /* access modifiers changed from: package-private */
        public AuthorizationRequest setCallback(StatusCallback statusCallback2) {
            this.statusCallback = statusCallback2;
            return this;
        }

        /* access modifiers changed from: package-private */
        public StatusCallback getCallback() {
            return this.statusCallback;
        }

        /* access modifiers changed from: package-private */
        public AuthorizationRequest setLoginBehavior(SessionLoginBehavior loginBehavior2) {
            if (loginBehavior2 != null) {
                this.loginBehavior = loginBehavior2;
            }
            return this;
        }

        /* access modifiers changed from: package-private */
        public SessionLoginBehavior getLoginBehavior() {
            return this.loginBehavior;
        }

        /* access modifiers changed from: package-private */
        public AuthorizationRequest setRequestCode(int requestCode2) {
            if (requestCode2 >= 0) {
                this.requestCode = requestCode2;
            }
            return this;
        }

        /* access modifiers changed from: package-private */
        public int getRequestCode() {
            return this.requestCode;
        }

        /* access modifiers changed from: package-private */
        public AuthorizationRequest setPermissions(List<String> permissions2) {
            if (permissions2 != null) {
                this.permissions = permissions2;
            }
            return this;
        }

        /* access modifiers changed from: package-private */
        public AuthorizationRequest setPermissions(String... permissions2) {
            return setPermissions((List<String>) Arrays.asList(permissions2));
        }

        /* access modifiers changed from: package-private */
        public List<String> getPermissions() {
            return this.permissions;
        }

        /* access modifiers changed from: package-private */
        public AuthorizationRequest setDefaultAudience(SessionDefaultAudience defaultAudience2) {
            if (defaultAudience2 != null) {
                this.defaultAudience = defaultAudience2;
            }
            return this;
        }

        /* access modifiers changed from: package-private */
        public SessionDefaultAudience getDefaultAudience() {
            return this.defaultAudience;
        }

        /* access modifiers changed from: package-private */
        public StartActivityDelegate getStartActivityDelegate() {
            return this.startActivityDelegate;
        }

        /* access modifiers changed from: package-private */
        public String getApplicationId() {
            return this.applicationId;
        }

        /* access modifiers changed from: package-private */
        public void setApplicationId(String applicationId2) {
            this.applicationId = applicationId2;
        }

        /* access modifiers changed from: package-private */
        public String getValidateSameFbidAsToken() {
            return this.validateSameFbidAsToken;
        }

        /* access modifiers changed from: package-private */
        public void setValidateSameFbidAsToken(String validateSameFbidAsToken2) {
            this.validateSameFbidAsToken = validateSameFbidAsToken2;
        }

        /* access modifiers changed from: package-private */
        public String getAuthId() {
            return this.authId;
        }

        /* access modifiers changed from: package-private */
        public AuthorizationClient.AuthorizationRequest getAuthorizationClientRequest() {
            return new AuthorizationClient.AuthorizationRequest(this.loginBehavior, this.requestCode, this.isLegacy, this.permissions, this.defaultAudience, this.applicationId, this.validateSameFbidAsToken, new AuthorizationClient.StartActivityDelegate() {
                public void startActivityForResult(Intent intent, int requestCode) {
                    AuthorizationRequest.this.startActivityDelegate.startActivityForResult(intent, requestCode);
                }

                public Activity getActivityContext() {
                    return AuthorizationRequest.this.startActivityDelegate.getActivityContext();
                }
            }, this.authId);
        }

        /* access modifiers changed from: package-private */
        public Object writeReplace() {
            return new AuthRequestSerializationProxyV1(this.loginBehavior, this.requestCode, this.permissions, this.defaultAudience.name(), this.isLegacy, this.applicationId, this.validateSameFbidAsToken);
        }

        private void readObject(ObjectInputStream stream) throws InvalidObjectException {
            throw new InvalidObjectException("Cannot readObject, serialization proxy required");
        }

        private static class AuthRequestSerializationProxyV1 implements Serializable {
            private static final long serialVersionUID = -8748347685113614927L;
            private final String applicationId;
            private final String defaultAudience;
            private boolean isLegacy;
            private final SessionLoginBehavior loginBehavior;
            private final List<String> permissions;
            private final int requestCode;
            private final String validateSameFbidAsToken;

            private AuthRequestSerializationProxyV1(SessionLoginBehavior loginBehavior2, int requestCode2, List<String> permissions2, String defaultAudience2, boolean isLegacy2, String applicationId2, String validateSameFbidAsToken2) {
                this.loginBehavior = loginBehavior2;
                this.requestCode = requestCode2;
                this.permissions = permissions2;
                this.defaultAudience = defaultAudience2;
                this.isLegacy = isLegacy2;
                this.applicationId = applicationId2;
                this.validateSameFbidAsToken = validateSameFbidAsToken2;
            }

            private Object readResolve() {
                return new AuthorizationRequest(this.loginBehavior, this.requestCode, this.permissions, this.defaultAudience, this.isLegacy, this.applicationId, this.validateSameFbidAsToken);
            }
        }
    }

    public static final class OpenRequest extends AuthorizationRequest {
        private static final long serialVersionUID = 1;

        public OpenRequest(Activity activity) {
            super(activity);
        }

        public OpenRequest(Fragment fragment) {
            super(fragment);
        }

        public final OpenRequest setCallback(StatusCallback statusCallback) {
            super.setCallback(statusCallback);
            return this;
        }

        public final OpenRequest setLoginBehavior(SessionLoginBehavior loginBehavior) {
            super.setLoginBehavior(loginBehavior);
            return this;
        }

        public final OpenRequest setRequestCode(int requestCode) {
            super.setRequestCode(requestCode);
            return this;
        }

        public final OpenRequest setPermissions(List<String> permissions) {
            super.setPermissions(permissions);
            return this;
        }

        public final OpenRequest setPermissions(String... permissions) {
            super.setPermissions(permissions);
            return this;
        }

        public final OpenRequest setDefaultAudience(SessionDefaultAudience defaultAudience) {
            super.setDefaultAudience(defaultAudience);
            return this;
        }
    }

    public static final class NewPermissionsRequest extends AuthorizationRequest {
        private static final long serialVersionUID = 1;

        public NewPermissionsRequest(Activity activity, List<String> permissions) {
            super(activity);
            setPermissions(permissions);
        }

        public NewPermissionsRequest(Fragment fragment, List<String> permissions) {
            super(fragment);
            setPermissions(permissions);
        }

        public NewPermissionsRequest(Activity activity, String... permissions) {
            super(activity);
            setPermissions(permissions);
        }

        public NewPermissionsRequest(Fragment fragment, String... permissions) {
            super(fragment);
            setPermissions(permissions);
        }

        public final NewPermissionsRequest setCallback(StatusCallback statusCallback) {
            super.setCallback(statusCallback);
            return this;
        }

        public final NewPermissionsRequest setLoginBehavior(SessionLoginBehavior loginBehavior) {
            super.setLoginBehavior(loginBehavior);
            return this;
        }

        public final NewPermissionsRequest setRequestCode(int requestCode) {
            super.setRequestCode(requestCode);
            return this;
        }

        public final NewPermissionsRequest setDefaultAudience(SessionDefaultAudience defaultAudience) {
            super.setDefaultAudience(defaultAudience);
            return this;
        }

        /* access modifiers changed from: package-private */
        public AuthorizationClient.AuthorizationRequest getAuthorizationClientRequest() {
            AuthorizationClient.AuthorizationRequest request = super.getAuthorizationClientRequest();
            request.setRerequest(true);
            return request;
        }
    }
}
