package org.chromium.media;

import android.media.DeniedByServerException;
import android.media.MediaCrypto;
import android.media.MediaCryptoException;
import android.media.MediaDrm;
import android.media.MediaDrmException;
import android.media.NotProvisionedException;
import android.media.UnsupportedSchemeException;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.UUID;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.chromium.base.CalledByNative;
import org.chromium.base.JNINamespace;

@JNINamespace("media")
public class MediaDrmBridge {
    static final /* synthetic */ boolean $assertionsDisabled = (!MediaDrmBridge.class.desiredAssertionStatus());
    private static final String ENABLE = "enable";
    private static final int INVALID_SESSION_ID = 0;
    private static final String PRIVACY_MODE = "privacyMode";
    private static final String SECURITY_LEVEL = "securityLevel";
    private static final String SESSION_SHARING = "sessionSharing";
    private static final String TAG = "MediaDrmBridge";
    private Handler mHandler = new Handler();
    private MediaCrypto mMediaCrypto;
    private ByteBuffer mMediaCryptoSession;
    private MediaDrm mMediaDrm;
    /* access modifiers changed from: private */
    public long mNativeMediaDrmBridge;
    private ArrayDeque<PendingCreateSessionData> mPendingCreateSessionDataQueue;
    /* access modifiers changed from: private */
    public boolean mProvisioningPending;
    private boolean mResetDeviceCredentialsPending;
    private UUID mSchemeUUID;
    /* access modifiers changed from: private */
    public HashMap<ByteBuffer, Integer> mSessionIds;
    /* access modifiers changed from: private */
    public HashMap<ByteBuffer, String> mSessionMimeTypes;
    private boolean mSingleSessionMode;

    private static native void nativeAddKeySystemUuidMapping(String str, ByteBuffer byteBuffer);

    private native void nativeOnMediaCryptoReady(long j);

    private native void nativeOnResetDeviceCredentialsCompleted(long j, boolean z);

    /* access modifiers changed from: private */
    public native void nativeOnSessionClosed(long j, int i);

    /* access modifiers changed from: private */
    public native void nativeOnSessionCreated(long j, int i, String str);

    /* access modifiers changed from: private */
    public native void nativeOnSessionError(long j, int i);

    /* access modifiers changed from: private */
    public native void nativeOnSessionMessage(long j, int i, byte[] bArr, String str);

    /* access modifiers changed from: private */
    public native void nativeOnSessionReady(long j, int i);

    private static class PendingCreateSessionData {
        private final byte[] mInitData;
        private final String mMimeType;
        private final int mSessionId;

        private PendingCreateSessionData(int sessionId, byte[] initData, String mimeType) {
            this.mSessionId = sessionId;
            this.mInitData = initData;
            this.mMimeType = mimeType;
        }

        /* access modifiers changed from: private */
        public int sessionId() {
            return this.mSessionId;
        }

        /* access modifiers changed from: private */
        public byte[] initData() {
            return this.mInitData;
        }

        /* access modifiers changed from: private */
        public String mimeType() {
            return this.mMimeType;
        }
    }

    private static UUID getUUIDFromBytes(byte[] data) {
        if (data.length != 16) {
            return null;
        }
        long mostSigBits = 0;
        long leastSigBits = 0;
        for (int i = 0; i < 8; i++) {
            mostSigBits = (mostSigBits << 8) | ((long) (data[i] & 255));
        }
        for (int i2 = 8; i2 < 16; i2++) {
            leastSigBits = (leastSigBits << 8) | ((long) (data[i2] & 255));
        }
        return new UUID(mostSigBits, leastSigBits);
    }

    private ByteBuffer getSession(int sessionId) {
        for (ByteBuffer session : this.mSessionIds.keySet()) {
            if (this.mSessionIds.get(session).intValue() == sessionId) {
                return session;
            }
        }
        return null;
    }

