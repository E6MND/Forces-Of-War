package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import com.google.android.gms.internal.hn;

public class CreateFileRequest implements SafeParcelable {
    public static final Parcelable.Creator<CreateFileRequest> CREATOR = new j();
    final MetadataBundle IA;
    final Integer IB;
    final DriveId IC;
    final boolean IE;
    final Contents It;
    final String Iv;
    final int xJ;

    CreateFileRequest(int versionCode, DriveId parentDriveId, MetadataBundle metadata, Contents contentsReference, Integer fileType, boolean shouldReportConflict, String operationTag) {
        this.xJ = versionCode;
        this.IC = (DriveId) hn.f(parentDriveId);
        this.IA = (MetadataBundle) hn.f(metadata);
        if (fileType == null || fileType.intValue() == 0) {
            this.It = (Contents) hn.f(contentsReference);
        } else {
            this.It = null;
        }
        this.IB = fileType;
        this.IE = shouldReportConflict;
        this.Iv = operationTag;
    }

    public CreateFileRequest(DriveId parentDriveId, MetadataBundle metadata, Contents contentsReference, int fileType, boolean shouldReportConflict, String operationTag) {
        this(2, parentDriveId, metadata, contentsReference, Integer.valueOf(fileType), shouldReportConflict, operationTag);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        j.a(this, dest, flags);
    }
}
