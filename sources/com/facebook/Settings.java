package com.facebook;

import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.facebook.Request;
import com.facebook.internal.AttributionIdentifiers;
import com.facebook.internal.ServerProtocol;
import com.facebook.internal.Utility;
import com.facebook.internal.Validate;
import com.facebook.model.GraphObject;
import java.net.HttpURLConnection;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import org.json.JSONException;
import org.json.JSONObject;

public final class Settings {
    private static final String ANALYTICS_EVENT = "event";
    public static final String APPLICATION_ID_PROPERTY = "com.facebook.sdk.ApplicationId";
    private static final String APP_EVENT_PREFERENCES = "com.facebook.sdk.appEventPreferences";
    private static final String ATTRIBUTION_ID_COLUMN_NAME = "aid";
    private static final Uri ATTRIBUTION_ID_CONTENT_URI = Uri.parse("content://com.facebook.katana.provider.AttributionIdProvider");
    private static final String ATTRIBUTION_PREFERENCES = "com.facebook.sdk.attributionTracking";
    private static final String AUTO_PUBLISH = "auto_publish";
    public static final String CLIENT_TOKEN_PROPERTY = "com.facebook.sdk.ClientToken";
    private static final int DEFAULT_CORE_POOL_SIZE = 5;
    private static final int DEFAULT_KEEP_ALIVE = 1;
    private static final int DEFAULT_MAXIMUM_POOL_SIZE = 128;
    private static final ThreadFactory DEFAULT_THREAD_FACTORY = new ThreadFactory() {
        private final AtomicInteger counter = new AtomicInteger(0);

        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "FacebookSdk #" + this.counter.incrementAndGet());
        }
    };
    private static final BlockingQueue<Runnable> DEFAULT_WORK_QUEUE = new LinkedBlockingQueue(10);
    private static final String FACEBOOK_COM = "facebook.com";
    private static final Object LOCK = new Object();
    private static final String MOBILE_INSTALL_EVENT = "MOBILE_APP_INSTALL";
    private static final String PUBLISH_ACTIVITY_PATH = "%s/activities";
    private static final String TAG = Settings.class.getCanonicalName();
    private static volatile String appClientToken;
    private static volatile String appVersion;
    private static volatile String applicationId;
    private static volatile boolean defaultsLoaded = false;
    private static volatile Executor executor;
    private static volatile String facebookDomain = FACEBOOK_COM;
    private static volatile boolean isLoggingEnabled = false;
    private static final HashSet<LoggingBehavior> loggingBehaviors = new HashSet<>(Arrays.asList(new LoggingBehavior[]{LoggingBehavior.DEVELOPER_ERRORS}));
    private static AtomicLong onProgressThreshold = new AtomicLong(65536);
    private static volatile boolean platformCompatibilityEnabled;
    private static Boolean sdkInitialized = false;
    private static volatile boolean shouldAutoPublishInstall;

    public static synchronized void sdkInitialize(Context context) {
        synchronized (Settings.class) {
            if (!sdkInitialized.booleanValue()) {
                BoltsMeasurementEventListener.getInstance(context.getApplicationContext());
                sdkInitialized = true;
            }
        }
    }

    public static final Set<LoggingBehavior> getLoggingBehaviors() {
        Set<LoggingBehavior> unmodifiableSet;
        synchronized (loggingBehaviors) {
            unmodifiableSet = Collections.unmodifiableSet(new HashSet(loggingBehaviors));
        }
        return unmodifiableSet;
    }

    public static final void addLoggingBehavior(LoggingBehavior behavior) {
        synchronized (loggingBehaviors) {
            loggingBehaviors.add(behavior);
        }
    }

    public static final void removeLoggingBehavior(LoggingBehavior behavior) {
        synchronized (loggingBehaviors) {
            loggingBehaviors.remove(behavior);
        }
    }

    public static final void clearLoggingBehaviors() {
        synchronized (loggingBehaviors) {
            loggingBehaviors.clear();
        }
    }

    public static final boolean isLoggingBehaviorEnabled(LoggingBehavior behavior) {
        boolean z;
        synchronized (loggingBehaviors) {
            z = isLoggingEnabled() && loggingBehaviors.contains(behavior);
        }
        return z;
    }

    public static final boolean isLoggingEnabled() {
        return isLoggingEnabled;
    }

    public static final void setIsLoggingEnabled(boolean enabled) {
        isLoggingEnabled = enabled;
    }

    public static Executor getExecutor() {
        synchronized (LOCK) {
            if (executor == null) {
                Executor executor2 = getAsyncTaskExecutor();
                if (executor2 == null) {
                    executor2 = new ThreadPoolExecutor(5, 128, 1, TimeUnit.SECONDS, DEFAULT_WORK_QUEUE, DEFAULT_THREAD_FACTORY);
                }
                executor = executor2;
            }
        }
        return executor;
    }

    public static void setExecutor(Executor executor2) {
        Validate.notNull(executor2, "executor");
        synchronized (LOCK) {
            executor = executor2;
        }
    }

    public static String getFacebookDomain() {
        return facebookDomain;
    }

    public static void setFacebookDomain(String facebookDomain2) {
        Log.w(TAG, "WARNING: Calling setFacebookDomain from non-DEBUG code.");
        facebookDomain = facebookDomain2;
    }

    private static Executor getAsyncTaskExecutor() {
        try {
            try {
                Object executorObject = AsyncTask.class.getField("THREAD_POOL_EXECUTOR").get((Object) null);
                if (executorObject == null) {
                    return null;
                }
                if (!(executorObject instanceof Executor)) {
                    return null;
                }
                return (Executor) executorObject;
            } catch (IllegalAccessException e) {
                return null;
            }
        } catch (NoSuchFieldException e2) {
            return null;
        }
    }

    static void publishInstallAsync(Context context, final String applicationId2, final Request.Callback callback) {
        final Context applicationContext = context.getApplicationContext();
        getExecutor().execute(new Runnable() {
            public void run() {
                final Response response = Settings.publishInstallAndWaitForResponse(applicationContext, applicationId2, false);
                if (callback != null) {
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        public void run() {
                            callback.onCompleted(response);
                        }
                    });
                }
            }
        });
    }

    @Deprecated
    public static void setShouldAutoPublishInstall(boolean shouldAutoPublishInstall2) {
        shouldAutoPublishInstall = shouldAutoPublishInstall2;
    }

    @Deprecated
    public static boolean getShouldAutoPublishInstall() {
        return shouldAutoPublishInstall;
    }

    static Response publishInstallAndWaitForResponse(Context context, String applicationId2, boolean isAutoPublish) {
        if (context == null || applicationId2 == null) {
            try {
                throw new IllegalArgumentException("Both context and applicationId must be non-null");
            } catch (Exception e) {
                Utility.logd("Facebook-publish", e);
                return new Response((Request) null, (HttpURLConnection) null, new FacebookRequestError((HttpURLConnection) null, e));
            }
        } else {
            AttributionIdentifiers identifiers = AttributionIdentifiers.getAttributionIdentifiers(context);
            SharedPreferences preferences = context.getSharedPreferences(ATTRIBUTION_PREFERENCES, 0);
            String pingKey = applicationId2 + "ping";
            String jsonKey = applicationId2 + "json";
            long lastPing = preferences.getLong(pingKey, 0);
            String lastResponseJSON = preferences.getString(jsonKey, (String) null);
            if (!isAutoPublish) {
                setShouldAutoPublishInstall(false);
            }
            GraphObject publishParams = GraphObject.Factory.create();
            publishParams.setProperty("event", MOBILE_INSTALL_EVENT);
            Utility.setAppEventAttributionParameters(publishParams, identifiers, Utility.getHashedDeviceAndAppID(context, applicationId2), getLimitEventAndDataUsage(context));
            publishParams.setProperty(AUTO_PUBLISH, Boolean.valueOf(isAutoPublish));
            publishParams.setProperty("application_package_name", context.getPackageName());
            Request publishRequest = Request.newPostRequest((Session) null, String.format(PUBLISH_ACTIVITY_PATH, new Object[]{applicationId2}), publishParams, (Request.Callback) null);
            if (lastPing != 0) {
                GraphObject graphObject = null;
                if (lastResponseJSON != null) {
                    try {
                        graphObject = GraphObject.Factory.create(new JSONObject(lastResponseJSON));
                    } catch (JSONException e2) {
                    }
                }
                if (graphObject != null) {
                    return new Response((Request) null, (HttpURLConnection) null, (String) null, graphObject, true);
                }
                return Response.createResponsesFromString(ServerProtocol.DIALOG_RETURN_SCOPES_TRUE, (HttpURLConnection) null, new RequestBatch(publishRequest), true).get(0);
            } else if (identifiers == null || (identifiers.getAndroidAdvertiserId() == null && identifiers.getAttributionId() == null)) {
                throw new FacebookException("No attribution id available to send to server.");
            } else if (!Utility.queryAppSettings(applicationId2, false).supportsAttribution()) {
                throw new FacebookException("Install attribution has been disabled on the server.");
            } else {
                Response publishResponse = publishRequest.executeAndWait();
                SharedPreferences.Editor editor = preferences.edit();
                editor.putLong(pingKey, System.currentTimeMillis());
                if (!(publishResponse.getGraphObject() == null || publishResponse.getGraphObject().getInnerJSONObject() == null)) {
                    editor.putString(jsonKey, publishResponse.getGraphObject().getInnerJSONObject().toString());
                }
                editor.commit();
                return publishResponse;
            }
        }
    }

    public static String getAttributionId(ContentResolver contentResolver) {
        try {
            ContentResolver contentResolver2 = contentResolver;
            Cursor c = contentResolver2.query(ATTRIBUTION_ID_CONTENT_URI, new String[]{"aid"}, (String) null, (String[]) null, (String) null);
            if (c == null || !c.moveToFirst()) {
                return null;
            }
            String string = c.getString(c.getColumnIndex("aid"));
            c.close();
            return string;
        } catch (Exception e) {
            Log.d(TAG, "Caught unexpected exception in getAttributionId(): " + e.toString());
            return null;
        }
    }

    public static String getAppVersion() {
        return appVersion;
    }

    public static void setAppVersion(String appVersion2) {
        appVersion = appVersion2;
    }

    public static String getSdkVersion() {
        return FacebookSdkVersion.BUILD;
    }

    public static boolean getLimitEventAndDataUsage(Context context) {
        return context.getSharedPreferences(APP_EVENT_PREFERENCES, 0).getBoolean("limitEventUsage", false);
    }

    public static void setLimitEventAndDataUsage(Context context, boolean limitEventUsage) {
        SharedPreferences.Editor editor = context.getSharedPreferences(APP_EVENT_PREFERENCES, 0).edit();
        editor.putBoolean("limitEventUsage", limitEventUsage);
        editor.commit();
    }

    public static long getOnProgressThreshold() {
        return onProgressThreshold.get();
    }

    public static void setOnProgressThreshold(long threshold) {
        onProgressThreshold.set(threshold);
    }

    public static boolean getPlatformCompatibilityEnabled() {
        return platformCompatibilityEnabled;
    }

    public static void setPlatformCompatibilityEnabled(boolean platformCompatibilityEnabled2) {
        platformCompatibilityEnabled = platformCompatibilityEnabled2;
    }

    public static void loadDefaultsFromMetadata(Context context) {
        defaultsLoaded = true;
        if (context != null) {
            try {
                ApplicationInfo ai = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
                if (ai != null && ai.metaData != null) {
                    if (applicationId == null) {
                        applicationId = ai.metaData.getString(APPLICATION_ID_PROPERTY);
                    }
                    if (appClientToken == null) {
                        appClientToken = ai.metaData.getString(CLIENT_TOKEN_PROPERTY);
                    }
                }
            } catch (PackageManager.NameNotFoundException e) {
            }
        }
    }

    static void loadDefaultsFromMetadataIfNeeded(Context context) {
        if (!defaultsLoaded) {
            loadDefaultsFromMetadata(context);
        }
    }

    public static String getApplicationId() {
        return applicationId;
    }

    public static void setApplicationId(String applicationId2) {
        applicationId = applicationId2;
    }

    public static String getClientToken() {
        return appClientToken;
    }

    public static void setClientToken(String clientToken) {
        appClientToken = clientToken;
    }
}