    private MediaDrmBridge(UUID schemeUUID, long nativeMediaDrmBridge, boolean singleSessionMode) throws UnsupportedSchemeException {
        this.mSchemeUUID = schemeUUID;
        this.mMediaDrm = new MediaDrm(schemeUUID);
        this.mNativeMediaDrmBridge = nativeMediaDrmBridge;
        this.mSingleSessionMode = singleSessionMode;
        this.mSessionIds = new HashMap<>();
        this.mSessionMimeTypes = new HashMap<>();
        this.mPendingCreateSessionDataQueue = new ArrayDeque<>();
        this.mResetDeviceCredentialsPending = false;
        this.mProvisioningPending = false;
        this.mMediaDrm.setOnEventListener(new MediaDrmListener());
        this.mMediaDrm.setPropertyString(PRIVACY_MODE, ENABLE);
        if (!this.mSingleSessionMode) {
            this.mMediaDrm.setPropertyString(SESSION_SHARING, ENABLE);
        }
    }

    private boolean createMediaCrypto() throws NotProvisionedException {
        if (this.mMediaDrm == null) {
            return false;
        }
        if (!$assertionsDisabled && this.mProvisioningPending) {
            throw new AssertionError();
        } else if (!$assertionsDisabled && this.mMediaCryptoSession != null) {
            throw new AssertionError();
        } else if ($assertionsDisabled || this.mMediaCrypto == null) {
            this.mMediaCryptoSession = openSession();
            if (this.mMediaCryptoSession == null) {
                Log.e(TAG, "Cannot create MediaCrypto Session.");
                return false;
            }
            Log.d(TAG, "MediaCrypto Session created: " + this.mMediaCryptoSession);
            try {
                if (MediaCrypto.isCryptoSchemeSupported(this.mSchemeUUID)) {
                    this.mMediaCrypto = new MediaCrypto(this.mSchemeUUID, this.mMediaCryptoSession.array());
                    Log.d(TAG, "MediaCrypto successfully created!");
                    this.mSessionIds.put(this.mMediaCryptoSession, 0);
                    nativeOnMediaCryptoReady(this.mNativeMediaDrmBridge);
                    return true;
                }
                Log.e(TAG, "Cannot create MediaCrypto for unsupported scheme.");
                release();
                return false;
            } catch (MediaCryptoException e) {
                Log.e(TAG, "Cannot create MediaCrypto", e);
            }
        } else {
            throw new AssertionError();
        }
    }

    private ByteBuffer openSession() throws NotProvisionedException {
        if ($assertionsDisabled || this.mMediaDrm != null) {
            try {
                return ByteBuffer.wrap((byte[]) this.mMediaDrm.openSession().clone());
            } catch (RuntimeException e) {
                Log.e(TAG, "Cannot open a new session", e);
                release();
                return null;
            } catch (NotProvisionedException e2) {
                throw e2;
            } catch (MediaDrmException e3) {
                Log.e(TAG, "Cannot open a new session", e3);
                release();
                return null;
            }
        } else {
            throw new AssertionError();
        }
    }

    private void closeSession(ByteBuffer session) {
        if ($assertionsDisabled || this.mMediaDrm != null) {
            this.mMediaDrm.closeSession(session.array());
            return;
        }
        throw new AssertionError();
    }

    @CalledByNative
    private static boolean isCryptoSchemeSupported(byte[] schemeUUID, String containerMimeType) {
        UUID cryptoScheme = getUUIDFromBytes(schemeUUID);
        if (containerMimeType.isEmpty()) {
            return MediaDrm.isCryptoSchemeSupported(cryptoScheme);
        }
        return MediaDrm.isCryptoSchemeSupported(cryptoScheme, containerMimeType);
    }

