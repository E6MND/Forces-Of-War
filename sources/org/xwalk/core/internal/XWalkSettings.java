package org.xwalk.core.internal;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import org.chromium.base.CalledByNative;
import org.chromium.base.JNINamespace;
import org.chromium.base.ThreadUtils;

@JNINamespace("xwalk")
public class XWalkSettings {
    static final /* synthetic */ boolean $assertionsDisabled;
    private static final int MAXIMUM_FONT_SIZE = 72;
    private static final int MINIMUM_FONT_SIZE = 1;
    private static final String TAG = "XWalkSettings";
    private static boolean sAppCachePathIsSet = false;
    private static final Object sGlobalContentSettingsLock = new Object();
    private boolean mAllowContentUrlAccess = true;
    private boolean mAllowFileAccessFromFileURLs = false;
    private boolean mAllowFileUrlAccess = true;
    private boolean mAllowScriptsToCloseWindows = true;
    private boolean mAllowUniversalAccessFromFileURLs = false;
    private boolean mAppCacheEnabled = true;
    private boolean mBlockNetworkLoads;
    private int mCacheMode = -1;
    private final Context mContext;
    private boolean mDatabaseEnabled = true;
    private String mDefaultVideoPosterURL;
    private boolean mDomStorageEnabled = true;
    private final EventHandler mEventHandler;
    private boolean mGeolocationEnabled = true;
    private boolean mImagesEnabled = true;
    /* access modifiers changed from: private */
    public boolean mIsUpdateWebkitPrefsMessagePending = false;
    private boolean mJavaScriptCanOpenWindowsAutomatically = true;
    private boolean mJavaScriptEnabled = true;
    private boolean mLoadsImagesAutomatically = true;
    private boolean mMediaPlaybackRequiresUserGesture = false;
    /* access modifiers changed from: private */
    public long mNativeXWalkSettings = 0;
    private boolean mShouldFocusFirstNode = true;
    private boolean mSupportMultipleWindows = true;
    private boolean mUseWideViewport = false;
    private String mUserAgent;
    /* access modifiers changed from: private */
    public final Object mXWalkSettingsLock = new Object();

    private native void nativeDestroy(long j);

    /* access modifiers changed from: private */
    public static native String nativeGetDefaultUserAgent();

    private native long nativeInit(long j);

    private native void nativeUpdateEverythingLocked(long j);

    /* access modifiers changed from: private */
    public native void nativeUpdateUserAgent(long j);

    private native void nativeUpdateWebkitPreferences(long j);

    static {
        boolean z;
        if (!XWalkSettings.class.desiredAssertionStatus()) {
            z = true;
        } else {
            z = false;
        }
        $assertionsDisabled = z;
    }

    static class LazyDefaultUserAgent {
        /* access modifiers changed from: private */
        public static final String sInstance = XWalkSettings.nativeGetDefaultUserAgent();

        LazyDefaultUserAgent() {
        }
    }

    private class EventHandler {
        static final /* synthetic */ boolean $assertionsDisabled = (!XWalkSettings.class.desiredAssertionStatus());
        private static final int UPDATE_WEBKIT_PREFERENCES = 0;
        private Handler mHandler;

        EventHandler() {
        }

        /* access modifiers changed from: package-private */
        public void bindUiThread() {
            if (this.mHandler == null) {
                this.mHandler = new Handler(ThreadUtils.getUiThreadLooper()) {
                    public void handleMessage(Message msg) {
                        switch (msg.what) {
                            case 0:
                                synchronized (XWalkSettings.this.mXWalkSettingsLock) {
                                    XWalkSettings.this.updateWebkitPreferencesOnUiThread();
                                    boolean unused = XWalkSettings.this.mIsUpdateWebkitPrefsMessagePending = false;
                                    XWalkSettings.this.mXWalkSettingsLock.notifyAll();
                                }
                                return;
                            default:
                                return;
                        }
                    }
                };
            }
        }

        /* access modifiers changed from: package-private */
        public void maybeRunOnUiThreadBlocking(Runnable r) {
            if (this.mHandler != null) {
                ThreadUtils.runOnUiThreadBlocking(r);
            }
        }

