package org.xwalk.core.internal;

import android.app.Activity;
import android.view.View;
import org.chromium.content.browser.ContentVideoView;
import org.chromium.content.browser.ContentVideoViewClient;
import org.xwalk.core.internal.XWalkWebChromeClient;

class XWalkContentVideoViewClient implements ContentVideoViewClient {
    private Activity mActivity;
    private XWalkContentsClient mContentsClient;
    private XWalkViewInternal mView;

    public XWalkContentVideoViewClient(XWalkContentsClient client, Activity activity, XWalkViewInternal view) {
        this.mContentsClient = client;
        this.mActivity = activity;
        this.mView = view;
    }

    public void enterFullscreenVideo(View view) {
        this.mView.setOverlayVideoMode(true);
        this.mContentsClient.onShowCustomView(view, new XWalkWebChromeClient.CustomViewCallback() {
            public void onCustomViewHidden() {
                ContentVideoView contentVideoView = ContentVideoView.getContentVideoView();
                if (contentVideoView != null) {
                    contentVideoView.exitFullscreen(false);
                }
            }
        });
    }

    public void exitFullscreenVideo() {
        this.mView.setOverlayVideoMode(false);
        this.mContentsClient.onHideCustomView();
    }

    public View getVideoLoadingProgressView() {
        return null;
    }
}
