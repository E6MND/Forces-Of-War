package org.xwalk.core.internal.extension.api.presentation;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import java.lang.ref.WeakReference;
import org.xwalk.core.internal.XWalkUIClientInternal;
import org.xwalk.core.internal.XWalkViewInternal;

public class XWalkPresentationContent {
    public final int INVALID_PRESENTATION_ID = -1;
    private WeakReference<Activity> mActivity;
    /* access modifiers changed from: private */
    public XWalkViewInternal mContentView;
    private Context mContext;
    private PresentationDelegate mDelegate;
    /* access modifiers changed from: private */
    public int mPresentationId = -1;

    public interface PresentationDelegate {
        void onContentClosed(XWalkPresentationContent xWalkPresentationContent);

        void onContentLoaded(XWalkPresentationContent xWalkPresentationContent);
    }

    public XWalkPresentationContent(Context context, WeakReference<Activity> activity, PresentationDelegate delegate) {
        this.mContext = context;
        this.mActivity = activity;
        this.mDelegate = delegate;
    }

    public void load(String url) {
        Activity activity = (Activity) this.mActivity.get();
        if (activity != null) {
            if (this.mContentView == null) {
                this.mContentView = new XWalkViewInternal(this.mContext, activity);
                this.mContentView.setUIClient(new XWalkUIClientInternal(this.mContentView) {
                    public void onJavascriptCloseWindow(XWalkViewInternal view) {
                        int unused = XWalkPresentationContent.this.mPresentationId = -1;
                        XWalkPresentationContent.this.onContentClosed();
                    }

                    public void onPageLoadStopped(XWalkViewInternal view, String url, XWalkUIClientInternal.LoadStatusInternal status) {
                        if (status == XWalkUIClientInternal.LoadStatusInternal.FINISHED) {
                            int unused = XWalkPresentationContent.this.mPresentationId = XWalkPresentationContent.this.mContentView.getContentID();
                            XWalkPresentationContent.this.onContentLoaded();
                        }
                    }
                });
            }
            this.mContentView.load(url, (String) null);
        }
    }

    public int getPresentationId() {
        return this.mPresentationId;
    }

    public View getContentView() {
        return this.mContentView;
    }

    public void close() {
        this.mContentView.onDestroy();
        this.mPresentationId = -1;
        this.mContentView = null;
    }

    public void onPause() {
        this.mContentView.pauseTimers();
        this.mContentView.onHide();
    }

    public void onResume() {
        this.mContentView.resumeTimers();
        this.mContentView.onShow();
    }

    /* access modifiers changed from: private */
    public void onContentLoaded() {
        if (this.mDelegate != null) {
            this.mDelegate.onContentLoaded(this);
        }
    }

    /* access modifiers changed from: private */
    public void onContentClosed() {
        if (this.mDelegate != null) {
            this.mDelegate.onContentClosed(this);
        }
    }
}