    @CalledByNative
    private static MediaDrmBridge create(byte[] schemeUUID, long nativeMediaDrmBridge) {
        UUID cryptoScheme = getUUIDFromBytes(schemeUUID);
        if (cryptoScheme == null || !MediaDrm.isCryptoSchemeSupported(cryptoScheme)) {
            return null;
        }
        boolean singleSessionMode = false;
        if (Build.VERSION.RELEASE.equals("4.4")) {
            singleSessionMode = true;
        }
        Log.d(TAG, "MediaDrmBridge uses " + (singleSessionMode ? "single" : "multiple") + "-session mode.");
        MediaDrmBridge mediaDrmBridge = null;
        try {
            MediaDrmBridge mediaDrmBridge2 = new MediaDrmBridge(cryptoScheme, nativeMediaDrmBridge, singleSessionMode);
            try {
                Log.d(TAG, "MediaDrmBridge successfully created.");
                return mediaDrmBridge2;
            } catch (UnsupportedSchemeException e) {
                e = e;
                mediaDrmBridge = mediaDrmBridge2;
                Log.e(TAG, "Unsupported DRM scheme", e);
                return mediaDrmBridge;
            } catch (IllegalArgumentException e2) {
                e = e2;
                mediaDrmBridge = mediaDrmBridge2;
                Log.e(TAG, "Failed to create MediaDrmBridge", e);
                return mediaDrmBridge;
            } catch (IllegalStateException e3) {
                e = e3;
                mediaDrmBridge = mediaDrmBridge2;
                Log.e(TAG, "Failed to create MediaDrmBridge", e);
                return mediaDrmBridge;
            }
        } catch (UnsupportedSchemeException e4) {
            e = e4;
            Log.e(TAG, "Unsupported DRM scheme", e);
            return mediaDrmBridge;
        } catch (IllegalArgumentException e5) {
            e = e5;
            Log.e(TAG, "Failed to create MediaDrmBridge", e);
            return mediaDrmBridge;
        } catch (IllegalStateException e6) {
            e = e6;
            Log.e(TAG, "Failed to create MediaDrmBridge", e);
            return mediaDrmBridge;
        }
    }

    @CalledByNative
    private boolean setSecurityLevel(String securityLevel) {
        if (this.mMediaDrm == null || this.mMediaCrypto != null) {
            return false;
        }
        String currentSecurityLevel = this.mMediaDrm.getPropertyString(SECURITY_LEVEL);
        Log.e(TAG, "Security level: current " + currentSecurityLevel + ", new " + securityLevel);
        if (securityLevel.equals(currentSecurityLevel)) {
            return true;
        }
        try {
            this.mMediaDrm.setPropertyString(SECURITY_LEVEL, securityLevel);
            return true;
        } catch (IllegalArgumentException e) {
            Log.e(TAG, "Failed to set security level " + securityLevel, e);
        } catch (IllegalStateException e2) {
            Log.e(TAG, "Failed to set security level " + securityLevel, e2);
        }
        Log.e(TAG, "Security level " + securityLevel + " not supported!");
        return false;
    }

    @CalledByNative
    private MediaCrypto getMediaCrypto() {
        return this.mMediaCrypto;
    }

    @CalledByNative
    private void resetDeviceCredentials() {
        this.mResetDeviceCredentialsPending = true;
        MediaDrm.ProvisionRequest request = this.mMediaDrm.getProvisionRequest();
        new PostRequestTask(request.getData()).execute(new String[]{request.getDefaultUrl()});
    }

    @CalledByNative
    private void release() {
        this.mPendingCreateSessionDataQueue.clear();
        this.mPendingCreateSessionDataQueue = null;
        for (ByteBuffer session : this.mSessionIds.keySet()) {
            closeSession(session);
        }
        this.mSessionIds.clear();
        this.mSessionIds = null;
        this.mSessionMimeTypes.clear();
        this.mSessionMimeTypes = null;
        this.mMediaCryptoSession = null;
        if (this.mMediaCrypto != null) {
            this.mMediaCrypto.release();
            this.mMediaCrypto = null;
        }
        if (this.mMediaDrm != null) {
            this.mMediaDrm.release();
            this.mMediaDrm = null;
        }
    }