        /* access modifiers changed from: private */
        public void updateWebkitPreferencesLocked() {
            if (!$assertionsDisabled && !Thread.holdsLock(XWalkSettings.this.mXWalkSettingsLock)) {
                throw new AssertionError();
            } else if (XWalkSettings.this.mNativeXWalkSettings != 0 && this.mHandler != null) {
                if (ThreadUtils.runningOnUiThread()) {
                    XWalkSettings.this.updateWebkitPreferencesOnUiThread();
                } else if (!XWalkSettings.this.mIsUpdateWebkitPrefsMessagePending) {
                    boolean unused = XWalkSettings.this.mIsUpdateWebkitPrefsMessagePending = true;
                    this.mHandler.sendMessage(Message.obtain((Handler) null, 0));
                    while (XWalkSettings.this.mIsUpdateWebkitPrefsMessagePending) {
                        try {
                            XWalkSettings.this.mXWalkSettingsLock.wait();
                        } catch (InterruptedException e) {
                            return;
                        }
                    }
                }
            }
        }
    }

    public XWalkSettings(Context context, long nativeWebContents, boolean isAccessFromFileURLsGrantedByDefault) {
        boolean z = false;
        ThreadUtils.assertOnUiThread();
        this.mContext = context;
        this.mBlockNetworkLoads = this.mContext.checkPermission("android.permission.INTERNET", Process.myPid(), Process.myUid()) != 0 ? true : z;
        if (isAccessFromFileURLsGrantedByDefault) {
            this.mAllowUniversalAccessFromFileURLs = true;
            this.mAllowFileAccessFromFileURLs = true;
        }
        this.mUserAgent = LazyDefaultUserAgent.sInstance;
        this.mEventHandler = new EventHandler();
        setWebContents(nativeWebContents);
    }

    /* access modifiers changed from: package-private */
    public void setWebContents(long nativeWebContents) {
        synchronized (this.mXWalkSettingsLock) {
            if (this.mNativeXWalkSettings != 0) {
                nativeDestroy(this.mNativeXWalkSettings);
                if (!$assertionsDisabled && this.mNativeXWalkSettings != 0) {
                    throw new AssertionError();
                }
            }
            if (nativeWebContents != 0) {
                this.mEventHandler.bindUiThread();
                this.mNativeXWalkSettings = nativeInit(nativeWebContents);
                nativeUpdateEverythingLocked(this.mNativeXWalkSettings);
            }
        }
    }

    @CalledByNative
    private void nativeXWalkSettingsGone(long nativeXWalkSettings) {
        if ($assertionsDisabled || (this.mNativeXWalkSettings != 0 && this.mNativeXWalkSettings == nativeXWalkSettings)) {
            this.mNativeXWalkSettings = 0;
            return;
        }
        throw new AssertionError();
    }

    public void setAllowScriptsToCloseWindows(boolean allow) {
        synchronized (this.mXWalkSettingsLock) {
            if (this.mAllowScriptsToCloseWindows != allow) {
                this.mAllowScriptsToCloseWindows = allow;
            }
        }
    }

    public boolean getAllowScriptsToCloseWindows() {
        boolean z;
        synchronized (this.mXWalkSettingsLock) {
            z = this.mAllowScriptsToCloseWindows;
        }
        return z;
    }

    public void setCacheMode(int mode) {
        synchronized (this.mXWalkSettingsLock) {
            if (this.mCacheMode != mode) {
                this.mCacheMode = mode;
            }
        }
    }

    public int getCacheMode() {
        int i;
        synchronized (this.mXWalkSettingsLock) {
            i = this.mCacheMode;
        }
        return i;
    }

    public void setBlockNetworkLoads(boolean flag) {
        synchronized (this.mXWalkSettingsLock) {
            if (!flag) {
                if (this.mContext.checkPermission("android.permission.INTERNET", Process.myPid(), Process.myUid()) != 0) {
                    throw new SecurityException("Permission denied - application missing INTERNET permission");
                }
            }
            this.mBlockNetworkLoads = flag;
        }
    }

    public boolean getBlockNetworkLoads() {
        boolean z;
        synchronized (this.mXWalkSettingsLock) {
            z = this.mBlockNetworkLoads;
        }
        return z;
    }

    public void setAllowFileAccess(boolean allow) {
        synchronized (this.mXWalkSettingsLock) {
            if (this.mAllowFileUrlAccess != allow) {
                this.mAllowFileUrlAccess = allow;
            }
        }
    }

    public boolean getAllowFileAccess() {
        boolean z;
        synchronized (this.mXWalkSettingsLock) {
            z = this.mAllowFileUrlAccess;
        }
        return z;
    }

    public void setAllowContentAccess(boolean allow) {
        synchronized (this.mXWalkSettingsLock) {
            if (this.mAllowContentUrlAccess != allow) {
                this.mAllowContentUrlAccess = allow;
            }
        }
    }

    public boolean getAllowContentAccess() {
        boolean z;
        synchronized (this.mXWalkSettingsLock) {
            z = this.mAllowContentUrlAccess;
        }
        return z;
    }

