package org.chromium.base;

import android.app.Application;

public class BaseChromiumApplication extends Application {
    public void onCreate() {
        super.onCreate();
        ApplicationStatusManager.init(this);
    }
}
