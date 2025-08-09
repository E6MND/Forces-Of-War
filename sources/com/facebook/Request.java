package com.facebook;

import android.content.Context;
import android.graphics.Bitmap;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.facebook.RequestBatch;
import com.facebook.internal.AttributionIdentifiers;
import com.facebook.internal.Logger;
import com.facebook.internal.NativeProtocol;
import com.facebook.internal.ServerProtocol;
import com.facebook.internal.Utility;
import com.facebook.internal.Validate;
import com.facebook.model.GraphMultiResult;
import com.facebook.model.GraphObject;
import com.facebook.model.GraphObjectList;
import com.facebook.model.GraphPlace;
import com.facebook.model.GraphUser;
import com.facebook.model.OpenGraphAction;
import com.facebook.model.OpenGraphObject;
import com.google.android.gms.games.request.Requests;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xwalk.core.internal.extension.api.messaging.MessagingSmsConsts;

public class Request {
    private static final String ACCEPT_LANGUAGE_HEADER = "Accept-Language";
    private static final String ACCESS_TOKEN_PARAM = "access_token";
    private static final String ATTACHED_FILES_PARAM = "attached_files";
    private static final String ATTACHMENT_FILENAME_PREFIX = "file";
    private static final String BATCH_APP_ID_PARAM = "batch_app_id";
    private static final String BATCH_BODY_PARAM = "body";
    private static final String BATCH_ENTRY_DEPENDS_ON_PARAM = "depends_on";
    private static final String BATCH_ENTRY_NAME_PARAM = "name";
    private static final String BATCH_ENTRY_OMIT_RESPONSE_ON_SUCCESS_PARAM = "omit_response_on_success";
    private static final String BATCH_METHOD_PARAM = "method";
    private static final String BATCH_PARAM = "batch";
    private static final String BATCH_RELATIVE_URL_PARAM = "relative_url";
    private static final String CONTENT_TYPE_HEADER = "Content-Type";
    private static final String FORMAT_JSON = "json";
    private static final String FORMAT_PARAM = "format";
    private static final String ISO_8601_FORMAT_STRING = "yyyy-MM-dd'T'HH:mm:ssZ";
    public static final int MAXIMUM_BATCH_SIZE = 50;
    private static final String ME = "me";
    private static final String MIME_BOUNDARY = "3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f";
    private static final String MY_ACTION_FORMAT = "me/%s";
    private static final String MY_FEED = "me/feed";
    private static final String MY_FRIENDS = "me/friends";
    private static final String MY_OBJECTS_FORMAT = "me/objects/%s";
    private static final String MY_PHOTOS = "me/photos";
    private static final String MY_STAGING_RESOURCES = "me/staging_resources";
    private static final String MY_VIDEOS = "me/videos";
    private static final String OBJECT_PARAM = "object";
    private static final String PICTURE_PARAM = "picture";
    private static final String SDK_ANDROID = "android";
    private static final String SDK_PARAM = "sdk";
    private static final String SEARCH = "search";
    private static final String STAGING_PARAM = "file";
    public static final String TAG = Request.class.getSimpleName();
    private static final String USER_AGENT_BASE = "FBAndroidSDK";
    private static final String USER_AGENT_HEADER = "User-Agent";
    private static final String VIDEOS_SUFFIX = "/videos";
    private static String defaultBatchApplicationId;
    private static volatile String userAgent;
    private static Pattern versionPattern = Pattern.compile("^/?v\\d+\\.\\d+/(.*)");
    private String batchEntryDependsOn;
    private String batchEntryName;
    private boolean batchEntryOmitResultOnSuccess;
    private Callback callback;
    private GraphObject graphObject;
    private String graphPath;
    private HttpMethod httpMethod;
    private String overriddenURL;
    private Bundle parameters;
    private Session session;
    private Object tag;
    private String version;

    public interface Callback {
        void onCompleted(Response response);
    }

    public interface GraphPlaceListCallback {
        void onCompleted(List<GraphPlace> list, Response response);
    }

    public interface GraphUserCallback {
        void onCompleted(GraphUser graphUser, Response response);
    }

    public interface GraphUserListCallback {
        void onCompleted(List<GraphUser> list, Response response);
    }

    private interface KeyValueSerializer {
        void writeString(String str, String str2) throws IOException;
    }

    public interface OnProgressCallback extends Callback {
        void onProgress(long j, long j2);
    }

    public Request() {
        this((Session) null, (String) null, (Bundle) null, (HttpMethod) null, (Callback) null);
    }

    public Request(Session session2, String graphPath2) {
        this(session2, graphPath2, (Bundle) null, (HttpMethod) null, (Callback) null);
    }

    public Request(Session session2, String graphPath2, Bundle parameters2, HttpMethod httpMethod2) {
        this(session2, graphPath2, parameters2, httpMethod2, (Callback) null);
    }

    public Request(Session session2, String graphPath2, Bundle parameters2, HttpMethod httpMethod2, Callback callback2) {
        this(session2, graphPath2, parameters2, httpMethod2, callback2, (String) null);
    }

    public Request(Session session2, String graphPath2, Bundle parameters2, HttpMethod httpMethod2, Callback callback2, String version2) {
        this.batchEntryOmitResultOnSuccess = true;
        this.session = session2;
        this.graphPath = graphPath2;
        this.callback = callback2;
        this.version = version2;
        setHttpMethod(httpMethod2);
        if (parameters2 != null) {
            this.parameters = new Bundle(parameters2);
        } else {
            this.parameters = new Bundle();
        }
        if (this.version == null) {
            this.version = ServerProtocol.getAPIVersion();
        }
    }

    Request(Session session2, URL overriddenURL2) {
        this.batchEntryOmitResultOnSuccess = true;
        this.session = session2;
        this.overriddenURL = overriddenURL2.toString();
        setHttpMethod(HttpMethod.GET);
        this.parameters = new Bundle();
    }

    public static Request newPostRequest(Session session2, String graphPath2, GraphObject graphObject2, Callback callback2) {
        Request request = new Request(session2, graphPath2, (Bundle) null, HttpMethod.POST, callback2);
        request.setGraphObject(graphObject2);
        return request;
    }

    public static Request newMeRequest(Session session2, final GraphUserCallback callback2) {
        return new Request(session2, ME, (Bundle) null, (HttpMethod) null, new Callback() {
            public void onCompleted(Response response) {
                if (callback2 != null) {
                    callback2.onCompleted((GraphUser) response.getGraphObjectAs(GraphUser.class), response);
                }
            }
        });
    }

