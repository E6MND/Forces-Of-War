package org.chromium.content.browser;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import org.chromium.base.CalledByNative;
import org.chromium.base.JNINamespace;

@JNINamespace("content")
class TimeZoneMonitor {
    private static final String TAG = "TimeZoneMonitor";
    private final Context mAppContext;
    private final BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (!intent.getAction().equals("android.intent.action.TIMEZONE_CHANGED")) {
                Log.e(TimeZoneMonitor.TAG, "unexpected intent");
            } else {
                TimeZoneMonitor.this.nativeTimeZoneChangedFromJava(TimeZoneMonitor.this.mNativePtr);
            }
        }
    };
    private final IntentFilter mFilter = new IntentFilter("android.intent.action.TIMEZONE_CHANGED");
    /* access modifiers changed from: private */
    public long mNativePtr;

    /* access modifiers changed from: private */
    public native void nativeTimeZoneChangedFromJava(long j);

    private TimeZoneMonitor(Context context, long nativePtr) {
        this.mAppContext = context.getApplicationContext();
        this.mNativePtr = nativePtr;
        this.mAppContext.registerReceiver(this.mBroadcastReceiver, this.mFilter);
    }

    @CalledByNative
    static TimeZoneMonitor getInstance(Context context, long nativePtr) {
        return new TimeZoneMonitor(context, nativePtr);
    }

    /* access modifiers changed from: package-private */
    @CalledByNative
    public void stop() {
        this.mAppContext.unregisterReceiver(this.mBroadcastReceiver);
        this.mNativePtr = 0;
    }
}
