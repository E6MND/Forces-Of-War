package com.google.android.gms.location;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class DetectedActivity implements SafeParcelable {
    public static final DetectedActivityCreator CREATOR = new DetectedActivityCreator();
    public static final int IN_VEHICLE = 0;
    public static final int ON_BICYCLE = 1;
    public static final int ON_FOOT = 2;
    public static final int RUNNING = 8;
    public static final int STILL = 3;
    public static final int TILTING = 5;
    public static final int UNKNOWN = 4;
    public static final int WALKING = 7;
    int UV;
    int UW;
    private final int xJ;

    public DetectedActivity(int activityType, int confidence) {
        this.xJ = 1;
        this.UV = activityType;
        this.UW = confidence;
    }

    public DetectedActivity(int versionCode, int activityType, int confidence) {
        this.xJ = versionCode;
        this.UV = activityType;
        this.UW = confidence;
    }

    private int cF(int i) {
        if (i > 8) {
            return 4;
        }
        return i;
    }

    public int describeContents() {
        return 0;
    }

    public int getConfidence() {
        return this.UW;
    }

    public int getType() {
        return cF(this.UV);
    }

    public int getVersionCode() {
        return this.xJ;
    }

    public String toString() {
        return "DetectedActivity [type=" + getType() + ", confidence=" + this.UW + "]";
    }

    public void writeToParcel(Parcel out, int flags) {
        DetectedActivityCreator.a(this, out, flags);
    }
}
