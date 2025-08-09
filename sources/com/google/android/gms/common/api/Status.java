package com.google.android.gms.common.api;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Parcel;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.hl;

public final class Status implements Result, SafeParcelable {
    public static final StatusCreator CREATOR = new StatusCreator();
    public static final Status Ek = new Status(0);
    public static final Status El = new Status(14);
    public static final Status Em = new Status(8);
    public static final Status En = new Status(15);
    public static final Status Eo = new Status(16);
    private final int CQ;
    private final String Ep;
    private final PendingIntent mPendingIntent;
    private final int xJ;

    public Status(int statusCode) {
        this(1, statusCode, (String) null, (PendingIntent) null);
    }

    Status(int versionCode, int statusCode, String statusMessage, PendingIntent pendingIntent) {
        this.xJ = versionCode;
        this.CQ = statusCode;
        this.Ep = statusMessage;
        this.mPendingIntent = pendingIntent;
    }

    public Status(int statusCode, String statusMessage, PendingIntent pendingIntent) {
        this(1, statusCode, statusMessage, pendingIntent);
    }

    private String es() {
        return this.Ep != null ? this.Ep : CommonStatusCodes.getStatusCodeString(this.CQ);
    }

    public int describeContents() {
        return 0;
    }

    /* access modifiers changed from: package-private */
    public PendingIntent eL() {
        return this.mPendingIntent;
    }

    @Deprecated
    public ConnectionResult eM() {
        return new ConnectionResult(this.CQ, this.mPendingIntent);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Status)) {
            return false;
        }
        Status status = (Status) obj;
        return this.xJ == status.xJ && this.CQ == status.CQ && hl.equal(this.Ep, status.Ep) && hl.equal(this.mPendingIntent, status.mPendingIntent);
    }

    public PendingIntent getResolution() {
        return this.mPendingIntent;
    }

    public Status getStatus() {
        return this;
    }

    public int getStatusCode() {
        return this.CQ;
    }

    public String getStatusMessage() {
        return this.Ep;
    }

    /* access modifiers changed from: package-private */
    public int getVersionCode() {
        return this.xJ;
    }

    public boolean hasResolution() {
        return this.mPendingIntent != null;
    }

    public int hashCode() {
        return hl.hashCode(Integer.valueOf(this.xJ), Integer.valueOf(this.CQ), this.Ep, this.mPendingIntent);
    }

    public boolean isCanceled() {
        return this.CQ == 16;
    }

    public boolean isInterrupted() {
        return this.CQ == 14;
    }

    public boolean isSuccess() {
        return this.CQ <= 0;
    }

    public void startResolutionForResult(Activity activity, int requestCode) throws IntentSender.SendIntentException {
        if (hasResolution()) {
            activity.startIntentSenderForResult(this.mPendingIntent.getIntentSender(), requestCode, (Intent) null, 0, 0, 0);
        }
    }

    public String toString() {
        return hl.e(this).a("statusCode", es()).a("resolution", this.mPendingIntent).toString();
    }

    public void writeToParcel(Parcel out, int flags) {
        StatusCreator.a(this, out, flags);
    }
}
