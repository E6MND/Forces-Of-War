package org.xwalk.core.internal;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.ValueCallback;
import android.webkit.WebResourceResponse;
import android.widget.FrameLayout;
import java.io.IOException;
import java.lang.annotation.Annotation;
import org.chromium.base.CalledByNative;
import org.chromium.base.JNINamespace;
import org.chromium.base.ThreadUtils;
import org.chromium.components.navigation_interception.InterceptNavigationDelegate;
import org.chromium.content.browser.ContentView;
import org.chromium.content.browser.ContentViewCore;
import org.chromium.content.browser.ContentViewRenderView;
import org.chromium.content.browser.ContentViewStatics;
import org.chromium.content.common.CleanupReference;
import org.chromium.content_public.browser.JavaScriptCallback;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.content_public.browser.NavigationController;
import org.chromium.content_public.browser.WebContents;
import org.chromium.media.MediaPlayerBridge;
import org.chromium.ui.base.ActivityWindowAndroid;
import org.chromium.ui.gfx.DeviceDisplayInfo;
import org.xwalk.core.internal.XWalkDevToolsServer;
import org.xwalk.core.internal.XWalkGeolocationPermissions;
import org.xwalk.core.internal.XWalkPreferencesInternal;

@JNINamespace("xwalk")
class XWalkContent extends FrameLayout implements XWalkPreferencesInternal.KeyValueChangeListener {
    static final /* synthetic */ boolean $assertionsDisabled = (!XWalkContent.class.desiredAssertionStatus());
    public static final String SAVE_RESTORE_STATE_KEY = "XWALKVIEW_STATE";
    private static String TAG = "XWalkContent";
    private static Class<? extends Annotation> javascriptInterfaceClass = null;
    private static boolean timerPaused = false;
    private CleanupReference mCleanupReference;
    private ContentView mContentView;
    private ContentViewCore mContentViewCore;
    private ContentViewRenderView mContentViewRenderView;
    /* access modifiers changed from: private */
    public XWalkContentsClientBridge mContentsClientBridge = new XWalkContentsClientBridge(this.mXWalkView);
    private XWalkDevToolsServer mDevToolsServer;
    /* access modifiers changed from: private */
    public XWalkGeolocationPermissions mGeolocationPermissions;
    private XWalkContentsIoThreadClient mIoThreadClient = new XWalkIoThreadClientImpl();
    private XWalkLaunchScreenManager mLaunchScreenManager;
    long mNativeContent;
    long mNativeWebContents;
    private NavigationController mNavigationController;
    /* access modifiers changed from: private */
    public XWalkSettings mSettings;
    private WebContents mWebContents;
    private ActivityWindowAndroid mWindow;
    private XWalkWebContentsDelegateAdapter mXWalkContentsDelegateAdapter = new XWalkWebContentsDelegateAdapter(this.mContentsClientBridge);
    private XWalkViewInternal mXWalkView;

    private native void nativeClearCache(long j, boolean z);

    /* access modifiers changed from: private */
    public static native void nativeDestroy(long j);

    private native String nativeDevToolsAgentId(long j);

    private native int nativeGetRoutingID(long j);

    private native byte[] nativeGetState(long j);

    private native String nativeGetVersion(long j);

    private native long nativeGetWebContents(long j);

    private native long nativeInit();

    /* access modifiers changed from: private */
    public native void nativeInvokeGeolocationCallback(long j, boolean z, String str);

    private native long nativeReleasePopupXWalkContent(long j);

    private native void nativeSetBackgroundColor(long j, int i);

    private native void nativeSetJavaPeers(long j, XWalkContent xWalkContent, XWalkWebContentsDelegateAdapter xWalkWebContentsDelegateAdapter, XWalkContentsClientBridge xWalkContentsClientBridge, XWalkContentsIoThreadClient xWalkContentsIoThreadClient, InterceptNavigationDelegate interceptNavigationDelegate);

    private native void nativeSetJsOnlineProperty(long j, boolean z);

    private native boolean nativeSetManifest(long j, String str, String str2);

    private native boolean nativeSetState(long j, byte[] bArr);

    static void setJavascriptInterfaceClass(Class<? extends Annotation> clazz) {
        if ($assertionsDisabled || javascriptInterfaceClass == null) {
            javascriptInterfaceClass = clazz;
            return;
        }
        throw new AssertionError();
    }

    private static final class DestroyRunnable implements Runnable {
        private final long mNativeContent;

