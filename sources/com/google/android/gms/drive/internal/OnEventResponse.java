package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.events.ChangeEvent;
import com.google.android.gms.drive.events.FileConflictEvent;

public class OnEventResponse implements SafeParcelable {
    public static final Parcelable.Creator<OnEventResponse> CREATOR = new aj();
    final int In;
    final ChangeEvent Jv;
    final FileConflictEvent Jw;
    final int xJ;

    OnEventResponse(int versionCode, int eventType, ChangeEvent changeEvent, FileConflictEvent conflictEvent) {
        this.xJ = versionCode;
        this.In = eventType;
        this.Jv = changeEvent;
        this.Jw = conflictEvent;
    }

    public int describeContents() {
        return 0;
    }

    public int getEventType() {
        return this.In;
    }

    public ChangeEvent gr() {
        return this.Jv;
    }

    public FileConflictEvent gs() {
        return this.Jw;
    }

    public void writeToParcel(Parcel dest, int flags) {
        aj.a(this, dest, flags);
    }
}