    public static Request newMyFriendsRequest(Session session2, final GraphUserListCallback callback2) {
        return new Request(session2, MY_FRIENDS, (Bundle) null, (HttpMethod) null, new Callback() {
            public void onCompleted(Response response) {
                if (callback2 != null) {
                    callback2.onCompleted(Request.typedListFromResponse(response, GraphUser.class), response);
                }
            }
        });
    }

    public static Request newUploadPhotoRequest(Session session2, Bitmap image, Callback callback2) {
        Bundle parameters2 = new Bundle(1);
        parameters2.putParcelable(PICTURE_PARAM, image);
        return new Request(session2, MY_PHOTOS, parameters2, HttpMethod.POST, callback2);
    }

    public static Request newUploadPhotoRequest(Session session2, File file, Callback callback2) throws FileNotFoundException {
        ParcelFileDescriptor descriptor = ParcelFileDescriptor.open(file, 268435456);
        Bundle parameters2 = new Bundle(1);
        parameters2.putParcelable(PICTURE_PARAM, descriptor);
        return new Request(session2, MY_PHOTOS, parameters2, HttpMethod.POST, callback2);
    }

    public static Request newUploadVideoRequest(Session session2, File file, Callback callback2) throws FileNotFoundException {
        ParcelFileDescriptor descriptor = ParcelFileDescriptor.open(file, 268435456);
        Bundle parameters2 = new Bundle(1);
        parameters2.putParcelable(file.getName(), descriptor);
        return new Request(session2, MY_VIDEOS, parameters2, HttpMethod.POST, callback2);
    }

    public static Request newGraphPathRequest(Session session2, String graphPath2, Callback callback2) {
        return new Request(session2, graphPath2, (Bundle) null, (HttpMethod) null, callback2);
    }

    public static Request newPlacesSearchRequest(Session session2, Location location, int radiusInMeters, int resultsLimit, String searchText, final GraphPlaceListCallback callback2) {
        if (location != null || !Utility.isNullOrEmpty(searchText)) {
            Bundle parameters2 = new Bundle(5);
            parameters2.putString(MessagingSmsConsts.TYPE, "place");
            parameters2.putInt("limit", resultsLimit);
            if (location != null) {
                parameters2.putString("center", String.format(Locale.US, "%f,%f", new Object[]{Double.valueOf(location.getLatitude()), Double.valueOf(location.getLongitude())}));
                parameters2.putInt("distance", radiusInMeters);
            }
            if (!Utility.isNullOrEmpty(searchText)) {
                parameters2.putString("q", searchText);
            }
            return new Request(session2, SEARCH, parameters2, HttpMethod.GET, new Callback() {
                public void onCompleted(Response response) {
                    if (callback2 != null) {
                        callback2.onCompleted(Request.typedListFromResponse(response, GraphPlace.class), response);
                    }
                }
            });
        }
        throw new FacebookException("Either location or searchText must be specified.");
    }

    public static Request newStatusUpdateRequest(Session session2, String message, Callback callback2) {
        return newStatusUpdateRequest(session2, message, (String) null, (List<String>) null, callback2);
    }

    private static Request newStatusUpdateRequest(Session session2, String message, String placeId, List<String> tagIds, Callback callback2) {
        Bundle parameters2 = new Bundle();
        parameters2.putString("message", message);
        if (placeId != null) {
            parameters2.putString("place", placeId);
        }
        if (tagIds != null && tagIds.size() > 0) {
            parameters2.putString("tags", TextUtils.join(",", tagIds));
        }
        return new Request(session2, MY_FEED, parameters2, HttpMethod.POST, callback2);
    }

    public static Request newStatusUpdateRequest(Session session2, String message, GraphPlace place, List<GraphUser> tags, Callback callback2) {
        List<String> tagIds = null;
        if (tags != null) {
            tagIds = new ArrayList<>(tags.size());
            for (GraphUser tag2 : tags) {
                tagIds.add(tag2.getId());
            }
        }
        return newStatusUpdateRequest(session2, message, place == null ? null : place.getId(), tagIds, callback2);
    }

    public static Request newCustomAudienceThirdPartyIdRequest(Session session2, Context context, Callback callback2) {
        return newCustomAudienceThirdPartyIdRequest(session2, context, (String) null, callback2);
    }

    public static Request newCustomAudienceThirdPartyIdRequest(Session session2, Context context, String applicationId, Callback callback2) {
        if (session2 == null) {
            session2 = Session.getActiveSession();
        }
        if (session2 != null && !session2.isOpened()) {
            session2 = null;
        }
        if (applicationId == null) {
            if (session2 != null) {
                applicationId = session2.getApplicationId();
            } else {
                applicationId = Utility.getMetadataApplicationId(context);
            }
        }
        if (applicationId == null) {
            throw new FacebookException("Facebook App ID cannot be determined");
        }
        String endpoint = applicationId + "/custom_audience_third_party_id";
        AttributionIdentifiers attributionIdentifiers = AttributionIdentifiers.getAttributionIdentifiers(context);
        Bundle parameters2 = new Bundle();
        if (session2 == null) {
            String udid = attributionIdentifiers.getAttributionId() != null ? attributionIdentifiers.getAttributionId() : attributionIdentifiers.getAndroidAdvertiserId();
            if (attributionIdentifiers.getAttributionId() != null) {
                parameters2.putString("udid", udid);
            }
        }
        if (Settings.getLimitEventAndDataUsage(context) || attributionIdentifiers.isTrackingLimited()) {
            parameters2.putString("limit_event_usage", AppEventsConstants.EVENT_PARAM_VALUE_YES);
        }
        return new Request(session2, endpoint, parameters2, HttpMethod.GET, callback2);
    }

    public static Request newUploadStagingResourceWithImageRequest(Session session2, Bitmap image, Callback callback2) {
        Bundle parameters2 = new Bundle(1);
        parameters2.putParcelable(AndroidProtocolHandler.FILE_SCHEME, image);
        return new Request(session2, MY_STAGING_RESOURCES, parameters2, HttpMethod.POST, callback2);
    }

    public static Request newUploadStagingResourceWithImageRequest(Session session2, File file, Callback callback2) throws FileNotFoundException {
        ParcelFileDescriptorWithMimeType descriptorWithMimeType = new ParcelFileDescriptorWithMimeType(ParcelFileDescriptor.open(file, 268435456), "image/png");
        Bundle parameters2 = new Bundle(1);
        parameters2.putParcelable(AndroidProtocolHandler.FILE_SCHEME, descriptorWithMimeType);
        return new Request(session2, MY_STAGING_RESOURCES, parameters2, HttpMethod.POST, callback2);
    }