        private DestroyRunnable(long nativeXWalkContent) {
            this.mNativeContent = nativeXWalkContent;
        }

        public void run() {
            XWalkContent.nativeDestroy(this.mNativeContent);
        }
    }

    public XWalkContent(Context context, AttributeSet attrs, XWalkViewInternal xwView) {
        super(context, attrs);
        this.mXWalkView = xwView;
        this.mWindow = new ActivityWindowAndroid(xwView.getActivity());
        this.mGeolocationPermissions = new XWalkGeolocationPermissions(new InMemorySharedPreferences());
        MediaPlayerBridge.setResourceLoadingFilter(new XWalkMediaPlayerResourceLoadingFilter());
        setNativeContent(nativeInit());
        XWalkPreferencesInternal.load(this);
    }

    private void setNativeContent(long newNativeContent) {
        if (this.mNativeContent != 0) {
            destroy();
            this.mContentViewCore = null;
        }
        if ($assertionsDisabled || (this.mNativeContent == 0 && this.mCleanupReference == null && this.mContentViewCore == null)) {
            this.mContentViewRenderView = new ContentViewRenderView(getContext(), XWalkPreferencesInternal.getValue("animatable-xwalk-view") ? ContentViewRenderView.CompositingSurfaceType.TEXTURE_VIEW : ContentViewRenderView.CompositingSurfaceType.SURFACE_VIEW) {
                /* access modifiers changed from: protected */
                public void onReadyToRender() {
                }
            };
            this.mContentViewRenderView.onNativeLibraryLoaded(this.mWindow);
            this.mLaunchScreenManager = new XWalkLaunchScreenManager(getContext(), this.mXWalkView);
            this.mContentViewRenderView.registerFirstRenderedFrameListener(this.mLaunchScreenManager);
            addView(this.mContentViewRenderView, new FrameLayout.LayoutParams(-1, -1));
            this.mNativeContent = newNativeContent;
            this.mCleanupReference = new CleanupReference(this, new DestroyRunnable(this.mNativeContent));
            this.mNativeWebContents = nativeGetWebContents(this.mNativeContent);
            this.mContentViewCore = new ContentViewCore(getContext());
            this.mContentView = ContentView.newInstance(getContext(), this.mContentViewCore);
            this.mContentViewCore.initialize(this.mContentView, this.mContentView, this.mNativeWebContents, this.mWindow);
            this.mWebContents = this.mContentViewCore.getWebContents();
            this.mNavigationController = this.mWebContents.getNavigationController();
            addView(this.mContentView, new FrameLayout.LayoutParams(-1, -1));
            this.mContentViewCore.setContentViewClient(this.mContentsClientBridge);
            this.mContentViewRenderView.setCurrentContentViewCore(this.mContentViewCore);
            this.mContentsClientBridge.installWebContentsObserver(this.mWebContents);
            this.mContentsClientBridge.setDIPScale(DeviceDisplayInfo.create(getContext()).getDIPScale());
            this.mContentViewCore.setDownloadDelegate(this.mContentsClientBridge);
            this.mSettings = new XWalkSettings(getContext(), this.mNativeWebContents, false);
            this.mSettings.setAllowFileAccessFromFileURLs(true);
            this.mSettings.setSupportMultipleWindows(true);
            nativeSetJavaPeers(this.mNativeContent, this, this.mXWalkContentsDelegateAdapter, this.mContentsClientBridge, this.mIoThreadClient, this.mContentsClientBridge.getInterceptNavigationDelegate());
            return;
        }
        throw new AssertionError();
    }

    public void supplyContentsForPopup(XWalkContent newContents) {
        if (this.mNativeContent != 0) {
            long popupNativeXWalkContent = nativeReleasePopupXWalkContent(this.mNativeContent);
            if (popupNativeXWalkContent == 0) {
                Log.w(TAG, "Popup XWalkView bind failed: no pending content.");
                if (newContents != null) {
                    newContents.destroy();
                }
            } else if (newContents == null) {
                nativeDestroy(popupNativeXWalkContent);
            } else {
                newContents.receivePopupContents(popupNativeXWalkContent);
            }
        }
    }

    private void receivePopupContents(long popupNativeXWalkContents) {
        setNativeContent(popupNativeXWalkContents);
        this.mContentViewCore.onShow();
    }

