package org.chromium.content.browser;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.util.Log;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.CalledByNative;
import org.chromium.base.JNINamespace;
import org.chromium.base.ThreadUtils;
import org.chromium.content_public.common.ScreenOrientationConstants;
import org.chromium.ui.gfx.DeviceDisplayInfo;

@JNINamespace("content")
public class ScreenOrientationProvider {
    private static final String TAG = "ScreenOrientationProvider";

    private static int getOrientationFromWebScreenOrientations(byte orientation, Activity activity) {
        switch (orientation) {
            case 0:
                return -1;
            case 1:
                return 1;
            case 2:
                return 9;
            case 3:
                return 0;
            case 4:
                return 8;
            case 5:
                return 10;
            case 6:
                return 6;
            case 7:
                return 7;
            case 8:
                DeviceDisplayInfo displayInfo = DeviceDisplayInfo.create(activity);
                int rotation = displayInfo.getRotationDegrees();
                if (rotation == 0 || rotation == 180) {
                    if (displayInfo.getDisplayHeight() >= displayInfo.getDisplayWidth()) {
                        return 1;
                    }
                    return 0;
                } else if (displayInfo.getDisplayHeight() < displayInfo.getDisplayWidth()) {
                    return 1;
                } else {
                    return 0;
                }
            default:
                Log.w(TAG, "Trying to lock to unsupported orientation!");
                return -1;
        }
    }

    @CalledByNative
    static void lockOrientation(byte orientation) {
        lockOrientation(orientation, ApplicationStatus.getLastTrackedFocusedActivity());
    }

    public static void lockOrientation(byte webScreenOrientation, Activity activity) {
        int orientation;
        if (activity != null && (orientation = getOrientationFromWebScreenOrientations(webScreenOrientation, activity)) != -1) {
            activity.setRequestedOrientation(orientation);
        }
    }

    @CalledByNative
    static void unlockOrientation() {
        Activity activity = ApplicationStatus.getLastTrackedFocusedActivity();
        if (activity != null) {
            int defaultOrientation = getOrientationFromWebScreenOrientations((byte) activity.getIntent().getIntExtra(ScreenOrientationConstants.EXTRA_ORIENTATION, 0), activity);
            if (defaultOrientation == -1) {
                try {
                    defaultOrientation = activity.getPackageManager().getActivityInfo(activity.getComponentName(), 128).screenOrientation;
                } catch (PackageManager.NameNotFoundException e) {
                    activity.setRequestedOrientation(defaultOrientation);
                    return;
                } catch (Throwable th) {
                    activity.setRequestedOrientation(defaultOrientation);
                    throw th;
                }
            }
            activity.setRequestedOrientation(defaultOrientation);
        }
    }

    @CalledByNative
    static void startAccurateListening() {
        ThreadUtils.runOnUiThread((Runnable) new Runnable() {
            public void run() {
                ScreenOrientationListener.getInstance().startAccurateListening();
            }
        });
    }

    @CalledByNative
    static void stopAccurateListening() {
        ThreadUtils.runOnUiThread((Runnable) new Runnable() {
            public void run() {
                ScreenOrientationListener.getInstance().stopAccurateListening();
            }
        });
    }

    private ScreenOrientationProvider() {
    }
}
