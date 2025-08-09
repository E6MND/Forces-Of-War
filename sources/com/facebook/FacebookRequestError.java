package com.facebook;

import com.facebook.android.R;
import com.facebook.internal.Utility;
import java.net.HttpURLConnection;
import org.json.JSONException;
import org.json.JSONObject;

public final class FacebookRequestError {
    private static final String BODY_KEY = "body";
    private static final String CODE_KEY = "code";
    private static final int EC_APP_NOT_INSTALLED = 458;
    private static final int EC_APP_TOO_MANY_CALLS = 4;
    private static final int EC_EXPIRED = 463;
    private static final int EC_INVALID_SESSION = 102;
    private static final int EC_INVALID_TOKEN = 190;
    private static final int EC_PASSWORD_CHANGED = 460;
    private static final int EC_PERMISSION_DENIED = 10;
    private static final Range EC_RANGE_PERMISSION = new Range(200, 299);
    private static final int EC_SERVICE_UNAVAILABLE = 2;
    private static final int EC_UNCONFIRMED_USER = 464;
    private static final int EC_UNKNOWN_ERROR = 1;
    private static final int EC_USER_CHECKPOINTED = 459;
    private static final int EC_USER_TOO_MANY_CALLS = 17;
    private static final String ERROR_CODE_FIELD_KEY = "code";
    private static final String ERROR_CODE_KEY = "error_code";
    private static final String ERROR_IS_TRANSIENT_KEY = "is_transient";
    private static final String ERROR_KEY = "error";
    private static final String ERROR_MESSAGE_FIELD_KEY = "message";
    private static final String ERROR_MSG_KEY = "error_msg";
    private static final String ERROR_REASON_KEY = "error_reason";
    private static final String ERROR_SUB_CODE_KEY = "error_subcode";
    private static final String ERROR_TYPE_FIELD_KEY = "type";
    private static final String ERROR_USER_MSG_KEY = "error_user_msg";
    private static final String ERROR_USER_TITLE_KEY = "error_user_title";
    private static final Range HTTP_RANGE_CLIENT_ERROR = new Range(400, 499);
    private static final Range HTTP_RANGE_SERVER_ERROR = new Range(500, 599);
    private static final Range HTTP_RANGE_SUCCESS = new Range(200, 299);
    public static final int INVALID_ERROR_CODE = -1;
    public static final int INVALID_HTTP_STATUS_CODE = -1;
    private static final int INVALID_MESSAGE_ID = 0;
    private final Object batchRequestResult;
    private final Category category;
    private final HttpURLConnection connection;
    private final int errorCode;
    private final boolean errorIsTransient;
    private final String errorMessage;
    private final String errorType;
    private final String errorUserMessage;
    private final String errorUserTitle;
    private final FacebookException exception;
    private final JSONObject requestResult;
    private final JSONObject requestResultBody;
    private final int requestStatusCode;
    private final boolean shouldNotifyUser;
    private final int subErrorCode;
    private final int userActionMessageId;

    public enum Category {
        AUTHENTICATION_RETRY,
        AUTHENTICATION_REOPEN_SESSION,
        PERMISSION,
        SERVER,
        THROTTLING,
        OTHER,
        BAD_REQUEST,
        CLIENT
    }

    private static class Range {
        private final int end;
        private final int start;

        private Range(int start2, int end2) {
            this.start = start2;
            this.end = end2;
        }

        /* access modifiers changed from: package-private */
        public boolean contains(int value) {
            return this.start <= value && value <= this.end;
        }
    }