    /* access modifiers changed from: private */
    public MediaDrm.KeyRequest getKeyRequest(ByteBuffer session, byte[] data, String mime) throws NotProvisionedException {
        if (!$assertionsDisabled && this.mMediaDrm == null) {
            throw new AssertionError();
        } else if (!$assertionsDisabled && this.mMediaCrypto == null) {
            throw new AssertionError();
        } else if ($assertionsDisabled || !this.mProvisioningPending) {
            MediaDrm.KeyRequest request = this.mMediaDrm.getKeyRequest(session.array(), data, mime, 1, new HashMap<>());
            Log.d(TAG, "getKeyRequest " + (request != null ? "successed" : "failed") + "!");
            return request;
        } else {
            throw new AssertionError();
        }
    }

    private void savePendingCreateSessionData(int sessionId, byte[] initData, String mime) {
        Log.d(TAG, "savePendingCreateSessionData()");
        this.mPendingCreateSessionDataQueue.offer(new PendingCreateSessionData(sessionId, initData, mime));
    }

    /* access modifiers changed from: private */
    public void processPendingCreateSessionData() {
        Log.d(TAG, "processPendingCreateSessionData()");
        if ($assertionsDisabled || this.mMediaDrm != null) {
            while (this.mMediaDrm != null && !this.mProvisioningPending && !this.mPendingCreateSessionDataQueue.isEmpty()) {
                PendingCreateSessionData pendingData = this.mPendingCreateSessionDataQueue.poll();
                createSession(pendingData.sessionId(), pendingData.initData(), pendingData.mimeType());
            }
            return;
        }
        throw new AssertionError();
    }

    private void resumePendingOperations() {
        this.mHandler.post(new Runnable() {
            public void run() {
                MediaDrmBridge.this.processPendingCreateSessionData();
            }
        });
    }

    @CalledByNative
    private void createSession(int sessionId, byte[] initData, String mime) {
        ByteBuffer session;
        Log.d(TAG, "createSession()");
        if (this.mMediaDrm == null) {
            Log.e(TAG, "createSession() called when MediaDrm is null.");
        } else if (!this.mProvisioningPending) {
            boolean newSessionOpened = false;
            try {
                if (this.mMediaCrypto == null && !createMediaCrypto()) {
                    onSessionError(sessionId);
                } else if (!$assertionsDisabled && this.mMediaCrypto == null) {
                    throw new AssertionError();
                } else if ($assertionsDisabled || this.mSessionIds.containsKey(this.mMediaCryptoSession)) {
                    if (this.mSingleSessionMode) {
                        session = this.mMediaCryptoSession;
                        if (this.mSessionMimeTypes.get(session) != null && !this.mSessionMimeTypes.get(session).equals(mime)) {
                            Log.e(TAG, "Only one mime type is supported in single session mode.");
                            onSessionError(sessionId);
                            return;
                        }
                    } else {
                        session = openSession();
                        if (session == null) {
                            Log.e(TAG, "Cannot open session in createSession().");
                            onSessionError(sessionId);
                            return;
                        }
                        newSessionOpened = true;
                        if (!$assertionsDisabled && this.mSessionIds.containsKey(session)) {
                            throw new AssertionError();
                        }
                    }
                    MediaDrm.KeyRequest request = getKeyRequest(session, initData, mime);
                    if (request == null) {
                        if (newSessionOpened) {
                            closeSession(session);
                        }
                        onSessionError(sessionId);
                        return;
                    }
                    onSessionCreated(sessionId, getWebSessionId(session));
                    onSessionMessage(sessionId, request);
                    if (newSessionOpened) {
                        Log.d(TAG, "createSession(): Session " + getWebSessionId(session) + " (" + sessionId + ") created.");
                    }
                    this.mSessionIds.put(session, Integer.valueOf(sessionId));
                    this.mSessionMimeTypes.put(session, mime);
                } else {
                    throw new AssertionError();
                }
            } catch (NotProvisionedException e) {
                Log.e(TAG, "Device not provisioned", e);
                if (0 != 0) {
                    closeSession((ByteBuffer) null);
                }
                savePendingCreateSessionData(sessionId, initData, mime);
                startProvisioning();
            }
        } else if ($assertionsDisabled || this.mMediaCrypto == null) {
            savePendingCreateSessionData(sessionId, initData, mime);
        } else {
            throw new AssertionError();
        }
    }

