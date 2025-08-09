package org.chromium.base;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import java.util.Iterator;

public class ApplicationStatusManager {
    /* access modifiers changed from: private */
    public static ObserverList<WindowFocusChangedListener> sWindowFocusListeners = new ObserverList<>();

    public interface WindowFocusChangedListener {
        void onWindowFocusChanged(Activity activity, boolean z);
    }

    public static void init(Application app) {
        ApplicationStatus.initialize(app);
        app.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            static final /* synthetic */ boolean $assertionsDisabled = (!ApplicationStatusManager.class.desiredAssertionStatus());

            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                ApplicationStatusManager.setWindowFocusChangedCallback(activity);
            }

            public void onActivityDestroyed(Activity activity) {
                if (!$assertionsDisabled && !(activity.getWindow().getCallback() instanceof WindowCallbackWrapper)) {
                    throw new AssertionError();
                }
            }

            public void onActivityPaused(Activity activity) {
                if (!$assertionsDisabled && !(activity.getWindow().getCallback() instanceof WindowCallbackWrapper)) {
                    throw new AssertionError();
                }
            }

            public void onActivityResumed(Activity activity) {
                if (!$assertionsDisabled && !(activity.getWindow().getCallback() instanceof WindowCallbackWrapper)) {
                    throw new AssertionError();
                }
            }

            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
                if (!$assertionsDisabled && !(activity.getWindow().getCallback() instanceof WindowCallbackWrapper)) {
                    throw new AssertionError();
                }
            }

            public void onActivityStarted(Activity activity) {
                if (!$assertionsDisabled && !(activity.getWindow().getCallback() instanceof WindowCallbackWrapper)) {
                    throw new AssertionError();
                }
            }

            public void onActivityStopped(Activity activity) {
                if (!$assertionsDisabled && !(activity.getWindow().getCallback() instanceof WindowCallbackWrapper)) {
                    throw new AssertionError();
                }
            }
        });
    }

    public static void registerWindowFocusChangedListener(WindowFocusChangedListener listener) {
        sWindowFocusListeners.addObserver(listener);
    }

    public static void unregisterWindowFocusChangedListener(WindowFocusChangedListener listener) {
        sWindowFocusListeners.removeObserver(listener);
    }

    public static void informActivityStarted(Activity activity) {
        setWindowFocusChangedCallback(activity);
        ApplicationStatus.informActivityStarted(activity);
    }

    /* access modifiers changed from: private */
    public static void setWindowFocusChangedCallback(final Activity activity) {
        activity.getWindow().setCallback(new WindowCallbackWrapper(activity.getWindow().getCallback()) {
            public void onWindowFocusChanged(boolean hasFocus) {
                super.onWindowFocusChanged(hasFocus);
                Iterator i$ = ApplicationStatusManager.sWindowFocusListeners.iterator();
                while (i$.hasNext()) {
                    ((WindowFocusChangedListener) i$.next()).onWindowFocusChanged(activity, hasFocus);
                }
            }
        });
    }
}
