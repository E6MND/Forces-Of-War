package org.chromium.content.browser;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.widget.FrameLayout;

public class ActivityContentVideoViewClient implements ContentVideoViewClient {
    private final Activity mActivity;
    private View mView;

    public ActivityContentVideoViewClient(Activity activity) {
        this.mActivity = activity;
    }

    public void enterFullscreenVideo(View view) {
        FrameLayout decor = (FrameLayout) this.mActivity.getWindow().getDecorView();
        decor.addView(view, 0, new FrameLayout.LayoutParams(-1, -1, 17));
        setSystemUiVisibility(decor, true);
        this.mView = view;
    }

    public void exitFullscreenVideo() {
        FrameLayout decor = (FrameLayout) this.mActivity.getWindow().getDecorView();
        decor.removeView(this.mView);
        setSystemUiVisibility(decor, false);
        this.mView = null;
    }

    public View getVideoLoadingProgressView() {
        return null;
    }

    @SuppressLint({"InlinedApi"})
    private void setSystemUiVisibility(View view, boolean enterFullscreen) {
        int systemUiVisibility;
        if (enterFullscreen) {
            this.mActivity.getWindow().setFlags(1024, 1024);
        } else {
            this.mActivity.getWindow().clearFlags(1024);
        }
        if (Build.VERSION.SDK_INT >= 19) {
            int systemUiVisibility2 = view.getSystemUiVisibility();
            if (enterFullscreen) {
                systemUiVisibility = systemUiVisibility2 | 5638;
            } else {
                systemUiVisibility = systemUiVisibility2 & -5639;
            }
            view.setSystemUiVisibility(systemUiVisibility);
        }
    }
}
