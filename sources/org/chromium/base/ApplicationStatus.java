package org.chromium.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.chromium.base.ApplicationStatusManager;

@JNINamespace("base::android")
public class ApplicationStatus {
    static final /* synthetic */ boolean $assertionsDisabled;
    /* access modifiers changed from: private */
    public static Activity sActivity;
    private static final Map<Activity, ActivityInfo> sActivityInfo = new ConcurrentHashMap();
    private static Application sApplication;
    private static final ObserverList<ApplicationStateListener> sApplicationStateListeners = new ObserverList<>();
    private static Integer sCachedApplicationState;
    private static Object sCachedApplicationStateLock = new Object();
    private static final ObserverList<ActivityStateListener> sGeneralActivityStateListeners = new ObserverList<>();
    /* access modifiers changed from: private */
    public static ApplicationStateListener sNativeApplicationStateListener;

    public interface ActivityStateListener {
        void onActivityStateChange(Activity activity, int i);
    }

    public interface ApplicationStateListener {
        void onApplicationStateChange(int i);
    }

    /* access modifiers changed from: private */
    public static native void nativeOnApplicationStateChange(int i);

    static {
        boolean z;
        if (!ApplicationStatus.class.desiredAssertionStatus()) {
            z = true;
        } else {
            z = false;
        }
        $assertionsDisabled = z;
    }

    private static class ActivityInfo {
        private ObserverList<ActivityStateListener> mListeners;
        private int mStatus;

        private ActivityInfo() {
            this.mStatus = 6;
            this.mListeners = new ObserverList<>();
        }

        public int getStatus() {
            return this.mStatus;
        }

        public void setStatus(int status) {
            this.mStatus = status;
        }

        public ObserverList<ActivityStateListener> getListeners() {
            return this.mListeners;
        }
    }

    private ApplicationStatus() {
    }

    public static void initialize(Application app) {
        sApplication = app;
        ApplicationStatusManager.registerWindowFocusChangedListener(new ApplicationStatusManager.WindowFocusChangedListener() {
            public void onWindowFocusChanged(Activity activity, boolean hasFocus) {
                int state;
                if (hasFocus && activity != ApplicationStatus.sActivity && (state = ApplicationStatus.getStateForActivity(activity)) != 6 && state != 5) {
                    Activity unused = ApplicationStatus.sActivity = activity;
                }
            }
        });
        sApplication.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                ApplicationStatus.onStateChange(activity, 1);
            }

            public void onActivityDestroyed(Activity activity) {
                ApplicationStatus.onStateChange(activity, 6);
            }

            public void onActivityPaused(Activity activity) {
                ApplicationStatus.onStateChange(activity, 4);
            }