    private FacebookRequestError(int requestStatusCode2, int errorCode2, int subErrorCode2, String errorType2, String errorMessage2, String errorUserTitle2, String errorUserMessage2, boolean errorIsTransient2, JSONObject requestResultBody2, JSONObject requestResult2, Object batchRequestResult2, HttpURLConnection connection2, FacebookException exception2) {
        this.requestStatusCode = requestStatusCode2;
        this.errorCode = errorCode2;
        this.subErrorCode = subErrorCode2;
        this.errorType = errorType2;
        this.errorMessage = errorMessage2;
        this.requestResultBody = requestResultBody2;
        this.requestResult = requestResult2;
        this.batchRequestResult = batchRequestResult2;
        this.connection = connection2;
        this.errorUserTitle = errorUserTitle2;
        this.errorUserMessage = errorUserMessage2;
        this.errorIsTransient = errorIsTransient2;
        boolean isLocalException = false;
        if (exception2 != null) {
            this.exception = exception2;
            isLocalException = true;
        } else {
            this.exception = new FacebookServiceException(this, errorMessage2);
        }
        Category errorCategory = null;
        int messageId = 0;
        if (isLocalException) {
            errorCategory = Category.CLIENT;
            messageId = 0;
        } else {
            if (errorCode2 == 1 || errorCode2 == 2) {
                errorCategory = Category.SERVER;
            } else if (errorCode2 == 4 || errorCode2 == 17) {
                errorCategory = Category.THROTTLING;
            } else if (errorCode2 == 10 || EC_RANGE_PERMISSION.contains(errorCode2)) {
                errorCategory = Category.PERMISSION;
                messageId = R.string.com_facebook_requesterror_permissions;
            } else if (errorCode2 == 102 || errorCode2 == EC_INVALID_TOKEN) {
                if (subErrorCode2 == EC_USER_CHECKPOINTED || subErrorCode2 == EC_UNCONFIRMED_USER) {
                    errorCategory = Category.AUTHENTICATION_RETRY;
                    messageId = R.string.com_facebook_requesterror_web_login;
                } else {
                    errorCategory = Category.AUTHENTICATION_REOPEN_SESSION;
                    messageId = (subErrorCode2 == EC_APP_NOT_INSTALLED || subErrorCode2 == EC_EXPIRED) ? R.string.com_facebook_requesterror_relogin : subErrorCode2 == EC_PASSWORD_CHANGED ? R.string.com_facebook_requesterror_password_changed : R.string.com_facebook_requesterror_reconnect;
                }
            }
            if (errorCategory == null) {
                if (HTTP_RANGE_CLIENT_ERROR.contains(requestStatusCode2)) {
                    errorCategory = Category.BAD_REQUEST;
                } else if (HTTP_RANGE_SERVER_ERROR.contains(requestStatusCode2)) {
                    errorCategory = Category.SERVER;
                } else {
                    errorCategory = Category.OTHER;
                }
            }
        }
        boolean shouldNotify = errorUserMessage2 != null && errorUserMessage2.length() > 0;
        this.category = errorCategory;
        this.userActionMessageId = messageId;
        this.shouldNotifyUser = shouldNotify;
    }

