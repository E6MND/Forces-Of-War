package org.xwalk.core.internal;

import android.view.KeyEvent;
import org.chromium.content.browser.ContentVideoView;

class XWalkWebContentsDelegateAdapter extends XWalkWebContentsDelegate {
    private XWalkContentsClient mXWalkContentsClient;

    public XWalkWebContentsDelegateAdapter(XWalkContentsClient client) {
        this.mXWalkContentsClient = client;
    }

    public boolean shouldOpenWithDefaultBrowser(String contentUrl) {
        if (this.mXWalkContentsClient != null) {
            return this.mXWalkContentsClient.shouldOpenWithDefaultBrowser(contentUrl);
        }
        return false;
    }

    public void onLoadProgressChanged(int progress) {
        if (this.mXWalkContentsClient != null) {
            this.mXWalkContentsClient.onProgressChanged(progress);
        }
    }

    public boolean addNewContents(boolean isDialog, boolean isUserGesture) {
        return this.mXWalkContentsClient.onCreateWindow(isDialog, isUserGesture);
    }

    public void closeContents() {
        if (this.mXWalkContentsClient != null) {
            this.mXWalkContentsClient.onCloseWindow();
        }
    }

    public void activateContents() {
        if (this.mXWalkContentsClient != null) {
            this.mXWalkContentsClient.onRequestFocus();
        }
    }

    public void rendererUnresponsive() {
        if (this.mXWalkContentsClient != null) {
            this.mXWalkContentsClient.onRendererUnresponsive();
        }
    }

    public void rendererResponsive() {
        if (this.mXWalkContentsClient != null) {
            this.mXWalkContentsClient.onRendererResponsive();
        }
    }

    public void handleKeyboardEvent(KeyEvent event) {
        if (this.mXWalkContentsClient != null) {
            this.mXWalkContentsClient.onUnhandledKeyEvent(event);
        }
    }

    public void toggleFullscreen(boolean enterFullscreen) {
        ContentVideoView videoView;
        if (!enterFullscreen && (videoView = ContentVideoView.getContentVideoView()) != null) {
            videoView.exitFullscreen(false);
        }
        if (this.mXWalkContentsClient != null) {
            this.mXWalkContentsClient.onToggleFullscreen(enterFullscreen);
        }
    }

    public boolean isFullscreen() {
        if (this.mXWalkContentsClient != null) {
            return this.mXWalkContentsClient.hasEnteredFullscreen();
        }
        return false;
    }

    public boolean shouldOverrideRunFileChooser(int processId, int renderId, int mode, String acceptTypes, boolean capture) {
        if (this.mXWalkContentsClient != null) {
            return this.mXWalkContentsClient.shouldOverrideRunFileChooser(processId, renderId, mode, acceptTypes, capture);
        }
        return false;
    }
}