    public static Request newPostOpenGraphObjectRequest(Session session2, OpenGraphObject openGraphObject, Callback callback2) {
        if (openGraphObject == null) {
            throw new FacebookException("openGraphObject cannot be null");
        } else if (Utility.isNullOrEmpty(openGraphObject.getType())) {
            throw new FacebookException("openGraphObject must have non-null 'type' property");
        } else if (Utility.isNullOrEmpty(openGraphObject.getTitle())) {
            throw new FacebookException("openGraphObject must have non-null 'title' property");
        } else {
            String path = String.format(MY_OBJECTS_FORMAT, new Object[]{openGraphObject.getType()});
            Bundle bundle = new Bundle();
            bundle.putString(OBJECT_PARAM, openGraphObject.getInnerJSONObject().toString());
            return new Request(session2, path, bundle, HttpMethod.POST, callback2);
        }
    }

    public static Request newPostOpenGraphObjectRequest(Session session2, String type, String title, String imageUrl, String url, String description, GraphObject objectProperties, Callback callback2) {
        OpenGraphObject openGraphObject = OpenGraphObject.Factory.createForPost(OpenGraphObject.class, type, title, imageUrl, url, description);
        if (objectProperties != null) {
            openGraphObject.setData(objectProperties);
        }
        return newPostOpenGraphObjectRequest(session2, openGraphObject, callback2);
    }

    public static Request newPostOpenGraphActionRequest(Session session2, OpenGraphAction openGraphAction, Callback callback2) {
        if (openGraphAction == null) {
            throw new FacebookException("openGraphAction cannot be null");
        } else if (Utility.isNullOrEmpty(openGraphAction.getType())) {
            throw new FacebookException("openGraphAction must have non-null 'type' property");
        } else {
            return newPostRequest(session2, String.format(MY_ACTION_FORMAT, new Object[]{openGraphAction.getType()}), openGraphAction, callback2);
        }
    }

    public static Request newDeleteObjectRequest(Session session2, String id, Callback callback2) {
        return new Request(session2, id, (Bundle) null, HttpMethod.DELETE, callback2);
    }

    public static Request newUpdateOpenGraphObjectRequest(Session session2, OpenGraphObject openGraphObject, Callback callback2) {
        if (openGraphObject == null) {
            throw new FacebookException("openGraphObject cannot be null");
        }
        String path = openGraphObject.getId();
        if (path == null) {
            throw new FacebookException("openGraphObject must have an id");
        }
        Bundle bundle = new Bundle();
        bundle.putString(OBJECT_PARAM, openGraphObject.getInnerJSONObject().toString());
        return new Request(session2, path, bundle, HttpMethod.POST, callback2);
    }

    public static Request newUpdateOpenGraphObjectRequest(Session session2, String id, String title, String imageUrl, String url, String description, GraphObject objectProperties, Callback callback2) {
        OpenGraphObject openGraphObject = OpenGraphObject.Factory.createForPost(OpenGraphObject.class, (String) null, title, imageUrl, url, description);
        openGraphObject.setId(id);
        openGraphObject.setData(objectProperties);
        return newUpdateOpenGraphObjectRequest(session2, openGraphObject, callback2);
    }

    public final GraphObject getGraphObject() {
        return this.graphObject;
    }

    public final void setGraphObject(GraphObject graphObject2) {
        this.graphObject = graphObject2;
    }

    public final String getGraphPath() {
        return this.graphPath;
    }

    public final void setGraphPath(String graphPath2) {
        this.graphPath = graphPath2;
    }

    public final HttpMethod getHttpMethod() {
        return this.httpMethod;
    }

    public final void setHttpMethod(HttpMethod httpMethod2) {
        if (this.overriddenURL == null || httpMethod2 == HttpMethod.GET) {
            if (httpMethod2 == null) {
                httpMethod2 = HttpMethod.GET;
            }
            this.httpMethod = httpMethod2;
            return;
        }
        throw new FacebookException("Can't change HTTP method on request with overridden URL.");
    }

    public final String getVersion() {
        return this.version;
    }

    public final void setVersion(String version2) {
        this.version = version2;
    }

    public final Bundle getParameters() {
        return this.parameters;
    }

    public final void setParameters(Bundle parameters2) {
        this.parameters = parameters2;
    }

    public final Session getSession() {
        return this.session;
    }

    public final void setSession(Session session2) {
        this.session = session2;
    }

    public final String getBatchEntryName() {
        return this.batchEntryName;
    }

    public final void setBatchEntryName(String batchEntryName2) {
        this.batchEntryName = batchEntryName2;
    }

    public final String getBatchEntryDependsOn() {
        return this.batchEntryDependsOn;
    }

    public final void setBatchEntryDependsOn(String batchEntryDependsOn2) {
        this.batchEntryDependsOn = batchEntryDependsOn2;
    }

    public final boolean getBatchEntryOmitResultOnSuccess() {
        return this.batchEntryOmitResultOnSuccess;
    }

    public final void setBatchEntryOmitResultOnSuccess(boolean batchEntryOmitResultOnSuccess2) {
        this.batchEntryOmitResultOnSuccess = batchEntryOmitResultOnSuccess2;
    }

    public static final String getDefaultBatchApplicationId() {
        return defaultBatchApplicationId;
    }

    public static final void setDefaultBatchApplicationId(String applicationId) {
        defaultBatchApplicationId = applicationId;
    }

    public final Callback getCallback() {
        return this.callback;
    }

    public final void setCallback(Callback callback2) {
        this.callback = callback2;
    }

    public final void setTag(Object tag2) {
        this.tag = tag2;
    }

    public final Object getTag() {
        return this.tag;
    }

    @Deprecated
    public static RequestAsyncTask executePostRequestAsync(Session session2, String graphPath2, GraphObject graphObject2, Callback callback2) {
        return newPostRequest(session2, graphPath2, graphObject2, callback2).executeAsync();
    }

    @Deprecated
    public static RequestAsyncTask executeMeRequestAsync(Session session2, GraphUserCallback callback2) {
        return newMeRequest(session2, callback2).executeAsync();
    }

    @Deprecated
    public static RequestAsyncTask executeMyFriendsRequestAsync(Session session2, GraphUserListCallback callback2) {
        return newMyFriendsRequest(session2, callback2).executeAsync();
    }

    @Deprecated
    public static RequestAsyncTask executeUploadPhotoRequestAsync(Session session2, Bitmap image, Callback callback2) {
        return newUploadPhotoRequest(session2, image, callback2).executeAsync();
    }

    @Deprecated
    public static RequestAsyncTask executeUploadPhotoRequestAsync(Session session2, File file, Callback callback2) throws FileNotFoundException {
        return newUploadPhotoRequest(session2, file, callback2).executeAsync();
    }

