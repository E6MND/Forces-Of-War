package org.chromium.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Looper;
import org.chromium.base.ApplicationStatus;

@JNINamespace("base::android")
public class PowerMonitor implements ApplicationStatus.ApplicationStateListener {
    private static final long SUSPEND_DELAY_MS = 60000;
    private static PowerMonitor sInstance;
    private static final Runnable sSuspendTask = new Runnable() {
        public void run() {
            PowerMonitor.nativeOnMainActivitySuspended();
        }
    };
    private final Handler mHandler;
    private boolean mIsBatteryPower;

    private static native void nativeOnBatteryChargingChanged();

    private static native void nativeOnMainActivityResumed();

    /* access modifiers changed from: private */
    public static native void nativeOnMainActivitySuspended();

    private static class LazyHolder {
        /* access modifiers changed from: private */
        public static final PowerMonitor INSTANCE = new PowerMonitor();

        private LazyHolder() {
        }
    }

    public static void createForTests(Context context) {
        sInstance = LazyHolder.INSTANCE;
    }

    public static void create(Context context) {
        Context context2 = context.getApplicationContext();
        if (sInstance == null) {
            sInstance = LazyHolder.INSTANCE;
            ApplicationStatus.registerApplicationStateListener(sInstance);
            onBatteryChargingChanged(context2.registerReceiver((BroadcastReceiver) null, new IntentFilter("android.intent.action.BATTERY_CHANGED")));
        }
    }

    private PowerMonitor() {
        this.mHandler = new Handler(Looper.getMainLooper());
    }

    public static void onBatteryChargingChanged(Intent intent) {
        boolean z = true;
        if (sInstance != null) {
            int chargePlug = intent.getIntExtra("plugged", -1);
            PowerMonitor powerMonitor = sInstance;
            if (chargePlug == 2 || chargePlug == 1) {
                z = false;
            }
            powerMonitor.mIsBatteryPower = z;
            nativeOnBatteryChargingChanged();
        }
    }

    public void onApplicationStateChange(int newState) {
        if (newState == 1) {
            this.mHandler.removeCallbacks(sSuspendTask);
            nativeOnMainActivityResumed();
        } else if (newState == 2) {
            this.mHandler.postDelayed(sSuspendTask, SUSPEND_DELAY_MS);
        }
    }

    @CalledByNative
    private static boolean isBatteryPower() {
        return sInstance.mIsBatteryPower;
    }
}