    /* access modifiers changed from: private */
    public boolean sessionExists(ByteBuffer session) {
        if (this.mMediaCryptoSession == null) {
            if ($assertionsDisabled || this.mSessionIds.isEmpty()) {
                Log.e(TAG, "Session doesn't exist because media crypto session is not created.");
                return false;
            }
            throw new AssertionError();
        } else if (!$assertionsDisabled && !this.mSessionIds.containsKey(this.mMediaCryptoSession)) {
            throw new AssertionError();
        } else if (this.mSingleSessionMode) {
            return this.mMediaCryptoSession.equals(session);
        } else {
            if (session.equals(this.mMediaCryptoSession) || !this.mSessionIds.containsKey(session)) {
                return false;
            }
            return true;
        }
    }

    @CalledByNative
    private void releaseSession(int sessionId) {
        Log.d(TAG, "releaseSession(): " + sessionId);
        if (this.mMediaDrm == null) {
            Log.e(TAG, "releaseSession() called when MediaDrm is null.");
            return;
        }
        ByteBuffer session = getSession(sessionId);
        if (session == null) {
            Log.e(TAG, "Invalid sessionId in releaseSession.");
            onSessionError(sessionId);
            return;
        }
        this.mMediaDrm.removeKeys(session.array());
        if (!this.mSingleSessionMode) {
            Log.d(TAG, "Session " + sessionId + "closed.");
            closeSession(session);
            this.mSessionIds.remove(session);
            onSessionClosed(sessionId);
        }
    }

    @CalledByNative
    private void updateSession(int sessionId, byte[] key) {
        Log.d(TAG, "updateSession(): " + sessionId);
        if (this.mMediaDrm == null) {
            Log.e(TAG, "updateSession() called when MediaDrm is null.");
            return;
        }
        ByteBuffer session = getSession(sessionId);
        if (!sessionExists(session)) {
            Log.e(TAG, "Invalid session in updateSession.");
            onSessionError(sessionId);
            return;
        }
        try {
            this.mMediaDrm.provideKeyResponse(session.array(), key);
        } catch (IllegalStateException e) {
            Log.e(TAG, "Exception intentionally caught when calling provideKeyResponse()", e);
        }
        try {
            onSessionReady(sessionId);
            Log.d(TAG, "Key successfully added for session " + sessionId);
            return;
        } catch (NotProvisionedException e2) {
            Log.e(TAG, "failed to provide key response", e2);
        } catch (DeniedByServerException e3) {
            Log.e(TAG, "failed to provide key response", e3);
        }
        onSessionError(sessionId);
        release();
    }

    @CalledByNative
    private String getSecurityLevel() {
        if (this.mMediaDrm != null) {
            return this.mMediaDrm.getPropertyString(SECURITY_LEVEL);
        }
        Log.e(TAG, "getSecurityLevel() called when MediaDrm is null.");
        return null;
    }

    /* access modifiers changed from: private */
    public void startProvisioning() {
        Log.d(TAG, "startProvisioning");
        if (!$assertionsDisabled && this.mMediaDrm == null) {
            throw new AssertionError();
        } else if ($assertionsDisabled || !this.mProvisioningPending) {
            this.mProvisioningPending = true;
            MediaDrm.ProvisionRequest request = this.mMediaDrm.getProvisionRequest();
            new PostRequestTask(request.getData()).execute(new String[]{request.getDefaultUrl()});
        } else {
            throw new AssertionError();
        }
    }

