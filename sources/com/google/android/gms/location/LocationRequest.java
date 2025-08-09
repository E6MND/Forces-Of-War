package com.google.android.gms.location;

import android.os.Parcel;
import android.os.SystemClock;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.hl;

public final class LocationRequest implements SafeParcelable {
    public static final LocationRequestCreator CREATOR = new LocationRequestCreator();
    public static final int PRIORITY_BALANCED_POWER_ACCURACY = 102;
    public static final int PRIORITY_HIGH_ACCURACY = 100;
    public static final int PRIORITY_LOW_POWER = 104;
    public static final int PRIORITY_NO_POWER = 105;
    long UY;
    long Vi;
    long Vj;
    boolean Vk;
    int Vl;
    float Vm;
    int mPriority;
    private final int xJ;

    public LocationRequest() {
        this.xJ = 1;
        this.mPriority = 102;
        this.Vi = 3600000;
        this.Vj = 600000;
        this.Vk = false;
        this.UY = Long.MAX_VALUE;
        this.Vl = Integer.MAX_VALUE;
        this.Vm = 0.0f;
    }

    LocationRequest(int versionCode, int priority, long interval, long fastestInterval, boolean explicitFastestInterval, long expireAt, int numUpdates, float smallestDisplacement) {
        this.xJ = versionCode;
        this.mPriority = priority;
        this.Vi = interval;
        this.Vj = fastestInterval;
        this.Vk = explicitFastestInterval;
        this.UY = expireAt;
        this.Vl = numUpdates;
        this.Vm = smallestDisplacement;
    }

    private static void a(float f) {
        if (f < 0.0f) {
            throw new IllegalArgumentException("invalid displacement: " + f);
        }
    }

    private static void cG(int i) {
        switch (i) {
            case 100:
            case 102:
            case PRIORITY_LOW_POWER /*104*/:
            case PRIORITY_NO_POWER /*105*/:
                return;
            default:
                throw new IllegalArgumentException("invalid quality: " + i);
        }
    }

    public static String cH(int i) {
        switch (i) {
            case 100:
                return "PRIORITY_HIGH_ACCURACY";
            case 102:
                return "PRIORITY_BALANCED_POWER_ACCURACY";
            case PRIORITY_LOW_POWER /*104*/:
                return "PRIORITY_LOW_POWER";
            case PRIORITY_NO_POWER /*105*/:
                return "PRIORITY_NO_POWER";
            default:
                return "???";
        }
    }

    public static LocationRequest create() {
        return new LocationRequest();
    }

    private static void v(long j) {
        if (j < 0) {
            throw new IllegalArgumentException("invalid interval: " + j);
        }
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof LocationRequest)) {
            return false;
        }
        LocationRequest locationRequest = (LocationRequest) object;
        return this.mPriority == locationRequest.mPriority && this.Vi == locationRequest.Vi && this.Vj == locationRequest.Vj && this.Vk == locationRequest.Vk && this.UY == locationRequest.UY && this.Vl == locationRequest.Vl && this.Vm == locationRequest.Vm;
    }

    public long getExpirationTime() {
        return this.UY;
    }

    public long getFastestInterval() {
        return this.Vj;
    }

    public long getInterval() {
        return this.Vi;
    }

    public int getNumUpdates() {
        return this.Vl;
    }

    public int getPriority() {
        return this.mPriority;
    }

    public float getSmallestDisplacement() {
        return this.Vm;
    }

    /* access modifiers changed from: package-private */
    public int getVersionCode() {
        return this.xJ;
    }

    public int hashCode() {
        return hl.hashCode(Integer.valueOf(this.mPriority), Long.valueOf(this.Vi), Long.valueOf(this.Vj), Boolean.valueOf(this.Vk), Long.valueOf(this.UY), Integer.valueOf(this.Vl), Float.valueOf(this.Vm));
    }

    public LocationRequest setExpirationDuration(long millis) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (millis > Long.MAX_VALUE - elapsedRealtime) {
            this.UY = Long.MAX_VALUE;
        } else {
            this.UY = elapsedRealtime + millis;
        }
        if (this.UY < 0) {
            this.UY = 0;
        }
        return this;
    }

    public LocationRequest setExpirationTime(long millis) {
        this.UY = millis;
        if (this.UY < 0) {
            this.UY = 0;
        }
        return this;
    }

    public LocationRequest setFastestInterval(long millis) {
        v(millis);
        this.Vk = true;
        this.Vj = millis;
        return this;
    }

    public LocationRequest setInterval(long millis) {
        v(millis);
        this.Vi = millis;
        if (!this.Vk) {
            this.Vj = (long) (((double) this.Vi) / 6.0d);
        }
        return this;
    }

    public LocationRequest setNumUpdates(int numUpdates) {
        if (numUpdates <= 0) {
            throw new IllegalArgumentException("invalid numUpdates: " + numUpdates);
        }
        this.Vl = numUpdates;
        return this;
    }

    public LocationRequest setPriority(int priority) {
        cG(priority);
        this.mPriority = priority;
        return this;
    }

    public LocationRequest setSmallestDisplacement(float smallestDisplacementMeters) {
        a(smallestDisplacementMeters);
        this.Vm = smallestDisplacementMeters;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Request[").append(cH(this.mPriority));
        if (this.mPriority != 105) {
            sb.append(" requested=");
            sb.append(this.Vi + "ms");
        }
        sb.append(" fastest=");
        sb.append(this.Vj + "ms");
        if (this.UY != Long.MAX_VALUE) {
            sb.append(" expireIn=");
            sb.append((this.UY - SystemClock.elapsedRealtime()) + "ms");
        }
        if (this.Vl != Integer.MAX_VALUE) {
            sb.append(" num=").append(this.Vl);
        }
        sb.append(']');
        return sb.toString();
    }

    public void writeToParcel(Parcel parcel, int flags) {
        LocationRequestCreator.a(this, parcel, flags);
    }
}