    /* access modifiers changed from: package-private */
    public void doLoadUrl(String url, String content) {
        LoadUrlParams params;
        if (url == null || url.isEmpty() || !TextUtils.equals(url, this.mWebContents.getUrl())) {
            if (content == null || content.isEmpty()) {
                params = new LoadUrlParams(url);
            } else {
                params = LoadUrlParams.createLoadDataParamsWithBaseUrl(content, "text/html", false, url, (String) null);
            }
            params.setOverrideUserAgent(2);
            this.mNavigationController.loadUrl(params);
        } else {
            this.mNavigationController.reload(true);
        }
        this.mContentView.requestFocus();
    }

    public void loadUrl(String url, String data) {
        if (this.mNativeContent != 0) {
            if ((url != null && !url.isEmpty()) || (data != null && !data.isEmpty())) {
                doLoadUrl(url, data);
            }
        }
    }

    public void reload(int mode) {
        if (this.mNativeContent != 0) {
            switch (mode) {
                case 1:
                    this.mNavigationController.reloadIgnoringCache(true);
                    return;
                default:
                    this.mNavigationController.reload(true);
                    return;
            }
        }
    }

    public String getUrl() {
        if (this.mNativeContent == 0) {
            return null;
        }
        String url = this.mWebContents.getUrl();
        if (url == null || url.trim().isEmpty()) {
            return null;
        }
        return url;
    }

    public String getTitle() {
        if (this.mNativeContent == 0) {
            return null;
        }
        String title = this.mWebContents.getTitle().trim();
        if (title == null) {
            return "";
        }
        return title;
    }

    public void addJavascriptInterface(Object object, String name) {
        if (this.mNativeContent != 0) {
            this.mContentViewCore.addPossiblyUnsafeJavascriptInterface(object, name, javascriptInterfaceClass);
        }
    }

    public void evaluateJavascript(String script, ValueCallback<String> callback) {
        if (this.mNativeContent != 0) {
            final ValueCallback<String> fCallback = callback;
            JavaScriptCallback coreCallback = null;
            if (fCallback != null) {
                coreCallback = new JavaScriptCallback() {
                    public void handleJavaScriptResult(String jsonResult) {
                        fCallback.onReceiveValue(jsonResult);
                    }
                };
            }
            this.mContentViewCore.getWebContents().evaluateJavaScript(script, coreCallback);
        }
    }

    public void setUIClient(XWalkUIClientInternal client) {
        if (this.mNativeContent != 0) {
            this.mContentsClientBridge.setUIClient(client);
        }
    }

    public void setResourceClient(XWalkResourceClientInternal client) {
        if (this.mNativeContent != 0) {
            this.mContentsClientBridge.setResourceClient(client);
        }
    }

    public void setXWalkWebChromeClient(XWalkWebChromeClient client) {
        if (this.mNativeContent != 0) {
            this.mContentsClientBridge.setXWalkWebChromeClient(client);
        }
    }

    public XWalkWebChromeClient getXWalkWebChromeClient() {
        if (this.mNativeContent == 0) {
            return null;
        }
        return this.mContentsClientBridge.getXWalkWebChromeClient();
    }

    public void setXWalkClient(XWalkClient client) {
        if (this.mNativeContent != 0) {
            this.mContentsClientBridge.setXWalkClient(client);
        }
    }

    public void setDownloadListener(DownloadListener listener) {
        if (this.mNativeContent != 0) {
            this.mContentsClientBridge.setDownloadListener(listener);
        }
    }

    public void setNavigationHandler(XWalkNavigationHandler handler) {
        if (this.mNativeContent != 0) {
            this.mContentsClientBridge.setNavigationHandler(handler);
        }
    }

    public void setNotificationService(XWalkNotificationService service) {
        if (this.mNativeContent != 0) {
            this.mContentsClientBridge.setNotificationService(service);
        }
    }

    public void onPause() {
        if (this.mNativeContent != 0) {
            this.mContentViewCore.onHide();
        }
    }