    /* access modifiers changed from: private */
    public void onProvisionResponse(byte[] response) {
        Log.d(TAG, "onProvisionResponse()");
        if ($assertionsDisabled || this.mProvisioningPending) {
            this.mProvisioningPending = false;
            if (this.mMediaDrm != null) {
                boolean success = provideProvisionResponse(response);
                if (this.mResetDeviceCredentialsPending) {
                    nativeOnResetDeviceCredentialsCompleted(this.mNativeMediaDrmBridge, success);
                    this.mResetDeviceCredentialsPending = false;
                }
                if (success) {
                    resumePendingOperations();
                    return;
                }
                return;
            }
            return;
        }
        throw new AssertionError();
    }

    /* access modifiers changed from: package-private */
    public boolean provideProvisionResponse(byte[] response) {
        if (response == null || response.length == 0) {
            Log.e(TAG, "Invalid provision response.");
            return false;
        }
        try {
            this.mMediaDrm.provideProvisionResponse(response);
            return true;
        } catch (DeniedByServerException e) {
            Log.e(TAG, "failed to provide provision response", e);
            return false;
        } catch (IllegalStateException e2) {
            Log.e(TAG, "failed to provide provision response", e2);
            return false;
        }
    }

    private void onSessionCreated(final int sessionId, final String webSessionId) {
        this.mHandler.post(new Runnable() {
            public void run() {
                MediaDrmBridge.this.nativeOnSessionCreated(MediaDrmBridge.this.mNativeMediaDrmBridge, sessionId, webSessionId);
            }
        });
    }

    /* access modifiers changed from: private */
    public void onSessionMessage(final int sessionId, final MediaDrm.KeyRequest request) {
        this.mHandler.post(new Runnable() {
            public void run() {
                MediaDrmBridge.this.nativeOnSessionMessage(MediaDrmBridge.this.mNativeMediaDrmBridge, sessionId, request.getData(), request.getDefaultUrl());
            }
        });
    }

    private void onSessionReady(final int sessionId) {
        this.mHandler.post(new Runnable() {
            public void run() {
                MediaDrmBridge.this.nativeOnSessionReady(MediaDrmBridge.this.mNativeMediaDrmBridge, sessionId);
            }
        });
    }

    private void onSessionClosed(final int sessionId) {
        this.mHandler.post(new Runnable() {
            public void run() {
                MediaDrmBridge.this.nativeOnSessionClosed(MediaDrmBridge.this.mNativeMediaDrmBridge, sessionId);
            }
        });
    }

    /* access modifiers changed from: private */
    public void onSessionError(final int sessionId) {
        this.mHandler.post(new Runnable() {
            public void run() {
                MediaDrmBridge.this.nativeOnSessionError(MediaDrmBridge.this.mNativeMediaDrmBridge, sessionId);
            }
        });
    }

