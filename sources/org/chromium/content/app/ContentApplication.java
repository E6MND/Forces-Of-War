package org.chromium.content.app;

import android.content.Context;
import android.os.Looper;
import android.os.MessageQueue;
import org.chromium.base.BaseChromiumApplication;
import org.chromium.base.library_loader.LibraryLoader;
import org.chromium.content.browser.TracingControllerAndroid;

public abstract class ContentApplication extends BaseChromiumApplication {
    private TracingControllerAndroid mTracingController;

    public abstract void initCommandLine();

    /* access modifiers changed from: package-private */
    public TracingControllerAndroid getTracingController() {
        if (this.mTracingController == null) {
            this.mTracingController = new TracingControllerAndroid(this);
        }
        return this.mTracingController;
    }

    public void onCreate() {
        super.onCreate();
        Looper.myQueue().addIdleHandler(new MessageQueue.IdleHandler() {
            public boolean queueIdle() {
                if (!LibraryLoader.isInitialized()) {
                    return true;
                }
                try {
                    ContentApplication.this.getTracingController().registerReceiver(ContentApplication.this);
                } catch (SecurityException e) {
                }
                return false;
            }
        });
    }

    public void onTerminate() {
        try {
            getTracingController().unregisterReceiver(this);
            getTracingController().destroy();
        } catch (SecurityException e) {
        }
        super.onTerminate();
    }

    public static void initCommandLine(Context context) {
        ((ContentApplication) context.getApplicationContext()).initCommandLine();
    }
}