    @Deprecated
    public static RequestAsyncTask executeGraphPathRequestAsync(Session session2, String graphPath2, Callback callback2) {
        return newGraphPathRequest(session2, graphPath2, callback2).executeAsync();
    }

    @Deprecated
    public static RequestAsyncTask executePlacesSearchRequestAsync(Session session2, Location location, int radiusInMeters, int resultsLimit, String searchText, GraphPlaceListCallback callback2) {
        return newPlacesSearchRequest(session2, location, radiusInMeters, resultsLimit, searchText, callback2).executeAsync();
    }

    @Deprecated
    public static RequestAsyncTask executeStatusUpdateRequestAsync(Session session2, String message, Callback callback2) {
        return newStatusUpdateRequest(session2, message, callback2).executeAsync();
    }

    public final Response executeAndWait() {
        return executeAndWait(this);
    }

    public final RequestAsyncTask executeAsync() {
        return executeBatchAsync(this);
    }

    public static HttpURLConnection toHttpConnection(Request... requests) {
        return toHttpConnection((Collection<Request>) Arrays.asList(requests));
    }

    public static HttpURLConnection toHttpConnection(Collection<Request> requests) {
        Validate.notEmptyAndContainsNoNulls(requests, Requests.EXTRA_REQUESTS);
        return toHttpConnection(new RequestBatch(requests));
    }

    public static HttpURLConnection toHttpConnection(RequestBatch requests) {
        URL url;
        try {
            if (requests.size() == 1) {
                url = new URL(requests.get(0).getUrlForSingleRequest());
            } else {
                url = new URL(ServerProtocol.getGraphUrlBase());
            }
            try {
                HttpURLConnection connection = createConnection(url);
                serializeToUrlConnection(requests, connection);
                return connection;
            } catch (IOException e) {
                throw new FacebookException("could not construct request body", e);
            } catch (JSONException e2) {
                throw new FacebookException("could not construct request body", e2);
            }
        } catch (MalformedURLException e3) {
            throw new FacebookException("could not construct URL for request", e3);
        }
    }

    public static Response executeAndWait(Request request) {
        List<Response> responses = executeBatchAndWait(request);
        if (responses != null && responses.size() == 1) {
            return responses.get(0);
        }
        throw new FacebookException("invalid state: expected a single response");
    }

    public static List<Response> executeBatchAndWait(Request... requests) {
        Validate.notNull(requests, Requests.EXTRA_REQUESTS);
        return executeBatchAndWait((Collection<Request>) Arrays.asList(requests));
    }

    public static List<Response> executeBatchAndWait(Collection<Request> requests) {
        return executeBatchAndWait(new RequestBatch(requests));
    }

    public static List<Response> executeBatchAndWait(RequestBatch requests) {
        Validate.notEmptyAndContainsNoNulls(requests, Requests.EXTRA_REQUESTS);
        try {
            return executeConnectionAndWait(toHttpConnection(requests), requests);
        } catch (Exception ex) {
            List<Response> responses = Response.constructErrorResponses(requests.getRequests(), (HttpURLConnection) null, new FacebookException((Throwable) ex));
            runCallbacks(requests, responses);
            return responses;
        }
    }

    public static RequestAsyncTask executeBatchAsync(Request... requests) {
        Validate.notNull(requests, Requests.EXTRA_REQUESTS);
        return executeBatchAsync((Collection<Request>) Arrays.asList(requests));
    }

    public static RequestAsyncTask executeBatchAsync(Collection<Request> requests) {
        return executeBatchAsync(new RequestBatch(requests));
    }

    public static RequestAsyncTask executeBatchAsync(RequestBatch requests) {
        Validate.notEmptyAndContainsNoNulls(requests, Requests.EXTRA_REQUESTS);
        RequestAsyncTask asyncTask = new RequestAsyncTask(requests);
        asyncTask.executeOnSettingsExecutor();
        return asyncTask;
    }

    public static List<Response> executeConnectionAndWait(HttpURLConnection connection, Collection<Request> requests) {
        return executeConnectionAndWait(connection, new RequestBatch(requests));
    }

    public static List<Response> executeConnectionAndWait(HttpURLConnection connection, RequestBatch requests) {
        List<Response> responses = Response.fromHttpConnection(connection, requests);
        Utility.disconnectQuietly(connection);
        int numRequests = requests.size();
        if (numRequests != responses.size()) {
            throw new FacebookException(String.format("Received %d responses while expecting %d", new Object[]{Integer.valueOf(responses.size()), Integer.valueOf(numRequests)}));
        }
        runCallbacks(requests, responses);
        HashSet<Session> sessions = new HashSet<>();
        Iterator i$ = requests.iterator();
        while (i$.hasNext()) {
            Request request = (Request) i$.next();
            if (request.session != null) {
                sessions.add(request.session);
            }
        }
        Iterator i$2 = sessions.iterator();
        while (i$2.hasNext()) {
            i$2.next().extendAccessTokenIfNeeded();
        }
        return responses;
    }

    public static RequestAsyncTask executeConnectionAsync(HttpURLConnection connection, RequestBatch requests) {
        return executeConnectionAsync((Handler) null, connection, requests);
    }

    public static RequestAsyncTask executeConnectionAsync(Handler callbackHandler, HttpURLConnection connection, RequestBatch requests) {
        Validate.notNull(connection, "connection");
        RequestAsyncTask asyncTask = new RequestAsyncTask(connection, requests);
        requests.setCallbackHandler(callbackHandler);
        asyncTask.executeOnSettingsExecutor();
        return asyncTask;
    }

    public String toString() {
        return "{Request: " + " session: " + this.session + ", graphPath: " + this.graphPath + ", graphObject: " + this.graphObject + ", httpMethod: " + this.httpMethod + ", parameters: " + this.parameters + "}";
    }

    static void runCallbacks(final RequestBatch requests, List<Response> responses) {
        int numRequests = requests.size();
        final ArrayList<Pair<Callback, Response>> callbacks = new ArrayList<>();
        for (int i = 0; i < numRequests; i++) {
            Request request = requests.get(i);
            if (request.callback != null) {
                callbacks.add(new Pair(request.callback, responses.get(i)));
            }
        }
        if (callbacks.size() > 0) {
            Runnable runnable = new Runnable() {
                public void run() {
                    Iterator i$ = callbacks.iterator();
                    while (i$.hasNext()) {
                        Pair<Callback, Response> pair = (Pair) i$.next();
                        ((Callback) pair.first).onCompleted((Response) pair.second);
                    }
                    for (RequestBatch.Callback batchCallback : requests.getCallbacks()) {
                        batchCallback.onBatchCompleted(requests);
                    }
                }
            };
            Handler callbackHandler = requests.getCallbackHandler();
            if (callbackHandler == null) {
                runnable.run();
            } else {
                callbackHandler.post(runnable);
            }
        }
    }

