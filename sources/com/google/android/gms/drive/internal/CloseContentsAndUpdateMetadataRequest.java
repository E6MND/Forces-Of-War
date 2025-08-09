package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

public class CloseContentsAndUpdateMetadataRequest implements SafeParcelable {
    public static final Parcelable.Creator<CloseContentsAndUpdateMetadataRequest> CREATOR = new e();
    final DriveId Ir;
    final MetadataBundle Is;
    final Contents It;
    final boolean Iu;
    final String Iv;
    final int xJ;

    CloseContentsAndUpdateMetadataRequest(int versionCode, DriveId id, MetadataBundle metadataChangeSet, Contents contentsReference, boolean shouldDetectConflicts, String operationTag) {
        this.xJ = versionCode;
        this.Ir = id;
        this.Is = metadataChangeSet;
        this.It = contentsReference;
        this.Iu = shouldDetectConflicts;
        this.Iv = operationTag;
    }

    public CloseContentsAndUpdateMetadataRequest(DriveId id, MetadataBundle metadataChangeSet, Contents contentsReference, boolean shouldDetectConflicts, String operationTag) {
        this(1, id, metadataChangeSet, contentsReference, shouldDetectConflicts, operationTag);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        e.a(this, dest, flags);
    }
}
