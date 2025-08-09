package com.google.android.gms.drive.events;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import java.util.ArrayList;

public final class FileConflictEvent implements SafeParcelable, DriveEvent {
    public static final Parcelable.Creator<FileConflictEvent> CREATOR = new c();
    final DriveId Hw;
    final ParcelFileDescriptor Ig;
    final ParcelFileDescriptor Ih;
    final MetadataBundle Ii;
    final ArrayList<String> Ij;
    private boolean Ik = false;
    private boolean Il = false;
    private boolean Im = false;
    final int xJ;
    final String yN;

    FileConflictEvent(int versionCode, DriveId driveId, String accountName, ParcelFileDescriptor baseParcelFileDescriptor, ParcelFileDescriptor modifiedParcelFileDescriptor, MetadataBundle modifiedMetadataBundle, ArrayList<String> operationTags) {
        this.xJ = versionCode;
        this.Hw = driveId;
        this.yN = accountName;
        this.Ig = baseParcelFileDescriptor;
        this.Ih = modifiedParcelFileDescriptor;
        this.Ii = modifiedMetadataBundle;
        this.Ij = operationTags;
    }

    public int describeContents() {
        return 0;
    }

    public DriveId getDriveId() {
        return this.Hw;
    }

    public int getType() {
        return 2;
    }

    public String toString() {
        return String.format("FileConflictEvent [id=%s]", new Object[]{this.Hw});
    }

    public void writeToParcel(Parcel dest, int flags) {
        c.a(this, dest, flags);
    }
}