    static HttpURLConnection createConnection(URL url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty(USER_AGENT_HEADER, getUserAgent());
        connection.setRequestProperty(CONTENT_TYPE_HEADER, getMimeContentType());
        connection.setRequestProperty(ACCEPT_LANGUAGE_HEADER, Locale.getDefault().toString());
        connection.setChunkedStreamingMode(0);
        return connection;
    }

    private void addCommonParameters() {
        if (this.session != null) {
            if (!this.session.isOpened()) {
                throw new FacebookException("Session provided to a Request in un-opened state.");
            } else if (!this.parameters.containsKey("access_token")) {
                String accessToken = this.session.getAccessToken();
                Logger.registerAccessToken(accessToken);
                this.parameters.putString("access_token", accessToken);
            }
        } else if (!this.parameters.containsKey("access_token")) {
            String appID = Settings.getApplicationId();
            String clientToken = Settings.getClientToken();
            if (Utility.isNullOrEmpty(appID) || Utility.isNullOrEmpty(clientToken)) {
                Log.d(TAG, "Warning: Sessionless Request needs token but missing either application ID or client token.");
            } else {
                this.parameters.putString("access_token", appID + "|" + clientToken);
            }
        }
        this.parameters.putString(SDK_PARAM, SDK_ANDROID);
        this.parameters.putString(FORMAT_PARAM, FORMAT_JSON);
    }

    private String appendParametersToBaseUrl(String baseUrl) {
        Uri.Builder uriBuilder = new Uri.Builder().encodedPath(baseUrl);
        for (String key : this.parameters.keySet()) {
            Object value = this.parameters.get(key);
            if (value == null) {
                value = "";
            }
            if (isSupportedParameterType(value)) {
                uriBuilder.appendQueryParameter(key, parameterToString(value).toString());
            } else if (this.httpMethod == HttpMethod.GET) {
                throw new IllegalArgumentException(String.format("Unsupported parameter type for GET request: %s", new Object[]{value.getClass().getSimpleName()}));
            }
        }
        return uriBuilder.toString();
    }

    /* access modifiers changed from: package-private */
    public final String getUrlForBatchedRequest() {
        if (this.overriddenURL != null) {
            throw new FacebookException("Can't override URL for a batch request");
        }
        String baseUrl = getGraphPathWithVersion();
        addCommonParameters();
        return appendParametersToBaseUrl(baseUrl);
    }

    /* access modifiers changed from: package-private */
    public final String getUrlForSingleRequest() {
        String graphBaseUrlBase;
        if (this.overriddenURL != null) {
            return this.overriddenURL.toString();
        }
        if (getHttpMethod() != HttpMethod.POST || this.graphPath == null || !this.graphPath.endsWith(VIDEOS_SUFFIX)) {
            graphBaseUrlBase = ServerProtocol.getGraphUrlBase();
        } else {
            graphBaseUrlBase = ServerProtocol.getGraphVideoUrlBase();
        }
        String baseUrl = String.format("%s/%s", new Object[]{graphBaseUrlBase, getGraphPathWithVersion()});
        addCommonParameters();
        return appendParametersToBaseUrl(baseUrl);
    }

    private String getGraphPathWithVersion() {
        if (versionPattern.matcher(this.graphPath).matches()) {
            return this.graphPath;
        }
        return String.format("%s/%s", new Object[]{this.version, this.graphPath});
    }

    private static class Attachment {
        private final Request request;
        private final Object value;

        public Attachment(Request request2, Object value2) {
            this.request = request2;
            this.value = value2;
        }

        public Request getRequest() {
            return this.request;
        }

        public Object getValue() {
            return this.value;
        }
    }

    private void serializeToBatch(JSONArray batch, Map<String, Attachment> attachments) throws JSONException, IOException {
        JSONObject batchEntry = new JSONObject();
        if (this.batchEntryName != null) {
            batchEntry.put(BATCH_ENTRY_NAME_PARAM, this.batchEntryName);
            batchEntry.put(BATCH_ENTRY_OMIT_RESPONSE_ON_SUCCESS_PARAM, this.batchEntryOmitResultOnSuccess);
        }
        if (this.batchEntryDependsOn != null) {
            batchEntry.put(BATCH_ENTRY_DEPENDS_ON_PARAM, this.batchEntryDependsOn);
        }
        String relativeURL = getUrlForBatchedRequest();
        batchEntry.put(BATCH_RELATIVE_URL_PARAM, relativeURL);
        batchEntry.put(BATCH_METHOD_PARAM, this.httpMethod);
        if (this.session != null) {
            Logger.registerAccessToken(this.session.getAccessToken());
        }
        ArrayList<String> attachmentNames = new ArrayList<>();
        for (String key : this.parameters.keySet()) {
            Object value = this.parameters.get(key);
            if (isSupportedAttachmentType(value)) {
                String name = String.format("%s%d", new Object[]{AndroidProtocolHandler.FILE_SCHEME, Integer.valueOf(attachments.size())});
                attachmentNames.add(name);
                attachments.put(name, new Attachment(this, value));
            }
        }
        if (!attachmentNames.isEmpty()) {
            batchEntry.put(ATTACHED_FILES_PARAM, TextUtils.join(",", attachmentNames));
        }
        if (this.graphObject != null) {
            final ArrayList<String> keysAndValues = new ArrayList<>();
            processGraphObject(this.graphObject, relativeURL, new KeyValueSerializer() {
                public void writeString(String key, String value) throws IOException {
                    keysAndValues.add(String.format("%s=%s", new Object[]{key, URLEncoder.encode(value, "UTF-8")}));
                }
            });
            batchEntry.put("body", TextUtils.join("&", keysAndValues));
        }
        batch.put(batchEntry);
    }

    private static boolean hasOnProgressCallbacks(RequestBatch requests) {
        for (RequestBatch.Callback callback2 : requests.getCallbacks()) {
            if (callback2 instanceof RequestBatch.OnProgressCallback) {
                return true;
            }
        }
        Iterator i$ = requests.iterator();
        while (i$.hasNext()) {
            if (((Request) i$.next()).getCallback() instanceof OnProgressCallback) {
                return true;
            }
        }
        return false;
    }

