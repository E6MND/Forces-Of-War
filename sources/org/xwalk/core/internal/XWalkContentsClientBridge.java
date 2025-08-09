package org.xwalk.core.internal;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Picture;
import android.net.Uri;
import android.net.http.SslCertificate;
import android.net.http.SslError;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.ValueCallback;
import android.webkit.WebResourceResponse;
import org.chromium.base.CalledByNative;
import org.chromium.base.JNINamespace;
import org.chromium.components.navigation_interception.InterceptNavigationDelegate;
import org.chromium.components.navigation_interception.NavigationParams;
import org.chromium.content.browser.ContentVideoViewClient;
import org.chromium.content.browser.ContentViewDownloadDelegate;
import org.chromium.content.browser.DownloadInfo;
import org.xwalk.core.internal.XWalkGeolocationPermissions;
import org.xwalk.core.internal.XWalkUIClientInternal;
import org.xwalk.core.internal.XWalkWebChromeClient;

@JNINamespace("xwalk")
class XWalkContentsClientBridge extends XWalkContentsClient implements ContentViewDownloadDelegate {
    private static final int NEW_ICON_DOWNLOAD = 101;
    private static final int NEW_XWALKVIEW_CREATED = 100;
    private static final String TAG = XWalkContentsClientBridge.class.getName();
    private DownloadListener mDownloadListener;
    private Bitmap mFavicon;
    private InterceptNavigationDelegate mInterceptNavigationDelegate;
    private boolean mIsFullscreen = false;
    private XWalkUIClientInternal.LoadStatusInternal mLoadStatus = XWalkUIClientInternal.LoadStatusInternal.FINISHED;
    private String mLoadingUrl = null;
    /* access modifiers changed from: private */
    public long mNativeContentsClientBridge;
    /* access modifiers changed from: private */
    public XWalkNavigationHandler mNavigationHandler;
    private XWalkNotificationService mNotificationService;
    private PageLoadListener mPageLoadListener;
    private float mPageScaleFactor;
    /* access modifiers changed from: private */
    public Handler mUiThreadHandler;
    private XWalkClient mXWalkClient;
    private XWalkResourceClientInternal mXWalkResourceClient;
    private XWalkUIClientInternal mXWalkUIClient;
    /* access modifiers changed from: private */
    public XWalkViewInternal mXWalkView;
    private XWalkWebChromeClient mXWalkWebChromeClient;

    private native void nativeCancelJsResult(long j, int i);

    private native void nativeConfirmJsResult(long j, int i, String str);

    /* access modifiers changed from: private */
    public native void nativeDownloadIcon(long j, String str);

    private native void nativeExitFullscreen(long j, long j2);

    private native void nativeNotificationClicked(long j, int i);

    private native void nativeNotificationClosed(long j, int i, boolean z);

    private native void nativeNotificationDisplayed(long j, int i);

    /* access modifiers changed from: private */
    public native void nativeOnFilesNotSelected(long j, int i, int i2, int i3);

    /* access modifiers changed from: private */
    public native void nativeOnFilesSelected(long j, int i, int i2, int i3, String str, String str2);

    private native void nativeProceedSslError(long j, boolean z, int i);

    private class InterceptNavigationDelegateImpl implements InterceptNavigationDelegate {
        private XWalkContentsClient mContentsClient;

        public InterceptNavigationDelegateImpl(XWalkContentsClient client) {
            this.mContentsClient = client;
        }

        public boolean shouldIgnoreNavigation(NavigationParams navigationParams) {
            String url = navigationParams.url;
            boolean ignoreNavigation = XWalkContentsClientBridge.this.shouldOverrideUrlLoading(url) || (XWalkContentsClientBridge.this.mNavigationHandler != null && XWalkContentsClientBridge.this.mNavigationHandler.handleNavigation(navigationParams));
            if (!ignoreNavigation) {
                this.mContentsClient.getCallbackHelper().postOnPageStarted(url);
            }
            return ignoreNavigation;
        }
    }

