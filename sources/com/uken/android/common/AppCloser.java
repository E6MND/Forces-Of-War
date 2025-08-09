package com.uken.android.common;

import android.app.Activity;
import android.webkit.JavascriptInterface;

public class AppCloser {
    private Activity activity;

    public AppCloser(Activity activity2) {
        this.activity = activity2;
    }

    @JavascriptInterface
    public void exit() {
        this.activity.finish();
    }
}
