package org.chromium.device.battery;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.util.Log;
import org.chromium.base.CalledByNative;
import org.chromium.base.JNINamespace;
import org.chromium.base.VisibleForTesting;
import org.xwalk.core.internal.extension.api.messaging.MessagingSmsConsts;

@JNINamespace("device")
class BatteryStatusManager {
    private static final String TAG = "BatteryStatusManager";
    private final Context mAppContext;
    private boolean mEnabled = false;
    private final IntentFilter mFilter = new IntentFilter("android.intent.action.BATTERY_CHANGED");
    private long mNativePtr;
    private final Object mNativePtrLock = new Object();
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            BatteryStatusManager.this.onReceive(intent);
        }
    };

    private native void nativeGotBatteryStatus(long j, boolean z, double d, double d2, double d3);

    protected BatteryStatusManager(Context context) {
        this.mAppContext = context.getApplicationContext();
    }

    @CalledByNative
    static BatteryStatusManager getInstance(Context appContext) {
        return new BatteryStatusManager(appContext);
    }

    /* access modifiers changed from: package-private */
    @CalledByNative
    public boolean start(long nativePtr) {
        synchronized (this.mNativePtrLock) {
            if (!this.mEnabled && this.mAppContext.registerReceiver(this.mReceiver, this.mFilter) != null) {
                this.mNativePtr = nativePtr;
                this.mEnabled = true;
            }
        }
        return this.mEnabled;
    }

    /* access modifiers changed from: package-private */
    @CalledByNative
    public void stop() {
        synchronized (this.mNativePtrLock) {
            if (this.mEnabled) {
                this.mAppContext.unregisterReceiver(this.mReceiver);
                this.mNativePtr = 0;
                this.mEnabled = false;
            }
        }
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public void onReceive(Intent intent) {
        if (!intent.getAction().equals("android.intent.action.BATTERY_CHANGED")) {
            Log.e(TAG, "Unexpected intent.");
            return;
        }
        boolean present = ignoreBatteryPresentState() ? true : intent.getBooleanExtra("present", false);
        int pluggedStatus = intent.getIntExtra("plugged", -1);
        if (!present || pluggedStatus == -1) {
            gotBatteryStatus(true, 0.0d, Double.POSITIVE_INFINITY, 1.0d);
            return;
        }
        boolean charging = pluggedStatus != 0;
        boolean batteryFull = intent.getIntExtra(MessagingSmsConsts.STATUS, -1) == 5;
        double level = ((double) intent.getIntExtra("level", -1)) / ((double) intent.getIntExtra("scale", -1));
        if (level < 0.0d || level > 1.0d) {
            level = 1.0d;
        }
        gotBatteryStatus(charging, charging & batteryFull ? 0.0d : Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, level);
    }

    /* access modifiers changed from: protected */
    public boolean ignoreBatteryPresentState() {
        return Build.MODEL.equals("Galaxy Nexus");
    }

    /* access modifiers changed from: protected */
    public void gotBatteryStatus(boolean charging, double chargingTime, double dischargingTime, double level) {
        synchronized (this.mNativePtrLock) {
            if (this.mNativePtr != 0) {
                nativeGotBatteryStatus(this.mNativePtr, charging, chargingTime, dischargingTime, level);
            }
        }
    }
}