    public void onResume() {
        if (this.mNativeContent != 0) {
            this.mContentViewCore.onShow();
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (this.mNativeContent != 0) {
            this.mWindow.onActivityResult(requestCode, resultCode, data);
        }
    }

    public boolean onNewIntent(Intent intent) {
        if (this.mNativeContent == 0) {
            return false;
        }
        return this.mContentsClientBridge.onNewIntent(intent);
    }

    public void clearCache(boolean includeDiskFiles) {
        if (this.mNativeContent != 0) {
            nativeClearCache(this.mNativeContent, includeDiskFiles);
        }
    }

    public void clearHistory() {
        if (this.mNativeContent != 0) {
            this.mNavigationController.clearHistory();
        }
    }

    public boolean canGoBack() {
        if (this.mNativeContent == 0) {
            return false;
        }
        return this.mNavigationController.canGoBack();
    }

    public void goBack() {
        if (this.mNativeContent != 0) {
            this.mNavigationController.goBack();
        }
    }

    public boolean canGoForward() {
        if (this.mNativeContent == 0) {
            return false;
        }
        return this.mNavigationController.canGoForward();
    }

    public void goForward() {
        if (this.mNativeContent != 0) {
            this.mNavigationController.goForward();
        }
    }

    /* access modifiers changed from: package-private */
    public void navigateTo(int offset) {
        this.mNavigationController.goToOffset(offset);
    }

    public void stopLoading() {
        if (this.mNativeContent != 0) {
            this.mWebContents.stop();
            this.mContentsClientBridge.onStopLoading();
        }
    }

    public void pauseTimers() {
        if (!timerPaused && this.mNativeContent != 0) {
            ContentViewStatics.setWebKitSharedTimersSuspended(true);
            timerPaused = true;
        }
    }

    public void resumeTimers() {
        if (timerPaused && this.mNativeContent != 0) {
            ContentViewStatics.setWebKitSharedTimersSuspended(false);
            timerPaused = false;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x000a, code lost:
        r1 = r8.mNavigationController.getNavigationHistory();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getOriginalUrl() {
        /*
            r8 = this;
            r2 = 0
            long r4 = r8.mNativeContent
            r6 = 0
            int r3 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r3 != 0) goto L_0x000a
        L_0x0009:
            return r2
        L_0x000a:
            org.chromium.content_public.browser.NavigationController r3 = r8.mNavigationController
            org.chromium.content_public.browser.NavigationHistory r1 = r3.getNavigationHistory()
            int r0 = r1.getCurrentEntryIndex()
            if (r0 < 0) goto L_0x0009
            int r3 = r1.getEntryCount()
            if (r0 >= r3) goto L_0x0009
            org.chromium.content_public.browser.NavigationEntry r2 = r1.getEntryAtIndex(r0)
            java.lang.String r2 = r2.getOriginalUrl()
            goto L_0x0009
        */
        throw new UnsupportedOperationException("Method not decompiled: org.xwalk.core.internal.XWalkContent.getOriginalUrl():java.lang.String");
    }

    public String getXWalkVersion() {
        if (this.mNativeContent == 0) {
            return "";
        }
        return nativeGetVersion(this.mNativeContent);
    }

    public void setBackgroundColor(int color) {
        if (this.mNativeContent != 0) {
            nativeSetBackgroundColor(this.mNativeContent, color);
        }
    }

    public void setNetworkAvailable(boolean networkUp) {
        if (this.mNativeContent != 0) {
            nativeSetJsOnlineProperty(this.mNativeContent, networkUp);
        }
    }

    public ContentViewCore getContentViewCoreForTest() {
        return this.mContentViewCore;
    }

    public void installWebContentsObserverForTest(XWalkContentsClient contentClient) {
        if (this.mNativeContent != 0) {
            contentClient.installWebContentsObserver(this.mContentViewCore.getWebContents());
        }
    }

    public String devToolsAgentId() {
        if (this.mNativeContent == 0) {
            return "";
        }
        return nativeDevToolsAgentId(this.mNativeContent);
    }

    public XWalkSettings getSettings() {
        return this.mSettings;
    }

    public void loadAppFromManifest(String url, String data) {
        if (this.mNativeContent == 0) {
            return;
        }
        if ((url != null && !url.isEmpty()) || (data != null && !data.isEmpty())) {
            String content = data;
            if (data == null || data.isEmpty()) {
                try {
                    content = AndroidProtocolHandler.getUrlContent(this.mXWalkView.getActivity(), url);
                } catch (IOException e) {
                    throw new RuntimeException("Failed to read the manifest: " + url);
                }
            }
            String baseUrl = url;
            int position = url.lastIndexOf("/");
            if (position != -1) {
                baseUrl = url.substring(0, position + 1);
            } else {
                Log.w(TAG, "The url of manifest.json is probably not set correctly.");
            }
            if (!nativeSetManifest(this.mNativeContent, baseUrl, content)) {
                throw new RuntimeException("Failed to parse the manifest file: " + url);
            }
        }
    }

    public XWalkNavigationHistoryInternal getNavigationHistory() {
        if (this.mNativeContent == 0) {
            return null;
        }
        return new XWalkNavigationHistoryInternal(this.mXWalkView, this.mNavigationController.getNavigationHistory());
    }

    public XWalkNavigationHistoryInternal saveState(Bundle outState) {
        byte[] state;
        if (this.mNativeContent == 0 || outState == null || (state = nativeGetState(this.mNativeContent)) == null) {
            return null;
        }
        outState.putByteArray(SAVE_RESTORE_STATE_KEY, state);
        return getNavigationHistory();
    }

    public XWalkNavigationHistoryInternal restoreState(Bundle inState) {
        byte[] state;
        if (this.mNativeContent == 0 || inState == null || (state = inState.getByteArray(SAVE_RESTORE_STATE_KEY)) == null) {
            return null;
        }
        boolean result = nativeSetState(this.mNativeContent, state);
        if (result) {
            this.mContentsClientBridge.onUpdateTitle(this.mWebContents.getTitle());
        }
        if (result) {
            return getNavigationHistory();
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public boolean hasEnteredFullscreen() {
        return this.mContentsClientBridge.hasEnteredFullscreen();
    }

    /* access modifiers changed from: package-private */
    public void exitFullscreen() {
        if (hasEnteredFullscreen()) {
            this.mContentsClientBridge.exitFullscreen(this.mNativeWebContents);
        }
    }

    @CalledByNative
    public void onGetUrlFromManifest(String url) {
        if (url != null && !url.isEmpty()) {
            loadUrl(url, (String) null);
        }
    }

    @CalledByNative
    public void onGetUrlAndLaunchScreenFromManifest(String url, String readyWhen, String imageBorder) {
        if (url != null && !url.isEmpty()) {
            this.mLaunchScreenManager.displayLaunchScreen(readyWhen, imageBorder);
            this.mContentsClientBridge.registerPageLoadListener(this.mLaunchScreenManager);
            loadUrl(url, (String) null);
        }
    }

    @CalledByNative
    public void onGetFullscreenFlagFromManifest(boolean enterFullscreen) {
        if (!enterFullscreen) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 19) {
            this.mXWalkView.getActivity().getWindow().getDecorView().setSystemUiVisibility(5894);
        } else {
            this.mXWalkView.getActivity().getWindow().addFlags(1024);
        }
    }

    public void destroy() {
        if (this.mNativeContent != 0) {
            XWalkPreferencesInternal.unload(this);
            setNotificationService((XWalkNotificationService) null);
            removeView(this.mContentView);
            removeView(this.mContentViewRenderView);
            this.mContentViewRenderView.setCurrentContentViewCore((ContentViewCore) null);
            this.mContentViewRenderView.destroy();
            this.mContentViewCore.destroy();
            this.mCleanupReference.cleanupNow();
            this.mCleanupReference = null;
            this.mNativeContent = 0;
        }
    }

    public int getRoutingID() {
        return nativeGetRoutingID(this.mNativeContent);
    }

    private class XWalkIoThreadClientImpl implements XWalkContentsIoThreadClient {
        private XWalkIoThreadClientImpl() {
        }

        public int getCacheMode() {
            return XWalkContent.this.mSettings.getCacheMode();
        }

        public InterceptedRequestData shouldInterceptRequest(String url, boolean isMainFrame) {
            XWalkContent.this.mContentsClientBridge.getCallbackHelper().postOnResourceLoadStarted(url);
            WebResourceResponse webResourceResponse = XWalkContent.this.mContentsClientBridge.shouldInterceptRequest(url);
            if (webResourceResponse == null) {
                XWalkContent.this.mContentsClientBridge.getCallbackHelper().postOnLoadResource(url);
                return null;
            }
            if (isMainFrame && webResourceResponse.getData() == null) {
                XWalkContent.this.mContentsClientBridge.getCallbackHelper().postOnReceivedError(-1, (String) null, url);
            }
            return new InterceptedRequestData(webResourceResponse.getMimeType(), webResourceResponse.getEncoding(), webResourceResponse.getData());
        }

        public boolean shouldBlockContentUrls() {
            return !XWalkContent.this.mSettings.getAllowContentAccess();
        }

        public boolean shouldBlockFileUrls() {
            return !XWalkContent.this.mSettings.getAllowFileAccess();
        }

        public boolean shouldBlockNetworkLoads() {
            return XWalkContent.this.mSettings.getBlockNetworkLoads();
        }

        public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimeType, long contentLength) {
            XWalkContent.this.mContentsClientBridge.getCallbackHelper().postOnDownloadStart(url, userAgent, contentDisposition, mimeType, contentLength);
        }

        public void newLoginRequest(String realm, String account, String args) {
            XWalkContent.this.mContentsClientBridge.getCallbackHelper().postOnReceivedLoginRequest(realm, account, args);
        }
    }

    private class XWalkGeolocationCallback implements XWalkGeolocationPermissions.Callback {
        private XWalkGeolocationCallback() {
        }

        public void invoke(final String origin, final boolean allow, final boolean retain) {
            ThreadUtils.runOnUiThread((Runnable) new Runnable() {
                public void run() {
                    if (retain) {
                        if (allow) {
                            XWalkContent.this.mGeolocationPermissions.allow(origin);
                        } else {
                            XWalkContent.this.mGeolocationPermissions.deny(origin);
                        }
                    }
                    XWalkContent.this.nativeInvokeGeolocationCallback(XWalkContent.this.mNativeContent, allow, origin);
                }
            });
        }
    }

    @CalledByNative
    private void onGeolocationPermissionsShowPrompt(String origin) {
        if (this.mNativeContent != 0) {
            if (!this.mSettings.getGeolocationEnabled()) {
                nativeInvokeGeolocationCallback(this.mNativeContent, false, origin);
            } else if (this.mGeolocationPermissions.hasOrigin(origin)) {
                nativeInvokeGeolocationCallback(this.mNativeContent, this.mGeolocationPermissions.isOriginAllowed(origin), origin);
            } else {
                this.mContentsClientBridge.onGeolocationPermissionsShowPrompt(origin, new XWalkGeolocationCallback());
            }
        }
    }

    @CalledByNative
    public void onGeolocationPermissionsHidePrompt() {
        this.mContentsClientBridge.onGeolocationPermissionsHidePrompt();
    }

    public void enableRemoteDebugging() {
        String socketName = getContext().getApplicationContext().getPackageName() + "_devtools_remote";
        if (this.mDevToolsServer == null) {
            this.mDevToolsServer = new XWalkDevToolsServer(socketName);
            this.mDevToolsServer.setRemoteDebuggingEnabled(true, XWalkDevToolsServer.Security.ALLOW_SOCKET_ACCESS);
        }
    }

    /* access modifiers changed from: package-private */
    public void disableRemoteDebugging() {
        if (this.mDevToolsServer != null) {
            if (this.mDevToolsServer.isRemoteDebuggingEnabled()) {
                this.mDevToolsServer.setRemoteDebuggingEnabled(false);
            }
            this.mDevToolsServer.destroy();
            this.mDevToolsServer = null;
        }
    }

    public String getRemoteDebuggingUrl() {
        if (this.mDevToolsServer == null) {
            return "";
        }
        return "ws://" + this.mDevToolsServer.getSocketName() + "/devtools/page/" + devToolsAgentId();
    }

    public void onKeyValueChanged(String key, XWalkPreferencesInternal.PreferenceValue value) {
        if (key != null) {
            if (key.equals("remote-debugging")) {
                if (value.getBooleanValue()) {
                    enableRemoteDebugging();
                } else {
                    disableRemoteDebugging();
                }
            } else if (key.equals("enable-javascript")) {
                if (this.mSettings != null) {
                    this.mSettings.setJavaScriptEnabled(value.getBooleanValue());
                }
            } else if (key.equals("javascript-can-open-window")) {
                if (this.mSettings != null) {
                    this.mSettings.setJavaScriptCanOpenWindowsAutomatically(value.getBooleanValue());
                }
            } else if (key.equals("allow-universal-access-from-file")) {
                if (this.mSettings != null) {
                    this.mSettings.setAllowUniversalAccessFromFileURLs(value.getBooleanValue());
                }
            } else if (key.equals("support-multiple-windows") && this.mSettings != null) {
                this.mSettings.setSupportMultipleWindows(value.getBooleanValue());
            }
        }
    }

    public void setOverlayVideoMode(boolean enabled) {
        if (this.mContentViewRenderView != null) {
            this.mContentViewRenderView.setOverlayVideoMode(enabled);
        }
    }
}