    private String getWebSessionId(ByteBuffer session) {
        try {
            return new String(session.array(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            Log.e(TAG, "getWebSessionId failed", e);
            return null;
        } catch (NullPointerException e2) {
            Log.e(TAG, "getWebSessionId failed", e2);
            return null;
        }
    }

    private class MediaDrmListener implements MediaDrm.OnEventListener {
        static final /* synthetic */ boolean $assertionsDisabled = (!MediaDrmBridge.class.desiredAssertionStatus());

        private MediaDrmListener() {
        }

        public void onEvent(MediaDrm mediaDrm, byte[] sessionArray, int event, int extra, byte[] data) {
            if (sessionArray == null) {
                Log.e(MediaDrmBridge.TAG, "MediaDrmListener: Null session.");
                return;
            }
            ByteBuffer session = ByteBuffer.wrap(sessionArray);
            if (!MediaDrmBridge.this.sessionExists(session)) {
                Log.e(MediaDrmBridge.TAG, "MediaDrmListener: Invalid session.");
                return;
            }
            Integer sessionId = (Integer) MediaDrmBridge.this.mSessionIds.get(session);
            if (sessionId == null || sessionId.intValue() == 0) {
                Log.e(MediaDrmBridge.TAG, "MediaDrmListener: Invalid session ID.");
                return;
            }
            switch (event) {
                case 1:
                    Log.d(MediaDrmBridge.TAG, "MediaDrm.EVENT_PROVISION_REQUIRED");
                    return;
                case 2:
                    Log.d(MediaDrmBridge.TAG, "MediaDrm.EVENT_KEY_REQUIRED");
                    if (!MediaDrmBridge.this.mProvisioningPending) {
                        try {
                            MediaDrm.KeyRequest request = MediaDrmBridge.this.getKeyRequest(session, data, (String) MediaDrmBridge.this.mSessionMimeTypes.get(session));
                            if (request != null) {
                                MediaDrmBridge.this.onSessionMessage(sessionId.intValue(), request);
                                return;
                            } else {
                                MediaDrmBridge.this.onSessionError(sessionId.intValue());
                                return;
                            }
                        } catch (NotProvisionedException e) {
                            Log.e(MediaDrmBridge.TAG, "Device not provisioned", e);
                            MediaDrmBridge.this.startProvisioning();
                            return;
                        }
                    } else {
                        return;
                    }
                case 3:
                    Log.d(MediaDrmBridge.TAG, "MediaDrm.EVENT_KEY_EXPIRED");
                    MediaDrmBridge.this.onSessionError(sessionId.intValue());
                    return;
                case 4:
                    Log.d(MediaDrmBridge.TAG, "MediaDrm.EVENT_VENDOR_DEFINED");
                    if (!$assertionsDisabled) {
                        throw new AssertionError();
                    }
                    return;
                default:
                    Log.e(MediaDrmBridge.TAG, "Invalid DRM event " + event);
                    return;
            }
        }
    }

    private class PostRequestTask extends AsyncTask<String, Void, Void> {
        private static final String TAG = "PostRequestTask";
        private byte[] mDrmRequest;
        private byte[] mResponseBody;

        public PostRequestTask(byte[] drmRequest) {
            this.mDrmRequest = drmRequest;
        }

        /* access modifiers changed from: protected */
        public Void doInBackground(String... urls) {
            this.mResponseBody = postRequest(urls[0], this.mDrmRequest);
            if (this.mResponseBody == null) {
                return null;
            }
            Log.d(TAG, "response length=" + this.mResponseBody.length);
            return null;
        }

        private byte[] postRequest(String url, byte[] drmRequest) {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url + "&signedRequest=" + new String(drmRequest));
            Log.d(TAG, "PostRequest:" + httpPost.getRequestLine());
            try {
                httpPost.setHeader("Accept", "*/*");
                httpPost.setHeader("User-Agent", "Widevine CDM v1.0");
                httpPost.setHeader("Content-Type", "application/json");
                HttpResponse response = httpClient.execute(httpPost);
                int responseCode = response.getStatusLine().getStatusCode();
                if (responseCode == 200) {
                    return EntityUtils.toByteArray(response.getEntity());
                }
                Log.d(TAG, "Server returned HTTP error code " + responseCode);
                return null;
            } catch (ClientProtocolException e) {
                e.printStackTrace();
                return null;
            } catch (IOException e2) {
                e2.printStackTrace();
                return null;
            }
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Void v) {
            MediaDrmBridge.this.onProvisionResponse(this.mResponseBody);
        }
    }

    public static void addKeySystemUuidMapping(String keySystem, UUID uuid) {
        ByteBuffer uuidBuffer = ByteBuffer.allocateDirect(16);
        uuidBuffer.order(ByteOrder.BIG_ENDIAN);
        uuidBuffer.putLong(uuid.getMostSignificantBits());
        uuidBuffer.putLong(uuid.getLeastSignificantBits());
        nativeAddKeySystemUuidMapping(keySystem, uuidBuffer);
    }
}
