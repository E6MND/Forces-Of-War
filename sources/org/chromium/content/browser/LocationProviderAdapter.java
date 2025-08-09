package org.chromium.content.browser;

import android.content.Context;
import java.util.concurrent.FutureTask;
import org.chromium.base.CalledByNative;
import org.chromium.base.ThreadUtils;
import org.chromium.base.VisibleForTesting;
import org.chromium.content.browser.LocationProviderFactory;

@VisibleForTesting
public class LocationProviderAdapter {
    static final /* synthetic */ boolean $assertionsDisabled = (!LocationProviderAdapter.class.desiredAssertionStatus());
    /* access modifiers changed from: private */
    public LocationProviderFactory.LocationProvider mImpl;

    private static native void nativeNewErrorAvailable(String str);

    private static native void nativeNewLocationAvailable(double d, double d2, double d3, boolean z, double d4, boolean z2, double d5, boolean z3, double d6, boolean z4, double d7);

    private LocationProviderAdapter(Context context) {
        this.mImpl = LocationProviderFactory.get(context);
    }

    @CalledByNative
    static LocationProviderAdapter create(Context context) {
        return new LocationProviderAdapter(context);
    }

    @CalledByNative
    public boolean start(final boolean gpsEnabled) {
        ThreadUtils.runOnUiThread(new FutureTask<>(new Runnable() {
            public void run() {
                LocationProviderAdapter.this.mImpl.start(gpsEnabled);
            }
        }, (Object) null));
        return true;
    }

    @CalledByNative
    public void stop() {
        ThreadUtils.runOnUiThread(new FutureTask<>(new Runnable() {
            public void run() {
                LocationProviderAdapter.this.mImpl.stop();
            }
        }, (Object) null));
    }

    public boolean isRunning() {
        if ($assertionsDisabled || ThreadUtils.runningOnUiThread()) {
            return this.mImpl.isRunning();
        }
        throw new AssertionError();
    }

    public static void newLocationAvailable(double latitude, double longitude, double timestamp, boolean hasAltitude, double altitude, boolean hasAccuracy, double accuracy, boolean hasHeading, double heading, boolean hasSpeed, double speed) {
        nativeNewLocationAvailable(latitude, longitude, timestamp, hasAltitude, altitude, hasAccuracy, accuracy, hasHeading, heading, hasSpeed, speed);
    }

    public static void newErrorAvailable(String message) {
        nativeNewErrorAvailable(message);
    }
}
