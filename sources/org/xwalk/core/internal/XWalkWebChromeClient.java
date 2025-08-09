package org.xwalk.core.internal;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Message;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.ValueCallback;
import android.webkit.WebStorage;
import android.widget.FrameLayout;
import org.xwalk.core.internal.XWalkGeolocationPermissions;

public class XWalkWebChromeClient {
    private final int INVALID_ORIENTATION = -2;
    private long XWALK_MAX_QUOTA = 104857600;
    private XWalkContentsClient mContentsClient = null;
    private Context mContext;
    private CustomViewCallback mCustomViewCallback;
    private View mCustomXWalkView;
    private int mPreOrientation = -2;
    private XWalkViewInternal mXWalkView;

    public interface CustomViewCallback {
        void onCustomViewHidden();
    }

    public XWalkWebChromeClient(XWalkViewInternal view) {
        this.mContext = view.getContext();
        this.mXWalkView = view;
    }

    /* access modifiers changed from: package-private */
    public void setContentsClient(XWalkContentsClient client) {
        this.mContentsClient = client;
    }

    public void onReceivedIcon(XWalkViewInternal view, Bitmap icon) {
    }

    private Activity addContentView(View view, CustomViewCallback callback) {
        Activity activity = this.mXWalkView.getActivity();
        if (this.mCustomXWalkView != null || activity == null) {
            callback.onCustomViewHidden();
            return null;
        }
        this.mCustomXWalkView = view;
        this.mCustomViewCallback = callback;
        if (this.mContentsClient != null) {
            this.mContentsClient.onToggleFullscreen(true);
        }
        ((FrameLayout) activity.getWindow().getDecorView()).addView(this.mCustomXWalkView, 0, new FrameLayout.LayoutParams(-1, -1, 17));
        return activity;
    }

    public void onShowCustomView(View view, CustomViewCallback callback) {
        addContentView(view, callback);
    }

    public void onShowCustomView(View view, int requestedOrientation, CustomViewCallback callback) {
        int orientation;
        Activity activity = addContentView(view, callback);
        if (activity != null && requestedOrientation != (orientation = activity.getResources().getConfiguration().orientation) && requestedOrientation >= -1 && requestedOrientation <= 14) {
            this.mPreOrientation = orientation;
            activity.setRequestedOrientation(requestedOrientation);
        }
    }

    public void onHideCustomView() {
        Activity activity = this.mXWalkView.getActivity();
        if (this.mCustomXWalkView != null && activity != null) {
            if (this.mContentsClient != null) {
                this.mContentsClient.onToggleFullscreen(false);
            }
            ((FrameLayout) activity.getWindow().getDecorView()).removeView(this.mCustomXWalkView);
            this.mCustomViewCallback.onCustomViewHidden();
            if (this.mPreOrientation != -2 && this.mPreOrientation >= -1 && this.mPreOrientation <= 14) {
                activity.setRequestedOrientation(this.mPreOrientation);
                this.mPreOrientation = -2;
            }
            this.mCustomXWalkView = null;
            this.mCustomViewCallback = null;
        }
    }

    public void onExceededDatabaseQuota(String url, String databaseIdentifier, long quota, long estimatedDatabaseSize, long totalQuota, WebStorage.QuotaUpdater quotaUpdater) {
        quotaUpdater.updateQuota(this.XWALK_MAX_QUOTA);
    }

    public void onReachedMaxAppCacheSize(long requiredStorage, long quota, WebStorage.QuotaUpdater quotaUpdater) {
        quotaUpdater.updateQuota(this.XWALK_MAX_QUOTA);
    }

    public void onGeolocationPermissionsShowPrompt(String origin, XWalkGeolocationPermissions.Callback callback) {
        callback.invoke(origin, true, false);
    }

    public void onGeolocationPermissionsHidePrompt() {
    }

    public boolean onJsTimeout() {
        return true;
    }

    @Deprecated
    public void onConsoleMessage(String message, int lineNumber, String sourceID) {
    }

    public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
        onConsoleMessage(consoleMessage.message(), consoleMessage.lineNumber(), consoleMessage.sourceId());
        return false;
    }

    public void getVisitedHistory(ValueCallback<String[]> valueCallback) {
    }

    public void setInstallableWebApp() {
    }

    public void setupAutoFill(Message msg) {
    }
}
