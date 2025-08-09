package com.facebook;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.facebook.Session;
import com.facebook.internal.ServerProtocol;
import com.facebook.internal.Utility;
import com.facebook.internal.Validate;
import com.facebook.model.GraphObject;
import com.facebook.model.GraphObjectList;
import com.facebook.model.GraphUser;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class TestSession extends Session {
    static final /* synthetic */ boolean $assertionsDisabled = (!TestSession.class.desiredAssertionStatus());
    private static final String LOG_TAG = "FacebookSDK.TestSession";
    private static Map<String, TestAccount> appTestAccounts = null;
    private static final long serialVersionUID = 1;
    private static String testApplicationId;
    private static String testApplicationSecret;
    private final Mode mode;
    private final List<String> requestedPermissions;
    private final String sessionUniqueUserTag;
    private String testAccountId;
    private String testAccountUserName;
    private boolean wasAskedToExtendAccessToken;

    private enum Mode {
        PRIVATE,
        SHARED
    }

    private interface TestAccount extends GraphObject {
        String getAccessToken();

        String getId();

        String getName();

        void setName(String str);
    }

    private interface TestAccountsResponse extends GraphObject {
        GraphObjectList<TestAccount> getData();
    }

    TestSession(Activity activity, List<String> permissions, TokenCachingStrategy tokenCachingStrategy, String sessionUniqueUserTag2, Mode mode2) {
        super(activity, testApplicationId, tokenCachingStrategy);
        Validate.notNull(permissions, "permissions");
        Validate.notNullOrEmpty(testApplicationId, "testApplicationId");
        Validate.notNullOrEmpty(testApplicationSecret, "testApplicationSecret");
        this.sessionUniqueUserTag = sessionUniqueUserTag2;
        this.mode = mode2;
        this.requestedPermissions = permissions;
    }

    public static TestSession createSessionWithPrivateUser(Activity activity, List<String> permissions) {
        return createTestSession(activity, permissions, Mode.PRIVATE, (String) null);
    }

    public static TestSession createSessionWithSharedUser(Activity activity, List<String> permissions) {
        return createSessionWithSharedUser(activity, permissions, (String) null);
    }

    public static TestSession createSessionWithSharedUser(Activity activity, List<String> permissions, String sessionUniqueUserTag2) {
        return createTestSession(activity, permissions, Mode.SHARED, sessionUniqueUserTag2);
    }

    public static synchronized String getTestApplicationId() {
        String str;
        synchronized (TestSession.class) {
            str = testApplicationId;
        }
        return str;
    }

    public static synchronized void setTestApplicationId(String applicationId) {
        synchronized (TestSession.class) {
            if (testApplicationId == null || testApplicationId.equals(applicationId)) {
                testApplicationId = applicationId;
            } else {
                throw new FacebookException("Can't have more than one test application ID");
            }
        }
    }

    public static synchronized String getTestApplicationSecret() {
        String str;
        synchronized (TestSession.class) {
            str = testApplicationSecret;
        }
        return str;
    }

    public static synchronized void setTestApplicationSecret(String applicationSecret) {
        synchronized (TestSession.class) {
            if (testApplicationSecret == null || testApplicationSecret.equals(applicationSecret)) {
                testApplicationSecret = applicationSecret;
            } else {
                throw new FacebookException("Can't have more than one test application secret");
            }
        }
    }

    public final String getTestUserId() {
        return this.testAccountId;
    }

    public final String getTestUserName() {
        return this.testAccountUserName;
    }

    private static synchronized TestSession createTestSession(Activity activity, List<String> permissions, Mode mode2, String sessionUniqueUserTag2) {
        TestSession testSession;
        synchronized (TestSession.class) {
            if (Utility.isNullOrEmpty(testApplicationId) || Utility.isNullOrEmpty(testApplicationSecret)) {
                throw new FacebookException("Must provide app ID and secret");
            }
            if (Utility.isNullOrEmpty(permissions)) {
                permissions = Arrays.asList(new String[]{"email", "publish_actions"});
            }
            testSession = new TestSession(activity, permissions, new TestTokenCachingStrategy(), sessionUniqueUserTag2, mode2);
        }
        return testSession;
    }

    private static synchronized void retrieveTestAccountsForAppIfNeeded() {
        synchronized (TestSession.class) {
            if (appTestAccounts == null) {
                appTestAccounts = new HashMap();
                Request.setDefaultBatchApplicationId(testApplicationId);
                Bundle parameters = new Bundle();
                parameters.putString("access_token", getAppAccessToken());
                Request requestTestUsers = new Request((Session) null, "app/accounts/test-users", parameters, (HttpMethod) null);
                requestTestUsers.setBatchEntryName("testUsers");
                requestTestUsers.setBatchEntryOmitResultOnSuccess(false);
                Bundle testUserNamesParam = new Bundle();
                testUserNamesParam.putString("access_token", getAppAccessToken());
                testUserNamesParam.putString("ids", "{result=testUsers:$.data.*.id}");
                testUserNamesParam.putString("fields", "name");
                Request requestTestUserNames = new Request((Session) null, "", testUserNamesParam, (HttpMethod) null);
                requestTestUserNames.setBatchEntryDependsOn("testUsers");
                List<Response> responses = Request.executeBatchAndWait(requestTestUsers, requestTestUserNames);
                if (responses == null || responses.size() != 2) {
                    throw new FacebookException("Unexpected number of results from TestUsers batch query");
                }
                populateTestAccounts(((TestAccountsResponse) responses.get(0).getGraphObjectAs(TestAccountsResponse.class)).getData(), responses.get(1).getGraphObject());
            }
        }
    }

    private static synchronized void populateTestAccounts(Collection<TestAccount> testAccounts, GraphObject userAccountsMap) {
        synchronized (TestSession.class) {
            for (TestAccount testAccount : testAccounts) {
                testAccount.setName(((GraphUser) userAccountsMap.getPropertyAs(testAccount.getId(), GraphUser.class)).getName());
                storeTestAccount(testAccount);
            }
        }
    }

    private static synchronized void storeTestAccount(TestAccount testAccount) {
        synchronized (TestSession.class) {
            appTestAccounts.put(testAccount.getId(), testAccount);
        }
    }

    private static synchronized TestAccount findTestAccountMatchingIdentifier(String identifier) {
        TestAccount testAccount;
        synchronized (TestSession.class) {
            retrieveTestAccountsForAppIfNeeded();
            Iterator i$ = appTestAccounts.values().iterator();
            while (true) {
                if (!i$.hasNext()) {
                    testAccount = null;
                    break;
                }
                testAccount = i$.next();
                if (testAccount.getName().contains(identifier)) {
                    break;
                }
            }
        }
        return testAccount;
    }

    public final String toString() {
        return "{TestSession" + " testUserId:" + this.testAccountId + " " + super.toString() + "}";
    }

    /* access modifiers changed from: package-private */
    public void authorize(Session.AuthorizationRequest request) {
        if (this.mode == Mode.PRIVATE) {
            createTestAccountAndFinishAuth();
        } else {
            findOrCreateSharedTestAccount();
        }
    }

    /* access modifiers changed from: package-private */
    public void postStateChange(SessionState oldState, SessionState newState, Exception error) {
        String id = this.testAccountId;
        super.postStateChange(oldState, newState, error);
        if (newState.isClosed() && id != null && this.mode == Mode.PRIVATE) {
            deleteTestAccount(id, getAppAccessToken());
        }
    }

    /* access modifiers changed from: package-private */
    public boolean getWasAskedToExtendAccessToken() {
        return this.wasAskedToExtendAccessToken;
    }

    /* access modifiers changed from: package-private */
    public void forceExtendAccessToken(boolean forceExtendAccessToken) {
        AccessToken currentToken = getTokenInfo();
        setTokenInfo(new AccessToken(currentToken.getToken(), new Date(), currentToken.getPermissions(), currentToken.getDeclinedPermissions(), AccessTokenSource.TEST_USER, new Date(0)));
        setLastAttemptedTokenExtendDate(new Date(0));
    }

    /* access modifiers changed from: package-private */
    public boolean shouldExtendAccessToken() {
        boolean result = super.shouldExtendAccessToken();
        this.wasAskedToExtendAccessToken = false;
        return result;
    }

    /* access modifiers changed from: package-private */
    public void extendAccessToken() {
        this.wasAskedToExtendAccessToken = true;
        super.extendAccessToken();
    }

    /* access modifiers changed from: package-private */
    public void fakeTokenRefreshAttempt() {
        setCurrentTokenRefreshRequest(new Session.TokenRefreshRequest());
    }

    static final String getAppAccessToken() {
        return testApplicationId + "|" + testApplicationSecret;
    }

    private void findOrCreateSharedTestAccount() {
        TestAccount testAccount = findTestAccountMatchingIdentifier(getSharedTestAccountIdentifier());
        if (testAccount != null) {
            finishAuthWithTestAccount(testAccount);
        } else {
            createTestAccountAndFinishAuth();
        }
    }

    private void finishAuthWithTestAccount(TestAccount testAccount) {
        this.testAccountId = testAccount.getId();
        this.testAccountUserName = testAccount.getName();
        finishAuthOrReauth(AccessToken.createFromString(testAccount.getAccessToken(), this.requestedPermissions, AccessTokenSource.TEST_USER), (Exception) null);
    }

    private TestAccount createTestAccountAndFinishAuth() {
        Bundle parameters = new Bundle();
        parameters.putString("installed", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
        parameters.putString("permissions", getPermissionsString());
        parameters.putString("access_token", getAppAccessToken());
        if (this.mode == Mode.SHARED) {
            parameters.putString("name", String.format("Shared %s Testuser", new Object[]{getSharedTestAccountIdentifier()}));
        }
        Response response = new Request((Session) null, String.format("%s/accounts/test-users", new Object[]{testApplicationId}), parameters, HttpMethod.POST).executeAndWait();
        FacebookRequestError error = response.getError();
        TestAccount testAccount = (TestAccount) response.getGraphObjectAs(TestAccount.class);
        if (error != null) {
            finishAuthOrReauth((AccessToken) null, error.getException());
            return null;
        } else if ($assertionsDisabled || testAccount != null) {
            if (this.mode == Mode.SHARED) {
                testAccount.setName(parameters.getString("name"));
                storeTestAccount(testAccount);
            }
            finishAuthWithTestAccount(testAccount);
            return testAccount;
        } else {
            throw new AssertionError();
        }
    }

    private void deleteTestAccount(String testAccountId2, String appAccessToken) {
        Bundle parameters = new Bundle();
        parameters.putString("access_token", appAccessToken);
        Response response = new Request((Session) null, testAccountId2, parameters, HttpMethod.DELETE).executeAndWait();
        FacebookRequestError error = response.getError();
        GraphObject graphObject = response.getGraphObject();
        if (error != null) {
            Log.w(LOG_TAG, String.format("Could not delete test account %s: %s", new Object[]{testAccountId2, error.getException().toString()}));
        } else if (graphObject.getProperty(Response.NON_JSON_RESPONSE_PROPERTY) == false || graphObject.getProperty(Response.SUCCESS_KEY) == false) {
            Log.w(LOG_TAG, String.format("Could not delete test account %s: unknown reason", new Object[]{testAccountId2}));
        }
    }

    private String getPermissionsString() {
        return TextUtils.join(",", this.requestedPermissions);
    }

    private String getSharedTestAccountIdentifier() {
        return validNameStringFromInteger((((long) getPermissionsString().hashCode()) & 4294967295L) ^ (this.sessionUniqueUserTag != null ? ((long) this.sessionUniqueUserTag.hashCode()) & 4294967295L : 0));
    }

    private String validNameStringFromInteger(long i) {
        String s = Long.toString(i);
        StringBuilder result = new StringBuilder("Perm");
        char lastChar = 0;
        for (char c : s.toCharArray()) {
            if (c == lastChar) {
                c = (char) (c + 10);
            }
            result.append((char) ((c + 'a') - 48));
            lastChar = c;
        }
        return result.toString();
    }

    private static final class TestTokenCachingStrategy extends TokenCachingStrategy {
        private Bundle bundle;

        private TestTokenCachingStrategy() {
        }

        public Bundle load() {
            return this.bundle;
        }

        public void save(Bundle value) {
            this.bundle = value;
        }

        public void clear() {
            this.bundle = null;
        }
    }
}