    private FacebookRequestError(int requestStatusCode2, int errorCode2, int subErrorCode2, String errorType2, String errorMessage2, String errorUserTitle2, String errorUserMessage2, boolean errorIsTransient2, JSONObject requestResultBody2, JSONObject requestResult2, Object batchRequestResult2, HttpURLConnection connection2) {
        this(requestStatusCode2, errorCode2, subErrorCode2, errorType2, errorMessage2, errorUserTitle2, errorUserMessage2, errorIsTransient2, requestResultBody2, requestResult2, batchRequestResult2, connection2, (FacebookException) null);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    FacebookRequestError(HttpURLConnection connection2, Exception exception2) {
        this(-1, -1, -1, (String) null, (String) null, (String) null, (String) null, false, (JSONObject) null, (JSONObject) null, (Object) null, connection2, exception2 instanceof FacebookException ? (FacebookException) exception2 : new FacebookException((Throwable) exception2));
    }

    public FacebookRequestError(int errorCode2, String errorType2, String errorMessage2) {
        this(-1, errorCode2, -1, errorType2, errorMessage2, (String) null, (String) null, false, (JSONObject) null, (JSONObject) null, (Object) null, (HttpURLConnection) null, (FacebookException) null);
    }

    public int getUserActionMessageId() {
        return this.userActionMessageId;
    }

    public boolean shouldNotifyUser() {
        return this.shouldNotifyUser;
    }

    public Category getCategory() {
        return this.category;
    }

    public int getRequestStatusCode() {
        return this.requestStatusCode;
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public int getSubErrorCode() {
        return this.subErrorCode;
    }

    public String getErrorType() {
        return this.errorType;
    }

    public String getErrorMessage() {
        if (this.errorMessage != null) {
            return this.errorMessage;
        }
        return this.exception.getLocalizedMessage();
    }

    public String getErrorUserMessage() {
        return this.errorUserMessage;
    }

    public String getErrorUserTitle() {
        return this.errorUserTitle;
    }

    public boolean getErrorIsTransient() {
        return this.errorIsTransient;
    }

    public JSONObject getRequestResultBody() {
        return this.requestResultBody;
    }

    public JSONObject getRequestResult() {
        return this.requestResult;
    }

    public Object getBatchRequestResult() {
        return this.batchRequestResult;
    }

    public HttpURLConnection getConnection() {
        return this.connection;
    }

    public FacebookException getException() {
        return this.exception;
    }

    public String toString() {
        return "{HttpStatus: " + this.requestStatusCode + ", errorCode: " + this.errorCode + ", errorType: " + this.errorType + ", errorMessage: " + getErrorMessage() + "}";
    }

    static FacebookRequestError checkResponseAndCreateError(JSONObject singleResult, Object batchResult, HttpURLConnection connection2) {
        try {
            if (singleResult.has("code")) {
                int responseCode = singleResult.getInt("code");
                Object body = Utility.getStringPropertyAsJSON(singleResult, "body", Response.NON_JSON_RESPONSE_PROPERTY);
                if (body != null && (body instanceof JSONObject)) {
                    JSONObject jsonBody = (JSONObject) body;
                    String errorType2 = null;
                    String errorMessage2 = null;
                    String errorUserMessage2 = null;
                    String errorUserTitle2 = null;
                    boolean errorIsTransient2 = false;
                    int errorCode2 = -1;
                    int errorSubCode = -1;
                    boolean hasError = false;
                    if (jsonBody.has("error")) {
                        JSONObject error = (JSONObject) Utility.getStringPropertyAsJSON(jsonBody, "error", (String) null);
                        errorType2 = error.optString("type", (String) null);
                        errorMessage2 = error.optString(ERROR_MESSAGE_FIELD_KEY, (String) null);
                        errorCode2 = error.optInt("code", -1);
                        errorSubCode = error.optInt(ERROR_SUB_CODE_KEY, -1);
                        errorUserMessage2 = error.optString(ERROR_USER_MSG_KEY, (String) null);
                        errorUserTitle2 = error.optString(ERROR_USER_TITLE_KEY, (String) null);
                        errorIsTransient2 = error.optBoolean(ERROR_IS_TRANSIENT_KEY, false);
                        hasError = true;
                    } else if (jsonBody.has(ERROR_CODE_KEY) || jsonBody.has(ERROR_MSG_KEY) || jsonBody.has(ERROR_REASON_KEY)) {
                        errorType2 = jsonBody.optString(ERROR_REASON_KEY, (String) null);
                        errorMessage2 = jsonBody.optString(ERROR_MSG_KEY, (String) null);
                        errorCode2 = jsonBody.optInt(ERROR_CODE_KEY, -1);
                        errorSubCode = jsonBody.optInt(ERROR_SUB_CODE_KEY, -1);
                        hasError = true;
                    }
                    if (hasError) {
                        return new FacebookRequestError(responseCode, errorCode2, errorSubCode, errorType2, errorMessage2, errorUserTitle2, errorUserMessage2, errorIsTransient2, jsonBody, singleResult, batchResult, connection2);
                    }
                }
                if (!HTTP_RANGE_SUCCESS.contains(responseCode)) {
                    return new FacebookRequestError(responseCode, -1, -1, (String) null, (String) null, (String) null, (String) null, false, singleResult.has("body") ? (JSONObject) Utility.getStringPropertyAsJSON(singleResult, "body", Response.NON_JSON_RESPONSE_PROPERTY) : null, singleResult, batchResult, connection2);
                }
            }
        } catch (JSONException e) {
        }
        return null;
    }
}