    public void setGeolocationEnabled(boolean flag) {
        synchronized (this.mXWalkSettingsLock) {
            if (this.mGeolocationEnabled != flag) {
                this.mGeolocationEnabled = flag;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean getGeolocationEnabled() {
        boolean z;
        synchronized (this.mXWalkSettingsLock) {
            z = this.mGeolocationEnabled;
        }
        return z;
    }

    public void setJavaScriptEnabled(boolean flag) {
        synchronized (this.mXWalkSettingsLock) {
            if (this.mJavaScriptEnabled != flag) {
                this.mJavaScriptEnabled = flag;
                this.mEventHandler.updateWebkitPreferencesLocked();
            }
        }
    }

    public void setAllowUniversalAccessFromFileURLs(boolean flag) {
        synchronized (this.mXWalkSettingsLock) {
            if (this.mAllowUniversalAccessFromFileURLs != flag) {
                this.mAllowUniversalAccessFromFileURLs = flag;
                this.mEventHandler.updateWebkitPreferencesLocked();
            }
        }
    }

    public void setAllowFileAccessFromFileURLs(boolean flag) {
        synchronized (this.mXWalkSettingsLock) {
            if (this.mAllowFileAccessFromFileURLs != flag) {
                this.mAllowFileAccessFromFileURLs = flag;
                this.mEventHandler.updateWebkitPreferencesLocked();
            }
        }
    }

    public void setLoadsImagesAutomatically(boolean flag) {
        synchronized (this.mXWalkSettingsLock) {
            if (this.mLoadsImagesAutomatically != flag) {
                this.mLoadsImagesAutomatically = flag;
                this.mEventHandler.updateWebkitPreferencesLocked();
            }
        }
    }

    public boolean getLoadsImagesAutomatically() {
        boolean z;
        synchronized (this.mXWalkSettingsLock) {
            z = this.mLoadsImagesAutomatically;
        }
        return z;
    }

    public void setImagesEnabled(boolean flag) {
        synchronized (this.mXWalkSettingsLock) {
            if (this.mImagesEnabled != flag) {
                this.mImagesEnabled = flag;
                this.mEventHandler.updateWebkitPreferencesLocked();
            }
        }
    }

    public boolean getImagesEnabled() {
        boolean z;
        synchronized (this.mXWalkSettingsLock) {
            z = this.mImagesEnabled;
        }
        return z;
    }

    public boolean getJavaScriptEnabled() {
        boolean z;
        synchronized (this.mXWalkSettingsLock) {
            z = this.mJavaScriptEnabled;
        }
        return z;
    }

    public boolean getAllowUniversalAccessFromFileURLs() {
        boolean z;
        synchronized (this.mXWalkSettingsLock) {
            z = this.mAllowUniversalAccessFromFileURLs;
        }
        return z;
    }

    public boolean getAllowFileAccessFromFileURLs() {
        boolean z;
        synchronized (this.mXWalkSettingsLock) {
            z = this.mAllowFileAccessFromFileURLs;
        }
        return z;
    }

    public void setJavaScriptCanOpenWindowsAutomatically(boolean flag) {
        synchronized (this.mXWalkSettingsLock) {
            if (this.mJavaScriptCanOpenWindowsAutomatically != flag) {
                this.mJavaScriptCanOpenWindowsAutomatically = flag;
                this.mEventHandler.updateWebkitPreferencesLocked();
            }
        }
    }

    public boolean getJavaScriptCanOpenWindowsAutomatically() {
        boolean z;
        synchronized (this.mXWalkSettingsLock) {
            z = this.mJavaScriptCanOpenWindowsAutomatically;
        }
        return z;
    }

    public void setSupportMultipleWindows(boolean support) {
        synchronized (this.mXWalkSettingsLock) {
            if (this.mSupportMultipleWindows != support) {
                this.mSupportMultipleWindows = support;
                this.mEventHandler.updateWebkitPreferencesLocked();
            }
        }
    }

    public boolean supportMultipleWindows() {
        boolean z;
        synchronized (this.mXWalkSettingsLock) {
            z = this.mSupportMultipleWindows;
        }
        return z;
    }

    public void setUseWideViewPort(boolean use) {
        synchronized (this.mXWalkSettingsLock) {
            if (this.mUseWideViewport != use) {
                this.mUseWideViewport = use;
                this.mEventHandler.updateWebkitPreferencesLocked();
            }
        }
    }

    public boolean getUseWideViewPort() {
        boolean z;
        synchronized (this.mXWalkSettingsLock) {
            z = this.mUseWideViewport;
        }
        return z;
    }

    public void setAppCacheEnabled(boolean flag) {
        synchronized (this.mXWalkSettingsLock) {
            if (this.mAppCacheEnabled != flag) {
                this.mAppCacheEnabled = flag;
                this.mEventHandler.updateWebkitPreferencesLocked();
            }
        }
    }

    public void setAppCachePath(String path) {
        boolean needToSync = false;
        synchronized (sGlobalContentSettingsLock) {
            if (!sAppCachePathIsSet && path != null && !path.isEmpty()) {
                sAppCachePathIsSet = true;
                needToSync = true;
            }
        }
        if (needToSync) {
            synchronized (this.mXWalkSettingsLock) {
                this.mEventHandler.updateWebkitPreferencesLocked();
            }
        }
    }

    @CalledByNative
    private boolean getAppCacheEnabled() {
        return this.mAppCacheEnabled;
    }

    public void setDomStorageEnabled(boolean flag) {
        synchronized (this.mXWalkSettingsLock) {
            if (this.mDomStorageEnabled != flag) {
                this.mDomStorageEnabled = flag;
                this.mEventHandler.updateWebkitPreferencesLocked();
            }
        }
    }

    public boolean getDomStorageEnabled() {
        boolean z;
        synchronized (this.mXWalkSettingsLock) {
            z = this.mDomStorageEnabled;
        }
        return z;
    }

    public void setDatabaseEnabled(boolean flag) {
        synchronized (this.mXWalkSettingsLock) {
            if (this.mDatabaseEnabled != flag) {
                this.mDatabaseEnabled = flag;
                this.mEventHandler.updateWebkitPreferencesLocked();
            }
        }
    }

    public boolean getDatabaseEnabled() {
        boolean z;
        synchronized (this.mXWalkSettingsLock) {
            z = this.mDatabaseEnabled;
        }
        return z;
    }

    public void setMediaPlaybackRequiresUserGesture(boolean require) {
        synchronized (this.mXWalkSettingsLock) {
            if (this.mMediaPlaybackRequiresUserGesture != require) {
                this.mMediaPlaybackRequiresUserGesture = require;
                this.mEventHandler.updateWebkitPreferencesLocked();
            }
        }
    }

    public boolean getMediaPlaybackRequiresUserGesture() {
        boolean z;
        synchronized (this.mXWalkSettingsLock) {
            z = this.mMediaPlaybackRequiresUserGesture;
        }
        return z;
    }

    public void setDefaultVideoPosterURL(String url) {
        synchronized (this.mXWalkSettingsLock) {
            if ((this.mDefaultVideoPosterURL != null && !this.mDefaultVideoPosterURL.equals(url)) || (this.mDefaultVideoPosterURL == null && url != null)) {
                this.mDefaultVideoPosterURL = url;
                this.mEventHandler.updateWebkitPreferencesLocked();
            }
        }
    }

    public static String getDefaultUserAgent() {
        return LazyDefaultUserAgent.sInstance;
    }

    public void setUserAgentString(String ua) {
        synchronized (this.mXWalkSettingsLock) {
            String oldUserAgent = this.mUserAgent;
            if (ua == null || ua.length() == 0) {
                this.mUserAgent = LazyDefaultUserAgent.sInstance;
            } else {
                this.mUserAgent = ua;
            }
            if (!oldUserAgent.equals(this.mUserAgent)) {
                this.mEventHandler.maybeRunOnUiThreadBlocking(new Runnable() {
                    public void run() {
                        if (XWalkSettings.this.mNativeXWalkSettings != 0) {
                            XWalkSettings.this.nativeUpdateUserAgent(XWalkSettings.this.mNativeXWalkSettings);
                        }
                    }
                });
            }
        }
    }

    public String getUserAgentString() {
        String str;
        synchronized (this.mXWalkSettingsLock) {
            str = this.mUserAgent;
        }
        return str;
    }

    @CalledByNative
    private String getUserAgentLocked() {
        return this.mUserAgent;
    }

    public String getDefaultVideoPosterURL() {
        String str;
        synchronized (this.mXWalkSettingsLock) {
            str = this.mDefaultVideoPosterURL;
        }
        return str;
    }

    @CalledByNative
    private void updateEverything() {
        synchronized (this.mXWalkSettingsLock) {
            nativeUpdateEverythingLocked(this.mNativeXWalkSettings);
        }
    }

    /* access modifiers changed from: private */
    public void updateWebkitPreferencesOnUiThread() {
        if (this.mNativeXWalkSettings != 0) {
            ThreadUtils.assertOnUiThread();
            nativeUpdateWebkitPreferences(this.mNativeXWalkSettings);
        }
    }
}
