package org.chromium.content.browser;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import java.util.List;
import org.chromium.base.ThreadUtils;
import org.chromium.base.VisibleForTesting;

public class LocationProviderFactory {
    static final /* synthetic */ boolean $assertionsDisabled = (!LocationProviderFactory.class.desiredAssertionStatus());
    private static LocationProvider sProviderImpl;

    public interface LocationProvider {
        boolean isRunning();

        void start(boolean z);

        void stop();
    }

    private LocationProviderFactory() {
    }

    @VisibleForTesting
    public static void setLocationProviderImpl(LocationProvider provider) {
        if ($assertionsDisabled || sProviderImpl == null) {
            sProviderImpl = provider;
            return;
        }
        throw new AssertionError();
    }

    public static LocationProvider get(Context context) {
        if (sProviderImpl == null) {
            sProviderImpl = new LocationProviderImpl(context);
        }
        return sProviderImpl;
    }

    private static class LocationProviderImpl implements LocationListener, LocationProvider {
        static final /* synthetic */ boolean $assertionsDisabled = (!LocationProviderFactory.class.desiredAssertionStatus());
        private static final String TAG = "LocationProvider";
        private Context mContext;
        private boolean mIsRunning;
        private LocationManager mLocationManager;

        LocationProviderImpl(Context context) {
            this.mContext = context;
        }

        public void start(boolean gpsEnabled) {
            unregisterFromLocationUpdates();
            registerForLocationUpdates(gpsEnabled);
        }

        public void stop() {
            unregisterFromLocationUpdates();
        }

        public boolean isRunning() {
            return this.mIsRunning;
        }

        public void onLocationChanged(Location location) {
            if (this.mIsRunning) {
                updateNewLocation(location);
            }
        }

        /* access modifiers changed from: private */
        public void updateNewLocation(Location location) {
            LocationProviderAdapter.newLocationAvailable(location.getLatitude(), location.getLongitude(), ((double) location.getTime()) / 1000.0d, location.hasAltitude(), location.getAltitude(), location.hasAccuracy(), (double) location.getAccuracy(), location.hasBearing(), (double) location.getBearing(), location.hasSpeed(), (double) location.getSpeed());
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
        }

        public void onProviderEnabled(String provider) {
        }

        public void onProviderDisabled(String provider) {
        }

        private void ensureLocationManagerCreated() {
            if (this.mLocationManager == null) {
                this.mLocationManager = (LocationManager) this.mContext.getSystemService("location");
                if (this.mLocationManager == null) {
                    Log.e(TAG, "Could not get location manager.");
                }
            }
        }

        private void registerForLocationUpdates(boolean isGpsEnabled) {
            ensureLocationManagerCreated();
            if (!usePassiveOneShotLocation()) {
                if ($assertionsDisabled || !this.mIsRunning) {
                    this.mIsRunning = true;
                    try {
                        Criteria criteria = new Criteria();
                        if (isGpsEnabled) {
                            criteria.setAccuracy(1);
                        }
                        this.mLocationManager.requestLocationUpdates(0, 0.0f, criteria, this, ThreadUtils.getUiThreadLooper());
                    } catch (SecurityException e) {
                        Log.e(TAG, "Caught security exception registering for location updates from system. This should only happen in DumpRenderTree.");
                    } catch (IllegalArgumentException e2) {
                        Log.e(TAG, "Caught IllegalArgumentException registering for location updates.");
                    }
                } else {
                    throw new AssertionError();
                }
            }
        }

        private void unregisterFromLocationUpdates() {
            if (this.mIsRunning) {
                this.mIsRunning = false;
                this.mLocationManager.removeUpdates(this);
            }
        }

        private boolean usePassiveOneShotLocation() {
            if (!isOnlyPassiveLocationProviderEnabled()) {
                return false;
            }
            final Location location = this.mLocationManager.getLastKnownLocation("passive");
            if (location != null) {
                ThreadUtils.runOnUiThread((Runnable) new Runnable() {
                    public void run() {
                        LocationProviderImpl.this.updateNewLocation(location);
                    }
                });
            }
            return true;
        }

        private boolean isOnlyPassiveLocationProviderEnabled() {
            List<String> providers = this.mLocationManager.getProviders(true);
            return providers != null && providers.size() == 1 && providers.get(0).equals("passive");
        }
    }
}