    public XWalkContentsClientBridge(XWalkViewInternal xwView) {
        this.mXWalkView = xwView;
        this.mInterceptNavigationDelegate = new InterceptNavigationDelegateImpl(this);
        this.mUiThreadHandler = new Handler() {
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 100:
                        XWalkViewInternal newXWalkView = (XWalkViewInternal) msg.obj;
                        if (newXWalkView == XWalkContentsClientBridge.this.mXWalkView) {
                            throw new IllegalArgumentException("Parent XWalkView cannot host it's own popup window");
                        } else if (newXWalkView == null || newXWalkView.getNavigationHistory().size() == 0) {
                            XWalkContentsClientBridge.this.mXWalkView.completeWindowCreation(newXWalkView);
                            return;
                        } else {
                            throw new IllegalArgumentException("New WebView for popup window must not have been previously navigated.");
                        }
                    case 101:
                        XWalkContentsClientBridge.this.nativeDownloadIcon(XWalkContentsClientBridge.this.mNativeContentsClientBridge, (String) msg.obj);
                        return;
                    default:
                        throw new IllegalStateException();
                }
            }
        };
    }

    public void setUIClient(XWalkUIClientInternal client) {
        if (client != null) {
            this.mXWalkUIClient = client;
        } else {
            this.mXWalkUIClient = new XWalkUIClientInternal(this.mXWalkView);
        }
    }

    public void setResourceClient(XWalkResourceClientInternal client) {
        if (client != null) {
            this.mXWalkResourceClient = client;
        } else {
            this.mXWalkResourceClient = new XWalkResourceClientInternal(this.mXWalkView);
        }
    }

    public void setXWalkWebChromeClient(XWalkWebChromeClient client) {
        if (client != null) {
            client.setContentsClient(this);
            this.mXWalkWebChromeClient = client;
        }
    }

    public XWalkWebChromeClient getXWalkWebChromeClient() {
        return this.mXWalkWebChromeClient;
    }

    public void setXWalkClient(XWalkClient client) {
        this.mXWalkClient = client;
    }

    public void setNavigationHandler(XWalkNavigationHandler handler) {
        this.mNavigationHandler = handler;
    }

    /* access modifiers changed from: package-private */
    public void registerPageLoadListener(PageLoadListener listener) {
        this.mPageLoadListener = listener;
    }

    public void setNotificationService(XWalkNotificationService service) {
        if (this.mNotificationService != null) {
            this.mNotificationService.shutdown();
        }
        this.mNotificationService = service;
        if (this.mNotificationService != null) {
            this.mNotificationService.setBridge(this);
        }
    }

    public boolean onNewIntent(Intent intent) {
        return this.mNotificationService.maybeHandleIntent(intent);
    }

    public InterceptNavigationDelegate getInterceptNavigationDelegate() {
        return this.mInterceptNavigationDelegate;
    }

    private boolean isOwnerActivityRunning() {
        if (this.mXWalkView == null || !this.mXWalkView.isOwnerActivityRunning()) {
            return false;
        }
        return true;
    }

    public boolean shouldOverrideUrlLoading(String url) {
        if (this.mXWalkResourceClient == null || this.mXWalkView == null) {
            return false;
        }
        return this.mXWalkResourceClient.shouldOverrideUrlLoading(this.mXWalkView, url);
    }

    public boolean shouldOverrideKeyEvent(KeyEvent event) {
        boolean overridden = false;
        if (!(this.mXWalkUIClient == null || this.mXWalkView == null)) {
            overridden = this.mXWalkUIClient.shouldOverrideKeyEvent(this.mXWalkView, event);
        }
        if (!overridden) {
            return super.shouldOverrideKeyEvent(event);
        }
        return overridden;
    }

    public void onUnhandledKeyEvent(KeyEvent event) {
        if (this.mXWalkUIClient != null && this.mXWalkView != null) {
            this.mXWalkUIClient.onUnhandledKeyEvent(this.mXWalkView, event);
        }
    }

    public void getVisitedHistory(ValueCallback<String[]> valueCallback) {
    }

    public void doUpdateVisitedHistory(String url, boolean isReload) {
    }

    public void onProgressChanged(int progress) {
        if (isOwnerActivityRunning()) {
            this.mXWalkResourceClient.onProgressChanged(this.mXWalkView, progress);
        }
    }

    public WebResourceResponse shouldInterceptRequest(String url) {
        if (isOwnerActivityRunning()) {
            return this.mXWalkResourceClient.shouldInterceptLoadRequest(this.mXWalkView, url);
        }
        return null;
    }

    public void onResourceLoadStarted(String url) {
        if (isOwnerActivityRunning()) {
            this.mXWalkResourceClient.onLoadStarted(this.mXWalkView, url);
        }
    }

    public void onResourceLoadFinished(String url) {
        if (isOwnerActivityRunning()) {
            this.mXWalkResourceClient.onLoadFinished(this.mXWalkView, url);
        }
    }

    public void onLoadResource(String url) {
        if (this.mXWalkClient != null && isOwnerActivityRunning()) {
            this.mXWalkClient.onLoadResource(this.mXWalkView, url);
        }
    }

    public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
        return false;
    }

    @CalledByNative
    public void onReceivedHttpAuthRequest(XWalkHttpAuthHandler handler, String host, String realm) {
        if (this.mXWalkClient != null && isOwnerActivityRunning()) {
            this.mXWalkClient.onReceivedHttpAuthRequest(this.mXWalkView, handler, host, realm);
        }
    }

    public void onReceivedSslError(ValueCallback<Boolean> callback, SslError error) {
        if (this.mXWalkResourceClient != null && isOwnerActivityRunning()) {
            this.mXWalkResourceClient.onReceivedSslError(this.mXWalkView, callback, error);
        }
    }

    public void onReceivedLoginRequest(String realm, String account, String args) {
    }

    public void onGeolocationPermissionsShowPrompt(String origin, XWalkGeolocationPermissions.Callback callback) {
        if (this.mXWalkWebChromeClient != null && isOwnerActivityRunning()) {
            this.mXWalkWebChromeClient.onGeolocationPermissionsShowPrompt(origin, callback);
        }
    }

    public void onGeolocationPermissionsHidePrompt() {
        if (this.mXWalkWebChromeClient != null && isOwnerActivityRunning()) {
            this.mXWalkWebChromeClient.onGeolocationPermissionsHidePrompt();
        }
    }

    public void onFindResultReceived(int activeMatchOrdinal, int numberOfMatches, boolean isDoneCounting) {
    }

    public void onNewPicture(Picture picture) {
    }

    public void onPageStarted(String url) {
        if (this.mXWalkUIClient != null && isOwnerActivityRunning()) {
            this.mLoadingUrl = url;
            this.mLoadStatus = XWalkUIClientInternal.LoadStatusInternal.FINISHED;
            this.mXWalkUIClient.onPageLoadStarted(this.mXWalkView, url);
        }
    }

    public void onPageFinished(String url) {
        if (isOwnerActivityRunning()) {
            if (this.mPageLoadListener != null) {
                this.mPageLoadListener.onPageFinished(url);
            }
            if (this.mXWalkUIClient != null) {
                if (this.mLoadStatus != XWalkUIClientInternal.LoadStatusInternal.CANCELLED || this.mLoadingUrl == null) {
                    this.mXWalkUIClient.onPageLoadStopped(this.mXWalkView, url, this.mLoadStatus);
                } else {
                    this.mXWalkUIClient.onPageLoadStopped(this.mXWalkView, this.mLoadingUrl, this.mLoadStatus);
                }
                this.mLoadingUrl = null;
            }
            onResourceLoadFinished(url);
        }
    }

    /* access modifiers changed from: protected */
    public void onStopLoading() {
        this.mLoadStatus = XWalkUIClientInternal.LoadStatusInternal.CANCELLED;
    }

    public void onReceivedError(int errorCode, String description, String failingUrl) {
        if (isOwnerActivityRunning()) {
            if (this.mLoadingUrl != null && this.mLoadingUrl.equals(failingUrl)) {
                this.mLoadStatus = XWalkUIClientInternal.LoadStatusInternal.FAILED;
            }
            this.mXWalkResourceClient.onReceivedLoadError(this.mXWalkView, errorCode, description, failingUrl);
        }
    }

    public void onRendererUnresponsive() {
        if (this.mXWalkClient != null && isOwnerActivityRunning()) {
            this.mXWalkClient.onRendererUnresponsive(this.mXWalkView);
        }
    }

    public void onRendererResponsive() {
        if (this.mXWalkClient != null && isOwnerActivityRunning()) {
            this.mXWalkClient.onRendererResponsive(this.mXWalkView);
        }
    }

    public void onFormResubmission(Message dontResend, Message resend) {
        dontResend.sendToTarget();
    }

    public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimeType, long contentLength) {
    }

    public boolean onCreateWindow(boolean isDialog, boolean isUserGesture) {
        if (isDialog) {
            return false;
        }
        XWalkUIClientInternal.InitiateByInternal initiator = XWalkUIClientInternal.InitiateByInternal.BY_JAVASCRIPT;
        if (isUserGesture) {
            initiator = XWalkUIClientInternal.InitiateByInternal.BY_USER_GESTURE;
        }
        return this.mXWalkUIClient.onCreateWindowRequested(this.mXWalkView, initiator, new ValueCallback<XWalkViewInternal>() {
            public void onReceiveValue(XWalkViewInternal newXWalkView) {
                XWalkContentsClientBridge.this.mUiThreadHandler.obtainMessage(100, newXWalkView).sendToTarget();
            }
        });
    }

    public void onRequestFocus() {
        if (isOwnerActivityRunning()) {
            this.mXWalkUIClient.onRequestFocus(this.mXWalkView);
        }
    }

    public void onCloseWindow() {
        if (isOwnerActivityRunning()) {
            this.mXWalkUIClient.onJavascriptCloseWindow(this.mXWalkView);
        }
    }

    public void onReceivedIcon(Bitmap bitmap) {
        if (!(this.mXWalkWebChromeClient == null || this.mXWalkView == null)) {
            this.mXWalkWebChromeClient.onReceivedIcon(this.mXWalkView, bitmap);
        }
        this.mFavicon = bitmap;
    }

    public void onShowCustomView(View view, XWalkWebChromeClient.CustomViewCallback callback) {
        if (this.mXWalkWebChromeClient != null && isOwnerActivityRunning()) {
            this.mXWalkWebChromeClient.onShowCustomView(view, callback);
        }
    }

    public void onShowCustomView(View view, int requestedOrientation, XWalkWebChromeClient.CustomViewCallback callback) {
        if (this.mXWalkWebChromeClient != null && isOwnerActivityRunning()) {
            this.mXWalkWebChromeClient.onShowCustomView(view, requestedOrientation, callback);
        }
    }

    public void onHideCustomView() {
        if (this.mXWalkWebChromeClient != null && isOwnerActivityRunning()) {
            this.mXWalkWebChromeClient.onHideCustomView();
        }
    }

    public void onScaleChangedScaled(float oldScale, float newScale) {
        if (isOwnerActivityRunning()) {
            this.mXWalkUIClient.onScaleChanged(this.mXWalkView, oldScale, newScale);
        }
    }

    public void didFinishLoad(String url) {
    }

    public void onTitleChanged(String title) {
        if (this.mXWalkUIClient != null && isOwnerActivityRunning()) {
            this.mXWalkUIClient.onReceivedTitle(this.mXWalkView, title);
        }
    }

    public void onToggleFullscreen(boolean enterFullscreen) {
        if (isOwnerActivityRunning()) {
            this.mIsFullscreen = enterFullscreen;
            this.mXWalkUIClient.onFullscreenToggled(this.mXWalkView, enterFullscreen);
        }
    }

    public boolean hasEnteredFullscreen() {
        return this.mIsFullscreen;
    }

    public boolean shouldOpenWithDefaultBrowser(String contentUrl) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setData(Uri.parse(contentUrl));
        try {
            this.mXWalkView.getActivity().startActivity(intent);
            return true;
        } catch (ActivityNotFoundException e) {
            Log.w(TAG, "Activity not found for Intent:");
            return false;
        }
    }

    public boolean shouldOverrideRunFileChooser(final int processId, final int renderId, final int modeFlags, String acceptTypes, boolean capture) {
        boolean z = true;
        if (!isOwnerActivityRunning()) {
            return false;
        }
        AnonymousClass1UriCallback uploadFile = new AnonymousClass1UriCallback() {
            boolean completed = false;

            public void onReceiveValue(Uri value) {
                String result;
                String displayName;
                if (this.completed) {
                    throw new IllegalStateException("Duplicate openFileChooser result");
                } else if (value != null || this.syncCallFinished) {
                    this.completed = true;
                    if (value == null) {
                        XWalkContentsClientBridge.this.nativeOnFilesNotSelected(XWalkContentsClientBridge.this.mNativeContentsClientBridge, processId, renderId, modeFlags);
                        return;
                    }
                    if (AndroidProtocolHandler.FILE_SCHEME.equals(value.getScheme())) {
                        result = value.getSchemeSpecificPart();
                        displayName = value.getLastPathSegment();
                    } else if ("content".equals(value.getScheme())) {
                        result = value.toString();
                        displayName = resolveFileName(value, XWalkContentsClientBridge.this.mXWalkView.getActivity().getContentResolver());
                    } else {
                        result = value.getPath();
                        displayName = value.getLastPathSegment();
                    }
                    if (displayName == null || displayName.isEmpty()) {
                        displayName = result;
                    }
                    XWalkContentsClientBridge.this.nativeOnFilesSelected(XWalkContentsClientBridge.this.mNativeContentsClientBridge, processId, renderId, modeFlags, result, displayName);
                } else {
                    this.syncNullReceived = true;
                }
            }
        };
        this.mXWalkUIClient.openFileChooser(this.mXWalkView, uploadFile, acceptTypes, Boolean.toString(capture));
        uploadFile.syncCallFinished = true;
        if (uploadFile.syncNullReceived) {
            return this.mXWalkView.showFileChooser(uploadFile, acceptTypes, Boolean.toString(capture));
        }
        if (uploadFile.syncNullReceived) {
            z = false;
        }
        return z;
    }

    public ContentVideoViewClient getContentVideoViewClient() {
        return new XWalkContentVideoViewClient(this, this.mXWalkView.getActivity(), this.mXWalkView);
    }

    @CalledByNative
    private void setNativeContentsClientBridge(long nativeContentsClientBridge) {
        this.mNativeContentsClientBridge = nativeContentsClientBridge;
    }

    @CalledByNative
    private boolean allowCertificateError(int certError, byte[] derBytes, String url, final int id) {
        SslCertificate cert = SslUtil.getCertificateFromDerBytes(derBytes);
        if (cert == null) {
            return false;
        }
        onReceivedSslError(new ValueCallback<Boolean>() {
            public void onReceiveValue(Boolean value) {
                XWalkContentsClientBridge.this.proceedSslError(value.booleanValue(), id);
            }
        }, SslUtil.sslErrorFromNetErrorCode(certError, cert, url));
        return true;
    }

    /* access modifiers changed from: private */
    public void proceedSslError(boolean proceed, int id) {
        if (this.mNativeContentsClientBridge != 0) {
            nativeProceedSslError(this.mNativeContentsClientBridge, proceed, id);
        }
    }

    @CalledByNative
    private void handleJsAlert(String url, String message, int id) {
        if (isOwnerActivityRunning()) {
            this.mXWalkUIClient.onJavascriptModalDialog(this.mXWalkView, XWalkUIClientInternal.JavascriptMessageTypeInternal.JAVASCRIPT_ALERT, url, message, "", new XWalkJavascriptResultHandlerInternal(this, id));
        }
    }

    @CalledByNative
    private void handleJsConfirm(String url, String message, int id) {
        if (isOwnerActivityRunning()) {
            this.mXWalkUIClient.onJavascriptModalDialog(this.mXWalkView, XWalkUIClientInternal.JavascriptMessageTypeInternal.JAVASCRIPT_CONFIRM, url, message, "", new XWalkJavascriptResultHandlerInternal(this, id));
        }
    }

    @CalledByNative
    private void handleJsPrompt(String url, String message, String defaultValue, int id) {
        if (isOwnerActivityRunning()) {
            this.mXWalkUIClient.onJavascriptModalDialog(this.mXWalkView, XWalkUIClientInternal.JavascriptMessageTypeInternal.JAVASCRIPT_PROMPT, url, message, defaultValue, new XWalkJavascriptResultHandlerInternal(this, id));
        }
    }

    @CalledByNative
    private void handleJsBeforeUnload(String url, String message, int id) {
        if (isOwnerActivityRunning()) {
            this.mXWalkUIClient.onJavascriptModalDialog(this.mXWalkView, XWalkUIClientInternal.JavascriptMessageTypeInternal.JAVASCRIPT_BEFOREUNLOAD, url, message, "", new XWalkJavascriptResultHandlerInternal(this, id));
        }
    }

    @CalledByNative
    private void showNotification(String title, String message, String replaceId, Bitmap icon, int notificationId) {
        this.mNotificationService.showNotification(title, message, replaceId, icon, notificationId);
    }

    @CalledByNative
    private void cancelNotification(int notificationId) {
        this.mNotificationService.cancelNotification(notificationId);
    }

    /* access modifiers changed from: package-private */
    public void confirmJsResult(int id, String prompt) {
        if (this.mNativeContentsClientBridge != 0) {
            nativeConfirmJsResult(this.mNativeContentsClientBridge, id, prompt);
        }
    }

    /* access modifiers changed from: package-private */
    public void cancelJsResult(int id) {
        if (this.mNativeContentsClientBridge != 0) {
            nativeCancelJsResult(this.mNativeContentsClientBridge, id);
        }
    }

    /* access modifiers changed from: package-private */
    public void exitFullscreen(long nativeWebContents) {
        if (this.mNativeContentsClientBridge != 0) {
            nativeExitFullscreen(this.mNativeContentsClientBridge, nativeWebContents);
        }
    }

    public void notificationDisplayed(int id) {
        if (this.mNativeContentsClientBridge != 0) {
            nativeNotificationDisplayed(this.mNativeContentsClientBridge, id);
        }
    }

    public void notificationClicked(int id) {
        if (this.mNativeContentsClientBridge != 0) {
            nativeNotificationClicked(this.mNativeContentsClientBridge, id);
        }
    }

    public void notificationClosed(int id, boolean byUser) {
        if (this.mNativeContentsClientBridge != 0) {
            nativeNotificationClosed(this.mNativeContentsClientBridge, id, byUser);
        }
    }

    /* access modifiers changed from: package-private */
    public void setDownloadListener(DownloadListener listener) {
        this.mDownloadListener = listener;
    }

    public void requestHttpGetDownload(DownloadInfo downloadInfo) {
        if (this.mDownloadListener != null) {
            this.mDownloadListener.onDownloadStart(downloadInfo.getUrl(), downloadInfo.getUserAgent(), downloadInfo.getContentDisposition(), downloadInfo.getMimeType(), downloadInfo.getContentLength());
        }
    }

    public void onDownloadStarted(String filename, String mimeType) {
    }

    public void onDangerousDownload(String filename, int downloadId) {
    }

    @CalledByNative
    public void onWebLayoutPageScaleFactorChanged(float pageScaleFactor) {
        if (this.mPageScaleFactor != pageScaleFactor) {
            float oldPageScaleFactor = this.mPageScaleFactor;
            this.mPageScaleFactor = pageScaleFactor;
            onScaleChanged(oldPageScaleFactor, this.mPageScaleFactor);
        }
    }

    @CalledByNative
    public void onIconAvailable(String url) {
        this.mXWalkUIClient.onIconAvailable(this.mXWalkView, url, this.mUiThreadHandler.obtainMessage(101, url));
    }

    @CalledByNative
    public void onReceivedIcon(String url, Bitmap icon) {
        this.mXWalkUIClient.onReceivedIcon(this.mXWalkView, url, icon);
    }
}
