package com.google.android.gms.location;

import android.os.SystemClock;
import com.google.android.gms.internal.ji;

public interface Geofence {
    public static final int GEOFENCE_TRANSITION_DWELL = 4;
    public static final int GEOFENCE_TRANSITION_ENTER = 1;
    public static final int GEOFENCE_TRANSITION_EXIT = 2;
    public static final long NEVER_EXPIRE = -1;

    public static final class Builder {
        private String Oy = null;
        private int UX = 0;
        private long UY = Long.MIN_VALUE;
        private short UZ = -1;
        private double Va;
        private double Vb;
        private float Vc;
        private int Vd = 0;
        private int Ve = -1;

        public Geofence build() {
            if (this.Oy == null) {
                throw new IllegalArgumentException("Request ID not set.");
            } else if (this.UX == 0) {
                throw new IllegalArgumentException("Transitions types not set.");
            } else if ((this.UX & 4) != 0 && this.Ve < 0) {
                throw new IllegalArgumentException("Non-negative loitering delay needs to be set when transition types include GEOFENCE_TRANSITION_DWELLING.");
            } else if (this.UY == Long.MIN_VALUE) {
                throw new IllegalArgumentException("Expiration not set.");
            } else if (this.UZ == -1) {
                throw new IllegalArgumentException("Geofence region not set.");
            } else if (this.Vd >= 0) {
                return new ji(this.Oy, this.UX, 1, this.Va, this.Vb, this.Vc, this.UY, this.Vd, this.Ve);
            } else {
                throw new IllegalArgumentException("Notification responsiveness should be nonnegative.");
            }
        }

        public Builder setCircularRegion(double latitude, double longitude, float radius) {
            this.UZ = 1;
            this.Va = latitude;
            this.Vb = longitude;
            this.Vc = radius;
            return this;
        }

        public Builder setExpirationDuration(long durationMillis) {
            if (durationMillis < 0) {
                this.UY = -1;
            } else {
                this.UY = SystemClock.elapsedRealtime() + durationMillis;
            }
            return this;
        }

        public Builder setLoiteringDelay(int loiteringDelayMs) {
            this.Ve = loiteringDelayMs;
            return this;
        }

        public Builder setNotificationResponsiveness(int notificationResponsivenessMs) {
            this.Vd = notificationResponsivenessMs;
            return this;
        }

        public Builder setRequestId(String requestId) {
            this.Oy = requestId;
            return this;
        }

        public Builder setTransitionTypes(int transitionTypes) {
            this.UX = transitionTypes;
            return this;
        }
    }

    String getRequestId();
}