    static final void serializeToUrlConnection(RequestBatch requests, HttpURLConnection connection) throws IOException, JSONException {
        OutputStream outputStream;
        boolean isPost = false;
        Logger logger = new Logger(LoggingBehavior.REQUESTS, "Request");
        int numRequests = requests.size();
        HttpMethod connectionHttpMethod = numRequests == 1 ? requests.get(0).httpMethod : HttpMethod.POST;
        connection.setRequestMethod(connectionHttpMethod.name());
        URL url = connection.getURL();
        logger.append("Request:\n");
        logger.appendKeyValue("Id", requests.getId());
        logger.appendKeyValue("URL", url);
        logger.appendKeyValue("Method", connection.getRequestMethod());
        logger.appendKeyValue(USER_AGENT_HEADER, connection.getRequestProperty(USER_AGENT_HEADER));
        logger.appendKeyValue(CONTENT_TYPE_HEADER, connection.getRequestProperty(CONTENT_TYPE_HEADER));
        connection.setConnectTimeout(requests.getTimeout());
        connection.setReadTimeout(requests.getTimeout());
        if (connectionHttpMethod == HttpMethod.POST) {
            isPost = true;
        }
        if (!isPost) {
            logger.log();
            return;
        }
        connection.setDoOutput(true);
        try {
            if (hasOnProgressCallbacks(requests)) {
                ProgressNoopOutputStream countingStream = new ProgressNoopOutputStream(requests.getCallbackHandler());
                processRequest(requests, (Logger) null, numRequests, url, countingStream);
                outputStream = new ProgressOutputStream(new BufferedOutputStream(connection.getOutputStream()), requests, countingStream.getProgressMap(), (long) countingStream.getMaxProgress());
            } else {
                outputStream = new BufferedOutputStream(connection.getOutputStream());
            }
            try {
                processRequest(requests, logger, numRequests, url, outputStream);
                outputStream.close();
                logger.log();
            } catch (Throwable th) {
                th = th;
                outputStream.close();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            outputStream = null;
        }
    }

    private static void processRequest(RequestBatch requests, Logger logger, int numRequests, URL url, OutputStream outputStream) throws IOException, JSONException {
        Serializer serializer = new Serializer(outputStream, logger);
        if (numRequests == 1) {
            Request request = requests.get(0);
            Map<String, Attachment> attachments = new HashMap<>();
            for (String key : request.parameters.keySet()) {
                Object value = request.parameters.get(key);
                if (isSupportedAttachmentType(value)) {
                    attachments.put(key, new Attachment(request, value));
                }
            }
            if (logger != null) {
                logger.append("  Parameters:\n");
            }
            serializeParameters(request.parameters, serializer, request);
            if (logger != null) {
                logger.append("  Attachments:\n");
            }
            serializeAttachments(attachments, serializer);
            if (request.graphObject != null) {
                processGraphObject(request.graphObject, url.getPath(), serializer);
                return;
            }
            return;
        }
        String batchAppID = getBatchAppId(requests);
        if (Utility.isNullOrEmpty(batchAppID)) {
            throw new FacebookException("At least one request in a batch must have an open Session, or a default app ID must be specified.");
        }
        serializer.writeString(BATCH_APP_ID_PARAM, batchAppID);
        Map<String, Attachment> attachments2 = new HashMap<>();
        serializeRequestsAsJSON(serializer, requests, attachments2);
        if (logger != null) {
            logger.append("  Attachments:\n");
        }
        serializeAttachments(attachments2, serializer);
    }

    private static boolean isMeRequest(String path) {
        Matcher matcher = versionPattern.matcher(path);
        if (matcher.matches()) {
            path = matcher.group(1);
        }
        if (path.startsWith("me/") || path.startsWith("/me/")) {
            return true;
        }
        return false;
    }

    private static void processGraphObject(GraphObject graphObject2, String path, KeyValueSerializer serializer) throws IOException {
        boolean passByValue;
        boolean isOGAction = false;
        if (isMeRequest(path)) {
            int colonLocation = path.indexOf(":");
            int questionMarkLocation = path.indexOf("?");
            if (colonLocation <= 3 || (questionMarkLocation != -1 && colonLocation >= questionMarkLocation)) {
                isOGAction = false;
            } else {
                isOGAction = true;
            }
        }
        for (Map.Entry<String, Object> entry : graphObject2.asMap().entrySet()) {
            if (!isOGAction || !entry.getKey().equalsIgnoreCase("image")) {
                passByValue = false;
            } else {
                passByValue = true;
            }
            processGraphObjectProperty(entry.getKey(), entry.getValue(), serializer, passByValue);
        }
    }

    private static void processGraphObjectProperty(String key, Object value, KeyValueSerializer serializer, boolean passByValue) throws IOException {
        Class<?> valueClass = value.getClass();
        if (GraphObject.class.isAssignableFrom(valueClass)) {
            value = ((GraphObject) value).getInnerJSONObject();
            valueClass = value.getClass();
        } else if (GraphObjectList.class.isAssignableFrom(valueClass)) {
            value = ((GraphObjectList) value).getInnerJSONArray();
            valueClass = value.getClass();
        }
        if (JSONObject.class.isAssignableFrom(valueClass)) {
            JSONObject jsonObject = (JSONObject) value;
            if (passByValue) {
                Iterator<String> keys = jsonObject.keys();
                while (keys.hasNext()) {
                    String propertyName = keys.next();
                    processGraphObjectProperty(String.format("%s[%s]", new Object[]{key, propertyName}), jsonObject.opt(propertyName), serializer, passByValue);
                }
            } else if (jsonObject.has("id")) {
                processGraphObjectProperty(key, jsonObject.optString("id"), serializer, passByValue);
            } else if (jsonObject.has("url")) {
                processGraphObjectProperty(key, jsonObject.optString("url"), serializer, passByValue);
            } else if (jsonObject.has(NativeProtocol.OPEN_GRAPH_CREATE_OBJECT_KEY)) {
                processGraphObjectProperty(key, jsonObject.toString(), serializer, passByValue);
            }
        } else if (JSONArray.class.isAssignableFrom(valueClass)) {
            JSONArray jsonArray = (JSONArray) value;
            int length = jsonArray.length();
            for (int i = 0; i < length; i++) {
                processGraphObjectProperty(String.format("%s[%d]", new Object[]{key, Integer.valueOf(i)}), jsonArray.opt(i), serializer, passByValue);
            }
        } else if (String.class.isAssignableFrom(valueClass) || Number.class.isAssignableFrom(valueClass) || Boolean.class.isAssignableFrom(valueClass)) {
            serializer.writeString(key, value.toString());
        } else if (Date.class.isAssignableFrom(valueClass)) {
            KeyValueSerializer keyValueSerializer = serializer;
            String str = key;
            keyValueSerializer.writeString(str, new SimpleDateFormat(ISO_8601_FORMAT_STRING, Locale.US).format((Date) value));
        }
    }

    private static void serializeParameters(Bundle bundle, Serializer serializer, Request request) throws IOException {
        for (String key : bundle.keySet()) {
            Object value = bundle.get(key);
            if (isSupportedParameterType(value)) {
                serializer.writeObject(key, value, request);
            }
        }
    }

    private static void serializeAttachments(Map<String, Attachment> attachments, Serializer serializer) throws IOException {
        for (String key : attachments.keySet()) {
            Attachment attachment = attachments.get(key);
            if (isSupportedAttachmentType(attachment.getValue())) {
                serializer.writeObject(key, attachment.getValue(), attachment.getRequest());
            }
        }
    }

    private static void serializeRequestsAsJSON(Serializer serializer, Collection<Request> requests, Map<String, Attachment> attachments) throws JSONException, IOException {
        JSONArray batch = new JSONArray();
        for (Request request : requests) {
            request.serializeToBatch(batch, attachments);
        }
        serializer.writeRequestsAsJson(BATCH_PARAM, batch, requests);
    }

    private static String getMimeContentType() {
        return String.format("multipart/form-data; boundary=%s", new Object[]{MIME_BOUNDARY});
    }

    private static String getUserAgent() {
        if (userAgent == null) {
            userAgent = String.format("%s.%s", new Object[]{USER_AGENT_BASE, FacebookSdkVersion.BUILD});
        }
        return userAgent;
    }

    private static String getBatchAppId(RequestBatch batch) {
        if (!Utility.isNullOrEmpty(batch.getBatchApplicationId())) {
            return batch.getBatchApplicationId();
        }
        Iterator i$ = batch.iterator();
        while (i$.hasNext()) {
            Session session2 = ((Request) i$.next()).session;
            if (session2 != null) {
                return session2.getApplicationId();
            }
        }
        return defaultBatchApplicationId;
    }

    /* access modifiers changed from: private */
    public static <T extends GraphObject> List<T> typedListFromResponse(Response response, Class<T> clazz) {
        GraphObjectList<GraphObject> data;
        GraphMultiResult multiResult = (GraphMultiResult) response.getGraphObjectAs(GraphMultiResult.class);
        if (multiResult == null || (data = multiResult.getData()) == null) {
            return null;
        }
        return data.castToListOf(clazz);
    }

    private static boolean isSupportedAttachmentType(Object value) {
        return (value instanceof Bitmap) || (value instanceof byte[]) || (value instanceof ParcelFileDescriptor) || (value instanceof ParcelFileDescriptorWithMimeType);
    }

    /* access modifiers changed from: private */
    public static boolean isSupportedParameterType(Object value) {
        return (value instanceof String) || (value instanceof Boolean) || (value instanceof Number) || (value instanceof Date);
    }

    /* access modifiers changed from: private */
    public static String parameterToString(Object value) {
        if (value instanceof String) {
            return (String) value;
        }
        if ((value instanceof Boolean) || (value instanceof Number)) {
            return value.toString();
        }
        if (value instanceof Date) {
            return new SimpleDateFormat(ISO_8601_FORMAT_STRING, Locale.US).format(value);
        }
        throw new IllegalArgumentException("Unsupported parameter type.");
    }

    private static class Serializer implements KeyValueSerializer {
        private boolean firstWrite = true;
        private final Logger logger;
        private final OutputStream outputStream;

        public Serializer(OutputStream outputStream2, Logger logger2) {
            this.outputStream = outputStream2;
            this.logger = logger2;
        }

        public void writeObject(String key, Object value, Request request) throws IOException {
            if (this.outputStream instanceof RequestOutputStream) {
                ((RequestOutputStream) this.outputStream).setCurrentRequest(request);
            }
            if (Request.isSupportedParameterType(value)) {
                writeString(key, Request.parameterToString(value));
            } else if (value instanceof Bitmap) {
                writeBitmap(key, (Bitmap) value);
            } else if (value instanceof byte[]) {
                writeBytes(key, (byte[]) value);
            } else if (value instanceof ParcelFileDescriptor) {
                writeFile(key, (ParcelFileDescriptor) value, (String) null);
            } else if (value instanceof ParcelFileDescriptorWithMimeType) {
                writeFile(key, (ParcelFileDescriptorWithMimeType) value);
            } else {
                throw new IllegalArgumentException("value is not a supported type: String, Bitmap, byte[]");
            }
        }

        public void writeRequestsAsJson(String key, JSONArray requestJsonArray, Collection<Request> requests) throws IOException, JSONException {
            if (!(this.outputStream instanceof RequestOutputStream)) {
                writeString(key, requestJsonArray.toString());
                return;
            }
            RequestOutputStream requestOutputStream = (RequestOutputStream) this.outputStream;
            writeContentDisposition(key, (String) null, (String) null);
            write("[", new Object[0]);
            int i = 0;
            for (Request request : requests) {
                JSONObject requestJson = requestJsonArray.getJSONObject(i);
                requestOutputStream.setCurrentRequest(request);
                if (i > 0) {
                    write(",%s", requestJson.toString());
                } else {
                    write("%s", requestJson.toString());
                }
                i++;
            }
            write("]", new Object[0]);
            if (this.logger != null) {
                this.logger.appendKeyValue("    " + key, requestJsonArray.toString());
            }
        }

        public void writeString(String key, String value) throws IOException {
            writeContentDisposition(key, (String) null, (String) null);
            writeLine("%s", value);
            writeRecordBoundary();
            if (this.logger != null) {
                this.logger.appendKeyValue("    " + key, value);
            }
        }

        public void writeBitmap(String key, Bitmap bitmap) throws IOException {
            writeContentDisposition(key, key, "image/png");
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, this.outputStream);
            writeLine("", new Object[0]);
            writeRecordBoundary();
            if (this.logger != null) {
                this.logger.appendKeyValue("    " + key, "<Image>");
            }
        }

        public void writeBytes(String key, byte[] bytes) throws IOException {
            writeContentDisposition(key, key, "content/unknown");
            this.outputStream.write(bytes);
            writeLine("", new Object[0]);
            writeRecordBoundary();
            if (this.logger != null) {
                this.logger.appendKeyValue("    " + key, String.format("<Data: %d>", new Object[]{Integer.valueOf(bytes.length)}));
            }
        }

        public void writeFile(String key, ParcelFileDescriptorWithMimeType descriptorWithMimeType) throws IOException {
            writeFile(key, descriptorWithMimeType.getFileDescriptor(), descriptorWithMimeType.getMimeType());
        }

        /* JADX WARNING: Removed duplicated region for block: B:26:0x0096  */
        /* JADX WARNING: Removed duplicated region for block: B:28:0x009b  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void writeFile(java.lang.String r18, android.os.ParcelFileDescriptor r19, java.lang.String r20) throws java.io.IOException {
            /*
                r17 = this;
                if (r20 != 0) goto L_0x0004
                java.lang.String r20 = "content/unknown"
            L_0x0004:
                r0 = r17
                r1 = r18
                r2 = r18
                r3 = r20
                r0.writeContentDisposition(r1, r2, r3)
                r10 = 0
                r0 = r17
                java.io.OutputStream r11 = r0.outputStream
                boolean r11 = r11 instanceof com.facebook.ProgressNoopOutputStream
                if (r11 == 0) goto L_0x0065
                r0 = r17
                java.io.OutputStream r11 = r0.outputStream
                com.facebook.ProgressNoopOutputStream r11 = (com.facebook.ProgressNoopOutputStream) r11
                long r12 = r19.getStatSize()
                r11.addProgress(r12)
            L_0x0025:
                java.lang.String r11 = ""
                r12 = 0
                java.lang.Object[] r12 = new java.lang.Object[r12]
                r0 = r17
                r0.writeLine(r11, r12)
                r17.writeRecordBoundary()
                r0 = r17
                com.facebook.internal.Logger r11 = r0.logger
                if (r11 == 0) goto L_0x0064
                r0 = r17
                com.facebook.internal.Logger r11 = r0.logger
                java.lang.StringBuilder r12 = new java.lang.StringBuilder
                r12.<init>()
                java.lang.String r13 = "    "
                java.lang.StringBuilder r12 = r12.append(r13)
                r0 = r18
                java.lang.StringBuilder r12 = r12.append(r0)
                java.lang.String r12 = r12.toString()
                java.lang.String r13 = "<Data: %d>"
                r14 = 1
                java.lang.Object[] r14 = new java.lang.Object[r14]
                r15 = 0
                java.lang.Integer r16 = java.lang.Integer.valueOf(r10)
                r14[r15] = r16
                java.lang.String r13 = java.lang.String.format(r13, r14)
                r11.appendKeyValue(r12, r13)
            L_0x0064:
                return
            L_0x0065:
                r8 = 0
                r5 = 0
                android.os.ParcelFileDescriptor$AutoCloseInputStream r9 = new android.os.ParcelFileDescriptor$AutoCloseInputStream     // Catch:{ all -> 0x0093 }
                r0 = r19
                r9.<init>(r0)     // Catch:{ all -> 0x0093 }
                java.io.BufferedInputStream r6 = new java.io.BufferedInputStream     // Catch:{ all -> 0x009f }
                r6.<init>(r9)     // Catch:{ all -> 0x009f }
                r11 = 8192(0x2000, float:1.14794E-41)
                byte[] r4 = new byte[r11]     // Catch:{ all -> 0x00a2 }
            L_0x0077:
                int r7 = r6.read(r4)     // Catch:{ all -> 0x00a2 }
                r11 = -1
                if (r7 == r11) goto L_0x0088
                r0 = r17
                java.io.OutputStream r11 = r0.outputStream     // Catch:{ all -> 0x00a2 }
                r12 = 0
                r11.write(r4, r12, r7)     // Catch:{ all -> 0x00a2 }
                int r10 = r10 + r7
                goto L_0x0077
            L_0x0088:
                if (r6 == 0) goto L_0x008d
                r6.close()
            L_0x008d:
                if (r9 == 0) goto L_0x0025
                r9.close()
                goto L_0x0025
            L_0x0093:
                r11 = move-exception
            L_0x0094:
                if (r5 == 0) goto L_0x0099
                r5.close()
            L_0x0099:
                if (r8 == 0) goto L_0x009e
                r8.close()
            L_0x009e:
                throw r11
            L_0x009f:
                r11 = move-exception
                r8 = r9
                goto L_0x0094
            L_0x00a2:
                r11 = move-exception
                r5 = r6
                r8 = r9
                goto L_0x0094
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.Request.Serializer.writeFile(java.lang.String, android.os.ParcelFileDescriptor, java.lang.String):void");
        }

        public void writeRecordBoundary() throws IOException {
            writeLine("--%s", Request.MIME_BOUNDARY);
        }

        public void writeContentDisposition(String name, String filename, String contentType) throws IOException {
            write("Content-Disposition: form-data; name=\"%s\"", name);
            if (filename != null) {
                write("; filename=\"%s\"", filename);
            }
            writeLine("", new Object[0]);
            if (contentType != null) {
                writeLine("%s: %s", Request.CONTENT_TYPE_HEADER, contentType);
            }
            writeLine("", new Object[0]);
        }

        public void write(String format, Object... args) throws IOException {
            if (this.firstWrite) {
                this.outputStream.write("--".getBytes());
                this.outputStream.write(Request.MIME_BOUNDARY.getBytes());
                this.outputStream.write("\r\n".getBytes());
                this.firstWrite = false;
            }
            this.outputStream.write(String.format(format, args).getBytes());
        }

        public void writeLine(String format, Object... args) throws IOException {
            write(format, args);
            write("\r\n", new Object[0]);
        }
    }

    private static class ParcelFileDescriptorWithMimeType implements Parcelable {
        public static final Parcelable.Creator<ParcelFileDescriptorWithMimeType> CREATOR = new Parcelable.Creator<ParcelFileDescriptorWithMimeType>() {
            public ParcelFileDescriptorWithMimeType createFromParcel(Parcel in) {
                return new ParcelFileDescriptorWithMimeType(in);
            }

            public ParcelFileDescriptorWithMimeType[] newArray(int size) {
                return new ParcelFileDescriptorWithMimeType[size];
            }
        };
        private final ParcelFileDescriptor fileDescriptor;
        private final String mimeType;

        public String getMimeType() {
            return this.mimeType;
        }

        public ParcelFileDescriptor getFileDescriptor() {
            return this.fileDescriptor;
        }

        public int describeContents() {
            return 1;
        }

        public void writeToParcel(Parcel out, int flags) {
            out.writeString(this.mimeType);
            out.writeFileDescriptor(this.fileDescriptor.getFileDescriptor());
        }

        public ParcelFileDescriptorWithMimeType(ParcelFileDescriptor fileDescriptor2, String mimeType2) {
            this.mimeType = mimeType2;
            this.fileDescriptor = fileDescriptor2;
        }

        private ParcelFileDescriptorWithMimeType(Parcel in) {
            this.mimeType = in.readString();
            this.fileDescriptor = in.readFileDescriptor();
        }
    }
}