            public void onActivityResumed(Activity activity) {
                ApplicationStatus.onStateChange(activity, 3);
            }

            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            }

            public void onActivityStarted(Activity activity) {
                ApplicationStatus.onStateChange(activity, 2);
            }

            public void onActivityStopped(Activity activity) {
                ApplicationStatus.onStateChange(activity, 5);
            }
        });
    }

    /* access modifiers changed from: private */
    public static void onStateChange(Activity activity, int newState) {
        if (activity == null) {
            throw new IllegalArgumentException("null activity is not supported");
        }
        if (sActivity == null || newState == 1 || newState == 3 || newState == 2) {
            sActivity = activity;
        }
        int oldApplicationState = getStateForApplication();
        if (newState == 1) {
            if ($assertionsDisabled || !sActivityInfo.containsKey(activity)) {
                sActivityInfo.put(activity, new ActivityInfo());
            } else {
                throw new AssertionError();
            }
        }
        synchronized (sCachedApplicationStateLock) {
            sCachedApplicationState = null;
        }
        ActivityInfo info = sActivityInfo.get(activity);
        if (info != null) {
            info.setStatus(newState);
            Iterator i$ = info.getListeners().iterator();
            while (i$.hasNext()) {
                i$.next().onActivityStateChange(activity, newState);
            }
            Iterator i$2 = sGeneralActivityStateListeners.iterator();
            while (i$2.hasNext()) {
                i$2.next().onActivityStateChange(activity, newState);
            }
            int applicationState = getStateForApplication();
            if (applicationState != oldApplicationState) {
                Iterator i$3 = sApplicationStateListeners.iterator();
                while (i$3.hasNext()) {
                    i$3.next().onApplicationStateChange(applicationState);
                }
            }
            if (newState == 6) {
                sActivityInfo.remove(activity);
                if (activity == sActivity) {
                    sActivity = null;
                }
            }
        }
    }

    @VisibleForTesting
    public static void onStateChangeForTesting(Activity activity, int newState) {
        onStateChange(activity, newState);
    }

    public static Activity getLastTrackedFocusedActivity() {
        return sActivity;
    }

    public static List<WeakReference<Activity>> getRunningActivities() {
        List<WeakReference<Activity>> activities = new ArrayList<>();
        for (Activity activity : sActivityInfo.keySet()) {
            activities.add(new WeakReference(activity));
        }
        return activities;
    }

    public static Context getApplicationContext() {
        if (sApplication != null) {
            return sApplication.getApplicationContext();
        }
        return null;
    }

    public static int getStateForActivity(Activity activity) {
        ActivityInfo info = sActivityInfo.get(activity);
        if (info != null) {
            return info.getStatus();
        }
        return 6;
    }

    public static int getStateForApplication() {
        synchronized (sCachedApplicationStateLock) {
            if (sCachedApplicationState == null) {
                sCachedApplicationState = Integer.valueOf(determineApplicationState());
            }
        }
        return sCachedApplicationState.intValue();
    }

    public static boolean hasVisibleActivities() {
        int state = getStateForApplication();
        if (state == 1 || state == 2) {
            return true;
        }
        return false;
    }

    public static boolean isEveryActivityDestroyed() {
        return sActivityInfo.isEmpty();
    }

    public static void registerStateListenerForAllActivities(ActivityStateListener listener) {
        sGeneralActivityStateListeners.addObserver(listener);
    }

    public static void registerStateListenerForActivity(ActivityStateListener listener, Activity activity) {
        if ($assertionsDisabled || activity != null) {
            ActivityInfo info = sActivityInfo.get(activity);
            if ($assertionsDisabled || !(info == null || info.getStatus() == 6)) {
                info.getListeners().addObserver(listener);
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static void unregisterActivityStateListener(ActivityStateListener listener) {
        sGeneralActivityStateListeners.removeObserver(listener);
        for (ActivityInfo info : sActivityInfo.values()) {
            info.getListeners().removeObserver(listener);
        }
    }

    public static void registerApplicationStateListener(ApplicationStateListener listener) {
        sApplicationStateListeners.addObserver(listener);
    }

    public static void unregisterApplicationStateListener(ApplicationStateListener listener) {
        sApplicationStateListeners.removeObserver(listener);
    }

    public static void informActivityStarted(Activity activity) {
        onStateChange(activity, 1);
        onStateChange(activity, 2);
        onStateChange(activity, 3);
    }

    @CalledByNative
    private static void registerThreadSafeNativeApplicationStateListener() {
        ThreadUtils.runOnUiThread((Runnable) new Runnable() {
            public void run() {
                if (ApplicationStatus.sNativeApplicationStateListener == null) {
                    ApplicationStateListener unused = ApplicationStatus.sNativeApplicationStateListener = new ApplicationStateListener() {
                        public void onApplicationStateChange(int newState) {
                            ApplicationStatus.nativeOnApplicationStateChange(newState);
                        }
                    };
                    ApplicationStatus.registerApplicationStateListener(ApplicationStatus.sNativeApplicationStateListener);
                }
            }
        });
    }

    private static int determineApplicationState() {
        boolean hasPausedActivity = false;
        boolean hasStoppedActivity = false;
        for (ActivityInfo info : sActivityInfo.values()) {
            int state = info.getStatus();
            if (state != 4 && state != 5 && state != 6) {
                return 1;
            }
            if (state == 4) {
                hasPausedActivity = true;
            } else if (state == 5) {
                hasStoppedActivity = true;
            }
        }
        if (hasPausedActivity) {
            return 2;
        }
        if (hasStoppedActivity) {
            return 3;
        }
        return 4;
    }
}
